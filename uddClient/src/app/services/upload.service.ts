import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpHeaders, HttpRequest} from '@angular/common/http';
import { Observable } from 'rxjs';
import {AuthenticationService} from "./authentication.service";

@Injectable({
  providedIn: 'root'
})
export class UploadService {

  serverUser :any;
  authenticationService: any;

  constructor(private http: HttpClient,  authenticationService: AuthenticationService) {
    this.authenticationService = authenticationService;
  }

  pushFileToStorage(file: File, formUpload, magazineId): Observable<HttpEvent<{}>> {
    this.serverUser = this.authenticationService.getUserFromService();
    console.log("Ulogovani -> " + this.serverUser);

    const formdata: FormData = new FormData();

    formdata.append('textPDF', file);
    formdata.append('name',formUpload.name);
    formdata.append('keywords',formUpload.keywords);
    formdata.append('abbstract',formUpload.abbstract);


    const formdataNewTry: FormData = new FormData(formUpload);

    let headers = new HttpHeaders();

    headers.append('Content-type', 'multipart/form-data');
    //headers.append('Accept', 'application/json');

/*
    const req = new HttpRequest('POST', 'http://localhost:8080/upload/post', formdata, {
      reportProgress: true,
      responseType: 'text',
      headers: headers
    });
*/
    const req = new HttpRequest('POST', 'http://localhost:8080/paper/postUpload/' + magazineId, formdata, {
      reportProgress: true,
      responseType: 'text',
      headers: headers
    });
    return this.http.request(req);


  }

  uploadIndex(formdata) {
    return this.http.post('http://localhost:8080/index/add/1',formdata);
  }

  getFiles(): Observable<any> {
    return this.http.get('http://localhost:8080/paper/getallfiles');
  }
}
