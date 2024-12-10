package com.Java_CW.TicketBooking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Java_CW.TicketBooking.javaCLI.BasicConfiguration;
import com.Java_CW.TicketBooking.model.Customer;
import com.Java_CW.TicketBooking.model.TicketPool;
import com.Java_CW.TicketBooking.model.Vendor;

@Service
public class TicketSellService {
	
	public TicketPool ticketPool;
	public BasicConfiguration configuration;
	
	
	Thread t1;
	Thread t2;
	
	Thread t3;
	Thread t4;
	Thread t5;
	Thread t6;
	Thread t7;
	
	public TicketSellService(TicketPool ticketPool, BasicConfiguration configuration) {
		this.ticketPool = ticketPool;
		this.configuration = configuration;
	}
	
	public void runThreads() {
		
		// Reinitialize TicketPool and configuration for fresh state
	    this.ticketPool = new TicketPool(); // Ensure this aligns with your constructor
	    this.configuration = new BasicConfiguration(); // Ensure this aligns with your constructor
		
		t1 = new Thread(new Vendor(1, ticketPool, configuration));
		t2 = new Thread(new Vendor(2, ticketPool, configuration));
		
		t3 = new Thread(new Customer(1, ticketPool, configuration));
		t4 = new Thread(new Customer(2, ticketPool, configuration));
		t5 = new Thread(new Customer(3, ticketPool, configuration));
		t6 = new Thread(new Customer(4, ticketPool, configuration));
		t7 = new Thread(new Customer(5, ticketPool, configuration));

		try {
			t1.start();
			t2.start();
			t3.start();
			t4.start();
			t5.start();
			t6.start();
			t7.start();
			
	        t1.join();
	        t2.join();
	        t3.join();
			t4.join();
			t5.join();
			t6.join();
			t7.join();
			System.out.println("\nSessions Ended\n");
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}
	
	public void stopThreads() {
		if (t1 != null) t1.interrupt();
	    if (t2 != null) t2.interrupt();
	    if (t3 != null) t3.interrupt();
	    if (t4 != null) t4.interrupt();
	    if (t5 != null) t5.interrupt();
	    if (t6 != null) t6.interrupt();
	    if (t7 != null) t7.interrupt();
	    ticketPool.clearConsoleOutputArray();
	    System.out.println("All threads interrupted.");
	}
	
	public BasicConfiguration getConfigData() {
		return configuration.loadConfigarations();
	}
	
	public boolean saveConfigData(BasicConfiguration configData) {
		if(configuration.saveConfigarations(configData)) {
			return true;
		}else{
			return false;
		}
	}
	
	public List<String> addOutputsToArray() {
        return ticketPool.getOutputMsgArray();   
    }
	
}
