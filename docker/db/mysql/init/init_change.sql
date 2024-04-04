CREATE TABLE chessBoard
(
    id             int        not null,
    camp           varchar(5) not null,
    lettering      int        not null,
    numbering      int        not null,
    chessPieceType varchar(6) not null
);

CREATE TABLE turn
(
    camp          varchar(5) not null,
    FOREIGN KEY (chessBoard_id) REFERENCES chessBoard (id),
    chessBoard_id int        not null
);

CREATE TABLE user
(
    id   int         not null,
    name varchar(10) not null
);

CREATE TABLE chessGameInfo
(
    FOREIGN KEY (user_id) REFERENCES user (id),
    user_id       int        not null,
    FOREIGN KEY (chessBoard_id) REFERENCES chessBoard (id),
    chessBoard_id int        not null,
    camp          varchar(5) not null
);
