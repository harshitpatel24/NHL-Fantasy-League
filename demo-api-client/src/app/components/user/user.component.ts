import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute, Params} from '@angular/router';
import { User } from '../../models/user.model';
import { UserService } from '../../services/user/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  user: User;
  id: number; 

  constructor(private userService: UserService, private route: ActivatedRoute) { }

  editProfile() {
    
  }

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
  }

}
