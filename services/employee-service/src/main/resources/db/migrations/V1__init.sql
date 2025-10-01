CREATE TABLE employees (
    id CHAR(36) NOT NULL PRIMARY KEY,  -- UUID stored as 16 bytes (efficient in MySQL)

    employee_code VARCHAR(50) NOT NULL UNIQUE,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100),
    date_of_birth DATE NOT NULL,
    date_of_joining DATE NOT NULL,
    department VARCHAR(100),
    designation VARCHAR(100),
    email VARCHAR(150) NOT NULL UNIQUE,
    phone_number VARCHAR(15),
    address VARCHAR(200),
    city VARCHAR(100),
    state VARCHAR(100),
    postal_code VARCHAR(20),
    country VARCHAR(100),
    employment_type VARCHAR(50),
    salary DOUBLE,
    status VARCHAR(20),

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);