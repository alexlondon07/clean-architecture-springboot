import { Routes } from '@angular/router';
import { BrandListComponent } from './components/brand/brand-list/brand-list.component';
import { CategoryListComponent } from './components/category/list/category-list.component';
import { LoginGuard } from './guards/login-guard';


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
  }
];
