import {
  OnInit,
  Component,
  ViewChild
} from "@angular/core";
import { MessageApp } from "src/app/utils/messages";
import { Player } from "src/app/core/models/player";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { MatPaginator } from "@angular/material/paginator";
import { MatSort } from "@angular/material/sort";
import { MatTableDataSource } from "@angular/material/table";
import { SelectionModel } from "@angular/cdk/collections";
import { formatDate } from "@angular/common";
import { SoccerGames } from "src/app/core/models/soccer-games";
import { PlayerService } from "src/app/core/services/player.service";
import { NotificationService } from "src/app/core/services/shared/notification.service";
import { SoccerGamesService } from "src/app/core/services/soccer-games.service";

@Component({
  selector: 'app-soccer-games',
  templateUrl: './soccer-games.component.html',
  styleUrls: ['./soccer-games.component.css']
})
export class SoccerGamesComponent implements OnInit {
  soccerGamesForm: FormGroup;
  displayedColumns: string[] = [
    "select",
    "name"
  ];
  dataSource: MatTableDataSource<Player>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  players = new SelectionModel<Player>(true, []);
  selectedValue: string;
  selectedCar: string;

  selectedField: string = "CAMPO_AMOR";
  selectedFieldNumber: string = "2B";
  selectedPrice: string = "6500";

  constructor(
    private fb: FormBuilder,
    private service: SoccerGamesService,
    private playerService: PlayerService,
    public notificationService: NotificationService
  ) { }

  ngOnInit(): void {
    this.buildForm();
    this.getAllPlayers();
  }
  buildForm() {
    this.soccerGamesForm = this.fb.group({
      id: [null], // Assuming id can be null for a new SoccerGame
      date: [formatDate(new Date, 'yyyy-MM-dd', 'en'), Validators.required],
      time: ['21:00:00', Validators.required],
      field: ['CAMPO_AMOR', Validators.required],
      fieldNumber: ['2A', Validators.required],
      price: ["6500.00", Validators.required],
      playerNumber: ['18', Validators.required],
      description: ['Ninguna', null],
      players: [this.players, Validators.required], // Nested form array for players
      status: ['CONFIRMADA', Validators.required],
      enable: [true, Validators.required], // Default value set to true
    });
  }

  getAllPlayers() {
    this.playerService.getAll().subscribe({
      complete: () => console.info("complete getAllPlayers"),
      error: (err) => {
        this.notificationService.error(err.error.message, 'Close');
      },
      next: (response) => {
        this.dataSource = new MatTableDataSource(response);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      },
    });
  }

  saveSoccerGames(data: SoccerGames) {
    this.service.create(data).subscribe({
      complete: () => console.info("complete save Soccer Games"),
      error: (err) => {
        this.notificationService.error(err.error.message, 'Close');
      },
      next: (resp: any) => {

        this.notificationService.success(MessageApp.GENERIC_MESSAGE_CREATED);

        this.reloadPage();
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

  selectHandler(row: Player) {
    this.players.toggle(row);
  }

  onSubmit() {

    if(this.players.selected.length == 0){
      this.notificationService.error('You must select players for soccer games');
      return;
    }

    if (!this.soccerGamesForm.valid) {
      // Handle form errors
      this.notificationService.error('You must review the required fields', 'Close');
      return;
    }

    if (this.soccerGamesForm.valid) {
      this.soccerGamesForm.patchValue({players: this.players.selected});
      this.saveSoccerGames(this.soccerGamesForm.value);
    }

  }

  reloadPage() {
    setTimeout(()=>{
      window.location.reload();
    }, 1000);
  }
}
