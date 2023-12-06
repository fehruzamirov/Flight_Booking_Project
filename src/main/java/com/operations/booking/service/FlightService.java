package com.operations.booking.service;


import com.operations.booking.entity.Flight;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;


public interface FlightService {
    List<Flight> getAllFlights();

    Flight getFlight(Integer id);

    void addFlight(@RequestBody Flight flight);

    Flight updateFlight(@RequestBody Flight flight);

    boolean deleteFlight(Integer id);

    List<Flight> getAllFlightsDepartureDateBetween(Date start, Date end);

    Flight getFlightByCode(String code);

}
