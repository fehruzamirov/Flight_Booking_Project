package com.operations.booking.repository.dynamic;


import com.operations.booking.entity.Route;

import java.util.List;

public interface RouteRepositoryCustom {

    List<Route> getRoutesByDepartureAirportAndArrivalAirport(Integer departure_airport_id, Integer arrival_airport_id);

    Route getFirstRouteByDepartureAirport(Integer departure_airport_id);
}
