import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from '../../components/user/user.component';
import { LoginComponent } from '../../components/login/login.component';
import { WelcomeComponent } from '../../components/welcome/welcome.component';
import { RegisterComponent } from '../../components/register/register.component';
import { CreateLeagueComponent } from '../../components/create-league/create-league.component'; 
// import { JoinLeagueComponent } from '../../components/join-league/join-league.component'; //mohit
import { ViewLeagueComponent } from '../../components/view-league/view-league.component'; //mohit
import { LeagueDashboardComponent } from '../../components/league-dashboard/league-dashboard.component'; //mohit
import { AppComponent } from 'src/app/app.component';

const routes: Routes = [
  { path: '', redirectTo: '/welcome', pathMatch: 'full'},
  { path: 'welcome', component: WelcomeComponent },
  { path: 'users/:id', component: UserComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'create-league/:id', component: CreateLeagueComponent },
  // { path: 'join-league/:id', component: JoinLeagueComponent },  //mohit
  // { path: 'view-league/:id', component: ViewLeagueComponent },  //mohit
  { path: 'users/:id/:leagueid', component: LeagueDashboardComponent}
];

@NgModule({
  exports: [RouterModule],
  imports: [ RouterModule.forRoot(routes)]
})

export class RoutingModule { 


}
