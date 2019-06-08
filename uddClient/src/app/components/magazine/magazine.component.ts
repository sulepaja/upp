import { Component, OnInit } from '@angular/core';
import { MagazineService } from 'src/app/services/magazine.service';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-magazine',
  templateUrl: './magazine.component.html',
  styleUrls: ['./magazine.component.css']
})

export class MagazineComponent implements OnInit {

  private magazines: any[];
  private userMags: any[];
  private allowedUsers: any[];

  private loggedUser: any;
  private userLogged: boolean;


  constructor(private magazineService: MagazineService, private authService: AuthenticationService, private router: Router) { }

  ngOnInit() {
    this.magazineService.getAllMagazines().subscribe((data: any) => {
      this.magazines = data;
      this.userMags = [];
      console.log(data);

      this.loggedUser = this.authService.getUserFromService();
      if(this.loggedUser != null){
        this.userLogged = true;

        this.magazines.forEach((magazine) => {
          this.allowedUsers = magazine.allowedUsers;
          this.allowedUsers.forEach((user) => {
            if(this.loggedUser.id == user.id){
              this.userMags.push(magazine.id);
             // console.log(this.userMags);
            }
          });
        });
      }
        else{
          this.userLogged = false;
        }
    });
  }

  buyAccess(magazine: any){
    if(!this.userLogged){
      this.router.navigate(['/login']);
    }
    else{
      console.log(magazine);
      if(magazine.price != 0){
        this.magazineService.createTransaction(magazine.price).subscribe(data => {
          console.log(data);
          localStorage.setItem('magazine', magazine.id);
          this.magazineService.sendTransaction(data).subscribe((response:any) => {
            console.log(response);
            top.location.href = "http://localhost:4200/opcije/" + response.id;
          });
        });
      }
      else{
        this.magazineService.allowUser(magazine.id).subscribe( data => {
          location.reload();
        });
      }
    }
  }

  download(paper){
    this.magazineService.downloadPaper(paper.locationOnDrive);
  }

  redirectPaper(paper){
    localStorage.setItem('paper', JSON.stringify(paper));
    this.router.navigate(['/paper']);
  }
}
