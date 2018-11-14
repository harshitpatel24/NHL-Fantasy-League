import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { map } from "rxjs/operators";
import { User } from '../../models/user.model';
import 'rxjs/add/operator/do';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  users = []
  constructor(private http: Http) { }
  
  getUser(user){

    let tempObj = {
      "email": user.email,
      "password": user.password
    }

    return this.http.post('/api/get-user', tempObj).pipe(map((response: Response) => response.json()));
  }

  createUser(user){
    let tempObj = {
      "uname": user.uname,
      "email": user.email,
      "password": user.password
    }

    return this.http.post('/api/register', tempObj).pipe(map((response: Response) => response.json()));
  }
}
