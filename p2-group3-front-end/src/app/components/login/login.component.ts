import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  constructor(private ls:LoginService) { }

  public username:string = "";
  public password:string = "";

  public user:any = null;

  ngOnInit(): void {
    
  }//end onInit
  
  login():void{
    console.log(this.username + this.password);
    this.ls.login(this.username,this.password);
  }//end
  
}
