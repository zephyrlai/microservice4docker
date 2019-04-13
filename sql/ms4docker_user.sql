/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : ms4docker_user

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 14/04/2019 00:17:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pe_teacher
-- ----------------------------
DROP TABLE IF EXISTS `pe_teacher`;
CREATE TABLE `pe_teacher` (
  `user_id` int(11) NOT NULL,
  `intro` varchar(255) NOT NULL,
  `stars` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='教师信息表';

-- ----------------------------
-- Records of pe_teacher
-- ----------------------------
BEGIN;
INSERT INTO `pe_teacher` VALUES (1, '特级教师', '5星级');
COMMIT;

-- ----------------------------
-- Table structure for pe_user
-- ----------------------------
DROP TABLE IF EXISTS `pe_user`;
CREATE TABLE `pe_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `real_name` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='学生信息表';

-- ----------------------------
-- Records of pe_user
-- ----------------------------
BEGIN;
INSERT INTO `pe_user` VALUES (1, 'haha', '96e79218965eb72c92a549dd5a330112', '哈哈', NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
