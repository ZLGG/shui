package com.gs.lshly.biz.support.trade.service.bbc.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.gs.lshly.common.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.common.utils.CollectionUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.Coupon;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantCardGoods;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantCardUsers;
import com.gs.lshly.biz.support.trade.entity.Trade;
import com.gs.lshly.biz.support.trade.entity.TradeCancel;
import com.gs.lshly.biz.support.trade.entity.TradeDelivery;
import com.gs.lshly.biz.support.trade.entity.TradeGoods;
import com.gs.lshly.biz.support.trade.entity.TradePay;
import com.gs.lshly.biz.support.trade.entity.TradePayOffline;
import com.gs.lshly.biz.support.trade.entity.TradePayOfflineImg;
import com.gs.lshly.biz.support.trade.entity.TradeRights;
import com.gs.lshly.biz.support.trade.entity.TradeRightsGoods;
import com.gs.lshly.biz.support.trade.enums.MarketPtCardStatusEnum;
import com.gs.lshly.biz.support.trade.job.QuartzSchedulerManager;
import com.gs.lshly.biz.support.trade.mapper.CouponMapper;
import com.gs.lshly.biz.support.trade.mapper.TradeMapper;
import com.gs.lshly.biz.support.trade.mapper.TradePayMapper;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantCardGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantCardUsersRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeCancelRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeCommentRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeDeliveryRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.ITradePayOfflineImgRepository;
import com.gs.lshly.biz.support.trade.repository.ITradePayOfflineRepository;
import com.gs.lshly.biz.support.trade.repository.ITradePayRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeRightsGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeRightsRepository;
import com.gs.lshly.biz.support.trade.service.bbc.IBbcTradeService;
import com.gs.lshly.biz.support.trade.service.common.Impl.ICommonMarketCardServiceImpl;
import com.gs.lshly.biz.support.trade.travelsky.ITravelskyOrderService;
import com.gs.lshly.biz.support.trade.utils.TradeUtils;
import com.gs.lshly.common.ctcc.b2i.SimpleBusinessAccept;
import com.gs.lshly.common.enums.GoodsCouponTypeEnum;
import com.gs.lshly.common.enums.GoodsCtccApiEnum;
import com.gs.lshly.common.enums.GoodsExchangeTypeEnum;
import com.gs.lshly.common.enums.GoodsStateEnum;
import com.gs.lshly.common.enums.ResponseCodeEnum;
import com.gs.lshly.common.enums.StockAddressTypeEnum;
import com.gs.lshly.common.enums.StockCheckStateEnum;
import com.gs.lshly.common.enums.TradeCancelApplyTypeEnum;
import com.gs.lshly.common.enums.TradeCancelRefundStateEnum;
import com.gs.lshly.common.enums.TradeCancelStateEnum;
import com.gs.lshly.common.enums.TradeDeliveryTypeEnum;
import com.gs.lshly.common.enums.TradeHideEnum;
import com.gs.lshly.common.enums.TradePayOfficeEnum;
import com.gs.lshly.common.enums.TradePayResultStateEnum;
import com.gs.lshly.common.enums.TradePayStateEnum;
import com.gs.lshly.common.enums.TradePayTypeEnum;
import com.gs.lshly.common.enums.TradeRightsEndStateEnum;
import com.gs.lshly.common.enums.TradeRightsReasonTypeEnum;
import com.gs.lshly.common.enums.TradeRightsRefundMoneyTypeEnum;
import com.gs.lshly.common.enums.TradeRightsRefundTypeEnum;
import com.gs.lshly.common.enums.TradeRightsStateEnum;
import com.gs.lshly.common.enums.TradeRightsStateTradeEnum;
import com.gs.lshly.common.enums.TradeRightsTypeEnum;
import com.gs.lshly.common.enums.TradeSourceTypeEnum;
import com.gs.lshly.common.enums.TradeStateEnum;
import com.gs.lshly.common.enums.TradeTimeOutCancelEnum;
import com.gs.lshly.common.enums.TradeTrueFalseEnum;
import com.gs.lshly.common.enums.UserCouponStatusEnum;
import com.gs.lshly.common.enums.commondity.GoodsSourceTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsInfoDTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.merchant.dto.BbcShopDTO;
import com.gs.lshly.common.struct.bbc.merchant.qto.BbcShopQTO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopVO;
import com.gs.lshly.common.struct.bbc.stock.dto.BbcStockAddressDTO;
import com.gs.lshly.common.struct.bbc.stock.dto.BbcStockDeliveryDTO;
import com.gs.lshly.common.struct.bbc.stock.vo.BbcStockAddressVO;
import com.gs.lshly.common.struct.bbc.stock.vo.BbcStockDeliveryVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeBuildDTO.DTO.ProductData;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeBuildDTO.DTO.ShopData;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeCancelDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeGoodsDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradePayBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTravelskyDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradePayBuildDTO.CheckAndPointDoPayETO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeListVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeResultNotifyVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeSettlementVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeSettlementVO.ListCouponVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeSettlementVO.ShopListVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeVO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserCtccPointDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserIntegralDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserShoppingCarDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcUserCouponQTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcUserQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserCouponVO.ListVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserShoppingCarVO.ShopSkuVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserShoppingCarVO.SkuQuantityVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcInUserCouponVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO;
import com.gs.lshly.common.struct.common.CommonLogisticsCompanyVO;
import com.gs.lshly.common.struct.common.CommonStockDTO;
import com.gs.lshly.common.struct.common.CommonStockVO;
import com.gs.lshly.common.struct.common.CommonUserVO;
import com.gs.lshly.common.struct.ctcc.dto.B2IDTO;
import com.gs.lshly.common.struct.ctcc.dto.BSS30DTO;
import com.gs.lshly.common.struct.ctcc.vo.B2IVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsReceiptVO;
import com.gs.lshly.middleware.mq.aliyun.producerService.ProducerService;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.middleware.redis.RedisUtil;
import com.gs.lshly.middleware.sms.IContactSMSService;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsInfoRpc;
import com.gs.lshly.rpc.api.bbc.merchant.IBbcShopRpc;
import com.gs.lshly.rpc.api.bbc.stock.IBbcStockAddressRpc;
import com.gs.lshly.rpc.api.bbc.stock.IBbcStockDeliveryRpc;
import com.gs.lshly.rpc.api.bbc.stock.IBbcStockRpc;
import com.gs.lshly.rpc.api.bbc.user.IBbcInUserCouponRpc;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserCtccPointRpc;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserIntegralRpc;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserRpc;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserShoppingCarRpc;
import com.gs.lshly.rpc.api.common.ICommonLogisticsCompanyRpc;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import com.gs.lshly.rpc.api.common.ICommonStockRpc;
import com.gs.lshly.rpc.api.common.ICommonUserRpc;
import com.gs.lshly.rpc.api.platadmin.foundation.ISettingsReceiptRpc;
import com.lakala.boss.api.common.Common;
import com.lakala.boss.api.config.MerchantBaseEnum;
import com.lakala.boss.api.entity.notify.EntMergeOfflineResultNotify;
import com.lakala.boss.api.entity.request.EntOffLinePaymentRequest;
import com.lakala.boss.api.entity.request.OrderDetail;
import com.lakala.boss.api.entity.request.QRCodePaymentCommitRequest;
import com.lakala.boss.api.entity.response.EntOffLinePaymentResponse;
import com.lakala.boss.api.entity.response.QRCodePaymentCommitResponse;
import com.lakala.boss.api.utils.BossClient;
import com.lakala.boss.api.utils.DateUtil;
import com.lakala.boss.api.utils.UuidUtil;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author oy
 * @since 2020-10-28
 */
@Component
@Slf4j
@SuppressWarnings({"unchecked", "unused", "rawtypes"})
public class BbcTradeServiceImpl implements IBbcTradeService {

    private final String PAYING_TRADE = "trade_paying";

    @Resource
    private QuartzSchedulerManager quartzSchedulerManager;

    private final ITradeRepository tradeRepository;

    private final ITradeGoodsRepository tradeGoodsRepository;

    private final ITradePayRepository tradePayRepository;

    private final ITradePayOfflineRepository tradePayOfflineRepository;

    private final ITradeDeliveryRepository tradeDeliveryRepository;

    private final ITradeCancelRepository tradeCancelRepository;
    @Autowired
    private ITradePayOfflineImgRepository iTradePayOfflineImgRepository;

    @Autowired
    private ProducerService producerService;

    @Autowired
    private IMarketMerchantCardUsersRepository iMarketMerchantCardUsersRepository;

    @Autowired
    private IMarketMerchantCardGoodsRepository iMarketMerchantCardGoodsRepository;

    @Autowired
    private ITradeCommentRepository iTradeCommentRepository;

    @Autowired
    private IMarketMerchantCardUsersRepository repository;

    @Autowired
    private ITradeRightsRepository iTradeRightsRepository;

    @Autowired
    private ITradeRightsGoodsRepository iTradeRightsGoodsRepository;

    @Autowired
    private TradeMapper tradeMapper;

    @Autowired
    private TradePayMapper tradePayMapper;

//    @Autowired
//    private ISMSService ismsService;

    @Autowired
    private IContactSMSService iContactSMSService;

    @Autowired
    private ITradeRightsGoodsRepository tradeRightsGoodsRepository;

    @Autowired
    private ITradeRightsRepository tradeRightsRepository;

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ITravelskyOrderService travelskyOrderService;

    @DubboReference
    private IBbcInUserCouponRpc bbcInUserCouponRpc;

    @Value("${lakala.wxpay.notifyurl}")
    private String notifyUrl;

    @Value("${lakala.wxpay.appid}")
    private String appId;

    @DubboReference
    private IBbcGoodsInfoRpc bbcGoodsInfoRpc;

    @DubboReference
    private ICommonStockRpc commonStockRpc;

    @DubboReference
    private IBbcStockAddressRpc bbcStockAddressRpc;

    @DubboReference
    private IBbcUserShoppingCarRpc bbcUserShoppingCarRpc;

    @DubboReference
    private IBbcShopRpc iBbcShopRpc;

    @DubboReference
    private ICommonLogisticsCompanyRpc commonLogisticsCompanyRpc;

    @DubboReference
    private IBbcStockDeliveryRpc bbcStockDeliveryRpc;

    @DubboReference
    private IBbcUserRpc bbcUserRpc;

    @DubboReference
    private ICommonShopRpc iCommonShopRpc;

    @DubboReference
    private ISettingsReceiptRpc iSettingsReceiptRpc;
    @DubboReference
    private IBbcUserRpc iBbcUserRpc;
    @DubboReference
    private IBbcUserCtccPointRpc bbcUserCtccPointRpc;
    @DubboReference
    private IBbcGoodsInfoRpc iBbcGoodsInfoRpc;
    @DubboReference
    private ICommonUserRpc commonUserRpc;

    @DubboReference
    private IBbcStockRpc bbcStockRpc;
    
    @Autowired
    private ICommonMarketCardServiceImpl commonMarketCardService;
    
    

