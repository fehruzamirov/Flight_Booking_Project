package com.operations.booking.service.impl;

import com.operations.booking.dto.Address;
import com.operations.booking.entity.Airport;
import com.operations.booking.exception.newexcep.NotFoundException;
import com.operations.booking.repository.AirportRepository;
import com.operations.booking.service.AirportService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;

    @Override
    public List<Airport> getAllAirports() {
        log.info("Getting all airports");
        return airportRepository.findAll();
    }

    @Override
    public Airport getAirport(Integer id) {
        log.info("Getting airport with ID: {}", id);

        Optional<Airport> byId = airportRepository.findById(id);
        return byId.orElseThrow(() -> new NotFoundException("Airport"));
    }

    @Override
    public void addAirport(Airport airport) {
        log.info("Adding a new airport: {}", airport);

        airportRepository.save(airport);
    }

    @Override
    public Airport updateAirport(Airport airport) {
        log.info("Updating airport with ID: {}", airport.getId());

        return airportRepository.save(airport);
    }

    @Override
    public boolean deleteAirport(Integer id) {
        log.info("Deleting airport with ID: {}", id);

        airportRepository.delete(getAirport(id));
        return true;
    }

    // Loglama əmrləri üçün
    // ----------------------------------------------------------------------------------

    private List<Address> getAddressCityStartsWith(String prefix) {
        List<Airport> allAirports = getAllAirports();
        return allAirports.stream()
                .map(Airport::getAddresses)
                .flatMap(Collection::stream)
                .distinct()
                .filter(a -> a.getCity().startsWith(prefix))
                .collect(Collectors.toList());
    }

    private void printAllAdressCityStartsWith(String prefix) {
        List<Address> addressCityStartsWith = getAddressCityStartsWith(prefix);
        addressCityStartsWith.stream()
                .map(address -> address.getCity() + "/" + address.getStreetCode() + "/" +
                        address.getBuildingNo())
                .distinct()
                .collect(Collectors.toList())
                .forEach(address -> log.info("Address: {}", address));
    }

    private void reduceAddressListToCityNameAndStreetCode() {
        List<Airport> allAirports = getAllAirports();
        String reducedAddressList = allAirports.stream()
                .map(Airport::getAddresses)
                .flatMap(Collection::stream)
                .map(address -> address.getCity() + " " + address.getStreetCode())
                .reduce("", (s1, s2) -> s1 + s2);

        log.info("Reduced address List : {}", reducedAddressList);
    }

    private String getCombinedAddressOfBoth(Airport airport1, Airport airport2) {
        BiFunction<Airport, Airport, String> function = (a1, a2) -> a1.getAddresses().get(0).getCity() + "-" + a1.getAddresses().get(0).getStreetCode()
                + " ------- " +
                a2.getAddresses().get(0).getCity() + "-" + a2.getAddresses().get(0).getStreetCode();

        // Gets combined Address String
        return function.apply(airport1, airport2);
    }

    private void consumeAirportAddresses(Integer airport_id) {
        Airport airport = airportRepository.getOne(airport_id);

        Consumer<Airport> airportConsumer = air -> air.getAddresses().forEach(address -> log.info("Address: {}", address));

        airportConsumer.accept(airport);
    }
}
