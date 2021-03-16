package com.gs.lshly.biz.support.user.service.bbb.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserFrequentGoodsDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserFrequentGoodsQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserFrequentGoodsVO;

public interface IPCBbbUserFrequentGoodsService {

    PageData<PCBbbUserFrequentGoodsVO.ListVO> pageData(PCBbbUserFrequentGoodsQTO.QTO qto);

    void addMore(PCBbbUserFrequentGoodsDTO.ETO eto);

    void addOne(PCBbbUserFrequentGoodsDTO.OneETO dto);

    void deleteUserFrequentGoods(PCBbbUserFrequentGoodsDTO.IdDTO dto);

    void deleteBatch(PCBbbUserFrequentGoodsDTO.IdListDTO dto);

}