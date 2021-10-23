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
      this.actionText = "You feel something caught in your net. "; // test text for now can update later

      if(this.randNum === 10){
        //add item to database
        this.trainer_id = parseInt(this.cookieValue);
        this.itemString = "potions";
        this.pokemonService.addItem(this.trainer_id, this.itemString);
        this.actionText += "As you pull it in, you find a potion."
      } 
      else{
        this.actionText += "As you pull it in, your net snags on a log.";
      }
      
    } else if (this.randNum <= 4) {
      this.actionText = "You have no luck catching any fish. As you leave, "; // test text for now can update later
      
      if(this.randNum === 1){
        //add item to database
        this.trainer_id = parseInt(this.cookieValue);
        this.itemString = "pokeball";
        this.pokemonService.addItem(this.trainer_id, this.itemString);
        this.actionText += "you find a Pokeball!";
      } 
      else{
        this.actionText += "you curse your bad luck.";
      }
      
    } else {
      this.router.navigate(['/battles']);
    }
  }

  swimHandler(){
    this.randNum = Math.floor(Math.random() * 10) + 1;
    if(this.randNum >= 7){
      this.actionText = "As you dive into the river something catches your eye. ";

      if(this.randNum === 10){
        //add item to database
        this.trainer_id = parseInt(this.cookieValue);
        this.itemString = "superpotions";
        this.pokemonService.addItem(this.trainer_id, this.itemString);
        this.actionText += "You find a Super Potion!";
      } 
      else{
        this.actionText += "You grab the glinting stone and skip it across the river.";
      }
    } else if (this.randNum <= 4){
      this.actionText = "As you surface, you spot "

      if(this.randNum === 1){
        //add item to database
        this.trainer_id = parseInt(this.cookieValue);
        this.itemString = "pokeballs";
        this.pokemonService.addItem(this.trainer_id, this.itemString);
        this.actionText += "a Pokeball that another trainer dropped"
      } 
      else{
        this.actionText += "a \"No Swimming\" sign. Oops.";
      }

    } else {
      this.router.navigate(['/battles']);
    }
  }

  startBattleHandler(){
    this.randNum = Math.floor(Math.random() * 10) + 1;
    if(this.randNum >= 7){
      this.actionText = "The Magikarp jump happily, seemingly mocking you. ";

      if(this.randNum === 10){
        //add item to database
        this.trainer_id = parseInt(this.cookieValue);
        this.itemString = "potions";
        this.pokemonService.addItem(this.trainer_id, this.itemString);
        this.actionText += "Oh well, at least you found a Potion!";
      } 
      else{
        this.actionText += "You sit down on the bench and watch their futile struggle to make it upstream.";
      }

    } else if (this.randNum <= 4){
      this.actionText = "Some pokemon run back into the bushes as they spot you.";
    } else {
      this.router.navigate(['/battles']);
    }
  }
}
