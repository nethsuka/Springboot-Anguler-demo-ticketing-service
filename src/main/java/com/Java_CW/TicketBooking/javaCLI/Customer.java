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
		for (int i = 0; i < config.loadConfigarations().getCustomerRetrievalRate(); i++) {
			ticketPool.removeTicket(customerId);
//			try {
//				Thread.sleep(30);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
		
//		try {
//			Thread.sleep(100);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		
//		ticketPool.removeTicket(customerId);
	}
}
