import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { map } from "rxjs/operators";
import { User } from '../../models/user.model';
import 'rxjs/add/operator/do';
import { LeagueMember } from '../../models/league-member.model';
import { MemberTeam } from '../../models/member-team.model';

@Injectable({
  providedIn: 'root'
})
export class PlayerService {

  constructor(private http: Http) { }

  getPlayersByTeam(team: String){

    let tempObj = {
      "teamName": team
    }

    return this.http.post('/api/getAllPlayersbyTeam', tempObj).pipe(map((response: Response) => response.json()));
  }

  getAllPlayers(){
    let headers = new Headers({ 'Content-Type': 'application/json' });
    //let options = new RequestOptions({ headers: headers });
    return this.http.get('/api/getAllPlayers', {headers: headers}).pipe(map((response: Response) => response.json()));
  }

  getPlayersByMember(leagueMember: LeagueMember){

    let tempObj = {
      "userid": leagueMember.user.userid,
      "leagueId": leagueMember.league.leagueId
     }

     return this.http.post('/api/getSelectedPlayersByMember', tempObj).pipe(map((response: Response) => response.json())); 
  }

  getPlayersByMembers(leagueMember: LeagueMember){
    let tempObj = {
      "memberId": leagueMember.id
     }

     return this.http.post('/api/getSelectedPlayersByMembers', tempObj).pipe(map((response: Response) => response.json()));
  }

  addPlayers(memberTeam: MemberTeam){

    let tempObj = {
      "userid": memberTeam.userid,
      "leagueId": memberTeam.leagueId,
      "playerIds": memberTeam.playersIds
    }

    return this.http.post('/api/addSelectedPlayersByMember', tempObj).pipe(map((response: Response) => response.json())); 
  }
 
  
}
