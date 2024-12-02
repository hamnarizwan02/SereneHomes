create database SereneHomes;
use SereneHomes;
create table users(
 userID int primary key auto_increment, 
 name varchar(255), 
 email varchar(255), 
 password varchar(255), 
 phone_number varchar(255), 
 usertype varchar(255));
 
insert into users values (1,'Hamna Rizwan', 'hamna@gmail.com', 'hamna123', '+923214511222', 'Customer');
insert into users (name, email, password, phone_number, usertype) values ('Abeeha Rizwan', 'abeeha@gmail.com', 'abheea', '+923215521222', 'Customer');
create table Property (
    property_id INT PRIMARY KEY AUTO_INCREMENT,
    owner_id INT, 
    property_name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    price_per_night DECIMAL(10, 2) NOT NULL,
    start_date_availability DATE,
    end_date_availability DATE,
    available BOOLEAN NOT NULL
);
INSERT INTO Property (owner_id, property_name, location, price_per_night, start_date_availability, end_date_availability, available)
VALUES (3, 'Karachi Beach House', 'Karachi', 120.00, '2023-12-01', '2023-12-15', true);
INSERT INTO Property (owner_id, property_name, location, price_per_night, start_date_availability, end_date_availability, available)
VALUES (4, 'Islamabad Retreat', 'Islamabad', 90.00, '2023-12-10', '2023-12-25', true);
INSERT INTO Property (owner_id, property_name, location, price_per_night, start_date_availability, end_date_availability, available)
VALUES (6, 'Lahore Loft', 'Lahore', 80.00, '2023-12-05', '2023-12-20', true);


CREATE TABLE Booking (
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    property_id INT,
    check_in_date DATE,
    check_out_date DATE,
    FOREIGN KEY (customer_id) REFERENCES users(userID),
    FOREIGN KEY (property_id) REFERENCES Property(property_id)
);

CREATE TABLE curr_user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    userID INT,
    user_type VARCHAR(255),
    FOREIGN KEY (userID) REFERENCES users(userID)
);

create table feedback (
  feedbackID int primary key auto_increment, 
  feedback varchar(255),
  property_id int ,
  userID int,
  foreign key (property_id) references Property(property_id),
  foreign key (userID) references users(userID)
);
