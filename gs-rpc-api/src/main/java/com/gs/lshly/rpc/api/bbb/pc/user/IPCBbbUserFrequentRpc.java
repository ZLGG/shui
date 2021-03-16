package com.gs.lshly.rpc.api.bbb.pc.user;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserFrequentGoodsDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserFrequentGoodsQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserFrequentGoodsVO;

/**
*常购清单
* @author xxfc
* @since 2020-12-10
*/
public interface IPCBbbUserFrequentRpc {

    PageData<PCBbbUserFrequentGoodsVO.ListVO> pageData(PCBbbUserFrequentGoodsQTO.QTO qto);

    void addMore(PCBbbUserFrequentGoodsDTO.ETO eto);

    void addOne(PCBbbUserFrequentGoodsDTO.OneETO dto);

    void deleteUserFrequentGoods(PCBbbUserFrequentGoodsDTO.IdDTO dto);

    void deleteBatch(PCBbbUserFrequentGoodsDTO.IdListDTO dto);

}