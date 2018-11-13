import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from '../../components/user/user.component';
import { LoginComponent } from '../../components/login/login.component';
import { AppComponent } from 'src/app/app.component';

const routes: Routes = [ 
  //{path: '', component: AppComponent},
  {path: 'users', component: UserComponent},
  {path: 'login', component: LoginComponent}
];

@NgModule({
  exports: [RouterModule],
  imports: [ RouterModule.forRoot(routes)]
})

export class RoutingModule { 


}
