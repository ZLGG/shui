package com.gs.lshly.biz.support.commodity.mapper.view;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * @Author Starry
 * @Date 11:35 2021/1/12
 */
@Data
public class GoodsQaCountView extends Model {

    /**
     * 该商品全部咨询数量
     */
    private Integer allNum;

    /**
     * 商品咨询数量
     */
    private Integer goodsQuizNum;

    /**
     * 发票保修数量
     */
    private Integer invoiceWarrantyNum;

    /**
     * 支付方式数量
     */
    private Integer paymentWayNum;

    /**
     * 库存配送数量
     */
    private Integer inventoryDistributionNum;
}
