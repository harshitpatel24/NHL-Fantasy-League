import { Component, OnInit } from '@angular/core';
// import { League } from '../../models/league.model';
// import { User } from '../../models/user.model';
import { PlayerService } from '../../services/player/player.service'; 
import {Router, ActivatedRoute, Params} from '@angular/router';

@Component({
  selector: 'app-create-league',
  templateUrl: './create-league.component.html',
  styleUrls: ['./create-league.component.css']
})
export class PlayerComponent implements OnInit {

  

  constructor(private playerService: PlayerService, private route: ActivatedRoute) { }

  ngOnInit() 
  {
    this.addPlayer()
   };

   addPlayer(){

   }
  }