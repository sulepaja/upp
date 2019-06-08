import { Component, OnInit } from '@angular/core';
import { MagazineService } from 'src/app/services/magazine.service';

@Component({
  selector: 'app-paper',
  templateUrl: './paper.component.html',
  styleUrls: ['./paper.component.css']
})
export class PaperComponent implements OnInit {

  private paper: any;

  constructor(private magazineService: MagazineService) { }

  ngOnInit() {
    this.paper = JSON.parse(localStorage.getItem('paper'));
    console.log(this.paper);
  }

  download(paperName){
    this.magazineService.downloadPaper(paperName);
  }

}
