/*
Navicat MySQL Data Transfer

Source Server         : bendi
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : zmn-census

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2020-09-21 09:36:13
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
  `charge_person_id` int(11) DEFAULT NULL,
  `charge_person_name` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `plan_hold_count` int(11) DEFAULT '0' COMMENT '计划统计总户数',
  `real_hold_count` int(11) DEFAULT '0' COMMENT '实际统计总户数',
  `plan_person_count` int(11) DEFAULT '0' COMMENT '计划统计总人数',
  `real_person_count` int(11) DEFAULT '0' COMMENT '实际统计总人数',
  `deleted` tinyint(4) NOT NULL COMMENT '是否删除：0否，1是',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_unique_tvc` (`town`,`village`,`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='小区表';

-- ----------------------------
-- Records of zmn_census_community
-- ----------------------------
INSERT INTO `zmn_census_community` VALUES ('1', 'A-街道', 'A-普查区-1', 'A-普查区1-1', '4', '朱凡乐', '0', '1', '0', '2', '0', '2020-09-13 12:47:03', '2020-09-13 15:48:28');
INSERT INTO `zmn_census_community` VALUES ('2', 'A-街道', 'A-普查区-1', 'A-普查区1-2', '4', '朱凡乐', '0', '0', '0', '0', '0', '2020-09-10 12:47:31', '2020-09-13 10:05:28');
INSERT INTO `zmn_census_community` VALUES ('3', 'A-街道', 'A-普查区-1', 'A-普查区1-3', '4', '朱凡乐', '0', '0', '0', '0', '0', '2020-09-10 12:48:02', '2020-09-13 10:05:28');
INSERT INTO `zmn_census_community` VALUES ('4', 'A-街道', 'A-普查区-2', 'A-普查区2-1', '4', '朱凡乐', '0', '0', '0', '0', '0', '2020-09-10 12:48:41', '2020-09-13 10:05:29');
INSERT INTO `zmn_census_community` VALUES ('5', 'A-街道', 'A-普查区-2', 'A-普查区2-2', '4', '朱凡乐', '0', '0', '0', '0', '0', '2020-09-10 12:49:05', '2020-09-13 10:05:30');
INSERT INTO `zmn_census_community` VALUES ('6', 'A-街道', 'A-普查区-2', 'A-普查区2-3', '4', '朱凡乐', '0', '0', '0', '0', '0', '2020-09-10 12:49:27', '2020-09-13 10:05:30');
INSERT INTO `zmn_census_community` VALUES ('7', 'A-街道', 'A-普查区-3', 'A-普查区3-1', '4', '朱凡乐', '0', '0', '0', '0', '0', '2020-09-10 12:49:58', '2020-09-13 10:05:31');
INSERT INTO `zmn_census_community` VALUES ('8', 'A-街道', 'A-普查区-3', 'A-普查区3-2', '3', 'hyy', '0', '5', '0', '6', '0', '2020-09-10 12:50:29', '2020-09-18 22:35:03');
INSERT INTO `zmn_census_community` VALUES ('9', 'A-街道', 'A-普查区-3', 'A-普查区3-3', '4', '朱凡乐', '0', '0', '0', '0', '0', '2020-09-10 12:50:49', '2020-09-13 10:05:32');
INSERT INTO `zmn_census_community` VALUES ('10', 'B-街道', 'B-普查区-1', 'B-普查区1-1', '4', '朱凡乐', '0', '0', '0', '0', '0', '2020-09-10 12:51:13', '2020-09-13 10:05:33');
INSERT INTO `zmn_census_community` VALUES ('11', 'B-街道', 'B-普查区-1', 'B-普查区1-2', '4', '朱凡乐', '0', '0', '0', '0', '0', '2020-09-10 12:51:38', '2020-09-13 10:04:58');
INSERT INTO `zmn_census_community` VALUES ('12', 'B-街道', 'B-普查区-1', 'B-普查区1-3', '3', 'hyy', '0', '0', '0', '0', '0', '2020-09-10 12:52:09', '2020-09-13 09:32:15');

-- ----------------------------
-- Table structure for `zmn_census_config`
-- ----------------------------
DROP TABLE IF EXISTS `zmn_census_config`;
CREATE TABLE `zmn_census_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `value` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '值',
  `key` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '类型',
  `deleted` tinyint(4) NOT NULL COMMENT '是否删除：0否，1是',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_uni_key` (`key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='配置表';

-- ----------------------------
-- Records of zmn_census_config
-- ----------------------------
INSERT INTO `zmn_census_config` VALUES ('1', 'http://47.108.128.35:8080/#/censusSurvey', 'census_fill', '0', '2020-09-11 16:26:12', '2020-09-13 14:47:33');

-- ----------------------------
-- Table structure for `zmn_census_house_hold`
-- ----------------------------
DROP TABLE IF EXISTS `zmn_census_house_hold`;
CREATE TABLE `zmn_census_house_hold` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `room_address_id` int(11) NOT NULL COMMENT '房间地址表id',
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
  `h8` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '您家中是否有6个月及以上的孕妇（是/否）',
  `h9` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '填报人类型：住户，租户',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '是否删除：0否，1是',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='住户信息表';

-- ----------------------------
-- Records of zmn_census_house_hold
-- ----------------------------
INSERT INTO `zmn_census_house_hold` VALUES ('39', '42', '家庭户', '3', '2', '2', '3', '1', '1', '普通住房', '120', '4', '是', '租户', '0', '2020-09-13 15:38:40', '2020-09-13 15:38:40');
INSERT INTO `zmn_census_house_hold` VALUES ('40', '43', '家庭户', '3', '2', '2', '3', '1', '1', '普通住房', '120', '4', '是', '租户', '0', '2020-09-13 15:41:29', '2020-09-13 15:41:29');
INSERT INTO `zmn_census_house_hold` VALUES ('41', '44', '家庭户', '3', '2', '2', '3', '1', '1', '普通住房', '120', '4', '是', '租户', '0', '2020-09-13 15:42:27', '2020-09-13 15:48:35');
INSERT INTO `zmn_census_house_hold` VALUES ('42', '45', '家庭户', '2', '1', '2', '1', '1', '1', '普通住房', '111', '3', '是', '住户', '0', '2020-09-17 23:47:12', '2020-09-17 23:47:12');
INSERT INTO `zmn_census_house_hold` VALUES ('43', '46', '家庭户', '0', '1', '1', '0', '0', '1', '普通住房', '1111', '2', '是', '住户', '0', '2020-09-17 23:49:08', '2020-09-17 23:49:08');
INSERT INTO `zmn_census_house_hold` VALUES ('44', '47', '家庭户', '2', '1', '2', '1', '1', '1', '普通住房', '111', '1', '是', '住户', '0', '2020-09-17 23:50:57', '2020-09-17 23:50:57');
INSERT INTO `zmn_census_house_hold` VALUES ('45', '48', '家庭户', '1', '0', '0', '0', '0', '0', '普通住房', '111', '3', '是', '住户', '0', '2020-09-18 22:33:33', '2020-09-18 22:33:33');
INSERT INTO `zmn_census_house_hold` VALUES ('46', '49', '家庭户', '1', '0', '0', '0', '0', '0', '普通住房', '111', '3', '是', '住户', '0', '2020-09-18 22:35:03', '2020-09-18 22:35:03');

-- ----------------------------
-- Table structure for `zmn_census_person_info`
-- ----------------------------
DROP TABLE IF EXISTS `zmn_census_person_info`;
CREATE TABLE `zmn_census_person_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `room_address_id` int(11) NOT NULL COMMENT '房间地址表id',
  `d1` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `d2` varchar(24) COLLATE utf8_bin DEFAULT NULL COMMENT '与户主关系',
  `d3` varchar(24) COLLATE utf8_bin DEFAULT NULL COMMENT '公民身份号码',
  `d4` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '性别',
  `d5` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '出生年月',
  `d6` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '民族',
  `d7` varchar(48) COLLATE utf8_bin DEFAULT NULL COMMENT '普查时点居住地',
  `d7_province` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '普查时点居住地不在本地的省',
  `d7_city` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '普查时点居住地不在本地的市',
  `d7_county` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '普查时点居住地不在本地的县',
  `d8` varchar(48) COLLATE utf8_bin DEFAULT NULL COMMENT '户口登记地',
  `d8_province` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '户口登记地不在本地的省',
  `d8_city` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '户口登记地不在本地的市',
  `d8_county` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '户口登记地不在本地的县',
  `d9` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '离开户口登记地时间',
  `d10` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '离开户口登记地原因',
  `d11` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '是否识字',
  `d12` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '受教育程度',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '是否删除：0否，1是',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='个人信息表';

-- ----------------------------
-- Records of zmn_census_person_info
-- ----------------------------
INSERT INTO `zmn_census_person_info` VALUES ('36', '42', '张三', '户主', '622722202012254910', '男', '2020-12', '汉', '本普查小区', '', '', '', '本村（居）委会', '', '', '', '没有离开户口登记地', '', '', '', '0', '2020-09-13 15:38:46', '2020-09-13 15:38:46');
INSERT INTO `zmn_census_person_info` VALUES ('37', '42', '李四', '配偶', '622722199312254910', '女', '1993-12', '汉', '本普查小区', '', '', '', '其他县（市、区、旗），请在下面填写地址', '甘肃', '兰州', '安宁', '不满半年', '工作就业', '未上过学', '是', '0', '2020-09-13 15:38:46', '2020-09-13 15:38:46');
INSERT INTO `zmn_census_person_info` VALUES ('38', '42', '王麻子', '子女', '622722200312254910', '男', '2003-12', '汉', '其他县（市、区、旗），请在下面填写地址', '甘肃', '兰州', '西固', '本村（居）委会', '', '', '', '没有离开户口登记地', '', '小学', '否', '0', '2020-09-13 15:38:46', '2020-09-13 15:38:46');
INSERT INTO `zmn_census_person_info` VALUES ('39', '43', '李四', '户主', '622722202012254910', '男', '2020-12', '汉', '本普查小区', '', '', '', '本村（居）委会', '', '', '', '没有离开户口登记地', '', '', '', '0', '2020-09-13 15:41:31', '2020-09-13 15:41:31');
INSERT INTO `zmn_census_person_info` VALUES ('40', '44', '王麻子', '户主', '622722202012254910', '男', '2020-12', '汉', '本普查小区', '', '', '', '本村（居）委会', '', '', '', '没有离开户口登记地', '', '', '', '0', '2020-09-13 15:42:27', '2020-09-13 15:48:40');
INSERT INTO `zmn_census_person_info` VALUES ('41', '44', '胡汉三', '配偶', '622722200312254910', '女', '2003-12', '汉', '本普查小区', '', '', '', '本村（居）委会', '', '', '', '没有离开户口登记地', '', '未上过学', '是', '0', '2020-09-13 15:42:27', '2020-09-13 15:48:41');
INSERT INTO `zmn_census_person_info` VALUES ('42', '45', '赵志荣', '户主', '622722202012254910', '男', '2020-12', '汉', '本普查小区', '', '', '', '本村（居）委会', '', '', '', '没有离开户口登记地', '', '', '', '0', '2020-09-17 23:47:12', '2020-09-17 23:47:12');
INSERT INTO `zmn_census_person_info` VALUES ('43', '45', '张三', '配偶', '622722200312254910', '女', '2003-12', '汉', '本普查小区', '', '', '', '本村（居）委会', '', '', '', '没有离开户口登记地', '', '未上过学', '是', '0', '2020-09-17 23:47:12', '2020-09-17 23:47:12');
INSERT INTO `zmn_census_person_info` VALUES ('44', '46', '赵志荣', '户主', '622722202012254910', '男', '2020-12', '汉', '本普查小区', '', '', '', '本村（居）委会', '', '', '', '没有离开户口登记地', '', '', '', '0', '2020-09-17 23:49:08', '2020-09-17 23:49:08');
INSERT INTO `zmn_census_person_info` VALUES ('45', '47', '赵志荣', '户主', '622722202012254910', '男', '2020-12', '汉', '本普查小区', '', '', '', '本村（居）委会', '', '', '', '没有离开户口登记地', '', '', '', '0', '2020-09-17 23:50:57', '2020-09-17 23:50:57');
INSERT INTO `zmn_census_person_info` VALUES ('46', '48', '赵志荣', '户主', '622722202012254910', '男', '2020-12', '汉', '本普查小区', '', '', '', '本村（居）委会', '', '', '', '没有离开户口登记地', '', '', '', '0', '2020-09-18 22:33:33', '2020-09-18 22:33:33');
INSERT INTO `zmn_census_person_info` VALUES ('47', '49', '赵志荣', '户主', '622722202012254910', '男', '2020-12', '汉', '本普查小区', '', '', '', '本村（居）委会', '', '', '', '没有离开户口登记地', '', '', '', '0', '2020-09-18 22:35:03', '2020-09-18 22:35:03');

-- ----------------------------
-- Table structure for `zmn_census_room_address`
-- ----------------------------
DROP TABLE IF EXISTS `zmn_census_room_address`;
CREATE TABLE `zmn_census_room_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `charge_person_id` int(11) DEFAULT NULL,
  `charge_person_name` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `community_id` int(11) DEFAULT NULL COMMENT '小区id',
  `person_count` int(4) DEFAULT '0' COMMENT '每户人数',
  `province` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '省',
  `city` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '市或者区',
  `county` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '县城，或者区',
  `town` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '街道',
  `village` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '普查区',
  `community` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '小区名字',
  `build_num` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '楼栋号',
  `unit_num` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '单元号',
  `floor_num` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '楼层',
  `room_num` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '房间号',
  `fill_person_phone` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '填报人电话',
  `examine_person_name` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '普查员姓名',
  `remark` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT 'remark',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '是否删除：0否，1是',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_unique_tvc` (`town`,`village`,`community`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='房间地址表';

-- ----------------------------
-- Records of zmn_census_room_address
-- ----------------------------
INSERT INTO `zmn_census_room_address` VALUES ('42', '4', '朱凡乐', '1', '3', '陕西省', '西安市', '浐灞区', 'A-街道', 'A-普查区-1', 'A-普查区1-1', '1', '2', '3', '4', '18911942675', '张三', '张三备注', '0', '2020-09-12 15:38:40', '2020-09-13 15:50:05');
INSERT INTO `zmn_census_room_address` VALUES ('43', '4', '朱凡乐', '1', '1', '陕西省', '西安市', '浐灞区', 'A-街道', 'A-普查区-1', 'A-普查区1-1', '2', '3', '4', '5', '18911942675', '李四', '李四备注', '0', '2020-09-13 15:41:29', '2020-09-13 15:41:29');
INSERT INTO `zmn_census_room_address` VALUES ('44', '4', '朱凡乐', '1', '2', '陕西省', '西安市', '浐灞区', 'A-街道', 'A-普查区-1', 'A-普查区1-1', '2', '3', '4', '5', '18911942675', '王麻子', '王麻子备注', '0', '2020-09-13 15:42:27', '2020-09-13 15:48:45');
INSERT INTO `zmn_census_room_address` VALUES ('45', '3', 'hyy', '8', '2', '陕西省', '西安市', '浐灞生态区', 'A-街道', 'A-普查区-3', 'A-普查区3-2', '1', '', '', '1', '18911942675', 'eee', 'eee', '0', '2020-09-17 23:47:12', '2020-09-17 23:47:12');
INSERT INTO `zmn_census_room_address` VALUES ('46', '3', 'hyy', '8', '1', '陕西省', '西安市', '浐灞生态区', 'A-街道', 'A-普查区-3', 'A-普查区3-2', '1', '1', '2', '4', '18911942675', '222', '222', '0', '2020-09-17 23:49:08', '2020-09-17 23:49:08');
INSERT INTO `zmn_census_room_address` VALUES ('47', '3', 'hyy', '8', '1', '陕西省', '西安市', '浐灞生态区', 'A-街道', 'A-普查区-3', 'A-普查区3-2', '1', '1', '1', '1', '18911942675', '111', '111', '0', '2020-09-17 23:50:56', '2020-09-17 23:50:56');
INSERT INTO `zmn_census_room_address` VALUES ('48', '3', 'hyy', '8', '1', '陕西省', '西安市', '浐灞生态区', 'A-街道', 'A-普查区-3', 'A-普查区3-2', '1', '1', '11', '111', '18911942675', 'zzzz', 'zzzz', '0', '2020-09-18 22:33:33', '2020-09-18 22:33:33');
INSERT INTO `zmn_census_room_address` VALUES ('49', '3', 'hyy', '8', '1', '陕西省', '西安市', '浐灞生态区', 'A-街道', 'A-普查区-3', 'A-普查区3-2', '1', '1', '1', '1', '18911942675', 'zzxx', 'zzxx111', '0', '2020-09-18 22:35:03', '2020-09-18 22:35:03');

-- ----------------------------
-- Table structure for `zmn_census_user`
-- ----------------------------
DROP TABLE IF EXISTS `zmn_census_user`;
CREATE TABLE `zmn_census_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `nick_name` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '昵称',
  `user_name` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '密码',
  `phone` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号',
  `deleted` tinyint(4) NOT NULL COMMENT '是否删除：0否，1是',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表';

-- ----------------------------
-- Records of zmn_census_user
-- ----------------------------
INSERT INTO `zmn_census_user` VALUES ('1', 'zzr', 'zzr', 'e10adc3949ba59abbe56e057f20f883e', '18911942675', '0', '2020-09-11 12:37:04', '2020-09-11 12:39:21');
INSERT INTO `zmn_census_user` VALUES ('2', 'hyy', 'hyy', 'e10adc3949ba59abbe56e057f20f883e', '13211113333', '1', '2020-09-11 12:41:52', '2020-09-11 12:41:52');
INSERT INTO `zmn_census_user` VALUES ('3', 'hyy', 'hyy', 'e10adc3949ba59abbe56e057f20f883e', '18988882222', '0', '2020-09-11 14:02:38', '2020-09-11 14:02:38');
INSERT INTO `zmn_census_user` VALUES ('4', '朱凡乐', 'zhufanle', 'e10adc3949ba59abbe56e057f20f883e', '13312341234', '0', '2020-09-13 08:48:09', '2020-09-13 08:48:09');
