package com.gs.lshly.biz.support.commodity.rpc.merchadmin.pc;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsShopNavigationService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsShopNavigationDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsShopNavigationQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsShopNavigationVO;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchGoodsShopNavigationRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author Starry
* @since 2020-11-09
*/
@DubboService
public class PCMerchGoodsShopNavigationRpc implements IPCMerchGoodsShopNavigationRpc {
    @Autowired
    private IPCMerchGoodsShopNavigationService pCMerchGoodsShopNavigationService;


    @Override
    public void bindGoods(PCMerchGoodsShopNavigationDTO.BindGoodsDTO dto) {
        pCMerchGoodsShopNavigationService.bindGoods(dto);
    }

    @Override
    public List<PCMerchGoodsShopNavigationVO.ListVO> listInnerService(PCMerchGoodsShopNavigationQTO.QTO qto) {
        return pCMerchGoodsShopNavigationService.ListInnerService(qto);
    }

    @Override
    public void deleteInnerService(PCMerchGoodsShopNavigationDTO.IdListDTO dto) {
        pCMerchGoodsShopNavigationService.deleteInnerService(dto);
    }
}