import { Component, OnInit } from '@angular/core';
import { RegistrationService } from 'src/app/services/registration.service';
import { UserDto } from 'src/app/models/user-dto';
import { NavbarService } from 'src/app/services/navbar.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public username:string = "";
  public password:string = "";

  constructor(private nav: NavbarService,private rs:RegistrationService) { }

  ngOnInit(): void {
    this.nav.hide();
  }

  register():void{
    if ((this.username == "") || (this.username == "")){
      alert("Please enter both a username and a password.");
      return;
    }
    else{
      console.log(this.username + this.password);
      this.rs.register(this.username, this.password);
      
    }
  }

}
