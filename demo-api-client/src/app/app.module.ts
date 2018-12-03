import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { CustomMaterialModule } from './core/material.module';
import { MatCheckboxModule, MatPaginatorModule } from '@angular/material';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http';
import {NgxWebstorageModule} from 'ngx-webstorage';

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
import { LeagueDashboardComponent } from './components/league-dashboard/league-dashboard.component';
import { UserEditComponent } from './components/user-edit/user-edit.component';
import { SelectPlayersComponent } from './components/select-players/select-players.component';
import { PlayerListComponent } from './components/player-list/player-list.component';
import { ScoreLogComponent } from './components/score-log/score-log.component';
import { HeaderComponent } from './components/header/header.component';
import { MemberPlayersComponent } from './components/member-players/member-players.component';

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
    ViewLeagueComponent,
    LeagueDashboardComponent,
    UserEditComponent,
    SelectPlayersComponent,
    PlayerListComponent,
    ScoreLogComponent,
    HeaderComponent,
    MemberPlayersComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    CustomMaterialModule,
    HttpModule,
    RoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MatCheckboxModule,
    MatPaginatorModule,
    NgxWebstorageModule.forRoot()
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
