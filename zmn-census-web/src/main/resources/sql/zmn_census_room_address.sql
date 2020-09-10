/*
Navicat MySQL Data Transfer

Source Server         : bendi
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : zmn-census

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2020-09-10 12:56:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `zmn_census_room_address`
-- ----------------------------
DROP TABLE IF EXISTS `zmn_census_room_address`;
CREATE TABLE `zmn_census_room_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `province` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '省',
  `city` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '市或者区',
  `county` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '县城，或者区',
  `town` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '街道',
  `village` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '普查区',
  `community` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '小区名字',
  `build_num` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '楼栋号',
  `unit_num` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '单元号',
  `floor_num` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '楼层',
  `room_num` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '房间号',
  `fill_person_phone` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '填报人电话',
  `deleted` tinyint(4) NOT NULL COMMENT '是否删除：0否，1是',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_unique_tvc` (`town`,`village`,`community`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='房间地址表';
