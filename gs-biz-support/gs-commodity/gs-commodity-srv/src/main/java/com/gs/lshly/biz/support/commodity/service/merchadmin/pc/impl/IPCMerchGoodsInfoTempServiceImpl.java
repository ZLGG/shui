package com.gs.lshly.biz.support.commodity.service.merchadmin.pc.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.*;
import com.gs.lshly.biz.support.commodity.repository.*;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsInfoTempService;
import com.gs.lshly.biz.support.commodity.service.platadmin.*;
import com.gs.lshly.common.enums.*;
import com.gs.lshly.common.enums.merchant.ShopStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.common.CommonStockDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.*;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.common.utils.GoodsNoUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.common.ICommonStockRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchShopRpc;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenyang
 */
@Component
public class IPCMerchGoodsInfoTempServiceImpl implements IPCMerchGoodsInfoTempService {

    @DubboReference
    private IPCMerchShopRpc shopRpc;

    @DubboReference
    private ICommonStockRpc commonStockRpc;

    @Autowired
    private IGoodsInfoTempRepository repository;

    @Autowired
    private IGoodsAttributeInfoTempService attributeInfoTempService;

    @Autowired
    private IGoodsExtendParamsTempService extendParamsTempService;

    @Autowired
    private IGoodsSpecInfoTempService goodsSpecInfoTempService;

    @Autowired
    private ISkuGoodInfoTempRepository skuGoodInfoTempRepository;

    @Autowired
    private IGoodsTempalteTempRepository goodsTempalteTempRepository;

    @Autowired
    private IGoodsShopNavigationTempRepository shopNavigationTempRepository;

    @Autowired
    private IGoodsServeCorTempRepository goodsServeCorTempRepository;

