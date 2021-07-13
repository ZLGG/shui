package com.gs.lshly.biz.support.trade.service.bbb.pc.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.*;
import com.gs.lshly.biz.support.trade.enums.MarketPtCardStatusEnum;
import com.gs.lshly.biz.support.trade.repository.*;
import com.gs.lshly.biz.support.trade.service.bbb.pc.IBbbPcTradeService;
import com.gs.lshly.biz.support.trade.service.bbc.IBbcMarketSettleService;
import com.gs.lshly.biz.support.trade.service.common.Impl.ICommonMarketCardServiceImpl;
import com.gs.lshly.biz.support.trade.utils.TradeUtils;
import com.gs.lshly.common.enums.*;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsInfoVO;
import com.gs.lshly.common.struct.bbb.pc.merchant.vo.BbbShopVO;
import com.gs.lshly.common.struct.bbb.pc.stock.dto.BbbStockAddressDTO;
import com.gs.lshly.common.struct.bbb.pc.stock.dto.BbbStockDeliveryDTO;
import com.gs.lshly.common.struct.bbb.pc.stock.vo.BbbStockAddressVO;
import com.gs.lshly.common.struct.bbb.pc.stock.vo.BbbStockDeliveryVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.*;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.BbbOrderQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbOrderVO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeListVO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeSettlementVO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserShoppingCarDTO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserFrequentGoodsV2DTO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserIntegralDTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserShoppingCarVO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.common.struct.bbc.merchant.qto.BbcShopQTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeCancelDTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeResultNotifyVO;
import com.gs.lshly.common.struct.common.CommonLogisticsCompanyVO;
import com.gs.lshly.common.struct.common.CommonStockDTO;
import com.gs.lshly.common.struct.common.CommonStockVO;
import com.gs.lshly.common.struct.common.CommonUserVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsReceiptVO;
import com.gs.lshly.common.utils.Base64;
import com.gs.lshly.common.utils.DateUtils;
import com.gs.lshly.common.utils.IpUtil;
import com.gs.lshly.common.utils.JsonUtils;
import com.gs.lshly.middleware.mq.aliyun.producerService.ProducerService;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.middleware.sms.ISMSService;
import com.gs.lshly.rpc.api.bbb.pc.commodity.IPCBbbGoodsInfoRpc;
import com.gs.lshly.rpc.api.bbb.pc.commodity.IPCBbbGoodsSkuRpc;
import com.gs.lshly.rpc.api.bbb.pc.merchant.IBbbShopRpc;
import com.gs.lshly.rpc.api.bbb.pc.stock.IBbbPcStockAddressRpc;
import com.gs.lshly.rpc.api.bbb.pc.stock.IBbbStockDeliveryRpc;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserIntegralRpc;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserRpc;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserShoppingCarRpc;
import com.gs.lshly.rpc.api.bbb.pc.user.IPCBbbUserFrequentV2Rpc;
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
import com.lakala.boss.api.utils.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Component
@Slf4j
public class BbbPcTradeServiceImpl implements IBbbPcTradeService {
    @Autowired
    private ITradeRepository tradeRepository;
    @Autowired
    private ITradeGoodsRepository tradeGoodsRepository;
    @Autowired
    private  ITradeDeliveryRepository tradeDeliveryRepository;
    @Autowired
    private ITradePayRepository tradePayRepository;
    @Autowired
    private ITradeCommentRepository iTradeCommentRepository;
    @Autowired
    private  ITradeCancelRepository tradeCancelRepository;
    @Autowired
    private ITradeRightsRepository iTradeRightsRepository;
    @Autowired
    private ITradeRightsGoodsRepository iTradeRightsGoodsRepository;
    @Autowired
    private ITradePayOfflineImgRepository iTradePayOfflineImgRepository;
    @Autowired
    private IMarketMerchantCardGoodsRepository iMarketMerchantCardGoodsRepository;
    @Autowired
    private IMarketMerchantCardRepository iMarketMerchantCardRepository;
    @Autowired
    private IMarketMerchantCardUsersRepository iMarketMerchantCardUsersRepository;
    @Autowired
    private ITradePayRepository iTradePayRepository;
//    @Autowired
//    private ISMSService ismsService;
    @Autowired
    private ITradePayOfflineRepository iTradePayOfflineRepository;
    @Autowired
    private ITradeInvoiceRepository iTradeInvoiceRepository;
    @Autowired
    private ICommonMarketCardServiceImpl commonMarketCardService;
    @DubboReference
    private ICommonShopRpc iCommonShopRpc;
    @Autowired
    private ProducerService producerService;
    @DubboReference
    private ICommonLogisticsCompanyRpc commonLogisticsCompanyRpc;
    @DubboReference
    private ICommonStockRpc commonStockRpc;
    @DubboReference
    private IBbbShopRpc iBbbShopRpc;
    @DubboReference
    private IPCBbbGoodsInfoRpc ipcBbbGoodsInfoRpc;
    @DubboReference
    private IBbbPcStockAddressRpc iBbbPcStockAddressRpc;
    @DubboReference
    private IBbbStockDeliveryRpc iBbbStockDeliveryRpc;
    @DubboReference
    private IBbbUserShoppingCarRpc iBbbUserShoppingCarRpc;
    @DubboReference
    private IBbbUserRpc iBbbUserRpc;
    @DubboReference
    private ISettingsReceiptRpc iSettingsReceiptRpc;
    @DubboReference
    private IBbbUserIntegralRpc iBbbUserIntegralRpc;
    @DubboReference
    private ICommonUserRpc iCommonUserRpc;
    @DubboReference
    private IPCBbbGoodsSkuRpc ipcBbbGoodsSkuRpc;

    @DubboReference
    private IPCBbbUserFrequentV2Rpc ipcBbbUserFrequentV2Rpc;

    private final ITradePayOfflineRepository tradePayOfflineRepository;

    @Value("${lakala.qrpay.notifyurl}")
    private String notifyUrl;

    @Value("${lakala.wxpay.appid}")
    private String appId;

    @Autowired
    private IBbcMarketSettleService marketSettleService;

    public BbbPcTradeServiceImpl(ITradeRepository tradeRepository, ITradeGoodsRepository tradeGoodsRepository,
                                 ITradePayRepository tradePayRepository, ITradePayOfflineRepository tradePayOfflineRepository, ITradeDeliveryRepository tradeDeliveryRepository, ITradeCancelRepository tradeCancelRepository) {
        this.tradeRepository = tradeRepository;
        this.tradeGoodsRepository = tradeGoodsRepository;
        this.tradePayRepository = tradePayRepository;
        this.tradePayOfflineRepository = tradePayOfflineRepository;
        this.tradeDeliveryRepository = tradeDeliveryRepository;
        this.tradeCancelRepository = tradeCancelRepository;
    }

    @Override
    public ResponseData<BbbTradeSettlementVO.ListVO> settlementVO(BbbTradeBuildDTO.cartIdsDTO dto) {
        //购物车id/skuid/数量
        List<BbbUserShoppingCarVO.InnerSimpleItem> itemList = new ArrayList<>();

        //skuid/数量
        List<CommonStockDTO.InnerSkuGoodsInfoItem> goodsItemList = new ArrayList<>();
        if(StringUtils.isNotEmpty(dto.getGoodsSkuId())){//立即购买
            CommonStockDTO.InnerSkuGoodsInfoItem innerSkuGoodsInfoItem = new CommonStockDTO.InnerSkuGoodsInfoItem(null,dto.getGoodsSkuId(),dto.getQuantity());
            goodsItemList.add(innerSkuGoodsInfoItem);

            BbbUserShoppingCarVO.InnerSimpleItem innerSimpleItem = new BbbUserShoppingCarVO.InnerSimpleItem();
            BeanUtils.copyProperties(innerSkuGoodsInfoItem,innerSimpleItem);
            itemList.add(innerSimpleItem);
        }else {
            //购物车购买 dto.getCartIds();

            //根据购物车id查询实体 //判断商品是否属于同一个店铺
            BbbUserShoppingCarDTO.InnerIdListDTO innerIdListDTO = new BbbUserShoppingCarDTO.InnerIdListDTO();
            innerIdListDTO.setIdList(dto.getCartIds());
            ResponseData<BbbUserShoppingCarVO.InnerSimpleVO> innerSimpleVOResponseData = iBbbUserShoppingCarRpc.innerSimpleShoppingCarlist(innerIdListDTO);
            if (ResponseCodeEnum.失败.getCode() == innerSimpleVOResponseData.getCode()) {
                throw new BusinessException(innerSimpleVOResponseData.getMessage());
            }
            BbbUserShoppingCarVO.InnerSimpleVO innerSimpleVO = innerSimpleVOResponseData.getData();
            if (ObjectUtils.isEmpty(innerSimpleVO)) {
                throw new BusinessException("无购物车数据");
            }
            //商品信息
            itemList = innerSimpleVO.getItemList();
            for (BbbUserShoppingCarVO.InnerSimpleItem innerSimpleItem : itemList) {
                goodsItemList.add(new CommonStockDTO.InnerSkuGoodsInfoItem(null, innerSimpleItem.getSkuId(), innerSimpleItem.getQuantity()));
            }
        }
        //StockCheckStateEnum
        //根据SKU ID数组检查库存
        CommonStockVO.InnerListCheckStockVO checkStockVO = checkStock(goodsItemList);
        //返回地址、商家、商品（图片、名称、规格、单价）、商品总金额、商品数量、运费
        BbbTradeSettlementVO.ListVO listVO = new BbbTradeSettlementVO.ListVO();
        //组装商家信息
        fillShop(checkStockVO.getShopId(), listVO);

        List<BbbTradeSettlementVO.ListVO.goodsInfoVO> goodsInfoVOS = new ArrayList<>();
        BigDecimal goodsAmount = BigDecimal.ZERO;//商品总金额
        Integer goodsCount = 0;//商品总数
        BigDecimal goodsTotalWights=BigDecimal.ZERO;

        //店铺商品
        for(BbbUserShoppingCarVO.InnerSimpleItem innerSimpleItem : itemList){
            //根据SKU ID获取商品信息（图片、商品名称、规格值、售价、状态）
            PCBbbGoodsInfoVO.InnerServiceVO innerServiceGoodsVO = ipcBbbGoodsInfoRpc.innerServiceVO(innerSimpleItem.getSkuId(),dto);
            log.info("PC结算-取价前：" + JsonUtils.toJson(innerServiceGoodsVO));
            if(ObjectUtils.isEmpty(innerServiceGoodsVO) || innerServiceGoodsVO.getGoodsId() == null){
                throw new BusinessException("无商品数据或已下架");
            }else if(!innerServiceGoodsVO.getGoodsState().equals(GoodsStateEnum.已上架.getCode())){
                throw new BusinessException("商品已下架");
            }
            goodsTotalWights=goodsTotalWights.add(innerServiceGoodsVO.getGoodsWeight().multiply(new BigDecimal(innerSimpleItem.getQuantity())));
            //此件商品库存状态
            Integer stockStatus=null;
            for (CommonStockVO.InnerCheckItem checkItemList: checkStockVO.getCheckItemList()){
                if (checkItemList.getSkuId().equals(innerSimpleItem.getSkuId())){
                    stockStatus=checkItemList.getCheckState();
                }
            }
            //获取批发价
            BigDecimal wholesalePrice = null;
            //计算阶梯价格
            BigDecimal goodsStepPrice = null;
            //获取价格
            BigDecimal salePrice =null;
            if (ObjectUtils.isNotEmpty(innerServiceGoodsVO)) {
                salePrice = innerServiceGoodsVO.getSalePrice();
                if (ObjectUtils.isNotEmpty(innerServiceGoodsVO.getWholesalePrice())) {
                    wholesalePrice = innerServiceGoodsVO.getWholesalePrice();
                }
                if (ObjectUtils.isNotEmpty(innerServiceGoodsVO.getStepPrice())) {
                    goodsStepPrice = StepPriceCalculation(innerServiceGoodsVO.getStepPrice(), innerServiceGoodsVO.getSalePrice(), innerSimpleItem.getQuantity());
                }
            }
            //组装商品信息
            BbbTradeSettlementVO.ListVO.goodsInfoVO goodsInfoVO = fillGoodsInfoVO(innerSimpleItem.getId(),innerSimpleItem.getQuantity(), innerServiceGoodsVO,stockStatus);
            goodsInfoVOS.add(goodsInfoVO);
            BigDecimal productTotalPrice=BigDecimal.ZERO;
            BigDecimal productPrice = BigDecimal.ZERO;
            if (wholesalePrice != null) {
                productPrice = wholesalePrice;
                productTotalPrice = wholesalePrice.multiply(new BigDecimal(goodsInfoVO.getQuantity()));
            } else if (goodsStepPrice != null) {
                productPrice = goodsStepPrice;
                productTotalPrice = goodsStepPrice.multiply(new BigDecimal(goodsInfoVO.getQuantity()));
            } else if (salePrice != null) {
                productPrice = salePrice;
                productTotalPrice = salePrice.multiply(new BigDecimal(goodsInfoVO.getQuantity()));
            }
            goodsInfoVO.setSalePrice(productPrice);
            log.info("PC结算-各价格：批发价格【" + wholesalePrice + "】，阶梯价【" + goodsStepPrice + "】，零售价【" + innerServiceGoodsVO.getSalePrice() + "】");
            goodsAmount = goodsAmount.add(productTotalPrice);
            //计算商品总数
            goodsCount = goodsCount+goodsInfoVO.getQuantity();
        }
        listVO.setTotalWeight(goodsTotalWights);
        log.info("PC结算-取价后：" + JsonUtils.toJson(goodsInfoVOS));
        listVO.setGoodsInfoVOS(goodsInfoVOS);
        //组装收货地址信息
        fillAddress(dto, listVO);
        listVO.setGoodsAmount(goodsAmount);//商品总额
        listVO.setGoodsCount(goodsCount);//商品总数
        //营销结算
        try {
            log.info("开始结算:"+ JsonUtils.toJson(listVO));
            marketSettleService.settlementPCBBB(listVO, dto);
            log.info("结算结果:"+ JsonUtils.toJson(listVO));
        } catch (Exception e) {
            log.error("bbb-pc营销结算异常:" + e.getMessage(), e);
        }
        //订单总额=商品总额+运费
        BigDecimal delivery = listVO.getDeliveryAmount() != null ? listVO.getDeliveryAmount() : BigDecimal.ZERO;
        listVO.setTradeAmount(listVO.getGoodsAmount().add(delivery));

        return ResponseData.data(listVO);
    }

