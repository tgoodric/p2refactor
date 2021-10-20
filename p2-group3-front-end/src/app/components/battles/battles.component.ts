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

  //Trainer and pokemon related variables
  public apiPokemon:any = null;
  public enemyPokemon:any = null;
  public playerPokemon:any = null;
  public pokemonSelected:any = null;
  public trainerId:number = 1;
  public pokedexNum = 0;
  public myHP:number = 0;
  public enemyHP:number = 0;


  //game control variables
  public turn:boolean = false;
  public gameStart:boolean = false;
  public gameOver:boolean = false;
  public randNum:number = 0;




  constructor(private pokemonService:PokemonService) { }

  ngOnInit(): void {
    this.getEnemyPokemon()
  }

  

  //Get your selected pokemon API infor as well as a random enemy pokemon
  getApiPokemon():Pokemon {
    
    this.pokemonService.getPokemonFromApi(this.pokedexNum).subscribe( 
      (data:any) => {
        this.apiPokemon = data;
      },
      () => { //set pokemon to null incase of error
        this.apiPokemon = null;
        console.log("Error fetching api pokemon");
      }
    )
    return this.apiPokemon;
  } //end getApiPokemon

  getEnemyPokemon():Pokemon{
    this.randNum = Math.floor(Math.random() * 898) + 1;  //generate random number between 1-898 to get random api pokemon
    console.log(this.randNum);
    this.pokemonService.getPokemonFromApi(this.randNum).subscribe( 
      (data:any) => {
        this.enemyPokemon = data;
        console.log(this.enemyPokemon);
      },
      () => { //set pokemon to null incase of error
        this.enemyPokemon = null;
        console.log("Error fetching api pokemon");
      }
    )
    return this.enemyPokemon;
  }


  //get the player's pokemon database info
  getPlayerPokemon():Pokemon {
    //need some input from player for pokemon id, use temp int for test
    this.trainerId = this.trainerId; // test getting the initial pokemon a new player gets when register
    console.log(this.trainerId);
    this.pokemonService.getPokemonFromDatabase(this.trainerId).subscribe( 
      (data:any) => {
        this.playerPokemon = data;
        console.log(this.playerPokemon);
        console.log(this.playerPokemon.name)
      },
      () => { //set pokemon to null incase of error
        this.playerPokemon = null;
        console.log("Error fetching player pokemon");
      }
    )
    return this.playerPokemon;
  } //end getPlayerPokemon

  
  //set stage for battle
  prepareBattle(d:Pokemon){
    console.log("in prepare battle function")

    //set pokedex num and pokemon id for user selected pokemon
    this.pokedexNum = d.pokedexNumber;
    this.pokemonSelected = d

    //logs for debug
    console.log(d)
    console.log(d.pokedexNumber)
  }

  //battle functionality
  battle(){
    console.log("in battle func")
    
    //decide who goes first
    this.randNum = Math.random()
    if(this.randNum <= .5){
      console.log("our turn")
      this.turn=true
    }else {
      console.log("their turn")
      this.turn=false
    }

    //change the start game boolean to true
    this.gameStart=true;

    
    }


    // battle(playerPokemon:Pokemon, apiPokemon:Pokemon, actionType: number, action: number){
    //   let attacker:Pokemon = apiPokemon;
    //   let defender:Pokemon = playerPokemon;
    //   if(this.coinFlip()){
    //     attacker = playerPokemon;
    //     defender = apiPokemon;
    //   } //during an enemy turn, we call battleturn()
    //   console.log("playerPokemon: " + playerPokemon);
    //   console.log("apippokemon: " + apiPokemon);
    //   do{
    //     this.pokemonService.pokemonBattleTurn(attacker, defender, actionType, action);
    //   }while((attacker.hitPoints > 0) && (defender.hitPoints > 0));
    //   console.log(playerPokemon);
    //   console.log(apiPokemon);
    // }


  attackFunc(){
    console.log("in attackFunc")


    // this.pokemonService.attackFunc(this.pokemonSelected, this.enemyPokemon, this.pokemonSelected.myHP)

    // this.pokemonService.attackFunc(this.enemyPokemon, this.pokemonSelected, this.enemyPokemon.enemyHP)

  }

  specialAttackFunc(){
    console.log("in specialAttackFunc")

  }

 
} //end component export



