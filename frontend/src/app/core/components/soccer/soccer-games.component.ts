import { NotificationService } from './../../services/shared/notification.service';
import {
  OnInit,
  Component,
  ViewChild
} from "@angular/core";
import { MessageApp } from "src/app/utils/messages";
import { Player } from "src/app/core/models/player";
import { PlayerService } from "src/app/core/services/player.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { MatPaginator } from "@angular/material/paginator";
import { MatSort } from "@angular/material/sort";
import { MatTableDataSource } from "@angular/material/table";
import { SelectionModel } from "@angular/cdk/collections";
import { formatDate } from "@angular/common";

@Component({
  selector: 'app-soccer-games',
  templateUrl: './soccer-games.component.html',
  styleUrls: ['./soccer-games.component.css']
})
export class SoccerGamesComponent implements OnInit {
  typesOfShoes = ['Boots', 'Clogs', 'Loafers', 'Moccasins', 'Sneakers'];
  messages = [
    {
      from: 'Nirav joshi (nbj@gmail.com)',
      image: 'assets/images/users/1.jpg',
      subject: 'Material angular',
      content: 'This is the material angular template'
    },
    {
      from: 'Sunil joshi (sbj@gmail.com)',
      image: 'assets/images/users/2.jpg',
      subject: 'Wrappixel',
      content: 'We have wrappixel launched'
    },
    {
      from: 'Vishal Bhatt (bht@gmail.com)',
      image: 'assets/images/users/3.jpg',
      subject: 'Task list',
      content: 'This is the latest task hasbeen done'
    }
  ];

  folders = [
    {
      name: 'Photos',
      updated: new Date('1/1/16')
    },
    {
      name: 'Recipes',
      updated: new Date('1/17/16')
    },
    {
      name: 'Work',
      updated: new Date('1/28/16')
    }
  ];
  notes = [
    {
      name: 'Vacation Itinerary',
      updated: new Date('2/20/16')
    },
    {
      name: 'Kitchen Remodel',
      updated: new Date('1/18/16')
    }
  ];

  form: FormGroup;
  playerList: Player[] = [];
  displayedColumns: string[] = [
    "select",
    "name"
  ];
  dataSource: MatTableDataSource<Player>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  selection = new SelectionModel<Player>(true, []);
  selectedValue: string;
  selectedCar: string;

  selectedField: string = "CAMPO_AMOR";
  selectedFieldNumber: string = "2B";
  selectedPrice: string = "6500";

  constructor(
    private fb: FormBuilder,
    private service: PlayerService,
    public notificationService: NotificationService
  ) { }

  ngOnInit(): void {
    this.buildForm();
    this.getAllPlayers();
  }
  buildForm() {
    this.form = this.fb.group({
      date: [formatDate(new Date, 'yyyy-MM-dd', 'en'), Validators.required],
      time: ['21:00:00', Validators.required],
      field: ['CAMPO_AMOR', Validators.required],
      price: [6500, Validators.required],
      playerNumber: ['18', Validators.required],
      description: ['Ninguna', null]
    });
  }

  getAllPlayers() {
    this.service.getAll().subscribe({
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

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  selectHandler(row: Player) {
    this.selection.toggle(row);
  }

  onSubmit() {
    if (this.form.valid) {
      // Handle form submission
      console.log(this.form.value);
    } else {
      // Handle form errors
      console.error('Form is invalid');
    }
  }
}
