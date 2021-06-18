package com.gs.lshly.biz.support.commodity.rpc.merchadmin.pc;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsFupinService;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsInfoTempService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsInfoQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsInfoService;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchSkuGoodInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodityfupin.dto.PCMerchGoodsFupinDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodityfupin.qto.PCMerchGoodsFupinQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodityfupin.vo.PCMerchGoodsFupinVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMarketPtSeckillQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtSeckillVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsInfoRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Starry
 * @since 2020-10-08
 */
@DubboService
public class PCMerchAdminGoodsInfoRpc implements IPCMerchAdminGoodsInfoRpc {

    @Autowired
    private IPCMerchGoodsInfoService goodsInfoService;
    @Autowired
    private IPCMerchGoodsFupinService goodsFupinService;
    @Autowired
    private IPCMerchGoodsInfoTempService goodsInfoTempService;

    @Override
    public PageData<PCMerchGoodsInfoVO.SpuListVO> pageData(PCMerchGoodsInfoQTO.GoodsInfoParamsQTO qto) {
        return goodsInfoService.pageGoodsData(qto);
    }

    @Override
    public PageData<PCMerchGoodsInfoVO.StockAlarmGoodsVO> pageStockAlarmGoods(PCMerchGoodsInfoQTO.GoodsInfoParamsQTO qto) {
        return goodsInfoService.pageStockAlarmGoods(qto);
    }

    @Override
    public void addGoodsInfo(PCMerchGoodsInfoDTO.AddGoodsETO eto) {
        goodsInfoTempService.addGoodsInfo(eto);
    }

    @Override
    public void updateStock(PCMerchGoodsInfoDTO.UpdateStockDTO dto) {
        goodsInfoService.updateGoodsStock(dto);
    }

    @Override
    public void deleteGoodsInfo(PCMerchGoodsInfoDTO.IdDTO dto) {
        goodsInfoService.deleteGoodsInfo(dto);

        //删除商品与扶贫之间的关联
        /*PCMerchGoodsFupinDTO.IdDTO idDTO = new PCMerchGoodsFupinDTO.IdDTO(dto.getId());
        goodsFupinService.deleteGoodsFupin(idDTO);*/
    }


    @Override
    public void editGoodsInfo(PCMerchGoodsInfoDTO.AddGoodsETO eto) {
        //直接更新temp表
        goodsInfoTempService.editGoodsInfo(eto);
    }

    @Override
    public PCMerchGoodsInfoVO.DetailVO detailGoodsInfo(PCMerchGoodsInfoDTO.EditIdsDTO dto) {
        return goodsInfoService.detailGoodsInfo(dto);
    }

    @Override
    public void groundingGoods(PCMerchGoodsInfoDTO.IdListDTO dto) {
        goodsInfoService.groundingGoods(dto);
    }

    @Override
    public void underCarriageGoods(PCMerchGoodsInfoDTO.IdListDTO dto) {
        goodsInfoService.underCarriageGoods(dto);
    }

    @Override
    public void deleteGoodsBatches(PCMerchGoodsInfoDTO.IdListDTO dto) {
        goodsInfoService.deleteGoodsBatches(dto);
    }

    @Override
    public ExportDataDTO export(PCMerchGoodsInfoQTO.IdListQTO qto) throws Exception {
        return ExcelUtil.treatmentBean(goodsInfoService.exportGoodsData(qto), PCMerchGoodsInfoVO.ExcelGoodsDataVO.class);
    }

    @Override
    public ExportDataDTO downExcelMode() throws Exception {
        return ExcelUtil.treatmentBean(goodsInfoService.downExcelModel(), PCMerchGoodsInfoVO.ImportGoodsDataVO.class);
    }

