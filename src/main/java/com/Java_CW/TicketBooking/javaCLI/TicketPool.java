package com.Java_CW.TicketBooking.javaCLI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TicketPool {
	
//	private static int numOfReleasedTickets;
    
    List<Ticket> tickets = new ArrayList<>();
    List<Ticket> synchronizedList = Collections.synchronizedList(tickets);
    
    private static final Logger logger = LogManager.getLogger(TicketPool.class); // logger instance


//    BasicConfiguration config = new BasicConfiguration();
	
	public synchronized void addTickets(Ticket tName) {
//		if(synchronizedList.size() <= config.loadConfigarations().getMaxTicketCapacity())
		synchronizedList.add(tName);
//		numOfReleasedTickets++;
	}
	
	public synchronized void removeTicket(int id) {
		if (synchronizedList.isEmpty()) {
//            System.out.println("Customer "+id+" tried but No tickets available.");
            logger.info("Customer "+id+" tried but No tickets available.");
            return;
        }
//		System.out.println("ticket "+synchronizedList.get(0).getName()+" bought by customer "+id);
		logger.info("ticket "+synchronizedList.get(0).getName()+" bought by customer "+id);
        synchronizedList.remove(0);
	}

	public List<Ticket> getSynchronizedList() {
		return synchronizedList;
	}

	public void setSynchronizedList(List<Ticket> synchronizedList) {
		this.synchronizedList = synchronizedList;
	}

//	public static int getNumOfReleasedTickets() {
//		return numOfReleasedTickets;
//	}
//
//	public static void setNumOfReleasedTickets(int numOfReleasedTickets) {
//		TicketPool.numOfReleasedTickets = numOfReleasedTickets;
//	}
	
}
