package com.gs.lshly.rpc.api.test;

import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;

import java.util.List;

public interface IESTestRpc {

    void importGoodsInfoById(String goodsId);

    List<BbcGoodsInfoVO.GoodsListVO> searchByNameOrTitle(String name, String title);

}
