SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `Project` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `Project` ;

-- -----------------------------------------------------
-- Table `Project`.`Team`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Project`.`Team` (
  `idTeam` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NULL,
  `description` VARCHAR(300) NULL,
  PRIMARY KEY (`idTeam`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Project`.`Project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Project`.`Project` (
  `idProject` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NULL,
  `description` VARCHAR(300) NULL,
  `dateBegin` DATE NULL,
  `dateEnd` DATE NULL,
  `iterationCycle` INT NULL,
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
-- Table `Project`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Project`.`User` (
  `idUser` VARCHAR(20) NOT NULL,
  `eMail` VARCHAR(50) NULL,
  `firstName` VARCHAR(50) NOT NULL,
  `lastName` VARCHAR(50) NOT NULL,
  `pwHash` VARCHAR(80) NOT NULL,
  `active` TINYINT(1) NOT NULL,
  `idTeam` INT UNSIGNED NULL,
  `prefProject` INT UNSIGNED NULL,
  PRIMARY KEY (`idUser`),
  INDEX `fk_User_Team1_idx` (`idTeam` ASC),
  UNIQUE INDEX `eMail_UNIQUE` (`eMail` ASC),
  INDEX `fk_User_Project1_idx` (`prefProject` ASC),
  CONSTRAINT `fk_User_Team1`
    FOREIGN KEY (`idTeam`)
    REFERENCES `Project`.`Team` (`idTeam`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_Project1`
    FOREIGN KEY (`prefProject`)
    REFERENCES `Project`.`Project` (`idProject`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Project`.`Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Project`.`Role` (
  `idRole` VARCHAR(10) NOT NULL,
  `title` VARCHAR(100) NULL,
  PRIMARY KEY (`idRole`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Project`.`User_Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Project`.`User_Role` (
  `idRole` VARCHAR(10) NOT NULL,
  `idUser` VARCHAR(20) NOT NULL,
  INDEX `fk_Role_Permission1_idx` (`idRole` ASC),
  INDEX `fk_Role_User1_idx` (`idUser` ASC),
  PRIMARY KEY (`idRole`, `idUser`),
  CONSTRAINT `fk_Role_Permission1`
    FOREIGN KEY (`idRole`)
    REFERENCES `Project`.`Role` (`idRole`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Role_User1`
    FOREIGN KEY (`idUser`)
    REFERENCES `Project`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Project`.`Iteration`
-- -----------------------------------------------------
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
CREATE TABLE IF NOT EXISTS `Project`.`WorkItem` (
  `idWorkItem` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NULL,
  `description` VARCHAR(300) NULL,
  `type` TINYINT UNSIGNED NULL,
  `sizeEstimate` TINYINT NULL,
  `priority` TINYINT NULL,
  `idProject` INT UNSIGNED NOT NULL,
  `targetIteration` INT UNSIGNED NULL,
  `idLowWorkitem` INT UNSIGNED NULL,
  PRIMARY KEY (`idWorkItem`),
  INDEX `fk_WorkItem_Project1_idx` (`idProject` ASC),
  INDEX `fk_WorkItem_Iteration1_idx` (`targetIteration` ASC),
  INDEX `fk_WorkItem_WorkItem1_idx` (`idLowWorkitem` ASC),
  CONSTRAINT `fk_WorkItem_Project1`
    FOREIGN KEY (`idProject`)
    REFERENCES `Project`.`Project` (`idProject`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_WorkItem_Iteration1`
    FOREIGN KEY (`targetIteration`)
    REFERENCES `Project`.`Iteration` (`idIteration`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_WorkItem_WorkItem1`
    FOREIGN KEY (`idLowWorkitem`)
    REFERENCES `Project`.`WorkItem` (`idWorkItem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Project`.`Task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Project`.`Task` (
  `idTask` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NULL,
  `description` VARCHAR(300) NULL,
  `priority` TINYINT NULL,
  `resolved` TINYINT(1) NULL,
  `hoursPlanned` DECIMAL(3,2) NULL,
  `hoursWorked` DECIMAL(3,2) NULL,
  `hoursRemaining` DECIMAL(3,2) NULL,
  `AssignedTo` VARCHAR(20) NOT NULL,
  `idIteration` INT UNSIGNED NOT NULL,
  `motherTask` INT UNSIGNED NULL,
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
-- Table `Project`.`OpenUpRole`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Project`.`OpenUpRole` (
  `idOpenUpRole` VARCHAR(10) NOT NULL,
  `title` VARCHAR(100) NULL,
  PRIMARY KEY (`idOpenUpRole`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Project`.`User_Project_Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Project`.`User_Project_Role` (
  `idOpenUpRole` VARCHAR(10) NOT NULL,
  `idProject` INT UNSIGNED NOT NULL,
  `idUser` VARCHAR(20) NOT NULL,
  INDEX `fk_User_Project_Role_Project1_idx` (`idProject` ASC),
  INDEX `fk_User_Project_Role_User1_idx` (`idUser` ASC),
  PRIMARY KEY (`idOpenUpRole`, `idProject`, `idUser`),
  CONSTRAINT `fk_table1_OpenUpRole1`
    FOREIGN KEY (`idOpenUpRole`)
    REFERENCES `Project`.`OpenUpRole` (`idOpenUpRole`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_Project_Role_Project1`
    FOREIGN KEY (`idProject`)
    REFERENCES `Project`.`Project` (`idProject`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_Project_Role_User1`
    FOREIGN KEY (`idUser`)
    REFERENCES `Project`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
