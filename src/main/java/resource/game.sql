use chess;

CREATE TABLE `game` (
    `gameId` int NOT NULL AUTO_INCREMENT,
    `turn` varchar(10) DEFAULT NULL,
    PRIMARY KEY (`gameId`)
);
