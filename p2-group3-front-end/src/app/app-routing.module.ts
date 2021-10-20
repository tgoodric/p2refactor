import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BattlesComponent } from './components/battles/battles.component';
import { CaveComponent } from './components/cave/cave.component';
import { ForestComponent } from './components/forest/forest.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { RiverComponent } from './components/river/river.component';

const routes: Routes = [

  {
    path:"",
    component:LoginComponent
  },

  {
    path:"login",
    component:LoginComponent
  },

  {
    path:"register",
    component:RegisterComponent
  },

  {
    path:"home",
    component:HomeComponent
  },

  {
    path:"forest",
    component:ForestComponent
  },

  {
    path:"cave",
    component:CaveComponent
  },

  {
    path:"battles",
    component:BattlesComponent
  },

  {
    path:"river",
    component:RiverComponent
  }

  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
