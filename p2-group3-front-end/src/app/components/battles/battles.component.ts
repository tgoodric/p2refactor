import { Component, OnInit } from '@angular/core';
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


  constructor(private pokemonService:PokemonService) { }

  ngOnInit(): void {
  }

  //Battle logic
  getApiPokemon():void {
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
  } //end getApiPokemon

  getPlayerPokemon():void {
    //need some input from player for pokemon id, use temp int for test
    this.pokemonSelected = 9; // test getting the initial pokemon a new player gets when register
    console.log();
    this.pokemonService.getPokemonFromDatabase(this.pokemonSelected).subscribe( 
      (data:any) => {
        this.playerPokemon = data;
        this.playerPokemon.level = data.level;
        console.log(this.playerPokemon);
        
      },
      () => { //set pokemon to null incase of error
        this.playerPokemon = null;
        console.log("Error fetching player pokemon");
      }
    )
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

  


} //end component export

