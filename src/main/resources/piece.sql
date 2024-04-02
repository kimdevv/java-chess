USE chess;

CREATE TABLE piece (
    id BIGINT NOT NULL AUTO_INCREMENT,
    chess_room_id BIGINT NOT NULL,
    position VARCHAR(2) NOT NULL,
    type VARCHAR(30) NOT NULL,
    team VARCHAR(10) NOT NULL,

    PRIMARY KEY (id)
);
