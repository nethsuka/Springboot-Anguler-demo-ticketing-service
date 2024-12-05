package com.Java_CW.TicketBooking.model;

import org.springframework.stereotype.Component;

@Component
public class Ticket {

	private String name;
	private int tickitId;

	public Ticket() {
		
	}
	
	public Ticket(String name) {
		this.name = name;
	}
	
	public Ticket(int tickitId) {
		this.tickitId = tickitId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTickitId() {
		return tickitId;
	}

	public void setTickitId(int tickitId) {
		this.tickitId = tickitId;
	}
	
}
