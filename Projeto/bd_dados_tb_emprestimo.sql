-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: bd_dados
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_emprestimo`
--

DROP TABLE IF EXISTS `tb_emprestimo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_emprestimo` (
  `ID` int NOT NULL,
  `id_amigo` int NOT NULL,
  `id_ferramenta` int NOT NULL,
  `data_inicio` date DEFAULT NULL,
  `data_devolucao` date DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_tb_emprestimo_tb_amigo` (`id_amigo`),
  KEY `fk_tb_emprestimo_tb_ferramenta` (`id_ferramenta`),
  CONSTRAINT `fk_tb_emprestimo_tb_amigo` FOREIGN KEY (`id_amigo`) REFERENCES `tb_amigo` (`ID`),
  CONSTRAINT `fk_tb_emprestimo_tb_ferramenta` FOREIGN KEY (`id_ferramenta`) REFERENCES `tb_ferramenta` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_emprestimo`
--

LOCK TABLES `tb_emprestimo` WRITE;
/*!40000 ALTER TABLE `tb_emprestimo` DISABLE KEYS */;
INSERT INTO `tb_emprestimo` VALUES (1,1,1,'2023-06-01','2023-06-08'),(2,2,2,'2023-06-03','2023-06-10'),(3,3,3,'2023-06-05','2023-06-12');
/*!40000 ALTER TABLE `tb_emprestimo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-18 10:48:02