    private static BigDecimal StepPriceCalculation(List<PCBbbGoodsInfoVO.GoodsStepPriceVO> stepPrice, BigDecimal SalePrice,Integer quantity) {
        stepPrice.sort((PCBbbGoodsInfoVO.GoodsStepPriceVO o1, PCBbbGoodsInfoVO.GoodsStepPriceVO o2) -> o1.getStartNum()- o1.getStartNum());
        BigDecimal goodsStepPrice = SalePrice;
        if (ObjectUtils.isNotEmpty(stepPrice)){
            for (int i = 0; i < stepPrice.size(); i++) {
                if (quantity>=stepPrice.get(i).getStartNum()&&quantity<stepPrice.get(i).getEndNum()){
                    goodsStepPrice=stepPrice.get(i).getStepPrice();
                    break;
                }
                if (quantity > stepPrice.get(i).getEndNum()){
                    goodsStepPrice = stepPrice.get(i).getStepPrice();
                }
            }
        }
        return goodsStepPrice;
    }

    @Override
    @Transactional
    public synchronized ResponseData<BbbOrderDTO.IdDTO> orderSubmit(BbbTradeBuildDTO.DTO dto) {
        BbbStockAddressDTO.IdDTO idDTO = new BbbStockAddressDTO.IdDTO(dto.getAddressId());
        idDTO.setJwtUserId(dto.getJwtUserId());
        BbbStockAddressVO.ListVO addressVO = new BbbStockAddressVO.ListVO();

        //根据id查询地址
        addressVO = iBbbPcStockAddressRpc.innerDetailVO(idDTO);
        if(ObjectUtils.isEmpty(addressVO) || addressVO.getId() == null){
            throw new BusinessException("查询不到收货地址");
        }
        //校验库存
        List<CommonStockDTO.InnerSkuGoodsInfoItem> goodsItemList = new ArrayList<>();
        for (BbbTradeBuildDTO.DTO.ProductData productData:dto.getProductData()){
            goodsItemList.add(new CommonStockDTO.InnerSkuGoodsInfoItem(productData.getGoodsId(),productData.getGoodsSkuId(),productData.getQuantity()));
        }
        //根据SKU ID数组检查库存
        CommonStockVO.InnerListCheckStockVO innerListCheckStockVO = checkStock(goodsItemList);
        //购物车ID
        List<String> cartIdList = new ArrayList<>();
        //SKUID/购买数量
        List<CommonStockDTO.InnerChangeStockItem> subtractStockItems = new ArrayList<>();
        Set<BbbTradeGoodsDTO.ETO> tradeGoodsDTOSet = new HashSet<>();//交易商品数据
        BigDecimal shopProductAmount = BigDecimal.ZERO;//店铺商品总金额
        //店铺商品
        String shopId=null;
        if (ObjectUtils.isNotEmpty(dto.getShopId())){
            shopId=dto.getShopId();
        }
        for(BbbTradeBuildDTO.DTO.ProductData productData : dto.getProductData()){

            //查询商品、判断上下架)
            PCBbbGoodsInfoVO.InnerServiceVO innerServiceGoodsVO = ipcBbbGoodsInfoRpc.innerServiceVO(productData.getGoodsSkuId(),dto);
            if(ObjectUtils.isEmpty(innerServiceGoodsVO) || innerServiceGoodsVO.getGoodsId() == null){
                throw new BusinessException("无商品数据或已下架");
            }else if(!innerServiceGoodsVO.getGoodsState().equals(GoodsStateEnum.已上架.getCode())){
                throw new BusinessException("商品已下架");
            }
            if (ObjectUtils.isEmpty(shopId)){
               shopId= innerServiceGoodsVO.getShopId();
            }
            log.info("PC支付-取价前：" + JsonUtils.toJson(innerServiceGoodsVO));
            //获取批发价
            BigDecimal wholesalePrice = innerServiceGoodsVO.getWholesalePrice();
            //计算此商品的阶梯价
            BigDecimal goodsStepPrice = null;
            if (ObjectUtils.isNotEmpty(innerServiceGoodsVO.getStepPrice())){
               goodsStepPrice= StepPriceCalculation(innerServiceGoodsVO.getStepPrice(), innerServiceGoodsVO.getSalePrice(), productData.getQuantity());
            }
            //单品小计金额
            BigDecimal productPrice = BigDecimal.ZERO;
            BigDecimal productTotalPrice = BigDecimal.ZERO;
            if (wholesalePrice != null) {
                productPrice = wholesalePrice;
                productTotalPrice = wholesalePrice.multiply(new BigDecimal(productData.getQuantity()));
            } else if (goodsStepPrice != null) {
                productPrice = goodsStepPrice;
                productTotalPrice = goodsStepPrice.multiply(new BigDecimal(productData.getQuantity()));
            } else if (innerServiceGoodsVO.getSalePrice() != null) {
                productPrice = innerServiceGoodsVO.getSalePrice();
                productTotalPrice = innerServiceGoodsVO.getSalePrice().multiply(new BigDecimal(productData.getQuantity()));
            }
            log.info("PC支付-各价格：批发价格【" + wholesalePrice + "】，阶梯价【" + goodsStepPrice + "】，零售价【" + innerServiceGoodsVO.getSalePrice() + "】");
            shopProductAmount = shopProductAmount.add(productTotalPrice);
            CommonStockDTO.InnerChangeStockItem innerChangeStockItem = new CommonStockDTO.InnerChangeStockItem();
            innerChangeStockItem.setSkuId(productData.getGoodsSkuId());
            innerChangeStockItem.setQuantity(productData.getQuantity());
            subtractStockItems.add(innerChangeStockItem);

            //组装订单商品表信息
            BbbTradeGoodsDTO.ETO tradeGoodsDTO = fillTradeGoodsDTO(dto.getJwtUserId(),dto.getShopId(),productData.getQuantity(),innerServiceGoodsVO,productPrice);
            tradeGoodsDTO.setSalePrice(productPrice);
            tradeGoodsDTOSet.add(tradeGoodsDTO);

            //购物车ID
            if(productData.getCartId() != null){
                cartIdList.add(productData.getCartId());
            }

        }
        log.info("PC支付-取价后：" + JsonUtils.toJson(tradeGoodsDTOSet));
        dto.setShopProductAmount(shopProductAmount);
        //计算运费
        BigDecimal deliveryAmount = BigDecimal.ZERO; //运费
        if(dto.getDeliveryType().equals(TradeDeliveryTypeEnum.快递配送.getCode()) ||
                dto.getDeliveryType().equals(TradeDeliveryTypeEnum.门店配送.getCode())){
            deliveryAmount = getDeliveryAmount(dto.getShopId(),dto.getProductData(),dto.getDeliveryType(),addressVO.getId());
        }
        dto.setDeliveryAmount(deliveryAmount);
        //营销结算
        try {
            marketSettleService.settlementPCBBB(tradeGoodsDTOSet, dto);
        }catch (Exception e){
            log.error("bbb-pc营销结算异常:" + e.getMessage(), e);
        }
        //创建订单信息
        Trade trade = saveTrade(dto, addressVO, shopProductAmount.subtract(dto.getShopProductAmount()), dto.getDeliveryAmount(),shopId);
        try{
            producerService.sendHttpMessage(trade.getId());
            //HttpProducerUtil.sendMessage(trade.getId());
            //producerService.sendTimeMsg("TradeTimeOutCancel",trade.getId().getBytes(),"5e10ac22eb5348dc928e425c1fbf2841",0);
            log.info("向队列发送:BbbPcTradeServiceImpl");
        }catch (Exception e){
            log.info("推送队列失败：");
        }
        //创建交易订单商品信息
        saveTradeGoods(trade.getId(),tradeGoodsDTOSet);
        //创建支付信息
        saveTradePay(trade);
        //根据SKU ID、数量减库存
        boolean subtractStockResult = commonStockRpc.innerSubtractStockForTrade(subtractStockItems);
        if(!subtractStockResult){
            throw new BusinessException("库存不足");
        }
        //根据购物车ID删除记录
        if(ObjectUtils.isNotEmpty(cartIdList)){
            iBbbUserShoppingCarRpc.innerClearShopCarList(cartIdList);
        }
        return ResponseData.data(new BbbOrderDTO.IdDTO(trade.getId()));
    }


