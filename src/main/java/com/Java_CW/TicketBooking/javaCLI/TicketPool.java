package com.Java_CW.TicketBooking.javaCLI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketPool {

//    private List<Ticket> tickets;
    
    List<Ticket> tickets = new ArrayList<>();
    List<Ticket> synchronizedList = Collections.synchronizedList(tickets);

	
	public synchronized void addTickets(Ticket tName) {
		synchronizedList.add(tName);
	}
	
	public synchronized void removeTicket(int id) {
		if (tickets.isEmpty()) {
            System.out.println("No tickets available.");
            return;
        }
		System.out.println("ticket "+synchronizedList.get(0).getName()+" bought by customer "+id);
        synchronizedList.remove(0);
	}

	public List<Ticket> getSynchronizedList() {
		return synchronizedList;
	}

	public void setSynchronizedList(List<Ticket> synchronizedList) {
		this.synchronizedList = synchronizedList;
	}
	
}
