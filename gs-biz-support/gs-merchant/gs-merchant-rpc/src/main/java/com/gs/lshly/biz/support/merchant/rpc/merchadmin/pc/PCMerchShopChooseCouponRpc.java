package com.gs.lshly.biz.support.merchant.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopChooseCouponDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopChooseCouponQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopChooseCouponVO;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchShopChooseCouponRpc;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchShopChooseCouponService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author 陈奇
* @since 2020-10-23
*/
@DubboService
public class PCMerchShopChooseCouponRpc implements IPCMerchShopChooseCouponRpc{
    @Autowired
    private IPCMerchShopChooseCouponService  pCMerchShopChooseCouponService;

    @Override
    public PageData<PCMerchShopChooseCouponVO.ListVO> pageData(PCMerchShopChooseCouponQTO.QTO qto){
        return pCMerchShopChooseCouponService.pageData(qto);
    }

    @Override
    public void addShopChooseCoupon(PCMerchShopChooseCouponDTO.ETO eto){
        pCMerchShopChooseCouponService.addShopChooseCoupon(eto);
    }

    @Override
    public void deleteShopChooseCoupon(PCMerchShopChooseCouponDTO.IdDTO dto){
        pCMerchShopChooseCouponService.deleteShopChooseCoupon(dto);
    }


    @Override
    public void editShopChooseCoupon(PCMerchShopChooseCouponDTO.ETO eto){
        pCMerchShopChooseCouponService.editShopChooseCoupon(eto);
    }

    @Override
    public PCMerchShopChooseCouponVO.DetailVO detailShopChooseCoupon(PCMerchShopChooseCouponDTO.IdDTO dto){
        return  pCMerchShopChooseCouponService.detailShopChooseCoupon(dto);
    }

}