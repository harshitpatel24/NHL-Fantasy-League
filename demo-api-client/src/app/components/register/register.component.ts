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

  constructor(private formBuilder: FormBuilder, private router: Router, private loginService: LoginService) { }

  signUp(){
    
  }

  ngOnInit() {
  }

}
