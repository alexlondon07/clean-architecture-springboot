import { Routes } from '@angular/router';
import { BrandListComponent } from './components/brand/brand-list/brand-list.component';
import { CategoryListComponent } from './components/category/list/category-list.component';


export const CoreRoutes: Routes = [
  {
    path: 'categories',
    component: CategoryListComponent
  },
  {
    path: 'brands',
    component: BrandListComponent
  }

];
