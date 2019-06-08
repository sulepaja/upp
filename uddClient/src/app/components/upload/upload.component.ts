import { Component, OnInit, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { UploadService } from '../../services/upload.service';
import { HttpResponse, HttpEventType } from '@angular/common/http';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import { MagazineService } from 'src/app/services/magazine.service';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css','./utils.css']
})
export class UploadComponent implements OnInit {
  
  private magazines: any[];
  private magazineId: string;

  showFile = false;
  fileUploads: Observable<string[]>;
  @Input() fileUpload: string;

  selectedFiles: FileList;
  currentFileUpload: File;
  progress: { percentage: number } = { percentage: 0 };

  title = '';
  description = '';


  formUpload = new FormGroup({
    name: new FormControl('', Validators.compose ([Validators.required])),
    keywords: new FormControl('', Validators.compose ([Validators.required])),
    abbstract: new FormControl('', Validators.compose ([Validators.required])),
    selectedMagazine: new FormControl('',Validators.compose ([Validators.required])),
  });

  constructor(private uploadService: UploadService, private magazineService: MagazineService) { }

  ngOnInit() {
    this.magazineService.getAllMagazines().subscribe((data: any) => {
      this.magazines = data;
    });
  }

  showFiles(enable: boolean) {
    this.showFile = enable;

    if (enable) {
      this.fileUploads = this.uploadService.getFiles();
    }
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  upload(formUpload) {
    this.progress.percentage = 0;

    this.currentFileUpload = this.selectedFiles.item(0);    

    this.magazines.forEach((magazine) => {
      if(magazine.name == formUpload.selectedMagazine){
        console.log(magazine);
        this.magazineId = magazine.id;
        console.log(this.magazineId);
      }
    })

    this.uploadService.pushFileToStorage(this.currentFileUpload, formUpload, this.magazineId).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        console.log('File is completely uploaded!');
      }
    });
    
    this.selectedFiles = undefined;
  }

}
