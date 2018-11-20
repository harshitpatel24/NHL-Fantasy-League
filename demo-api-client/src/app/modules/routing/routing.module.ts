import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from '../../components/user/user.component';
import { LoginComponent } from '../../components/login/login.component';
import { WelcomeComponent } from '../../components/welcome/welcome.component';
import { RegisterComponent } from '../../components/register/register.component';
import { CreateLeagueComponent } from '../../components/create-league/create-league.component'; 
import { AppComponent } from 'src/app/app.component';

const routes: Routes = [
  { path: '', redirectTo: '/welcome', pathMatch: 'full'},
  { path: 'welcome', component: WelcomeComponent },
  { path: 'users/:id', component: UserComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'create-league/:id', component: CreateLeagueComponent }
];

@NgModule({
  exports: [RouterModule],
  imports: [ RouterModule.forRoot(routes)]
})

export class RoutingModule { 


}
