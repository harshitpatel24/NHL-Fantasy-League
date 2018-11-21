import { User } from '../models/user.model';

export class League
{
    leagueId: number;
    leagueName: string;
    leagueCapacity: number;
    leaguePassword: string;
    user: User;
}