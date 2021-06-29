package com.gs.lshly.biz.support.commodity.service.merchadmin.pc.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsAttributeInfoTemp;
import com.gs.lshly.biz.support.commodity.entity.GoodsExtendParamsTemp;
import com.gs.lshly.biz.support.commodity.entity.GoodsInfoTemp;
import com.gs.lshly.biz.support.commodity.entity.GoodsServeCorTemp;
import com.gs.lshly.biz.support.commodity.entity.GoodsShopNavigationTemp;
import com.gs.lshly.biz.support.commodity.entity.GoodsSpecInfoTemp;
import com.gs.lshly.biz.support.commodity.entity.GoodsTempalteTemp;
import com.gs.lshly.biz.support.commodity.entity.SkuGoodInfoTemp;
import com.gs.lshly.biz.support.commodity.repository.IGoodsAttributeInfoTempRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsExtendParamsTempRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsInfoTempRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsServeCorTempRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsShopNavigationTempRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsSpecInfoTempRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsTempalteTempRepository;
import com.gs.lshly.biz.support.commodity.repository.ISkuGoodInfoTempRepository;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsInfoTempService;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchSkuGoodInfoTempService;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsAttributeInfoTempService;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsExtendParamsTempService;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsSpecInfoTempService;
import com.gs.lshly.common.enums.GoodsStateEnum;
import com.gs.lshly.common.enums.ShowOldPriceEnum;
import com.gs.lshly.common.enums.SingleStateEnum;
import com.gs.lshly.common.enums.StockDataFromTypeEnum;
import com.gs.lshly.common.enums.StockLocationEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.enums.merchant.ShopStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.CommonStockDTO;
import com.gs.lshly.common.struct.common.CommonStockVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsAttributeInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsExtendParamsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsServeDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsSpecInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchSkuGoodInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsAttributeInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsExtendParamsVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsSpecInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchSkuGoodInfoVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.GoodsNoUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.common.ICommonStockRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchGoodsServeRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchShopRpc;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chenyang
 */
@Component
@Slf4j
public class PCMerchGoodsInfoTempServiceImpl implements IPCMerchGoodsInfoTempService {

    @DubboReference
    private IPCMerchShopRpc shopRpc;

    @DubboReference
    private ICommonStockRpc commonStockRpc;

    @DubboReference
    private IPCMerchGoodsServeRpc goodsServeRpc;

    @Autowired
    private IGoodsInfoTempRepository repository;

    @Autowired
    private IGoodsAttributeInfoTempService attributeInfoTempService;

    @Autowired
    private IGoodsExtendParamsTempService extendParamsTempService;

    @Autowired
    private IGoodsSpecInfoTempService goodsSpecInfoTempService;

    @Autowired
    private IPCMerchSkuGoodInfoTempService skuGoodInfoTempService;

    @Autowired
    private ISkuGoodInfoTempRepository skuGoodInfoTempRepository;

    @Autowired
    private IGoodsTempalteTempRepository goodsTempalteTempRepository;

    @Autowired
    private IGoodsShopNavigationTempRepository shopNavigationTempRepository;

    @Autowired
    private IGoodsServeCorTempRepository goodsServeCorTempRepository;

    @Autowired
    private IGoodsAttributeInfoTempRepository attributeInfoTempRepository;

    @Autowired
    private IGoodsSpecInfoTempRepository goodsSpecInfoTempRepository;

    @Autowired
    private IGoodsExtendParamsTempRepository extendParamsTempRepository;

