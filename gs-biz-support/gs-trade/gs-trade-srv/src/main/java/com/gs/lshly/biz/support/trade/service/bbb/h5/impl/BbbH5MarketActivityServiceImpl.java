package com.gs.lshly.biz.support.trade.service.bbb.h5.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.*;
import com.gs.lshly.biz.support.trade.enums.PlatformCardCheckStatusEnum;
import com.gs.lshly.biz.support.trade.repository.*;
import com.gs.lshly.biz.support.trade.service.bbb.h5.IBbbH5MarketActivityService;
import com.gs.lshly.common.enums.ActivitySignEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5MarketActivityDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5MarketMerchantActivityDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5MarketActivityQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5MarketActivityVO;
import com.gs.lshly.common.struct.bbb.pc.merchant.vo.BbbShopVO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketActivityVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbb.h5.commodity.IBbbH5GoodsInfoRpc;
import com.gs.lshly.rpc.api.bbb.pc.merchant.IBbbShopRpc;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


/**
* <p>
*  服务实现类
* </p>
* @author zdf
* @since 2021-01-08
*/
@Component
public class BbbH5MarketActivityServiceImpl implements IBbbH5MarketActivityService {

    @Autowired
    private IMarketMerchantCardUsersRepository repository;
    @Autowired
    private IMarketMerchantCutRepository iMarketMerchantCutRepository;
    @Autowired
    private IMarketMerchantDiscountRepository iMarketMerchantDiscountRepository;
    @Autowired
    private IMarketMerchantGiftRepository iMarketMerchantGiftRepository;
    @Autowired
    private IMarketMerchantGiftGoodsGiveRepository iMarketMerchantGiftGoodsGiveRepository;
    @Autowired
    private IMarketMerchantGroupbuyRepository iMarketMerchantGroupbuyRepository;
    @Autowired
    private IMarketPtActivityRepository iMarketPtActivityRepository;
    @Autowired
    private IMarketPtActivityGoodsSkuRepository iMarketPtActivityGoodsSkuRepository;
    @Autowired
    private IMarketPtActivityGoodsSpuRepository iMarketPtActivityGoodsSpuRepository;
    @Autowired
    private IMarketPtActivityMerchantRepository iMarketPtActivityMerchantRepository;
    @Autowired
    private IMarketMerchantDiscountGoodsRepository iMarketMerchantDiscountGoodsRepository;
    @Autowired
    private IMarketMerchantCutGoodsRepository iMarketMerchantCutGoodsRepository;
    @Autowired
    private IMarketMerchantGroupbuyGoodsRepository iMarketMerchantGroupbuyGoodsRepository;
    @Autowired
    private IMarketMerchantGiftGoodsRepository iMarketMerchantGiftGoodsRepository;
    @Autowired
    private IMarketMerchantCardRepository iMarketMerchantCardRepository;
    @Autowired
    private IMarketMerchantCardUsersRepository iMarketMerchantCardUsersRepository;
    @Autowired
    private IMarketPtActivityJurisdictionRepository iMarketPtActivityJurisdictionRepository;
    @DubboReference
    private IBbbH5GoodsInfoRpc iBbbH5GoodsInfoRpc;
    @DubboReference
    private IBbbShopRpc iBbbShopRpc;

    @Value("${activity.pc.cut}")
    private String pcCut;
    @Value("${activity.pc.gift}")
    private String pcGift;
    @Value("${activity.pc.groupbuy}")
    private String pcGroupbuy;
    @Value("${activity.pc.discount}")
    private String pcDiscount;

    @Value("${activity.h5.cut}")
    private String h5Cut;
    @Value("${activity.h5.gift}")
    private String h5Gift;
    @Value("${activity.h5.groupbuy}")
    private String h5Groupbuy;
    @Value("${activity.h5.discount}")
    private String h5Discount;

