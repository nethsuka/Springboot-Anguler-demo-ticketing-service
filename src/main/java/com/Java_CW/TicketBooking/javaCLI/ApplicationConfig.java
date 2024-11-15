package com.Java_CW.TicketBooking.javaCLI;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ApplicationConfig {

	public static final String CYAN = "\033[36m";     // Cyan
    public static final String RESET = "\033[0m";   // Reset color
    public static final String RED = "\033[31m";    // Red color
    public static final String YELLOW_BOLD = "\033[1;38;5;221m";   // Yellow Bold
    public static final String GREEN_BOLD = "\033[1;38;5;46m";    // Green Bold
    public static final String Lightblue = "\033[38;5;153m";


	static Scanner scannerObj = new Scanner(System.in);
	
	static int totalTickets;
	static double ticketReleaseRate;
	static double customerRetrievalRate;
	static int maxTicketCapacity;
	
	static TicketPool ticketPool = new TicketPool();
	static BasicConfiguration configuration = new BasicConfiguration();

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
//				BasicConfiguration test = new BasicConfiguration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
				configuration.setTotalTickets(totalTickets);
				configuration.setTicketReleaseRate(ticketReleaseRate);
				configuration.setCustomerRetrievalRate(customerRetrievalRate);
				configuration.setMaxTicketCapacity(maxTicketCapacity);
				configuration.saveConfigarations(configuration);
				break;
		        
			case "help":
				System.out.println(RESET+"\n\tCommands:");
				System.out.println("\t\t"+GREEN_BOLD+"config"+RESET+"          Make configurations");
				System.out.println("\t\t"+GREEN_BOLD+"save-config"+RESET+"     Save configurations");
				System.out.println("\t\t"+GREEN_BOLD+"help"+RESET+"            Get help");
				System.out.println("\t\t"+GREEN_BOLD+"exit"+RESET+"            Quit");
				
				System.out.println(ticketPool.getSynchronizedList());
//				BasicConfiguration test2 = new BasicConfiguration();
				configuration.loadConfigarations();

				break;
				
			case "rt":

				configuration.loadConfigarations().getTotalTickets();
				Thread t1 = new Thread(new Vendor(1, ticketPool, configuration));
				Thread t2 = new Thread(new Vendor(2, ticketPool, configuration));
				
				Thread t3 = new Thread(new Customer(1, ticketPool, configuration));
				Thread t4 = new Thread(new Customer(2, ticketPool, configuration));
				Thread t5 = new Thread(new Customer(3, ticketPool, configuration));
				Thread t6 = new Thread(new Customer(4, ticketPool, configuration));
				Thread t7 = new Thread(new Customer(5, ticketPool, configuration));

				try {
					t1.start();
					Thread.sleep(10);
					t2.start();
					Thread.sleep(10);
					t3.start();
					Thread.sleep(10);
					t4.start();
					Thread.sleep(10);
					t5.start();
					Thread.sleep(10);
					t6.start();
					Thread.sleep(10);
					t7.start();
					Thread.sleep(10);
					
			        t1.join();
			        t2.join();
			        t3.join();
					t4.join();
					t5.join();
					t6.join();
					t7.join();
			    } catch (InterruptedException e) {
			        e.printStackTrace();
			    }
				break;
				
			case "bt":
				
				int vendorCount = 4;
				int customerCount = 7;
				
				Thread[] threads = new Thread[vendorCount+customerCount];
				
				
				for(int i=0 ; i<vendorCount ; i++) {
					threads[i] = new Thread(new Vendor(i, ticketPool, configuration));
				}
				
				for(int i=0 ; i<customerCount ; i++) {
					threads[vendorCount+i] = new Thread(new Customer(i, ticketPool, configuration));
				}
				
				try {
					for (Thread thread : threads) {
						thread.start();
//						Thread.sleep(100);
					}
					for(Thread thread : threads) {
						thread.join();
					}
			    } catch (InterruptedException e) {
			        e.printStackTrace();
			    }
				break;
				
			case "exit":
				status = false;
				break;
				
			default:
				System.out.println(RED+"Invalid command!\n"+RESET);
//				throw new IllegalArgumentException("Unexpected value: " + optionNum);
			}
		}
        
		
		
	}	
	
	
	public static int validateTotalTickets() {
//		test.setTotalTickets(scannerObj.nextInt());
		int validTotalTickets;
		while (true) {
			System.out.print(RESET+"Enter the total tickets available in the system : "+YELLOW_BOLD);
            try {
            	validTotalTickets = scannerObj.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println(RED+"Invalid input. Please enter a valid integer.\n"+RESET);
                scannerObj.next(); // Clear the invalid input
            }
        }
        return validTotalTickets;
	}
		
	
	public static double validateTicketReleaseRate() {
//		test.setTicketReleaseRate(scannerObj.nextDouble());
		double validTicketReleaseRate;
		while (true) {
			System.out.print(RESET+"Enter vendors ticket release rate : "+YELLOW_BOLD);
			try {
				validTicketReleaseRate = scannerObj.nextDouble();
				break;
			} catch(InputMismatchException e) {
				System.out.println(RED+"Invalid input. Please enter a valid input.\n"+RESET);
                scannerObj.next(); // Clear the invalid input
			}
		}
		return validTicketReleaseRate;
	}
	
	public static double validateCustomerRetrievalRate() {
//		test.setCustomerRetrievalRate(scannerObj.nextDouble());
		
		double validCustomerRetrievalRate;
		while (true) {
			System.out.print(RESET+"Enter customer retrieval rate : "+YELLOW_BOLD);
			try {
				validCustomerRetrievalRate = scannerObj.nextDouble();
				break;
			} catch(InputMismatchException e) {
				System.out.println(RED+"Invalid input. Please enter a valid input.\n"+RESET);
				scannerObj.next();
			}
		}
		return validCustomerRetrievalRate;
	}
	
	public static int validateMaxTicketCapacity() {
//		test.setMaxTicketCapacity(scannerObj.nextInt());
		
		int validMaxTicketCapacity;
		while (true) {
			System.out.print(RESET+"Enter the maximum ticket capacity : "+YELLOW_BOLD);
			try {
				validMaxTicketCapacity = scannerObj.nextInt();
				break;
			} catch(InputMismatchException e) {
				System.out.println(RED+"Invalid input. Please enter a valid input.\n"+RESET);
				scannerObj.next();
			}
		}
		return validMaxTicketCapacity;
	}
	
}
