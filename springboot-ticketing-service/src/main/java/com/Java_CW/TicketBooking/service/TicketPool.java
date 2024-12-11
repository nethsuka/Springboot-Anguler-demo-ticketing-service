package com.Java_CW.TicketBooking.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.Java_CW.TicketBooking.javaCLI.BasicConfiguration;
import com.Java_CW.TicketBooking.model.Ticket;

@Service
public class TicketPool implements TicketPoolInterface{
	
	/**
	 * synchronized array list
	 */
    private ArrayList<Ticket> tickets = new ArrayList<>();
    private List<Ticket> synchronizedList = Collections.synchronizedList(tickets);
    
    // array that collects data to sent to the front-end
    private List<String> consoleOutputs = Collections.synchronizedList(new ArrayList<>());
    
    private static int ticketNo = 0;
    private int totalTicketsReleased = 0;
    private int totalTicketsSold = 0;
    
    //logger instance
    private static Logger logger = LogManager.getLogger(TicketPool.class); // logger instance


    BasicConfiguration config = new BasicConfiguration();
    
    /**
     * This synchronized function add tickets to the tickets array until
     * total released tickets equals to total number of tickets
     */
    @Override
    public synchronized void addTickets(int vendorId, int ticketReleaseRate) {        
        for (int i = 0; i < ticketReleaseRate; i++) {
            if (totalTicketsReleased >= config.loadConfigarations().getTotalTickets()) {
                String msg = "Vendor " + vendorId + " cannot add more tickets. Tickets are finished.";
                logger.info(msg);
                consoleOutputs.add(msg);
                break;
            }

            ticketNo += 1;
            Ticket ticket = new Ticket(ticketNo);
            synchronizedList.add(ticket);
            totalTicketsReleased++;

            String msg = "Vendor " + vendorId + " added ticket " + ticketNo;
            logger.info(msg);
            consoleOutputs.add(msg); 
        }
    }

    
    /**
     * This synchronized function remover tickets from the tickets array
     */
	@Override
	public synchronized void removeTicket(int customerId, int customerRetrievalRate) {
		String msg;
		for(int i = 0; i < customerRetrievalRate; i++) {
			if (synchronizedList.isEmpty()) {
				msg = "Customer "+customerId+" tried but No tickets available.";
	            logger.info(msg);
	            consoleOutputs.add(msg);
	            return;
	        }
			msg = "ticket "+synchronizedList.get(0).getTickitId()+" bought by customer "+customerId;
			logger.info(msg);
			consoleOutputs.add(msg);
	        synchronizedList.remove(0);
	        totalTicketsSold ++;
		}
	}
	
	
	// Getters and setters
	
	public List<String> getOutputMsgArray() {
		return consoleOutputs;
	}
	
	public void clearConsoleOutputArray() {
		consoleOutputs.clear();
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
