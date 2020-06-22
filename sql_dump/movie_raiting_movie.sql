CREATE DATABASE  IF NOT EXISTS `movie_raiting` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `movie_raiting`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: movie_raiting
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `uid` varchar(100) NOT NULL,
  `image_link` varchar(255) NOT NULL,
  `name` varchar(100) NOT NULL,
  `year` year NOT NULL,
  `budget` bigint NOT NULL,
  `fees` bigint NOT NULL,
  `duration` time NOT NULL,
  `rating` decimal(4,3) NOT NULL DEFAULT '0.000',
  `added` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `description` text NOT NULL,
  `fk_filmmaker_id` int NOT NULL,
  `fk_genre_id` int NOT NULL,
  `fk_category_id` int NOT NULL,
  `fk_country_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_filmmaker_id_idx` (`fk_filmmaker_id`),
  KEY `fk_genre_id_idx` (`fk_genre_id`),
  KEY `fk_category_id_idx` (`fk_category_id`),
  KEY `fk_country_id_idx` (`fk_country_id`),
  CONSTRAINT `fk_category_id` FOREIGN KEY (`fk_category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_country_id` FOREIGN KEY (`fk_country_id`) REFERENCES `country` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_filmmaker_id` FOREIGN KEY (`fk_filmmaker_id`) REFERENCES `filmmaker` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_genre_id` FOREIGN KEY (`fk_genre_id`) REFERENCES `genre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=194 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `update_user_rating` AFTER UPDATE ON `movie` FOR EACH ROW BEGIN 
	set @total_votes = (SELECT COUNT(`comment`.`id`) FROM `comment` where `comment`.`fk_movie_id` = NEW.id);
	set @is_update_rating = IF(MOD(@total_votes,2) > 0, false, true);
        if @is_update_rating then
        update `user` set `user`.`rating` =  `user`.`rating` + 1
        where `user`.`id` in (SELECT distinctrow `comment`.`fk_user_id` FROM `comment` where `comment`.`fk_movie_id` = NEW.id)
        and (SELECT distinctrow `comment`.`rating` FROM `comment` where `comment`.`fk_movie_id` = NEW.id and `comment`.`fk_user_id` = `user`.`id`) between NEW.`rating`-1 and NEW.`rating`+1;
       
       update `user` set `user`.`rating` =  `user`.`rating` - 1
        where `user`.`id` in (SELECT distinctrow `comment`.`fk_user_id` FROM `comment` where `comment`.`fk_movie_id` = NEW.id)
        and abs(NEW.`rating` - (SELECT distinctrow `comment`.`rating` FROM `comment` where `comment`.`fk_movie_id` = NEW.id and `comment`.`fk_user_id` = `user`.`id`)) > 2;

       end if;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-22 17:38:19