    @Override
    public PCMerchGoodsInfoVO.EditDetailVO getEditDetailEto(PCMerchGoodsInfoDTO.IdDTO dto) {
        PCMerchGoodsInfoVO.EditDetailVO editDetailVO = goodsInfoService.getEditDetailEto(dto);

        PCMerchGoodsFupinQTO.QTO qto = new PCMerchGoodsFupinQTO.QTO();
        qto.setGoodsId(dto.getId());
        PCMerchGoodsFupinVO.DetailVO detailVO = goodsFupinService.getDetailVO(qto);
        if (ObjectUtils.isNotEmpty(detailVO)) {
            editDetailVO.setFuPinDetailVO(detailVO);
        }
        return editDetailVO;
    }

    @Override
    public PCMerchGoodsInfoVO.EditDetailVO getEditDetailTempEto(PCMerchGoodsInfoDTO.IdDTO dto) {
        PCMerchGoodsInfoVO.EditDetailVO editDetailVO = goodsInfoTempService.getEditDetailEto(dto);
        return editDetailVO;
    }

    @Override
    public PageData<PCMerchGoodsInfoVO.ShopFloorCommodityVO> getShopFloorCommodityVO(PCMerchGoodsInfoQTO.ShopFloorQTO qto) {
        return goodsInfoService.getShopFloorCommodityVO(qto);
    }

    @Override
    public PageData<PCMerchGoodsInfoVO.ShopNavigationCommodityVO> pageShopNavigationCommodityVO(PCMerchGoodsInfoQTO.ShopNavigationQTO qto) {
        return goodsInfoService.pageShopNavigationCommodityVO(qto);
    }

    @Override
    public PageData<PCMerchGoodsInfoVO.GoodsActiveVO> pageGoodsActiveVO(PCMerchGoodsInfoQTO.GoodsActiveQTO qto) {
        return goodsInfoService.pageGoodsActiveVO(qto);
    }

    @Override
    public PageData<PCMerchGoodsInfoVO.SkuActivePageVO> pageSkuActivePageVO(PCMerchGoodsInfoQTO.GoodsActiveQTO qto) {
        return goodsInfoService.pageSkuActivePageVO(qto);
    }

    @Override
    public List<PCMerchGoodsInfoVO.SkuActiveVO> listSkuActiveVO(PCMerchGoodsInfoDTO.IdDTO dto) {
        return goodsInfoService.listSkuActiveVO(dto);
    }

    @Override
    public void setGoodsTemplate(PCMerchGoodsInfoDTO.SettingGoodsTemplateDTO dto) {
        goodsInfoService.setGoodsTemplate(dto);
    }

    @Override
    public void addGoodsBatch(List<PCMerchGoodsInfoDTO.ExcelGoodsDataETO> list, BaseDTO dto) {
        goodsInfoService.addGoodsBatch(list, dto);
    }

    @Override
    public List<PCMerchGoodsInfoVO.ShopCategoryGoodsVO> getShopCategoryGoodsVO(BaseDTO dto) {
        return goodsInfoService.getShopCategoryGoodsVO(dto);
    }


    @Override
    public List<PCMerchGoodsInfoVO.InnerServiceGoodsVO> innerServiceGoodsVO(PCMerchGoodsInfoDTO.IdsInnerServiceDTO dto) {
        return goodsInfoService.innerServiceGoodsVO(dto);
    }

    @Override
    public List<PCMerchGoodsInfoVO.ListVO> innerServiceSpuGoodsInfo(PCMerchGoodsInfoDTO.IdsInnerServiceDTO dto) {
        return goodsInfoService.innerServiceSpuGoodsInfo(dto);
    }

    @Override
    public List<PCMerchGoodsInfoVO.InnerServiceGoodsInfoVO> innerServiceGoodsInfo(PCMerchGoodsInfoDTO.IdsInnerServiceDTO dto) {
        return goodsInfoService.innerServiceGoodsInfo(dto);
    }

    @Override
    public List<PCMerchGoodsInfoVO.InnerServiceGoodsVO> innerServiceAllGoodsVO(PCMerchGoodsInfoDTO.IdsInnerServiceDTO dto) {
        return goodsInfoService.innerServiceAllGoodsVO(dto);
    }

