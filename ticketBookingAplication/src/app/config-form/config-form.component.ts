import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Configuration } from '../configuration';
import { ConfigurationService } from '../configuration-service.service';

@Component({
  selector: 'app-config-form',
  imports: [FormsModule, CommonModule],
  templateUrl: './config-form.component.html',
  styleUrl: './config-form.component.css'
})

export class ConfigFormComponent {

    //form inputs
    totalNumberOfTickets = '';
    ticketReleasingRate = '';
    customerRetrievalRate = '';
    maxTicketCapacity = '';

    private configData = {};

    constructor (private config: ConfigurationService) {}

    //send form data to the backend
    saveConfig(): void {
      if (this.isFormValid()) {
        const configData: Configuration = {
          totalTickets: +this.totalNumberOfTickets, // Convert to number
          ticketReleaseRate: +this.ticketReleasingRate,
          customerRetrievalRate: +this.customerRetrievalRate,
          maxTicketCapacity: +this.maxTicketCapacity,
        };
  
        // Call the service to send the POST request
        this.config.saveConfiguration(configData).subscribe(
          (response) => {
            console.log('Configuration saved successfully:', response);
            if(response){
              alert("Configuration saved successfully")
            }else{
              alert("Coulden't save Configuration")
            }
          }
        );
      }else{
        alert("Total Tickets sholud be less than Max Ticket Capacity.");
      }
    }
  
    //validate form inputs
    isFormValid(): boolean {
      const totalTicketsValid = this.isValidNumber(this.totalNumberOfTickets);
      const releaseRateValid = this.isValidNumber(this.ticketReleasingRate);
      const retrievalRateValid = this.isValidNumber(this.customerRetrievalRate);
      const capacityValid = this.isValidNumber(this.maxTicketCapacity);

      const totalLessThanCapacity = +this.totalNumberOfTickets < +this.maxTicketCapacity;

      return totalTicketsValid && releaseRateValid && retrievalRateValid && capacityValid && totalLessThanCapacity;
    }

    // check whether its a number or not and more than or equal to zero
    isValidNumber(value: string): boolean {
      const numberValue = +value;
      return !isNaN(numberValue) && numberValue >= 0;
    }

    //Initializing variables on page load
    ngOnInit(): void {
      this.config.getConfiguration().subscribe((data: Configuration) => {
        this.totalNumberOfTickets = data?.totalTickets != null ? data.totalTickets.toString() : "";
        this.ticketReleasingRate = data?.ticketReleaseRate != null ? data.ticketReleaseRate.toString() : "";
        this.customerRetrievalRate = data?.customerRetrievalRate != null ? data.customerRetrievalRate.toString() : "";
        this.maxTicketCapacity = data?.maxTicketCapacity != null ? data.maxTicketCapacity.toString() : "";
      });
    }    

}
