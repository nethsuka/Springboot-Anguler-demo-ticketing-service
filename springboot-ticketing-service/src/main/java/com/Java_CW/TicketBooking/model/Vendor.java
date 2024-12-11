package com.Java_CW.TicketBooking.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.Java_CW.TicketBooking.javaCLI.ApplicationConfig;
import com.Java_CW.TicketBooking.javaCLI.BasicConfiguration;
import com.Java_CW.TicketBooking.service.TicketPool;

@Component
public class Vendor implements Runnable{
	
	private int vendorId;
	private TicketPool TicketPoolObj;
	private BasicConfiguration config;
	
	//logger instance
	private static final Logger logger = LogManager.getLogger(ApplicationConfig.class);
	
	/**
	 * No argument constructor
	 */
	public Vendor() {
		
	}

	public Vendor(int venderId, TicketPool TicketPoolObj, BasicConfiguration config) {
		this.vendorId = venderId;
		this.TicketPoolObj = TicketPoolObj;
		this.config = config;
	}

	
	/**
	 * This function allows the vendors to add tickets to the ticket pool
	 */
	@Override
	public void run() {
		while (TicketPoolObj.getTotalTicketsReleased() < config.loadConfigarations().getTotalTickets()) {
			TicketPoolObj.addTickets(vendorId, config.loadConfigarations().getTicketReleaseRate()); // Each vendor releases 2 tickets at a time
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				logger.error("An error occured while trying to sleep the vendor thread");
			}
        }

        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			logger.error("An error occured while trying to sleep the vendor thread");
		}
	}

}
