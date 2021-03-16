package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.MarketPtActivity;
import com.gs.lshly.biz.support.trade.entity.MarketPtActivityGoodsSku;
import com.gs.lshly.biz.support.trade.entity.MarketPtActivityGoodsSpu;
import com.gs.lshly.biz.support.trade.entity.MarketPtActivityMerchant;
import com.gs.lshly.biz.support.trade.entity.MarketPtActivityGoodsCategory;
import com.gs.lshly.biz.support.trade.repository.*;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketPtActivityMerchantService;
import com.gs.lshly.biz.support.trade.service.platadmin.IMarketPtActivityService;
import com.gs.lshly.common.enums.ActivitySignEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchSkuGoodInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsCategoryVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchSkuGoodInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtActivityGoodsSpuDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtActivityMerchantDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMarketPtActivityMerchantQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtActivityMerchantVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.MarketPtActivityDTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtActivityVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsCategoryRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsInfoRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminSkuGoodInfoRpc;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  服务实现类
 *
 *  活动记录
* </p>
* @author zdf
* @since 2020-12-01
*/
@Component
@Slf4j
public class PCMerchMarketPtActivityMerchantServiceImpl implements IPCMerchMarketPtActivityMerchantService {

    @Autowired
    private IMarketPtActivityMerchantRepository repository;

    @Autowired
    private IMarketPtActivityGoodsSpuRepository goodsSpuRepository;

