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
ADD COLUMN `user_type` int(11) DEFAULT 1 COMMENT '用户类型(1-普通用户 2-电信用户)' AFTER `flag`,
MODIFY COLUMN `is_in_user` int(11) DEFAULT 0 COMMENT '是否为in会员(1-是 0-否)' AFTER `user_type`;