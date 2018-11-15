import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import { LoginService } from '../../services/login/login.service';

import { User } from  '../../models/user.model';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  name: string; 
  username: string;
  password: string;
  user: User;

  constructor(private formBuilder: FormBuilder, private router: Router, private loginService: LoginService) { }

  signUp(){
    let usr = 
    {
      uname: this.name,
      email: this.username,
      password: this.password
    }

    //console.log(usr);

    this.loginService.createUser(usr).subscribe(data => {
      this.user = data;
      console.log(data);
      if (this.user.userid != -1)
      {
        this.router.navigate(['/users', this.user.userid]);
        //alert("Success!");
      }
      else
      {
        alert("Fail!");
      }
  });
  }

  ngOnInit() {
  }

}
