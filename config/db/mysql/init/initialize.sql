CREATE DATABASE chess DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE chess;

CREATE TABLE `file` (
  `file` CHAR(1) NOT NULL,
  PRIMARY KEY (`file`)
);

CREATE TABLE `rank` (
  `rank` CHAR(1) NOT NULL,
  PRIMARY KEY (`rank`)
);

CREATE TABLE `color` (
  `color` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`color`)
);

CREATE TABLE `piece_type` (
  `piece_type` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`piece_type`)
);

CREATE TABLE `board` (
  `file` CHAR(1) NOT NULL,
  `rank` CHAR(1) NOT NULL,
  `color` VARCHAR(5) NOT NULL,
  `piece_type` VARCHAR(15) NOT NULL,
  FOREIGN KEY (`file`) REFERENCES `file` (`file`),
  FOREIGN KEY (`rank`) REFERENCES `rank` (`rank`),
  FOREIGN KEY (`color`) REFERENCES `color` (`color`),
  FOREIGN KEY (`piece_type`) REFERENCES `piece_type` (`piece_type`)
);

CREATE TABLE `current_turn_color` (
  `color` VARCHAR(5) NOT NULL,
  FOREIGN KEY (`color`) REFERENCES `color` (`color`)
);

INSERT INTO `file` (`file`) VALUES ('a'), ('b'), ('c'), ('d'), ('e'), ('f'), ('g'), ('h');
INSERT INTO `rank` (`rank`) VALUES ('1'), ('2'), ('3'), ('4'), ('5'), ('6'), ('7'), ('8');
INSERT INTO `color` (`color`) VALUES ('black'), ('white');
INSERT INTO `piece_type` (`piece_type`) VALUES ('king'), ('queen'), ('rook'), ('bishop'), ('knight'), ('black_pawn'), ('white_pawn');
