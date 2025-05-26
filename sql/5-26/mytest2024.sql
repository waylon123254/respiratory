/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80035 (8.0.35)
 Source Host           : localhost:3306
 Source Schema         : mytest

 Target Server Type    : MySQL
 Target Server Version : 80035 (8.0.35)
 File Encoding         : 65001

 Date: 18/02/2025 11:18:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for person_article
-- ----------------------------
DROP TABLE IF EXISTS `person_article`;
CREATE TABLE `person_article`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `author` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '作者',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '文章标题',
  `user_id` int NOT NULL COMMENT '用户id',
  `category_id` int NULL DEFAULT NULL COMMENT '分类id',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '文章内容',
  `views` bigint NOT NULL DEFAULT 0 COMMENT '文章浏览量',
  `total_words` bigint NOT NULL DEFAULT 0 COMMENT '文章总字数',
  `commentable_id` int NULL DEFAULT NULL COMMENT '评论id',
  `art_status` tinyint NOT NULL DEFAULT 1 COMMENT '发布，默认1, 1-发布, 2-仅我可见  3-草稿',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '描述',
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '文章logo',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '文章管理表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of person_article
-- ----------------------------

-- ----------------------------
-- Table structure for person_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `person_article_tag`;
CREATE TABLE `person_article_tag`  (
  `tag_id` int NOT NULL COMMENT '标签id',
  `article_id` int NOT NULL COMMENT '文章id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '文章和标签关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of person_article_tag
-- ----------------------------

-- ----------------------------
-- Table structure for person_category
-- ----------------------------
DROP TABLE IF EXISTS `person_category`;
CREATE TABLE `person_category`  (
  `category_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '分类名称',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '文章分类管理表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of person_category
-- ----------------------------

-- ----------------------------
-- Table structure for person_login_log
-- ----------------------------
DROP TABLE IF EXISTS `person_login_log`;
CREATE TABLE `person_login_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '登录访问id',
  `login_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '登录账号',
  `ip_address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '登录地点',
  `browser_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '操作系统',
  `login_status` tinyint NULL DEFAULT 0 COMMENT '登录状态，默认0, 0-成功, 1-失败',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '登录日志表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of person_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for person_notice
-- ----------------------------
DROP TABLE IF EXISTS `person_notice`;
CREATE TABLE `person_notice`  (
  `notice_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `notice_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '公告标题',
  `notice_type` tinyint NOT NULL DEFAULT 0 COMMENT '公告类型，默认0, 0-公告, 1-通知, 2-提醒',
  `notice_status` tinyint NOT NULL DEFAULT 0 COMMENT '状态，默认0, 0-正常, 1-关闭',
  `notice_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '公告内容',
  `create_by` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '通知公告表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of person_notice
-- ----------------------------

-- ----------------------------
-- Table structure for person_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `person_operation_log`;
CREATE TABLE `person_operation_log`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `operation_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '主机地址',
  `opera_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '操作地点',
  `methods` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '方法名',
  `args` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '请求参数',
  `operation_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '操作人',
  `operation_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '操作类型',
  `return_value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '返回参数',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '操作日志表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of person_operation_log
-- ----------------------------

-- ----------------------------
-- Table structure for person_tag
-- ----------------------------
DROP TABLE IF EXISTS `person_tag`;
CREATE TABLE `person_tag`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tag_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '标签名',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '标签管理表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of person_tag
-- ----------------------------

-- ----------------------------
-- Table structure for person_user
-- ----------------------------
DROP TABLE IF EXISTS `person_user`;
CREATE TABLE `person_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '邮箱',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '上次登录时间',
  `phone` bigint NOT NULL DEFAULT 0 COMMENT '手机号',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '昵称',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户管理表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of person_user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
