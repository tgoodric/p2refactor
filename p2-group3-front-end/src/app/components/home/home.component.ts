import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';
import { Pokemon } from 'src/app/models/pokemon';
import { PokemonService } from 'src/app/services/pokemon.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  private url:string = "http://localhost:8090/"

  constructor(private router: Router, private cs:CookieService, private http:HttpClient, private ps:PokemonService) { }

  ngOnInit(): void {
  }

  public narratorText:String = "I am Professor Oak, and this is my Pokémon research laboratory. The Pokémon of the Kanto region have been very active in recent months, causing havoc amongst the townsfolk. The mayor has put out a call for help to Pokémon trainers from across Sinnoh. To attract Pokémon trainers such as yourself, the town will soon be hosting a tournament of champions. You must train and prepare for the upcoming challenge if you wish to win the grand prize!"
                              + " The random encounters outside of town are dangerous to those unprepared. Use caution when venturing beyond the town gates. However, exploring the areas around town will allow a young trainer like you to capture rare and powerful Pokémon for the upcoming tournament. Good luck in your journey adventurer...you will need it. If you need to restock items to heal your Pokémon, return and speak with me.";
  public actionText:String = "";
  public randNum:number = 0;

  getItemHandler(){   
    this.randNum = Math.floor(Math.random() * 10) + 1;
    let userId = parseInt(this.cs.get("userId"));
    if(this.randNum >= 7){
      this.actionText = "You ask Professor Oak for some potions. He opens his bag and hands you a Super Potion."; 
      this.ps.addItem(userId, "superpotions");
    } else if (this.randNum <= 4) {
      this.actionText = "You ask Professor Oak for some potions. He opens his bag and hands you two Potions."; 
      this.ps.addItem(userId, "potions");
      this.ps.addItem(userId, "potions");
    } else {
      this.actionText = "Professor Oak's lab is locked. A sign on the door reads \"Out to lunch, back soon\"";
    }
  }

  startBattleHandler(){
    this.randNum = Math.floor(Math.random() * 10) + 1;
    if(this.randNum >= 7){
      this.actionText = "You search for a pokemon to battle. No luck finding one, though." //will update text later
    } else if (this.randNum <= 4){
      this.actionText = "You don't see any pokemon around to battle."
    } else {
      this.router.navigate(['/battles']);
    }
  }

  async pokemonCenter(){
    let userId:string = this.cs.get("userId");  //don't actually need to convert to number
    let response = await fetch(this.url + "pokemon/" + userId, { //TODO: use userId
      method: "GET",
      credentials:"include"
    });
    let pokeList:Pokemon[] = await response.json(); //parse response into object
    console.log(pokeList); //TODO: delete debug print line
    for (const pokemon of pokeList) {
      if(pokemon.maxHitPoints !== pokemon.hitPoints){
        pokemon.hitPoints = pokemon.maxHitPoints;
        let data = JSON.stringify(pokemon);
        await fetch(this.url + "pokemon/update", {
          method:"PUT",
          body:data,
          credentials:"include"
        }) //end fetch
      }// end if
    } //end for

    this.actionText = "You visit the Pokemon Center. The nurse smiles at you and heals your pokemon."
  }
}
