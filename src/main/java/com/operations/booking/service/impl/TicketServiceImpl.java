package com.operations.booking.service.impl;

import com.operations.booking.dto.PageableQuery;
import com.operations.booking.entity.Flight;
import com.operations.booking.entity.Ticket;
import com.operations.booking.exception.newexcep.NotFoundException;
import com.operations.booking.repository.TicketRepository;
import com.operations.booking.service.TicketService;

import com.operations.booking.util.PageableRequestBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public List<Ticket> getAllTickets() {
        log.info("Getting all tickets");
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getTicket(Integer id) {
        log.info("Getting ticket with ID: {}", id);

        Optional<Ticket> byId = ticketRepository.findById(id);
        return byId.orElseThrow(() -> new NotFoundException("Ticket"));
    }

    @Override
    public void addTicket(Ticket ticket) {
        log.info("Adding a new ticket: {}", ticket);

        ticketRepository.save(ticket);
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        log.info("Updating ticket with ID: {}", ticket.getId());

        return ticketRepository.save(ticket);
    }

    @Override
    public boolean deleteTicket(Integer id) {
        log.info("Deleting ticket with ID: {}", id);

        Ticket ticket = getTicket(id);
        ticketRepository.delete(ticket);
        return true;
    }

    @Override
    public Page<Ticket> getAllByFlightPagination(PageableQuery pageable, Flight flight) {
        log.info("Getting all tickets by flight with pagination: {}", pageable);

        PageRequest p = PageableRequestBuilder.build(pageable);
        return ticketRepository.getAllByFlightPagination(p, flight);
    }
}
