package com.Java_CW.TicketBooking.javaCLI;

public class Vendor implements Runnable{
	
	private int venderId;
	private TicketPool TicketPoolObj;
	private BasicConfiguration config;
	private static int numOfReleasedTickets = 0;

	public Vendor(int venderId, TicketPool TicketPoolObj, BasicConfiguration config) {
		super();
		this.venderId = venderId;
		this.TicketPoolObj = TicketPoolObj;
		this.config = config;
	}

	@Override
	public void run() {
		
//		BasicConfiguration f1 = new BasicConfiguration();
		
		for (int i = 0; i < config.loadConfigarations().getTicketReleaseRate(); i++) {
			if(TicketPoolObj.getSynchronizedList().size() <= config.loadConfigarations().getMaxTicketCapacity() 
					&& numOfReleasedTickets <= config.loadConfigarations().getTotalTickets()) {
				Ticket t1 = new Ticket("v"+venderId+"t"+i);
				TicketPoolObj.addTickets(t1);
				numOfReleasedTickets++;
				System.out.println("vendor "+venderId+" added "+"v"+venderId+"t"+i);
			}
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
