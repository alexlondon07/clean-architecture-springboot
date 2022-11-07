import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { CommonService } from './common.service';
import { Category } from '../models/category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService extends CommonService<Category> {

  protected baseEnpoint = `${environment.urlBackendSpringBoot}/${environment.v1}/categories`;

}
