package com.gs.lshly.biz.support.merchant.rpc.bbc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.merchant.qto.BbcShopBannerQTO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopBannerVO;
import com.gs.lshly.rpc.api.bbc.merchant.IBbcShopBannerRpc;
import com.gs.lshly.biz.support.merchant.service.bbc.IBbcShopBannerService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author 陈奇
* @since 2020-10-24
*/
@DubboService
public class BbcShopBannerRpc implements IBbcShopBannerRpc{

    @Autowired
    private IBbcShopBannerService  bbcShopBannerService;

    @Override
    public PageData<BbcShopBannerVO.ListVO> pageData(BbcShopBannerQTO.QTO qto){
        return bbcShopBannerService.pageData(qto);
    }



}