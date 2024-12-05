import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { ConfigFormComponent } from './config-form/config-form.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ConfigFormComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent {
  title = 'ticketBookingAplication';

  constructor(private http: HttpClient) {}

  startTicketSelling() {
    this.http.get("http://localhost:8080/api/start").subscribe({
      next: () => {
        console.log('Ticket selling process started successfully');
      },
      error: (err) => {
        console.error('Error starting ticket selling process:', err);
      }
    });
  }

  stopTicketSelling() {
    this.http.get("http://localhost:8080/api/stop").subscribe({
      next: () => {
        console.log('Ticket selling process started successfully');
      },
      error: (err) => {
        console.error('Error starting ticket selling process:', err);
      }
    });
  }
}