    @Override
    @Transactional
    public PageData<BbbTradeListVO.tradeVO>  tradeListPageData(BbbOrderQTO.TradeList qto) {
        QueryWrapper<BbbOrderQTO.TradeList> wrapper = new QueryWrapper<>();
        wrapper.and(i -> i.eq("t.`user_id`",qto.getJwtUserId()));
        wrapper.isNull("t.`is_hide`");
        if(ObjectUtils.isNotEmpty(qto.getKeyword())){
            wrapper.and(i -> i.
                    like("tg.`goods_name`",qto.getKeyword()).or().
                    eq("tg.`goods_no`",qto.getKeyword()).or().
                    eq("t.`trade_code`",qto.getKeyword()));
        }
        if(ObjectUtils.isNotEmpty(qto.getTradeState())){
            wrapper.and(i -> i.eq("t.`trade_state`",qto.getTradeState()));//交易状态,不传则查所有状态数据
        }
        //查询2b的订单列表
        wrapper.and(i->i.eq("t.`source_type`",TradeSourceTypeEnum._2B.getCode()));
        wrapper.orderByDesc("cdate");

        IPage<BbbTradeListVO.tradeVO> page = MybatisPlusUtil.pager(qto);

        tradeRepository.selectBbbTradeListPage(page,wrapper);

        List<BbbTradeListVO.tradeVO> voList = new ArrayList<>();
        for(BbbTradeListVO.tradeVO tradeVO : page.getRecords()){
            //填充发票信息
            if (StringUtils.isNotEmpty(tradeVO.getInvoiceId())){
                SetInvoice(tradeVO);
            }
            //查询店铺信息
            QueryWrapper<TradeRights> query = MybatisPlusUtil.query();
            query.and(i->i.eq("trade_id",tradeVO.getId()));
            query.last("limit 0,1");
            TradeRights one = iTradeRightsRepository.getOne(query);
            if(ObjectUtils.isNotEmpty(one)){
                BbbTradeListVO.tradeVO.Right right = new BbbTradeListVO.tradeVO.Right();
                right.setRightsState(one.getState());
                tradeVO.setRightsInfo(right);
                if (one.getState()==20){
                    //可以投书
                    tradeVO.setIsComplaint(10);
                }else {
                    tradeVO.setIsComplaint(20);
                }
            }else {
                tradeVO.setIsComplaint(20);
            }
            BbcShopQTO.InnerShopQTO innerShopQTO = new BbcShopQTO.InnerShopQTO();
            innerShopQTO.setShopId(tradeVO.getShopId());
            BbbShopVO.ComplexDetailVO complexDetailVO = iBbbShopRpc.inneComplexDetailShop(tradeVO.getShopId(), new BaseDTO().setJwtUserId(tradeVO.getUserId()));
            if (ObjectUtils.isNotEmpty(complexDetailVO)){
                tradeVO.setShopName(complexDetailVO.getShopName());
            }
            QueryWrapper<TradePayOffline> query1 = MybatisPlusUtil.query();
            query1.and(i->i.eq("trade_id",tradeVO.getId()));
            query1.last("limit 0,1");
            TradePayOffline one1 = tradePayOfflineRepository.getOne(query1);
            if (ObjectUtils.isNotEmpty(one1)){
                tradeVO.setIsOfflinePay(one1.getVerifyState());
            }
            //根据交易ID查询交易商品集合
            fillTradeVO(tradeVO);
            if (tradeVO.getPayType().equals(TradePayTypeEnum.线下支付.getCode())){
                //获取线下支付表
                QueryWrapper<TradePayOffline> query2 = MybatisPlusUtil.query();
                query2.and(i->i.eq("trade_id",tradeVO.getId()));
                TradePayOffline one2 = iTradePayOfflineRepository.getOne(query2);
                if (ObjectUtils.isNotEmpty(one2)){
                    tradeVO.setOfflineState(one2.getVerifyState()).setVerifyRemark(one2.getVerifyRemark());
                }
            }
            voList.add(tradeVO);
        }

        return new PageData<>(voList, qto.getPageNum(), qto.getPageSize(), page.getTotal());
    }

    private void SetInvoice(BbbTradeListVO.tradeVO tradeVO) {
        TradeInvoice byId = iTradeInvoiceRepository.getById(tradeVO.getInvoiceId());
        if (ObjectUtils.isNotEmpty(byId)){
            BbbTradeListVO.tradeVO.Invoice invoice = new BbbTradeListVO.tradeVO.Invoice();
            invoice.setInvoiceId(byId.getId()).
                    setInvoiceType(byId.getInvoiceType()).
                    setFirmName(byId.getFirmName()).
                    setTaxNumber(byId.getTaxNumber());
            tradeVO.setInvoiceInfo(invoice);
        }
    }

    @Override
    public ResponseData<BbbTradeListVO.tradeVO> orderDetail(BbbOrderDTO.IdDTO dto) {
        BbbTradeListVO.tradeVO tradeVO = new BbbTradeListVO.tradeVO();
        Trade trade = tradeRepository.getById(dto.getId());
        if(ObjectUtils.isEmpty(trade)){
            throw new BusinessException("无订单数据");
        }
        BeanUtils.copyProperties(trade,tradeVO);
        //填充发票信息
        if (StringUtils.isNotEmpty(tradeVO.getInvoiceId())){
            SetInvoice(tradeVO);
        }
        //填充商家信息
        fillShop(tradeVO);
        //填充商品集合
        fillTradeVO(tradeVO);
        //查询售后表
        QueryWrapper<TradeRights> query3 = MybatisPlusUtil.query();
        query3.and(i->i.eq("trade_id",tradeVO.getId()));
        query3.last("limit 0,1");
        TradeRights one3 = iTradeRightsRepository.getOne(query3);
        if (ObjectUtils.isNotEmpty(one3)){
            BbbTradeListVO.tradeVO.Right right = new BbbTradeListVO.tradeVO.Right();
            right.setRightsState(one3.getState());
            right.setRemark(one3.getRightsRemark());
            tradeVO.setRightsInfo(right);
        }
        //线下支付信息
        if(tradeVO.getTradeState().equals(TradeStateEnum.待支付.getCode())){
            if (tradeVO.getPayType().equals(TradePayTypeEnum.线下支付.getCode())){
                //可以线下支付
                tradeVO.setIsOfflinePay(10);
                //获取线下支付表
                QueryWrapper<TradePayOffline> query2 = MybatisPlusUtil.query();
                query2.and(i->i.eq("trade_id",tradeVO.getId()));
                TradePayOffline one2 = iTradePayOfflineRepository.getOne(query2);
                if (ObjectUtils.isNotEmpty(one2)){
                    tradeVO.setOfflineState(one2.getVerifyState()).setVerifyRemark(one2.getVerifyRemark());
                }
            }else {
                //不可以线下支付
                tradeVO.setIsOfflinePay(20);
            }
            tradeVO.setPayDeadline(tradeVO.getCreateTime().plusMinutes(Common.PAYMENT_TIME_OUT));
            tradeVO.setTradeDetailState(TradeDetailStateEnum.订单已生成等待您付款.getCode());
        }
        //取消订单信息
        if(tradeVO.getTradeState().equals(TradeStateEnum.已取消.getCode())){
            BbbTradeListVO.tradeVO.Cancel cancel = new BbbTradeListVO.tradeVO.Cancel();
            QueryWrapper<TradeCancel> query1 = MybatisPlusUtil.query();
            query1.and(i -> i.eq("trade_id", trade.getId()));
            List<TradeCancel> one1 = tradeCancelRepository.list(query1);
            if (ObjectUtils.isNotEmpty(one1)) {
                TradeCancel tradeCancel = one1.get(0);
                tradeVO.setReasonType(tradeCancel.getReasonType());
                tradeVO.setRemark(tradeCancel.getRemark());
                BeanUtils.copyProperties(tradeCancel,cancel);
                tradeVO.setCancelInfo(cancel);
            }
        }
        //订单状态:10:待支付,20:待发货,30:待收货,40:已完成,50:已取消
        if (tradeVO.getTradeState().equals(TradeStateEnum.待发货.getCode())){
            tradeVO.setTradeDetailState(TradeDetailStateEnum.您已付款成功等待商家发货.getCode());
        }
        if (tradeVO.getTradeState().equals(TradeStateEnum.待收货.getCode())){
            tradeVO.setTradeDetailState(TradeDetailStateEnum.商家已发货等待您确认收货.getCode());
        }
        if (tradeVO.getTradeState().equals(TradeStateEnum.已完成.getCode())){
            tradeVO.setTradeDetailState(TradeDetailStateEnum.订单完成.getCode());
        }
        if (tradeVO.getTradeState().equals(TradeStateEnum.已取消.getCode())){
            tradeVO.setTradeDetailState(TradeDetailStateEnum.订单取消.getCode());
        }
        //查询物流信息
        if(tradeVO.getDeliveryType().equals(TradeDeliveryTypeEnum.快递配送.getCode()) &&
                (tradeVO.getTradeState().equals(TradeStateEnum.待收货.getCode()) ||
                        tradeVO.getTradeState().equals(TradeStateEnum.已完成.getCode()))){//快递配送/已发货/已收货
            //填充物流信息
            fillLogisticsCompany(tradeVO);
        }
        //填充用户信息
        fillUserInfo(tradeVO);
        QueryWrapper<TradePay> query = MybatisPlusUtil.query();
        query.and(i->i.eq("trade_id",trade.getId()));
        List<TradePay> one = tradePayRepository.list(query);
        if (ObjectUtils.isNotEmpty(one)){
            TradePay tradePay = one.get(0);
            tradeVO.setPayState(tradePay.getPayState());
        }
        return ResponseData.data(tradeVO);
    }

    @Override
    @Transactional
    public ResponseData<Void> orderCancel(BbbTradeCancelDTO.CancelDTO dto) {
        TradeCancel tradeCancel = new TradeCancel();
        Trade trade = tradeRepository.getById(dto.getTradeId());
        if(ObjectUtils.isEmpty(trade)){
            throw new BusinessException("无订单信息");
        }
        if(trade.getTradeState().equals(TradeStateEnum.待支付.getCode())){//直接取消订单
            trade.setTradeState(TradeStateEnum.已取消.getCode());
            trade.setTimeoutCancel(TradeTimeOutCancelEnum.买家取消.getCode());

            fillTradeCancel(dto,tradeCancel,trade,null,TradeCancelStateEnum.完成.getCode(),TradeCancelRefundStateEnum.无需退款.getCode());

        }else if(trade.getTradeState().equals(TradeStateEnum.待发货.getCode())){
            //添加售后表，状态为
            trade.setTradeState(TradeStateEnum.已取消.getCode());
            trade.setTimeoutCancel(TradeTimeOutCancelEnum.买家取消.getCode());
            inset(trade,dto,tradeCancel);

        }else{
            throw new BusinessException("不允许取消订单");
        }
        tradeCancelRepository.save(tradeCancel);
        tradeRepository.saveOrUpdate(trade);
        //回库存
        cancelTradeReturnStock(trade.getId());

        return ResponseData.success();

    }

