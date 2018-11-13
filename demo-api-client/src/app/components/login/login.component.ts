import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import { User } from  '../../models/user.model'; // '../models/user.model';
import {first} from "rxjs/operators";
import { LoginService } from '../../services/login.service';
import { NONE_TYPE } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
  
})
export class LoginComponent implements OnInit {
        
  loginForm: FormGroup;
  submitted: boolean = false;
  invalidLogin: boolean = false;
  user: User; 
  constructor(private formBuilder: FormBuilder, private router: Router, private loginService: LoginService) { }

  
    
    onSubmit() {
    this.submitted = true;
    if (this.loginForm.invalid) {
      return;
    }

    this.user = {
      userid: 1,
      username: "Bob",
      email: this.loginForm.controls.email.value,
      password: this.loginForm.controls.password.value
    }

    // user = {
    //   userid: 1,
    //   name: "Bob",
    //   email: this.loginForm.controls.email.value,
    //   password: this.loginForm.controls.email.value
    // }

    //console.log("hi")

    this.loginService.getUser(this.user).subscribe(data => {this.user = data; console.log(data)});

    //console.log(this.user);
//    if(this.loginForm.controls.email.value == 'dhiraj@gmail.com' && this.loginForm.controls.password.value == 'password') {
//        this.router.navigate(['users']);
//    }else {
//      this.invalidLogin = true;
//    }
  }

   ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });
       
//       this.LoginService.getUser().subscribe(data => {
//      this.user = data;
//      console.log(data); 
//    });
  }

}