    public BbcTradeServiceImpl(ITradeRepository tradeRepository, ITradeGoodsRepository tradeGoodsRepository,
                               ITradePayRepository tradePayRepository, ITradePayOfflineRepository tradePayOfflineRepository, ITradeDeliveryRepository tradeDeliveryRepository, ITradeCancelRepository tradeCancelRepository) {
        this.tradeRepository = tradeRepository;
        this.tradeGoodsRepository = tradeGoodsRepository;
        this.tradePayRepository = tradePayRepository;
        this.tradePayOfflineRepository = tradePayOfflineRepository;
        this.tradeDeliveryRepository = tradeDeliveryRepository;
        this.tradeCancelRepository = tradeCancelRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseData<BbcTradeSettlementVO.DetailVO> settlementVO(BbcTradeBuildDTO.cartIdsDTO dto) {

        /**
         * 如果是立即支付时，会有考虑是不是要扣优惠券的值
         */

        Integer isInCar = 1;
        //返回地址、商家、商品（图片、名称、规格、单价）、商品总金额、商品数量、运费
        BbcTradeSettlementVO.DetailVO settlementVO = new BbcTradeSettlementVO.DetailVO();
        Integer exchangeType = 20;
        // 获取用户信息，获取是否是IN会员
        String jwtUserId = dto.getJwtUserId();

        BbcUserQTO.QTO qto = new BbcUserQTO.QTO();
        qto.setJwtUserId(jwtUserId);
        BbcUserVO.DetailVO userInfo = iBbcUserRpc.getUserInfoNoLogin(qto);//当前用户类型
        if (userInfo == null || StringUtils.isEmpty(userInfo.getId())) {
            throw new BusinessException("无用户信息");
        }
        Integer memberType = userInfo.getMemberType();    //判断用户类型 1普通用户
        // 用户积分
        BigDecimal telecomsIntegral = BigDecimal.ZERO;
        if (memberType != null && memberType.equals(2)) {
            telecomsIntegral = new BigDecimal(userInfo.getTelecomsIntegral());
        }

        Integer isInUser = userInfo.getIsInUser();    //判断是否是IN会员商户。
        log.info("[settlementVO][userInfo=>{}]", userInfo);
//        //skuid/数量
//        List<CommonStockDTO.InnerSkuGoodsInfoItem> goodsItemList = new ArrayList<>();
//
//        //购物车id/skuid/数量
//        List<BbcUserShoppingCarVO.InnerSimpleItem> itemList = new ArrayList<>();

        List<ShopSkuVO> shopskuList = new ArrayList<ShopSkuVO>();
        List<ShopListVO> shopList = new ArrayList<ShopListVO>();

        BigDecimal goodsAmount = BigDecimal.ZERO;//商品总金额
        BigDecimal goodsPointAmount = BigDecimal.ZERO;//商品总积分金额
        Integer goodsCount = 0;//商品总数

        if (StringUtils.isNotEmpty(dto.getGoodsSkuId())) {//立即购买
        	//跟据SKUID查询存存
        	isInCar = 0;
            BbcGoodsInfoVO.InnerServiceVO innerServiceVO = iBbcGoodsInfoRpc.innerSimpleServiceGoodsVO(dto.getGoodsSkuId());
            ShopSkuVO shopSkuVO = new ShopSkuVO();
            shopSkuVO.setShopId(innerServiceVO.getShopId());
            List<SkuQuantityVO> skuQuantity = new ArrayList<SkuQuantityVO>();
            SkuQuantityVO skuQuantityVO = new SkuQuantityVO();

            skuQuantityVO.setQuantity(dto.getQuantity());
            skuQuantityVO.setSkuId(dto.getGoodsSkuId());
            skuQuantityVO.setGoodsPointPrice(innerServiceVO.getGoodsPointAmount());
            skuQuantityVO.setGoodsPrice(innerServiceVO.getGoodsAmount());
            skuQuantityVO.setInMemberPointPrice(innerServiceVO.getInMemberPointPrice());
            skuQuantityVO.setIsInMemberGift(innerServiceVO.getIsInMemberGift());
            skuQuantityVO.setIsPointGood(innerServiceVO.getIsPointGood());

            skuQuantity.add(skuQuantityVO);
            shopSkuVO.setSkuQuantity(skuQuantity);
            shopskuList.add(shopSkuVO);

            //判断商品类型
            exchangeType = innerServiceVO.getExchangeType();
        } else {//购物车购买 dto.getCartIds();
            //根据购物车id查询实体 //判断商品是否属于同一个店铺
            BbcUserShoppingCarDTO.InnerIdListDTO innerIdListDTO = new BbcUserShoppingCarDTO.InnerIdListDTO();
            innerIdListDTO.setIdList(dto.getCartIds());
            //跟据购物车查询所属什么店铺ids
            shopskuList = bbcUserShoppingCarRpc.groupShopByCarId(innerIdListDTO);

        }

        //计算总积分，用户够不够
        BigDecimal totalPrice = BigDecimal.ZERO;//总现金值
        BigDecimal totalPointPrice = BigDecimal.ZERO;//总积分值
        BigDecimal totalInPointPrice = BigDecimal.ZERO;//总IN会员价格

        for (ShopSkuVO shopskuvo : shopskuList) {
            List<SkuQuantityVO> skuList = shopskuvo.getSkuQuantity();
            for (SkuQuantityVO skuQuantityVO : skuList) {

                if ((skuQuantityVO.getIsInMemberGift() != null && skuQuantityVO.getIsInMemberGift()) || isInUser.equals(1)) {
                    totalPointPrice = totalPointPrice.add(skuQuantityVO.getGoodsPointPrice().multiply(new BigDecimal(skuQuantityVO.getQuantity())));
                    totalInPointPrice = totalInPointPrice.add(skuQuantityVO.getInMemberPointPrice().multiply(new BigDecimal(skuQuantityVO.getQuantity())));
                } else if (skuQuantityVO.getIsPointGood() != null && skuQuantityVO.getIsPointGood()) {//积分商品
                    totalInPointPrice = totalInPointPrice.add(skuQuantityVO.getGoodsPointPrice().multiply(new BigDecimal(skuQuantityVO.getQuantity())));
                    totalPointPrice = totalPointPrice.add(skuQuantityVO.getGoodsPointPrice().multiply(new BigDecimal(skuQuantityVO.getQuantity())));
                } else {
                    totalPrice = totalPrice.add(skuQuantityVO.getGoodsPrice().multiply(new BigDecimal(skuQuantityVO.getQuantity())));
                }
            }
        }

        Boolean flag = false;
        //判断用户是否需要分积分
        if (isInUser.equals(1)) {    //是IN会员
            if (telecomsIntegral.compareTo(totalInPointPrice) < 0) {
                flag = true;
                totalPrice = totalPrice.add(totalInPointPrice.subtract(telecomsIntegral).divide(new BigDecimal(100)));
            }
        } else {
            if (memberType.equals(2)) {
                if (telecomsIntegral.compareTo(totalPointPrice) < 0) {
                    flag = true;
                    totalPrice = totalPrice.add(totalPointPrice.subtract(telecomsIntegral).divide(new BigDecimal(100)));
                }
            } else {
                totalPrice = totalPrice.add(totalPointPrice.subtract(telecomsIntegral).divide(new BigDecimal(100)));
                flag = true;
            }
        }

        ShopListVO shopListVO = null;

        for (ShopSkuVO shopskuvo : shopskuList) {
            String shopId = shopskuvo.getShopId(); // 店铺ID
            shopListVO = new ShopListVO();
            fillShop(shopId, shopListVO);
            // 组装sku
            List<SkuQuantityVO> skuList = shopskuvo.getSkuQuantity();
            BigDecimal goodsAmountDetail = BigDecimal.ZERO;//单个商品总金额
            BigDecimal goodsPointAmountDetail = BigDecimal.ZERO;//单个商品总积分金额
            Integer goodsCountDetail = 0;//商品总数
            List<BbcTradeSettlementVO.ShopListVO.goodsInfoVO> goodsInfoVOS = new ArrayList<BbcTradeSettlementVO.ShopListVO.goodsInfoVO>();

            BigDecimal tradeAmountDetail = BigDecimal.ZERO;//实付现金
            BigDecimal tradePointAmountDetail = BigDecimal.ZERO;//实付积分

            for (int i = 0; i < skuList.size(); i++) {
                SkuQuantityVO skuQuantityVO = skuList.get(i);

                String skuId = skuQuantityVO.getSkuId();
                Integer quantity = skuQuantityVO.getQuantity();
                String carId = skuQuantityVO.getCarId();

                // 店铺商品
                // for(BbcUserShoppingCarVO.InnerSimpleItem innerSimpleItem :
                // itemList){
                // 根据SKU ID获取商品信息（图片、商品名称、规格值、售价、状态）
                BbcGoodsInfoVO.InnerServiceVO innerServiceGoodsVO = bbcGoodsInfoRpc.innerServiceGoodsVO(skuId);
                if (ObjectUtils.isEmpty(innerServiceGoodsVO) || innerServiceGoodsVO.getGoodsId() == null) {
                    throw new BusinessException("无商品数据或已下架");
                } else if (!innerServiceGoodsVO.getGoodsState().equals(GoodsStateEnum.已上架.getCode())) {
                    throw new BusinessException("商品已下架");
                }
                
                Integer stock = bbcStockRpc.getQuantity(dto.getGoodsSkuId());
            	if(dto.getQuantity()>stock)
            		throw new BusinessException("库存不足");
                
                
                // 组装商品信息
                BbcTradeSettlementVO.ShopListVO.goodsInfoVO goodsInfoVO = fillGoodsInfoVO(carId, quantity,
                        innerServiceGoodsVO);

                if (goodsInfoVO.getIsInMemberGift()) {//是IN会员商品
                    if (isInUser.equals(1)) {    //是IN会员用IN会员价格

                        List<ListCouponVO> optionalCouponList = new ArrayList<ListCouponVO>();
                        //默认优惠券
                        List<ListCouponVO> defaultCouponList = new ArrayList<ListCouponVO>();

                        //跟据商品选优惠券
                        List<Coupon> couponList = couponMapper.listByGoodsId(goodsInfoVO.getGoodsId());
                        if (CollectionUtil.isNotEmpty(couponList)) {//有优惠劵
                            //判断用户有没有对应的这个优惠劵
                            for (Coupon coupon : couponList) {
                                String couponId = coupon.getCouponId();//优惠券ID
                                BbcUserCouponQTO.ListByCouponIdQTO listByCouponIdQTO = new BbcUserCouponQTO.ListByCouponIdQTO();

                                BigDecimal memberPointPrice = goodsInfoVO.getInMemberPointPrice();
                                if (memberPointPrice.compareTo(coupon.getUseThreshold()) < 0)
                                    continue;

                                BeanCopyUtils.copyProperties(dto, listByCouponIdQTO);
                                listByCouponIdQTO.setCouponId(couponId);
                                List<ListVO> userCouponList = bbcInUserCouponRpc.listByCouponId(listByCouponIdQTO);

                                if (userCouponList != null && userCouponList.size() > 0) {

                                    ListCouponVO listCouponVO = null;
                                    for (ListVO listVO : userCouponList) {
                                        listCouponVO = new ListCouponVO();

                                        //BeanCopyUtils.copyProperties(coupon, listCouponVO);
                                        listCouponVO.setCouponName(listVO.getCouponName());
                                        listCouponVO.setCouponType(listVO.getCouponType());
                                        listCouponVO.setCouponTypeText(GoodsCouponTypeEnum.getRemarkByCode(coupon.getCouponType()));
                                        listCouponVO.setDeduction(listVO.getCouponPrice());
                                        listCouponVO.setDeductionType(1);
                                        listCouponVO.setId(listVO.getId());
                                        listCouponVO.setUseThreshold(listVO.getMinPrice());
                                        listCouponVO.setUseTime("领取后" + coupon.getEffectiveDate() + "天内使用");
                                        if (defaultCouponList == null || defaultCouponList.size() == 0) {
                                            defaultCouponList.add(listCouponVO);
                                        }
                                        optionalCouponList.add(listCouponVO);
                                    }
                                }

                            }
                        }
                        goodsInfoVO.setDefaultCouponList(defaultCouponList);
                        goodsInfoVO.setOptionalCouponList(optionalCouponList);
                        BigDecimal pointPrice = goodsInfoVO.getInMemberPointPrice()
                                .multiply(new BigDecimal(goodsInfoVO.getQuantity()));


                        if (flag) {//需要分积分
                            BigDecimal tradePointAmount = BigDecimal.ZERO;

                            tradePointAmount = pointPrice.divide(totalInPointPrice.setScale(0, BigDecimal.ROUND_HALF_UP)).multiply(telecomsIntegral);
                            goodsInfoVO.setTradePointAmount(tradePointAmount);
                            goodsInfoVO.setTradeAmount(pointPrice.subtract(tradePointAmount).divide(new BigDecimal(100)));
                            tradePointAmountDetail = tradePointAmountDetail.add(tradePointAmount);
                            tradeAmountDetail = tradeAmountDetail.add(goodsInfoVO.getTradeAmount());
                        } else {

                            //本期就考虑不用分积分的
                            BigDecimal money = BigDecimal.ZERO;
                            if (defaultCouponList != null && defaultCouponList.size() > 0) {
                                ListCouponVO couponVO = defaultCouponList.get(0);
                                money = couponVO.getDeduction();
                            }
                            BigDecimal deduction = money.multiply(new BigDecimal("100"));

                            pointPrice = pointPrice.compareTo(deduction) <= 0 ? BigDecimal.ZERO : pointPrice.subtract(deduction);

                            goodsInfoVO.setTradePointAmount(pointPrice);
                            goodsInfoVO.setTradeAmount(BigDecimal.ZERO);
                        }

                        goodsPointAmount = goodsPointAmount.add(pointPrice);
                        goodsPointAmountDetail = goodsPointAmountDetail.add(pointPrice);
                    } else {
                        throw new BusinessException("请先成为IN会员，再买IN会员商品");
                    }
                } else if (goodsInfoVO.getIsPointGood()) { // 积分商品
                    // 判断用户是不是IN会员，如果是的话，就用IN会员价格
                    if (isInUser.equals(1)) {    //是IN会员用IN会员价格

                        BigDecimal pointPrice = goodsInfoVO.getInMemberPointPrice()
                                .multiply(new BigDecimal(goodsInfoVO.getQuantity()));
                        goodsPointAmount = goodsPointAmount.add(pointPrice);
                        goodsPointAmountDetail = goodsPointAmountDetail.add(pointPrice);

                        if (flag) {//需要分积分

                            BigDecimal tradePointAmount = pointPrice.divide(totalInPointPrice, 2, BigDecimal.ROUND_HALF_UP).multiply(telecomsIntegral);
                            goodsInfoVO.setTradePointAmount(tradePointAmount);
                            goodsInfoVO.setTradeAmount(pointPrice.subtract(tradePointAmount).divide(new BigDecimal(100)));
                            tradePointAmountDetail = tradePointAmountDetail.add(tradePointAmount);
                            tradeAmountDetail = tradeAmountDetail.add(goodsInfoVO.getTradeAmount());
                        } else {
                            goodsInfoVO.setTradePointAmount(pointPrice);
                            goodsInfoVO.setTradeAmount(BigDecimal.ZERO);
                        }
                    } else {

                        // throw new BusinessException("存在IN会员专属商品，请开通IN会员后重试");
                        BigDecimal pointPrice = goodsInfoVO.getPointPrice()
                                .multiply(new BigDecimal(goodsInfoVO.getQuantity()));
                        goodsPointAmount = goodsPointAmount.add(pointPrice);
                        goodsPointAmountDetail = goodsPointAmountDetail.add(pointPrice);

                        if (flag) {
                            if (memberType.equals(2)) {
                                BigDecimal tradePointAmount = BigDecimal.ZERO;

                                tradePointAmount = pointPrice.divide(totalPointPrice, 2, BigDecimal.ROUND_HALF_UP).multiply(telecomsIntegral);
                                goodsInfoVO.setTradePointAmount(tradePointAmount);
                                goodsInfoVO.setTradeAmount(pointPrice.subtract(tradePointAmount).divide(new BigDecimal(100)));
                                tradePointAmountDetail = tradePointAmountDetail.add(tradePointAmount);
                                tradeAmountDetail = tradeAmountDetail.add(goodsInfoVO.getTradeAmount());
                            } else {
                                goodsInfoVO.setTradePointAmount(BigDecimal.ZERO);
                                goodsInfoVO.setTradeAmount(pointPrice.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP));
                            }
                        } else {
                            goodsInfoVO.setTradePointAmount(pointPrice);
                            goodsInfoVO.setTradeAmount(BigDecimal.ZERO);
                        }
                    }
                } else {
                    BigDecimal productPrice = goodsInfoVO.getSalePrice()
                            .multiply(new BigDecimal(goodsInfoVO.getQuantity()));
                    goodsAmount = goodsAmount.add(productPrice);
                    goodsAmountDetail = goodsAmountDetail.add(productPrice);
                    goodsInfoVO.setTradePointAmount(productPrice);
                    goodsInfoVO.setTradeAmount(BigDecimal.ZERO);
                }

                goodsCount = goodsCount + goodsInfoVO.getQuantity();
                goodsCountDetail = goodsCountDetail + goodsInfoVO.getQuantity();

                goodsInfoVOS.add(goodsInfoVO);
            }

            shopListVO.setGoodsInfoVOS(goodsInfoVOS);

            shopListVO.setGoodsAmount(goodsAmountDetail);
            shopListVO.setGoodsPointAmount(goodsPointAmountDetail);
            if (!flag) {
                shopListVO.setTradeAmount(goodsAmountDetail);//应付的钱
                shopListVO.setTradePointAmount(goodsPointAmountDetail);//应付的钱
            } else {
                shopListVO.setTradeAmount(tradeAmountDetail);//应付的钱
                shopListVO.setTradePointAmount(tradePointAmountDetail);//应付的钱
            }

            shopListVO.setGoodsCount(goodsCountDetail);
            shopList.add(shopListVO);
        }
        settlementVO.setShopList(shopList);

        //组装收货地址信息
        fillAddress(dto, settlementVO);

        //商品总额
        settlementVO.setGoodsAmount(goodsAmount);
        //商品总积分额
        settlementVO.setGoodsPointAmount(goodsPointAmount);
        //商品总数
        settlementVO.setGoodsCount(goodsCount);
        settlementVO.setTelecomsIntegral(Integer.valueOf(telecomsIntegral.toString()));

        if (!flag) {
            settlementVO.setTradeAmount(goodsAmount);//应付的钱
            settlementVO.setTradePointAmount(goodsPointAmount);//应付的钱
        } else {//要分积分
            settlementVO.setTradeAmount(totalPrice);//应付的钱
            settlementVO.setTradePointAmount(telecomsIntegral);//应付的钱
        }
        settlementVO.setDiscountAmount(BigDecimal.ZERO);
        settlementVO.setDiscountPointAmount(BigDecimal.ZERO);
        /**营销结算
         try {
         log.info("开始结算:"+ JsonUtils.toJson(settlementVO));
         marketSettleService.settlement(settlementVO, dto);
         log.info("结算结果:"+ JsonUtils.toJson(settlementVO));
         } catch (Exception e) {
         log.error("营销结算异常:"+e.getMessage(), e);
         }**/
        settlementVO.setExchangeType(exchangeType);

        return ResponseData.data(settlementVO);
    }

    private List<ListCouponVO> listCoupon() {

        List<ListCouponVO> retList = new ArrayList<ListCouponVO>();
        ListCouponVO listCouponVO = new ListCouponVO();
        listCouponVO.setCouponType(1);
        listCouponVO.setUseTime("2021/01/01 2021/08/01");
        listCouponVO.setCouponName("仅购买IN会员商品可以使用");
        listCouponVO.setDeduction(new BigDecimal("20.00"));
        listCouponVO.setUseThreshold(new BigDecimal("40.00"));
        listCouponVO.setDeductionType(Integer.valueOf(1));
        listCouponVO.setId("4ecef3ea3d6c421f9fd7f4c82bfcab5b");
        retList.add(listCouponVO);
        return retList;
    }

    private List<ListCouponVO> listCoupon1() {

        List<ListCouponVO> retList = new ArrayList<ListCouponVO>();

        ListCouponVO listCouponVO = new ListCouponVO();
        listCouponVO.setCouponType(1);
        listCouponVO.setUseTime("2021/01/01 2021/08/01");
        listCouponVO.setCouponName("仅购买IN会员商品可以使用");
        listCouponVO.setDeduction(new BigDecimal("20.00"));
        listCouponVO.setUseThreshold(new BigDecimal("40.00"));
        listCouponVO.setDeductionType(Integer.valueOf(1));
        listCouponVO.setId("4ecef3ea3d6c421f9fd7f4c82bfcab5b");
        retList.add(listCouponVO);

        listCouponVO = new ListCouponVO();
        listCouponVO.setCouponType(1);
        listCouponVO.setUseTime("2021/01/01 2021/11/01");
        listCouponVO.setCouponName("仅购买IN会员商品可以使用");
        listCouponVO.setDeduction(new BigDecimal("30.00"));
        listCouponVO.setUseThreshold(new BigDecimal("50.00"));
        listCouponVO.setDeductionType(Integer.valueOf(1));
        listCouponVO.setId("666666666c421f9fd7f4c82bfcab5b");
        retList.add(listCouponVO);

        listCouponVO = new ListCouponVO();
        listCouponVO.setCouponType(1);
        listCouponVO.setUseTime("2021/01/01 2021/12/01");
        listCouponVO.setCouponName("仅购买IN会员商品可以使用");
        listCouponVO.setDeduction(new BigDecimal("40.00"));
        listCouponVO.setUseThreshold(new BigDecimal("60.00"));
        listCouponVO.setDeductionType(Integer.valueOf(1));
        listCouponVO.setId("77777777c421f9fd7f4c82bfcab5b");
        retList.add(listCouponVO);

        return retList;
    }

    /**
     * 组装商家信息
     * @param shopId
     * @param settlementVO

    private void fillShop(String shopId, BbcTradeSettlementVO.ListVO settlementVO) {
    //根据店铺ID获取店铺自提、发货地址和店铺状态  //根据店铺信息 、判断店铺状态
    BbcShopQTO.InnerShopQTO innerShopQTO = new BbcShopQTO.InnerShopQTO();
    innerShopQTO.setShopId(shopId);
    BbcShopVO.InnerDetailVO innerDetailVO = iBbcShopRpc.innerDetailShop(innerShopQTO);
    settlementVO.setShopId(innerDetailVO.getId());
    settlementVO.setShopLogo(innerDetailVO.getShopLogo());
    settlementVO.setShopName(innerDetailVO.getShopName());
    settlementVO.setDeliveryScope(innerDetailVO.getDeliveryScope());
    settlementVO.setShopFullAddres(innerDetailVO.getShopFullAddres());
    settlementVO.setShopLongitude(innerDetailVO.getShopLongitude());
    settlementVO.setShopLatitude(innerDetailVO.getShopLatitude());
    List<String> strings = iBbcShopRpc.innerShopDelivery(shopId);
    if (ObjectUtils.isNotEmpty(strings)){
    settlementVO.setShopDeliveryType(strings);
    }

    }
     */
    /**
     * 组装商家信息-2
     *
     * @param shopId
     * @param shopListVO
     */
    private void fillShop(String shopId, ShopListVO shopListVO) {
        //根据店铺ID获取店铺自提、发货地址和店铺状态  //根据店铺信息 、判断店铺状态
        BbcShopQTO.InnerShopQTO innerShopQTO = new BbcShopQTO.InnerShopQTO();
        innerShopQTO.setShopId(shopId);
        BbcShopVO.InnerDetailVO innerDetailVO = iBbcShopRpc.innerDetailShop(innerShopQTO);
        shopListVO.setShopId(innerDetailVO.getId());
        shopListVO.setShopLogo(innerDetailVO.getShopLogo());
        shopListVO.setShopName(innerDetailVO.getShopName());
        shopListVO.setDeliveryScope(innerDetailVO.getDeliveryScope());
        shopListVO.setShopFullAddres(innerDetailVO.getShopFullAddres());
        shopListVO.setShopLongitude(innerDetailVO.getShopLongitude());
        shopListVO.setShopLatitude(innerDetailVO.getShopLatitude());
        List<String> strings = iBbcShopRpc.innerShopDelivery(shopId);
        if (ObjectUtils.isNotEmpty(strings)) {
            shopListVO.setShopDeliveryType(strings);
        }
    }

