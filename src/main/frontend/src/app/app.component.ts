import {Component, OnInit} from '@angular/core';
import {AppService} from './app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'Demo Docker Compose';
  text;

  constructor(private appService: AppService) {
  }

  ngOnInit(): void {
    this.appService.getHelloWorld().subscribe(
      response => {
        console.log(response);
        this.text = response;
      }
    );
  }

}
