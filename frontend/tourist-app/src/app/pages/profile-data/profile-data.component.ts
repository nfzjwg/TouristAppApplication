import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/User/user.service';

@Component({
  selector: 'app-profile-data',
  templateUrl: './profile-data.component.html',
  styleUrls: ['./profile-data.component.css']
})
export class ProfileDataComponent implements OnInit {
  rate : number;
  userService : UserService;
  constructor( userService : UserService) { }
  ngOnInit() {
    var result = this.userService.user.ratingValue/ this.userService.user.ratingNumber
    var helper = +result.toFixed(1)
    this.rate = helper
    console.log(this.rate)
  }

}
