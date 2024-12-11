package com.Java_CW.TicketBooking.model;

import org.springframework.stereotype.Component;

import com.Java_CW.TicketBooking.javaCLI.BasicConfiguration;
import com.Java_CW.TicketBooking.service.TicketPool;

@Component
public class Vendor implements Runnable{
	
	private int vendorId;
	private TicketPool TicketPoolObj;
	private BasicConfiguration config;
	private static int numOfReleasedTickets = 0;
	
	public Vendor() {
		
	}

	public Vendor(int venderId, TicketPool TicketPoolObj, BasicConfiguration config) {
		this.vendorId = venderId;
		this.TicketPoolObj = TicketPoolObj;
		this.config = config;
	}

	@Override
	public void run() {
		while (TicketPoolObj.getTotalTicketsReleased() < config.loadConfigarations().getTotalTickets()) {
			TicketPoolObj.addTickets(vendorId, config.loadConfigarations().getTicketReleaseRate()); // Each vendor releases 2 tickets at a time
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }

        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static int getNumOfReleasedTickets() {
		return numOfReleasedTickets;
	}

	public static void setNumOfReleasedTickets(int numOfReleasedTickets) {
		Vendor.numOfReleasedTickets = numOfReleasedTickets;
	}
}
