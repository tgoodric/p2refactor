import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap'; //add for dropdown button

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ForestComponent } from './components/forest/forest.component';
import { CaveComponent } from './components/cave/cave.component';
import { BattlesComponent } from './components/battles/battles.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ForestComponent,
    CaveComponent,
    BattlesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
