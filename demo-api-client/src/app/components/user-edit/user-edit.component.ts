import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user/user.service';
import { User } from '../../models/user.model';
import {Router, ActivatedRoute, Params} from '@angular/router';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {

  user: User;
  id: number;
  constructor(private userService: UserService, private route: ActivatedRoute, private router: Router) { }

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

  submitProfile() {

    this.userService.updateUser(this.user).subscribe(data => {
      this.user = data;

      if (this.user.userid != -1)
      {
        alert("Success!");
      }
      else
      {
        alert("Fail!");

      }

    });
  }

}
