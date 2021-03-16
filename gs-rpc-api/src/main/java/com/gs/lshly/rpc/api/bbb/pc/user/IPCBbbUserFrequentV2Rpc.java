package com.gs.lshly.rpc.api.bbb.pc.user;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsInfoVO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserFrequentGoodsDTO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserFrequentGoodsV2DTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserFrequentGoodsQTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserFrequentGoodsV2QTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserFrequentGoodsVO;

/**
*常购清单
* @author xxfc
* @since 2020-12-10
*/
public interface IPCBbbUserFrequentV2Rpc {

    PageData<PCBbbGoodsInfoVO.InnerServiceVO> pageData(PCBbbUserFrequentGoodsV2QTO.QTO qto);

    void addMore(PCBbbUserFrequentGoodsV2DTO.ETO eto);

    void addOne(PCBbbUserFrequentGoodsV2DTO.OneETO dto);

    void deleteUserFrequentGoods(PCBbbUserFrequentGoodsV2DTO.IdDTO dto);

    void deleteBatch(PCBbbUserFrequentGoodsV2DTO.IdListDTO dto);

}