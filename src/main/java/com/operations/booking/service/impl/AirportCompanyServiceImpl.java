package com.operations.booking.service.impl;

import com.operations.booking.entity.AirportCompany;
import com.operations.booking.entity.Flight;
import com.operations.booking.entity.Passenger;
import com.operations.booking.entity.Ticket;
import com.operations.booking.exception.newexcep.NotFoundException;
import com.operations.booking.exception.newexcep.QuotaIsFullException;
import com.operations.booking.repository.AirportCompanyRepository;
import com.operations.booking.service.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AirportCompanyServiceImpl implements AirportCompanyService {

    private final AirportCompanyRepository airportCompanyRepository;
    private final FlightService flightService;
    private final RouteService routeService;
    private final AirportService airportService;
    private final TicketService ticketService;
    private final PassengerService passengerService;

    @Override
    public List<AirportCompany> getAllAirportCompanies() {
        log.info("Getting all airport companies");
        return airportCompanyRepository.findAll();
    }

    @Override
    public AirportCompany getAirportCompany(final Integer id) {
        log.info("Getting airport company with ID: {}", id);

        Optional<AirportCompany> byId = airportCompanyRepository.findById(id);
        return byId.orElseThrow(() -> new NotFoundException("Airport Company"));
    }

    @Override
    public void addAirportCompany(final AirportCompany airportCompany) {
        log.info("Adding a new airport company: {}", airportCompany);

        airportCompanyRepository.save(airportCompany);
    }

    @Override
    public AirportCompany updateAirportCompany(final AirportCompany airportCompany) {
        log.info("Updating airport company with ID: {}", airportCompany.getId());

        return airportCompanyRepository.save(airportCompany);
    }

    @Override
    public boolean addNewFlight(final Integer airport_company_id, final Integer flight_id) {
        log.info("Adding a new flight with ID {} to airport company with ID: {}", flight_id, airport_company_id);

        AirportCompany one = airportCompanyRepository.getOne(airport_company_id);
        Flight flight = flightService.getFlight(flight_id);
        List<Flight> flights = one.getFlights();
        flights.add(flight);
        airportCompanyRepository.save(one);
        return true;
    }

    @Override
    public boolean deleteAirportCompany(final Integer id) {
        log.info("Deleting airport company with ID: {}", id);

        AirportCompany airportCompany = getAirportCompany(id);
        airportCompanyRepository.delete(airportCompany);
        return true;
    }

    @Override
    public boolean cancelTicket(final Integer ticket_id) {
        log.info("Cancelling ticket with ID: {}", ticket_id);

        Ticket ticket = ticketService.getTicket(ticket_id);
        Flight flight = flightService.getFlight(ticket.getFlight().getId());
        flight.getTickets().remove(ticket);
        flightService.updateFlight(flight);
        ticketService.deleteTicket(ticket.getId());
        return true;
    }

    @Override
    public Ticket searchTicket(final Integer ticket_id) {
        log.info("Searching for ticket with ID: {}", ticket_id);

        return ticketService.getTicket(ticket_id);
    }

    @Override
    public Ticket buyTicketForFlight(final Integer flight_id, final Integer passenger_id) {
        log.info("Buying ticket for flight with ID {} for passenger with ID: {}", flight_id, passenger_id);

        Passenger passenger = passengerService.getPassenger(passenger_id);
        Flight flight = flightService.getFlight(flight_id);

        if (flight.getTickets().size() < flight.getQuota()) {
            Ticket newTicket = new Ticket();
            newTicket.setPassenger(passenger);
            newTicket.setFlight(flight);

            ticketService.addTicket(newTicket);

            int rate = (flight.getQuota() * 10) / 100;
            if (flight.getTickets().size() > rate) {
                int newPrice = flight.getPrice() + (flight.getPrice() * ((flight.getTickets().size() / rate) * 10)) / 100;
                flight.setPrice(newPrice);
                flightService.updateFlight(flight);
            }
        } else {
            throw new QuotaIsFullException(flight.getCode());
        }
        return null;
    }

    @Override
    public List<Flight> getAllFlightByAirportCompany(final Integer airport_company_id) {
        log.info("Getting all flights for airport company with ID: {}", airport_company_id);

        AirportCompany airportCompany = getAirportCompany(airport_company_id);
        return airportCompany.getFlights();
    }
}
