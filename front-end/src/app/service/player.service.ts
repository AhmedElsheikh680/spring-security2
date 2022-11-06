import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {map} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PlayerService {

  constructor(private httpClient: HttpClient) { }

  getPlayer(){
    let basicAuthHeaderString = `Basic `+ window.btoa('a@a.com'+ ':'+'12345'); //Base 64
    let header = new HttpHeaders({
      Authorization: basicAuthHeaderString
    });
    return this.httpClient.get<any>(`http://localhost:8090/basketball/start`, {headers: header}).pipe(map(
      response =>{
        console.log(response);
        return response;
      }
    ))

  }
}
