/*
 Navicat Premium Data Transfer

 Source Server         : localhost3306
 Source Server Type    : MySQL
 Source Server Version : 50741
 Source Host           : localhost:3306
 Source Schema         : myproject_myweb

 Target Server Type    : MySQL
 Target Server Version : 50741
 File Encoding         : 65001

 Date: 06/07/2023 00:00:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for myweb_contactme
-- ----------------------------
DROP TABLE IF EXISTS `myweb_contactme`;
CREATE TABLE `myweb_contactme`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contactme_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系姓名',
  `contactme_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系邮箱',
  `contactme_subject` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系主题',
  `contactme_comment` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '联系内容',
  `contactme_createtime` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `contactme_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '联系状态（0：待回复、1：已回复）',
  `contactme_replytime` datetime NULL DEFAULT NULL COMMENT '回复时间',
  `isdel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否逻辑删除（0：未删除、1：已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 69 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for myweb_pageview
-- ----------------------------
DROP TABLE IF EXISTS `myweb_pageview`;
CREATE TABLE `myweb_pageview`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pageview_count` bigint(20) NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `page_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `redis_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_unique_pageview`(`page_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Function structure for underlineToCamel
-- ----------------------------
DROP FUNCTION IF EXISTS `underlineToCamel`;
delimiter ;;
CREATE FUNCTION `underlineToCamel`(paramString VARCHAR ( 200 ))
 RETURNS varchar(200) CHARSET utf8
  DETERMINISTIC
BEGIN
	
	SET paramString = LOWER( paramString );
	
	SET paramString = REPLACE ( paramString, '_a', 'A' );
	
	SET paramString = REPLACE ( paramString, '_b', 'B' );
	
	SET paramString = REPLACE ( paramString, '_c', 'C' );
	
	SET paramString = REPLACE ( paramString, '_d', 'D' );
	
	SET paramString = REPLACE ( paramString, '_e', 'E' );
	
	SET paramString = REPLACE ( paramString, '_f', 'F' );
	
	SET paramString = REPLACE ( paramString, '_g', 'G' );
	
	SET paramString = REPLACE ( paramString, '_h', 'H' );
	
	SET paramString = REPLACE ( paramString, '_i', 'I' );
	
	SET paramString = REPLACE ( paramString, '_j', 'J' );
	
	SET paramString = REPLACE ( paramString, '_k', 'K' );
	
	SET paramString = REPLACE ( paramString, '_l', 'L' );
	
	SET paramString = REPLACE ( paramString, '_m', 'M' );
	
	SET paramString = REPLACE ( paramString, '_n', 'N' );
	
	SET paramString = REPLACE ( paramString, '_o', 'O' );
	
	SET paramString = REPLACE ( paramString, '_p', 'P' );
	
	SET paramString = REPLACE ( paramString, '_q', 'Q' );
	
	SET paramString = REPLACE ( paramString, '_r', 'R' );
	
	SET paramString = REPLACE ( paramString, '_s', 'S' );
	
	SET paramString = REPLACE ( paramString, '_t', 'T' );
	
	SET paramString = REPLACE ( paramString, '_u', 'U' );
	
	SET paramString = REPLACE ( paramString, '_v', 'V' );
	
	SET paramString = REPLACE ( paramString, '_w', 'W' );
	
	SET paramString = REPLACE ( paramString, '_x', 'X' );
	
	SET paramString = REPLACE ( paramString, '_y', 'Y' );
	
	SET paramString = REPLACE ( paramString, '_z', 'Z' );
	
	SET paramString = REPLACE ( paramString, '_', '' );
	RETURN paramString;

END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
