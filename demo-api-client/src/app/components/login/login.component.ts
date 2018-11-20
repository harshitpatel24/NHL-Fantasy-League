import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import { User } from  '../../models/user.model';
import { MatDialog, MatSnackBar } from '@angular/material';
import { LoginService } from '../../services/login/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
  
})
export class LoginComponent implements OnInit {
  
  snackBar: MatSnackBar;
  username: string;
  password: string;
  user: User;

  constructor(private formBuilder: FormBuilder, private router: Router, private loginService: LoginService) { }

  login() : void 
  {
    let usr = 
    {
      email: this.username,
      password: this.password
    }

    this.loginService.getUser(usr).subscribe(data => {
        this.user = data;
        if (this.user.userid != -1)
        {
          console.log(this.user);
          this.router.navigate(['/users', this.user.userid]);
        }
        else
        {
          alert("Fail!");
        }
    });

    // if (this.user.userid != null)
    // {
    //   let snackBarRef = this.snackBar.open('Message archived');
    //   //TODO: navigate to user dashboard

    // }
  }

  ngOnInit() 
  {

  }

}
