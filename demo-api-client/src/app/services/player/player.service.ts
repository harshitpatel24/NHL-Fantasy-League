import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { map } from "rxjs/operators";
import { User } from '../../models/user.model';
import 'rxjs/add/operator/do';

@Injectable({
  providedIn: 'root'
})
export class PlayerService {

  constructor(private http: Http) { }

 
  
}
