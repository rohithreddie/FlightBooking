package com.example.FlightBooking.service;

import com.example.FlightBooking.controller.BookingController;
import com.example.FlightBooking.entity.Booking;
//import com.example.FlightBooking.entity.BookingStatus;
import com.example.FlightBooking.entity.Flight;
import com.example.FlightBooking.entity.Passenger;
import com.example.FlightBooking.repository.BookingRepository;
import com.example.FlightBooking.repository.FlightRepository;
import com.example.FlightBooking.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.*;

@Service
public class BookingService {
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightRepository flightRepository;

    public Booking createBooking(Long flightID, Long passengerId){
       Flight flight = flightRepository.findById(flightID)
               .orElseThrow(() -> new RuntimeException("Flight Not Found"));
        Passenger passenger = passengerRepository.findById(passengerId)
               .orElseThrow(() -> new RuntimeException("Passenger Not Found"));
        if (flight.getSeatsAvailable() <= 0) {
            throw new RuntimeException("No seats available");
        }

        // Decrease available seats and create booking
        flight.setSeatsAvailable(flight.getSeatsAvailable() - 1);

        Booking booking = new Booking();
        booking.setFlight(flight);
        booking.setPassenger(passenger);
        booking.setBookingDate(LocalDateTime.now());
        booking.setTotalAmount(flight.getPricePerSeat());
        booking.setStatus(Booking.BookingStatus.CONFIRMED);
        Booking savedBooking = bookingRepository.save(booking);
        return savedBooking;

       }
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }
    public void cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        //booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
    }
    public Page<Booking> listBookings(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        return bookingRepository.findAll(pageable);
    }
}


