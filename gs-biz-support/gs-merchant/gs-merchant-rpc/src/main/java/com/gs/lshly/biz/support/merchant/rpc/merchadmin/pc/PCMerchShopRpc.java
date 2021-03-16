package com.gs.lshly.biz.support.merchant.rpc.merchadmin.pc;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchSettingsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchSettingsVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopVO;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchShopService;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchShopRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-13
*/
@DubboService
public class PCMerchShopRpc implements IPCMerchShopRpc {

    @Autowired
    private IPCMerchShopService pcMerchShopService;

    @Override
    public List<PCMerchShopVO.ListVO> list(PCMerchShopQTO.QTO qto){
        return pcMerchShopService.list(qto);
    }

    @Override
    public void editShop(PCMerchShopDTO.ETO eto){
        pcMerchShopService.editShop(eto);
    }

    @Override
    public PCMerchShopVO.DetailVO detailShop(BaseDTO dto){
        return pcMerchShopService.detailShop(dto);
    }

    @Override
    public PCMerchSettingsVO.DeliveryStyleVO getDeliveryStyle(BaseDTO baseDTO) {
        return pcMerchShopService.fetch(baseDTO);
    }

    @Override
    public void setDeliveryStyle(PCMerchSettingsDTO.DeliveryStyleDTO dto) {
        if (ObjectUtils.isEmpty(dto.getDeliveryTypes())) {
            throw new BusinessException("配送方式不能为空");
        }
        pcMerchShopService.set(dto);
    }

    @Override
    public Integer innerShopState(String shopId) {
        return pcMerchShopService.innerShopState(shopId);
    }

    @Override
    public PCMerchShopVO.ShopSimpleVO innerShopSimple(String shopId) {
        return pcMerchShopService.innerShopSimple(shopId);
    }

}