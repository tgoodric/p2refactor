export class Pokemon {
    //declaration of instance vars
    id:number;
    pokedexNumber:number;
    pokeName:string;
    level:number;
    maxHitPoints:number;
    hitPoints: number;
    attack:number;
    specialAttack:number;
    defense:number;
    specialDefense:number;
    experience:number;

    //this constructor will initialize the Pokemon's variables when we create one
    constructor(
        id:number,
        pokedexNumber:number,
        pokeName:string,
        level:number,
        maxHitPoints:number,
        hitPoints: number,
        attack:number,
        specialAttack:number,
        defense:number,
        specialDefense:number,
        experience:number,            //every 3-4 battles-ish this gets set to 0; level++
    ) { 
        this.id = id;
        this.pokedexNumber = pokedexNumber;
        this.pokeName = pokeName;
        this.level = level;
        this.maxHitPoints = maxHitPoints;
        this.hitPoints = hitPoints;
        this.attack = attack;
        this.specialAttack = specialAttack;
        this.defense = defense;
        this.specialDefense = specialDefense
        this.experience = experience;
    }

    
}
