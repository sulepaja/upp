import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { LogUser } from '../../entities/logUser';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css', './utils.css']
})
export class LoginComponent implements OnInit {
  
  private logUser: LogUser = new LogUser();
  private loginError = false;
  private form: FormGroup;
  private error: string;
  private serverUser: any;

  constructor(private userService: UserService, @Inject(FormBuilder) formBuilder: FormBuilder, private router: Router, private authenticationService: AuthenticationService) {

    this.form = formBuilder.group({
      email: new FormControl('',[Validators.required]),
      password: new FormControl('',[Validators.required])
    });

   }

  ngOnInit() {
  }

  login(){
    this.logUser.email = this.form.value.email;
    this.logUser.password = this.form.value.password;
    console.log(this.logUser);
    let response = this.userService.logUser(this.logUser).subscribe(data =>{
      console.log("PODACI IZ KOMPONENTE SU:::::");
      this.serverUser = this.authenticationService.getUserFromService();
      console.log(this.serverUser);
      if(this.serverUser != null){
        let redirect = this.authenticationService.getRedirectUrl() ? this.authenticationService.getRedirectUrl() : '';
        this.loginError = false;
        this.router.navigate(['']);
        console.log("Uspelo logovanje");
      }
      else{
        this.loginError = true;
      }      
    }, error => {
      this.error = "Bad credentials";
      console.log(error);      
    });
    
  }
}


/* 
this.userService.logUser(this.logUser).subscribe(data =>{
      console.log(data);
      if(data == null){
        this.loginError = true;
      }
      else {
        this.loginError = false;
        this.router.navigate(['/']);
      }
    });

    */