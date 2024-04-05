import { Component, Inject, OnInit, Optional } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { SoccerGames } from '../../../models/soccer-games';
import { PlayerFormComponent } from '../../player/create-edit/player-form.component';

@Component({
  selector: 'app-soccer-games-view',
  templateUrl: './soccer-games-view.component.html',
  styleUrls: ['./soccer-games-view.component.css']
})
export class SoccerGamesViewComponent implements OnInit {

  id: any;
  soccerGames: SoccerGames = new SoccerGames();

  constructor(
    private route: ActivatedRoute,
    public dialogRef: MatDialogRef<PlayerFormComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public data: any
    ) {
      this.soccerGames = data;
    }

  ngOnInit() {
    // snapshot way
    console.log('thing', this.route.snapshot.params.id);

    // observable way
    this.route.paramMap.subscribe(params => {
        console.log(params.get('id'));
        this.id = params.get('id');
    });
  }

}
