package com.gs.lshly.biz.support.merchant.rpc.merchadmin.h5;
import com.gs.lshly.biz.support.merchant.service.merchadmin.h5.IH5MerchShopService;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.dto.H5MerchShopDTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.qto.H5MerchShopQTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.vo.H5MerchShopVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopVO;
import com.gs.lshly.rpc.api.merchadmin.h5.merchant.IH5MerchShopRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author zst
* @since 2021-01-20
*/
@DubboService
public class H5MerchShopRpc implements IH5MerchShopRpc{
    @Autowired
    private IH5MerchShopService h5MerchShopService;

    @Override
    public List<H5MerchShopVO.ListVO> list(H5MerchShopQTO.QTO qto){
        return h5MerchShopService.list(qto);
    }

    @Override
    public void editShop(H5MerchShopDTO.ETO eto){
        h5MerchShopService.editShop(eto);
    }

    @Override
    public H5MerchShopVO.DetailVO detailShop(BaseDTO dto){
        return  h5MerchShopService.detailShop(dto);
    }

    @Override
    public H5MerchShopVO.ShopSimpleVO innerShopSimple(String shopId) {
        return h5MerchShopService.innerShopSimple(shopId);
    }
}