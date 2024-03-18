import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { Generic } from "../models/generic";
import { AuthService } from "./auth.service";

@Injectable({
  providedIn: "root",
})
export abstract class CommonService<E> {

  protected baseEndpoint: string;

  protected headers: HttpHeaders = new HttpHeaders({
    "Content-type": "application/json",
    Authorization: `Bearer ${this.authService.getTokenSession()}`,
  });

  constructor(protected http: HttpClient, protected authService: AuthService) {}

  public getAll(): Observable<any> {
    return this.http.get<E[]>(`${this.baseEndpoint}`, {
      headers: this.headers,
    });
  }

  public getAllPages(page: string, size: string): Observable<any> {
    /*       const params = new HttpParams()
        .set('page', page)
        .set('size', size) */
    return this.http.get<any>(`${this.baseEndpoint}/page/${page}/${size}`, {
      headers: this.headers,
    });
  }

  public getAllPagesWithText(
    page: string,
    size: string,
    text: string
  ): Observable<any> {
    /*       const params = new HttpParams()
        .set('page', page)
        .set('size', size)
        .set('text', text) */
    return this.http.get<any>(
      `${this.baseEndpoint}/page/${page}/${size}/${text}`,
      { headers: this.headers }
    );
  }

  public getById(id: number): Observable<E> {
    return this.http.get<E>(`${this.baseEndpoint}/${id}`, {
      headers: this.headers,
    });
  }

  public create(data: E): Observable<E> {
    return this.http.post<E>(`${this.baseEndpoint}/`, data, {
      headers: this.headers,
    });
  }

  public update(data: any): Observable<E> {
    return this.http.put<E>(`${this.baseEndpoint}/${data["id"]}`, data, {
      headers: this.headers,
    });
  }

  public delete(id: number): Observable<E> {
    return this.http.delete<E>(`${this.baseEndpoint}/${id}`, {
      headers: this.headers,
    });
  }

  public enable(id: number, enable: Boolean): Observable<any> {
    return this.http.get<E>(`${this.baseEndpoint}/${id}/${enable}`, {
      headers: this.headers,
    });
  }
}
