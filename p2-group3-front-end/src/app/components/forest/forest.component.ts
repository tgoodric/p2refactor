import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-forest',
  templateUrl: './forest.component.html',
  styleUrls: ['./forest.component.css']
})
export class ForestComponent implements OnInit {

  public actionText:String = ""

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  pickBerriesHandler(){
    this.actionText = "You Picked berries"; // test text for now can update later
    //add potions or super_potions to database below 

  }

  collectFirewoodHandler(){
    this.actionText = "chopping down tree" //will update text later

  }

  startBattleHandler(){
    //this.router.navigate['/battles']; //not sure why this doesnt work but looking into it
  }

}
