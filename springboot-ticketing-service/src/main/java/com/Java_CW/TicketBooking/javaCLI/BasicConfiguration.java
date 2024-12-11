package com.Java_CW.TicketBooking.javaCLI;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class BasicConfiguration {

	//color codes
    public static final String Lightblue = "\033[48;5;155m"; // Light blue color
    public static final String RESET = "\033[0m";   // Reset color
    public static final String Black = "\033[30m"; // Black color
    public static final String RED = "\033[31m";    // Red color

    //logger instance 
	private static Logger logger = LogManager.getLogger(ApplicationConfig.class);
    
	static Gson gson = new Gson();
    
	private int totalTickets;
	private int ticketReleaseRate;
	private int customerRetrievalRate;
	private int maxTicketCapacity;
	
	
	/**
	 * no argument constructor
	 */
	public BasicConfiguration() {
		
	}

	/**
	 * four arguments constructor with configuration parameters
	 * @param totalTickets
	 * @param ticketReleaseRate
	 * @param customerRetrievalRate
	 * @param maxTicketCapacity
	 */
	public BasicConfiguration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate,
			int maxTicketCapacity) {
		this.totalTickets = totalTickets;
		this.ticketReleaseRate = ticketReleaseRate;
		this.customerRetrievalRate = customerRetrievalRate;
		this.maxTicketCapacity = maxTicketCapacity;
		
	}

	//getters and setters for the four parameters
	public int getTotalTickets() {
		return totalTickets;
	}
	
	public void setTotalTickets(int totalTickets) {
		this.totalTickets = totalTickets;
	}
	
	public int getTicketReleaseRate() {
		return ticketReleaseRate;
	}
	
	public void setTicketReleaseRate(int ticketReleaseRate) {
		this.ticketReleaseRate = ticketReleaseRate;
	}
	
	public int getCustomerRetrievalRate() {
		return customerRetrievalRate;
	}
	
	public void setCustomerRetrievalRate(int customerRetrievalRate) {
		this.customerRetrievalRate = customerRetrievalRate;
	}
	
	public int getMaxTicketCapacity() {
		return maxTicketCapacity;
	}
	
	public void setMaxTicketCapacity(int maxTicketCapacity) {
		this.maxTicketCapacity = maxTicketCapacity;
	}
	
	
	/**
	 * Save the four parameters to the config.jason file and returns true 
	 * if its success otherwise false
	 * @param configObj
	 * @return
	 */
	public boolean saveConfigarations(BasicConfiguration configObj) {
	    
		String gsonObj = gson.toJson(configObj);
		
		try (FileWriter config = new FileWriter("src/main/resources/config.json")) {  // this method automatically close the file
			config.write(gsonObj);
            System.out.println(Black+Lightblue+"DATA SAVED SUCCESSFULLY"+RESET);
            return true;
            
        } catch (IOException e) {
            logger.error(RED+"An error occurred while saving the configuration"+RESET);
            return false;
        }
	}
	
	
	/**
	 * Read the data from the saved config.json file and return them as an object
	 * @return
	 */
	public BasicConfiguration loadConfigarations() {
		
		try (FileReader reader = new FileReader("src/main/resources/config.json")) { // this method automatically close the file
            BasicConfiguration config = gson.fromJson(reader, BasicConfiguration.class);
            
            if (config == null) {
            	logger.error(RED+"Failed to parse the configuration file. Invalid JSON."+RESET);
                return null;
            }
            return config;
            
        } catch (IOException e) {     
        	logger.error(RED+"An error occurred while reading the configuration file"+RESET);
            return null;
        }

	}
}