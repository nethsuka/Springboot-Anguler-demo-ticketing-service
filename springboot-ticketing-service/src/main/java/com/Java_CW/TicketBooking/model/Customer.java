package com.Java_CW.TicketBooking.model;

import org.springframework.stereotype.Component;

import com.Java_CW.TicketBooking.javaCLI.BasicConfiguration;
import com.Java_CW.TicketBooking.service.TicketPool;

@Component
public class Customer implements Runnable{
	
	private int customerId;
	private BasicConfiguration config;
	private TicketPool ticketPool;
	
	public Customer() {
		
	}
	
	public Customer(int customerId, TicketPool ticketPool, BasicConfiguration config) {
		this.customerId = customerId;
		this.ticketPool = ticketPool;
		this.config = config;
	}


	@Override
	public void run() {

        while (ticketPool.getTotalTicketsSold() < config.loadConfigarations().getTotalTickets()) {
            ticketPool.removeTicket(customerId, config.loadConfigarations().getCustomerRetrievalRate());
            
            try {
    			Thread.sleep(500);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
        }

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
