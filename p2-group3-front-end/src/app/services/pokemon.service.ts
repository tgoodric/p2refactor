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

  private url:string = "localhost:8090/project2/"
  
  //we dependency inject HttpClient in order to make HTTP requests in this service class 
  constructor(private http:HttpClient) { }

  


  getPokemonFromApi(id:number):Observable<Pokemon>{
    return this.http.get("https://pokeapi.co/api/v2/pokemon/" + id + "/") as Observable<Pokemon>
  } //shamelessly ripped from the NgPokedex demo
  
  getPokemonFromDatabase(id:number):Observable<Pokemon>{ //maybe fetch as a Pokemon object from DB
    return this.http.get(this.url + "pokemon/" + id + "/") as Observable<Pokemon>;
  }
  
  pokemonBattleTurn(attacker:Pokemon, defender:Pokemon, actionType:number, action:number):boolean{  //intent is to call the function
    let escaped:boolean = false                                                                     //in the game loop.
    switch(actionType){                                                                             //Each pass of the loop, the loop
      case BattleActions.ActionType.ATTACK:{                                                        //checks 3 conditions:
        //call attack function                                                                      //1. attacker hp > 0
        break;                                                                                      //2. defender hp > 0
      }                                                                                             //3. nobody has escaped
      case BattleActions.ActionType.ITEM:{                                                          //To that end, the loop would
        //call item function                                                                        //look like this: do{
        break;                                                                                      //pokemonBattleTurn()
      }                                                                                             //} while(conditions);
      case BattleActions.ActionType.RUN:{                                                           //Since we need user input
        //call run function or something                                                            //we can't actually use a 
        //for now it's a flat 75% chance to escape                                                  //proper loop. Instead I think
        escaped = (Math.random() < .75)                                                             //we can use an *ngIf to dynamically
        break;                                                                                      //alter the game screen
      }                                                                                             
      default:{                                                                                     
        console.log("Error: unknown action selected");                                              
      }                                                                                             
      return (!escaped && (attacker.hitPoints > 0) && (defender.hitPoints > 0));  //nobody is KO'd or ran away
    }


  }

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