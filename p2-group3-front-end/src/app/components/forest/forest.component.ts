import { ɵparseCookieValue } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { PokemonService } from 'src/app/services/pokemon.service';

@Component({
  selector: 'app-forest',
  templateUrl: './forest.component.html',
  styleUrls: ['./forest.component.css']
})
export class ForestComponent implements OnInit {

  //component variables
  public narratorText:String = "As you enter the forest canopy, a sense of dread overcomes you. " +
                               "The eyes of many Pokémon peer at you from the distant bushes. " +
                               "The trees creak eerily as the wind whips through the branches above. Fallen leaves at your feet swirl as you move deep into the forest as if blown by some unknown force. " +
                               "Far off, you can see many of the coveted Enigma berries ripe for picking. Professor Oak also requested you collect firewood to help the townspeople. " +
                               "You also see the tracks of a rare Pokémon leading deeper into the woods.";

  public actionText:String = ""
  public randNum:number = 0;
  public backgroundUrl:any = "../assets/"
  public cookieValue:string;
  public trainer_id:number = 0;
  public itemString:string = "";

  constructor(private pokemonService: PokemonService,private router: Router,
    private cookieService: CookieService
    ) { 
      this.cookieValue = this.cookieService.get('userId');
    }

  ngOnInit(): void {
  }
  
  pickBerriesHandler(){   
    this.randNum = Math.floor(Math.random() * 10) + 1;
    if(this.randNum >= 7){
      this.actionText = "You Picked rawst berries"; // test text for now can update later
      
      if(this.randNum === 10){
        //add item to database
        console.log(this.randNum);
        this.trainer_id = parseInt(this.cookieValue);
        this.itemString = "potions";
        console.log(this.trainer_id);
        console.log(this.itemString);
        this.pokemonService.addItem(this.trainer_id, this.itemString);
        this.actionText = "You found a Potion!"
      } 

    } else if (this.randNum <= 4) {
      if(this.randNum === 3){
        //add potions to database below 
        console.log(this.randNum)
        this.actionText = "You found a Potion!"
      }
      this.actionText = "You Picked berries2"; // test text for now can update later
    } else {
      //this.router.navigate(['/battles']);
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
