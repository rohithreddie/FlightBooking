package com.example.FlightBooking.controller;

import com.example.FlightBooking.entity.Flight;
import com.example.FlightBooking.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @PostMapping
    public Flight createFlight(@RequestBody Flight flight){
        return flightService.createFlight(flight);
    }

    @GetMapping
    public Page<Flight> listFlights(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10")int size,
        @RequestParam(defaultValue = "departureTime") String sortBy){

        return flightService.listFlights(page, size, sortBy);
    }
    @GetMapping("/{id}")
    public Optional<Flight> getFlightById(@PathVariable Long id){
        return flightService.getFlightById(id);
    }
    @PutMapping("/{id}")
    public Flight updateFlight(@PathVariable Long id, @RequestBody Flight flight){
        return flightService.updateFlight(id,flight);
    }
    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id){
        flightService.deleteFlight(id);
    }

}
