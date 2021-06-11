package com.gs.lshly.biz.support.commodity.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchSkuGoodInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchSkuGoodInfoQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchSkuGoodInfoVO;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchSkuGoodInfoService;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtSeckillVO;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminSkuGoodInfoRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author Starry
* @since 2020-10-08
*/
@DubboService
public class PCMerchAdminSkuGoodInfoRpc implements IPCMerchAdminSkuGoodInfoRpc {

    @Autowired
    private IPCMerchSkuGoodInfoService skuGoodInfoService;

    @Override
    public PageData<PCMerchSkuGoodInfoVO.ListVO> pageData(PCMerchSkuGoodInfoQTO.QTO qto){
        return skuGoodInfoService.pageData(qto);
    }

    @Override
    public void addSkuGoodInfo(PCMerchSkuGoodInfoDTO.ETO eto){
        skuGoodInfoService.addSkuGoodInfo(eto);
    }

    @Override
    public void deleteSkuGoodInfo(PCMerchSkuGoodInfoDTO.IdDTO dto){
        skuGoodInfoService.deleteSkuGoodInfo(dto);
    }


    @Override
    public void editSkuGoodInfo(PCMerchSkuGoodInfoDTO.ETO eto){
        skuGoodInfoService.editSkuGoodInfo(eto);
    }

    @Override
    public PCMerchSkuGoodInfoVO.DetailVO detailSkuGoodInfo(PCMerchSkuGoodInfoDTO.IdDTO dto){
        return skuGoodInfoService.detailSkuGoodInfo(dto);
    }

    @Override
    public List<PCMerchMarketPtSeckillVO.AllSkuVO> selectByGoodsId(String goodsId) {
        return skuGoodInfoService.selectByGoodsId(goodsId);
    }

}