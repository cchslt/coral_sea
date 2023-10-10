/*
 Navicat Premium Data Transfer

 Source Server         : llll
 Source Server Type    : MySQL
 Source Server Version : 80034 (8.0.34)
 Source Host           : 120.24.252.160:3306
 Source Schema         : psi

 Target Server Type    : MySQL
 Target Server Version : 80034 (8.0.34)
 File Encoding         : 65001

 Date: 10/10/2023 11:27:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_inventory_change_log
-- ----------------------------
DROP TABLE IF EXISTS `t_inventory_change_log`;
CREATE TABLE `t_inventory_change_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `product_sku_id` bigint NULL DEFAULT NULL COMMENT 'sku id',
  `warehouse_id` bigint NULL DEFAULT NULL,
  `change_type` tinyint NOT NULL DEFAULT 1 COMMENT '库存变更类型:1:增加,2:减少',
  `inventory_type` tinyint NOT NULL DEFAULT 1 COMMENT '变更库存类型:1:可售库存,2:可用,3待入库,4占用',
  `change_quantity` int NOT NULL DEFAULT 0 COMMENT '变更数量 ',
  `change_source_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '变更源编码,eg:order_code',
  `change_remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '变更占用备注',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除0:未删除,1删除',
  `create_by` bigint NULL DEFAULT 0 COMMENT '创建人',
  `update_by` bigint NULL DEFAULT 0 COMMENT '最后修改人',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_product_sku_id`(`product_sku_id` ASC) USING BTREE,
  INDEX `index_inventory_type`(`inventory_type` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 68 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '库存变更记录主表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_inventory_change_log
-- ----------------------------
INSERT INTO `t_inventory_change_log` VALUES (1, 2, 610, 1, 1, 100, 'STO202310718173195699', NULL, 0, 482, 482, '2023-10-07 17:35:31', '2023-10-07 17:35:31');
INSERT INTO `t_inventory_change_log` VALUES (2, 3, 610, 1, 1, 10, 'STO202310718410187763', NULL, 0, 482, 482, '2023-10-07 17:39:01', '2023-10-07 17:39:01');
INSERT INTO `t_inventory_change_log` VALUES (3, 1, 610, 1, 1, 100, 'STO202310718413440917', NULL, 0, 482, 482, '2023-10-07 17:41:34', '2023-10-07 17:41:34');
INSERT INTO `t_inventory_change_log` VALUES (4, 3, 611, 1, 1, 50, 'STO202310819394081025', NULL, 0, 482, 482, '2023-10-08 18:07:40', '2023-10-08 18:07:40');
INSERT INTO `t_inventory_change_log` VALUES (5, 3, 611, 2, 1, 30, 'STO202310819594669123', NULL, 0, 482, 482, '2023-10-08 18:12:46', '2023-10-08 18:12:46');
INSERT INTO `t_inventory_change_log` VALUES (6, 3, 611, 2, 1, 20, 'STO202310911405144719', NULL, 0, 482, 482, '2023-10-09 10:01:51', '2023-10-09 10:01:51');
INSERT INTO `t_inventory_change_log` VALUES (7, 3, 610, 1, 1, 20, 'STO202310911405144719', NULL, 0, 482, 482, '2023-10-09 10:01:51', '2023-10-09 10:01:51');
INSERT INTO `t_inventory_change_log` VALUES (8, 3, 610, 1, 1, 50, 'STO202310911182224563', NULL, 0, 482, 482, '2023-10-09 10:23:22', '2023-10-09 10:23:22');
INSERT INTO `t_inventory_change_log` VALUES (9, 3, 610, 2, 1, 2, 'STO202310911912405614', NULL, 0, 482, 482, '2023-10-09 10:27:24', '2023-10-09 10:27:24');
INSERT INTO `t_inventory_change_log` VALUES (10, 3, 610, 1, 1, 2, 'STO202310911912405614', NULL, 0, 482, 482, '2023-10-09 10:27:24', '2023-10-09 10:27:24');
INSERT INTO `t_inventory_change_log` VALUES (11, 3, 610, 2, 1, 5, 'STO202310911195400023', NULL, 0, 482, 482, '2023-10-09 10:30:54', '2023-10-09 10:30:54');
INSERT INTO `t_inventory_change_log` VALUES (12, 18, 616, 1, 1, 5, 'STO2023101011593352335', NULL, 0, 482, 482, '2023-10-10 10:25:33', '2023-10-10 10:25:33');
INSERT INTO `t_inventory_change_log` VALUES (13, 24, 616, 1, 1, 5, 'STO2023101011693616072', NULL, 0, 482, 482, '2023-10-10 10:26:36', '2023-10-10 10:26:36');
INSERT INTO `t_inventory_change_log` VALUES (14, 21, 616, 1, 1, 5, 'STO2023101011111107271', NULL, 0, 482, 482, '2023-10-10 10:27:11', '2023-10-10 10:27:11');
INSERT INTO `t_inventory_change_log` VALUES (15, 23, 616, 1, 1, 5, 'STO2023101011800917697', NULL, 0, 482, 482, '2023-10-10 10:31:09', '2023-10-10 10:31:09');
INSERT INTO `t_inventory_change_log` VALUES (16, 22, 616, 1, 1, 5, 'STO2023101011653789150', NULL, 0, 482, 482, '2023-10-10 10:31:37', '2023-10-10 10:31:37');
INSERT INTO `t_inventory_change_log` VALUES (17, 7, 616, 1, 1, 5, 'STO2023101011440220837', NULL, 0, 482, 482, '2023-10-10 10:32:02', '2023-10-10 10:32:02');
INSERT INTO `t_inventory_change_log` VALUES (18, 10, 616, 1, 1, 5, 'STO2023101011474375981', NULL, 0, 482, 482, '2023-10-10 10:32:43', '2023-10-10 10:32:43');
INSERT INTO `t_inventory_change_log` VALUES (19, 9, 616, 1, 1, 5, 'STO2023101011375638357', NULL, 0, 482, 482, '2023-10-10 10:32:56', '2023-10-10 10:32:56');
INSERT INTO `t_inventory_change_log` VALUES (20, 14, 616, 1, 1, 5, 'STO2023101011572337511', NULL, 0, 482, 482, '2023-10-10 10:33:23', '2023-10-10 10:33:23');
INSERT INTO `t_inventory_change_log` VALUES (21, 13, 616, 1, 1, 5, 'STO2023101011144165527', NULL, 0, 482, 482, '2023-10-10 10:33:41', '2023-10-10 10:33:41');
INSERT INTO `t_inventory_change_log` VALUES (22, 5, 616, 1, 1, 5, 'STO2023101011335108865', NULL, 0, 482, 482, '2023-10-10 10:51:51', '2023-10-10 10:51:51');
INSERT INTO `t_inventory_change_log` VALUES (23, 6, 616, 1, 1, 5, 'STO2023101011911954069', NULL, 0, 482, 482, '2023-10-10 10:53:19', '2023-10-10 10:53:19');
INSERT INTO `t_inventory_change_log` VALUES (24, 8, 616, 1, 1, 5, 'STO2023101011720764009', NULL, 0, 482, 482, '2023-10-10 10:54:07', '2023-10-10 10:54:07');
INSERT INTO `t_inventory_change_log` VALUES (25, 11, 616, 1, 1, 5, 'STO2023101011421420498', NULL, 0, 482, 482, '2023-10-10 10:55:14', '2023-10-10 10:55:14');
INSERT INTO `t_inventory_change_log` VALUES (26, 12, 616, 1, 1, 5, 'STO2023101011534990832', NULL, 0, 482, 482, '2023-10-10 10:55:49', '2023-10-10 10:55:49');
INSERT INTO `t_inventory_change_log` VALUES (27, 15, 616, 1, 1, 5, 'STO2023101011873091350', NULL, 0, 482, 482, '2023-10-10 10:56:30', '2023-10-10 10:56:30');
INSERT INTO `t_inventory_change_log` VALUES (28, 16, 616, 1, 1, 5, 'STO2023101011794624020', NULL, 0, 482, 482, '2023-10-10 10:56:46', '2023-10-10 10:56:46');
INSERT INTO `t_inventory_change_log` VALUES (29, 17, 616, 1, 1, 5, 'STO2023101011591640452', NULL, 0, 482, 482, '2023-10-10 10:57:16', '2023-10-10 10:57:16');
INSERT INTO `t_inventory_change_log` VALUES (30, 19, 616, 1, 1, 5, 'STO2023101011065198385', NULL, 0, 482, 482, '2023-10-10 10:57:51', '2023-10-10 10:57:51');
INSERT INTO `t_inventory_change_log` VALUES (31, 20, 616, 1, 1, 5, 'STO2023101011712371133', NULL, 0, 482, 482, '2023-10-10 10:58:23', '2023-10-10 10:58:23');
INSERT INTO `t_inventory_change_log` VALUES (32, 20, 616, 2, 1, 4, 'STO2023101011591269661', NULL, 0, 482, 482, '2023-10-10 10:59:12', '2023-10-10 10:59:12');
INSERT INTO `t_inventory_change_log` VALUES (33, 20, 617, 1, 1, 4, 'STO2023101011591269661', NULL, 0, 482, 482, '2023-10-10 10:59:12', '2023-10-10 10:59:12');
INSERT INTO `t_inventory_change_log` VALUES (34, 19, 616, 2, 1, 5, 'STO2023101011275305998', NULL, 0, 482, 482, '2023-10-10 10:59:53', '2023-10-10 10:59:53');
INSERT INTO `t_inventory_change_log` VALUES (35, 19, 617, 1, 1, 5, 'STO2023101011275305998', NULL, 0, 482, 482, '2023-10-10 10:59:53', '2023-10-10 10:59:53');
INSERT INTO `t_inventory_change_log` VALUES (36, 17, 616, 2, 1, 5, 'STO2023101012581525791', NULL, 0, 482, 482, '2023-10-10 11:00:15', '2023-10-10 11:00:15');
INSERT INTO `t_inventory_change_log` VALUES (37, 17, 617, 1, 1, 5, 'STO2023101012581525791', NULL, 0, 482, 482, '2023-10-10 11:00:15', '2023-10-10 11:00:15');
INSERT INTO `t_inventory_change_log` VALUES (38, 16, 616, 2, 1, 5, 'STO2023101012851853436', NULL, 0, 482, 482, '2023-10-10 11:01:18', '2023-10-10 11:01:18');
INSERT INTO `t_inventory_change_log` VALUES (39, 16, 617, 1, 1, 5, 'STO2023101012851853436', NULL, 0, 482, 482, '2023-10-10 11:01:18', '2023-10-10 11:01:18');
INSERT INTO `t_inventory_change_log` VALUES (40, 15, 616, 2, 1, 4, 'STO2023101012454177201', NULL, 0, 482, 482, '2023-10-10 11:01:41', '2023-10-10 11:01:41');
INSERT INTO `t_inventory_change_log` VALUES (41, 15, 617, 1, 1, 4, 'STO2023101012454177201', NULL, 0, 482, 482, '2023-10-10 11:01:41', '2023-10-10 11:01:41');
INSERT INTO `t_inventory_change_log` VALUES (42, 12, 616, 2, 1, 5, 'STO2023101012042560342', NULL, 0, 482, 482, '2023-10-10 11:02:25', '2023-10-10 11:02:25');
INSERT INTO `t_inventory_change_log` VALUES (43, 12, 617, 1, 1, 5, 'STO2023101012042560342', NULL, 0, 482, 482, '2023-10-10 11:02:25', '2023-10-10 11:02:25');
INSERT INTO `t_inventory_change_log` VALUES (44, 11, 616, 2, 1, 5, 'STO2023101012674170860', NULL, 0, 482, 482, '2023-10-10 11:02:41', '2023-10-10 11:02:41');
INSERT INTO `t_inventory_change_log` VALUES (45, 11, 617, 1, 1, 5, 'STO2023101012674170860', NULL, 0, 482, 482, '2023-10-10 11:02:41', '2023-10-10 11:02:41');
INSERT INTO `t_inventory_change_log` VALUES (46, 8, 616, 2, 1, 5, 'STO2023101012460273356', NULL, 0, 482, 482, '2023-10-10 11:03:02', '2023-10-10 11:03:02');
INSERT INTO `t_inventory_change_log` VALUES (47, 8, 617, 1, 1, 5, 'STO2023101012460273356', NULL, 0, 482, 482, '2023-10-10 11:03:02', '2023-10-10 11:03:02');
INSERT INTO `t_inventory_change_log` VALUES (48, 6, 616, 2, 1, 5, 'STO2023101012353068702', NULL, 0, 482, 482, '2023-10-10 11:03:30', '2023-10-10 11:03:30');
INSERT INTO `t_inventory_change_log` VALUES (49, 6, 617, 1, 1, 5, 'STO2023101012353068702', NULL, 0, 482, 482, '2023-10-10 11:03:30', '2023-10-10 11:03:30');
INSERT INTO `t_inventory_change_log` VALUES (50, 5, 616, 2, 1, 5, 'STO2023101012074588053', NULL, 0, 482, 482, '2023-10-10 11:03:45', '2023-10-10 11:03:45');
INSERT INTO `t_inventory_change_log` VALUES (51, 5, 617, 1, 1, 5, 'STO2023101012074588053', NULL, 0, 482, 482, '2023-10-10 11:03:45', '2023-10-10 11:03:45');
INSERT INTO `t_inventory_change_log` VALUES (52, 15, 616, 2, 1, 1, 'STO2023101012192612480', NULL, 0, 482, 482, '2023-10-10 11:04:26', '2023-10-10 11:04:26');
INSERT INTO `t_inventory_change_log` VALUES (53, 15, 617, 1, 1, 1, 'STO2023101012192612480', NULL, 0, 482, 482, '2023-10-10 11:04:26', '2023-10-10 11:04:26');
INSERT INTO `t_inventory_change_log` VALUES (54, 13, 616, 2, 1, 5, 'STO2023101012410234224', NULL, 0, 482, 482, '2023-10-10 11:05:02', '2023-10-10 11:05:02');
INSERT INTO `t_inventory_change_log` VALUES (55, 13, 617, 1, 1, 5, 'STO2023101012410234224', NULL, 0, 482, 482, '2023-10-10 11:05:02', '2023-10-10 11:05:02');
INSERT INTO `t_inventory_change_log` VALUES (56, 14, 616, 2, 1, 5, 'STO2023101012821507462', NULL, 0, 482, 482, '2023-10-10 11:05:15', '2023-10-10 11:05:15');
INSERT INTO `t_inventory_change_log` VALUES (57, 14, 617, 1, 1, 5, 'STO2023101012821507462', NULL, 0, 482, 482, '2023-10-10 11:05:15', '2023-10-10 11:05:15');
INSERT INTO `t_inventory_change_log` VALUES (58, 9, 616, 2, 1, 5, 'STO2023101012453231280', NULL, 0, 482, 482, '2023-10-10 11:05:32', '2023-10-10 11:05:32');
INSERT INTO `t_inventory_change_log` VALUES (59, 9, 617, 1, 1, 5, 'STO2023101012453231280', NULL, 0, 482, 482, '2023-10-10 11:05:32', '2023-10-10 11:05:32');
INSERT INTO `t_inventory_change_log` VALUES (60, 10, 616, 2, 1, 5, 'STO2023101012025794590', NULL, 0, 482, 482, '2023-10-10 11:05:57', '2023-10-10 11:05:57');
INSERT INTO `t_inventory_change_log` VALUES (61, 10, 617, 1, 1, 5, 'STO2023101012025794590', NULL, 0, 482, 482, '2023-10-10 11:05:57', '2023-10-10 11:05:57');
INSERT INTO `t_inventory_change_log` VALUES (62, 7, 616, 2, 1, 5, 'STO2023101012401833690', NULL, 0, 482, 482, '2023-10-10 11:06:18', '2023-10-10 11:06:18');
INSERT INTO `t_inventory_change_log` VALUES (63, 7, 617, 1, 1, 5, 'STO2023101012401833690', NULL, 0, 482, 482, '2023-10-10 11:06:18', '2023-10-10 11:06:18');
INSERT INTO `t_inventory_change_log` VALUES (64, 22, 616, 2, 1, 5, 'STO2023101012843429257', NULL, 0, 482, 482, '2023-10-10 11:06:34', '2023-10-10 11:06:34');
INSERT INTO `t_inventory_change_log` VALUES (65, 22, 617, 1, 1, 5, 'STO2023101012843429257', NULL, 0, 482, 482, '2023-10-10 11:06:34', '2023-10-10 11:06:34');
INSERT INTO `t_inventory_change_log` VALUES (66, 23, 616, 2, 1, 5, 'STO2023101012350741664', NULL, 0, 482, 482, '2023-10-10 11:07:07', '2023-10-10 11:07:07');
INSERT INTO `t_inventory_change_log` VALUES (67, 23, 617, 1, 1, 5, 'STO2023101012350741664', NULL, 0, 482, 482, '2023-10-10 11:07:07', '2023-10-10 11:07:07');

-- ----------------------------
-- Table structure for t_psi_in_warehouse_user_relation
-- ----------------------------
DROP TABLE IF EXISTS `t_psi_in_warehouse_user_relation`;
CREATE TABLE `t_psi_in_warehouse_user_relation`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `warehouse_id` bigint NULL DEFAULT NULL COMMENT '仓库id',
  `user_id` bigint NULL DEFAULT NULL COMMENT 'psi用户id',
  `relation_type` tinyint NOT NULL DEFAULT 1 COMMENT '关联关系类型 1:强关联',
  `warehouse_type` tinyint NOT NULL DEFAULT 0 COMMENT '仓库类型',
  `contract_type` tinyint NOT NULL DEFAULT 0 COMMENT '合同类型',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除0:未删除,1删除',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_warehouse_id`(`warehouse_id` ASC) USING BTREE,
  INDEX `index_user_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 416 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '卖家调入仓库关系配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_psi_in_warehouse_user_relation
-- ----------------------------
INSERT INTO `t_psi_in_warehouse_user_relation` VALUES (408, 610, 482, 0, 0, 0, 1, '2023-10-07 10:20:23', '2023-10-08 14:32:38');
INSERT INTO `t_psi_in_warehouse_user_relation` VALUES (409, 611, 482, 0, 0, 0, 1, '2023-10-07 10:25:20', '2023-10-08 14:32:38');
INSERT INTO `t_psi_in_warehouse_user_relation` VALUES (410, 612, 482, 0, 0, 0, 1, '2023-10-07 10:25:31', '2023-10-08 14:32:38');
INSERT INTO `t_psi_in_warehouse_user_relation` VALUES (411, 613, 482, 0, 0, 0, 1, '2023-10-07 10:25:50', '2023-10-08 14:32:38');
INSERT INTO `t_psi_in_warehouse_user_relation` VALUES (412, 614, 482, 0, 0, 0, 1, '2023-10-07 10:50:26', '2023-10-08 14:32:38');
INSERT INTO `t_psi_in_warehouse_user_relation` VALUES (413, 615, 482, 0, 0, 0, 1, '2023-10-07 17:57:51', '2023-10-08 14:32:38');
INSERT INTO `t_psi_in_warehouse_user_relation` VALUES (414, 616, 482, 0, 0, 0, 0, '2023-10-09 10:56:25', '2023-10-09 10:56:25');
INSERT INTO `t_psi_in_warehouse_user_relation` VALUES (415, 617, 482, 0, 0, 0, 0, '2023-10-10 10:35:48', '2023-10-10 10:35:48');

-- ----------------------------
-- Table structure for t_psi_inventory
-- ----------------------------
DROP TABLE IF EXISTS `t_psi_inventory`;
CREATE TABLE `t_psi_inventory`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `product_sku_id` bigint NOT NULL COMMENT '库存归属 sku id',
  `product_sku_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '冗余 sku编码',
  `warehouse_id` bigint NOT NULL COMMENT '库存所属 仓库id',
  `sellable_quantity` int NOT NULL DEFAULT 0 COMMENT '可售库存(可用库存 - 占用库存) ',
  `available_quantity` int NOT NULL DEFAULT 0 COMMENT '可用库存',
  `in_transit_quantity` int NOT NULL DEFAULT 0 COMMENT '在途库存',
  `occupied_quantity` int NOT NULL DEFAULT 0 COMMENT '占用库存',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除0:未删除,1删除',
  `create_by` bigint NULL DEFAULT 0 COMMENT '创建人',
  `update_by` bigint NULL DEFAULT 0 COMMENT '最后修改人',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_product_sku_id`(`product_sku_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '库存主表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_psi_inventory
-- ----------------------------
INSERT INTO `t_psi_inventory` VALUES (1, 2, '2023100701', 610, 100, 100, 0, 0, 0, 482, 482, '2023-10-07 17:35:31', '2023-10-07 17:35:31');
INSERT INTO `t_psi_inventory` VALUES (2, 3, '20301007002', 610, 75, 75, 0, 0, 0, 482, 482, '2023-10-07 17:39:01', '2023-10-09 10:30:54');
INSERT INTO `t_psi_inventory` VALUES (3, 1, 'sku001', 610, 100, 100, 0, 0, 0, 482, 482, '2023-10-07 17:41:34', '2023-10-07 17:41:34');
INSERT INTO `t_psi_inventory` VALUES (4, 3, '20301007002', 611, 0, 0, 0, 0, 0, 482, 482, '2023-10-08 18:07:40', '2023-10-09 10:01:51');
INSERT INTO `t_psi_inventory` VALUES (5, 18, '20231011014', 616, 5, 5, 0, 0, 0, 482, 482, '2023-10-10 10:25:33', '2023-10-10 10:25:33');
INSERT INTO `t_psi_inventory` VALUES (6, 24, '20231011020', 616, 5, 5, 0, 0, 0, 482, 482, '2023-10-10 10:26:36', '2023-10-10 10:26:36');
INSERT INTO `t_psi_inventory` VALUES (7, 21, '20231011017', 616, 5, 5, 0, 0, 0, 482, 482, '2023-10-10 10:27:11', '2023-10-10 10:27:11');
INSERT INTO `t_psi_inventory` VALUES (8, 23, '20231011019', 616, 0, 0, 0, 0, 0, 482, 482, '2023-10-10 10:31:09', '2023-10-10 11:07:07');
INSERT INTO `t_psi_inventory` VALUES (9, 22, '20231011018', 616, 0, 0, 0, 0, 0, 482, 482, '2023-10-10 10:31:37', '2023-10-10 11:06:34');
INSERT INTO `t_psi_inventory` VALUES (10, 7, '20231011003', 616, 0, 0, 0, 0, 0, 482, 482, '2023-10-10 10:32:02', '2023-10-10 11:06:18');
INSERT INTO `t_psi_inventory` VALUES (11, 10, '20231011006', 616, 0, 0, 0, 0, 0, 482, 482, '2023-10-10 10:32:43', '2023-10-10 11:05:57');
INSERT INTO `t_psi_inventory` VALUES (12, 9, '20231011005', 616, 0, 0, 0, 0, 0, 482, 482, '2023-10-10 10:32:56', '2023-10-10 11:05:32');
INSERT INTO `t_psi_inventory` VALUES (13, 14, '20231011010', 616, 0, 0, 0, 0, 0, 482, 482, '2023-10-10 10:33:23', '2023-10-10 11:05:15');
INSERT INTO `t_psi_inventory` VALUES (14, 13, '20231011009', 616, 0, 0, 0, 0, 0, 482, 482, '2023-10-10 10:33:41', '2023-10-10 11:05:02');
INSERT INTO `t_psi_inventory` VALUES (15, 5, '20231011001', 616, 0, 0, 0, 0, 0, 482, 482, '2023-10-10 10:51:51', '2023-10-10 11:03:45');
INSERT INTO `t_psi_inventory` VALUES (16, 6, '20231011002', 616, 0, 0, 0, 0, 0, 482, 482, '2023-10-10 10:53:19', '2023-10-10 11:03:30');
INSERT INTO `t_psi_inventory` VALUES (17, 8, '20231011004', 616, 0, 0, 0, 0, 0, 482, 482, '2023-10-10 10:54:07', '2023-10-10 11:03:02');
INSERT INTO `t_psi_inventory` VALUES (18, 11, '20231011007', 616, 0, 0, 0, 0, 0, 482, 482, '2023-10-10 10:55:14', '2023-10-10 11:02:41');
INSERT INTO `t_psi_inventory` VALUES (19, 12, '20231011008', 616, 0, 0, 0, 0, 0, 482, 482, '2023-10-10 10:55:49', '2023-10-10 11:02:25');
INSERT INTO `t_psi_inventory` VALUES (20, 15, '20231011011', 616, 0, 0, 0, 0, 0, 482, 482, '2023-10-10 10:56:30', '2023-10-10 11:04:26');
INSERT INTO `t_psi_inventory` VALUES (21, 16, '20231011012', 616, 0, 0, 0, 0, 0, 482, 482, '2023-10-10 10:56:46', '2023-10-10 11:01:18');
INSERT INTO `t_psi_inventory` VALUES (22, 17, '20231011013', 616, 0, 0, 0, 0, 0, 482, 482, '2023-10-10 10:57:16', '2023-10-10 11:00:15');
INSERT INTO `t_psi_inventory` VALUES (23, 19, '20231011015', 616, 0, 0, 0, 0, 0, 482, 482, '2023-10-10 10:57:51', '2023-10-10 10:59:53');
INSERT INTO `t_psi_inventory` VALUES (24, 20, '20231011016', 616, 1, 1, 0, 0, 0, 482, 482, '2023-10-10 10:58:23', '2023-10-10 10:59:12');
INSERT INTO `t_psi_inventory` VALUES (25, 20, '20231011016', 617, 4, 4, 0, 0, 0, 482, 482, '2023-10-10 10:59:12', '2023-10-10 10:59:12');
INSERT INTO `t_psi_inventory` VALUES (26, 19, '20231011015', 617, 5, 5, 0, 0, 0, 482, 482, '2023-10-10 10:59:53', '2023-10-10 10:59:53');
INSERT INTO `t_psi_inventory` VALUES (27, 17, '20231011013', 617, 5, 5, 0, 0, 0, 482, 482, '2023-10-10 11:00:15', '2023-10-10 11:00:15');
INSERT INTO `t_psi_inventory` VALUES (28, 16, '20231011012', 617, 5, 5, 0, 0, 0, 482, 482, '2023-10-10 11:01:18', '2023-10-10 11:01:18');
INSERT INTO `t_psi_inventory` VALUES (29, 15, '20231011011', 617, 5, 5, 0, 0, 0, 482, 482, '2023-10-10 11:01:41', '2023-10-10 11:04:26');
INSERT INTO `t_psi_inventory` VALUES (30, 12, '20231011008', 617, 5, 5, 0, 0, 0, 482, 482, '2023-10-10 11:02:25', '2023-10-10 11:02:25');
INSERT INTO `t_psi_inventory` VALUES (31, 11, '20231011007', 617, 5, 5, 0, 0, 0, 482, 482, '2023-10-10 11:02:41', '2023-10-10 11:02:41');
INSERT INTO `t_psi_inventory` VALUES (32, 8, '20231011004', 617, 5, 5, 0, 0, 0, 482, 482, '2023-10-10 11:03:02', '2023-10-10 11:03:02');
INSERT INTO `t_psi_inventory` VALUES (33, 6, '20231011002', 617, 5, 5, 0, 0, 0, 482, 482, '2023-10-10 11:03:30', '2023-10-10 11:03:30');
INSERT INTO `t_psi_inventory` VALUES (34, 5, '20231011001', 617, 5, 5, 0, 0, 0, 482, 482, '2023-10-10 11:03:45', '2023-10-10 11:03:45');
INSERT INTO `t_psi_inventory` VALUES (35, 13, '20231011009', 617, 5, 5, 0, 0, 0, 482, 482, '2023-10-10 11:05:02', '2023-10-10 11:05:02');
INSERT INTO `t_psi_inventory` VALUES (36, 14, '20231011010', 617, 5, 5, 0, 0, 0, 482, 482, '2023-10-10 11:05:15', '2023-10-10 11:05:15');
INSERT INTO `t_psi_inventory` VALUES (37, 9, '20231011005', 617, 5, 5, 0, 0, 0, 482, 482, '2023-10-10 11:05:32', '2023-10-10 11:05:32');
INSERT INTO `t_psi_inventory` VALUES (38, 10, '20231011006', 617, 5, 5, 0, 0, 0, 482, 482, '2023-10-10 11:05:57', '2023-10-10 11:05:57');
INSERT INTO `t_psi_inventory` VALUES (39, 7, '20231011003', 617, 5, 5, 0, 0, 0, 482, 482, '2023-10-10 11:06:18', '2023-10-10 11:06:18');
INSERT INTO `t_psi_inventory` VALUES (40, 22, '20231011018', 617, 5, 5, 0, 0, 0, 482, 482, '2023-10-10 11:06:34', '2023-10-10 11:06:34');
INSERT INTO `t_psi_inventory` VALUES (41, 23, '20231011019', 617, 5, 5, 0, 0, 0, 482, 482, '2023-10-10 11:07:07', '2023-10-10 11:07:07');

-- ----------------------------
-- Table structure for t_psi_out_warehouse_user_relation
-- ----------------------------
DROP TABLE IF EXISTS `t_psi_out_warehouse_user_relation`;
CREATE TABLE `t_psi_out_warehouse_user_relation`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `warehouse_id` bigint NULL DEFAULT NULL COMMENT '仓库id',
  `user_id` bigint NULL DEFAULT NULL COMMENT 'psi用户id',
  `relation_type` tinyint NOT NULL DEFAULT 1 COMMENT '关联关系类型 1:强关联',
  `warehouse_type` tinyint NOT NULL DEFAULT 0 COMMENT '仓库类型',
  `contract_type` tinyint NOT NULL DEFAULT 0 COMMENT '合同类型',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除0:未删除,1删除',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_warehouse_id`(`warehouse_id` ASC) USING BTREE,
  INDEX `index_user_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 601 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '卖家调出仓库关系配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_psi_out_warehouse_user_relation
-- ----------------------------
INSERT INTO `t_psi_out_warehouse_user_relation` VALUES (593, 610, 482, 0, 0, 0, 1, '2023-10-07 10:20:23', '2023-10-08 14:32:38');
INSERT INTO `t_psi_out_warehouse_user_relation` VALUES (594, 611, 482, 0, 0, 0, 1, '2023-10-07 10:25:20', '2023-10-08 14:32:38');
INSERT INTO `t_psi_out_warehouse_user_relation` VALUES (595, 612, 482, 0, 0, 0, 1, '2023-10-07 10:25:31', '2023-10-08 14:32:38');
INSERT INTO `t_psi_out_warehouse_user_relation` VALUES (596, 613, 482, 0, 0, 0, 1, '2023-10-07 10:25:50', '2023-10-08 14:32:38');
INSERT INTO `t_psi_out_warehouse_user_relation` VALUES (597, 614, 482, 0, 0, 0, 1, '2023-10-07 10:50:26', '2023-10-08 14:32:38');
INSERT INTO `t_psi_out_warehouse_user_relation` VALUES (598, 615, 482, 0, 0, 0, 1, '2023-10-07 17:57:51', '2023-10-08 14:32:38');
INSERT INTO `t_psi_out_warehouse_user_relation` VALUES (599, 616, 482, 0, 0, 0, 0, '2023-10-09 10:56:25', '2023-10-09 10:56:25');
INSERT INTO `t_psi_out_warehouse_user_relation` VALUES (600, 617, 482, 0, 0, 0, 0, '2023-10-10 10:35:47', '2023-10-10 10:35:47');

-- ----------------------------
-- Table structure for t_psi_outbound_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_psi_outbound_order_detail`;
CREATE TABLE `t_psi_outbound_order_detail`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `outbound_order_id` bigint NOT NULL COMMENT '关联出库单主键',
  `outbound_order_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '出库单单号',
  `source_type` tinyint NOT NULL DEFAULT 0 COMMENT '冗余 来源：0->手动创建；1->Egatee',
  `warehouse_id` bigint NOT NULL COMMENT '冗余 所在仓库id 继承入库单',
  `source_user_id` bigint NOT NULL COMMENT '冗余 来源用户id',
  `belong_user_id` bigint NOT NULL COMMENT '所属PSI用户id',
  `source_sku_id` bigint NULL DEFAULT NULL COMMENT '冗余 来源skuid ',
  `product_sku_id` bigint NOT NULL COMMENT 'skuid ',
  `product_sku_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'sku编码',
  `product_sku_name` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '冗余 商品名称',
  `product_attribute` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '冗余 商品销售属性',
  `product_sku_price` decimal(22, 2) NULL DEFAULT NULL COMMENT '商品 销售单价',
  `product_amount` decimal(22, 2) NULL DEFAULT NULL COMMENT '商品总价= 单价*数量',
  `product_sale_unit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '销售单位(多项)',
  `product_count` int NULL DEFAULT NULL COMMENT '冗余 商品数量',
  `received_outbound_count` int NULL DEFAULT NULL COMMENT '已出库数量',
  `should_outbound_count` int NULL DEFAULT NULL COMMENT '应出库数量',
  `return_count` int NULL DEFAULT 0 COMMENT '退货数量',
  `ware_batch_number` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '冗余 当前商品入库批次号 数据来自t_psi_imei_serial',
  `add_type` tinyint NOT NULL DEFAULT 0 COMMENT '数据录入来源：1->扫码录入,2 手动录入,3:导入',
  `country_id` bigint NULL DEFAULT 0 COMMENT '国家Id',
  `country_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '国家code',
  `create_by` bigint NULL DEFAULT 0 COMMENT '创建人',
  `update_by` bigint NULL DEFAULT 0 COMMENT '最后修改人',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除0:未删除,1删除',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_outbound_order_code`(`outbound_order_code` ASC) USING BTREE,
  INDEX `index_belong_user_id`(`belong_user_id` ASC) USING BTREE,
  INDEX `index_product_sku_id`(`product_sku_id` ASC) USING BTREE,
  INDEX `index_warehouse_id`(`warehouse_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14460 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '出库单详情信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_psi_outbound_order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for t_psi_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_psi_permission`;
CREATE TABLE `t_psi_permission`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `menu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '按钮名称',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'api权限名',
  `path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'api权限资源',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'icon',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '权限父节点（0为根节点）',
  `type` tinyint(1) NULL DEFAULT 0 COMMENT '菜单类型 （0菜单 1按钮）',
  `hideIn_menu` tinyint(1) NULL DEFAULT NULL COMMENT '是否隐藏tab',
  `show` tinyint(1) NULL DEFAULT 0 COMMENT '是否展示菜单（以后设置按钮权限后可废除）',
  `virtual` tinyint(1) NULL DEFAULT 0 COMMENT '是否是虚拟节点',
  `status` tinyint NULL DEFAULT 1 COMMENT '状态：0禁用，1启用',
  `priority` int NULL DEFAULT 0 COMMENT '优先级，越大，同级显示的时候越靠前',
  `redirect` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `en` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '英文',
  `in_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '印度尼西亚语言',
  `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1802 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_psi_permission
-- ----------------------------
INSERT INTO `t_psi_permission` VALUES (1200, '作品库存管理', '作品库存管理', '/warehousing', 'profile', 0, 0, 0, 1, 0, 1, 3, NULL, NULL, 'Inbound', 'Penjagaan', '2022-03-08 16:01:05', '2022-03-08 16:01:05', 0);
INSERT INTO `t_psi_permission` VALUES (1201, '采购入库', '采购入库', '/warehousing/PurchaseInbound', 'smile', 1200, 0, 0, 1, 0, 1, 1, NULL, NULL, 'Purchase Inbound', 'Penerimaan Pembelian', '2022-03-08 16:01:42', '2022-03-08 16:01:42', 1);
INSERT INTO `t_psi_permission` VALUES (1202, '新增采购入库', '新增采购入库', '/warehousing/PurchaseInbound/EditInbound', 'smile', 1200, 0, 1, 0, 0, 1, 2, NULL, NULL, 'Edit Purchase Inbound', 'Tambah Penerimaan Pembelian', '2022-03-08 16:02:21', '2022-03-08 16:02:21', 0);
INSERT INTO `t_psi_permission` VALUES (1203, '作品基础数据', '作品基础数据', '/warehousing/OtherInbound', 'smile', 1200, 0, 0, 1, 0, 1, 3, NULL, NULL, 'Other Inbound', 'Gudang lain', '2022-03-08 16:02:52', '2022-03-08 16:02:52', 0);
INSERT INTO `t_psi_permission` VALUES (1204, '新增其他入库单', '新增其他入库', '/warehousing/OtherInbound/Add', 'smile', 1200, 0, 1, 0, 0, 1, 4, NULL, NULL, 'Other Inbound Add', 'Tambah penyimpanan Lain', '2022-03-08 16:03:17', '2022-03-08 16:03:17', 0);
INSERT INTO `t_psi_permission` VALUES (1205, '制作作品管理', '制作作品管理', '/warehousing/TransferInbound', 'CheckCircleOutlined', 1200, 0, 0, 1, 0, 1, 5, NULL, NULL, 'Transfer Inbound', 'Transfer terima', '2022-11-02 11:52:02', '2022-11-02 11:52:02', 0);
INSERT INTO `t_psi_permission` VALUES (1206, '操作入库', '操作入库', '/warehousing/TransferInbound/EditInbound', 'CheckCircleOutlined', 1200, 0, 1, 0, 0, 1, 6, NULL, NULL, 'Edit Transfer Inbound', 'Operasi penyimpanan', '2022-11-02 11:52:02', '2022-11-02 11:52:02', 0);
INSERT INTO `t_psi_permission` VALUES (1207, '仓库管理', '仓库管理', '/warehousing/ReturnInbound', 'smile', 1200, 0, 0, 1, 0, 1, 7, NULL, NULL, 'Return Inbound', 'Kembali ke gudang', '2022-12-06 10:12:21', '2022-12-06 10:12:21', 0);
INSERT INTO `t_psi_permission` VALUES (1208, '新增退货入库单', '新增退货入库单', '/warehousing/returnInbound/Add', 'smile', 1200, 0, 1, 0, 0, 1, 8, NULL, NULL, 'Return Inbound Add', 'Tambah dokumen terima kembali', '2022-12-06 10:12:21', '2022-12-06 10:12:21', 0);
INSERT INTO `t_psi_permission` VALUES (1209, '采购入库-详情', '详情', '/warehousing/PurchaseInbound/detail', 'smile', 1201, 0, 1, 1, 0, 1, 2, NULL, NULL, 'Purchase Inbound Detail', 'Membeli Detail Dalam Masuk', '2023-02-13 16:35:32', '2023-02-13 16:35:32', 0);
INSERT INTO `t_psi_permission` VALUES (1210, '导出采购入库-详情IMEI', '导出IMEI', '/warehousing/PurchaseInbound/export_IMEI_button', 'smile', 1209, 1, 1, 1, 0, 1, 1, NULL, NULL, 'Export IMEI', 'Ekspor IMEI', '2023-02-13 16:39:28', '2023-02-13 16:39:28', 0);
INSERT INTO `t_psi_permission` VALUES (1211, '其他入库-详情', '详情', '/warehousing/OtherInbound/detail', 'smile', 1203, 0, 1, 1, 0, 1, 2, NULL, NULL, 'Other Inbound Detail', 'Detail Masuk Lain', '2023-02-13 16:42:02', '2023-02-13 16:42:02', 1);
INSERT INTO `t_psi_permission` VALUES (1212, '导出其他采购入库-详情IMEI', '导出IMEI', '/warehousing/OtherInbound/export_IMEI_button', 'smile', 1211, 1, 1, 1, 0, 1, 1, NULL, NULL, 'Export IMEI', 'Ekspor IMEI', '2023-02-13 16:42:02', '2023-02-13 16:42:02', 0);
INSERT INTO `t_psi_permission` VALUES (1213, '调拨入库-详情', '详情', '/warehousing/TransferInbound/detail', 'smile', 1205, 0, 1, 1, 0, 1, 2, NULL, NULL, 'Transfer Inbound Detail', 'Transfer Detail Masuk', '2023-02-13 16:44:00', '2023-02-13 16:44:00', 1);
INSERT INTO `t_psi_permission` VALUES (1214, '导出调拨入库库-详情IMEI', '导出IMEI', '/warehousing/TransferInbound/export_IMEI_button', 'smile', 1213, 1, 1, 1, 0, 1, 1, NULL, NULL, 'Export IMEI', 'Ekspor IMEI', '2023-02-13 16:44:00', '2023-02-13 16:44:00', 0);
INSERT INTO `t_psi_permission` VALUES (1215, '退货入库-详情', '详情', '/warehousing/ReturnInbound/detail', 'smile', 1207, 0, 1, 1, 0, 1, 2, NULL, NULL, 'Transfer Inbound Detail', 'Transfer Detail Masuk', '2023-02-13 16:45:54', '2023-02-13 16:45:54', 1);
INSERT INTO `t_psi_permission` VALUES (1216, '导出退货入库库-详情IMEI', '导出IMEI', '/warehousing/ReturnInbound/export_IMEI_button', 'smile', 1215, 1, 1, 1, 0, 1, 1, NULL, NULL, 'Export IMEI', 'Ekspor IMEI', '2023-02-13 16:45:54', '2023-02-13 16:45:54', 0);
INSERT INTO `t_psi_permission` VALUES (1217, '查看数据', '查看数据', '/warehousing/PurchaseInbound/list', 'smile', 1201, 0, 1, 1, 1, 1, 1, NULL, NULL, 'View Data', 'Lihat Data', '2023-02-14 15:26:50', '2023-02-14 15:26:50', 0);
INSERT INTO `t_psi_permission` VALUES (1218, '查看数据', '查看数据', '/warehousing/OtherInbound/list', 'smile', 1203, 0, 1, 1, 1, 1, 1, NULL, NULL, 'View Data', 'Lihat Data', '2023-02-14 15:26:50', '2023-02-14 15:26:50', 0);
INSERT INTO `t_psi_permission` VALUES (1219, '查看数据', '查看数据', '/warehousing/TransferInbound/list', 'smile', 1205, 0, 1, 1, 1, 1, 1, NULL, NULL, 'View Data', 'Lihat Data', '2023-02-14 15:26:50', '2023-02-14 15:26:50', 0);
INSERT INTO `t_psi_permission` VALUES (1220, '查看作品列表', '查看作品列表', '/warehousing/ReturnInbound/list', 'smile', 1207, 1, 1, 1, 1, 1, 1, NULL, NULL, 'View Data', 'Lihat Data', '2023-02-14 15:26:50', '2023-02-14 15:26:50', 0);
INSERT INTO `t_psi_permission` VALUES (1221, '新建作品', '新建作品', '/warehousing/OtherInbound/addNewProduct', 'smile', 1203, 1, 1, 1, 0, 1, 1, NULL, NULL, 'Create', 'Ekspor IMEI', '2023-02-13 16:42:02', '2023-02-13 16:42:02', 0);
INSERT INTO `t_psi_permission` VALUES (1222, '详情', '详情', '/warehousing/OtherInbound/detail', 'smile', 1203, 1, 1, 1, 0, 1, 1, NULL, NULL, 'detail', 'Ekspor IMEI', '2023-02-13 16:42:02', '2023-02-13 16:42:02', 0);
INSERT INTO `t_psi_permission` VALUES (1223, '编辑', '编辑', '/warehousing/OtherInbound/edite', 'smile', 1203, 1, 1, 1, 0, 1, 1, NULL, NULL, 'edite', 'Ekspor IMEI', '2023-02-13 16:42:02', '2023-02-13 16:42:02', 0);
INSERT INTO `t_psi_permission` VALUES (1224, '删除', '删除', '/warehousing/OtherInbound/delete', 'smile', 1203, 1, 1, 1, 0, 1, 1, NULL, NULL, 'delete', 'Ekspor IMEI', '2023-02-13 16:42:02', '2023-02-13 16:42:02', 0);
INSERT INTO `t_psi_permission` VALUES (1225, '调拨', '调拨', '/warehousing/TransferInbound/transffer', 'smile', 1205, 1, 1, 1, 0, 1, 1, NULL, NULL, 'transfer', 'Ekspor IMEI', '2023-02-13 16:42:02', '2023-02-13 16:42:02', 0);
INSERT INTO `t_psi_permission` VALUES (1226, '修改作品状态', '修改作品状态', '/warehousing/TransferInbound/update', 'smile', 1205, 1, 1, 1, 0, 1, 1, NULL, NULL, 'transfer', 'Ekspor IMEI', '2023-02-13 16:42:02', '2023-02-13 16:42:02', 0);
INSERT INTO `t_psi_permission` VALUES (1227, '创建仓库', '创建仓库', '/warehousing/ReturnInbound/create', 'smile', 1207, 1, 1, 1, 0, 1, 1, NULL, NULL, 'create', 'Ekspor IMEI', '2023-02-13 16:42:02', '2023-02-13 16:42:02', 0);
INSERT INTO `t_psi_permission` VALUES (1228, '调拨/销售', '调拨/销售', '/warehousing/ReturnInbound/sales', 'smile', 1207, 1, 1, 1, 0, 1, 1, NULL, NULL, 'sales', 'Ekspor IMEI', '2023-02-13 16:42:02', '2023-02-13 16:42:02', 0);
INSERT INTO `t_psi_permission` VALUES (1229, '修改', '修改', '/warehousing/ReturnInbound/update', 'smile', 1207, 1, 1, 1, 0, 1, 1, NULL, NULL, 'update', 'Ekspor IMEI', '2023-02-13 16:42:02', '2023-02-13 16:42:02', 0);
INSERT INTO `t_psi_permission` VALUES (1230, '删除', '删除', '/warehousing/ReturnInbound/delete', 'smile', 1207, 1, 1, 1, 0, 1, 1, NULL, NULL, 'delete', 'Ekspor IMEI', '2023-02-13 16:42:02', '2023-02-13 16:42:02', 0);
INSERT INTO `t_psi_permission` VALUES (1232, '删除', '删除', '/warehousing/TransferInbound/delete', 'smile', 1205, 1, 1, 1, 0, 1, 1, NULL, NULL, 'transfer', 'Ekspor IMEI', '2023-02-13 16:42:02', '2023-02-13 16:42:02', 0);
INSERT INTO `t_psi_permission` VALUES (1233, '详情', '详情', '/warehousing/TransferInbound/list', 'smile', 1205, 1, 1, 1, 0, 1, 1, NULL, NULL, 'transfer', 'Ekspor IMEI', '2023-02-13 16:42:02', '2023-02-13 16:42:02', 0);
INSERT INTO `t_psi_permission` VALUES (1234, '新增制作作品', '新增制作作品', '/warehousing/TransferInbound/create', 'smile', 1205, 1, 1, 1, 0, 1, 1, NULL, NULL, 'transfer', 'Ekspor IMEI', '2023-02-13 16:42:02', '2023-02-13 16:42:02', 0);
INSERT INTO `t_psi_permission` VALUES (1500, '基础资料', '基础资料', '/BasicInfo', 'profile', 0, 0, 0, 1, 0, 1, 6, NULL, NULL, 'Basic Info', 'Info dasar', '2022-03-08 16:22:26', '2022-03-08 16:22:26', 1);
INSERT INTO `t_psi_permission` VALUES (1501, '商品管理', '商品管理', '/BasicInfo/GoodsManagement', 'smile', 1500, 0, 0, 1, 0, 1, 1, NULL, NULL, 'Goods Management', 'Manajemen Barang', '2022-03-08 16:22:53', '2022-03-08 16:22:53', 0);
INSERT INTO `t_psi_permission` VALUES (1502, '供应商管理', '供应商管理', '/BasicInfo/SupplierManagement', 'smile', 1500, 0, 0, 1, 0, 1, 2, NULL, NULL, 'Supplier Management', 'Manajemen Pemasok', '2022-03-08 16:23:25', '2022-03-08 16:23:25', 0);
INSERT INTO `t_psi_permission` VALUES (1503, '仓库管理', '仓库管理', '/BasicInfo/Warehouse', 'smile', 1500, 0, 0, 1, 0, 1, 3, NULL, NULL, 'Warehouse', 'Gudang', '2022-03-08 16:23:59', '2022-03-08 16:23:59', 0);
INSERT INTO `t_psi_permission` VALUES (1504, '客户管理', '客户管理', '/BasicInfo/CustomerManagement', 'CheckCircleOutlined', 1500, 0, 0, 1, 0, 1, 4, NULL, NULL, 'Customers', 'Manajemen Pelanggan', '2023-02-07 17:45:50', '2023-02-07 17:45:50', 0);
INSERT INTO `t_psi_permission` VALUES (1600, '设置', '设置', '/Setting', 'user', 0, 0, 0, 1, 0, 1, 7, NULL, NULL, 'Setting', 'Menetapkan', '2022-03-08 16:24:25', '2022-03-08 16:24:25', 0);
INSERT INTO `t_psi_permission` VALUES (1601, '业务设置', '业务设置', '/Setting/BusinessSettings', 'user', 1600, 0, 0, 1, 0, 1, 3, NULL, NULL, 'Business Settings', 'Pengaturan Bisnis', '2022-03-08 16:24:43', '2022-03-08 16:24:43', 1);
INSERT INTO `t_psi_permission` VALUES (1602, '员工账号', '员工账号', '/Setting/EmployeeAccount', 'user', 1600, 0, 0, 1, 0, 1, 1, NULL, NULL, 'Employee Account', 'Akaun Staf', '2022-03-08 16:25:05', '2022-03-08 16:25:05', 0);
INSERT INTO `t_psi_permission` VALUES (1603, '角色权限', '角色权限', '/Setting/RolePermissions', 'user', 1600, 0, 0, 1, 0, 1, 2, NULL, NULL, 'Role Permissions', 'Hak Perang', '2022-03-08 16:25:43', '2022-03-08 16:25:43', 0);
INSERT INTO `t_psi_permission` VALUES (1604, '权限设置', '权限设置', '/Setting/RolePermissions/RolePermissionsEdit', 'user', 1600, 0, 1, 0, 0, 1, 4, NULL, NULL, 'Role Permissions Edit', 'Edit Hak Perang', '2022-03-08 16:26:00', '2022-03-08 16:26:00', 0);
INSERT INTO `t_psi_permission` VALUES (1605, '新建员工', '新建员工', '/Setting/EmployeeAccount/create', 'smile', 1602, 1, 1, 1, 0, 1, 1, NULL, NULL, 'create', 'Ekspor IMEI', '2023-02-13 16:42:02', '2023-02-13 16:42:02', 0);
INSERT INTO `t_psi_permission` VALUES (1606, '编辑', '编辑', '/Setting/EmployeeAccount/update', 'smile', 1602, 1, 1, 1, 0, 1, 1, NULL, NULL, 'update', 'Ekspor IMEI', '2023-02-13 16:42:02', '2023-02-13 16:42:02', 0);
INSERT INTO `t_psi_permission` VALUES (1607, '重置密码', '重置密码', '/Setting/EmployeeAccount/update-password', 'smile', 1602, 1, 1, 1, 0, 1, 1, NULL, NULL, 'update-password', 'Ekspor IMEI', '2023-02-13 16:42:02', '2023-02-13 16:42:02', 0);
INSERT INTO `t_psi_permission` VALUES (1608, '状态', '状态', '/Setting/EmployeeAccount/enable', 'smile', 1602, 1, 1, 1, 0, 1, 1, NULL, NULL, 'enable', 'Ekspor IMEI', '2023-02-13 16:42:02', '2023-02-13 16:42:02', 0);
INSERT INTO `t_psi_permission` VALUES (1609, '新建角色', '新建角色', '/Setting/RolePermissions/create', 'smile', 1603, 1, 1, 1, 0, 1, 1, NULL, NULL, 'create', 'Ekspor IMEI', '2023-02-13 16:42:02', '2023-02-13 16:42:02', 0);
INSERT INTO `t_psi_permission` VALUES (1610, '权限设置', '权限设置', '/Setting/RolePermissions/set', 'smile', 1603, 1, 1, 1, 0, 1, 1, NULL, NULL, 'set', 'Ekspor IMEI', '2023-02-13 16:42:02', '2023-02-13 16:42:02', 0);
INSERT INTO `t_psi_permission` VALUES (1611, '编辑', '编辑', '/Setting/RolePermissions/update', 'smile', 1603, 1, 1, 1, 0, 1, 1, NULL, NULL, 'update', 'Ekspor IMEI', '2023-02-13 16:42:02', '2023-02-13 16:42:02', 0);
INSERT INTO `t_psi_permission` VALUES (1700, '首页', '首页', '/Dashboard', 'dashboard', 0, 0, 1, 0, 0, 1, 0, NULL, NULL, 'Dashboard', 'halaman rumah', '2022-03-08 21:02:16', '2022-03-08 21:02:16', 0);
INSERT INTO `t_psi_permission` VALUES (1701, '欢迎', '欢迎', '/Dashboard/Welcome', 'Welcome', 1700, 0, 1, 0, 0, 1, 0, NULL, NULL, 'Welcome', 'Selamat Datang', '2022-03-08 21:02:45', '2022-03-08 21:02:45', 0);
INSERT INTO `t_psi_permission` VALUES (1800, '任务中心', '任务中心', '/taskCenter', 'table', 0, 0, 1, 0, 0, 1, 8, NULL, NULL, 'Task Center', NULL, '2023-05-31 16:51:10', '2023-05-31 16:51:10', 1);
INSERT INTO `t_psi_permission` VALUES (1801, '任务中心', '任务中心', '/Dashboard/taskCenter', 'table', 1700, 0, 1, 0, 0, 1, 1, NULL, NULL, 'Task List', 'Daftar Tugas', '2023-05-31 16:51:10', '2023-05-31 16:51:10', 0);

-- ----------------------------
-- Table structure for t_psi_product_sku
-- ----------------------------
DROP TABLE IF EXISTS `t_psi_product_sku`;
CREATE TABLE `t_psi_product_sku`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'sku表id',
  `sku_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'sku编码',
  `sku_product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'sku商品称',
  `create_user_id` bigint NULL DEFAULT 0 COMMENT '创建userId',
  `modify_user_id` bigint NULL DEFAULT 0 COMMENT '修改userId',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除0:未删除,1删除',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_sku_code`(`sku_code` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品sku信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_psi_product_sku
-- ----------------------------
INSERT INTO `t_psi_product_sku` VALUES (1, 'sku001', '蒙娜丽莎', 482, 482, 1, '2023-10-07 10:19:45', '2023-10-09 10:44:26');
INSERT INTO `t_psi_product_sku` VALUES (2, '2023100701', '撒金牡丹', 482, 482, 1, '2023-10-07 10:24:56', '2023-10-09 10:45:09');
INSERT INTO `t_psi_product_sku` VALUES (3, '20301007002', '玛瑙山茶', 482, 482, 1, '2023-10-07 16:55:15', '2023-10-09 10:45:22');
INSERT INTO `t_psi_product_sku` VALUES (4, 'sku999111', '千里江山图', 482, 482, 1, '2023-10-08 15:19:18', '2023-10-09 10:45:44');
INSERT INTO `t_psi_product_sku` VALUES (5, '20231011001', '洛神赋侍女图', 482, 482, 0, '2023-10-09 10:38:11', '2023-10-09 10:38:11');
INSERT INTO `t_psi_product_sku` VALUES (6, '20231011002', '九龙池', 482, 482, 0, '2023-10-09 10:38:42', '2023-10-09 10:38:42');
INSERT INTO `t_psi_product_sku` VALUES (7, '20231011003', '新苗', 482, 482, 0, '2023-10-09 10:39:05', '2023-10-09 10:39:05');
INSERT INTO `t_psi_product_sku` VALUES (8, '20231011004', '三阳开泰', 482, 482, 0, '2023-10-09 10:39:35', '2023-10-09 10:39:35');
INSERT INTO `t_psi_product_sku` VALUES (9, '20231011005', '江南小景', 482, 482, 0, '2023-10-09 10:40:06', '2023-10-09 10:40:06');
INSERT INTO `t_psi_product_sku` VALUES (10, '20231011006', '江南小巷', 482, 482, 0, '2023-10-09 10:40:30', '2023-10-09 10:40:30');
INSERT INTO `t_psi_product_sku` VALUES (11, '20231011007', '金盆玉鸽图', 482, 482, 0, '2023-10-09 10:42:49', '2023-10-09 10:42:49');
INSERT INTO `t_psi_product_sku` VALUES (12, '20231011008', '一花一叶天地皆春', 482, 482, 0, '2023-10-09 10:47:50', '2023-10-09 10:47:50');
INSERT INTO `t_psi_product_sku` VALUES (13, '20231011009', '玛瑙山茶', 482, 482, 0, '2023-10-09 10:48:17', '2023-10-09 10:48:17');
INSERT INTO `t_psi_product_sku` VALUES (14, '20231011010', '撒金牡丹', 482, 482, 0, '2023-10-09 10:48:37', '2023-10-09 10:48:37');
INSERT INTO `t_psi_product_sku` VALUES (15, '20231011011', '青绿山水', 482, 482, 0, '2023-10-09 10:49:37', '2023-10-09 10:49:37');
INSERT INTO `t_psi_product_sku` VALUES (16, '20231011012', '雪梅', 482, 482, 0, '2023-10-09 10:50:15', '2023-10-09 10:50:15');
INSERT INTO `t_psi_product_sku` VALUES (17, '20231011013', '春趣图', 482, 482, 0, '2023-10-09 10:51:21', '2023-10-09 10:51:21');
INSERT INTO `t_psi_product_sku` VALUES (18, '20231011014', '富贵图', 482, 482, 0, '2023-10-09 10:51:41', '2023-10-09 10:51:41');
INSERT INTO `t_psi_product_sku` VALUES (19, '20231011015', '清漓春色', 482, 482, 0, '2023-10-09 10:53:15', '2023-10-09 10:53:15');
INSERT INTO `t_psi_product_sku` VALUES (20, '20231011016', '延年', 482, 482, 0, '2023-10-09 10:53:40', '2023-10-09 10:53:40');
INSERT INTO `t_psi_product_sku` VALUES (21, '20231011017', '双寿', 482, 482, 0, '2023-10-09 10:53:59', '2023-10-09 10:53:59');
INSERT INTO `t_psi_product_sku` VALUES (22, '20231011018', '冬日', 482, 482, 0, '2023-10-09 10:54:39', '2023-10-09 10:54:39');
INSERT INTO `t_psi_product_sku` VALUES (23, '20231011019', '哥本哈根', 482, 482, 0, '2023-10-09 10:55:02', '2023-10-09 10:55:02');
INSERT INTO `t_psi_product_sku` VALUES (24, '20231011020', '嘉藕图', 482, 482, 0, '2023-10-09 10:55:16', '2023-10-09 10:55:16');

-- ----------------------------
-- Table structure for t_psi_role
-- ----------------------------
DROP TABLE IF EXISTS `t_psi_role`;
CREATE TABLE `t_psi_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名字',
  `belong_user_id` bigint NULL DEFAULT NULL COMMENT '所属用户',
  `belong_supplier_id` bigint NULL DEFAULT NULL COMMENT '所属供应商',
  `status` tinyint NULL DEFAULT 1 COMMENT '状态：0禁用，1启用',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `country_id` int NULL DEFAULT NULL COMMENT '国家id',
  `country_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '国家code',
  `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `created_user_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `is_deleted` tinyint NULL DEFAULT 0 COMMENT '是否被删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1247 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_psi_role
-- ----------------------------
INSERT INTO `t_psi_role` VALUES (262, '超级管理员', 482, 0, 1, NULL, 3, NULL, '2023-10-07 17:13:19', '2023-02-21 11:26:00', 0, 0);
INSERT INTO `t_psi_role` VALUES (1246, '公司总部', 482, NULL, 1, NULL, 1, 'ZH', '2023-10-07 10:09:58', '2023-10-07 10:09:58', 482, 0);

-- ----------------------------
-- Table structure for t_psi_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_psi_role_permission`;
CREATE TABLE `t_psi_role_permission`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `permission_id` bigint NOT NULL COMMENT '权限ID',
  `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否被删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 94081 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色-权限' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_psi_role_permission
-- ----------------------------
INSERT INTO `t_psi_role_permission` VALUES (93765, 262, 1801, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93766, 262, 1700, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93767, 262, 1701, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93768, 262, 1200, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93769, 262, 1202, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93770, 262, 1203, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93771, 262, 1204, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93772, 262, 1205, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93773, 262, 1206, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93774, 262, 1207, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93775, 262, 1208, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93776, 262, 1600, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93777, 262, 1218, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93778, 262, 1602, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93779, 262, 1219, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93780, 262, 1603, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93781, 262, 1220, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93782, 262, 1604, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93783, 262, 1605, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93784, 262, 1221, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93785, 262, 1606, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93786, 262, 1222, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93787, 262, 1607, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93788, 262, 1223, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93789, 262, 1608, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93790, 262, 1224, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93791, 262, 1609, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93792, 262, 1225, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93793, 262, 1610, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93794, 262, 1226, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93795, 262, 1611, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93796, 262, 1227, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93797, 262, 1228, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93798, 262, 1229, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93799, 262, 1230, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93800, 262, 1232, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93801, 262, 1233, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93802, 262, 1234, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93803, 262, 1206, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93804, 262, 1204, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93805, 262, 1202, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93806, 262, 1208, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93807, 262, 1701, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93808, 262, 1801, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93809, 262, 1604, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (93810, 262, 1700, '2023-10-08 17:39:41', '2023-10-08 17:39:41', 0);
INSERT INTO `t_psi_role_permission` VALUES (94036, 1246, 1801, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94037, 1246, 1700, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94038, 1246, 1701, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94039, 1246, 1200, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94040, 1246, 1202, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94041, 1246, 1203, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94042, 1246, 1204, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94043, 1246, 1205, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94044, 1246, 1206, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94045, 1246, 1207, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94046, 1246, 1208, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94047, 1246, 1600, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94048, 1246, 1218, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94049, 1246, 1602, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94050, 1246, 1219, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94051, 1246, 1603, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94052, 1246, 1220, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94053, 1246, 1604, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94054, 1246, 1221, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94055, 1246, 1605, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94056, 1246, 1222, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94057, 1246, 1606, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94058, 1246, 1223, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94059, 1246, 1607, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94060, 1246, 1224, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94061, 1246, 1608, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94062, 1246, 1225, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94063, 1246, 1609, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94064, 1246, 1226, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94065, 1246, 1610, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94066, 1246, 1227, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94067, 1246, 1611, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94068, 1246, 1228, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94069, 1246, 1229, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94070, 1246, 1230, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94071, 1246, 1232, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94072, 1246, 1233, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94073, 1246, 1206, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94074, 1246, 1204, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94075, 1246, 1202, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94076, 1246, 1208, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94077, 1246, 1701, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94078, 1246, 1801, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94079, 1246, 1604, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);
INSERT INTO `t_psi_role_permission` VALUES (94080, 1246, 1700, '2023-10-08 17:39:48', '2023-10-08 17:39:48', 0);

-- ----------------------------
-- Table structure for t_psi_storage_order
-- ----------------------------
DROP TABLE IF EXISTS `t_psi_storage_order`;
CREATE TABLE `t_psi_storage_order`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `storage_order_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '入库单单号',
  `source_id` bigint NULL DEFAULT NULL COMMENT '来源Id ',
  `source_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '来源单号',
  `source_business_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '来源业务单据时间',
  `warehouse_id` bigint NULL DEFAULT NULL COMMENT '所在仓库id',
  `source_warehouse_id` bigint NULL DEFAULT NULL COMMENT '调出仓库',
  `product_sku_id` bigint NULL DEFAULT NULL COMMENT '商品skuid',
  `product_sku_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品skucode',
  `product_count` int NULL DEFAULT NULL COMMENT ' 商品数量',
  `received_count` int NULL DEFAULT NULL COMMENT '入库数量',
  `storage_status` tinyint NULL DEFAULT 1 COMMENT '入库状态,1未入库、2部分入库、3全部入库、0已经关闭',
  `add_type` tinyint NULL DEFAULT NULL COMMENT '调拨类型 1：调拨 2：销售',
  `belong_user_id` bigint NOT NULL COMMENT '所属PSI用户id',
  `remarks` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '单据备注',
  `create_by` bigint NULL DEFAULT 0 COMMENT '创建人',
  `update_by` bigint NULL DEFAULT 0 COMMENT '最后修改人',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除0:未删除,1删除',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_warehouse_id`(`warehouse_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '入库单主信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_psi_storage_order
-- ----------------------------
INSERT INTO `t_psi_storage_order` VALUES (1, 'STO202310718173195699', 0, NULL, '2023-10-07 17:35:32', 610, 611, 2, '2023100701', 100, 100, 3, 1, 482, NULL, 482, 482, 0, '2023-10-07 17:35:31', '2023-10-07 17:35:31');
INSERT INTO `t_psi_storage_order` VALUES (2, 'STO202310718410187763', 0, NULL, '2023-10-07 17:39:01', 610, 611, 3, '20301007002', 10, 10, 3, 1, 482, NULL, 482, 482, 0, '2023-10-07 17:39:01', '2023-10-07 17:39:01');
INSERT INTO `t_psi_storage_order` VALUES (3, 'STO202310718413440917', 0, NULL, '2023-10-07 17:41:34', 610, 610, 1, 'sku001', 100, 100, 3, 1, 482, NULL, 482, 482, 0, '2023-10-07 17:41:34', '2023-10-07 17:41:34');
INSERT INTO `t_psi_storage_order` VALUES (4, 'STO202310819394081025', 0, NULL, '2023-10-08 18:07:40', 611, 610, 3, '20301007002', 50, 50, 3, 1, 482, NULL, 482, 482, 0, '2023-10-08 18:07:40', '2023-10-08 18:07:40');
INSERT INTO `t_psi_storage_order` VALUES (5, 'STO202310819594669123', 0, NULL, '2023-10-08 18:12:46', 610, 611, 3, '20301007002', 30, 30, 3, 2, 482, NULL, 482, 482, 0, '2023-10-08 18:12:46', '2023-10-08 18:12:46');
INSERT INTO `t_psi_storage_order` VALUES (6, 'STO202310911405144719', 0, NULL, '2023-10-09 10:01:52', 610, 611, 3, '20301007002', 20, 20, 3, 1, 482, NULL, 482, 482, 0, '2023-10-09 10:01:51', '2023-10-09 10:01:51');
INSERT INTO `t_psi_storage_order` VALUES (7, 'STO202310911182224563', 0, NULL, '2023-10-09 10:23:22', 610, 610, 3, '20301007002', 50, 50, 3, 1, 482, NULL, 482, 482, 0, '2023-10-09 10:23:22', '2023-10-09 10:23:22');
INSERT INTO `t_psi_storage_order` VALUES (8, 'STO202310911912405614', 0, NULL, '2023-10-09 10:27:25', 610, 610, 3, '20301007002', 2, 2, 3, 1, 482, NULL, 482, 482, 0, '2023-10-09 10:27:24', '2023-10-09 10:27:24');
INSERT INTO `t_psi_storage_order` VALUES (9, 'STO202310911195400023', 0, NULL, '2023-10-09 10:30:55', NULL, 610, 3, '20301007002', 5, 5, 3, 2, 482, 'ttttttt', 482, 482, 0, '2023-10-09 10:30:54', '2023-10-09 10:30:54');
INSERT INTO `t_psi_storage_order` VALUES (10, 'STO2023101011593352335', 0, NULL, '2023-10-10 10:25:33', 616, 616, 18, '20231011014', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:25:33', '2023-10-10 10:25:33');
INSERT INTO `t_psi_storage_order` VALUES (11, 'STO2023101011693616072', 0, NULL, '2023-10-10 10:26:37', 616, 616, 24, '20231011020', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:26:36', '2023-10-10 10:26:36');
INSERT INTO `t_psi_storage_order` VALUES (12, 'STO2023101011111107271', 0, NULL, '2023-10-10 10:27:12', 616, 616, 21, '20231011017', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:27:11', '2023-10-10 10:27:11');
INSERT INTO `t_psi_storage_order` VALUES (13, 'STO2023101011800917697', 0, NULL, '2023-10-10 10:31:10', 616, 616, 23, '20231011019', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:31:09', '2023-10-10 10:31:09');
INSERT INTO `t_psi_storage_order` VALUES (14, 'STO2023101011653789150', 0, NULL, '2023-10-10 10:31:38', 616, 616, 22, '20231011018', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:31:37', '2023-10-10 10:31:37');
INSERT INTO `t_psi_storage_order` VALUES (15, 'STO2023101011440220837', 0, NULL, '2023-10-10 10:32:03', 616, 616, 7, '20231011003', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:32:02', '2023-10-10 10:32:02');
INSERT INTO `t_psi_storage_order` VALUES (16, 'STO2023101011474375981', 0, NULL, '2023-10-10 10:32:44', 616, 616, 10, '20231011006', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:32:43', '2023-10-10 10:32:43');
INSERT INTO `t_psi_storage_order` VALUES (17, 'STO2023101011375638357', 0, NULL, '2023-10-10 10:32:57', 616, 616, 9, '20231011005', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:32:56', '2023-10-10 10:32:56');
INSERT INTO `t_psi_storage_order` VALUES (18, 'STO2023101011572337511', 0, NULL, '2023-10-10 10:33:23', 616, 616, 14, '20231011010', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:33:23', '2023-10-10 10:33:23');
INSERT INTO `t_psi_storage_order` VALUES (19, 'STO2023101011144165527', 0, NULL, '2023-10-10 10:33:41', 616, 616, 13, '20231011009', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:33:41', '2023-10-10 10:33:41');
INSERT INTO `t_psi_storage_order` VALUES (20, 'STO2023101011335108865', 0, NULL, '2023-10-10 10:51:51', 616, 616, 5, '20231011001', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:51:51', '2023-10-10 10:51:51');
INSERT INTO `t_psi_storage_order` VALUES (21, 'STO2023101011911954069', 0, NULL, '2023-10-10 10:53:20', 616, 616, 6, '20231011002', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:53:19', '2023-10-10 10:53:19');
INSERT INTO `t_psi_storage_order` VALUES (22, 'STO2023101011720764009', 0, NULL, '2023-10-10 10:54:08', 616, 616, 8, '20231011004', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:54:07', '2023-10-10 10:54:07');
INSERT INTO `t_psi_storage_order` VALUES (23, 'STO2023101011421420498', 0, NULL, '2023-10-10 10:55:14', 616, 616, 11, '20231011007', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:55:14', '2023-10-10 10:55:14');
INSERT INTO `t_psi_storage_order` VALUES (24, 'STO2023101011534990832', 0, NULL, '2023-10-10 10:55:49', 616, 616, 12, '20231011008', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:55:49', '2023-10-10 10:55:49');
INSERT INTO `t_psi_storage_order` VALUES (25, 'STO2023101011873091350', 0, NULL, '2023-10-10 10:56:31', 616, 616, 15, '20231011011', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:56:30', '2023-10-10 10:56:30');
INSERT INTO `t_psi_storage_order` VALUES (26, 'STO2023101011794624020', 0, NULL, '2023-10-10 10:56:46', 616, 616, 16, '20231011012', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:56:46', '2023-10-10 10:56:46');
INSERT INTO `t_psi_storage_order` VALUES (27, 'STO2023101011591640452', 0, NULL, '2023-10-10 10:57:17', 616, 616, 17, '20231011013', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:57:16', '2023-10-10 10:57:16');
INSERT INTO `t_psi_storage_order` VALUES (28, 'STO2023101011065198385', 0, NULL, '2023-10-10 10:57:51', 616, 616, 19, '20231011015', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:57:51', '2023-10-10 10:57:51');
INSERT INTO `t_psi_storage_order` VALUES (29, 'STO2023101011712371133', 0, NULL, '2023-10-10 10:58:24', 616, 616, 20, '20231011016', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:58:23', '2023-10-10 10:58:23');
INSERT INTO `t_psi_storage_order` VALUES (30, 'STO2023101011591269661', 0, NULL, '2023-10-10 10:59:13', 617, 616, 20, '20231011016', 4, 4, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:59:12', '2023-10-10 10:59:12');
INSERT INTO `t_psi_storage_order` VALUES (31, 'STO2023101011275305998', 0, NULL, '2023-10-10 10:59:54', 617, 616, 19, '20231011015', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:59:53', '2023-10-10 10:59:53');
INSERT INTO `t_psi_storage_order` VALUES (32, 'STO2023101012581525791', 0, NULL, '2023-10-10 11:00:16', 617, 616, 17, '20231011013', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 11:00:15', '2023-10-10 11:00:15');
INSERT INTO `t_psi_storage_order` VALUES (33, 'STO2023101012851853436', 0, NULL, '2023-10-10 11:01:18', 617, 616, 16, '20231011012', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 11:01:18', '2023-10-10 11:01:18');
INSERT INTO `t_psi_storage_order` VALUES (34, 'STO2023101012454177201', 0, NULL, '2023-10-10 11:01:41', 617, 616, 15, '20231011011', 4, 4, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 11:01:41', '2023-10-10 11:01:41');
INSERT INTO `t_psi_storage_order` VALUES (35, 'STO2023101012042560342', 0, NULL, '2023-10-10 11:02:25', 617, 616, 12, '20231011008', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 11:02:25', '2023-10-10 11:02:25');
INSERT INTO `t_psi_storage_order` VALUES (36, 'STO2023101012674170860', 0, NULL, '2023-10-10 11:02:42', 617, 616, 11, '20231011007', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 11:02:41', '2023-10-10 11:02:41');
INSERT INTO `t_psi_storage_order` VALUES (37, 'STO2023101012460273356', 0, NULL, '2023-10-10 11:03:03', 617, 616, 8, '20231011004', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 11:03:02', '2023-10-10 11:03:02');
INSERT INTO `t_psi_storage_order` VALUES (38, 'STO2023101012353068702', 0, NULL, '2023-10-10 11:03:31', 617, 616, 6, '20231011002', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 11:03:30', '2023-10-10 11:03:30');
INSERT INTO `t_psi_storage_order` VALUES (39, 'STO2023101012074588053', 0, NULL, '2023-10-10 11:03:46', 617, 616, 5, '20231011001', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 11:03:45', '2023-10-10 11:03:45');
INSERT INTO `t_psi_storage_order` VALUES (40, 'STO2023101012192612480', 0, NULL, '2023-10-10 11:04:27', 617, 616, 15, '20231011011', 1, 1, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 11:04:26', '2023-10-10 11:04:26');
INSERT INTO `t_psi_storage_order` VALUES (41, 'STO2023101012410234224', 0, NULL, '2023-10-10 11:05:03', 617, 616, 13, '20231011009', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 11:05:02', '2023-10-10 11:05:02');
INSERT INTO `t_psi_storage_order` VALUES (42, 'STO2023101012821507462', 0, NULL, '2023-10-10 11:05:16', 617, 616, 14, '20231011010', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 11:05:15', '2023-10-10 11:05:15');
INSERT INTO `t_psi_storage_order` VALUES (43, 'STO2023101012453231280', 0, NULL, '2023-10-10 11:05:33', 617, 616, 9, '20231011005', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 11:05:32', '2023-10-10 11:05:32');
INSERT INTO `t_psi_storage_order` VALUES (44, 'STO2023101012025794590', 0, NULL, '2023-10-10 11:05:57', 617, 616, 10, '20231011006', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 11:05:57', '2023-10-10 11:05:57');
INSERT INTO `t_psi_storage_order` VALUES (45, 'STO2023101012401833690', 0, NULL, '2023-10-10 11:06:19', 617, 616, 7, '20231011003', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 11:06:18', '2023-10-10 11:06:18');
INSERT INTO `t_psi_storage_order` VALUES (46, 'STO2023101012843429257', 0, NULL, '2023-10-10 11:06:35', 617, 616, 22, '20231011018', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 11:06:34', '2023-10-10 11:06:34');
INSERT INTO `t_psi_storage_order` VALUES (47, 'STO2023101012350741664', 0, NULL, '2023-10-10 11:07:07', 617, 616, 23, '20231011019', 5, 5, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 11:07:07', '2023-10-10 11:07:07');

-- ----------------------------
-- Table structure for t_psi_transferring_order
-- ----------------------------
DROP TABLE IF EXISTS `t_psi_transferring_order`;
CREATE TABLE `t_psi_transferring_order`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `transfer_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调拨单编码',
  `source_warehouse_id` bigint NOT NULL COMMENT '调拨源仓库id',
  `product_sku_id` bigint NOT NULL COMMENT 'skuid ',
  `product_sku_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'sku编码',
  `product_sku_name` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '冗余 商品名称',
  `product_count` int NULL DEFAULT NULL COMMENT ' 商品数量',
  `received_storage_count` int NULL DEFAULT NULL COMMENT '已入库数量',
  `not_yet_storage_count` int NULL DEFAULT NULL COMMENT '未入库数量',
  `transferring_status` tinyint NULL DEFAULT 1 COMMENT '调拨单状态',
  `relation_storage_status` tinyint NULL DEFAULT 1 COMMENT '入库状态,1未入库、2部分入库、3全部入库、0已经关闭 ',
  `add_type` tinyint NOT NULL DEFAULT 1 COMMENT '数据类型：1:调拨, 2: 销售',
  `belong_user_id` bigint NOT NULL COMMENT '所属用户id',
  `remarks` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` bigint NULL DEFAULT 0 COMMENT '创建人',
  `update_by` bigint NULL DEFAULT 0 COMMENT '最后修改人',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除0:未删除,1删除',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_product_sku_id`(`product_sku_id` ASC) USING BTREE,
  INDEX `index_product_sku_code`(`product_sku_code` ASC) USING BTREE,
  INDEX `index_source_warehouse_id`(`source_warehouse_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '调拨单信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_psi_transferring_order
-- ----------------------------
INSERT INTO `t_psi_transferring_order` VALUES (1, 'TFO202310718202556637', 611, 2, '2023100701', '撒金牡丹', 100, 100, 0, 4, 3, 1, 482, NULL, 482, 482, 0, '2023-10-07 17:10:25', '2023-10-07 17:35:32');
INSERT INTO `t_psi_transferring_order` VALUES (2, 'TFO202310718641411570', 611, 3, '20301007002', '玛瑙山茶', 10, 10, 0, 4, 3, 1, 482, NULL, 482, 482, 0, '2023-10-07 17:26:14', '2023-10-07 17:39:01');
INSERT INTO `t_psi_transferring_order` VALUES (3, 'TFO202310718881202009', 610, 1, 'sku001', '蒙娜丽莎', 100, 100, 0, 3, 3, 1, 482, NULL, 482, 482, 0, '2023-10-07 17:41:12', '2023-10-07 17:41:34');
INSERT INTO `t_psi_transferring_order` VALUES (4, 'TFO202310819584016930', 610, 3, '20301007002', '玛瑙山茶', 100, 100, 0, 4, 3, 1, 482, NULL, 482, 482, 0, '2023-10-08 18:06:40', '2023-10-09 10:23:22');
INSERT INTO `t_psi_transferring_order` VALUES (5, 'TFO202310912064597896', 616, 5, '20231011001', '洛神赋侍女图', 100, 5, 95, 1, 2, 1, 482, NULL, 482, 482, 0, '2023-10-09 11:01:45', '2023-10-10 10:51:51');
INSERT INTO `t_psi_transferring_order` VALUES (6, 'TFO202310912382030886', 616, 6, '20231011002', '九龙池', 103, 5, 98, 1, 2, 1, 482, NULL, 482, 482, 0, '2023-10-09 11:02:20', '2023-10-10 10:53:20');
INSERT INTO `t_psi_transferring_order` VALUES (7, 'TFO202310912240963048', 616, 7, '20231011003', '新苗', 50, 0, 50, 1, 1, 1, 482, NULL, 482, 482, 0, '2023-10-09 11:03:09', '2023-10-09 11:03:09');
INSERT INTO `t_psi_transferring_order` VALUES (8, 'TFO202310912033311750', 616, 8, '20231011004', '三阳开泰', 100, 5, 95, 1, 2, 1, 482, NULL, 482, 482, 0, '2023-10-09 11:03:33', '2023-10-10 10:54:08');
INSERT INTO `t_psi_transferring_order` VALUES (9, 'TFO202310912550273412', 616, 9, '20231011005', '江南小景', 50, 0, 50, 1, 1, 1, 482, NULL, 482, 482, 0, '2023-10-09 11:04:02', '2023-10-09 11:04:02');
INSERT INTO `t_psi_transferring_order` VALUES (10, 'TFO202310912012520336', 616, 10, '20231011006', '江南小巷', 50, 0, 50, 1, 1, 1, 482, NULL, 482, 482, 0, '2023-10-09 11:04:25', '2023-10-09 11:04:25');
INSERT INTO `t_psi_transferring_order` VALUES (11, 'TFO202310912745075360', 616, 11, '20231011007', '金盆玉鸽图', 100, 5, 95, 1, 2, 1, 482, NULL, 482, 482, 0, '2023-10-09 11:04:50', '2023-10-10 10:55:14');
INSERT INTO `t_psi_transferring_order` VALUES (12, 'TFO202310912600802140', 616, 12, '20231011008', '一花一叶天地皆春', 100, 5, 95, 1, 2, 1, 482, NULL, 482, 482, 0, '2023-10-09 11:05:08', '2023-10-10 10:55:49');
INSERT INTO `t_psi_transferring_order` VALUES (13, 'TFO202310912673219214', 616, 13, '20231011009', '玛瑙山茶', 50, 0, 50, 1, 1, 1, 482, NULL, 482, 482, 0, '2023-10-09 11:05:32', '2023-10-09 11:05:32');
INSERT INTO `t_psi_transferring_order` VALUES (14, 'TFO202310912885472056', 616, 14, '20231011010', '撒金牡丹', 50, 0, 50, 1, 1, 1, 482, NULL, 482, 482, 0, '2023-10-09 11:05:54', '2023-10-09 11:05:54');
INSERT INTO `t_psi_transferring_order` VALUES (15, 'TFO202310912673362143', 616, 15, '20231011011', '青绿山水', 100, 5, 95, 1, 2, 1, 482, NULL, 482, 482, 0, '2023-10-09 11:06:33', '2023-10-10 10:56:31');
INSERT INTO `t_psi_transferring_order` VALUES (16, 'TFO202310912025728552', 616, 16, '20231011012', '雪梅', 100, 5, 95, 1, 2, 1, 482, NULL, 482, 482, 0, '2023-10-09 11:06:57', '2023-10-10 10:56:46');
INSERT INTO `t_psi_transferring_order` VALUES (17, 'TFO202310912571998015', 616, 17, '20231011013', '春趣图', 100, 5, 95, 1, 2, 1, 482, NULL, 482, 482, 0, '2023-10-09 11:07:19', '2023-10-10 10:57:17');
INSERT INTO `t_psi_transferring_order` VALUES (18, 'TFO202310912643504505', 616, 18, '20231011014', '富贵图', 50, 0, 50, 1, 1, 1, 482, NULL, 482, 482, 0, '2023-10-09 11:07:35', '2023-10-09 11:07:35');
INSERT INTO `t_psi_transferring_order` VALUES (19, 'TFO202310912420437853', 616, 19, '20231011015', '清漓春色', 100, 5, 95, 1, 2, 1, 482, NULL, 482, 482, 0, '2023-10-09 11:08:04', '2023-10-10 10:57:51');
INSERT INTO `t_psi_transferring_order` VALUES (20, 'TFO202310912152296664', 616, 20, '20231011016', '延年', 100, 5, 95, 1, 2, 1, 482, NULL, 482, 482, 0, '2023-10-09 11:08:22', '2023-10-10 10:58:24');
INSERT INTO `t_psi_transferring_order` VALUES (21, 'TFO202310912663918430', 616, 21, '20231011017', '双寿', 50, 0, 50, 1, 1, 1, 482, NULL, 482, 482, 0, '2023-10-09 11:08:39', '2023-10-09 11:08:39');
INSERT INTO `t_psi_transferring_order` VALUES (22, 'TFO202310912785935625', 616, 23, '20231011019', '哥本哈根', 50, 0, 50, 1, 1, 1, 482, NULL, 482, 482, 0, '2023-10-09 11:08:59', '2023-10-09 11:08:59');
INSERT INTO `t_psi_transferring_order` VALUES (23, 'TFO202310912232023578', 616, 24, '20231011020', '嘉藕图', 50, 0, 50, 1, 1, 1, 482, NULL, 482, 482, 0, '2023-10-09 11:09:20', '2023-10-09 11:09:20');
INSERT INTO `t_psi_transferring_order` VALUES (24, 'TFO202310912184748825', 616, 22, '20231011018', '冬日', 50, 0, 50, 1, 1, 1, 482, NULL, 482, 482, 0, '2023-10-09 11:11:47', '2023-10-10 10:48:26');
INSERT INTO `t_psi_transferring_order` VALUES (25, 'TFO2023101011343353269', 616, 13, '20231011009', '玛瑙山茶', 5, 5, 0, 4, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:20:33', '2023-10-10 10:33:41');
INSERT INTO `t_psi_transferring_order` VALUES (26, 'TFO2023101011830208394', 616, 14, '20231011010', '撒金牡丹', 5, 5, 0, 4, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:21:02', '2023-10-10 10:33:23');
INSERT INTO `t_psi_transferring_order` VALUES (27, 'TFO2023101011041950502', 616, 9, '20231011005', '江南小景', 5, 5, 0, 4, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:21:19', '2023-10-10 10:32:57');
INSERT INTO `t_psi_transferring_order` VALUES (28, 'TFO2023101011453483991', 616, 10, '20231011006', '江南小巷', 5, 5, 0, 4, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:21:34', '2023-10-10 10:32:44');
INSERT INTO `t_psi_transferring_order` VALUES (29, 'TFO2023101011720131173', 616, 7, '20231011003', '新苗', 5, 5, 0, 4, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:22:01', '2023-10-10 10:32:03');
INSERT INTO `t_psi_transferring_order` VALUES (30, 'TFO2023101011072170025', 616, 22, '20231011018', '冬日', 5, 5, 0, 4, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:22:21', '2023-10-10 10:31:38');
INSERT INTO `t_psi_transferring_order` VALUES (31, 'TFO2023101011010890761', 616, 23, '20231011019', '哥本哈根', 5, 5, 0, 4, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:24:08', '2023-10-10 10:31:10');
INSERT INTO `t_psi_transferring_order` VALUES (32, 'TFO2023101011772865002', 616, 21, '20231011017', '双寿', 5, 5, 0, 4, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:24:28', '2023-10-10 10:27:12');
INSERT INTO `t_psi_transferring_order` VALUES (33, 'TFO2023101011644762665', 616, 24, '20231011020', '嘉藕图', 5, 5, 0, 4, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:24:47', '2023-10-10 10:26:37');
INSERT INTO `t_psi_transferring_order` VALUES (34, 'TFO2023101011985996376', 616, 18, '20231011014', '富贵图', 5, 5, 0, 4, 3, 1, 482, NULL, 482, 482, 0, '2023-10-10 10:24:59', '2023-10-10 10:25:33');

-- ----------------------------
-- Table structure for t_psi_transferring_order_status
-- ----------------------------
DROP TABLE IF EXISTS `t_psi_transferring_order_status`;
CREATE TABLE `t_psi_transferring_order_status`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `transferring_order_id` bigint NOT NULL COMMENT '归属id',
  `transferring_status` int NOT NULL DEFAULT 0 COMMENT '状态 ',
  `product_count` int NOT NULL DEFAULT 0 COMMENT '数量',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除0:未删除,1删除',
  `create_by` bigint NULL DEFAULT 0 COMMENT '创建人',
  `update_by` bigint NULL DEFAULT 0 COMMENT '最后修改人',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_transferring_order_id`(`transferring_order_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 165 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '调拨单状态节点信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_psi_transferring_order_status
-- ----------------------------
INSERT INTO `t_psi_transferring_order_status` VALUES (1, 1, 1, 100, 0, 482, 0, '2023-10-07 17:10:25', '2023-10-07 17:10:25');
INSERT INTO `t_psi_transferring_order_status` VALUES (2, 2, 1, 10, 0, 482, 0, '2023-10-07 17:26:14', '2023-10-07 17:26:14');
INSERT INTO `t_psi_transferring_order_status` VALUES (3, 3, 1, 100, 0, 482, 0, '2023-10-07 17:41:12', '2023-10-07 17:41:12');
INSERT INTO `t_psi_transferring_order_status` VALUES (4, 4, 1, 100, 0, 482, 0, '2023-10-08 18:06:40', '2023-10-08 18:06:40');
INSERT INTO `t_psi_transferring_order_status` VALUES (5, 5, 1, 100, 0, 482, 0, '2023-10-09 11:01:45', '2023-10-09 11:01:45');
INSERT INTO `t_psi_transferring_order_status` VALUES (6, 6, 1, 103, 0, 482, 0, '2023-10-09 11:02:20', '2023-10-09 11:02:20');
INSERT INTO `t_psi_transferring_order_status` VALUES (7, 7, 1, 50, 0, 482, 0, '2023-10-09 11:03:09', '2023-10-09 11:03:09');
INSERT INTO `t_psi_transferring_order_status` VALUES (8, 8, 1, 100, 0, 482, 0, '2023-10-09 11:03:33', '2023-10-09 11:03:33');
INSERT INTO `t_psi_transferring_order_status` VALUES (9, 9, 1, 50, 0, 482, 0, '2023-10-09 11:04:02', '2023-10-09 11:04:02');
INSERT INTO `t_psi_transferring_order_status` VALUES (10, 10, 1, 50, 0, 482, 0, '2023-10-09 11:04:25', '2023-10-09 11:04:25');
INSERT INTO `t_psi_transferring_order_status` VALUES (11, 11, 1, 100, 0, 482, 0, '2023-10-09 11:04:50', '2023-10-09 11:04:50');
INSERT INTO `t_psi_transferring_order_status` VALUES (12, 12, 1, 100, 0, 482, 0, '2023-10-09 11:05:08', '2023-10-09 11:05:08');
INSERT INTO `t_psi_transferring_order_status` VALUES (13, 13, 1, 50, 0, 482, 0, '2023-10-09 11:05:32', '2023-10-09 11:05:32');
INSERT INTO `t_psi_transferring_order_status` VALUES (14, 14, 1, 50, 0, 482, 0, '2023-10-09 11:05:54', '2023-10-09 11:05:54');
INSERT INTO `t_psi_transferring_order_status` VALUES (15, 15, 1, 100, 0, 482, 0, '2023-10-09 11:06:33', '2023-10-09 11:06:33');
INSERT INTO `t_psi_transferring_order_status` VALUES (16, 16, 1, 100, 0, 482, 0, '2023-10-09 11:06:57', '2023-10-09 11:06:57');
INSERT INTO `t_psi_transferring_order_status` VALUES (17, 17, 1, 100, 0, 482, 0, '2023-10-09 11:07:19', '2023-10-09 11:07:19');
INSERT INTO `t_psi_transferring_order_status` VALUES (18, 18, 1, 50, 0, 482, 0, '2023-10-09 11:07:35', '2023-10-09 11:07:35');
INSERT INTO `t_psi_transferring_order_status` VALUES (19, 19, 1, 100, 0, 482, 0, '2023-10-09 11:08:04', '2023-10-09 11:08:04');
INSERT INTO `t_psi_transferring_order_status` VALUES (20, 20, 1, 100, 0, 482, 0, '2023-10-09 11:08:22', '2023-10-09 11:08:22');
INSERT INTO `t_psi_transferring_order_status` VALUES (21, 21, 1, 50, 0, 482, 0, '2023-10-09 11:08:39', '2023-10-09 11:08:39');
INSERT INTO `t_psi_transferring_order_status` VALUES (22, 22, 1, 50, 0, 482, 0, '2023-10-09 11:08:59', '2023-10-09 11:08:59');
INSERT INTO `t_psi_transferring_order_status` VALUES (23, 23, 1, 50, 0, 482, 0, '2023-10-09 11:09:20', '2023-10-09 11:09:20');
INSERT INTO `t_psi_transferring_order_status` VALUES (24, 24, 1, 45, 0, 482, 482, '2023-10-09 11:11:47', '2023-10-10 10:48:26');
INSERT INTO `t_psi_transferring_order_status` VALUES (32, 1, 2, 0, 0, 482, 0, '2023-10-07 17:10:25', '2023-10-07 17:10:25');
INSERT INTO `t_psi_transferring_order_status` VALUES (33, 2, 2, 0, 0, 482, 0, '2023-10-07 17:26:14', '2023-10-07 17:26:14');
INSERT INTO `t_psi_transferring_order_status` VALUES (34, 3, 2, 0, 0, 482, 0, '2023-10-07 17:41:12', '2023-10-07 17:41:12');
INSERT INTO `t_psi_transferring_order_status` VALUES (35, 4, 2, 0, 0, 482, 0, '2023-10-08 18:06:40', '2023-10-08 18:06:40');
INSERT INTO `t_psi_transferring_order_status` VALUES (36, 5, 2, 0, 0, 482, 0, '2023-10-09 11:01:45', '2023-10-09 11:01:45');
INSERT INTO `t_psi_transferring_order_status` VALUES (37, 6, 2, 0, 0, 482, 0, '2023-10-09 11:02:20', '2023-10-09 11:02:20');
INSERT INTO `t_psi_transferring_order_status` VALUES (38, 7, 2, 0, 0, 482, 0, '2023-10-09 11:03:09', '2023-10-09 11:03:09');
INSERT INTO `t_psi_transferring_order_status` VALUES (39, 8, 2, 0, 0, 482, 0, '2023-10-09 11:03:33', '2023-10-09 11:03:33');
INSERT INTO `t_psi_transferring_order_status` VALUES (40, 9, 2, 0, 0, 482, 0, '2023-10-09 11:04:02', '2023-10-09 11:04:02');
INSERT INTO `t_psi_transferring_order_status` VALUES (41, 10, 2, 0, 0, 482, 0, '2023-10-09 11:04:25', '2023-10-09 11:04:25');
INSERT INTO `t_psi_transferring_order_status` VALUES (42, 11, 2, 0, 0, 482, 0, '2023-10-09 11:04:50', '2023-10-09 11:04:50');
INSERT INTO `t_psi_transferring_order_status` VALUES (43, 12, 2, 0, 0, 482, 0, '2023-10-09 11:05:08', '2023-10-09 11:05:08');
INSERT INTO `t_psi_transferring_order_status` VALUES (44, 13, 2, 0, 0, 482, 0, '2023-10-09 11:05:32', '2023-10-09 11:05:32');
INSERT INTO `t_psi_transferring_order_status` VALUES (45, 14, 2, 0, 0, 482, 0, '2023-10-09 11:05:54', '2023-10-09 11:05:54');
INSERT INTO `t_psi_transferring_order_status` VALUES (46, 15, 2, 0, 0, 482, 0, '2023-10-09 11:06:33', '2023-10-09 11:06:33');
INSERT INTO `t_psi_transferring_order_status` VALUES (47, 16, 2, 0, 0, 482, 0, '2023-10-09 11:06:57', '2023-10-09 11:06:57');
INSERT INTO `t_psi_transferring_order_status` VALUES (48, 17, 2, 0, 0, 482, 0, '2023-10-09 11:07:19', '2023-10-09 11:07:19');
INSERT INTO `t_psi_transferring_order_status` VALUES (49, 18, 2, 0, 0, 482, 0, '2023-10-09 11:07:35', '2023-10-09 11:07:35');
INSERT INTO `t_psi_transferring_order_status` VALUES (50, 19, 2, 0, 0, 482, 0, '2023-10-09 11:08:04', '2023-10-09 11:08:04');
INSERT INTO `t_psi_transferring_order_status` VALUES (51, 20, 2, 0, 0, 482, 0, '2023-10-09 11:08:22', '2023-10-09 11:08:22');
INSERT INTO `t_psi_transferring_order_status` VALUES (52, 21, 2, 0, 0, 482, 0, '2023-10-09 11:08:39', '2023-10-09 11:08:39');
INSERT INTO `t_psi_transferring_order_status` VALUES (53, 22, 2, 0, 0, 482, 0, '2023-10-09 11:08:59', '2023-10-09 11:08:59');
INSERT INTO `t_psi_transferring_order_status` VALUES (54, 23, 2, 0, 0, 482, 0, '2023-10-09 11:09:20', '2023-10-09 11:09:20');
INSERT INTO `t_psi_transferring_order_status` VALUES (55, 24, 2, 0, 0, 482, 482, '2023-10-09 11:11:47', '2023-10-10 10:48:26');
INSERT INTO `t_psi_transferring_order_status` VALUES (63, 1, 3, 0, 0, 482, 0, '2023-10-07 17:10:25', '2023-10-07 17:10:25');
INSERT INTO `t_psi_transferring_order_status` VALUES (64, 2, 3, 0, 0, 482, 0, '2023-10-07 17:26:14', '2023-10-07 17:26:14');
INSERT INTO `t_psi_transferring_order_status` VALUES (65, 3, 3, 0, 0, 482, 0, '2023-10-07 17:41:12', '2023-10-07 17:41:12');
INSERT INTO `t_psi_transferring_order_status` VALUES (66, 4, 3, 0, 0, 482, 0, '2023-10-08 18:06:40', '2023-10-08 18:06:40');
INSERT INTO `t_psi_transferring_order_status` VALUES (67, 5, 3, 0, 0, 482, 0, '2023-10-09 11:01:45', '2023-10-09 11:01:45');
INSERT INTO `t_psi_transferring_order_status` VALUES (68, 6, 3, 0, 0, 482, 0, '2023-10-09 11:02:20', '2023-10-09 11:02:20');
INSERT INTO `t_psi_transferring_order_status` VALUES (69, 7, 3, 0, 0, 482, 0, '2023-10-09 11:03:09', '2023-10-09 11:03:09');
INSERT INTO `t_psi_transferring_order_status` VALUES (70, 8, 3, 0, 0, 482, 0, '2023-10-09 11:03:33', '2023-10-09 11:03:33');
INSERT INTO `t_psi_transferring_order_status` VALUES (71, 9, 3, 0, 0, 482, 0, '2023-10-09 11:04:02', '2023-10-09 11:04:02');
INSERT INTO `t_psi_transferring_order_status` VALUES (72, 10, 3, 0, 0, 482, 0, '2023-10-09 11:04:25', '2023-10-09 11:04:25');
INSERT INTO `t_psi_transferring_order_status` VALUES (73, 11, 3, 0, 0, 482, 0, '2023-10-09 11:04:50', '2023-10-09 11:04:50');
INSERT INTO `t_psi_transferring_order_status` VALUES (74, 12, 3, 0, 0, 482, 0, '2023-10-09 11:05:08', '2023-10-09 11:05:08');
INSERT INTO `t_psi_transferring_order_status` VALUES (75, 13, 3, 0, 0, 482, 0, '2023-10-09 11:05:32', '2023-10-09 11:05:32');
INSERT INTO `t_psi_transferring_order_status` VALUES (76, 14, 3, 0, 0, 482, 0, '2023-10-09 11:05:54', '2023-10-09 11:05:54');
INSERT INTO `t_psi_transferring_order_status` VALUES (77, 15, 3, 0, 0, 482, 0, '2023-10-09 11:06:33', '2023-10-09 11:06:33');
INSERT INTO `t_psi_transferring_order_status` VALUES (78, 16, 3, 0, 0, 482, 0, '2023-10-09 11:06:57', '2023-10-09 11:06:57');
INSERT INTO `t_psi_transferring_order_status` VALUES (79, 17, 3, 0, 0, 482, 0, '2023-10-09 11:07:19', '2023-10-09 11:07:19');
INSERT INTO `t_psi_transferring_order_status` VALUES (80, 18, 3, 0, 0, 482, 0, '2023-10-09 11:07:35', '2023-10-09 11:07:35');
INSERT INTO `t_psi_transferring_order_status` VALUES (81, 19, 3, 0, 0, 482, 0, '2023-10-09 11:08:04', '2023-10-09 11:08:04');
INSERT INTO `t_psi_transferring_order_status` VALUES (82, 20, 3, 0, 0, 482, 0, '2023-10-09 11:08:22', '2023-10-09 11:08:22');
INSERT INTO `t_psi_transferring_order_status` VALUES (83, 21, 3, 0, 0, 482, 0, '2023-10-09 11:08:39', '2023-10-09 11:08:39');
INSERT INTO `t_psi_transferring_order_status` VALUES (84, 22, 3, 0, 0, 482, 0, '2023-10-09 11:08:59', '2023-10-09 11:08:59');
INSERT INTO `t_psi_transferring_order_status` VALUES (85, 23, 3, 0, 0, 482, 0, '2023-10-09 11:09:20', '2023-10-09 11:09:20');
INSERT INTO `t_psi_transferring_order_status` VALUES (86, 24, 3, 0, 0, 482, 482, '2023-10-09 11:11:47', '2023-10-10 10:48:26');
INSERT INTO `t_psi_transferring_order_status` VALUES (94, 1, 4, 0, 0, 482, 0, '2023-10-07 17:10:25', '2023-10-07 17:10:25');
INSERT INTO `t_psi_transferring_order_status` VALUES (95, 2, 4, 0, 0, 482, 0, '2023-10-07 17:26:14', '2023-10-07 17:26:14');
INSERT INTO `t_psi_transferring_order_status` VALUES (96, 3, 4, 0, 0, 482, 0, '2023-10-07 17:41:12', '2023-10-07 17:41:12');
INSERT INTO `t_psi_transferring_order_status` VALUES (97, 4, 4, 0, 0, 482, 0, '2023-10-08 18:06:40', '2023-10-08 18:06:40');
INSERT INTO `t_psi_transferring_order_status` VALUES (98, 5, 4, 0, 0, 482, 0, '2023-10-09 11:01:45', '2023-10-09 11:01:45');
INSERT INTO `t_psi_transferring_order_status` VALUES (99, 6, 4, 0, 0, 482, 0, '2023-10-09 11:02:20', '2023-10-09 11:02:20');
INSERT INTO `t_psi_transferring_order_status` VALUES (100, 7, 4, 0, 0, 482, 0, '2023-10-09 11:03:09', '2023-10-09 11:03:09');
INSERT INTO `t_psi_transferring_order_status` VALUES (101, 8, 4, 0, 0, 482, 0, '2023-10-09 11:03:33', '2023-10-09 11:03:33');
INSERT INTO `t_psi_transferring_order_status` VALUES (102, 9, 4, 0, 0, 482, 0, '2023-10-09 11:04:02', '2023-10-09 11:04:02');
INSERT INTO `t_psi_transferring_order_status` VALUES (103, 10, 4, 0, 0, 482, 0, '2023-10-09 11:04:25', '2023-10-09 11:04:25');
INSERT INTO `t_psi_transferring_order_status` VALUES (104, 11, 4, 0, 0, 482, 0, '2023-10-09 11:04:50', '2023-10-09 11:04:50');
INSERT INTO `t_psi_transferring_order_status` VALUES (105, 12, 4, 0, 0, 482, 0, '2023-10-09 11:05:08', '2023-10-09 11:05:08');
INSERT INTO `t_psi_transferring_order_status` VALUES (106, 13, 4, 0, 0, 482, 0, '2023-10-09 11:05:32', '2023-10-09 11:05:32');
INSERT INTO `t_psi_transferring_order_status` VALUES (107, 14, 4, 0, 0, 482, 0, '2023-10-09 11:05:54', '2023-10-09 11:05:54');
INSERT INTO `t_psi_transferring_order_status` VALUES (108, 15, 4, 0, 0, 482, 0, '2023-10-09 11:06:33', '2023-10-09 11:06:33');
INSERT INTO `t_psi_transferring_order_status` VALUES (109, 16, 4, 0, 0, 482, 0, '2023-10-09 11:06:57', '2023-10-09 11:06:57');
INSERT INTO `t_psi_transferring_order_status` VALUES (110, 17, 4, 0, 0, 482, 0, '2023-10-09 11:07:19', '2023-10-09 11:07:19');
INSERT INTO `t_psi_transferring_order_status` VALUES (111, 18, 4, 0, 0, 482, 0, '2023-10-09 11:07:35', '2023-10-09 11:07:35');
INSERT INTO `t_psi_transferring_order_status` VALUES (112, 19, 4, 0, 0, 482, 0, '2023-10-09 11:08:04', '2023-10-09 11:08:04');
INSERT INTO `t_psi_transferring_order_status` VALUES (113, 20, 4, 0, 0, 482, 0, '2023-10-09 11:08:22', '2023-10-09 11:08:22');
INSERT INTO `t_psi_transferring_order_status` VALUES (114, 21, 4, 0, 0, 482, 0, '2023-10-09 11:08:39', '2023-10-09 11:08:39');
INSERT INTO `t_psi_transferring_order_status` VALUES (115, 22, 4, 0, 0, 482, 0, '2023-10-09 11:08:59', '2023-10-09 11:08:59');
INSERT INTO `t_psi_transferring_order_status` VALUES (116, 23, 4, 0, 0, 482, 0, '2023-10-09 11:09:20', '2023-10-09 11:09:20');
INSERT INTO `t_psi_transferring_order_status` VALUES (117, 24, 4, 5, 0, 482, 482, '2023-10-09 11:11:47', '2023-10-10 10:48:26');
INSERT INTO `t_psi_transferring_order_status` VALUES (125, 25, 1, 0, 0, 482, 0, '2023-10-10 10:20:33', '2023-10-10 10:20:33');
INSERT INTO `t_psi_transferring_order_status` VALUES (126, 25, 2, 0, 0, 482, 0, '2023-10-10 10:20:33', '2023-10-10 10:20:33');
INSERT INTO `t_psi_transferring_order_status` VALUES (127, 25, 3, 0, 0, 482, 0, '2023-10-10 10:20:33', '2023-10-10 10:20:33');
INSERT INTO `t_psi_transferring_order_status` VALUES (128, 25, 4, 5, 0, 482, 0, '2023-10-10 10:20:33', '2023-10-10 10:20:33');
INSERT INTO `t_psi_transferring_order_status` VALUES (129, 26, 1, 0, 0, 482, 0, '2023-10-10 10:21:02', '2023-10-10 10:21:02');
INSERT INTO `t_psi_transferring_order_status` VALUES (130, 26, 2, 0, 0, 482, 0, '2023-10-10 10:21:02', '2023-10-10 10:21:02');
INSERT INTO `t_psi_transferring_order_status` VALUES (131, 26, 3, 0, 0, 482, 0, '2023-10-10 10:21:02', '2023-10-10 10:21:02');
INSERT INTO `t_psi_transferring_order_status` VALUES (132, 26, 4, 5, 0, 482, 0, '2023-10-10 10:21:02', '2023-10-10 10:21:02');
INSERT INTO `t_psi_transferring_order_status` VALUES (133, 27, 1, 0, 0, 482, 0, '2023-10-10 10:21:19', '2023-10-10 10:21:19');
INSERT INTO `t_psi_transferring_order_status` VALUES (134, 27, 2, 0, 0, 482, 0, '2023-10-10 10:21:19', '2023-10-10 10:21:19');
INSERT INTO `t_psi_transferring_order_status` VALUES (135, 27, 3, 0, 0, 482, 0, '2023-10-10 10:21:19', '2023-10-10 10:21:19');
INSERT INTO `t_psi_transferring_order_status` VALUES (136, 27, 4, 5, 0, 482, 0, '2023-10-10 10:21:19', '2023-10-10 10:21:19');
INSERT INTO `t_psi_transferring_order_status` VALUES (137, 28, 1, 0, 0, 482, 0, '2023-10-10 10:21:34', '2023-10-10 10:21:34');
INSERT INTO `t_psi_transferring_order_status` VALUES (138, 28, 2, 0, 0, 482, 0, '2023-10-10 10:21:34', '2023-10-10 10:21:34');
INSERT INTO `t_psi_transferring_order_status` VALUES (139, 28, 3, 0, 0, 482, 0, '2023-10-10 10:21:34', '2023-10-10 10:21:34');
INSERT INTO `t_psi_transferring_order_status` VALUES (140, 28, 4, 5, 0, 482, 0, '2023-10-10 10:21:34', '2023-10-10 10:21:34');
INSERT INTO `t_psi_transferring_order_status` VALUES (141, 29, 1, 0, 0, 482, 0, '2023-10-10 10:22:01', '2023-10-10 10:22:01');
INSERT INTO `t_psi_transferring_order_status` VALUES (142, 29, 2, 0, 0, 482, 0, '2023-10-10 10:22:01', '2023-10-10 10:22:01');
INSERT INTO `t_psi_transferring_order_status` VALUES (143, 29, 3, 0, 0, 482, 0, '2023-10-10 10:22:01', '2023-10-10 10:22:01');
INSERT INTO `t_psi_transferring_order_status` VALUES (144, 29, 4, 5, 0, 482, 0, '2023-10-10 10:22:01', '2023-10-10 10:22:01');
INSERT INTO `t_psi_transferring_order_status` VALUES (145, 30, 1, 0, 0, 482, 0, '2023-10-10 10:22:21', '2023-10-10 10:22:21');
INSERT INTO `t_psi_transferring_order_status` VALUES (146, 30, 2, 0, 0, 482, 0, '2023-10-10 10:22:21', '2023-10-10 10:22:21');
INSERT INTO `t_psi_transferring_order_status` VALUES (147, 30, 3, 0, 0, 482, 0, '2023-10-10 10:22:21', '2023-10-10 10:22:21');
INSERT INTO `t_psi_transferring_order_status` VALUES (148, 30, 4, 5, 0, 482, 0, '2023-10-10 10:22:21', '2023-10-10 10:22:21');
INSERT INTO `t_psi_transferring_order_status` VALUES (149, 31, 1, 0, 0, 482, 0, '2023-10-10 10:24:08', '2023-10-10 10:24:08');
INSERT INTO `t_psi_transferring_order_status` VALUES (150, 31, 2, 0, 0, 482, 0, '2023-10-10 10:24:08', '2023-10-10 10:24:08');
INSERT INTO `t_psi_transferring_order_status` VALUES (151, 31, 3, 0, 0, 482, 0, '2023-10-10 10:24:08', '2023-10-10 10:24:08');
INSERT INTO `t_psi_transferring_order_status` VALUES (152, 31, 4, 5, 0, 482, 0, '2023-10-10 10:24:08', '2023-10-10 10:24:08');
INSERT INTO `t_psi_transferring_order_status` VALUES (153, 32, 1, 0, 0, 482, 0, '2023-10-10 10:24:28', '2023-10-10 10:24:28');
INSERT INTO `t_psi_transferring_order_status` VALUES (154, 32, 2, 0, 0, 482, 0, '2023-10-10 10:24:28', '2023-10-10 10:24:28');
INSERT INTO `t_psi_transferring_order_status` VALUES (155, 32, 3, 0, 0, 482, 0, '2023-10-10 10:24:28', '2023-10-10 10:24:28');
INSERT INTO `t_psi_transferring_order_status` VALUES (156, 32, 4, 5, 0, 482, 0, '2023-10-10 10:24:28', '2023-10-10 10:24:28');
INSERT INTO `t_psi_transferring_order_status` VALUES (157, 33, 1, 0, 0, 482, 0, '2023-10-10 10:24:47', '2023-10-10 10:24:47');
INSERT INTO `t_psi_transferring_order_status` VALUES (158, 33, 2, 0, 0, 482, 0, '2023-10-10 10:24:47', '2023-10-10 10:24:47');
INSERT INTO `t_psi_transferring_order_status` VALUES (159, 33, 3, 0, 0, 482, 0, '2023-10-10 10:24:47', '2023-10-10 10:24:47');
INSERT INTO `t_psi_transferring_order_status` VALUES (160, 33, 4, 5, 0, 482, 0, '2023-10-10 10:24:47', '2023-10-10 10:24:47');
INSERT INTO `t_psi_transferring_order_status` VALUES (161, 34, 1, 0, 0, 482, 0, '2023-10-10 10:24:59', '2023-10-10 10:24:59');
INSERT INTO `t_psi_transferring_order_status` VALUES (162, 34, 2, 0, 0, 482, 0, '2023-10-10 10:24:59', '2023-10-10 10:24:59');
INSERT INTO `t_psi_transferring_order_status` VALUES (163, 34, 3, 0, 0, 482, 0, '2023-10-10 10:24:59', '2023-10-10 10:24:59');
INSERT INTO `t_psi_transferring_order_status` VALUES (164, 34, 4, 5, 0, 482, 0, '2023-10-10 10:24:59', '2023-10-10 10:24:59');

-- ----------------------------
-- Table structure for t_psi_user
-- ----------------------------
DROP TABLE IF EXISTS `t_psi_user`;
CREATE TABLE `t_psi_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名(登录名称)',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `user_type` bigint NULL DEFAULT 0 COMMENT '用户类型（0 平台管理 1 商家管理员 ）',
  `source_type` int NULL DEFAULT 0 COMMENT '用户来源 0 psi 新增的 1 Egatee用户 99 其他',
  `source_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '来源用户关系信息 (Egatee 用户关联的是 userId)',
  `belong_user_id` bigint NULL DEFAULT 0 COMMENT '所属用户 默认0',
  `level` int NULL DEFAULT 0 COMMENT '账户级别:0.最高',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `user_status` tinyint NULL DEFAULT 0 COMMENT '是否可用',
  `create_user_id` bigint NULL DEFAULT 0 COMMENT '创建userId',
  `country_id` bigint NULL DEFAULT NULL COMMENT '国家ID',
  `country_code` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '国家code',
  `customer_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联的国包/二级(DBR)客户代码',
  `customer_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关联的国包/二级(DBR)客户名称',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  `email_encode` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '邮箱加密',
  `phone_encode` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '手机号加密',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 483 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_psi_user
-- ----------------------------
INSERT INTO `t_psi_user` VALUES (482, 'admin', 'admin', '$2a$10$pPQ/cz7JDTAAcNKvthXusOY60Lz5YoeOAXiKpQEyyHjBhLkbB8RB2', 0, 99, NULL, 482, 0, NULL, NULL, 1, 482, 1, 'ZH', NULL, NULL, '2023-09-25 13:54:44', '2023-10-08 14:32:38', 0, '', '');

-- ----------------------------
-- Table structure for t_psi_user_identity
-- ----------------------------
DROP TABLE IF EXISTS `t_psi_user_identity`;
CREATE TABLE `t_psi_user_identity`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `identity_type` int NULL DEFAULT NULL COMMENT '(1、 国包  2、二级（DBR)  3、R)',
  `source_id` bigint NULL DEFAULT NULL COMMENT '关联外部 Id (国包 二级 存入的是 sellerId   R 存buyer_id )',
  `user_id` bigint NULL DEFAULT NULL COMMENT 'psi 用户id',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否被删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1065 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户来源身份记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_psi_user_identity
-- ----------------------------

-- ----------------------------
-- Table structure for t_psi_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_psi_user_role`;
CREATE TABLE `t_psi_user_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `gmt_modified` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `gmt_create` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否被删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1168 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户-角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_psi_user_role
-- ----------------------------
INSERT INTO `t_psi_user_role` VALUES (533, 482, 163, '2022-04-25 15:53:38', '2022-04-25 15:53:38', 1);
INSERT INTO `t_psi_user_role` VALUES (1167, 482, 262, '2023-10-08 14:32:38', '2023-10-08 14:32:38', 0);

-- ----------------------------
-- Table structure for t_warehouse_info
-- ----------------------------
DROP TABLE IF EXISTS `t_warehouse_info`;
CREATE TABLE `t_warehouse_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `belong_user_id` bigint NOT NULL COMMENT '自增主键',
  `warehouse_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库编码',
  `warehouse_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '仓库名称',
  `warehouse_contact` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库联系人',
  `warehouse_phone` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库联系电话',
  `warehouse_email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库联系邮箱',
  `warehouse_type` tinyint NOT NULL DEFAULT 1 COMMENT '仓库类型 1：egatee自动创建仓，2,egatee自营仓,3:托管仓库,4:第三方仓库',
  `warehouse_status` tinyint NOT NULL DEFAULT 1 COMMENT '仓库状态 1:启用,2:停用',
  `warehouse_area` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库区域',
  `warehouse_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库详细地址',
  `warehouse_acreage` int NULL DEFAULT 0 COMMENT '仓库面积 单位平方米',
  `shipping_region` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '冗余 发货区域',
  `inventory_priority` int NULL DEFAULT 0 COMMENT '库存优先级',
  `country_id` bigint NULL DEFAULT 0 COMMENT '所属国家id',
  `country_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '国家code',
  `warehouse_remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库备注',
  `create_by` bigint NULL DEFAULT 0 COMMENT '创建人',
  `update_by` bigint NULL DEFAULT 0 COMMENT '最后修改人',
  `is_default` tinyint(1) NULL DEFAULT 0 COMMENT '是否默认仓库 0 否 1 是',
  `is_virtual` tinyint(1) NULL DEFAULT 1 COMMENT '是否虚拟仓库 0 否 1 是',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除0:未删除,1删除',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `warehouse_email_encode` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '仓库联系邮箱加密',
  `warehouse_phone_encode` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '仓库联系电话加密',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_warehouse_code`(`warehouse_code` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 618 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '仓库主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_warehouse_info
-- ----------------------------
INSERT INTO `t_warehouse_info` VALUES (610, 482, 'WHS202310711322354154', '深圳总部', NULL, NULL, NULL, 4, 1, NULL, NULL, 0, NULL, 2147483647, 1, '', '', 482, 482, 0, 1, 1, '2023-10-07 10:20:23', '2023-10-09 10:46:49', '', '');
INSERT INTO `t_warehouse_info` VALUES (611, 482, 'WHS202310711171970196', '深圳仓', NULL, NULL, NULL, 4, 1, NULL, NULL, 0, NULL, 2147483647, 1, '', '', 482, 482, 0, 1, 1, '2023-10-07 10:25:20', '2023-10-09 10:46:28', '', '');
INSERT INTO `t_warehouse_info` VALUES (612, 482, 'WHS202310711363108945', '深圳仓', NULL, NULL, NULL, 4, 1, NULL, NULL, 0, NULL, 2147483647, 1, '', '', 482, 482, 0, 1, 1, '2023-10-07 10:25:31', '2023-10-07 17:09:33', '', '');
INSERT INTO `t_warehouse_info` VALUES (613, 482, 'WHS202310711674927204', '深圳仓', NULL, NULL, NULL, 4, 1, NULL, NULL, 0, NULL, 2147483647, 1, '', '', 482, 482, 0, 1, 1, '2023-10-07 10:25:50', '2023-10-07 17:09:26', '', '');
INSERT INTO `t_warehouse_info` VALUES (614, 482, 'WHS202310711072668368', '深圳仓', NULL, NULL, NULL, 4, 1, NULL, NULL, 0, NULL, 2147483647, 1, '', '', 482, 482, 0, 1, 1, '2023-10-07 10:50:26', '2023-10-07 17:09:16', '', '');
INSERT INTO `t_warehouse_info` VALUES (615, 482, 'WHS202310718205114921', '深圳总部', NULL, NULL, NULL, 4, 1, NULL, NULL, 0, NULL, 2147483647, 1, '', '深圳总部', 482, 482, 0, 1, 1, '2023-10-07 17:57:51', '2023-10-08 14:35:54', '', '');
INSERT INTO `t_warehouse_info` VALUES (616, 482, 'WHS202310911632414906', '深圳仓', NULL, NULL, NULL, 4, 1, NULL, NULL, 0, NULL, 2147483647, 1, '', '', 482, 482, 0, 1, 0, '2023-10-09 10:56:25', '2023-10-09 10:56:25', '', '');
INSERT INTO `t_warehouse_info` VALUES (617, 482, 'WHS2023101011824733842', '青岛仓库', NULL, NULL, NULL, 4, 1, NULL, NULL, 0, NULL, 2147483647, 1, '', '青岛飞天和青岛华汇酒业', 482, 482, 0, 1, 0, '2023-10-10 10:35:47', '2023-10-10 10:35:47', '', '');

-- ----------------------------
-- Table structure for t_warehouse_shipping_region_relation
-- ----------------------------
DROP TABLE IF EXISTS `t_warehouse_shipping_region_relation`;
CREATE TABLE `t_warehouse_shipping_region_relation`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `warehouse_id` bigint NULL DEFAULT NULL COMMENT '仓库id',
  `district_id` bigint NOT NULL COMMENT '关联egatee_district_id',
  `area_id` bigint NOT NULL COMMENT '三级区域id',
  `area_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '冗余 三级区域名称',
  `priority` int NOT NULL DEFAULT 9999999 COMMENT '区域优先级，数字越小优先级越高',
  `create_by` bigint NULL DEFAULT 0 COMMENT '创建人',
  `update_by` bigint NULL DEFAULT 0 COMMENT '最后修改人',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除0:未删除,1删除',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_warehouse_id`(`warehouse_id` ASC) USING BTREE,
  INDEX `index_area_id`(`area_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 421524 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '仓库与发货区域关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_warehouse_shipping_region_relation
-- ----------------------------

-- ----------------------------
-- Table structure for t_warehouse_user_relation
-- ----------------------------
DROP TABLE IF EXISTS `t_warehouse_user_relation`;
CREATE TABLE `t_warehouse_user_relation`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `warehouse_id` bigint NULL DEFAULT NULL COMMENT '仓库id',
  `user_id` bigint NULL DEFAULT NULL COMMENT 'psi用户id',
  `relation_type` tinyint NOT NULL DEFAULT 1 COMMENT '关联关系类型 1:强关联',
  `warehouse_type` tinyint NOT NULL COMMENT '仓库类型',
  `contract_type` tinyint NOT NULL COMMENT '合同类型',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除0:未删除,1删除',
  `gmt_create` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_warehouse_id`(`warehouse_id` ASC) USING BTREE,
  INDEX `index_user_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 848 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '卖家仓库关系配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_warehouse_user_relation
-- ----------------------------
INSERT INTO `t_warehouse_user_relation` VALUES (837, 610, 482, 0, 0, 0, 1, '2023-10-07 10:20:23', '2023-10-08 14:32:38');
INSERT INTO `t_warehouse_user_relation` VALUES (838, 611, 482, 0, 0, 0, 1, '2023-10-07 10:25:20', '2023-10-08 14:32:38');
INSERT INTO `t_warehouse_user_relation` VALUES (839, 612, 482, 0, 0, 0, 1, '2023-10-07 10:25:31', '2023-10-08 14:32:38');
INSERT INTO `t_warehouse_user_relation` VALUES (840, 613, 482, 0, 0, 0, 1, '2023-10-07 10:25:50', '2023-10-08 14:32:38');
INSERT INTO `t_warehouse_user_relation` VALUES (841, 614, 482, 0, 0, 0, 1, '2023-10-07 10:50:26', '2023-10-08 14:32:38');
INSERT INTO `t_warehouse_user_relation` VALUES (842, 615, 482, 0, 0, 0, 1, '2023-10-07 17:57:51', '2023-10-08 14:32:38');
INSERT INTO `t_warehouse_user_relation` VALUES (843, 610, 482, 0, 0, 0, 0, '2023-10-08 14:32:38', '2023-10-08 14:32:38');
INSERT INTO `t_warehouse_user_relation` VALUES (844, 611, 482, 0, 0, 0, 0, '2023-10-08 14:32:38', '2023-10-08 14:32:38');
INSERT INTO `t_warehouse_user_relation` VALUES (845, 615, 482, 0, 0, 0, 0, '2023-10-08 14:32:38', '2023-10-08 14:32:38');
INSERT INTO `t_warehouse_user_relation` VALUES (846, 616, 482, 0, 0, 0, 0, '2023-10-09 10:56:25', '2023-10-09 10:56:25');
INSERT INTO `t_warehouse_user_relation` VALUES (847, 617, 482, 0, 0, 0, 0, '2023-10-10 10:35:47', '2023-10-10 10:35:47');

SET FOREIGN_KEY_CHECKS = 1;
