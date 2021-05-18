package com.gs.lshly.biz.support.commodity.service.platadmin.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsAuditRecord;
import com.gs.lshly.biz.support.commodity.entity.GoodsInfo;
import com.gs.lshly.biz.support.commodity.entity.GoodsShopNavigation;
import com.gs.lshly.biz.support.commodity.entity.GoodsTempalte;
import com.gs.lshly.biz.support.commodity.entity.SkuGoodInfo;
import com.gs.lshly.biz.support.commodity.mapper.GoodsInfoMapper;
import com.gs.lshly.biz.support.commodity.repository.IGoodsAuditRecordRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsInfoRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsShopNavigationRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsTempalteRepository;
import com.gs.lshly.biz.support.commodity.repository.ISkuGoodInfoRepository;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsAuditRecordService;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsBrandService;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsCategoryService;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsInfoService;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsRelationLabelService;
import com.gs.lshly.biz.support.commodity.service.platadmin.ISkuGoodInfoService;
import com.gs.lshly.common.enums.GoodAuditRecordEnum;
import com.gs.lshly.common.enums.GoodsQueryTypeEnum;
import com.gs.lshly.common.enums.GoodsStateEnum;
import com.gs.lshly.common.enums.GoodsUsePlatformEnums;
import com.gs.lshly.common.enums.ShopTypeEnum;
import com.gs.lshly.common.enums.SingleStateEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bb.commodity.qto.BbGoodsInfoQTO.QTO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.common.CommonStockVO;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateVO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsAuditRecordDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsBrandDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsRelationLabelDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.SkuGoodsInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsAuditRecordQTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsInfoQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsAuditRecordVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsBrandVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsCategoryVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO.ListVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsLabelVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.SkuGoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import com.gs.lshly.rpc.api.common.ICommonStockRpc;
import com.gs.lshly.rpc.api.common.ICommonStockTemplateRpc;
import com.gs.lshly.rpc.api.platadmin.merchant.IShopRpc;

import cn.hutool.core.collection.CollectionUtil;

/**
 * @Author Starry
 * @Date 15:59 2020/10/14
 */
@Service
public class GoodsInfoServiceImpl implements IGoodsInfoService {
    @Autowired
    private IGoodsInfoRepository repository;
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;
    @Autowired
    private IGoodsCategoryService categoryService;
    @Autowired
    private IGoodsBrandService brandService;
    @Autowired
    private ISkuGoodInfoService skuGoodInfoService;
    @Autowired
    private ISkuGoodInfoRepository skuGoodInfoRepository;
    @Autowired
    private IGoodsRelationLabelService relationLabelService;
    @Autowired
    private IGoodsAuditRecordService auditRecordService;
    @Autowired
    private IGoodsAuditRecordRepository auditRecordRepository;
    @Autowired
    private IGoodsShopNavigationRepository navigationRepository;
    @Autowired
    private IGoodsTempalteRepository goodsTempalteRepository;
    @DubboReference
    private ICommonStockRpc commonStockRpc;

    @DubboReference
    private ICommonShopRpc commonShopRpc;

    @DubboReference
    private IShopRpc shopRpc;

    @DubboReference
    private ICommonStockTemplateRpc commonStockTemplateRpc;



