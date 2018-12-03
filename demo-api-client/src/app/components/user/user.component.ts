import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute, Params} from '@angular/router';
import { User } from '../../models/user.model';
import { UserService } from '../../services/user/user.service';
import {LocalStorageService, SessionStorageService} from 'ngx-webstorage';
import {AppComponent} from '../../app.component';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  user: User;
  id: number;
  joinLeagueForm: boolean = false;
  joinLeagueButton: boolean = true; 

  constructor(private userService: UserService, private route: ActivatedRoute, private router: Router,private appComponent: AppComponent,private localSt:LocalStorageService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
        this.id = +params['id'];
    });

    if (this.id != null)
    {
      let usr = {
        userid: this.id
        }

        this.userService.getUser(usr).subscribe(data => {
          this.user = data;
        });
    }
    this.appComponent.validateUser(this.id);
  }

  editProfile() {
    this.router.navigate([this.router.url + '/editProfile']);
  }

  createLeague() {
    this.router.navigate([this.router.url + '/create-league']);
  }

  joinLeague() {
    this.joinLeagueButton = false;
    this.joinLeagueForm = true;
  }
  

}
