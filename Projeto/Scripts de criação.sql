CREATE DATABASE IF NOT EXISTS bd_dados;
USE bd_dados;

CREATE TABLE IF NOT EXISTS tb_amigo (
  ID INT PRIMARY KEY,
  Nome VARCHAR(50),
  Telefone VARCHAR(20),
  email_amigo VARCHAR(255)
);


CREATE TABLE IF NOT EXISTS tb_ferramenta (
  ID INT PRIMARY KEY,
  Nome VARCHAR(50),
  Marca VARCHAR(50),
  Custo DOUBLE
);

CREATE TABLE IF NOT EXISTS `tb_emprestimo` (
  `ID` int NOT NULL,
  `id_amigo` int NOT NULL,
  `id_ferramenta` int NOT NULL,
  `data_inicio` date DEFAULT NULL,
  `data_devolucao` date DEFAULT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `fk_tb_emprestimo_tb_amigo` FOREIGN KEY (`id_amigo`) REFERENCES `tb_amigo` (`ID`),
  CONSTRAINT `fk_tb_emprestimo_tb_ferramenta` FOREIGN KEY (`id_ferramenta`) REFERENCES `tb_ferramenta` (`ID`)
);

