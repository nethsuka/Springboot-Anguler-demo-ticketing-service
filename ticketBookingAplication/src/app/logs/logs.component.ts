import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ConfigurationService } from '../configuration-service.service';
import { CommonModule } from '@angular/common';
import { TicketAvailabilityComponent } from './ticket-availability/ticket-availability.component';

@Component({
  selector: 'app-logs',
  imports: [CommonModule, TicketAvailabilityComponent],
  templateUrl: './logs.component.html',
})
export class LogsComponent {

  consoleOutputs: string[] = [];
  souldTicketCount: number = 0;

  constructor(private http: HttpClient, private config: ConfigurationService) {}

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
