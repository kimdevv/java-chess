USE chess;

CREATE TABLE chess_game
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    game_status VARCHAR(8) NOT NULL
);

CREATE TABLE move
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    source        VARCHAR(2) NOT NULL,
    target        VARCHAR(2) NOT NULL,
    chess_game_id BIGINT     NOT NULL,
    FOREIGN KEY (chess_game_id) REFERENCES chess_game (id)
);
