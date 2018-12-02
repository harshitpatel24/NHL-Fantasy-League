import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { LeagueMember } from '../../models/league-member.model';
import { map } from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class ScoreLogService {

  constructor(private http: Http) { }

  getScoreLogs(leagueMember: LeagueMember){

    let tempObj = {
      "userid": leagueMember.user.userid,
      "leagueId": leagueMember.league.leagueId
    }

    return this.http.post('/api/getPointsHistory', tempObj).pipe(map((response: Response) => response.json()));
  }
}
