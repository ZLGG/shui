package com.gs.lshly.biz.support.commodity.mapper.view;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author Starry
 * @Date 11:25 2021/1/6
 */
@Data
public class GoodSkuInfoView extends Model {
    private String goodsId;

    private String skuId;

    private String goodsName;

    private BigDecimal salePrice;
}
