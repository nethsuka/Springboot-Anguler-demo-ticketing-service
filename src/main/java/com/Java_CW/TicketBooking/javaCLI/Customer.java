package com.Java_CW.TicketBooking.javaCLI;

public class Customer implements Runnable{
	
	private int customerId;
	private TicketPool ticketPool;
	
	public Customer(int customerId, TicketPool ticketPool) {
		super();
		this.customerId = customerId;
		this.ticketPool = ticketPool;
	}


	@Override
	public void run() {
//		for (int i = 0; i < 10; i++) {
//			System.out.println("running customer");
//			try {
//				Thread.sleep(30);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		
		ticketPool.removeTicket(customerId);
	}
}
