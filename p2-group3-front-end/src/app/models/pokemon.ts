export class Pokemon {

    //this constructor will initialize the Pokemon's variables when we create one
    constructor(
        public id:number,
        public pokedexNumber:number,
        public pokeName:string,
        public level:number,
        public maxHitPoints:number,
        public hitPoints: number,
        public attack:number,
        public specialAttack:number,
        public defense:number,
        public specialDefense:number,
        public experience:number,            //every 3-4 battles-ish this gets set to 0; level++
    ) { }
}
