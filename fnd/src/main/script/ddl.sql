
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_inventory_change_log
-- ----------------------------
DROP TABLE IF EXISTS `t_inventory_change_log`;
CREATE TABLE `t_inventory_change_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `product_spu_id` bigint(20) DEFAULT NULL COMMENT 'spu id',
  `product_sku_id` bigint(20) DEFAULT NULL COMMENT 'sku id',
  `change_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '库存变更类型:1:增加,2:减少,3占用',
  `inventory_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '变更库存类型:1:可售库存,2:可用,3待入库,4,已上架,5未上架,6待出库,7在途,8不良品,9占用,10虚拟',
  `change_quantity` int(11) NOT NULL DEFAULT '0' COMMENT '变更数量 ',
  `change_source_code` varchar(64) DEFAULT NULL COMMENT '变更源编码,eg:order_code',
  `change_remark` varchar(512) DEFAULT NULL COMMENT '变更占用备注',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除0:未删除,1删除',
  `create_by` bigint(20) DEFAULT '0' COMMENT '创建人',
  `update_by` bigint(20) DEFAULT '0' COMMENT '最后修改人',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `out_put_storage_code` varchar(128) DEFAULT NULL COMMENT '出入库单号',
  `assocation_business_type` int(11) DEFAULT NULL COMMENT '关联业务类型 1-采购入库 2-调拨入库 3-售后入库 4-其他入库 5-销售入库 6-销售出库 7-其他出库 8-采购退货',
  `warehouse_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_product_spu_id` (`product_spu_id`) USING BTREE,
  KEY `index_product_sku_id` (`product_sku_id`) USING BTREE,
  KEY `index_inventory_type` (`inventory_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=215641 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='psi库存变更记录主表';