    @Override
    public void editGoodsInfo(PCMerchGoodsInfoDTO.AddGoodsETO eto) {
        //数据校验
        checkAddGoodsData(eto);
        eto.setShopId(eto.getJwtShopId());
        String goodId = eto.getId();

        //获取原来发布商品的传参对象
        //PCMerchGoodsInfoVO.EditDetailVO editDetailVO = getEditDetailEto(new PCMerchGoodsInfoDTO.IdDTO(goodId));

        GoodsInfoTemp oldGoodsInfoTemp = repository.getById(goodId);
        if(ObjectUtil.isEmpty(oldGoodsInfoTemp)){
            //temp表新增
            addGoodsInfo(eto,2);
            return;
        }

        GoodsInfoTemp goodsInfoTemp = new GoodsInfoTemp();
        BeanUtils.copyProperties(eto, goodsInfoTemp);
        goodsInfoTemp.setId(goodId);
        goodsInfoTemp.setGoodsState(eto.getGoodsState()!=null?eto.getGoodsState():GoodsStateEnum.待审核.getCode());
        //表更新商品
        goodsInfoTemp.setApplyType(2);
        goodsInfoTemp.setCdate(new Date());
        UpdateWrapper<GoodsInfoTemp> goodsBoost = MybatisPlusUtil.update();
        goodsBoost.eq("id", oldGoodsInfoTemp.getId());
        boolean flag = repository.update(goodsInfoTemp, goodsBoost);

        if (!flag) {
            throw new BusinessException("修改商品失败");
        }

        //声明拓展id容器
        StringBuffer attributeBuffer = new StringBuffer();
        StringBuffer paramsBuffer = new StringBuffer();
        StringBuffer specBuffer = new StringBuffer();

        //如果商品属性不为空则向拓展属性表中添加数据
        if (ObjectUtils.isNotEmpty(eto.getAttributeList())) {
            //先删除商品属性
            Map<String, Object> columnMap = new HashMap<>();
            columnMap.put("good_id", goodId);
            attributeInfoTempRepository.removeByMap(columnMap);

            PCMerchGoodsAttributeInfoDTO.ETO attributeInfo;
            for (PCMerchGoodsAttributeInfoDTO.ETO attributeInfoListVO : eto.getAttributeList()) {
                attributeInfo = new PCMerchGoodsAttributeInfoDTO.ETO();
                BeanCopyUtils.copyProperties(attributeInfoListVO, attributeInfo);
                attributeInfo.setGoodId(goodsInfoTemp.getId());
                attributeInfo.setId("");
                String attributeId = attributeInfoTempService.addGoodsAttributeInfo(attributeInfo);

                //获取属性拓展id组
                attributeBuffer.append(attributeId + ",");
            }
        }

        //如果商品参数不为空则向拓展参数表中添加数据
        if (ObjectUtils.isNotEmpty(eto.getParamsList())) {
            //先删除商品参数
            Map<String, Object> columnMap = new HashMap<>();
            columnMap.put("good_id", goodId);
            extendParamsTempRepository.removeByMap(columnMap);

            PCMerchGoodsExtendParamsDTO.ETO paramsInfo;
            for (PCMerchGoodsExtendParamsDTO.ETO paramsInfoListVO : eto.getParamsList()) {
                paramsInfo = new PCMerchGoodsExtendParamsDTO.ETO();
                BeanCopyUtils.copyProperties(paramsInfoListVO, paramsInfo);
                paramsInfo.setGoodId(goodsInfoTemp.getId());
                paramsInfo.setId("");
                String paramsId = extendParamsTempService.addGoodsExtendParams(paramsInfo);

                //获取参数拓展id组
                paramsBuffer.append(paramsId + ",");
            }
        }
        //商品关联拓展id
        JoinGoodsExtendIds(attributeBuffer, specBuffer, paramsBuffer, goodsInfoTemp.getId());

        /*
         * 如果商品为多规格商品
         * 1.向拓展规格表中修改数据
         * 2.向sku商品库中修改数据
         */
        //建立商品以及sku与库存的关联
        List<CommonStockDTO.InnerChangeStockItem> items = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(eto.getSpecList())) {

            //先删除商品规格拓展
            Map<String, Object> columnMap = new HashMap<>();
            columnMap.put("good_id", goodId);
            goodsSpecInfoTempRepository.removeByMap(columnMap);

            PCMerchGoodsSpecInfoDTO.ETO specInfo;
            for (PCMerchGoodsSpecInfoDTO.ETO specInfoListVO : eto.getSpecList()) {
                specInfo = new PCMerchGoodsSpecInfoDTO.ETO();
                BeanCopyUtils.copyProperties(specInfoListVO, specInfo);
                specInfo.setGoodId(goodsInfoTemp.getId());
                specInfo.setId("");
                String specId = goodsSpecInfoTempService.addGoodsSpecInfo(specInfo);

                //获取规格拓展id组
                specBuffer.append(specId + ",");

            }

            //先删除商品sku
            columnMap = new HashMap<>();
            columnMap.put("good_id", goodId);
            skuGoodInfoTempRepository.removeByMap(columnMap);

            List<SkuGoodInfoTemp> skuGoodInfos = new ArrayList<>();
            SkuGoodInfoTemp skuGoodInfo;
            for (PCMerchSkuGoodInfoDTO.AddETO skuInfo : eto.getEtoList()) {
                skuGoodInfo = new SkuGoodInfoTemp();
                BeanUtils.copyProperties(skuInfo, skuGoodInfo);
                skuGoodInfo.setGoodId(goodId);
                skuGoodInfo.setSkuGoodsNo(StringUtils.isBlank(skuInfo.getSkuGoodsNo()) ? GoodsNoUtil.getGoodsNo() : skuInfo.getSkuGoodsNo());
                skuGoodInfo.setState(GoodsStateEnum.待审核.getCode());
                skuGoodInfo.setId("");
                skuGoodInfo.setCategoryId(eto.getCategoryId());
                skuGoodInfo.setPosSpuId(StringUtils.isBlank(eto.getPosSpuId()) ? "" : eto.getPosSpuId());
                skuGoodInfo.setIsPointGood(eto.getIsPointGood());
                skuGoodInfo.setIsInMemberGift(eto.getIsInMemberGift());
                skuGoodInfo.setMerchantId(eto.getMerchantId());
                skuGoodInfo.setShopId(eto.getJwtShopId());
                if (skuInfo.getPointPrice() != null) {
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
                stockItem.setGoodsId(goodId);
                stockItem.setSkuId(skuGoodInfo.getId());
                stockItem.setQuantity(skuInfo.getSkuStock() != null ? skuInfo.getSkuStock() : 0);
                items.add(stockItem);
            }

            //商品关联拓展id
            JoinGoodsExtendIds(attributeBuffer, specBuffer, paramsBuffer, goodId);

            //sku商品关联spec拓展id组
            JoinSkuSpecIds(specBuffer, goodId);
        } else {
            //商品关联拓展id
            JoinGoodsExtendIds(attributeBuffer, specBuffer, paramsBuffer, goodId);

            //先删除商品sku
            Map<String, Object> columnMap = new HashMap<>();
            columnMap.put("good_id", goodId);
            skuGoodInfoTempRepository.removeByMap(columnMap);

            SkuGoodInfoTemp skuGoodInfo = new SkuGoodInfoTemp();
            skuGoodInfo.setGoodId(goodId);
            skuGoodInfo.setPosSpuId(StringUtils.isBlank(eto.getPosSpuId()) ? "" : eto.getPosSpuId());
            skuGoodInfo.setId("");
            skuGoodInfo.setCategoryId(eto.getCategoryId());
            skuGoodInfo.setPosSpuId(StringUtils.isBlank(eto.getPosSpuId()) ? "" : eto.getPosSpuId());
            skuGoodInfo.setIsPointGood(eto.getIsPointGood());
            skuGoodInfo.setIsInMemberGift(eto.getIsInMemberGift());
            skuGoodInfoTempRepository.save(skuGoodInfo);

            CommonStockDTO.InnerChangeStockItem stockItem = new CommonStockDTO.InnerChangeStockItem();
            stockItem.setGoodsId(goodId);
            stockItem.setSkuId(skuGoodInfo.getId());
            stockItem.setQuantity(eto.getSpuStock() != null ? eto.getSpuStock() : 0);
            items.add(stockItem);
        }

        //建立商品与运费模板的关联关系

        Map columnMap = new HashMap<>();
        columnMap.put("goods_id", goodId);
        goodsTempalteTempRepository.removeByMap(columnMap);

        GoodsTempalteTemp template = new GoodsTempalteTemp();
        template.setGoodsId(goodId);
        template.setTemplateId(eto.getTemplateId());
        template.setStockSubtractType(eto.getStockSubtractType());
        goodsTempalteTempRepository.save(template);

        //建立商品与店铺自定义类目的关联关系
        QueryWrapper<GoodsShopNavigationTemp> wrapper = MybatisPlusUtil.query();
        wrapper.eq("goods_id", goodId);
        shopNavigationTempRepository.remove(wrapper);
        if (StringUtils.isNotBlank(eto.getShopNavigationId())) {
            createGoodsShopNaigationBind(eto.getJwtMerchantId(), eto.getJwtShopId(), goodId, eto.getShopNavigationId(), TerminalEnum.BBB.getCode());
        }

        //sku的库存先不更新的库存表去，等审核通过再更新过去
        initGoodsStock(eto, items);

        columnMap = new HashMap<>();
        columnMap.put("goods_id", goodId);
        goodsServeCorTempRepository.removeByMap(columnMap);

        PCMerchGoodsInfoVO.GoodsIdVO goodsIdVO = new PCMerchGoodsInfoVO.GoodsIdVO();
        goodsIdVO.setGoodsId(goodId);
        //添加商品与服务关联数据
        GoodsServeCorTemp goodsServeCor = new GoodsServeCorTemp();
        goodsServeCor.setGoodsId(goodId);
        StringBuilder sb = new StringBuilder();
        if(CollectionUtil.isNotEmpty(eto.getGoodsServeIdS())){
	        for (String goodsServeId : eto.getGoodsServeIdS()) {
	            sb.append(goodsServeId + ",");
	        }
        }
        String serveIds = sb.toString();
        if (StringUtils.isNotEmpty(serveIds)) {
            goodsServeCor.setServeId(serveIds.substring(0, sb.length() - 1));
        }
        goodsServeCorTempRepository.save(goodsServeCor);

    }

