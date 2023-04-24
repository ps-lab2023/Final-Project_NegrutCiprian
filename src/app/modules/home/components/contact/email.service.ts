import {Injectable, NgModule} from '@angular/core';

import {HttpClient, HttpClientModule} from '@angular/common/http';
import {FormGroup} from "@angular/forms";

@NgModule({
  imports: [
    HttpClientModule
  ],
  providers: [
    EmailService
  ]
})
@Injectable({
  providedIn: 'root'
})

export class EmailService {

  private url = "https://mailthis.to/CipriaNGR";

  constructor(private http: HttpClient) {}

  SendEmail(input: FormGroup) {
    console.log(this.http.post<any>(this.url, input));
    return this.http.post<any>(this.url, input);
  }

}
