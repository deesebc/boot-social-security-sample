-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.1.21-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando estructura para tabla conmigodb.user
CREATE TABLE IF NOT EXISTS `user` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) DEFAULT NULL,
  `FIRST_NAME` varchar(50) DEFAULT NULL,
  `LAST_NAME` varchar(50) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `USERNAME` varchar(50) DEFAULT NULL,
  `PASSWORD` varchar(100) DEFAULT NULL,
  `ENABLE` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `EMAIL` (`EMAIL`),
  KEY `USER_IDX` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='Tabla de Usuarios';


-- Volcando estructura para tabla conmigodb.userconnection
CREATE TABLE IF NOT EXISTS `userconnection` (
  `userId` varchar(255) NOT NULL,
  `providerId` varchar(255) NOT NULL,
  `providerUserId` varchar(255) NOT NULL,
  `rank` int(11) NOT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  `profileUrl` varchar(512) DEFAULT NULL,
  `imageUrl` varchar(512) DEFAULT NULL,
  `accessToken` varchar(1024) NOT NULL,
  `secret` varchar(255) DEFAULT NULL,
  `refreshToken` varchar(255) DEFAULT NULL,
  `expireTime` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`userId`,`providerId`,`providerUserId`),
  UNIQUE KEY `UserConnectionRank` (`userId`,`providerId`,`rank`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Volcando estructura para tabla conmigodb.userprofile
CREATE TABLE IF NOT EXISTS `userprofile` (
  `userId` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `UserProfilePK` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40000 ALTER TABLE `userprofile` DISABLE KEYS */;
/*!40000 ALTER TABLE `userprofile` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