    @Override
    public PCMerchGoodsInfoVO.EditDetailVO getEditDetailEto(PCMerchGoodsInfoDTO.IdDTO dto) {
        if (dto == null) {
            throw new BusinessException("参数不能为空！");
        }
        GoodsInfoTemp goodsInfoTemp = repository.getById(dto.getId());
        if (goodsInfoTemp == null) {
            throw new BusinessException("查询数据异常");
        }
        PCMerchGoodsInfoVO.EditDetailVO detailVO = new PCMerchGoodsInfoVO.EditDetailVO();
        //获取商品信息
        BeanUtils.copyProperties(goodsInfoTemp, detailVO);

        //sku商品列表
        PCMerchSkuGoodInfoDTO.GoodIdDTO goodId = new PCMerchSkuGoodInfoDTO.GoodIdDTO();
        goodId.setGoodId(goodsInfoTemp.getId());
        goodId.setJwtShopId(goodsInfoTemp.getShopId());
        List<PCMerchSkuGoodInfoVO.DetailVO> skuList = skuGoodInfoTempService.getByGoodsId(goodId);
        if (ObjectUtils.isNotEmpty(skuList) && StringUtils.isNotEmpty(skuList.get(0).getSpecsKey())) {
            for (PCMerchSkuGoodInfoVO.DetailVO vo : skuList) {
                vo.setSkuStock(getSkuStockNum(dto.getJwtShopId(),vo.getId()));
            }
            detailVO.setSkuVoList(skuList);
        }
        //商品拓展属性列表
        PCMerchGoodsAttributeInfoDTO.GoodIdDTO goodIdDTO = new PCMerchGoodsAttributeInfoDTO.GoodIdDTO(goodId.getGoodId());
        List<PCMerchGoodsAttributeInfoVO.ListVO> attributeList = attributeInfoTempService.getListVO(goodIdDTO);
        if (ObjectUtils.isNotEmpty(attributeList)) {
            detailVO.setAttributeList(attributeList);
        }
        //商品拓展规格列表
        PCMerchGoodsSpecInfoDTO.GoodIdDTO dto1 = new PCMerchGoodsSpecInfoDTO.GoodIdDTO(goodsInfoTemp.getId());
        List<PCMerchGoodsSpecInfoVO.ListVO> specList = goodsSpecInfoTempService.specInfoListVO(dto1);
        if (ObjectUtils.isNotEmpty(specList)) {
            detailVO.setSpecList(specList);
        }

        //商品拓展参数列表
        PCMerchGoodsExtendParamsDTO.GoodIdDTO goodIdDTO1 = new PCMerchGoodsExtendParamsDTO.GoodIdDTO(goodsInfoTemp.getId());
        List<PCMerchGoodsExtendParamsVO.ListVO> extentParamList = extendParamsTempService.extendListVO(goodIdDTO1);
        if (ObjectUtils.isNotEmpty(extentParamList)) {
            detailVO.setParamsList(extentParamList);
        }
        //运费模板ID
        QueryWrapper<GoodsTempalteTemp> tempalteBoost = MybatisPlusUtil.query();
        tempalteBoost.eq("goods_id", goodsInfoTemp.getId());
        GoodsTempalteTemp tempalte = goodsTempalteTempRepository.getOne(tempalteBoost);
        if (tempalte == null) {
            throw new BusinessException("查询异常");
        }
        detailVO.setTemplateId(tempalte.getTemplateId());

        //库存计数方式
        if (ObjectUtils.isNotEmpty(tempalte.getStockSubtractType())) {
            detailVO.setStockChargeWay(tempalte.getStockSubtractType());
        }

        //店铺自定义类目id
        QueryWrapper<GoodsShopNavigationTemp> navigationBoost = MybatisPlusUtil.query();
        navigationBoost.eq("goods_id", goodsInfoTemp.getId());
        List<GoodsShopNavigationTemp> navigations = shopNavigationTempRepository.list(navigationBoost);
        if (ObjectUtils.isNotEmpty(navigations)) {
            for (GoodsShopNavigationTemp shopNavigation : navigations) {
                if (ObjectUtils.isNotEmpty(shopNavigation.getTerminal()) && shopNavigation.getTerminal().equals(TerminalEnum.BBB.getCode())) {
                    detailVO.setShopNavigationId(shopNavigation.getShopNavigation());
                }
                if (ObjectUtils.isNotEmpty(shopNavigation.getTerminal()) && shopNavigation.getTerminal().equals(TerminalEnum.BBC.getCode())) {
                    detailVO.setShop2cNavigationId(shopNavigation.getShopNavigation());
                }
            }
        }

        // spu库存
        detailVO.setSpuStock(getSpuStockNum(goodsInfoTemp.getId(), goodsInfoTemp.getShopId()));
        PCMerchGoodsServeDTO.IdDTO idDTO = new PCMerchGoodsServeDTO.IdDTO(dto.getId());
        List<String> serveListVO = goodsServeRpc.getServeTempIdByGoodsId(idDTO);
        if (CollUtil.isNotEmpty(serveListVO)) {
            detailVO.setGoodsServeList(serveListVO);
        }
        return detailVO;
    }

