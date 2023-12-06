package com.operations.booking.controller;



import com.operations.booking.entity.AirportCompany;
import com.operations.booking.entity.Flight;
import com.operations.booking.entity.Ticket;

import com.operations.booking.service.AirportCompanyService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/airport-company")
public class AirportCompanyController {

    private final AirportCompanyService airportCompanyService;


    @GetMapping(value = "/all")
    public List<AirportCompany> getAllAirportCompanies() {
        return airportCompanyService.getAllAirportCompanies();
    }

    @GetMapping(value = "/{id}")
    public AirportCompany getAirportCompany(@PathVariable @Min(1) final Integer id) {
        return airportCompanyService.getAirportCompany(id);
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveAirportCompany(@Valid @RequestBody final AirportCompany airportCompany) {
        airportCompanyService.addAirportCompany(airportCompany);
    }

    @PutMapping(value = "/update")
    public AirportCompany updateAirportCompany(@RequestBody final AirportCompany airportCompany) {
        return airportCompanyService.updateAirportCompany(airportCompany);
    }

    @DeleteMapping(value = "/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteAirportCompany(@RequestParam @Min(1) final Integer id) {
        return airportCompanyService.deleteAirportCompany(id);
    }

    @PostMapping(value = "/add-flight")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean addNewFlight(@RequestParam @Min(1) final Integer organization_id,
                                @RequestParam @Min(1) final Integer flight_id) {
        return airportCompanyService.addNewFlight(organization_id, flight_id);
    }

    @PostMapping(value = "/buy-ticket")
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket buyTicket(@RequestParam @Min(1) final Integer flight_id,
                            @RequestParam @Min(1) final Integer passenger_id) {
        return airportCompanyService.buyTicketForFlight(flight_id, passenger_id);
    }

    @PostMapping(value = "/cancel-ticket")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean cancelTicket(@RequestParam @Min(1) final Integer ticket_id) {
        return airportCompanyService.cancelTicket(ticket_id);
    }

    @PostMapping(value = "/search-ticket")
    public Ticket searchTicket(@RequestParam @Min(1) final Integer ticket_id) {
        return airportCompanyService.searchTicket(ticket_id);
    }

    @GetMapping("/by-airport-company/{airport_company_id}")
    public List<Flight> getAllFlightByAirportCompany(@PathVariable final Integer airport_company_id) {
        return airportCompanyService.getAllFlightByAirportCompany(airport_company_id);
    }

}
