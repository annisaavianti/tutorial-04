package com.apap.tu04.service;

import java.util.List;

import com.apap.tu04.model.FlightModel;
import com.apap.tu04.model.PilotModel;

/**
* FlightService
*/

public interface FlightService {
	void addFlight(FlightModel flight);
	void deleteFlight(PilotModel pilot, String flightNumber);
	List<FlightModel> getFlightList();
	FlightModel getFlightDetail(PilotModel pilot, String flightNumber);
	void updateFlight(FlightModel flight);
}