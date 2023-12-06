package com.operations.booking.service;


import com.operations.booking.dto.PageableQuery;
import com.operations.booking.entity.Route;


import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface RouteService {

    List<Route> getAllRoutes();

    Route getRoute(Integer id);

    void addRoute(@RequestBody Route route);

    Route updateRoute(@RequestBody Route route);

    boolean deleteRoute(Integer id);


    List<Route> getRoutesByDepartureAirportAndArrivalAirportByCustomRepo(Integer departure_airport_id, Integer arrival_airport_id);

    Page<Route> getRoutesByDepartureAirportAndArrivalAirportByDefault(PageableQuery query, Integer departure_airport_id, Integer arrival_airport_id);

    Route getFirstRouteByDepartureAirportByCustomRepo(Integer departure_airport_id);

    Route getFirstRouteByDepartureAirportByDefault(Integer departure_airport_id);

}
