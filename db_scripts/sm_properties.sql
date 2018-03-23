
--- 
--- sm properties
---

DROP TABLE IF EXISTS `sm_properties`;

CREATE TABLE `sm_properties` (
  `propertyid` int(11) NOT NULL AUTO_INCREMENT,
  `comments` varchar(255) DEFAULT NULL,
  `createdon` datetime DEFAULT NULL,
  `datatype` varchar(255) DEFAULT NULL,
  `prop_categoryid` int(11) DEFAULT NULL,
  `propkey` varchar(255) DEFAULT NULL,
  `propvalue` varchar(255) DEFAULT NULL,
  `updatedby` varchar(255) DEFAULT NULL,
  `updatedon` datetime DEFAULT NULL,
  PRIMARY KEY (`propertyid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


--
-- Dumping data for table `sm_properties`
--


INSERT INTO `sm_properties` VALUES (1,' document located path','2017-11-02 14:56:45','string',0,'UPLOAD_USER_FILE_PATH','D:/Reports/SM_Docs','admin',NULL);


CREATE TABLE `unique_key` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `uniqueProperty` varchar(45) NOT NULL,
  `uniqueValue` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

