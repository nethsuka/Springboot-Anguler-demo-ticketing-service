package com.Java_CW.TicketBooking.javaCLI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Vendor implements Runnable{
	
	private int venderId;
	private TicketPool TicketPoolObj;
	private BasicConfiguration config;
	private static int numOfReleasedTickets = 0;
	
    private static final Logger logger = LogManager.getLogger(Vendor.class); // logger instance


	public Vendor(int venderId, TicketPool TicketPoolObj, BasicConfiguration config) {
		super();
		this.venderId = venderId;
		this.TicketPoolObj = TicketPoolObj;
		this.config = config;
	}

	@Override
	public void run() {
		
//		BasicConfiguration f1 = new BasicConfiguration();
		
//		for (;;) {
//			if(TicketPool.getNumOfReleasedTickets() <= config.loadConfigarations().getTotalTickets()) {
//				for (int i = 0; i < config.loadConfigarations().getTicketReleaseRate(); i++) {
//					if(TicketPoolObj.getSynchronizedList().size() <= config.loadConfigarations().getMaxTicketCapacity() 
//							&& numOfReleasedTickets <= config.loadConfigarations().getTotalTickets()) {
//						Ticket t1 = new Ticket("v"+venderId+"t"+i);
//						TicketPoolObj.addTickets(t1);
//						numOfReleasedTickets++;
//						System.out.println("vendor "+venderId+" added "+"v"+venderId+"t"+i);
//					}
//				}
//			}
//		}
		
		while (TicketPoolObj.getSynchronizedList().size() < config.loadConfigarations().getTotalTickets()) {
            // Add 5 Tickets at a time
            for (int i = 0; i < config.loadConfigarations().getTicketReleaseRate() ; i++) {
            	if(TicketPoolObj.getSynchronizedList().size() < config.loadConfigarations().getTotalTickets()) {
	            	Ticket t1 = new Ticket("v"+venderId+"t"+i);
					TicketPoolObj.addTickets(t1);
					setNumOfReleasedTickets(getNumOfReleasedTickets() + 1);
//					System.out.println("vendor "+venderId+" added "+"v"+venderId+"t"+i);
					logger.info("vendor "+venderId+" added "+"v"+venderId+"t"+i);
            	}else {
            		break;
            	}
            }
        }
		
		try {
			Thread.sleep(100);
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
