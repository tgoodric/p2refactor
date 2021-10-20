import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pokemon } from '../models/pokemon';

@Injectable({
  providedIn: 'root'
})

//Remember, the Services are what give functionality to our components
//We inject services into components, thus giving the component the service's data/functions 
export class PokemonService {

  private url:string = "http://localhost:8090/"
  
  //we dependency inject HttpClient in order to make HTTP requests in this service class 
  constructor(private http:HttpClient) { }

  
  getPokemonFromApi(pokedex:number):Observable<Pokemon>{
    return this.http.get("https://pokeapi.co/api/v2/pokemon/" + pokedex + "/") as Observable<Pokemon>
  } 
  
  getPokemonFromDatabase(id:number):Observable<Pokemon>{ //maybe fetch as a Pokemon object from DB
    return this.http.get(this.url + "pokemon/" + id + "/") as Observable<Pokemon>;
  }
  
  pokemonBattleTurn(attacker:Pokemon, defender:Pokemon, actionType:number, action:number):boolean{  //intent is to call the function
    let escaped:boolean = false                                                                     //in the game loop.
    switch(actionType){                                                                             //Each pass of the loop, the loop
      case BattleActions.ActionType.ATTACK:{                                                        //checks 3 conditions:
        this.attackFunc(attacker, defender, action);
        break;                                                                                      //1. attacker hp > 0
      }                                                                                             //2. defender hp > 0
      case BattleActions.ActionType.ITEM:{                                                          //3. nobody has escaped
        //call item function   
        break;                                                                                      //To that end, the loop would
      }                                                                                             //look like this: do{
      case BattleActions.ActionType.RUN:{                                                           //pokemonBattleTurn()
        //call run function or something                                                            //} while(conditions);
        //for now it's a flat 75% chance to escape                                                  //Since we need user input
        escaped = (Math.random() < .75)   
        break;                                                                                      //we can't actually use a 
      }                                                                                             //proper loop. Instead I think
      default:{                                                                                     //we can use an *ngIf to dynamically
        console.log("Error: unknown action selected");                                              //alter the game screen
      }
    }
    console.log("attacker: " + attacker);
    console.log("Defender: " + defender);
    return (!escaped && (attacker.hitPoints > 0) && (defender.hitPoints > 0)); 

  } //end of pokemonBattleTurn

  attackFunc(attacker:Pokemon, defender:Pokemon, attackType:number):void{
      const SPECIAL_ATTACK_POWER:number = 80; //these values can be tweaked
      const NORMAL_ATTACK_POWER:number = 60;
      if(attackType){ //since Special is implicitly defined as 1, it's truthy
        defender.hitPoints -= this.calculateDamage(attacker.specialAttack, 
                                                   defender.specialDefense,
                                                   attacker.level,
                                                   SPECIAL_ATTACK_POWER)
    }
    else{
      defender.hitPoints -= this.calculateDamage(attacker.attack, 
                                                 defender.defense,
                                                 attacker.level,
                                                 NORMAL_ATTACK_POWER)
    }
  }

  //formula for calculating move damage
  calculateDamage(attack:number, defense:number, level:number, power:number){
    return (((((2 * level)/5) + 2) * power * (attack/defense)) / 50) + 2;
  }


  capturePokemon(pokemon:Pokemon, catchModifier:number):boolean {
    let probability:number = 1 - (pokemon.hitPoints/pokemon.maxHitPoints);
    return (Math.random() + catchModifier > probability);
  }
}

export namespace BattleActions{

  export enum AttackType{
    NORMAL,       //0
    SPECIAL       //1
  };

  export enum ItemType{
    POTION,       //implicitly 0
    SUPER_POTION, //1
    POKE_BALL,    //2
    GREAT_BALL,   //3
    ULTRA_BALL,   //4
  }

  export enum ActionType{
    ATTACK,       //0
    ITEM,         //1
    RUN           //2
  }
  
}