import { User } from '../models/user.model';
import { League } from '../models/league.model'; 

export class LeagueMember
{
    id: number;
    password: string;
    user: User;
    league: League;

}