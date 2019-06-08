import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MagazineService } from 'src/app/services/magazine.service';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})
export class ResultComponent implements OnInit {
  private result: string;
  private magazineId: string;

  constructor(private activatedRoute: ActivatedRoute, private magazineService: MagazineService, private router: Router) { }

  ngOnInit() {
    this.result = this.activatedRoute.snapshot.params['result'];
    console.log(this.result);
    if(this.result == 'success'){
      this.allowMagazineToUser();
    }    
    setTimeout(() => {
      this.router.navigate(['']);
    }
    , 2500);
  }


  allowMagazineToUser(){
    this.magazineId = localStorage.getItem('magazine');
    console.log("MAGAZIN ID JD::::" + this.magazineId);
    this.magazineService.allowUser(this.magazineId).subscribe(data => {
      setTimeout(() => {
        this.router.navigate(['']);
      }
      , 2500);
    });
  }
}

//success
//failure
//error