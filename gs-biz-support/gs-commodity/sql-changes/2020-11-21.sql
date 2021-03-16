-- 商品仓储物流配置表增加字段
ALTER TABLE `gs_goods_tempalte`
ADD COLUMN `stock_subtract_type`  int(2) NULL COMMENT '减库存方式(10-付款减库存，下单减库存)' AFTER `template_id`,
COMMENT='商品仓储物流配置';

