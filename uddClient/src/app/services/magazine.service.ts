import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MagazineService {

  constructor(private httpClient: HttpClient) { }

  getAllMagazines(){
    return this.httpClient.get('http://localhost:8080/magazine/getAllMagazines');
  }

  createTransaction(price){
    console.log(price);
    return this.httpClient.post('http://localhost:8080/magazine/createKoncentratorData', price);
  }
  ////NGROK http://dee1c4ff.ngrok.io
  sendTransaction(transaction){
     return this.httpClient.post('http://localhost:8181/api/transakcija/kreirajTransakciju', transaction);
    //return this.httpClient.post('http://dee1c4ff.ngrok.io/api/transakcija/kreirajTransakciju', transaction);
  }

  allowUser(magazineId){
    return this.httpClient.post('http://localhost:8080/magazine/allowUser', magazineId);
  }

  allowUser2(magazineName) {
    console.log(magazineName);
    return this.httpClient.post('http://localhost:8080/magazine/allowUser2', magazineName);
  }

  downloadPaper(name){
    //return this.httpClient.get('http://localhost:8080/paper/download/' + paper.name);
    window.location.href="http://localhost:8080/paper/download/" + name;
  }  
}
