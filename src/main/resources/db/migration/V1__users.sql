CREATE TABLE users (
    email VARCHAR(50) PRIMARY KEY,
    name VARCHAR(80) NOT NULL,
    role ENUM('USER', 'ADMIN') DEFAULT 'USER',
    password VARCHAR(60) NOT NULL,
    phone_number VARCHAR(20) NOT NULL
);