    @Override
    public Boolean isUpdateGoodInfo(String goodId) {
        GoodsInfoTemp goodsInfoTemp = repository.getById(goodId);
        if (ObjectUtils.isNotEmpty(goodsInfoTemp) && goodsInfoTemp.getApplyType().intValue() == 2) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public PCMerchGoodsInfoVO.GoodsIdVO addGoodsInfo(PCMerchGoodsInfoDTO.AddGoodsETO eto,Integer applyType) {
        //数据校验
        checkAddGoodsData(eto);
        //判断编号的唯一性
        if (StringUtils.isNotEmpty(eto.getGoodsNo())) {
            PCMerchGoodsInfoDTO.GoodNoDTO dto = new PCMerchGoodsInfoDTO.GoodNoDTO();
            dto.setGoodsNo(eto.getGoodsNo());
            if (this.countGoodsNo(dto) > 0) {
                throw new BusinessException("商品货号" + eto.getGoodsNo() + "已存在");
            }
        }
        GoodsInfoTemp goodsInfo = new GoodsInfoTemp();
        BeanUtils.copyProperties(eto, goodsInfo);
        goodsInfo.setGoodsNo(eto.getGoodsNo());
        goodsInfo.setIsShowOldPrice(ObjectUtils.isEmpty(goodsInfo.getIsShowOldPrice()) ? ShowOldPriceEnum.不显示原价.getCode() : goodsInfo.getIsShowOldPrice());
        goodsInfo.setShopId(StringUtils.isBlank(eto.getShopId()) ? eto.getJwtShopId() : eto.getShopId());
        goodsInfo.setMerchantId(StringUtils.isBlank(eto.getMerchantId()) ? eto.getJwtMerchantId() : eto.getMerchantId());
        //goodsInfo.setGoodsState(GoodsStateEnum.待审核.getCode());
        goodsInfo.setApplyType(applyType);
        goodsInfo.setFlag(false);
        goodsInfo.setCdate(new Date());
        goodsInfo.setUdate(new Date());
        goodsInfo.setMerchantId(eto.getJwtMerchantId());
        goodsInfo.setShopId(eto.getJwtShopId());
        switch (eto.getCtccMold()) {
            case 20:
                goodsInfo.setIsPointGood(true);
                goodsInfo.setIsInMemberGift(false);
                break;
            case 30:
                goodsInfo.setIsPointGood(false);
                goodsInfo.setIsInMemberGift(true);
                break;
            default:
                goodsInfo.setIsPointGood(false);
                goodsInfo.setIsInMemberGift(false);
                break;
        }

        boolean flag = repository.save(goodsInfo);
        if (!flag) {
            throw new BusinessException("添加商品失败");
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
         * 1.向拓展规格表中添加数据
         * 2.向sku商品库中添加数据
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
                skuGoodInfo.setShopId(eto.getJwtShopId());
                skuGoodInfo.setMerchantId(eto.getJwtMerchantId());
                skuGoodInfo.setId("");
                skuGoodInfo.setCategoryId(eto.getCategoryId());
                skuGoodInfo.setPosSpuId(StringUtils.isBlank(eto.getPosSpuId()) ? "" : eto.getPosSpuId());
                skuGoodInfo.setIsPointGood(eto.getIsPointGood());
                skuGoodInfo.setIsInMemberGift(eto.getIsInMemberGift());
                skuGoodInfo.setCdate(new Date());
                skuGoodInfo.setUdate(new Date());
                skuGoodInfo.setFlag(false);
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

        } else {
            //商品关联拓展id
            JoinGoodsExtendIds(attributeBuffer, specBuffer, paramsBuffer, goodsInfo.getId());

            SkuGoodInfoTemp skuGoodInfo = new SkuGoodInfoTemp();

            skuGoodInfo.setGoodId(goodsInfo.getId());
            skuGoodInfo.setPosSpuId(StringUtils.isBlank(eto.getPosSpuId()) ? "" : eto.getPosSpuId());
            skuGoodInfo.setId("");
            skuGoodInfo.setCategoryId(eto.getCategoryId());
            skuGoodInfo.setIsPointGood(eto.getIsPointGood());
            skuGoodInfo.setIsInMemberGift(eto.getIsInMemberGift());
            skuGoodInfo.setState(GoodsStateEnum.待审核.getCode());
            skuGoodInfo.setMerchantId(eto.getJwtMerchantId());
            skuGoodInfo.setShopId(eto.getJwtShopId());
            skuGoodInfo.setMerchantId(eto.getJwtMerchantId());
            skuGoodInfo.setSkuGoodsNo(eto.getGoodsNo());
            skuGoodInfo.setCostPrice(eto.getCostPrice());
            skuGoodInfo.setCtccMold(eto.getCtccMold());
            skuGoodInfo.setOldPrice(eto.getOldPrice());
            skuGoodInfo.setOldPointPrice(eto.getOldPointPrice());
            skuGoodInfo.setSalePrice(eto.getSalePrice());
            skuGoodInfo.setSettlementPrice(eto.getSettlementPrice());
            skuGoodInfo.setCdate(new Date());
            skuGoodInfo.setUdate(new Date());
            skuGoodInfo.setFlag(false);
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
        if (StringUtils.isNotBlank(eto.getShop2cNavigationId())) {
            createGoodsShopNaigationBind(eto.getJwtMerchantId(), eto.getJwtShopId(), goodsInfo.getId(), eto.getShop2cNavigationId(), TerminalEnum.BBC.getCode());
        }

        initGoodsStock(eto, items);

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
        return goodsIdVO;
    }

    @Override
    public int countGoodsNo(PCMerchGoodsInfoDTO.GoodNoDTO dto) {
        QueryWrapper<GoodsInfoTemp> queryWrapperBoost = MybatisPlusUtil.query();
        queryWrapperBoost.eq("goods_no", dto.getGoodsNo());
        int count = repository.count(queryWrapperBoost);
        return count;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteBatchesTemp(PCMerchGoodsInfoDTO.IdsDTO idsDTO) {
        if (ObjectUtils.isEmpty(idsDTO)) {
            throw new BusinessException("参数不能为空！");
        }
        for (String goodsId : idsDTO.getIdList()) {
            /*
             * 清除掉对应的商品信息以及与商品关联的信息
             */
            PCMerchGoodsInfoDTO.IdDTO idDTO = new PCMerchGoodsInfoDTO.IdDTO(goodsId);
            deleteGoodsInfo(idDTO);
        }
        return true;
    }

    @Override
    public void updateGoodsInfoStateTemp(String goodId, Integer state) {
        GoodsInfoTemp goodsInfoTemp = new GoodsInfoTemp();
        goodsInfoTemp.setGoodsState(state);

        UpdateWrapper<GoodsInfoTemp> goodsBoost = MybatisPlusUtil.update();
        goodsBoost.eq("id", goodId);
        repository.update(goodsInfoTemp, goodsBoost);
    }

    @Override
    public Boolean cancelBatchesTemp(PCMerchGoodsInfoDTO.IdsDTO idsDTO) {
        if (ObjectUtils.isEmpty(idsDTO)) {
            throw new BusinessException("参数不能为空！");
        }
        for (String goodsId : idsDTO.getIdList()) {
            /*
             * 清除掉对应的商品信息以及与商品关联的信息
             */
            PCMerchGoodsInfoDTO.IdDTO idDTO = new PCMerchGoodsInfoDTO.IdDTO(goodsId);
            cancelGoodsInfo(idDTO);
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteGoodsInfo(PCMerchGoodsInfoDTO.IdDTO dto) {

        if (ObjectUtils.isEmpty(dto)) {
            throw new BusinessException("参数不能为空！！");
        }
        String id = dto.getId();
        //清除商品关联的运费模板信息
        QueryWrapper<GoodsTempalteTemp> templateWrapper = new QueryWrapper<>();
        templateWrapper.eq("goods_id", id);
        goodsTempalteTempRepository.remove(templateWrapper);

        //清除商品关联的店铺分类信息
        QueryWrapper<GoodsShopNavigationTemp> shopNavigationWrapper = new QueryWrapper<>();
        shopNavigationWrapper.eq("goods_id", id);
        shopNavigationTempRepository.remove(shopNavigationWrapper);

        // TODO 清除商品关联的库存信息

        //清除商品关联的拓展信息
        QueryWrapper<GoodsAttributeInfoTemp> attributeWrapper = new QueryWrapper<>();
        attributeWrapper.eq("good_id", id);
        attributeInfoTempRepository.remove(attributeWrapper);

        QueryWrapper<GoodsSpecInfoTemp> specWrapper = new QueryWrapper<>();
        specWrapper.eq("good_id", id);
        goodsSpecInfoTempRepository.remove(specWrapper);

        QueryWrapper<GoodsExtendParamsTemp> paramsWrapper = new QueryWrapper<>();
        paramsWrapper.eq("good_id", id);
        extendParamsTempRepository.remove(paramsWrapper);

        //如果该商品是多规格的则清除商品关联的sku数据
        QueryWrapper<SkuGoodInfoTemp> skuWrapper = new QueryWrapper<>();
        skuWrapper.eq("good_id", id);
        skuGoodInfoTempRepository.remove(skuWrapper);

        //清除商品信息
        QueryWrapper<GoodsInfoTemp> goodsWrapper = MybatisPlusUtil.query();
        goodsWrapper.eq("id", id);
        repository.remove(goodsWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    public void cancelGoodsInfo(PCMerchGoodsInfoDTO.IdDTO dto) {
        UpdateWrapper<GoodsInfoTemp> updateWrapper = MybatisPlusUtil.update();
        updateWrapper.eq("id", dto.getId());
        GoodsInfoTemp goodsInfoTemp = new GoodsInfoTemp();
        goodsInfoTemp.setGoodsState(GoodsStateEnum.草稿箱.getCode());
        goodsInfoTemp.setUdate(new Date());
        repository.update(goodsInfoTemp,updateWrapper);
    }

    private void checkAddGoodsData(PCMerchGoodsInfoDTO.AddGoodsETO eto) {
    	if (eto == null) {
            throw new BusinessException("参数不能为空");
        }
    	log.info("[checkAddGoodsData][eto][shopId==>{}]",eto.getJwtShopId());
        Integer shopState = shopRpc.innerShopState(eto.getJwtShopId());
        if (ObjectUtils.isEmpty(shopState) || shopState.equals(ShopStateEnum.关闭状态.getCode())) {
            throw new BusinessException("店铺未开通！！");
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
        /*if (StringUtils.isEmpty(eto.getShopNavigationId())) {
            throw new BusinessException("请选择店铺商品分类");
        }*/
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
        commonStockRpc.innerChangeStockTemp(dto);
    }

    private Integer getSpuStockNum(String goodsId, String shopId) {
        QueryWrapper<SkuGoodInfoTemp> boost = MybatisPlusUtil.query();
        boost.eq("good_id", goodsId);
        List<SkuGoodInfoTemp> skuGoodInfos = skuGoodInfoTempRepository.list(boost);
        if (ObjectUtils.isEmpty(skuGoodInfos)) {
            throw new BusinessException("查询数据异常");
        }
        int spuStockNum = 0;
        for (SkuGoodInfoTemp skuGoodInfo : skuGoodInfos) {
            spuStockNum += getSkuStockNum(shopId, skuGoodInfo.getId());
        }
        return spuStockNum;
    }

    private Integer getSkuStockNum(String shopId, String skuId) {
        CommonStockVO.InnerStockVO stockVO = commonStockRpc.queryStockTemp(new BaseDTO(), shopId, skuId).getData();
        if (stockVO != null && stockVO.getQuantity() != null) {
            return stockVO.getQuantity();
        }
        return 0;
    }
}
