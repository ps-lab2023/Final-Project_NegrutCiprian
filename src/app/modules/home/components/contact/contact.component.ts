import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {EmailService} from "./email.service";

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent implements OnInit{

  FormData!: FormGroup;

  constructor(private builder: FormBuilder, private contact: EmailService) { }

  ngOnInit() {
    this.FormData = this.builder.group({
      EmailAddress:  new FormControl('', {validators: [Validators.required, Validators.email]}),
      Body: new FormControl('', [Validators.required])
    })
  }

  onSubmit(FormData: any) {
    this.contact.SendEmail(FormData)
      .subscribe(response => {
        location.href = 'https://mailthis.to/confirm'
      }, error => {
        console.warn(error.responseText)
        console.log({ error })
      })
  }

}
