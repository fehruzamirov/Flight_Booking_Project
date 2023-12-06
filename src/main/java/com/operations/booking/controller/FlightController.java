package com.operations.booking.controller;


import com.operations.booking.entity.Flight;
import com.operations.booking.service.FlightService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/flight")
public class FlightController {

    private final FlightService flightService;

    @GetMapping(value = "/all")
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping(value = "/{id}")
    public Flight getFlight(@PathVariable @Min(1) Integer id) {
        return flightService.getFlight(id);
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveFlight(@Valid @RequestBody Flight flight) {
        flightService.addFlight(flight);
    }

    @PutMapping(value = "/update")
    public Flight updateFlight(@Valid @RequestBody Flight flight) {
        return flightService.updateFlight(flight);
    }

    @DeleteMapping(value = "/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteFlight(@RequestParam @Min(1) Integer id) {
        return flightService.deleteFlight(id);
    }

    @GetMapping(value = "/all/departure-data-between")
    public List<Flight> getAllFlightsDepartureDateBetween(@Valid @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
                                                          @Valid @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        return flightService.getAllFlightsDepartureDateBetween(start, end);
    }

    @GetMapping("/by-code/{code}")
    public Flight getFlightByCode(@PathVariable String code) {
        return flightService.getFlightByCode(code);
    }

}
