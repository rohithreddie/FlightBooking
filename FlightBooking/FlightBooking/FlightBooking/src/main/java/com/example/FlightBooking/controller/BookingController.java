package com.example.FlightBooking.controller;

import com.example.FlightBooking.entity.Booking;
import com.example.FlightBooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public Booking createBooking(@RequestParam Long flightId, @RequestParam Long passengerId){
        return bookingService.createBooking(flightId,passengerId);
    }
    @GetMapping("/{id}")
    public  Booking getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id)
                .orElseThrow(() -> new RuntimeException("Booking Not Found"));
    }
    @DeleteMapping("/{id}")
    public void cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
    }
    @GetMapping
    public Page<Booking> listBookings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "bookingDate") String sortBy) {
        return bookingService.listBookings(page, size, sortBy);
    }

}
