import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { map } from "rxjs/operators";
import { League } from '../../models/league.model';

@Injectable({
  providedIn: 'root'
})
export class LeagueService {

  constructor(private http: Http) { }

  addLeague(league: League){
    console.log(league);

    let tempObj = {
      "leagueName": league.leagueName,
      "leagueCapacity": league.leagueCapacity,
      "leaguePassword": league.leaguePassword,
      "creatorId" : { "userid" : league.user.userid }
    }

    return this.http.post('/api/addLeague', tempObj).pipe(map((response: Response) => response.json()));
  }
}
