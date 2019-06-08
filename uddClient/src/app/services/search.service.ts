import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private httpClient: HttpClient) { }

  serachMagazineTitle(formTitleMagazine){

    return this.httpClient.post('http://localhost:8080/search/nameMagazine', formTitleMagazine);
  }

  serachTitle(formTitle){
    return this.httpClient.post('http://localhost:8080/search/title', formTitle);
  }

  serachAutor(formAuthor){
    return this.httpClient.post('http://localhost:8080/search/author', formAuthor);
  }

  serachKeywords(formKeywords){
    return this.httpClient.post('http://localhost:8080/search/keywords', formKeywords);
  }

  serachScientificField(formScientificField){
    return this.httpClient.post('http://localhost:8080/search/scientificField', formScientificField);
  }

  serachContent(formContent){
    return this.httpClient.post('http://localhost:8080/search/content', formContent);
  }

  searchCombination(advancedQuery){
    return this.httpClient.post('http://localhost:8080/search/combination', advancedQuery);
  }
}
