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

  league: League[];
  user: User;
  id: number;
  constructor(private leagueService: LeagueService, private router: Router,private route: ActivatedRoute) { }

 
  ngOnInit() {

    this.route.params.subscribe(params => {
      this.id = +params['id'];
   });

   if (this.id != null)
   {
     let usr = {
       userid: this.id
      }

        this.leagueService.showCreatedLeagues(usr).subscribe(data => {
        this.league = data;
        console.log(this.league);
        for(var i = 0; i < data.length; i++) {
          var obj = data[i];
          console.log(obj.leagueId);
      }
      });
   }
   }
   goBack() {
    this.router.navigate(['users/:id', this.user.userid]);
  }
}
