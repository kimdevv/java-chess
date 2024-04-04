CREATE TABLE game(
    game_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    moving_color VARCHAR(10) NOT NULL,
    PRIMARY KEY (game_id)
);

CREATE TABLE board(
    coordinate VARCHAR(10) NOT NULL,
    piece_type VARCHAR(10) NOT NULL,
    piece_color VARCHAR(10) NOT NULL,
    PRIMARY KEY (coordinate)
);