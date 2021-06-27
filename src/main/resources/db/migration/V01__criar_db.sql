-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';



-- -----------------------------------------------------
-- Schema gescolar
-- -----------------------------------------------------



CREATE SCHEMA IF NOT EXISTS `gescolar` DEFAULT CHARACTER SET utf8 ;
USE `gescolar` ;

-- -----------------------------------------------------
-- Table `gescolar`.`periodo_letivo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gescolar`.`periodo_letivo` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `data_ini` DATE NOT NULL,
  `data_fim` DATE NOT NULL,
  `situacao` VARCHAR(45) NOT NULL,
  `minutos_periodo` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gescolar`.`turma`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gescolar`.`turma` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `sala` VARCHAR(45) NULL DEFAULT NULL,
  `serie` VARCHAR(45) NULL DEFAULT NULL,
  `turno` VARCHAR(45) NULL DEFAULT NULL,
  `quant_dias_semana` INT(11) NULL DEFAULT NULL,
  `codigo_periodo_letivo` INT(11),
  `vagas` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_turma_periodo_letivo1_idx` (`codigo_periodo_letivo` ASC),
  CONSTRAINT `fk_turma_periodo_letivo1`
    FOREIGN KEY (`codigo_periodo_letivo`)
    REFERENCES `gescolar`.`periodo_letivo` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gescolar`.`tipo_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gescolar`.`tipo_usuario` (
  `codigo` INT(11) NOT NULL,
  `desc_tipo_usuario` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


CREATE TABLE IF NOT EXISTS gescolar.permissao (
	codigo INT(11) PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS gescolar.tipo_usuario_permissao (
	codigo_tipo_usuario INT(11) NOT NULL,
	codigo_permissao INT(11) NOT NULL,
	PRIMARY KEY (codigo_tipo_usuario, codigo_permissao),
	FOREIGN KEY (codigo_tipo_usuario) REFERENCES tipo_usuario(codigo),
	FOREIGN KEY (codigo_permissao) REFERENCES permissao(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- -----------------------------------------------------
-- Table `gescolar`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gescolar`.`usuario` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NULL DEFAULT NULL,
  `senha` VARCHAR(300) NULL DEFAULT NULL,
  `codigo_tipo_usuario` INT(11) NOT NULL,
  `device_id` VARCHAR(300) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  INDEX `fk_usuario_tipo_usuario1_idx` (`codigo_tipo_usuario` ASC),
  CONSTRAINT `fk_usuario_tipo_usuario1`
    FOREIGN KEY (`codigo_tipo_usuario`)
    REFERENCES `gescolar`.`tipo_usuario` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gescolar`.`aluno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gescolar`.`aluno` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NULL DEFAULT NULL,
  `foto` VARCHAR(1000) NULL DEFAULT NULL,
  `matricula` VARCHAR(50) NULL DEFAULT NULL,
  `sexo` VARCHAR(2) NULL DEFAULT NULL,
  `codigo_usuario` INT(11) NOT NULL,
  `codigo_turma` INT(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_aluno_usuario_idx` (`codigo_usuario` ASC),
  INDEX `fk_aluno_turma1_idx` (`codigo_turma` ASC),
  CONSTRAINT `fk_aluno_turma1`
    FOREIGN KEY (`codigo_turma`)
    REFERENCES `gescolar`.`turma` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_aluno_usuario`
    FOREIGN KEY (`codigo_usuario`)
    REFERENCES `gescolar`.`usuario` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;





-- -----------------------------------------------------
-- Table `gescolar`.`mensagem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gescolar`.`mensagem` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(100) NULL DEFAULT NULL,
  `mensagem` VARCHAR(1000) NULL DEFAULT NULL,
  `data_cadastro` TIMESTAMP NOT NULL,
  `codigo_usuario_from` INT(11) NOT NULL,
  `codigo_usuario_to` INT(11) NOT NULL,
  `notificar` TINYINT(1),
  PRIMARY KEY (`codigo`),
  INDEX `fk_codigo_usuario_from_idx` (`codigo_usuario_from` ASC),
  INDEX `fk_codigo_usuario_to_idx` (`codigo_usuario_to` ASC),
  CONSTRAINT `fk_codigo_usuario_from`
    FOREIGN KEY (`codigo_usuario_from`)
    REFERENCES `gescolar`.`usuario` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_codigo_usuario_to`
    FOREIGN KEY (`codigo_usuario_to`)
    REFERENCES `gescolar`.`usuario` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;




-- -----------------------------------------------------
-- Table `gescolar`.`disciplina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gescolar`.`disciplina` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(300) NULL DEFAULT NULL,
  `nome` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gescolar`.`professor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gescolar`.`professor` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `cpf` VARCHAR(50) NOT NULL,
  `celular` VARCHAR(50) NULL DEFAULT NULL,
  `foto` VARCHAR(1000) NULL DEFAULT NULL,
  `email` VARCHAR(50) NOT NULL,
  `telefone` VARCHAR(50) NULL DEFAULT NULL,
  `sexo` VARCHAR(2) NULL DEFAULT NULL,
  `codigo_usuario` INT(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_professor_usuario1_idx` (`codigo_usuario` ASC),
  CONSTRAINT `fk_professor_usuario1`
    FOREIGN KEY (`codigo_usuario`)
    REFERENCES `gescolar`.`usuario` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gescolar`.`disciplina_turma`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gescolar`.`disciplina_turma` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `codigo_turma` INT(11) NOT NULL,
  `codigo_professor` INT(11) NOT NULL,
  `codigo_disciplina` INT(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_disciplina_turma_turma1_idx` (`codigo_turma` ASC),
  INDEX `fk_disciplina_turma_professor1_idx` (`codigo_professor` ASC),
  INDEX `fk_disciplina_turma_disciplina1_idx` (`codigo_disciplina` ASC),
  CONSTRAINT `fk_disciplina_turma_disciplina1`
    FOREIGN KEY (`codigo_disciplina`)
    REFERENCES `gescolar`.`disciplina` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_disciplina_turma_professor1`
    FOREIGN KEY (`codigo_professor`)
    REFERENCES `gescolar`.`professor` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_disciplina_turma_turma1`
    FOREIGN KEY (`codigo_turma`)
    REFERENCES `gescolar`.`turma` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gescolar`.`avaliacao_disciplina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gescolar`.`avaliacao_disciplina` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `peso` DECIMAL(1,1) NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `descriacao` VARCHAR(45) NOT NULL,
  `codigo_disciplina_turma` INT(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_avaliacao_disciplina_disciplina_turma1_idx` (`codigo_disciplina_turma` ASC),
  CONSTRAINT `fk_avaliacao_disciplina_disciplina_turma1`
    FOREIGN KEY (`codigo_disciplina_turma`)
    REFERENCES `gescolar`.`disciplina_turma` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gescolar`.`boletim`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gescolar`.`boletim` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `nota` DOUBLE NULL DEFAULT NULL,
  `bi_tri` VARCHAR(45) NULL DEFAULT NULL,
  `codigo_aluno` INT(11) NOT NULL,
  `codigo_disciplina_turma` INT(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_boletim_aluno1_idx` (`codigo_aluno` ASC),
  INDEX `fk_boletim_disciplina_turma1_idx` (`codigo_disciplina_turma` ASC),
  CONSTRAINT `fk_boletim_aluno1`
    FOREIGN KEY (`codigo_aluno`)
    REFERENCES `gescolar`.`aluno` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_boletim_disciplina_turma1`
    FOREIGN KEY (`codigo_disciplina_turma`)
    REFERENCES `gescolar`.`disciplina_turma` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gescolar`.`chamada`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gescolar`.`chamada` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `data_chamada` DATE NOT NULL,
  `data_inclusao` DATE NOT NULL,
  `codigo_turma_periodo` INT(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_chamada_codigo_turma_idx` (`codigo_turma_periodo` ASC),
  CONSTRAINT `fk_chamada_codigo_turma`
    FOREIGN KEY (`codigo_turma_periodo`)
    REFERENCES `gescolar`.`turma_periodo` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gescolar`.`nota_aulo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gescolar`.`nota_aulo` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `nota` DECIMAL(2,2) NOT NULL,
  `codigo_avaliacao_disciplina` INT(11) NOT NULL,
  `codigo_aluno` INT(11) NOT NULL,
  PRIMARY KEY (`codigo`, `codigo_aluno`),
  INDEX `fk_nota_aulo_avaliacao_disciplina1_idx` (`codigo_avaliacao_disciplina` ASC),
  INDEX `fk_nota_aulo_aluno1_idx` (`codigo_aluno` ASC),
  CONSTRAINT `fk_nota_aulo_aluno1`
    FOREIGN KEY (`codigo_aluno`)
    REFERENCES `gescolar`.`aluno` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_nota_aulo_avaliacao_disciplina1`
    FOREIGN KEY (`codigo_avaliacao_disciplina`)
    REFERENCES `gescolar`.`avaliacao_disciplina` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gescolar`.`responsavel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gescolar`.`responsavel` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `cpf` VARCHAR(50) NOT NULL,
  `parentesco` VARCHAR(50) NULL DEFAULT NULL,
  `telefone` VARCHAR(50) NULL DEFAULT NULL,
  `celular` VARCHAR(50) NULL DEFAULT NULL,
  `sexo` VARCHAR(1) NULL DEFAULT NULL,
  `email` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gescolar`.`responsavel_aluno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gescolar`.`responsavel_aluno` (
  `codigo_aluno` INT(11) NOT NULL AUTO_INCREMENT,
  `codigo_responsavel` INT(11) NOT NULL,
  PRIMARY KEY (`codigo_aluno`, `codigo_responsavel`),
  INDEX `fk_aluno_has_responsavel_responsavel1_idx` (`codigo_responsavel` ASC),
  INDEX `fk_aluno_has_responsavel_aluno1_idx` (`codigo_aluno` ASC),
  CONSTRAINT `fk_aluno_has_responsavel_aluno1`
    FOREIGN KEY (`codigo_aluno`)
    REFERENCES `gescolar`.`aluno` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_aluno_has_responsavel_responsavel1`
    FOREIGN KEY (`codigo_responsavel`)
    REFERENCES `gescolar`.`responsavel` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;



-- -----------------------------------------------------
-- Table `gescolar`.`CHAMADA_ALUNO`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `gescolar`.`chamada_aluno` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `codigo_chamada` INT(11) NOT NULL,
  `codigo_aluno` INT(11) NULL,
  `presenca` TINYINT(1) NOT NULL,
  `notificado` TINYINT(1),
  PRIMARY KEY (`codigo`),
  INDEX `fk_turma_codigo_idx` (`codigo_chamada` ASC),
  INDEX `fk_aluno_codigo_idx` (`codigo_aluno` ASC),
  CONSTRAINT `fk_chamada_codigo`
    FOREIGN KEY (`codigo_chamada`)
    REFERENCES `gescolar`.`chamada` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_aluno_codigo`
    FOREIGN KEY (`codigo_aluno`)
    REFERENCES `gescolar`.`aluno` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `gescolar`.`TURMA_PERIODO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gescolar`.`turma_periodo` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `dia` VARCHAR(45) NULL,
  `periodo` VARCHAR(45) NULL,
  `turma_codigo` INT(11) NOT NULL,
  `codigo_disciplina_turma` INT(11) NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_TURMA_PERIODO_turma1_idx` (`turma_codigo` ASC),
  INDEX `fk_TURMA_PERIODO_disciplina_turma1_idx` (`codigo_disciplina_turma` ASC),
  CONSTRAINT `fk_TURMA_PERIODO_turma1`
    FOREIGN KEY (`turma_codigo`)
    REFERENCES `gescolar`.`turma` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TURMA_PERIODO_disciplina_turma1`
    FOREIGN KEY (`codigo_disciplina_turma`)
    REFERENCES `gescolar`.`disciplina_turma` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `gescolar`.`notificacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gescolar`.`notificacao` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `codigo_mensagem` INT(11) NOT NULL,
  `data_notificacao` TIMESTAMP NULL DEFAULT NULL,
  `notificado` TINYINT(1),
  `error` TINYINT(1),
  `msg_erro` VARCHAR(1000) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_codigo_mensagem_idx` (`codigo_mensagem` ASC),
  CONSTRAINT `fk_codigo_mensagem`
    FOREIGN KEY (`codigo_mensagem`)
    REFERENCES `gescolar`.`mensagem` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `gescolar`.`evento` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(100) NOT NULL,
  `dt_inicial` DATETIME NOT NULL,
  `dt_final` DATETIME NULL,
  `tipo_evento` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`codigo`)
 )
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gescolar`.`calendario_turma`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gescolar`.`calendario_turma` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `codigo_evento` INT(11) NOT NULL,
  `codigo_turma` INT(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_codigo_evento_idx` (`codigo_evento` ASC),
  INDEX `fk_turma_codigo_idx` (`codigo_turma` ASC),
   CONSTRAINT `fk_TURMA_calendario_turma_1`
    FOREIGN KEY (`codigo_turma`)
    REFERENCES `gescolar`.`turma` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_calendario_turma_evento`
    FOREIGN KEY (`codigo_evento`)
    REFERENCES `gescolar`.`evento` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
 )
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gescolar`.`calendario_professor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gescolar`.`calendario_professor` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `codigo_evento` INT(11) NULL DEFAULT NULL,
  `codigo_professor` INT(11) NULL DEFAULT NULL,
 PRIMARY KEY (`codigo`),
  INDEX `fk_calendario_prof_usuario_idx` (`codigo_professor` ASC),
  INDEX `fk_codigo_evento_codigo_evento_idx` (`codigo_evento` ASC),
  CONSTRAINT `fk_codigo_evento_cal`
    FOREIGN KEY (`codigo_evento`)
    REFERENCES `gescolar`.`evento` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_aluno_usuario_cal`
    FOREIGN KEY (`codigo_professor`)
    REFERENCES `gescolar`.`professor` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;



-- -----------------------------------------------------
-- Table `gescolar`.`cnab`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gescolar`.`cnab` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `tipo_pessoa` VARCHAR(100) NOT NULL,
  `cpf_cnpj` VARCHAR(100) NOT NULL,
  `agencia` VARCHAR(100) NOT NULL,	
  `conta` VARCHAR(100) NOT NULL,	
  `nome` VARCHAR(100) NOT NULL,
  `endereco` VARCHAR(100) NOT NULL,
  `cep` VARCHAR(100) NOT NULL,
  `cidade` VARCHAR(100) NOT NULL,
  `uf` VARCHAR(100) NOT NULL,
  `digito_conta` VARCHAR(100) NOT NULL,
  `posto` VARCHAR(100) NOT NULL,
  `seq_nosso_numero` INT(8) NOT NULL,
  `seq_seu_numero` INT(8) NOT NULL,
  `local_pagamento_boleto` VARCHAR(100) NULL DEFAULT NULL,
  `instrucao_boleto` VARCHAR(100) NULL DEFAULT NULL,
PRIMARY KEY (`codigo`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;




-- -----------------------------------------------------
-- Table `gescolar`.`contrato`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gescolar`.`contrato` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `codigo_matricula` INT(11) NULL DEFAULT NULL,
  `data_ini` DATE NOT NULL,
  `data_fim` DATE NOT NULL,
  `ativo` TINYINT(1),
  `nr_parcela` INT(6),
  `nr_dia_pagamento` INT(6),
  `nr_dia_vencimento` INT(6),
  `valor` DECIMAL(6,2),
  `valor_juros` DECIMAL(6,2),
  `contrato` LONGBLOB,
  `arquivo_cnab` LONGTEXT,
  `status_arquivo_cnab` VARCHAR(100) NULL DEFAULT NULL,
  `data_arquivo_cnab` DATE NOT NULL,
  `nome_arquivo_cnab` VARCHAR(100) NULL DEFAULT NULL,
 PRIMARY KEY (`codigo`),
  INDEX `fk_codigo_matricula_idx` (`codigo_matricula` ASC),
  CONSTRAINT `fk_codigo_matricula`
    FOREIGN KEY (`codigo_matricula`)
    REFERENCES `gescolar`.`matricula_ini` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
 )
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gescolar`.`parcela`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gescolar`.`parcela` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `codigo_contrato` INT(11) NULL DEFAULT NULL,
  `data_emisao` DATE NOT NULL,
  `data_vencimento` DATE NOT NULL,
  `status` VARCHAR(100),
  `nr_parcela` INT(6),
  `valor` DECIMAL(6,2),
  `valor_juros` DECIMAL(6,2),
  `boleto` LONGBLOB,
  `nosso_numero` VARCHAR(100) NULL DEFAULT NULL,
  `digito_nosso_numero` VARCHAR(100) NULL DEFAULT NULL,
  `seu_numero` VARCHAR(100) NULL DEFAULT NULL,
  `nome_boleto` VARCHAR(100) NULL DEFAULT NULL,
 PRIMARY KEY (`codigo`),
  INDEX `fk_codigo_contrato_idx` (`codigo_contrato` ASC),
  CONSTRAINT `fk_codigo_contrato`
    FOREIGN KEY (`codigo_contrato`)
    REFERENCES `gescolar`.`contrato` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
 )
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;







-- -----------------------------------------------------
-- Table `gescolar`.`matricula_ini`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gescolar`.`matricula_ini` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NULL DEFAULT NULL, 
  `status` VARCHAR(100) NULL DEFAULT NULL,	
  `grupo_etnico` VARCHAR(300) NULL DEFAULT NULL,
  `data_nasc` DATE NULL DEFAULT NULL,
  `naturalidade` VARCHAR(100) NULL DEFAULT NULL,
  `nacionalidade` VARCHAR(100) NULL DEFAULT NULL,
  `certidao_nasc` VARCHAR(100) NULL DEFAULT NULL,
  `certidao_nasc_data_emissao` DATE NULL DEFAULT NULL,
  `rg` VARCHAR(100) NULL DEFAULT NULL,
  `religiao` VARCHAR(100) NULL DEFAULT NULL,
  `pediatra` VARCHAR(100) NULL DEFAULT NULL,
  `pediatra_tel` VARCHAR(100) NULL DEFAULT NULL,
  `convenio` VARCHAR(100) NULL DEFAULT NULL,
  `convenio_tel` VARCHAR(100) NULL DEFAULT NULL,
  `convenio_nr` VARCHAR(100) NULL DEFAULT NULL,
  `sus_nr` VARCHAR(100) NULL DEFAULT NULL,
  alergico_medicamento TINYINT(1),
  alergico_medicamento_qual VARCHAR(100) NULL DEFAULT NULL,
  alergico_alimento TINYINT(1),
  alergico_alimento_qual VARCHAR(100) NULL DEFAULT NULL,
  medicamento_uso_continuo TINYINT(1),
  medicamento_uso_continuo_qual VARCHAR(100) NULL DEFAULT NULL,
  medicamento_uso_continuo_receita TINYINT(1),
  bolsa_familia TINYINT(1),
  transporte VARCHAR(100) NULL DEFAULT NULL,
  
  nome_pai VARCHAR(100) NULL DEFAULT NULL,
  dt_nasc_pai DATE NULL DEFAULT NULL,
  cpf_pai VARCHAR(100) NULL DEFAULT NULL,
  rg_pai VARCHAR(100) NULL DEFAULT NULL,
  grau_instrucao_pai VARCHAR(100) NULL DEFAULT NULL,
  religiao_pai VARCHAR(100) NULL DEFAULT NULL,
  profissao_pai VARCHAR(100) NULL DEFAULT NULL,
  local_trabalho_pai VARCHAR(100) NULL DEFAULT NULL,
  telefone_trabalho_pai VARCHAR(100) NULL DEFAULT NULL,
  celular_pai VARCHAR(100) NULL DEFAULT NULL,
  
  nome_mae VARCHAR(100) NULL DEFAULT NULL,
  dt_nasc_mae DATE NULL DEFAULT NULL,
  cpf_mae VARCHAR(100) NULL DEFAULT NULL,
  rg_mae VARCHAR(100) NULL DEFAULT NULL,
  grau_instrucao_mae VARCHAR(100) NULL DEFAULT NULL,
  religiao_mae VARCHAR(100) NULL DEFAULT NULL,
  profissao_mae VARCHAR(100) NULL DEFAULT NULL,
  local_trabalho_mae VARCHAR(100) NULL DEFAULT NULL,
  telefone_trabalho_mae VARCHAR(100) NULL DEFAULT NULL,
  celular_mae VARCHAR(100) NULL DEFAULT NULL,
  
  
  nome_resposavel VARCHAR(100) NULL DEFAULT NULL,
  dt_nasc_resposavel DATE NULL DEFAULT NULL,
  cpf_resposavel VARCHAR(100) NULL DEFAULT NULL,
  rg_resposavel VARCHAR(100) NULL DEFAULT NULL,
  grau_instrucao_resposavel VARCHAR(100) NULL DEFAULT NULL,
  religiao_resposavel VARCHAR(100) NULL DEFAULT NULL,
  profissao_resposavel VARCHAR(100) NULL DEFAULT NULL,
  local_trabalho_resposavel VARCHAR(100) NULL DEFAULT NULL,
  telefone_trabalho_resposavel VARCHAR(100) NULL DEFAULT NULL,
  celular_resposavel VARCHAR(100) NULL DEFAULT NULL,
  
  nome_caso_imprevisto VARCHAR(100) NULL DEFAULT NULL,
  telefone_caso_imprevisto VARCHAR(100) NULL DEFAULT NULL,
  endereco_caso_imprevisto VARCHAR(100) NULL DEFAULT NULL,
  
  nome_autorizado_1 VARCHAR(100) NULL DEFAULT NULL,
  rg_autorizado_1 VARCHAR(100) NULL DEFAULT NULL,
  grau_parentesco_autorizado_1 VARCHAR(100) NULL DEFAULT NULL,
  
  nome_autorizado_2 VARCHAR(100) NULL DEFAULT NULL,
  rg_autorizado_2 VARCHAR(100) NULL DEFAULT NULL,
  grau_parentesco_autorizado_2 VARCHAR(100) NULL DEFAULT NULL,
  
  nome_autorizado_3 VARCHAR(100) NULL DEFAULT NULL,
  rg_autorizado_3 VARCHAR(100) NULL DEFAULT NULL,
  grau_parentesco_autorizado_3 VARCHAR(100) NULL DEFAULT NULL,
  
  nome_autorizado_4 VARCHAR(100) NULL DEFAULT NULL,
  rg_autorizado_4 VARCHAR(100) NULL DEFAULT NULL,
  grau_parentesco_autorizado_4 VARCHAR(100) NULL DEFAULT NULL,
  
  nome_autorizado_5 VARCHAR(100) NULL DEFAULT NULL,
  rg_autorizado_5 VARCHAR(100) NULL DEFAULT NULL,
  grau_parentesco_autorizado_5 VARCHAR(100) NULL DEFAULT NULL,
  
  responsavel_financeiro VARCHAR(100) NULL DEFAULT NULL,	
  uf_responsavel_financeiro VARCHAR(100) NULL DEFAULT NULL,
  cidade_responsavel_financeiro VARCHAR(100) NULL DEFAULT NULL,
  rua_responsavel_financeiro VARCHAR(100) NULL DEFAULT NULL,
  cep_responsavel_financeiro VARCHAR(100) NULL DEFAULT NULL,
  bairro_responsavel_financeiro VARCHAR(100) NULL DEFAULT NULL,
  numero_responsavel_financeiro VARCHAR(100) NULL DEFAULT NULL,

 foto VARCHAR(100) NULL DEFAULT NULL,	
  
PRIMARY KEY (`codigo`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