    @Override
    public PageData<GoodsInfoVO.SpuListVO> pageGoodsInfoData(GoodsInfoQTO.QTO qto) {
        QueryWrapper<GoodsInfoVO.SpuListVO> boost = MybatisPlusUtil.query();
        if (ObjectUtils.isNotEmpty(qto.getGoodState()) && qto.getGoodState().intValue() != -1){
            boost.eq("gs.goods_state",qto.getGoodState());
        }
        if (ObjectUtils.isNotEmpty(qto.getCostPrice()) && qto.getCostPrice().intValue() !=0){
            boost.ge("gs.cost_price",qto.getCostPrice());
        }
        if (ObjectUtils.isNotEmpty(qto.getCostPrice2()) && qto.getCostPrice2().intValue() !=0){
            boost.le("gs.cost_price",qto.getCostPrice2());
        }
        if (ObjectUtils.isNotEmpty(qto.getSalePrice()) && qto.getSalePrice().intValue() !=0){
            boost.ge("gs.sale_price",qto.getSalePrice());
        }
        if (ObjectUtils.isNotEmpty(qto.getSalePrice2()) && qto.getSalePrice2().intValue() !=0){
            boost.le("gs.sale_price",qto.getSalePrice2());
        }
        if (ObjectUtils.isNotEmpty(qto.getOldPrice()) && qto.getOldPrice().intValue() !=0){
            boost.ge("gs.old_price",qto.getOldPrice());
        }
        if (ObjectUtils.isNotEmpty(qto.getOldPrice2()) && qto.getOldPrice2().intValue() !=0){
            boost.le("gs.old_price",qto.getOldPrice2());
        }
        if (StringUtils.isNotBlank(qto.getGoodsNo())){
            boost.like("gs.goods_no",qto.getGoodsNo());
        }
        if (StringUtils.isNotBlank(qto.getGoodsBrand())){
            boost.like("gb.brand_name",qto.getGoodsBrand());
        }
        if (StringUtils.isNotEmpty(qto.getGoodsCategory())){
            boost.like("gc.gs_category_name",qto.getGoodsCategory());
        }
        if (StringUtils.isNotEmpty(qto.getGoodsName())){
            boost.like("gs.goods_name",qto.getGoodsName());
        }
        if (StringUtils.isNotEmpty(qto.getGoodsPriceUnit())){
            boost.like("gs.goods_price_unit",qto.getGoodsPriceUnit());
        }
        if (ObjectUtils.isNotEmpty(qto.getIsSingle())){
            boost.like("gs.is_single",qto.getIsSingle());
        }
        if (StringUtils.isNotBlank(qto.getLabelId())){
            boost.like("grl.label_id",qto.getLabelId());
        }
        if (StringUtils.isNotBlank(qto.getShopName())){
            List<CommonShopVO.SimpleVO> simpleVOS = commonShopRpc.searchDetailShop(qto.getShopName());
            if(ObjectUtils.isEmpty(simpleVOS)){
                return new PageData<>();
            }
            List<String> shopIdList = ListUtil.getIdList(CommonShopVO.SimpleVO.class,simpleVOS);
            if(ObjectUtils.isEmpty(shopIdList)){
                return new PageData<>();
            }
            boost.in("gs.shop_id",shopIdList);
        }
        //自营商品
        if (ObjectUtils.isNotEmpty(qto.getShopType())){
            List<ShopVO.InnerSimpleVO> idNameVOS = shopRpc.innerlistShopIdName(qto, ShopTypeEnum.运营商自营.getCode(),TerminalEnum.BBB.getCode());
            if (ObjectUtils.isEmpty(idNameVOS)){
                return new PageData<>();
            }
            List<String> shopIdList = ListUtil.getIdList(ShopVO.InnerSimpleVO.class,idNameVOS);
            if(ObjectUtils.isEmpty(shopIdList)){
                return new PageData<>();
            }
            boost.in("gs.shop_id",shopIdList);
        }
        IPage<GoodsInfoVO.SpuListVO> page = MybatisPlusUtil.pager(qto);
        IPage<GoodsInfoVO.SpuListVO> spuListVOIPage = goodsInfoMapper.getGoodsInfo(page,boost);
        if (ObjectUtils.isEmpty(spuListVOIPage)){
            return new PageData<>();
        }
        for (GoodsInfoVO.SpuListVO spuListVO : spuListVOIPage.getRecords()){
            //填充商品标签
            if (ObjectUtils.isNotEmpty(getLabelInfo(spuListVO))){
                spuListVO.setLabels(getLabelInfo(spuListVO));
            }
            //填充商品图片
            spuListVO.setGoodsImage(ObjectUtils.isEmpty(getImage(spuListVO.getGoodsImage()))?"{}":getImage(spuListVO.getGoodsImage()));

            CommonShopVO.SimpleVO simpleVO = commonShopRpc.shopDetails(spuListVO.getShopId());
            if (ObjectUtils.isEmpty(simpleVO)){
                throw new BusinessException("店铺数据异常");
            }
            //填充店铺信息
            spuListVO.setShopName(StringUtils.isEmpty(simpleVO.getShopName())?"":simpleVO.getShopName());

            //填充店铺类型
            spuListVO.setShopType(simpleVO.getShopType());
        }
        return MybatisPlusUtil.toPageData(qto, GoodsInfoVO.SpuListVO.class,spuListVOIPage);
    }

