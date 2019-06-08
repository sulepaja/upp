import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { RegisterUser } from '../../entities/RegisterUser';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css', './utils.css']
})
export class RegisterComponent implements OnInit {

  private newUser: RegisterUser = new RegisterUser();
  private form: FormGroup;
  private usernameError = false;
  private emailError = false;

  constructor(private userService: UserService, @Inject(FormBuilder) formBuilder: FormBuilder, private router: Router) { 
    this.form = formBuilder.group({
      username: new FormControl('',[Validators.required]),
      email: new FormControl('',[Validators.required]),
      password: new FormControl('',[Validators.required]),
      firstname: new FormControl('',[Validators.required]),
      lastname: new FormControl('',[Validators.required]),
      country: new FormControl('',[Validators.required]),
      city: new FormControl('',[Validators.required]),
    });
  }

  ngOnInit() {
  }

  register(){
    this.newUser.username = this.form.value.username;
    this.newUser.email = this.form.value.email;
    this.newUser.password = this.form.value.password;
    this.newUser.firstname = this.form.value.firstname;
    this.newUser.lastname = this.form.value.lastname;
    this.newUser.country = this.form.value.country;
    this.newUser.city = this.form.value.city;

    console.log(this.newUser);

    this.userService.registerUser(this.newUser).subscribe((data:any) => {
      console.log(data);
      if(data.code == "success") {
        this.router.navigate(['/login']);
      }
      else if(data.code == "username"){
        this.usernameError = true;
        this.emailError = false;
      }
      else if(data.code == "email"){
        this.emailError = true;
        this.usernameError = false;
      }
    });
  }

}
