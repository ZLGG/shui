package com.gs.lshly.biz.support.trade.service.bbc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.*;
import com.gs.lshly.biz.support.trade.enums.PlatformCardCheckStatusEnum;
import com.gs.lshly.biz.support.trade.repository.*;
import com.gs.lshly.biz.support.trade.service.bbc.IBbcMarketActivityService;
import com.gs.lshly.common.enums.ActivitySignEnum;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5MarketActivityDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5MarketActivityQTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsInfoVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbMarketActivityDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbMarketActivityQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketActivityVO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketActivityVO.FlashsaleVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteTopicVO.TopicVO;
import com.gs.lshly.common.struct.bbc.merchant.qto.BbcShopQTO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcMarketActivityDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcMarketMerchantActivityDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcMarketActivityQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketActivityVO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsInfoRpc;
import com.gs.lshly.rpc.api.bbc.merchant.IBbcShopRpc;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsInfoRpc;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class BbcMarketActivityServiceImpl implements IBbcMarketActivityService {
    @Autowired
    private IMarketPtActivityRepository iMarketPtActivityRepository;
    @Autowired
    private IMarketPtActivityGoodsSpuRepository iMarketPtActivityGoodsSpuRepository;
    @Autowired
    private IMarketPtActivityMerchantRepository iMarketPtActivityMerchantRepository;
    @Autowired
    private IMarketMerchantDiscountRepository iMarketMerchantDiscountRepository;
    @Autowired
    private IMarketMerchantDiscountGoodsRepository iMarketMerchantDiscountGoodsRepository;
    @Autowired
    private IMarketMerchantCutGoodsRepository iMarketMerchantCutGoodsRepository;
    @Autowired
    private IMarketMerchantCutRepository iMarketMerchantCutRepository;
    @Autowired
    private IMarketMerchantGiftGoodsRepository iMarketMerchantGiftGoodsRepository;
    @Autowired
    private IMarketMerchantGiftRepository iMarketMerchantGiftRepository;
    @Autowired
    private IMarketMerchantGiftGoodsGiveRepository iMarketMerchantGiftGoodsGiveRepository;
    @Autowired
    private IMarketMerchantGroupbuyRepository iMarketMerchantGroupbuyRepository;
    @Autowired
    private IMarketMerchantGroupbuyGoodsRepository iMarketMerchantGroupbuyGoodsRepository;
    @Autowired
    private IMarketMerchantCardRepository iMarketMerchantCardRepository;
    @Autowired
    private IMarketMerchantCardGoodsRepository iMarketMerchantCardGoodsRepository;
    @Autowired
    private IMarketMerchantCardUsersRepository iMarketMerchantCardUsersRepository;

    @DubboReference
    private IBbcGoodsInfoRpc iBbcGoodsInfoRpc;
    @DubboReference
    private IBbcShopRpc iBbcShopRpc;
    
    @DubboReference
    private IGoodsInfoRpc goodsInfoRpc;


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
    public ResponseData<BbcMarketActivityVO.ListVO> activityList(BbcMarketActivityDTO.DTO dto) {
        BbcMarketActivityVO.ListVO listVO = new BbcMarketActivityVO.ListVO();
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
        return ResponseData.data(listVO);
    }

    @Override
    public ResponseData<List<BbcMarketActivityVO.ListCardVO>> activityCardList(BbcMarketActivityDTO.DTO dto) {
        List<BbcMarketActivityVO.ListCardVO> listVO=new ArrayList<>();
        QueryWrapper<MarketMerchantCardGoods> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(i->i.eq("goods_id",dto.getId()));
        List<MarketMerchantCardGoods> list = iMarketMerchantCardGoodsRepository.list(queryWrapper);
        ArrayList<String> activityId = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(list)){
            for (MarketMerchantCardGoods marketMerchantCardGoods:list){
                activityId.add(marketMerchantCardGoods.getCardId());
            }
        }
        if (ObjectUtils.isNotEmpty(activityId)){
            List<MarketMerchantCard> marketMerchantCard = iMarketMerchantCardRepository.listByIds(activityId);
            if (ObjectUtils.isNotEmpty(marketMerchantCard)){
                for (MarketMerchantCard marketMerchantCard1:marketMerchantCard){
                    BbcMarketActivityVO.ListCardVO listCardVO = new BbcMarketActivityVO.ListCardVO();
                    BeanUtils.copyProperties(marketMerchantCard1, listCardVO);
                    CheckActivityTime(marketMerchantCard1.getValidStartTime(), marketMerchantCard1.getValidEndTime(), listCardVO);
                    if (listCardVO.getStatus().equals("已结束")) {
                        continue;
                    }
                    if (!marketMerchantCard1.getState().equals(PlatformCardCheckStatusEnum.通过.getCode()) ) {
                        continue;
                    }
                    if (marketMerchantCard1.getIsCancel()){
                        continue;
                    }
                    listVO.add(listCardVO);
                }
            }
        }
        return ResponseData.data(listVO);
    }

    @Override
    public PageData<BbcMarketActivityVO.cutVO> cutList(BbcMarketActivityQTO.QTO qto) {
        IPage<BbcMarketActivityVO.cutVO> pager = MybatisPlusUtil.pager(qto);
        QueryWrapper<BbcMarketActivityQTO.QTO> query = MybatisPlusUtil.query();
        query.and(i->i.likeRight("cut.`terminal`",10));
        query.and(i->i.le("cut.`valid_start_time`",LocalDateTime.now()).ge("cut.`valid_end_time`",LocalDateTime.now()));
        iMarketMerchantCutRepository.selectCutBbcH5ListPage(pager, query);
        List<BbcMarketActivityVO.cutVO> listVOS=new ArrayList<>();
        List<BbcGoodsInfoVO.HomeAndShopInnerServiceVO>  goodsInfoVOS=null;
        List<String> goodsIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(pager)){
            for (BbcMarketActivityVO.cutVO cutVO : pager.getRecords()) {
                BbcMarketActivityVO.cutVO cutVO1 = new BbcMarketActivityVO.cutVO();
                BeanUtils.copyProperties(cutVO,cutVO1);
                listVOS.add(cutVO1);
                goodsIds.add(cutVO.getGoodsId());
            }
            if (ObjectUtils.isNotEmpty(goodsIds)){
                goodsInfoVOS = iBbcGoodsInfoRpc.getInnerSimpleServiceVO(goodsIds);
            }
            if (ObjectUtils.isNotEmpty(listVOS) && ObjectUtils.isNotEmpty(goodsInfoVOS)){
                SetGoodsInfo(listVOS,goodsInfoVOS);
            }

        }
        return new PageData<>(listVOS,qto.getPageNum(),qto.getPageSize(),pager.getTotal());
    }

    @Override
    public List<BbcMarketActivityVO.cutVO> viewFour(BaseDTO dto) {
        IPage<BbcMarketActivityVO.cutVO> pager = MybatisPlusUtil.pager(new BbcMarketActivityQTO.QTO());
        QueryWrapper<BbcMarketActivityQTO.QTO> query = MybatisPlusUtil.query();
        query.and(i->i.likeRight("cut.`terminal`",10));
        query.and(i->i.le("cut.`valid_start_time`",LocalDateTime.now()).ge("cut.`valid_end_time`",LocalDateTime.now()));
        iMarketMerchantCutRepository.selectCutBbcH5ListPage(pager, query);
        List<BbcMarketActivityVO.cutVO> listVOS=new ArrayList<>();
        List<BbcGoodsInfoVO.HomeAndShopInnerServiceVO>  goodsInfoVOS=null;
        List<String> goodsIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(pager)){
            for (BbcMarketActivityVO.cutVO cutVO : pager.getRecords()) {
                BbcMarketActivityVO.cutVO cutVO1 = new BbcMarketActivityVO.cutVO();
                BeanUtils.copyProperties(cutVO,cutVO1);
                listVOS.add(cutVO1);
                goodsIds.add(cutVO.getGoodsId());
            }
            if (ObjectUtils.isNotEmpty(goodsIds)){
                goodsInfoVOS = iBbcGoodsInfoRpc.getInnerSimpleServiceVO(goodsIds);
            }
            if (ObjectUtils.isNotEmpty(listVOS) && ObjectUtils.isNotEmpty(goodsInfoVOS)){
                SetGoodsInfo(listVOS,goodsInfoVOS);
            }

        }
        if (listVOS.size()>4){
            List<BbcMarketActivityVO.cutVO> list=new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                list.add(getValue(listVOS));
            }
            return list;
        }else {
            return listVOS;
        }
    }

    @Override
    public PageData<BbcMarketActivityVO.discountVO> discountList(BbcMarketActivityQTO.QTO qto) {
        IPage<BbcMarketActivityVO.discountVO> pager = MybatisPlusUtil.pager(qto);
        QueryWrapper<BbcMarketActivityQTO.QTO> query = MybatisPlusUtil.query();
        query.and(i->i.likeRight("discount.`terminal`","10"));
        query.and(i->i.le("discount.`valid_start_time`",LocalDateTime.now()).ge("discount.`valid_end_time`",LocalDateTime.now()));
        iMarketMerchantDiscountRepository.selectDiscountBbcH5ListPage(pager,query);
        List<BbcMarketActivityVO.discountVO> listVOS=new ArrayList<>();
        List<BbcGoodsInfoVO.HomeAndShopInnerServiceVO>  goodsInfoVOS=null;
        List<String> goodsIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(pager)){
            for (BbcMarketActivityVO.discountVO cutVO : pager.getRecords()) {
                BbcMarketActivityVO.discountVO cutVO1 = new BbcMarketActivityVO.discountVO();
                BeanUtils.copyProperties(cutVO,cutVO1);
                listVOS.add(cutVO1);
                goodsIds.add(cutVO.getGoodsId());
            }
            if (ObjectUtils.isNotEmpty(goodsIds)){
                goodsInfoVOS = iBbcGoodsInfoRpc.getInnerSimpleServiceVO(goodsIds);
            }
            if (ObjectUtils.isNotEmpty(listVOS) && ObjectUtils.isNotEmpty(goodsInfoVOS)){
                SetGoodsInfo(listVOS,goodsInfoVOS);
            }

        }
        return new PageData<>(listVOS,qto.getPageNum(),qto.getPageSize(),pager.getTotal());
    }

    @Override
    public List<BbcMarketActivityVO.discountVO> discountViewFour(BaseDTO dto) {
        IPage<BbcMarketActivityVO.discountVO> pager = MybatisPlusUtil.pager(new BbcMarketActivityQTO.QTO());
        QueryWrapper<BbcMarketActivityQTO.QTO> query = MybatisPlusUtil.query();
        query.and(i->i.likeRight("discount.`terminal`","10"));
        query.and(i->i.le("discount.`valid_start_time`",LocalDateTime.now()).ge("discount.`valid_end_time`",LocalDateTime.now()));
        iMarketMerchantDiscountRepository.selectDiscountBbcH5ListPage(pager,query);
        List<BbcMarketActivityVO.discountVO> listVOS=new ArrayList<>();
        List<BbcGoodsInfoVO.HomeAndShopInnerServiceVO>  goodsInfoVOS=null;
        List<String> goodsIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(pager)){
            for (BbcMarketActivityVO.discountVO cutVO : pager.getRecords()) {
                BbcMarketActivityVO.discountVO cutVO1 = new BbcMarketActivityVO.discountVO();
                BeanUtils.copyProperties(cutVO,cutVO1);
                listVOS.add(cutVO1);
                goodsIds.add(cutVO.getGoodsId());
            }
            if (ObjectUtils.isNotEmpty(goodsIds)){
                goodsInfoVOS = iBbcGoodsInfoRpc.getInnerSimpleServiceVO(goodsIds);
            }
            if (ObjectUtils.isNotEmpty(listVOS) && ObjectUtils.isNotEmpty(goodsInfoVOS)){
                SetGoodsInfo(listVOS,goodsInfoVOS);
            }

        }
        if (listVOS.size() > 4) {
            List<BbcMarketActivityVO.discountVO> list = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                list.add(getValue(listVOS));
            }
            return list;
        } else {
            return listVOS;
        }
    }

    @Override
    public PageData<BbcMarketActivityVO.giftVO> giftList(BbcMarketActivityQTO.QTO qto) {
        IPage<BbcMarketActivityVO.giftVO> pager = MybatisPlusUtil.pager(qto);
        QueryWrapper<BbcMarketActivityQTO.QTO> query = MybatisPlusUtil.query();
        query.and(i->i.likeRight("gift.`terminal`","10"));
        query.and(i->i.le("gift.`valid_start_time`",LocalDateTime.now()).ge("gift.`valid_end_time`",LocalDateTime.now()));
        iMarketMerchantGiftRepository.selectGiftBbcH5ListPage(pager,query);
        List<BbcMarketActivityVO.giftVO> listVOS=new ArrayList<>();
        List<BbcGoodsInfoVO.HomeAndShopInnerServiceVO>  goodsInfoVOS=null;
        List<String> goodsIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(pager)){
            for (BbcMarketActivityVO.giftVO cutVO : pager.getRecords()) {
                BbcMarketActivityVO.giftVO cutVO1 = new BbcMarketActivityVO.giftVO();
                BeanUtils.copyProperties(cutVO,cutVO1);
                listVOS.add(cutVO1);
                goodsIds.add(cutVO.getGoodsId());
            }
            if (ObjectUtils.isNotEmpty(goodsIds)){
                goodsInfoVOS = iBbcGoodsInfoRpc.getInnerSimpleServiceVO(goodsIds);
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
                    List<BbcMarketActivityVO.giftVO.giftGiveVO> giftGiveVOS=new ArrayList<>();
                    if (ObjectUtils.isNotEmpty(list)){
                        for (MarketMerchantGiftGoodsGive marketMerchantGiftGoodsGive : list) {
                            BbcMarketActivityVO.giftVO.giftGiveVO giftGiveVO = new BbcMarketActivityVO.giftVO.giftGiveVO();
                            BbcGoodsInfoVO.InnerServiceVO innerServiceVO = iBbcGoodsInfoRpc.innerSimpleServiceGoodsVO(marketMerchantGiftGoodsGive.getSkuId());
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
    public List<BbcMarketActivityVO.giftVO> giftViewFour(BaseDTO dto) {
        IPage<BbcMarketActivityVO.giftVO> pager = MybatisPlusUtil.pager(new BbcMarketActivityQTO.QTO());
        QueryWrapper<BbcMarketActivityQTO.QTO> query = MybatisPlusUtil.query();
        query.and(i->i.likeRight("gift.`terminal`","10"));
        query.and(i->i.le("gift.`valid_start_time`",LocalDateTime.now()).ge("gift.`valid_end_time`",LocalDateTime.now()));
        iMarketMerchantGiftRepository.selectGiftBbcH5ListPage(pager,query);
        List<BbcMarketActivityVO.giftVO> listVOS=new ArrayList<>();
        List<BbcGoodsInfoVO.HomeAndShopInnerServiceVO>  goodsInfoVOS=null;
        List<String> goodsIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(pager)){
            for (BbcMarketActivityVO.giftVO cutVO : pager.getRecords()) {
                BbcMarketActivityVO.giftVO cutVO1 = new BbcMarketActivityVO.giftVO();
                BeanUtils.copyProperties(cutVO,cutVO1);
                listVOS.add(cutVO1);
                goodsIds.add(cutVO.getGoodsId());
            }
            if (ObjectUtils.isNotEmpty(goodsIds)){
                goodsInfoVOS = iBbcGoodsInfoRpc.getInnerSimpleServiceVO(goodsIds);
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
                    List<BbcMarketActivityVO.giftVO.giftGiveVO> giftGiveVOS=new ArrayList<>();
                    if (ObjectUtils.isNotEmpty(list)){
                        for (MarketMerchantGiftGoodsGive marketMerchantGiftGoodsGive : list) {
                            BbcMarketActivityVO.giftVO.giftGiveVO giftGiveVO = new BbcMarketActivityVO.giftVO.giftGiveVO();
                            BbcGoodsInfoVO.InnerServiceVO innerServiceVO = iBbcGoodsInfoRpc.innerSimpleServiceGoodsVO(marketMerchantGiftGoodsGive.getSkuId());
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
            List<BbcMarketActivityVO.giftVO> list=new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                list.add(getValue(listVOS));
            }
            return list;

        }else {
            return listVOS;
        }
    }

    @Override
    public PageData<BbcMarketActivityVO.groupbuyVO> groupbuyList(BbcMarketActivityQTO.QTO qto) {
        IPage<BbcMarketActivityVO.groupbuyVO> pager = MybatisPlusUtil.pager(qto);
        QueryWrapper<BbcMarketActivityQTO.QTO> query = MybatisPlusUtil.query();
        query.and(i->i.likeRight("groupbuy.`terminal`","10"));
        query.and(i->i.le("groupbuy.`valid_start_time`",LocalDateTime.now()).ge("groupbuy.`valid_end_time`",LocalDateTime.now()));
        iMarketMerchantGroupbuyRepository.selectGroupbuyBbcH5ListPage(pager,query);
        List<BbcMarketActivityVO.groupbuyVO> listVOS=new ArrayList<>();
        List<BbcGoodsInfoVO.HomeAndShopInnerServiceVO>  goodsInfoVOS=null;
        List<String> goodsIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(pager)){
            for (BbcMarketActivityVO.groupbuyVO cutVO : pager.getRecords()) {
                BbcMarketActivityVO.groupbuyVO cutVO1 = new BbcMarketActivityVO.groupbuyVO();
                BeanUtils.copyProperties(cutVO,cutVO1);
                listVOS.add(cutVO1);
                goodsIds.add(cutVO.getGoodsId());
            }
            if (ObjectUtils.isNotEmpty(goodsIds)){
                goodsInfoVOS = iBbcGoodsInfoRpc.getInnerSimpleServiceVO(goodsIds);
            }
            if (ObjectUtils.isNotEmpty(listVOS) && ObjectUtils.isNotEmpty(goodsInfoVOS)){
                SetGoodsInfo(listVOS,goodsInfoVOS);
            }

        }
        return new PageData<>(listVOS,qto.getPageNum(),qto.getPageSize(),pager.getTotal());
    }

    @Override
    public List<BbcMarketActivityVO.groupbuyVO> groupbuyViewFour(BaseDTO dto) {
        IPage<BbcMarketActivityVO.groupbuyVO> pager = MybatisPlusUtil.pager(new BbcMarketActivityQTO.QTO());
        QueryWrapper<BbcMarketActivityQTO.QTO> query = MybatisPlusUtil.query();
        query.and(i->i.likeRight("groupbuy.`terminal`","10"));
        query.and(i->i.le("groupbuy.`valid_start_time`",LocalDateTime.now()).ge("groupbuy.`valid_end_time`",LocalDateTime.now()));
        iMarketMerchantGroupbuyRepository.selectGroupbuyBbcH5ListPage(pager,query);
        List<BbcMarketActivityVO.groupbuyVO> listVOS=new ArrayList<>();
        List<BbcGoodsInfoVO.HomeAndShopInnerServiceVO>  goodsInfoVOS=null;
        List<String> goodsIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(pager)){
            for (BbcMarketActivityVO.groupbuyVO cutVO : pager.getRecords()) {
                BbcMarketActivityVO.groupbuyVO cutVO1 = new BbcMarketActivityVO.groupbuyVO();
                BeanUtils.copyProperties(cutVO,cutVO1);
                listVOS.add(cutVO1);
                goodsIds.add(cutVO.getGoodsId());
            }
            if (ObjectUtils.isNotEmpty(goodsIds)){
                goodsInfoVOS = iBbcGoodsInfoRpc.getInnerSimpleServiceVO(goodsIds);
            }
            if (ObjectUtils.isNotEmpty(listVOS) && ObjectUtils.isNotEmpty(goodsInfoVOS)){
                SetGoodsInfo(listVOS,goodsInfoVOS);
            }

        }
        if (listVOS.size()>4){
            List<BbcMarketActivityVO.groupbuyVO> list=new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                list.add(getValue(listVOS));
            }
            return list;
        }else {
            return listVOS;
        }
    }

    @Override
    public List<BbcMarketActivityVO.merchantCard> merchantCard(BbcMarketMerchantActivityDTO.MerchantIdDTO dto) {
        QueryWrapper<MarketMerchantCard> query = MybatisPlusUtil.query();
        query.and(i->i.eq("shop_id",dto.getId()));
        query.and(i->i.eq("state",20));
        List<MarketMerchantCard> list = iMarketMerchantCardRepository.list(query);
        if (ObjectUtils.isNotEmpty(list)){
            List< BbcMarketActivityVO.merchantCard> cards=new ArrayList<>();
            for (MarketMerchantCard marketMerchantCard : list) {
                BbcMarketActivityVO.merchantCard merchantCard = new BbcMarketActivityVO.merchantCard();
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
    public BbcMarketActivityVO.activityVO activity(BbcMarketActivityQTO.IdQTO qto) {
        MarketPtActivity activity = iMarketPtActivityRepository.getById(qto.getId());
        if (ObjectUtils.isEmpty(activity)){
            throw new BusinessException("没有查询到此活动");
        }
        BbcMarketActivityVO.activityVO activityVO = new BbcMarketActivityVO.activityVO();
        BeanUtils.copyProperties(activity,activityVO);
        IPage<BbcMarketActivityVO.activityGoodsVO> pager = MybatisPlusUtil.pager(new BbcMarketActivityQTO.QTO());
        QueryWrapper<BbcMarketActivityDTO.IdDTO> query = MybatisPlusUtil.query();
        query.and(i->i.eq("goods.activity_id",qto.getId()));
        iMarketPtActivityRepository.selectActivityPageDataBbcH5(pager,query);
        List<BbcMarketActivityVO.activityGoodsVO> listVOS=new ArrayList<>();
        List<BbcGoodsInfoVO.HomeAndShopInnerServiceVO>  goodsInfoVOS=null;
        List<String> goodsIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(pager)){
            for (BbcMarketActivityVO.activityGoodsVO cutVO : pager.getRecords()) {
                BbcMarketActivityVO.activityGoodsVO cutVO1 = new BbcMarketActivityVO.activityGoodsVO();
                BeanUtils.copyProperties(cutVO,cutVO1);
                listVOS.add(cutVO1);
                goodsIds.add(cutVO.getGoodsId());
            }
            if (ObjectUtils.isNotEmpty(goodsIds)){
                goodsInfoVOS = iBbcGoodsInfoRpc.getInnerSimpleServiceVO(goodsIds);
            }
            if (ObjectUtils.isNotEmpty(listVOS) && ObjectUtils.isNotEmpty(goodsInfoVOS)){
                SetGoodsInfo(listVOS,goodsInfoVOS);
            }

        }
        activityVO.setGoodsVOList(new PageData<>(listVOS,qto.getPageNum(),qto.getPageSize(),pager.getTotal()));
        return activityVO;
    }

    @Override
    public BbcMarketActivityVO.merchantCardSuccess userReciveCard(BbcMarketMerchantActivityDTO.CardIdDTO dto) {
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
        BbcMarketActivityVO.merchantCardSuccess merchantCardSuccess = new BbcMarketActivityVO.merchantCardSuccess();
        BeanUtils.copyProperties(marketMerchantCard,merchantCardSuccess);
        BbcShopVO.InnerDetailVO innerDetailVO = iBbcShopRpc.innerDetailShop(new BbcShopQTO.InnerShopQTO(marketMerchantCard.getShopId()));
        if (ObjectUtils.isNotEmpty(innerDetailVO)){
            merchantCardSuccess.setShopName(innerDetailVO.getShopName());
        }
        merchantCardSuccess.setRule("满"+marketMerchantCard.getCutPrice()+"减"+marketMerchantCard.getToPrice());
        return merchantCardSuccess;
    }

    @Override
    public BbcMarketActivityVO.jurisdiction jurisdiction() {
        BbcMarketActivityVO.jurisdiction jurisdiction = new BbcMarketActivityVO.jurisdiction();
        List< BbcMarketActivityVO.jurisdiction.state> pc=new ArrayList<>();
        List< BbcMarketActivityVO.jurisdiction.state> h5=new ArrayList<>();
        pc.add( new BbcMarketActivityVO.jurisdiction.state(10,Integer.parseInt(pcGroupbuy)));
        pc.add( new BbcMarketActivityVO.jurisdiction.state(20,Integer.parseInt(pcDiscount)));
        pc.add( new BbcMarketActivityVO.jurisdiction.state(30,Integer.parseInt(pcCut)));
        pc.add( new BbcMarketActivityVO.jurisdiction.state(40,Integer.parseInt(pcGift)));
        h5.add( new BbcMarketActivityVO.jurisdiction.state(10,Integer.parseInt(h5Groupbuy)));
        h5.add( new BbcMarketActivityVO.jurisdiction.state(20,Integer.parseInt(h5Discount)));
        h5.add( new BbcMarketActivityVO.jurisdiction.state(30,Integer.parseInt(h5Cut)));
        h5.add( new BbcMarketActivityVO.jurisdiction.state(40,Integer.parseInt(h5Gift)));
        jurisdiction.setH5Activity(h5);
        jurisdiction.setPcActivity(pc);
        return jurisdiction;
    }

    public  <T> T getValue( List<T> mArrayList){
        Random mRandom = new Random();
        int a = mRandom.nextInt(mArrayList.size()-1);
        mArrayList.remove(a);
        return mArrayList.get(a);
    }
    private void SetGoodsInfo(List<? extends BbcMarketActivityVO.ListYiVO> listVOS, List<BbcGoodsInfoVO.HomeAndShopInnerServiceVO> goodsInfoVOS) {
        for (int i = 0; i < listVOS.size(); i++) {
            for (BbcGoodsInfoVO.HomeAndShopInnerServiceVO goodsInfoVO : goodsInfoVOS) {
                if (listVOS.get(i).getGoodsId().equals(goodsInfoVO.getId())){
                    listVOS.get(i).setGoodsImg(goodsInfoVO.getGoodsImage());
                    listVOS.get(i).setGoodsName(goodsInfoVO.getGoodsName()+","+goodsInfoVO.getGoodsTitle());
                    listVOS.get(i).setOldPrice(goodsInfoVO.getSalePrice());
                    listVOS.get(i).setGoodsTitle(goodsInfoVO.getGoodsTitle());
                }
            }
        }
    }

    private List<BbcMarketActivityVO.GroupbuyActivity> GoupbuyActivity(BbcMarketActivityDTO.DTO dto) {
        List<BbcMarketActivityVO.GroupbuyActivity> groupbuyActivity=new ArrayList<>();
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
                    BbcMarketActivityVO.GroupbuyActivity groupbuyActivity1 = new BbcMarketActivityVO.GroupbuyActivity();
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

    private List<BbcMarketActivityVO.GiftActivity> GiftActivity(BbcMarketActivityDTO.DTO dto) {
        List<BbcMarketActivityVO.GiftActivity> giftActivity=new ArrayList<>();
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
                    BbcMarketActivityVO.GiftActivity GiftActivity1 = new BbcMarketActivityVO.GiftActivity();
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

    private List<BbcMarketActivityVO.CutActivity> CutActivity(BbcMarketActivityDTO.DTO dto) {
        List<BbcMarketActivityVO.CutActivity> cutActivity=new ArrayList<>();
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
                    BbcMarketActivityVO.CutActivity cutActivity1 = new BbcMarketActivityVO.CutActivity();
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

    private List<BbcMarketActivityVO.ScountActivity> ScountActivity(BbcMarketActivityDTO.DTO dto) {
        List<BbcMarketActivityVO.ScountActivity> scountActivity=new ArrayList<>();
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
                    BbcMarketActivityVO.ScountActivity scountActivity1 = new BbcMarketActivityVO.ScountActivity();
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

    private List<BbcMarketActivityVO.PlataformActivity> PlataformActivity(BbcMarketActivityDTO.DTO dto) {
        List<BbcMarketActivityVO.PlataformActivity> plataformActivity=new ArrayList<>();
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
                    BbcMarketActivityVO.PlataformActivity plataformActivity1 = new BbcMarketActivityVO.PlataformActivity();
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
    private void CheckActivityTime(LocalDateTime activityStartTime, LocalDateTime activityEndTime,  BbcMarketActivityVO.ListCardVO plataformActivity1) {
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
    private void CheckActivityTime(LocalDateTime activityStartTime, LocalDateTime activityEndTime,  BbcMarketActivityVO.GroupbuyActivity plataformActivity1) {
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

    private void CheckActivityTime(LocalDateTime activityStartTime, LocalDateTime activityEndTime, BbcMarketActivityVO.PlataformActivity plataformActivity1) {
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
    private void CheckActivityTime(LocalDateTime activityStartTime, LocalDateTime activityEndTime, BbcMarketActivityVO.ScountActivity plataformActivity1) {
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
    private void CheckActivityTime(LocalDateTime activityStartTime, LocalDateTime activityEndTime, BbcMarketActivityVO.CutActivity plataformActivity1) {
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
    private void CheckActivityTime(LocalDateTime activityStartTime, LocalDateTime activityEndTime, BbcMarketActivityVO.GiftActivity plataformActivity1) {
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

	@Override
	public TopicVO listFlashsale(BaseDTO dto) {
		
		//查询生成进行的活动
		QueryWrapper<MarketPtActivity> wrapper = new QueryWrapper<>();
        wrapper.eq("label","秒杀");
        wrapper.le("activity_start_time", LocalDateTime.now());
        wrapper.ge("activity_end_time", LocalDateTime.now());
        //查询商品的活动
        MarketPtActivity marketPtActivity = iMarketPtActivityRepository.getOne(wrapper);
        if(marketPtActivity==null){
        	return null;
        }
        
        if (ObjectUtils.isEmpty(marketPtActivity)){
            return null;
        }
        TopicVO topicVO = new TopicVO();
        
        topicVO.setId(marketPtActivity.getId());
        topicVO.setName("热门秒杀");
        topicVO.setRemark("秒优惠 享好礼");
        
        PCBbbMarketActivityVO.activityVO activityVO = new PCBbbMarketActivityVO.activityVO();
        BeanUtils.copyProperties(marketPtActivity,activityVO);
        IPage<PCBbbMarketActivityVO.activityGoodsVO> pager = MybatisPlusUtil.pager(new PCBbbMarketActivityQTO.QTO());
        QueryWrapper<BbbMarketActivityDTO.IdDTO> query = MybatisPlusUtil.query();
        query.and(i->i.eq("goods.activity_id",marketPtActivity.getId()));
        iMarketPtActivityRepository.selectActivityPageData(pager,query);
        
        List<GoodsInfoVO.DetailVO> goodsList = new ArrayList<GoodsInfoVO.DetailVO>();
        if (ObjectUtils.isNotEmpty(pager)){
            for (PCBbbMarketActivityVO.activityGoodsVO cutVO : pager.getRecords()) {
            	
            	GoodsInfoVO.DetailVO goodsInfoDetailVO= goodsInfoRpc.getGoodsDetail(new GoodsInfoDTO.IdDTO(cutVO.getGoodsId()));
            	goodsList.add(goodsInfoDetailVO);
        		
            }
        }
        topicVO.setGoodsList(goodsList);
        
		return topicVO;
	}


}
