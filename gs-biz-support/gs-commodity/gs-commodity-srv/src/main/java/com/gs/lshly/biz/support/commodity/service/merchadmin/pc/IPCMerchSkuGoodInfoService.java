package com.gs.lshly.biz.support.commodity.service.merchadmin.pc;
import com.gs.lshly.biz.support.commodity.entity.SkuGoodInfo;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchSkuGoodInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchSkuGoodInfoQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchSkuGoodInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtSeckillVO;

import java.util.List;

public interface IPCMerchSkuGoodInfoService {

    PageData<PCMerchSkuGoodInfoVO.ListVO> pageData(PCMerchSkuGoodInfoQTO.QTO qto);

    void addSkuGoodInfo(PCMerchSkuGoodInfoDTO.ETO eto);

    void deleteSkuGoodInfo(PCMerchSkuGoodInfoDTO.IdDTO dto);
    void editSkuGoodInfo(PCMerchSkuGoodInfoDTO.ETO eto);

    PCMerchSkuGoodInfoVO.DetailVO detailSkuGoodInfo(PCMerchSkuGoodInfoDTO.IdDTO dto);

    /**
     * 根据商品id查询sku
     * @param goodId
     * @return
     */
    List<PCMerchSkuGoodInfoVO.DetailVO> getByGoodsId(PCMerchSkuGoodInfoDTO.GoodIdDTO goodId);

    //-------------内部服务-------

    void updateSkuInfo(SkuGoodInfo skuGoodInfo);
    void addSkuInfo(SkuGoodInfo skuGoodInfo);

    String selectSkuImg(String skuId);

    List<PCMerchMarketPtSeckillVO.AllSkuVO> selectByGoodsId(String goodsId);
}