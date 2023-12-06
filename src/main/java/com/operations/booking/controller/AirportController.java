package com.operations.booking.controller;


import com.operations.booking.dto.AirportDTO;
import com.operations.booking.entity.Airport;
import com.operations.booking.mapper.AirportMapper;
import com.operations.booking.service.AirportService;



import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/airport")
public class AirportController {

    private final AirportService airportService;

    @GetMapping
    public List<AirportDTO> getAllAirports() {
        List<Airport> allAirports = airportService.getAllAirports();
        return allAirports.stream().map(AirportMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public Airport getAirport(@PathVariable @Min(1) Integer id) {
        return airportService.getAirport(id);
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveAirport(@Valid @RequestBody Airport airport) {
        airportService.addAirport(airport);
    } // duzelis

    @PutMapping(value = "/update")
    public Airport updateAirport(@Valid @RequestBody Airport airport) {
        return airportService.updateAirport(airport);
    }

    @DeleteMapping(value = "/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteAirport(@RequestParam @Min(1) Integer id) {
        return airportService.deleteAirport(id);
    }

}
