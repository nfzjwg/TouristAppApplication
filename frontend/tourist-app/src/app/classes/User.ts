import { Favourite } from './Favourite';
import { Rent } from './Rent';
import { Car } from './Car';
import { Motobike } from './Motobike';
import { Receipt } from './Receipt';

export class User{
    constructor(username : string, clientID: string, role:string, email : string){
        this.username= username;
        this.clientID=clientID;
        this.email= email;
        this.role = role;
    }
    id : number;
    username : string;
    clientID: string;
    email : string;
    role:string;
    ratingNumber : number;
    ratingValue : number;
    favourites : Favourite;
    rent : Rent;
    cars : Car;
    motobikes : Motobike;
    receipt : Receipt
}