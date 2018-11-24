import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { CustomMaterialModule } from './core/material.module';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http'

import { UserService } from './services/user/user.service';

import { AppComponent } from './app.component';
import { UserComponent } from './components/user/user.component';
import { LoginComponent } from './components/login/login.component';
import { RoutingModule } from './modules/routing/routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { RegisterComponent } from './components/register/register.component';
import { LeagueComponent } from './components/league/league.component';
import { CreateLeagueComponent } from './components/create-league/create-league.component';
import { JoinLeagueComponent } from './components/join-league/join-league.component';
import { ViewLeagueComponent } from './components/view-league/view-league.component';

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    LoginComponent,
    WelcomeComponent,
    RegisterComponent,
    LeagueComponent,
    CreateLeagueComponent,
    JoinLeagueComponent,
    ViewLeagueComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    CustomMaterialModule,
    HttpModule,
    RoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
