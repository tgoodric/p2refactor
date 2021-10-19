import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-forest',
  templateUrl: './forest.component.html',
  styleUrls: ['./forest.component.css']
})
export class ForestComponent implements OnInit {

  //component variables
  public actionText:String = ""
  public randNum:number = 0;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  pickBerriesHandler(){   
    this.randNum = Math.floor(Math.random() * 10) + 1;
    if(this.randNum >= 7){
      this.actionText = "You Picked berries"; // test text for now can update later
      console.log(this.randNum);
    } else if (this.randNum <= 4) {
      this.actionText = "You Picked berries2"; // test text for now can update later
      //add potions to database below 
      console.log(this.randNum);

    } else {
      this.actionText = "You Picked berries3"; // test text for now can update later
      //add super_potions to database below 
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
