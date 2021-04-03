package com.gs.lshly.biz.support.trade.service.bbb.pc.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantCard;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantCardUsers;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantCut;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantCutGoods;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantDiscount;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantDiscountGoods;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantGift;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantGiftGoods;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantGiftGoodsGive;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantGroupbuy;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantGroupbuyGoods;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantGroupbuyGoodsSku;
import com.gs.lshly.biz.support.trade.entity.MarketPtActivity;
import com.gs.lshly.biz.support.trade.entity.MarketPtActivityGoodsSpu;
import com.gs.lshly.biz.support.trade.entity.MarketPtActivityJurisdiction;
import com.gs.lshly.biz.support.trade.entity.MarketPtActivityMerchant;
import com.gs.lshly.biz.support.trade.enums.PlatformCardCheckStatusEnum;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantCardRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantCardUsersRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantCutGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantCutRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantDiscountGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantDiscountRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantGiftGoodsGiveRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantGiftGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantGiftRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantGroupbuyGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantGroupbuyGoodsSkuRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantGroupbuyRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketPtActivityGoodsSkuRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketPtActivityGoodsSpuRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketPtActivityJurisdictionRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketPtActivityMerchantRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketPtActivityRepository;
import com.gs.lshly.biz.support.trade.service.bbb.pc.IPCBbbMarketActivityService;
import com.gs.lshly.common.enums.ActivitySignEnum;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsInfoVO;
import com.gs.lshly.common.struct.bbb.pc.merchant.vo.BbbShopVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbMarketActivityDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbMarketMerchantActivityDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbMarketActivityQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketActivityVO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketActivityVO.FlashsaleVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcMarketMerchantActivityDTO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbb.pc.commodity.IPCBbbGoodsInfoRpc;
import com.gs.lshly.rpc.api.bbb.pc.merchant.IBbbShopRpc;


/**
* <p>
*  服务实现类
* </p>
* @author zdf
* @since 2021-01-08
*/
@Component
public class PCBbbMarketActivityServiceImpl implements IPCBbbMarketActivityService {

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
    private IMarketMerchantGroupbuyGoodsSkuRepository iMarketMerchantGroupbuyGoodsSkuRepository;
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
    private IPCBbbGoodsInfoRpc ipcBbbGoodsInfoRpc;
    @DubboReference
    private IBbbShopRpc iBbbShopRpc;

