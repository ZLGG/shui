package com.gs.lshly.biz.support.commodity.service.test;

import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import org.springframework.stereotype.Component;

import java.util.List;


public interface IESTestSrv {

    void importGoodsInfoById(String goodsId);

    List<BbcGoodsInfoVO.GoodsListVO> searchByNameOrTitle(String name, String title);
}
