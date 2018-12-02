import { Component, OnInit, ViewChild } from '@angular/core';
import {MatPaginator, MatTableDataSource} from '@angular/material';
import {Router, ActivatedRoute, Params} from '@angular/router';
import { PlayerService } from '../../services/player/player.service';
import { League } from '../../models/league.model';
import { User } from '../../models/user.model';
import { LeagueMember } from '../../models/league-member.model';

@Component({
  selector: 'app-player-list',
  templateUrl: './player-list.component.html',
  styleUrls: ['./player-list.component.css']
})
export class PlayerListComponent implements OnInit {

  constructor(private playerService: PlayerService, private router: Router,private route: ActivatedRoute) { }

  displayedColumns: string[] = ['rank','name','position','team','playerValue'];
  dataSource: any;
  leagueid: any;
  userid: any;

  @ViewChild(MatPaginator) paginator: MatPaginator;

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

    this.playerService.getPlayersByMember(leagueMember).subscribe(data => {
      const ELEMENT_DATA: PlayerElement[] = data;
      this.dataSource = new MatTableDataSource<PlayerElement>(ELEMENT_DATA);
      this.dataSource.paginator = this.paginator;
    });
  }

}

export interface PlayerElement {
  name: any;
  rank: any;
  position: any;
  playerValue: any;
  teamAbbr: any;
}