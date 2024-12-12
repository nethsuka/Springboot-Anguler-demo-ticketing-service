import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-start-stop-button',
  imports: [],
  templateUrl: './start-stop-button.component.html',
})
export class StartStopButtonComponent {

  constructor(private http: HttpClient) {}

  //send start program request to the backend
  startTicketSelling() {
    this.http.get("http://localhost:8080/api/start").subscribe({
      next: () => {
        console.log('Ticket selling process started successfully');
      },
      error: (error) => {
        console.error('Error starting ticket selling process:', error);
      }
    });
  }

  //send stop program request to the backend
  stopTicketSelling() {
    this.http.get("http://localhost:8080/api/stop").subscribe({
      next: () => {
        console.log('Ticket selling process stoped successfully');
      },
      error: (error) => {
        console.error('Error stop ticket selling process:', error);
      }
    });
  }
}
