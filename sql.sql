CREATE SCHEMA IF NOT EXISTS `projekt2` DEFAULT CHARACTER SET utf8 ;
USE `projekt2` ;

CREATE TABLE `projekt2`.`cvicenie` (
  `uuid` VARCHAR(36) NOT NULL,
  `nazov` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE INDEX `uuid_UNIQUE` (`uuid` ASC));

CREATE TABLE `projekt2`.`student` (
  `uuid` VARCHAR(36) NOT NULL,
  `meno` VARCHAR(45) NOT NULL,
  `priezvisko` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE INDEX `uuid_UNIQUE` (`uuid` ASC));



CREATE TABLE `projekt2`.`prezencka` (
  `uuid` VARCHAR(36) NOT NULL,
  `idCvicenie` VARCHAR(36) NOT NULL,
  `datum` DATETIME NOT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE INDEX `uuid_UNIQUE` (`uuid` ASC));


CREATE TABLE `projekt2`.`studentnaprezencke` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idStudent` VARCHAR(36) NOT NULL,
  `idPrezencka` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));
  
CREATE USER 'klient'@'localhost' IDENTIFIED BY 'kopr';
GRANT ALL PRIVILEGES ON projekt2.* TO 'klient'@'localhost';