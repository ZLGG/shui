package com.gs.lshly.biz.support.commodity.service.bbb.pc;


import java.util.List;

public interface IPCBbbGoodsLabelService {

    /**
     * 获取所有打上推荐标签的2B商品id
     * @return
     */
    List<String> listGoodsId();

}