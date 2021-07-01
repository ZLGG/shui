package com.gs.lshly.biz.support.commodity.service.platadmin.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gs.lshly.biz.support.commodity.entity.*;
import com.gs.lshly.biz.support.commodity.repository.*;
import com.gs.lshly.biz.support.commodity.service.platadmin.*;
import com.gs.lshly.common.enums.SingleStateEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.common.CommonStockVO;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.*;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.*;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsBrandDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.SkuGoodsInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsAuditRecordQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.*;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import com.gs.lshly.rpc.api.common.ICommonStockRpc;
import com.gs.lshly.rpc.api.common.ICommonStockTemplateRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchGoodsServeRpc;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GoodsInfoTempServiceImpl implements IGoodsInfoTempService {

    @DubboReference
    private IPCMerchGoodsServeRpc goodsServeRpc;

    @DubboReference
    private ICommonStockRpc commonStockRpc;

    @DubboReference
    private ICommonShopRpc commonShopRpc;

    @DubboReference
    private ICommonStockTemplateRpc commonStockTemplateRpc;

    @Autowired
    private IGoodsInfoTempRepository repository;

    @Autowired
    private ISkuGoodInfoTempService skuGoodInfoTempService;

    @Autowired
    private IGoodsAttributeInfoTempService attributeInfoTempService;

    @Autowired
    private IGoodsExtendParamsTempService extendParamsTempService;

    @Autowired
    private IGoodsSpecInfoTempService goodsSpecInfoTempService;

    @Autowired
    private IGoodsCategoryService categoryService;

    @Autowired
    private IGoodsBrandService brandService;

    @Autowired
    private IGoodsTempalteTempRepository goodsTempalteTempRepository;

    @Autowired
    private IGoodsShopNavigationTempRepository shopNavigationTempRepository;

    @Autowired
    private ISkuGoodInfoTempRepository skuGoodInfoTempRepository;

    @Autowired
    private IGoodsRelationLabelService relationLabelService;

    @Autowired
    private IGoodsAuditRecordService auditRecordService;
    @Autowired
    private IGoodsAuditRecordRepository auditRecordRepository;
    @Autowired
    private IGoodsShopNavigationTempRepository navigationTempRepository;
    @Autowired
    private IGoodsServeCorTempRepository goodsServeCorTempRepository;
    @Autowired
    private IGoodsServeRepository goodsServeTempRepository;


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
        detailVO.setSpuStock(getSpuStockNum(goodsInfoTemp.getId(), dto.getJwtShopId()));
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
        if(ObjectUtils.isNotEmpty(goodsInfoTemp)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public GoodsInfoVO.DetailVO getGoodsDetail(GoodsInfoDTO.IdDTO dto) {
        if (dto.getId() == null) {
            throw new BusinessException("参数不能为空！");
        }
        GoodsInfoTemp goodsInfo = repository.getById(dto.getId());
        if (goodsInfo == null) {
            throw new BusinessException("查询异常！");
        }
        GoodsInfoVO.DetailVO detailVO = new GoodsInfoVO.DetailVO();
        BeanUtils.copyProperties(goodsInfo, detailVO);

        //填充类目信息
        GoodsCategoryVO.ParentCategoryVO parentCategoryVO = getCategories(goodsInfo.getCategoryId());
        detailVO.setCategoryLevel1(parentCategoryVO.getLev2Name());
        detailVO.setCategoryLevel2(parentCategoryVO.getLev1Name());
        detailVO.setCategoryLevel3(parentCategoryVO.getLevName());

        //填充品牌信息
        if(ObjectUtil.isNotEmpty(goodsInfo.getBrandId())){
            GoodsBrandVO.DetailVO brandVO = getBrand(goodsInfo.getBrandId());
            detailVO.setBrandName(brandVO.getBrandName());
        }

        //填充sku列表信息
        if (detailVO.getIsSingle().intValue() == SingleStateEnum.多规格.getCode().intValue()) {
            List<SkuGoodsInfoVO.DetailVO> listVOS = skuGoodInfoTempService.listSku(new SkuGoodsInfoDTO.GoodsIdDTO(dto.getId()));
            if (ObjectUtils.isEmpty(listVOS)) {
                throw new BusinessException("sku数据异常！！");
            }
            for (SkuGoodsInfoVO.DetailVO sku : listVOS) {
                // 填充sku库存
                sku.setStockNum(getSkuStockNum(goodsInfo.getShopId(), sku.getId()));
            }
            detailVO.setList(listVOS);
        }
        //填充店铺分类
        QueryWrapper<GoodsShopNavigationTemp> boost = MybatisPlusUtil.query();
        boost.eq("goods_id", goodsInfo.getId());
        List<GoodsShopNavigationTemp> navigations = navigationTempRepository.list(boost);
        if (ObjectUtils.isNotEmpty(navigations)) {
            for (GoodsShopNavigationTemp shopNavigation : navigations) {
                if (ObjectUtils.isNotEmpty(shopNavigation.getTerminal()) && shopNavigation.getTerminal().equals(TerminalEnum.BBB.getCode())) {
                    CommonShopVO.NavigationVO navigationVO = getShopNavigation(shopNavigation.getShopNavigation());
                    //填充店铺类目信息
                    if (ObjectUtils.isNotEmpty(navigationVO)) {
                        detailVO.setShopLevel1Name(StringUtils.isEmpty(navigationVO.getNavigationName()) ? "" : navigationVO.getNavigationName());
                        detailVO.setShopLevel2Name(StringUtils.isEmpty(navigationVO.getNavigationTowName()) ? "" : navigationVO.getNavigationTowName());
                    }
                }
                if (ObjectUtils.isNotEmpty(shopNavigation.getTerminal()) && shopNavigation.getTerminal().equals(TerminalEnum.BBC.getCode())) {
                    CommonShopVO.NavigationVO navigationVO = getShopNavigation(shopNavigation.getShopNavigation());
                    //填充店铺类目信息
                    if (ObjectUtils.isNotEmpty(navigationVO)) {
                        detailVO.setShop2cLevel1Name(StringUtils.isEmpty(navigationVO.getNavigationName()) ? "" : navigationVO.getNavigationName());
                        detailVO.setShop2cLevel2Name(StringUtils.isEmpty(navigationVO.getNavigationTowName()) ? "" : navigationVO.getNavigationTowName());
                    }
                }
            }
        }


        //填充店铺信息
        CommonShopVO.SimpleVO simpleVO = commonShopRpc.shopDetails(goodsInfo.getShopId());
        if (ObjectUtils.isEmpty(simpleVO)) {
            throw new BusinessException("店铺数据异常");
        }
        detailVO.setShopName(StringUtils.isEmpty(simpleVO.getShopName()) ? "" : simpleVO.getShopName());


        detailVO.setChargeUint(StringUtils.isEmpty(goodsInfo.getGoodsPriceUnit()) ? "" : goodsInfo.getGoodsPriceUnit());

        //填充spu库存
        detailVO.setSpuStockNum(getSpuStockNum(goodsInfo.getId(), goodsInfo.getShopId()));

        //商品图片
        detailVO.setGoodsImage(StringUtils.isEmpty(getImage(goodsInfo.getGoodsImage())) ? "" : getImage(goodsInfo.getGoodsImage()));
        //填充审核记录信息
        List<GoodsAuditRecordVO.ListVO> auditRecords = getAuditRecord(dto);
        if (ObjectUtils.isNotEmpty(auditRecords)) {
            detailVO.setListAuditRecord(auditRecords);
        }

        //填充运费模板
        String templateName = getTemplateName(goodsInfo.getId());
        detailVO.setTemplateName(StringUtils.isBlank(templateName) ? "" : templateName);

        //查询标签
        detailVO.setTags(relationLabelService.listGoodsLabelByGoodsId(goodsInfo.getId()));

        //查询服务
        List<String> serveListVO = goodsServeRpc.getServeTempIdByGoodsId(new PCMerchGoodsServeDTO.IdDTO(goodsInfo.getId()));
		if (CollUtil.isNotEmpty(serveListVO)) {
		    detailVO.setGoodsServeList(serveListVO);
		}
		
        return detailVO;
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

    private GoodsCategoryVO.ParentCategoryVO getCategories(String categoryId) {
        if (StringUtils.isEmpty(categoryId)) {
            throw new BusinessException("数据异常");
        }
        GoodsCategoryVO.ParentCategoryVO parentCategoryVO = categoryService.findParentCategoryVO(new GoodsCategoryDTO.IdDTO(categoryId));
        if (parentCategoryVO == null) {
            throw new BusinessException("数据异常");
        }
        return parentCategoryVO;
    }

    private GoodsBrandVO.DetailVO getBrand(String branId) {
        if (StringUtils.isEmpty(branId)) {
            throw new BusinessException("数据异常");
        }
        GoodsBrandVO.DetailVO detailVO = brandService.select(new GoodsBrandDTO.IdDTO(branId));
        if (detailVO == null) {
            throw new BusinessException("数据异常");
        }
        return detailVO;
    }

    private CommonShopVO.NavigationVO getShopNavigation(String navigationId) {
        CommonShopVO.NavigationVO shopNavigation = commonShopRpc.shopNavigation(navigationId);
        if (ObjectUtils.isNotEmpty(shopNavigation)) {
            return shopNavigation;
        }
        return null;
    }

    private String getImage(String images) {
        if (images != null && !images.equals("{}")) {
            JSONArray arr = JSONArray.parseArray(images);
            if (ObjectUtils.isEmpty(arr)) {
                return null;
            }
            JSONObject obj = arr.getJSONObject(0);
            String imgUrl = obj.getString("imgSrc");
            return imgUrl;
        }
        return null;
    }

    private String getTemplateName(String goodsId) {
        QueryWrapper<GoodsTempalteTemp> tempalteBoost = MybatisPlusUtil.query();
        tempalteBoost.eq("goods_id", goodsId);
        GoodsTempalteTemp tempalte = goodsTempalteTempRepository.getOne(tempalteBoost);
        if (tempalte == null) {
            throw new BusinessException("查询异常");
        }
        CommonStockTemplateVO.IdNameVO idNameVO = commonStockTemplateRpc.innerIdNameVO(tempalte.getTemplateId());
        if (null != idNameVO) {
            return idNameVO.getTemplateName();
        }
        return null;
    }

    private List<GoodsAuditRecordVO.ListVO> getAuditRecord(GoodsInfoDTO.IdDTO dto) {
        GoodsAuditRecordQTO.QTO qto = new GoodsAuditRecordQTO.QTO();
        qto.setGoodsId(dto.getId());
        List<GoodsAuditRecordVO.ListVO> listVOS = auditRecordService.listAuditRecordByGoodsId(qto);
        return listVOS;
    }
}
