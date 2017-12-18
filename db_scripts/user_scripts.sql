
/* User table */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `about` varchar(255) DEFAULT NULL,
  `address1` varchar(255) DEFAULT NULL,
  `address2` varchar(255) DEFAULT NULL,
  `changebleRole` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `dateofbirth` date DEFAULT NULL,
  `dynamic_access_code` varchar(255) DEFAULT NULL,
  `dynamic_status` tinyint(1) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  `facbookUser` tinyint(1) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `mobile_no` varchar(255) DEFAULT NULL,
  `modifiedImage` varchar(255) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `profileImage` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `userName` varchar(200) DEFAULT NULL,
  `userRole` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `zipCode` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `userId` (`userId`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `userName` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=100005 DEFAULT CHARSET=utf8;


LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,NULL,NULL,NULL,NULL,'Bangalore','India',NULL,NULL,'829547',1,'omprakash.reddy@frescano.se',1,0,'Omprakash','reddy','9900905120',NULL,'12345678','Bangalore',NULL,'Karnataka','9900905120','ROLE_CUSTOMER',NULL,NULL,NULL),
(6,NULL,NULL,NULL,NULL,NULL,NULL,'2017-10-25 12:58:35',NULL,'752505',1,'babu.devandla@frescano.se',1,0,'Ramu','krishna','9036159989',NULL,'1234567',NULL,NULL,NULL,'9036159989','ROLE_CUSTOMER',NULL,NULL,NULL),
(100001,NULL,NULL,NULL,NULL,NULL,NULL,'2017-10-27 18:08:39',NULL,'728442',1,'babu.devandla1@frescano.se',1,0,'Babu','devandla','9901244023',NULL,'123456',NULL,NULL,NULL,'9901244023','ROLE_CUSTOMER',NULL,NULL,NULL),
(100002,NULL,NULL,NULL,NULL,'Bangalore','IND','2017-11-16 15:58:26',NULL,NULL,0,'vijaya.kumar@frescano.se',0,0,'lakshman','test',NULL,NULL,'123456',NULL,NULL,NULL,NULL,'ROLE_CUSTOMER',NULL,NULL,NULL),
(100004,NULL,NULL,NULL,NULL,'Bangalore','IND','2017-11-16 16:12:57',NULL,'27756',1,'rers@gmail.com',1,0,'raavi','kumar','8792137934',NULL,'123456',NULL,NULL,NULL,'8792137934','ROLE_CUSTOMER',NULL,NULL,NULL);

/* Role Table */
DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`roleId`),
  UNIQUE KEY `roleId` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


INSERT INTO `role` VALUES (1,'ROLE_CUSTOMER');


 /* User Role table */
DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  UNIQUE KEY `userId_UNIQUE` (`userId`),
  KEY `FK143BF46A320E3D95` (`roleId`),
  KEY `FK143BF46A8BFEECE0` (`userId`),
  CONSTRAINT `FK143BF46A320E3D95` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`),
  CONSTRAINT `FK143BF46A8BFEECE0` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `user_role` VALUES (1,1),(6,1),(100001,1),(100004,1);
