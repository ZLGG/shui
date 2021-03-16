package com.gs.lshly.biz.support.commodity.service.bbc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.commodity.dto.PCBbbGoodsQaDTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.qto.PCBbbGoodsQaQTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsQaVO;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsQaDTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsQaQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsQaVO;

public interface IBbcGoodsQaService {

    /**
     * 查询商品咨询列表
     * @param qto
     * @return
     */
    PageData<BbcGoodsQaVO.ShowListVO> pageData(BbcGoodsQaQTO.QTO qto);

    /**
     * 进行商品咨询
     * @param eto
     */
    void addGoodsQa(BbcGoodsQaDTO.ETO eto);


}