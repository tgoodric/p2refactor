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

  useItem(id:number, item:string):void{
    this.http.patch(this.url + "inventory/" + id + "/useItem/" + item + "/", null);
  }

  addItem(id:number, item:string):void{
    this.http.patch(this.url + "inventory/" + id + "/addItem/" + item + "/", null);
  }

  userAttackFunc(user:Pokemon, oppDefense:number, oppHP:number, attackType:number):number{
    console.log("in thee attack func in pokemons service")
    console.log(user.attack)
    const NORMAL_ATTACK_POWER:number = 60;
    const SPECIAL_ATTACK_POWER:number = 80; //these values can be tweaked
    let damage = 0;
    if(attackType==1){ //since Special is implicitly defined as 1, it's truthy
      damage = this.calculateDamage(user.attack, 
                                                 oppDefense,
                                                 user.level,
                                                 NORMAL_ATTACK_POWER)
  }
  else{
    damage = this.calculateDamage(user.attack, 
                                               oppDefense,
                                               user.level,
                                               SPECIAL_ATTACK_POWER)
  }

  return damage; //returns the defender pokemon with the updated HP
}

  enemyAttackFunc(user:Pokemon, oppAttack:number, oppSpecialAttack:number, oppLevel:number, attackType:number): number{

    console.log("in thee OPPONENT attack func in pokemons service")
    console.log(user.attack)
    const NORMAL_ATTACK_POWER:number = 60;
    const SPECIAL_ATTACK_POWER:number = 80; //these values can be tweaked
    let damage = 0;
    if(attackType==1){ //since Special is implicitly defined as 1, it's truthy
       damage = this.calculateDamage(oppAttack, 
                                                 user.defense,
                                                 oppLevel,
                                                 NORMAL_ATTACK_POWER)
  }
  else{
    damage = this.calculateDamage(oppSpecialAttack, 
                                               user.defense,
                                               oppLevel,
                                               SPECIAL_ATTACK_POWER)
  }

  return damage; //returns the defender pokemon with the updated HP
  }

  //formula for calculating move damage
  calculateDamage(attack:number, defense:number, level:number, power:number):number{
    console.log("in the calculate damage function")

    let damage =  Math.ceil( (((((2 * level)/5) + 2) * power * (attack/defense)) / 50) + 2 );

    console.log(damage)

    return damage;
  }

  capturePokemon(hp:number, maxHP:number):boolean {
    let probability = 1 - (hp/maxHP);
    return (probability > Math.random());
  }
}
  