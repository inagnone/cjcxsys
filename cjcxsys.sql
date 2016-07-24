/*
MySQL Data Transfer
Source Host: localhost
Source Database: cjcxsys
Target Host: localhost
Target Database: cjcxsys
Date: 2016/7/25 1:34:20
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for cj
-- ----------------------------
CREATE TABLE `cj` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `company` varchar(255) NOT NULL,
  `personid` varchar(255) NOT NULL,
  `examtype` char(2) NOT NULL,
  `exampc` int(11) NOT NULL,
  `sgqycj` int(255) default NULL,
  `sgdwcj` int(255) default NULL,
  `xmfrcj` int(11) default NULL,
  `zynlcj` int(11) default NULL,
  `examtime` date default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for examtype
-- ----------------------------
CREATE TABLE `examtype` (
  `id` int(11) NOT NULL auto_increment,
  `examname` varchar(255) default NULL,
  `typeid` char(3) NOT NULL default '',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `typeid` (`typeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `cj` VALUES ('4', '1', '1', '1', '11', '1', '1', '1', '1', '1', '2016-06-27');
INSERT INTO `examtype` VALUES ('1', '123444', '11');
INSERT INTO `examtype` VALUES ('2', '2222', '22');
INSERT INTO `examtype` VALUES ('3', '333', '');
