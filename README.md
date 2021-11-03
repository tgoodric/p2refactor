# Pokemon Adventure Simulator

**Project Overview:**
A full stack web application where a user can register an account and login to play a pokemon adventure game. After successfully logging in, the user is taken to the hometown and greeted by the narrator, Professor Oak, and given instructions aboout the different options they have. These options include: asking for items, training, and exploring different areas. The user can explore three different areas where he can search for items as well as wild pokemon. When a player encounters a wild pokemon, they can choose which pokemon they would like to battle with from their list of pokemon. Once the player begins the battle, the game randomly decides which player goes first. Each time it the user's turn they can either: select one of their attack moves, use a potion from their inventory to heal their pokemon, or attempt to catch the enemy pokemon with a pokeball. After each turn, the game calculates the damage done to the oppossing pokemon using the pokemon damage calculation formula. If the user wins the battle, their pokemon will gain and experience point, and every two victorious battles that pokemon will level up. If a user selects to use a pokball during the battle to capture an enemy pokemon, the game will decide whether or not the attempt is successful using a version of the pokemon catch rate formula. If a pokemon is successfully caught, it will be added to the user's list of pokemon. Once the battle or catch attempt is complete, the user can choose to navigate to a different area or back to the hometown.

**Technologies:**

**Backend:**
- Java
- Hibernate
- JWT
- Javalin
- PostgreSQL
- DBeaver
- GCP Cloud SQL

**Frontend:**
- Angular 2+
- Bootstrap
- HTML
- CSS
- Javascript
- Typescript

**Testing:**
- JUnit

**Logging**
- log4j

**Features**

* Interactive design
* User can create an account
* User can login 
* User can travel to different areas
* User can find items that get added to their inventory
* User can find pokemon 
* User can battle pokemon 
* User can attempt to catch pokemon
* User can heal pokemon with potions
* A pokemon's level increases after winning 2 games


## Getting Started/Usage:

To run this application locally, following requirements are needed in your local machine:

**Backend:**
- Install JDK 8
- Install SpringToolSuite4
- PostgreSQL
- DBeaver

To run the backend, run the launcher in the com.revature package as Java Application.

**Frontend:**
- Install NodeJs
- Install Angular CLI

After installing all the basic requirement, at frontend you need to run this command:
* npm install

Once all the dependencies are ready, you can start an application with this command:
* npm serve -o


**Contributors**
- Eric Shawn
- Tristan	Goodrich
- Brett	Garber
- Collin Funaki
- Nabin Khatri

