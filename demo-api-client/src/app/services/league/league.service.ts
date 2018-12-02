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
  showJoinedLeagues(usr){
    let tempObj = {
      "userId": usr.userid,
    }
    return this.http.post('/api/showjoinedleague', tempObj).pipe(map((response: Response) => response.json()));
  }
  getMatchSchedule(date){
    let tempObj = {
      "date": date,
    }
    return this.http.post('/api/getMatchBydate', tempObj).pipe(map((response: Response) => response.json()));
  }
  getLeagueLeaders(leagueid){
    let tempObj = {
      "leagueId": leagueid,
    }
    return this.http.post('/api/getLeagueLeaders', tempObj).pipe(map((response: Response) => response.json()));
  }
  getLeagueByLeagueId(leagueid){
    let tempObj = {
      "leagueId": leagueid,
    }
    return this.http.post('/api/getLeagueByLeagueId', tempObj).pipe(map((response: Response) => response.json()));
  }
}
