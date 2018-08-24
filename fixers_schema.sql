CREATE DATABASE  IF NOT EXISTS `skate_fixers` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `skate_fixers`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: skate_fixers
-- ------------------------------------------------------
-- Server version	8.0.11

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
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `addresses` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1fa36y2oqhao3wgg2rw1pi459` (`user_id`),
  CONSTRAINT `FK1fa36y2oqhao3wgg2rw1pi459` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresses`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
INSERT INTO `addresses` VALUES (1,'ж.к. Младост 1, бл. 20, вход-г, ап. 2','Tsvetelin Nikolaev Yakimov ','876736973',1,''),(2,'Mladost 1 Block 2','Goran Ignatov','0876736973',1,'\0'),(3,'ж.к. Младост 1, бл. 20, вход-г, ап. 2','Goran Ignatov','876736973',3,''),(4,'No addr given mate srt','Georgi','7777777777',1,''),(5,'Искам да е до офис на SPEEDY МНТ','Горан ','089-238569-238',1,''),(6,'hahaha ne jiveq nikude','Big Smoke','087947949',2,''),(7,'Нямам и адрес :/','Фалшив Цеци','няям тел',4,'');
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banners`
--

DROP TABLE IF EXISTS `banners`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `banners` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `background_image_link` varchar(255) NOT NULL,
  `is_disabled` bit(1) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banners`
--

