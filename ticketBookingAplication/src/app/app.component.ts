import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { ConfigFormComponent } from './config-form/config-form.component';
import { ConfigurationService } from './configuration-service.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ConfigFormComponent, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent{

  title = 'Ticket Booking Aplication';
  consoleOutputs: string[] = [];
  souldTicketCount: number = 0;

  constructor(private http: HttpClient, private config: ConfigurationService) {}

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

  //Initializing variables on page load
  ngOnInit(): void {
    this.config.pollConsoleOutputs(300).subscribe({
      next: (array) => this.consoleOutputs = array,
      error: (error) => console.error('Error fetching messages', error)
    });
    
    this.config.pollSouldTickets(150).subscribe({
      next: (count) => this.souldTicketCount = count,
      error: (error) => console.error('Error fetching count', error)
    });
  }

}
