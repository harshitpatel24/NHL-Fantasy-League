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
  inactive: boolean = true;
  editButton: boolean = false; 
  submitButton: boolean = true; 
  joinLeagueForm: boolean = false;
  joinLeagueButton: boolean = true; 

  constructor(private userService: UserService, private route: ActivatedRoute, private router: Router) { }

  editProfile() {
    this.inactive = false;
    this.editButton = true;
    this.submitButton = false;
  }

  getSubmitButton() {
    return this.submitButton; 
  }

  getEditButton() {
    return this.editButton;
  }

  getDisabled() {
    return this.inactive;
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

  createLeague() {
    this.router.navigate(['/create-league', this.user.userid]);
  }

  joinLeague() {
    this.joinLeagueButton = false;
    this.joinLeagueForm = true;

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
