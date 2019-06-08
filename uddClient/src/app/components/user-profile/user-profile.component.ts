import { Component, OnInit } from '@angular/core';
import { SciencePaperService } from '../../services/science-paper.service';
import { MagazineService } from 'src/app/services/magazine.service';
import { HttpResponse, HttpEventType } from '@angular/common/http';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  constructor(private paperService: SciencePaperService, private magazineService:MagazineService, private authService: AuthenticationService) { }

  sciencePapers: any[];
  sciencePapersMainEditor = [];
  sciencePapersUser = [];
  selectedPaper: any;
  secondEditorSiencePapers = []
  showPopUpComment = false;
  commentValue = '';
  loggedUser: any;

  selectedFiles: FileList;
  currentFileUpload: File;
  progress: { percentage: number } = { percentage: 0 };

  ngOnInit() {
    this.loggedUser = this.authService.getUserFromService();
    console.log(this.loggedUser);

    if(this.loggedUser.role=='SECEDITOR'){
      //this.paperService.
    }

    this.paperService.getAllPapers("AAA").subscribe((data:any[]) => {      
      this.sciencePapers = data;
      console.log("PAPER DATA JE");
      console.log(this.sciencePapers);
      for(let paper of this.sciencePapers){        
        if(paper.status=='editor' && paper.editor == this.loggedUser.id){
          this.sciencePapersMainEditor.push(paper);
          console.log("Pozz iz for-a");
        }
        if(paper.status=='wrong_formatting'){
          this.sciencePapersUser.push(paper);          
        }
        if(paper.status=='sec_editor_rev' && paper.editor == this.loggedUser.id){
          this.secondEditorSiencePapers.push(paper);
        }
      }
    });
  }

  download(paper){
    this.magazineService.downloadPaper(paper.locationOnDrive)
  }

  deletePaper(paper){
    this.paperService.deletePaper(paper).subscribe(data => {
    });
  }

  openReworkCommentPopUp(paper){
    this.selectedPaper = paper;
    this.togglePopUpVisibility();
  }

  togglePopUpVisibility(){
    this.showPopUpComment = !this.showPopUpComment;
    this.commentValue = '';
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  uploadPaper(paper){
    this.progress.percentage = 0;
    this.currentFileUpload = this.selectedFiles.item(0);        

    this.paperService.updateSciencePaper(this.currentFileUpload, paper).subscribe((event:any) => {
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        console.log('File is completely uploaded!');
      }
    });
    this.selectedFiles = undefined;
  }

  sendComment(){
    console.log(this.commentValue);
    this.paperService.addComment(this.commentValue, this.selectedPaper.id).subscribe(data => {
      this.togglePopUpVisibility();
    });
  }

  approve(paper){
    this.paperService.approveByMainEditor(paper).subscribe(data => {

    });
  }

}
