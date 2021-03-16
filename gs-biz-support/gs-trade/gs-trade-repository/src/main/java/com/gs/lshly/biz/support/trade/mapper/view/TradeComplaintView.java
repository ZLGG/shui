package com.gs.lshly.biz.support.trade.mapper.view;

import com.gs.lshly.biz.support.trade.entity.TradeComplaint;
import lombok.Data;

/**
 * @Author Starry
 * @Date 19:10 2020/12/23
 */
@Data
public class TradeComplaintView extends TradeComplaint {

    private String tradeCode;

    private String goodsName;
}
