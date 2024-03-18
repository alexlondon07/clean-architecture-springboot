import { environment } from 'src/environments/environment';
import { Injectable } from '@angular/core';
import { CommonService } from './common.service';
import { Player } from '../models/player';

@Injectable({
  providedIn: 'root'
})
export abstract class PlayerService extends CommonService<Player> {
  protected baseEndpoint = `${environment.urlBackendSpringBoot}/${environment.v1}/players`;
}
