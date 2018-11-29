import { Component } from '@angular/core';
import { Router } from '@angular/router'; 
import { UserComponent } from './components/user/user.component'; 
import {LocalStorageService, SessionStorageService} from 'ngx-webstorage';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  constructor(private localSt:LocalStorageService,private router: Router){}

  validateUser(id){
    if(id != this.localSt.retrieve('userid')){
      this.router.navigate(['/welcome']);
    }
  }
}
