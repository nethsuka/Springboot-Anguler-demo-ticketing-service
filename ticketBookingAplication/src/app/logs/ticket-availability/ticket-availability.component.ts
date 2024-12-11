import { Component, input} from '@angular/core';

@Component({
  selector: 'app-ticket-availability',
  imports: [],
  templateUrl: './ticket-availability.component.html',
  styleUrl: './ticket-availability.component.css'
})

export class TicketAvailabilityComponent {
  ticketCount = input(0);
}
