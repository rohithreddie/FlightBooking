
-- Insert sample flights
INSERT INTO flight (flight_number, departure_city, arrival_city, departure_time, seats_available, price_per_seat)
VALUES ('FL123', 'New York', 'London', '2024-10-20T10:00:00', 50, 500.00);

-- Insert sample passengers
INSERT INTO passenger (first_name, last_name, email, passport_number)
VALUES ('John', 'Doe', 'john.doe@example.com', 'A12345678');

-- Insert sample booking
INSERT INTO booking (booking_date, total_amount, flight_id, passenger_id, status)
VALUES ('2024-10-19T12:00:00', 500.00, 1, 1, 'CONFIRMED');

