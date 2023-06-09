DROP TABLE IF EXISTS `gs_site_advert_popup`;
CREATE TABLE `gs_site_advert_popup` (
`id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id' ,
`name`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '广告弹窗名称' ,
`image_url`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址' ,
`onoff`  int(2) NULL DEFAULT 0 COMMENT '上下架 0下 1上' ,
`jump_url`  varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跳转地址' ,
`cdate`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
`udate`  datetime NULL DEFAULT NULL COMMENT '更新时间' ,
`flag`  tinyint(1) NULL DEFAULT NULL COMMENT '逻辑删除标记' ,
`pc_show`  int(11) NULL DEFAULT NULL COMMENT '是否PC显示[10=是 20=否]' ,
`terminal`  int(11) NULL DEFAULT NULL COMMENT '终端[10=2b 20=2c]' ,
`subject`  int(11) NULL DEFAULT NULL COMMENT '专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏 50=积分商城]' ,
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='站点广告弹窗';


DROP TABLE IF EXISTS `gs_site_notice`;
CREATE TABLE `gs_site_notice` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `content` text COMMENT '内容',
  `cdate` datetime DEFAULT NULL COMMENT '创建时间',
  `udate` datetime DEFAULT NULL COMMENT '更新时间',
  `flag` tinyint(1) DEFAULT NULL COMMENT '逻辑删除标记',
  `pc_show` int(11) DEFAULT NULL COMMENT '是否PC显示[10=是 20=否]',
  `terminal` int(11) DEFAULT NULL COMMENT '终端[10=2b 20=2c]',
  `subject` int(11) DEFAULT NULL COMMENT '专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏 50=积分商城]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='站点公告信息';

ALTER TABLE `gs_goods_info`
ADD COLUMN `pos_spu_id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `flag`;

ALTER TABLE `gs_sku_good_info`
ADD COLUMN `pos_spu_id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `flag`;

ALTER TABLE `gs_site_bottom_article`
ADD COLUMN `pid`  varchar(32) NULL AFTER `flag`;

ALTER TABLE `gs_site_bottom_article`
ADD COLUMN `image_url`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址' AFTER `pid`;

ALTER TABLE `gs_site_topic`
ADD COLUMN `image_url`  varchar(255) NULL AFTER `subject`;

ALTER TABLE `fy_mall`.`gs_goods_info`
ADD COLUMN `point_price` decimal(12, 0) NULL DEFAULT NULL COMMENT '积分价格' AFTER `sub_category_id`,
ADD COLUMN `remarks` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '办理备注' AFTER `point_price`,
ADD COLUMN `is_point_good` tinyint(1) NULL DEFAULT NULL COMMENT '是否是积分商品' AFTER `remarks`,
ADD COLUMN `is_in_member_gift` tinyint(1) NULL DEFAULT NULL COMMENT '是否是in会员礼品' AFTER `is_point_good`,
ADD COLUMN `in_member_point_price` decimal(12, 0) NULL DEFAULT NULL COMMENT 'in会员积分价格' AFTER `is_in_member_gift`,
ADD COLUMN `sale_type` int(11) NULL DEFAULT NULL COMMENT '出售类型（0普通，1活动）' AFTER `in_member_point_price`;

ALTER TABLE `fy_mall`.`gs_sku_good_info`
ADD COLUMN `point_price` decimal(12, 0) NULL DEFAULT NULL COMMENT '积分价格' AFTER `pos_spu_id`,
ADD COLUMN `remarks` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '办理备注' AFTER `point_price`,
ADD COLUMN `is_point_good` tinyint(1) NULL DEFAULT NULL COMMENT '是否是积分商品' AFTER `remarks`,
ADD COLUMN `is_in_member_gift` tinyint(1) NULL DEFAULT NULL COMMENT '是否是in会员礼品' AFTER `is_point_good`,
ADD COLUMN `in_member_point_price` decimal(12, 0) NULL DEFAULT NULL COMMENT 'in会员积分价格' AFTER `is_in_member_gift`;

ALTER TABLE `fy_mall`.`gs_merchant_account`
ADD COLUMN `address` varchar(120) COMMENT '联系地址' AFTER `flag`;

ALTER TABLE `fy_mall`.`gs_user`
ADD COLUMN `is_in_user` tinyint(1) COMMENT '是否为in会员(1-是 0-否)' AFTER `flag`,
ADD COLUMN `telecoms_integral` int(11) COMMENT '电信积分' AFTER `is_in_user`,
ADD COLUMN `telecoms_pass` int(11) COMMENT '年底过期积分(电信)' AFTER `telecoms_integral`,
ADD COLUMN `direction_integral` int(11) COMMENT '电信定向积分' AFTER `telecoms_pass`,
ADD COLUMN `telecoms_level` varchar(24) COMMENT '电信等级' AFTER `direction_integral`;

ALTER TABLE `fy_mall`.`gs_goods_info`
ADD COLUMN `third_product_id` int(11) NULL DEFAULT NULL COMMENT '信天游产品号' AFTER `sale_type`;

ALTER TABLE `fy_mall`.`gs_merchant_account`
ADD COLUMN `address` varchar(50) COMMENT '联系地址' AFTER `flag`,
ADD COLUMN `name` varchar(50) COMMENT '商户名称' AFTER `address`,
ADD COLUMN `type` int(11) COMMENT '商户类别（10=积分商户 20=普通商户）' AFTER `name`,
ADD COLUMN `province` varchar(32) COMMENT '商户属地（省名称）' AFTER `type`,
ADD COLUMN `city` varchar(32) COMMENT '商户属地（市名称）' AFTER `province`,
ADD COLUMN `expiration_time` datetime(0) COMMENT '协议到期时间' AFTER `city`,
ADD COLUMN `agreement_code` varchar(120) COMMENT '协议号' AFTER `expiration_time`,
ADD COLUMN `tax_type` int(11) COMMENT '供应商纳税性质(10=一般纳税人 20=小规模纳税人)' AFTER `agreement_code`,
ADD COLUMN `tax_rate` decimal(10, 0) COMMENT '税率(%)' AFTER `tax_type`;

ALTER TABLE `fy_mall`.`gs_user`
ADD COLUMN `member_type` int(11) DEFAULT 1 COMMENT '用户类型(1-普通用户 2-电信用户)' AFTER `flag`,
MODIFY COLUMN `is_in_user` int(11) DEFAULT 0 COMMENT '是否为in会员(1-是 0-否)' AFTER `member_type`;

ALTER TABLE `fy_mall`.`gs_goods_info`
ADD COLUMN `sale_quantity` int(11) NOT NULL DEFAULT 0 DEFAULT NULL COMMENT '销售数量（临时排序用）' AFTER `third_product_id`;

ALTER TABLE `fy_mall`.`gs_goods_info`
ADD COLUMN `exchange_type` int(11) NULL DEFAULT NULL COMMENT '兑换类型（实物，虚拟）' AFTER `sale_quantity`;



DROP TABLE IF EXISTS `gs_trade_goods_travelsky`;
CREATE TABLE `gs_trade_goods_travelsky` (
  `id` varchar(32) NOT NULL COMMENT '交易商品ID',
  `trade_goods_id` varchar(32) DEFAULT NULL COMMENT '交易商品ID',
  `goods_id` varchar(32) DEFAULT NULL COMMENT '商品ID',
  `trade_id` varchar(32) DEFAULT NULL,
  `third_product_id` int(11) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL COMMENT '会员ID',
  `shop_id` varchar(32) DEFAULT NULL COMMENT '店铺ID',
  `merchant_id` varchar(32) DEFAULT NULL COMMENT '商家ID',
  `status` varchar(32) DEFAULT NULL,
  `result_msg` varchar(255) DEFAULT NULL,
  `order_id` varchar(32) DEFAULT NULL,
  `product_code` varchar(255) DEFAULT NULL,
  `valid_date` varchar(32) DEFAULT NULL,
  `sms_msg` varchar(255) DEFAULT NULL,
  `cdate` datetime DEFAULT NULL COMMENT '创建时间',
  `udate` datetime DEFAULT NULL COMMENT '更新时间',
  `flag` tinyint(1) DEFAULT NULL COMMENT '逻辑删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='信天游商品日志表';

DROP TABLE IF EXISTS `gs_user_coupon`;
CREATE TABLE `gs_user_coupon` (
  `id` varchar(32) NOT NULL COMMENT '主键id',
  `coupon_id` bigint(20) NOT NULL COMMENT '运营配置优惠券id',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `coupon_name` varchar(64) DEFAULT NULL COMMENT '优惠券名称',
  `coupon_desc` varchar(255) DEFAULT NULL COMMENT '优惠券说明',
  `start_time` date DEFAULT NULL COMMENT '优惠券使用开始时间',
  `end_time` date DEFAULT NULL COMMENT '优惠券使用结束时间',
  `coupon_price` decimal(10,2) NOT NULL COMMENT '优惠券抵扣金额',
  `min_price` decimal(10,2) DEFAULT NULL COMMENT '使用门槛',
  `coupon_type` varchar(255) DEFAULT NULL COMMENT '优惠券类型（1-IN会员抵扣券 2-店铺券 3-平台券 4-个人券）',
  `coupon_status` tinyint(1) NOT NULL COMMENT '优惠券状态（0-未使用 1-已使用 2-已过期）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员优惠券表';

ALTER TABLE `fy_mall`.`gs_trade`
    ADD COLUMN `goods_source_type` int(11) NULL COMMENT '商品来源类型：1:商城商品，2:积分商品' AFTER `trade_state`,
    ADD COLUMN `point_price_payable` int(11) NULL COMMENT '应付积分' AFTER `goods_amount`,
    ADD COLUMN `amount_payable` int(11) NULL COMMENT '应付现金' AFTER `point_price_payable`,
    ADD COLUMN `point_price_actually_paid` int(11) NULL COMMENT '实付积分' AFTER `merchant_amount`,
    ADD COLUMN `amount_actually_paid` int(11) NULL COMMENT '实付现金' AFTER `point_price_actually_paid`,
    ADD COLUMN `goods_point_amount` decimal(12, 3) NULL COMMENT '商品总积分' AFTER `goods_amount`;

ALTER TABLE `fy_mall`.`gs_trade_goods`
    ADD COLUMN `coupon_type` int(11) NULL COMMENT '优惠券类型（普通、IN会员）' AFTER `comment_flag`;

ALTER TABLE `fy_mall`.`gs_trade_pay`
    ADD COLUMN `merge_payment_trade_code` varchar(64) NULL COMMENT '合并支付交易编号（传递给第三方支付的交易单号）' AFTER `merchant_id`;

ALTER TABLE `fy_mall`.`gs_user_shopping_car`
    ADD COLUMN `is_point_good` tinyint(1) NULL COMMENT '是否是积分商品' AFTER `quantity`,
    ADD COLUMN `goods_point_price` decimal(10, 2) NULL COMMENT '商品积分价格' AFTER `is_point_good`;
    ADD COLUMN `in_member_point_price` decimal(12, 0) NULL COMMENT 'in会员积分价格' AFTER `is_in_member_gift`;

ALTER TABLE `fy_mall`.`gs_goods_info`
MODIFY COLUMN `is_suport_poor_goods` int(11) NULL DEFAULT 10 COMMENT '是否是扶贫商品(10=标准商品 20=扶贫商品）' AFTER `is_show_old_price`;

ALTER TABLE `fy_mall`.`gs_user_coupon`
    DROP COLUMN `min_price`
ALTER TABLE `fy_mall`.`gs_goods_info`
ADD COLUMN `click_volume` int(11) NOT NULL DEFAULT 0 COMMENT '点击量（临时）' AFTER `exchange_type`;

ALTER TABLE `fy_mall`.`gs_goods_info`
ADD COLUMN `video_url` varchar(512) NULL COMMENT '视频地址' AFTER `click_volume`;

ALTER TABLE `gs_merchant`
ADD COLUMN `address`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系地址' AFTER `flag`,
ADD COLUMN `name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户名称' AFTER `address`,
ADD COLUMN `type`  int(11) NULL DEFAULT NULL COMMENT '商户类别（10=积分商户 20=普通商户）' AFTER `name`,
ADD COLUMN `province`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户属地（省名称）' AFTER `type`,
ADD COLUMN `city`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户属地（市名称）' AFTER `province`,
ADD COLUMN `expiration_time`  datetime NULL DEFAULT NULL COMMENT '协议到期时间' AFTER `city`,
ADD COLUMN `agreement_code`  varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '协议号' AFTER `expiration_time`,
ADD COLUMN `tax_type`  int(11) NULL DEFAULT NULL COMMENT '供应商纳税性质(10=一般纳税人 20=小规模纳税人)' AFTER `agreement_code`,
ADD COLUMN `tax_rate`  decimal(10,0) NULL DEFAULT NULL COMMENT '税率(%)' AFTER `tax_type`;

ALTER TABLE `fy_mall`.`gs_goods_info`
ADD COLUMN `in_coupon_type` int(10) NULL COMMENT 'in会员优惠券类型（20,30,50,99,200）' AFTER `in_member_point_price`;

ALTER TABLE `gs_shop_navigation`
ADD COLUMN `img_url`  varchar(255) NULL AFTER `merchant_id`;

DROP TABLE IF EXISTS `gs_goods_search_history`;
CREATE TABLE `gs_goods_search_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `keyword` varchar(255) DEFAULT NULL COMMENT '关键字',
  `user_id` varchar(64) NOT NULL COMMENT '用户id',
  `cdate` datetime DEFAULT NULL COMMENT '创建时间',
  `udate` datetime DEFAULT NULL COMMENT '更新时间',
  `flag` tinyint(1) DEFAULT NULL COMMENT '删除标志（0-未删除，1-已删除）',
  `search_entry` tinyint(1) DEFAULT NULL COMMENT '搜索入口（0-电信商城，1-积分商城）',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='搜索历史记录表';

ALTER TABLE `fy_mall`.`gs_stock_address`
ADD COLUMN `full_address` varchar(255) COMMENT '完整地址' AFTER `reals`;

ALTER TABLE `fy_mall`.`gs_trade`
ADD COLUMN `is_modify_address` varchar(255) DEFAULT '0' COMMENT '是否修改过地址（0-否，1-是）' AFTER `timeout_cancel`;

CREATE TABLE `gs_ctcc_pt_activity_goods` (
  `id` varchar(32) NOT NULL COMMENT '主键id',
  `category_id` varchar(32) DEFAULT NULL COMMENT '类目id',
  `goods_id` varchar(32) DEFAULT NULL COMMENT '商品id',
  `goods_state` int(11) DEFAULT NULL COMMENT '商品状态（10-未上架，20-已上架）',
  `idx` int(15) DEFAULT NULL COMMENT '排序',
  `cdate` datetime DEFAULT NULL COMMENT '创建时间',
  `udate` datetime DEFAULT NULL COMMENT '更新时间',
  `flag` tinyint(1) DEFAULT NULL COMMENT '是否删除（0-未删除，1-已删除）',
  PRIMARY KEY (`id`),
  KEY `idx_goods_id` (`goods_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='运营平台电信国际活动商品表';

-- 添中字段 from 上海
child_trade_id	varchar	32	0	-1	0	0	0	0		0		utf8mb4	utf8mb4_general_ci		0	0
invoice_id	varchar	32	0	-1	0	0	0	0		0		utf8mb4	utf8mb4_general_ci		0	0
invoice_address_id	varchar	32	0	-1	0	0	0	0		0		utf8mb4	utf8mb4_general_ci		0	0
user_card_id	varchar	32	0	-1	0	0	0	0		0		utf8mb4	utf8mb4_general_ci		0	0

gs_remind_merchant
state	int	11	0	-1	0	0	0	0		0	10=待读 20=已读				0	0








