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
  public playerPokemon:any = null;
  public pokemonSelected:any = null;
  public trainerId:number = 1; //this needs to be changed to whatever user is logged in
  public pokedexNum = 0;
  public myHP:number = 0;

  // opponent pokemon variables
  public randPokeDex:number =  Math.floor(Math.random() * 898) + 1; //random pokedex
  public enemyPokemon:any = null;
  public enemyHP:number = 0;
  public enemyLevel:number = Math.floor(Math.random() * 100) + 1; //randomly assign enemy pokemon level
  public enemyAttack:number = 0;
  public enemyDefense:number = 0;
  public enemySpecialAttack:number = 0;


  //game control variables
  public gameStart:boolean = false;
  public gameOver:boolean = false;
  public firstMove:string = "";
  public secondMove:string = "";
  public gameOverNar:string = "";
  public coinFlip:number = Math.random();
  public myTurn:boolean = false;




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

  //randomly select an enemy pokemon from the poke API
  getEnemyPokemon():Pokemon{
    
    this.pokemonService.getPokemonFromApi(this.randPokeDex).subscribe( 
      (data:any) => {
        this.enemyPokemon = data;
        console.log(this.enemyPokemon);

        //set the enemy HP, attack, defense and special attack values from the base API values
        this.enemyHP = this.enemyPokemon.stats[0].base_stat
        this.enemyAttack = this.enemyPokemon.stats[1].base_stat
        this.enemyDefense = this.enemyPokemon.stats[2].base_stat
        this.enemySpecialAttack = this.enemyPokemon.stats[3].base_stat
        console.log(this.enemySpecialAttack)

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

  
  //set stage for battle and set values of user selected pokemon
  prepareBattle(d:Pokemon){
    console.log("in prepare battle function")

    //set pokedex num and pokemon id for user selected pokemon
    this.pokedexNum = d.pokedexNumber;
    this.pokemonSelected = d

    //decide who goes first
    this.coinFlip = Math.random()
    console.log(this.coinFlip)
    if(this.coinFlip >= .5){
      console.log("our turn")
      this.myTurn=true
    }else {
      console.log("their turn")
      this.myTurn=false
    }

  }

  //battle functionality
  battle(){
    console.log("in battle func")

    //change the start game boolean to true
    this.gameStart=true;

    //if the opponenent wins the coin flip then run their attack
    if(this.myTurn==false){
      this.attackFunc();
      console.log("hiiii")
    }
    }


  attackFunc(){

    //this code runs when its the user's turn
    if(this.myTurn){

        //user attacks
        let damage = this.pokemonService. userAttackFunc(this.pokemonSelected, this.enemyDefense, this.enemyHP, 1) //last parameter is attack type, attack type 1 is a normal attack
        this.enemyHP = this.enemyHP - damage

        if(this.enemyHP<0){ //if the hp is less than 0, make it 0
          this.enemyHP=0
        }

        //narrate the result of the user move
        this.firstMove = this.pokemonSelected.pokeName + " used attack (" + damage + " damage done)... " 
          + this.enemyPokemon.name + "'s HP is now " + this.enemyHP + "...";

        //if the oponent's hp is 0, end the battle
        if(this.enemyHP==0){
          this.gameOver = true;
          this.gameOverNar = this.enemyPokemon.name + " has fainted!"
        }


        //opponent goes if not already fainted
        if(this.gameOver==false){
          
          damage = this.pokemonService.enemyAttackFunc(this.pokemonSelected, this.enemyAttack, this.enemySpecialAttack, this.enemyLevel, 1)
          this.pokemonSelected.hitPoints = this.pokemonSelected.hitPoints - damage

          if(this.pokemonSelected.hitPoints<0){ //if the hp is less than 0, make it 0
            this.pokemonSelected.hitPoints=0
          }
        
          //narrate the result of the opponenet's move
          this.secondMove=this.enemyPokemon.name + " used attack (" + this.enemyPokemon.stats[1].base_stat + " damage done)... " 
            + this.pokemonSelected.pokeName + "'s HP is now " + this.pokemonSelected.hitPoints + "...";
        }

        //if the user's hp is 0, end the battle
        if(this.pokemonSelected.hitPoints==0){
          this.gameOver = true;
          this.gameOverNar = this.pokemonSelected.pokeName + " has fainted!"
        }
      
    }
    
    //this code run if opponenet gets to go first
    else{

        //opponent goes
        if(!this.gameOver){
          
          let damage = this.pokemonService.enemyAttackFunc(this.pokemonSelected, this.enemyAttack, this.enemySpecialAttack, this.enemyLevel, 1)
          this.pokemonSelected.hitPoints = this.pokemonSelected.hitPoints - damage
          console.log(this.enemyHP)
          if(this.pokemonSelected.hitPoints<0){ //if the hp is less than 0, make it 0
            this.pokemonSelected.hitPoints=0
          }
        
          //narrate the result of the opponenet's move
          this.firstMove=this.enemyPokemon.name + " used attack (" + this.enemyPokemon.stats[1].base_stat + " damage done)... " 
            + this.pokemonSelected.pokeName + "'s HP is now " + this.pokemonSelected.hitPoints + "...";
        }

        //if the user's hp is 0, end the battle
        if(this.pokemonSelected.hitPoints==0){
          this.gameOver = true;
          this.gameOverNar = this.pokemonSelected.name + " has fainted!"
        }

        this.myTurn=true;
        // //user goes if not already fainted
        // let damage = this.pokemonService. userAttackFunc(this.pokemonSelected, this.enemyDefense, this.enemyHP, 1) //last parameter is attack type, attack type 1 is a normal attack
        // this.enemyHP = this.enemyHP - damage
        // console.log(this.enemyHP)
        // if(this.enemyHP<0){ //if the hp is less than 0, make it 0
        //   this.enemyHP=0
        // }

        // //narrate the result of the user move
        // this.firstMove = this.pokemonSelected.pokeName + " used attack (" + damage + " damage done)... " 
        //   + this.enemyPokemon.name + "'s HP is now " + this.enemyHP;

        // //if the oponent's hp is 0, end the battle
        // if(this.enemyHP==0){
        //   this.gameOver = true;
        //   this.gameOverNar = this.enemyPokemon.name + " has fainted!"
        // }
        
    }

  }




  specialAttackFunc(){
    console.log("in specialAttackFunc")

  }

  somefunc(){

  }

 
} //end component export



