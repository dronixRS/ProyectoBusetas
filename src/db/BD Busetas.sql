-- MySQL Script generated by MySQL Workbench
-- 08/22/18 16:54:15
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema transporte
-- -----------------------------------------------------
-- Base de datos transporte escolar para mejorar el control de las rutas de la institución.

-- -----------------------------------------------------
-- Schema transporte
--
-- Base de datos transporte escolar para mejorar el control de las rutas de la institución.
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `transporte` DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci ;
USE `transporte` ;

-- -----------------------------------------------------
-- Table `transporte`.`funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transporte`.`funcionario` (
  `id_func` VARCHAR(15) NOT NULL COMMENT '',
  `nombre_func` VARCHAR(45) NULL COMMENT '',
  `apellido_func` VARCHAR(45) NULL COMMENT '',
  `celular_func` VARCHAR(15) NULL COMMENT '',
  `correo_func` VARCHAR(45) NULL COMMENT '',
  `direccion_func` VARCHAR(45) NULL COMMENT '',
  `usuario_func` VARCHAR(45) NULL COMMENT '',
  `clave_func` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`id_func`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transporte`.`aseguradora`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transporte`.`aseguradora` (
  `codigo_aseg` INT NOT NULL COMMENT '',
  `nombre_aseg` VARCHAR(45) NULL COMMENT '',
  `id_func` VARCHAR(15) NULL COMMENT '',
  PRIMARY KEY (`codigo_aseg`)  COMMENT '',
  INDEX `id_func_idx` (`id_func` ASC)  COMMENT '',
  CONSTRAINT `id_func`
    FOREIGN KEY (`id_func`)
    REFERENCES `transporte`.`funcionario` (`id_func`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transporte`.`asistente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transporte`.`asistente` (
  `id_asis` VARCHAR(15) NOT NULL COMMENT '',
  `nombre_asis` VARCHAR(45) NULL COMMENT '',
  `apellido_asis` VARCHAR(45) NULL COMMENT '',
  `celular_asis` VARCHAR(15) NULL COMMENT '',
  `correo_asis` VARCHAR(45) NULL COMMENT '',
  `direccion_asis` VARCHAR(45) NULL COMMENT '',
  `ruta_foto_asis` VARCHAR(45) NULL COMMENT '',
  `id_func` VARCHAR(15) NULL COMMENT '',
  PRIMARY KEY (`id_asis`)  COMMENT '',
  INDEX `id_func_asis_idx` (`id_func` ASC)  COMMENT '',
  CONSTRAINT `id_func_asis`
    FOREIGN KEY (`id_func`)
    REFERENCES `transporte`.`funcionario` (`id_func`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transporte`.`estudiante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transporte`.`estudiante` (
  `codigo_est` VARCHAR(10) NOT NULL COMMENT '',
  `id_est` VARCHAR(15) NULL COMMENT '',
  `nombre_est` VARCHAR(45) NULL COMMENT '',
  `apellido_est` VARCHAR(45) NULL COMMENT '',
  `celular_est` VARCHAR(15) NULL COMMENT '',
  `correo_est` VARCHAR(45) NULL COMMENT '',
  `direccion_est` VARCHAR(45) NULL COMMENT '',
  `grado_est` VARCHAR(4) NULL COMMENT '',
  `ciudad_est` VARCHAR(45) NULL COMMENT '',
  `acudiente_est` VARCHAR(100) NULL COMMENT '',
  `celular_acu_est` VARCHAR(15) NULL COMMENT '',
  `correo_acu_est` VARCHAR(45) NULL COMMENT '',
  `direccion_acu_est` VARCHAR(45) NULL COMMENT '',
  `id_func` VARCHAR(15) NULL COMMENT '',
  PRIMARY KEY (`codigo_est`)  COMMENT '',
  INDEX `id_func_est_idx` (`id_func` ASC)  COMMENT '',
  CONSTRAINT `id_func_est`
    FOREIGN KEY (`id_func`)
    REFERENCES `transporte`.`funcionario` (`id_func`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transporte`.`soat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transporte`.`soat` (
  `codigo_soat` VARCHAR(30) NOT NULL COMMENT '',
  `codigo_aseg_soat` INT NULL COMMENT '',
  `fecha_soat` DATE NULL COMMENT '',
  `fecha_vigencia_soat` DATE NULL COMMENT '',
  `ruta_foto_soat` VARCHAR(100) NULL COMMENT '',
  `id_func` VARCHAR(15) NULL COMMENT '',
  PRIMARY KEY (`codigo_soat`)  COMMENT '',
  INDEX `codigo_aseg_soat_idx` (`codigo_aseg_soat` ASC)  COMMENT '',
  INDEX `id_func_soat_idx` (`id_func` ASC)  COMMENT '',
  CONSTRAINT `codigo_aseg_soat`
    FOREIGN KEY (`codigo_aseg_soat`)
    REFERENCES `transporte`.`aseguradora` (`codigo_aseg`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_func_soat`
    FOREIGN KEY (`id_func`)
    REFERENCES `transporte`.`funcionario` (`id_func`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transporte`.`conductor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transporte`.`conductor` (
  `id_cond` VARCHAR(15) NOT NULL COMMENT '',
  `nombre_cond` VARCHAR(45) NULL COMMENT '',
  `apellido_cond` VARCHAR(45) NULL COMMENT '',
  `ruta_foto_cond` VARCHAR(100) NULL COMMENT '',
  `celular_cond` VARCHAR(15) NULL COMMENT '',
  `correo_cond` VARCHAR(45) NULL COMMENT '',
  `direccion_cond` VARCHAR(45) NULL COMMENT '',
  `categoria_licencia` VARCHAR(4) NULL COMMENT '',
  `vigencia_licencia` DATE NULL COMMENT '',
  `ciudad_licencia` VARCHAR(45) NULL COMMENT '',
  `restriccion_licencia` VARCHAR(100) NULL COMMENT '',
  `ruta_foto_licencia` VARCHAR(100) NULL COMMENT '',
  `id_func` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`id_cond`)  COMMENT '',
  INDEX `id_func_cond_idx` (`id_func` ASC)  COMMENT '',
  CONSTRAINT `id_func_cond`
    FOREIGN KEY (`id_func`)
    REFERENCES `transporte`.`funcionario` (`id_func`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transporte`.`tecnomecanica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transporte`.`tecnomecanica` (
  `codigo_tecno` VARCHAR(30) NOT NULL COMMENT '',
  `centro_diagnostico_tecno` VARCHAR(60) NULL COMMENT '',
  `fecha_tecno` DATE NULL COMMENT '',
  `fecha_vigencia_tecno` DATE NULL COMMENT '',
  `ruta_foto_tecno` VARCHAR(100) NULL COMMENT '',
  `id_func` VARCHAR(15) NULL COMMENT '',
  PRIMARY KEY (`codigo_tecno`)  COMMENT '',
  INDEX `id_func_tecno_idx` (`id_func` ASC)  COMMENT '',
  CONSTRAINT `id_func_tecno`
    FOREIGN KEY (`id_func`)
    REFERENCES `transporte`.`funcionario` (`id_func`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transporte`.`vehiculo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transporte`.`vehiculo` (
  `placa_veh` VARCHAR(7) NOT NULL COMMENT '',
  `numero_veh` INT NULL COMMENT '',
  `modelo_veh` INT NULL COMMENT '',
  `tipo_veh` VARCHAR(45) NULL COMMENT '',
  `capacidad_veh` INT NULL COMMENT '',
  `empresa_veh` VARCHAR(45) NULL COMMENT '',
  `marca_veh` VARCHAR(45) NULL COMMENT '',
  `ruta_foto_veh` VARCHAR(100) NULL COMMENT '',
  `id_cond` VARCHAR(15) NULL COMMENT '',
  `id_asis` VARCHAR(15) NULL COMMENT '',
  `cod_soat` VARCHAR(30) NULL COMMENT '',
  `cod_tecno` VARCHAR(30) NULL COMMENT '',
  `id_func` VARCHAR(15) NULL COMMENT '',
  PRIMARY KEY (`placa_veh`)  COMMENT '',
  INDEX `id_func_veh_idx` (`id_func` ASC)  COMMENT '',
  INDEX `id_cond_veh_idx` (`id_cond` ASC)  COMMENT '',
  INDEX `id_asis_veh_idx` (`id_asis` ASC)  COMMENT '',
  INDEX `cod_soat_veh_idx` (`cod_soat` ASC)  COMMENT '',
  INDEX `cod_tecno_veh_idx` (`cod_tecno` ASC)  COMMENT '',
  CONSTRAINT `id_func_veh`
    FOREIGN KEY (`id_func`)
    REFERENCES `transporte`.`funcionario` (`id_func`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_cond_veh`
    FOREIGN KEY (`id_cond`)
    REFERENCES `transporte`.`conductor` (`id_cond`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_asis_veh`
    FOREIGN KEY (`id_asis`)
    REFERENCES `transporte`.`asistente` (`id_asis`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `cod_soat_veh`
    FOREIGN KEY (`cod_soat`)
    REFERENCES `transporte`.`soat` (`codigo_soat`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `cod_tecno_veh`
    FOREIGN KEY (`cod_tecno`)
    REFERENCES `transporte`.`tecnomecanica` (`codigo_tecno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transporte`.`ruta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transporte`.`ruta` (
  `codigo_ruta` VARCHAR(4) NOT NULL COMMENT '',
  `periodo_ruta` INT NULL COMMENT '',
  `zona_ruta` VARCHAR(20) NULL COMMENT '',
  `placa_veh` VARCHAR(7) NULL COMMENT '',
  `recorrido_veh` VARCHAR(15) NULL COMMENT '',
  `hora_inicio_ruta` VARCHAR(10) NULL COMMENT '',
  `hora_final_ruta` VARCHAR(10) NULL COMMENT '',
  `cupos_asignados_ruta` INT NULL COMMENT '',
  `cupos_disponibles_ruta` INT NULL COMMENT '',
  `tiempo_recorrido_ruta` VARCHAR(10) NULL COMMENT '',
  `num_paradas_ruta` INT NULL COMMENT '',
  `id_func` VARCHAR(15) NULL COMMENT '',
  PRIMARY KEY (`codigo_ruta`)  COMMENT '',
  INDEX `placa_veh_ruta_idx` (`placa_veh` ASC)  COMMENT '',
  INDEX `id_func_ruta_idx` (`id_func` ASC)  COMMENT '',
  CONSTRAINT `placa_veh_ruta`
    FOREIGN KEY (`placa_veh`)
    REFERENCES `transporte`.`vehiculo` (`placa_veh`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_func_ruta`
    FOREIGN KEY (`id_func`)
    REFERENCES `transporte`.`funcionario` (`id_func`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transporte`.`detalle_ruta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transporte`.`detalle_ruta` (
  `codigo_ruta` VARCHAR(4) NULL COMMENT '',
  `codigo_est` VARCHAR(10) NULL COMMENT '',
  INDEX `cod_ruta_det_idx` (`codigo_ruta` ASC)  COMMENT '',
  INDEX `cod_est_det_idx` (`codigo_est` ASC)  COMMENT '',
  CONSTRAINT `cod_ruta_det`
    FOREIGN KEY (`codigo_ruta`)
    REFERENCES `transporte`.`ruta` (`codigo_ruta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `cod_est_det`
    FOREIGN KEY (`codigo_est`)
    REFERENCES `transporte`.`estudiante` (`codigo_est`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
