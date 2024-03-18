import { Injectable } from '@angular/core';
import { SoccerGames } from '../models/soccer-games';
import { environment } from 'src/environments/environment';
import { CommonService } from './common.service';

@Injectable({
  providedIn: 'root'
})
export abstract class SoccerGamesService extends CommonService<SoccerGames> {
  protected baseEndpoint = `${environment.urlBackendSpringBoot}/${environment.v1}/soccer-games`;
}
