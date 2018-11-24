import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { map } from "rxjs/operators";
import { League } from '../../models/league.model';
import { LeagueMember } from '../../models/league-member.model';

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

  joinLeague(leagueMember: LeagueMember)
  {
    let tempObj = {
      "userid" : leagueMember.user.userid,
      "leagueId":  leagueMember.league.leagueId,
      "leaguePassword": leagueMember.password
    }

    console.log(tempObj);

    return this.http.post('/api/addLeagueMember', tempObj).pipe(map((response: Response) => response.json()));
    

  }
  showCreatedLeagues(usr){
    let tempObj = {
      "creatorId": usr.userid,
    }
    return this.http.post('/api/showcreatedleague', tempObj).pipe(map((response: Response) => response.json()));
  }
}
