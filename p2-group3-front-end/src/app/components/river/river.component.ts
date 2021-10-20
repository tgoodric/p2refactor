import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-river',
  templateUrl: './river.component.html',
  styleUrls: ['./river.component.css']
})
export class RiverComponent implements OnInit {

  public actionText:String = ""
  public randNum:number = 0;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  fishHandler(){   
    this.randNum = Math.floor(Math.random() * 10) + 1;
    if(this.randNum >= 7){
      this.actionText = "You Picked berries"; // test text for now can update later
      
    } else if (this.randNum <= 4) {
      this.actionText = "You Picked berries2"; // test text for now can update later
      //add potions to database below 
     

    } else {
      this.actionText = "You Picked berries3"; // test text for now can update later
      this.router.navigate(['/battles']);
    }
  }

  swimHandler(){
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
