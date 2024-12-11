package com.Java_CW.TicketBooking.service;

public interface TicketPoolInterface {

	void addTickets(int vendorId, int ticketReleaseRate);
	
	void removeTicket(int customerId, double customerRetrievalRate);
}
