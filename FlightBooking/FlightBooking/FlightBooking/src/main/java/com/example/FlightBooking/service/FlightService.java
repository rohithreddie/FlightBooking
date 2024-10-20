package com.example.FlightBooking.service;


import com.example.FlightBooking.entity.Flight;
import com.example.FlightBooking.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;


    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public List<Flight> getAllFLights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> getFlightById(Long id) {
        return Optional.ofNullable(flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found")));
    }

    public Flight updateFlight(Long id, Flight flightDetails) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight Not Found"));
        flight.setFlightNumber(flightDetails.getFlightNumber());
        flight.setDepartureCity(flightDetails.getDepartureCity());
        flight.setArrivalCity(flightDetails.getArrivalCity());
        flight.setDepartureTime(flightDetails.getDepartureTime());
        flight.setSeatsAvailable(flightDetails.getSeatsAvailable());
        flight.setPricePerSeat(flightDetails.getPricePerSeat());
        return flightRepository.save(flight);
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    public Page<Flight> listFlights(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return flightRepository.findAll(pageable);

    }
}
