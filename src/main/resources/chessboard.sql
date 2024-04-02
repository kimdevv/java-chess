USE chess;

CREATE TABLE game
(
    id          BIGINT      NOT NULL AUTO_INCREMENT,
    game_status VARCHAR(10) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE board
(
    id       BIGINT  NOT NULL AUTO_INCREMENT,
    game_id  BIGINT  NOT NULL,
    position CHAR(2) NOT NULL,
    piece_id TINYINT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE piece
(
    id   TINYINT    NOT NULL AUTO_INCREMENT,
    type VARCHAR(6) NOT NULL,
    team CHAR(5)    NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT type_with_team UNIQUE (type, team)
);

INSERT INTO game (game_status)
VALUES ('WHITE_TURN');

INSERT INTO piece (type, team)
VALUES ('KING', 'WHITE'),
       ('KING', 'BLACK'),
       ('QUEEN', 'WHITE'),
       ('QUEEN', 'BLACK'),
       ('ROOK', 'WHITE'),
       ('ROOK', 'BLACK'),
       ('BISHOP', 'WHITE'),
       ('BISHOP', 'BLACK'),
       ('KNIGHT', 'WHITE'),
       ('KNIGHT', 'BLACK'),
       ('PAWN', 'WHITE'),
       ('PAWN', 'BLACK');
