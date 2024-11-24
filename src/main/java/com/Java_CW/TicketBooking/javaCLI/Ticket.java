package com.Java_CW.TicketBooking.javaCLI;

public class Ticket {

	private String name;
	private int tickitId;

	public Ticket(String name) {
		super();
		this.name = name;
	}
	
	public Ticket(int tickitId) {
		super();
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
