import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public cookieValue:string;
  constructor(private cookieService: CookieService) { 
    this.cookieValue = this.cookieService.get('userId');
  }

  ngOnInit(): void {
  }

}
