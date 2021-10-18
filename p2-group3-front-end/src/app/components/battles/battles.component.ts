import { Component, OnInit } from '@angular/core';
import { Pokemon } from 'src/app/models/pokemon';
import { PokemonSprite } from 'src/app/models/pokemon-sprite';
import { PokemonService } from 'src/app/services/pokemon.service';

@Component({
  selector: 'app-battles',
  templateUrl: './battles.component.html',
  styleUrls: ['./battles.component.css']
})
export class BattlesComponent implements OnInit {

  //create class variables
  public apiPokemon:any = null;
  public enemyPokemonSprite:any = null;
  public playerPokemon:any = null;
  public pokemonSprite:any = null;
  public randNum:number = 0;
  public pokemonSelected:number = 0;
  public testPoke:any = null;


  constructor(private pokemonService:PokemonService) { }

  ngOnInit(): void {
  }

  //Battle logic
  getApiPokemon():Pokemon {
    this.randNum = Math.floor(Math.random() * 898) + 1;  //generate random number between 1-898 to get random api pokemon
    console.log(this.randNum);
    
    this.pokemonService.getPokemonFromApi(this.randNum).subscribe( 
      (data:any) => {
        this.apiPokemon = data;
        console.log(this.apiPokemon);
      },
      () => { //set pokemon to null incase of error
        this.apiPokemon = null;
        console.log("Error fetching api pokemon");
      }
    )
    return this.apiPokemon;
  } //end getApiPokemon

  getPlayerPokemon():Pokemon {
    //need some input from player for pokemon id, use temp int for test
    this.pokemonSelected = 1; // test getting the initial pokemon a new player gets when register
    console.log();
    this.pokemonService.getPokemonFromDatabase(this.pokemonSelected).subscribe( 
      (data:any) => {
        this.playerPokemon = data;
        
        console.log(this.playerPokemon);
        console.log("Data: " + data);
      },
      () => { //set pokemon to null incase of error
        this.playerPokemon = null;
        console.log("Error fetching player pokemon");
      }
    )
    return this.playerPokemon;
  } //end getPlayerPokemon

  getPlayerSprite():void{
    this.pokemonService.getPokemonFromApi(this.playerPokemon.id).subscribe(
      //get the data out of the observable we subscribed to and put it in a pokemonSprite object
      (data:any) => {
         //assign it to our pokemonSprite variable
         this.pokemonSprite.sprite = data.sprites.back_default;
         console.log(this.pokemonSprite);
      },
      () => {
        this.pokemonSprite = null;
        console.log("Error fetching player pokemon sprite");
      }
    )
  } //end of getPlayerSprite

  getEnemySprite():void{
    this.pokemonService.getPokemonFromApi(this.apiPokemon.id).subscribe(
      //get the data out of the observable we subscribed to and put it in a pokemonSprite object
      (data:any) => {
         //assign it to our pokemonSprite variable
         this.enemyPokemonSprite.sprite = data.sprites.front_default;
         console.log(this.enemyPokemonSprite);
      },
      () => {
        this.enemyPokemonSprite = null;
        console.log("Error fetching enemy pokemon sprite");
      }
    )
  } //end of getEnemySprite

  coinFlip () {
    return Math.random() <= .5; 
  } //let's say true is we go first

  battle(playerPokemon:Pokemon, apiPokemon:Pokemon, actionType: number, action: number){
    let attacker:Pokemon = apiPokemon;
    let defender:Pokemon = playerPokemon;
    if(this.coinFlip()){
      attacker = playerPokemon;
      defender = apiPokemon;
    } //during an enemy turn, we call battleturn()
    console.log("playerPokemon: " + playerPokemon);
    console.log("apippokemon: " + apiPokemon);
    do{
      this.pokemonService.pokemonBattleTurn(attacker, defender, actionType, action);
    }while((attacker.hitPoints > 0) && (defender.hitPoints > 0));
    console.log(playerPokemon);
    console.log(apiPokemon);
  }

  battleWrapperFunc(){
    this.battle(this.getPlayerPokemon(), this.getApiPokemon(), 0,0);
  }
 
} //end component export





