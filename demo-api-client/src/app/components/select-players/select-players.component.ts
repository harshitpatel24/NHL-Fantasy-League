import { Component, OnInit, ViewChild, Input } from '@angular/core';
import {Location} from '@angular/common';
import { SelectionModel } from '@angular/cdk/collections';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import {Router, ActivatedRoute, Params} from '@angular/router';
import { PlayerService } from '../../services/player/player.service';
import { LeagueMember } from '../../models/league-member.model';
import { League } from '../../models/league.model';
import { User } from '../../models/user.model';
import { MemberTeam } from '../../models/member-team.model';


@Component({
  selector: 'app-select-players',
  templateUrl: './select-players.component.html',
  styleUrls: ['./select-players.component.css']
})
export class SelectPlayersComponent implements OnInit {

  name: any;
  rank: any;
  position: any;
  playerValue: any;
  teamAbbr: any;
  leagueid: any;
  userid: any
  checkedData = []; 
       
  constructor(private playerService: PlayerService, private router: Router, private route: ActivatedRoute, private location: Location) { }
     
   ngOnInit(){
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
        this.name = data;
        this.position = data;
        this.rank = data;
        this.playerValue = data;
        this.teamAbbr = data;
        this.checkedDataSource.data = (data as Element[]);
      });

      this.playerService.getAllPlayers().subscribe(data => {
        this.checkedDataSource.data.forEach((k: any, item) => {
          data = data.filter(player2 => player2.playerId !=  k.playerId);
        });
        this.name = data;
        this.position = data;
        this.rank = data;
        this.playerValue = data;
        this.teamAbbr = data;
        this.dataSource.data = (data as Element[]);
      });

    }
   
  
    displayedColumns = [ 'select','name','position','rank','playerValue','team'];
    data = Object.assign(ELEMENT_DATA);
    
    dataSource = new MatTableDataSource<Element>(this.data);
    selection = new SelectionModel<Element>(true, []);
  
    checkedDataSource = new MatTableDataSource<Element>(this.checkedData);
    checkedSelection = new SelectionModel<Element>(true, []);
  
    uncheckedData = this.data;
  
    @ViewChild('paginator') paginator: MatPaginator;
    @ViewChild('checkedpaginator') checkedpaginator: MatPaginator;
  
    ngAfterViewInit() {
      this.dataSource.paginator = this.paginator;
      this.checkedDataSource.paginator = this.checkedpaginator;
    }
   
    
    /** Whether the number of selected elements matches the total number of rows. */
  
    isAllSelected() {
      const numSelected = this.selection.selected.length;
      const numRows = this.dataSource.data.length;
      return numSelected === numRows;
    }
  
    isAllCheckedSelected() {
      const numSelected = this.checkedSelection.selected.length;
      const numRows = this.checkedDataSource.data.length;
      return numSelected === numRows;
    }
  
  /* Function to move row from table 1-->2 */
  
    moveToTableTwo() {
        this.selection.selected.forEach((k,item) => {
          this.dataSource.data.splice(this.dataSource.data.indexOf(k),1); 
          this.checkedDataSource.data.push(k);
        });
        this.dataSource = new MatTableDataSource<Element>(this.dataSource.data);
        this.checkedDataSource = new MatTableDataSource<Element>(this.checkedDataSource.data);
        this.selection.clear();
    }
  
  /* Function to move row from table 2-->1 */
  
    moveToTableOne() {
        this.checkedSelection.selected.forEach((k,item) => {
          this.checkedDataSource.data.splice(this.checkedDataSource.data.indexOf(k),1);
          this.dataSource.data.push(k);
        });
        this.dataSource = new MatTableDataSource<Element>(this.dataSource.data);
        this.checkedDataSource = new MatTableDataSource<Element>(this.checkedDataSource.data);
        this.checkedSelection.clear();
    }

    submitPlayers() {
      let flag = false; 
      let memberTeam = new MemberTeam();
      memberTeam.userid = this.userid;
      memberTeam.leagueId = this.leagueid;

      let playerIds = new Array<Number>();
      let counts = {"F": 0, "D": 0, "G": 0}
      let cost = 0;
      this.checkedDataSource.data.forEach((k: any,item) => {
        cost += k.playerValue;
        if (k.position == "C" || k.position == "C/LW" || k.position == "C/RW" || k.position == "C/LW/RW" || k.position == "LW" || k.position == "RW" || k.position == "LW/RW")
        {
          counts["F"]++; 
        }
        else if (k.position == "D")
        {
          counts["D"]++;
        }
        else if (k.position == "G")
        {
          counts["G"]++; 
        }
        playerIds.push(k.playerId);
      });

      let tests = 0; 
      if (playerIds.length != 22)
      {
        alert("Please make sure you have 22 players max/min.");
      }
      else
      {
        tests++; 
      }

      if (counts["F"] != 12)
      {
        alert("Please make sure you have 12 forwards max/min.");
      }
      else
      {
        tests++; 
      }

      if (counts["D"] != 9)
      {
        alert("Please make sure you have 9 defencemen max/min.");
      }
      else
      {
        tests++; 
      }

      if (counts["G"] != 1)
      {
        alert("Please make sure you have 1 goalie max/min.");
      }
      else
      {
        tests++; 
      }

      if (cost > 80)
      {
        alert("Exceeding your budget. Select within budget.");
      }
      else
      {
        tests++; 
      }

      if (tests == 5)
      {
        flag = true; 
      }

      memberTeam.playersIds = playerIds;
      
      if (flag != false)
      {
        this.playerService.addPlayers(memberTeam).subscribe(data => {
          if (data.user.userid != -1 && data.league.leagueId != -1)
          {
            alert("Success!");
            this.location.back();
          }
          else
          {
            alert("Failed!");
          }
        });
      }
    }
  
    /** Selects all rows if they are not all selected; otherwise clear selection. */
    
    masterToggle() {
      this.isAllSelected() ? 
        this.selection.clear() :
        this.dataSource.data.forEach(row => this.selection.select(row));
      console.log(this.data);
    }
  
    masterCheckedToggle() {
      this.isAllCheckedSelected()?
        this.checkedSelection.clear() : this.checkedDataSource.data.forEach(row => this.checkedSelection.select(row));
    }
  
    
  } 
    
  export interface Element {
    name: any;
    rank: any;
    position: any;
    playerValue: any;
    teamAbbr: any;
  }
  
  const ELEMENT_DATA: Element[] = [];
