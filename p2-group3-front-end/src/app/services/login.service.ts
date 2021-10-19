import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import { UserDto } from '../models/user-dto';

@Injectable({
  providedIn: 'root'
})
export class LoginService {


  private url:string = "http://localhost:8090/"


  constructor(private http:HttpClient) { }

  getUser() {
    return this.http.get(this.url + "login") as Observable<User>;
  }

  async login(username:string, password:string) {
    console.log(username + password + "in service");
    let udto = new UserDto(username,password);
    console.log(udto);
    let response = await fetch(this.url + "login", {
      method: "POST",
      body: JSON.stringify(udto),
      credentials:"include"
    })
    if (response.status == 200){
      console.log("login successful");
      //redirect to the landing page
    }
    else{
      console.log("Username or password not found");
    }
  }
}
