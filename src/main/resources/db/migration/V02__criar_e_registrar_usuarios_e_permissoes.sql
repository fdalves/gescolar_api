INSERT INTO `gescolar`.`tipo_usuario` (`codigo`, `desc_tipo_usurio`, `role`) VALUES ('1', 'ADIM', 'ADM_ROLE');
INSERT INTO `gescolar`.`tipo_usuario` (`codigo`, `desc_tipo_usurio`, `role`) VALUES ('2', 'PROFESSOR', 'PRO_ROLE');
INSERT INTO `gescolar`.`tipo_usuario` (`codigo`, `desc_tipo_usurio`, `role`) VALUES ('3', 'ALUNO_RESPONSSAVEL', 'ALU_RESP_ROLE');

INSERT INTO `gescolar`.`usuario` (`codigo`, `login`, `senha`, `codigo_tipo_usuario`) VALUES ('1', 'teste', '$2a$10$6MVabyV/8iQ8TyTblTdtie2AZzESahwpqxAFjBDSON3PlXmZSHFzW', '1');
INSERT INTO `gescolar`.`usuario` (`codigo`, `login`, `senha`, `codigo_tipo_usuario`) VALUES ('2', 'teste2', '$2a$10$6MVabyV/8iQ8TyTblTdtie2AZzESahwpqxAFjBDSON3PlXmZSHFzW', '2');
INSERT INTO `gescolar`.`usuario` (`codigo`, `login`, `senha`, `codigo_tipo_usuario`) VALUES ('3', 'teste3', '$2a$10$6MVabyV/8iQ8TyTblTdtie2AZzESahwpqxAFjBDSON3PlXmZSHFzW', '3');


INSERT INTO `gescolar`.`periodo_letivo` (`codigo`, `data_ini`, `data_fim`, `situacao`, `minutos_periodo`) VALUES ('1', '2018-03-01', '2018-12-06', 'A', '45');

INSERT INTO `gescolar`.`turma` (`codigo`, `nome`, `sala`, `quant_dias_semana`, `serie`, `codigo_periodo_letivo`, `vagas`) VALUES ('1', 'Turma 101 - dia','100', '5', 'SEXTA_SERIE', '1', '40');
INSERT INTO `gescolar`.`turma` (`codigo`, `nome`, `sala`, `quant_dias_semana`, `serie`, `codigo_periodo_letivo`, `vagas`) VALUES ('2', 'Turma 102- dia' , '101','5', 'SEXTA_SERIE', '1', '40');
INSERT INTO `gescolar`.`turma` (`codigo`, `nome`, `sala`, `quant_dias_semana`, `serie`, `codigo_periodo_letivo`, `vagas`) VALUES ('3', 'Turma 103 -dia' , '102','5', 'SEXTA_SERIE', '1', '40');
INSERT INTO `gescolar`.`turma` (`codigo`, `nome`, `sala`, `quant_dias_semana`, `serie`, `codigo_periodo_letivo`, `vagas`) VALUES ('4', 'Turma -104'     , '104','5', 'SEXTA_SERIE', '1', '40');


INSERT INTO `gescolar`.`aluno` (`codigo`, `nome`, `matricula`, `sexo`, `codigo_usuario`, `codigo_turma`) VALUES ('1', 'teste aluno', '12345', 'M', '1', '1');


INSERT INTO `gescolar`.`disciplina` (`codigo`, `descricao`, `nome`) VALUES ('1', 'Matematica', 'Matematica');
INSERT INTO `gescolar`.`disciplina` (`codigo`, `descricao`, `nome`) VALUES ('2', 'Portugues', 'Portugues');
INSERT INTO `gescolar`.`disciplina` (`codigo`, `descricao`, `nome`) VALUES ('3', 'Geografia', 'Geografia');
INSERT INTO `gescolar`.`disciplina` (`codigo`, `descricao`, `nome`) VALUES ('4', 'Historia', 'Historia');
INSERT INTO `gescolar`.`disciplina` (`codigo`, `descricao`, `nome`) VALUES ('5', 'Ingles', 'Ingles');
INSERT INTO `gescolar`.`disciplina` (`codigo`, `descricao`, `nome`) VALUES ('6', 'Biologia', 'Biologia');


