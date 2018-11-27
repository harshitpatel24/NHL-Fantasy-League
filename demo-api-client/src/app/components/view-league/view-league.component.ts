import { Component, OnInit } from '@angular/core';
import { League } from '../../models/league.model';
import { User } from '../../models/user.model';
import { LeagueService } from '../../services/league/league.service'; 
import {Router, ActivatedRoute, Params} from '@angular/router';

@Component({
  selector: 'app-view-league',
  templateUrl: './view-league.component.html',
  styleUrls: ['./view-league.component.css']
})
export class ViewLeagueComponent implements OnInit {

  createdleague: League[];
  joinedleague: League[];
  user: User;
  id: number;
  path: any;

  constructor(private leagueService: LeagueService, private router: Router,private route: ActivatedRoute) { }

 
  ngOnInit() {

    this.route.params.subscribe(params => {
      this.id = +params['id'];
   });

   this.path = this.router.url + '/league/';

   if (this.id != null)
   {
     let usr = {
       userid: this.id
      }

        this.leagueService.showCreatedLeagues(usr).subscribe(data => {
        this.createdleague = data;
      });

      this.leagueService.showJoinedLeagues(usr).subscribe(data =>{
        this.joinedleague = data;
      });
   }
   }
}
