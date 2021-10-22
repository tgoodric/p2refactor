import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { PokemonService } from 'src/app/services/pokemon.service';

@Component({
  selector: 'app-river',
  templateUrl: './river.component.html',
  styleUrls: ['./river.component.css']
})
export class RiverComponent implements OnInit {

  public actionText:String = ""
  public randNum:number = 0;
  public cookieValue:string;
  public trainer_id:number = 0;
  public itemString:string = "";
  public narratorText:String = "The sound of the flowing river is calming as you approach. The water is clear and teeming with a variety of fish. " +
                               "A bench near the riverbank is available to rest after a long journey. However, in the sand, you spot tracks from many of Pokémon. " + 
                               "Better keep vigilant of the surroundings things may not be as inviting as it seems. The townspeople could use extra fish, and a cast net is lying nearby. " +
                               "A shimmering light can also be seen deep in the water. The area would also be perfect for searching for rare water-type Pokémon.";

                               
  constructor(private router: Router,
    private cookieService: CookieService,
    private pokemonService: PokemonService 
  ) { 

    this.cookieValue = this.cookieService.get('userId');
  }

  ngOnInit(): void {
  }

  fishHandler(){   
    this.randNum = Math.floor(Math.random() * 10) + 1;
    if(this.randNum >= 7){
      this.actionText = "You fished"; // test text for now can update later

      if(this.randNum === 10){
        //add item to database
        this.trainer_id = parseInt(this.cookieValue);
        this.itemString = "potions";
        this.pokemonService.addItem(this.trainer_id, this.itemString);
        this.actionText = "You found a Potion!"
      } 
      
    } else if (this.randNum <= 4) {
      this.actionText = "fishing"; // test text for now can update later
      
      if(this.randNum === 1){
        //add item to database
        this.trainer_id = parseInt(this.cookieValue);
        this.itemString = "pokeball";
        this.pokemonService.addItem(this.trainer_id, this.itemString);
        this.actionText = "You found a Pokeball!"
      } 
      
    } else {
      //this.router.navigate(['/battles']);
    }
  }

  swimHandler(){
    this.randNum = Math.floor(Math.random() * 10) + 1;
    if(this.randNum >= 7){
      this.actionText = "swimming" //will update text later

      if(this.randNum === 10){
        //add item to database
        this.trainer_id = parseInt(this.cookieValue);
        this.itemString = "superpotions";
        this.pokemonService.addItem(this.trainer_id, this.itemString);
        this.actionText = "You found a Super Potion!"
      } 

    } else if (this.randNum <= 4){
      this.actionText = "swimming2"

      if(this.randNum === 1){
        //add item to database
        this.trainer_id = parseInt(this.cookieValue);
        this.itemString = "pokeballs";
        this.pokemonService.addItem(this.trainer_id, this.itemString);
        this.actionText = "You found a Pokeball!"
      } 

    } else {
      this.router.navigate(['/battles']);
    }
  }

  startBattleHandler(){
    this.randNum = Math.floor(Math.random() * 10) + 1;
    if(this.randNum >= 7){
      this.actionText = "pokemon not found" //will update text later

      if(this.randNum === 10){
        //add item to database
        this.trainer_id = parseInt(this.cookieValue);
        this.itemString = "potions";
        this.pokemonService.addItem(this.trainer_id, this.itemString);
        this.actionText = "You found a Potion!"
      } 

    } else if (this.randNum <= 4){
      this.actionText = "pokemon not found 2"
    } else {
      this.router.navigate(['/battles']);
    }
  }
}
