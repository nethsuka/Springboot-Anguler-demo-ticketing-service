import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ConfigFormComponent } from './config-form/config-form.component';
import { CommonModule } from '@angular/common';
import { LogsComponent } from './logs/logs.component';
import { StartStopButtonComponent } from './start-stop-button/start-stop-button.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ConfigFormComponent, CommonModule, LogsComponent, StartStopButtonComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent{
  title = 'Ticket Booking Aplication';

}
