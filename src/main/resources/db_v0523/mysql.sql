CREATE DATABASE IF NOT EXISTS flight_booking;
USE flight_booking;

create table user_data(
   id INT AUTO_INCREMENT PRIMARY KEY,
   email varchar(255),
   password varchar(255),
   date_of_birth date,
   gender varchar(10),
   status varchar(10),
   contact_number numeric(20),
   registration_date date,
   role varchar(255)
);

create table user_log(
   user_log_id INT AUTO_INCREMENT PRIMARY KEY ,
   last_login_date date,
   user_data_id INT,
   foreign key (user_data_id) references user_data(id)
);

create table token(
   id INT AUTO_INCREMENT PRIMARY KEY,
   user_data_id int,
   token varchar(255),
   token_type varchar (255),
   revoked BOOLEAN,
   expired BOOLEAN,
   foreign key (user_data_id) references user_data(id)

);

CREATE TABLE IF NOT EXISTS passenger (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_data_id INT,
    document_type VARCHAR(255),
    document_number VARCHAR(255),
    citizenship VARCHAR(255),
    issue_date DATE,
    expiry_date DATE,
    FOREIGN KEY (user_data_id) REFERENCES user_data(id)
    );

CREATE TABLE IF NOT EXISTS airport (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(255),
    name VARCHAR(255),
    city VARCHAR(255),
    country VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS company_and_aircraft (
    id INT AUTO_INCREMENT PRIMARY KEY,
    company_name VARCHAR(255),
    aircraft_model VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS flight (
    id INT AUTO_INCREMENT PRIMARY KEY,
    number VARCHAR(255) UNIQUE,
    company_and_aircraft_id INT,
    from_airport_id INT,
    to_airport_id INT,
    departure_time DATETIME,
    arrival_time DATETIME,
    duration TIME,
    date DATE,
    price DECIMAL(10, 2),
    FOREIGN KEY (from_airport_id) REFERENCES airport(id),
    FOREIGN KEY (to_airport_id) REFERENCES airport(id),
    FOREIGN KEY (company_and_aircraft_id) REFERENCES company_and_aircraft(id)
    );

CREATE TABLE IF NOT EXISTS aircraft_seat (
    id INT AUTO_INCREMENT PRIMARY KEY,
    number INT,
    class_name VARCHAR(255),
    flight_id INT,
    seat_status VARCHAR(255),
    FOREIGN KEY (flight_id) REFERENCES flight(id)
    );

CREATE TABLE IF NOT EXISTS food (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    price DECIMAL(10, 2)
    );

CREATE TABLE IF NOT EXISTS booking (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_data_id INT,
    flight_id INT,
    food_id INT,
    final_amount DECIMAL(10, 2),
    FOREIGN KEY (user_data_id) REFERENCES user_data(id),
    FOREIGN KEY (flight_id) REFERENCES flight(id),
    FOREIGN KEY (food_id) REFERENCES food(id)
    );

CREATE TABLE IF NOT EXISTS payment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    booking_id INT,
    card_holder VARCHAR(255),
    card_number VARCHAR(16) UNIQUE,
    exp_date DATE,
    cvc_code VARCHAR(3),
    final_amount DECIMAL(10, 2),
    payment_status VARCHAR(255),
    FOREIGN KEY (booking_id) REFERENCES booking(id)
    );


