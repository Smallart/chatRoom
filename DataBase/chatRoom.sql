/*
Navicat MySQL Data Transfer

Source Server         : LocalHost_Demo
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : chatroom

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2019-05-29 10:39:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ginfo
-- ----------------------------
DROP TABLE IF EXISTS `ginfo`;
CREATE TABLE `ginfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `gid` int(11) NOT NULL,
  `message` text,
  `infotime` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  KEY `gid` (`gid`),
  CONSTRAINT `ginfo_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`id`),
  CONSTRAINT `ginfo_ibfk_2` FOREIGN KEY (`gid`) REFERENCES `usersgroup` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(30) NOT NULL,
  `privilege` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for uinfo
-- ----------------------------
DROP TABLE IF EXISTS `uinfo`;
CREATE TABLE `uinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` text,
  `infotime` datetime DEFAULT NULL,
  `infostate` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for userlist
-- ----------------------------
DROP TABLE IF EXISTS `userlist`;
CREATE TABLE `userlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `friendid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  KEY `friendid` (`friendid`),
  CONSTRAINT `userlist_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`id`),
  CONSTRAINT `userlist_ibfk_2` FOREIGN KEY (`friendid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ucid` varchar(20) NOT NULL,
  `uname` varchar(50) NOT NULL,
  `upassword` varchar(50) NOT NULL,
  `uphonenum` varchar(30) NOT NULL,
  `ugender` varchar(20) DEFAULT NULL,
  `uheadimg` varchar(50) NOT NULL,
  `ustate` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for usersgroup
-- ----------------------------
DROP TABLE IF EXISTS `usersgroup`;
CREATE TABLE `usersgroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ugid` varchar(20) DEFAULT NULL,
  `gname` varchar(50) DEFAULT NULL,
  `gimg` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_group_role
-- ----------------------------
DROP TABLE IF EXISTS `user_group_role`;
CREATE TABLE `user_group_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `gid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  KEY `gid` (`gid`),
  KEY `rid` (`rid`),
  CONSTRAINT `user_group_role_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`id`),
  CONSTRAINT `user_group_role_ibfk_2` FOREIGN KEY (`gid`) REFERENCES `usersgroup` (`id`),
  CONSTRAINT `user_group_role_ibfk_3` FOREIGN KEY (`rid`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fromid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `infoid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fromid` (`fromid`),
  KEY `uid` (`uid`),
  KEY `infoid` (`infoid`),
  CONSTRAINT `user_info_ibfk_1` FOREIGN KEY (`fromid`) REFERENCES `users` (`id`),
  CONSTRAINT `user_info_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `users` (`id`),
  CONSTRAINT `user_info_ibfk_3` FOREIGN KEY (`infoid`) REFERENCES `uinfo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8;
