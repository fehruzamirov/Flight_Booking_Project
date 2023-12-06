package com.operations.booking.controller;


import com.operations.booking.dto.PassengerDto;
import com.operations.booking.entity.Passenger;
import com.operations.booking.exception.newexcep.InvalidRequestException;
import com.operations.booking.mapper.PassengerMapper;
import com.operations.booking.service.PassengerService;



import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/passenger")
public class PassengerController {

    private final PassengerService passengerService;
    private static final PassengerMapper PASSENGER_MAPPER = Mappers.getMapper(PassengerMapper.class);

    @GetMapping(value = "/all")
    public List<PassengerDto> getAllPassengers() {
        List<Passenger> allPassengers = passengerService.getAllPassengers();
        return allPassengers.stream().map(PASSENGER_MAPPER::toDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public PassengerDto getPassenger(@PathVariable @Min(1) Integer id) {
        return PASSENGER_MAPPER.toDto(passengerService.getPassenger(id));
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void savePassenger(@Valid @RequestBody PassengerDto passenger) {
        passengerService.addPassenger(PASSENGER_MAPPER.toEntity(passenger));
    }

    @PutMapping(value = "/update")
    public Passenger updatePassenger(@Valid @RequestBody Passenger passenger) {
        if (passenger.getId() == null) {
            throw new InvalidRequestException("Passenger id can not be null for update!");
        }
        return passengerService.updatePassenger(passenger);
    }

    @DeleteMapping(value = "/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deletePassenger(@RequestParam @Min(1) Integer id) {
        return passengerService.deletePassenger(id);
    }

}
