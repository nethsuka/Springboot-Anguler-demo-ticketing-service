import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { ConfigFormComponent } from './config-form/config-form.component';
import { ConfigurationService } from './configuration-service.service';
import { CommonModule } from '@angular/common';
import { LogsComponent } from './logs/logs.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ConfigFormComponent, CommonModule, LogsComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent{

  title = 'Ticket Booking Aplication';

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
