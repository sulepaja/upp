import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  private loggedUser: any;
  private userLogged: boolean;

  constructor(private authService: AuthenticationService) { }



  ngOnInit() {
    this.loggedUser = this.authService.getUserFromService();
    if(this.loggedUser != null){
      this.userLogged = true;
      console.log(this.loggedUser);
    }
    else{
      this.userLogged = false;
    }
  }

  clickLogout(){
    this.authService.logOutUser();
  }

}
