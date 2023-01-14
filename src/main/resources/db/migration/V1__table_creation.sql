CREATE TABLE orders
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    status VARCHAR(50) NOT NULL,
    sender_name VARCHAR(50) NOT NULL,
    sender_phone VARCHAR(50) NOT NULL,
    receiver_name VARCHAR(50) NOT NULL,
    receiver_phone VARCHAR(50) NOT NULL,
    sender_city VARCHAR(50) NOT NULL,
    sender_county VARCHAR(50) NOT NULL,
    sender_country VARCHAR(50) NOT NULL,
    sender_address VARCHAR(50) NOT NULL,
    receiver_city VARCHAR(50) NOT NULL,
    receiver_county VARCHAR(50) NOT NULL,
    receiver_country VARCHAR(50) NOT NULL,
    receiver_address VARCHAR(50) NOT NULL,
    parcel_number INT NOT NULL,
    cost DECIMAL(6,2),
    pickup_date datetime,

    PRIMARY KEY (id)
);