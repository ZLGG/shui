package com.gs.lshly.biz.support.merchant.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopFloorDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopFloorQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopFloorVO;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchShopFloorRpc;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchShopFloorService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-11-05
*/
@DubboService
public class PCMerchShopFloorRpc implements IPCMerchShopFloorRpc{

    @Autowired
    private IPCMerchShopFloorService  pCMerchShopFloorService;

    @Override
    public List<PCMerchShopFloorVO.H5ListVO> h5List(PCMerchShopFloorQTO.H5QTO qto) {
        return pCMerchShopFloorService.h5List(qto);
    }

    @Override
    public void h5Editor(PCMerchShopFloorDTO.H5ETO eto) {
        pCMerchShopFloorService.h5Editor(eto);
    }

    @Override
    public List<PCMerchShopFloorVO.PCListVO> pcList(PCMerchShopFloorQTO.PCQTO qto) {
        return pCMerchShopFloorService.pcList(qto);
    }

    @Override
    public void pcEditor(PCMerchShopFloorDTO.PCETO eto) {
        pCMerchShopFloorService.pcEditor(eto);
    }
}