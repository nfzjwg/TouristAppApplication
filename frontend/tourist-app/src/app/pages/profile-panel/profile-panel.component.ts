import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/User/user.service';

@Component({
  selector: 'app-profile-panel',
  templateUrl: './profile-panel.component.html',
  styleUrls: ['./profile-panel.component.css']
})
export class ProfilePanelComponent implements OnInit {
  userService : UserService
  constructor(userService: UserService) {
    this.userService= userService
   }

  ngOnInit() {
  }

}
