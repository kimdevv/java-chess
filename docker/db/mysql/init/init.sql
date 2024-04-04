CREATE TABLE chessBoard
(
    camp           varchar(5) not null,
    lettering      int        not null,
    numbering      int        not null,
    chessPieceType varchar(6) not null
);

CREATE TABLE turn
(
    camp varchar(5) not null primary key
)
