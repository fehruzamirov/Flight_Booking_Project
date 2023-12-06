package com.operations.booking.service.impl;

import com.operations.booking.entity.Flight;
import com.operations.booking.exception.newexcep.NotFoundException;
import com.operations.booking.repository.FlightRepository;
import com.operations.booking.service.FlightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j // Add this annotation to enable logging
@RequiredArgsConstructor
@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    @Override
    public List<Flight> getAllFlights() {
        log.info("Getting all flights");
        return flightRepository.findAll();
    }

    @Override
    public Flight getFlight(Integer id) {
        log.info("Getting flight with ID: {}", id);

        Optional<Flight> byId = flightRepository.findById(id);
        return byId.orElseThrow(() -> new NotFoundException("Flight"));
    }

    @Override
    public void addFlight(Flight flight) {
        log.info("Adding a new flight: {}", flight);
        flightRepository.save(flight);
    }

    @Override
    public Flight updateFlight(Flight flight) {
        log.info("Updating flight with ID: {}", flight.getId());
        return flightRepository.save(flight);
    }

    @Override
    public boolean deleteFlight(Integer id) {
        log.info("Deleting flight with ID: {}", id);
        flightRepository.delete(getFlight(id));
        return true;
    }

    private void printFlightBasicInfos(Integer flight_id) {
        Flight flight = flightRepository.getOne(flight_id);

        StringBuilder sb = new StringBuilder();
        sb.append("Flight Info : ")
                .append("Flight Code  : ").append(flight.getCode())
                .append("Flight Price : ").append(flight.getPrice())
                .append("Flight Airport Company : ").append(flight.getAirportCompany().getName());

        log.info(sb.toString());
    }

    @Override
    public List<Flight> getAllFlightsDepartureDateBetween(Date start, Date end) {
        log.info("Getting all flights between departure dates: {} and {}", start, end);
        return flightRepository.getAllByDepartureDateBetween(start, end);
    }

    @Override
    public Flight getFlightByCode(String code) {
        log.info("Getting flight by code: {}", code);
        return flightRepository.getByCode(code);
    }
}
