INSERT INTO `gescolar`.`tipo_usuario` (`codigo`, `desc_tipo_usuario`) VALUES ('1', 'ADM');
INSERT INTO `gescolar`.`tipo_usuario` (`codigo`, `desc_tipo_usuario`) VALUES ('2', 'PROFESSOR');
INSERT INTO `gescolar`.`tipo_usuario` (`codigo`, `desc_tipo_usuario`) VALUES ('3', 'ALUNO_RESPONSSAVEL');

INSERT INTO `gescolar`.`usuario` (`codigo`, `login`, `senha`, `codigo_tipo_usuario`) VALUES ('1', 'teste', '$2a$10$6MVabyV/8iQ8TyTblTdtie2AZzESahwpqxAFjBDSON3PlXmZSHFzW', '1');
INSERT INTO `gescolar`.`usuario` (`codigo`, `login`, `senha`, `codigo_tipo_usuario`) VALUES ('2', 'teste2', '$2a$10$6MVabyV/8iQ8TyTblTdtie2AZzESahwpqxAFjBDSON3PlXmZSHFzW', '2');
INSERT INTO `gescolar`.`usuario` (`codigo`, `login`, `senha`, `codigo_tipo_usuario`) VALUES ('3', 'teste3', '$2a$10$6MVabyV/8iQ8TyTblTdtie2AZzESahwpqxAFjBDSON3PlXmZSHFzW', '3');


INSERT INTO `gescolar`.`periodo_letivo` (`codigo`, `data_ini`, `data_fim`, `situacao`, `minutos_periodo`) VALUES ('1', '2018-03-01', '2018-12-06', 'A', '45');

INSERT INTO `gescolar`.`disciplina` (`codigo`, `descricao`, `nome`) VALUES ('1', 'Matematica', 'Matematica');
INSERT INTO `gescolar`.`disciplina` (`codigo`, `descricao`, `nome`) VALUES ('2', 'Portugues', 'Portugues');
INSERT INTO `gescolar`.`disciplina` (`codigo`, `descricao`, `nome`) VALUES ('3', 'Geografia', 'Geografia');
INSERT INTO `gescolar`.`disciplina` (`codigo`, `descricao`, `nome`) VALUES ('4', 'Historia', 'Historia');
INSERT INTO `gescolar`.`disciplina` (`codigo`, `descricao`, `nome`) VALUES ('5', 'Ingles', 'Ingles');
INSERT INTO `gescolar`.`disciplina` (`codigo`, `descricao`, `nome`) VALUES ('6', 'Biologia', 'Biologia');


INSERT INTO `gescolar`.`permissao` (`codigo`, `descricao`) VALUES ('1', 'ROLE_ADM');
INSERT INTO `gescolar`.`permissao` (`codigo`, `descricao`) VALUES ('2', 'ROLE_PROF');
INSERT INTO `gescolar`.`permissao` (`codigo`, `descricao`) VALUES ('3', 'ROLE_ALUNO_RESP');


INSERT INTO `gescolar`.`tipo_usuario_permissao` (`codigo_tipo_usuario`, `codigo_permissao`) VALUES ('1', '1');
INSERT INTO `gescolar`.`tipo_usuario_permissao` (`codigo_tipo_usuario`, `codigo_permissao`) VALUES ('2', '2');
INSERT INTO `gescolar`.`tipo_usuario_permissao` (`codigo_tipo_usuario`, `codigo_permissao`) VALUES ('3', '3');

INSERT INTO `gescolar`.`cnab` (`codigo`, `tipo_pessoa`, `cpf_cnpj`, `agencia`, `conta`, `nome`, `endereco`, `cep`, `cidade`, `uf`, `digito_conta`, `posto`, `seq_nosso_numero`, `seq_seu_numero`) VALUES ('1', '2', '05854255000135', '0116', '01671', 'Centro de Educacao Infantil MA', ' ', '94015070', 'GRAVATAI', 'RS', '8', '05', 1, 1);




