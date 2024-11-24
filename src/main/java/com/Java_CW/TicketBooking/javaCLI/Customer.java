package com.Java_CW.TicketBooking.javaCLI;


public class Customer implements Runnable{
	
	private int customerId;
	private BasicConfiguration config;
	private TicketPool ticketPool;
	
	public Customer(int customerId, TicketPool ticketPool, BasicConfiguration config) {
		super();
		this.customerId = customerId;
		this.ticketPool = ticketPool;
		this.config = config;
	}


	@Override
	public void run() {

        while (ticketPool.getTotalTicketsSold() < config.loadConfigarations().getTotalTickets()) {
            ticketPool.removeTicket(customerId);
        }
	}
	
}
