import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from '../../components/user/user.component';
import { LoginComponent } from '../../components/login/login.component';
import { WelcomeComponent } from '../../components/welcome/welcome.component';
import { RegisterComponent } from '../../components/register/register.component';
import { CreateLeagueComponent } from '../../components/create-league/create-league.component'; 
import { UserEditComponent } from '../../components/user-edit/user-edit.component';
import { SelectPlayersComponent } from '../../components/select-players/select-players.component';
import { ViewLeagueComponent } from '../../components/view-league/view-league.component';
import { LeagueDashboardComponent } from '../../components/league-dashboard/league-dashboard.component';
import { ScoreLogComponent } from '../../components/score-log/score-log.component';
import { MemberPlayersComponent } from '../../components/member-players/member-players.component';
import { AppComponent } from 'src/app/app.component';

const routes: Routes = [
  { path: '', redirectTo: '/welcome', pathMatch: 'full'},
  { path: 'welcome', component: WelcomeComponent },
  { path: 'users/:id', component: UserComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'users/:id/create-league', component: CreateLeagueComponent },
  { path: 'users/:id/league/:leagueid', component: LeagueDashboardComponent },
  { path: 'users/:id/editProfile', component: UserEditComponent },
  { path: 'users/:id/league/:leagueid/selectPlayers', component: SelectPlayersComponent },
  { path: 'users/:id/league/:leagueid/checkPointsLog', component: ScoreLogComponent },
  { path: 'users/:id/league/:leagueid/players/:memberid', component: MemberPlayersComponent }
];

@NgModule({
  exports: [RouterModule],
  imports: [ RouterModule.forRoot(routes)]
})

export class RoutingModule { }