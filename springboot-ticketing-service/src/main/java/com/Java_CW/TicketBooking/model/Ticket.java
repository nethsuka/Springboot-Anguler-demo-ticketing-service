package com.Java_CW.TicketBooking.model;

import org.springframework.stereotype.Component;

@Component
public class Ticket {

	private int tickitId;

	/**
	 * No arguments constructor
	 */
	public Ticket() {
		
	}
	
	public Ticket(int tickitId) {
		this.tickitId = tickitId;
	}

	
	// Getters and Setters
	
	public int getTickitId() {
		return tickitId;
	}

	public void setTickitId(int tickitId) {
		this.tickitId = tickitId;
	}
	
}