    @Autowired
    private IMarketPtActivityGoodsSkuRepository goodsSkuRepository;
    @Autowired
    private IMarketPtActivityService iMarketPtActivityService;
    @Autowired
    private IMarketPtActivityRepository iMarketPtActivityRepository;
    @Autowired
    private IMarketPtActivityGoodsCategoryRepository iMarketPtActivityGoodsCategoryRepository;
    @Autowired
    private IMarketPtActivityGoodsSpuRepository iMarketPtActivityGoodsSpuRepository;
    @Autowired
    private IMarketPtActivityGoodsSkuRepository iMarketPtActivityGoodsSkuRepository;
    @Autowired
    private IMarketPtActivityMerchantRepository iMarketPtActivityMerchantRepository;
    @DubboReference
    private IPCMerchAdminGoodsInfoRpc ipcMerchAdminGoodsInfoRpc;
    @DubboReference
    private IPCMerchAdminSkuGoodInfoRpc ipcMerchAdminSkuGoodInfoRpc;
    @DubboReference
    private IPCMerchAdminGoodsCategoryRpc ipcMerchAdminGoodsCategoryRpc;
    @Override
    public PageData<PCMerchMarketPtActivityMerchantVO.ListVO> pageData(PCMerchMarketPtActivityMerchantQTO.QTO qto) {
        QueryWrapper<MarketPtActivityMerchant> wrapper = new QueryWrapper<>();
        IPage<MarketPtActivityMerchant> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto,PCMerchMarketPtActivityMerchantVO.ListVO.class, page);
    }

    @Override
    public void addMarketPtActivityMerchant(PCMerchMarketPtActivityMerchantDTO.ETO eto) {
        MarketPtActivityMerchant marketPtActivityMerchant = new MarketPtActivityMerchant();
        BeanUtils.copyProperties(eto, marketPtActivityMerchant);
        marketPtActivityMerchant.setShopId(eto.getJwtShopId());
        marketPtActivityMerchant.setMerchantId(eto.getJwtMerchantId());
        repository.save(marketPtActivityMerchant);
    }


    @Override
    public void deleteMarketPtActivityMerchant(PCMerchMarketPtActivityMerchantDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editMarketPtActivityMerchant(PCMerchMarketPtActivityMerchantDTO.ETO eto) {
        MarketPtActivityMerchant marketPtActivityMerchant = new MarketPtActivityMerchant();
        BeanUtils.copyProperties(eto, marketPtActivityMerchant);
        repository.updateById(marketPtActivityMerchant);
    }

    @Override
    public PCMerchMarketPtActivityMerchantVO.DetailVO detailMarketPtActivityMerchant(PCMerchMarketPtActivityMerchantDTO.IdDTO dto) {
        MarketPtActivityMerchant marketPtActivityMerchant = repository.getById(dto.getId());
        PCMerchMarketPtActivityMerchantVO.DetailVO detailVo = new PCMerchMarketPtActivityMerchantVO.DetailVO();
        if(ObjectUtils.isEmpty(marketPtActivityMerchant)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(marketPtActivityMerchant, detailVo);
        return detailVo;
    }

    @Override
    public List<PCMerchMarketPtActivityMerchantVO.ListVO> queryCountRecord(PCMerchMarketPtActivityMerchantDTO.isRecordDTO dto) {
        QueryWrapper<MarketPtActivityMerchant> Wrapper = new QueryWrapper<>();
        Wrapper.eq(" activity_id",dto.getActivityId()).eq("shop_id",dto.getShopId()).eq("merchant_id",dto.getMerchantId());
        List<MarketPtActivityMerchant> marketPtActivityMerchants = repository.getBaseMapper().selectList(Wrapper);
        List<PCMerchMarketPtActivityMerchantVO.ListVO> listVOS = new ArrayList<>();
        for (MarketPtActivityMerchant marketPtActivityMerchant:marketPtActivityMerchants){
            PCMerchMarketPtActivityMerchantVO.ListVO listVO = new PCMerchMarketPtActivityMerchantVO.ListVO();
            BeanUtils.copyProperties(marketPtActivityMerchant,listVO);
            listVOS.add(listVO);
        }
        return listVOS;
    }

    @Override
    public PageData<PCMerchMarketPtActivityMerchantVO.MyMerchantActivity> queryMyList(PCMerchMarketPtActivityMerchantQTO.QTO qto) {
        QueryWrapper<MarketPtActivityMerchant> Wrapper = new QueryWrapper<>();
        Wrapper.eq("shop_id",qto.getJwtShopId()).eq("merchant_id",qto.getJwtMerchantId());
        List<MarketPtActivityMerchant> marketPtActivityMerchants = repository.getBaseMapper().selectList(Wrapper);
        List<PCMerchMarketPtActivityMerchantVO.MyMerchantActivity> listVOS = new ArrayList<>();
        Long now = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        for (MarketPtActivityMerchant marketPtActivityMerchant:marketPtActivityMerchants){
            PCMerchMarketPtActivityMerchantVO.MyMerchantActivity listVO = new PCMerchMarketPtActivityMerchantVO.MyMerchantActivity();
            BeanUtils.copyProperties(marketPtActivityMerchant,listVO);
            //设置日期格式
            DateTimeFormatter sdf=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            listVO.setSignTime(marketPtActivityMerchant.getSignStartTime().format(sdf)+"~"+marketPtActivityMerchant.getSignStartTime().format(sdf));
            listVO.setActivityTime(marketPtActivityMerchant.getActivityStartTime().format(sdf)+"~"+marketPtActivityMerchant.getActivityEndTime().format(sdf));
            if (qto.getIsHistory()==1){
                System.out.println("历史报名");
                Long end = marketPtActivityMerchant.getActivityEndTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
                if (end<now){
                    listVOS.add(listVO);
                }
            }else {
                listVOS.add(listVO);
            }
        }
        return new PageData<>(listVOS,qto.getPageNum(),qto.getPageSize(),listVOS.size());
    }

    @Override
    @Transactional
    public void merchantActivitySign(PCMerchMarketPtActivityGoodsSpuDTO.Sign dto) {
        /**
         * //添加活动记录表
         * */
        //获取活动ID
        QueryWrapper<MarketPtActivityMerchant> query = MybatisPlusUtil.query();
        query.and(i->i.eq("shop_id",dto.getJwtShopId()));
        query.and(i->i.eq("activity_id",dto.getActivityId()));
        List<MarketPtActivityMerchant> list1 = iMarketPtActivityMerchantRepository.list(query);
        if (list1.size()>0){
            throw new BusinessException("此商家已参与该活动");
        }
        String activityId = dto.getActivityId();
        MarketPtActivity activity = iMarketPtActivityRepository.getById(activityId);
        MarketPtActivityMerchant marketPtActivityMerchant = new MarketPtActivityMerchant();
        BeanUtils.copyProperties(activity,marketPtActivityMerchant);
        marketPtActivityMerchant.setId(null);
        marketPtActivityMerchant.setActivityId(activity.getId());
        marketPtActivityMerchant.setActivityEndTime(activity.getActivityEndTime()).
                setActivityStartTime(activity.getActivityStartTime()).
                setSignEndTime(activity.getSignEndTime()).
                setSignStartTime(activity.getSignStartTime()).
                setName(activity.getName()).
                setLabel(activity.getLabel()).
                setActivityDescribe(activity.getActivityDescribe()).
                setUserBuyMax(activity.getBuyMax()).
                setShopGoodsMax(activity.getGoodsMax()).
                setCoverImage(activity.getCoverImage()).
                setSmsBefore(activity.getSmsBefore()).
                setSmsIsTell(activity.getSmsIsTell());
        marketPtActivityMerchant.
                setShopId(dto.getJwtShopId()).
                setMerchantId(dto.getJwtMerchantId()).
                setState(ActivitySignEnum.待审核.getCode().toString());
        repository.save(marketPtActivityMerchant);
        //添加商品报名表
        if (ObjectUtils.isNotEmpty(dto.getGoodsAll())) {
            for (PCMerchMarketPtActivityGoodsSpuDTO.SignGoods goods : dto.getGoodsAll()) {
                MarketPtActivityGoodsSpu goodsSup = new MarketPtActivityGoodsSpu();
                goodsSup.setActivityDescribe(marketPtActivityMerchant.getActivityDescribe()).
                        setGoodsId(goods.getGoodsId()).
                        setActivityId(activityId).
                        setLabel(marketPtActivityMerchant.getLabel()).
                        setName(marketPtActivityMerchant.getName()).
                        setShopId(dto.getJwtShopId()).
                        setMerchantId(dto.getJwtMerchantId()).
                        setActivitySalePrice(ObjectUtils.isNotEmpty(goods.getActivitySalePrice())?goods.getActivitySalePrice():BigDecimal.ZERO);
                QueryWrapper<MarketPtActivityGoodsSpu> wrapper = new QueryWrapper<>();
                wrapper.eq("shop_id", goodsSup.getShopId()).
                        eq("goods_id", goodsSup.getGoodsId());
                List<MarketPtActivityGoodsSpu> list = goodsSpuRepository.list(wrapper);
                if (list.size() > 0) {
                    Boolean flag = true;
                    Long now = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
                    for (MarketPtActivityGoodsSpu goodsTime : list) {
                        String timeout = goodsTime.getActivityId();
                        MarketPtActivity activityRepositoryById = iMarketPtActivityRepository.getById(timeout);
                        if (ObjectUtils.isNotEmpty(activityRepositoryById)) {
                            Long endtime = activityRepositoryById.getActivityEndTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
                            if (now < endtime) {
                                flag = false;
                            }
                        }
                    }
                    if (flag) {
                        goodsSpuRepository.save(goodsSup);
                        SetSku(goodsSup,dto,activityId);
                    } else {
                        throw new BusinessException("该商品正在参加其它的活动");
                    }
                } else {
                    goodsSpuRepository.save(goodsSup);
                    SetSku(goodsSup,dto,activityId);
                }
            }
        }


    }

    private void SetSku(MarketPtActivityGoodsSpu goodsSup, PCMerchMarketPtActivityGoodsSpuDTO.Sign dto,String activityId) {
        //添加商品SPU表
        if (ObjectUtils.isNotEmpty(dto.getGoodsSPUAll())) {
            for (PCMerchMarketPtActivityGoodsSpuDTO.SignGoodsSku goodsSku : dto.getGoodsSPUAll()) {
                MarketPtActivityGoodsSku goodsSKKu = new MarketPtActivityGoodsSku();
                goodsSKKu.setActivityId(activityId).
                        setActivitySaleSkuPrice(goodsSku.getActivitySalePrice()).
                        setGoodsId(goodsSku.getGoodsId()).
                        setGoodsSpuItemId(goodsSup.getId()).
                        setMerchantId(dto.getJwtMerchantId()).
                        setShopId(dto.getJwtShopId()).
                        setSkuId(goodsSku.getSkuId());
                goodsSkuRepository.save(goodsSKKu);
            }
        }
    }

    @Override
    public MarketPtActivityVO.MerchantViewDetails viewMyOrHistoryDetails(PCMerchMarketPtActivityMerchantDTO.IdDTO dto) {
        //获取商家参与记录
        MarketPtActivityMerchant signRecord = repository.getById(dto.getId());
        //获取活动信息
        MarketPtActivityVO.CheckActivity checkActivity= GetActivityInformation(signRecord.getActivityId());
        //参加活动商品
        List<MarketPtActivityVO.ActivityGoods> activityGoods = ParticipateActivityGoods(dto, signRecord.getActivityId());
        return new MarketPtActivityVO.MerchantViewDetails(checkActivity,activityGoods);
    }

    private MarketPtActivityVO.CheckActivity GetActivityInformation(String activityId) {
        MarketPtActivity activityInfo = iMarketPtActivityRepository.getById( activityId);
        MarketPtActivityVO.CheckActivity checkActivity = new MarketPtActivityVO.CheckActivity();
        BeanUtils.copyProperties(activityInfo,checkActivity);
        QueryWrapper<MarketPtActivityGoodsCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("activity_id", activityId);
        List<MarketPtActivityGoodsCategory> list = iMarketPtActivityGoodsCategoryRepository.list(wrapper);
        if (ObjectUtils.isNotEmpty(list)) {
            StringBuffer categoryString = new StringBuffer();
            List<MarketPtActivityVO.CategoryJoinSearchVO> caList=new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                PCMerchGoodsCategoryVO.innerCategoryVO innerCategoryVO = ipcMerchAdminGoodsCategoryRpc.innerGetListVo(list.get(i).getCategoryId());

                if (ObjectUtils.isNotEmpty(innerCategoryVO)){
                    MarketPtActivityVO.CategoryJoinSearchVO categoryJoinSearchVO = new MarketPtActivityVO.CategoryJoinSearchVO();
                    categoryJoinSearchVO.setGsCategoryName(innerCategoryVO.getGsCategoryName());
                    categoryJoinSearchVO.setId(list.get(i).getCategoryId());
                    caList.add(categoryJoinSearchVO);
                }
                if (i == list.size() - 1) {
                    categoryString.append(list.get(i).getCategoryId());
                } else {
                    categoryString.append(list.get(i).getCategoryId()).append(",");
                }
            }
            checkActivity.setActivityCategory(categoryString.toString());
            checkActivity.setActivityCategoryName(caList);
        }
        return checkActivity;
    }

    private List<MarketPtActivityVO.ActivityGoods> ParticipateActivityGoods(PCMerchMarketPtActivityMerchantDTO.IdDTO dto, String activityId) {
        List<MarketPtActivityVO.ActivityGoods> activityGoodsList=new ArrayList<>();

        QueryWrapper<MarketPtActivityGoodsSpu> wrapper = new QueryWrapper<>();
        wrapper.eq("activity_id",activityId).
                eq("shop_id",dto.getJwtShopId());
        List<MarketPtActivityGoodsSpu> spuList = iMarketPtActivityGoodsSpuRepository.list(wrapper);
        if (ObjectUtils.isNotEmpty(spuList)) {
            for (MarketPtActivityGoodsSpu goodsId : spuList) {
                MarketPtActivityVO.ActivityGoods activityGoods = new MarketPtActivityVO.ActivityGoods();
                List<String> strings = new ArrayList<>();
                strings.add(goodsId.getGoodsId());
                List<PCMerchGoodsInfoVO.InnerServiceGoodsInfoVO> innerServiceGoodsInfoVOS=null;
                if (ObjectUtils.isNotEmpty(strings)) {
                    innerServiceGoodsInfoVOS = ipcMerchAdminGoodsInfoRpc.innerServiceAllGoodsInfo(new PCMerchGoodsInfoDTO.IdsInnerServiceDTO(strings, null, 10));
                }
                PCMerchGoodsInfoVO.InnerServiceGoodsInfoVO innerServiceGoodsInfoVO=null;
                if (ObjectUtils.isNotEmpty(innerServiceGoodsInfoVOS)){
                    innerServiceGoodsInfoVO = innerServiceGoodsInfoVOS.get(0);
                }
                if (ObjectUtils.isNotEmpty(innerServiceGoodsInfoVO)){
                    activityGoods .setImageUrl(innerServiceGoodsInfoVO.getGoodsImage()).
                            setName(innerServiceGoodsInfoVO.getGoodsName()).
                            setSalePrice(innerServiceGoodsInfoVO.getSalePrice());
                }
                QueryWrapper<MarketPtActivityGoodsSku> query = MybatisPlusUtil.query();
                query.and(i->i.eq("goods_spu_item_id",goodsId.getId()));
                List<MarketPtActivityGoodsSku> list = goodsSkuRepository.list(query);
                if (ObjectUtils.isNotEmpty(list)) {
                    List<MarketPtActivityVO.ActivityGoodsSku> skuList=new ArrayList<>();
                    for (MarketPtActivityGoodsSku marketPtActivityGoodsSku : list) {

                        if (ObjectUtils.isNotEmpty(innerServiceGoodsInfoVO)){
                            for (PCMerchSkuGoodInfoVO.ListVO listVO : innerServiceGoodsInfoVO.getSkuList()) {
                                if (listVO.getId().equals(marketPtActivityGoodsSku.getSkuId())){
                                    System.out.println(listVO+"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                                    MarketPtActivityVO.ActivityGoodsSku activityGoodsSku = new MarketPtActivityVO.ActivityGoodsSku();
                                    activityGoodsSku.setActivitySaleSkuPrice(marketPtActivityGoodsSku.getActivitySaleSkuPrice()).
                                            setId(marketPtActivityGoodsSku.getId()).
                                            setImageUrl(listVO.getImage()).
                                            setSpecsValue(listVO.getSpecsValue()).
                                            setName(innerServiceGoodsInfoVO.getGoodsName());
                                    skuList.add(activityGoodsSku);
                                }
                            }
                        }
                    }
                    activityGoods.setSkuInfo(skuList);

                }
                activityGoods.setActivitySalePrice(goodsId.getActivitySalePrice()).
                        setId(goodsId.getId());
                activityGoodsList.add(activityGoods);
            }
        }

        return activityGoodsList;
    }

    @Override
    public MarketPtActivityVO.MerchantViewDetails viewActivityListDetails(MarketPtActivityDTO.IdDTO dto) {
        QueryWrapper<MarketPtActivityMerchant> wrapper = new QueryWrapper<>();
        wrapper.eq("activity_id",dto.getId()).
                eq("shop_id",dto.getJwtShopId()).
                eq("merchant_id",dto.getJwtMerchantId());
        MarketPtActivityMerchant one = repository.getOne(wrapper);
        MarketPtActivityVO.CheckActivity checkActivity = GetActivityInformation(dto.getId());
        if (!ObjectUtils.isEmpty(one)){
            List<MarketPtActivityVO.ActivityGoods> activityGoods = ParticipateActivityGoods(new PCMerchMarketPtActivityMerchantDTO.IdDTO(one.getId()), dto.getId());
            return new MarketPtActivityVO.MerchantViewDetails(checkActivity,activityGoods);
        }
        return new MarketPtActivityVO.MerchantViewDetails(checkActivity,null);
    }
}
