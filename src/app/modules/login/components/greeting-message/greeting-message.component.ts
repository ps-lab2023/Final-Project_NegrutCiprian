import {Component, OnInit} from '@angular/core';
import {WebSocketAPI} from "./WebSocketAPI";

@Component({
  selector: 'app-greeting-message',
  templateUrl: './greeting-message.component.html',
  styleUrls: []
})
export class GreetingMessageComponent {

  greeting: string;
  name: string;

  handleMessage(message: any){
    const jsonObj = JSON.parse(message)
    const jsonObjBody = JSON.parse(jsonObj.body)
    this.greeting = jsonObjBody.content
    console.log(this.greeting)
  }

}
