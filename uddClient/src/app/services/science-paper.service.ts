import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpHeaders, HttpRequest} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Stringer } from '../entities/Stringer'
import { ReviewData } from '../entities/ReviewData';

@Injectable({
  providedIn: 'root'
})
export class SciencePaperService {

  constructor(private httpClient: HttpClient) { }

  getAllPapers(param: String) {
    return this.httpClient.get('http://localhost:8080/paper/getUnapproved');
  }

  deletePaper(paper){
    console.log(paper.id);
    return this.httpClient.delete('http://localhost:8080/paper/delete/' + paper.id);
  }

  addComment(commentValue, selectedPaperId: String){
    let stringer = new Stringer();
    stringer.commentValue = commentValue;
    return this.httpClient.post('http://localhost:8080/paper/addComment/' + selectedPaperId, stringer);
  }

  updateSciencePaper(updatedFile: File, sciencePaper:any): Observable<HttpEvent<{}>> {
    let formdata: FormData = new FormData();
    formdata.append('file', updatedFile);

    const req = new HttpRequest('POST', 'http://localhost:8080/paper/updatedSciencePaper/' + sciencePaper.id, formdata, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.httpClient.request(req);
  }

  approveByMainEditor(sciencePaper){
    return this.httpClient.get('http://localhost:8080/paper/approve-by-main-editor/' + sciencePaper.id);
  }

  findReviewers(magazineId){
    console.log("MAGAZINE ID JE");
    console.log(magazineId);
    return this.httpClient.get('http://localhost:8080/paper/magazine-reviewers/' + magazineId);
  }

  setReviewers(listOfReviwers, paperId) {
    return this.httpClient.post('http://localhost:8080/paper/confirm-reviwers/' + paperId , listOfReviwers);
  }

  addReview(data: ReviewData, paperId){
    return this.httpClient.post('http://localhost:8080/paper/add-review/' + paperId , data);
  }

  editorDecision(sciencePaper, decision){
    return this.httpClient.get('http://localhost:8080/paper/final-decision/'+sciencePaper.id+'/'+ decision);
  }
  editorDecisionSmallMistake(sciencePaper, decision){
    return this.httpClient.get('http://localhost:8080/paper/small-mistake/'+sciencePaper.id+'/'+ decision);
  }
}
