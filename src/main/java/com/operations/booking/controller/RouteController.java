package com.operations.booking.controller;



import com.operations.booking.dto.PageableQuery;
import com.operations.booking.entity.Route;
import com.operations.booking.service.RouteService;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/route")
public class RouteController {

    private final RouteService routeService;

    @GetMapping(value = "/all")
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @GetMapping(value = "/{id}")
    public Route getRoute(@PathVariable @Min(1) Integer id) {
        return routeService.getRoute(id);
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveRoute(@Valid @RequestBody Route route) {
        routeService.addRoute(route);
    }

    @PutMapping(value = "/update")
    public Route updateRoute(@Valid @RequestBody Route route) {
        return routeService.updateRoute(route);
    }

    @DeleteMapping(value = "/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteRoute(@RequestParam @Min(1) Integer id) {
        return routeService.deleteRoute(id);
    }


    @GetMapping(value = "/v1/departure-airport/{dep_id}/arrival-airport/{arr_id}")
    public ResponseEntity<List<Route>> getRoutesByDepartureAndArrivalAirportV1(
            @PathVariable @Min(1) Integer dep_id,
            @PathVariable @Min(1) Integer arr_id
    ) {
        List<Route> routes = routeService.getRoutesByDepartureAirportAndArrivalAirportByCustomRepo(dep_id, arr_id);
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @PostMapping(value = "/v2/departure-airport/{dep_id}/arrival-airport/{arr_id}")
    public ResponseEntity<Page<Route>> getRoutesByDepartureAndArrivalAirportV2(
            @Valid @RequestBody PageableQuery query,
            @PathVariable @Min(1) Integer dep_id,
            @PathVariable @Min(1) Integer arr_id
    ) {
        Page<Route> routes = routeService.getRoutesByDepartureAirportAndArrivalAirportByDefault(query, dep_id, arr_id);
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @GetMapping(value = "/v1/departure-airport/{dep_id}")
    public ResponseEntity<Route> getOneByDepartureIdV1(
            @PathVariable @Min(1) Integer dep_id
    ) {
        Route route = routeService.getFirstRouteByDepartureAirportByCustomRepo(dep_id);
        return new ResponseEntity<>(route, HttpStatus.OK);
    }

    @GetMapping(value = "/v2/departure-airport/{dep_id}")
    public ResponseEntity<Route> getOneByDepartureIdV2(
            @PathVariable @Min(1) Integer dep_id
    ) {
        Route route = routeService.getFirstRouteByDepartureAirportByDefault(dep_id);
        return new ResponseEntity<>(route, HttpStatus.OK);
    }

}


