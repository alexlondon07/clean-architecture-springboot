import {
  OnInit,
  AfterViewInit,
  ChangeDetectorRef,
  Component,
  ViewChild,
} from "@angular/core";
import { MatTableDataSource } from "@angular/material/table";
import { MatPaginator } from "@angular/material/paginator";
import { MatSort } from "@angular/material/sort";
import { MatDialog } from "@angular/material/dialog";
import { DeleteDialogComponent } from "src/app/core/shared/dialogs/delete-dialog/delete-dialog.component";
import { MessageApp } from "src/app/utils/messages";
import { Player } from "src/app/core/models/player";
import { PlayerService } from "src/app/core/services/player.service";
import { PlayerFormComponent } from "src/app/core/components/player/create-edit/player-form.component";

@Component({
  selector: "app-player-list",
  templateUrl: "./player-list.component.html",
  styleUrls: ["./player-list.component.css"],
})
export class PlayerListComponent implements AfterViewInit {
  title: string = "Players";
  description: string = "Players list in the system";
  dataList: Player[] = [];
  displayedColumns: string[] = [
    "id",
    "name",
    "position",
    "cellphone",
    "photo",
    "icons",
  ];
  dataSource: MatTableDataSource<Player>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private service: PlayerService,
    public _dialog: MatDialog,
    private changeDetectorRefs: ChangeDetectorRef
  ) { }

  ngAfterViewInit() {
    this.init();
  }

  init() {
    this.getAllPlayers();
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  getAllPlayers() {
    this.service.getAll().subscribe({
      complete: () => console.info("complete getAllPlayers"),
      error: (err) => {
        console.log(err.error.message);
      },
      next: (response) => {
        this.dataSource = new MatTableDataSource(response);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        this.dataList = response;
      },
    });
  }

  delete(id: number) {
    let request = {
      title: 'Are you sure?',
      message: MessageApp.GENERIC_CONFIRMATION_MESSAGE_DELETE,
    };

    const dialogRef = this._dialog.open(DeleteDialogComponent, {
      data: request,
    });

    dialogRef.afterClosed().subscribe((confirmed: boolean) => {
      if (confirmed) {
        this.service.delete(id).subscribe({
          complete: () => console.info("complete delete"),
          error: (err) => {
            console.log(err.error.message);
          },
          next: (resp) => {
            this.init();
          },
        });
      }
    });
  }

  openDialog(data: any): void {
    const dialogRef = this._dialog.open(PlayerFormComponent, {
      width: "800px",
      disableClose: true,
      data: data,
    });
    dialogRef.afterClosed().subscribe((data) => {
      if (data) {
        console.log(
          "🚀 ~ PlayerListComponent ~ dialogRef.afterClosed ~ data:",
          data
        );
        if (data["event"] == "close") {
          this.init();
        }
      }
    });
  }
  create(): void {
    this.openDialog(null);
  }

  detectChanges() {
    this.changeDetectorRefs.detectChanges();
  }
}
