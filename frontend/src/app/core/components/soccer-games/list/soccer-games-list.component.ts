import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { SoccerGames } from 'src/app/core/models/soccer-games';
import { SoccerGamesService } from 'src/app/core/services/soccer-games.service';
import { SoccerGamesViewComponent } from '../view/soccer-games-view.component';

@Component({
  selector: 'app-soccer-games-list',
  templateUrl: './soccer-games-list.component.html',
  styleUrls: ['./soccer-games-list.component.css']
})
export class SoccerGamesListComponent implements OnInit {
  title: string = "Soccer Games List";
  description: string = "Soccer Games list in the system";
  dataList: SoccerGames[] = [];
  displayedColumns: string[] = [
    "id",
    "date",
    "time",
    "field",
    "price",
    "playerNumber",
    "description",
    "status",
    "icons",
  ];
  dataSource: MatTableDataSource<SoccerGames>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;


  constructor(public service: SoccerGamesService, public _dialog: MatDialog) { }

  ngOnInit() {
    this.getAllSoccerGames();
  }

  getAllSoccerGames() {
    this.service.getAll().subscribe({
      complete: () => console.info("complete getAllPlayers"),
      error: (err) => {
        console.log(err.error.message);
      },
      next: (response) => {
        this.dataSource = new MatTableDataSource(response);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      },
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  showSoccerGames(data: any): void {

    const dialogRef = this._dialog.open(SoccerGamesViewComponent, {
      width: "900px",
      disableClose: false,
      data: data,
    });

    dialogRef.afterClosed().subscribe((data) => {
      if (data) {
        console.log(
          "🚀 ~ SoccerGamesViewComponent ~ dialogRef.afterClosed ~ data:",
          data
        );
        if (data["event"] == "close") {
        }
      }
    });
  }

}
