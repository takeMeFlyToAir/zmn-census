/*
Navicat MySQL Data Transfer

Source Server         : bendi
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : zmn-census

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2020-09-10 12:56:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `zmn_census_community`
-- ----------------------------
DROP TABLE IF EXISTS `zmn_census_community`;
CREATE TABLE `zmn_census_community` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `town` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '街道',
  `village` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '普查区',
  `name` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '小区名字',
  `deleted` tinyint(4) NOT NULL COMMENT '是否删除：0否，1是',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_unique_tvc` (`town`,`village`,`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='小区表';

-- ----------------------------
-- Records of zmn_census_community
-- ----------------------------
INSERT INTO `zmn_census_community` VALUES ('1', 'A-街道', 'A-普查区-1', 'A-普查区1-1', '0', '2020-09-10 12:47:03', '2020-09-10 12:48:07');
INSERT INTO `zmn_census_community` VALUES ('2', 'A-街道', 'A-普查区-1', 'A-普查区1-2', '0', '2020-09-10 12:47:31', '2020-09-10 12:47:32');
INSERT INTO `zmn_census_community` VALUES ('3', 'A-街道', 'A-普查区-1', 'A-普查区1-3', '0', '2020-09-10 12:48:02', '2020-09-10 12:48:03');
INSERT INTO `zmn_census_community` VALUES ('4', 'A-街道', 'A-普查区-2', 'A-普查区2-1', '0', '2020-09-10 12:48:41', '2020-09-10 12:48:42');
INSERT INTO `zmn_census_community` VALUES ('5', 'A-街道', 'A-普查区-2', 'A-普查区2-2', '0', '2020-09-10 12:49:05', '2020-09-10 12:49:07');
INSERT INTO `zmn_census_community` VALUES ('6', 'A-街道', 'A-普查区-2', 'A-普查区2-3', '0', '2020-09-10 12:49:27', '2020-09-10 12:49:31');
INSERT INTO `zmn_census_community` VALUES ('7', 'A-街道', 'A-普查区-3', 'A-普查区3-1', '0', '2020-09-10 12:49:58', '2020-09-10 12:50:04');
INSERT INTO `zmn_census_community` VALUES ('8', 'A-街道', 'A-普查区-3', 'A-普查区3-2', '0', '2020-09-10 12:50:29', '2020-09-10 12:50:33');
INSERT INTO `zmn_census_community` VALUES ('9', 'A-街道', 'A-普查区-3', 'A-普查区3-3', '0', '2020-09-10 12:50:49', '2020-09-10 12:50:54');
INSERT INTO `zmn_census_community` VALUES ('10', 'B-街道', 'B-普查区-1', 'B-普查区1-1', '0', '2020-09-10 12:51:13', '2020-09-10 12:51:14');
INSERT INTO `zmn_census_community` VALUES ('11', 'B-街道', 'B-普查区-1', 'B-普查区1-2', '0', '2020-09-10 12:51:38', '2020-09-10 12:51:39');
INSERT INTO `zmn_census_community` VALUES ('12', 'B-街道', 'B-普查区-1', 'B-普查区1-3', '0', '2020-09-10 12:52:09', '2020-09-10 12:52:11');
