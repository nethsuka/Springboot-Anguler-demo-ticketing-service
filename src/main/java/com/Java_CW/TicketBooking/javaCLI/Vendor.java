package com.Java_CW.TicketBooking.javaCLI;

public class Vendor implements Runnable{
	
	private int venderId;
	private TicketPool TicketPoolObj;

	public Vendor(int venderId, TicketPool TicketPoolObj) {
		super();
		this.venderId = venderId;
		this.TicketPoolObj = TicketPoolObj;
	}

	@Override
	public void run() {
//		for (int i = 0; i < 10; i++) {
//			System.out.println("running vender");
//			try {
//				Thread.sleep(20);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		
//		Ticket t1 = new Ticket("vt1");
//		Ticket t2 = new Ticket("vt1");
//		Ticket t3 = new Ticket("vt1");
//
//		
//		TicketPoolObj.addTickets(t1);
//		TicketPoolObj.addTickets(t2);
//		TicketPoolObj.addTickets(t3);
		
		for (int i = 0; i < 3 ; i++) {
			Ticket t1 = new Ticket("v"+venderId+"t"+i);
			TicketPoolObj.addTickets(t1);
			System.out.println("vendor "+venderId+" added "+"v"+venderId+"t"+i);
		}
	}
}
