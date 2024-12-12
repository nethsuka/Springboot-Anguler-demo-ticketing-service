import { Component, input} from '@angular/core';

@Component({
  selector: 'app-ticket-availability',
  imports: [],
  templateUrl: './ticket-availability.component.html',
})

export class TicketAvailabilityComponent {
  ticketCount = input(0);
}
