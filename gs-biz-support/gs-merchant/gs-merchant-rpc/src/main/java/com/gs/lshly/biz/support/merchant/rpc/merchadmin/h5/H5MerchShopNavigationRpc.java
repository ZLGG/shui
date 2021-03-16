package com.gs.lshly.biz.support.merchant.rpc.merchadmin.h5;

import com.gs.lshly.biz.support.merchant.service.merchadmin.h5.IH5MerchShopNavigationService;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.qto.H5MerchShopNavigationQTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.qto.H5MerchShopQTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.vo.H5MerchMerchantAccountVO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.vo.H5MerchShopNavigationVO;
import com.gs.lshly.rpc.api.merchadmin.h5.merchant.IH5MerchShopNavigationRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author zst
* @since 2021-1-27
*/
@DubboService
public class H5MerchShopNavigationRpc implements IH5MerchShopNavigationRpc {

    @Autowired
    private IH5MerchShopNavigationService h5MerchShopNavigationService;

    @Override
    public List<H5MerchShopNavigationVO.ListVO> list(H5MerchShopNavigationQTO.QTO qto){
        return h5MerchShopNavigationService.list(qto);
    }

    @Override
    public List<H5MerchShopNavigationVO.NavigationVO> listLevel001(BaseDTO dto) {
        return h5MerchShopNavigationService.listLevel001(dto);
    }

    @Override
    public H5MerchMerchantAccountVO.checkShopByShopId checkShopByShopId(H5MerchShopQTO.CutShopQTO qto) {
        return h5MerchShopNavigationService.checkShopByShopId(qto);
    }

    @Override
    public H5MerchMerchantAccountVO.CheckShopVO checkShop(BaseDTO dto) {
        return h5MerchShopNavigationService.checkShop(dto);
    }

}