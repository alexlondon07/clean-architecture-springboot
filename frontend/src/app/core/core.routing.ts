import { Routes } from '@angular/router';
import { BrandListComponent } from './components/brand/brand-list/brand-list.component';
import { CategoryListComponent } from './components/category/list/category-list.component';
import { LoginGuard } from './guards/login-guard';
import { PlayerListComponent } from './components/player/list/player-list.component';
import { SoccerGamesViewComponent } from './components/soccer-games/view/soccer-games-view.component';
import { SoccerGamesComponent } from './components/soccer-games/create/soccer-games.component';
import { SoccerGamesListComponent } from './components/soccer-games/list/soccer-games-list.component';



export const CoreRoutes: Routes = [
  {
    path: 'categories',
    component: CategoryListComponent,
    canActivate: [LoginGuard],
  },
  {
    path: 'brands',
    canActivate: [LoginGuard],
    component: BrandListComponent
  },
  {
    path: 'players',
    canActivate: [LoginGuard],
    component: PlayerListComponent
  },
  {
    path: 'soccer-games-create',
    canActivate: [LoginGuard],
    component: SoccerGamesComponent
  },
  {
    path: 'soccer-games-list',
    canActivate: [LoginGuard],
    component: SoccerGamesListComponent
  },
];
