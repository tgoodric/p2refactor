import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-forest',
  templateUrl: './forest.component.html',
  styleUrls: ['./forest.component.css']
})
export class ForestComponent implements OnInit {

  //component variables
  public narratorText:String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et" +
                               "dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex" +
                               "ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

  public actionText:String = ""
  public randNum:number = 0;
  public backgroundUrl:any = "../assets/"
  public cookieValue:string;

  constructor(private router: Router,
    private cookieService: CookieService
    ) { 
      this.cookieValue = this.cookieService.get('userId');
    }

  ngOnInit(): void {
  }

  pickBerriesHandler(){   
    this.randNum = Math.floor(Math.random() * 10) + 1;
    if(this.randNum >= 7){
      this.actionText = "You Picked berries"; // test text for now can update later
      
    } else if (this.randNum <= 4) {
      this.actionText = "You Picked berries2"; // test text for now can update later
      //add potions to database below 
     

    } else {
      this.router.navigate(['/battles']);
    }
  }

  collectFirewoodHandler(){
    this.randNum = Math.floor(Math.random() * 10) + 1;
    if(this.randNum >= 7){
      this.actionText = "chopping down tree" //will update text later
    } else if (this.randNum <= 4){
      this.actionText = "chopping down tree2"
    } else {
      this.router.navigate(['/battles']);
    }
  }

  startBattleHandler(){
    this.randNum = Math.floor(Math.random() * 10) + 1;
    if(this.randNum >= 7){
      this.actionText = "pokemon not found" //will update text later
    } else if (this.randNum <= 4){
      this.actionText = "pokemon not found 2"
    } else {
      this.router.navigate(['/battles']);
    }
  }

}
