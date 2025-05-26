/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80035 (8.0.35)
 Source Host           : localhost:3306
 Source Schema         : boot

 Target Server Type    : MySQL
 Target Server Version : 80035 (8.0.35)
 File Encoding         : 65001

 Date: 25/09/2024 17:19:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_answers
-- ----------------------------
DROP TABLE IF EXISTS `sys_answers`;
CREATE TABLE `sys_answers`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '序号：回答的唯一标识符',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '回答内容：用户的回答文本',
  `question_id` int NOT NULL COMMENT '关联问题：指向相关问题的外键',
  `likes` int NULL DEFAULT 0 COMMENT '赞同数：回答的赞同次数',
  `favorites` int NULL DEFAULT 0 COMMENT '收藏数：回答的收藏次数',
  `responder` int NOT NULL COMMENT '回答人：关联用户表的外键',
  `answered_time` datetime NOT NULL COMMENT '回答时间：回答的时间',
  `status` tinyint NULL DEFAULT 0 COMMENT '状态：回答的状态',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_answer_question`(`question_id` ASC) USING BTREE,
  INDEX `fk_answer_user`(`responder` ASC) USING BTREE,
  CONSTRAINT `fk_answer_question` FOREIGN KEY (`question_id`) REFERENCES `sys_questions` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_answer_user` FOREIGN KEY (`responder`) REFERENCES `sys_users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '回答表：记录问题的回答信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_answers
-- ----------------------------

-- ----------------------------
-- Table structure for sys_articles
-- ----------------------------
DROP TABLE IF EXISTS `sys_articles`;
CREATE TABLE `sys_articles`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '序号：推荐文章的唯一标识符',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文章标题：文章的名称',
  `issue` int NULL DEFAULT NULL COMMENT '所属期：文章所属的期数',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类型：文章的类别，例如技术类、新闻类等',
  `author` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '作者：文章的作者，可能不在用户表中',
  `views` int NULL DEFAULT 0 COMMENT '阅读量：文章的阅读次数',
  `likes` int NULL DEFAULT 0 COMMENT '点赞数：文章获得的点赞数',
  `comments_count` int NULL DEFAULT 0 COMMENT '评论数：文章的评论数量',
  `creator_id` int NOT NULL COMMENT '创建人：推荐这篇文章的用户ID，关联sys_users表',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间：记录文章创建的时间',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态：0表示未发布，1表示发布中，2表示草稿等其他状态',
  `sort_order` int NULL DEFAULT 0 COMMENT '排序：用于决定文章显示的顺序，数值越大，优先级越高',
  `operation` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作：可执行的操作，如编辑、删除等',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_creator`(`creator_id` ASC) USING BTREE,
  CONSTRAINT `fk_creator` FOREIGN KEY (`creator_id`) REFERENCES `sys_users` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '推荐文章表：记录所有推荐的文章信息及相关数据' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_articles
-- ----------------------------