LOCK TABLES `banners` WRITE;
/*!40000 ALTER TABLE `banners` DISABLE KEYS */;
INSERT INTO `banners` VALUES (1,'http://www.spulsa.info/wp-content/uploads/skateboard-banners-nike-sb-team-banner-forty-two-skateboard-shop.jpg','\0','Skate remontiori v1','https://www.facebook.com/'),(2,'http://www.latabla.cl/wp-content/uploads/2016/03/DcShoes-EvanSmith2.jpg','\0','Nekvi shemi','https://www.facebook.com/'),(3,'https://images.tcdn.com.br/img/img_prod/481988/1509493378_banner_element.jpg','\0','New Cat skater just found','https://www.facebook.com/ceci2205'),(4,'https://drive.google.com/uc?id=1V8vealxQRayzbgQe6pNtS-kCSLrHR6k5&export=download','\0','New skateboards from Zero','http://localhost:8000/brands/Zero'),(5,'https://drive.google.com/uc?id=18inGi1qMZc0PPfoUGkwQkL5wZv412kmZ&export=download','','Пълна издънка','https://www.brailleskateboarding.com/');
/*!40000 ALTER TABLE `banners` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brands`
--

DROP TABLE IF EXISTS `brands`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brands` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gds2u6k2vfeo1tkrtgwcyqj36` (`brand_name`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brands`
--

LOCK TABLES `brands` WRITE;
/*!40000 ALTER TABLE `brands` DISABLE KEYS */;
INSERT INTO `brands` VALUES (1,'Bones','https://drive.google.com/uc?id=1i45AZA1HCwC6fkGAjUF9vlieKJYOX7EH&export=download'),(2,'Fallen','https://drive.google.com/uc?id=1qY35nUD_bloNQ40jmCjnS9kSXZXb-ftB&export=download'),(3,'Nike Sb','https://drive.google.com/uc?id=1vVURUX7pq-ukkjWsFbPqbA8OO1byvdZx&export=download'),(4,'Independent','https://drive.google.com/uc?id=1GDY2bYsWlT3Zy2cb7hn4-3TByfTwikhe&export=download'),(5,'Royal','https://drive.google.com/uc?id=1ANprdZwLQJI699-K6BJsauylurUm3zE_&export=download'),(6,'Spitfire','https://drive.google.com/uc?id=1VGgW64Xk5zclonPDbXVgyBSk2uC8nR9F&export=download'),(7,'DC Shoes','https://drive.google.com/uc?id=1VLiSXjB66ANqkiMEd9XxMIMh4H3jgDMb&export=download'),(8,'Insomnia','https://drive.google.com/uc?id=1CtsbFW6Tj_oRMVg5xzwnsuTxVukHUOhl&export=download'),(9,'Zero','https://drive.google.com/uc?id=16wk9f38YaEgl2FPw2Z-cK_5UNjSYKBCT&export=download'),(10,'Jart','https://drive.google.com/uc?id=151anWzrKe7Um8btkv4TtKkByh9v2ODgM&export=download'),(11,'Element','https://drive.google.com/uc?id=1TgsHAfGWCrrdAcN04TXvsJpRfC32Cl0U&');
/*!40000 ALTER TABLE `brands` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notifications` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `date` datetime DEFAULT NULL,
  `href` varchar(255) NOT NULL,
  `is_seen` bit(1) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9y21adhxn0ayjhfocscqox7bh` (`user_id`),
  CONSTRAINT `FK9y21adhxn0ayjhfocscqox7bh` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (16,'New order received','2018-08-21 21:14:19','/orders/review/17','\0',3),(18,'New order received','2018-08-21 21:16:46','/orders/review/18','',3),(22,'New order received','2018-08-21 22:21:12','/orders/review/20','',1),(25,'New order received','2018-08-22 09:37:35','/orders/review/21','',1),(26,'New order received','2018-08-22 09:37:35','/orders/review/21','',3),(28,'Нова поръчка','2018-08-22 13:29:47','/orders/review/22','',1),(30,'Order status has changed to REJECTED','2018-08-22 13:30:18','/users/orders/22','',4),(31,'ei geicheeeeeee','2018-08-22 14:32:16','#','',4),(32,'te nekvi zerota','2018-08-22 14:37:38','http://localhost:8000/brands/Zero','',4);
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_date` datetime DEFAULT NULL,
  `total_price` double NOT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  `serialized_products` varchar(255) NOT NULL,
  `order_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhlglkvf5i60dv6dn397ethgpt` (`address_id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`),
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKhlglkvf5i60dv6dn397ethgpt` FOREIGN KEY (`address_id`) REFERENCES `addresses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'2018-08-20 22:01:52',304,5,1,'{\"1\":1,\"17\":1,\"2\":1,\"22\":1,\"11\":1}',2),(2,'2018-08-20 22:15:14',138,3,3,'{\"17\":1,\"22\":1}',2),(3,'2018-08-20 23:21:54',2497.25,6,2,'{\"17\":1,\"1\":1,\"21\":1,\"22\":1,\"23\":1,\"8\":1,\"11\":1}',2),(4,'2018-08-20 23:22:12',2039.25,6,2,'{\"20\":1,\"21\":1,\"22\":1}',1),(5,'2018-08-21 09:59:20',266,6,2,'{\"17\":2,\"1\":1,\"22\":2}',1),(6,'2018-08-21 10:12:49',518,6,2,'{\"17\":1,\"22\":1,\"28\":2}',1),(8,'2018-08-21 13:36:45',1002,1,1,'{\"2\":1,\"22\":1,\"11\":8}',1),(9,'2018-08-21 13:43:28',118,3,3,'{\"17\":1}',2),(10,'2018-08-21 13:45:10',118,1,1,'{\"11\":1}',2),(11,'2018-08-21 15:30:09',19,1,1,'{\"24\":1}',1),(12,'2018-08-21 15:41:55',118,1,1,'{\"17\":1}',2),(13,'2018-08-21 19:08:22',274,6,2,'{\"17\":1,\"2\":1,\"11\":1}',2),(14,'2018-08-21 20:57:12',138,6,2,'{\"17\":1,\"22\":1}',2),(15,'2018-08-21 20:57:23',118,6,2,'{\"17\":1}',1),(16,'2018-08-21 21:05:04',255,3,3,'{\"16\":1,\"2\":1,\"27\":1}',2),(17,'2018-08-21 21:14:19',102,6,2,'{\"23\":1}',0),(18,'2018-08-21 21:16:46',38,6,2,'{\"2\":1}',0),(19,'2018-08-21 21:22:38',20,6,2,'{\"22\":1}',2),(20,'2018-08-21 22:21:12',667,7,4,'{\"18\":15,\"26\":1}',2),(21,'2018-08-22 09:37:34',30,7,4,'{\"1\":1,\"22\":1}',2),(22,'2018-08-22 13:29:47',138,7,4,'{\"17\":1,\"22\":1}',2);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_categories`
--

DROP TABLE IF EXISTS `product_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_categories` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_name_cyrillic` varchar(255) NOT NULL,
  `category_name_latin` varchar(255) NOT NULL,
  `parent_category` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_5bi0laalmqm5q38g8wsipwxfx` (`category_name_cyrillic`),
  UNIQUE KEY `UK_h4bx645v1j1m0bchvqy6wx5pd` (`category_name_latin`),
  KEY `FKtqtx4nhmyodqxppbbn4bekf1g` (`parent_category`),
  CONSTRAINT `FKtqtx4nhmyodqxppbbn4bekf1g` FOREIGN KEY (`parent_category`) REFERENCES `product_categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_categories`
--

LOCK TABLES `product_categories` WRITE;
/*!40000 ALTER TABLE `product_categories` DISABLE KEYS */;
INSERT INTO `product_categories` VALUES (1,'Обувки','Shoes',NULL),(2,'Дрехи','Clothes',NULL),(3,'Скейтборд','Skateboards',NULL),(4,'Аксесоари','Accessories',NULL),(5,'Мъжки обувки','Man\'s shoes',1),(6,'Дамски обувки','Woman\'s shoes',1),(7,'Тениски','T-Shirts',2),(8,'Качулки','Sweatshirts',2),(9,'Дъски','Boards',3),(10,'Комплекти','Full Setup',3),(11,'Колесници','Trucks',3),(12,'Колелца','Wheels',3),(13,'Чорапи','Socks',4),(14,'Слънчеви очила','Sunglasses',4),(15,'Шапки','Hats',4),(16,'Рейнболс','Rainbows',4);
/*!40000 ALTER TABLE `product_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_images`
--

DROP TABLE IF EXISTS `product_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_images` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `image_url` varchar(255) NOT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqnq71xsohugpqwf3c9gxmsuy` (`product_id`),
  CONSTRAINT `FKqnq71xsohugpqwf3c9gxmsuy` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_images`
--

LOCK TABLES `product_images` WRITE;
/*!40000 ALTER TABLE `product_images` DISABLE KEYS */;
INSERT INTO `product_images` VALUES (1,'https://drive.google.com/uc?id=1TAIux7jRqOROiRbpnAU4_NxIE4cqhKvb&export=download',1),(2,'https://drive.google.com/uc?id=1gQq-WewEzCZnPa6n-gMdU07SiwArY0V0&export=download',6),(3,'https://drive.google.com/uc?id=10bpnfKKs40Zq0CU2GHIWP5KcmqqF0Ch8&export=download',6),(4,'https://drive.google.com/uc?id=1yW32JtL7dtPjdWPvL_zMnIO8qtmcAbr3&export=download',6),(5,'https://drive.google.com/uc?id=1dEfr17GS32gxC-eerz-CjtfgxjbN335G&export=download',7),(6,'https://drive.google.com/uc?id=1H_TU7kCT6R8aKgcCBnkwxWeUIhC-6dN2&export=download',7),(7,'https://drive.google.com/uc?id=1ou96sjFgfwcakGcj2JaTlRn8qxRY94-3&export=download',7),(8,'https://drive.google.com/uc?id=1CZ0txhRTDDVVr0m9rm6D-uk-K1C4oQ7E&export=download',8),(9,'https://drive.google.com/uc?id=101iLxGqBvhegtKqhM57HvcOzo9dvbPqR&export=download',8),(10,'https://drive.google.com/uc?id=1nGZNf4S2GccgihfLaf_I12hw8IQC82Lh&export=download',8),(11,'https://drive.google.com/uc?id=1bM3ZsV-ArhLgB5OUXF7U0qncg9Ri9UNu&export=download',9),(12,'https://drive.google.com/uc?id=19Tvns3SJYaU_7QcgkbVyCGBbh8G0II_o&export=download',9),(13,'https://drive.google.com/uc?id=1nQYR1AUBKRqHtVj_w7yC2PE-XOm38ohi&export=download',10),(14,'https://drive.google.com/uc?id=1elBQkM2KDsJ4xrovJscYg4Hca0C-ZM7J&export=download',10),(15,'https://drive.google.com/uc?id=1AeiM7ZHnQhwl1piT8iwaZ6IVGXHT1pZh&export=download',10);
/*!40000 ALTER TABLE `product_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `dtype` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` text,
  `image` varchar(255) DEFAULT NULL,
  `is_enabled` bit(1) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `product_name` varchar(255) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `weekly_views` int(11) DEFAULT NULL,
  `brand_id` bigint(20) NOT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa3a4mpsfdf4d2y6r8ra3sc8mv` (`brand_id`),
  KEY `FK6t5dtw6tyo83ywljwohuc6g7k` (`category_id`),
  CONSTRAINT `FK6t5dtw6tyo83ywljwohuc6g7k` FOREIGN KEY (`category_id`) REFERENCES `product_categories` (`id`),
  CONSTRAINT `FKa3a4mpsfdf4d2y6r8ra3sc8mv` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES ('Product',1,'Състав:\r\n100% памук','https://drive.google.com/uc?id=1GoSZocMqe3Fs1SGDngi-OORMNzj5pU3a&export=download','',10,'ДЕТСКА ТЕНИСКА INDEPENDENT OE CROSS',4,'Black M',8,4,7),('Product',2,'','https://drive.google.com/uc?id=1Uc790q8p55hY7UZRANqriWkxdY8-qjKC&','',38,'ТЕНИСКА DC ENDLESS FRONTIE',9,'Black S',5,7,7),('Product',3,'','https://drive.google.com/uc?id=1Qk_H9nIpnE2DGzhbALP-wCSf6_5OGJU-&export=download','',68,'КАЧУЛКА DC ELLIS PH',1,'Black M',0,7,8),('Product',4,'','https://drive.google.com/uc?id=1Eq_5OoBHKLrv1NEg8LBB3rNsaEd0H5Od&export=download','',134.12,'КАЧУЛКА DC REBEL STAR ZIP',1,'RED: M',1,7,8),('Product',5,'','https://drive.google.com/uc?id=1r4je01QE2GoZuaBbqsDnDnyNUJ1symu-&export=download','',134,'КАЧУЛКА DC REBEL STAR ZIP',1,'Gray: M',1,7,8),('Product',6,'Състав:\r\nВъншна част: естествена кожа и текстил\r\nВътрешна част: синтетика\r\nПодметка: каучук\r\n \r\n\r\nЗабележка: От вътрешната част на обувката има отвори за проветрение.','https://drive.google.com/uc?id=1ZtnKc8L4EaiJ-pt6DfC6_xoB0NcQG0nC&export=download','',100,'ОБУВКИ DC EVAN SMITH',2,'44',4,7,5),('Product',7,'Състав:\r\nВъншна част: синтетика и текстил\r\nВътрешна част: синтетика\r\nПодметка: каучук','https://drive.google.com/uc?id=1ZWNhlL8h__-xUBQ64PcdPyZat-nCbwga&export=download','',134,'ОБУВКИ DC HEATHROW',2,'44.5',3,7,5),('Product',8,'Състав:\r\nВъншна част: естествена кожа и текстил\r\nВътрешна част: синтетика\r\nПодметка: каучук','https://drive.google.com/uc?id=1QUOicNVnuPsyYiNXNgTXKcC1071S_qpN&export=download','',128,'ОБУВКИ DC LYNNFIELD S',1,'43',10,7,5),('Product',9,'Състав:\r\nВъншна част: естествена кожа и текстил\r\nВътрешна част: синтетика\r\nПодметка: каучук','https://drive.google.com/uc?id=1G7xXl-XJi-v8P7DzOCCpIgK7heG6Msyy&export=download','',128,'ОБУВКИ DC SWITCH S',1,'44',2,7,5),('Product',10,'Състав:\r\nВъншна част: текстил\r\nВътрешна част: синтетика\r\nПодметка: каучук','https://drive.google.com/uc?id=1BOlPMaMwKZo2ljk-isDYKGGGFAoqkD5V&export=download','',89,'ОБУВКИ DC TRASE TX',1,'42',6,7,5),('Product',11,'','https://drive.google.com/uc?id=1lgxDOq6jzoDnNDn6_Ae4u9AhlsjcUq4B&export=download','',118,'СКЕЙТБОРД ДЪСКА JART PEACE 8',2,'8',10,10,9),('Product',12,'','https://drive.google.com/uc?id=1jyegQi3jJbeANbg381v0BnvxrY783wTR&export=download','',90,'СКЕЙТБОРД ДЪСКА JART REINAISSANCE 8',2,'8',1,10,9),('Product',13,'Скейт дъска Insomnia Movie Freak 7.5\" е от серията Insomnia Revolution на магазин Insomnia и е произведена във фабриката на JART в Испания.','https://drive.google.com/uc?id=1EsR053xOahEms45rESaL2AhpTwU_Twjp&export=download','',50,'ДЪСКА INSOMNIA MOVIE FREAK 7.5',10,'7.5',0,8,9),('Product',14,'','https://drive.google.com/uc?id=17WT8exnkrBV7qX1qLYez8U_2_58X79q2&export=download','',50,'ДЪСКА INSOMNIA MAPS LOGO 7.5',10,'7.5',0,8,9),('Product',16,'','https://drive.google.com/uc?id=18sqy1aw408RUzxBERP99C6qegbsY74ud&','',90,'СКЕЙТБОРД ДЪСКА JART I WANT TO BELIEVE 7.87',10,'7.8',2,10,9),('Product',17,'All time greatest skaters\r\n     ->Garrett Hill\r\n     ->Chris Cole','https://drive.google.com/uc?id=1M_wzh1GOgnUGVY6xgQXlbRbXBEowMsvV&','',118,'Zero Team editio',6,'7.75',14,9,9),('Product',18,'','https://drive.google.com/uc?id=1G_1lEYT1h9-fkd9hf2_xSUXVZCMfZxvt&','',36,'ЧОРАПИ INDEPENDENT COLOURED STRIPES',10,'',2,4,13),('Product',19,'Състав:  \r\n40% Coolmax\r\n40% Памук\r\n18% Найлон\r\n2% ЛИкра','https://drive.google.com/uc?id=1hTSaWBIcWh_62ZmcDM4LZf9TBpzwPumt&','',28,'ЧОРАПИ INDEPENDENT TRUCK CO WI14',10,'',0,4,13),('Product',20,'','https://drive.google.com/uc?id=1xIiOICo1qZtyj8UFJx45OzCngNKZdlTh&','',18,'ЧОРАПИ INDEPENDENT FINISHLINE',0,'',2,4,13),('Product',21,'absolute\r\n           mad\r\n                  (lad)','https://drive.google.com/uc?id=1aZfPZMcAPao7hPBrvm0pSDEcE7sPTZk2&','',2001.25,'Hot ass rainbow',0,'EXTRA SMALL',6,5,16),('Product',22,'1 rog \r\ngotena macka','https://drive.google.com/uc?id=1DaKbe8z4HpATHFxcgdx3mo0ToY_Vzpwx&','',20,'Rainbow six seige',1,'',18,9,16),('Product',23,'LIKE A\r\n        RAINBOW IN THE\r\n             daaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\r\n            rrrrrrrrrrrrrrrrrrrrrrrkkkkkkkk\r\nYEEEEAH','https://drive.google.com/uc?id=1V2T53bk1pKv-K98GJD7yPP1mhUmKXu8u&','',102,'Rainbow in the dark',1,'',2,9,16),('Product',24,'','https://drive.google.com/uc?id=1yth_5iC9i31m1qMY-_DZwIDNmdCgjeN9&','',19,'Rainbow in the forest',0,'',4,5,16),('Product',25,'Скейтборд дъска Element.\r\nРазмер:\r\n- Width 8.3125\".\r\n- Length 32.1875\".\r\nДопълнителна информация:\r\n- Премиум 7-пластова кленова конструкция.\r\n- Лека и гъвкъва дъска.','https://drive.google.com/uc?id=1eQQP1OO-FbfgFIq2ZVAFA04rVBMp-IVz&','',127,'Element Mason Owl',10,'8.3',0,11,9),('Product',26,'Скейтборд дъска Element.\r\nРазмер:\r\n- Width 8.25\".\r\n- Length 31.933\".\r\nДопълнителна информация:\r\n- Премиум 7-пластова кленова конструкция.\r\n- Лека и гъвкъва дъска.','https://drive.google.com/uc?id=1oA_lQq_wjrou8RxyPQaDx-pBx4QvXwB-&','',127,' Element Barbee Goodwin',5,'8.2',1,11,9),('Product',27,'Скейтборд дъска Element.\r\nРазмер:\r\n- Width 8”.\r\n- Length 31.75\"\r\nДопълнителна информация:\r\n- Премиум 7-пластова кленова конструкция.\r\n- Лека и гъвкъва дъска.','https://drive.google.com/uc?id=15qSeXaIDYqS4eQtEAgdM_8D9BWY-kiIs&','',127,'Element YAWYE Orange',5,'8',2,11,9),('Product',28,'Скейтборд комплект Element.\r\nРазмер:\r\n- Width 8”.\r\n- Length 31.75\"\r\nДопълнителна информация:\r\n- Машинки: Element\r\n- Колелца: Element 52mm\r\n- Лагери: Abec 5','https://drive.google.com/uc?id=1H5yXlKWDRvWW0OYe35vbTdnZ_3PCHbGE&','',190,'Element Hatched Rasta 8 Skateboard Complete ',0,'8',2,11,10),('Product',30,'Цената е за 2бр.','https://drive.google.com/uc?id=1yaMceu_AUugheVrueuiX-9k4nsN6KqBK&','',128,'КОЛЕСАРИ INDEPENDENT STAGE 11 SILVER POLISHED STANDARD (e)',5,'215mm',1,4,11);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority` int(11) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `UK_r261muslviw4d89p3xlvagqof` (`authority`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,0),(2,1),(3,2);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shopping_carts`
--

DROP TABLE IF EXISTS `shopping_carts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shopping_carts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `serialized_products` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3iw2988ea60alsp0gnvvyt744` (`user_id`),
  CONSTRAINT `FK3iw2988ea60alsp0gnvvyt744` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopping_carts`
--

LOCK TABLES `shopping_carts` WRITE;
/*!40000 ALTER TABLE `shopping_carts` DISABLE KEYS */;
INSERT INTO `shopping_carts` VALUES (1,'{\"22\":1,\"23\":1}',1),(2,'{\"17\":1,\"22\":1,\"11\":1}',3),(18,'{}',2),(19,'{}',4);
/*!40000 ALTER TABLE `shopping_carts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `is_account_non_expired` bit(1) DEFAULT NULL,
  `is_account_non_locked` bit(1) DEFAULT NULL,
  `is_credentials_non_expired` bit(1) DEFAULT NULL,
  `is_enabled` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `registered_on` datetime DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'ceci2205@abv.bg','','','','','$2a$10$x8uNhQ9U/gJaKGF62ySLBexIwy/pda0FvEqSBoHCeE5XuUaty69ie','2018-08-19 15:15:58','cyecize'),(2,'smoke@abv.bg','','','','','$2a$10$4n.5HoKlCwABgjar0/Tk2eqPZBfB3/i7NH07gFjDcDt.hZxcYqvaK','2018-08-19 15:16:32','elsmokio'),(3,'bosa@abv.bg','','','','','$2a$10$cUSz20EQq2SI/nXk/Ie6j.pwF7iWs0CpZ98OfCNQpRnA4sqfXwNyG','2018-08-19 15:16:42','bosa'),(4,'cyecizefake@abv.bg','','','','','$2a$10$jVoZuZ5qpB/GpqiOWQPWTOS1Hxf6RQJjlgbDUKmzcIQ5s9s0PvhdK','2018-08-21 22:18:22','zzfake');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`),
  KEY `FK2o0jvgh89lemvvo17cbqvdxaa` (`user_id`),
  CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (1,1),(1,2),(1,3),(2,3),(4,3),(3,3),(3,2);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'skate_fixers'
--

--
-- Dumping routines for database 'skate_fixers'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-24 23:48:52
