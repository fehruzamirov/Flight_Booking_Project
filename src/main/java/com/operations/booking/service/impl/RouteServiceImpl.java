package com.operations.booking.service.impl;

import com.operations.booking.dto.PageableQuery;
import com.operations.booking.entity.Airport;
import com.operations.booking.entity.Route;
import com.operations.booking.exception.newexcep.NotFoundException;
import com.operations.booking.repository.RouteRepository;
import com.operations.booking.service.AirportService;
import com.operations.booking.service.RouteService;

import com.operations.booking.util.PageableRequestBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final AirportService airportService;

    @Override
    public List<Route> getAllRoutes() {
        log.info("Getting all routes");
        return routeRepository.findAll();
    }

    @Override
    public Route getRoute(Integer id) {
        log.info("Getting route with ID: {}", id);

        Optional<Route> byId = routeRepository.findById(id);
        return byId.orElseThrow(() -> new NotFoundException("Route"));
    }

    @Override
    public void addRoute(Route route) {
        log.info("Adding a new route: {}", route);

        routeRepository.save(route);
    }

    @Override
    public Route updateRoute(Route route) {
        log.info("Updating route with ID: {}", route.getId());

        return routeRepository.save(route);
    }

    @Override
    public boolean deleteRoute(Integer id) {
        log.info("Deleting route with ID: {}", id);

        routeRepository.delete(getRoute(id));
        return true;
    }

    @Override
    public List<Route> getRoutesByDepartureAirportAndArrivalAirportByCustomRepo(Integer departure_airport_id, Integer arrival_airport_id) {
        log.info("Getting routes by departure airport ID {} and arrival airport ID {}", departure_airport_id, arrival_airport_id);

        Airport dep_airport = airportService.getAirport(departure_airport_id);
        Airport arr_airport = airportService.getAirport(arrival_airport_id);
        return routeRepository.getRoutesByDepartureAirportAndArrivalAirport(dep_airport.getId(), arr_airport.getId());
    }

    @Override
    public Page<Route> getRoutesByDepartureAirportAndArrivalAirportByDefault(PageableQuery query, Integer departure_airport_id, Integer arrival_airport_id) {
        log.info("Getting routes by departure airport ID {} and arrival airport ID {} with pagination: {}", departure_airport_id, arrival_airport_id, query);

        Airport dep_airport = airportService.getAirport(departure_airport_id);
        Airport arr_airport = airportService.getAirport(arrival_airport_id);
        PageRequest p = PageableRequestBuilder.build(query);
        return routeRepository.findByDeparture_airportAndArrival_airport(p, dep_airport, arr_airport);
    }

    @Override
    public Route getFirstRouteByDepartureAirportByCustomRepo(Integer departure_airport_id) {
        log.info("Getting the first route by departure airport ID {}", departure_airport_id);

        Airport dep_airport = airportService.getAirport(departure_airport_id);
        return routeRepository.getFirstRouteByDepartureAirport(dep_airport.getId());
    }

    @Override
    public Route getFirstRouteByDepartureAirportByDefault(Integer departure_airport_id) {
        log.info("Getting the first route by departure airport ID {}", departure_airport_id);

        Airport dep_airport = airportService.getAirport(departure_airport_id);
        Optional<Route> byDepartureAirport = Optional.ofNullable(routeRepository.findByDepartureAirport(dep_airport));
        return byDepartureAirport.orElseThrow(() -> new NotFoundException("FirstRouteByDepartureAirport"));
    }
}