-- ----------------------------
-- Table structure for sys_comments
-- ----------------------------
DROP TABLE IF EXISTS `sys_comments`;
CREATE TABLE `sys_comments`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '序号：评论的唯一标识符',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论内容：用户的评论文本',
  `parent_id` int NULL DEFAULT NULL COMMENT '父评论ID：引用父评论的外键',
  `question_id` int NULL DEFAULT NULL COMMENT '关联问题：评论对应的问题ID',
  `answer_id` int NULL DEFAULT NULL COMMENT '关联回答：评论对应的回答ID',
  `user_id` int NOT NULL COMMENT '评论人：关联用户表的外键',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间：创建评论的时间',
  `status` tinyint NULL DEFAULT 0 COMMENT '状态：评论的审核状态',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_comment_user`(`user_id` ASC) USING BTREE,
  INDEX `fk_comment_answer`(`answer_id` ASC) USING BTREE,
  INDEX `fk_comment_parent`(`parent_id` ASC) USING BTREE,
  INDEX `fk_comment_question`(`question_id` ASC) USING BTREE,
  CONSTRAINT `fk_comment_answer` FOREIGN KEY (`answer_id`) REFERENCES `sys_answers` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_comment_parent` FOREIGN KEY (`parent_id`) REFERENCES `sys_comments` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_comment_question` FOREIGN KEY (`question_id`) REFERENCES `sys_questions` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_comment_user` FOREIGN KEY (`user_id`) REFERENCES `sys_users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '评论表：记录评论的层次结构和内容' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_comments
-- ----------------------------

-- ----------------------------
-- Table structure for sys_journals
-- ----------------------------
DROP TABLE IF EXISTS `sys_journals`;
CREATE TABLE `sys_journals`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '序号：期刊的唯一标识符',
  `cover_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '封面图：期刊的封面图片',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标题：期刊的标题',
  `issue` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所属期：期刊所属的期数',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型：期刊的类型',
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '作者：期刊的作者',
  `views` int NULL DEFAULT 0 COMMENT '阅读量：期刊的阅读次数',
  `likes` int NULL DEFAULT 0 COMMENT '点赞量：期刊的点赞次数',
  `comments` int NULL DEFAULT 0 COMMENT '评论数：期刊的评论数量',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人：期刊条目的创建者',
  `created_time` datetime NOT NULL COMMENT '创建时间：期刊的创建时间',
  `status` tinyint NULL DEFAULT 0 COMMENT '状态：期刊当前的状态',
  PRIMARY KEY (`id`) USING BTREE,
  CONSTRAINT `fk_journal_author` FOREIGN KEY (`id`) REFERENCES `sys_users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '期刊表：记录期刊信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_journals
-- ----------------------------

-- ----------------------------
-- Table structure for sys_questions
-- ----------------------------
DROP TABLE IF EXISTS `sys_questions`;
CREATE TABLE `sys_questions`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '序号：问题的唯一标识符',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '问题标题：问题的简短描述或名称',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类型：问题的分类或种类',
  `related_topics` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联话题：与问题相关联的话题或标签',
  `likes` int NULL DEFAULT 0 COMMENT '点赞数：表示有多少人喜欢这个问题',
  `answers_count` int NULL DEFAULT 0 COMMENT '回答数：问题的回答数量',
  `created_by` int NOT NULL COMMENT '创建人：问题的创建者，关联用户表的外键',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间：问题的创建时间',
  `status` tinyint NULL DEFAULT 0 COMMENT '状态：问题的审核状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '问题表：记录问题的基本信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_questions
-- ----------------------------

-- ----------------------------
-- Table structure for sys_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles`;
CREATE TABLE `sys_roles`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '序号：记录角色的顺序编号',
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称：例如admin, engineer, user',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role_name`(`role_name` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表：定义系统中的不同用户角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_roles
-- ----------------------------

-- ----------------------------
-- Table structure for sys_topics
-- ----------------------------
DROP TABLE IF EXISTS `sys_topics`;
CREATE TABLE `sys_topics`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '序号：话题的唯一标识符',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '话题标题：每个话题的主题或名称',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '话题描述：关于话题的详细信息',
  `created_by` int NOT NULL COMMENT '创建人：关联用户表的外键',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间：话题的创建时间',
  `status` tinyint NULL DEFAULT 0 COMMENT '状态：话题的当前状态',
  `sort_order` int NULL DEFAULT 0 COMMENT '排序：话题的排列顺序',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_topic_user`(`created_by` ASC) USING BTREE,
  INDEX `fk_topic_title`(`title` ASC) USING BTREE,
  CONSTRAINT `fk_topic_user` FOREIGN KEY (`created_by`) REFERENCES `sys_users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '话题表：记录话题信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_topics
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_roles`;
CREATE TABLE `sys_user_roles`  (
  `user_id` int NOT NULL COMMENT '用户ID：关联sys_users表',
  `role_id` int NOT NULL COMMENT '角色ID：关联sys_roles表',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `fk_user_roles_role`(`role_id` ASC) USING BTREE,
  CONSTRAINT `fk_user_roles_role` FOREIGN KEY (`role_id`) REFERENCES `sys_roles` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_roles_user` FOREIGN KEY (`user_id`) REFERENCES `sys_users` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户角色中间表：记录用户与角色的多对多关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_roles
-- ----------------------------

-- ----------------------------
-- Table structure for sys_users
-- ----------------------------
DROP TABLE IF EXISTS `sys_users`;
CREATE TABLE `sys_users`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '序号：记录在列表中的顺序编号',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名：用户的唯一登录标识符',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码：明文存储的密码',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号：用户的联系电话号码',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态：0表示禁用，1表示启用',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '最后登录时间：记录用户最近一次登录时间',
  `created_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人：创建该用户的管理员用户名',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间：记录创建用户的时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE,
  UNIQUE INDEX `phone_number`(`phone` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表：记录系统中所有用户的基础信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_users
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
