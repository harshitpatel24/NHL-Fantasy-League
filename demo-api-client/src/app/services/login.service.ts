import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { map } from "rxjs/operators";
import { User } from '../models/user.model';
import 'rxjs/add/operator/do';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  users = []
  constructor(private http: Http) { }

  getUser(user: User){
    //let s = '/api/get-user/' + user.username + '/' + user.email + '/' + user.password
    let tempObj = {
      "userid":user.userid,
      "uname": user.username,
      "email": user.email,
      "password": user.password
    }
    return this.http.post('/api/get-user', tempObj)
    //return this.http.get('/api/get-user')
    .pipe(map((response: Response) => response.json()));
  }
}