    /**
     * 组装收货地址信息
     *
     * @param dto
     * @param settlementVO
     */
    private void fillAddress(BbcTradeBuildDTO.cartIdsDTO dto, BbcTradeSettlementVO.DetailVO settlementVO) {
        if (StringUtils.isNotEmpty(dto.getAddressId())) {
            BbcStockAddressDTO.IdDTO idDto = new BbcStockAddressDTO.IdDTO(dto.getAddressId());
            idDto.setJwtUserId(dto.getJwtUserId());
            BbcStockAddressVO.ListVO addressVO = new BbcStockAddressVO.DetailVO();
            if (!dto.getDeliveryType().equals(TradeDeliveryTypeEnum.门店自提.getCode())) {
                //根据id查询地址
                addressVO = bbcStockAddressRpc.detailStockAddress(idDto);
                if (ObjectUtils.isNotEmpty(addressVO) && addressVO.getId() != null) {
                    settlementVO.setRecvAddresId(addressVO.getId());
                    settlementVO.setRecvPersonName(addressVO.getContactsName());
                    settlementVO.setRecvPhone(addressVO.getContactsPhone());
                    settlementVO.setRecvFullAddres(addressVO.getFullAddress());
                    //根据店铺的商品SKU ID获取快递配送运费模板，根据重量、件数计算运费.
//                    BigDecimal deliveryAmount = getDeliveryAmount(settlementVO);
//                    settlementVO.setDeliveryAmount(deliveryAmount);//运费
                }
            }
        } else {
            //获取用户默认收货地址
            BbcStockAddressVO.DetailVO addressVO = bbcStockAddressRpc.innerGetDefault(dto, StockAddressTypeEnum.收货.getCode());
            if (ObjectUtils.isNotEmpty(addressVO)) {
                settlementVO.setRecvAddresId(addressVO.getId());
                settlementVO.setRecvPersonName(addressVO.getContactsName());
                settlementVO.setRecvPhone(addressVO.getContactsPhone());
                settlementVO.setRecvFullAddres(addressVO.getFullAddress());
                //根据店铺的商品SKU ID获取快递配送运费模板，根据重量、件数计算运费.
//                BigDecimal deliveryAmount = getDeliveryAmount(settlementVO);
//                settlementVO.setDeliveryAmount(deliveryAmount);//运费
            }
        }


    }

    /**
     * private BigDecimal getDeliveryAmount(BbcTradeSettlementVO.ListVO settlementVO) {
     * BigDecimal deliveryAmount = BigDecimal.ZERO; //运费
     * BbcStockDeliveryDTO.DeliveryAmountDTO deliveryAmountDTO = new BbcStockDeliveryDTO.DeliveryAmountDTO();
     * deliveryAmountDTO.setAddressId(settlementVO.getRecvAddresId());
     * deliveryAmountDTO.setDeliveryType(TradeDeliveryTypeEnum.快递配送.getCode());
     * deliveryAmountDTO.setShopId(settlementVO.getShopId());
     * List<BbcStockDeliveryDTO.DeliverySKUDTO> deliverySKUDTOS = new ArrayList<>();
     * for(BbcTradeSettlementVO.ListVO.goodsInfoVO goodsInfoVO : settlementVO.getGoodsInfoVOS()){
     * BbcStockDeliveryDTO.DeliverySKUDTO deliverySKUDTO = new BbcStockDeliveryDTO.DeliverySKUDTO();
     * deliverySKUDTO.setSkuId(goodsInfoVO.getSkuId());
     * deliverySKUDTO.setAmount(new BigDecimal(goodsInfoVO.getQuantity()));
     * deliverySKUDTOS.add(deliverySKUDTO);
     * }
     * deliveryAmountDTO.setSkus(deliverySKUDTOS);
     * BbcStockDeliveryVO.DeliveryAmountVO deliveryAmountVO = bbcStockDeliveryRpc.calculate(deliveryAmountDTO);
     * if(ObjectUtils.isNotEmpty(deliveryAmountVO)){
     * deliveryAmount = deliveryAmountVO.getAmount();
     * }
     * return deliveryAmount;
     * }
     * 组装门店自提联系人信息
     *
     * @param dto
     * @param settlementVO
     */
    private void fillContacts(BbcTradeBuildDTO.cartIdsDTO dto, BbcTradeSettlementVO.ListVO settlementVO) {
        QueryWrapper<Trade> tradeWrapper = new QueryWrapper<>();
        tradeWrapper.eq("user_id", dto.getJwtUserId());
        tradeWrapper.eq("delivery_type", TradeDeliveryTypeEnum.门店自提.getCode());
        tradeWrapper.orderByDesc("cdate");
        tradeWrapper.last("limit 0,1");
        Trade tradeForContacts = tradeRepository.getOne(tradeWrapper);
        if (ObjectUtils.isNotEmpty(tradeForContacts)) {
            settlementVO.setContactsName(tradeForContacts.getRecvPersonName());
            settlementVO.setContactsPhone(tradeForContacts.getRecvPhone());
        }
    }

    /**
     * 组装商品信息
     *
     * @param carId
     * @param quantity
     * @param innerServiceGoodsVO
     * @return
     */
    private BbcTradeSettlementVO.ShopListVO.goodsInfoVO fillGoodsInfoVO(String carId, Integer quantity, BbcGoodsInfoVO.InnerServiceVO innerServiceGoodsVO) {
        BbcTradeSettlementVO.ShopListVO.goodsInfoVO goodsInfoVO = new BbcTradeSettlementVO.ShopListVO.goodsInfoVO();
        goodsInfoVO.setGoodsId(innerServiceGoodsVO.getGoodsId());
        goodsInfoVO.setGoodsName(innerServiceGoodsVO.getGoodsName());
        goodsInfoVO.setGoodsTitle(innerServiceGoodsVO.getGoodsTitle());
        goodsInfoVO.setSkuId(innerServiceGoodsVO.getSkuId());
        goodsInfoVO.setSkuImg(innerServiceGoodsVO.getGoodsImage());
        goodsInfoVO.setSkuSpecValue(StringUtils.isEmpty(innerServiceGoodsVO.getSkuSpecValue()) ? "" : innerServiceGoodsVO.getSkuSpecValue());
        goodsInfoVO.setQuantity(quantity);//购买数量
        goodsInfoVO.setCartId(carId);//购物车id
        goodsInfoVO.setIsPointGood(innerServiceGoodsVO.getIsPointGood());
        goodsInfoVO.setSalePrice(innerServiceGoodsVO.getSalePrice());
        goodsInfoVO.setPointPrice(innerServiceGoodsVO.getPointPrice());
        goodsInfoVO.setIsInMemberGift(innerServiceGoodsVO.getIsInMemberGift());
        goodsInfoVO.setInMemberPointPrice(innerServiceGoodsVO.getInMemberPointPrice());
        return goodsInfoVO;
    }

    /**
     * 检查库存
     *
     * @param goodsItemList
     */
    private CommonStockVO.InnerListCheckStockVO checkStock(List<CommonStockDTO.InnerSkuGoodsInfoItem> goodsItemList) {
        CommonStockDTO.InnerCheckStockDTO innerListCheckStockDTO = new CommonStockDTO.InnerCheckStockDTO();
        innerListCheckStockDTO.setGoodsItemList(goodsItemList);
        ResponseData<CommonStockVO.InnerListCheckStockVO> checkStockVO = commonStockRpc.innerListCheckStock(innerListCheckStockDTO);
        if (ResponseCodeEnum.失败.getCode() == checkStockVO.getCode()) {
//            throw new BusinessException(checkStockVO.getMessage());
        }
        if (!StockCheckStateEnum.库存正常.getCode().equals(checkStockVO.getData().getCheckState())) {//库存状态
//            throw new BusinessException(StockCheckStateEnum.库存不足.getRemark());
        }
        checkStockVO.setCode(StockCheckStateEnum.库存正常.getCode());
        return checkStockVO.getData();
    }

    /**
     * 创建订单
     *
     * @param dto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized ResponseData<BbcTradeDTO.ListIdDTO> orderSubmit(BbcTradeBuildDTO.DTO dto) {
        BbcUserVO.DetailVO userInfo = bbcUserRpc.getUserInfoNoLogin(dto);
        if (userInfo == null || StringUtils.isEmpty(userInfo.getId())) {
            throw new BusinessException("用户不存在！");
        }

        //验证优惠券
        List<String> couponIds = bbcInUserCouponRpc.modifyUserCoupon(dto.getCouponIds(), dto.getJwtUserId(), UserCouponStatusEnum.使用中.getCode());

        Integer telecomsIntegral = userInfo.getTelecomsIntegral();

        BigDecimal realTradePointAmount = BigDecimal.ZERO;    //实际应该付的积分值
        BbcTradeDTO.ListIdDTO listIdDTO = new BbcTradeDTO.ListIdDTO();
        List<String> ids = new ArrayList<String>();
        BbcStockAddressDTO.IdDTO idDto = new BbcStockAddressDTO.IdDTO(dto.getAddressId());
        idDto.setJwtUserId(dto.getJwtUserId());
        BbcStockAddressVO.ListVO addressVO = new BbcStockAddressVO.DetailVO();
        if (!dto.getDeliveryType().equals(TradeDeliveryTypeEnum.门店自提.getCode())) {
            Boolean flag = true;
            //查询是不是虚拟商品购买
            List<ShopData> shopList = dto.getShopData();
            if (shopList.size() == 1) {
                List<ProductData> prod = shopList.get(0).getProductData();
                if (prod.size() == 1) {
                    ProductData productData = prod.get(0);
                    //获取goodsid 判断是不是虚拟商品
                    String goodsId = productData.getGoodsId();
                    BbcGoodsInfoVO.SimpleListVO goodsInfo = bbcGoodsInfoRpc.simpleListVO(new BbcGoodsInfoDTO.IdDTO(goodsId));
                    if (goodsInfo.getExchangeType().equals(GoodsExchangeTypeEnum.虚拟.getCode())) {
                        flag = false;
                    }
                }
            }

            if (flag) {
                //根据id查询地址
                addressVO = bbcStockAddressRpc.detailStockAddress(idDto);
                if (ObjectUtils.isEmpty(addressVO) || addressVO.getId() == null) {
                    throw new BusinessException("查询不到收货地址");
                }
            }
        }
        BigDecimal totalAmount = BigDecimal.ZERO;//总金额
        BigDecimal totalPointAmount = BigDecimal.ZERO;//总积分额

        //商品价格
        BigDecimal totalGoodsAmount = BigDecimal.ZERO;
        BigDecimal totalGoodsPointAmount = BigDecimal.ZERO;

        List<BbcTradeBuildDTO.DTO.ShopData> shopDataList = dto.getShopData();//店铺
        for (BbcTradeBuildDTO.DTO.ShopData shopData : shopDataList) {

            //支付金额
            BigDecimal tradePointAmount = BigDecimal.ZERO;    //总分配积分金额
            BigDecimal tradeAmount = BigDecimal.ZERO;//总分配金额

            //商品价格
            BigDecimal goodsAmount = BigDecimal.ZERO;
            BigDecimal goodsPointAmount = BigDecimal.ZERO;

            //校验库存
            List<CommonStockDTO.InnerSkuGoodsInfoItem> goodsItemList = new ArrayList<>();
            for (BbcTradeBuildDTO.DTO.ProductData productData : shopData.getProductData()) {
                goodsItemList.add(new CommonStockDTO.InnerSkuGoodsInfoItem(null, productData.getGoodsSkuId(), productData.getQuantity()));
            }
            //根据SKU ID数组检查库存
//            checkStock(goodsItemList);

            //查询店铺信息
            BbcShopVO.DetailVO shopDetailVO = iBbcShopRpc.detailShop(new BbcShopDTO.IdDTO(shopData.getShopId()));

            //购物车ID
            List<String> cartIdList = new ArrayList<>();

            //SKUID/购买数量
            List<CommonStockDTO.InnerChangeStockItem> subtractStockItems = new ArrayList<>();

            Set<BbcTradeGoodsDTO.ETO> tradeGoodsDTOSet = new HashSet<>();//交易商品数据

            //店铺商品总金额
            BigDecimal shopProductAmount = BigDecimal.ZERO;

            /**
             *  GoodsAmount(goodsAmount);
             GoodsPointAmount(goodsPointAmount);
             PayableAmount(payableAmount);
             PayablePointAmount(payablePointAmount);
             DiscountAmount(discountAmount);
             DiscountPointAmount(discountPointAmount);
             TradeAmount
             TradePointAmount
             */
            //店铺商品
            for (BbcTradeBuildDTO.DTO.ProductData productData : shopData.getProductData()) {
                //查询商品、判断上下架)
                //获取商品信息
                BbcGoodsInfoVO.InnerServiceVO innerServiceGoodsVO = bbcGoodsInfoRpc.innerServiceGoodsVO(productData.getGoodsSkuId());
                if (ObjectUtils.isEmpty(innerServiceGoodsVO) || innerServiceGoodsVO.getGoodsId() == null) {
                    throw new BusinessException("无商品数据或已下架");
                } else if (!innerServiceGoodsVO.getGoodsState().equals(GoodsStateEnum.已上架.getCode())) {
                    throw new BusinessException("商品已下架");
                }
                tradePointAmount = tradePointAmount.add(productData.getPointAmount()/*.multiply(new BigDecimal(productData.getQuantity()))*/);

                //totalPointAmount = totalPointAmount.add(tradePointAmount);

                if ("gift".equals(productData.getGoodsSkuId())) {
                    continue;
                }

                //单品小计金额
                BigDecimal goodsPrice = innerServiceGoodsVO.getSalePrice().multiply(new BigDecimal(productData.getQuantity()));//单品小计金额
                totalGoodsAmount = totalGoodsAmount.add(goodsPrice);//商品总金额

                //判断当前用户的是不是IN会员
                BigDecimal goodsPointPrice = BigDecimal.ZERO;
                if (userInfo.getIsInUser().equals(1) || innerServiceGoodsVO.getIsInMemberGift()) {
                    goodsPointPrice = innerServiceGoodsVO.getInMemberPointPrice().multiply(new BigDecimal(productData.getQuantity()));//单品小计金额
                } else {
                    goodsPointPrice = innerServiceGoodsVO.getPointPrice().multiply(new BigDecimal(productData.getQuantity()));//单品小计金额
                }
                totalGoodsPointAmount = totalGoodsAmount.add(goodsPointPrice);//商品总金额
                BigDecimal diffAmount = BigDecimal.ZERO;
                if (productData.getPointAmount().compareTo(goodsPointPrice) < 0) {
                    diffAmount = goodsPointPrice.subtract(productData.getPointAmount()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
                }
                tradeAmount = tradeAmount.add(goodsPrice.add(diffAmount));
                totalAmount = totalAmount.add(goodsAmount);

                CommonStockDTO.InnerChangeStockItem innerChangeStockItem = new CommonStockDTO.InnerChangeStockItem();
                innerChangeStockItem.setSkuId(productData.getGoodsSkuId());
                innerChangeStockItem.setQuantity(productData.getQuantity());
                subtractStockItems.add(innerChangeStockItem);

                //组装订单商品表信息
                BbcTradeGoodsDTO.ETO tradeGoodsDTO = fillTradeGoodsDTO(userInfo,
                        shopData, productData.getQuantity(),
                        productData.getPointAmount(), diffAmount, innerServiceGoodsVO, shopDetailVO);

                tradeGoodsDTOSet.add(tradeGoodsDTO);
                //购物车ID
                if (productData.getCartId() != null) {
                    cartIdList.add(productData.getCartId());
                }

                //商品应该付的积分值
                realTradePointAmount = realTradePointAmount.add((innerServiceGoodsVO.getPointPrice()).multiply(new BigDecimal(productData.getQuantity() + "")));
            }
            totalPointAmount = totalPointAmount.add(tradePointAmount);
            //shopData.setShopProductAmount(shopProductAmount);
            /**计算运费
             BigDecimal deliveryAmount = BigDecimal.ZERO; //运费
             if(dto.getDeliveryType().equals(TradeDeliveryTypeEnum.快递配送.getCode()) ||
             dto.getDeliveryType().equals(TradeDeliveryTypeEnum.门店配送.getCode())){
             deliveryAmount = getDeliveryAmount(dto.getShopId(),dto.getProductData(),dto.getDeliveryType(),addressVO.getId());
             }**/
            //shopData.setDeliveryAmount(new BigDecimal("0"));
            /**营销结算
             try {
             marketSettleService.settlement(tradeGoodsDTOSet, dto);
             }catch (Exception e){
             log.error("营销结算异常:"+e.getMessage(), e);
             }**/
            //创建订单信息
            BbcTradeBuildDTO.SingtonDTO singtonDTO = new BbcTradeBuildDTO.SingtonDTO();
            BeanCopyUtils.copyProperties(dto, singtonDTO);
            BeanCopyUtils.copyProperties(shopData, singtonDTO);

            Trade trade = saveTrade(singtonDTO, addressVO,
                    tradeAmount, tradePointAmount, totalGoodsAmount, totalGoodsPointAmount, BigDecimal.ZERO, couponIds);
            try {
                producerService.sendHttpMessage(trade.getId());
                // HttpProducerUtil.sendMessage(trade.getId());
                // producerService.sendTimeMsg("TradeTimeOutCancel",trade.getId().getBytes(),"5e10ac22eb5348dc928e425c1fbf2841",0);
                log.info("向队列发送:BbcTradeServiceImpl");
            } catch (Exception e) {
                log.info("推送队列失败：");
            }
            //创建交易订单商品信息
            saveTradeGoods(trade.getId(), tradeGoodsDTOSet);

            //创建支付信息
            saveTradePay(trade);

            //根据SKU ID、数量减库存
            boolean subtractStockResult = commonStockRpc.innerSubtractStockForTrade(subtractStockItems);
            if (!subtractStockResult) {
                throw new BusinessException("库存不足");
            }
            //根据购物车ID删除记录
            if (ObjectUtils.isNotEmpty(cartIdList)) {
                bbcUserShoppingCarRpc.innerClearShopCarList(cartIdList);
            }
            ids.add(trade.getId());
        }
        /**
         * 把订单ID放到redis里面
         * PermitNode allPermitNode = (PermitNode)redisUtil.get(RbacContants.SESSION_PERMIT_PREFIX + terminal + RbacContants.ALL_PERMIT);

         */

