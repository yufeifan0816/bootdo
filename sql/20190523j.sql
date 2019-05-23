/*
Navicat MySQL Data Transfer

Source Server         : yff
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : bootdo

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-05-23 22:21:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `xjbg_room`
-- ----------------------------
DROP TABLE IF EXISTS `xjbg_room`;
CREATE TABLE `xjbg_room` (
  `id` int(20) NOT NULL,
  `room_no` varchar(20) DEFAULT NULL COMMENT '房间编号',
  `room_type` int(2) DEFAULT NULL COMMENT '房间类型',
  `floor` int(2) DEFAULT NULL COMMENT '楼层',
  `room_state` int(2) DEFAULT NULL COMMENT '房间状态',
  `price` int(11) DEFAULT NULL COMMENT '标价',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xjbg_room
-- ----------------------------
