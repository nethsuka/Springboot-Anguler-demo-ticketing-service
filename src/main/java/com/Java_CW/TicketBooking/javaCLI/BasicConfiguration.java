package com.Java_CW.TicketBooking.javaCLI;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

public class BasicConfiguration {

    public static final String Lightblue = "\033[48;5;155m"; // Light blue color
    public static final String RESET = "\033[0m";   // Reset color
    public static final String Black = "\033[30m"; // Black color

	static Gson gson = new Gson();
    
	private int totalTickets;
	private double ticketReleaseRate;
	private double customerRetrievalRate;
	private int maxTicketCapacity;
	
	public BasicConfiguration() {
		super();
	}

	public BasicConfiguration(int totalTickets, double ticketReleaseRate, double customerRetrievalRate,
			int maxTicketCapacity) {
		this.totalTickets = totalTickets;
		this.ticketReleaseRate = ticketReleaseRate;
		this.customerRetrievalRate = customerRetrievalRate;
		this.maxTicketCapacity = maxTicketCapacity;
		
	}

	public int getTotalTickets() {
		return totalTickets;
	}
	
	public void setTotalTickets(int totalTickets) {
		this.totalTickets = totalTickets;
	}
	
	public double getTicketReleaseRate() {
		return ticketReleaseRate;
	}
	
	public void setTicketReleaseRate(double ticketReleaseRate) {
		this.ticketReleaseRate = ticketReleaseRate;
	}
	
	public double getCustomerRetrievalRate() {
		return customerRetrievalRate;
	}
	
	public void setCustomerRetrievalRate(double customerRetrievalRate) {
		this.customerRetrievalRate = customerRetrievalRate;
	}
	
	public int getMaxTicketCapacity() {
		return maxTicketCapacity;
	}
	
	public void setMaxTicketCapacity(int maxTicketCapacity) {
		this.maxTicketCapacity = maxTicketCapacity;
	}
	
	public void saveConfigarations(BasicConfiguration configObj) {
		String gsonObj = gson.toJson(configObj);
		try (FileWriter config = new FileWriter("src/main/resources/config.json")) {  // this method automatically close the file
			config.write(gsonObj);
            System.out.println(Black+Lightblue+"DATA SAVED SUCCESSFULLY"+RESET);
        } catch (IOException e) {
            System.out.println("Some error occured");
        }
	}
	
	public void loadConfigarations() {
		try (FileReader reader = new FileReader("src/main/resources/config.json")) { // this method automatically close the file
            BasicConfiguration config = gson.fromJson(reader, BasicConfiguration.class);
            System.out.println(config.totalTickets);
        } catch (IOException e) {       // handle the error if file was there ??
            e.printStackTrace();
        }

	}
}
