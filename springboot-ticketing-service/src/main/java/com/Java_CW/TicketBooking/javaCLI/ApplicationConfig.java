package com.Java_CW.TicketBooking.javaCLI;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Java_CW.TicketBooking.model.Customer;
import com.Java_CW.TicketBooking.model.Vendor;
import com.Java_CW.TicketBooking.service.TicketPool;

public class ApplicationConfig {

	//color codes
	public static final String CYAN = "\033[36m";     // Cyan
    public static final String RESET = "\033[0m";   // Reset color
    public static final String RED = "\033[31m";    // Red color
    public static final String YELLOW_BOLD = "\033[1;38;5;221m";   // Yellow Bold
    public static final String GREEN_BOLD = "\033[1;38;5;46m";    // Green Bold
    public static final String Lightblue = "\033[38;5;123m";
	
    //configuration parameters
	static int totalTickets;
	static int ticketReleaseRate;
	static int customerRetrievalRate;
	static int maxTicketCapacity;
	
	// logger instance
	private static Logger logger = LogManager.getLogger(ApplicationConfig.class);

	static Scanner scannerObj = new Scanner(System.in);
	
	static TicketPool ticketPool = new TicketPool();
	static BasicConfiguration configuration = new BasicConfiguration();

	private static String filePath = "src/main/resources/config.json";
    private static File configFile = new File(filePath);

