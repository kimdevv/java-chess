CREATE DATABASE IF NOT EXISTS chess DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE chess;

DROP TABLE IF EXISTS movement;


CREATE TABLE movement
(
    movement_id INT         NOT NULL AUTO_INCREMENT,
    source      VARCHAR(64) NOT NULL,
    target      VARCHAR(64) NOT NULL,
    color       VARCHAR(64) NOT NULL,
    shape       VARCHAR(64) NOT NULL,

    PRIMARY KEY (movement_id)
);

