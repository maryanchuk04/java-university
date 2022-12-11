import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Student } from '../models/student';

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  urt: string = 'http://localhost:8080/RestAPI_war_exploded/StudentServlet';

  constructor(private httpClient: HttpClient) { }

  getAllStudents(): Observable<any>{
    return this.httpClient.get(this.urt);
  }

  getFiltered(filtered: string){
    if(filtered == ""){
      return this.getAllStudents();
    }
    return this.httpClient.get(this.urt + "?searchText="+ filtered);
  }
}
