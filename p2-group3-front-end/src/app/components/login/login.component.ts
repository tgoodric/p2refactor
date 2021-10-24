import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { LoginService } from 'src/app/services/login.service';
import { NavbarService } from 'src/app/services/navbar.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  constructor(public nav: NavbarService,private ls:LoginService, private router:Router) { }

  public username:string = "";
  public password:string = "";

  public user:any = null;

  ngOnInit(): void {
    this.nav.hide();
  }//end onInit
  
  login():void{
    console.log(this.username + this.password);
    this.ls.login(this.username,this.password);

    
  }//end
  
  register(){
    this.router.navigate(['/register']);
  }
}
