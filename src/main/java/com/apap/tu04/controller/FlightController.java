package com.apap.tu04.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tu04.model.FlightModel;
import com.apap.tu04.model.PilotModel;
import com.apap.tu04.service.FlightService;
import com.apap.tu04.service.PilotService;

/**
 * FlightController
 */

@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		flight.setPilot(pilot);
		model.addAttribute("flight", flight);
		return "addFlight";
	}
	
	@RequestMapping(value = "/flight/add", method = RequestMethod.POST)
	private String addFlightSubmit(@ModelAttribute FlightModel flight) {
		flightService.addFlight(flight);
		return "add";
	}
	
	@RequestMapping(value = "/flight/delete/{licenseNumber}/{flightNumber}", method = RequestMethod.GET)
	private String deleteFlight(@PathVariable(value = "licenseNumber") String licenseNumber, @PathVariable(value = "flightNumber") String flightNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		flightService.deleteFlight(pilot, flightNumber);
		return "delete";
	}
	
	@RequestMapping(value = "/flight/update/{licenseNumber}/{flightNumber}", method = RequestMethod.GET)
	private String updateFlight(@PathVariable(value = "licenseNumber") String licenseNumber, @PathVariable(value = "flightNumber") String flightNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		FlightModel flight = flightService.getFlightDetail(pilot, flightNumber);
		model.addAttribute("pilot", pilot);
		model.addAttribute("flight", flight);
		return "updateFlight";
	}
	
	@RequestMapping(value = "/flight/update", method = RequestMethod.POST)
	private String updateFlightSubmit(@ModelAttribute FlightModel flight, Long id) {
		flightService.updateFlight(flight);
		return "update";
	}
	
	@RequestMapping(value = "/flight/view")
	private String viewFlight(Model model) {
		List<FlightModel> flight = flightService.getFlightList();
		model.addAttribute("flight", flight);
		return "view-flight";
	}
}
