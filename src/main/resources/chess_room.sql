USE chess;

CREATE TABLE chess_room (
    id BIGINT NOT NULL,
    turn VARCHAR(10) NOT NULL,

    PRIMARY KEY (id)
);
