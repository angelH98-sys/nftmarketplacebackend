CREATE TABLE nftmarketplacedb;

USE nftmarketplacedb;

CREATE TABLE `users` (
  `userid` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(200) NOT NULL,
  `fullname` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `userstatus` varchar(10) NOT NULL,
  `usertype` varchar(10) NOT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
);

CREATE TABLE `usercontraints` (
  `constraintid` int NOT NULL AUTO_INCREMENT,
  `userid` int NOT NULL,
  `constraintname` varchar(20) NOT NULL,
  `constraintvalue` varchar(100) NOT NULL,
  PRIMARY KEY (`constraintid`)
);

CREATE TABLE `projects` (
  `projectid` int NOT NULL AUTO_INCREMENT,
  `userid` int NOT NULL,
  PRIMARY KEY (`projectid`)
);

CREATE TABLE `usersubscriptions` (
  `subscriptionid` int NOT NULL AUTO_INCREMENT,
  `userid` int NOT NULL,
  `projectid` int NOT NULL,
  `subscriptionstatus` varchar(10) NOT NULL,
  PRIMARY KEY (`subscriptionid`)
);

CREATE TABLE `projectspecifications` (
  `specificationid` int NOT NULL AUTO_INCREMENT,
  `projectid` int NOT NULL,
  `projectname` varchar(50) NOT NULL,
  `projectdescription` varchar(1000) NOT NULL,
  `projectstatus` varchar(10) NOT NULL,
  PRIMARY KEY (`specificationid`)
);

CREATE TABLE `projectmedia` (
  `mediaid` int NOT NULL AUTO_INCREMENT,
  `specificationid` int NOT NULL,
  `urlwebsite` varchar(1000) NOT NULL,
  `urlimage` varchar(1000) NOT NULL,
  `urlvideo` varchar(1000) NOT NULL,
  PRIMARY KEY (`mediaid`)
);

CREATE TABLE `projectinvestment` (
  `investmentid` int NOT NULL AUTO_INCREMENT,
  `projectid` int NOT NULL,
  `currentinvestment` decimal(32,2) NOT NULL,
  `pendinginvestment` decimal(32,2) NOT NULL,
  `totalinvestment` decimal(32,2) NOT NULL,
  PRIMARY KEY (`investmentid`)
);

CREATE TABLE `projectcurrency` (
  `currencyid` int NOT NULL AUTO_INCREMENT,
  `investmentid` int NOT NULL,
  `nativecurrencyid` int NOT NULL,
  `blockchainid` int NOT NULL,
  PRIMARY KEY (`currencyid`)
);

CREATE TABLE `blockchain` (
  `blockchainid` int NOT NULL AUTO_INCREMENT,
  `blockchainame` varchar(30) NOT NULL,
  PRIMARY KEY (`blockchainid`)
);

CREATE TABLE `nativecurrency` (
  `nativecurrencyid` int NOT NULL AUTO_INCREMENT,
  `currencyname` varchar(50) NOT NULL,
  PRIMARY KEY (`nativecurrencyid`)
);

CREATE TABLE `projectdates` (
  `projectdateid` int NOT NULL AUTO_INCREMENT,
  `projectid` int NOT NULL,
  `creationdate` date NOT NULL,
  `testingdate` date NOT NULL,
  `releasedate` date NOT NULL,
  PRIMARY KEY (`projectdateid`)
);

CREATE TABLE `projectmembers` (
  `memberid` int NOT NULL AUTO_INCREMENT,
  `projectid` int NOT NULL,
  `membername` varchar(50) NOT NULL,
  `urlprofile` varchar(1000) NOT NULL,
  `memberstatus` varchar(10) NOT NULL,
  PRIMARY KEY (`memberid`)
);
