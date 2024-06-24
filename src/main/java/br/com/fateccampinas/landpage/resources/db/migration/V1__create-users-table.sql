CREATE TABLE users (
    email VARCHAR(50) PRIMARY KEY,
    username VARCHAR(20) UNIQUE NOT NULL,
    name VARCHAR(80) NOT NULL,
    authority ENUM('USER', 'ADMIN') DEFAULT 'USER',
    hashedPassword VARCHAR(60) NOT NULL,
    phoneNumber VARCHAR(20) NOT NULL
);
