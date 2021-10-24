export class Inventory {

    // Model used for storing a users inventory retrieved from the database
    constructor(
        public inventoryId:number,
        public pokeballs:number,
        public potions:number,
        public superPotions:number,
        public trainerId:number,
    ) { }
}
