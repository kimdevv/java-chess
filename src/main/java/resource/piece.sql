use chess;

CREATE TABLE `piece` (
     `file` int NOT NULL,
     `rank` int NOT NULL,
     `pieceColor` varchar(50) NOT NULL,
     `pieceType` varchar(50) NOT NULL,
     `gameId` int NOT NULL,
     PRIMARY KEY (`file`,`rank`,`gameId`),
     KEY `gameId` (`gameId`),
     CONSTRAINT `gameId` FOREIGN KEY (`gameId`) REFERENCES `game` (`gameId`)
);
