package com.Java_CW.TicketBooking.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.Java_CW.TicketBooking.javaCLI.BasicConfiguration;

@Component
public class TicketPool {
	
    
    ArrayList<Ticket> tickets = new ArrayList<>();
    List<Ticket> synchronizedList = Collections.synchronizedList(tickets);
    
    private static int ticketNo = 0;
    private int totalTicketsReleased = 0;
    private int totalTicketsSold = 0;
    
    private static final Logger logger = LogManager.getLogger(TicketPool.class); // logger instance


    BasicConfiguration config = new BasicConfiguration();
    
    public synchronized void addTickets(int vendorId, double ticketReleaseRate) {
        for (int i = 0; i < ticketReleaseRate; i++) {
            if (totalTicketsReleased >= config.loadConfigarations().getTotalTickets()) {
                logger.info("Vendor " + vendorId + " cannot add more tickets. Tickets are finished.");
                break;
            }
            ticketNo += 1;
            Ticket ticket = new Ticket(ticketNo);
            synchronizedList.add(ticket);
            totalTicketsReleased++;
            logger.info("Vendor " + vendorId + " added ticket " + ticketNo);
        }
    }

	
	public synchronized void removeTicket(int customerId, double customerRetrievalRate) {
		for(int i = 0; i < customerRetrievalRate; i++) {
			if (synchronizedList.isEmpty()) {
	            logger.info("Customer "+customerId+" tried but No tickets available.");
	            return;
	        }
			logger.info("ticket "+synchronizedList.get(0).getTickitId()+" bought by customer "+customerId);
	        synchronizedList.remove(0);
	        totalTicketsSold += 1;
		}
	}

	public int getTotalTicketsSold() {
		return totalTicketsSold;
	}


	public void setTotalTicketsSold(int totalTicketsSold) {
		this.totalTicketsSold = totalTicketsSold;
	}


	public List<Ticket> getSynchronizedList() {
		return synchronizedList;
	}

	public void setSynchronizedList(List<Ticket> synchronizedList) {
		this.synchronizedList = synchronizedList;
	}

	public int getTotalTicketsReleased() {
		return totalTicketsReleased;
	}
	
	public void setTotalTicketsReleased(int totalTicketsReleased) {
		this.totalTicketsReleased = totalTicketsReleased;
	}
	
}
