import { Component, OnInit } from '@angular/core';
import { League } from '../../models/league.model';
import { User } from '../../models/user.model';
import { LeagueService } from '../../services/league/league.service'; 
import {Router, ActivatedRoute, Params} from '@angular/router';

@Component({
  selector: 'app-create-league',
  templateUrl: './create-league.component.html',
  styleUrls: ['./create-league.component.css']
})
export class CreateLeagueComponent implements OnInit {

  league: League;
  user: User;

  constructor(private leagueService: LeagueService, private route: ActivatedRoute) { }

  ngOnInit() 
  {
    this.user = new User();
    this.league = new League();
    this.league.user = this.user;
    this.route.params.subscribe(params => {
      this.user.userid = +params['id'];
   });

  }

  submitLeague()
  {
    this.leagueService.addLeague(this.league).subscribe(data => {
      this.league = data;

      if (this.league.leagueId != -1)
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
