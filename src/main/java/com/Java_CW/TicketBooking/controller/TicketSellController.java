package com.Java_CW.TicketBooking.controller;

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
	
	@GetMapping("/config-data")
	public BasicConfiguration index() {
		return ticketSellService.getConfigData();
	}
	
	@PostMapping("/config-data")
	public boolean saveConfigData(@RequestBody BasicConfiguration configData) {
        return ticketSellService.saveConfigData(configData);
    }
	
	@GetMapping("/start")
	public void startRunning() {
		ticketSellService.runThreads();
	}
	
	@GetMapping("/stop")
	public void stopRunnning() {
		ticketSellService.stopThreads();
	}
}
