USE chess;

create table board
(
    board_file varchar(1) not null,
    board_rank varchar(1) not null,
    color      varchar(5) not null,
    type       varchar(6) not null,
    primary key (board_file, board_rank)
);

create table turns
(
    turn varchar(5) not null,
    primary key (turn)
);