-- ----------------------------
-- Table structure for t_psi_in_warehouse_user_relation
-- ----------------------------
DROP TABLE IF EXISTS `t_psi_in_warehouse_user_relation`;
CREATE TABLE `t_psi_in_warehouse_user_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `warehouse_id` bigint(20) DEFAULT NULL COMMENT '仓库id',
  `user_id` bigint(20) DEFAULT NULL COMMENT 'psi用户id',
  `relation_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '关联关系类型 1:强关联',
  `warehouse_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '仓库类型',
  `contract_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '合同类型',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除0:未删除,1删除',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_warehouse_id` (`warehouse_id`) USING BTREE,
  KEY `index_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=408 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='卖家调入仓库关系配置表';

-- ----------------------------
-- Table structure for t_psi_inventory
-- ----------------------------
DROP TABLE IF EXISTS `t_psi_inventory`;
CREATE TABLE `t_psi_inventory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `product_spu_id` bigint(20) DEFAULT NULL COMMENT '库存归属spu id',
  `product_sku_id` bigint(20) NOT NULL COMMENT '库存归属 sku id',
  `product_spu_code` varchar(64) DEFAULT NULL COMMENT '冗余 spu编码',
  `product_sku_code` varchar(64) NOT NULL COMMENT '冗余 sku编码',
  `warehouse_id` bigint(20) NOT NULL COMMENT '库存所属 仓库id',
  `inventory_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '库存类型:1:强管控,2:允许以采代销',
  `sellable_quantity` int(11) NOT NULL DEFAULT '0' COMMENT '冗余计算:可售库存(可用库存 - 占用库存) ',
  `available_quantity` int(11) NOT NULL DEFAULT '0' COMMENT '可用库存',
  `inbound_quantity` int(11) NOT NULL DEFAULT '0' COMMENT '待入库库存',
  `stocked_quantity` int(11) NOT NULL DEFAULT '0' COMMENT '已上架库存',
  `un_stocked_quantity` int(11) NOT NULL DEFAULT '0' COMMENT '未上架库存',
  `outbound_quantity` int(11) NOT NULL DEFAULT '0' COMMENT '待出库库存',
  `in_transit_quantity` int(11) NOT NULL DEFAULT '0' COMMENT '在途库存',
  `defective_quantity` int(11) NOT NULL DEFAULT '0' COMMENT '不良品库存',
  `occupied_quantity` int(11) NOT NULL DEFAULT '0' COMMENT '占用库存',
  `virtual_quantity` int(11) NOT NULL DEFAULT '0' COMMENT '虚拟库存',
  `weighted_cost_price` decimal(22,2) DEFAULT NULL COMMENT '冗余 加权平均价',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除0:未删除,1删除',
  `create_by` bigint(20) DEFAULT '0' COMMENT '创建人',
  `update_by` bigint(20) DEFAULT '0' COMMENT '最后修改人',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_product_spu_id` (`product_spu_id`) USING BTREE,
  KEY `index_product_sku_id` (`product_sku_id`) USING BTREE,
  KEY `index_sellable_quantity` (`sellable_quantity`) USING BTREE,
  KEY `index_available_quantity` (`available_quantity`) USING BTREE,
  KEY `index_occupied_quantity` (`occupied_quantity`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13796 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='psi库存主表';

-- ----------------------------
-- Table structure for t_psi_order_relation
-- ----------------------------
DROP TABLE IF EXISTS `t_psi_order_relation`;
CREATE TABLE `t_psi_order_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `relation_type` tinyint(4) DEFAULT '0' COMMENT '关联类型 1：采购单关联入库单,2:销售单关联出库单,3:入库单关联出库单',
  `purchase_order_id` bigint(20) DEFAULT NULL COMMENT '采购单id',
  `storage_order_id` bigint(20) DEFAULT NULL COMMENT '入库单id',
  `sales_order_id` bigint(20) DEFAULT NULL COMMENT '销售单id',
  `outbound_order_id` bigint(20) DEFAULT NULL COMMENT '出库单id',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除0:未删除,1删除',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26828 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='psi业务单据关联关系,满足手动创建单据 多对多关系';

-- ----------------------------
-- Table structure for t_psi_out_warehouse_user_relation
-- ----------------------------
DROP TABLE IF EXISTS `t_psi_out_warehouse_user_relation`;
CREATE TABLE `t_psi_out_warehouse_user_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `warehouse_id` bigint(20) DEFAULT NULL COMMENT '仓库id',
  `user_id` bigint(20) DEFAULT NULL COMMENT 'psi用户id',
  `relation_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '关联关系类型 1:强关联',
  `warehouse_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '仓库类型',
  `contract_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '合同类型',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除0:未删除,1删除',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_warehouse_id` (`warehouse_id`) USING BTREE,
  KEY `index_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=593 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='卖家调出仓库关系配置表';

-- ----------------------------
-- Table structure for t_psi_outbound_order
-- ----------------------------
DROP TABLE IF EXISTS `t_psi_outbound_order`;
CREATE TABLE `t_psi_outbound_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `outbound_order_code` varchar(128) CHARACTER SET utf8mb4 NOT NULL COMMENT '出库单单号',
  `source_type` tinyint(4) DEFAULT '0' COMMENT '来源：0->手动创建；1:egatee自动同步',
  `outbound_type` tinyint(4) DEFAULT '0' COMMENT '出库单据类型：1销售出库,2调拨出库,3:盘亏出库,4:采购退货出库, 5:线下订单，0其他入库',
  `business_type` tinyint(4) DEFAULT NULL,
  `source_id` bigint(20) DEFAULT NULL COMMENT '来源Id ',
  `source_code` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '来源单号',
  `delivery_type` int(11) DEFAULT '0' COMMENT '配送方式 0:配送;1:自提;2:无需配送 虚拟商品',
  `source_business_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '来源业务单据时间,eg:采购单创建时间',
  `warehouse_id` bigint(20) NOT NULL COMMENT '所在仓库id',
  `source_user_id` bigint(20) NOT NULL COMMENT '来源用户id',
  `source_user_name` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '来源用户名称',
  `belong_user_id` bigint(20) NOT NULL COMMENT '所属PSI用户id',
  `supplier_id` bigint(20) DEFAULT NULL COMMENT 'psi供应商id',
  `source_supplier_id` bigint(20) DEFAULT NULL COMMENT '冗余 来源供应商id eg：egatee seller_id',
  `product_count` int(11) DEFAULT NULL COMMENT ' 商品数量',
  `received_storage_count` int(11) DEFAULT NULL COMMENT '已入库数量',
  `not_yet_storage_count` int(11) DEFAULT NULL COMMENT '未入库数量',
  `storage_status` tinyint(4) DEFAULT '1' COMMENT '入库状态,1未入库、2部分入库、3全部入库、0已经关闭',
  `settlement_account_id` bigint(20) NOT NULL COMMENT '预留 结算账户id',
  `order_remarks` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '单据备注',
  `country_id` bigint(20) DEFAULT '0' COMMENT '国家Id',
  `country_code` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '国家code',
  `receiver_name` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '收货人姓名',
  `receiver_phone` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '收货人电话',
  `agent_phone` varchar(50) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '紧急联系人',
  `receiver_post_code` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '收货地址行政编码',
  `receiver_province_id` bigint(20) DEFAULT NULL COMMENT '省Id',
  `receiver_province_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '省份名称',
  `receiver_city_id` bigint(20) DEFAULT NULL COMMENT '城市id',
  `receiver_city_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '城市名称',
  `receiver_area_id` bigint(20) DEFAULT NULL COMMENT '区域id',
  `receiver_area_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '区域名称',
  `receiver_detail_address` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '详细地址',
  `receiver_address_landmark` varchar(255) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT '收货地址 地标 用于送货员 快速参考定位',
  `shipping_method` varchar(2) CHARACTER SET utf8mb4 DEFAULT '0' COMMENT '发货方式 0-> 自发货, 1：Speedaf  3: 买家自提',
  `outbound_finish_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '出库完成时间',
  `create_by` bigint(20) DEFAULT '0' COMMENT '创建人',
  `update_by` bigint(20) DEFAULT '0' COMMENT '最后修改人',
  `order_time` datetime DEFAULT NULL,
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除0:未删除,1删除',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_outbound_order_code` (`outbound_order_code`) USING BTREE,
  KEY `index_belong_user_id` (`belong_user_id`) USING BTREE,
  KEY `index_supplier_id` (`supplier_id`) USING BTREE,
  KEY `index_warehouse_id` (`warehouse_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11725 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='出库单主信息';

-- ----------------------------
-- Table structure for t_psi_outbound_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_psi_outbound_order_detail`;
CREATE TABLE `t_psi_outbound_order_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `outbound_order_id` bigint(20) NOT NULL COMMENT '关联出库单主键',
  `outbound_order_code` varchar(128) NOT NULL COMMENT '出库单单号',
  `source_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '冗余 来源：0->手动创建；1->Egatee',
  `warehouse_id` bigint(20) NOT NULL COMMENT '冗余 所在仓库id 继承入库单',
  `source_user_id` bigint(20) NOT NULL COMMENT '冗余 来源用户id',
  `belong_user_id` bigint(20) NOT NULL COMMENT '所属PSI用户id',
  `source_sku_id` bigint(20) DEFAULT NULL COMMENT '冗余 来源skuid ',
  `product_sku_id` bigint(20) NOT NULL COMMENT 'skuid ',
  `product_sku_code` varchar(128) NOT NULL COMMENT 'sku编码',
  `product_sku_name` varchar(512) DEFAULT NULL COMMENT '冗余 商品名称',
  `product_attribute` varchar(128) DEFAULT NULL COMMENT '冗余 商品销售属性',
  `product_sku_price` decimal(22,2) DEFAULT NULL COMMENT '商品 销售单价',
  `product_amount` decimal(22,2) DEFAULT NULL COMMENT '商品总价= 单价*数量',
  `product_sale_unit` varchar(255) DEFAULT '' COMMENT '销售单位(多项)',
  `product_count` int(11) DEFAULT NULL COMMENT '冗余 商品数量',
  `received_outbound_count` int(11) DEFAULT NULL COMMENT '已出库数量',
  `should_outbound_count` int(11) DEFAULT NULL COMMENT '应出库数量',
  `return_count` int(11) DEFAULT '0' COMMENT '退货数量',
  `ware_batch_number` varchar(128) NOT NULL COMMENT '冗余 当前商品入库批次号 数据来自t_psi_imei_serial',
  `add_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '数据录入来源：1->扫码录入,2 手动录入,3:导入',
  `country_id` bigint(20) DEFAULT '0' COMMENT '国家Id',
  `country_code` varchar(16) DEFAULT '' COMMENT '国家code',
  `create_by` bigint(20) DEFAULT '0' COMMENT '创建人',
  `update_by` bigint(20) DEFAULT '0' COMMENT '最后修改人',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除0:未删除,1删除',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_outbound_order_code` (`outbound_order_code`) USING BTREE,
  KEY `index_belong_user_id` (`belong_user_id`) USING BTREE,
  KEY `index_product_sku_id` (`product_sku_id`) USING BTREE,
  KEY `index_warehouse_id` (`warehouse_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14460 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='出库单详情信息';

-- ----------------------------
-- Table structure for t_psi_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_psi_permission`;
CREATE TABLE `t_psi_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `menu_name` varchar(255) DEFAULT NULL COMMENT '按钮名称',
  `name` varchar(100) DEFAULT NULL COMMENT 'api权限名',
  `path` varchar(100) DEFAULT NULL COMMENT 'api权限资源',
  `icon` varchar(255) DEFAULT NULL COMMENT 'icon',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '权限父节点（0为根节点）',
  `type` tinyint(1) DEFAULT '0' COMMENT '菜单类型 （0菜单 1按钮）',
  `hideIn_menu` tinyint(1) DEFAULT NULL COMMENT '是否隐藏tab',
  `show` tinyint(1) DEFAULT '0' COMMENT '是否展示菜单（以后设置按钮权限后可废除）',
  `virtual` tinyint(1) DEFAULT '0' COMMENT '是否是虚拟节点',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态：0禁用，1启用',
  `priority` int(11) DEFAULT '0' COMMENT '优先级，越大，同级显示的时候越靠前',
  `redirect` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `en` varchar(255) DEFAULT NULL COMMENT '英文',
  `in_id` varchar(255) DEFAULT NULL COMMENT '印度尼西亚语言',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1802 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户权限表';

-- ----------------------------
-- Table structure for t_psi_product_sku
-- ----------------------------
DROP TABLE IF EXISTS `t_psi_product_sku`;
CREATE TABLE `t_psi_product_sku` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'sku表id',
  `source_type` tinyint(4) NOT NULL COMMENT '来源类型 1：egatee,2:手动创建',
  `source_sku_id` bigint(20) DEFAULT NULL COMMENT '来源sku表id',
  `source_sku_code` varchar(64) DEFAULT NULL COMMENT '来源sku编码',
  `template_sku_id` bigint(20) DEFAULT '0' COMMENT '模板skuId',
  `spu_id` bigint(20) NOT NULL COMMENT '所属商品id',
  `sku_code` varchar(64) NOT NULL COMMENT 'sku编码',
  `price` decimal(19,2) DEFAULT NULL COMMENT '商品价格',
  `underline_price` decimal(19,2) DEFAULT NULL COMMENT '划线价格',
  `purchase_price` decimal(19,2) DEFAULT NULL COMMENT '进货价',
  `system_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '系统类型  ',
  `gb_code` varchar(64) DEFAULT '' COMMENT '国标码',
  `original_price` decimal(19,2) DEFAULT NULL COMMENT '市场价（建议零售价）',
  `sku_publish_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '上架状态：0->未上架；1->上架 2->下架',
  `sku_images` varchar(1000) NOT NULL COMMENT '商品sku图片限制为5张，以逗号分割',
  `sale_unit` varchar(255) NOT NULL DEFAULT '' COMMENT '销售单位(多项)',
  `is_package` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否是组合商品。0-不是 1-是',
  `is_serial_number` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否是串码管控商品。0-不是 1-是',
  `country_id` bigint(20) DEFAULT '0' COMMENT '国家Id',
  `country_code` varchar(16) DEFAULT '' COMMENT '国家code',
  `low_stock` bigint(20) DEFAULT '0' COMMENT '库存预警值',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除0:未删除,1删除',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `sku_product_name` varchar(512) NOT NULL COMMENT 'sku商品称',
  `attribute_value` varchar(200) DEFAULT NULL COMMENT '销售属性json形式存储{“”}',
  `channel_price` decimal(19,2) DEFAULT NULL COMMENT '渠道价格',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_spu_id` (`spu_id`) USING BTREE,
  KEY `index_source_sku_id` (`source_sku_id`) USING BTREE,
  KEY `index_sku_code` (`sku_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15207 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='商品sku信息表';

-- ----------------------------
-- Table structure for t_psi_role
-- ----------------------------
DROP TABLE IF EXISTS `t_psi_role`;
CREATE TABLE `t_psi_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(255) NOT NULL COMMENT '角色名字',
  `belong_user_id` bigint(20) DEFAULT NULL COMMENT '所属用户',
  `belong_supplier_id` bigint(20) DEFAULT NULL COMMENT '所属供应商',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态：0禁用，1启用',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `country_id` int(11) DEFAULT NULL COMMENT '国家id',
  `country_code` varchar(20) DEFAULT NULL COMMENT '国家code',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `created_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `is_deleted` tinyint(4) DEFAULT '0' COMMENT '是否被删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1246 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Table structure for t_psi_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_psi_role_permission`;
CREATE TABLE `t_psi_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否被删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=92460 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色-权限';

-- ----------------------------
-- Table structure for t_psi_storage_order
-- ----------------------------
DROP TABLE IF EXISTS `t_psi_storage_order`;
CREATE TABLE `t_psi_storage_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `storage_order_code` varchar(128) CHARACTER SET utf8mb4 NOT NULL COMMENT '入库单单号',
  `source_type` tinyint(4) DEFAULT '0' COMMENT '来源：0->手动创建；1:egatee自动同步',
  `storage_type` tinyint(4) DEFAULT '0' COMMENT '入库单据类型：1采购入库,2调拨入库,3:不良品入库,4售后入库,0其他入库',
  `business_type` tinyint(4) DEFAULT '0' COMMENT '业务类型:0其他 1，盘盈，2:盘亏',
  `source_id` bigint(20) DEFAULT NULL COMMENT '来源Id ',
  `source_code` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '来源单号',
  `source_business_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '来源业务单据时间,eg:采购单创建时间',
  `warehouse_id` bigint(20) NOT NULL COMMENT '所在仓库id',
  `actual_inbound_warehouse_id` bigint(20) DEFAULT NULL COMMENT '实际入仓id',
  `source_user_id` bigint(20) DEFAULT NULL COMMENT '来源用户id',
  `source_user_name` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '来源用户名称',
  `belong_user_id` bigint(20) NOT NULL COMMENT '所属PSI用户id',
  `supplier_id` bigint(20) DEFAULT NULL COMMENT 'psi供应商id',
  `source_supplier_id` bigint(20) DEFAULT NULL COMMENT '冗余 来源供应商id eg：egatee seller_id',
  `product_count` int(11) DEFAULT NULL COMMENT ' 商品数量',
  `received_storage_count` int(11) DEFAULT NULL COMMENT '已入库数量',
  `not_yet_storage_count` int(11) DEFAULT NULL COMMENT '未入库数量',
  `storage_status` tinyint(4) DEFAULT '1' COMMENT '入库状态,1未入库、2部分入库、3全部入库、0已经关闭',
  `storage_finish_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '入库完成时间',
  `settlement_account_id` bigint(20) NOT NULL COMMENT '预留 结算账户id',
  `order_remarks` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '单据备注',
  `country_id` bigint(20) DEFAULT '0' COMMENT '国家Id',
  `country_code` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '国家code',
  `logistics_no` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '物流单号',
  `create_by` bigint(20) DEFAULT '0' COMMENT '创建人',
  `update_by` bigint(20) DEFAULT '0' COMMENT '最后修改人',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除0:未删除,1删除',
  `order_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '单据创建时间 前端传递',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_storage_order_code` (`storage_order_code`) USING BTREE,
  KEY `index_belong_user_id` (`belong_user_id`) USING BTREE,
  KEY `index_supplier_id` (`supplier_id`) USING BTREE,
  KEY `index_warehouse_id` (`warehouse_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=192515 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='入库单主信息';

-- ----------------------------
-- Table structure for t_psi_storage_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_psi_storage_order_detail`;
CREATE TABLE `t_psi_storage_order_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `storage_order_id` bigint(20) NOT NULL COMMENT '关联入库单主键',
  `storage_order_code` varchar(128) NOT NULL COMMENT '入库单单号',
  `source_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '冗余 来源：0->手动创建；1->Egatee',
  `warehouse_id` bigint(20) NOT NULL COMMENT '冗余 所在仓库id 继承入库单',
  `source_user_id` bigint(20) DEFAULT NULL COMMENT '冗余 来源用户id',
  `belong_user_id` bigint(20) NOT NULL COMMENT '所属PSI用户id',
  `source_sku_id` bigint(20) DEFAULT NULL COMMENT '冗余 来源skuid ',
  `product_sku_id` bigint(20) NOT NULL COMMENT 'skuid ',
  `product_sku_code` varchar(128) NOT NULL COMMENT 'sku编码',
  `product_sku_name` varchar(512) DEFAULT NULL COMMENT '冗余 商品名称',
  `product_attribute` varchar(128) DEFAULT NULL COMMENT '冗余 商品销售属性',
  `product_sku_price` decimal(22,2) DEFAULT NULL COMMENT '商品 销售单价',
  `product_amount` decimal(22,2) DEFAULT NULL COMMENT '商品总价= 单价*数量',
  `product_sale_unit` varchar(255) DEFAULT '' COMMENT '销售单位(多项)',
  `product_count` int(11) DEFAULT NULL COMMENT '冗余 商品数量',
  `received_storage_count` int(11) DEFAULT NULL COMMENT '已入库数量',
  `should_storage_count` int(11) DEFAULT NULL COMMENT '应入库数量',
  `return_count` int(11) DEFAULT '0' COMMENT '退货数量',
  `ware_batch_number` varchar(128) DEFAULT NULL COMMENT '冗余 当前商品入库批次号 数据来自t_psi_imei_serial',
  `add_type` tinyint(4) DEFAULT '0' COMMENT '数据录入来源：1->扫码录入,2 手动录入,3:导入',
  `country_id` bigint(20) DEFAULT '0' COMMENT '国家Id',
  `country_code` varchar(16) DEFAULT '' COMMENT '国家code',
  `create_by` bigint(20) DEFAULT '0' COMMENT '创建人',
  `update_by` bigint(20) DEFAULT '0' COMMENT '最后修改人',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除0:未删除,1删除',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_storage_order_code` (`storage_order_code`) USING BTREE,
  KEY `index_belong_user_id` (`belong_user_id`) USING BTREE,
  KEY `index_product_sku_id` (`product_sku_id`) USING BTREE,
  KEY `index_warehouse_id` (`warehouse_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=208573 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='入库单详情信息';

-- ----------------------------
-- Table structure for t_psi_transferring_order
-- ----------------------------
DROP TABLE IF EXISTS `t_psi_transferring_order`;
CREATE TABLE `t_psi_transferring_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `transferring_order_code` varchar(128) NOT NULL COMMENT '出库单单号',
  `source_type` tinyint(4) DEFAULT '0' COMMENT '来源：0->psi自动创建；1:OMS下发',
  `transferring_type` tinyint(4) DEFAULT '0' COMMENT '调拨单据类型：0、PSI内部调拨 1、PSI-唯智 调拨  2、PSI-易仓调拨 ',
  `transferring_method` tinyint(4) NOT NULL DEFAULT '1' COMMENT '调拨方式 1: 分步调拨 2：直接调拨',
  `business_type` tinyint(4) DEFAULT '0' COMMENT '调拨业务类型：1 移库手动调拨 2卸货异常调拨',
  `source_transferring_id` bigint(20) DEFAULT NULL COMMENT '来源调拨单Id  用于记录自动生成的调拨单关联',
  `source_transferring_code` varchar(128) DEFAULT NULL COMMENT '来源调拨单号 用于记录自动生成的调拨单关联',
  `source_warehouse_id` bigint(20) NOT NULL COMMENT '调拨源仓库id',
  `target_warehouse_id` bigint(20) NOT NULL COMMENT '调拨目标仓库id',
  `relation_storage_order_code` varchar(128) DEFAULT NULL COMMENT '冗余 关联入库单单号',
  `relation_outbound_order_code` varchar(128) DEFAULT NULL COMMENT '冗余 关联出库单单号',
  `belong_user_id` bigint(20) NOT NULL COMMENT '所属PSI用户id',
  `supplier_id` bigint(20) DEFAULT NULL COMMENT 'psi供应商id',
  `source_supplier_id` bigint(20) DEFAULT NULL COMMENT '冗余 来源供应商id eg：egatee seller_id',
  `product_count` int(11) DEFAULT NULL COMMENT ' 商品数量',
  `received_outbound_count` int(11) DEFAULT NULL COMMENT '已出库数量',
  `not_yet_outbound_count` int(11) DEFAULT NULL COMMENT '未出库数量',
  `on_passage_count` int(11) DEFAULT NULL COMMENT '在途数量',
  `received_storage_count` int(11) DEFAULT NULL COMMENT '已入库数量',
  `not_yet_storage_count` int(11) DEFAULT NULL COMMENT '未入库数量',
  `transferring_status` tinyint(4) DEFAULT '1' COMMENT '调拨单状态,0关闭 1未出库、2部分出库、3全部出库、11 已入库、12部分入库、13全部入库 ',
  `relation_storage_status` tinyint(4) DEFAULT '1' COMMENT '入库状态,1未入库、2部分入库、3全部入库、0已经关闭 ',
  `relation_outbound_status` tinyint(4) DEFAULT '1' COMMENT '调拨单状态,0关闭 1未出库、2部分出库、3全部出库',
  `settlement_account_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '预留 结算账户id',
  `order_remarks` varchar(1000) DEFAULT NULL COMMENT '调拨单据备注',
  `country_id` bigint(20) DEFAULT '0' COMMENT '国家Id',
  `country_code` varchar(16) DEFAULT '' COMMENT '国家code',
  `transferring_finish_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '出库完成时间',
  `create_by` bigint(20) DEFAULT '0' COMMENT '创建人',
  `update_by` bigint(20) DEFAULT '0' COMMENT '最后修改人',
  `order_time` datetime DEFAULT NULL,
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除0:未删除,1删除',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_transferring_order_code` (`transferring_order_code`) USING BTREE,
  KEY `index_source_transferring_code` (`source_transferring_code`) USING BTREE,
  KEY `index_relation_storage_order_code` (`relation_storage_order_code`) USING BTREE,
  KEY `index_relation_outbound_order_code` (`relation_outbound_order_code`) USING BTREE,
  KEY `index_source_warehouse_id` (`source_warehouse_id`) USING BTREE,
  KEY `index_target_warehouse_id` (`target_warehouse_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=182 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='调拨单主信息';

-- ----------------------------
-- Table structure for t_psi_transferring_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_psi_transferring_order_detail`;
CREATE TABLE `t_psi_transferring_order_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `transferring_order_id` bigint(20) NOT NULL COMMENT '关联调拨单主键',
  `transferring_order_code` varchar(128) NOT NULL COMMENT '关联调拨单单号',
  `source_warehouse_id` bigint(20) NOT NULL COMMENT '冗余 调拨源仓库id',
  `target_warehouse_id` bigint(20) NOT NULL COMMENT '冗余 调拨目标仓库id',
  `belong_user_id` bigint(20) NOT NULL COMMENT '所属PSI用户id',
  `source_sku_id` bigint(20) DEFAULT NULL COMMENT '冗余 来源skuid ',
  `product_sku_id` bigint(20) NOT NULL COMMENT 'skuid ',
  `product_sku_code` varchar(128) NOT NULL COMMENT 'sku编码',
  `product_sku_name` varchar(512) DEFAULT NULL COMMENT '冗余 商品名称',
  `product_attribute` varchar(128) DEFAULT NULL COMMENT '冗余 商品销售属性',
  `product_sku_price` decimal(22,2) DEFAULT NULL COMMENT '商品 销售单价',
  `product_amount` decimal(22,2) DEFAULT NULL COMMENT '商品总价= 单价*数量',
  `product_sale_unit` varchar(255) DEFAULT '' COMMENT '销售单位(多项)',
  `product_count` int(11) DEFAULT NULL COMMENT ' 商品数量',
  `received_outbound_count` int(11) DEFAULT NULL COMMENT '已出库数量',
  `not_yet_outbound_count` int(11) DEFAULT NULL COMMENT '未出库数量',
  `on_passage_count` int(11) DEFAULT NULL COMMENT '在途数量',
  `received_storage_count` int(11) DEFAULT NULL COMMENT '已入库数量',
  `not_yet_storage_count` int(11) DEFAULT NULL COMMENT '未入库数量',
  `add_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '数据录入来源：1->扫码录入,2 手动录入,3:导入',
  `country_id` bigint(20) DEFAULT '0' COMMENT '国家Id',
  `country_code` varchar(16) DEFAULT '' COMMENT '国家code',
  `create_by` bigint(20) DEFAULT '0' COMMENT '创建人',
  `update_by` bigint(20) DEFAULT '0' COMMENT '最后修改人',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除0:未删除,1删除',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_transferring_order_id` (`transferring_order_id`) USING BTREE,
  KEY `index_transferring_order_code` (`transferring_order_code`) USING BTREE,
  KEY `index_product_sku_id` (`product_sku_id`) USING BTREE,
  KEY `index_product_sku_code` (`product_sku_code`) USING BTREE,
  KEY `index_source_warehouse_id` (`source_warehouse_id`) USING BTREE,
  KEY `index_target_warehouse_id` (`target_warehouse_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=225697 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='调拨单详情信息';

-- ----------------------------
-- Table structure for t_psi_user
-- ----------------------------
DROP TABLE IF EXISTS `t_psi_user`;
CREATE TABLE `t_psi_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(255) NOT NULL COMMENT '用户名(登录名称)',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `user_type` bigint(20) DEFAULT '0' COMMENT '用户类型（0 平台管理 1 商家管理员 ）',
  `source_type` int(11) DEFAULT '0' COMMENT '用户来源 0 psi 新增的 1 Egatee用户 99 其他',
  `source_id` varchar(255) DEFAULT NULL COMMENT '来源用户关系信息 (Egatee 用户关联的是 userId)',
  `belong_user_id` bigint(20) DEFAULT '0' COMMENT '所属用户 默认0',
  `level` int(11) DEFAULT '0' COMMENT '账户级别:0.最高',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(36) DEFAULT NULL COMMENT '手机号',
  `user_status` tinyint(4) DEFAULT '0' COMMENT '是否可用',
  `create_user_id` bigint(20) DEFAULT '0' COMMENT '创建userId',
  `country_id` bigint(20) DEFAULT NULL COMMENT '国家ID',
  `country_code` varchar(8) DEFAULT NULL COMMENT '国家code',
  `customer_code` varchar(64) DEFAULT NULL COMMENT '关联的国包/二级(DBR)客户代码',
  `customer_name` varchar(255) DEFAULT NULL COMMENT '关联的国包/二级(DBR)客户名称',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  `email_encode` varchar(128) DEFAULT '' COMMENT '邮箱加密',
  `phone_encode` varchar(128) DEFAULT '' COMMENT '手机号加密',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=482 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Table structure for t_psi_user_identity
-- ----------------------------
DROP TABLE IF EXISTS `t_psi_user_identity`;
CREATE TABLE `t_psi_user_identity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `identity_type` int(11) DEFAULT NULL COMMENT '(1、 国包  2、二级（DBR)  3、R)',
  `source_id` bigint(20) DEFAULT NULL COMMENT '关联外部 Id (国包 二级 存入的是 sellerId   R 存buyer_id )',
  `user_id` bigint(20) DEFAULT NULL COMMENT 'psi 用户id',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否被删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1065 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='用户来源身份记录表';

-- ----------------------------
-- Table structure for t_psi_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_psi_user_role`;
CREATE TABLE `t_psi_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否被删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1167 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户-角色';

-- ----------------------------
-- Table structure for t_warehouse_info
-- ----------------------------
DROP TABLE IF EXISTS `t_warehouse_info`;
CREATE TABLE `t_warehouse_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `belong_user_id` bigint(20) NOT NULL COMMENT '自增主键',
  `warehouse_code` varchar(64) DEFAULT NULL COMMENT '仓库编码',
  `warehouse_name` varchar(128) NOT NULL COMMENT '仓库名称',
  `warehouse_contact` varchar(128) DEFAULT NULL COMMENT '仓库联系人',
  `warehouse_phone` varchar(64) DEFAULT NULL COMMENT '仓库联系电话',
  `warehouse_email` varchar(128) DEFAULT NULL COMMENT '仓库联系邮箱',
  `warehouse_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '仓库类型 1：egatee自动创建仓，2,egatee自营仓,3:托管仓库,4:第三方仓库',
  `warehouse_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '仓库状态 1:启用,2:停用',
  `warehouse_area` varchar(128) DEFAULT NULL COMMENT '仓库区域',
  `warehouse_address` varchar(255) DEFAULT NULL COMMENT '仓库详细地址',
  `warehouse_acreage` int(11) DEFAULT '0' COMMENT '仓库面积 单位平方米',
  `shipping_region` mediumtext COMMENT '冗余 发货区域',
  `inventory_priority` int(11) DEFAULT '0' COMMENT '库存优先级',
  `country_id` bigint(20) DEFAULT '0' COMMENT '所属国家id',
  `country_code` varchar(16) DEFAULT '' COMMENT '国家code',
  `warehouse_remark` varchar(512) DEFAULT NULL COMMENT '仓库备注',
  `create_by` bigint(20) DEFAULT '0' COMMENT '创建人',
  `update_by` bigint(20) DEFAULT '0' COMMENT '最后修改人',
  `is_default` tinyint(1) DEFAULT '0' COMMENT '是否默认仓库 0 否 1 是',
  `is_virtual` tinyint(1) DEFAULT '1' COMMENT '是否虚拟仓库 0 否 1 是',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除0:未删除,1删除',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `warehouse_email_encode` varchar(128) DEFAULT '' COMMENT '仓库联系邮箱加密',
  `warehouse_phone_encode` varchar(128) DEFAULT '' COMMENT '仓库联系电话加密',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_warehouse_code` (`warehouse_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=610 DEFAULT CHARSET=utf8mb4 COMMENT='仓库主表';

-- ----------------------------
-- Table structure for t_warehouse_shipping_region_relation
-- ----------------------------
DROP TABLE IF EXISTS `t_warehouse_shipping_region_relation`;
CREATE TABLE `t_warehouse_shipping_region_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `warehouse_id` bigint(20) DEFAULT NULL COMMENT '仓库id',
  `district_id` bigint(20) NOT NULL COMMENT '关联egatee_district_id',
  `area_id` bigint(20) NOT NULL COMMENT '三级区域id',
  `area_name` varchar(255) DEFAULT NULL COMMENT '冗余 三级区域名称',
  `priority` int(11) NOT NULL DEFAULT '9999999' COMMENT '区域优先级，数字越小优先级越高',
  `create_by` bigint(20) DEFAULT '0' COMMENT '创建人',
  `update_by` bigint(20) DEFAULT '0' COMMENT '最后修改人',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除0:未删除,1删除',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_warehouse_id` (`warehouse_id`) USING BTREE,
  KEY `index_area_id` (`area_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=421524 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='仓库与发货区域关联表';

-- ----------------------------
-- Table structure for t_warehouse_user_relation
-- ----------------------------
DROP TABLE IF EXISTS `t_warehouse_user_relation`;
CREATE TABLE `t_warehouse_user_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `warehouse_id` bigint(20) DEFAULT NULL COMMENT '仓库id',
  `user_id` bigint(20) DEFAULT NULL COMMENT 'psi用户id',
  `relation_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '关联关系类型 1:强关联',
  `warehouse_type` tinyint(4) NOT NULL COMMENT '仓库类型',
  `contract_type` tinyint(4) NOT NULL COMMENT '合同类型',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除0:未删除,1删除',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_warehouse_id` (`warehouse_id`) USING BTREE,
  KEY `index_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=837 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='卖家仓库关系配置表';


SET FOREIGN_KEY_CHECKS = 1;