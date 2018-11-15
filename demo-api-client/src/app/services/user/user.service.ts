import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { map } from "rxjs/operators";
import { User } from '../../models/user.model';
import 'rxjs/add/operator/do';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: Http) { }

  getAll(){
    return this.http.get('/api/users')
    .pipe(map((response: Response) => response.json()));
  }

  getUser(usr){

    let tempObj = {
      "userid": usr.userid,
    }

    return this.http.post('/api/user', tempObj).pipe(map((response: Response) => response.json()));
  }

  updateUser(usr){
    let tempObj = {
      "userid": usr.userid,
      "uname": usr.uname,
      "email": usr.email,
      "password": usr.password
    }

    return this.http.post('/api/update-user', tempObj).pipe(map((response: Response) => response.json()));
  }
  
}
