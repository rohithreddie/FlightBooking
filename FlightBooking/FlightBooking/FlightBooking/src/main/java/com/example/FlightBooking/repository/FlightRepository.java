package com.example.FlightBooking.repository;

import com.example.FlightBooking.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface FlightRepository extends JpaRepository<Flight, Long> {
    Optional<Flight> findByFlightNumber(String flightNumber);
}