	public static void main(String[] args) {
		
		System.out.println(CYAN+"""
	              _____ ___ _  __ ___ 
	             |_   _|_ _| |/ /|_ _|
	               | |  | || ' /  | | 
	               | |  | || . \\ _| |
	               |_| |___|_|\\_\\____|  
               
               ! Welcome to the TIKI ticket booking application !
               """+RESET);
				
		boolean status = true;
		String command;
		while(status) {
			
			System.out.print(RESET+"\nEnter command>> "+GREEN_BOLD);
			command = scannerObj.next().toLowerCase().trim();
			
			switch (command) {
				
			case "config":
				System.out.println();
				totalTickets = validateTotalTickets();
		        ticketReleaseRate = validateTicketReleaseRate();
		        customerRetrievalRate = validateCustomerRetrievalRate();
		        maxTicketCapacity = validateMaxTicketCapacity();
		        break;
		        
			case "save-config":
				if (totalTickets == 0 && ticketReleaseRate == 0 && customerRetrievalRate == 0 && maxTicketCapacity == 0) {
					System.out.print(YELLOW_BOLD);
					logger.warn("Please do the configuration."+RESET);
				}else {
					configuration.setTotalTickets(totalTickets);
					configuration.setTicketReleaseRate(ticketReleaseRate);
					configuration.setCustomerRetrievalRate(customerRetrievalRate);
					configuration.setMaxTicketCapacity(maxTicketCapacity);
					
					configuration.saveConfigarations(configuration);
				}
				break;
		        
			case "help":
				System.out.println(RESET+"\n\tCommands:");
				System.out.println("\t\t"+GREEN_BOLD+"start"+RESET+"           start the simulaion");
				System.out.println("\t\t"+GREEN_BOLD+"config"+RESET+"          Make configurations");
				System.out.println("\t\t"+GREEN_BOLD+"save-config"+RESET+"     Save configurations");
				System.out.println("\t\t"+GREEN_BOLD+"show-config"+RESET+"     Show configurations");
				System.out.println("\t\t"+GREEN_BOLD+"help"+RESET+"            Get help");
				System.out.println("\t\t"+GREEN_BOLD+"exit"+RESET+"            Quit Program");
				break;
				
			case "start":

			    if (!configFile.exists()) {
			    	System.out.print(YELLOW_BOLD);
			        logger.warn("Configuration file does not exist, Please make a one."+RESET);
			        break;
			    }
			    
			    System.out.print(Lightblue);
			    
				ticketPool.setTotalTicketsReleased(0);
				ticketPool.setTotalTicketsSold(0);
				
				Thread t1 = new Thread(new Vendor(1, ticketPool, configuration));
				Thread t2 = new Thread(new Vendor(2, ticketPool, configuration));
				
				Thread t3 = new Thread(new Customer(1, ticketPool, configuration));
				Thread t4 = new Thread(new Customer(2, ticketPool, configuration));
				Thread t5 = new Thread(new Customer(3, ticketPool, configuration));
				Thread t6 = new Thread(new Customer(4, ticketPool, configuration));
				Thread t7 = new Thread(new Customer(5, ticketPool, configuration));

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
			    } catch (InterruptedException e) {
			    	System.out.print(RED);
			        logger.error("Error occurred while running threads"+RESET);
			    }
				System.out.print(RESET);
				break;
				
			case "show-config":
				System.out.println();
				System.out.println(RESET+"Maximaum Ticket capacity in ticket pool - "+Lightblue+configuration.getMaxTicketCapacity()+RESET);
				System.out.println(RESET+"Total number of tickets available in ticket pool - "+Lightblue+configuration.getTotalTickets()+RESET);
				System.out.println(RESET+"Vendors' ticket release rate - "+Lightblue+configuration.getTicketReleaseRate()+RESET);
				System.out.println(RESET+"Customers' ticket buying rate - "+Lightblue+configuration.getCustomerRetrievalRate()+RESET); 
				break;
				
			case "exit":
				status = false;
				System.out.print(Lightblue);
				logger.info("Program Ended\n"+RESET);
				break;
				
			default:
				System.out.print(RED);
				logger.error("Invalid command!\n"+RESET);
			}
		}
        
		
		
	}	
	
	
	/**
	 * This function validate the total ticket count from only getting positive values.
	 * @return
	 */
	public static int validateTotalTickets() {
		
		int validTotalTickets;		
		while (true) {
			System.out.print(RESET+"Enter the total tickets available in the system : "+YELLOW_BOLD);
            try {
            	validTotalTickets = scannerObj.nextInt();
                break;
            } catch (InputMismatchException e) {
            	System.out.print(RED);
                logger.error("Invalid input. Please enter a valid integer.\n"+RESET);
                scannerObj.next(); // Clear the invalid input
            }
        }
        return validTotalTickets;
	}
		
	
	/**
	 * This function validate the ticket release rate from only getting positive values.
	 * @return
	 */
	public static int validateTicketReleaseRate() {
		
		int validTicketReleaseRate;
		while (true) {
			System.out.print(RESET+"Enter vendors ticket release rate : "+YELLOW_BOLD);
			try {
				validTicketReleaseRate = scannerObj.nextInt();
				break;
			} catch(InputMismatchException e) {
				System.out.print(RED);
                logger.error(RED+"Invalid input. Please enter a valid input.\n"+RESET);
                scannerObj.next(); // Clear the invalid input
			}
		}
		return validTicketReleaseRate;
	}
	
	
	/**
	 * This function validate the customer retrieval rate from only getting positive values.
	 * @return
	 */
	public static int validateCustomerRetrievalRate() {
		
		int validCustomerRetrievalRate;
		while (true) {
			System.out.print(RESET+"Enter customer retrieval rate : "+YELLOW_BOLD);
			try {
				validCustomerRetrievalRate = scannerObj.nextInt();
				break;
			} catch(InputMismatchException e) {
				System.out.print(RED);
                logger.error("Invalid input. Please enter a valid input.\n"+RESET);
				scannerObj.next();
			}
		}
		return validCustomerRetrievalRate;
	}
	
	
	/**
	 * This function validate the max ticket capacity from getting positive values and it checks 
	 * whether the max ticket capacity is larger than the total ticket count.
	 * @return
	 */
	public static int validateMaxTicketCapacity() {
		
		int validMaxTicketCapacity;
		while (true) {
			System.out.print(RESET+"Enter the maximum ticket capacity : "+YELLOW_BOLD);
			try {
				validMaxTicketCapacity = scannerObj.nextInt();
				if(validMaxTicketCapacity < totalTickets) {
					System.out.print(RED);
					logger.error("Max ticket capacity should be greater than total ticket count."+RESET);
					continue;
				}
				break;
			} catch(InputMismatchException e) {
				System.out.print(RED);
                logger.error("Invalid input. Please enter a valid input.\n"+RESET);
				scannerObj.next();
			}
		}
		return validMaxTicketCapacity;
	}
	
}
