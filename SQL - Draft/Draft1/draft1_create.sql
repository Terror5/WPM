SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `Project` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `Project` ;

-- -----------------------------------------------------
-- Table `Project`.`Team`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Project`.`Team` (
  `idTeam` INT NOT NULL,
  `title` VARCHAR(100) NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`idTeam`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Project`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Project`.`User` (
  `eMail` VARCHAR(50) NOT NULL,
  `firstName` VARCHAR(50) NOT NULL,
  `lastName` VARCHAR(50) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `idTeam` INT NOT NULL,
  PRIMARY KEY (`eMail`),
  INDEX `fk_User_Team1_idx` (`idTeam` ASC),
  CONSTRAINT `fk_User_Team1`
    FOREIGN KEY (`idTeam`)
    REFERENCES `Project`.`Team` (`idTeam`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Project`.`Permission`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Project`.`Permission` (
  `idPermission` INT NOT NULL,
  `createProject` TINYINT(1) NULL,
  `modifyProject` TINYINT(1) NULL,
  `deleteProject` TINYINT(1) NULL,
  `super` TINYINT(1) NULL,
  PRIMARY KEY (`idPermission`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Project`.`Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Project`.`Role` (
  `idRole` INT NOT NULL,
  `nameRole` VARCHAR(100) NULL,
  `idPermission` INT NOT NULL,
  `eMail` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idRole`),
  INDEX `fk_Role_Permission_idx` (`idPermission` ASC),
  INDEX `fk_Role_User1_idx` (`eMail` ASC),
  CONSTRAINT `fk_Role_Permission`
    FOREIGN KEY (`idPermission`)
    REFERENCES `Project`.`Permission` (`idPermission`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Role_User1`
    FOREIGN KEY (`eMail`)
    REFERENCES `Project`.`User` (`eMail`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Project`.`Project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Project`.`Project` (
  `idProject` INT NOT NULL,
  `title` VARCHAR(100) NULL,
  `description` TEXT NULL,
  `idTeam` INT NOT NULL,
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
CREATE TABLE IF NOT EXISTS `Project`.`Iteration` (
  `idIteration` INT NOT NULL,
  `startIteration` DATE NULL,
  `endIteration` DATE NULL,
  `evaluation` TEXT NULL,
  `idProject` INT NOT NULL,
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
  `idWorkItem` INT NOT NULL,
  `title` VARCHAR(100) NULL,
  `description` TEXT NULL,
  `type` TINYINT UNSIGNED NULL,
  `sizeEstimate` TINYINT NULL,
  `priority` TINYINT NULL,
  `Project_idProject` INT NOT NULL,
  `targetIteration` INT NOT NULL,
  PRIMARY KEY (`idWorkItem`),
  INDEX `fk_WorkItem_Project1_idx` (`Project_idProject` ASC),
  INDEX `fk_WorkItem_Iteration1_idx` (`targetIteration` ASC),
  CONSTRAINT `fk_WorkItem_Project1`
    FOREIGN KEY (`Project_idProject`)
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
CREATE TABLE IF NOT EXISTS `Project`.`Task` (
  `idTask` INT NOT NULL,
  `title` VARCHAR(100) NULL,
  `description` TEXT NULL,
  `priority` TINYINT NULL,
  `resolved` TINYINT(1) NULL,
  `hoursPlanned` DECIMAL(3,2) NULL,
  `hoursWorked` DECIMAL(3,2) NULL,
  `AssignedTo` VARCHAR(50) NOT NULL,
  `idIteration` INT NOT NULL,
  `motherTask` INT NOT NULL,
  `WorkItem_idWorkItem` INT NOT NULL,
  PRIMARY KEY (`idTask`),
  INDEX `fk_Task_User1_idx` (`AssignedTo` ASC),
  INDEX `fk_Task_Iteration1_idx` (`idIteration` ASC),
  INDEX `fk_Task_Task1_idx` (`motherTask` ASC),
  INDEX `fk_Task_WorkItem1_idx` (`WorkItem_idWorkItem` ASC),
  CONSTRAINT `fk_Task_User1`
    FOREIGN KEY (`AssignedTo`)
    REFERENCES `Project`.`User` (`eMail`)
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
    FOREIGN KEY (`WorkItem_idWorkItem`)
    REFERENCES `Project`.`WorkItem` (`idWorkItem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Project`.`User_IP`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Project`.`User_IP` (
  `eMail` VARCHAR(50) NOT NULL,
  `ip1` TINYINT UNSIGNED NULL,
  `ip2` TINYINT UNSIGNED NULL,
  `ip3` TINYINT UNSIGNED NULL,
  `ip4` TINYINT UNSIGNED NULL,
  `sub1` TINYINT UNSIGNED NULL,
  `sub2` TINYINT UNSIGNED NULL,
  `sub3` TINYINT UNSIGNED NULL,
  `sub4` TINYINT UNSIGNED NULL,
  `timeStamp` TIME NULL,
  INDEX `fk_User_IP_User1_idx` (`eMail` ASC),
  PRIMARY KEY (`eMail`),
  CONSTRAINT `fk_User_IP_User1`
    FOREIGN KEY (`eMail`)
    REFERENCES `Project`.`User` (`eMail`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
