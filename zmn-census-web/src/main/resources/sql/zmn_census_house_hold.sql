/*
Navicat MySQL Data Transfer

Source Server         : bendi
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : zmn-census

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2020-09-10 12:56:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `zmn_census_house_hold`
-- ----------------------------
DROP TABLE IF EXISTS `zmn_census_house_hold`;
CREATE TABLE `zmn_census_house_hold` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `h1` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '户别',
  `h2_live` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '本户应登记人数,2020年10月31日晚居住本户的人数',
  `h2_no_live` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '户口在本户，2020年10月31日晚未住本户的人数',
  `h3_man` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '户2019年11月1日至2020年10月31日期间的出生人口,男人',
  `h3_woman` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '户2019年11月1日至2020年10月31日期间的出生人口,女人',
  `h4_man` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '本户2019年11月1日至2020年10月31日期间的死亡人口,男人',
  `h4_woman` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '本户2019年11月1日至2020年10月31日期间的死亡人口,女人',
  `h5` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '住房类型,h5List,（选择3-6的，跳至个人项目。）',
  `h6` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '本户住房建筑面积',
  `h7` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '本户住房间数',
  `deleted` tinyint(4) NOT NULL COMMENT '是否删除：0否，1是',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='住户信息表';
