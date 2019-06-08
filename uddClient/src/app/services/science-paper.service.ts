import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpHeaders, HttpRequest} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Stringer } from '../entities/Stringer'

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
    console.log(sciencePaper);
    return this.httpClient.get('http://localhost:8080/paper/approve-by-main-editor/' + sciencePaper.id);
  }
}