    @Override
    public void editGoodsInfo(PCMerchGoodsInfoDTO.AddGoodsETO eto) {
        //数据校验
        checkAddGoodsData(eto);

        //获取原来发布商品的传参对象
        eto.setGoodsState(GoodsStateEnum.待审核.getCode());
        GoodsInfoTemp goodsInfo = new GoodsInfoTemp();
        BeanUtils.copyProperties(eto, goodsInfo);
        goodsInfo.setGoodsNo(StringUtils.isEmpty(eto.getGoodsNo()) ? GoodsNoUtil.getGoodsNo() : eto.getGoodsNo());
        goodsInfo.setIsShowOldPrice(goodsInfo.getIsShowOldPrice().intValue() == 0 ? ShowOldPriceEnum.不显示原价.getCode() : goodsInfo.getIsShowOldPrice());
        goodsInfo.setShopId(eto.getJwtShopId());
        goodsInfo.setMerchantId(eto.getJwtMerchantId());
        boolean flag = repository.save(goodsInfo);

        if (!flag) {
            throw new BusinessException("修改商品失败");
        }

        //声明拓展id容器
        StringBuffer attributeBuffer = new StringBuffer();
        StringBuffer paramsBuffer = new StringBuffer();
        StringBuffer specBuffer = new StringBuffer();

        //如果商品属性不为空则向拓展属性表中添加数据
        if (ObjectUtils.isNotEmpty(eto.getAttributeList())) {
            for (PCMerchGoodsAttributeInfoDTO.ETO attributeInfo : eto.getAttributeList()) {
                attributeInfo.setGoodId(goodsInfo.getId());
                attributeInfo.setId("");
                String attributeId = attributeInfoTempService.addGoodsAttributeInfo(attributeInfo);

                //获取属性拓展id组
                attributeBuffer.append(attributeId + ",");
            }
        }

        //如果商品参数不为空则向拓展参数表中添加数据
        if (ObjectUtils.isNotEmpty(eto.getParamsList())) {
            for (PCMerchGoodsExtendParamsDTO.ETO paramsInfo : eto.getParamsList()) {
                paramsInfo.setGoodId(goodsInfo.getId());
                paramsInfo.setId("");
                String paramsId = extendParamsTempService.addGoodsExtendParams(paramsInfo);

                //获取参数拓展id组
                paramsBuffer.append(paramsId + ",");
            }
        }
        //商品关联拓展id
        JoinGoodsExtendIds(attributeBuffer, specBuffer, paramsBuffer, goodsInfo.getId());

        /*
         * 如果商品为多规格商品
         * 1.向拓展规格表中修改数据
         * 2.向sku商品库中修改数据
         */
        //建立商品以及sku与库存的关联
        List<CommonStockDTO.InnerChangeStockItem> items = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(eto.getSpecList())) {
            for (PCMerchGoodsSpecInfoDTO.ETO specInfo : eto.getSpecList()) {
                specInfo.setGoodId(goodsInfo.getId());
                specInfo.setId("");
                String specId = goodsSpecInfoTempService.addGoodsSpecInfo(specInfo);

                //获取规格拓展id组
                specBuffer.append(specId + ",");

            }
            List<SkuGoodInfoTemp> skuGoodInfos = new ArrayList<>();
            for (PCMerchSkuGoodInfoDTO.AddETO skuInfo : eto.getEtoList()) {
                SkuGoodInfoTemp skuGoodInfo = new SkuGoodInfoTemp();
                BeanUtils.copyProperties(skuInfo, skuGoodInfo);
                skuGoodInfo.setGoodId(goodsInfo.getId());
                skuGoodInfo.setSkuGoodsNo(StringUtils.isBlank(skuInfo.getSkuGoodsNo()) ? GoodsNoUtil.getGoodsNo() : skuInfo.getSkuGoodsNo());
                skuGoodInfo.setState(GoodsStateEnum.待审核.getCode());
                skuGoodInfo.setShopId(StringUtils.isBlank(eto.getShopId()) ? eto.getJwtShopId() : eto.getShopId());
                skuGoodInfo.setMerchantId(StringUtils.isBlank(eto.getMerchantId()) ? eto.getJwtMerchantId() : eto.getMerchantId());
                skuGoodInfo.setId("");
                skuGoodInfo.setCategoryId(eto.getCategoryId());
                skuGoodInfo.setPosSpuId(StringUtils.isBlank(eto.getPosSpuId()) ? "" : eto.getPosSpuId());
                skuGoodInfo.setIsPointGood(eto.getIsPointGood());
                skuGoodInfo.setIsInMemberGift(eto.getIsInMemberGift());
                skuGoodInfo.setMerchantId(eto.getMerchantId());
                skuGoodInfo.setShopId(eto.getJwtShopId());
                if (skuInfo.getCostPrice() != null) {
                    skuGoodInfo.setPointPrice(new BigDecimal(skuInfo.getPointPrice()));
                }

                if (skuInfo.getInMemberPointPrice() != null) {
                    skuGoodInfo.setInMemberPointPrice(new BigDecimal(skuInfo.getInMemberPointPrice()));
                }
                skuGoodInfo.setRemarks(eto.getRemarks());
                skuGoodInfos.add(skuGoodInfo);

                //添加sku商品信息
                skuGoodInfoTempRepository.save(skuGoodInfo);

                CommonStockDTO.InnerChangeStockItem stockItem = new CommonStockDTO.InnerChangeStockItem();
                stockItem.setGoodsId(goodsInfo.getId());
                stockItem.setSkuId(skuGoodInfo.getId());
                stockItem.setQuantity(skuInfo.getSkuStock() != null ? skuInfo.getSkuStock() : 0);
                items.add(stockItem);
            }

            //商品关联拓展id
            JoinGoodsExtendIds(attributeBuffer, specBuffer, paramsBuffer, goodsInfo.getId());

            //sku商品关联spec拓展id组
            JoinSkuSpecIds(specBuffer, goodsInfo.getId());
        }else {
            //商品关联拓展id
            JoinGoodsExtendIds(attributeBuffer, specBuffer, paramsBuffer, goodsInfo.getId());

            SkuGoodInfoTemp skuGoodInfo = new SkuGoodInfoTemp();
            skuGoodInfo.setGoodId(goodsInfo.getId());
            skuGoodInfo.setPosSpuId(StringUtils.isBlank(eto.getPosSpuId()) ? "" : eto.getPosSpuId());
            skuGoodInfo.setId("");
            skuGoodInfo.setCategoryId(eto.getCategoryId());
            skuGoodInfo.setPosSpuId(StringUtils.isBlank(eto.getPosSpuId()) ? "" : eto.getPosSpuId());
            skuGoodInfo.setIsPointGood(eto.getIsPointGood());
            skuGoodInfo.setIsInMemberGift(eto.getIsInMemberGift());
            skuGoodInfoTempRepository.save(skuGoodInfo);

            CommonStockDTO.InnerChangeStockItem stockItem = new CommonStockDTO.InnerChangeStockItem();
            stockItem.setGoodsId(goodsInfo.getId());
            stockItem.setSkuId(skuGoodInfo.getId());
            stockItem.setQuantity(eto.getSpuStock() != null ? eto.getSpuStock() : 0);
            items.add(stockItem);
        }

        //建立商品与运费模板的关联关系
        GoodsTempalteTemp template = new GoodsTempalteTemp();
        template.setGoodsId(goodsInfo.getId());
        template.setTemplateId(eto.getTemplateId());
        template.setStockSubtractType(eto.getStockSubtractType());
        goodsTempalteTempRepository.save(template);

