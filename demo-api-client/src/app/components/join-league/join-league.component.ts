import { Component, OnInit, Input } from '@angular/core';
import { LeagueService } from '../../services/league/league.service';
import { League } from '../../models/league.model';
import { User } from '../../models/user.model';
import { LeagueMember } from '../../models/league-member.model';

@Component({
  selector: 'app-join-league',
  templateUrl: './join-league.component.html',
  styleUrls: ['./join-league.component.css']
})
export class JoinLeagueComponent implements OnInit {

  league: League;
  leagueMember: LeagueMember; 

  @Input()
  user: User;

  constructor(private leagueService: LeagueService) { }

  ngOnInit() {
    this.league = new League();
    this.leagueMember = new LeagueMember();
  }

  joinLeague() {
    let u = new User();
    u.userid = this.user.userid;
    let lM = new LeagueMember();
    lM.user = u;
    lM.league = this.league;
    lM.password = this.league.leaguePassword;

    this.leagueService.joinLeague(lM).subscribe(data => {
      this.leagueMember = data;

      if (this.leagueMember.id != -1)
      {
        alert("Success!");
      }
      else
      {
        alert("Fail!");

      }

    });
    

  }

}
