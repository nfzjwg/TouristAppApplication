import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/User/user.service';
import { FormBuilder, FormGroup, Validators} from "@angular/forms";
import { Router } from '@angular/router';
import { User } from 'src/app/classes/User';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm : FormGroup
  helper : Map<String,String>
  constructor(private fb : FormBuilder, private router : Router, private userService : UserService) { 
    this.loginForm= this.fb.group({
      username: ["", Validators.required],
	    password: ["", Validators.required],
    });
  }

  ngOnInit() {
  }
   redi(){
    this.userService.redirectAfterLogin()
  }
 
  login(){
    if(this.loginForm.invalid){
      console.log("hiba")
      return
    }
    this.userService.login(this.loginForm.value.username,
        this.loginForm.value.password).then(response =>{
          if(response){
            console.log(this.loginForm.value.username)
            console.log(this.loginForm.value.password)
          }
          this.router.navigate(['/home'])
        })
        console.log("logged in")
  }
  
}
