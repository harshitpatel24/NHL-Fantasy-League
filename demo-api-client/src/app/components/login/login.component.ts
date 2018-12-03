import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import { User } from  '../../models/user.model';
import { MatDialog, MatSnackBar } from '@angular/material';
import { LoginService } from '../../services/login/login.service';
import {LocalStorageService, SessionStorageService} from 'ngx-webstorage';

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

  constructor(private formBuilder: FormBuilder, private router: Router, private loginService: LoginService, private localSt: LocalStorageService) { }

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
          this.localSt.store('userid',this.user.userid);
          this.router.navigate(['/users', this.user.userid]);
        }
        else
        {
          alert("Fail!");
        }
    });
  }

  ngOnInit() 
  {

  }

}
