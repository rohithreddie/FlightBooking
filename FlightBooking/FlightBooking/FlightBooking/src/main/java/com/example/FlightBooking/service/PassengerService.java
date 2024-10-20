package com.example.FlightBooking.service;

import com.example.FlightBooking.entity.Passenger;
import com.example.FlightBooking.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;

    public Passenger createPassenger(Passenger passenger){
        return passengerRepository.save(passenger);
    }
    public Optional<Passenger> getPassengerById(Long id) {
        return passengerRepository.findById(id);
    }
    public void deletePassenger(Long id){
        passengerRepository.deleteById(id);
    }
    public Passenger updatePassenger(Long id, Passenger passengerDetails) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        // Update passenger fields
        passenger.setFirstName(passengerDetails.getFirstName());
        passenger.setLastName(passengerDetails.getLastName());
        passenger.setEmail(passengerDetails.getEmail());
        passenger.setPassportNumber(passengerDetails.getPassportNumber());

        return passengerRepository.save(passenger);
    }
    public Page<Passenger> listPassengers(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return passengerRepository.findAll(pageable);
    }
}
