package com.gs.lshly.rpc.api.bbc.commodity;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.commodity.dto.PCBbbGoodsQaDTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.qto.PCBbbGoodsQaQTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsQaVO;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsQaDTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsQaQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsQaVO;

/**
*
* @author Starry
* @since 2020-10-16
*/
public interface IBbcGoodsQaRpc {

    PageData<BbcGoodsQaVO.ShowListVO> pageData(BbcGoodsQaQTO.QTO qto);

    void addGoodsQa(BbcGoodsQaDTO.ETO eto);



}