    private TradeRights inset(Trade trade,BbbTradeCancelDTO.CancelDTO dto,TradeCancel tradeCancel) {
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
                setRefundRemarks(dto.getRemark()).
                setApplyTime(LocalDateTime.now());
        iTradeRightsRepository.save(tradeRights);
        QueryWrapper<TradeGoods> query = MybatisPlusUtil.query();
        query.and(i->i.eq("trade_id",tradeRights.getTradeId()));
        List<TradeGoods> list = tradeGoodsRepository.list(query);
        if (ObjectUtils.isNotEmpty(list)){
            for (TradeGoods tradeGoods:list){
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
        QueryWrapper<TradePay> query1 = MybatisPlusUtil.query();
        query1.and(i->i.eq(StringUtils.isNotEmpty(trade.getId()),"trade_id",trade.getId()));
        TradePay one = iTradePayRepository.getOne(query1);
        String payId=null;
        if (ObjectUtils.isNotEmpty(one)){
            payId=one.getId();
        }
        fillTradeCancel(dto,tradeCancel,trade,payId,TradeCancelStateEnum.提交申请.getCode(),TradeCancelRefundStateEnum.等待审核.getCode());

        return tradeRights;
    }

    @Override
    @Transactional
    public ResponseData<Void> orderConfirmReceipt(BbbOrderDTO.IdDTO dto) {
        Trade trade = tradeRepository.getById(dto.getId());
        if(ObjectUtils.isEmpty(trade)){
            throw new BusinessException("无订单信息");
        }
        if(trade.getTradeState().intValue() != TradeStateEnum.待收货.getCode()){
            //如果订单状态不是"待收货"则不允许确认收货
            throw new BusinessException("不允许确认收货");
        }
        trade.setTradeState(TradeStateEnum.已完成.getCode());
        trade.setRecvTime(LocalDateTime.now());
        tradeRepository.saveOrUpdate(trade);
        PCBbbUserIntegralDTO.ETO eto = new PCBbbUserIntegralDTO.ETO();
        eto.setTradeAmount(trade.getTradeAmount());
        eto.setFromType(20);
        eto.setUserId(trade.getUserId());
        eto.setFromId(trade.getId());
        iBbbUserIntegralRpc.addUserTradeIntergral(eto);
        //推送Pos
        CommonUserVO.selectUserIdByShopIdVO selectUserIdByShopIdVO = iCommonUserRpc.selectUserIdByShopId(trade.getUserId());
        System.out.println("~~~~~~~~~~~~~___________---------"+selectUserIdByShopIdVO);
        if (ObjectUtils.isNotEmpty(selectUserIdByShopIdVO)){
            Set<BbbTradeGoodsDTO.ETO> tradeGoodsDTOSet = new HashSet<>();
            QueryWrapper<TradeGoods> query = MybatisPlusUtil.query();
            query.and(i->i.eq("trade_id",trade.getId()));
            List<TradeGoods> list = tradeGoodsRepository.list(query);
            if (ObjectUtils.isNotEmpty(list)){
                for (TradeGoods tradeGoods : list) {
                    BbbTradeGoodsDTO.ETO eto1 = new BbbTradeGoodsDTO.ETO();
                    BeanUtils.copyProperties(tradeGoods,eto1);
                    tradeGoodsDTOSet.add(eto1);
                }
            }
        }
        return ResponseData.success();

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseData orderPay(BbbPcTradePayBuildDTO.ETO dto) {

        //根据订单ID查询数据
        Trade trade = tradeRepository.getById(dto.getTradeId());
        if(ObjectUtils.isEmpty(trade)){
            throw new BusinessException("无订单数据");
        }else if(trade.getTradeState().intValue() == TradeStateEnum.待发货.getCode()){
            throw new BusinessException("订单已支付");
        }else if(trade.getTradeState().intValue() != TradeStateEnum.待支付.getCode()){
            throw new BusinessException("请退出登录后重试");
        }
        if(TradeUtils.checkPayTime(trade.getCreateTime())){
            //取消订单
            trade.setTradeState(TradeStateEnum.已取消.getCode());
            trade.setTimeoutCancel(TradeTimeOutCancelEnum.超时取消.getCode());
            tradeRepository.saveOrUpdate(trade);
            //回库存
            cancelTradeReturnStock(trade.getId());
            return ResponseData.fail("支付超时,请重新下单");
        }
        //0元订单支付
        if(trade.getTradeAmount().compareTo(BigDecimal.ZERO) <= 0){
            String payResult = paySuccess(trade.getTradeCode());
            return ResponseData.success(payResult);
        }
        //查询支付数据
        QueryWrapper<TradePay> tradePayWrapper = new QueryWrapper<>();
        tradePayWrapper.eq("trade_id",trade.getId());
        TradePay tradePay = tradePayRepository.getOne(tradePayWrapper);

        if(ObjectUtils.isNotEmpty(tradePay) && StringUtils.isNotEmpty(tradePay.getPayInfo())){
            log.info("1tradePay.getPayInfo():"+tradePay.getPayInfo());
            JSONObject json = JSON.parseObject(tradePay.getPayInfo());
            return ResponseData.data(json);
        }

        if(trade.getPayType().equals(TradePayTypeEnum.支付扫码.getCode())){
            log.info("tradePay.getToken():"+tradePay.getToken());
            String qrCode = goPayment(trade,dto.getPayType());
            return ResponseData.data(qrCode);
        }
        return ResponseData.fail("支付失败");
    }
    public String paySuccess(String tradeCode) {
        JSONObject responseJson = new JSONObject();
        QueryWrapper<Trade> tradeWrapper = new QueryWrapper<>();
        tradeWrapper.eq("trade_code",tradeCode);
        Trade trade = tradeRepository.getOne(tradeWrapper);
        if(ObjectUtils.isEmpty(trade)){
            log.error("trade is null");
            responseJson.put("result", TradePayResultStateEnum.FAILED.getRemark());
            return responseJson.toString();
        }

        QueryWrapper<TradePay> tradePayWrapper = new QueryWrapper<>();
        tradePayWrapper.eq("trade_id",trade.getId());
        TradePay tradePay = tradePayRepository.getOne(tradePayWrapper);
        if(ObjectUtils.isEmpty(tradePay)){
            log.error("tradePay is null");
            responseJson.put("result",TradePayResultStateEnum.FAILED.getRemark());
            return responseJson.toString();
        }

        if(trade.getTradeState().equals(TradeStateEnum.待发货.getCode()) &&
                tradePay.getPayState().equals(TradePayStateEnum.已支付.getCode())){
            responseJson.put("result",TradePayResultStateEnum.SUCCESS.getRemark());
            return responseJson.toString();
        }
        //修改订单状态//修改支付状态
        if(trade.getTradeState().equals(TradeStateEnum.待支付.getCode()) &&
                tradePay.getPayState().equals(TradePayStateEnum.待支付.getCode())){

            trade.setTradeState(TradeStateEnum.待发货.getCode());
            trade.setPayTime(LocalDateTime.now());
            tradeRepository.saveOrUpdate(trade);
            tradePay.setPayState(TradePayStateEnum.已支付.getCode());
            tradePayRepository.saveOrUpdate(tradePay);
            //创建常购清单
            this.addFrequent(null,trade.getId());
            if (trade.getDeliveryType().equals(TradeDeliveryTypeEnum.门店自提.getCode())){
                if (StringUtils.isNotEmpty(trade.getRecvPhone())&&ObjectUtils.isNotEmpty(trade.getRecvPersonName())&&StringUtils.isNotEmpty(trade.getTradeCode())) {
//                    ismsService.sendPickUpSMSCode( trade.getRecvPhone(),trade.getTakeGoodsCode(),trade.getRecvPersonName());
                }
            }
            commonMarketCardService.useCard(trade.getUserCardId(), trade.getUserId());
            responseJson.put("result",TradePayResultStateEnum.SUCCESS.getRemark());

            return responseJson.toString();
        }
        responseJson.put("result",TradePayResultStateEnum.FAILED.getRemark());
        return responseJson.toString();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String payNotify(BbcTradeResultNotifyVO.notifyVO notifyVO) {
        log.info("pc扫码-订单支付回调:" + notifyVO.toString());
        EntMergeOfflineResultNotify resultNotify = new EntMergeOfflineResultNotify();
        BeanUtils.copyProperties(notifyVO,resultNotify);
        if(notifyVO.getReturnMessage() != null){
            resultNotify.setReturnMessage(new String(Base64.decode(notifyVO.getReturnMessage())));
        }
        if(notifyVO.getBackParam() != null){
            resultNotify.setBackParam(new String(Base64.decode(notifyVO.getBackParam())));
        }
        if(notifyVO.getFailMsg() != null){
            resultNotify.setFailMsg(new String(Base64.decode(notifyVO.getFailMsg())));
        }

        try {
            BossClient client = new BossClient(MerchantBaseEnum.merchant_hly_CertPath.getValue(),
                    MerchantBaseEnum.merchant_hly_CertPass.getValue(), MerchantBaseEnum.serverUrl.getValue());

            JSONObject responseJson = new JSONObject();

            if (client.verify(resultNotify)) {
                System.out.println("验签成功，开始处理业务逻辑");
                //判断支付结果
                if(!resultNotify.getStatus().equals(TradePayResultStateEnum.SUCCESS.getRemark())){
                    log.error(resultNotify.getFailMsg());
                    responseJson.put("result",TradePayResultStateEnum.FAILED.getRemark());
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

    @Override
    @Transactional
    public void offlinePay(BbbOrderDTO.OfflinePayDTO dto) {
        //根据订单ID查询数据
        Trade trade = tradeRepository.getById(dto.getId());
        if(ObjectUtils.isEmpty(trade)){
            throw new BusinessException("无订单数据");
        }else if(trade.getTradeState().intValue() == TradeStateEnum.待发货.getCode()){
            throw new BusinessException("订单已支付");
        }else if(trade.getTradeState().intValue() != TradeStateEnum.待支付.getCode()){
            throw new BusinessException("请退出登录后重试");
        }
        QueryWrapper<TradePayOffline> query1 = MybatisPlusUtil.query();
        query1.and(i->i.eq("trade_id",trade.getId()));
        List<TradePayOffline> list = tradePayOfflineRepository.list(query1);
        //阻止重复提交
        if (list.size()>0){
            //修改表
            TradePayOffline tradePayOffline = list.get(0);
            if (ObjectUtils.isNotEmpty(tradePayOffline)){
                if (tradePayOffline.getVerifyState()==20){
                    //删除图片
                    QueryWrapper<TradePayOfflineImg> query = MybatisPlusUtil.query();
                    query.and(i->i.eq("offline_id",tradePayOffline.getId()));
                    iTradePayOfflineImgRepository.remove(query);
                    //修改信息
                    updateTradeOffline(dto,tradePayOffline);
                }
            }
        }else {
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


    }
    private void updateTradeOffline(BbbOrderDTO.OfflinePayDTO dto, TradePayOffline tradePayOffline) {
        tradePayOffline. setUserName(dto.getJwtUserName()).
                setPayAmount(dto.getTransferAmount()).
                setPayRemark(dto.getTransferRemarks()).
                setVerifyState(TradePayOfficeEnum.待确认.getCode());
        iTradePayOfflineRepository.updateById(tradePayOffline);
        for (String img : dto.getTransImage()){
            TradePayOfflineImg tradePayOfflineImg = new TradePayOfflineImg();
            tradePayOfflineImg.setOfflineId(tradePayOffline.getId()).setOfflineImg(img);
            iTradePayOfflineImgRepository.save(tradePayOfflineImg);
        }

    }

    @Override
    public ResponseData<BbbTradeListVO.OfflinePayVO> offlineDetail(BbbOrderDTO.IdDTO dto) {
        BbbTradeListVO.OfflinePayVO offlinePayVO = new BbbTradeListVO.OfflinePayVO();
        SettingsReceiptVO.DetailVO detailVO = iSettingsReceiptRpc.detailSettingsReceipt(dto);
        if (ObjectUtils.isNotEmpty(offlinePayVO)){
            BeanUtils.copyProperties(detailVO,offlinePayVO);
        }
        if (StringUtils.isNotEmpty(dto.getId())){
            Trade trade = tradeRepository.getById(dto.getId());
            if (ObjectUtils.isNotEmpty(trade)){
                offlinePayVO.setTrade_id(dto.getId()).setTradeAmount(trade.getTradeAmount());
            }
        }
        //获取线下支付表
        QueryWrapper<TradePayOffline> query = MybatisPlusUtil.query();
        query.and(i->i.eq("trade_id",dto.getId()));
        TradePayOffline one2 = iTradePayOfflineRepository.getOne(query);
        if (ObjectUtils.isNotEmpty(one2)){
            offlinePayVO.setPayName(one2.getAccountName()).
                    setPayCardNum(one2.getAccountNumber()).
                    setPayBlank(one2.getBankName()).
                    setTransferAmount(one2.getPayAmount()).setTransferRemarks(one2.getPayRemark());
            QueryWrapper<TradePayOfflineImg> query1 = MybatisPlusUtil.query();
            query1.and(i->i.eq("offline_id",one2.getId()));
            List<TradePayOfflineImg> list = iTradePayOfflineImgRepository.list(query1);
            List<String> strings = new ArrayList<>();
            if (ObjectUtils.isNotEmpty(list)){
                for (TradePayOfflineImg tradePayOfflineImg : list) {
                    strings.add(tradePayOfflineImg.getOfflineImg());
                }
            }
            offlinePayVO.setTransImage(strings);
        }

        return ResponseData.data(offlinePayVO);
    }

    @Override
    public List<BbbTradeListVO.tradeVO> latelyTrade(BbbOrderDTO.StateDTO dto) {
        QueryWrapper<BbbOrderQTO.TradeList> wrapper = new QueryWrapper<>();
        wrapper.and(i -> i.eq("t.`user_id`",dto.getJwtUserId()));
        wrapper.isNull("t.`is_hide`");
        wrapper.orderByDesc("cdate");
        if(ObjectUtils.isNotEmpty(dto.getState())){
            if (!dto.getState().equals(20)) {
                wrapper.and(i -> i.eq("t.`trade_state`", dto.getState()));//交易状态,不传则查所有状态数据
            }
        }
        BaseQTO baseQTO = new BaseQTO();
        baseQTO.setPageNum(0);
        baseQTO.setPageSize(5);
        IPage<BbbTradeListVO.tradeVO> page = MybatisPlusUtil.pager(baseQTO);
        wrapper.and(i->i.eq("t.`source_type`",TradeSourceTypeEnum._2B.getCode()));
        tradeRepository.selectBbbTradeListPage(page,wrapper);
        List<BbbTradeListVO.tradeVO> voList = new ArrayList<>();
        for(BbbTradeListVO.tradeVO tradeVO : page.getRecords()){
            QueryWrapper<TradeRights> query = MybatisPlusUtil.query();
            query.and(i->i.eq("trade_id",tradeVO.getId()));
            query.last("limit 0,1");
            TradeRights one = iTradeRightsRepository.getOne(query);
            if (ObjectUtils.isNotEmpty(one)){
                BbbTradeListVO.tradeVO.Right right = new BbbTradeListVO.tradeVO.Right();
                right.setRightsState(one.getState());
                tradeVO.setRightsInfo(right);
            }
            //查询店铺信息
            BbcShopQTO.InnerShopQTO innerShopQTO = new BbcShopQTO.InnerShopQTO();
            innerShopQTO.setShopId(tradeVO.getShopId());
            BbbShopVO.ComplexDetailVO complexDetailVO = iBbbShopRpc.inneComplexDetailShop(tradeVO.getShopId(), new BaseDTO().setJwtUserId(tradeVO.getUserId()));
            if (ObjectUtils.isNotEmpty(complexDetailVO)){
                tradeVO.setShopName(complexDetailVO.getShopName());
            }
            //根据交易ID查询交易商品集合
            fillTradeVO(tradeVO);
            if (tradeVO.getPayType().equals(TradePayTypeEnum.线下支付.getCode())){
                //获取线下支付表
                QueryWrapper<TradePayOffline> query2 = MybatisPlusUtil.query();
                query2.and(i->i.eq("trade_id",tradeVO.getId()));
                TradePayOffline one2 = iTradePayOfflineRepository.getOne(query2);
                if (ObjectUtils.isNotEmpty(one2)){
                    tradeVO.setOfflineState(one2.getVerifyState()).setVerifyRemark(one2.getVerifyRemark());
                }
            }
            voList.add(tradeVO);
        }

        return voList;
    }

    @Override
    public ResponseData<List<BbbTradeListVO.tradeVO>> newTrade() {
        BaseQTO baseQTO = new BaseQTO();
        baseQTO.setPageNum(0);
        baseQTO.setPageSize(10);
        IPage<BbbTradeListVO.tradeVO> page = MybatisPlusUtil.pager(baseQTO);
        QueryWrapper<BbbOrderQTO.TradeList> wrapper = new QueryWrapper<>();
        wrapper.isNull("t.`is_hide`");
        wrapper.and(i->i.eq("t.`source_type`",TradeSourceTypeEnum._2B.getCode()));
        wrapper.orderByDesc("cdate");
        tradeRepository.selectBbbTradeListPage(page,wrapper);
        if (ObjectUtils.isNotEmpty(page) || ObjectUtils.isNotEmpty(page.getRecords())){
            for (int i=0;i<page.getRecords().size();i++){
                if (StringUtils.isNotEmpty(page.getRecords().get(i).getUserId())){
                    BbbUserVO.InnerUserInfoVO innerUserInfoVO = iBbbUserRpc.innerGetUserInfo(page.getRecords().get(i).getUserId());
                    if (ObjectUtils.isNotEmpty(innerUserInfoVO)){
                        page.getRecords().get(i).setUserName(innerUserInfoVO.getUserName());
                    }
                }
            }
            return ResponseData.data(page.getRecords());
        }
        return null;
    }

    @Override
    public List<BbbTradeListVO.UseCard> useCard(BbbOrderDTO.UseCard dto) {
        //查询会员领取的所有优惠卷
        QueryWrapper<BbbOrderDTO.UseCard> query = MybatisPlusUtil.query();
        query.and(i->i.eq("td.`user_id`",dto.getJwtUserId()));
        query.and(i->i.gt("td.`valid_end_time`",LocalDateTime.now()).lt("td.`valid_start_time`",LocalDateTime.now()));
        if (StringUtils.isEmpty(dto.getShopId())){
            throw new BusinessException("请传入shopId");
        }
        query.and(i->i.eq("td.`shop_id`",dto.getShopId()));
        query.and(i->i.eq("td.`state`",MarketPtCardStatusEnum.未使用.getCode()));
        List<BbbTradeListVO.UseCard> useCards = iMarketMerchantCardUsersRepository.selectUseCard(query);
        BbbShopVO.ComplexDetailVO complexDetailVO = iBbbShopRpc.inneComplexDetailShop(dto.getShopId(), dto);
        if (ObjectUtils.isNotEmpty(useCards)) {
            for (BbbTradeListVO.UseCard useCard : useCards) {
                if (ObjectUtils.isNotEmpty(complexDetailVO)){
                    useCard.setShopName(complexDetailVO.getShopName());
                }
                QueryWrapper<MarketMerchantCardGoods> query1 = MybatisPlusUtil.query();
                query1.and(i->i.eq(ObjectUtils.isNotEmpty(useCard.getCardId()),"card_id",useCard.getCardId()));
                List<MarketMerchantCardGoods> list = iMarketMerchantCardGoodsRepository.list(query1);
                BigDecimal total=new BigDecimal(0);
                if (ObjectUtils.isNotEmpty(list)){
                    for (MarketMerchantCardGoods marketMerchantCardGoods : list) {
                        for (BbbOrderDTO.UseCardGoods useCardGoods : dto.getGoodsInfo()) {
                            if (marketMerchantCardGoods.getGoodsId().equals(useCardGoods.getGoodsId())){
                                System.out.println("~~~~~~~~~~~~~~~~"+useCardGoods.getTotalAmount());
                                total=total.add(useCardGoods.getTotalAmount());
                                System.out.println("Total___----:"+total);
                            }
                        }

                    }
                }
                System.out.println("总价："+total);
                if (total.compareTo(useCard.getToPrice())==-1){
                    useCard.setIsHide(10);
                }else {
                    useCard.setIsHide(20);
                }
            }
        }
        //查询所有优惠卷对应的商品
        return useCards;
    }

    @Override
    public List<BbbTradeListVO.InnerGoodsCompareTo> innerCommpareTo(BbbOrderDTO.innerCommpareTo dto) {
        if (dto.getCompareToType() == 10){
            dto.setCompareToType(20);
        }else if (dto.getCompareToType() == 20){
            dto.setCompareToType(10);
        }
        if (ObjectUtils.isNotEmpty(dto.getGoodsIds())){
            if (ObjectUtils.isNotEmpty(dto.getCompareToType())){
                if (dto.getCompareToType()==10){
                    //评分
                    QueryWrapper<BbbOrderDTO.innerCommpareTo> query = MybatisPlusUtil.query();
                    query.and(i->i.in("trade_goods_id",dto.getGoodsIds()));
                    query.groupBy("trade_goods_id");
                    List<BbbOrderVO.InnerCompareTo> innerCompareTos = iTradeCommentRepository.selectCompareTo(query);
                    if (ObjectUtils.isNotEmpty(innerCompareTos)){
                        if (ObjectUtils.isNotEmpty(dto.getCompareToMode())){
                            if (dto.getCompareToMode()==10){
                                //评分升序
                                innerCompareTos.sort(Comparator.comparing(BbbOrderVO.InnerCompareTo::getGrade));
                                List<BbbTradeListVO.InnerGoodsCompareTo> list=new ArrayList<>();
                                int num=0;
                                for (int i = 0; i < innerCompareTos.size(); i++) {
                                    num++;
                                    list.add(new BbbTradeListVO.InnerGoodsCompareTo(innerCompareTos.get(i).getId(),num));
                                }
                                for (String s : dto.getGoodsIds()) {
                                    for( BbbTradeListVO.InnerGoodsCompareTo string : list) {
                                        if (!s.equals(string.getId())){
                                            list.add(new BbbTradeListVO.InnerGoodsCompareTo(s,num++));
                                            break;
                                        }
                                    }
                                }
                                return list;
                            }else if (dto.getCompareToMode()==20){
                                //评分降序
                                innerCompareTos.sort(Comparator.comparing(BbbOrderVO.InnerCompareTo::getGrade).reversed());
                                List<BbbTradeListVO.InnerGoodsCompareTo> list=new ArrayList<>();
                                int num=0;
                                for (int i = 0; i < innerCompareTos.size(); i++) {
                                    num++;
                                    list.add(new BbbTradeListVO.InnerGoodsCompareTo(innerCompareTos.get(i).getId(),num));
                                }
                                for (String s : dto.getGoodsIds()) {
                                    for (BbbTradeListVO.InnerGoodsCompareTo string : list) {
                                        if (!s.equals(string.getId())){
                                            list.add(new BbbTradeListVO.InnerGoodsCompareTo(s,num++));
                                            break;
                                        }
                                    }
                                }
                                return list;
                            }
                        }
                    }else {
                        List<BbbTradeListVO.InnerGoodsCompareTo> list=new ArrayList<>();
                        int num=0;
                        for (String s : dto.getGoodsIds()) {
                            num++;
                            list.add(new BbbTradeListVO.InnerGoodsCompareTo(s,num));
                        }
                        return list;
                    }

                }else if (dto.getCompareToType()==20){
                    //销量
                    QueryWrapper<BbbOrderDTO.innerCommpareTo> query = MybatisPlusUtil.query();
                    query.and(i->i.in("goods_id",dto.getGoodsIds()));
                    List<BbbOrderVO.InnerCompareToCount> innerCompareToCounts = iTradeCommentRepository.selectCompareToCount(query);
                    if (ObjectUtils.isNotEmpty(innerCompareToCounts)) {
                        if (dto.getCompareToMode() == 10) {
                            //销量升序
                            innerCompareToCounts.sort(Comparator.comparing(BbbOrderVO.InnerCompareToCount::getCount));
                            List<BbbTradeListVO.InnerGoodsCompareTo> list=new ArrayList<>();
                            int num=0;
                            for (int i = 0; i < innerCompareToCounts.size(); i++) {
                                num++;
                                list.add(new BbbTradeListVO.InnerGoodsCompareTo(innerCompareToCounts.get(i).getId(),num));
                            }
                            if (ObjectUtils.isNotEmpty(list)) {
                                for (String s : dto.getGoodsIds()) {
                                    for (BbbTradeListVO.InnerGoodsCompareTo string : list) {
                                        if (!s.equals(string.getId())){
                                            list.add(new BbbTradeListVO.InnerGoodsCompareTo(s,num++));
                                            break;
                                        }
                                    }
                                }
                            }else {
                                List<BbbTradeListVO.InnerGoodsCompareTo> list1=new ArrayList<>();
                                int num1=0;
                                for (String s : dto.getGoodsIds()) {
                                    num++;
                                    list1.add(new BbbTradeListVO.InnerGoodsCompareTo(s,num));
                                }
                                return list1;
                            }
                            return list;
                        } else if (dto.getCompareToMode() == 20) {
                            //销量降序
                            innerCompareToCounts.sort(Comparator.comparing(BbbOrderVO.InnerCompareToCount::getCount).reversed());
                            List<BbbTradeListVO.InnerGoodsCompareTo> list=new ArrayList<>();
                            int num=0;
                            for (int i = 0; i < innerCompareToCounts.size(); i++) {
                                num++;
                                list.add(new BbbTradeListVO.InnerGoodsCompareTo(innerCompareToCounts.get(i).getId(),num));
                            }
                            if (ObjectUtils.isEmpty(list)){
                                List<BbbTradeListVO.InnerGoodsCompareTo> list1=new ArrayList<>();
                                int num1=0;
                                for (String s : dto.getGoodsIds()) {
                                    num++;
                                    list1.add(new BbbTradeListVO.InnerGoodsCompareTo(s,num));
                                }
                                return list1;
                            }
                            for (String s : dto.getGoodsIds()) {
                                for (BbbTradeListVO.InnerGoodsCompareTo string : list) {
                                    if (!s.equals(string.getId())){
                                        list.add(new BbbTradeListVO.InnerGoodsCompareTo(s,num++));
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
    public BbbTradeListVO.InnerGoodsScore innerShopScore(String shopId, String id) {
        System.out.println("shop:"+shopId);
        System.out.println("id:"+id);
        if (StringUtils.isEmpty(shopId) || StringUtils.isEmpty(id)){
           return new BbbTradeListVO.InnerGoodsScore();
        }
        QueryWrapper<Object> query = MybatisPlusUtil.query();
        query.and(i->i.eq("shop_id",shopId));
        QueryWrapper<Object> query1 = MybatisPlusUtil.query();
        query1.and(i->i.eq("id",id));
        BbbTradeListVO.InnerGoodsScore innerGoodsScore=tradeRepository.selectShopScore(query);
        BbbTradeListVO.InnerGoodsScore innerGoodsScore1=tradeRepository.selectGoodScore(query1);
        innerGoodsScore.setGoodsGrade(innerGoodsScore1.getGoodsGrade()).setCommentCount(innerGoodsScore1.getCommentCount());
        return innerGoodsScore;
    }

    private void addFrequent(List<TradeGoods> tradeGoodsList,String tradeId){
        //支付成功-把成功的订单商品信息->常购清单
        if(null == tradeGoodsList){
            QueryWrapper<TradeGoods> tradeGoodsQueryWrapper = MybatisPlusUtil.query();
            tradeGoodsQueryWrapper.in("trade_id",tradeId);
            tradeGoodsList = tradeGoodsRepository.list(tradeGoodsQueryWrapper);
        }
        if(ObjectUtils.isNotEmpty(tradeGoodsList)){
            PCBbbUserFrequentGoodsV2DTO.ETO frequentGoodsV2DTO = new PCBbbUserFrequentGoodsV2DTO.ETO();
            for(TradeGoods tradeGoods: tradeGoodsList){
                PCBbbUserFrequentGoodsV2DTO.FrequentItem oneItem = new PCBbbUserFrequentGoodsV2DTO.FrequentItem();
                oneItem.setGoodsId(tradeGoods.getGoodsId());
                oneItem.setSkuId(tradeGoods.getSkuId());
                oneItem.setQuantity(tradeGoods.getQuantity());
                oneItem.setShopId(tradeGoods.getShopId());
                oneItem.setMerchantId(tradeGoods.getMerchantId());
                if(StringUtils.isBlank(frequentGoodsV2DTO.getJwtUserId())){
                    frequentGoodsV2DTO.setJwtUserId(tradeGoods.getUserId());
                }
                frequentGoodsV2DTO.getFrequentItemList().add(oneItem);
            }
            ipcBbbUserFrequentV2Rpc.addMore(frequentGoodsV2DTO);
        }
    }


    private TradePayOffline saveTradeOffline(BbbOrderDTO.OfflinePayDTO dto, Trade trade, TradePay tradePay) {
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
        for (String img : dto.getTransImage()){
            TradePayOfflineImg tradePayOfflineImg = new TradePayOfflineImg();
            tradePayOfflineImg.setOfflineId(tradePayOffline.getId()).setOfflineImg(img);
            iTradePayOfflineImgRepository.save(tradePayOfflineImg);
        }
        return tradePayOffline;
    }


    @Override
    public boolean isFinishedPay(String tradeId,String userCard) {
        Trade trade = tradeRepository.getById(tradeId);
        if(ObjectUtils.isEmpty(trade)){
            throw new BusinessException("订单未找到");
        }
        if (trade.getTradeState().equals(TradeStateEnum.待支付.getCode())) {
            return false;
        }
        //如果使用了优惠卷，在这里把用户的优惠卷状态改为已使用，将商家优惠卷已使用的数量+1
        if (userCard!=null){
            MarketMerchantCardUsers byId = iMarketMerchantCardUsersRepository.getById(userCard);
            if (ObjectUtils.isNotEmpty(byId)){
                byId.setState(MarketPtCardStatusEnum.已使用.getCode());
                iMarketMerchantCardUsersRepository.updateById(byId);
                MarketMerchantCard marketMerchantCard = iMarketMerchantCardRepository.getById(byId.getCardId());
                marketMerchantCard.setUsedTotal(marketMerchantCard.getUsedTotal()+1);
                iMarketMerchantCardRepository.updateById(marketMerchantCard);
            }
        }
        return true;
    }

    private String goPayment(Trade trade,Integer payType) {
        try{
            //预下单
            EntOffLinePaymentResponse preOrderResponse = doPreOrder(trade,payType);
            if(ObjectUtils.isEmpty(preOrderResponse)){
                throw new BusinessException("预下单失败");
            }
            if (preOrderResponse.isSuccess()) {
                if(payType==10){
                    //微信扫码支付,将预下单返回的的H5JumpUrl作为二维码内容
                    String qrCode = preOrderResponse.getH5JumpUrl();
                    return new String(Base64.decode(qrCode));
                }else if(payType==20){
                    //支付宝扫码支付，预下单后再调用支付接口，使用返回的payinfo字段作为二维码内容
                    QRCodePaymentCommitResponse payResponse = doPay(preOrderResponse);
                    if(ObjectUtils.isNotEmpty(payResponse)){
                        if (payResponse.isSuccess()) {
                            return payResponse.getPayInfo();
                        }else{
                            log.info("[doPay] 接口调用失败"+payResponse.getReturnMessage()+payResponse.getReturnCode());
                        }
                    }else{
                        log.info("doPay is null");
                    }
                }
            }else{
                log.info("扫码支付预下单接口调用失败:" + preOrderResponse.getReturnMessage() + preOrderResponse.getReturnCode());
                throw new BusinessException(preOrderResponse.getReturnMessage());
            }
        }catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw new BusinessException(e.getMessage());
        }
        return null;
    }

    private void fillTradeCancel(BbcTradeCancelDTO.CancelDTO dto, TradeCancel tradeCancel, Trade trade, String tradePayId,Integer cancelState,Integer cancelRefundState) {
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

    private JSONObject payInfoToJSON(String payInfo) {
        String[] str = payInfo.split("\\|");
        JSONObject payInfoJson = new JSONObject(true);
        for(String s : str){
            payInfoJson.put(s.substring(0,s.indexOf("=")),s.substring(s.indexOf("=")+1));
        }
        return payInfoJson;
    }

    public EntOffLinePaymentResponse doPreOrder(Trade trade,Integer payType) throws Exception {

        String orderTime=DateUtils.fomatDate(new Date(),"yyyyMMddHHmmss");
        String merchantId = MerchantBaseEnum.merchant_hly_Id.getValue();
        EntOffLinePaymentRequest request = new EntOffLinePaymentRequest();
        request.setCharset("00");
        request.setVersion("1.0");

        request.setSignType("RSA");
        request.setService("EntOffLinePayment");
        log.info("notifyURL:" + notifyUrl);
        //交易结果通过后台通讯通知到这个url
        request.setOfflineNotifyUrl(notifyUrl);
        if(ObjectUtils.isNotEmpty(IpUtil.getHttpServletRequest())){
            request.setClientIP(IpUtil.getRemoteHost(IpUtil.getHttpServletRequest()));
        }else{
            request.setClientIP("127.0.0.1");
        }
        request.setRequestId(UuidUtil.getUuid());
        request.setMerchantId(merchantId);
        request.setMerchantName("陇上好粮油");
        request.setOrderId(trade.getTradeCode());
        log.info("trade.getTradeCode():"+trade.getTradeCode());
        request.setOrderTime(orderTime);
        request.setTotalAmount(""+trade.getTradeAmount().multiply(new BigDecimal("100")).intValue());
        //todo 测试金额1分
        //request.setTotalAmount("1");
        request.setCurrency("CNY");
        request.setBackParam("保留数据1");
        request.setSplitType("1");
        request.setValidUnit("00");
        request.setValidNum("15");
        //WECHATAPP:微信APP支付ALIPAYAPP 支付宝app支付
        //预下单环节不用传支付方式
//        if(10==payType){
//            request.setTradeType("WECHATAPP");
//        }else if(20==payType){
//            request.setTradeType("ALIPAYAPP");
//        }else{
//            return null;
//        }

        QueryWrapper<TradeGoods> wrapper=new QueryWrapper();
        wrapper.eq("trade_id",trade.getId());
        List<TradeGoods> goodsList=tradeGoodsRepository.list(wrapper);
        TradeGoods tradeGoods=null;
        if (ObjectUtils.isNotEmpty(goodsList)) {
            tradeGoods=goodsList.get(0);
        }
        List<OrderDetail> list = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        //订单序号 订单顺序号,只能上送数字
        orderDetail.setOrderSeqNo(101+"");
        //orderDetail.setDetailOrderId("1326831734460809218");
        //子订单金额，以分为单位，等于子订单支付金额与子订单手续费金额和,如果是按比例分账，此信息域上送分账比例，如40.75%上送为4075，我方收到后做相应缩小
        orderDetail.setOrderAmt(""+trade.getTradeAmount().multiply(new BigDecimal("100")).intValue());

        orderDetail.setDetailOrderId(trade.getChildTradeId());//子订单号 商户的子订单号，保证全局唯一，不能与订单号重复
        String childMerchantId = iCommonShopRpc.lakalaNoByShopId(trade.getShopId());
        System.out.println(childMerchantId+"~~~~~~~~~~~~~~~~~~~~~");
        if (com.baomidou.mybatisplus.core.toolkit.StringUtils.isNotBlank(childMerchantId)){
            System.out.println("获取到子商户号："+childMerchantId);
            orderDetail.setRcvMerchantId(childMerchantId);//收款方商户编号-子商户
        }
        //分账手续费，如果上送，将按照此手续费计算，商户平台不需要指定手续费时，默认为0
        orderDetail.setShareFee("0");
        //收款方商户名称-子商户
        orderDetail.setRcvMerchantIdName(tradeGoods.getShopName());
        //商品展示的url
        orderDetail.setShowUrl("");
        //所购买商品的编号，只能是数字与字母
        orderDetail.setProductId(tradeGoods.getGoodsId());
        //这里可允许的商品编号为5位数，暂时写死一个五位数
        orderDetail.setProductId("12345");
        //所购买商品的名称
        orderDetail.setProductName(tradeGoods.getGoodsName());
        //所购买商品的描述
        orderDetail.setProductDesc("");
        list.add(orderDetail);

//        for(int i=0;i<goodsList.size();i++){
//            TradeGoods tradeGoods=goodsList.get(i);
//            OrderDetail orderDetail = new OrderDetail();
//            //订单序号 订单顺序号,只能上送数字
//            orderDetail.setOrderSeqNo(i+101+"");
//            //子订单号 商户的子订单号，保证全局唯一，不能与订单号重复
//            orderDetail.setDetailOrderId(tradeGoods.getId());
//
//            //orderDetail.setDetailOrderId("1326831734460809218");
//
//            //子订单金额，以分为单位，等于子订单支付金额与子订单手续费金额和,如果是按比例分账，此信息域上送分账比例，如40.75%上送为4075，我方收到后做相应缩小
//            orderDetail.setOrderAmt("1");
//
//            //分账手续费，如果上送，将按照此手续费计算，商户平台不需要指定手续费时，默认为0
//            orderDetail.setShareFee("0");
//            //收款方商户编号-子商户
//            orderDetail.setRcvMerchantId(childMerchantId);
//            //收款方商户名称-子商户
//            orderDetail.setRcvMerchantIdName(tradeGoods.getShopName());
//            //商品展示的url
//            orderDetail.setShowUrl("");
//            //所购买商品的编号，只能是数字与字母
//            orderDetail.setProductId(tradeGoods.getGoodsId());
//            //这里可允许的商品编号为5位数，暂时写死一个五位数
//            orderDetail.setProductId("12345");
//            //所购买商品的名称
//            orderDetail.setProductName(tradeGoods.getGoodsName());
//            //所购买商品的描述
//            orderDetail.setProductDesc("");
//            list.add(orderDetail);
//        }
//        if(BigDecimal.ZERO.compareTo(trade.getDeliveryAmount())<0){
//            OrderDetail orderDetail = new OrderDetail();
//            //订单序号 订单顺序号,只能上送数字
//            orderDetail.setOrderSeqNo(goodsList.size()+101+"");
//            //这里运费的商品id设置一个默认值，到时候可以在商品里加上一个服务类商品（运送），设置运费金额
//            orderDetail.setDetailOrderId("10000000000"+DateUtils.fomatDate(new Date(),"yyyyMMddHHmmss"));
//
//            //orderDetail.setDetailOrderId("1326831734460809218");
//
//            //子订单金额，以分为单位，等于子订单支付金额与子订单手续费金额和,如果是按比例分账，此信息域上送分账比例，如40.75%上送为4075，我方收到后做相应缩小
//            //orderDetail.setOrderAmt(""+tradeGoods.getPayAmount().multiply(new BigDecimal("100")).intValue());
//            orderDetail.setOrderAmt(""+trade.getDeliveryAmount().multiply(new BigDecimal("100")).intValue());
//
//            //分账手续费，如果上送，将按照此手续费计算，商户平台不需要指定手续费时，默认为0
//            orderDetail.setShareFee("0");
//            //收款方商户编号-子商户
//            orderDetail.setRcvMerchantId(merchantId);
//            //收款方商户名称-子商户
//            orderDetail.setRcvMerchantIdName(trade.getShopId());
//            //商品展示的url
//            orderDetail.setShowUrl("http://www.test.com/test/callback.jsp");
//            //这里可允许的商品编号为5位数，暂时写死一个五位数
//            orderDetail.setProductId("00001");
//            //所购买商品的名称
//            orderDetail.setProductName("运费商品");
//            //所购买商品的描述
//            orderDetail.setProductDesc("");
//            list.add(orderDetail);
//        }
        request.setOrderDetail(list);
        BossClient client = new BossClient(MerchantBaseEnum.merchant_hly_CertPath.getValue(), MerchantBaseEnum.merchant_hly_CertPass.getValue(), MerchantBaseEnum.serverUrl.getValue());
        return client.execute(request);
    }

    /**
     * 支付宝扫码方式-调用支付接口
     * @param
     * @param entOffLinePaymentResponse
     */
    private QRCodePaymentCommitResponse doPay(EntOffLinePaymentResponse entOffLinePaymentResponse) throws Exception {
        log.info("doPay start");
        String requestId = DateUtils.fomatDate(new Date(),"yyyyMMddHHmmss");
        String creDt = DateUtils.fomatDate(new Date(),"yyyyMMdd");
        QRCodePaymentCommitRequest request = new QRCodePaymentCommitRequest();
        request.setCharset("00");
        request.setVersion("1.0");
        request.setSignType("RSA");
        request.setService("QRCodePaymentCommit");
        if(ObjectUtils.isNotEmpty(IpUtil.getHttpServletRequest())){
            request.setClientIP(IpUtil.getRemoteHost(IpUtil.getHttpServletRequest()));
        }else{
            request.setClientIP("127.0.0.1");
        }
        request.setRequestId(requestId);
        request.setMerchantId(MerchantBaseEnum.merchant_hly_Id.getValue());
        request.setOrderId(entOffLinePaymentResponse.getOrderId());
        request.setCreDt(creDt);
        //预下单创建时间下单后返回的订单token
        request.setToken(entOffLinePaymentResponse.getToken());
        // WECHAT: 微信钱包；ALIPAY:支付宝
        request.setPayChlTyp("ALIPAY");
        request.setTradeType("ALINATIVE");
        log.info("appId:" + appId);
        // 微信支付（扫码、APP、公众号、小程序）必传
        request.setAppId(appId);
        BossClient client = new BossClient(MerchantBaseEnum.merchant_hly_CertPath.getValue(), MerchantBaseEnum.merchant_hly_CertPass.getValue(), MerchantBaseEnum.serverUrl.getValue());
        return client.execute(request);
    }

    /**
     * 取消交易,回库存
     * @param tradeId
     * @return
     */
    private boolean cancelTradeReturnStock(String tradeId) {
        List<CommonStockDTO.InnerChangeStockItem> goodsItemList = new ArrayList<>();
        fillChangeStockItem(tradeId, goodsItemList);
        return commonStockRpc.innerPlusStockForTrade(goodsItemList);
    }

    /**
     * 填充skuId/购买数量
     * @param tradeId
     * @param goodsItemList
     */
    private void fillChangeStockItem(String tradeId, List<CommonStockDTO.InnerChangeStockItem> goodsItemList) {
        QueryWrapper<TradeGoods> tradeGoodsQueryWrapper = new QueryWrapper<>();
        tradeGoodsQueryWrapper.eq("trade_id",tradeId);
        List<TradeGoods> tradeGoodsList = tradeGoodsRepository.list(tradeGoodsQueryWrapper);
        for(TradeGoods tradeGoods : tradeGoodsList){
            CommonStockDTO.InnerChangeStockItem innerChangeStockItem = new CommonStockDTO.InnerChangeStockItem();
            innerChangeStockItem.setSkuId(tradeGoods.getSkuId());
            innerChangeStockItem.setQuantity(tradeGoods.getQuantity());
            goodsItemList.add(innerChangeStockItem);
        }
    }
    /**
     * 取消订单
     * */
    private void fillTradeCancel(BbbTradeCancelDTO.CancelDTO dto, TradeCancel tradeCancel, Trade trade, String tradePayId, Integer cancelState, Integer cancelRefundState) {
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
     * 填充用户信息
     * */
    private void fillUserInfo(BbbTradeListVO.tradeVO tradeVO) {
        BbbUserVO.InnerUserInfoVO innerUserInfoVO = iBbbUserRpc.innerGetUserInfo(tradeVO.getUserId());
        if(ObjectUtils.isNotEmpty(innerUserInfoVO)){
            tradeVO.setUserName(innerUserInfoVO.getUserName());
        }
    }
    /**
     *  填充物流信息
     * */
    private void fillLogisticsCompany(BbbTradeListVO.tradeVO tradeVO) {
        QueryWrapper<TradeDelivery> tradeDeliveryQueryWrapper = new QueryWrapper<>();
        tradeDeliveryQueryWrapper.eq("trade_id", tradeVO.getId());
        TradeDelivery tradeDelivery = tradeDeliveryRepository.getOne(tradeDeliveryQueryWrapper);
        if(ObjectUtils.isNotEmpty(tradeDelivery)){
            tradeVO.setLogisticsNumber(tradeDelivery.getLogisticsNumber());
            CommonLogisticsCompanyVO.DetailVO logisticsDetailVO = commonLogisticsCompanyRpc.getLogisticsCompany(tradeDelivery.getLogisticsId());
            if(ObjectUtils.isNotEmpty(logisticsDetailVO)){
                tradeVO.setLogisticsCompanyCode(logisticsDetailVO.getCode());
                tradeVO.setLogisticsCompanyName(logisticsDetailVO.getName());
            }
        }
    }

    /**
     * 组装TradeVO、tradeGoodsVO数据
     * @param tradeVO
     */
    private void fillTradeVO(BbbTradeListVO.tradeVO tradeVO) {
        QueryWrapper<TradeGoods> tradeGoodsQueryWrapper = new QueryWrapper<>();
        tradeGoodsQueryWrapper.eq("trade_id",tradeVO.getId());
        List<TradeGoods> tradeGoodsList = tradeGoodsRepository.list(tradeGoodsQueryWrapper);
        List<BbbTradeListVO.TradeGoodsVO> tradeGoodsVOS = new ArrayList<>();
        for(TradeGoods tradeGoods : tradeGoodsList){
            BbbTradeListVO.TradeGoodsVO tradeGoodsVO = new BbbTradeListVO.TradeGoodsVO();
            BeanUtils.copyProperties(tradeGoods, tradeGoodsVO);
            QueryWrapper<TradeComment> query = MybatisPlusUtil.query();
            query.and(i->i.eq("trade_id",tradeVO.getId()));
            query.and(i->i.eq("trade_goods_id",tradeGoods.getId()));
            List<TradeComment> list = iTradeCommentRepository.list(query);
            if (list.size()>0){
                tradeGoodsVO.setCommentFlag(20);
            }else {
                tradeGoodsVO.setCommentFlag(10);
            }
            tradeGoodsVO.setShopName(tradeVO.getShopName());
            tradeGoodsVO.setSalePrice(tradeGoods.getSalePrice());
            tradeGoodsVOS.add(tradeGoodsVO);
        }
        tradeVO.setTradeGoodsVOS(tradeGoodsVOS);
    }

    /**
     * 创建支付信息
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
     * @param tradeId
     * @param tradeGoodsDTOSet
     */
    private void saveTradeGoods(String tradeId,Set<BbbTradeGoodsDTO.ETO> tradeGoodsDTOSet) {
        for(BbbTradeGoodsDTO.ETO tradeGoodsDTO : tradeGoodsDTOSet){
            tradeGoodsDTO.setTradeId(tradeId);
            TradeGoods tradeGoods = new TradeGoods();
            BeanUtils.copyProperties(tradeGoodsDTO, tradeGoods);
            tradeGoodsRepository.save(tradeGoods);
        }
    }
    /**
     * 创建订单
     * @param dto
     * @param addressVO
     * @param discountAmount
     * @param deliveryAmount
     * @return
     */
    private Trade saveTrade(BbbTradeBuildDTO.DTO dto, BbbStockAddressVO.ListVO addressVO, BigDecimal discountAmount, BigDecimal deliveryAmount,String shopId) {
        Trade trade = new Trade();
        trade.setUserId(dto.getJwtUserId());
        trade.setShopId(shopId);
        trade.setTradeCode(TradeUtils.getTradeCode());
        trade.setTradeState(TradeStateEnum.待支付.getCode());
        trade.setCreateTime(LocalDateTime.now());
        trade.setPayType(dto.getPayType());
        trade.setDeliveryType(dto.getDeliveryType());
        trade.setInvoiceId(StringUtils.isNotEmpty(dto.getInvoiceId())?dto.getInvoiceId():"");
        //优惠券id
        if (dto.getUserCardVO() != null && StrUtil.isNotBlank(dto.getUserCardVO().getCardId())) {
            trade.setUserCardId(dto.getUserCardVO().getCardId());
        }
        if(dto.getDeliveryType() != null && dto.getDeliveryType().intValue() == TradeDeliveryTypeEnum.门店自提.getCode()){
            trade.setTakeGoodsCode(TradeUtils.getTakeGoodsCode());
        }
        if(dto.getDeliveryType().equals(TradeDeliveryTypeEnum.门店自提.getCode())){
            trade.setRecvPersonName(addressVO.getContactsName());
            System.out.println(addressVO.getContactsName());
            trade.setRecvPhone(addressVO.getContactsPhone());
        }else{
            trade.setRecvAddresId(addressVO.getId());
            trade.setRecvPersonName(addressVO.getContactsName());
            System.out.println(addressVO.getContactsName()+"___________________________________");
            trade.setRecvPhone(addressVO.getContactsPhone());
            StringBuffer stringBuffer = new StringBuffer(addressVO.getProvince()+addressVO.getCity()+addressVO.getCounty());
            if (ObjectUtils.isNotEmpty(addressVO.getStreet())){
                stringBuffer.append(addressVO.getStreet());
            }
            trade.setRecvFullAddres(stringBuffer.toString());
        }

        trade.setBuyerRemark(dto.getBuyerRemark());
        trade.setGoodsAmount(dto.getShopProductAmount());
        trade.setDeliveryAmount(deliveryAmount);
        trade.setIsInvoice(dto.getIsInvoice());
        trade.setTradeAmount(dto.getShopProductAmount().add(deliveryAmount));
        trade.setDiscountAmount(discountAmount);
        trade.setSourceType(TradeSourceTypeEnum._2B.getCode());
        trade.setChildTradeId(UuidUtil.getUuid());
        tradeRepository.save(trade);

        return trade;
    }

    /**
     * 组装订单商品表信息
     * @param userId
     * @param shopId
     * @param quantity
     * @param innerServiceGoodsVO
     * @return
     */
    private BbbTradeGoodsDTO.ETO fillTradeGoodsDTO(String userId, String shopId, Integer quantity, PCBbbGoodsInfoVO.InnerServiceVO innerServiceGoodsVO,BigDecimal stepPrice) {
        BbbTradeGoodsDTO.ETO tradeGoodsDTO = new BbbTradeGoodsDTO.ETO();
        tradeGoodsDTO.setUserId(userId);
        tradeGoodsDTO.setShopId(shopId);
        tradeGoodsDTO.setGoodsId(innerServiceGoodsVO.getGoodsId());
        tradeGoodsDTO.setGoodsName(innerServiceGoodsVO.getGoodsName());
        tradeGoodsDTO.setGoodsTitle(innerServiceGoodsVO.getGoodsTitle());
        tradeGoodsDTO.setGoodsNo(innerServiceGoodsVO.getGoodsNo());
        tradeGoodsDTO.setSkuId(innerServiceGoodsVO.getSkuId());
        tradeGoodsDTO.setSkuSpecValue(StringUtils.isEmpty(innerServiceGoodsVO.getSkuSpecValue()) ? "" : innerServiceGoodsVO.getSkuSpecValue());
        tradeGoodsDTO.setSkuImg(innerServiceGoodsVO.getGoodsImage());
        tradeGoodsDTO.setSkuGoodsNo(innerServiceGoodsVO.getSkuGoodsNo());
        tradeGoodsDTO.setQuantity(quantity);
        tradeGoodsDTO.setSalePrice(ObjectUtils.isNotEmpty(stepPrice)?stepPrice:BigDecimal.ZERO);
        tradeGoodsDTO.setPayAmount(innerServiceGoodsVO.getSalePrice());
        tradeGoodsDTO.setCommentFlag(TradeTrueFalseEnum.是.getCode());
        return tradeGoodsDTO;
    }

    /**
     * 组装收货地址信息
     * @param dto
     * @param settlementVO
     */
    private void fillAddress(BbbTradeBuildDTO.cartIdsDTO dto,  BbbTradeSettlementVO.ListVO settlementVO) {
     if (StringUtils.isNotEmpty(dto.getAddressId())){
         BbbStockAddressDTO.IdDTO idDto = new BbbStockAddressDTO.IdDTO(dto.getAddressId());
         idDto.setJwtUserId(dto.getJwtUserId());
         BbbStockAddressVO.ListVO addressVO ;
         //根据id查询地址
         addressVO = iBbbPcStockAddressRpc.innerDetailVO(idDto);
         if(ObjectUtils.isNotEmpty(addressVO) && addressVO.getId() != null){
             settlementVO.setRecvAddresId(addressVO.getId());
             settlementVO.setRecvPersonName(addressVO.getContactsName());
             settlementVO.setRecvPhone(addressVO.getContactsPhone());
             settlementVO.setRecvFullAddres(addressVO.getFullAddres());
             //根据店铺的商品SKU ID获取快递配送运费模板，根据重量、件数计算运费.
             BbbTradeSettlementVO.TotalVO deliveryAmount = getDeliveryAmount(settlementVO);
             if (ObjectUtils.isNotEmpty(deliveryAmount)){
                 settlementVO.setDeliveryAmount(deliveryAmount.getDeliveryAmount());//运费
                 settlementVO.setTotalWeight(deliveryAmount.getTotalWeight());
             }

         }

     }else {
         //获取用户默认收货地址
         BbbStockAddressVO.DetailVO addressVO = iBbbPcStockAddressRpc.innerGetDefault(dto, StockAddressTypeEnum.收货.getCode());
         if (ObjectUtils.isNotEmpty(addressVO)) {
             settlementVO.setRecvAddresId(addressVO.getId());
             settlementVO.setRecvPersonName(addressVO.getContactsName());
             settlementVO.setRecvPhone(addressVO.getContactsPhone());
             settlementVO.setRecvFullAddres(addressVO.getFullAddres());
             //根据店铺的商品SKU ID获取快递配送运费模板，根据重量、件数计算运费.
             BbbTradeSettlementVO.TotalVO deliveryAmount = getDeliveryAmount(settlementVO);
             if (ObjectUtils.isNotEmpty(deliveryAmount)){
                 settlementVO.setDeliveryAmount(deliveryAmount.getDeliveryAmount());//运费
                 settlementVO.setTotalWeight(deliveryAmount.getTotalWeight());
             }

         }
     }


    }
    private BbbTradeSettlementVO.TotalVO getDeliveryAmount(BbbTradeSettlementVO.ListVO settlementVO) {
        BigDecimal deliveryAmount = BigDecimal.ZERO; //运费
        BigDecimal totalWeights = BigDecimal.ZERO; //运费
        BbbStockDeliveryDTO.DeliveryAmountDTO deliveryAmountDTO = new BbbStockDeliveryDTO.DeliveryAmountDTO();
        deliveryAmountDTO.setAddressId(settlementVO.getRecvAddresId());
        deliveryAmountDTO.setDeliveryType(TradeDeliveryTypeEnum.快递配送.getCode());
        deliveryAmountDTO.setShopId(settlementVO.getShopId());
        List<BbbStockDeliveryDTO.DeliverySKUDTO> deliverySKUDTOS = new ArrayList<>();
        for(BbbTradeSettlementVO.ListVO.goodsInfoVO goodsInfoVO : settlementVO.getGoodsInfoVOS()){
            BbbStockDeliveryDTO.DeliverySKUDTO deliverySKUDTO = new BbbStockDeliveryDTO.DeliverySKUDTO();
            deliverySKUDTO.setSkuId(goodsInfoVO.getSkuId());
            deliverySKUDTO.setAmount(new BigDecimal(goodsInfoVO.getQuantity()));
            deliverySKUDTOS.add(deliverySKUDTO);
        }
        deliveryAmountDTO.setSkus(deliverySKUDTOS);
        BbbStockDeliveryVO.DeliveryAmountVO deliveryAmountVO = iBbbStockDeliveryRpc.calculate(deliveryAmountDTO);
        if(ObjectUtils.isNotEmpty(deliveryAmountVO)){
            deliveryAmount = deliveryAmountVO.getAmount();
            totalWeights=deliveryAmountVO.getTotalWeight();
        }
        BbbTradeSettlementVO.TotalVO totalVO = new BbbTradeSettlementVO.TotalVO();
        totalVO.setDeliveryAmount(deliveryAmount).setTotalWeight(totalWeights);
        return totalVO;
    }
    private BigDecimal getDeliveryAmount(String shopId,List<BbbTradeBuildDTO.DTO.ProductData> productDataList,Integer deliveryType, String addressId) {
        BbbStockDeliveryDTO.DeliveryAmountDTO deliveryAmountDTO = new BbbStockDeliveryDTO.DeliveryAmountDTO();
        deliveryAmountDTO.setAddressId(addressId);
        deliveryAmountDTO.setDeliveryType(deliveryType);
        deliveryAmountDTO.setShopId(shopId);
        List<BbbStockDeliveryDTO.DeliverySKUDTO> deliverySKUDTOS = new ArrayList<>();
        for(BbbTradeBuildDTO.DTO.ProductData productData : productDataList){
            BbbStockDeliveryDTO.DeliverySKUDTO deliverySKUDTO = new BbbStockDeliveryDTO.DeliverySKUDTO();
            deliverySKUDTO.setSkuId(productData.getGoodsSkuId());
            deliverySKUDTO.setAmount(new BigDecimal(productData.getQuantity()));
            deliverySKUDTOS.add(deliverySKUDTO);
        }
        deliveryAmountDTO.setSkus(deliverySKUDTOS);
        BbbStockDeliveryVO.DeliveryAmountVO deliveryAmountVO = iBbbStockDeliveryRpc.calculate(deliveryAmountDTO);
        if(ObjectUtils.isNotEmpty(deliveryAmountVO)){
            return deliveryAmountVO.getAmount();
        }
        return BigDecimal.ZERO;
    }
    /**
     * 组装商品信息
     * @param carId
     * @param quantity
     * @param innerServiceGoodsVO
     * @return
     */
    private BbbTradeSettlementVO.ListVO.goodsInfoVO fillGoodsInfoVO(String carId,Integer quantity, PCBbbGoodsInfoVO.InnerServiceVO  innerServiceGoodsVO,Integer checkStockVO) {
        BbbTradeSettlementVO.ListVO.goodsInfoVO goodsInfoVO = new BbbTradeSettlementVO.ListVO.goodsInfoVO();
        goodsInfoVO.setGoodsId(innerServiceGoodsVO.getGoodsId());
        goodsInfoVO.setGoodsName(innerServiceGoodsVO.getGoodsName());
        goodsInfoVO.setGoodsTitle(innerServiceGoodsVO.getGoodsTitle());
        goodsInfoVO.setSkuId(innerServiceGoodsVO.getSkuId());
        goodsInfoVO.setSkuImg(innerServiceGoodsVO.getGoodsImage());
        goodsInfoVO.setSkuSpecValue(StringUtils.isEmpty(innerServiceGoodsVO.getSkuSpecValue()) ? "" : innerServiceGoodsVO.getSkuSpecValue());
        goodsInfoVO.setQuantity(quantity);//购买数量
        goodsInfoVO.setCartId(carId);//购物车id
        goodsInfoVO.setSalePrice(innerServiceGoodsVO.getSalePrice());
        goodsInfoVO.setSockStatus(checkStockVO);
        return goodsInfoVO;
    }
    /**
     * 组装商家信息
     * @param shopId
     * @param settlementVO
     */
    private void fillShop(String shopId, BbbTradeSettlementVO.ListVO settlementVO) {
        //根据店铺ID获取店铺自提、发货地址和店铺状态  //根据店铺信息 、判断店铺状态
        BbbShopVO.ComplexDetailVO innerDetailVO = iBbbShopRpc.inneComplexDetailShop(shopId, new BaseDTO());
        if(ObjectUtils.isNotEmpty(innerDetailVO)){
            settlementVO.setShopId(innerDetailVO.getId());
            settlementVO.setShopLogo(innerDetailVO.getShopLogo());
            settlementVO.setShopName(innerDetailVO.getShopName());
        }

    }
    private void fillShop(BbbTradeListVO.tradeVO tradeVO) {
        BbbShopVO.ComplexDetailVO innerDetailVO = iBbbShopRpc.inneComplexDetailShop(tradeVO.getShopId(), new BaseDTO().setJwtUserId(tradeVO.getUserId()));
        if(ObjectUtils.isNotEmpty(innerDetailVO)){
            tradeVO.setShopName(innerDetailVO.getShopName());
            tradeVO.setShopLogo(innerDetailVO.getShopLogo());
            tradeVO.setShopManName(innerDetailVO.getShopManName());
            tradeVO.setShopManPhone(innerDetailVO.getShopManPhone());
            tradeVO.setShopFullAddres(innerDetailVO.getShopFullAddres());
            tradeVO.setShopLatitude(innerDetailVO.getShopLatitude());
            tradeVO.setShopLongitude(innerDetailVO.getShopLongitude());
        }
    }
    /**
     * 检查库存
     * @param goodsItemList
     */
    private CommonStockVO.InnerListCheckStockVO checkStock(List<CommonStockDTO.InnerSkuGoodsInfoItem> goodsItemList) {
        CommonStockDTO.InnerCheckStockDTO innerListCheckStockDTO = new CommonStockDTO.InnerCheckStockDTO();
        innerListCheckStockDTO.setGoodsItemList(goodsItemList);
        ResponseData<CommonStockVO.InnerListCheckStockVO> checkStockVO = commonStockRpc.innerListCheckStock(innerListCheckStockDTO);
        if (ResponseCodeEnum.失败.getCode().equals(checkStockVO.getCode())) {
            throw new BusinessException(checkStockVO.getMessage());
        }
//        if(!StockCheckStateEnum.库存正常.getCode().equals(checkStockVO.getData().getCheckState())){//库存状态
//            throw new BusinessException(StockCheckStateEnum.库存不足.getRemark());
//        }
        return checkStockVO.getData();
    }

}
