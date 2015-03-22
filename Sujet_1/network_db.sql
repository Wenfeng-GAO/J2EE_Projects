-- MySQL dump 10.13  Distrib 5.6.23, for osx10.8 (x86_64)
--
-- Host: localhost    Database: network_db
-- ------------------------------------------------------
-- Server version	5.6.23

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
-- Table structure for table `biographie`
--

DROP TABLE IF EXISTS `biographie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `biographie` (
  `user_id` int(11) NOT NULL,
  `bio` blob,
  KEY `user_user_id_biographie_fk` (`user_id`),
  CONSTRAINT `user_user_id_biographie_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `biographie`
--

LOCK TABLES `biographie` WRITE;
/*!40000 ALTER TABLE `biographie` DISABLE KEYS */;
/*!40000 ALTER TABLE `biographie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competence`
--

DROP TABLE IF EXISTS `competence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competence` (
  `competence_id` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(30) NOT NULL,
  PRIMARY KEY (`competence_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competence`
--

LOCK TABLES `competence` WRITE;
/*!40000 ALTER TABLE `competence` DISABLE KEYS */;
/*!40000 ALTER TABLE `competence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competence_desc`
--

DROP TABLE IF EXISTS `competence_desc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competence_desc` (
  `competence_id` int(11) NOT NULL,
  `description` blob,
  KEY `competence_competence_id_competence_desc_fk` (`competence_id`),
  CONSTRAINT `competence_competence_id_competence_desc_fk` FOREIGN KEY (`competence_id`) REFERENCES `competence` (`competence_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competence_desc`
--

LOCK TABLES `competence_desc` WRITE;
/*!40000 ALTER TABLE `competence_desc` DISABLE KEYS */;
/*!40000 ALTER TABLE `competence_desc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passion`
--

DROP TABLE IF EXISTS `passion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `passion` (
  `passion_id` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(30) NOT NULL,
  PRIMARY KEY (`passion_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passion`
--

LOCK TABLES `passion` WRITE;
/*!40000 ALTER TABLE `passion` DISABLE KEYS */;
/*!40000 ALTER TABLE `passion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passion_desc`
--

DROP TABLE IF EXISTS `passion_desc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `passion_desc` (
  `passion_id` int(11) NOT NULL,
  `description` blob,
  KEY `passion_passion_id_passion_desc_fk` (`passion_id`),
  CONSTRAINT `passion_passion_id_passion_desc_fk` FOREIGN KEY (`passion_id`) REFERENCES `passion` (`passion_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passion_desc`
--

LOCK TABLES `passion_desc` WRITE;
/*!40000 ALTER TABLE `passion_desc` DISABLE KEYS */;
/*!40000 ALTER TABLE `passion_desc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(30) NOT NULL,
  `password` varchar(20) NOT NULL,
  `nom` varchar(15) NOT NULL,
  `prenom` varchar(15) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_competence`
--

DROP TABLE IF EXISTS `user_competence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_competence` (
  `user_id` int(11) NOT NULL,
  `competence_id` int(11) NOT NULL,
  KEY `user_user_id_user_competence_fk` (`user_id`),
  KEY `competence_competence_id_user_competence_fk` (`competence_id`),
  CONSTRAINT `competence_competence_id_user_competence_fk` FOREIGN KEY (`competence_id`) REFERENCES `competence` (`competence_id`),
  CONSTRAINT `user_user_id_user_competence_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_competence`
--

LOCK TABLES `user_competence` WRITE;
/*!40000 ALTER TABLE `user_competence` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_competence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_passion`
--

DROP TABLE IF EXISTS `user_passion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_passion` (
  `user_id` int(11) NOT NULL,
  `passion_id` int(11) NOT NULL,
  KEY `user_user_id_user_passion_fk` (`user_id`),
  KEY `passion_passion_id_user_passion_fk` (`passion_id`),
  CONSTRAINT `passion_passion_id_user_passion_fk` FOREIGN KEY (`passion_id`) REFERENCES `passion` (`passion_id`),
  CONSTRAINT `user_user_id_user_passion_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_passion`
--

LOCK TABLES `user_passion` WRITE;
/*!40000 ALTER TABLE `user_passion` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_passion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-03-22 16:18:17