        //建立商品与店铺自定义类目的关联关系
        if (StringUtils.isNotBlank(eto.getShopNavigationId())) {
            createGoodsShopNaigationBind(eto.getJwtMerchantId(), eto.getJwtShopId(), goodsInfo.getId(), eto.getShopNavigationId(), TerminalEnum.BBB.getCode());
        }

        //sku的库存先不更新的库存表去，等审核通过再更新过去
        //initGoodsStock(eto, items);

        PCMerchGoodsInfoVO.GoodsIdVO goodsIdVO = new PCMerchGoodsInfoVO.GoodsIdVO();
        goodsIdVO.setGoodsId(goodsInfo.getId());
        //添加商品与服务关联数据
        GoodsServeCorTemp goodsServeCor = new GoodsServeCorTemp();
        goodsServeCor.setGoodsId(goodsInfo.getId());
        StringBuilder sb = new StringBuilder();
        for (String goodsServeId : eto.getGoodsServeIdS()) {
            sb.append(goodsServeId + ",");
        }
        String serveIds = sb.toString();
        if (StringUtils.isNotEmpty(serveIds)) {
            goodsServeCor.setServeId(serveIds.substring(0, sb.length() - 1));
        }
        goodsServeCorTempRepository.save(goodsServeCor);

    }

    private void checkAddGoodsData(PCMerchGoodsInfoDTO.AddGoodsETO eto) {
        Integer shopState = shopRpc.innerShopState(eto.getShopId());
        if (ObjectUtils.isEmpty(shopState) || shopState.equals(ShopStateEnum.关闭状态.getCode())) {
            throw new BusinessException("店铺未开通！！");
        }
        if (eto == null) {
            throw new BusinessException("参数不能为空");
        }
        if (StringUtils.isBlank(eto.getCategoryId())) {
            throw new BusinessException("请选择分类");
        }
        if (StringUtils.isBlank(eto.getGoodsName())) {
            throw new BusinessException("请填写商品标题");
        }
        if (StringUtils.isBlank(eto.getGoodsTitle())) {
            throw new BusinessException("请填写商品简介");
        }
        if (StringUtils.isBlank(eto.getCategoryId())) {
            throw new BusinessException("请选择商品平台分类");
        }
        /*if (ObjectUtils.isEmpty(eto.getUsePlatform())) {
            throw new BusinessException("请选择商品发布的平台");
        }*/
        if (StringUtils.isBlank(eto.getBrandId())) {
            throw new BusinessException("请选择品牌，若没有品牌请先去绑定品牌");
        }
        if (ObjectUtils.isEmpty(eto.getStockSubtractType())) {
            throw new BusinessException("请选择商品的库存计数方式");
        }
        if (ObjectUtils.isEmpty(eto.getGoodsValidDays())) {
            throw new BusinessException("请填写有限期天数");
        }
        if (ObjectUtils.isEmpty(eto.getIsSingle())) {
            throw new BusinessException("请选择商品是单规格还是多规格");
        }
        if (ObjectUtils.isEmpty(eto.getGoodsWeight())) {
            throw new BusinessException("请填写商品的重量");
        }
        if (ObjectUtils.isEmpty(eto.getSalePrice())) {
            throw new BusinessException("请填写商品的标准售价！");
        }
        if (ObjectUtils.isEmpty(eto.getOldPrice())) {
            throw new BusinessException("请填写商品的原价");
        }
        if (ObjectUtils.isEmpty(eto.getCostPrice())) {
            throw new BusinessException("请填写商品的成本价");
        }
        if (eto.getIsSingle().equals(SingleStateEnum.多规格.getCode()) && ObjectUtils.isEmpty(eto.getSpecList())) {
            throw new BusinessException("多规格商品请选择规格！");
        }
        if (eto.getIsSingle().equals(SingleStateEnum.多规格.getCode()) && ObjectUtils.isEmpty(eto.getEtoList())) {
            throw new BusinessException("多规格商品请填写sku商品信息");
        }
        if (eto.getIsSingle().equals(SingleStateEnum.多规格.getCode()) && ObjectUtils.isNotEmpty(eto.getEtoList())) {
            for (PCMerchSkuGoodInfoDTO.AddETO addETO : eto.getEtoList()) {
                if (ObjectUtils.isEmpty(addETO.getSalePrice())) {
                    throw new BusinessException("请填写sku商品的标准售价！");
                }
                if (ObjectUtils.isEmpty(addETO.getOldPrice())) {
                    throw new BusinessException("请填写sku商品的原价");
                }
                if (StringUtils.isNotEmpty(addETO.getSkuGoodsNo()) && StringUtils.isBlank(eto.getId())) {
                    if (this.count(addETO.getSkuGoodsNo()) > 0) {
                        throw new BusinessException("该sku商品货号" + eto.getGoodsNo() + "已存在");
                    }
                }
                if (StringUtils.isBlank(addETO.getImage())) {
                    throw new BusinessException("请上传该规格商品的图片");
                }
            }
        }
        if (StringUtils.isEmpty(eto.getTemplateId())) {
            throw new BusinessException("请选择运费模板");
        }
        if (StringUtils.isEmpty(eto.getShopNavigationId())) {
            throw new BusinessException("请选择店铺商品分类");
        }
        if (ObjectUtil.isEmpty(eto.getCtccMold())) {
            throw new BusinessException("请选择商品类型");
        }
    }

    private int count(String skuGoodsNo) {
        QueryWrapper<SkuGoodInfoTemp> wrapper = MybatisPlusUtil.query();
        wrapper.eq("sku_goods_no", skuGoodsNo);
        int count = skuGoodInfoTempRepository.count(wrapper);
        return count;
    }

    private void JoinGoodsExtendIds(StringBuffer attributeBuffer, StringBuffer specBuffer, StringBuffer
            paramsBuffer, String id) {

        String attributeIds = "";
        String specIds = "";
        String paramsIds = "";
        if (StringUtils.isNotEmpty(attributeBuffer.toString())) {
            attributeIds = attributeBuffer.toString().substring(0, attributeBuffer.toString().lastIndexOf(","));
        }
        if (StringUtils.isNotEmpty(specBuffer.toString())) {
            specIds = specBuffer.toString().substring(0, specBuffer.toString().lastIndexOf(","));
        }
        if (StringUtils.isNotEmpty(paramsBuffer.toString())) {
            paramsIds = paramsBuffer.toString().substring(0, paramsBuffer.toString().lastIndexOf(","));
        }
        GoodsInfoTemp info = new GoodsInfoTemp();
        info.setId(id);
        info.setAttributeInfoId(StringUtils.isEmpty(attributeIds) ? "" : attributeIds);
        info.setSpecInfoId(StringUtils.isEmpty(specIds) ? "" : specIds);
        info.setExtendParamsId(StringUtils.isEmpty(paramsIds) ? "" : paramsIds);

        repository.updateById(info);
    }

    private void JoinSkuSpecIds(StringBuffer specBuffer, String id) {
        String specIds = "";
        if (StringUtils.isNotEmpty(specBuffer.toString())) {
            specIds = specBuffer.toString().substring(0, specBuffer.toString().lastIndexOf(","));
        }
        SkuGoodInfoTemp skuGoodInfo = new SkuGoodInfoTemp();
        skuGoodInfo.setSpecsKey(specIds);
        QueryWrapper<SkuGoodInfoTemp> wrapper = new QueryWrapper<>();
        wrapper.eq("good_id", id);
        skuGoodInfoTempRepository.update(skuGoodInfo, wrapper);
    }

    private void createGoodsShopNaigationBind(String merchantId, String shopId, String goodsId, String
            navigationId, Integer terminal) {
        GoodsShopNavigationTemp shopNavigation = new GoodsShopNavigationTemp();
        shopNavigation.setMerchantId(merchantId);
        shopNavigation.setShopId(shopId);
        shopNavigation.setGoodsId(goodsId);
        shopNavigation.setShopNavigation(navigationId);
        shopNavigation.setTerminal(terminal);
        shopNavigationTempRepository.save(shopNavigation);
    }

    private void initGoodsStock(PCMerchGoodsInfoDTO.AddGoodsETO
                                        eto, List<CommonStockDTO.InnerChangeStockItem> items) {
        CommonStockDTO.InnerChangeStockDTO dto = new CommonStockDTO.InnerChangeStockDTO();
        dto.setShopId(StringUtils.isEmpty(eto.getShopId()) ? eto.getJwtShopId() : eto.getShopId());
        dto.setMerchantId(StringUtils.isEmpty(eto.getMerchantId()) ? eto.getJwtMerchantId() : eto.getMerchantId());
        dto.setLocation(StockLocationEnum.初始化.getCode());
        dto.setDataFromType(StockDataFromTypeEnum.商家运维.getCode());
        dto.setGoodsItemList(ObjectUtils.isEmpty(items) ? new ArrayList<>() : items);
        commonStockRpc.innerChangeStock(dto);
    }
}
