package com.gs.lshly.biz.support.user.service.bbb.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsInfoVO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserFrequentGoodsV2DTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserFrequentGoodsV2QTO;

public interface IPCBbbUserFrequentGoodsV2Service {

    PageData<PCBbbGoodsInfoVO.InnerServiceVO> pageData(PCBbbUserFrequentGoodsV2QTO.QTO qto);

    void addMore(PCBbbUserFrequentGoodsV2DTO.ETO eto);

    void addOne(PCBbbUserFrequentGoodsV2DTO.OneETO dto);

    void deleteUserFrequentGoods(PCBbbUserFrequentGoodsV2DTO.IdDTO dto);

    void deleteBatch(PCBbbUserFrequentGoodsV2DTO.IdListDTO dto);

}