    @Override
    public GoodsInfoVO.DetailVO getGoodsDetail(GoodsInfoDTO.IdDTO dto) {
        if (dto.getId() == null){
            throw new BusinessException("参数不能为空！");
        }
        GoodsInfo goodsInfo = repository.getById(dto.getId());
        if (goodsInfo == null){
            throw new BusinessException("查询异常！");
        }
        GoodsInfoVO.DetailVO detailVO = new GoodsInfoVO.DetailVO();
        BeanUtils.copyProperties(goodsInfo,detailVO);

        //填充类目信息
        GoodsCategoryVO.ParentCategoryVO parentCategoryVO = getCategories(goodsInfo.getCategoryId());
        detailVO.setCategoryLevel1(parentCategoryVO.getLev2Name());
        detailVO.setCategoryLevel2(parentCategoryVO.getLev1Name());
        detailVO.setCategoryLevel3(parentCategoryVO.getLevName());

        //填充品牌信息
        GoodsBrandVO.DetailVO brandVO = getBrand(goodsInfo.getBrandId());
        detailVO.setBrandName(brandVO.getBrandName());

        //填充sku列表信息
        if (detailVO.getIsSingle().intValue() == SingleStateEnum.多规格.getCode().intValue()){
            List<SkuGoodsInfoVO.DetailVO> listVOS =  skuGoodInfoService.listSku(new SkuGoodsInfoDTO.GoodsIdDTO(dto.getId()));
            if (ObjectUtils.isEmpty(listVOS)){
               throw new BusinessException("sku数据异常！！");
            }
            for (SkuGoodsInfoVO.DetailVO sku : listVOS){
                // 填充sku库存
                sku.setStockNum(getSkuStockNum(goodsInfo.getShopId(),sku.getId()));
            }
            detailVO.setList(listVOS);
        }
        //填充店铺分类
        QueryWrapper<GoodsShopNavigation> boost = MybatisPlusUtil.query();
        boost.eq("goods_id",goodsInfo.getId());
        List<GoodsShopNavigation> navigations = navigationRepository.list(boost);
        if (ObjectUtils.isNotEmpty(navigations)){
            for (GoodsShopNavigation shopNavigation : navigations){
                if (ObjectUtils.isNotEmpty(shopNavigation.getTerminal()) && shopNavigation.getTerminal().equals(TerminalEnum.BBB.getCode())){
                    CommonShopVO.NavigationVO navigationVO = getShopNavigation(shopNavigation.getShopNavigation());
                    //填充店铺类目信息
                    if (ObjectUtils.isNotEmpty(navigationVO)){
                        detailVO.setShopLevel1Name(StringUtils.isEmpty(navigationVO.getNavigationName())?"":navigationVO.getNavigationName());
                        detailVO.setShopLevel2Name(StringUtils.isEmpty(navigationVO.getNavigationTowName())?"":navigationVO.getNavigationTowName());
                    }
                }
                if (ObjectUtils.isNotEmpty(shopNavigation.getTerminal()) && shopNavigation.getTerminal().equals(TerminalEnum.BBC.getCode())){
                    CommonShopVO.NavigationVO navigationVO = getShopNavigation(shopNavigation.getShopNavigation());
                    //填充店铺类目信息
                    if (ObjectUtils.isNotEmpty(navigationVO)){
                        detailVO.setShop2cLevel1Name(StringUtils.isEmpty(navigationVO.getNavigationName())?"":navigationVO.getNavigationName());
                        detailVO.setShop2cLevel2Name(StringUtils.isEmpty(navigationVO.getNavigationTowName())?"":navigationVO.getNavigationTowName());
                    }
                }
            }
        }



        //填充店铺信息
        CommonShopVO.SimpleVO simpleVO = commonShopRpc.shopDetails(goodsInfo.getShopId());
        if (ObjectUtils.isEmpty(simpleVO)){
            throw new BusinessException("店铺数据异常");
        }
        detailVO.setShopName(StringUtils.isEmpty(simpleVO.getShopName())?"":simpleVO.getShopName());


        detailVO.setChargeUint(StringUtils.isEmpty(goodsInfo.getGoodsPriceUnit())?"":goodsInfo.getGoodsPriceUnit());

        //填充spu库存
        detailVO.setSpuStockNum(getSpuStockNum(goodsInfo.getId(),goodsInfo.getShopId()));

        //商品图片
        detailVO.setGoodsImage(StringUtils.isEmpty(getImage(goodsInfo.getGoodsImage()))?"":getImage(goodsInfo.getGoodsImage()));
        //填充审核记录信息
        List<GoodsAuditRecordVO.ListVO> auditRecords = getAuditRecord(dto);
        if (ObjectUtils.isNotEmpty(auditRecords)){
            detailVO.setListAuditRecord(auditRecords);
        }
        
        //填充运费模板
        String templateName = getTemplateName(goodsInfo.getId());
        detailVO.setTemplateName(StringUtils.isBlank(templateName)?"":templateName);

        //查询标签
        detailVO.setTags(relationLabelService.listGoodsLabelByGoodsId(goodsInfo.getId()));
        return detailVO;
    }

    @Override
    public void underCarriageGoods(GoodsInfoDTO.IdListDTO dto) {
        upOrOnGoods(dto, GoodsStateEnum.未上架.getCode());
    }

