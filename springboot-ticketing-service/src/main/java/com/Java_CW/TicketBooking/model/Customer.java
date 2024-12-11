package com.Java_CW.TicketBooking.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.Java_CW.TicketBooking.javaCLI.ApplicationConfig;
import com.Java_CW.TicketBooking.javaCLI.BasicConfiguration;
import com.Java_CW.TicketBooking.service.TicketPool;

@Component
public class Customer implements Runnable{
	
	private int customerId;
	private BasicConfiguration config;
	private TicketPool ticketPool;
	
	//logger instance
	private static final Logger logger = LogManager.getLogger(ApplicationConfig.class);
	
	/**
	 * No argument constructor
	 */
	public Customer() {
		
	}
	
	
	/**
	 * Three arguments constructor
	 * @param customerId
	 * @param ticketPool
	 * @param config
	 */
	public Customer(int customerId, TicketPool ticketPool, BasicConfiguration config) {
		this.customerId = customerId;
		this.ticketPool = ticketPool;
		this.config = config;
	}


	/**
	 * this functions allows customers to buy tickets from the ticket pool with time intervals.
	 */
	@Override
	public void run() {

        while (ticketPool.getTotalTicketsSold() < config.loadConfigarations().getTotalTickets()) {
            ticketPool.removeTicket(customerId, config.loadConfigarations().getCustomerRetrievalRate());
            
            try {
    			Thread.sleep(500);
    		} catch (InterruptedException e) {
				logger.error("An error occured while trying to sleep the customer thread");
    		}
        }

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			logger.error("An error occured while trying to sleep the customer thread");
		}
	}
	
}
