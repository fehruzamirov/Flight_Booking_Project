package com.operations.booking.service;


import com.operations.booking.entity.AirportCompany;
import com.operations.booking.entity.Flight;
import com.operations.booking.entity.Ticket;

import java.util.List;

public interface AirportCompanyService {
    List<AirportCompany> getAllAirportCompanies();

    AirportCompany getAirportCompany(Integer id);

    void addAirportCompany(AirportCompany airportCompany);

    AirportCompany updateAirportCompany(AirportCompany airportCompany);

    boolean deleteAirportCompany(Integer id);

    boolean addNewFlight(Integer airport_company_id, Integer flight_id);

    Ticket buyTicketForFlight(Integer flight_id, Integer passenger_id);

    boolean cancelTicket(Integer ticket_id);

    Ticket searchTicket(Integer ticket_id);

    List<Flight> getAllFlightByAirportCompany(Integer airport_company_id);
}
