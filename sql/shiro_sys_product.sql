-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: shiro
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `sys_product`
--

DROP TABLE IF EXISTS `sys_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sku` varchar(45) NOT NULL COMMENT 'sku',
  `spu` varchar(45) DEFAULT NULL COMMENT 'spu',
  `title` varchar(300) DEFAULT NULL COMMENT '标题',
  `description` varchar(5000) DEFAULT NULL COMMENT '描述',
  `status` int(2) DEFAULT NULL COMMENT '状态',
  `price` double DEFAULT NULL COMMENT '价格',
  `length` double DEFAULT NULL COMMENT '长',
  `width` double DEFAULT NULL COMMENT '宽',
  `height` double DEFAULT NULL COMMENT '高',
  `total_weight` double DEFAULT NULL COMMENT '总重量',
  `creator` int(11) DEFAULT NULL COMMENT '创建人',
  `create_date` varchar(45) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sku_UNIQUE` (`sku`),
  UNIQUE KEY `spu_UNIQUE` (`spu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_product`
--

LOCK TABLES `sys_product` WRITE;
/*!40000 ALTER TABLE `sys_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-04 18:22:42