    @Override
    public PageData<BbbH5MarketActivityVO.cutVO> cutList(BbbH5MarketActivityQTO.QTO qto) {
        IPage<BbbH5MarketActivityVO.cutVO> pager = MybatisPlusUtil.pager(qto);
        QueryWrapper<BbbH5MarketActivityQTO.QTO> query = MybatisPlusUtil.query();
        query.and(i->i.likeRight("cut.`terminal`",10));
        if (ObjectUtils.isNotEmpty(qto.getShopId())) {
            query.and(i -> i.eq("cut.shop_id",qto.getShopId() ));
        }
        query.and(i->i.le("cut.`valid_start_time`",LocalDateTime.now()).ge("cut.`valid_end_time`",LocalDateTime.now()));
        iMarketMerchantCutRepository.selectCutH5ListPage(pager, query);
        List<BbbH5MarketActivityVO.cutVO> listVOS=new ArrayList<>();
        List<BbbH5GoodsInfoVO.HomeInnerServiceVO>  goodsInfoVOS=null;
        List<String> goodsIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(pager)){
            for (BbbH5MarketActivityVO.cutVO cutVO : pager.getRecords()) {
                BbbH5MarketActivityVO.cutVO cutVO1 = new BbbH5MarketActivityVO.cutVO();
                BeanUtils.copyProperties(cutVO,cutVO1);
                listVOS.add(cutVO1);
                goodsIds.add(cutVO.getGoodsId());
            }
            if (ObjectUtils.isNotEmpty(goodsIds)){
                goodsInfoVOS = iBbbH5GoodsInfoRpc.getHomeGoodsInnerServiceVO(goodsIds, qto);
            }
            if (ObjectUtils.isNotEmpty(listVOS) && ObjectUtils.isNotEmpty(goodsInfoVOS)){
                SetGoodsInfo(listVOS,goodsInfoVOS);
            }

        }
        return new PageData<>(listVOS,qto.getPageNum(),qto.getPageSize(),pager.getTotal());
    }

    @Override
    public List<BbbH5MarketActivityVO.cutVO> viewFour(BaseDTO dto) {
        IPage<BbbH5MarketActivityVO.cutVO> pager = MybatisPlusUtil.pager(new BbbH5MarketActivityQTO.QTO());
        QueryWrapper<BbbH5MarketActivityQTO.QTO> query = MybatisPlusUtil.query();
        query.and(i->i.likeRight("cut.`terminal`",10));
        query.and(i->i.le("cut.`valid_start_time`",LocalDateTime.now()).ge("cut.`valid_end_time`",LocalDateTime.now()));
        iMarketMerchantCutRepository.selectCutH5ListPage(pager, query);
        List<BbbH5MarketActivityVO.cutVO> listVOS=new ArrayList<>();
        List<BbbH5GoodsInfoVO.HomeInnerServiceVO>  goodsInfoVOS=null;
        List<String> goodsIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(pager)){
            for (BbbH5MarketActivityVO.cutVO cutVO : pager.getRecords()) {
                BbbH5MarketActivityVO.cutVO cutVO1 = new BbbH5MarketActivityVO.cutVO();
                BeanUtils.copyProperties(cutVO,cutVO1);
                listVOS.add(cutVO1);
                goodsIds.add(cutVO.getGoodsId());
            }
            if (ObjectUtils.isNotEmpty(goodsIds)){
                goodsInfoVOS = iBbbH5GoodsInfoRpc.getHomeGoodsInnerServiceVO(goodsIds, dto);
            }
            if (ObjectUtils.isNotEmpty(listVOS) && ObjectUtils.isNotEmpty(goodsInfoVOS)){
                SetGoodsInfo(listVOS,goodsInfoVOS);
            }

        }
        if (listVOS.size()>4){
            List<BbbH5MarketActivityVO.cutVO> list=new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                list.add(getValue(listVOS));
            }
            return list;
        }else {
            return listVOS;
        }
    }

    @Override
    public PageData<BbbH5MarketActivityVO.discountVO> discountList(BbbH5MarketActivityQTO.QTO qto) {
        IPage<BbbH5MarketActivityVO.discountVO> pager = MybatisPlusUtil.pager(new BbbH5MarketActivityQTO.QTO());
        QueryWrapper<BbbH5MarketActivityQTO.QTO> query = MybatisPlusUtil.query();
        query.and(i->i.likeRight("discount.`terminal`","10"));
        if (ObjectUtils.isNotEmpty(qto.getShopId())) {
            query.and(i -> i.eq("discount.shop_id",qto.getShopId() ));
        }
        query.and(i->i.le("discount.`valid_start_time`",LocalDateTime.now()).ge("discount.`valid_end_time`",LocalDateTime.now()));
        iMarketMerchantDiscountRepository.selectDiscountH5ListPage(pager,query);
        List<BbbH5MarketActivityVO.discountVO> listVOS=new ArrayList<>();
        List<BbbH5GoodsInfoVO.HomeInnerServiceVO>  goodsInfoVOS=null;
        List<String> goodsIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(pager)){
            for (BbbH5MarketActivityVO.discountVO cutVO : pager.getRecords()) {
                BbbH5MarketActivityVO.discountVO cutVO1 = new BbbH5MarketActivityVO.discountVO();
                BeanUtils.copyProperties(cutVO,cutVO1);
                listVOS.add(cutVO1);
                goodsIds.add(cutVO.getGoodsId());
            }
            if (ObjectUtils.isNotEmpty(goodsIds)){
                goodsInfoVOS = iBbbH5GoodsInfoRpc.getHomeGoodsInnerServiceVO(goodsIds, qto);
            }
            if (ObjectUtils.isNotEmpty(listVOS) && ObjectUtils.isNotEmpty(goodsInfoVOS)){
                SetGoodsInfo(listVOS,goodsInfoVOS);
            }

        }
        return new PageData<>(listVOS,qto.getPageNum(),qto.getPageSize(),pager.getTotal());
    }

    @Override
    public List<BbbH5MarketActivityVO.discountVO> discountViewFour(BaseDTO dto) {
        IPage<BbbH5MarketActivityVO.discountVO> pager = MybatisPlusUtil.pager(new BbbH5MarketActivityQTO.QTO());
        QueryWrapper<BbbH5MarketActivityQTO.QTO> query = MybatisPlusUtil.query();
        query.and(i->i.likeRight("discount.`terminal`","10"));
        query.and(i->i.le("discount.`valid_start_time`",LocalDateTime.now()).ge("discount.`valid_end_time`",LocalDateTime.now()));
        iMarketMerchantDiscountRepository.selectDiscountH5ListPage(pager,query);
        List<BbbH5MarketActivityVO.discountVO> listVOS=new ArrayList<>();
        List<BbbH5GoodsInfoVO.HomeInnerServiceVO>  goodsInfoVOS=null;
        List<String> goodsIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(pager)){
            for (BbbH5MarketActivityVO.discountVO cutVO : pager.getRecords()) {
                BbbH5MarketActivityVO.discountVO cutVO1 = new BbbH5MarketActivityVO.discountVO();
                BeanUtils.copyProperties(cutVO,cutVO1);
                listVOS.add(cutVO1);
                goodsIds.add(cutVO.getGoodsId());
            }
            if (ObjectUtils.isNotEmpty(goodsIds)){
                goodsInfoVOS = iBbbH5GoodsInfoRpc.getHomeGoodsInnerServiceVO(goodsIds, dto);
            }
            if (ObjectUtils.isNotEmpty(listVOS) && ObjectUtils.isNotEmpty(goodsInfoVOS)){
                SetGoodsInfo(listVOS,goodsInfoVOS);
            }

        }
        if (listVOS.size() > 4) {
            List<BbbH5MarketActivityVO.discountVO> list = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                list.add(getValue(listVOS));
            }
            return list;
        } else {
            return listVOS;
        }
    }

    @Override
    public PageData<BbbH5MarketActivityVO.giftVO> giftList(BbbH5MarketActivityQTO.QTO qto) {
        IPage<BbbH5MarketActivityVO.giftVO> pager = MybatisPlusUtil.pager(new BbbH5MarketActivityQTO.QTO());
        QueryWrapper<BbbH5MarketActivityQTO.QTO> query = MybatisPlusUtil.query();
        query.and(i->i.likeRight("gift.`terminal`","10"));
        if (ObjectUtils.isNotEmpty(qto.getShopId())) {
            query.and(i -> i.eq("gift.shop_id",qto.getShopId() ));
        }
        query.and(i->i.le("gift.`valid_start_time`",LocalDateTime.now()).ge("gift.`valid_end_time`",LocalDateTime.now()));
        iMarketMerchantGiftRepository.selectGiftH5ListPage(pager,query);
        List<BbbH5MarketActivityVO.giftVO> listVOS=new ArrayList<>();
        List<BbbH5GoodsInfoVO.HomeInnerServiceVO>  goodsInfoVOS=null;
        List<String> goodsIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(pager)){
            for (BbbH5MarketActivityVO.giftVO cutVO : pager.getRecords()) {
                BbbH5MarketActivityVO.giftVO cutVO1 = new BbbH5MarketActivityVO.giftVO();
                BeanUtils.copyProperties(cutVO,cutVO1);
                listVOS.add(cutVO1);
                goodsIds.add(cutVO.getGoodsId());
            }
            if (ObjectUtils.isNotEmpty(goodsIds)){
                goodsInfoVOS = iBbbH5GoodsInfoRpc.getHomeGoodsInnerServiceVO(goodsIds, qto);
            }
            if (ObjectUtils.isNotEmpty(listVOS) && ObjectUtils.isNotEmpty(goodsInfoVOS)){
                SetGoodsInfo(listVOS,goodsInfoVOS);
            }
        }
        if (ObjectUtils.isNotEmpty(listVOS)){
            for (int i = 0; i < listVOS.size(); i++) {
                if (ObjectUtils.isNotEmpty(listVOS.get(i).getGoodsName())){
                    //已上架的商品
                    //查询赠品
                    String id = listVOS.get(i).getId();
                    QueryWrapper<MarketMerchantGiftGoodsGive> query1 = MybatisPlusUtil.query();
                    query1.and(e->e.eq("gift_id",id));
                    List<MarketMerchantGiftGoodsGive> list = iMarketMerchantGiftGoodsGiveRepository.list(query1);
                    List<BbbH5MarketActivityVO.giftVO.giftGiveVO> giftGiveVOS=new ArrayList<>();
                    if (ObjectUtils.isNotEmpty(list)){
                        for (MarketMerchantGiftGoodsGive marketMerchantGiftGoodsGive : list) {
                            BbbH5MarketActivityVO.giftVO.giftGiveVO giftGiveVO = new BbbH5MarketActivityVO.giftVO.giftGiveVO();
                            BbbH5GoodsInfoVO.InnerServiceVO innerServiceVO = iBbbH5GoodsInfoRpc.innerSimpleServiceVO(marketMerchantGiftGoodsGive.getSkuId());
                            giftGiveVO.setGiftGiveSkuId(marketMerchantGiftGoodsGive.getSkuId());
                            if (ObjectUtils.isNotEmpty(innerServiceVO)){
                                giftGiveVO.setGiftGiveName(innerServiceVO.getGoodsName());
                                giftGiveVO.setGiftGoodsId(innerServiceVO.getGoodsId());
                            }
                            giftGiveVOS.add(giftGiveVO);
                        }
                    }
                    listVOS.get(i).setGiftGiveVOList(giftGiveVOS);
                }
            }
        }
        return new PageData<>(listVOS,qto.getPageNum(),qto.getPageSize(),pager.getTotal());
    }

    @Override
    public List<BbbH5MarketActivityVO.giftVO> giftViewFour(BaseDTO dto) {
        IPage<BbbH5MarketActivityVO.giftVO> pager = MybatisPlusUtil.pager(new BbbH5MarketActivityQTO.QTO());
        QueryWrapper<BbbH5MarketActivityQTO.QTO> query = MybatisPlusUtil.query();
        query.and(i->i.likeRight("gift.`terminal`","10"));
        query.and(i->i.le("gift.`valid_start_time`",LocalDateTime.now()).ge("gift.`valid_end_time`",LocalDateTime.now()));
        iMarketMerchantGiftRepository.selectGiftH5ListPage(pager,query);
        List<BbbH5MarketActivityVO.giftVO> listVOS=new ArrayList<>();
        List<BbbH5GoodsInfoVO.HomeInnerServiceVO>  goodsInfoVOS=null;
        List<String> goodsIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(pager)){
            for (BbbH5MarketActivityVO.giftVO cutVO : pager.getRecords()) {
                BbbH5MarketActivityVO.giftVO cutVO1 = new BbbH5MarketActivityVO.giftVO();
                BeanUtils.copyProperties(cutVO,cutVO1);
                listVOS.add(cutVO1);
                goodsIds.add(cutVO.getGoodsId());
            }
            if (ObjectUtils.isNotEmpty(goodsIds)){
                goodsInfoVOS = iBbbH5GoodsInfoRpc.getHomeGoodsInnerServiceVO(goodsIds, dto);
            }
            if (ObjectUtils.isNotEmpty(listVOS) && ObjectUtils.isNotEmpty(goodsInfoVOS)){
                SetGoodsInfo(listVOS,goodsInfoVOS);
            }
        }
        if (ObjectUtils.isNotEmpty(listVOS)){
            for (int i = 0; i < listVOS.size(); i++) {
                if (ObjectUtils.isNotEmpty(listVOS.get(i).getGoodsName())){
                    //已上架的商品
                    //查询赠品
                    String id = listVOS.get(i).getId();
                    QueryWrapper<MarketMerchantGiftGoodsGive> query1 = MybatisPlusUtil.query();
                    query1.and(e->e.eq("gift_id",id));
                    List<MarketMerchantGiftGoodsGive> list = iMarketMerchantGiftGoodsGiveRepository.list(query1);
                    List<BbbH5MarketActivityVO.giftVO.giftGiveVO> giftGiveVOS=new ArrayList<>();
                    if (ObjectUtils.isNotEmpty(list)){
                        for (MarketMerchantGiftGoodsGive marketMerchantGiftGoodsGive : list) {
                            BbbH5MarketActivityVO.giftVO.giftGiveVO giftGiveVO = new BbbH5MarketActivityVO.giftVO.giftGiveVO();
                            BbbH5GoodsInfoVO.InnerServiceVO innerServiceVO = iBbbH5GoodsInfoRpc.innerSimpleServiceVO(marketMerchantGiftGoodsGive.getSkuId());
                            giftGiveVO.setGiftGiveSkuId(marketMerchantGiftGoodsGive.getSkuId());
                            if (ObjectUtils.isNotEmpty(innerServiceVO)){
                                giftGiveVO.setGiftGiveName(innerServiceVO.getGoodsName());
                                giftGiveVO.setGiftGoodsId(innerServiceVO.getGoodsId());
                            }
                            giftGiveVOS.add(giftGiveVO);
                        }
                    }
                    listVOS.get(i).setGiftGiveVOList(giftGiveVOS);
                }
            }
        }
        if (listVOS.size()>4){
            List<BbbH5MarketActivityVO.giftVO> list=new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                list.add(getValue(listVOS));
            }
            return list;

        }else {
            return listVOS;
        }
    }

    @Override
    public List<BbbH5MarketActivityVO.groupbuyVO> groupbuyViewFour(BaseDTO dto) {
        IPage<BbbH5MarketActivityVO.groupbuyVO> pager = MybatisPlusUtil.pager(new BbbH5MarketActivityQTO.QTO());
        QueryWrapper<BbbH5MarketActivityQTO.QTO> query = MybatisPlusUtil.query();
        query.and(i->i.likeRight("groupbuy.`terminal`","10"));
        query.and(i->i.le("groupbuy.`valid_start_time`",LocalDateTime.now()).ge("groupbuy.`valid_end_time`",LocalDateTime.now()));
        iMarketMerchantGroupbuyRepository.selectGroupbuyH5ListPage(pager,query);
        List<BbbH5MarketActivityVO.groupbuyVO> listVOS=new ArrayList<>();
        List<BbbH5GoodsInfoVO.HomeInnerServiceVO>  goodsInfoVOS=null;
        List<String> goodsIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(pager)){
            for (BbbH5MarketActivityVO.groupbuyVO cutVO : pager.getRecords()) {
                BbbH5MarketActivityVO.groupbuyVO cutVO1 = new BbbH5MarketActivityVO.groupbuyVO();
                BeanUtils.copyProperties(cutVO,cutVO1);
                listVOS.add(cutVO1);
                goodsIds.add(cutVO.getGoodsId());
            }
            if (ObjectUtils.isNotEmpty(goodsIds)){
                goodsInfoVOS = iBbbH5GoodsInfoRpc.getHomeGoodsInnerServiceVO(goodsIds, dto);
            }
            if (ObjectUtils.isNotEmpty(listVOS) && ObjectUtils.isNotEmpty(goodsInfoVOS)){
                SetGoodsInfo(listVOS,goodsInfoVOS);
            }

        }
        if (listVOS.size()>4){
            List<BbbH5MarketActivityVO.groupbuyVO> list=new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                list.add(getValue(listVOS));
            }
            return list;
        }else {
            return listVOS;
        }
    }

    @Override
    public PageData<BbbH5MarketActivityVO.groupbuyVO> groupbuyList(BbbH5MarketActivityQTO.QTO qto) {
        IPage<BbbH5MarketActivityVO.groupbuyVO> pager = MybatisPlusUtil.pager(qto);
        QueryWrapper<BbbH5MarketActivityQTO.QTO> query = MybatisPlusUtil.query();
        query.and(i->i.likeRight("groupbuy.`terminal`","10"));
        if (ObjectUtils.isNotEmpty(qto.getShopId())) {
            query.and(i -> i.eq("groupbuy.shop_id",qto.getShopId() ));
        }
        query.and(i->i.le("groupbuy.`valid_start_time`",LocalDateTime.now()).ge("groupbuy.`valid_end_time`",LocalDateTime.now()));
        iMarketMerchantGroupbuyRepository.selectGroupbuyH5ListPage(pager,query);
        List<BbbH5MarketActivityVO.groupbuyVO> listVOS=new ArrayList<>();
        List<BbbH5GoodsInfoVO.HomeInnerServiceVO>  goodsInfoVOS=null;
        List<String> goodsIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(pager)){
            for (BbbH5MarketActivityVO.groupbuyVO cutVO : pager.getRecords()) {
                BbbH5MarketActivityVO.groupbuyVO cutVO1 = new BbbH5MarketActivityVO.groupbuyVO();
                BeanUtils.copyProperties(cutVO,cutVO1);
                listVOS.add(cutVO1);
                goodsIds.add(cutVO.getGoodsId());
            }
            if (ObjectUtils.isNotEmpty(goodsIds)){
                goodsInfoVOS = iBbbH5GoodsInfoRpc.getHomeGoodsInnerServiceVO(goodsIds, qto);
            }
            if (ObjectUtils.isNotEmpty(listVOS) && ObjectUtils.isNotEmpty(goodsInfoVOS)){
                SetGoodsInfo(listVOS,goodsInfoVOS);
            }

        }
        return new PageData<>(listVOS,qto.getPageNum(),qto.getPageSize(),pager.getTotal());
    }

    @Override
    public BbbH5MarketActivityVO.activityVO activity(BbbH5MarketActivityQTO.IdQTO qto) {
        MarketPtActivity activity = iMarketPtActivityRepository.getById(qto.getId());
        if (ObjectUtils.isEmpty(activity)){
            throw new BusinessException("没有查询到此活动");
        }
        BbbH5MarketActivityVO.activityVO activityVO = new BbbH5MarketActivityVO.activityVO();
        BeanUtils.copyProperties(activity,activityVO);
        IPage<BbbH5MarketActivityVO.activityGoodsVO> pager = MybatisPlusUtil.pager(new BbbH5MarketActivityQTO.QTO());
        QueryWrapper<BbbH5MarketActivityDTO.IdDTO> query = MybatisPlusUtil.query();
        query.and(i->i.eq("goods.activity_id",qto.getId()));
        iMarketPtActivityRepository.selectActivityPageDataH5(pager,query);
        List<BbbH5MarketActivityVO.activityGoodsVO> listVOS=new ArrayList<>();
        List<BbbH5GoodsInfoVO.HomeInnerServiceVO>  goodsInfoVOS=null;
        List<String> goodsIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(pager)){
            for (BbbH5MarketActivityVO.activityGoodsVO cutVO : pager.getRecords()) {
                BbbH5MarketActivityVO.activityGoodsVO cutVO1 = new BbbH5MarketActivityVO.activityGoodsVO();
                BeanUtils.copyProperties(cutVO,cutVO1);
                listVOS.add(cutVO1);
                goodsIds.add(cutVO.getGoodsId());
            }
            if (ObjectUtils.isNotEmpty(goodsIds)){
                goodsInfoVOS = iBbbH5GoodsInfoRpc.getHomeGoodsInnerServiceVO(goodsIds, qto);
            }
            if (ObjectUtils.isNotEmpty(listVOS) && ObjectUtils.isNotEmpty(goodsInfoVOS)){
                SetGoodsInfo(listVOS,goodsInfoVOS);
            }

        }
        activityVO.setGoodsVOList(new PageData<>(listVOS,qto.getPageNum(),qto.getPageSize(),pager.getTotal()));
        return activityVO;
    }

    @Override
    public ResponseData<BbbH5MarketActivityVO.ListActivityVO> activityList(BbbH5MarketMerchantActivityDTO.IdDTO dto) {
        BbbH5MarketActivityVO.ListActivityVO listVO = new BbbH5MarketActivityVO.ListActivityVO();
        //活动
        listVO.setPlataformActivity(PlataformActivity(dto));
        //满折
        listVO.setScountActivity(ScountActivity(dto));
        //满减
        listVO.setCutActivity(CutActivity(dto));
        //满赠
        listVO.setGiftActivity(GiftActivity(dto));
        //团购
        listVO.setGroupbuyActivity(GoupbuyActivity(dto));
        //优惠卷
        listVO.setCardActivity(Car(dto));
        return ResponseData.data(listVO);
    }

    private List<BbbH5MarketActivityVO.merchantCard> Car(BbbH5MarketMerchantActivityDTO.IdDTO dto) {
        QueryWrapper<MarketMerchantCard> query = MybatisPlusUtil.query();
        query.and(i->i.eq("g.`goods_id`",dto.getId()));
        List<MarketMerchantCard> marketMerchantCardList= iMarketMerchantCardRepository.getByGoodsId(query);
        if (ObjectUtils.isNotEmpty(marketMerchantCardList)){
            List<BbbH5MarketActivityVO.merchantCard> list=marketMerchantCardList.parallelStream().map(e ->{
                BbbH5MarketActivityVO.merchantCard merchantCard = new BbbH5MarketActivityVO.merchantCard();
                merchantCard.setId(e.getId()).setRule("满"+e.getToPrice()+"减"+e.getCutPrice()).setFaceValue(e.getCutPrice()).setName(e.getCardName());
                if (ObjectUtils.isNotEmpty(e.getQuantity())
                        &&ObjectUtils.isNotEmpty(e.getReceivedTotal())
                        &&ObjectUtils.isNotEmpty(e.getGetStartTime())
                        &&ObjectUtils.isNotEmpty(e.getGetEndTime())){
                    if (LocalDateTime.now().isAfter(e.getGetEndTime())
                            ||LocalDateTime.now().isBefore(e.getGetStartTime())){
                        merchantCard.setIsReceive(20);
                    }else if (e.getQuantity()<=e.getReceivedTotal()){
                        merchantCard.setIsReceive(20);
                    }else {
                        merchantCard.setIsReceive(10);
                    }
                }
                if (merchantCard.getIsReceive()==10){
                    if (ObjectUtils.isNotEmpty(dto.getJwtUserId())){
                        //用户登录了
                        QueryWrapper<MarketMerchantCardUsers> query1 = MybatisPlusUtil.query();
                        query1.and(i->i.eq("user_id",dto.getJwtUserId()));
                        query1.and(i->i.eq("card_id",e.getId()));
                        List<MarketMerchantCardUsers> list1 = iMarketMerchantCardUsersRepository.list(query1);
                        if (list1.size()>=e.getUserGetMax()){
                            merchantCard.setIsReceive(20);
                        }
                    }
                }
                return merchantCard;
            }).collect(Collectors.toList());
            return list;
        }
        return null;
    }

    @Override
    public List<BbbH5MarketActivityVO.merchantCard> merchantCard(BbbH5MarketMerchantActivityDTO.MerchantIdDTO dto) {
        QueryWrapper<MarketMerchantCard> query = MybatisPlusUtil.query();
        query.and(i->i.eq("shop_id",dto.getId()));
        query.and(i->i.eq("state",20));
        List<MarketMerchantCard> list = iMarketMerchantCardRepository.list(query);
        if (ObjectUtils.isNotEmpty(list)){
            List< BbbH5MarketActivityVO.merchantCard> cards=new ArrayList<>();
            for (MarketMerchantCard marketMerchantCard : list) {
                BbbH5MarketActivityVO.merchantCard merchantCard = new BbbH5MarketActivityVO.merchantCard();
                merchantCard.setId(marketMerchantCard.getId()).
                        setFaceValue(marketMerchantCard.getCutPrice()).
                        setRule("满"+marketMerchantCard.getToPrice()+"减"+marketMerchantCard.getCutPrice());
                //可领取[1.时间 2.领取数量 3.用户领取完了]
                if (ObjectUtils.isNotEmpty(marketMerchantCard.getQuantity())
                        &&ObjectUtils.isNotEmpty(marketMerchantCard.getReceivedTotal())
                        &&ObjectUtils.isNotEmpty(marketMerchantCard.getGetStartTime())
                        &&ObjectUtils.isNotEmpty(marketMerchantCard.getGetEndTime())){
                    if (LocalDateTime.now().isAfter(marketMerchantCard.getGetEndTime())
                            ||LocalDateTime.now().isBefore(marketMerchantCard.getGetStartTime())){
                        merchantCard.setIsReceive(20);
                    }else if (marketMerchantCard.getQuantity()<=marketMerchantCard.getReceivedTotal()){
                        merchantCard.setIsReceive(20);
                    }else {
                        merchantCard.setIsReceive(10);
                    }
                }
                if (merchantCard.getIsReceive()==10){
                    if (ObjectUtils.isNotEmpty(dto.getJwtUserId())){
                        //用户登录了
                        QueryWrapper<MarketMerchantCardUsers> query1 = MybatisPlusUtil.query();
                        query1.and(i->i.eq("user_id",dto.getJwtUserId()));
                        query1.and(i->i.eq("card_id",marketMerchantCard.getId()));
                        List<MarketMerchantCardUsers> list1 = iMarketMerchantCardUsersRepository.list(query1);
                        if (list1.size()>=marketMerchantCard.getUserGetMax()){
                            merchantCard.setIsReceive(20);
                        }
                    }
                }
                cards.add(merchantCard);
            }
            return cards;
        }
        return new ArrayList<>();
    }

    @Override
    @Transactional
    public BbbH5MarketActivityVO.merchantCardSuccess userReciveCard(BbbH5MarketMerchantActivityDTO.CardIdDTO dto) {
        //查询优惠卷
        MarketMerchantCard marketMerchantCard = iMarketMerchantCardRepository.getById(dto.getId());
        if (ObjectUtils.isEmpty(marketMerchantCard)){
            throw new BusinessException("无此优惠卷");
        }
        QueryWrapper<MarketMerchantCardUsers> query1 = MybatisPlusUtil.query();
        query1.and(i->i.eq("user_id",dto.getJwtUserId()));
        query1.and(i->i.eq("card_id",marketMerchantCard.getId()));
        List<MarketMerchantCardUsers> list1 = iMarketMerchantCardUsersRepository.list(query1);
        if (list1.size()>=marketMerchantCard.getUserGetMax()
                ||LocalDateTime.now().isAfter(marketMerchantCard.getGetEndTime())
                ||LocalDateTime.now().isBefore(marketMerchantCard.getGetStartTime())
                ||marketMerchantCard.getQuantity()<=marketMerchantCard.getReceivedTotal()
                ||marketMerchantCard.getState()!=20){
            throw new BusinessException("用户不能领取了");
        }
        MarketMerchantCardUsers marketMerchantCardUsers = new MarketMerchantCardUsers();
        BeanUtils.copyProperties(marketMerchantCard,marketMerchantCardUsers);
        marketMerchantCardUsers.setId(null).
                setUserId(dto.getJwtUserId()).
                setCardId(marketMerchantCard.getId()).
                setUserDescribe(marketMerchantCard.getCardDescribe()).setTerminal(marketMerchantCard.getTerminal()).
                setState(10);
        iMarketMerchantCardUsersRepository.save(marketMerchantCardUsers);
        //修改优惠卷主表的领取数量
        marketMerchantCard.setReceivedTotal(marketMerchantCard.getReceivedTotal()+1);
        iMarketMerchantCardRepository.updateById(marketMerchantCard);
        BbbH5MarketActivityVO.merchantCardSuccess merchantCardSuccess = new BbbH5MarketActivityVO.merchantCardSuccess();
        BeanUtils.copyProperties(marketMerchantCard,merchantCardSuccess);
        BbbShopVO.ComplexDetailVO complexDetailVO = iBbbShopRpc.inneComplexDetailShop(marketMerchantCard.getShopId(), dto);
        if (ObjectUtils.isNotEmpty(complexDetailVO)){
            merchantCardSuccess.setShopName(complexDetailVO.getShopName());
        }
        merchantCardSuccess.setRule("满"+marketMerchantCard.getCutPrice()+"减"+marketMerchantCard.getToPrice());
        return merchantCardSuccess;

    }

    @Override
    public BbbH5MarketActivityVO.jurisdiction jurisdiction() {
        MarketPtActivityJurisdiction one = iMarketPtActivityJurisdictionRepository.getOne(null);
        if (ObjectUtils.isNotEmpty(one)) {
            BbbH5MarketActivityVO.jurisdiction jurisdiction = new BbbH5MarketActivityVO.jurisdiction();
            List<BbbH5MarketActivityVO.jurisdiction.state> pc = new ArrayList<>();
            List<BbbH5MarketActivityVO.jurisdiction.state> h5 = new ArrayList<>();
            pc.add(new BbbH5MarketActivityVO.jurisdiction.state(10, one.getPcGroupbuy()));
            pc.add(new BbbH5MarketActivityVO.jurisdiction.state(20, one.getPcDiscount()));
            pc.add(new BbbH5MarketActivityVO.jurisdiction.state(30, one.getPcCut()));
            pc.add(new BbbH5MarketActivityVO.jurisdiction.state(40, one.getPcGift()));
            h5.add(new BbbH5MarketActivityVO.jurisdiction.state(10, one.getH5Groupbuy()));
            h5.add(new BbbH5MarketActivityVO.jurisdiction.state(20, one.getH5Discount()));
            h5.add(new BbbH5MarketActivityVO.jurisdiction.state(30, one.getH5Cut()));
            h5.add(new BbbH5MarketActivityVO.jurisdiction.state(40, one.getH5Gift()));
            jurisdiction.setH5Activity(h5);
            jurisdiction.setPcActivity(pc);
            return jurisdiction;
        }
        return null;
    }

    @Override
    public List<BbbH5MarketActivityVO.merchantCard> activityCardGoodsInfo(BbbH5MarketMerchantActivityDTO.MerchantIdDTO dto) {
        QueryWrapper<MarketMerchantCard> query = MybatisPlusUtil.query();
        query.and(i->i.eq("c.`shop_id`",dto.getId()));
        query.and(i->i.eq("g.`goods_id`",dto.getGoodsId()));
        query.and(i->i.eq("c.`state`",20));
        query.and(i->i.le("c.`valid_start_time`",LocalDateTime.now()).ge("c.`valid_end_time`",LocalDateTime.now()));
        //是否可领取[10=可以 20=不可以]
        List<MarketMerchantCard> list = iMarketMerchantCardRepository.selectCard(query);
        if (ObjectUtils.isNotEmpty(list)){
            List< BbbH5MarketActivityVO.merchantCard> cards=new ArrayList<>();
            for (MarketMerchantCard marketMerchantCard : list) {
                BbbH5MarketActivityVO.merchantCard merchantCard = new BbbH5MarketActivityVO.merchantCard();
                merchantCard.setId(marketMerchantCard.getId()).
                        setFaceValue(marketMerchantCard.getCutPrice()).
                        setRule("满"+marketMerchantCard.getToPrice()+"减"+marketMerchantCard.getCutPrice()).
                        setName(marketMerchantCard.getCardName());
                //可领取[1.时间 2.领取数量 3.用户领取完了]
                if (ObjectUtils.isNotEmpty(marketMerchantCard.getQuantity())
                        &&ObjectUtils.isNotEmpty(marketMerchantCard.getReceivedTotal())
                        &&ObjectUtils.isNotEmpty(marketMerchantCard.getGetStartTime())
                        &&ObjectUtils.isNotEmpty(marketMerchantCard.getGetEndTime())){
                    if (marketMerchantCard.getQuantity()<=marketMerchantCard.getReceivedTotal()){
                        merchantCard.setIsReceive(20);
                    }else {
                        merchantCard.setIsReceive(10);
                    }
                }
                if (merchantCard.getIsReceive()==10){
                    if (ObjectUtils.isNotEmpty(dto.getJwtUserId())){
                        //用户登录了
                        QueryWrapper<MarketMerchantCardUsers> query1 = MybatisPlusUtil.query();
                        query1.and(i->i.eq("user_id",dto.getJwtUserId()));
                        query1.and(i->i.eq("card_id",marketMerchantCard.getId()));
                        List<MarketMerchantCardUsers> list1 = iMarketMerchantCardUsersRepository.list(query1);
                        if (list1.size()>=marketMerchantCard.getUserGetMax()){
                            merchantCard.setIsReceive(20);
                        }
                    }
                }
                cards.add(merchantCard);
            }
            return cards;
        }
        return new ArrayList<>();

    }

    @Override
    public PageData<BbbH5MarketActivityVO.activityListPageVO> activityListPage(BbbH5MarketActivityQTO.QTO qto) {
        QueryWrapper<BbbH5MarketActivityQTO.QTO> query = MybatisPlusUtil.query();
        query.and(i->i.le("online_start_time",LocalDateTime.now()));
        query.and(i->i.ge("activity_end_time",LocalDateTime.now()));
        IPage<BbbH5MarketActivityVO.activityListPageVO> pager = MybatisPlusUtil.pager(qto);
        iMarketPtActivityRepository.activityListBbbh5Page(pager,query);
        return MybatisPlusUtil.toPageData(qto,BbbH5MarketActivityVO.activityListPageVO.class,pager);
    }

    private List<BbbH5MarketActivityVO.GroupbuyActivity> GoupbuyActivity(BbbH5MarketMerchantActivityDTO.IdDTO dto) {
        List<BbbH5MarketActivityVO.GroupbuyActivity> groupbuyActivity=new ArrayList<>();
        QueryWrapper<MarketMerchantGroupbuyGoods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("goods_id",dto.getId());
        List<MarketMerchantGroupbuyGoods> list = iMarketMerchantGroupbuyGoodsRepository.list(queryWrapper);
        ArrayList<String> activityId = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(list)){
            for (MarketMerchantGroupbuyGoods marketMerchantGroupbuyGoods:list){
                activityId.add(marketMerchantGroupbuyGoods.getGroupbuyId());
            }
        }
        if (ObjectUtils.isNotEmpty(activityId)) {
            List<MarketMerchantGroupbuy> marketMerchantGroupbuys = iMarketMerchantGroupbuyRepository.listByIds(activityId);
            if (ObjectUtils.isNotEmpty(marketMerchantGroupbuys)) {
                for (MarketMerchantGroupbuy marketMerchantGroupbuy : marketMerchantGroupbuys) {
                    BbbH5MarketActivityVO.GroupbuyActivity groupbuyActivity1 = new BbbH5MarketActivityVO.GroupbuyActivity();
                    BeanUtils.copyProperties(marketMerchantGroupbuy, groupbuyActivity1);
                    CheckActivityTime(marketMerchantGroupbuy.getValidStartTime(), marketMerchantGroupbuy.getValidEndTime(), groupbuyActivity1);
                    if (groupbuyActivity1.getStatus().equals("已结束")) {
                        continue;
                    }
                    if (!marketMerchantGroupbuy.getState().equals(PlatformCardCheckStatusEnum.通过.getCode()) ) {
                        continue;
                    }
                    if (marketMerchantGroupbuy.getIsCancel()){
                        continue;
                    }
                    groupbuyActivity.add(groupbuyActivity1);
                }
            }
        }
        return groupbuyActivity;
    }
    private List<BbbH5MarketActivityVO.GiftActivity> GiftActivity(BbbH5MarketMerchantActivityDTO.IdDTO dto) {
        List<BbbH5MarketActivityVO.GiftActivity> giftActivity=new ArrayList<>();
        QueryWrapper<MarketMerchantGiftGoods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("goods_id",dto.getId());
        List<MarketMerchantGiftGoods> list = iMarketMerchantGiftGoodsRepository.list(queryWrapper);
        ArrayList<String> activityId = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(list)){
            for (MarketMerchantGiftGoods marketMerchantGiftGoods:list){
                activityId.add(marketMerchantGiftGoods.getGiftId());
            }
        }
        if (ObjectUtils.isNotEmpty(activityId)) {
            List<MarketMerchantGift> marketMerchantGifts = iMarketMerchantGiftRepository.listByIds(activityId);
            if (ObjectUtils.isNotEmpty(marketMerchantGifts)) {
                for (MarketMerchantGift marketMerchantGift : marketMerchantGifts) {
                    BbbH5MarketActivityVO.GiftActivity GiftActivity1 = new BbbH5MarketActivityVO.GiftActivity();
                    BeanUtils.copyProperties(marketMerchantGift, GiftActivity1);
                    CheckActivityTime(marketMerchantGift.getValidStartTime(), marketMerchantGift.getValidEndTime(), GiftActivity1);
                    if (GiftActivity1.getStatus().equals("已结束")) {
                        continue;
                    }
                    if (!marketMerchantGift.getState().equals(PlatformCardCheckStatusEnum.通过.getCode())) {
                        continue;
                    }
                    if (marketMerchantGift.getIsCancel()){
                        continue;
                    }
                    giftActivity.add(GiftActivity1);
                }
            }
        }
        return giftActivity;
    }

    private List<BbbH5MarketActivityVO.CutActivity> CutActivity(BbbH5MarketMerchantActivityDTO.IdDTO dto) {
        List<BbbH5MarketActivityVO.CutActivity> cutActivity=new ArrayList<>();
        QueryWrapper<MarketMerchantCutGoods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("goods_id",dto.getId());
        List<MarketMerchantCutGoods> list = iMarketMerchantCutGoodsRepository.list(queryWrapper);
        ArrayList<String> activityId = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(list)){
            for (MarketMerchantCutGoods marketMerchantCutGoods:list){
                activityId.add(marketMerchantCutGoods.getCutId());
            }
        }
        if (ObjectUtils.isNotEmpty(activityId)) {
            List<MarketMerchantCut> marketMerchantCuts = iMarketMerchantCutRepository.listByIds(activityId);
            if (ObjectUtils.isNotEmpty(marketMerchantCuts)) {
                for (MarketMerchantCut marketMerchantCut : marketMerchantCuts) {
                    BbbH5MarketActivityVO.CutActivity cutActivity1 = new BbbH5MarketActivityVO.CutActivity();
                    BeanUtils.copyProperties(marketMerchantCut, cutActivity1);
                    CheckActivityTime(marketMerchantCut.getValidStartTime(), marketMerchantCut.getValidEndTime(), cutActivity1);
                    if (cutActivity1.getStatus().equals("已结束")) {
                        continue;
                    }
                    if (!marketMerchantCut.getState().equals(PlatformCardCheckStatusEnum.通过.getCode())) {
                        continue;
                    }
                    if (marketMerchantCut.getIsCancel()){
                        continue;
                    }
                    cutActivity.add(cutActivity1);
                }
            }
        }
        return cutActivity;
    }
    private List<BbbH5MarketActivityVO.ScountActivity> ScountActivity(BbbH5MarketMerchantActivityDTO.IdDTO dto) {
        List<BbbH5MarketActivityVO.ScountActivity> scountActivity=new ArrayList<>();
        QueryWrapper<MarketMerchantDiscountGoods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("goods_id",dto.getId());
        List<MarketMerchantDiscountGoods> list = iMarketMerchantDiscountGoodsRepository.list(queryWrapper);
        ArrayList<String> activityId = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(list)){
            for (MarketMerchantDiscountGoods marketMerchantDiscountGoods:list){
                activityId.add(marketMerchantDiscountGoods.getDiscountId());
            }
        }
        if (ObjectUtils.isNotEmpty(activityId)) {
            List<MarketMerchantDiscount> marketMerchantDiscounts = iMarketMerchantDiscountRepository.listByIds(activityId);
            if (ObjectUtils.isNotEmpty(marketMerchantDiscounts)) {
                for (MarketMerchantDiscount marketMerchantDiscount : marketMerchantDiscounts) {
                    BbbH5MarketActivityVO.ScountActivity scountActivity1 = new BbbH5MarketActivityVO.ScountActivity();
                    BeanUtils.copyProperties(marketMerchantDiscount, scountActivity1);
                    CheckActivityTime(marketMerchantDiscount.getValidStartTime(), marketMerchantDiscount.getValidEndTime(), scountActivity1);
                    if (scountActivity1.getStatus().equals("已结束")) {
                        continue;
                    }
                    if (!marketMerchantDiscount.getState().equals(PlatformCardCheckStatusEnum.通过.getCode())) {
                        continue;
                    }
                    if (marketMerchantDiscount.getIsCancel()){
                        continue;
                    }
                    scountActivity.add(scountActivity1);
                }
            }
        }
        return scountActivity;
    }

    private List<BbbH5MarketActivityVO.PlataformActivity> PlataformActivity(BbbH5MarketMerchantActivityDTO.IdDTO dto) {
        List<BbbH5MarketActivityVO.PlataformActivity> plataformActivity=new ArrayList<>();
        QueryWrapper<MarketPtActivityGoodsSpu> wrapper = new QueryWrapper<>();
        wrapper.eq("goods_id",dto.getId());
        ArrayList<String> activityIds = new ArrayList<>();
        String shopId = null;
        String merchantId=null;
        //查询商品的活动
        List<MarketPtActivityGoodsSpu> list = iMarketPtActivityGoodsSpuRepository.list(wrapper);
        if (ObjectUtils.isNotEmpty(list)){
            for (MarketPtActivityGoodsSpu marketPtActivityGoodsSpu:list){
                activityIds.add(marketPtActivityGoodsSpu.getActivityId());
                shopId=marketPtActivityGoodsSpu.getShopId();
                merchantId=marketPtActivityGoodsSpu.getMerchantId();
            }
        }
        if (ObjectUtils.isNotEmpty(activityIds)) {
            List<MarketPtActivity> marketPtActivities = iMarketPtActivityRepository.listByIds(activityIds);
            if (ObjectUtils.isNotEmpty(marketPtActivities)) {
                for (MarketPtActivity marketPtActivity : marketPtActivities) {
                    BbbH5MarketActivityVO.PlataformActivity plataformActivity1 = new BbbH5MarketActivityVO.PlataformActivity();
                    BeanUtils.copyProperties(marketPtActivity, plataformActivity1);
                    //判断时间
                    CheckActivityTime(marketPtActivity.getActivityStartTime(), marketPtActivity.getActivityEndTime(), plataformActivity1);
                    QueryWrapper<MarketPtActivityMerchant> queryWrapper = new QueryWrapper<>();
                    queryWrapper.and(i -> i.eq("activity_id", marketPtActivity.getId()));
                    String finalShopId = shopId;
                    queryWrapper.and(i -> i.eq("shop_id", finalShopId));
                    String finalMerchantId = merchantId;
                    queryWrapper.and(i -> i.eq("merchant_id", finalMerchantId));
                    MarketPtActivityMerchant one = iMarketPtActivityMerchantRepository.getOne(queryWrapper);
                    if (one == null) {
                        continue;
                    }
                    if (!one.getState().equals(ActivitySignEnum.已审核.getCode().toString())) {
                        continue;
                    }
                    if (plataformActivity1.getStatus().equals("已结束")) {
                        continue;
                    }
                    plataformActivity.add(plataformActivity1);
                }
            }
        }
        return plataformActivity;
    }

    private void CheckActivityTime(LocalDateTime activityStartTime, LocalDateTime activityEndTime,  BbbH5MarketActivityVO.GroupbuyActivity plataformActivity1) {
        Long now = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Long start=activityStartTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Long end=activityEndTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        if (now>end ){
            plataformActivity1.setStatus("已结束");
        }
        if (now<end && now>=start){
            plataformActivity1.setStatus("进行中");
        }
        if (now<start){
            plataformActivity1.setStatus("未开始");
        }

    }

    private void CheckActivityTime(LocalDateTime activityStartTime, LocalDateTime activityEndTime, BbbH5MarketActivityVO.PlataformActivity plataformActivity1) {
        Long now = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Long start=activityStartTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Long end=activityEndTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        if (now>end ){
            plataformActivity1.setStatus("已结束");
        }
        if (now<end && now>=start){
            plataformActivity1.setStatus("进行中");
        }
        if (now<start){
            plataformActivity1.setStatus("未开始");
        }

    }
    private void CheckActivityTime(LocalDateTime activityStartTime, LocalDateTime activityEndTime, BbbH5MarketActivityVO.ScountActivity plataformActivity1) {
        Long now = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Long start=activityStartTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Long end=activityEndTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        if (now>end ){
            plataformActivity1.setStatus("已结束");
        }
        if (now<end && now>=start){
            plataformActivity1.setStatus("进行中");
        }
        if (now<start){
            plataformActivity1.setStatus("未开始");
        }

    }
    private void CheckActivityTime(LocalDateTime activityStartTime, LocalDateTime activityEndTime, BbbH5MarketActivityVO.CutActivity plataformActivity1) {
        Long now = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Long start=activityStartTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Long end=activityEndTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        if (now>end ){
            plataformActivity1.setStatus("已结束");
        }
        if (now<end && now>=start){
            plataformActivity1.setStatus("进行中");
        }
        if (now<start){
            plataformActivity1.setStatus("未开始");
        }

    }
    private void CheckActivityTime(LocalDateTime activityStartTime, LocalDateTime activityEndTime, BbbH5MarketActivityVO.GiftActivity plataformActivity1) {
        Long now = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Long start=activityStartTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Long end=activityEndTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        if (now>end ){
            plataformActivity1.setStatus("已结束");
        }
        if (now<end && now>=start){
            plataformActivity1.setStatus("进行中");
        }
        if (now<start){
            plataformActivity1.setStatus("未开始");
        }
    }

    public  <T> T getValue( List<T> mArrayList){
        Random mRandom = new Random();
        int a = mRandom.nextInt(mArrayList.size()-1);
        mArrayList.remove(a);
        return mArrayList.get(a);
    }


    private void SetGoodsInfo(List<? extends BbbH5MarketActivityVO.ListVO> listVOS, List<BbbH5GoodsInfoVO.HomeInnerServiceVO> goodsInfoVOS) {
        for (int i = 0; i < listVOS.size(); i++) {
            for (BbbH5GoodsInfoVO.HomeInnerServiceVO goodsInfoVO : goodsInfoVOS) {
                if (listVOS.get(i).getGoodsId().equals(goodsInfoVO.getGoodsId())){
                    listVOS.get(i).setGoodsImg(goodsInfoVO.getGoodsImage());
                    listVOS.get(i).setGoodsName(goodsInfoVO.getGoodsName()+","+goodsInfoVO.getGoodsTitle());
                    listVOS.get(i).setOldPrice(goodsInfoVO.getSalePrice());
                    listVOS.get(i).setGoodsTitle(goodsInfoVO.getGoodsTitle());
                }
            }
        }
    }


    private List<PCBbbMarketActivityVO.cutRuleVO> getruleVOs(String rule) {
        if (StringUtils.isNotBlank(rule) && !"\"\"".equals(rule)) {
            JSONArray arr = JSONArray.parseArray(rule);
            if (ObjectUtils.isEmpty(arr)) {
                return new ArrayList<>();
            }
            List<PCBbbMarketActivityVO.cutRuleVO> ruleVOS = new ArrayList<>();
            for (int i = 0; i < arr.size(); i++) {
                PCBbbMarketActivityVO.cutRuleVO ruleVO = new PCBbbMarketActivityVO.cutRuleVO();
                JSONObject jsonObject = arr.getJSONObject(i);
                BigDecimal cutPrice = jsonObject.getBigDecimal("cut_price");

                Integer toPrice = jsonObject.getInteger("to_price");
                ruleVO.setCutPrice(cutPrice);
                ruleVO.setToPrice(toPrice);
                ruleVOS.add(ruleVO);
            }
            return ruleVOS;
        }
        return new ArrayList<>();
    }
}
