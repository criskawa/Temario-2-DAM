CREATE DATABASE  IF NOT EXISTS `m06_incidencias` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `m06_incidencias`;
-- MySQL dump 10.13  Distrib 5.5.52, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: m06_incidencias
-- ------------------------------------------------------
-- Server version	5.5.52-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empleado` (
  `nombreusuario` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL,
  `nombrecompleto` varchar(50) NOT NULL,
  `telefono` varchar(9) NOT NULL,
  PRIMARY KEY (`nombreusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES ('Enjuto','intelne','Enjuto Mojamuto','333333333'),('fito','pez','Fito Fitipaldi','888888888'),('mar','1234','Mar Fontana','555555555'),('Quentin','pulp','Quentin Tarantino','777777777'),('Richard','gnu','Richard Stallman','123456789'),('Sheldon','big','Sheldon Cooper','111111111');
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historial`
--

DROP TABLE IF EXISTS `historial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `historial` (
  `idevento` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(1) NOT NULL,
  `fechahora` varchar(50) NOT NULL,
  `empleado` varchar(10) NOT NULL,
  PRIMARY KEY (`idevento`),
  KEY `fk_historial_1_idx` (`empleado`),
  CONSTRAINT `fk_historial_1` FOREIGN KEY (`empleado`) REFERENCES `empleado` (`nombreusuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historial`
--

LOCK TABLES `historial` WRITE;
/*!40000 ALTER TABLE `historial` DISABLE KEYS */;
INSERT INTO `historial` VALUES (5,'I','2016/11/14 17:53:45','mar'),(6,'I','2016/11/14 18:09:17','fito'),(7,'I','2016/11/14 18:10:07','Quentin'),(8,'I','2016/11/14 18:12:49','Richard'),(9,'I','2016/11/14 18:13:00','Enjuto'),(10,'I','2016/11/14 18:13:39','mar'),(11,'I','2016/11/14 18:18:19','mar'),(12,'I','2016/11/14 18:25:52','fito'),(13,'I','2016/11/14 18:38:04','Richard'),(14,'U','2016/11/14 18:38:04','mar'),(15,'I','2016/11/14 18:53:13','Richard'),(16,'I','2016/11/14 18:58:36','Richard'),(17,'I','2016/11/14 18:59:09','Richard'),(18,'I','2016/11/14 18:59:19','Richard'),(19,'I','2016/11/14 19:03:47','Enjuto'),(20,'U','2016/11/14 19:03:47','Enjuto'),(21,'I','2016/11/14 19:05:56','Enjuto'),(22,'U','2016/11/14 19:05:56','Richard'),(23,'I','2016/11/14 19:07:08','Enjuto'),(24,'I','2016/11/14 19:08:12','Enjuto'),(25,'I','2016/11/14 19:08:44','Enjuto'),(26,'U','2016/11/14 19:08:44','Sheldon'),(27,'I','2016/11/14 19:25:48','Sheldon'),(28,'U','2016/11/14 19:25:48','Richard'),(29,'C','2016/11/14 19:25:48','Sheldon'),(30,'I','2016/11/14 19:28:02','Sheldon'),(31,'U','2016/11/14 19:28:02','Richard'),(32,'C','2016/11/14 19:28:02','Sheldon'),(33,'I','2016/11/14 19:34:24','mar'),(34,'U','2016/11/14 19:34:24','Sheldon'),(35,'C','2016/11/14 19:34:24','Sheldon'),(36,'I','2016/11/14 19:43:23','mar'),(37,'U','2016/11/14 19:43:23','fito'),(38,'C','2016/11/14 19:43:23','Sheldon'),(39,'I','2016/11/14 19:44:24','mar'),(40,'U','2016/11/14 19:44:24','Richard'),(41,'C','2016/11/14 19:44:24','Sheldon'),(42,'I','2016/11/14 19:48:58','mar'),(43,'U','2016/11/14 19:48:58','fito'),(44,'C','2016/11/14 19:48:58','Sheldon'),(45,'I','2016/11/15 11:49:43','mar'),(46,'U','2016/11/15 11:49:43','Sheldon'),(47,'C','2016/11/15 11:49:43','Sheldon'),(48,'U','2016/11/15 12:03:16','Enjuto'),(49,'C','2016/11/15 12:20:33','mar'),(50,'C','2016/11/15 12:50:06','mar'),(51,'C','2016/11/15 12:51:32','mar'),(52,'U','2016/11/15 12:52:41','fito'),(53,'C','2016/11/15 12:52:41','mar'),(54,'I','2016/11/15 12:54:46','fito'),(55,'U','2016/11/15 12:54:46','Quentin'),(56,'C','2016/11/15 12:54:46','Sheldon'),(57,'C','2016/11/15 12:54:47','mar');
/*!40000 ALTER TABLE `historial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incidencia`
--

DROP TABLE IF EXISTS `incidencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `incidencia` (
  `idincidencia` int(11) NOT NULL,
  `origen` varchar(10) NOT NULL,
  `destino` varchar(10) NOT NULL,
  `fechahora` varchar(50) NOT NULL,
  `detalle` text NOT NULL,
  `tipo` varchar(8) NOT NULL,
  PRIMARY KEY (`idincidencia`),
  KEY `fk_incidencia_1_idx` (`origen`),
  KEY `fk_incidencia_2_idx` (`destino`),
  CONSTRAINT `fk_incidencia_1` FOREIGN KEY (`origen`) REFERENCES `empleado` (`nombreusuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_incidencia_2` FOREIGN KEY (`destino`) REFERENCES `empleado` (`nombreusuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incidencia`
--

LOCK TABLES `incidencia` WRITE;
/*!40000 ALTER TABLE `incidencia` DISABLE KEYS */;
INSERT INTO `incidencia` VALUES (1,'mar','fito','2016/11/14 18:38:04','El pez no tiene comida','Urgente'),(2,'Quentin','Sheldon','2016/11/14 18:59:08','No puedo imprimir el informe de El Lobo','Normal'),(3,'Enjuto','Sheldon','2016/11/14 19:03:46','No funciona interneeet!!','Urgente'),(4,'Richard','fito','2016/11/14 19:05:56','Tu licencia no es gnu!!!','Urgente'),(5,'Sheldon','mar','2016/11/14 19:07:08','No he aprendido nada de Java!','Normal'),(6,'Sheldon','Quentin','2016/11/14 19:08:44','¡Estás en mi sitio!','Urgente'),(7,'Richard','Enjuto','2016/11/14 19:25:48','Firefox no funciona','Urgente'),(8,'Richard','fito','2016/11/14 19:28:01','El sonido no funciona','Urgente'),(9,'Sheldon','Enjuto','2016/11/14 19:34:24','No queda tinta en la impresora.','Urgente'),(10,'fito','Quentin','2016/11/14 19:43:23','No funciona la pantalla.','Urgente'),(11,'Richard','mar','2016/11/14 19:44:24','Los informes no están acabados.','Urgente'),(12,'fito','Quentin','2016/11/14 19:48:57','El sr. Naranja no ha venido.','Urgente'),(13,'Sheldon','mar','2016/11/15 11:45:41','No me compila el código.','Normal'),(14,'Sheldon','fito','2016/11/15 11:49:43','Un pájaro está dentro del altavoz!','Urgente'),(15,'Enjuto','mar','2016/11/15 11:49:44','Eclipse da error de jdk.','Normal'),(16,'Enjuto','fito','2016/11/15 12:03:16','La fuente de alimentación echa humo.','Urgente'),(17,'mar','Enjuto','2016/11/15 12:20:32','Se ha roto una mesa del aula 36.','Normal'),(18,'Quentin','fito','2016/11/15 12:50:06','El tejado de la casa se está agrietando.','Normal'),(19,'Richard','Quentin','2016/11/15 12:51:32','El lector de DVD del portátil no funciona','Normal'),(20,'fito','Quentin','2016/11/15 12:52:41','Hay una serpiente en el escáner.','Urgente'),(21,'Quentin','Sheldon','2016/11/15 12:54:45','La webcam de conferencias no funciona.','Urgente'),(22,'Richard','fito','2016/11/15 12:54:47','Faltan informes definitivos.','Normal');
/*!40000 ALTER TABLE `incidencia` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-15 12:55:52
