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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banners`
--

LOCK TABLES `banners` WRITE;
/*!40000 ALTER TABLE `banners` DISABLE KEYS */;
INSERT INTO `banners` VALUES (1,'http://www.spulsa.info/wp-content/uploads/skateboard-banners-nike-sb-team-banner-forty-two-skateboard-shop.jpg','\0','Skate remontiori v1','https://www.facebook.com/'),(2,'http://www.latabla.cl/wp-content/uploads/2016/03/DcShoes-EvanSmith2.jpg','\0','Nekvi shemi','https://www.facebook.com/'),(3,'https://images.tcdn.com.br/img/img_prod/481988/1509493378_banner_element.jpg','\0','New Cat skater just found','https://www.facebook.com/ceci2205');
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brands`
--

LOCK TABLES `brands` WRITE;
/*!40000 ALTER TABLE `brands` DISABLE KEYS */;
INSERT INTO `brands` VALUES (1,'Element','https://drive.google.com/uc?id=1wh_rIU20SslhIWhtOqJSYkLX9kyeUMBg&export=download'),(2,'DC','http://cdn2.bigcommerce.com/server1500/ac84d/products/523/images/1160/SPO-DCS-001e__68332.1315556717.380.380.jpg?c=2'),(3,'Nike Sb','http://ipadflava.com/wp-content/uploads/nike-sb-logo1.jpg'),(4,'Zero','https://zeroskateboards.com/wp-content/uploads/2015/08/zero-logo-fb.jpg'),(5,'Fallen','https://orig00.deviantart.net/363c/f/2007/248/b/a/fallen_rxwxtxfx_by_adrak.jpg'),(6,'Insomnia','https://yt3.ggpht.com/a-/AJLlDp3vadlpSGGhoKgW41AW6mD6vjjQNcydBdUAxg=s900-mo-c-c0xffffffff-rj-k-no'),(7,'Bones','https://www.warehouseskateboards.com/images/products/preview/1WBONROGWLF54WK.jpg'),(8,'Independent','https://drive.google.com/uc?id=1SOJK3X-wjr5fZWqoX2PzJUIg2AnESF9D&export=download'),(9,'Royal Trucks','https://drive.google.com/uc?id=1_CFVxIDeE1__phXK-kEDNWOB9gIpw4QI&export=download'),(10,'Slaved','https://drive.google.com/uc?id=10i2EfiYBNKp7lijuDeoJ-_f2dqfNfdGR&export=download');
/*!40000 ALTER TABLE `brands` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_categories`
--

LOCK TABLES `product_categories` WRITE;
/*!40000 ALTER TABLE `product_categories` DISABLE KEYS */;
INSERT INTO `product_categories` VALUES (1,'Обувки','Shoes',NULL),(2,'Дрехи','Clothes',NULL),(3,'Скейтборд','Skateboards',NULL),(4,'Аксесоари','Accessories',NULL),(5,'Дъги','Rainbows',4),(6,'Портмонета','Wallets',4),(13,'Слънчеви очила','Sunglasses',4);
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_images`
--

LOCK TABLES `product_images` WRITE;
/*!40000 ALTER TABLE `product_images` DISABLE KEYS */;
INSERT INTO `product_images` VALUES (1,'https://s-i.huffpost.com/gen/1326478/images/o-RAINBOW-facebook.jpg',8),(2,'https://cdn.thinglink.me/api/image/741719476105379840/1240/10/scaletowidth',8),(3,'https://cdn.shopify.com/s/files/1/1890/6475/products/product-image-359298695_530x.jpg?v=1513239599',8),(4,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQxEadkp5FnpEUc94xJGXcTLiRqbcBj8b6OjxwWbRucyAsu4fZh',8),(5,'https://insomniacollective.com/media/7/11052.png',8),(6,'https://insomniacollective.com/media/7/9182.jpg',8),(7,'https://zatvarachki.com/product-gallery/categories/Avtomatichni%20zatvarachki/1512840224Optimized-15128401811.jpg',8);
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES ('Product',1,'<p>Quite a good board </p>','https://insomniacollective.com/media/7/11052.png','',90,'СКЕЙТ ДЪСКА CHOCOLATE ALVAREZ CITY COWBOYS 8\" + ШКУРКА',11,'8',55,1,3),('Product',2,'Insane board with kanadski klen','https://insomniacollective.com/media/7/11010.png','',120,'СКЕЙТБОРД ДЪСКА GIRL MALTO 93 TIL 7.75\" + ШКУРКА и допълнителни колелца и като цяло много други неша, може да попитате',10,'8',13,6,3),('Product',3,'Quality Indi socks man','https://insomniacollective.com/media/7/3790.jpg','',18,'ЧОРАПИ INDEPENDENT FINISHLINE- 2 БР.',2,'standard',2,5,4),('Product',4,'Very cool hat mane','https://insomniacollective.com/media/7/8217.jpg','',36,'ЗИМНА ШАПКА OSIRIS CLIP',11,NULL,16,2,4),('Product',5,'Характеристики на скейтборд комплекта:\r\n\r\nConcave: Medium\r\n\r\nРазмер: 7.6\" x 32\"\r\n\r\nСъстав: Hard Rock Maple - 7 Plies\r\n\r\nКолесари: Classic 5.0\r\n\r\nКолела: 52mm - 100A\r\n\r\nЛагери: Abec 5','https://insomniacollective.com/media/7/9261.jpg','',118,'СКЕЙТБОРД КОМПЛЕКТ ALOIKI TRIANGLE MC 7.6',2,'8.1',5,6,3),('Product',6,'Състав:\r\nВъншна част: текстил\r\nВътрешна част: синтетика\r\nПодметка: каучук\r\n \r\n\r\nЗабележка: От вътрешната част на обувката има отвори за проветрение.','https://insomniacollective.com/media/7/10419.jpg','',106,'ОБУВКИ DC EVAN SMITH TX SE',13,NULL,73,2,1),('Product',7,'Състав:\r\nВъншна част: текстил и синтетична кожа\r\nВътрешна част: синтетика\r\nПодметка: каучук','https://insomniacollective.com/media/7/9182.jpg','',77,'ОБУВКИ DVS AVERSA ',2,NULL,59,5,1),('Product',8,'Характеристики на скейтборд комплекта:\r\n\r\nConcave: Medium\r\n\r\nРазмер: 7.6\" x 32\"\r\n\r\nСъстав: Hard Rock Maple - 7 Plies\r\n\r\nКолесари: Classic 5.0\r\n\r\nКолела: 52mm - 100A\r\n\r\nЛагери: Abec 5','https://thechive.files.wordpress.com/2018/03/weekend-morning-awesomeness-36-photos-171.jpg?quality=85&strip=info&w=600','\0',991.249,'Girl ass rainbow',1,'small',152,7,5),('Product',9,'Rainbow in the forest','https://static.boredpanda.com/blog/wp-content/uploads/2016/10/best-action-photos-2016-red-bull-illume-48-57f6150f74455__880.jpg','',119.363,'Another rainbow',2,NULL,542,7,5),('Product',10,'Rainbow girl\r\nvery naas\r\n    -> good girl\r\n    -> good rainbow \r\n-----> what more do y want','https://st2.depositphotos.com/6367796/9267/v/950/depositphotos_92675558-stock-illustration-pop-art-rainbow-unicorn-woman.jpg','',19.9,'Rainbow six seige',1,'Non xiztent',9910,4,5),('Product',11,'Like a \r\n      RAINBOW \r\n      INN\r\n      THE \r\nDAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\r\nRRRRRKKKK\r\n\r\nYEAAAAAHH','https://ninecirclesblog.files.wordpress.com/2015/06/ronnie-james-dio.jpg','',200,'Rainbow in the dark mate',12,'World wide',25,4,5),('Product',12,'','https://orig00.deviantart.net/a14a/f/2010/264/b/8/rainbow_forest_by_twilightxgirl-d2z7jx6.png','',20,'Rainbow in the Forest',10,'',27,4,5),('Product',14,'','https://drive.google.com/uc?id=1W8HFVSlmPbYHx3II2fNtbM-JCcFvk1gG&export=download','',24,'СЛЪНЧЕВИ ОЧИЛА INDEPENDENT FOOLIN',10,'',3,8,13);
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
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'ceci2205@abv.bg','','','','','$2a$10$D9bUa9j3115cP3snFx8z/uWLKU8WiZMtje7wy2o3AVnTxXHGU3aDa','cyecize'),(2,'smoke@abv.bg','','','','','$2a$10$6kpNxDxIqnXYz6FksJaJb.5JQ99yAwjTFBFV1V48RLv5B9ljI/nia','elsmokio'),(3,'bosa@abv.bg','','','','','$2a$10$bMDCVpxG7FtQQE8zYKJgD.IfVM.r1ZYcmfT6s5bIZdIvF3ANB9Dpa','bosa');
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
INSERT INTO `users_roles` VALUES (1,1),(1,2),(1,3),(2,3),(3,3),(3,2);
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

-- Dump completed on 2018-08-17 23:39:15
