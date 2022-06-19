-- db schema
CREATE SCHEMA IF NOT EXISTS hiking_tour;

CREATE TABLE IF NOT EXISTS hiking_tour.place
(
    name       VARCHAR(100) PRIMARY KEY,
    start_time TIME,
    end_time   TIME,
    age_max    INTEGER        NOT NULL,
    age_min    INTEGER        NOT NULL,
    price      DECIMAL(19, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS hiking_tour.hiker
(
    id       BIGINT PRIMARY KEY AUTO_INCREMENT,
    name     VARCHAR(100),
    age      INTEGER NOT NULL,
    place VARCHAR(100) NOT NULL,
    group_id BIGINT  NOT NULL
);

CREATE TABLE IF NOT EXISTS hiking_tour.tour_group
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    booking_date DATE NOT NULL
);

INSERT INTO hiking_tour.place (name, start_time, end_time, age_min, age_max, price)
VALUES ('Shire', '07:00:00', '09:00:00', 5, 100, 29.90),
       ('Gondor', '10:00:00', '13:00:00', 11, 50, 59.90),
       ('Mordor', '14:00:00', '19:00:00', 18, 40, 99.90);
