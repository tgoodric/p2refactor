import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cave',
  templateUrl: './cave.component.html',
  styleUrls: ['./cave.component.css']
})
export class CaveComponent implements OnInit {

  public actionText:String = ""
  public randNum:number = 0;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  rockHandler(){   
    this.randNum = Math.floor(Math.random() * 10) + 1;
    if(this.randNum >= 7){
      this.actionText = "You looked under rock"; // test text for now can update later
      
    } else if (this.randNum <= 4) {
      this.actionText = "You looked under rock2"; // test text for now can update later
      //add potions to database below 
     

    } else {
      this.router.navigate(['/battles']);
    }
  }

  mineHandler(){
    this.randNum = Math.floor(Math.random() * 10) + 1;
    if(this.randNum >= 7){
      this.actionText = "mined ore" //will update text later
    } else if (this.randNum <= 4){
      this.actionText = "mined ore2"
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
