import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute, Params} from '@angular/router';
import { User } from '../../models/user.model';
import {LocalStorageService, SessionStorageService} from 'ngx-webstorage';
import { UserService } from '../../services/user/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  id: number;
  path: any;
  user: any;
  constructor(private route: ActivatedRoute,private localSt:LocalStorageService,private router: Router,private userService: UserService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
        this.id = +params['id'];
    });
    this.path = '/users/'+this.id;
    let usr = {
      userid: this.id
    }
    this.userService.getUser(usr).subscribe(data => {
      this.user = data;
    });
  }

  logout(){
    this.localSt.clear('userid')
    this.router.navigate(['/welcome']);
  }
}
