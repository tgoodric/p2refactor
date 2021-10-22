import { Component, OnInit } from '@angular/core';
import { Pokemon } from 'src/app/models/pokemon';
import { PokemonSprite } from 'src/app/models/pokemon-sprite';
import { PokemonService } from 'src/app/services/pokemon.service';
import { CookieService } from 'ngx-cookie-service';
import { Inventory } from 'src/app/models/inventory';
@Component({
  selector: 'app-battles',
  templateUrl: './battles.component.html',
  styleUrls: ['./battles.component.css']
})
export class BattlesComponent implements OnInit {

  //create class variables

  //Trainer and pokemon related variables
  public apiPokemon:any = null; //this is the API data for the users pokemon
  public playerPokemon:any = null;  //this is the database data for the user's list of pokemon
  public pokemonSelected:any = null;  //this is the database data for the user's selected pokemon
  public trainerId:number = 38; //this needs to be changed to whatever user is logged in
  public pokedexNum = 0;
  public myHP:number = 0;
  public inventory:any = null;
  public pokeballs:number = 0;
  public potions:number = 0;
  public superPotions:number = 0;


  // opponent pokemon variables
  public randPokeDex:number =  Math.floor(Math.random() * 721) + 1; //random pokedex
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
  public winner:any = null;
  public catchingPokemon:boolean = false;
  public caught:boolean = false;

  //cookie
  public cookieValue:string;


  constructor(private pokemonService:PokemonService,
    private cookieService: CookieService
    ) { 
      this.cookieValue = this.cookieService.get('userId');
      this.trainerId = parseInt(this.cookieValue);
      console.log("Trainer id is " + this.trainerId)
    }

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
    // 
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

