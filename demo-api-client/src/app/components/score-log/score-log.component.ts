import { Component, OnInit, ViewChild } from '@angular/core';
import {MatPaginator, MatTableDataSource} from '@angular/material';
import {Router, ActivatedRoute, Params} from '@angular/router';
import { LeagueMember } from '../../models/league-member.model';
import { ScoreLogService } from '../../services/score-log/score-log.service';
import { League } from '../../models/league.model';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-score-log',
  templateUrl: './score-log.component.html',
  styleUrls: ['./score-log.component.css']
})
export class ScoreLogComponent implements OnInit {

  displayedColumns: string[] = ['rank','name','position','team','date', 'points', 'pointSummary'];
  dataSource: any;
  leagueid: any;
  userid: any;
  memberid: any; 

  @ViewChild(MatPaginator) paginator: MatPaginator;
  
  constructor(private scoreLogService: ScoreLogService, private router: Router,private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.leagueid = +params['leagueid'];
      this.userid = +params['id'];
    });

    let leagueMember = new LeagueMember();
    let user = new User();
    user.userid = this.userid;
    let league = new League();
    league.leagueId = this.leagueid;
    leagueMember.user = user;
    leagueMember.league = league; 

    this.scoreLogService.getScoreLogs(leagueMember).subscribe(data => {
      const ELEMENT_DATA: PlayerElement[] = data;
      this.dataSource = new MatTableDataSource<PlayerElement>(ELEMENT_DATA);
      this.dataSource.paginator = this.paginator;
    });

  }
}

export interface PlayerElement {
  hockeyPlayer: any;
  date: any;
  points: any;
  pointSummary: any;
}
