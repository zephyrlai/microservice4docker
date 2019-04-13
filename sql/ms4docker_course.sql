/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : ms4docker_course

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 14/04/2019 00:17:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pe_course
-- ----------------------------
DROP TABLE IF EXISTS `pe_course`;
CREATE TABLE `pe_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='课程信息表';

-- ----------------------------
-- Records of pe_course
-- ----------------------------
BEGIN;
INSERT INTO `pe_course` VALUES (1, '小学英语', '从娃娃抓起');
COMMIT;

-- ----------------------------
-- Table structure for pr_user_course
-- ----------------------------
DROP TABLE IF EXISTS `pr_user_course`;
CREATE TABLE `pr_user_course` (
  `user_id` int(11) NOT NULL,
  `course_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='教师-课程 关系表';

-- ----------------------------
-- Records of pr_user_course
-- ----------------------------
BEGIN;
INSERT INTO `pr_user_course` VALUES (1, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