  switchPokemon(){
    this.pokemonSelected=null;
    this.getPlayerPokemon;
  }
  
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
      this.attackFunc(0); //we dont use the paramter here since opponent's attack type will be determined within the function
    }
  }


  //runs each time the user hits an attack button, or if its the opponent's turn
  attackFunc(attackType:number){ //this function takes in the type of attack (attack=1, special=2)

    //this code runs when its the user's turn
    if(this.myTurn){

        //user's turn
        let damage = this.pokemonService. userAttackFunc(this.pokemonSelected, this.enemyDefense, this.enemyHP, attackType) //last parameter is attack type, attack type 1 is a normal attack
        this.enemyHP = this.enemyHP - damage

        if(this.enemyHP<0){ //if the hp is less than 0, make it 0
          this.enemyHP=0
        }

        //narrate the result of the user move
        if(attackType==1){
        this.firstMove = this.pokemonSelected.pokeName + " used " + this.apiPokemon.moves[0].move.name + " (" + damage + " damage done)... " 
          + this.enemyPokemon.name + "'s HP is now " + this.enemyHP + "...";
        }else{
          this.firstMove = this.pokemonSelected.pokeName + " used " + this.apiPokemon.moves[1].move.name + " (" + damage + " damage done)... " 
          + this.enemyPokemon.name + "'s HP is now " + this.enemyHP + "...";
        }

        //if the oponent's hp is 0, end the battle
        if(this.enemyHP==0){
          this.gameOver = true;
          this.gameOverNar = this.enemyPokemon.name + " has fainted!"
          this.winner = this.pokemonSelected
          this.secondMove=""
        }


        
        //opponent turn if not already fainted
        if(this.gameOver==false){
          
          let oppAttackType = Math.floor(Math.random() *1.8) //semi randomly select the user's attack type
          damage = this.pokemonService.enemyAttackFunc(this.pokemonSelected, this.enemyAttack, this.enemySpecialAttack, this.enemyLevel, oppAttackType)
          this.pokemonSelected.hitPoints = this.pokemonSelected.hitPoints - damage
          console.log("yo"+ this.pokemonSelected.hitPoints)
          console.log("helllooo " + this.pokemonSelected.hitPoints)

          if(this.pokemonSelected.hitPoints<0){ //if the hp is less than 0, make it 0
            this.pokemonSelected.hitPoints=0
          }
        
          //narrate the result of the opponenet's move
          if(attackType==1){
          this.secondMove=this.enemyPokemon.name + " used " + this.enemyPokemon.moves[0].move.name + " (" + damage + " damage done)... " 
            + this.pokemonSelected.pokeName + "'s HP is now " + this.pokemonSelected.hitPoints + "...";
          }else {
            this.secondMove=this.enemyPokemon.name + " used " + this.enemyPokemon.moves[1].move.name + " (" + damage + " damage done)... " 
            + this.pokemonSelected.pokeName + "'s HP is now " + this.pokemonSelected.hitPoints + "...";
          }
        }

        //if the user's hp is 0, end the battle
        if(this.pokemonSelected.hitPoints==0){
          this.gameOver = true;
          this.gameOverNar = this.pokemonSelected.pokeName + " has fainted!"
          this.winner = this.enemyPokemon
        }
      
    }
    
    //this code run if opponent gets to go first
    else {

        //opponent goes
        if(this.gameOver==false){
          
          let oppAttackType = Math.floor(Math.random() *1.8) //semi randomly select the user's attack type
          //calculate damage using pokemon damage formula
          let damage = this.pokemonService.enemyAttackFunc(this.pokemonSelected, this.enemyAttack, this.enemySpecialAttack, this.enemyLevel, oppAttackType)
          this.pokemonSelected.hitPoints = this.pokemonSelected.hitPoints - damage
          console.log("hiiii" + damage)
          console.log(this.pokemonSelected.hitPoints)

          if(this.pokemonSelected.hitPoints<0){ //if the hp is less than 0, make it 0
            this.pokemonSelected.hitPoints=0
          }
        
          //narrate the result of the opponenet's move
          if(attackType==1){
          this.firstMove=this.enemyPokemon.name + " used " + this.enemyPokemon.moves[0].move.name + " (" + damage + " damage done)... " 
            + this.pokemonSelected.pokeName + "'s HP is now " + this.pokemonSelected.hitPoints + "...";
          }else{
            this.firstMove=this.enemyPokemon.name + " used " + this.enemyPokemon.moves[1].move.name + " (" + damage + " damage done)... " 
            + this.pokemonSelected.pokeName + "'s HP is now " + this.pokemonSelected.hitPoints + "...";
          }
        }

        //if the user's hp is 0, end the battle
        if(this.pokemonSelected.hitPoints==0){
          this.gameOver = true;
          this.gameOverNar = this.pokemonSelected.pokeName + " has fainted!"
          this.winner = this.enemyPokemon
        }

        //make it the user's turn
        this.myTurn=true;
    }

    //if the game is over, update the Pokemon's info in the database
    if(this.gameOver){
      this.updatePokemon();
    }
  }

  //function to get a users inventory
  getInventory():Inventory {
    this.trainerId = this.trainerId; // test getting the initial pokemon a new player gets when register
    console.log(this.trainerId);
    this.pokemonService.getInventoryFromDatabase(this.trainerId).subscribe( 
      (data:any) => {
        this.inventory = data;
        this.pokeballs = this.inventory[0].pokeballs;
        this.potions = this.inventory[0].potions;
        this.superPotions = this.inventory[0].superPotions;
        console.log(this.inventory);
        console.log(this.inventory[0].potions);
      },
      () => { //set pokemon to null incase of error
        this.inventory = null;
        console.log("Error fetching player pokemon");
      }
    )
    return this.inventory;
  } // end of getInventory

  //runs when the user selects to use a potion
  potionFunc(type:number){

    if(type==1){
      this.pokemonSelected.hitPoints+=10;
      this.firstMove=this.pokemonSelected.pokeName + " used a potion... " + this.pokemonSelected.pokeName +"'s HP is now " + this.pokemonSelected.hitPoints
      // this.myTurn=false;
      // this.attackFunc( Math.ceil(Math.random() * 1.8));
    }else{
      this.pokemonSelected.hitPoints+=30
      this.firstMove=this.pokemonSelected.pokeName + " used a super potion... "  + this.pokemonSelected.pokeName + "'s HP is now " + this.pokemonSelected.hitPoints
      // this.myTurn=false;
      // this.attackFunc( Math.ceil(Math.random() * 1.8));
    }

    //after potion is used, opponent goes
    if(this.gameOver==false){

      let oppAttackType = Math.floor(Math.random() *1.8) //semi randomly select the user's attack type
      let damage = this.pokemonService.enemyAttackFunc(this.pokemonSelected, this.enemyAttack, this.enemySpecialAttack, this.enemyLevel, oppAttackType)
      this.pokemonSelected.hitPoints = this.pokemonSelected.hitPoints - damage
      console.log("hiiii" + damage)
      console.log(this.pokemonSelected.hitPoints)

      if(this.pokemonSelected.hitPoints<0){ //if the hp is less than 0, make it 0
        this.pokemonSelected.hitPoints=0
      }
    
      //narrate the result of the opponenet's move
      if(oppAttackType=1){
      this.secondMove=this.enemyPokemon.name + " used " + this.enemyPokemon.moves[0].move.name + " (" + damage + " damage done)... " 
        + this.pokemonSelected.pokeName + "'s HP is now " + this.pokemonSelected.hitPoints + "...";
      }else{
        this.secondMove=this.enemyPokemon.name + " used " + this.enemyPokemon.moves[1].move.name + " (" + damage + " damage done)... " 
        + this.pokemonSelected.pokeName + "'s HP is now " + this.pokemonSelected.hitPoints + "...";
      }
    }

    //if the user's hp is 0, end the battle
    if(this.pokemonSelected.hitPoints==0){
      this.gameOver = true;
      this.gameOverNar = this.pokemonSelected.pokeName + " has fainted!"
      this.winner = this.enemyPokemon
    }

    //make it the user's turn
    this.myTurn=true;
  }

  //runs when the user selects to use a pokeball
  catchPokemon(){

    this.catchingPokemon = true;

    this.caught = this.pokemonService.capturePokemon(this.enemyHP, this.enemyPokemon.stats[0].base_stat);

    this.firstMove = "You used a pokeball!"

    if(this.caught){
      this.secondMove = "Gotcha! " + this.enemyPokemon.name + " was caught!"
      this.addNewPokemon();
    } else{
      this.secondMove =  "Oh no! " + this.enemyPokemon.name + " got away!"
    }

    this.gameOver=true;
  }


  //update the pokemon in the database with new values after battle ends -- uses fetch api for updating database
  async updatePokemon(){
    const url = "http://localhost:8090/" //putting in our base URL

    //update pokemon info in database
    //if they won the battle update experience and level if achieved a level up
    if (this.winner==this.pokemonSelected){
      this.pokemonSelected.experience+=1;
      if(this.pokemonSelected.experience % 2 == 0){ //every two wins, level up the pokemon
        this.pokemonSelected.level+=1;
      }
    }
    
    let response = await fetch(url + "pokemon/update", {
      method: "PUT", //send a POST request
      body: JSON.stringify(this.pokemonSelected), //turn our JS into JSON
      credentials: "include"
      //this last line will ensure that the cookie is captured
    });
  }

  //add the caught pokemon to the database
  async addNewPokemon(){
    const url = "http://localhost:8090/" //putting in our base URL

    console.log("about to complete add pokemon to db")
    //add opponent pokemon to user pokemon collection

    //first instantiate a new (database) Pokemon object with the necessary values from the caught pokemon
    let newPokemon = { 
                      pokeName : this.enemyPokemon.name,
                      pokedexNumber : this.enemyPokemon.id,
                      level : this.enemyLevel,
                      maxHitPoints : this.enemyPokemon.stats[0].base_stat,
                      hitPoints : this.enemyHP,
                      attack : this.enemyAttack,
                      defense : this.enemyDefense,
                      specialDefense : 0,
                      experience : this.enemyPokemon.base_experience,
                      trainerIdFk : {userId : 1} //needs to change to cookie value
    }
    
    console.log("about to do the await fetch")
    let response = await fetch(url + "pokemon", {
      method: "POST", //send a POST request
      body: JSON.stringify(newPokemon), //turn our JS into JSON
      credentials: "include"
      //this last line will ensure that the cookie is captured
    });
    console.log("yerrr " + response)
  }

} //end component export