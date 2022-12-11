import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Group } from '../models/group';

@Injectable({
  providedIn: 'root'
})
export class GroupService {
  url: string  = "http://localhost:8080/RestAPI_war_exploded/GroupServlet"
  constructor(private httpClient: HttpClient) { }

  insert(group: Group): Observable<any>{
    return this.httpClient.post(this.url, group);
  }

  getGroups(): Observable<any>{
    return this.httpClient.get(this.url);
  }

  getFiltred(searchText: string): Observable<any>{
    if(searchText === ""){
      return this.getGroups();
    }
    return this.httpClient.get(this.url+"?searchText="+searchText);
  }
}
