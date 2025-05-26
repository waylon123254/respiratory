/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80035 (8.0.35)
 Source Host           : localhost:3306
 Source Schema         : joweese

 Target Server Type    : MySQL
 Target Server Version : 80035 (8.0.35)
 File Encoding         : 65001

 Date: 28/10/2024 15:46:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for are
-- ----------------------------
DROP TABLE IF EXISTS `are`;
CREATE TABLE `are`  (
  `id` int NOT NULL,
  `are_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '专区名称',
  `are_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '专区描述',
  `time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '专区创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件专区表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of are
-- ----------------------------

-- ----------------------------
-- Table structure for auditor
-- ----------------------------
DROP TABLE IF EXISTS `auditor`;
CREATE TABLE `auditor`  (
  `id` int NOT NULL,
  `file_id` int NULL DEFAULT NULL COMMENT '文件id',
  `auditor` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核者',
  `audit_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核情况',
  `audit_comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核情况',
  `audit_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '审核时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_auditor_file`(`file_id` ASC) USING BTREE,
  CONSTRAINT `fk_auditor_file` FOREIGN KEY (`file_id`) REFERENCES `file` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件审核表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auditor
-- ----------------------------

-- ----------------------------
-- Table structure for blogpost
-- ----------------------------
DROP TABLE IF EXISTS `blogpost`;
CREATE TABLE `blogpost`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '帖子ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '帖子标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '帖子内容',
  `publish_date` datetime NOT NULL COMMENT '发布时间',
  `author_id` bigint NOT NULL COMMENT '作者ID',
  `topic_id` bigint NOT NULL COMMENT '主题ID',
  `ranking` int NULL DEFAULT 0 COMMENT '排名，具体逻辑根据业务需求定义',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '帖子创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '博客帖子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blogpost
-- ----------------------------

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int NOT NULL COMMENT '栏目id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '栏目名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '栏目简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件栏目表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '收藏表id',
  `name_id` int NULL DEFAULT NULL COMMENT '用户id',
  `file_id` int NULL DEFAULT NULL COMMENT '文件id',
  `time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '收藏时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '收藏表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collect
-- ----------------------------

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `blogpost_id` bigint NOT NULL COMMENT '博客帖子ID',
  `author_id` bigint NOT NULL COMMENT '评论作者ID',
  `comment_text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论内容',
  `comment_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '上级评论ID，用于实现评论楼层',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `blogpost_id`(`blogpost_id` ASC) USING BTREE,
  INDEX `parent_id`(`parent_id` ASC) USING BTREE,
  INDEX `comment_ibfk_3`(`author_id` ASC) USING BTREE,
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`blogpost_id`) REFERENCES `blogpost` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`parent_id`) REFERENCES `comment` (`id`) ON DELETE SET NULL ON UPDATE RESTRICT,
  CONSTRAINT `comment_ibfk_3` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for distribution
-- ----------------------------
DROP TABLE IF EXISTS `distribution`;
CREATE TABLE `distribution`  (
  `id` int NULL DEFAULT NULL,
  `distribution_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分销名称',
  `distribution_section` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分销比例',
  `distribution_time` datetime NULL DEFAULT NULL COMMENT '分销时间',
  INDEX `id`(`id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '分销表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of distribution
-- ----------------------------

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '文件id',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件名',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件标题',
  `keyword` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件关键字',
  `size` bigint NULL DEFAULT NULL COMMENT '文件大小',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件缩略图',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '文件价格',
  `pagecount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件页码总数',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件类型',
  `summary` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件摘要',
  `download` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件下载链接',
  `md5` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件唯一标识',
  `time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '文件上传时间',
  `deadtime` datetime NULL DEFAULT NULL COMMENT '文件截至时间',
  `publishtime` datetime NULL DEFAULT NULL COMMENT '文件发布时间',
  `hat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件热度',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '文件状态，0表示未被禁用，1表示被禁用',
  `copy` tinyint(1) NULL DEFAULT NULL COMMENT '是否复制\r\n0,允许复制\r\n1,不允许复制',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file
-- ----------------------------

-- ----------------------------
-- Table structure for file_are
-- ----------------------------
DROP TABLE IF EXISTS `file_are`;
CREATE TABLE `file_are`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `file_id` int NULL DEFAULT NULL COMMENT '文件id',
  `are_id` int NULL DEFAULT NULL COMMENT '区域id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_file_are_file`(`file_id` ASC) USING BTREE,
  INDEX `fk_file_are-are`(`are_id` ASC) USING BTREE,
  CONSTRAINT `fk_file_are-are` FOREIGN KEY (`are_id`) REFERENCES `are` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_file_are_file` FOREIGN KEY (`file_id`) REFERENCES `file` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件分区表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file_are
-- ----------------------------

-- ----------------------------
-- Table structure for file_category
-- ----------------------------
DROP TABLE IF EXISTS `file_category`;
CREATE TABLE `file_category`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `file_id` int NULL DEFAULT NULL COMMENT '文件id',
  `category_id` int NULL DEFAULT NULL COMMENT '栏目id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_file_category_file`(`file_id` ASC) USING BTREE,
  INDEX `fk_file_category_category`(`category_id` ASC) USING BTREE,
  CONSTRAINT `fk_file_category_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_file_category_file` FOREIGN KEY (`file_id`) REFERENCES `file` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件栏目表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file_category
-- ----------------------------

-- ----------------------------
-- Table structure for file_distribution
-- ----------------------------
DROP TABLE IF EXISTS `file_distribution`;
CREATE TABLE `file_distribution`  (
  `id` int NOT NULL,
  `file_id` int NULL DEFAULT NULL COMMENT '文件id',
  `distribution_id` int NULL DEFAULT NULL COMMENT '分销id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_file_distribution_file`(`file_id` ASC) USING BTREE,
  INDEX `fk_file_distribution_distribution`(`distribution_id` ASC) USING BTREE,
  CONSTRAINT `fk_file_distribution_distribution` FOREIGN KEY (`distribution_id`) REFERENCES `distribution` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_file_distribution_file` FOREIGN KEY (`file_id`) REFERENCES `file` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件分销情况表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file_distribution
-- ----------------------------

-- ----------------------------
-- Table structure for file_download_history
-- ----------------------------
DROP TABLE IF EXISTS `file_download_history`;
CREATE TABLE `file_download_history`  (
  `id` int NOT NULL COMMENT '下载历史id',
  `file_id` int NULL DEFAULT NULL COMMENT '文件id',
  `user_id` int NULL DEFAULT NULL COMMENT '下载用户id',
  `download_time` datetime NULL DEFAULT NULL COMMENT '下载时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_file_download_file`(`file_id` ASC) USING BTREE,
  CONSTRAINT `fk_file_download_file` FOREIGN KEY (`file_id`) REFERENCES `file` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件下载记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file_download_history
-- ----------------------------

-- ----------------------------
-- Table structure for file_lable
-- ----------------------------
DROP TABLE IF EXISTS `file_lable`;
CREATE TABLE `file_lable`  (
  `id` int NOT NULL,
  `file_id` int NULL DEFAULT NULL COMMENT '文件id',
  `lable_id` int NULL DEFAULT NULL COMMENT '标签id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_file_lable_file`(`file_id` ASC) USING BTREE,
  INDEX `fk_file_lable_labe`(`lable_id` ASC) USING BTREE,
  CONSTRAINT `fk_file_lable_file` FOREIGN KEY (`file_id`) REFERENCES `file` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_file_lable_labe` FOREIGN KEY (`lable_id`) REFERENCES `labe` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file_lable
-- ----------------------------

-- ----------------------------
-- Table structure for file_permission
-- ----------------------------
DROP TABLE IF EXISTS `file_permission`;
CREATE TABLE `file_permission`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `file_id` int NULL DEFAULT NULL COMMENT '文件id',
  `permission_id` int NULL DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_file_permission_file`(`file_id` ASC) USING BTREE,
  INDEX `fk_file_permssion_permssion`(`permission_id` ASC) USING BTREE,
  CONSTRAINT `fk_file_permission_file` FOREIGN KEY (`file_id`) REFERENCES `file` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_file_permssion_permssion` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file_permission
-- ----------------------------

-- ----------------------------
-- Table structure for file_subsite
-- ----------------------------
DROP TABLE IF EXISTS `file_subsite`;
CREATE TABLE `file_subsite`  (
  `id` int NOT NULL,
  `file_id` int NULL DEFAULT NULL COMMENT '文件id',
  `subsite_id` int NULL DEFAULT NULL COMMENT '子站id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_file_subsite_file`(`file_id` ASC) USING BTREE,
  INDEX `fk_file_subsite_subsite`(`subsite_id` ASC) USING BTREE,
  CONSTRAINT `fk_file_subsite_file` FOREIGN KEY (`file_id`) REFERENCES `file` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_file_subsite_subsite` FOREIGN KEY (`subsite_id`) REFERENCES `subsite` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件子站表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file_subsite
-- ----------------------------

-- ----------------------------
-- Table structure for file_upload
-- ----------------------------
DROP TABLE IF EXISTS `file_upload`;
CREATE TABLE `file_upload`  (
  `id` int NOT NULL,
  `name_id` int NULL DEFAULT NULL COMMENT '用户id',
  `file_id` int NULL DEFAULT NULL COMMENT '文件id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_file_upload_file`(`file_id` ASC) USING BTREE,
  CONSTRAINT `fk_file_upload_file` FOREIGN KEY (`file_id`) REFERENCES `file` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件上传者表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file_upload
-- ----------------------------

-- ----------------------------
-- Table structure for file_view_history
-- ----------------------------
DROP TABLE IF EXISTS `file_view_history`;
CREATE TABLE `file_view_history`  (
  `id` int NOT NULL,
  `file_id` int NULL DEFAULT NULL COMMENT '文件id',
  `user_id` int NULL DEFAULT NULL COMMENT '用户id',
  `view_time` datetime NULL DEFAULT NULL COMMENT '浏览时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_file_view_history_file`(`file_id` ASC) USING BTREE,
  CONSTRAINT `fk_file_view_history_file` FOREIGN KEY (`file_id`) REFERENCES `file` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件浏览情况表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file_view_history
-- ----------------------------

-- ----------------------------
-- Table structure for forum
-- ----------------------------
DROP TABLE IF EXISTS `forum`;
CREATE TABLE `forum`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '论坛ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '论坛名',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '父论坛ID',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '论坛层级路径',
  `forum_class` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '论坛种类',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_path`(`path` ASC) USING BTREE,
  INDEX `parent_id`(`parent_id` ASC) USING BTREE,
  CONSTRAINT `forum_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `forum` (`id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '论坛表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of forum
-- ----------------------------

-- ----------------------------
-- Table structure for labe
-- ----------------------------
DROP TABLE IF EXISTS `labe`;
CREATE TABLE `labe`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `lable_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of labe
-- ----------------------------

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int NULL DEFAULT NULL,
  `permission_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限名称',
  `permission_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限描述',
  `permission_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限状态',
  `starttime` datetime NULL DEFAULT NULL COMMENT '生效时间',
  `endtime` datetime NULL DEFAULT NULL COMMENT '失效时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  INDEX `id`(`id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------

-- ----------------------------
-- Table structure for privilege
-- ----------------------------
DROP TABLE IF EXISTS `privilege`;
CREATE TABLE `privilege`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键，自增ID',
  `permission_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限名称，不可为空',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限描述，可为空',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of privilege
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键，自增ID',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称，不可为空',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色描述，可为空',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for subsite
-- ----------------------------
DROP TABLE IF EXISTS `subsite`;
CREATE TABLE `subsite`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `subsite_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '子站名字',
  `subsite_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '子站描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '子站表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of subsite
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_privilege
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_privilege`;
CREATE TABLE `sys_role_privilege`  (
  `id` int NOT NULL COMMENT 'ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `privilege_id` bigint NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`role_id`, `privilege_id`) USING BTREE,
  INDEX `fk_role_privilege_role`(`role_id` ASC) USING BTREE,
  INDEX `fk_role_privilege_privilege`(`privilege_id` ASC) USING BTREE,
  CONSTRAINT `fk_role_privilege_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_privilege
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint NULL DEFAULT NULL COMMENT 'ID值',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `fk_user_role_role`(`role_id` ASC) USING BTREE,
  INDEX `fk_user_role_user`(`user_id` ASC) USING BTREE,
  CONSTRAINT `fk_user_role_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_role_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主题ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主题名称',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '主题描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '主题表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of topic
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键，自增ID',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号名',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名，不可为空',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码，不可为空',
  `phone` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户邮箱，可为空',
  `registration_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间，默认不能更改，自动填充',
  `login_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '登录时间，默认自动更新',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像URL',
  `points` int NOT NULL DEFAULT 0 COMMENT '用户积分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
