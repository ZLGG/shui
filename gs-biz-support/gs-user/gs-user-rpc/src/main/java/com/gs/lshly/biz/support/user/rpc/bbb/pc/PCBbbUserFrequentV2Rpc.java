package com.gs.lshly.biz.support.user.rpc.bbb.pc;
import com.gs.lshly.biz.support.user.service.bbb.pc.IPCBbbUserFrequentGoodsV2Service;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsInfoVO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserFrequentGoodsV2DTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserFrequentGoodsV2QTO;
import com.gs.lshly.rpc.api.bbb.pc.user.IPCBbbUserFrequentV2Rpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author xxfc
* @since 2020-12-10
*/
@DubboService
public class PCBbbUserFrequentV2Rpc implements IPCBbbUserFrequentV2Rpc {

    @Autowired
    private IPCBbbUserFrequentGoodsV2Service pCBbbUserFrequentGoodsV2Service;


    @Override
    public PageData<PCBbbGoodsInfoVO.InnerServiceVO> pageData(PCBbbUserFrequentGoodsV2QTO.QTO qto) {
        return pCBbbUserFrequentGoodsV2Service.pageData(qto);
    }

    @Override
    public void addMore(PCBbbUserFrequentGoodsV2DTO.ETO eto) {
        pCBbbUserFrequentGoodsV2Service.addMore(eto);
    }

    @Override
    public void addOne(PCBbbUserFrequentGoodsV2DTO.OneETO dto) {
        pCBbbUserFrequentGoodsV2Service.addOne(dto);
    }

    @Override
    public void deleteUserFrequentGoods(PCBbbUserFrequentGoodsV2DTO.IdDTO dto) {
        pCBbbUserFrequentGoodsV2Service.deleteUserFrequentGoods(dto);
    }

    @Override
    public void deleteBatch(PCBbbUserFrequentGoodsV2DTO.IdListDTO dto) {
        pCBbbUserFrequentGoodsV2Service.deleteBatch(dto);
    }
}