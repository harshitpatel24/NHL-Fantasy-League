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
      // this.name = data;
      // this.position = data;
      // this.rank = data;
      // this.playerValue = data;
      // this.teamAbbr = data;
      // this.dataSource.data = (data as Element[]);
      this.dataSource = new MatTableDataSource<PlayerElement>(ELEMENT_DATA);
      this.dataSource.paginator = this.paginator;
    });

    // this.dataSource = new MatTableDataSource<PlayerElement>(ELEMENT_DATA);
    // this.dataSource.paginator = this.paginator;
  }

}

export interface PlayerElement {
  name: any;
  rank: any;
  position: any;
  playerValue: any;
  teamAbbr: any;
}

// const ELEMENT_DATA: PlayerElement[] = [
//   {position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H'},
//   {position: 2, name: 'Helium', weight: 4.0026, symbol: 'He'},
//   {position: 3, name: 'Lithium', weight: 6.941, symbol: 'Li'},
//   {position: 4, name: 'Beryllium', weight: 9.0122, symbol: 'Be'},
//   {position: 5, name: 'Boron', weight: 10.811, symbol: 'B'},
//   {position: 6, name: 'Carbon', weight: 12.0107, symbol: 'C'},
//   {position: 7, name: 'Nitrogen', weight: 14.0067, symbol: 'N'},
//   {position: 8, name: 'Oxygen', weight: 15.9994, symbol: 'O'},
//   {position: 9, name: 'Fluorine', weight: 18.9984, symbol: 'F'},
//   {position: 10, name: 'Neon', weight: 20.1797, symbol: 'Ne'},
// ];
