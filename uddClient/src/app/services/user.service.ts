import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { AuthenticationService } from "./authentication.service";
import { map } from "rxjs/operators";

@Injectable({
  providedIn: "root"
})
export class UserService {
  authenticationService: any;

  constructor(
    private httpClient: HttpClient,
    authenticationService: AuthenticationService
  ) {
    this.authenticationService = authenticationService;
  }

  logUser(logUser) {
    console.log("Usao u user service");
    var token = btoa(logUser.email + ":" + logUser.password);
    this.authenticationService.setToken(token);
    let headers = new HttpHeaders();
    headers.append(
      "Authorization",
      `Basic ${this.authenticationService.getToken()}`
    );
    return this.httpClient.get("http://localhost:8080/user/login").pipe(
      map((data: Response) => {
        console.log("PODACI IZ SERVISA SU:::::::");
        console.log(data);
        this.authenticationService.setUser(data);
      })
    );
  }

  registerUser(newUser) {
    return this.httpClient.post("http://localhost:8080/user/register", newUser);
  }
}
