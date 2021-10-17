import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private url:string = "localhost:8090/project2/"

  constructor(private http:HttpClient) { }

  getUser() {
    return this.http.get(this.url + "login") as Observable<User>;
  }

}
