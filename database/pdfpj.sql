-- MySQL dump 10.13  Distrib 5.7.10, for Win64 (x86_64)
--
-- Host: localhost    Database: pdfpj
-- ------------------------------------------------------
-- Server version	5.7.10-log

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
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `bookid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `bookname` varchar(50) NOT NULL,
  `author` varchar(50) NOT NULL,
  `introduction` varchar(2000) NOT NULL,
  `label` varchar(50) NOT NULL,
  `introImage` tinyint(4) DEFAULT '0',
  `type` varchar(50) NOT NULL,
  `registerDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `page` smallint(6) DEFAULT '0',
  `state` tinyint(4) DEFAULT '0',
  `download` int(11) DEFAULT '0',
  PRIMARY KEY (`bookid`),
  KEY `userid` (`userid`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `book_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `user` (`id`),
  CONSTRAINT `book_ibfk_3` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,20,'abc','abc','abc','abc',0,'abc','2018-05-25 20:34:12',123,3,0),(2,20,'abc','abc','abc','abc',0,'abc','2018-05-25 20:57:02',555,4,0),(3,20,'abc','abc','abc','abc',0,'abc','2018-05-25 21:01:08',475,0,0),(4,20,'ABC','ABC','ABC','ABC',0,'ABC','2018-05-25 21:15:23',456,0,0),(5,20,'ABC','ABC','ABC','ABC',0,'ABC','2018-05-25 21:19:48',123,0,0),(6,20,'ABC','ABC','ABC','ABC',0,'ABC','2018-05-25 21:20:09',123,0,0),(7,20,'ABC','ABC','ABC','ABC',0,'ABC','2018-05-25 21:20:23',123,0,0),(8,20,'abc','ABC','abc','abc',0,'abc','2018-05-25 21:22:25',123,0,0),(9,20,'abc','abc','abc','abc',0,'abc','2018-05-25 21:27:16',123,0,0),(12,20,'51','132','321','32',0,'321','2018-05-27 17:53:35',23,0,0),(13,20,'4561','21','31','31',0,'3','2018-05-27 17:55:40',1321,0,0),(14,20,'1','23','132','132',0,'13','2018-05-27 17:57:19',21,0,0),(15,20,'132','13','13','13',0,'13','2018-05-27 18:22:03',1,0,0),(16,20,'132','13','13','13',0,'13','2018-05-27 18:22:16',1,0,0),(17,20,'132','13','13','13',0,'13','2018-05-27 18:23:11',1,0,0),(21,20,'1','321','31','313',0,'213','2018-05-27 18:43:45',321,0,0),(22,20,'54','65','46','54',0,'64','2018-05-27 18:44:57',65,0,0),(23,20,'54','65','46','54',0,'64','2018-05-27 18:44:57',65,0,0),(24,20,'23','3','123','5',0,'213312','2018-05-27 18:47:28',123,0,0),(25,20,'45','4','54','54',0,'54','2018-05-27 18:54:25',45,0,0),(26,20,'1','231','321','32',0,'13','2018-05-27 19:32:27',231,0,0),(27,20,'451','321','3213','213',0,'13','2018-05-27 19:34:20',2,0,0),(28,20,'1','231','31','2',0,'31','2018-05-27 19:48:56',13,0,0),(29,20,'1','231','31','2',0,'31','2018-05-27 19:51:53',13,0,0),(30,20,'1','231','31','2',0,'31','2018-05-27 19:55:32',13,0,0),(31,20,'1','231','31','2',0,'31','2018-05-27 19:56:30',13,0,0),(32,20,'1','231','31','2',0,'31','2018-05-27 19:57:33',13,0,0),(33,20,'1','231','31','2',0,'31','2018-05-27 19:58:29',13,0,0),(34,20,'1','231','31','2',0,'31','2018-05-27 19:59:52',13,0,0),(35,20,'1','231','31','2',0,'31','2018-05-27 20:00:42',13,0,0),(36,20,'1','231','31','2',0,'31','2018-05-27 20:03:03',13,0,0),(37,20,'4','54','54','123',0,'454','2018-05-28 12:05:16',5,0,0),(38,20,'4','54','54','123',0,'454','2018-05-28 12:06:46',5,0,0),(39,20,'4','54','54','123',0,'454','2018-05-28 12:07:32',5,0,0),(40,20,'4','54','54','123',0,'454','2018-05-28 12:09:22',5,0,0),(41,20,'4','54','54','123',0,'454','2018-05-28 12:12:58',5,0,0),(42,20,'4','54','54','123',0,'454','2018-05-28 12:16:32',5,0,0),(43,20,'4','54','54','123',0,'454','2018-05-28 12:17:33',5,0,0),(44,20,'4','54','54','123',0,'454','2018-05-28 12:20:20',5,0,0),(55,20,'1','12','3','1',0,'231','2018-05-29 13:46:41',3,0,0),(56,20,'54','56','456','46',0,'456','2018-05-29 21:46:14',4,0,0),(57,20,'54','56','456','46',0,'456','2018-05-29 21:49:05',4,0,0),(58,20,'54','56','456','46',0,'456','2018-05-29 21:53:20',4,0,0);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookmarks`
--

DROP TABLE IF EXISTS `bookmarks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookmarks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `bookid` int(11) NOT NULL,
  `createdate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastchangedate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `title` varchar(50) NOT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `pagenum` int(11) NOT NULL,
  `state` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  KEY `bookid` (`bookid`),
  CONSTRAINT `bookmarks_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `bookmarks_ibfk_2` FOREIGN KEY (`bookid`) REFERENCES `book` (`bookid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookmarks`
--

LOCK TABLES `bookmarks` WRITE;
/*!40000 ALTER TABLE `bookmarks` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookmarks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `table1`
--

DROP TABLE IF EXISTS `table1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `table1` (
  `type` tinyint(4) DEFAULT '0',
  `targetid` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table1`
--

LOCK TABLES `table1` WRITE;
/*!40000 ALTER TABLE `table1` DISABLE KEYS */;
INSERT INTO `table1` VALUES (4,5);
/*!40000 ALTER TABLE `table1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test` (
  `id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES (1),(2),(3);
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `passwd` varchar(20) DEFAULT NULL,
  `registerdate` datetime DEFAULT CURRENT_TIMESTAMP,
  `lastlogindate` datetime DEFAULT CURRENT_TIMESTAMP,
  `email` varchar(20) DEFAULT NULL,
  `permit` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'root','root','123456','2018-05-18 17:13:00',NULL,'123456@789',1),(3,'jack','jack','123456','2018-05-18 17:18:05',NULL,'123456@789',0),(11,'GNUBD','思密达','123456','2018-05-18 18:12:26','2018-06-02 20:59:12','740383038@qq.com',0),(12,'abc','cba','abcd','2018-05-19 15:24:41',NULL,'adasd',0),(13,'kkkk','看看看看','12312312','2018-05-19 16:55:20',NULL,'dadasd',0),(14,'sadasd','第三代','123123','2018-05-19 16:58:39',NULL,'adasdw',0),(15,'sad','asdasd','21312','2018-05-19 17:03:41',NULL,'qweqw',0),(16,'123','456','546','2018-05-25 10:27:33',NULL,'456',0),(17,'4595','54654','123','2018-05-25 12:03:32',NULL,'123',0),(18,'45951','546541','123','2018-05-25 12:05:05','2018-05-25 12:05:05','123',0),(20,'456','456','789','2018-05-25 13:24:45','2018-06-02 20:57:27','789',0),(21,'ddd','哈哈哈','123','2018-05-28 13:22:47','2018-05-28 13:22:47','456',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-03  9:13:58