    @Override
    public List<PCMerchGoodsInfoVO.ListVO> innerServiceAllSpuGoodsInfo(PCMerchGoodsInfoDTO.IdsInnerServiceDTO dto) {
        return goodsInfoService.innerServiceAllSpuGoodsInfo(dto);
    }

    @Override
    public List<PCMerchGoodsInfoVO.InnerServiceGoodsInfoVO> innerServiceAllGoodsInfo(PCMerchGoodsInfoDTO.IdsInnerServiceDTO dto) {
        return goodsInfoService.innerServiceAllGoodsInfo(dto);
    }

    @Override
    public boolean innerServiceShopNavigation(List<String> shopNavigationIds) {
        return goodsInfoService.innerServiceShopNavigation(shopNavigationIds);
    }

    @Override
    public List<PCMerchSkuGoodInfoVO.ListVO> innerServiceSkuGoodsList(List<String> skuIds) {
        return goodsInfoService.innerServiceSkuGoodsList(skuIds);
    }

    @Override
    public PCMerchGoodsInfoVO.HomeCountGoodsVO innerHomeCountGoodsVO(BaseDTO dto) {
        return goodsInfoService.innerHomeCountGoodsVO(dto);
    }

    @Override
    public PCMerchGoodsInfoVO.SkuIdByGoodsNoVO innerSkuIdByGoodsNo(String goodsNo) {
        return goodsInfoService.innerSkuIdByGoodsNo(goodsNo);
    }

    @Override
    public PCMerchGoodsInfoVO.SkuIdByGoodsNoVO innerByNoSkuId(String posSku69) {
        return goodsInfoService.innerByNoSkuId(posSku69);
    }

    @Override
    public String selectGoodsNo(String tradeGoodsId) {
        return goodsInfoService.selectGoodsNo(tradeGoodsId);
    }

    @Override
    public PCMerchMarketPtSeckillVO.SpuVO selectAllWithOutSeckill(PCMerchMarketPtSeckillQTO.AllSpuQTO qto) {
        return goodsInfoService.selectAllWithOutSeckill(qto);
    }

    @Override
    public PCMerchMarketPtSeckillVO.BrandAndCategry selectBrandAndCategory(String goodsId) {
        return goodsInfoService.selectBrandAndCategory(goodsId);
    }

    @Override
    public String selectGoodsImage(String goodsId) {
        return goodsInfoService.selectGoodsImage(goodsId);
    }

    @Override
    public Boolean deleteBatchesTemp(PCMerchGoodsInfoDTO.IdsDTO idsDTO) {
        return goodsInfoTempService.deleteBatchesTemp(idsDTO);
    }

    @Override
    public PCMerchGoodsInfoVO.GoodsStateCountVO getCountByGoodsState(PCMerchGoodsInfoDTO.MerchantDTO merchantDTO) {
        return goodsInfoService.getCountByGoodsState(merchantDTO);
    }

    @Override
    public Boolean cancelBatchesTemp(PCMerchGoodsInfoDTO.IdsDTO idsDTO) {
        return goodsInfoTempService.cancelBatchesTemp(idsDTO);
    }

    private void settingFuPin(PCMerchGoodsInfoDTO.AddGoodsETO eto, String goodsId) {
        if (ObjectUtils.isNotEmpty(eto.getFuPinEto())) {
            PCMerchGoodsFupinDTO.ETO fuPinEto = new PCMerchGoodsFupinDTO.ETO();
            BeanCopyUtils.copyProperties(eto.getFuPinEto(), fuPinEto);
            fuPinEto.setEtoList(eto.getFuPinEto().getEtoList());
            fuPinEto.setGoodsId(goodsId);
            fuPinEto.setShopId(eto.getJwtShopId());
            fuPinEto.setMerchantId(eto.getJwtMerchantId());
            goodsFupinService.saveGoodsFupin(fuPinEto);
        } else {
            PCMerchGoodsFupinDTO.IdDTO dto = new PCMerchGoodsFupinDTO.IdDTO(goodsId);
            goodsFupinService.deleteGoodsFupin(dto);
        }
    }

}