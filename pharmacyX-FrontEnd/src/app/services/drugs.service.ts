import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Drug } from '../model/drug';

@Injectable({
  providedIn: 'root'
})
export class DrugsService {

  constructor(private http: HttpClient) { }

  public loadDrugs() : Observable<Drug[]>{
    return this.http.get<Drug[]>('http://localhost:9003/drugs'); 
  }
}
