package com.operations.booking.controller;


import com.operations.booking.dto.PageableQuery;
import com.operations.booking.entity.Flight;
import com.operations.booking.entity.Ticket;
import com.operations.booking.service.FlightService;

import com.operations.booking.service.TicketService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ticket")
public class TicketController {

    private final TicketService ticketService;

    private final FlightService flightService;

    @GetMapping(value = "/all")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping(value = "/{id}")
    public Ticket getTicket(@PathVariable @Min(1) Integer id) {
        return ticketService.getTicket(id);
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveTicket(@Valid @RequestBody Ticket ticket) {
        ticketService.addTicket(ticket);
    }

    @PutMapping(value = "/update")
    public Ticket updateTicket(@Valid @RequestBody Ticket ticket) {
        return ticketService.updateTicket(ticket);
    }

    @DeleteMapping(value = "/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteTicket(@RequestParam @Min(1) Integer id) {
        return ticketService.deleteTicket(id);
    }

    @PostMapping(value = "/flight/{id}")
    public Page<Ticket> getAllTicketsByFlightId(
            @Valid @RequestBody PageableQuery query,
            @PathVariable @Min(1) Integer id
    ) {
        Flight flight = flightService.getFlight(id);
        return ticketService.getAllByFlightPagination(query, flight);
    }


}
