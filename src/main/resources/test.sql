/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 09/09/2019 14:32:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for analyze_customer
-- ----------------------------
DROP TABLE IF EXISTS `analyze_customer`;
CREATE TABLE `analyze_customer`  (
  `id` int(11) NOT NULL,
  `l` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `f` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `m` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `r` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for care
-- ----------------------------
DROP TABLE IF EXISTS `care`;
CREATE TABLE `care`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `theme` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  `executor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of care
-- ----------------------------
INSERT INTO `care` VALUES (1, '哈哈', '2019-03-09', 'OOOO');
INSERT INTO `care` VALUES (2, '呵呵', '2019-03-09', '哈哈');
INSERT INTO `care` VALUES (3, '太棒', '2019-03-09', '哈哈');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类别名',
  `descript` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类别描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '1', '1');
INSERT INTO `category` VALUES (2, '2', '2');
INSERT INTO `category` VALUES (3, '3', '3');
INSERT INTO `category` VALUES (4, '4', '4');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `adress` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` date NULL DEFAULT NULL,
  `customer_category_value` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stage_value` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `category_id` int(11) NULL DEFAULT NULL,
  `care_id` int(11) NULL DEFAULT NULL,
  `stage_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`customer_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, '疯不觉哈哈哈', '1231', '2210384480@qq.com', '深圳', '2019-03-13', NULL, NULL, 1, 1, 1);
INSERT INTO `customer` VALUES (2, 'cao', '1231231213', '2210384480@qq.com', '深圳', '2014-06-25', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer` VALUES (3, '3', '1', '2210384480@qq.com', '深圳', '2014-06-25', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer` VALUES (4, '4', '1', '2210384480@qq.com', '深圳', '2014-07-11', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer` VALUES (5, '5', '1', '2210384480@qq.com', '深圳', '2014-06-25', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer` VALUES (6, '6', '1', '2210384480@qq.com', '深圳', '2014-08-21', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer` VALUES (7, '7', '1', '2210384480@qq.com', '深圳', '2014-06-25', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer` VALUES (8, '8', '1', '2210384480@qq.com', '深圳', '2015-06-25', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer` VALUES (9, '9', '1', '2210384480@qq.com', '深圳', '2010-06-25', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer` VALUES (10, '10', '1', '2210384480@qq.com', '深圳', '2019-06-25', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer` VALUES (11, '66666', '66666', '66666', '湖北', NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO `customer` VALUES (12, '3', '1', '2210384480@qq.com', '深圳', NULL, NULL, NULL, 1, NULL, NULL);

-- ----------------------------
-- Table structure for customer_category
-- ----------------------------
DROP TABLE IF EXISTS `customer_category`;
CREATE TABLE `customer_category`  (
  `customer_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_category_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`customer_category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_category
-- ----------------------------
INSERT INTO `customer_category` VALUES (1, '啊啊啊');

-- ----------------------------
-- Table structure for customer_stage
-- ----------------------------
DROP TABLE IF EXISTS `customer_stage`;
CREATE TABLE `customer_stage`  (
  `stage_id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `number` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`stage_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_stage
-- ----------------------------
INSERT INTO `customer_stage` VALUES (1, 'O(∩_∩)O', 33);

-- ----------------------------
-- Table structure for frm_model
-- ----------------------------
DROP TABLE IF EXISTS `frm_model`;
CREATE TABLE `frm_model`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `initiation` float(10, 8) NULL DEFAULT NULL,
  `frequency` float(10, 8) NULL DEFAULT NULL,
  `monetary` float(10, 8) NULL DEFAULT NULL,
  `recency` float(10, 8) NULL DEFAULT NULL,
  `level` int(11) NOT NULL,
  `customer_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 708 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of frm_model
-- ----------------------------
INSERT INTO `frm_model` VALUES (678, 0.42835951, -1.02899146, -0.89296305, 0.49462157, 0, 5);
INSERT INTO `frm_model` VALUES (679, 0.42835951, 0.17149858, -0.17484590, -0.07066023, 1, 2);
INSERT INTO `frm_model` VALUES (680, 0.33314198, 0.17149858, -0.88047403, 0.49462157, 1, 6);
INSERT INTO `frm_model` VALUES (681, 0.42835951, -1.02899146, -0.10615645, 0.49462157, 2, 3);
INSERT INTO `frm_model` VALUES (682, -2.44821167, 1.37198865, 1.12400937, -2.40244770, 3, 1);
INSERT INTO `frm_model` VALUES (683, 0.40163177, 1.37198865, 1.82339299, 0.49462157, 3, 4);
INSERT INTO `frm_model` VALUES (684, 0.42835951, 0.17149858, -0.17484590, -0.07066023, 0, 2);
INSERT INTO `frm_model` VALUES (685, 0.33314198, 0.17149858, -0.88047403, 0.49462157, 0, 6);
INSERT INTO `frm_model` VALUES (686, 0.42835951, -1.02899146, -0.10615645, 0.49462157, 1, 3);
INSERT INTO `frm_model` VALUES (687, 0.42835951, -1.02899146, -0.89296305, 0.49462157, 1, 5);
INSERT INTO `frm_model` VALUES (688, -2.44821167, 1.37198865, 1.12400937, -2.40244770, 2, 1);
INSERT INTO `frm_model` VALUES (689, 0.40163177, 1.37198865, 1.82339299, 0.49462157, 3, 4);
INSERT INTO `frm_model` VALUES (690, 0.42835951, 0.17149858, -0.17484590, -0.07066023, 0, 2);
INSERT INTO `frm_model` VALUES (691, 0.33314198, 0.17149858, -0.88047403, 0.49462157, 0, 6);
INSERT INTO `frm_model` VALUES (692, 0.42835951, -1.02899146, -0.10615645, 0.49462157, 1, 3);
INSERT INTO `frm_model` VALUES (693, 0.42835951, -1.02899146, -0.89296305, 0.49462157, 1, 5);
INSERT INTO `frm_model` VALUES (694, -2.44821167, 1.37198865, 1.12400937, -2.40244770, 2, 1);
INSERT INTO `frm_model` VALUES (695, 0.40163177, 1.37198865, 1.82339299, 0.49462157, 3, 4);
INSERT INTO `frm_model` VALUES (696, 0.42835951, 0.17149858, -0.17484590, -0.07066023, 0, 2);
INSERT INTO `frm_model` VALUES (697, 0.33314198, 0.17149858, -0.88047403, 0.49462157, 0, 6);
INSERT INTO `frm_model` VALUES (698, 0.42835951, -1.02899146, -0.10615645, 0.49462157, 1, 3);
INSERT INTO `frm_model` VALUES (699, 0.42835951, -1.02899146, -0.89296305, 0.49462157, 1, 5);
INSERT INTO `frm_model` VALUES (700, -2.44821167, 1.37198865, 1.12400937, -2.40244770, 2, 1);
INSERT INTO `frm_model` VALUES (701, 0.40163177, 1.37198865, 1.82339299, 0.49462157, 3, 4);
INSERT INTO `frm_model` VALUES (702, 0.42835951, 0.17149858, -0.17484590, -0.07066023, 0, 2);
INSERT INTO `frm_model` VALUES (703, 0.33314198, 0.17149858, -0.88047403, 0.49462157, 0, 6);
INSERT INTO `frm_model` VALUES (704, 0.42835951, -1.02899146, -0.10615645, 0.49462157, 1, 3);
INSERT INTO `frm_model` VALUES (705, 0.42835951, -1.02899146, -0.89296305, 0.49462157, 1, 5);
INSERT INTO `frm_model` VALUES (706, -2.44821167, 1.37198865, 1.12400937, -2.40244770, 2, 1);
INSERT INTO `frm_model` VALUES (707, 0.40163177, 1.37198865, 1.82339299, 0.49462157, 3, 4);

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group`  (
  `id` int(11) NOT NULL,
  `group` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group
-- ----------------------------
INSERT INTO `group` VALUES (1, '重要保持客户');
INSERT INTO `group` VALUES (2, '重要挽留客户');
INSERT INTO `group` VALUES (3, '重要发展客户');
INSERT INTO `group` VALUES (4, '一般价值');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderNo` int(11) NULL DEFAULT NULL,
  `customerID` int(11) NULL DEFAULT NULL,
  `createtime` date NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `receiver` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (1, 222, 1, '2019-11-07', '组织者组织', '22222');
INSERT INTO `order` VALUES (2, 333, 1, '2014-03-21', '111', '111');
INSERT INTO `order` VALUES (3, 444, 1, '2016-03-19', '33', '33');
INSERT INTO `order` VALUES (4, 2222222, 1, '2018-09-19', '121', '122');
INSERT INTO `order` VALUES (5, 121231, 1, '2019-03-08', '222', '222');
INSERT INTO `order` VALUES (6, 3, 2, '2018-02-25', '2', '2');
INSERT INTO `order` VALUES (7, 2, 2, '2019-01-23', '2', '2');
INSERT INTO `order` VALUES (8, 1, 2, '2014-03-25', NULL, '2222');
INSERT INTO `order` VALUES (9, 1, 2, '2019-03-20', '2', '2');
INSERT INTO `order` VALUES (10, 2, 3, '2019-01-23', '2222', '2');
INSERT INTO `order` VALUES (11, 2, 3, '2019-01-23', '22', '2');
INSERT INTO `order` VALUES (12, 2, 3, '2019-01-23', '2', '2');
INSERT INTO `order` VALUES (13, 2, 4, '2019-01-23', '2', '2');
INSERT INTO `order` VALUES (14, 2, 4, '2019-01-23', '2', '2');
INSERT INTO `order` VALUES (15, 222, 4, '2019-01-23', '2', '2');
INSERT INTO `order` VALUES (16, 2, 4, '2019-01-23', '22', '2');
INSERT INTO `order` VALUES (17, 2, 4, '2019-01-23', '2', '2');
INSERT INTO `order` VALUES (18, 6, 5, '2019-01-23', '6', '6');
INSERT INTO `order` VALUES (19, 6, 5, '2019-01-23', '6', '6');
INSERT INTO `order` VALUES (20, 6, 5, '2019-01-23', '6', '6');
INSERT INTO `order` VALUES (21, 6, 6, '2019-01-23', '6', '6');
INSERT INTO `order` VALUES (22, 1995, 6, '2019-01-23', '6', '6');
INSERT INTO `order` VALUES (23, 7, 6, '2019-01-23', '6', '6');
INSERT INTO `order` VALUES (24, 7, 6, '2019-01-23', '6', '6');
INSERT INTO `order` VALUES (25, 1995, 7, '2019-01-23', '6', '6');
INSERT INTO `order` VALUES (26, 7, 7, '2019-01-23', '6', '6');
INSERT INTO `order` VALUES (27, 7, 7, '2019-01-23', '6', '6');

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderID` int(11) NULL DEFAULT NULL,
  `productID` int(11) NULL DEFAULT NULL,
  `price` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `count` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES (1, 1, 1, '1', 1);
INSERT INTO `orderitem` VALUES (2, 2, 2, '2', 2);
INSERT INTO `orderitem` VALUES (3, 3, 3, '3', 3);
INSERT INTO `orderitem` VALUES (4, 1, 1, '312', 2);
INSERT INTO `orderitem` VALUES (5, 2, 2, '11', 3);
INSERT INTO `orderitem` VALUES (6, 6, 33, '33', 33);
INSERT INTO `orderitem` VALUES (7, 7, 3, '33', 3);
INSERT INTO `orderitem` VALUES (8, 8, 33, '33', 3);
INSERT INTO `orderitem` VALUES (9, 9, 3, '22', 2);
INSERT INTO `orderitem` VALUES (10, 10, 3, '33', 2);
INSERT INTO `orderitem` VALUES (11, 11, 3, '44', 2);
INSERT INTO `orderitem` VALUES (12, 12, 3, '55', 2);
INSERT INTO `orderitem` VALUES (13, 13, 3, '66', 2);
INSERT INTO `orderitem` VALUES (14, 14, 3, '77', 2);
INSERT INTO `orderitem` VALUES (15, 15, 3, '88', 2);
INSERT INTO `orderitem` VALUES (16, 16, 3, '99', 2);
INSERT INTO `orderitem` VALUES (17, 17, 3, '111', 2);
INSERT INTO `orderitem` VALUES (18, 18, 3, '2', 2);
INSERT INTO `orderitem` VALUES (19, 19, 3, '2', 2);
INSERT INTO `orderitem` VALUES (20, 20, 3, '2', 2);
INSERT INTO `orderitem` VALUES (21, 21, 3, '2', 2);
INSERT INTO `orderitem` VALUES (22, 22, 3, '2', 2);
INSERT INTO `orderitem` VALUES (23, 23, 3, '2', 2);
INSERT INTO `orderitem` VALUES (24, 24, 3, '2', 2);
INSERT INTO `orderitem` VALUES (25, 25, 3, '2', 2);
INSERT INTO `orderitem` VALUES (26, 26, 3, '2', 2);
INSERT INTO `orderitem` VALUES (27, 27, 3, '2', 2);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `percode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parentid` int(11) NULL DEFAULT NULL,
  `seq` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `available` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '服务管理', '666', '', NULL, NULL, '1', NULL, '2019-03-06 15:03:34', '1');
INSERT INTO `permission` VALUES (2, '客户管理', '666', '', NULL, NULL, '1', NULL, '2019-03-06 15:03:34', '1');
INSERT INTO `permission` VALUES (4, '系统管理', '666', '', NULL, NULL, '1', NULL, '2019-03-06 15:03:34', '1');
INSERT INTO `permission` VALUES (5, '统计分析', '666', '', NULL, NULL, '1', NULL, '2019-03-06 15:03:34', '1');
INSERT INTO `permission` VALUES (10, '客户监控', '666', '/custom/control', NULL, 2, '', NULL, '2019-03-06 15:03:34', '1');
INSERT INTO `permission` VALUES (11, '客户分析', '66', '/custom/analy', NULL, 2, '', NULL, '2019-03-06 15:03:34', '1');
INSERT INTO `permission` VALUES (12, '客户关怀', '66', '/custom/care', NULL, 2, '', NULL, '2019-03-06 15:03:34', '1');
INSERT INTO `permission` VALUES (13, '客户信息', '66', '/custom/new', NULL, 2, '', NULL, '2019-03-06 15:03:34', '1');
INSERT INTO `permission` VALUES (14, '添加功能', '66', '/count/add', NULL, 5, '', NULL, '2019-03-06 15:03:34', '1');
INSERT INTO `permission` VALUES (15, '费用分析', '66', '/count/cost', NULL, 5, '', NULL, '2019-03-06 15:03:34', '1');
INSERT INTO `permission` VALUES (16, '市场分析', '66', '/count/market', NULL, 5, '', NULL, '2019-03-06 15:03:34', '1');
INSERT INTO `permission` VALUES (17, '采购分析', '66', '/count/purchase', NULL, 5, NULL, NULL, '2019-03-06 15:03:34', NULL);
INSERT INTO `permission` VALUES (18, '客户来源分析', '66', '/count/sales', NULL, 5, NULL, NULL, '2019-03-06 15:03:34', NULL);
INSERT INTO `permission` VALUES (19, '客户反馈', '66', '/serve/feedback', NULL, 1, NULL, NULL, '2019-03-06 15:03:34', NULL);
INSERT INTO `permission` VALUES (20, '产品类别', '66', '/serve/kind', NULL, 1, NULL, NULL, '2019-03-06 15:03:34', NULL);
INSERT INTO `permission` VALUES (21, '产品信息', '66', '/serve/order', NULL, 1, NULL, NULL, '2019-03-06 15:03:34', NULL);
INSERT INTO `permission` VALUES (22, 'QA库管理', NULL, '/serve/QA', NULL, 1, NULL, NULL, '2019-03-06 15:03:34', NULL);
INSERT INTO `permission` VALUES (23, '用户管理', '66', '/system/user', NULL, 4, NULL, NULL, '2019-03-06 15:03:34', NULL);
INSERT INTO `permission` VALUES (24, '权限管理', '66', '/system/power', NULL, 4, NULL, NULL, '2019-03-06 15:03:34', NULL);
INSERT INTO `permission` VALUES (25, '合同订单分析', '66', '/count/contract', NULL, 5, NULL, NULL, '2019-03-06 15:03:34', NULL);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `createtime` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '上市时间',
  `descript` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品描述',
  `categoryID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '1', 1.00, '2019-02-27 17:11:47', '1', 1);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin');
INSERT INTO `role` VALUES (2, 'user');
INSERT INTO `role` VALUES (3, 'visitor');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `permission_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (1, 1);
INSERT INTO `role_permission` VALUES (2, 1);
INSERT INTO `role_permission` VALUES (3, 1);
INSERT INTO `role_permission` VALUES (4, 1);
INSERT INTO `role_permission` VALUES (5, 1);
INSERT INTO `role_permission` VALUES (6, 1);
INSERT INTO `role_permission` VALUES (7, 1);
INSERT INTO `role_permission` VALUES (8, 1);
INSERT INTO `role_permission` VALUES (9, 1);
INSERT INTO `role_permission` VALUES (10, 1);
INSERT INTO `role_permission` VALUES (11, 1);
INSERT INTO `role_permission` VALUES (12, 1);
INSERT INTO `role_permission` VALUES (13, 1);
INSERT INTO `role_permission` VALUES (14, 1);
INSERT INTO `role_permission` VALUES (15, 1);
INSERT INTO `role_permission` VALUES (16, 1);
INSERT INTO `role_permission` VALUES (17, 1);
INSERT INTO `role_permission` VALUES (18, 1);
INSERT INTO `role_permission` VALUES (19, 1);
INSERT INTO `role_permission` VALUES (20, 1);
INSERT INTO `role_permission` VALUES (21, 1);
INSERT INTO `role_permission` VALUES (22, 1);
INSERT INTO `role_permission` VALUES (23, 1);
INSERT INTO `role_permission` VALUES (24, 1);
INSERT INTO `role_permission` VALUES (25, 1);

-- ----------------------------
-- Table structure for stage
-- ----------------------------
DROP TABLE IF EXISTS `stage`;
CREATE TABLE `stage`  (
  `stage_id` int(11) NOT NULL,
  `stage_value` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`stage_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stage
-- ----------------------------
INSERT INTO `stage` VALUES (1, 'O(∩_∩)O');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL,
  `login_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'Mark', 'Mark', '2019-03-18 19:15:51', '1', '111', '111', 'e10adc3949ba59abbe56e057f20f883e', '男');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1);
INSERT INTO `user_role` VALUES (1, 2);

SET FOREIGN_KEY_CHECKS = 1;
