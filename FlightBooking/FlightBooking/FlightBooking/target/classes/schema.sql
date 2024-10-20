CREATE TABLE IF NOT EXISTS flight (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    flight_number VARCHAR(50) NOT NULL,
    departure_city VARCHAR(100) NOT NULL,
    arrival_city VARCHAR(100) NOT NULL,
    departure_time TIMESTAMP NOT NULL,
    seats_available INT NOT NULL,
    price_per_seat DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS passenger (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    passport_number VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS booking (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    booking_date TIMESTAMP NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    flight_id BIGINT NOT NULL,
    passenger_id BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_flight FOREIGN KEY (flight_id) REFERENCES flight (id) ON DELETE CASCADE,
    CONSTRAINT fk_passenger FOREIGN KEY (passenger_id) REFERENCES passenger (id) ON DELETE CASCADE
);
