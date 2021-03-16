package com.gs.lshly.biz.support.commodity.service.bbb.h5;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.commodity.dto.BbbH5GoodsQaDTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.qto.BbbH5GoodsQaQTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsQaVO;

public interface IBbbH5GoodsQaService {

    /**
     * 查询商品咨询列表
     * @param qto
     * @return
     */
    PageData<BbbH5GoodsQaVO.ShowListVO> pageData(BbbH5GoodsQaQTO.QTO qto);

    /**
     * 进行商品咨询
     * @param eto
     */
    void addGoodsQa(BbbH5GoodsQaDTO.ETO eto);


}