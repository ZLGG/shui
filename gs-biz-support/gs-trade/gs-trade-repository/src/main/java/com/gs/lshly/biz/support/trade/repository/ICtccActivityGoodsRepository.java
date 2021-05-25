package com.gs.lshly.biz.support.trade.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.biz.support.trade.entity.CtccActivityGoods;

import java.util.List;

/**
 * @Author yangxi
 * @create 2021/5/24 21:57
 */
public interface ICtccActivityGoodsRepository extends IService<CtccActivityGoods> {
    /**
     * 根据商品id查询活动信息
     * @param goodsIds
     * @return
     */
    List<CtccActivityGoods> getActivityIdByGoodsId(List<String> goodsIds);
}