    @Override
    public PageData<PCBbbMarketActivityVO.cutVO> cutList(PCBbbMarketActivityQTO.QTO qto) {
        IPage<PCBbbMarketActivityVO.cutVO> pager = MybatisPlusUtil.pager(qto);
        QueryWrapper<PCBbbMarketActivityQTO.QTO> query = MybatisPlusUtil.query();
        query.and(i->i.likeRight("cut.`terminal`",10));
        if (ObjectUtils.isNotEmpty(qto.getShoId())) {
            query.and(i -> i.eq("cut.shop_id",qto.getShoId() ));
        }
        query.and(i->i.le("cut.`valid_start_time`",LocalDateTime.now()).ge("cut.`valid_end_time`",LocalDateTime.now()));
        iMarketMerchantCutRepository.selectCutListPage(pager, query);
        List<PCBbbMarketActivityVO.cutVO> listVOS=new ArrayList<>();
        List<PCBbbGoodsInfoVO.HomeInnerServiceVO>  goodsInfoVOS=null;
        List<String> goodsIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(pager)){
            for (PCBbbMarketActivityVO.cutVO cutVO : pager.getRecords()) {
                PCBbbMarketActivityVO.cutVO cutVO1 = new PCBbbMarketActivityVO.cutVO();
                BeanUtils.copyProperties(cutVO,cutVO1);
                listVOS.add(cutVO1);
                goodsIds.add(cutVO.getGoodsId());
            }
            if (ObjectUtils.isNotEmpty(goodsIds)){
                goodsInfoVOS = ipcBbbGoodsInfoRpc.getHomeGoodsInnerServiceVO(goodsIds, qto);
            }
            if (ObjectUtils.isNotEmpty(listVOS) && ObjectUtils.isNotEmpty(goodsInfoVOS)){
                SetGoodsInfo(listVOS,goodsInfoVOS);
            }

        }
        return new PageData<>(listVOS,qto.getPageNum(),qto.getPageSize(),pager.getTotal());
    }
    /**
     * 根据key去重重复
     * @param keyExtractor key执行器
     * @param <T>          泛型
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }


    @Override
    public List<PCBbbMarketActivityVO.cutVO> viewFour(BaseDTO dto) {
        IPage<PCBbbMarketActivityVO.cutVO> pager = MybatisPlusUtil.pager(new PCBbbMarketActivityQTO.QTO());
        QueryWrapper<PCBbbMarketActivityQTO.QTO> query = MybatisPlusUtil.query();
        query.and(i->i.likeRight("cut.`terminal`",10));
        query.and(i->i.le("cut.`valid_start_time`",LocalDateTime.now()).ge("cut.`valid_end_time`",LocalDateTime.now()));
        iMarketMerchantCutRepository.selectCutListPage(pager, query);
        List<PCBbbMarketActivityVO.cutVO> listVOS=new ArrayList<>();
        List<PCBbbGoodsInfoVO.HomeInnerServiceVO>  goodsInfoVOS=null;
        List<String> goodsIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(pager)){
            for (PCBbbMarketActivityVO.cutVO cutVO : pager.getRecords()) {
                PCBbbMarketActivityVO.cutVO cutVO1 = new PCBbbMarketActivityVO.cutVO();
                BeanUtils.copyProperties(cutVO,cutVO1);
                listVOS.add(cutVO1);
                goodsIds.add(cutVO.getGoodsId());
            }
            if (ObjectUtils.isNotEmpty(goodsIds)){
                goodsInfoVOS = ipcBbbGoodsInfoRpc.getHomeGoodsInnerServiceVO(goodsIds, dto);
            }
            if (ObjectUtils.isNotEmpty(listVOS) && ObjectUtils.isNotEmpty(goodsInfoVOS)){
                SetGoodsInfo(listVOS,goodsInfoVOS);
            }

        }

        if (listVOS.size()>4){
            List<PCBbbMarketActivityVO.cutVO> list=new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                list.add(getValue(listVOS));
            }
            return list;
        }else {
            return listVOS;
        }
    }

    @Override
    public PageData<PCBbbMarketActivityVO.discountVO> discountList(PCBbbMarketActivityQTO.QTO qto) {
        IPage<PCBbbMarketActivityVO.discountVO> pager = MybatisPlusUtil.pager(new PCBbbMarketActivityQTO.QTO());
        QueryWrapper<PCBbbMarketActivityQTO.QTO> query = MybatisPlusUtil.query();
        query.and(i->i.likeRight("discount.`terminal`","10"));
        if (ObjectUtils.isNotEmpty(qto.getShoId())) {
            query.and(i -> i.eq("discount.shop_id",qto.getShoId() ));
        }
        query.and(i->i.le("discount.`valid_start_time`",LocalDateTime.now()).ge("discount.`valid_end_time`",LocalDateTime.now()));
        iMarketMerchantDiscountRepository.selectDiscountListPage(pager,query);
        List<PCBbbMarketActivityVO.discountVO> listVOS=new ArrayList<>();
        List<PCBbbGoodsInfoVO.HomeInnerServiceVO>  goodsInfoVOS=null;
        List<String> goodsIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(pager)){
            for (PCBbbMarketActivityVO.discountVO cutVO : pager.getRecords()) {
                PCBbbMarketActivityVO.discountVO cutVO1 = new PCBbbMarketActivityVO.discountVO();
                BeanUtils.copyProperties(cutVO,cutVO1);
                listVOS.add(cutVO1);
                goodsIds.add(cutVO.getGoodsId());
            }
            if (ObjectUtils.isNotEmpty(goodsIds)){
                goodsInfoVOS = ipcBbbGoodsInfoRpc.getHomeGoodsInnerServiceVO(goodsIds, qto);
            }
            if (ObjectUtils.isNotEmpty(listVOS) && ObjectUtils.isNotEmpty(goodsInfoVOS)){
                SetGoodsInfo(listVOS,goodsInfoVOS);
            }

        }

        return new PageData<>(listVOS,qto.getPageNum(),qto.getPageSize(),pager.getTotal());
    }

    @Override
    public List<PCBbbMarketActivityVO.discountVO> discountViewFour(BaseDTO dto) {
        IPage<PCBbbMarketActivityVO.discountVO> pager = MybatisPlusUtil.pager(new PCBbbMarketActivityQTO.QTO());
        QueryWrapper<PCBbbMarketActivityQTO.QTO> query = MybatisPlusUtil.query();
        query.and(i -> i.likeRight("discount.`terminal`", "10"));
        query.and(i -> i.le("discount.`valid_start_time`", LocalDateTime.now()).ge("discount.`valid_end_time`", LocalDateTime.now()));
        iMarketMerchantDiscountRepository.selectDiscountListPage(pager, query);
        List<PCBbbMarketActivityVO.discountVO> listVOS = new ArrayList<>();
        List<PCBbbGoodsInfoVO.HomeInnerServiceVO> goodsInfoVOS = null;
        List<String> goodsIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(pager)) {
            for (PCBbbMarketActivityVO.discountVO cutVO : pager.getRecords()) {
                PCBbbMarketActivityVO.discountVO cutVO1 = new PCBbbMarketActivityVO.discountVO();
                BeanUtils.copyProperties(cutVO, cutVO1);
                listVOS.add(cutVO1);
                goodsIds.add(cutVO.getGoodsId());
            }
            if (ObjectUtils.isNotEmpty(goodsIds)) {
                goodsInfoVOS = ipcBbbGoodsInfoRpc.getHomeGoodsInnerServiceVO(goodsIds, dto);
            }
            if (ObjectUtils.isNotEmpty(listVOS) && ObjectUtils.isNotEmpty(goodsInfoVOS)) {
                SetGoodsInfo(listVOS, goodsInfoVOS);
            }

        }

        if (listVOS.size() > 4) {
            List<PCBbbMarketActivityVO.discountVO> list = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                list.add(getValue(listVOS));
            }
            return list;
        } else {
            return listVOS;
        }
    }

    @Override
    public PageData<PCBbbMarketActivityVO.giftVO> giftList(PCBbbMarketActivityQTO.QTO qto) {
        IPage<PCBbbMarketActivityVO.giftVO> pager = MybatisPlusUtil.pager(new PCBbbMarketActivityQTO.QTO());
        QueryWrapper<PCBbbMarketActivityQTO.QTO> query = MybatisPlusUtil.query();
        query.and(i->i.likeRight("gift.`terminal`","10"));
        if (ObjectUtils.isNotEmpty(qto.getShoId())) {
            query.and(i -> i.eq("gift.shop_id",qto.getShoId() ));
        }
        query.and(i->i.le("gift.`valid_start_time`",LocalDateTime.now()).ge("gift.`valid_end_time`",LocalDateTime.now()));
        iMarketMerchantGiftRepository.selectGiftListPage(pager,query);
        List<PCBbbMarketActivityVO.giftVO> listVOS=new ArrayList<>();
        List<PCBbbGoodsInfoVO.HomeInnerServiceVO>  goodsInfoVOS=null;
        List<String> goodsIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(pager)){
            for (PCBbbMarketActivityVO.giftVO cutVO : pager.getRecords()) {
                PCBbbMarketActivityVO.giftVO cutVO1 = new PCBbbMarketActivityVO.giftVO();
                BeanUtils.copyProperties(cutVO,cutVO1);
                listVOS.add(cutVO1);
                goodsIds.add(cutVO.getGoodsId());
            }
            if (ObjectUtils.isNotEmpty(goodsIds)){
                goodsInfoVOS = ipcBbbGoodsInfoRpc.getHomeGoodsInnerServiceVO(goodsIds, qto);
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
                    List<PCBbbMarketActivityVO.giftVO.giftGiveVO> giftGiveVOS=new ArrayList<>();
                    if (ObjectUtils.isNotEmpty(list)){
                        for (MarketMerchantGiftGoodsGive marketMerchantGiftGoodsGive : list) {
                            PCBbbMarketActivityVO.giftVO.giftGiveVO giftGiveVO = new PCBbbMarketActivityVO.giftVO.giftGiveVO();
                            PCBbbGoodsInfoVO.InnerServiceVO innerServiceVO = ipcBbbGoodsInfoRpc.innerSimpleServiceVO(marketMerchantGiftGoodsGive.getSkuId());
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
    public List<PCBbbMarketActivityVO.giftVO> giftViewFour(BaseDTO dto) {
        IPage<PCBbbMarketActivityVO.giftVO> pager = MybatisPlusUtil.pager(new PCBbbMarketActivityQTO.QTO());
        QueryWrapper<PCBbbMarketActivityQTO.QTO> query = MybatisPlusUtil.query();
        query.and(i->i.likeRight("gift.`terminal`","10"));
        query.and(i->i.le("gift.`valid_start_time`",LocalDateTime.now()).ge("gift.`valid_end_time`",LocalDateTime.now()));
        iMarketMerchantGiftRepository.selectGiftListPage(pager,query);
        List<PCBbbMarketActivityVO.giftVO> listVOS=new ArrayList<>();
        List<PCBbbGoodsInfoVO.HomeInnerServiceVO>  goodsInfoVOS=null;
        List<String> goodsIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(pager)){
            for (PCBbbMarketActivityVO.giftVO cutVO : pager.getRecords()) {
                PCBbbMarketActivityVO.giftVO cutVO1 = new PCBbbMarketActivityVO.giftVO();
                BeanUtils.copyProperties(cutVO,cutVO1);
                listVOS.add(cutVO1);
                goodsIds.add(cutVO.getGoodsId());
            }
            if (ObjectUtils.isNotEmpty(goodsIds)){
                goodsInfoVOS = ipcBbbGoodsInfoRpc.getHomeGoodsInnerServiceVO(goodsIds, dto);
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
                    List<PCBbbMarketActivityVO.giftVO.giftGiveVO> giftGiveVOS=new ArrayList<>();
                    if (ObjectUtils.isNotEmpty(list)){
                        for (MarketMerchantGiftGoodsGive marketMerchantGiftGoodsGive : list) {
                            PCBbbMarketActivityVO.giftVO.giftGiveVO giftGiveVO = new PCBbbMarketActivityVO.giftVO.giftGiveVO();
                            PCBbbGoodsInfoVO.InnerServiceVO innerServiceVO = ipcBbbGoodsInfoRpc.innerSimpleServiceVO(marketMerchantGiftGoodsGive.getSkuId());
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
            List<PCBbbMarketActivityVO.giftVO> list=new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                list.add(getValue(listVOS));
            }
            return list;

        }else {
            return listVOS;
        }
    }

    @Override
    public List<PCBbbMarketActivityVO.groupbuyVO> groupbuyViewFour(BaseDTO dto) {
        IPage<PCBbbMarketActivityVO.groupbuyVO> pager = MybatisPlusUtil.pager(new PCBbbMarketActivityQTO.QTO());
        QueryWrapper<PCBbbMarketActivityQTO.QTO> query = MybatisPlusUtil.query();
        query.and(i->i.likeRight("groupbuy.`terminal`","10"));
        query.and(i->i.le("groupbuy.`valid_start_time`",LocalDateTime.now()).ge("groupbuy.`valid_end_time`",LocalDateTime.now()));
        iMarketMerchantGroupbuyRepository.selectGroupbuyListPage(pager,query);
        List<PCBbbMarketActivityVO.groupbuyVO> listVOS=new ArrayList<>();
        List<PCBbbGoodsInfoVO.HomeInnerServiceVO>  goodsInfoVOS=null;
        List<String> goodsIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(pager)){
            for (PCBbbMarketActivityVO.groupbuyVO cutVO : pager.getRecords()) {
                PCBbbMarketActivityVO.groupbuyVO cutVO1 = new PCBbbMarketActivityVO.groupbuyVO();
                BeanUtils.copyProperties(cutVO,cutVO1);
                listVOS.add(cutVO1);
                QueryWrapper<MarketMerchantGroupbuyGoodsSku> query1 = MybatisPlusUtil.query();
                query1.and(i->i.eq("groupbuy_id",cutVO.getId()));
                query1.and(i->i.eq("goods_id",cutVO.getGoodsId()));
                query1.last("limit 0,1");
                MarketMerchantGroupbuyGoodsSku one = iMarketMerchantGroupbuyGoodsSkuRepository.getOne(query1);
                if (ObjectUtils.isNotEmpty(one)){
                    cutVO1.setGroupbuyPrice(one.getGroupbuySaleSkuPrice());
                }
                goodsIds.add(cutVO.getGoodsId());
            }
            if (ObjectUtils.isNotEmpty(goodsIds)){
                goodsInfoVOS = ipcBbbGoodsInfoRpc.getHomeGoodsInnerServiceVO(goodsIds, dto);
            }
            if (ObjectUtils.isNotEmpty(listVOS) && ObjectUtils.isNotEmpty(goodsInfoVOS)){
                SetGoodsInfo(listVOS,goodsInfoVOS);
            }

        }

        if (listVOS.size()>4){
            List<PCBbbMarketActivityVO.groupbuyVO> list=new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                list.add(getValue(listVOS));
            }
            return list;
        }else {
            return listVOS;
        }
    }

    @Override
    public PageData<PCBbbMarketActivityVO.groupbuyVO> groupbuyList(PCBbbMarketActivityQTO.QTO qto) {
        IPage<PCBbbMarketActivityVO.groupbuyVO> pager = MybatisPlusUtil.pager(new PCBbbMarketActivityQTO.QTO());
        QueryWrapper<PCBbbMarketActivityQTO.QTO> query = MybatisPlusUtil.query();
        query.and(i->i.likeRight("groupbuy.`terminal`","10"));
        if (ObjectUtils.isNotEmpty(qto.getShoId())) {
            query.and(i -> i.eq("groupbuy.shop_id",qto.getShoId() ));
        }
        query.and(i->i.le("groupbuy.`valid_start_time`",LocalDateTime.now()).ge("groupbuy.`valid_end_time`",LocalDateTime.now()));
        iMarketMerchantGroupbuyRepository.selectGroupbuyListPage(pager,query);
        List<PCBbbMarketActivityVO.groupbuyVO> listVOS=new ArrayList<>();
        List<PCBbbGoodsInfoVO.HomeInnerServiceVO>  goodsInfoVOS=null;
        List<String> goodsIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(pager)){
            for (PCBbbMarketActivityVO.groupbuyVO cutVO : pager.getRecords()) {
                PCBbbMarketActivityVO.groupbuyVO cutVO1 = new PCBbbMarketActivityVO.groupbuyVO();
                BeanUtils.copyProperties(cutVO,cutVO1);
                goodsIds.add(cutVO.getGoodsId());
                QueryWrapper<MarketMerchantGroupbuyGoodsSku> query1 = MybatisPlusUtil.query();
                query1.and(i->i.eq("groupbuy_id",cutVO.getId()));
                query1.and(i->i.eq("goods_id",cutVO.getGoodsId()));
                query1.last("limit 0,1");
                MarketMerchantGroupbuyGoodsSku one = iMarketMerchantGroupbuyGoodsSkuRepository.getOne(query1);
                if (ObjectUtils.isNotEmpty(one)){
                    cutVO1.setGroupbuyPrice(one.getGroupbuySaleSkuPrice());
                }
                listVOS.add(cutVO1);
            }
            if (ObjectUtils.isNotEmpty(goodsIds)){
                goodsInfoVOS = ipcBbbGoodsInfoRpc.getHomeGoodsInnerServiceVO(goodsIds, qto);
            }
            if (ObjectUtils.isNotEmpty(listVOS) && ObjectUtils.isNotEmpty(goodsInfoVOS)){
                SetGoodsInfo(listVOS,goodsInfoVOS);
            }

        }

        return new PageData<>(listVOS,qto.getPageNum(),qto.getPageSize(),pager.getTotal());
    }

    @Override
    public PCBbbMarketActivityVO.activityVO activity(PCBbbMarketActivityQTO.IdQTO qto) {
        MarketPtActivity activity = iMarketPtActivityRepository.getById(qto.getId());
        if (ObjectUtils.isEmpty(activity)){
            throw new BusinessException("没有查询到此活动");
        }
        PCBbbMarketActivityVO.activityVO activityVO = new PCBbbMarketActivityVO.activityVO();
        BeanUtils.copyProperties(activity,activityVO);
        IPage<PCBbbMarketActivityVO.activityGoodsVO> pager = MybatisPlusUtil.pager(qto);
        QueryWrapper<BbbMarketActivityDTO.IdDTO> query = MybatisPlusUtil.query();
        query.and(i->i.eq("goods.activity_id",qto.getId()));
        iMarketPtActivityRepository.selectActivityPageData(pager,query);
        List<PCBbbMarketActivityVO.activityGoodsVO> listVOS=new ArrayList<>();
        List<PCBbbGoodsInfoVO.HomeInnerServiceVO>  goodsInfoVOS=null;
        List<String> goodsIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(pager)){
            for (PCBbbMarketActivityVO.activityGoodsVO cutVO : pager.getRecords()) {
                PCBbbMarketActivityVO.activityGoodsVO cutVO1 = new PCBbbMarketActivityVO.activityGoodsVO();
                BeanUtils.copyProperties(cutVO,cutVO1);
                listVOS.add(cutVO1);
                goodsIds.add(cutVO.getGoodsId());
            }
            if (ObjectUtils.isNotEmpty(goodsIds)){
                goodsInfoVOS = ipcBbbGoodsInfoRpc.getHomeGoodsInnerServiceVO(goodsIds, qto);
            }
            if (ObjectUtils.isNotEmpty(listVOS) && ObjectUtils.isNotEmpty(goodsInfoVOS)){
                SetGoodsInfo(listVOS,goodsInfoVOS);
            }

        }
        activityVO.setGoodsVOList(new PageData<>(listVOS,qto.getPageNum(),qto.getPageSize(),pager.getTotal()));
        return activityVO;
    }

    @Override
    public ResponseData<PCBbbMarketActivityVO.ListActivityVO> activityList(BbbMarketMerchantActivityDTO.IdDTO dto) {
        PCBbbMarketActivityVO.ListActivityVO listVO = new PCBbbMarketActivityVO.ListActivityVO();
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

    private List<PCBbbMarketActivityVO.merchantCard> Car(BbbMarketMerchantActivityDTO.IdDTO dto) {
        QueryWrapper<MarketMerchantCard> query = MybatisPlusUtil.query();
        query.and(i->i.eq("g.`goods_id`",dto.getId()));
        List<MarketMerchantCard> marketMerchantCardList= iMarketMerchantCardRepository.getByGoodsId(query);
        if (ObjectUtils.isNotEmpty(marketMerchantCardList)){
            List<PCBbbMarketActivityVO.merchantCard> list=marketMerchantCardList.parallelStream().map(e ->{
                PCBbbMarketActivityVO.merchantCard merchantCard = new PCBbbMarketActivityVO.merchantCard();
                merchantCard.setId(e.getId()).setRule("满"+e.getToPrice()+"减"+e.getCutPrice()).setFaceValue(e.getCutPrice());
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
    public List<PCBbbMarketActivityVO.merchantCard> merchantCard(BbbMarketMerchantActivityDTO.MerchantIdDTO dto) {
        QueryWrapper<MarketMerchantCard> query = MybatisPlusUtil.query();
        query.and(i->i.eq("shop_id",dto.getId()));
        query.and(i->i.eq("state",20));
        List<MarketMerchantCard> list = iMarketMerchantCardRepository.list(query);
        if (ObjectUtils.isNotEmpty(list)){
            List< PCBbbMarketActivityVO.merchantCard> cards=new ArrayList<>();
            for (MarketMerchantCard marketMerchantCard : list) {
                PCBbbMarketActivityVO.merchantCard merchantCard = new PCBbbMarketActivityVO.merchantCard();
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
    public PCBbbMarketActivityVO.merchantCardSuccess userReciveCard(BbbMarketMerchantActivityDTO.CardIdDTO dto) {
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
        PCBbbMarketActivityVO.merchantCardSuccess merchantCardSuccess = new PCBbbMarketActivityVO.merchantCardSuccess();
        BeanUtils.copyProperties(marketMerchantCard,merchantCardSuccess);
        BbbShopVO.ComplexDetailVO complexDetailVO = iBbbShopRpc.inneComplexDetailShop(marketMerchantCard.getShopId(), dto);
        if (ObjectUtils.isNotEmpty(complexDetailVO)){
            merchantCardSuccess.setShopName(complexDetailVO.getShopName());
        }
        merchantCardSuccess.setRule("满"+marketMerchantCard.getCutPrice()+"减"+marketMerchantCard.getToPrice());
        return merchantCardSuccess;

    }

    @Override
    public PCBbbMarketActivityVO.jurisdiction jurisdiction() {
        MarketPtActivityJurisdiction one = iMarketPtActivityJurisdictionRepository.getOne(null);
        if (ObjectUtils.isNotEmpty(one)) {
            PCBbbMarketActivityVO.jurisdiction jurisdiction = new PCBbbMarketActivityVO.jurisdiction();
            List<PCBbbMarketActivityVO.jurisdiction.state> pc = new ArrayList<>();
            List<PCBbbMarketActivityVO.jurisdiction.state> h5 = new ArrayList<>();
            pc.add(new PCBbbMarketActivityVO.jurisdiction.state(10, one.getPcGroupbuy()));
            pc.add(new PCBbbMarketActivityVO.jurisdiction.state(20, one.getPcDiscount()));
            pc.add(new PCBbbMarketActivityVO.jurisdiction.state(30, one.getPcCut()));
            pc.add(new PCBbbMarketActivityVO.jurisdiction.state(40, one.getPcGift()));
            h5.add(new PCBbbMarketActivityVO.jurisdiction.state(10, one.getH5Groupbuy()));
            h5.add(new PCBbbMarketActivityVO.jurisdiction.state(20, one.getH5Discount()));
            h5.add(new PCBbbMarketActivityVO.jurisdiction.state(30, one.getH5Cut()));
            h5.add(new PCBbbMarketActivityVO.jurisdiction.state(40, one.getH5Gift()));
            jurisdiction.setH5Activity(h5);
            jurisdiction.setPcActivity(pc);
            return jurisdiction;
        }
        return null;
    }

    @Override
    public PageData<PCBbbMarketActivityVO.activityListPageVO> activityListPage(PCBbbMarketActivityQTO.QTO qto) {
        QueryWrapper<PCBbbMarketActivityQTO.QTO> query = MybatisPlusUtil.query();
        query.and(i->i.le("online_start_time",LocalDateTime.now()));
        query.and(i->i.ge("activity_end_time",LocalDateTime.now()));
        IPage<PCBbbMarketActivityVO.activityListPageVO> pager = MybatisPlusUtil.pager(qto);
        iMarketPtActivityRepository.activityListPage(pager,query);
        return MybatisPlusUtil.toPageData(qto,PCBbbMarketActivityVO.activityListPageVO.class,pager);
    }

    @Override
    public List<PCBbbMarketActivityVO.merchantCard> activityCardGoodsInfo(BbcMarketMerchantActivityDTO.MerchantIdDTO dto) {
        QueryWrapper<MarketMerchantCard> query = MybatisPlusUtil.query();
        query.and(i->i.eq("c.`shop_id`",dto.getId()));
        query.and(i->i.eq("g.`goods_id`",dto.getGoodsId()));
        query.and(i->i.eq("c.`state`",20));
        query.and(i->i.le("c.`valid_start_time`",LocalDateTime.now()).ge("c.`valid_end_time`",LocalDateTime.now()));
        List<MarketMerchantCard> list = iMarketMerchantCardRepository.selectCard(query);
        if (ObjectUtils.isNotEmpty(list)){
            List< PCBbbMarketActivityVO.merchantCard> cards=new ArrayList<>();
            for (MarketMerchantCard marketMerchantCard : list) {
                PCBbbMarketActivityVO.merchantCard merchantCard = new PCBbbMarketActivityVO.merchantCard();
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

    private List<PCBbbMarketActivityVO.GroupbuyActivity> GoupbuyActivity(BbbMarketMerchantActivityDTO.IdDTO dto) {
        List<PCBbbMarketActivityVO.GroupbuyActivity> groupbuyActivity=new ArrayList<>();
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
                    PCBbbMarketActivityVO.GroupbuyActivity groupbuyActivity1 = new PCBbbMarketActivityVO.GroupbuyActivity();
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
    private List<PCBbbMarketActivityVO.GiftActivity> GiftActivity(BbbMarketMerchantActivityDTO.IdDTO dto) {
        List<PCBbbMarketActivityVO.GiftActivity> giftActivity=new ArrayList<>();
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
                    PCBbbMarketActivityVO.GiftActivity GiftActivity1 = new PCBbbMarketActivityVO.GiftActivity();
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

    private List<PCBbbMarketActivityVO.CutActivity> CutActivity(BbbMarketMerchantActivityDTO.IdDTO dto) {
        List<PCBbbMarketActivityVO.CutActivity> cutActivity=new ArrayList<>();
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
                    PCBbbMarketActivityVO.CutActivity cutActivity1 = new PCBbbMarketActivityVO.CutActivity();
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
    private List<PCBbbMarketActivityVO.ScountActivity> ScountActivity(BbbMarketMerchantActivityDTO.IdDTO dto) {
        List<PCBbbMarketActivityVO.ScountActivity> scountActivity=new ArrayList<>();
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
                    PCBbbMarketActivityVO.ScountActivity scountActivity1 = new PCBbbMarketActivityVO.ScountActivity();
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

    private List<PCBbbMarketActivityVO.PlataformActivity> PlataformActivity(BbbMarketMerchantActivityDTO.IdDTO dto) {
        List<PCBbbMarketActivityVO.PlataformActivity> plataformActivity=new ArrayList<>();
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
                    PCBbbMarketActivityVO.PlataformActivity plataformActivity1 = new PCBbbMarketActivityVO.PlataformActivity();
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

    private void CheckActivityTime(LocalDateTime activityStartTime, LocalDateTime activityEndTime,  PCBbbMarketActivityVO.GroupbuyActivity plataformActivity1) {
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

    private void CheckActivityTime(LocalDateTime activityStartTime, LocalDateTime activityEndTime, PCBbbMarketActivityVO.PlataformActivity plataformActivity1) {
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
    private void CheckActivityTime(LocalDateTime activityStartTime, LocalDateTime activityEndTime, PCBbbMarketActivityVO.ScountActivity plataformActivity1) {
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
    private void CheckActivityTime(LocalDateTime activityStartTime, LocalDateTime activityEndTime, PCBbbMarketActivityVO.CutActivity plataformActivity1) {
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
    private void CheckActivityTime(LocalDateTime activityStartTime, LocalDateTime activityEndTime, PCBbbMarketActivityVO.GiftActivity plataformActivity1) {
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


    private void SetGoodsInfo(List<? extends PCBbbMarketActivityVO.ListVO> listVOS, List<PCBbbGoodsInfoVO.HomeInnerServiceVO> goodsInfoVOS) {
        for (int i = 0; i < listVOS.size(); i++) {
            for (PCBbbGoodsInfoVO.HomeInnerServiceVO goodsInfoVO : goodsInfoVOS) {
                if (listVOS.get(i).getGoodsId().equals(goodsInfoVO.getId())){
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
    
	@Override
	public FlashsaleVO listFlashsale(BaseDTO dto) {
		FlashsaleVO flashsaleVO = new FlashsaleVO();
		flashsaleVO.setStatus(TrueFalseEnum.否.getCode());
		//查询生成进行的活动
		QueryWrapper<MarketPtActivity> wrapper = new QueryWrapper<>();
        wrapper.eq("label","秒杀");
        wrapper.le("activity_start_time", LocalDateTime.now());
        wrapper.ge("activity_end_time", LocalDateTime.now());
        //查询商品的活动
        MarketPtActivity marketPtActivity = iMarketPtActivityRepository.getOne(wrapper);
        if(marketPtActivity==null){
        	return flashsaleVO;
        }
        
        BeanCopyUtils.copyProperties(marketPtActivity, flashsaleVO);
        
        if (ObjectUtils.isEmpty(marketPtActivity)){
            throw new BusinessException("没有查询到此活动");
        }
        PCBbbMarketActivityVO.activityVO activityVO = new PCBbbMarketActivityVO.activityVO();
        BeanUtils.copyProperties(marketPtActivity,activityVO);
        IPage<PCBbbMarketActivityVO.activityGoodsVO> pager = MybatisPlusUtil.pager(new PCBbbMarketActivityQTO.QTO());
        QueryWrapper<BbbMarketActivityDTO.IdDTO> query = MybatisPlusUtil.query();
        query.and(i->i.eq("goods.activity_id",marketPtActivity.getId()));
        iMarketPtActivityRepository.selectActivityPageData(pager,query);
        
        List<PCBbbMarketActivityVO.activityGoodsVO> listVOS=new ArrayList<>();
        List<PCBbbGoodsInfoVO.HomeInnerServiceVO>  goodsInfoVOS=null;
        List<String> goodsIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(pager)){
            for (PCBbbMarketActivityVO.activityGoodsVO cutVO : pager.getRecords()) {
                PCBbbMarketActivityVO.activityGoodsVO cutVO1 = new PCBbbMarketActivityVO.activityGoodsVO();
                BeanUtils.copyProperties(cutVO,cutVO1);
                listVOS.add(cutVO1);
                goodsIds.add(cutVO.getGoodsId());
            }
            if (ObjectUtils.isNotEmpty(goodsIds)){
                goodsInfoVOS = ipcBbbGoodsInfoRpc.getHomeGoodsInnerServiceVO(goodsIds, dto);
            }
            if (ObjectUtils.isNotEmpty(listVOS) && ObjectUtils.isNotEmpty(goodsInfoVOS)){
                SetGoodsInfo(listVOS,goodsInfoVOS);
            }

        }
        flashsaleVO.setList(new PageData<>(listVOS,1,10,pager.getTotal()));
        flashsaleVO.setStatus(TrueFalseEnum.是.getCode());
//        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        flashsaleVO.setActivityStartTime(LocalDateTime.parse(strDate3,dtf2));
		return flashsaleVO;
	}

}
