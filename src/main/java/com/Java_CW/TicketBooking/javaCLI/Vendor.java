package com.Java_CW.TicketBooking.javaCLI;


public class Vendor implements Runnable{
	
	private int vendorId;
	private TicketPool TicketPoolObj;
	private BasicConfiguration config;
	private static int numOfReleasedTickets = 0;

	public Vendor(int venderId, TicketPool TicketPoolObj, BasicConfiguration config) {
		super();
		this.vendorId = venderId;
		this.TicketPoolObj = TicketPoolObj;
		this.config = config;
	}

	@Override
	public void run() {
		while (TicketPoolObj.getTotalTicketsReleased() < config.loadConfigarations().getTotalTickets()) {
			TicketPoolObj.addTickets(vendorId, config.loadConfigarations().getTicketReleaseRate()); // Each vendor releases 2 tickets at a time
        }
		
	}

	public static int getNumOfReleasedTickets() {
		return numOfReleasedTickets;
	}

	public static void setNumOfReleasedTickets(int numOfReleasedTickets) {
		Vendor.numOfReleasedTickets = numOfReleasedTickets;
	}
}
