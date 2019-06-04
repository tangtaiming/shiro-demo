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
-- Table structure for table `sys_resource`
--

DROP TABLE IF EXISTS `sys_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `parent_ids` varchar(100) DEFAULT NULL,
  `permission` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_sys_resource_parent_id` (`parent_id`),
  KEY `idx_sys_resource_parent_ids` (`parent_ids`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_resource`
--

LOCK TABLES `sys_resource` WRITE;
/*!40000 ALTER TABLE `sys_resource` DISABLE KEYS */;
INSERT INTO `sys_resource` VALUES (1,'资源','menu','',0,'0/','',1),(21,'用户管理','menu','/user',1,'0/1/','user:*',1),(22,'用户新增','button','',21,'0/1/21/','user:create',1),(23,'用户修改','button','',21,'0/1/21/','user:update',1),(24,'用户删除','button','',21,'0/1/21/','user:delete',1),(25,'用户查看','button','',21,'0/1/21/','user:view',1),(31,'资源管理','menu','/resource',1,'0/1/','resource:*',1),(32,'资源新增','button','',31,'0/1/31/','resource:create',1),(33,'资源修改','button','',31,'0/1/31/','resource:update',1),(34,'资源删除','button','',31,'0/1/31/','resource:delete',1),(35,'资源查看','button','',31,'0/1/31/','resource:view',1),(41,'角色管理','menu','/role',1,'0/1/','role:*',1),(42,'角色新增','button','',41,'0/1/41/','role:create',1),(43,'角色修改','button','',41,'0/1/41/','role:update',1),(44,'角色删除','button','',41,'0/1/41/','role:delete',1),(45,'角色查看','button','',41,'0/1/41/','role:view',1),(46,'电影管理','menu','/doubanMovie',1,'0/1/','doubanMovie:*',0),(47,'组织管理','menu','/organization',1,'0/1/','organization:*',0),(48,'修改组织','button','',47,'0/1/47/','organization:update',0);
/*!40000 ALTER TABLE `sys_resource` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-04 18:22:39
