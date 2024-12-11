package com.Java_CW.TicketBooking.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Java_CW.TicketBooking.javaCLI.BasicConfiguration;
import com.Java_CW.TicketBooking.service.TicketSellService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class TicketSellController {
	
	private TicketSellService ticketSellService;
	
	public TicketSellController(TicketSellService ticketSellService) {
		this.ticketSellService = ticketSellService;
	}
	
	/**
	 * This function returns the configuration data
	 * @return
	 */
	@GetMapping("/config-data")
	public BasicConfiguration index() {
		return ticketSellService.getConfigData();
	}
	
	
	/**
	 * This function gets the data which comes with the request and save to the file and 
	 * it returns true if it works otherwise false.
	 * @param configData
	 * @return
	 */
	@PostMapping("/config-data")
	public boolean saveConfigData(@RequestBody BasicConfiguration configData) {
        return ticketSellService.saveConfigData(configData);
    }
	
	
	/**
	 * This function starts running threads.
	 */
	@GetMapping("/start")
	public void startRunning() {
		ticketSellService.runThreads();
	}
	
	
	/**
	 * This function stops the program while running.
	 */
	@GetMapping("/stop")
	public void stopRunnning() {
		ticketSellService.stopThreads();
	}
	
	
	/**
	 * This function returns the array of massages
	 * @return
	 */
	@GetMapping("/outputs")
    public List<String> getMessages() {
        return ticketSellService.addOutputsToArray();
    }
	
	
	/**
	 * This function returns the sold ticket count
	 * @return
	 */
	@GetMapping("/sould-tickets")
	public int getTickets() {
		return ticketSellService.getSouldTickets();
	}
}
