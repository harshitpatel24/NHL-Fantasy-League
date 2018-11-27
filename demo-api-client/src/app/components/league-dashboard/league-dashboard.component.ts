import { Component, OnInit } from '@angular/core';
import { League } from '../../models/league.model';
import { User } from '../../models/user.model';
import { MatchSchedule } from '../../models/matchschedule.model';
import { LeagueMember } from '../../models/league-member.model';
import { LeagueService } from '../../services/league/league.service'; 
import {Router, ActivatedRoute, Params} from '@angular/router';

@Component({
  selector: 'app-league-dashboard',
  templateUrl: './league-dashboard.component.html',
  styleUrls: ['./league-dashboard.component.css']
})

export class LeagueDashboardComponent implements OnInit {

  matchschedule: MatchSchedule[];
  id: number;
  today = new Date();
  leagueLeaders : LeagueMember[];
  leagueid : number;
  league : League;
  leagueName : string;
  leagueMember: {};

  constructor(private leagueService: LeagueService, private router: Router,private route: ActivatedRoute) { }

  ngOnInit() {
      this.route.params.subscribe(params => {
        this.leagueid = +params['leagueid'];
        this.id = +params['id'];
      });

      if (this.leagueid != null)
      {
        this.leagueMember = { leagueId: this.leagueid, userId: this.id };
        var month = this.today.getMonth()+1;
        var currentdate = this.today.getFullYear()+"-"+ month+"-"+ this.today.getDate();
          this.leagueService.getMatchSchedule(currentdate).subscribe(data => {
          this.matchschedule = data;
        });
        this.leagueService.getLeagueLeaders(this.leagueid).subscribe(data => {
          this.leagueLeaders = data;
        });
        this.getLeagueByLeagueId();
      }
  }

  getLeagueByLeagueId(){
      this.leagueService.getLeagueByLeagueId(this.leagueid).subscribe(data => {
      this.league = data;
      this.leagueName = this.league.leagueName;});
  }
}
  
