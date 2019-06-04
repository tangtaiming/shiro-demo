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
-- Table structure for table `sys_douban_intheaters`
--

DROP TABLE IF EXISTS `sys_douban_intheaters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_douban_intheaters` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `small_image` varchar(150) DEFAULT NULL,
  `douben_intheaters_id` int(11) DEFAULT NULL,
  `year` varchar(4) DEFAULT NULL,
  `stars` varchar(3) DEFAULT NULL,
  `average` int(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_douban_intheaters`
--

LOCK TABLES `sys_douban_intheaters` WRITE;
/*!40000 ALTER TABLE `sys_douban_intheaters` DISABLE KEYS */;
INSERT INTO `sys_douban_intheaters` VALUES (1,'如影随心','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2553279350.jpg',26871669,'2019','00',0),(2,'转型团伙','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2551331455.jpg',26857654,'2019','00',0),(3,'反贪风暴4','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2551353482.jpg',27202819,'2019','35',6),(4,'境·界','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2551872873.jpg',4844731,'2018','25',4),(5,'调音师','https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2551995207.jpg',30334073,'2018','45',8),(6,'神奇乐园历险记','https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2552076937.jpg',26662282,'2019','35',6),(7,'雷霆沙赞！','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2551249211.jpg',2244426,'2019','35',6),(8,'老师·好','https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2551352209.jpg',27663742,'2019','35',6),(9,'祈祷落幕时','https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2552073598.jpg',27040737,'2018','40',8),(10,'HELLO，北京','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2552080122.jpg',30465860,'2019','00',0),(11,'波西米亚狂想曲','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2549558913.jpg',5300054,'2018','45',8),(12,'小飞象','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2549234765.jpg',25924056,'2019','35',6),(13,'欲念游戏','https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2551984168.jpg',26850330,'2019','15',3),(14,'风中有朵雨做的云','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2552522615.jpg',26728669,'2018','40',7),(15,'流浪地球','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2545472803.jpg',26266893,'2019','40',8),(16,'地久天长','https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2550208359.jpg',26715636,'2019','40',7),(17,'比悲伤更悲伤的故事','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2549523952.jpg',27624661,'2018','25',4),(18,'海市蜃楼','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2551172384.jpg',30164448,'2018','40',7),(19,'绿皮书','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2549177902.jpg',27060077,'2018','45',8),(20,'最佳男友进化论','https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2553207098.jpg',26774017,'2019','00',0),(21,'守灵','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2549910141.jpg',27063305,'2017','00',0),(22,'毕业旅行之逍遥骑士','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2552542543.jpg',30447810,'2019','00',0),(23,'驯龙高手3','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2546335362.jpg',19899707,'2019','40',7),(24,'在乎你','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2552608202.jpg',27185558,'2019','30',5),(25,'青蛙王子历险记','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2552614343.jpg',30288751,'2019','00',0),(26,'原来如此','https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2546359739.jpg',30345723,'2018','00',0),(27,'阿丽塔：战斗天使','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2544987866.jpg',1652592,'2019','40',7),(28,'我的宠物是大象','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2553090666.jpg',26803717,'2019','00',0),(29,'我叫为何','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2552463974.jpg',32513733,'2019','00',0),(30,'三重威胁之跨国大营救','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2551252121.jpg',26909787,'2019','25',4),(31,'麦兜·我和我妈妈','https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2551331237.jpg',25884416,'2014','45',8),(32,'北斗风云','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2553912723.jpg',27198131,'2019','00',0),(33,'难以置信','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2550531644.jpg',27666591,'2018','00',0),(34,'夜伴歌声','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2548652745.jpg',30464313,'2019','00',0),(35,'精灵怪物：疯狂之旅','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2551444674.jpg',27042871,'2017','25',5),(36,'虫林大作战','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2550873121.jpg',27059060,'2017','00',0),(37,'人间·喜剧','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2551693460.jpg',27179414,'2019','20',3),(38,'狗眼看人心','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2551690865.jpg',30479973,'2019','00',0),(39,'醒来之爱的呼唤','https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2549170039.jpg',30460368,'2019','00',0),(40,'何以为家','https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2553734348.jpg',30170448,'2018','45',8),(41,'撞死了一只羊','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2550800623.jpg',30283179,'2018','40',7),(42,'功夫四侠：勇闯地宫','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2550448960.jpg',30145516,'2019','00',0),(43,'复仇者联盟4：终局之战','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2552058346.jpg',26100958,'2019','00',0),(44,'武陵山上的星光','https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2553669139.jpg',32568719,'2019','00',0),(45,'云雾笼罩的山峰','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2553741104.jpg',30336318,'2018','00',0),(46,'九克拉战栗','https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2553828138.jpg',26989671,'2019','00',0),(47,'我在社区当片儿警','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2549441144.jpg',30475118,'2019','00',0),(48,'雪暴','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2554545271.jpg',26899146,'2018','35',6),(49,'动物出击','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2554401295.jpg',30345341,'2019','00',0),(50,'天上再见','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2554018955.jpg',26731376,'2017','40',8),(51,'捉妖学院','https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2554386398.jpg',26879542,'2019','00',0),(52,'下一任：前任','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2554795775.jpg',26311974,'2019','00',0),(53,'悟空奇遇记','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2552558351.jpg',26878827,'2019','00',0),(54,'港珠澳大桥','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2554017175.jpg',30230160,'2019','00',0),(55,'猫公主苏菲','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2554014252.jpg',30320371,'2019','00',0),(56,'过昭关','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2554872580.jpg',30206431,'2018','40',7),(57,'午夜怪谈','https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2554981472.jpg',33434703,'2019','00',0);
/*!40000 ALTER TABLE `sys_douban_intheaters` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-04 18:22:43
