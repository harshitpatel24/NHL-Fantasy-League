import { Component, OnInit, ViewChild } from '@angular/core';
import {MatPaginator, MatTableDataSource} from '@angular/material';
import {Router, ActivatedRoute, Params} from '@angular/router';
import { PlayerService } from '../../services/player/player.service';
import { League } from '../../models/league.model';
import { User } from '../../models/user.model';
import { LeagueMember } from '../../models/league-member.model';

@Component({
  selector: 'app-member-players',
  templateUrl: './member-players.component.html',
  styleUrls: ['./member-players.component.css']
})
export class MemberPlayersComponent implements OnInit {

  constructor(private playerService: PlayerService, private router: Router,private route: ActivatedRoute) { }

  displayedColumns: string[] = ['rank','name','position','team','playerValue'];
  dataSource: any;
  leagueid: any;
  userid: any;
  memberid: any;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.memberid = +params['memberid'];
      //this.leagueid = +params['leagueid'];
      //this.userid = +params['id'];
    });

    let leagueMember = new LeagueMember();
    // let user = new User();
    // user.userid = this.userid;
    // let league = new League();
    // league.leagueId = this.leagueid;
    // leagueMember.user = user;
    // leagueMember.league = league; 
    leagueMember.id = this.memberid;

    this.playerService.getPlayersByMembers(leagueMember).subscribe(data => {
      const ELEMENT_DATA: PlayerElement[] = data;
      console.log(data);
      this.dataSource = new MatTableDataSource<PlayerElement>(ELEMENT_DATA);
      this.dataSource.paginator = this.paginator;
    });
  }

}

export interface PlayerElement {
  hockeyPlayer: any;
  // name: any;
  // rank: any;
  // position: any;
  // playerValue: any;
  // teamAbbr: any;
}
