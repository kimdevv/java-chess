USE chess;

CREATE TABLE IF NOT EXISTS chess_game
(
    id         BIGINT NOT NULL AUTO_INCREMENT,
    room_name  VARCHAR(20) UNIQUE,
    is_running BOOLEAN,
    turn       VARCHAR(5),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS piece
(
    id         BIGINT NOT NULL AUTO_INCREMENT,
    game_id    BIGINT NOT NULL,
    color      VARCHAR(5),
    piece_type VARCHAR(10),
    `row`      VARCHAR(1),
    `column`   VARCHAR(1),
    PRIMARY KEY (id),
    FOREIGN KEY (game_id) references chess_game (id)
);