        if (CollectionUtil.isNotEmpty(ids)) {
            JSONObject o = new JSONObject();
            if (redisUtil.get(PAYING_TRADE) != null) {
                String data = (String) redisUtil.get(PAYING_TRADE);
                o = JSONObject.parseObject(data);
            }


            for (String id : ids) {
                o.put(id, DateUtils.getAfterMin(30));
            }
            redisUtil.set(PAYING_TRADE, o.toString());
        }


        return ResponseData.data(new BbcTradeDTO.ListIdDTO(ids, totalAmount, totalPointAmount));
    }

    public static void main(String args[]) {
        System.out.println(DateUtils.parseDate(DateUtils.timeFormatStr, DateUtils.getAfterMin(30)));
        System.out.println(AESUtil.aesEncrypt("ZZqMst7uTJBq4k4f9v5qBw=="));
    }

    /**
     * 创建订单
     *
     * @param dto
     * @param addressVO
     * @param deliveryAmount
     * @return
     */
    private Trade saveTrade(BbcTradeBuildDTO.SingtonDTO dto, BbcStockAddressVO.ListVO addressVO, BigDecimal tradeAmount,
                            BigDecimal tradePointAmount, BigDecimal goodsAmount, BigDecimal goodsPointAmount,
                            BigDecimal deliveryAmount, List<String> couponIds) {
        Trade trade = new Trade();
        trade.setUserId(dto.getJwtUserId());
        trade.setShopId(dto.getShopId());
        trade.setTradeCode(TradeUtils.getTradeCode());
        trade.setTradeState(TradeStateEnum.待支付.getCode());
        trade.setCreateTime(LocalDateTime.now());
        trade.setPayType(TradePayTypeEnum.积分支付.getCode());
        trade.setDeliveryType(dto.getDeliveryType());
        // 优惠券id
        // if (dto.getUserCardVO() != null &&
        // StrUtil.isNotBlank(dto.getUserCardVO().getCardId())) {
        // trade.setUserCardId(dto.getUserCardVO().getCardId());
        // }
        // if(dto.getDeliveryType() != null && dto.getDeliveryType().intValue()
        // == TradeDeliveryTypeEnum.门店自提.getCode()){
        // trade.setTakeGoodsCode(TradeUtils.getTakeGoodsCode());
        // }
        // if(dto.getDeliveryType().equals(TradeDeliveryTypeEnum.门店自提.getCode())){
        // trade.setRecvPersonName(dto.getContactsName());
        // trade.setRecvPhone(dto.getContactsPhone());
        // }else{
        trade.setRecvAddresId(addressVO.getId());
        trade.setRecvPersonName(addressVO.getContactsName());
        trade.setRecvPhone(addressVO.getContactsPhone());
        trade.setRecvFullAddres(
                addressVO.getProvince() + addressVO.getCity() + addressVO.getCounty() + addressVO.getReals());
        // }

        trade.setBuyerRemark(dto.getBuyerRemark());
        trade.setGoodsAmount(dto.getShopProductAmount());
        trade.setDeliveryAmount(deliveryAmount);

        trade.setSourceType(TradeSourceTypeEnum._2C.getCode());
        trade.setGoodsSourceType(20);
        trade.setChildTradeId(UuidUtil.getUuid());


        /**
         * 补的数据
         */
        trade.setMerchantId(dto.getMerchantId());
        trade.setGoodsAmount(goodsAmount);
        trade.setGoodsPointAmount(goodsPointAmount);

        trade.setPayableAmount(goodsAmount);
        trade.setPayablePointAmount(goodsPointAmount);

        trade.setDiscountAmount(BigDecimal.ZERO);
        //优惠积分价格
        BigDecimal discountPointAmount = BigDecimal.ZERO;


        trade.setTradeAmount(BigDecimal.ZERO);
        trade.setTradePointAmount(tradePointAmount);
        if (CollectionUtils.isNotEmpty(couponIds)) {
            String cids = "";
            for (String couponId : couponIds) {
            	BbcInUserCouponVO.DetailVO coupon = bbcInUserCouponRpc.detailCoupon(couponId);
            	discountPointAmount = discountPointAmount.add(coupon.getCouponPrice());
                cids += couponId + ",";
            }
            trade.setCouponIds(cids);
        }
        trade.setDiscountPointAmount(discountPointAmount);
        tradeRepository.save(trade);

        return trade;
    }


    private BigDecimal getDeliveryAmount(String shopId, List<BbcTradeBuildDTO.DTO.ProductData> productDataList, Integer deliveryType, String addressId) {
        BbcStockDeliveryDTO.DeliveryAmountDTO deliveryAmountDTO = new BbcStockDeliveryDTO.DeliveryAmountDTO();
        deliveryAmountDTO.setAddressId(addressId);
        deliveryAmountDTO.setDeliveryType(deliveryType);
        deliveryAmountDTO.setShopId(shopId);
        List<BbcStockDeliveryDTO.DeliverySKUDTO> deliverySKUDTOS = new ArrayList<>();
        for (BbcTradeBuildDTO.DTO.ProductData productData : productDataList) {
            if ("gift".equals(productData.getGoodsId())) {
                continue;
            }
            BbcStockDeliveryDTO.DeliverySKUDTO deliverySKUDTO = new BbcStockDeliveryDTO.DeliverySKUDTO();
            deliverySKUDTO.setSkuId(productData.getGoodsSkuId());
            deliverySKUDTO.setAmount(new BigDecimal(productData.getQuantity()));
            deliverySKUDTOS.add(deliverySKUDTO);
        }
        deliveryAmountDTO.setSkus(deliverySKUDTOS);
        BbcStockDeliveryVO.DeliveryAmountVO deliveryAmountVO = bbcStockDeliveryRpc.calculate(deliveryAmountDTO);
        if (ObjectUtils.isNotEmpty(deliveryAmountVO)) {
            return deliveryAmountVO.getAmount();
        }
        return BigDecimal.ZERO;
    }

    /**
     * 创建支付信息
     *
     * @param trade
     */
    private void saveTradePay(Trade trade) {
        TradePay tradePay = new TradePay();
        tradePay.setTradeId(trade.getId());
        tradePay.setUserId(trade.getUserId());
        tradePay.setShopId(trade.getShopId());
        tradePay.setTradeCode(trade.getTradeCode());
        tradePay.setPayType(trade.getPayType());
        tradePay.setPayState(TradePayStateEnum.待支付.getCode());
        tradePay.setTotalAmount(trade.getTradeAmount());
        tradePayRepository.save(tradePay);
    }

    /**
     * 创建交易订单商品信息
     *
     * @param tradeId
     * @param tradeGoodsDTOSet
     */
    private int saveTradeGoods(String tradeId, Set<BbcTradeGoodsDTO.ETO> tradeGoodsDTOSet) {
        int qty = 0;
        for (BbcTradeGoodsDTO.ETO tradeGoodsDTO : tradeGoodsDTOSet) {
            tradeGoodsDTO.setTradeId(tradeId);
            qty = qty + tradeGoodsDTO.getQuantity();
            TradeGoods tradeGoods = new TradeGoods();
            BeanUtils.copyProperties(tradeGoodsDTO, tradeGoods);
            tradeGoods.setId(null);
            tradeGoodsRepository.save(tradeGoods);
        }
        return qty;
    }

    /**
     * 组装订单商品表信息
     *
     * @param quantity
     * @param innerServiceGoodsVO
     * @return
     */
    private BbcTradeGoodsDTO.ETO fillTradeGoodsDTO(BbcUserVO.DetailVO userInfo, ShopData shopData, Integer quantity,
                                                   BigDecimal payPointAmount, BigDecimal amount, BbcGoodsInfoVO.InnerServiceVO innerServiceGoodsVO,
                                                   BbcShopVO.DetailVO shopDetailVO) {
        BbcTradeGoodsDTO.ETO tradeGoodsDTO = new BbcTradeGoodsDTO.ETO();
        BeanCopyUtils.copyProperties(innerServiceGoodsVO, tradeGoodsDTO);
        BeanCopyUtils.copyProperties(shopData, tradeGoodsDTO);
        BeanCopyUtils.copyProperties(shopDetailVO, tradeGoodsDTO);
        tradeGoodsDTO.setUserId(userInfo.getId());
        tradeGoodsDTO.setSkuSpecValue(StringUtils.isEmpty(innerServiceGoodsVO.getSkuSpecValue()) ? "" : innerServiceGoodsVO.getSkuSpecValue());
        tradeGoodsDTO.setSkuImg(innerServiceGoodsVO.getGoodsImage());
        tradeGoodsDTO.setSkuGoodsNo(innerServiceGoodsVO.getSkuGoodsNo());
        tradeGoodsDTO.setQuantity(quantity);
        tradeGoodsDTO.setCommentFlag(TradeTrueFalseEnum.是.getCode());
        tradeGoodsDTO.setTradePointAmount(payPointAmount);
        tradeGoodsDTO.setTradeAmount(amount);

        if (userInfo.getIsInUser().equals(1) || innerServiceGoodsVO.getIsInMemberGift()) {
            tradeGoodsDTO.setGoodsPointAmount(innerServiceGoodsVO.getInMemberPointPrice());
        } else {
            tradeGoodsDTO.setGoodsPointAmount(innerServiceGoodsVO.getGoodsPointAmount());
        }
        tradeGoodsDTO.setPayableAmount(innerServiceGoodsVO.getGoodsAmount());
        tradeGoodsDTO.setPayablePointAmount(innerServiceGoodsVO.getPayablePointAmount());

        tradeGoodsDTO.setDiscountAmount(BigDecimal.ZERO);
        tradeGoodsDTO.setDiscountPointAmount(BigDecimal.ZERO);
        return tradeGoodsDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseData<Void> orderPay(BbcTradePayBuildDTO.ETO dto) {

        String openid = dto.getJwtWxOpenid();
        if (StringUtils.isEmpty(openid)) {
            if (StringUtils.isNotEmpty(dto.getOpenid())) {
                openid = dto.getOpenid();
            } else {
                throw new BusinessException("未获取微信用户信息");
            }
        }

        List<String> tradeIds = dto.getTradeIds();
        if (tradeIds == null || tradeIds.size() == 0) {
            throw new BusinessException("订单号参数错误");
        }

        // 查询订单信息
        List<Trade> tradeList = tradeRepository.listByIds(tradeIds);
        if (tradeList == null || tradeList.size() == 0 || tradeList.size() != tradeIds.size()) {
            throw new BusinessException("存在无效订单，请核对");
        }

        // 检查订单状态
        Integer consistentCashPayTypeCode = null;
        for (Trade trade : tradeList) {
            if (TradeStateEnum.待支付.getCode() != trade.getTradeState().intValue()) {
                throw new BusinessException("订单状态有误，请重试");
            }

            if (TradeUtils.checkPayTime(trade.getCreateTime())) {
                // TODO 由定时任务做超时取消操作
               /* //取消订单
                trade.setTradeState(TradeStateEnum.已取消.getCode());
                trade.setTimeoutCancel(TradeTimeOutCancelEnum.超时取消.getCode());
                tradeRepository.saveOrUpdate(trade);
                //回库存
                cancelTradeReturnStock(trade.getId());*/
                return ResponseData.fail("支付超时,请重新下单");
            }

            // 要求所有订单现金支付对应的第三方一致
            if (!TradePayTypeEnum.积分支付.getCode().equals(trade.getPayType())) {
                // 获取现金支付的第三方
                Integer cashPayTypeCode = TradePayTypeEnum.getEnum(trade.getPayType()).getMixedPayment() ?
                        TradePayTypeEnum.getEnum(trade.getPayType()).getSubType().getCode() : TradePayTypeEnum.getEnum(trade.getPayType()).getCode();
                if (consistentCashPayTypeCode == null) {
                    consistentCashPayTypeCode = cashPayTypeCode;
                } else if (!consistentCashPayTypeCode.equals(cashPayTypeCode)) {
                    throw new BusinessException("请统一现金支付方式");
                }
            }
        }

        // 检查是合并支付或单个订单，确定传递给第三方支付的交易单号
        String mergePaymentTradeCode;
        if (tradeList.size() == 1) {
            mergePaymentTradeCode = tradeList.get(0).getTradeCode();
        } else {
            mergePaymentTradeCode = TradeUtils.getTradeCode();
        }

        // 生成数据，准备插入支付表
        // 现金金额，涉及哪几个订单
        BigDecimal totalCashAmount = new BigDecimal(0);
        Map<String, Trade> cashTradeMap = new HashMap<>();
        // 积分金额，涉及哪几个订单
        BigDecimal totalPointAmount = new BigDecimal(0);
        Map<String, Trade> pointTradeMap = new HashMap<>();
        // 0元订单，最后直接支付成功 TODO 回调时处理为成功
        ArrayList<String> freeTradeIds = new ArrayList<>();

        for (Trade trade : tradeList) {
            // 判断商品来源
            if (GoodsSourceTypeEnum.商城商品.getCode().equals(trade.getGoodsSourceType())) {
                // 0元订单
                if (trade.getTradeAmount().compareTo(new BigDecimal(0)) == 0) {
                    freeTradeIds.add(trade.getId());
                    continue;
                }
                // 计算商城商品总金额
                totalCashAmount = totalCashAmount.add(trade.getTradeAmount());
                cashTradeMap.put(trade.getId(), trade);
                continue;
            } else {
                // 0元订单
                if (trade.getAmountActuallyPaid().compareTo(new BigDecimal(0)) == 0
                        && trade.getPointPriceActuallyPaid().compareTo(new BigDecimal(0)) == 0) {
                    freeTradeIds.add(trade.getId());
                    continue;
                }
                // 计算积分商城商品总金额
                if (trade.getAmountActuallyPaid().compareTo(new BigDecimal(0)) == 1) {
                    totalCashAmount = totalCashAmount.add(trade.getAmountActuallyPaid());
                    cashTradeMap.put(trade.getId(), trade);
                }
                // 计算积分商城商品总积分
                if (trade.getPointPriceActuallyPaid().compareTo(new BigDecimal(0)) == 1) {
                    totalPointAmount = totalPointAmount.add(trade.getPointPriceActuallyPaid());
                    pointTradeMap.put(trade.getId(), trade);
                }
                continue;
            }
        }

        //0元订单支付
        /*if (trade.getTradeAmount().compareTo(BigDecimal.ZERO) <= 0) {
            String payResult = paySuccess(trade.getTradeCode());
            return ResponseData.success(payResult);
        }*/

        // TODO 查询支付数据，如果订单有支付（待支付）记录则校验删除相关，如果有已支付则报异常
        QueryWrapper<TradePay> tradePayWrapper = new QueryWrapper<>();
        tradePayWrapper.in("trade_id", tradeIds);
        List<TradePay> tradePayList = tradePayRepository.list(tradePayWrapper);
        if (tradePayList != null && tradePayList.size() > 0) {
            // TODO yj 有支付（待支付）记录则校验是否继续执行
            // 存在支付记录的场景：1.合并支付未付款，后单独支付；2.支付结果未更新
            // 步骤：1.先拿到支付单号，请求对应的第三方查询支付信息
            // 2.如果返回支付超时，或者没有支付信息等未支付的信息，则将此支付单号的所有支付记录都标记删除
            // 3.如果返回支付成功等已支付的信息，则更新结果到支付表，或报错：刷新重试，等定时任务或者回调来处理


            ArrayList<TradePay> delTradePayList = new ArrayList<>();
            for (TradePay tradePay : tradePayList) {
                // 第三方支付信息查询结果 todo yj
                boolean thirdPayResult = false;

                if (!thirdPayResult) {
                    // 请求第三方后，结果显示未调用第三方/未支付，将此支付单号的所有支付记录都标记删除
                    TradePay delTradePay = new TradePay();
                    delTradePay.setId(tradePay.getId());
                    delTradePay.setFlag(true);
                    delTradePayList.add(delTradePay);
                } else {
                    // 请求第三方后，结果显示支付成功等已支付的信息，返回错误信息
                    throw new BusinessException("存在重复支付，请检查刷新后重试");
                }
            }

            if (delTradePayList != null && delTradePayList.size() > 0) {
//                List<String> ids = delTradePayList.stream().map(TradePay -> TradePay.getId()).collect(Collectors.toList());
                // 批量删除
                for (TradePay tradePay : delTradePayList) {
                    // TODO 批量修改的优化
                    int updateResult = tradePayMapper.delById(tradePay.getId());
                    if (updateResult < 1) {
                        throw new BusinessException("支付记录批量删除失败");
                    }
                }
            }
        }

        List<TradePay> prepareSaveTradePays = new ArrayList<>();
        // 积分
        if (pointTradeMap != null && pointTradeMap.size() != 0) {
            for (String pointTradeId : pointTradeMap.keySet()) {
                Trade trade = pointTradeMap.get(pointTradeId);
                TradePay tradePay = new TradePay();
                tradePay.setTradeId(trade.getId());
                tradePay.setMergePaymentTradeCode(mergePaymentTradeCode);
                tradePay.setUserId(trade.getUserId());
                tradePay.setShopId(trade.getShopId());
                tradePay.setTradeCode(trade.getTradeCode());
                tradePay.setPayState(TradePayStateEnum.待支付.getCode());
                tradePay.setPayType(TradePayTypeEnum.积分支付.getCode());
                tradePay.setTotalAmount(trade.getPointPriceActuallyPaid());
                prepareSaveTradePays.add(tradePay);
            }

            // 初始化积分支付相关支付记录
            boolean saveBatchTradePayResult = tradePayRepository.saveBatch(prepareSaveTradePays);
            if (!saveBatchTradePayResult) {
                throw new BusinessException("创建积分支付失败，请重试");
            }

            // 如果是纯积分支付，则调用积分支付  TODO yj 混合支付，积分相关操作等现金支付回调结果后处理
            if (cashTradeMap == null || cashTradeMap.size() == 0) {
                // TODO yj 调用积分支付；混合支付的积分相关操作等现金支付回调结果后处理
//                boolean result = pointPay(mergePaymentTradeCode, totalPointAmount);
                boolean result = true;
                if (!result) {
                    throw new BusinessException("积分支付失败，请重试");
                } else {
                    // 更新支付结果
                    for (TradePay prepareSaveTradePay : prepareSaveTradePays) {
                        prepareSaveTradePay.setPayState(TradePayStateEnum.已支付.getCode());
                    }
                    boolean updateResult = tradePayRepository.updateBatchById(prepareSaveTradePays);
                    if (!updateResult) {
                        throw new BusinessException("系统繁忙，请稍后再试");
                    }

                    List<Trade> updateTradeList = new ArrayList<>();
                    for (String tradeId : pointTradeMap.keySet()) {
                        Trade trade = new Trade();
                        trade.setId(tradeId);
                        trade.setTradeState(TradeStateEnum.待发货.getCode());
                        updateTradeList.add(trade);
                    }
                    updateResult = tradeRepository.updateBatchById(updateTradeList);
                    if (!updateResult) {
                        throw new BusinessException("系统繁忙，请稍后再试");
                    }
                }
                return ResponseData.data(true);
            }
        }

        prepareSaveTradePays = new ArrayList<>();
        // 现金支付
        if (cashTradeMap != null && cashTradeMap.size() != 0) {
            for (String cashTradeId : cashTradeMap.keySet()) {
                Trade trade = cashTradeMap.get(cashTradeId);
                TradePay tradePay = new TradePay();
                tradePay.setTradeId(trade.getId());
                tradePay.setMergePaymentTradeCode(mergePaymentTradeCode);
                tradePay.setUserId(trade.getUserId());
                tradePay.setShopId(trade.getShopId());
                tradePay.setTradeCode(trade.getTradeCode());
                tradePay.setPayState(TradePayStateEnum.待支付.getCode());
                tradePay.setPayType(trade.getPayType());
                tradePay.setTotalAmount(trade.getGoodsSourceType().equals(GoodsSourceTypeEnum.商城商品.getCode()) ?
                        trade.getTradeAmount() : trade.getAmountActuallyPaid());
                prepareSaveTradePays.add(tradePay);
            }

            // 根据支付类型，向第三方请求
            if (TradePayTypeEnum.微信小程序支付.getCode().equals(TradePayTypeEnum.getEnum(consistentCashPayTypeCode).getCode())
                    || TradePayTypeEnum.微信小程序支付.getCode().equals(TradePayTypeEnum.getEnum(consistentCashPayTypeCode).getSubType().getCode())) {
                // todo yj 根据合并订单号，调用微信小程序支付，获取微信支付单号，存入支付表，获取相关信息，返回前端
                // QRCodePaymentCommitResponse responseData = goPayment(openid, trade, tradePay);
                // todo 此代码是阻止传参报错，暂传null参数
                QRCodePaymentCommitResponse responseData = goPayment(openid, null, null);

                if (ObjectUtils.isNotEmpty(responseData) && responseData.isSuccess()) {
                    log.info("[doPay] 接口调用成功，开始处理业务逻辑");
                    log.info("tradeNo=" + responseData.getTradeNo());//保存第三方交易号
                    log.info("payInfo=" + responseData.getPayInfo());
                    log.info("prePayId=" + responseData.getPrePayId());
                    JSONObject payInfoJson = payInfoToJSON(responseData.getPayInfo());

                    //更新保存支付交易号
                    String payCode = responseData.getTradeNo();
                    String token = responseData.getToken();
                    String clientIp = "";
                    if (ObjectUtils.isNotEmpty(IpUtil.getHttpServletRequest())) {
                        log.info("getHttpServletRequest{}", IpUtil.getHttpServletRequest());
                        clientIp = IpUtil.getRemoteHost(IpUtil.getHttpServletRequest());
                    }
                    String payInfo = payInfoJson.toJSONString();
                    log.info("2tradePay.getPayInfo():" + payInfo);

                    // 将信息一起存入支付表
                    for (TradePay prepareSaveTradePay : prepareSaveTradePays) {
                        prepareSaveTradePay.setPayCode(payCode);
                        prepareSaveTradePay.setToken(token);
                        prepareSaveTradePay.setClientIp(clientIp);
                        prepareSaveTradePay.setPayInfo(payInfo);
                    }
                    // 初始化现金支付相关支付记录
                    boolean saveBatchTradePayResult = tradePayRepository.saveBatch(prepareSaveTradePays);
                    if (!saveBatchTradePayResult) {
                        throw new BusinessException("创建支付失败，请重试");
                    }

                    return ResponseData.data(payInfoJson);
                }
            }
/*            if (trade.getPayType().equals(TradePayTypeEnum.线下支付)) {
                QueryWrapper<TradePayOffline> tradePayOfflineQueryWrapper = new QueryWrapper<>();
                tradePayOfflineQueryWrapper.eq("pay_id", tradePay.getId());
                TradePayOffline tradePayOffline = tradePayOfflineRepository.getOne(tradePayOfflineQueryWrapper);
                if (ObjectUtils.isEmpty(tradePayOffline)) {
                    tradePayOffline = new TradePayOffline();
                }
                tradePayOffline.setPayId(tradePay.getId());
                tradePayOffline.setPayOrder(dto.getPayOrder());
                tradePayOffline.setPayAmount(dto.getPayAmount());
                tradePayOffline.setAccountName(dto.getAccountName());
                tradePayOffline.setAccountNumber(dto.getAccountNumber());
                tradePayOffline.setBankName(dto.getBankName());
                tradePayOffline.setPayRemark(dto.getPayRemark());
                tradePayOffline.setVerifyState(TradeVerifyStateEnum.待确认.getCode());
                tradePayOfflineRepository.saveOrUpdate(tradePayOffline);

                tradePay.setPayState(TradePayStateEnum.待确认.getCode());
                tradePayRepository.saveOrUpdate(tradePay);
            }*/

            // TODO 其他支付方式
        }
        return ResponseData.fail("支付失败");
    }

    private JSONObject payInfoToJSON(String payInfo) {
        String[] str = payInfo.split("\\|");
        JSONObject payInfoJson = new JSONObject(true);
        for (String s : str) {
            payInfoJson.put(s.substring(0, s.indexOf("=")), s.substring(s.indexOf("=") + 1));
        }
        return payInfoJson;
    }

    @Override
    public PageData<BbcTradeListVO.tradeVO> tradeListPageData(BbcTradeQTO.TradeList qto) {

        QueryWrapper<BbcTradeQTO.TradeList> wrapper = new QueryWrapper<>();
        wrapper.and(i -> i.eq("t.`user_id`", qto.getJwtUserId()));
        wrapper.isNull("t.`is_hide`");
        if (ObjectUtils.isNotEmpty(qto.getKeyword())) {
            wrapper.and(i -> i.
                    like("tg.`goods_name`", qto.getKeyword()).or().
                    eq("tg.`goods_no`", qto.getKeyword()).or().
                    eq("t.`trade_code`", qto.getKeyword()));
        }
        if (ObjectUtils.isNotEmpty(qto.getTradeState())) {
            if (qto.getTradeState() == 60) {
                wrapper.and(i -> i.eq("t.`trade_state`", 40));
                wrapper.groupBy("t.`id`");
                wrapper.having("bc > cc");
            } else if (qto.getTradeState() == 70) {
                wrapper.and(i -> i.eq("t.`trade_state`", 40));
                wrapper.having("rightsId is null");
            } else {
                wrapper.and(i -> i.eq("t.`trade_state`", qto.getTradeState()));//交易状态,不传则查所有状态数据
            }
        }
        wrapper.and(i -> i.eq("t.`source_type`", 10));
        wrapper.groupBy("t.`id`");
        List<BbcTradeListVO.tradeVO> count = tradeRepository.selectTradeListPage(wrapper);
        wrapper.orderByDesc("cdate");
        IPage<BbcTradeListVO.tradeVO> pager = MybatisPlusUtil.pager(qto);
        wrapper.last("limit " + pager.offset() + "," + pager.getSize());
        List<BbcTradeListVO.tradeVO> listPage = tradeRepository.selectTradeListPage(wrapper);
        List<BbcTradeListVO.tradeVO> voList = new ArrayList<>();
        if (ObjectUtils.isEmpty(listPage)) {
            return new PageData<>();
        }
        for (BbcTradeListVO.tradeVO tradeVO : listPage) {
            //查询退款表
            QueryWrapper<TradeRights> query = MybatisPlusUtil.query();
            query.and(i -> i.eq("trade_id", tradeVO.getId()));
            query.last("limit 0,1");
            TradeRights one = iTradeRightsRepository.getOne(query);
            if (ObjectUtils.isNotEmpty(one)) {
                tradeVO.setRightsState(one.getState());
            }
            //查询店铺信息
            BbcShopQTO.InnerShopQTO innerShopQTO = new BbcShopQTO.InnerShopQTO();
            innerShopQTO.setShopId(tradeVO.getShopId());
            BbcShopVO.InnerDetailVO innerDetailVO = iBbcShopRpc.innerDetailShop(innerShopQTO);
            if (null != innerDetailVO) {
                tradeVO.setShopName(StringUtils.isBlank(innerDetailVO.getShopName()) ? "" : innerDetailVO.getShopName());
            }
            //根据交易ID查询交易商品集合
            fillTradeVO(tradeVO);
            voList.add(tradeVO);
            //填充用户信息
            fillUserInfo(tradeVO);
        }

        return new PageData<>(voList, qto.getPageNum(), qto.getPageSize(), count.size());
    }

    /**
     * 获取订单详情
     */
    @Override
    public ResponseData<BbcTradeListVO.tradeVO> orderDetail(BbcTradeDTO.IdDTO dto) {
        BbcTradeListVO.tradeVO tradeVO = new BbcTradeListVO.tradeVO();
        Trade trade = tradeRepository.getById(dto.getId());
        if (ObjectUtils.isEmpty(trade)) {
            throw new BusinessException("无订单数据");
        }
        BeanUtils.copyProperties(trade, tradeVO);
        //填充商家信息
        fillShop(tradeVO);

        //填充商品集合
        fillTradeVO(tradeVO);

        if (tradeVO.getTradeState().equals(TradeStateEnum.待支付.getCode())) {
            tradeVO.setPayDeadline(tradeVO.getCreateTime().plusMinutes(Common.PAYMENT_TIME_OUT));
        }
        //查询退款表
        QueryWrapper<TradeRights> query = MybatisPlusUtil.query();
        query.and(i -> i.eq("trade_id", tradeVO.getId()));
        query.last("limit 0,1");
        TradeRights one = iTradeRightsRepository.getOne(query);
        if (ObjectUtils.isNotEmpty(one)) {
            tradeVO.setRightsState(one.getState());
        }
        //查询物流信息
        if (tradeVO.getDeliveryType().equals(TradeDeliveryTypeEnum.快递配送.getCode()) &&
                (tradeVO.getTradeState().equals(TradeStateEnum.待收货.getCode()) ||
                        tradeVO.getTradeState().equals(TradeStateEnum.已完成.getCode()))) {//快递配送/已发货/已收货
            //填充物流信息
            fillLogisticsCompany(tradeVO);
        }
        //填充用户信息
        fillUserInfo(tradeVO);

        return ResponseData.data(tradeVO);
    }

    private void fillUserInfo(BbcTradeListVO.tradeVO tradeVO) {
        BbcUserVO.InnerUserInfoVO innerUserInfoVO = bbcUserRpc.innerGetUserInfo(tradeVO.getUserId());
        if (ObjectUtils.isNotEmpty(innerUserInfoVO)) {
            tradeVO.setUserName(innerUserInfoVO.getUserName());
        }
    }

    private void fillShop(BbcTradeListVO.tradeVO tradeVO) {
        BbcShopQTO.InnerShopQTO innerShopQTO = new BbcShopQTO.InnerShopQTO();
        innerShopQTO.setShopId(tradeVO.getShopId());
        BbcShopVO.InnerDetailVO innerDetailVO = iBbcShopRpc.innerDetailShop(innerShopQTO);
        if (ObjectUtils.isNotEmpty(innerDetailVO)) {
            tradeVO.setShopName(innerDetailVO.getShopName());
            tradeVO.setShopLogo(innerDetailVO.getShopLogo());
            tradeVO.setShopManName(innerDetailVO.getShopManName());
            tradeVO.setShopManPhone(innerDetailVO.getShopManPhone());
            tradeVO.setShopFullAddres(innerDetailVO.getShopFullAddres());
            tradeVO.setShopLatitude(innerDetailVO.getShopLatitude());
            tradeVO.setShopLongitude(innerDetailVO.getShopLongitude());
        }
    }

    private void fillLogisticsCompany(BbcTradeListVO.tradeVO tradeVO) {
        QueryWrapper<TradeDelivery> tradeDeliveryQueryWrapper = new QueryWrapper<>();
        tradeDeliveryQueryWrapper.eq("trade_id", tradeVO.getId());
        TradeDelivery tradeDelivery = tradeDeliveryRepository.getOne(tradeDeliveryQueryWrapper);
        if (ObjectUtils.isNotEmpty(tradeDelivery)) {
            CommonLogisticsCompanyVO.DetailVO logisticsDetailVO = commonLogisticsCompanyRpc.getLogisticsCompany(tradeDelivery.getLogisticsId());
            if (ObjectUtils.isNotEmpty(logisticsDetailVO)) {
                tradeVO.setLogisticsNumber(tradeDelivery.getLogisticsNumber());
                tradeVO.setLogisticsCompanyCode(logisticsDetailVO.getCode());
                tradeVO.setLogisticsCompanyName(logisticsDetailVO.getName());
            }
        }
    }


    @DubboReference
    private IBbcUserIntegralRpc iBbcUserIntegralRpc;

    /**
     * 订单确认
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseData<Void> orderConfirmReceipt(BbcTradeDTO.IdDTO dto) {
        Trade trade = tradeRepository.getById(dto.getId());
        if (ObjectUtils.isEmpty(trade)) {
            throw new BusinessException("无订单信息");
        }
        if (trade.getTradeState().intValue() != TradeStateEnum.待收货.getCode()) {
            //如果订单状态不是"待收货"则不允许确认收货
            if (trade.getTradeState().intValue() == TradeStateEnum.待发货.getCode() ||
                    trade.getTradeState().intValue() == TradeStateEnum.待支付.getCode()) {
                throw new BusinessException("不允许确认收货");
            } else {
                throw new BusinessException("该商品已收货");
            }
        }
        trade.setTradeState(TradeStateEnum.已完成.getCode());
        trade.setRecvTime(LocalDateTime.now());
        tradeRepository.saveOrUpdate(trade);
        try {
            BbcUserIntegralDTO.ETO eto = new BbcUserIntegralDTO.ETO();
            eto.setTradeAmount(trade.getTradeAmount());
            eto.setFromType(20);
            eto.setUserId(trade.getUserId());
            eto.setFromId(trade.getId());
            iBbcUserIntegralRpc.addUserTradeIntergral(eto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 查询订单详细信息
        ResponseData<BbcTradeListVO.tradeVO> tradeVOResponseData = orderDetail(dto);
        BbcTradeListVO.tradeVO tradeVO = tradeVOResponseData.getData();
        // 获取用户详细信息
        BbcUserVO.InnerUserInfoVO innerUserInfoVO = iBbcUserRpc.innerGetUserInfo(tradeVO.getUserId());

        iContactSMSService.signForSMS(innerUserInfoVO.getPhone(),innerUserInfoVO.getUserName(),
                tradeVO.getLogisticsCompanyName(),tradeVO.getLogisticsNumber());
        return ResponseData.success();
    }

    @Override
    public ResponseData<Void> deliveryAmount(BbcTradeBuildDTO.DTO dto) {
        /**
         BbcStockDeliveryDTO.DeliveryAmountDTO deliveryAmountDTO = new BbcStockDeliveryDTO.DeliveryAmountDTO();
         deliveryAmountDTO.setAddressId(dto.getAddressId());
         deliveryAmountDTO.setDeliveryType(dto.getDeliveryType());
         deliveryAmountDTO.setShopId(dto.getShopId());
         List<BbcStockDeliveryDTO.DeliverySKUDTO> deliverySKUDTOS = new ArrayList<>();
         for(BbcTradeBuildDTO.DTO.ProductData goodsInfoVO : dto.getProductData()){
         BbcStockDeliveryDTO.DeliverySKUDTO deliverySKUDTO = new BbcStockDeliveryDTO.DeliverySKUDTO();
         deliverySKUDTO.setSkuId(goodsInfoVO.getGoodsSkuId());
         deliverySKUDTO.setAmount(new BigDecimal(goodsInfoVO.getQuantity()));
         deliverySKUDTOS.add(deliverySKUDTO);
         }
         deliveryAmountDTO.setSkus(deliverySKUDTOS);
         BbcStockDeliveryVO.DeliveryAmountVO deliveryAmountVO = bbcStockDeliveryRpc.calculate(deliveryAmountDTO);
         if(ObjectUtils.isNotEmpty(deliveryAmountVO)){
         Map result = new HashMap();
         result.put("deliveryAmount",deliveryAmountVO.getAmount());
         return ResponseData.data(result);
         }
         **/
        return ResponseData.fail();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String payNotify(BbcTradeResultNotifyVO.notifyVO notifyVO) {
        log.info("订单支付回调:" + notifyVO.toString());
        EntMergeOfflineResultNotify resultNotify = new EntMergeOfflineResultNotify();
        BeanUtils.copyProperties(notifyVO, resultNotify);
        if (notifyVO.getReturnMessage() != null) {
            resultNotify.setReturnMessage(new String(Base64.decode(notifyVO.getReturnMessage())));
        }
        if (notifyVO.getBackParam() != null) {
            resultNotify.setBackParam(new String(Base64.decode(notifyVO.getBackParam())));
        }
        if (notifyVO.getFailMsg() != null) {
            resultNotify.setFailMsg(new String(Base64.decode(notifyVO.getFailMsg())));
        }

        try {
            BossClient client = new BossClient(MerchantBaseEnum.merchant_hly_CertPath.getValue(),
                    MerchantBaseEnum.merchant_hly_CertPass.getValue(), MerchantBaseEnum.serverUrl.getValue());

            JSONObject responseJson = new JSONObject();

            if (client.verify(resultNotify)) {
                System.out.println("验签成功，开始处理业务逻辑");
                //判断支付结果
                if (!resultNotify.getStatus().equals(TradePayResultStateEnum.SUCCESS.getRemark())) {
                    log.error(resultNotify.getFailMsg());
                    responseJson.put("result", TradePayResultStateEnum.FAILED.getRemark());
                    return responseJson.toString();
                }
                //根据交易订单编号修改订单状态
                return paySuccess(resultNotify.getOrderId());
            } else {
                System.out.println("验签失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 隐藏订单
     *
     * @param dto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseData<Void> orderHide(BbcTradeDTO.IdDTO dto) {
        Trade trade = tradeRepository.getById(dto.getId());
        if (ObjectUtils.isEmpty(trade)) {
            throw new BusinessException("无订单信息");
        }
        trade.setIsHide(TradeHideEnum.隐藏.getCode());
        tradeRepository.saveOrUpdate(trade);

        return ResponseData.success();
    }

    /**
     * 取消订单
     *
     * @param dto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseData<Void> orderCancel(BbcTradeCancelDTO.CancelDTO dto) {
        if (ObjectUtils.isEmpty(dto.getRemark())) {
            throw new BusinessException("请输入原因");
        }
        System.out.println(dto);
        TradeCancel tradeCancel = new TradeCancel();
        Trade trade = tradeRepository.getById(dto.getTradeId());
        if (ObjectUtils.isEmpty(trade)) {
            throw new BusinessException("无订单信息");
        }
        if (trade.getTradeState().equals(TradeStateEnum.待支付.getCode())) {//直接取消订单
            trade.setTradeState(TradeStateEnum.已取消.getCode());
            trade.setTimeoutCancel(TradeTimeOutCancelEnum.买家取消.getCode());

            fillTradeCancel(dto, tradeCancel, trade, null, TradeCancelStateEnum.完成.getCode(), TradeCancelRefundStateEnum.无需退款.getCode());

        } else if (trade.getTradeState().equals(TradeStateEnum.待发货.getCode())) {
            //添加售后表，状态为
            trade.setTradeState(TradeStateEnum.已取消.getCode());
            trade.setTimeoutCancel(TradeTimeOutCancelEnum.买家取消.getCode());
            inset(trade, dto);


        } else {
            throw new BusinessException("不允许取消订单");
        }
        tradeCancelRepository.save(tradeCancel);
        tradeRepository.saveOrUpdate(trade);
        //回库存
        cancelTradeReturnStock(trade.getId());

        return ResponseData.success();
    }

    private TradeRights inset(Trade trade, BbcTradeCancelDTO.CancelDTO dto) {
        TradeRights tradeRights = new TradeRights();
        tradeRights.setTradeId(trade.getId()).
                setShopId(trade.getShopId()).
                setUserId(trade.getUserId()).
                setMerchantId(trade.getMerchantId()).
                setOrderCode(trade.getTradeCode()).
                setRefundRemarks(dto.getRemark()).
                setRefundAmount(trade.getTradeAmount()).
                setState(TradeRightsStateEnum.申请.getCode()).
                setRightsType(TradeRightsTypeEnum.仅退款.getCode()).
                setRightsReasonType(TradeRightsReasonTypeEnum.取消订单.getCode()).
                setRightsRemark(dto.getRemark()).
                //setRefundRemarks(dto.getRemark()).
                        setApplyTime(LocalDateTime.now()).setIsHide(0).setRefundMoneyType(TradeRightsRefundMoneyTypeEnum.取消订单退款.getCode())
                .setRefundType(TradeRightsRefundTypeEnum.原路退回.getCode());
        iTradeRightsRepository.save(tradeRights);
        QueryWrapper<TradeGoods> query = MybatisPlusUtil.query();
        query.and(i -> i.eq("trade_id", tradeRights.getTradeId()));
        List<TradeGoods> list = tradeGoodsRepository.list(query);
        if (ObjectUtils.isNotEmpty(list)) {
            for (TradeGoods tradeGoods : list) {
                TradeRightsGoods tradeRightsGoods = new TradeRightsGoods();
                tradeRightsGoods.setRightsId(tradeRights.getId()).
                        setTradeId(tradeRights.getTradeId()).
                        setTradeGoodsId(tradeGoods.getId()).
                        setUserId(tradeRights.getUserId()).
                        setShopId(tradeRights.getShopId()).
                        setMerchantId(tradeRights.getMerchantId()).
                        setOrderCode(tradeRights.getOrderCode()).
                        setGoodsName(tradeGoods.getGoodsName()).
                        setSkuId(tradeGoods.getSkuId()).
                        setSkuSpecValue(tradeGoods.getSkuSpecValue()).
                        setQuantity(tradeGoods.getQuantity()).
                        setSalePrice(tradeGoods.getSalePrice()).
                        setRefundAmount(tradeGoods.getPayAmount());
                iTradeRightsGoodsRepository.save(tradeRightsGoods);
            }

        }

        return tradeRights;
    }

    private void fillTradeCancel(BbcTradeCancelDTO.CancelDTO dto, TradeCancel tradeCancel, Trade trade, String tradePayId, Integer cancelState, Integer cancelRefundState) {
        tradeCancel.setTradeId(trade.getId());
        tradeCancel.setTradeCode(trade.getTradeCode());
        tradeCancel.setUserId(trade.getUserId());
        tradeCancel.setShopId(trade.getShopId());
        tradeCancel.setPayId(tradePayId);
        tradeCancel.setTradeAmount(trade.getTradeAmount());
        tradeCancel.setCancelState(cancelState);
        tradeCancel.setApplyType(TradeCancelApplyTypeEnum.用户取消订单.getCode());
        tradeCancel.setApplyTime(LocalDateTime.now());
        tradeCancel.setReasonType(dto.getReasonType());
        tradeCancel.setRemark(dto.getRemark());
        tradeCancel.setRefundState(cancelRefundState);
    }

    /**
     * 取消交易,回库存
     *
     * @param tradeId
     * @return
     */
    private boolean cancelTradeReturnStock(String tradeId) {
        List<CommonStockDTO.InnerChangeStockItem> goodsItemList = new ArrayList<>();
        fillChangeStockItem(tradeId, goodsItemList);
        return commonStockRpc.innerPlusStockForTrade(goodsItemList);
    }

    @Override
    public List<BbcTradeListVO.stateCountVO> tradeStateCount(BbcTradeDTO.IdDTO dto) {

        QueryWrapper<BbcTradeQTO> tradeWrapper = new QueryWrapper<>();
        tradeWrapper.and(i -> i.eq("t.`user_id`", dto.getJwtUserId()));
        tradeWrapper.and(i -> i.eq("t.`source_type`", 10));
        tradeWrapper.in("trade_state", Arrays.asList(TradeStateEnum.待支付.getCode(), TradeStateEnum.待收货.getCode(), TradeStateEnum.待发货.getCode()));
        tradeWrapper.groupBy("trade_state");

        List<BbcTradeListVO.stateCountVO> stateCountVO = tradeRepository.selectTradeStateCount(tradeWrapper);

        return stateCountVO;
    }

    @Override
    public List<BbcTradeListVO.InnerGoodsCompareTo> innerCommpareTo(BbcTradeDTO.innerCommpareTo dto) {
        if (dto.getCompareToType() == 10) {
            dto.setCompareToType(20);
        } else if (dto.getCompareToType() == 20) {
            dto.setCompareToType(10);
        }
        if (ObjectUtils.isNotEmpty(dto.getGoodsIds())) {
            if (ObjectUtils.isNotEmpty(dto.getCompareToType())) {
                if (dto.getCompareToType() == 10) {
                    //评分
                    QueryWrapper<BbcTradeDTO.innerCommpareTo> query = MybatisPlusUtil.query();
                    query.and(i -> i.in("trade_goods_id", dto.getGoodsIds()));
                    query.groupBy("trade_goods_id");
                    List<BbcTradeVO.InnerCompareTo> innerCompareTos = iTradeCommentRepository.selectBbcCompareTo(query);
                    if (ObjectUtils.isNotEmpty(innerCompareTos)) {
                        if (ObjectUtils.isNotEmpty(dto.getCompareToMode())) {
                            if (dto.getCompareToMode() == 10) {
                                //评分升序
                                innerCompareTos.sort(Comparator.comparing(BbcTradeVO.InnerCompareTo::getGrade));
                                List<BbcTradeListVO.InnerGoodsCompareTo> list = new ArrayList<>();
                                int num = 0;
                                for (int i = 0; i < innerCompareTos.size(); i++) {
                                    num++;
                                    list.add(new BbcTradeListVO.InnerGoodsCompareTo(innerCompareTos.get(i).getId(), num));
                                }
                                for (String s : dto.getGoodsIds()) {
                                    for (BbcTradeListVO.InnerGoodsCompareTo string : list) {
                                        if (!s.equals(string.getId())) {
                                            list.add(new BbcTradeListVO.InnerGoodsCompareTo(s, num++));
                                            break;
                                        }
                                    }
                                }
                                return list;
                            } else if (dto.getCompareToMode() == 20) {
                                //评分降序
                                innerCompareTos.sort(Comparator.comparing(BbcTradeVO.InnerCompareTo::getGrade).reversed());
                                List<BbcTradeListVO.InnerGoodsCompareTo> list = new ArrayList<>();
                                int num = 0;
                                for (int i = 0; i < innerCompareTos.size(); i++) {
                                    num++;
                                    list.add(new BbcTradeListVO.InnerGoodsCompareTo(innerCompareTos.get(i).getId(), num));
                                }
                                for (String s : dto.getGoodsIds()) {
                                    for (BbcTradeListVO.InnerGoodsCompareTo string : list) {
                                        if (!s.equals(string.getId())) {
                                            list.add(new BbcTradeListVO.InnerGoodsCompareTo(s, num++));
                                            break;
                                        }
                                    }
                                }
                                return list;
                            }
                        }
                    } else {
                        List<BbcTradeListVO.InnerGoodsCompareTo> list = new ArrayList<>();
                        int num = 0;
                        for (String s : dto.getGoodsIds()) {
                            num++;
                            list.add(new BbcTradeListVO.InnerGoodsCompareTo(s, num));
                        }
                        return list;
                    }

                } else if (dto.getCompareToType() == 20) {
                    //销量
                    QueryWrapper<BbcTradeDTO.innerCommpareTo> query = MybatisPlusUtil.query();
                    query.and(i -> i.in("goods_id", dto.getGoodsIds()));
                    List<BbcTradeVO.InnerCompareToCount> innerCompareToCounts = iTradeCommentRepository.selectBbcCompareToCount(query);
                    if (ObjectUtils.isNotEmpty(innerCompareToCounts)) {
                        if (dto.getCompareToMode() == 10) {
                            //销量升序
                            innerCompareToCounts.sort(Comparator.comparing(BbcTradeVO.InnerCompareToCount::getCount));
                            List<BbcTradeListVO.InnerGoodsCompareTo> list = new ArrayList<>();
                            int num = 0;
                            for (int i = 0; i < innerCompareToCounts.size(); i++) {
                                num++;
                                list.add(new BbcTradeListVO.InnerGoodsCompareTo(innerCompareToCounts.get(i).getId(), num));
                            }
                            if (ObjectUtils.isNotEmpty(list)) {
                                for (String s : dto.getGoodsIds()) {
                                    for (BbcTradeListVO.InnerGoodsCompareTo string : list) {
                                        if (!s.equals(string.getId())) {
                                            list.add(new BbcTradeListVO.InnerGoodsCompareTo(s, num++));
                                            break;
                                        }
                                    }
                                }
                            } else {
                                List<BbcTradeListVO.InnerGoodsCompareTo> list1 = new ArrayList<>();
                                int num1 = 0;
                                for (String s : dto.getGoodsIds()) {
                                    num++;
                                    list1.add(new BbcTradeListVO.InnerGoodsCompareTo(s, num));
                                }
                                return list1;
                            }
                            return list;
                        } else if (dto.getCompareToMode() == 20) {
                            //销量降序
                            innerCompareToCounts.sort(Comparator.comparing(BbcTradeVO.InnerCompareToCount::getCount).reversed());
                            List<BbcTradeListVO.InnerGoodsCompareTo> list = new ArrayList<>();
                            int num = 0;
                            for (int i = 0; i < innerCompareToCounts.size(); i++) {
                                num++;
                                list.add(new BbcTradeListVO.InnerGoodsCompareTo(innerCompareToCounts.get(i).getId(), num));
                            }
                            if (ObjectUtils.isEmpty(list)) {
                                List<BbcTradeListVO.InnerGoodsCompareTo> list1 = new ArrayList<>();
                                int num1 = 0;
                                for (String s : dto.getGoodsIds()) {
                                    num++;
                                    list1.add(new BbcTradeListVO.InnerGoodsCompareTo(s, num));
                                }
                                return list1;
                            }
                            for (String s : dto.getGoodsIds()) {
                                for (BbcTradeListVO.InnerGoodsCompareTo string : list) {
                                    if (!s.equals(string.getId())) {
                                        list.add(new BbcTradeListVO.InnerGoodsCompareTo(s, num++));
                                        break;
                                    }
                                }
                            }
                            return list;
                        }
                    }
                }
            }
        }
        return new ArrayList<>();
    }

    @Override
    public PageData<BbcTradeListVO.PageData> myUserCard(BbcTradeQTO.UserCardQTO qto) {
        QueryWrapper<BbcTradeQTO.UserCardQTO> query = MybatisPlusUtil.query();
        IPage<BbcTradeListVO.PageData> pager = MybatisPlusUtil.pager(qto);
        query.and(i -> i.eq("td.`user_id`", qto.getJwtUserId()));
        if (ObjectUtils.isNotEmpty(qto.getState())) {
            switch (qto.getState()) {
                case 10:
                    query.and(i -> i.eq("td.`state`", MarketPtCardStatusEnum.未使用.getCode()));
                    break;
                case 20:
                    query.and(i -> i.eq("td.`state`", MarketPtCardStatusEnum.已使用.getCode()));
                    break;
                case 30:
                    query.and(i -> i.lt("td.`valid_end_time`", LocalDateTime.now()));
                    break;
            }
        }
        if (ObjectUtils.isNotEmpty(qto.getExpirationTime())) {
            switch (qto.getExpirationTime()) {
                case 10:
                    query.orderByAsc("td.`valid_end_time`");
                case 20:
                    query.orderByDesc("td.`valid_end_time`");
            }
        } else if (ObjectUtils.isNotEmpty(qto.getPreferentialAmount())) {
            switch (qto.getPreferentialAmount()) {
                case 10:
                    query.orderByAsc("td.`cut_price`");
                case 20:
                    query.orderByDesc("td.`cut_price`");
            }
        }
        repository.selectBbcListPage(pager, query);
        if (ObjectUtils.isNotEmpty(query) || ObjectUtils.isNotEmpty(pager)) {
            return MybatisPlusUtil.toPageData(qto, BbcTradeListVO.PageData.class, pager);
        }

        return new PageData<>();
    }

    @Override
    public List<BbcTradeListVO.UseCard> useCard(BbcTradeDTO.UseCard dto) {
        //查询会员领取的所有优惠卷
        QueryWrapper<BbcTradeDTO.UseCard> query = MybatisPlusUtil.query();
        query.and(i -> i.eq("td.`user_id`", dto.getJwtUserId()));
        query.and(i -> i.gt("td.`valid_end_time`", LocalDateTime.now()).lt("td.`valid_start_time`", LocalDateTime.now()));
        if (StringUtils.isEmpty(dto.getShopId())) {
            throw new BusinessException("请传入shopId");
        }
        query.and(i -> i.eq("td.`shop_id`", dto.getShopId()));
        List<BbcTradeListVO.UseCard> useCards = iMarketMerchantCardUsersRepository.selectBbcUseCard(query);
        List<String> cardId = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(useCards)) {
            for (BbcTradeListVO.UseCard useCard : useCards) {
                QueryWrapper<MarketMerchantCardGoods> query1 = MybatisPlusUtil.query();
                query1.and(i -> i.eq(ObjectUtils.isNotEmpty(useCard.getCardId()), "card_id", cardId));
                List<MarketMerchantCardGoods> list = iMarketMerchantCardGoodsRepository.list(query1);
                BigDecimal total = new BigDecimal(0);
                if (ObjectUtils.isNotEmpty(list)) {
                    for (MarketMerchantCardGoods marketMerchantCardGoods : list) {
                        for (BbcTradeDTO.UseCardGoods useCardGoods : dto.getGoodsInfo()) {
                            if (marketMerchantCardGoods.getGoodsId().equals(useCardGoods.getGoodsId())) {
                                total.add(useCardGoods.getTotalAmount());
                            }
                        }

                    }
                }
                if (total.compareTo(useCard.getToPrice()) == -1) {
                    useCard.setIsHide(10);
                }
            }
        }

        //查询所有优惠卷对应的商品


        return useCards;
    }

    @Override
    public void offlinePay(BbcTradeDTO.OfflinePayDTO dto) {
        //根据订单ID查询数据
        Trade trade = tradeRepository.getById(dto.getId());
        if (ObjectUtils.isEmpty(trade)) {
            throw new BusinessException("无订单数据");
        } else if (trade.getTradeState().intValue() == TradeStateEnum.待发货.getCode()) {
            throw new BusinessException("订单已支付");
        } else if (trade.getTradeState().intValue() != TradeStateEnum.待支付.getCode()) {
            throw new BusinessException("请退出登录后重试");
        }
        //修改支付主表
        QueryWrapper<TradePay> query = MybatisPlusUtil.query();
        query.and(i -> i.eq("trade_id", trade.getId()));
        TradePay tradePay = tradePayRepository.getOne(query);
        if (ObjectUtils.isNotEmpty(tradePay)) {
            tradePay.setPayState(TradePayStateEnum.待确认.getCode());
        }
        //存线下付款表
        saveTradeOffline(dto, trade, tradePay);

    }

    @Override
    public int innerMonthSaleNum(String goodsId) {
        return tradeMapper.getCurrentMonthSaleNum(goodsId);
    }

    @Override
    public Integer myMerchantCard(BaseDTO dto) {
        QueryWrapper<MarketMerchantCardUsers> query = MybatisPlusUtil.query();
        query.and(i -> i.eq("user_id", dto.getJwtUserId()));
        int count = iMarketMerchantCardUsersRepository.count(query);
        return count;
    }

    @Override
    public void modifyOrderAddress(BbcTradeDTO.ModifyOrderAddressDTO dto) {
        tradeMapper.modifyOrderAddress(dto);
    }

    @Override
    public Integer getExchangeQuantity(String id) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", id);
        wrapper.eq("trade_state", TradeStateEnum.已完成.getCode());
        wrapper.eq("flag", false);
        wrapper.eq("goods_source_type", 2);
        return tradeMapper.selectCount(wrapper);
    }

    @Override
    public Integer getSaleQuantity(String id) {
        return tradeMapper.getSaleQuantity(id);
    }

    private TradePayOffline saveTradeOffline(BbcTradeDTO.OfflinePayDTO dto, Trade trade, TradePay tradePay) {
        TradePayOffline tradePayOffline = new TradePayOffline();
        SettingsReceiptVO.DetailVO detailVO = iSettingsReceiptRpc.detailSettingsReceipt(dto);
        tradePayOffline.setTradeId(trade.getId()).
                setUserName(dto.getJwtUserName()).
                setPayId(tradePay.getId()).setPayOrder(null).
                setPayAmount(dto.getTransferAmount()).
                setAccountName(detailVO.getName()).
                setAccountNumber(detailVO.getNumber()).
                setBankName(detailVO.getBank()).
                setPayRemark(dto.getTransferRemarks()).
                setVerifyState(TradePayOfficeEnum.待确认.getCode());
        tradePayOfflineRepository.save(tradePayOffline);
        for (String img : dto.getTransImage()) {
            TradePayOfflineImg tradePayOfflineImg = new TradePayOfflineImg();
            tradePayOfflineImg.setOfflineId(tradePayOffline.getTradeId()).setOfflineImg(img);
            iTradePayOfflineImgRepository.save(tradePayOfflineImg);
        }
        return tradePayOffline;
    }


    /**
     * 填充skuId/购买数量
     *
     * @param tradeId
     * @param goodsItemList
     */
    private void fillChangeStockItem(String tradeId, List<CommonStockDTO.InnerChangeStockItem> goodsItemList) {
        QueryWrapper<TradeGoods> tradeGoodsQueryWrapper = new QueryWrapper<>();
        tradeGoodsQueryWrapper.eq("trade_id", tradeId);
        List<TradeGoods> tradeGoodsList = tradeGoodsRepository.list(tradeGoodsQueryWrapper);
        for (TradeGoods tradeGoods : tradeGoodsList) {
            CommonStockDTO.InnerChangeStockItem innerChangeStockItem = new CommonStockDTO.InnerChangeStockItem();
            innerChangeStockItem.setSkuId(tradeGoods.getSkuId());
            innerChangeStockItem.setQuantity(tradeGoods.getQuantity());
            goodsItemList.add(innerChangeStockItem);
        }
    }


    /**
     * 组装TradeVO、tradeGoodsVO数据
     *
     * @param tradeVO
     */
    private void fillTradeVO(BbcTradeListVO.tradeVO tradeVO) {
        QueryWrapper<TradeGoods> tradeGoodsQueryWrapper = new QueryWrapper<>();
        tradeGoodsQueryWrapper.eq("trade_id", tradeVO.getId());
        List<TradeGoods> tradeGoodsList = tradeGoodsRepository.list(tradeGoodsQueryWrapper);
        List<BbcTradeListVO.TradeGoodsVO> tradeGoodsVOS = new ArrayList<>();
        Integer quantity = 0;
        BbcGoodsInfoVO.DetailVO goodsDetail = new BbcGoodsInfoVO.DetailVO();
        for (TradeGoods tradeGoods : tradeGoodsList) {
            BbcTradeListVO.TradeGoodsVO tradeGoodsVO = new BbcTradeListVO.TradeGoodsVO();
            BeanUtils.copyProperties(tradeGoods, tradeGoodsVO);
            tradeGoodsVO.setCouponAmount(tradeGoods.getDiscountAmount());
            tradeGoodsVO.setCouponPointAmount(tradeGoods.getDiscountPointAmount());
            String goodsId = tradeGoods.getGoodsId();
            goodsDetail = iBbcGoodsInfoRpc.detailGoodsInfo(new BbcGoodsInfoDTO.IdDTO(goodsId));
            tradeGoodsVO.setExchangeType(goodsDetail.getExchangeType());
            if (tradeGoods.getQuantity() != null)
                quantity = quantity + tradeGoods.getQuantity();

            //hanly 如果支付状态为待支付，根据时间判断，超过，关闭订单 todo 没有已关闭的状态 定时
            if (tradeVO.getTradeState().equals(TradeStateEnum.待支付.getCode())) {
                String payDeadline = tradeVO.getCreateTime().plusMinutes(Common.PAYMENT_TIME_OUT).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                String now = DateUtil.getTime();
                if (DateUtil.compareDate(now, payDeadline)) {
                    tradeVO.setTradeState(TradeStateEnum.已取消.getCode());
                    Trade trade = tradeMapper.selectById(tradeVO.getId());
                    trade.setTradeState(tradeGoodsVO.getTradeState());
                    tradeMapper.updateById(trade);
                }
            }

            tradeGoodsVO.setTradeState(tradeVO.getTradeState());
            tradeGoodsVO.setRightsStateDetail(0);
            tradeGoodsVO.setRightsState(0);

            //判断当前订单有没有售后
            // QueryWrapper<TradeRightsGoods> tradeGoodsWrapper = new QueryWrapper<>();
            QueryWrapper<TradeRightsGoods> tradeGoodsWrapper = MybatisPlusUtil.query();
            tradeGoodsWrapper.eq("trade_goods_id", tradeGoods.getId());
            tradeGoodsWrapper.eq("is_revocation", false);
            TradeRightsGoods tradeRightsGoods = tradeRightsGoodsRepository.getOne(tradeGoodsWrapper);
            if (tradeRightsGoods != null) {//有售后
                String rightsId = tradeRightsGoods.getRightsId();
                TradeRights tradeRights = tradeRightsRepository.getById(rightsId);
                if (tradeRights != null) {
                    Integer state = tradeRights.getState();
                    Integer rightsType = tradeRights.getRightsType();
                    tradeGoodsVO.setRightsStateDetail(state);
                    tradeGoodsVO.setRightsType(rightsType);

                    //50,60,70,80,90,
                    if (state.equals(TradeRightsEndStateEnum.平台同意.getCode()) ||
                            state.equals(TradeRightsEndStateEnum.平台驳回.getCode()) ||
                            state.equals(TradeRightsEndStateEnum.换货完成.getCode()) ||
                            state.equals(TradeRightsEndStateEnum.商家确认收货并退款.getCode()) ||
                            state.equals(TradeRightsEndStateEnum.用户取消.getCode())) {
                        tradeGoodsVO.setRightsState(TradeRightsStateTradeEnum.完成售后.getCode());
                    } else {
                        tradeGoodsVO.setRightsState(TradeRightsStateTradeEnum.售后中.getCode());
                    }
                }
            }
            tradeGoodsVOS.add(tradeGoodsVO);

//            tradeGoodsVOS.
        }
        tradeVO.setQuantity(quantity);
        tradeVO.setTradeGoodsVOS(tradeGoodsVOS);
    }

    public EntOffLinePaymentResponse doPreOrder(BossClient client, Trade trade) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderTime = sdf.format(new Date());
        String merchantId = MerchantBaseEnum.merchant_hly_Id.getValue();
        EntOffLinePaymentRequest request = new EntOffLinePaymentRequest();
        request.setCharset("00");
        request.setVersion("1.0");
        request.setSignType("RSA");
        request.setService("EntOffLinePayment");
        log.info("notifyURL:" + notifyUrl);
        request.setOfflineNotifyUrl(notifyUrl);//交易结果通过后台通讯通知到这个url
        if (ObjectUtils.isNotEmpty(IpUtil.getHttpServletRequest())) {
            request.setClientIP(IpUtil.getRemoteHost(IpUtil.getHttpServletRequest()));
        } else {
            request.setClientIP("127.0.0.1");
        }
        request.setRequestId(UuidUtil.getUuid());
        request.setMerchantId(merchantId);
        request.setMerchantName("商户展示名称");
        request.setOrderId(trade.getTradeCode());
        log.info("trade.getTradeCode():" + trade.getTradeCode());
        request.setOrderTime(orderTime);
        //TODO 测试1分钱
        //request.setTotalAmount("1");
        request.setTotalAmount("" + trade.getTradeAmount().multiply(new BigDecimal("100")).intValue());
        request.setCurrency("CNY");
        request.setBackParam("保留数据1");
        request.setSplitType("1");
        request.setValidUnit("00");
        request.setValidNum("15");
        //WECHATAPP:微信APP支付ALIPAYAPP 支付宝app支付
        //request.setTradeType("WECHATAPP");

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailOrderId(trade.getChildTradeId());//子订单号 商户的子订单号，保证全局唯一，不能与订单号重复
        String childMerchantId = iCommonShopRpc.lakalaNoByShopId(trade.getShopId());
        if (com.baomidou.mybatisplus.core.toolkit.StringUtils.isNotBlank(childMerchantId)) {
            orderDetail.setRcvMerchantId(childMerchantId);//收款方商户编号-子商户
        }
        orderDetail.setOrderSeqNo("001");//订单序号 订单顺序号,只能上送数字
        //orderDetail.setOrderAmt("1");
        orderDetail.setOrderAmt("" + trade.getTradeAmount().multiply(new BigDecimal("100")).intValue());//子订单金额，以分为单位，等于子订单支付金额与子订单手续费金额和,如果是按比例分账，此信息域上送分账比例，如40.75%上送为4075，我方收到后做相应缩小
        orderDetail.setShareFee("0");//分账手续费，如果上送，将按照此手续费计算，商户平台不需要指定手续费时，默认为0
        orderDetail.setRcvMerchantIdName("收款方商户名称");//收款方商户名称-子商户
        orderDetail.setShowUrl("http://www.test.com/test/callback.jsp");//商品展示的url
        orderDetail.setProductId("编号01");//所购买商品的编号，只能是数字与字母
        orderDetail.setProductName("测试商品01");//所购买商品的名称
        orderDetail.setProductDesc("商品描述01");//所购买商品的描述

        List<OrderDetail> list = new ArrayList<OrderDetail>();
        list.add(orderDetail);

        request.setOrderDetail(list);
        log.info("doPreOrder end");
        return client.execute(request);
    }

    /**
     * 调用支付接口
     *
     * @param client
     * @param entOffLinePaymentResponse
     */
    private QRCodePaymentCommitResponse doPay(String openid, BossClient client, EntOffLinePaymentResponse entOffLinePaymentResponse) throws Exception {
        log.info("doPay start");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
        String requestId = sdf1.format(new Date());
        String creDt = sdf2.format(new Date());

        QRCodePaymentCommitRequest request = new QRCodePaymentCommitRequest();
        request.setCharset("00");
        request.setVersion("1.0");
        request.setSignType("RSA");
        request.setService("QRCodePaymentCommit");
        if (ObjectUtils.isNotEmpty(IpUtil.getHttpServletRequest())) {
            request.setClientIP(IpUtil.getRemoteHost(IpUtil.getHttpServletRequest()));
        } else {
            request.setClientIP("127.0.0.1");
        }
        request.setRequestId(requestId);
        request.setMerchantId(MerchantBaseEnum.merchant_hly_Id.getValue());
        request.setOrderId(entOffLinePaymentResponse.getOrderId());
        request.setCreDt(creDt);
        request.setToken(entOffLinePaymentResponse.getToken());//预下单创建时间下单后返回的订单token
        request.setPayChlTyp("WECHAT"); // WECHAT: 微信钱包；ALIPAY:支付宝
        /**
         * JSAPI:微信公众号
         * WECHATAPP:微信APP支付
         * MINIAPP:小程序
         * WXNATIVE:微信扫码
         * ALINATIVE:支付宝扫码
         * ALIAPP:支付宝APP
         **/
        request.setTradeType("MINIAPP");
        log.info("appId:" + appId);
        request.setAppId(appId); // 微信支付（扫码、APP、公众号、小程序）必传
        //request.setOpenId("ouyXz5OWt8cUakLNM-NXbDgg-4aM"); // 微信支付（扫码、APP、公众号、小程序）必传
        request.setOpenId(openid);
        return client.execute(request);
    }

    private QRCodePaymentCommitResponse goPayment(String openid, Trade trade, TradePay tradePay) {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(MerchantBaseEnum.merchant_hly_CertPath.getValue());
            try {
                ClassPathResource resource = new ClassPathResource(MerchantBaseEnum.merchant_hly_CertPath.getValue());
                InputStream fis = resource.getInputStream();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
            BossClient client = new BossClient(MerchantBaseEnum.merchant_hly_CertPath.getValue(), MerchantBaseEnum.merchant_hly_CertPass.getValue(), MerchantBaseEnum.serverUrl.getValue());
            //预下单
            EntOffLinePaymentResponse preOrderResponse = doPreOrder(client, trade);
            if (ObjectUtils.isEmpty(preOrderResponse)) {
                log.info("doPreOrder is null");
                return null;
            }
            if (preOrderResponse.isSuccess()) {
//                if(StringUtils.isEmpty(preOrderResponse.getToken())){
//                    preOrderResponse.setToken(tradePay.getToken());
//                }
                //支付
                QRCodePaymentCommitResponse payResponse = doPay(openid, client, preOrderResponse);
                if (ObjectUtils.isEmpty(payResponse)) {
                    log.info("doPay is null");
                    return null;
                }
                if (payResponse.isSuccess()) {
                    return payResponse;
                } else {
                    log.info("[doPay] 接口调用失败" + payResponse.getReturnMessage() + payResponse.getReturnCode());
                    return null;
                }
            } else {
                log.info("[doPreOrder] 接口调用失败:" + preOrderResponse.getReturnMessage() + preOrderResponse.getReturnCode());
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseData<Void> checkAndPointDoPay(CheckAndPointDoPayETO dto) {
        JSONObject responseJson = new JSONObject();

        /**
         * 判断用户积分帐户的钱够不够了
         */
        BbcUserVO.DetailVO detailVO = iBbcUserRpc.getUserInfoNoLogin(dto);
        if (detailVO == null || StringUtils.isEmpty(detailVO.getId())) {
            throw new BusinessException("无有效的用户信息");
        }
        /**
         * 看看我积分的钱够不够
         */
        Integer telecomsIntegral = detailVO.getTelecomsIntegral();

        List<String> tradeIds = dto.getTradeIds();
        if (CollectionUtil.isNotEmpty(tradeIds)) {
            //跟据id查询要支付的订单

            /**
             * 算下我一共要付的积分是多少
             */
            String idsStr = CollUtil.isNotEmpty(tradeIds) ? CollUtil.join(tradeIds, "','") : "-1";
            Integer totalTelecomsIntegral = tradeMapper.sumTradePointAmount(idsStr);
            if (totalTelecomsIntegral > telecomsIntegral) {
                throw new BusinessException("您的积分值不够！");
            }

            /**
             * 1、判断是不是需要走b2i
             * TODO YINGJUN
             */
            //sendCtcc(tradeIds);
            /**
             * 减积分
             */
            for (String tradeId : tradeIds) {
                Trade trade = tradeRepository.getById(tradeId);
                JSONObject ret = this.paySuccessTrade(trade);
                if (ret.getInteger("code") == 1) {
                    return ResponseData.fail(ret.toString());
                }
            }
            BbcUserCtccPointDTO.SubCtccPointDTO subCtccPointDTO = new BbcUserCtccPointDTO.SubCtccPointDTO(detailVO.getId(), totalTelecomsIntegral);
            bbcUserCtccPointRpc.subCtccPoint(subCtccPointDTO);

        } else {

            responseJson.put("code", 1);
            responseJson.put("result", "没有找到对应订单");
            return ResponseData.fail(responseJson.toString());
        }
        responseJson.put("result", "支付成功！");

        //从redis里面取出数据来
        /**
         * 把订单ID放到redis里面
         */
        JSONObject o = new JSONObject();
        if (redisUtil.get(PAYING_TRADE) != null) {
            String data = (String) redisUtil.get(PAYING_TRADE);
            o = JSONObject.parseObject(data);
            for (String tradeId : tradeIds) {
                o.remove(tradeId);
            }
        }
        redisUtil.set(PAYING_TRADE, o.toString());

        /**
         * 算下我一共要付的积分是多少
         */
        String idsStr = CollUtil.isNotEmpty(tradeIds) ? CollUtil.join(tradeIds, "','") : "-1";
        Integer totalTelecomsIntegral = tradeMapper.sumTradePointAmount(idsStr);
        // 查询还有多少积分
        Integer telecomsIntegral1 = iBbcUserRpc.getUserInfoNoLogin(dto).getTelecomsIntegral();
        // 发送短信
        iContactSMSService.reminderSuccessfulOrder(dto.getPhone(),o.toString(),totalTelecomsIntegral.toString(),telecomsIntegral1.toString());

        return ResponseData.success(responseJson.toString());
    }


    /**
     * 1、走b2i
     *
     * @param tradeIds
     */
    private void sendCtcc(List<String> tradeIds) {
        Boolean flag = true;
        if (tradeIds.size() == 1) {
            QueryWrapper<TradeGoods> tradeGoodsWrapper = new QueryWrapper<>();
            tradeGoodsWrapper.eq("trade_id", tradeIds.get(0));
            List<TradeGoods> tradeGoodsList = tradeGoodsRepository.list(tradeGoodsWrapper);

            if (tradeGoodsList.size() == 1) {
                TradeGoods tradeGoods = (TradeGoods) tradeGoodsList.get(0);
                String goodsId = tradeGoods.getGoodsId();

                //获取商户是否走B2I;
                BbcGoodsInfoVO.GoodsCtccApiVO goodsCtccApiVO = bbcGoodsInfoRpc.getCtccApiByGoodId(goodsId);
                if (goodsCtccApiVO != null && goodsCtccApiVO.getCtccApi().equals(GoodsCtccApiEnum.B2I.getCode())) {
                    //走B2I
                    String userId = tradeGoods.getUserId();
                    CommonUserVO.DetailVO userDetail = commonUserRpc.details(userId);
                    B2IDTO.SimpleBusinessAcceptCreateDTO simpleBusinessAcceptCreateDTO = new B2IDTO.SimpleBusinessAcceptCreateDTO();
                    simpleBusinessAcceptCreateDTO.setCodeNumber(userDetail.getPhone());
                    simpleBusinessAcceptCreateDTO.setLinkMan(userDetail.getRealName());
                    simpleBusinessAcceptCreateDTO.setLinkPhone(userDetail.getPhone());
                    simpleBusinessAcceptCreateDTO.setOutOrderSeq(tradeIds.get(0));

                    List<B2IDTO.SimpleBusinessAcceptCreateDTO.DisCountList> disCountList = new ArrayList<B2IDTO.SimpleBusinessAcceptCreateDTO.DisCountList>();
                    B2IDTO.SimpleBusinessAcceptCreateDTO.DisCountList disCount = new B2IDTO.SimpleBusinessAcceptCreateDTO.DisCountList();
                    disCount.setDiscountCode(goodsCtccApiVO.getCouponCode());
                    disCount.setDiscountName(goodsCtccApiVO.getCouponName());
                    disCount.setDiscountType("促销");
                    disCountList.add(disCount);
                    simpleBusinessAcceptCreateDTO.setDisCountList(disCountList);

                    List<B2IDTO.SimpleBusinessAcceptCreateDTO.Remarks3> remarks3 = new ArrayList<B2IDTO.SimpleBusinessAcceptCreateDTO.Remarks3>();
                    B2IDTO.SimpleBusinessAcceptCreateDTO.Remarks3 remarks = new B2IDTO.SimpleBusinessAcceptCreateDTO.Remarks3();
                    remarks.setDiscountCode(goodsCtccApiVO.getCouponCode());
                    remarks.setAttrId(goodsCtccApiVO.getAttrId());
                    remarks.setAttrValue(goodsCtccApiVO.getAttrValue());
                    remarks3.add(remarks);
                    simpleBusinessAcceptCreateDTO.setRemarks3(remarks3);


                    B2IVO.SimpleBusinessAcceptCreateVO simpleBusinessAcceptCreateVO = SimpleBusinessAccept.create(simpleBusinessAcceptCreateDTO);

                    if (!simpleBusinessAcceptCreateVO.getSuccess())
                        throw new BusinessException("调B2I接口出错！");

                    String data = simpleBusinessAcceptCreateVO.getData();

                    //修改订单号
                    QueryWrapper<TradePay> tradePayWrapper = new QueryWrapper<>();
                    tradePayWrapper.eq("trade_id", tradeIds.get(0));
                    TradePay tradePay = tradePayRepository.getOne(tradePayWrapper);
                    if (ObjectUtils.isNotEmpty(tradePay)) {
                        tradePay.setThirdCode(data);
                        tradePayRepository.saveOrUpdate(tradePay);
                    }

                    flag = false;
                }
            }
        }
        if (flag) {//走电信积分扣减服务
            for (String tradeId : tradeIds) {
                QueryWrapper<Trade> tradeWrapper = new QueryWrapper<>();
                tradeWrapper.eq("id", tradeIds);
                Trade trade = tradeRepository.getOne(tradeWrapper);

                String userId = trade.getUserId();

                BSS30DTO.PointRightsDealForKJDTO pointRightsDealForKJDTO = new BSS30DTO.PointRightsDealForKJDTO();
//        		pointRightsDealForKJDTO.
            }
        }
    }

    /**
     * 根据交易订单编号修改状态
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public JSONObject paySuccessTrade(Trade trade) {
        JSONObject responseJson = new JSONObject();
        if (ObjectUtils.isEmpty(trade)) {
            log.error("trade is null");
            responseJson.put("code", 1);
            responseJson.put("result", "没有找到对应订单");
            return responseJson;
        }

        QueryWrapper<TradePay> tradePayWrapper = new QueryWrapper<>();
        tradePayWrapper.eq("trade_id", trade.getId());
        TradePay tradePay = tradePayRepository.getOne(tradePayWrapper);
        if (ObjectUtils.isEmpty(tradePay)) {
            log.error("tradePay is null");
            responseJson.put("code", 1);
            responseJson.put("result", "没有找到对应支付订单");
            return responseJson;
        }
        if (trade.getTradeState().equals(TradeStateEnum.待发货.getCode()) &&
                tradePay.getPayState().equals(TradePayStateEnum.已支付.getCode())) {
            responseJson.put("code", 1);
            responseJson.put("result", "您的订单已支付！");
            return responseJson;
        }


        //修改订单状态//修改支付状态
        if (trade.getTradeState().equals(TradeStateEnum.待支付.getCode()) &&
                tradePay.getPayState().equals(TradePayStateEnum.待支付.getCode())) {

            Integer type = tradeRepository.getExchangeType(trade.getId());

            if (type == 20) {
                trade.setTradeState(TradeStateEnum.待发货.getCode());
            } else {
                trade.setTradeState(TradeStateEnum.已完成.getCode());
                String tUserPhone = commonUserRpc.details(trade.getUserId()).getPhone();
                tUserPhone = AESUtil.aesDecrypt(tUserPhone);
                trade.setRecvPhone(tUserPhone);
            }

            trade.setPayTime(LocalDateTime.now());
            tradeRepository.saveOrUpdate(trade);
            tradePay.setPayState(TradePayStateEnum.已支付.getCode());
            tradePayRepository.saveOrUpdate(tradePay);
//            if (trade.getDeliveryType().equals(TradeDeliveryTypeEnum.门店自提.getCode())){
//                if (StringUtils.isNotEmpty(trade.getRecvPhone())&&ObjectUtils.isNotEmpty(trade.getRecvPersonName())&&StringUtils.isNotEmpty(trade.getTradeCode())) {
//                    BbcShopVO.InnerDetailVO innerDetailVO = iBbcShopRpc.innerDetailShop(new BbcShopQTO.InnerShopQTO(trade.getShopId()));
//                    if (ObjectUtils.isNotEmpty(innerDetailVO)) {
//                        ismsService.sendPickUpSMSCode(trade.getRecvPhone(), trade.getTakeGoodsCode(), trade.getRecvPersonName(),innerDetailVO.getShopName());
//                        log.info("支付成功发送自提码：{}",trade.getTakeGoodsCode());
//                    }else {
//                        ismsService.sendPickUpSMSCode(trade.getRecvPhone(), trade.getTakeGoodsCode(), trade.getRecvPersonName());
//                        log.info("支付成功使用旧的自提码模版发送自提码：{}",trade.getTakeGoodsCode());
//                    }
//                }
//            }已经关联了类目不可以直接删除
//            commonMarketCardService.useCard(trade.getUserCardId(), trade.getUserId());

            //扣减优惠券
            String couponIds = trade.getCouponIds();
            List<String> couponIdList = new ArrayList<String>();
            if(StringUtils.isNotEmpty(couponIds)){
            	String[] coupons = couponIds.split(",");
            	if(coupons!=null&&coupons.length>0){
            		for(int i=0;i<coupons.length;i++){
            			String couponId = coupons[i];
            			couponIdList.add(couponId);
            		}

            		bbcInUserCouponRpc.modifyUserCoupon(couponIdList, trade.getUserId(), UserCouponStatusEnum.已使用.getCode());
            	}
            }


            responseJson.put("code", 0);
            responseJson.put("result", "支付成功！");

            //判断有没有信天游商品
            QueryWrapper<TradeGoods> tradeGoodsQueryWrapper = new QueryWrapper<>();
            tradeGoodsQueryWrapper.eq("trade_id", trade.getId());
            List<TradeGoods> tradeGoodsList = tradeGoodsRepository.list(tradeGoodsQueryWrapper);
            if(CollectionUtil.isNotEmpty(tradeGoodsList)){
            	for(TradeGoods tradeGoods:tradeGoodsList){
            		String goodsId = tradeGoods.getGoodsId();
            		BbcGoodsInfoVO.DetailVO detailVO = bbcGoodsInfoRpc.detailGoodsInfo(new BbcGoodsInfoDTO.IdDTO(goodsId));
            		Integer travelskyCode = detailVO.getThirdProductId();
            		if(travelskyCode!=null&&travelskyCode>0){

            			String userId = trade.getUserId();
            			CommonUserVO.DetailVO userDetailVO = commonUserRpc.details(userId);
            			//调信天游接口
            			BbcTravelskyDTO.ETO dto = new BbcTravelskyDTO.ETO();
            			dto.setTradeGoodsId(tradeGoods.getId());
            			dto.setPhone(userDetailVO.getPhone());
            			travelskyOrderService.createOrder(dto);
            		}
            	}
            }
            return responseJson;
        }
        responseJson.put("code", 1);
        responseJson.put("result", TradePayResultStateEnum.FAILED.getRemark());
        return responseJson;
    }

    /**
     * 根据交易订单编号修改状态
     *
     * @param tradeCode
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String paySuccess(String tradeCode) {
        JSONObject responseJson = new JSONObject();
        QueryWrapper<Trade> tradeWrapper = new QueryWrapper<>();
        tradeWrapper.eq("trade_code", tradeCode);
        Trade trade = tradeRepository.getOne(tradeWrapper);
        if (ObjectUtils.isEmpty(trade)) {
            log.error("trade is null");
            responseJson.put("result", TradePayResultStateEnum.FAILED.getRemark());
            return responseJson.toString();
        }

        QueryWrapper<TradePay> tradePayWrapper = new QueryWrapper<>();
        tradePayWrapper.eq("trade_id", trade.getId());
        TradePay tradePay = tradePayRepository.getOne(tradePayWrapper);
        if (ObjectUtils.isEmpty(tradePay)) {
            log.error("tradePay is null");
            responseJson.put("result", TradePayResultStateEnum.FAILED.getRemark());
            return responseJson.toString();
        }

        if (trade.getTradeState().equals(TradeStateEnum.待发货.getCode()) &&
                tradePay.getPayState().equals(TradePayStateEnum.已支付.getCode())) {
            responseJson.put("result", TradePayResultStateEnum.SUCCESS.getRemark());
            return responseJson.toString();
        }
        //修改订单状态//修改支付状态
        if (trade.getTradeState().equals(TradeStateEnum.待支付.getCode()) &&
                tradePay.getPayState().equals(TradePayStateEnum.待支付.getCode())) {

            trade.setTradeState(TradeStateEnum.待发货.getCode());
            trade.setPayTime(LocalDateTime.now());
            tradeRepository.saveOrUpdate(trade);
            tradePay.setPayState(TradePayStateEnum.已支付.getCode());
            tradePayRepository.saveOrUpdate(tradePay);
            if (trade.getDeliveryType().equals(TradeDeliveryTypeEnum.门店自提.getCode())) {
                if (StringUtils.isNotEmpty(trade.getRecvPhone()) && ObjectUtils.isNotEmpty(trade.getRecvPersonName()) && StringUtils.isNotEmpty(trade.getTradeCode())) {
                    BbcShopVO.InnerDetailVO innerDetailVO = iBbcShopRpc.innerDetailShop(new BbcShopQTO.InnerShopQTO(trade.getShopId()));
                    if (ObjectUtils.isNotEmpty(innerDetailVO)) {
//                        ismsService.sendPickUpSMSCode(trade.getRecvPhone(), trade.getTakeGoodsCode(), trade.getRecvPersonName(), innerDetailVO.getShopName());
                        log.info("支付成功发送自提码：{}", trade.getTakeGoodsCode());
                    } else {
//                        ismsService.sendPickUpSMSCode(trade.getRecvPhone(), trade.getTakeGoodsCode(), trade.getRecvPersonName());
                        log.info("支付成功使用旧的自提码模版发送自提码：{}", trade.getTakeGoodsCode());
                    }
                }
            }
            commonMarketCardService.useCard(trade.getUserCardId(), trade.getUserId());
            responseJson.put("result", TradePayResultStateEnum.SUCCESS.getRemark());

            return responseJson.toString();
        }
        responseJson.put("result", TradePayResultStateEnum.FAILED.getRemark());
        return responseJson.toString();
    }

}
