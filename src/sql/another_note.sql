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

/*Table structure for table `a_permission` */

DROP TABLE IF EXISTS `a_permission`;

CREATE TABLE `a_permission` (
  `ID` varchar(50) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Permission` varchar(255) DEFAULT NULL,
  `Available` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `a_permission` */

insert  into `a_permission`(`ID`,`Name`,`Permission`,`Available`) values ('permission1','创建用户','user:*',1),('permission2','删除用户','user:del',0);

/*Table structure for table `a_role` */

DROP TABLE IF EXISTS `a_role`;

CREATE TABLE `a_role` (
  `ID` varchar(50) NOT NULL,
  `Role` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Available` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `a_role` */

insert  into `a_role`(`ID`,`Role`,`Description`,`Available`) values ('role1','admin','管理员',1),('role2','user','普通用户',1);

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

/*Data for the table `a_stroke` */

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

/*Data for the table `a_user` */

insert  into `a_user`(`ID`,`Phone`,`UserName`,`Password`,`Email`) values ('4979bd0b896a11e780b84ccc6a6d905e','123','lee','123','123'),('bf17f23d88aa11e7a65d4ccc6a6d905e','1234','456','789','11');

/*Table structure for table `m_role_permission` */

DROP TABLE IF EXISTS `m_role_permission`;

CREATE TABLE `m_role_permission` (
  `RoleID` varchar(50) NOT NULL,
  `PermissionID` varchar(50) NOT NULL,
  PRIMARY KEY (`RoleID`,`PermissionID`),
  KEY `KEY_Permission` (`PermissionID`),
  CONSTRAINT `KEY_Permission` FOREIGN KEY (`PermissionID`) REFERENCES `a_permission` (`ID`),
  CONSTRAINT `KEY_Role` FOREIGN KEY (`RoleID`) REFERENCES `a_role` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `m_role_permission` */

insert  into `m_role_permission`(`RoleID`,`PermissionID`) values ('role1','permission1'),('role1','permission2');

/*Table structure for table `m_user_role` */

DROP TABLE IF EXISTS `m_user_role`;

CREATE TABLE `m_user_role` (
  `UserID` varchar(50) NOT NULL,
  `RoleID` varchar(50) NOT NULL,
  PRIMARY KEY (`UserID`,`RoleID`),
  KEY `KEY_Role_User` (`RoleID`),
  CONSTRAINT `KEY_Role_User` FOREIGN KEY (`RoleID`) REFERENCES `a_role` (`ID`),
  CONSTRAINT `KEY_User_Role` FOREIGN KEY (`UserID`) REFERENCES `a_user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `m_user_role` */

insert  into `m_user_role`(`UserID`,`RoleID`) values ('4979bd0b896a11e780b84ccc6a6d905e','role1'),('4979bd0b896a11e780b84ccc6a6d905e','role2');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
