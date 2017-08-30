/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.13-log : Database - another_note
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`another_note` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `another_note`;

/*Table structure for table `a_stroke` */

DROP TABLE IF EXISTS `a_stroke`;

CREATE TABLE `a_stroke` (
  `ID` varchar(50) NOT NULL,
  `Title` varchar(255) NOT NULL,
  `Content` text,
  `StrokePlanID` varchar(50) DEFAULT NULL,
  `ForUserID` varchar(50) DEFAULT NULL,
  `CreateTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `StartTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `EndTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `State` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `a_user` */

DROP TABLE IF EXISTS `a_user`;

CREATE TABLE `a_user` (
  `ID` varchar(50) NOT NULL,
  `Phone` varchar(50) NOT NULL,
  `UserName` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UNIQUE_PHONE` (`Phone`),
  UNIQUE KEY `UNIQUE_NAME` (`UserName`),
  UNIQUE KEY `UNIQUE_EMAIL` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
