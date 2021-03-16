package com.gs.lshly.biz.support.user.rpc.bbb.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserFrequentGoodsDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserFrequentGoodsQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserFrequentGoodsVO;
import com.gs.lshly.biz.support.user.service.bbb.pc.IPCBbbUserFrequentGoodsService;
import com.gs.lshly.rpc.api.bbb.pc.user.IPCBbbUserFrequentRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author xxfc
* @since 2020-12-10
*/
@DubboService
public class PCBbbUserFrequentRpc implements IPCBbbUserFrequentRpc {

    @Autowired
    private IPCBbbUserFrequentGoodsService  pCBbbUserFrequentGoodsService;

    @Override
    public PageData<PCBbbUserFrequentGoodsVO.ListVO> pageData(PCBbbUserFrequentGoodsQTO.QTO qto){
        return pCBbbUserFrequentGoodsService.pageData(qto);
    }

    @Override
    public void addMore(PCBbbUserFrequentGoodsDTO.ETO eto){
        pCBbbUserFrequentGoodsService.addMore(eto);
    }

    @Override
    public void addOne(PCBbbUserFrequentGoodsDTO.OneETO dto) {
        pCBbbUserFrequentGoodsService.addOne(dto);
    }

    @Override
    public void deleteUserFrequentGoods(PCBbbUserFrequentGoodsDTO.IdDTO dto){
        pCBbbUserFrequentGoodsService.deleteUserFrequentGoods(dto);
    }

    @Override
    public void deleteBatch(PCBbbUserFrequentGoodsDTO.IdListDTO dto) {
        pCBbbUserFrequentGoodsService.deleteBatch(dto);
    }


}