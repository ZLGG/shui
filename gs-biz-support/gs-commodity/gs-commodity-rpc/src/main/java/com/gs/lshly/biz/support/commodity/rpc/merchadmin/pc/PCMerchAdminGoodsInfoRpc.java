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

import static java.util.stream.Collectors.toList;

/**
*
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
    public PageData<PCMerchGoodsInfoVO.SpuListVO> pageData(PCMerchGoodsInfoQTO.GoodsInfoParamsQTO qto){
        return goodsInfoService.pageGoodsData(qto);
    }

    @Override
    public PageData<PCMerchGoodsInfoVO.StockAlarmGoodsVO> pageStockAlarmGoods(PCMerchGoodsInfoQTO.GoodsInfoParamsQTO qto) {
        return goodsInfoService.pageStockAlarmGoods(qto);
    }

    @Override
    public void addGoodsInfo(PCMerchGoodsInfoDTO.AddGoodsETO eto){
       PCMerchGoodsInfoVO.GoodsIdVO goodsIdVO = goodsInfoService.addGoodsInfo(eto);

        //如果该商品是扶贫商品

        settingFuPin(eto,goodsIdVO.getGoodsId());
    }

    @Override
    public void deleteGoodsInfo(PCMerchGoodsInfoDTO.IdDTO dto){
        goodsInfoService.deleteGoodsInfo(dto);

        //删除商品与扶贫之间的关联
        PCMerchGoodsFupinDTO.IdDTO idDTO = new PCMerchGoodsFupinDTO.IdDTO(dto.getId());
        goodsFupinService.deleteGoodsFupin(idDTO);
    }


    @Override
    public void editGoodsInfo(PCMerchGoodsInfoDTO.AddGoodsETO eto){

        //用户编辑商品，需要先审核，若只是修改库存则直接修改
        //包含非库存字段修改
        if(ObjectUtils.isNotEmpty(eto) && ObjectUtils.isNotEmpty(eto.getIsIncludeOthers()) && eto.getIsIncludeOthers()){
            goodsInfoService.updateGoodsStock(eto);
        }else{
            goodsInfoTempService.editGoodsInfo(eto);
        }

        //goodsInfoService.editGoodsInfo(eto);

        //如果该商品是扶贫商品
        //settingFuPin(eto,eto.getId());

    }

    @Override
    public PCMerchGoodsInfoVO.DetailVO detailGoodsInfo(PCMerchGoodsInfoDTO.EditIdsDTO dto){
        return goodsInfoService.detailGoodsInfo(dto);
    }

    @Override
    public void groundingGoods(PCMerchGoodsInfoDTO.IdListDTO dto) {
        //如果这些商品为扶贫商品，平台方必须审核
        List<String> fupinGoodsIdList = goodsFupinService.listFuPinGoodsId(new BaseQTO());
        if (ObjectUtils.isNotEmpty(fupinGoodsIdList)){
            List<String> initGoodsIdList = dto.getIdList();
            List<String> list=dto.getIdList().stream().filter(t->fupinGoodsIdList.contains(t))
                    .collect(Collectors.toList());
            if (ObjectUtils.isNotEmpty(list)){
                dto.setFuPinGoodsIdList(list);
                dto.setIdList(list);
                goodsInfoService.groundingGoods(dto);
                //其他商品按正常流程上架
                List<String> intersection =initGoodsIdList.stream().filter(t-> !list.contains(t))
                        .collect(Collectors.toList());
                if (ObjectUtils.isNotEmpty(intersection)){
                    dto.setIdList(intersection);
                    dto.setFuPinGoodsIdList(new ArrayList<>());
                    goodsInfoService.groundingGoods(dto);
                }
            }else {
                goodsInfoService.groundingGoods(dto);
            }
        }else {
            goodsInfoService.groundingGoods(dto);
        }
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
        return ExcelUtil.treatmentBean(goodsInfoService.downExcelModel(),PCMerchGoodsInfoVO.ImportGoodsDataVO.class);
    }

    @Override
    public PCMerchGoodsInfoVO.EditDetailVO getEditDetailEto(PCMerchGoodsInfoDTO.IdDTO dto) {
        PCMerchGoodsInfoVO.EditDetailVO editDetailVO = goodsInfoService.getEditDetailEto(dto);

        PCMerchGoodsFupinQTO.QTO qto = new PCMerchGoodsFupinQTO.QTO();
        qto.setGoodsId(dto.getId());
        PCMerchGoodsFupinVO.DetailVO detailVO = goodsFupinService.getDetailVO(qto);
        if (ObjectUtils.isNotEmpty(detailVO)){
            editDetailVO.setFuPinDetailVO(detailVO);
        }
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
       goodsInfoService.addGoodsBatch(list,dto);
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


    private void settingFuPin(PCMerchGoodsInfoDTO.AddGoodsETO eto,String goodsId){
        if (ObjectUtils.isNotEmpty(eto.getFuPinEto())){
            PCMerchGoodsFupinDTO.ETO fuPinEto = new PCMerchGoodsFupinDTO.ETO();
            BeanCopyUtils.copyProperties(eto.getFuPinEto(),fuPinEto);
            fuPinEto.setEtoList(eto.getFuPinEto().getEtoList());
            fuPinEto.setGoodsId(goodsId);
            fuPinEto.setShopId(eto.getJwtShopId());
            fuPinEto.setMerchantId(eto.getJwtMerchantId());
            goodsFupinService.saveGoodsFupin(fuPinEto);
        }else {
            PCMerchGoodsFupinDTO.IdDTO dto = new PCMerchGoodsFupinDTO.IdDTO(goodsId);
            goodsFupinService.deleteGoodsFupin(dto);
        }
    }

}