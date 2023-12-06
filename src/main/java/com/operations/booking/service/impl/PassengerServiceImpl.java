package com.operations.booking.service.impl;

import com.operations.booking.entity.Passenger;
import com.operations.booking.exception.newexcep.NotFoundException;
import com.operations.booking.repository.PassengerRepository;
import com.operations.booking.service.PassengerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;

    @Override
    public List<Passenger> getAllPassengers() {
        log.info("Getting all passengers");
        return passengerRepository.findAll();
    }

    @Override
    public Passenger getPassenger(Integer id) {
        log.info("Getting passenger with ID: {}", id);

        Optional<Passenger> byId = passengerRepository.findById(id);
        return byId.orElseThrow(() -> new NotFoundException("Passenger"));
    }

    @Override
    public void addPassenger(Passenger passenger) {
        log.info("Adding a new passenger: {}", passenger);

        passengerRepository.save(passenger);
    }

    @Override
    public Passenger updatePassenger(Passenger passenger) {
        log.info("Updating passenger with ID: {}", passenger.getId());

        return passengerRepository.save(passenger);
    }

    @Override
    public boolean deletePassenger(Integer id) {
        log.info("Deleting passenger with ID: {}", id);

        passengerRepository.delete(getPassenger(id));
        return true;
    }


    @Override
    public List<Passenger> getPassengersNameStartsWith(String prefix) {
        log.info("Getting passengers with names starting with: {}", prefix);

        List<Passenger> allPassengers = getAllPassengers();
        return allPassengers.stream()
                .filter(p -> p.getFirstname().startsWith(prefix))
                .collect(Collectors.toList());
    }

    @Override
    public List<Passenger> getPassengersSortedViaLastNameAsUpperCase() {
        log.info("Getting passengers sorted by last name in uppercase");

        List<Passenger> allPassengers = getAllPassengers();
        return allPassengers.stream()
                .sorted(Comparator.comparing(Passenger::getLastname))
                .peek(p -> p.setLastname(p.getLastname().toUpperCase()))
                .collect(Collectors.toList());
    }

    private Passenger getTheOldestMalePassengerAndLowerCaseFirstLast() {
        log.info("Getting the oldest male passenger and setting first and last name to lowercase");

        List<Passenger> allPassengers = getAllPassengers();
        return allPassengers.stream()
                .max(Comparator.comparing(Passenger::getAge))
                .filter(p -> p.getGender().equals("male"))
                .orElseThrow(() -> new NotFoundException("No matching passenger"));
    }

    private Boolean isAnyPassengerLastNameStartsWithCharAndFemale(String prefix) {
        log.info("Checking if any passenger has a last name starting with {} and is female", prefix);

        List<Passenger> allPassengers = getAllPassengers();
        return allPassengers.stream()
                .anyMatch(p -> p.getLastname().startsWith(prefix) && p.getGender().equals("female"));
    }

    private Boolean isAllPassengerFemaleAndAgeBetween(Integer minAge, Integer maxAge) {
        log.info("Checking if all passengers are female and their age is between {} and {}", minAge, maxAge);

        List<Passenger> allPassengers = getAllPassengers();
        return allPassengers.stream()
                .allMatch(p -> p.getGender().equals("female") && (p.getAge() > minAge && p.getAge() < maxAge));
    }

    private Boolean isNonePassengerFirstNameAndPhoneStartsWith(String firstName, String phonePrefix) {
        log.info("Checking if no passenger has the first name {} and phone starts with {}", firstName, phonePrefix);

        List<Passenger> allPassengers = getAllPassengers();
        return allPassengers.stream()
                .noneMatch(p -> p.getFirstname().equals(firstName) && p.getPhone().startsWith(phonePrefix));
    }

    private Integer getCountOfMalePassengersAgeBetween(Integer minAge, Integer maxAge) {
        log.info("Getting the count of male passengers aged between {} and {}", minAge, maxAge);

        List<Passenger> allPassengers = getAllPassengers();
        return (int) allPassengers.stream()
                .filter(p -> p.getGender().equals("male") && (p.getAge() > minAge && p.getAge() < maxAge))
                .count();
    }

    private List<String> getPassengerListAsFirstNameAndLastName() {
        log.info("Getting the list of passengers as first name and last name");

        List<Passenger> allPassengers = getAllPassengers();
        return allPassengers.stream()
                .map(p -> p.getFirstname() + " " + p.getLastname())
                .collect(Collectors.toList());
    }
}
