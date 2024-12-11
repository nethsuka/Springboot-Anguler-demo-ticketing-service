package com.Java_CW.TicketBooking.service;

public interface TicketPoolInterface {

	/**
	 * Add tickets objects
	 * @param vendorId
	 * @param ticketReleaseRate
	 */
	void addTickets(int vendorId, int ticketReleaseRate);
	
	/**
	 * Remove tickets objects
	 * @param customerId
	 * @param customerRetrievalRate
	 */
	void removeTicket(int customerId, int customerRetrievalRate);
}