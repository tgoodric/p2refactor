import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { NavbarService } from 'src/app/services/navbar.service';
import { PokemonService } from 'src/app/services/pokemon.service';

@Component({
  selector: 'app-cave',
  templateUrl: './cave.component.html',
  styleUrls: ['./cave.component.css']
})

export class CaveComponent implements OnInit {

  public actionText:String = ""
  public randNum:number = 0;
  public cookieValue:string;
  public narratorText:String = "The air in the cave is thick and damp upon entering. A foul smell emanates from the depths that causes your stomach to turn. " +
                               "Undeterred, you courageously step deeper into the cave. As the outside light fades behind you, a loud screech pierces the silence, and your heart beings to race. " + 
                               "You feel as though you may be attacked at any moment. Venturing deeper a strange rock catches your eye with a tattered piece of cloth jumbled beneath it. " +
                               "A vein of Armorite ore also shines along the wall as the flame of your torch flickers. Searching the depths of the cave could also reveal the rarest Pokémon of them all.";
  public trainer_id:number = 0;
  public itemString:string = "";

  constructor(private nav: NavbarService,private pokemonService: PokemonService,private router: Router,
    private cookieService: CookieService
    ) { 
      this.cookieValue = this.cookieService.get('userId');
    }

  ngOnInit(): void {
    this.nav.show();
  }

  rockHandler(){   
    this.randNum = Math.floor(Math.random() * 10) + 1;
    if(this.randNum >= 7){
      this.actionText = "You look under the rock. The tattered cloth unfolds."; // test text for now can update later

      if(this.randNum === 10){
        //add item to database
        this.trainer_id = parseInt(this.cookieValue);
        this.itemString = "potions";
        this.pokemonService.addItem(this.trainer_id, this.itemString);
        this.actionText += this.actionText + " You found a Potion!"
      } 
      else{
        this.actionText += " You find a handful of coins, but not much else."
      }
      
    } else if (this.randNum <= 4) {
      this.actionText = "You look under the rock."; // test text for now can update later
      
      if(this.randNum === 1){
        //add item to database
        this.trainer_id = parseInt(this.cookieValue);
        this.itemString = "pokeballs";
        this.pokemonService.addItem(this.trainer_id, this.itemString);
        this.actionText += " You find a Pokeball!"
      } 
      else{
        this.actionText += " There's nothing there but dirt."
      }
     
    } else {
      this.router.navigate(['/battles']);
    }
  }

  mineHandler(){
    this.randNum = Math.floor(Math.random() * 10) + 1;
    if(this.randNum >= 7){
      this.actionText = "As you mine ore, a small box covered in mud catches your eye. As you open it, " //will update text later

      if(this.randNum === 10){
        //add item to database
        this.trainer_id = parseInt(this.cookieValue);
        this.itemString = "superpotions";
        this.pokemonService.addItem(this.trainer_id, this.itemString);
        this.actionText += "you find a Super Potion!"
      } 
      else{
        this.actionText += "you find half an old sandwich and an apple core."
      }

    } else if (this.randNum <= 4){
      this.actionText = "Your swing misses its mark, gouging a hole in the cave floor. Looking inside, "

      if(this.randNum === 1){
        //add item to database
        this.trainer_id = parseInt(this.cookieValue);
        this.itemString = "potions";
        this.pokemonService.addItem(this.trainer_id, this.itemString);
        this.actionText += "you find a buried Potion!"
      } 
      else{
        this.actionText += "you find mud and not much else."
      }

    } else {
      this.router.navigate(['/battles']);
    }
  }

  startBattleHandler(){
    this.randNum = Math.floor(Math.random() * 10) + 1;
    if(this.randNum >= 7){
      this.actionText = "Despite all the evidence of pokemon living in this cave, none appear." //will update text later

      if(this.randNum === 10){
        //add item to database
        this.trainer_id = parseInt(this.cookieValue);
        this.itemString = "potions";
        this.pokemonService.addItem(this.trainer_id, this.itemString);
        this.actionText += " However, you do find a Potion!"
      } 
      
    } else if (this.randNum <= 4){
      this.actionText = "What you thought was the cry of a wild pokemon turned out to have been the wind echoing in the cave."
    } else {
      this.router.navigate(['/battles']);
    }
  }

}