    @Override
    public void deleteGoodsBatches(GoodsInfoDTO.IdListDTO dto) {
        if (dto == null || dto.getIdList() == null || dto.getIdList().size() == 0){
            throw new BusinessException("参数不能为空！");
        }
        //TODO 如果sku商品是处于交易中并且订单的状态不是已完成那么不可以删除该商品


        //如果该商品是多规格商品则先删除sku商品然后再删除spu商品
        for (String goodsId:dto.getIdList()) {
            QueryWrapper<SkuGoodInfo> wrapper = new QueryWrapper<>();
            wrapper.eq("good_id",goodsId);
            List<SkuGoodInfo> infoList = skuGoodInfoRepository.list(wrapper);
            if (infoList != null && infoList.size()>0){
                skuGoodInfoRepository.remove(wrapper);
            }
        }
        repository.removeByIds(dto.getIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkGoods(GoodsInfoDTO.CheckGoodsDTO dto) {
        if (dto == null){
            throw new BusinessException("参数不能为空！");
        }
        //如果该商品是多规格商品则先修改sku商品然后再修改spu商品
        QueryWrapper<SkuGoodInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("good_id",dto.getId());

        List<SkuGoodInfo> infoList = skuGoodInfoRepository.list(wrapper);
        if (infoList != null && infoList.size()>0){
            for (SkuGoodInfo info:infoList) {

                UpdateWrapper<SkuGoodInfo> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("id",info.getId());

                SkuGoodInfo skuGoodInfo = new SkuGoodInfo();
                skuGoodInfo.setState(dto.getState());

                skuGoodInfoRepository.update(skuGoodInfo,updateWrapper);
            }
        }
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setGoodsState(dto.getState());

        QueryWrapper<GoodsInfo> goodsInfoQueryWrapper = new QueryWrapper<>();
        goodsInfoQueryWrapper.eq("id",dto.getId());
        repository.update(goodsInfo,goodsInfoQueryWrapper);

        //审核后添加审核记录
        addAuditRecord(dto);

    }

    @Override
    public GoodsInfoVO.GoodsNameVO getGoodsName(GoodsInfoDTO.IdDTO dto) {
        if (dto == null){
            throw new BusinessException("参数不能为空！");
        }
        GoodsInfo goodsInfo = repository.getById(dto.getId());
        if (goodsInfo == null){
            throw new BusinessException("没有数据");
        }
        GoodsInfoVO.GoodsNameVO goodsNameVO = new GoodsInfoVO.GoodsNameVO();
        BeanUtils.copyProperties(goodsInfo,goodsNameVO);
        return goodsNameVO;
    }

    @Override
    public PageData<GoodsInfoVO.ShopFloorCommodityVO> getShopFloorCommodityVO(GoodsInfoQTO.ShopFloorQTO qto) {
        List<ShopVO.InnerSimpleVO> idNameVOS = shopRpc.innerlistShopIdName(qto, ShopTypeEnum.运营商自营.getCode(),qto.getUsePlatform());
        if (ObjectUtils.isEmpty(idNameVOS)){
            return new PageData<>();
        }
        if (qto.getUsePlatform().intValue() == TerminalEnum.BBC.getCode()){
            qto.setUsePlatform(GoodsUsePlatformEnums.C商城.getCode()) ;
        }
        if (qto.getUsePlatform().intValue() == TerminalEnum.BBB.getCode()){
            qto.setUsePlatform(GoodsUsePlatformEnums.B商城.getCode()) ;
        }
        QueryWrapper<GoodsInfo> boost = MybatisPlusUtil.query();
        boost.in("use_platform",qto.getUsePlatform(),GoodsUsePlatformEnums.B商城和C商城.getCode());
        boost.eq("goods_state",GoodsStateEnum.已上架.getCode());
        if (StringUtils.isNotEmpty(qto.getGoodsName())){
            boost.like("goods_name",qto.getGoodsName());
        }
        if (StringUtils.isNotEmpty(qto.getGoodsNo())){
            boost.like("goods_no",qto.getGoodsNo());
        }
        //过滤出所需店铺的商品列表
        List<String> shopIds = ListUtil.getIdList(ShopVO.InnerSimpleVO.class,idNameVOS);
        if (ObjectUtils.isEmpty(shopIds)){
            return new PageData<>();
        }
        boost.in("shop_id",shopIds);
        IPage page = MybatisPlusUtil.pager(qto);
        IPage<GoodsInfo> goodsInfoIPage = repository.page(page,boost);

        List<GoodsInfoVO.ShopFloorCommodityVO> shopFloorCommodityVOS = new ArrayList<>();
        for (GoodsInfo goodsInfo : goodsInfoIPage.getRecords()){
            GoodsInfoVO.ShopFloorCommodityVO floorCommodityVO = new GoodsInfoVO.ShopFloorCommodityVO();
            BeanUtils.copyProperties(goodsInfo,floorCommodityVO);
            //获取类目名称
            if (StringUtils.isEmpty(goodsInfo.getCategoryId())){
                throw new BusinessException("商品信息数据异常");
            }
            GoodsCategoryVO.ListVO listVO = categoryService.getListVOById(new GoodsCategoryDTO.IdDTO(goodsInfo.getCategoryId()));
            floorCommodityVO.setGoodsCategoryName(StringUtils.isEmpty(listVO.getGsCategoryName())?"":listVO.getGsCategoryName());

            //获取商品品牌
            GoodsBrandVO.ListVO brandListVO = brandService.getBrandVOById(new GoodsBrandDTO.IdDTO(goodsInfo.getBrandId()));
            floorCommodityVO.setGoodsBrand(StringUtils.isEmpty(brandListVO.getBrandName())?"":brandListVO.getBrandName());
            shopFloorCommodityVOS.add(floorCommodityVO);

        }
        return new PageData<>(shopFloorCommodityVOS,qto.getPageNum(),qto.getPageSize(),goodsInfoIPage.getTotal());
    }

    @Override
    public PageData<GoodsInfoVO.FupinFloorCommodityVO> getFupinFloorCommodityVO(GoodsInfoQTO.FupinFloorQTO qto) {
        QueryWrapper<GoodsInfo> boost = MybatisPlusUtil.query();
        boost.in("use_platform",qto.getUsePlatform(),GoodsUsePlatformEnums.B商城和C商城.getCode());
        boost.eq("goods_state",GoodsStateEnum.已上架.getCode());
        if (StringUtils.isNotEmpty(qto.getGoodsName())){
            boost.like("goods_name",qto.getGoodsName());
        }
        if (StringUtils.isNotEmpty(qto.getGoodsNo())){
            boost.like("goods_no",qto.getGoodsNo());
        }
        boost.in("id",qto.getGoodsId());
        IPage<GoodsInfo> page =MybatisPlusUtil.pager(qto);
        IPage<GoodsInfo> goodsInfoIPage = repository.page(page,boost);
        if (ObjectUtils.isEmpty(goodsInfoIPage) || ObjectUtils.isEmpty(goodsInfoIPage.getRecords())){
            return new PageData<>();
        }
        return MybatisPlusUtil.toPageData(qto,GoodsInfoVO.FupinFloorCommodityVO.class,goodsInfoIPage);
    }

    @Override
    public PageData<GoodsInfoVO.BindCategoryGoodsVO> getBindCategoryGoodsVO(GoodsInfoQTO.CategoryIdQTO qto) {
        if (qto == null || StringUtils.isBlank(qto.getCategoryId())){
            throw new BusinessException("参数为空,异常！！");
        }
        QueryWrapper<GoodsInfo> wrapper = MybatisPlusUtil.query();
        wrapper.eq("category_id",qto.getCategoryId());
        wrapper.select("id, goods_name,goods_no,sale_price,point_price,remarks,is_point_good,is_in_member_gift,in_member_point_price,sale_type");
        IPage<GoodsInfo> page = MybatisPlusUtil.pager(qto);
        IPage<GoodsInfo> goodsInfoIPage = repository.page(page,wrapper);
        if (ObjectUtils.isEmpty(goodsInfoIPage) || ObjectUtils.isEmpty(goodsInfoIPage.getRecords())){
            return new PageData<>();
        }
        List<GoodsInfoVO.BindCategoryGoodsVO> categoryGoodsVOS = ListUtil.listCover(GoodsInfoVO.BindCategoryGoodsVO.class,goodsInfoIPage.getRecords());
        return new PageData<>(categoryGoodsVOS,qto.getPageNum(),qto.getPageSize(),goodsInfoIPage.getTotal());
    }

    @Override
    public List<GoodsInfoVO.InnerServiceGoodsVO> innerServiceGoodsVO(GoodsInfoDTO.IdsInnerServiceDTO dto) {
        return getInnerServiceGoodsVO(GoodsStateEnum.已上架.getCode(),dto);
    }

    @Override
    public List<GoodsInfoVO.ListVO> innerServiceSpuGoodsInfo(GoodsInfoDTO.IdsInnerServiceDTO dto) {

        return getListVO(GoodsStateEnum.已上架.getCode(),dto);
    }

    @Override
    public List<GoodsInfoVO.InnerServiceGoodsInfoVO> innerServiceGoodsInfo(GoodsInfoDTO.IdsInnerServiceDTO dto) {
       return getInnerServiceGoodsInfo(GoodsStateEnum.已上架.getCode(),dto);
    }

    @Override
    public List<GoodsInfoVO.InnerServiceGoodsVO> innerServiceAllGoodsVO(GoodsInfoDTO.IdsInnerServiceDTO dto) {
        return getInnerServiceGoodsVO(null,dto);
    }

    @Override
    public List<GoodsInfoVO.ListVO> innerServiceAllSpuGoodsInfo(GoodsInfoDTO.IdsInnerServiceDTO dto) {
        return getListVO(null,dto);
    }

    @Override
    public List<GoodsInfoVO.InnerServiceGoodsInfoVO> innerServiceAllGoodsInfo(GoodsInfoDTO.IdsInnerServiceDTO dto) {
        return getInnerServiceGoodsInfo(null,dto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void innerServiceUnderShelfGoods(List<String> shopId) {
        UpdateWrapper<GoodsInfo> wrapper = MybatisPlusUtil.update();
        wrapper.in("shop_id",shopId);

        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setGoodsState(GoodsStateEnum.未上架.getCode());

        UpdateWrapper<SkuGoodInfo> updateWrapper = MybatisPlusUtil.update();
        updateWrapper.in("shop_id",shopId);
        SkuGoodInfo skuGoodInfo = new SkuGoodInfo();
        skuGoodInfo.setState(GoodsStateEnum.未上架.getCode());
        skuGoodInfoRepository.update(skuGoodInfo,updateWrapper);

        repository.update(goodsInfo,wrapper);
    }


    private Integer getSkuStockNum(String shopId,String skuId){
        CommonStockVO.InnerStockVO stockVO = commonStockRpc.queryStock(new BaseDTO(),shopId,skuId).getData();
        if (stockVO != null && stockVO.getQuantity() != null){
            return  stockVO.getQuantity();
        }
        return 0;
    }

    private Integer getSpuStockNum(String goodsId,String shopId){
        QueryWrapper<SkuGoodInfo> boost = MybatisPlusUtil.query();
        boost.eq("good_id",goodsId);
        List<SkuGoodInfo> skuGoodInfos = skuGoodInfoRepository.list(boost);
        if (ObjectUtils.isEmpty(skuGoodInfos)){
            throw new BusinessException("查询数据异常");
        }
        int spuStockNum = 0;
        for (SkuGoodInfo skuGoodInfo : skuGoodInfos){
            spuStockNum += getSkuStockNum(shopId,skuGoodInfo.getId());
        }
        return spuStockNum;
    }

    private List<GoodsLabelVO.ListVO> getLabelInfo(GoodsInfoVO.SpuListVO spuListVO){
        StringBuffer labelBuffer = new StringBuffer();
        if (StringUtils.isEmpty(spuListVO.getId())){
            throw new BusinessException("数据异常！");
        }
        List<GoodsLabelVO.ListVO> labels = relationLabelService.getGoodsLabel(new GoodsRelationLabelDTO.GoodsIdDTO(spuListVO.getId()));
        if (ObjectUtils.isEmpty(labels)){
            return new ArrayList<>();
        }
        return labels;
    }

    private GoodsCategoryVO.ParentCategoryVO getCategories(String categoryId){
        if (StringUtils.isEmpty(categoryId)) {
            throw new BusinessException("数据异常");
        }
        GoodsCategoryVO.ParentCategoryVO parentCategoryVO = categoryService.findParentCategoryVO(new GoodsCategoryDTO.IdDTO(categoryId));
        if (parentCategoryVO == null){
            throw new BusinessException("数据异常");
        }
        return parentCategoryVO;
    }

    private GoodsBrandVO.DetailVO getBrand(String branId){
        if (StringUtils.isEmpty(branId)) {
            throw new BusinessException("数据异常");
        }
        GoodsBrandVO.DetailVO detailVO = brandService.select(new GoodsBrandDTO.IdDTO(branId));
        if (detailVO == null){
            throw new BusinessException("数据异常");
        }
        return detailVO;
    }

    private void upOrOnGoods(GoodsInfoDTO.IdListDTO dto, Integer state){
        if (dto == null || dto.getIdList() == null || dto.getIdList().size() == 0){
            throw new BusinessException("参数不能为空！");
        }
        //如果该商品是多规格商品则先修改sku商品然后再修改spu商品
        for (String goodsId:dto.getIdList()) {
            QueryWrapper<SkuGoodInfo> wrapper = new QueryWrapper<>();
            wrapper.eq("good_id",goodsId);
            List<SkuGoodInfo> infoList = skuGoodInfoRepository.list(wrapper);
            if (infoList != null && infoList.size()>0){
                for (SkuGoodInfo info:infoList) {

                    UpdateWrapper<SkuGoodInfo> updateWrapper = new UpdateWrapper<>();
                    updateWrapper.eq("id",info.getId());

                    SkuGoodInfo skuGoodInfo = new SkuGoodInfo();
                    skuGoodInfo.setState(state);

                    skuGoodInfoRepository.update(skuGoodInfo,updateWrapper);
                }
            }
        }
        for (String id:dto.getIdList()) {
            QueryWrapper<GoodsInfo> wrapper = new QueryWrapper<>();
            wrapper.eq("id",id);

            GoodsInfo goodsInfo = new GoodsInfo();
            goodsInfo.setGoodsState(state);
            //上架时间
            if (state.intValue() == GoodsStateEnum.已上架.getCode().intValue()){
                goodsInfo.setPublishTime(LocalDateTime.now());
            }
            repository.update(goodsInfo,wrapper);
        }
    }

    private void addAuditRecord(GoodsInfoDTO.CheckGoodsDTO dto){
        GoodsAuditRecordDTO.ETO  eto = new GoodsAuditRecordDTO.ETO();
        BeanUtils.copyProperties(dto,eto);
        //获取审核状态
        Integer state = null;
        if (dto.getState().intValue() == GoodsStateEnum.已上架.getCode().intValue()){
            state = GoodAuditRecordEnum.审核通过.getCode();
        }
        if (dto.getState().intValue() == GoodsStateEnum.审核失败.getCode().intValue()){
            state = GoodAuditRecordEnum.审核拒绝.getCode();
        }
        eto.setState(state);
        eto.setGoodsId(dto.getId());
        eto.setId("");
        auditRecordService.addGoodsAuditRecord(eto);
    }

    private List<GoodsAuditRecordVO.ListVO> getAuditRecord(GoodsInfoDTO.IdDTO dto){
        GoodsAuditRecordQTO.QTO qto = new GoodsAuditRecordQTO.QTO();
        qto.setGoodsId(dto.getId());
        List<GoodsAuditRecordVO.ListVO> listVOS = auditRecordService.listAuditRecordByGoodsId(qto);
        return listVOS;
    }

    private  String getImage(String images){
        if (images !=null&&!images.equals("{}")){
            JSONArray arr = JSONArray.parseArray(images);
            if (ObjectUtils.isEmpty(arr)){
                return null;
            }
            JSONObject obj = arr.getJSONObject(0);
            String imgUrl = obj.getString("imgSrc");
            return imgUrl;
        }
        return null;
    }

    private CommonShopVO.NavigationVO getShopNavigation(String navigationId){
        CommonShopVO.NavigationVO shopNavigation = commonShopRpc.shopNavigation(navigationId);
        if (ObjectUtils.isNotEmpty(shopNavigation)){
            return shopNavigation;
        }
        return null;
    }

    private List<GoodsInfoVO.InnerServiceGoodsVO> getInnerServiceGoodsVO(Integer state,GoodsInfoDTO.IdsInnerServiceDTO dto){
        List<GoodsInfo> goodsInfos = null;
        QueryWrapper<GoodsInfo> boost = MybatisPlusUtil.query();
        if (ObjectUtils.isNotEmpty(state)){
            boost.eq("goods_state",state);
        }
        if(GoodsQueryTypeEnum.店铺id.getCode().equals(dto.getQueryType())){
            if(ObjectUtils.isNotEmpty(dto.getShopIdList())){
                boost.in("shop_id",dto.getShopIdList());
            }
            goodsInfos = repository.list(boost);
        }
        if(GoodsQueryTypeEnum.商品id.getCode().equals(dto.getQueryType())){
            if(ObjectUtils.isNotEmpty(dto.getGoodsIdList())){
                boost.in("id",dto.getGoodsIdList());
            }
            goodsInfos = repository.list(boost);
        }
        if(ObjectUtils.isNotEmpty(goodsInfos)){
            return ListUtil.listCover(GoodsInfoVO.InnerServiceGoodsVO.class,goodsInfos);
        }
        return new ArrayList<>();
    }

    private List<GoodsInfoVO.ListVO> getListVO(Integer state,GoodsInfoDTO.IdsInnerServiceDTO dto){
        List<GoodsInfo> goodsInfos = null;
        QueryWrapper<GoodsInfo> boost = MybatisPlusUtil.query();
        if (ObjectUtils.isNotEmpty(state)){
            boost.eq("goods_state",state);
        }
        if(GoodsQueryTypeEnum.店铺id.getCode().equals(dto.getQueryType())){
            if(ObjectUtils.isNotEmpty(dto.getShopIdList())){
                boost.in("shop_id",dto.getShopIdList());
            }
            goodsInfos = repository.list(boost);
        }
        if(GoodsQueryTypeEnum.商品id.getCode().equals(dto.getQueryType())){
            if(ObjectUtils.isNotEmpty(dto.getGoodsIdList())){
                boost.in("id",dto.getGoodsIdList());
            }
            goodsInfos = repository.list(boost);
        }
        if(ObjectUtils.isNotEmpty(goodsInfos)){
            return ListUtil.listCover(GoodsInfoVO.ListVO.class,goodsInfos);
        }
        return new ArrayList<>();
    }

    private List<GoodsInfoVO.InnerServiceGoodsInfoVO> getInnerServiceGoodsInfo(Integer state,GoodsInfoDTO.IdsInnerServiceDTO dto) {
        List<GoodsInfoVO.InnerServiceGoodsInfoVO> list = new ArrayList<>();
        List<String> goodsIdList = new ArrayList<>();
        Map<String,GoodsInfoVO.InnerServiceGoodsInfoVO> voMap = new HashMap<>();
        List<GoodsInfo> goodsInfos = null;
        QueryWrapper<GoodsInfo> wrapperBoost = MybatisPlusUtil.query();
        if (ObjectUtils.isNotEmpty(state)){
            wrapperBoost.eq("goods_state",state);
        }
        if (dto.getQueryType().intValue() == GoodsQueryTypeEnum.商品id.getCode().intValue()) {
            if (ObjectUtils.isNotEmpty(dto.getGoodsIdList())){
                wrapperBoost.in("id",dto.getGoodsIdList());
                goodsInfos = repository.list(wrapperBoost);
            }
        }

        if (dto.getQueryType().intValue() == GoodsQueryTypeEnum.店铺id.getCode().intValue()) {
            if (ObjectUtils.isNotEmpty(dto.getShopIdList())){
                wrapperBoost.in("shop_id",dto.getShopIdList());
                goodsInfos = repository.list(wrapperBoost);
            }
        }
        if (ObjectUtils.isEmpty(goodsInfos)){
            return new ArrayList<>();
        }
        for(GoodsInfo goodsInfoItem:goodsInfos){
            GoodsInfoVO.InnerServiceGoodsInfoVO goodsInfoVO = new GoodsInfoVO.InnerServiceGoodsInfoVO();
            goodsInfoVO.setSkuList(new ArrayList<>());
            BeanUtils.copyProperties(goodsInfoItem,goodsInfoVO);
            list.add(goodsInfoVO);
            voMap.put(goodsInfoItem.getId(),goodsInfoVO);
            goodsIdList.add(goodsInfoItem.getId());
        }

        if(ObjectUtils.isNotEmpty(goodsIdList)){
            QueryWrapper<SkuGoodInfo> boost = MybatisPlusUtil.query();
            boost.in("good_id", goodsIdList);
            boost.eq("state",GoodsStateEnum.已上架.getCode());
            List<SkuGoodInfo> skuGoodInfos = skuGoodInfoRepository.list(boost);

            for(SkuGoodInfo skuGoodInfoItem:skuGoodInfos){
                GoodsInfoVO.InnerServiceGoodsInfoVO goodsInfoVO = voMap.get(skuGoodInfoItem.getGoodId());
                SkuGoodsInfoVO.ListVO listVO = new SkuGoodsInfoVO.ListVO();
                BeanUtils.copyProperties(skuGoodInfoItem,listVO);
                goodsInfoVO.getSkuList().add(listVO);
            }
        }
        return list;
    }

	@Override
	public List<ListVO> listGoodsData() {
		List<GoodsInfo> goodsInfos = repository.list();
		List<ListVO> retList = new ArrayList<ListVO>();
		
		if(CollectionUtil.isNotEmpty(goodsInfos))
			retList = com.gs.lshly.common.utils.BeanUtils.copyList(ListVO.class, goodsInfos);
		return retList;
	}

	@Override
	public PageData<ListVO> pageInGoods(QTO qto) {
		
        QueryWrapper<GoodsInfo> wrapper = MybatisPlusUtil.query();
        wrapper.eq("is_in_member_gift",TrueFalseEnum.是.getCode());
        IPage<GoodsInfo> page = MybatisPlusUtil.pager(qto);
        IPage<GoodsInfo> goodsInfoIPage = repository.page(page,wrapper);
        if (ObjectUtils.isEmpty(goodsInfoIPage) || ObjectUtils.isEmpty(goodsInfoIPage.getRecords())){
            return new PageData<>();
        }
        List<GoodsInfoVO.ListVO> categoryGoodsVOS = ListUtil.listCover(GoodsInfoVO.ListVO.class,goodsInfoIPage.getRecords());
        return new PageData<>(categoryGoodsVOS,qto.getPageNum(),qto.getPageSize(),goodsInfoIPage.getTotal());
	}
	
	
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkGoodsBatches(GoodsInfoDTO.CheckGoodsBatchesDTO dto) {
        if (null == dto || ObjectUtils.isEmpty(dto.getIdList())){
            throw new BusinessException("请选择要审核的商品！");
        }
        if (ObjectUtils.isEmpty(dto.getState())){
            throw new BusinessException("审核状态不能为空！");
        }
        if (dto.getState().equals(GoodsStateEnum.审核失败.getCode()) && StringUtils.isBlank(dto.getRefuseRemark())){
            throw new BusinessException("请填写拒绝原因！");
        }
        UpdateWrapper<SkuGoodInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("good_id",dto.getIdList());
        SkuGoodInfo skuGoodInfo = new SkuGoodInfo();
        skuGoodInfo.setState(dto.getState());
        skuGoodInfoRepository.update(skuGoodInfo,updateWrapper);

        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setGoodsState(dto.getState());
        QueryWrapper<GoodsInfo> goodsInfoQueryWrapper = new QueryWrapper<>();
        goodsInfoQueryWrapper.in("id",dto.getIdList());
        repository.update(goodsInfo,goodsInfoQueryWrapper);

        //审核后添加审核记录
        List<GoodsAuditRecord> auditRecords = new ArrayList<>();
        for (String goodsId : dto.getIdList()){
            GoodsAuditRecord auditRecord = new GoodsAuditRecord();
            BeanUtils.copyProperties(dto,auditRecord);
            //获取审核状态
            Integer state = null;
            if (dto.getState().equals( GoodsStateEnum.已上架.getCode())){
                state = GoodAuditRecordEnum.审核通过.getCode();
            }
            if (dto.getState().equals(GoodsStateEnum.审核失败.getCode())){
                state = GoodAuditRecordEnum.审核拒绝.getCode();
            }
            auditRecord.setState(state);
            auditRecord.setGoodsId(goodsId);
            auditRecord.setId("");
            auditRecords.add(auditRecord);
        }
        auditRecordRepository.saveBatch(auditRecords);

    }

    private String getTemplateName(String goodsId){
        QueryWrapper<GoodsTempalte> tempalteBoost = MybatisPlusUtil.query();
        tempalteBoost.eq("goods_id",goodsId);
        GoodsTempalte tempalte = goodsTempalteRepository.getOne(tempalteBoost);
        if (tempalte == null){
            throw new BusinessException("查询异常");
        }
        CommonStockTemplateVO.IdNameVO idNameVO = commonStockTemplateRpc.innerIdNameVO(tempalte.getTemplateId());
        if (null != idNameVO){
            return idNameVO.getTemplateName();
        }
        return null;
    }


}
