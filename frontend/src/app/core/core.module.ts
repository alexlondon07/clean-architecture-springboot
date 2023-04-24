import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { CdkTableModule } from '@angular/cdk/table';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';

// Components
import { CoreRoutes } from './core.routing';
import { DemoMaterialModule } from '../demo-material-module';
import { CUSTOM_ELEMENTS_SCHEMA }      from '@angular/core';

// Components
import { BrandListComponent } from './components/brand/brand-list/brand-list.component';
import { CategoryListComponent } from './components/category/list/category-list.component';
import { CategoryFormComponent } from './components/category/create-edit/category-form.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(CoreRoutes),
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    CdkTableModule,
    DemoMaterialModule
  ],
  entryComponents: [ CategoryFormComponent ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
  declarations: [
    BrandListComponent,
    CategoryListComponent,
    CategoryFormComponent,
  ]
})
export class CoreModule { }
