SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `Project` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `Project` ;

-- -----------------------------------------------------
-- Table `Project`.`Team`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Project`.`Team` ;

CREATE TABLE IF NOT EXISTS `Project`.`Team` (
  `idTeam` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NULL,
  `description` VARCHAR(300) NULL,
  PRIMARY KEY (`idTeam`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Project`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Project`.`User` ;

CREATE TABLE IF NOT EXISTS `Project`.`User` (
  `idUser` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `WinUsername` VARCHAR(8) NOT NULL,
  `eMail` VARCHAR(50) NULL,
  `firstName` VARCHAR(50) NOT NULL,
  `lastName` VARCHAR(50) NOT NULL,
  `pwHash` VARCHAR(100) NOT NULL,
  `idTeam` INT UNSIGNED NULL,
  PRIMARY KEY (`idUser`),
  INDEX `fk_User_Team1_idx` (`idTeam` ASC),
  UNIQUE INDEX `WinUsername_UNIQUE` (`WinUsername` ASC),
  UNIQUE INDEX `eMail_UNIQUE` (`eMail` ASC),
  CONSTRAINT `fk_User_Team1`
    FOREIGN KEY (`idTeam`)
    REFERENCES `Project`.`Team` (`idTeam`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Project`.`Permission`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Project`.`Permission` ;

CREATE TABLE IF NOT EXISTS `Project`.`Permission` (
  `idPermission` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `createProject` TINYINT(1) NULL,
  `modifyProject` TINYINT(1) NULL,
  `deleteProject` TINYINT(1) NULL,
  `super` TINYINT(1) NULL,
  PRIMARY KEY (`idPermission`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Project`.`Role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Project`.`Role` ;

CREATE TABLE IF NOT EXISTS `Project`.`Role` (
  `idRole` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nameRole` VARCHAR(100) NULL,
  `idPermission` INT UNSIGNED NOT NULL,
  `idUser` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idRole`),
  INDEX `fk_Role_Permission_idx` (`idPermission` ASC),
  INDEX `fk_Role_User1_idx` (`idUser` ASC),
  CONSTRAINT `fk_Role_Permission`
    FOREIGN KEY (`idPermission`)
    REFERENCES `Project`.`Permission` (`idPermission`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Role_User1`
    FOREIGN KEY (`idUser`)
    REFERENCES `Project`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Project`.`Project`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Project`.`Project` ;

CREATE TABLE IF NOT EXISTS `Project`.`Project` (
  `idProject` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NULL,
  `description` VARCHAR(300) NULL,
  `idTeam` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idProject`),
  INDEX `fk_Project_Team1_idx` (`idTeam` ASC),
  CONSTRAINT `fk_Project_Team1`
    FOREIGN KEY (`idTeam`)
    REFERENCES `Project`.`Team` (`idTeam`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Project`.`Iteration`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Project`.`Iteration` ;

CREATE TABLE IF NOT EXISTS `Project`.`Iteration` (
  `idIteration` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `startIteration` DATE NULL,
  `endIteration` DATE NULL,
  `evaluation` VARCHAR(300) NULL,
  `idProject` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idIteration`),
  INDEX `fk_Iteration_Project1_idx` (`idProject` ASC),
  CONSTRAINT `fk_Iteration_Project1`
    FOREIGN KEY (`idProject`)
    REFERENCES `Project`.`Project` (`idProject`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Project`.`WorkItem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Project`.`WorkItem` ;

CREATE TABLE IF NOT EXISTS `Project`.`WorkItem` (
  `idWorkItem` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NULL,
  `description` VARCHAR(300) NULL,
  `type` TINYINT UNSIGNED NULL,
  `sizeEstimate` TINYINT NULL,
  `priority` TINYINT NULL,
  `idProject` INT UNSIGNED NOT NULL,
  `targetIteration` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idWorkItem`),
  INDEX `fk_WorkItem_Project1_idx` (`idProject` ASC),
  INDEX `fk_WorkItem_Iteration1_idx` (`targetIteration` ASC),
  CONSTRAINT `fk_WorkItem_Project1`
    FOREIGN KEY (`idProject`)
    REFERENCES `Project`.`Project` (`idProject`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_WorkItem_Iteration1`
    FOREIGN KEY (`targetIteration`)
    REFERENCES `Project`.`Iteration` (`idIteration`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Project`.`Task`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Project`.`Task` ;

CREATE TABLE IF NOT EXISTS `Project`.`Task` (
  `idTask` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NULL,
  `description` VARCHAR(300) NULL,
  `priority` TINYINT NULL,
  `resolved` TINYINT(1) NULL,
  `hoursPlanned` DECIMAL(3,2) NULL,
  `hoursWorked` DECIMAL(3,2) NULL,
  `AssignedTo` INT UNSIGNED NOT NULL,
  `idIteration` INT UNSIGNED NOT NULL,
  `motherTask` INT UNSIGNED NOT NULL,
  `idWorkItem` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idTask`),
  INDEX `fk_Task_User1_idx` (`AssignedTo` ASC),
  INDEX `fk_Task_Iteration1_idx` (`idIteration` ASC),
  INDEX `fk_Task_Task1_idx` (`motherTask` ASC),
  INDEX `fk_Task_WorkItem1_idx` (`idWorkItem` ASC),
  CONSTRAINT `fk_Task_User1`
    FOREIGN KEY (`AssignedTo`)
    REFERENCES `Project`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Task_Iteration1`
    FOREIGN KEY (`idIteration`)
    REFERENCES `Project`.`Iteration` (`idIteration`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Task_Task1`
    FOREIGN KEY (`motherTask`)
    REFERENCES `Project`.`Task` (`idTask`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Task_WorkItem1`
    FOREIGN KEY (`idWorkItem`)
    REFERENCES `Project`.`WorkItem` (`idWorkItem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Project`.`User_IP`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Project`.`User_IP` ;

CREATE TABLE IF NOT EXISTS `Project`.`User_IP` (
  `idUser` INT UNSIGNED NOT NULL,
  `ip1` TINYINT UNSIGNED NULL,
  `ip2` TINYINT UNSIGNED NULL,
  `ip3` TINYINT UNSIGNED NULL,
  `ip4` TINYINT UNSIGNED NULL,
  `sub1` TINYINT UNSIGNED NULL,
  `sub2` TINYINT UNSIGNED NULL,
  `sub3` TINYINT UNSIGNED NULL,
  `sub4` TINYINT UNSIGNED NULL,
  `timeStamp` TIME NULL,
  INDEX `fk_User_IP_User1_idx` (`idUser` ASC),
  PRIMARY KEY (`idUser`),
  CONSTRAINT `fk_User_IP_User1`
    FOREIGN KEY (`idUser`)
    REFERENCES `Project`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
