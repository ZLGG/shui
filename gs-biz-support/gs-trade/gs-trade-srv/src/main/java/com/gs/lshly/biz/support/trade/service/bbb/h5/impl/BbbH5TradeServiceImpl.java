package com.gs.lshly.biz.support.trade.service.bbb.h5.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.*;
import com.gs.lshly.biz.support.trade.repository.*;
import com.gs.lshly.biz.support.trade.service.bbb.h5.IBbbH5TradeService;
import com.gs.lshly.biz.support.trade.service.bbc.IBbcMarketSettleService;
import com.gs.lshly.biz.support.trade.service.common.Impl.ICommonMarketCardServiceImpl;
import com.gs.lshly.biz.support.trade.utils.TradeUtils;
import com.gs.lshly.common.enums.*;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.merchant.qto.BbbH5ShopQTO;
import com.gs.lshly.common.struct.bbb.h5.merchant.vo.BbbH5ShopVO;
import com.gs.lshly.common.struct.bbb.h5.stock.dto.BbbH5StockAddressDTO;
import com.gs.lshly.common.struct.bbb.h5.stock.dto.BbbH5StockDeliveryDTO;
import com.gs.lshly.common.struct.bbb.h5.stock.vo.BbbH5StockAddressVO;
import com.gs.lshly.common.struct.bbb.h5.stock.vo.BbbH5StockDeliveryVO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.*;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5TradeQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeListVO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeResultNotifyVO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeSettlementVO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserFrequentGoodsV2DTO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserIntegralDTO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserShoppingCarDTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserShoppingCarVO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserVO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeSettlementVO;
import com.gs.lshly.common.struct.common.CommonLogisticsCompanyVO;
import com.gs.lshly.common.struct.common.CommonStockDTO;
import com.gs.lshly.common.struct.common.CommonStockVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsReceiptVO;
import com.gs.lshly.common.utils.Base64;
import com.gs.lshly.common.utils.IpUtil;
import com.gs.lshly.common.utils.JsonUtils;
import com.gs.lshly.middleware.mq.aliyun.producerService.ProducerService;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbb.h5.commodity.IBbbH5GoodsInfoRpc;
import com.gs.lshly.rpc.api.bbb.h5.merchant.IBbbH5ShopRpc;
import com.gs.lshly.rpc.api.bbb.h5.stock.IBbbH5StockAddressRpc;
import com.gs.lshly.rpc.api.bbb.h5.stock.IBbbH5StockDeliveryRpc;
import com.gs.lshly.rpc.api.bbb.h5.user.IBbbH5UserFrequentV2Rpc;
import com.gs.lshly.rpc.api.bbb.h5.user.IBbbH5UserIntegralRpc;
import com.gs.lshly.rpc.api.bbb.h5.user.IBbbH5UserRpc;
import com.gs.lshly.rpc.api.bbb.h5.user.IBbbH5UserShoppingCarRpc;
import com.gs.lshly.rpc.api.bbb.pc.commodity.IPCBbbGoodsInfoRpc;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserRpc;
import com.gs.lshly.rpc.api.common.ICommonLogisticsCompanyRpc;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import com.gs.lshly.rpc.api.common.ICommonStockRpc;
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
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
* <p>
*  服务实现类
* </p>
* @author oy
* @since 2020-10-28
*/
@Component
@Slf4j
public class BbbH5TradeServiceImpl implements IBbbH5TradeService {

    private final ITradeRepository tradeRepository;

    private final ITradeGoodsRepository tradeGoodsRepository;

    private final ITradePayRepository tradePayRepository;

    private final ITradePayOfflineRepository tradePayOfflineRepository;

    private final ITradeDeliveryRepository tradeDeliveryRepository;

    private final ITradeCancelRepository tradeCancelRepository;

    @Autowired
    private ITradeRightsRepository iTradeRightsRepository;

    @Autowired
    private ITradeRightsGoodsRepository iTradeRightsGoodsRepository;

    @Autowired
    private ITradePayRepository iTradePayRepository;

    @Autowired
    private ITradePayOfflineRepository iTradePayOfflineRepository;

    @Autowired
    private ITradePayOfflineImgRepository iTradePayOfflineImgRepository;

    @Autowired
    private IMarketMerchantCardUsersRepository iMarketMerchantCardUsersRepository;

    @Autowired
    private IMarketMerchantCardGoodsRepository iMarketMerchantCardGoodsRepository;

    @Autowired
    private  ITradeCommentRepository iTradeCommentRepository;

    @Autowired
    private ITradeInvoiceRepository iTradeInvoiceRepository;

    @Autowired
    private ProducerService producerService;

    @Value("${lakala.2bh5.notifyurl}")
    private String notifyUrl;

    @Value("${lakala.2bh5.appid}")
    private String appId;

    @DubboReference
    private IBbbH5GoodsInfoRpc bbcGoodsInfoRpc;

    @DubboReference
    private ICommonStockRpc commonStockRpc;

    @DubboReference
    private IBbbH5StockAddressRpc bbcStockAddressRpc;

    @DubboReference
    private IBbbH5UserShoppingCarRpc bbcUserShoppingCarRpc;

    @DubboReference
    private IBbbH5ShopRpc iBbbH5ShopRpc;

    @DubboReference
    private ICommonLogisticsCompanyRpc commonLogisticsCompanyRpc;

    @DubboReference
    private IBbbH5StockDeliveryRpc bbcStockDeliveryRpc;

    @DubboReference
    private IBbbH5UserRpc bbcUserRpc;

    @DubboReference
    private IBbbH5UserIntegralRpc iBbbH5UserIntegralRpc;

    @DubboReference
    private IBbbH5UserFrequentV2Rpc bbbH5UserFrequentV2Rpc;

    @DubboReference
    private IBbbUserRpc iBbbUserRpc;

    @DubboReference
    private IPCBbbGoodsInfoRpc ipcBbbGoodsInfoRpc;

    @DubboReference
    private ISettingsReceiptRpc iSettingsReceiptRpc;

    @Autowired
    private IBbcMarketSettleService marketSettleService;
    @Autowired
    private ICommonMarketCardServiceImpl commonMarketCardService;

    public BbbH5TradeServiceImpl(ITradeRepository tradeRepository, ITradeGoodsRepository tradeGoodsRepository,
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
    public ResponseData<BbbH5TradeSettlementVO.ListVO> settlementVO(BbbH5TradeBuildDTO.cartIdsDTO dto) {
        //skuid/数量
        List<CommonStockDTO.InnerSkuGoodsInfoItem> goodsItemList = new ArrayList<>();

        //购物车id/skuid/数量
        List<BbbH5UserShoppingCarVO.InnerSimpleItem> itemList = new ArrayList<>();

        if(StringUtils.isNotEmpty(dto.getGoodsSkuId())){//立即购买
            CommonStockDTO.InnerSkuGoodsInfoItem innerSkuGoodsInfoItem = new CommonStockDTO.InnerSkuGoodsInfoItem(null,dto.getGoodsSkuId(),dto.getQuantity());
            goodsItemList.add(innerSkuGoodsInfoItem);

            BbbH5UserShoppingCarVO.InnerSimpleItem innerSimpleItem = new BbbH5UserShoppingCarVO.InnerSimpleItem();
                BeanUtils.copyProperties(innerSkuGoodsInfoItem,innerSimpleItem);
            itemList.add(innerSimpleItem);

        }else{//购物车购买 dto.getCartIds();

            //根据购物车id查询实体 //判断商品是否属于同一个店铺
            BbbH5UserShoppingCarDTO.InnerIdListDTO innerIdListDTO = new BbbH5UserShoppingCarDTO.InnerIdListDTO();
                innerIdListDTO.setIdList(dto.getCartIds());
            ResponseData<BbbH5UserShoppingCarVO.InnerSimpleVO> innerSimpleVOResponseData = bbcUserShoppingCarRpc.innerSimpleShoppingCarlist(innerIdListDTO);
            if(ResponseCodeEnum.失败.getCode() == innerSimpleVOResponseData.getCode()){
                throw new BusinessException(innerSimpleVOResponseData.getMessage());
            }
            BbbH5UserShoppingCarVO.InnerSimpleVO innerSimpleVO = innerSimpleVOResponseData.getData();
            if(ObjectUtils.isEmpty(innerSimpleVO)){
                throw new BusinessException("无购物车数据");
            }
            //商品信息
            itemList = innerSimpleVO.getItemList();
            for(BbbH5UserShoppingCarVO.InnerSimpleItem innerSimpleItem : itemList){
                goodsItemList.add(new CommonStockDTO.InnerSkuGoodsInfoItem(null,innerSimpleItem.getSkuId(),innerSimpleItem.getQuantity()));
            }
        }
        //根据SKU ID数组检查库存
        CommonStockVO.InnerListCheckStockVO checkStockVO = checkStock(goodsItemList);

        //返回地址、商家、商品（图片、名称、规格、单价）、商品总金额、商品数量、运费
        BbbH5TradeSettlementVO.ListVO settlementVO = new BbbH5TradeSettlementVO.ListVO();

        //组装商家信息
        fillShop(checkStockVO.getShopId(), settlementVO);

        List<BbbH5TradeSettlementVO.ListVO.goodsInfoVO> goodsInfoVOS = new ArrayList<>();
        BigDecimal goodsAmount = BigDecimal.ZERO;//商品总金额
        Integer goodsCount = 0;//商品总数
        // 店铺商品
        for(BbbH5UserShoppingCarVO.InnerSimpleItem innerSimpleItem : itemList){
            BbbH5GoodsInfoVO.InnerServiceVO innerServiceGoodsVO=null;
            //根据SKU ID获取商品信息（图片、商品名称、规格值、售价、状态）
            if (ObjectUtils.isNotEmpty(innerSimpleItem.getSkuId())) {
               innerServiceGoodsVO = bbcGoodsInfoRpc.innerServiceVO(innerSimpleItem.getSkuId(), dto);
            }
            log.info("结算-取价前：" + JsonUtils.toJson(goodsInfoVOS));
            if(ObjectUtils.isEmpty(innerServiceGoodsVO) || innerServiceGoodsVO.getGoodsId() == null){
                throw new BusinessException("无商品数据或已下架");
            }else if(!innerServiceGoodsVO.getGoodsState().equals(GoodsStateEnum.已上架.getCode())){
                throw new BusinessException("商品已下架");
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
            BbbH5TradeSettlementVO.ListVO.goodsInfoVO goodsInfoVO = fillGoodsInfoVO(innerSimpleItem.getId(),innerSimpleItem.getQuantity(), innerServiceGoodsVO);
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
            log.info("H5结算-各价格：批发价格【" + wholesalePrice + "】，阶梯价【" + goodsStepPrice + "】，零售价【" + innerServiceGoodsVO.getSalePrice() + "】");
            goodsInfoVOS.add(goodsInfoVO);
            goodsAmount = goodsAmount.add(productTotalPrice);
            goodsCount = goodsCount+goodsInfoVO.getQuantity();
        }
        settlementVO.setGoodsInfoVOS(goodsInfoVOS);
        log.info("结算-取价后：" + JsonUtils.toJson(goodsInfoVOS));
        //组装收货地址信息
        fillAddress(dto, settlementVO);

        settlementVO.setGoodsAmount(goodsAmount);//商品总额
        settlementVO.setGoodsCount(goodsCount);//商品总数
        //营销结算
        try {
            log.info("开始结算:"+ JsonUtils.toJson(settlementVO));
            marketSettleService.settlementH5BBB(settlementVO, dto);
            log.info("结算结果:"+ JsonUtils.toJson(settlementVO));
        } catch (Exception e) {
            log.error("bbb-h5营销结算异常:" + e.getMessage(), e);
        }
        //订单总额=商品总额+运费
        BigDecimal delivery = settlementVO.getDeliveryAmount() != null ? settlementVO.getDeliveryAmount() : BigDecimal.ZERO;
        settlementVO.setTradeAmount(settlementVO.getGoodsAmount().add(delivery));

        //组装门店自提联系人信息
        fillContacts(dto, settlementVO);

        return ResponseData.data(settlementVO);
    }

    /**
     * 组装商家信息
     * @param shopId
     * @param settlementVO
     */
    private void fillShop(String shopId, BbbH5TradeSettlementVO.ListVO settlementVO) {
        //根据店铺ID获取店铺自提、发货地址和店铺状态  //根据店铺信息 、判断店铺状态
        BbbH5ShopQTO.InnerShopQTO innerShopQTO = new BbbH5ShopQTO.InnerShopQTO();
        innerShopQTO.setShopId(shopId);
        BbbH5ShopVO.InnerDetailVO innerDetailVO = iBbbH5ShopRpc.innerDetailShop(innerShopQTO);
        settlementVO.setShopId(innerDetailVO.getId());
        settlementVO.setShopLogo(innerDetailVO.getShopLogo());
        settlementVO.setShopName(innerDetailVO.getShopName());
        settlementVO.setDeliveryScope(innerDetailVO.getDeliveryScope());
        settlementVO.setShopFullAddres(innerDetailVO.getShopFullAddres());
    }

    /**
     * 组装收货地址信息
     * @param dto
     * @param settlementVO
     */
    private void fillAddress(BbbH5TradeBuildDTO.cartIdsDTO dto,BbbH5TradeSettlementVO.ListVO settlementVO) {

            BbbH5StockAddressDTO.IdAndTypeDTO idDto = new BbbH5StockAddressDTO.IdAndTypeDTO();
            idDto.setJwtUserId(dto.getJwtUserId());
            BbbH5StockAddressDTO.IdAndTypeDTO idAndTypeDTO = new BbbH5StockAddressDTO.IdAndTypeDTO();
            if(StringUtils.isNotEmpty(dto.getAddressId())) {
                idDto.setId(dto.getAddressId());
                idAndTypeDTO.setId(dto.getAddressId());
            }
            idAndTypeDTO.setJwtUserId(dto.getJwtUserId());
            if (ObjectUtils.isNotEmpty(dto.getDeliveryType())) {
                BbbH5StockAddressVO.ListVO addressVO = null;
                if (!dto.getDeliveryType().equals(TradeDeliveryTypeEnum.门店自提.getCode())) {
                    //根据id查询地址
                    addressVO = bbcStockAddressRpc.detailStockAddress(idAndTypeDTO);
                    if (ObjectUtils.isNotEmpty(addressVO) && addressVO.getId() != null) {
                        settlementVO.setRecvAddresId(addressVO.getId());
                        settlementVO.setRecvPersonName(addressVO.getContactsName());
                        settlementVO.setRecvPhone(addressVO.getContactsPhone());
                        settlementVO.setRecvFullAddres(addressVO.getFullAddres());
                        //根据店铺的商品SKU ID获取快递配送运费模板，根据重量、件数计算运费.
                        BbbTradeSettlementVO.TotalVO deliveryAmount = getDeliveryAmount(settlementVO);
                        if (ObjectUtils.isNotEmpty(deliveryAmount)) {
                            settlementVO.setDeliveryAmount(deliveryAmount.getDeliveryAmount()).setTotalWeight(deliveryAmount.getTotalWeight());//运费
                        }
                    }
                }
            } else {
                //获取用户默认收货地址
                BbbH5StockAddressVO.DetailVO addressVO = bbcStockAddressRpc.innerGetDefault(dto, StockAddressTypeEnum.收货.getCode());
                if (ObjectUtils.isNotEmpty(addressVO)) {
                    settlementVO.setRecvAddresId(addressVO.getId());
                    settlementVO.setRecvPersonName(addressVO.getContactsName());
                    settlementVO.setRecvPhone(addressVO.getContactsPhone());
                    settlementVO.setRecvFullAddres(addressVO.getFullAddres());
                    //根据店铺的商品SKU ID获取快递配送运费模板，根据重量、件数计算运费.
                    BbbTradeSettlementVO.TotalVO deliveryAmount = getDeliveryAmount(settlementVO);
                    if (ObjectUtils.isNotEmpty(deliveryAmount)) {
                        settlementVO.setDeliveryAmount(deliveryAmount.getDeliveryAmount()).setTotalWeight(deliveryAmount.getTotalWeight());//运费
                    }
                }
            }



    }

    private BbbTradeSettlementVO.TotalVO getDeliveryAmount(BbbH5TradeSettlementVO.ListVO settlementVO) {
        BigDecimal deliveryAmount = BigDecimal.ZERO; //运费
        BigDecimal totalWeight=BigDecimal.ZERO;
        BbbH5StockDeliveryDTO.DeliveryAmountDTO deliveryAmountDTO = new BbbH5StockDeliveryDTO.DeliveryAmountDTO();
        deliveryAmountDTO.setAddressId(settlementVO.getRecvAddresId());
        deliveryAmountDTO.setDeliveryType(TradeDeliveryTypeEnum.快递配送.getCode());
        deliveryAmountDTO.setShopId(settlementVO.getShopId());
        List<BbbH5StockDeliveryDTO.DeliverySKUDTO> deliverySKUDTOS = new ArrayList<>();
        for(BbbH5TradeSettlementVO.ListVO.goodsInfoVO goodsInfoVO : settlementVO.getGoodsInfoVOS()){
            BbbH5StockDeliveryDTO.DeliverySKUDTO deliverySKUDTO = new BbbH5StockDeliveryDTO.DeliverySKUDTO();
            deliverySKUDTO.setSkuId(goodsInfoVO.getSkuId());
            deliverySKUDTO.setAmount(new BigDecimal(goodsInfoVO.getQuantity()));
            deliverySKUDTOS.add(deliverySKUDTO);
        }
        deliveryAmountDTO.setSkus(deliverySKUDTOS);
        BbbH5StockDeliveryVO.DeliveryAmountVO deliveryAmountVO = bbcStockDeliveryRpc.calculate(deliveryAmountDTO);
        BbbTradeSettlementVO.TotalVO totalVO = new BbbTradeSettlementVO.TotalVO();
        if(ObjectUtils.isNotEmpty(deliveryAmountVO)){
            deliveryAmount=deliveryAmount.add(ObjectUtils.isNotEmpty(totalVO.getDeliveryAmount())?totalVO.getDeliveryAmount():BigDecimal.ZERO);
            totalWeight=totalWeight.add(ObjectUtils.isNotEmpty(totalVO.getTotalWeight())?totalVO.getTotalWeight():BigDecimal.ZERO);

        }
        totalVO.setDeliveryAmount(deliveryAmount).setTotalWeight(totalWeight);
        return totalVO;
    }

    /**
     *组装门店自提联系人信息
     * @param dto
     * @param settlementVO
     */
    private void fillContacts(BbbH5TradeBuildDTO.cartIdsDTO dto, BbbH5TradeSettlementVO.ListVO settlementVO) {
        QueryWrapper<Trade> tradeWrapper = new QueryWrapper<>();
        tradeWrapper.eq("user_id",dto.getJwtUserId());
        tradeWrapper.eq("delivery_type", TradeDeliveryTypeEnum.门店自提.getCode());
        tradeWrapper.orderByDesc("cdate");
        tradeWrapper.last("limit 0,1");
        Trade tradeForContacts = tradeRepository.getOne(tradeWrapper);
        if(ObjectUtils.isNotEmpty(tradeForContacts)){
            settlementVO.setContactsName(tradeForContacts.getRecvPersonName());
            settlementVO.setContactsPhone(tradeForContacts.getRecvPhone());
        }
    }

    /**
     * 组装商品信息
     * @param carId
     * @param quantity
     * @param innerServiceGoodsVO
     * @return
     */
    private BbbH5TradeSettlementVO.ListVO.goodsInfoVO fillGoodsInfoVO(String carId,Integer quantity, BbbH5GoodsInfoVO.InnerServiceVO innerServiceGoodsVO) {
        BbbH5TradeSettlementVO.ListVO.goodsInfoVO goodsInfoVO = new BbbH5TradeSettlementVO.ListVO.goodsInfoVO();
        if (ObjectUtils.isNotEmpty(innerServiceGoodsVO)){
            goodsInfoVO.setGoodsId(innerServiceGoodsVO.getGoodsId());
            goodsInfoVO.setGoodsName(innerServiceGoodsVO.getGoodsName());
            goodsInfoVO.setGoodsTitle(innerServiceGoodsVO.getGoodsTitle());
            goodsInfoVO.setSkuId(innerServiceGoodsVO.getSkuId());
            goodsInfoVO.setSkuImg(innerServiceGoodsVO.getGoodsImage());
            goodsInfoVO.setSalePrice(innerServiceGoodsVO.getSalePrice());
            goodsInfoVO.setSkuSpecValue(StringUtils.isEmpty(innerServiceGoodsVO.getSkuSpecValue()) ? "" : innerServiceGoodsVO.getSkuSpecValue());
        }
        goodsInfoVO.setQuantity(quantity);//购买数量
        goodsInfoVO.setCartId(carId);//购物车id

        return goodsInfoVO;
    }

    /**
     * 检查库存
     * @param goodsItemList
     */
    private CommonStockVO.InnerListCheckStockVO checkStock(List<CommonStockDTO.InnerSkuGoodsInfoItem> goodsItemList) {
        CommonStockDTO.InnerCheckStockDTO innerListCheckStockDTO = new CommonStockDTO.InnerCheckStockDTO();
        innerListCheckStockDTO.setGoodsItemList(goodsItemList);
        ResponseData<CommonStockVO.InnerListCheckStockVO> checkStockVO = commonStockRpc.innerListCheckStock(innerListCheckStockDTO);
        if(ResponseCodeEnum.失败.getCode() == checkStockVO.getCode()){
            throw new BusinessException(checkStockVO.getMessage());
        }
        if(!StockCheckStateEnum.库存正常.getCode().equals(checkStockVO.getData().getCheckState())){//库存状态
            throw new BusinessException(StockCheckStateEnum.库存不足.getRemark());
        }
        return checkStockVO.getData();
    }

    /**
     * 创建订单
     * @param dto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized ResponseData<BbbH5TradeDTO.IdDTO> orderSubmit(BbbH5TradeBuildDTO.DTO dto) {
        BbbH5StockAddressDTO.IdAndTypeDTO idDto = new BbbH5StockAddressDTO.IdAndTypeDTO();
        idDto.setId(dto.getAddressId());
        idDto.setJwtUserId(dto.getJwtUserId());
        BbbH5StockAddressDTO.IdAndTypeDTO idAndTypeDTO = new BbbH5StockAddressDTO.IdAndTypeDTO();
        idAndTypeDTO.setId(dto.getAddressId());
        idAndTypeDTO.setJwtUserId(dto.getJwtUserId());
        BbbH5StockAddressVO.ListVO addressVO = new BbbH5StockAddressVO.DetailVO();
        //根据id查询地址
        addressVO = bbcStockAddressRpc.innerdetailStockAddress(idAndTypeDTO);
        if(ObjectUtils.isEmpty(addressVO) || addressVO.getId() == null){
            throw new BusinessException("请重新添加收货地址");
        }
        //校验库存
        List<CommonStockDTO.InnerSkuGoodsInfoItem> goodsItemList = new ArrayList<>();
        for(BbbH5TradeBuildDTO.DTO.ProductData productData : dto.getProductData()){
            goodsItemList.add(new CommonStockDTO.InnerSkuGoodsInfoItem(null,productData.getGoodsSkuId(),productData.getQuantity()));
        }
        //根据SKU ID数组检查库存
        CommonStockVO.InnerListCheckStockVO checkStockVO = checkStock(goodsItemList);

        //查询店铺信息

        //购物车ID
        List<String> cartIdList = new ArrayList<>();

        //SKUID/购买数量
        List<CommonStockDTO.InnerChangeStockItem> subtractStockItems = new ArrayList<>();

        Set<BbbH5TradeGoodsDTO.ETO> tradeGoodsDTOSet = new HashSet<>();//交易商品数据

        BigDecimal shopProductAmount = BigDecimal.ZERO;//店铺商品总金额
        //店铺商品
        String shopId=null;
        if (ObjectUtils.isNotEmpty(dto.getShopId())){
            shopId=dto.getShopId();
        }
        //店铺商品
        for(BbbH5TradeBuildDTO.DTO.ProductData productData : dto.getProductData()){
            //查询商品、判断上下架)
            BbbH5GoodsInfoVO.InnerServiceVO innerServiceGoodsVO = bbcGoodsInfoRpc.innerServiceVO(productData.getGoodsSkuId(),dto);
            log.info("H5支付-取价前：" + JsonUtils.toJson(innerServiceGoodsVO));
            if(ObjectUtils.isEmpty(innerServiceGoodsVO) || innerServiceGoodsVO.getGoodsId() == null){
                throw new BusinessException("无商品数据或已下架");
            }else if(!innerServiceGoodsVO.getGoodsState().equals(GoodsStateEnum.已上架.getCode())){
                throw new BusinessException("商品已下架");
            }
            if (ObjectUtils.isEmpty(shopId)){
                shopId= innerServiceGoodsVO.getShopId();
            }
            //获取批发价
            BigDecimal wholesalePrice = innerServiceGoodsVO.getWholesalePrice();
            //计算此商品的阶梯价
            BigDecimal goodsStepPrice = null;
            if (ObjectUtils.isNotEmpty(innerServiceGoodsVO.getStepPrice())){
                goodsStepPrice= StepPriceCalculation(innerServiceGoodsVO.getStepPrice(), innerServiceGoodsVO.getSalePrice(), productData.getQuantity());
            }
            //单品小计金额
            BigDecimal productPrice = BigDecimal.ZERO;//单品小计金额
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
            log.info("支付-各价格：批发价格【" + wholesalePrice + "】，阶梯价【" + goodsStepPrice + "】，零售价【" + innerServiceGoodsVO.getSalePrice() + "】");
            shopProductAmount = shopProductAmount.add(productTotalPrice);
            CommonStockDTO.InnerChangeStockItem innerChangeStockItem = new CommonStockDTO.InnerChangeStockItem();
            innerChangeStockItem.setSkuId(productData.getGoodsSkuId());
            innerChangeStockItem.setQuantity(productData.getQuantity());
            subtractStockItems.add(innerChangeStockItem);

            //组装订单商品表信息
            BbbH5TradeGoodsDTO.ETO tradeGoodsDTO = fillTradeGoodsDTO(dto.getJwtUserId(),dto.getShopId(),productData.getQuantity(),innerServiceGoodsVO);
            tradeGoodsDTO.setSalePrice(productPrice);
            tradeGoodsDTOSet.add(tradeGoodsDTO);

            //购物车ID
            if(productData.getCartId() != null){
                cartIdList.add(productData.getCartId());
            }
        }
        log.info("H5支付-取价后：" + JsonUtils.toJson(tradeGoodsDTOSet));
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
            log.info("结算前-提交订单：" + JsonUtils.toJson(dto) + "\n商品:" + JsonUtils.toJson(tradeGoodsDTOSet));
            marketSettleService.settlementH5BBB(tradeGoodsDTOSet, dto);
            log.info("结算后-提交订单：" + JsonUtils.toJson(dto) + "\n商品:" + JsonUtils.toJson(tradeGoodsDTOSet));
        }catch (Exception e){
            log.error("bbb-h5营销结算异常:" + e.getMessage(), e);
        }
        //创建订单信息
        Trade trade = saveTrade(dto, addressVO, shopProductAmount.subtract(dto.getShopProductAmount()), dto.getDeliveryAmount());

        try{
            producerService.sendHttpMessage(trade.getId());
            //HttpProducerUtil.sendMessage(trade.getId());
            //producerService.sendTimeMsg("TradeTimeOutCancel",trade.getId().getBytes(),"5e10ac22eb5348dc928e425c1fbf2841",0);
            log.info("发送成功：Bbbh5TradeServiceImpl");
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
            bbcUserShoppingCarRpc.innerClearShopCarList(cartIdList);
        }

        return ResponseData.data(new BbbH5TradeDTO.IdDTO(trade.getId()));
    }
    private BigDecimal StepPriceCalculation(List<BbbH5GoodsInfoVO.GoodsStepPriceVO> stepPrice, BigDecimal SalePrice, Integer quantity) {
        stepPrice.sort((BbbH5GoodsInfoVO.GoodsStepPriceVO o1, BbbH5GoodsInfoVO.GoodsStepPriceVO o2) -> o1.getStartNum()- o1.getStartNum());
        BigDecimal goodsStepPrice=SalePrice;
        for (int i = 0; i < stepPrice.size(); i++) {
            if (stepPrice.get(i).getStartNum()>quantity||quantity>=stepPrice.get(i).getEndNum()){
                continue;
            }
            if (quantity>=stepPrice.get(i).getStartNum()&&quantity<stepPrice.get(i).getEndNum()){
                goodsStepPrice=stepPrice.get(i).getStepPrice();
                break;
            }
        }
        return goodsStepPrice;
    }

    public static void main(String[] args) {
        System.out.println(UuidUtil.getUuid());
    }

    private BigDecimal getDeliveryAmount(String shopId,List<BbbH5TradeBuildDTO.DTO.ProductData> productDataList,Integer deliveryType, String addressId) {
        BbbH5StockDeliveryDTO.DeliveryAmountDTO deliveryAmountDTO = new BbbH5StockDeliveryDTO.DeliveryAmountDTO();
        deliveryAmountDTO.setAddressId(addressId);
        deliveryAmountDTO.setDeliveryType(deliveryType);
        deliveryAmountDTO.setShopId(shopId);
        List<BbbH5StockDeliveryDTO.DeliverySKUDTO> deliverySKUDTOS = new ArrayList<>();
        for(BbbH5TradeBuildDTO.DTO.ProductData productData : productDataList){
            BbbH5StockDeliveryDTO.DeliverySKUDTO deliverySKUDTO = new BbbH5StockDeliveryDTO.DeliverySKUDTO();
            deliverySKUDTO.setSkuId(productData.getGoodsSkuId());
            deliverySKUDTO.setAmount(new BigDecimal(productData.getQuantity()));
            deliverySKUDTOS.add(deliverySKUDTO);
        }
        deliveryAmountDTO.setSkus(deliverySKUDTOS);
        BbbH5StockDeliveryVO.DeliveryAmountVO deliveryAmountVO = bbcStockDeliveryRpc.calculate(deliveryAmountDTO);
        if(ObjectUtils.isNotEmpty(deliveryAmountVO)){
            return deliveryAmountVO.getAmount();
        }
        return BigDecimal.ZERO;
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
    private void saveTradeGoods(String tradeId,Set<BbbH5TradeGoodsDTO.ETO> tradeGoodsDTOSet) {
        List<TradeGoods> tradeGoodsList = new ArrayList<>();
        for(BbbH5TradeGoodsDTO.ETO tradeGoodsDTO : tradeGoodsDTOSet){
            tradeGoodsDTO.setTradeId(tradeId);
            TradeGoods tradeGoods = new TradeGoods();
            BeanUtils.copyProperties(tradeGoodsDTO, tradeGoods);
            tradeGoodsList.add(tradeGoods);
        }
        if(ObjectUtils.isNotEmpty(tradeGoodsList)){
            tradeGoodsRepository.saveBatch(tradeGoodsList);
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
    private Trade saveTrade(BbbH5TradeBuildDTO.DTO dto, BbbH5StockAddressVO.ListVO addressVO, BigDecimal discountAmount, BigDecimal deliveryAmount) {
        Trade trade = new Trade();
        trade.setUserId(dto.getJwtUserId());
        trade.setShopId(dto.getShopId());
        trade.setTradeCode(TradeUtils.getTradeCode());
        trade.setTradeState(TradeStateEnum.待支付.getCode());
        trade.setCreateTime(LocalDateTime.now());
        trade.setPayType(dto.getPayType());
        trade.setDeliveryType(dto.getDeliveryType());
        trade.setInvoiceId(dto.getInvoiceId());
        trade.setInvoiceAddressId(dto.getInvoiceAddressId());
        //优惠券id
        if (dto.getUserCardVO() != null && StrUtil.isNotBlank(dto.getUserCardVO().getCardId())) {
            trade.setUserCardId(dto.getUserCardVO().getCardId());
        }
        if(dto.getDeliveryType() != null && dto.getDeliveryType().intValue() == TradeDeliveryTypeEnum.门店自提.getCode()){
            trade.setTakeGoodsCode(TradeUtils.getTakeGoodsCode());
        }
        if(dto.getDeliveryType().equals(TradeDeliveryTypeEnum.门店自提.getCode())){
            trade.setRecvPersonName(dto.getContactsName());
            trade.setRecvPhone(dto.getContactsPhone());
        }else{
            trade.setRecvAddresId(addressVO.getId());
            trade.setRecvPersonName(addressVO.getContactsName());
            trade.setRecvPhone(addressVO.getContactsPhone());
            trade.setRecvFullAddres(addressVO.getProvince()+addressVO.getCity()+addressVO.getCounty()+addressVO.getReals());
        }

        trade.setBuyerRemark(dto.getBuyerRemark());
        trade.setGoodsAmount(dto.getShopProductAmount());
        trade.setDeliveryAmount(deliveryAmount);
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
    private BbbH5TradeGoodsDTO.ETO fillTradeGoodsDTO(String userId,String shopId,Integer quantity, BbbH5GoodsInfoVO.InnerServiceVO innerServiceGoodsVO) {
        BbbH5TradeGoodsDTO.ETO tradeGoodsDTO = new BbbH5TradeGoodsDTO.ETO();
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
        tradeGoodsDTO.setSalePrice(innerServiceGoodsVO.getSalePrice());
        tradeGoodsDTO.setPayAmount(innerServiceGoodsVO.getSalePrice());
        tradeGoodsDTO.setCommentFlag(TradeTrueFalseEnum.是.getCode());
        return tradeGoodsDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseData<Void> orderPay(BbbH5TradePayBuildDTO.ETO dto) {
        String openid = dto.getJwtWxOpenid();
        if(StringUtils.isEmpty(openid)){
            if(StringUtils.isNotEmpty(dto.getOpenid())){
                openid = dto.getOpenid();
            }else{
                throw new BusinessException("未获取微信用户信息");
            }
        }
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
        //查询支付数据 TODO
        QueryWrapper<TradePay> tradePayWrapper = new QueryWrapper<>();
        tradePayWrapper.eq("trade_id",trade.getId());
        TradePay tradePay = tradePayRepository.getOne(tradePayWrapper);

        if(ObjectUtils.isNotEmpty(tradePay) && StringUtils.isNotEmpty(tradePay.getPayInfo())){
            log.info("1tradePay.getPayInfo():"+tradePay.getPayInfo());
            JSONObject json = JSON.parseObject(tradePay.getPayInfo());
            return ResponseData.data(json);
        }

        if(trade.getPayType().equals(TradePayTypeEnum.线下支付.getCode())){
            QueryWrapper<TradePayOffline> tradePayOfflineQueryWrapper = new QueryWrapper<>();
            tradePayOfflineQueryWrapper.eq("pay_id",tradePay.getId());
            TradePayOffline tradePayOffline = tradePayOfflineRepository.getOne(tradePayOfflineQueryWrapper);
            if(ObjectUtils.isEmpty(tradePayOffline)){
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
        }else if(trade.getPayType().equals(TradePayTypeEnum.微信小程序支付.getCode())){
            log.info("tradePay.getToken():"+tradePay.getToken());
            QRCodePaymentCommitResponse responseData = goPayment(openid,trade,tradePay);

            if (ObjectUtils.isNotEmpty(responseData) && responseData.isSuccess()) {
                log.info("[doPay] 接口调用成功，开始处理业务逻辑");
                log.info("tradeNo=" + responseData.getTradeNo());//保存第三方交易号
                log.info("payInfo=" + responseData.getPayInfo());
                log.info("prePayId=" + responseData.getPrePayId());
                JSONObject payInfoJson = payInfoToJSON(responseData.getPayInfo());

                //更新保存支付交易号
                tradePay.setPayCode(responseData.getTradeNo());
                tradePay.setToken(responseData.getToken());
                if(ObjectUtils.isNotEmpty(IpUtil.getHttpServletRequest())){
                    log.info("getHttpServletRequest{}",IpUtil.getHttpServletRequest());
                    tradePay.setClientIp(IpUtil.getRemoteHost(IpUtil.getHttpServletRequest()));
                }
                tradePay.setPayInfo(payInfoJson.toJSONString());

                log.info("2tradePay.getPayInfo():"+tradePay.getPayInfo());
                tradePayRepository.saveOrUpdate(tradePay);

                return ResponseData.data(payInfoJson);
            }

        }
        return ResponseData.fail("支付失败");
    }

    private QRCodePaymentCommitResponse goPayment(String openid,Trade trade,TradePay tradePay) {
        try{
            InputStream is = getClass().getClassLoader().getResourceAsStream(MerchantBaseEnum.merchant_hly_CertPath.getValue());
            try {
                ClassPathResource resource = new ClassPathResource(MerchantBaseEnum.merchant_hly_CertPath.getValue());
                InputStream fis = resource.getInputStream();
            }catch (Exception e){
                log.error(e.getMessage(),e);
            }
            BossClient client = new BossClient(MerchantBaseEnum.merchant_hly_CertPath.getValue(), MerchantBaseEnum.merchant_hly_CertPass.getValue(), MerchantBaseEnum.serverUrl.getValue());
            //预下单
            EntOffLinePaymentResponse preOrderResponse = doPreOrder(client,trade);
            if(ObjectUtils.isEmpty(preOrderResponse)){
                log.info("doPreOrder is null");
                return null;
            }
            if (preOrderResponse.isSuccess()) {
//                if(StringUtils.isEmpty(preOrderResponse.getToken())){
//                    preOrderResponse.setToken(tradePay.getToken());
//                }
                //支付
                QRCodePaymentCommitResponse payResponse = doPay(openid,client, preOrderResponse);
                if(ObjectUtils.isEmpty(payResponse)){
                    log.info("doPay is null");
                    return null;
                }
                if (payResponse.isSuccess()) {
                    return payResponse;
                }else{
                    log.info("[doPay] 接口调用失败"+payResponse.getReturnMessage()+payResponse.getReturnCode());
                    return null;
                }
            }else{
                log.info("[doPreOrder] 接口调用失败:"+preOrderResponse.getReturnMessage()+preOrderResponse.getReturnCode());
                return null;
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private JSONObject payInfoToJSON(String payInfo) {
        String[] str = payInfo.split("\\|");
        JSONObject payInfoJson = new JSONObject(true);
        for(String s : str){
            payInfoJson.put(s.substring(0,s.indexOf("=")),s.substring(s.indexOf("=")+1));
        }
        return payInfoJson;
    }

    @Override
    @Transactional
    public PageData<BbbH5TradeListVO.tradeVO> tradeListPageData(BbbH5TradeQTO.TradeList qto) {
        QueryWrapper<BbbH5TradeQTO.TradeList> wrapper = new QueryWrapper<>();
        wrapper.and(i -> i.eq("t.`user_id`",qto.getJwtUserId()));
        wrapper.isNull("t.`is_hide`");
        if(ObjectUtils.isNotEmpty(qto.getKeyword())){
            wrapper.and(i -> i.
                    like("tg.`goods_name`",qto.getKeyword()).or().
                    eq("tg.`goods_no`",qto.getKeyword()).or().
                    eq("t.`trade_code`",qto.getKeyword()));
        }
        //查询2b的订单列表
        wrapper.and(i->i.eq("t.`source_type`",TradeSourceTypeEnum._2B.getCode()));
        if(ObjectUtils.isNotEmpty(qto.getTradeState())){
            if(qto.getTradeState()==60){
                wrapper.and(i->i.eq("t.`trade_state`",40));
                wrapper.groupBy("t.`id`");
                wrapper.having("bc > cc");
            }else if (qto.getTradeState()==70){
                wrapper.and(i->i.eq("t.`trade_state`",40));
                wrapper.having("rightsId is null");
            }
            else {
                wrapper.and(i -> i.eq("t.`trade_state`", qto.getTradeState()));//交易状态,不传则查所有状态数据
            }
        }
        wrapper.groupBy("t.`id`");
        wrapper.orderByDesc("cdate");
        IPage<BbbH5TradeListVO.tradeVO> pager = MybatisPlusUtil.pager(qto);
        wrapper.last("limit "+pager.offset()+","+pager.getSize());

        //todo 欧阳
        List<BbbH5TradeListVO.tradeVO> tradeVOList=tradeRepository.BbbH5selectTradeListPage(wrapper);

        List<BbbH5TradeListVO.tradeVO> voList = new ArrayList<>();
        for(BbbH5TradeListVO.tradeVO tradeVO : tradeVOList){
            //查询退款表
            QueryWrapper<TradeRights> query = MybatisPlusUtil.query();
            query.and(i->i.eq("trade_id",tradeVO.getId()));
            query.last("limit 0,1");
            TradeRights one = iTradeRightsRepository.getOne(query);
            if (ObjectUtils.isNotEmpty(one)){
                tradeVO.setRightsState(one.getState());
            }
            //查询店铺信息
            BbbH5ShopQTO.InnerShopQTO innerShopQTO = new BbbH5ShopQTO.InnerShopQTO();
            innerShopQTO.setShopId(tradeVO.getShopId());
            BbbH5ShopVO.InnerDetailVO innerDetailVO = iBbbH5ShopRpc.innerDetailShop(innerShopQTO);
            if (ObjectUtils.isNotEmpty(innerDetailVO)){
                tradeVO.setShopName(innerDetailVO.getShopName());
            }
            //根据交易ID查询交易商品集合
            fillTradeVO(tradeVO);
            if (tradeVO.getPayType().equals(TradePayTypeEnum.线下支付.getCode())){
                //获取线下支付表
                QueryWrapper<TradePayOffline> query2 = MybatisPlusUtil.query();
                query2.and(i->i.eq("trade_id",tradeVO.getId()));
                TradePayOffline one2 = tradePayOfflineRepository.getOne(query2);
                if (ObjectUtils.isNotEmpty(one2)){
                    tradeVO.setOfflineState(one2.getVerifyState()).setVerifyRemark(one2.getVerifyRemark());
                }
            }
            voList.add(tradeVO);
        }

        return new PageData<>(voList, qto.getPageNum(), qto.getPageSize(),voList.size());
    }

    @Override
    public ResponseData<BbbH5TradeListVO.tradeVO> orderDetail(BbbH5TradeDTO.IdDTO dto) {
        BbbH5TradeListVO.tradeVO tradeVO = new BbbH5TradeListVO.tradeVO();
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
        if(tradeVO.getTradeState().equals(TradeStateEnum.待支付.getCode())){
            tradeVO.setPayDeadline(tradeVO.getCreateTime().plusMinutes(Common.PAYMENT_TIME_OUT));
        }
        if (tradeVO.getPayType().equals(TradePayTypeEnum.线下支付.getCode())){
            //获取线下支付表
            QueryWrapper<TradePayOffline> query2 = MybatisPlusUtil.query();
            query2.and(i->i.eq("trade_id",tradeVO.getId()));
            TradePayOffline one2 = tradePayOfflineRepository.getOne(query2);
            if (ObjectUtils.isNotEmpty(one2)){
                tradeVO.setOfflineState(one2.getVerifyState()).setVerifyRemark(one2.getVerifyRemark());
            }
        }
        //查询退款表
        QueryWrapper<TradeRights> query = MybatisPlusUtil.query();
        query.and(i->i.eq("trade_id",tradeVO.getId()));
        query.last("limit 0,1");
        TradeRights one = iTradeRightsRepository.getOne(query);
        if (ObjectUtils.isNotEmpty(one)){
            tradeVO.setRightsState(one.getState());
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

        return ResponseData.data(tradeVO);
    }

    private void SetInvoice(BbbH5TradeListVO.tradeVO tradeVO) {
        TradeInvoice byId = iTradeInvoiceRepository.getById(tradeVO.getInvoiceId());
        if (ObjectUtils.isNotEmpty(byId)){
            BbbH5TradeListVO.tradeVO.Invoice invoice = new BbbH5TradeListVO.tradeVO.Invoice();
            invoice.setInvoiceId(byId.getId()).
                    setInvoiceType(byId.getInvoiceType()).
                    setFirmName(byId.getFirmName()).
                    setTaxNumber(byId.getTaxNumber());
            tradeVO.setInvoiceInfo(invoice);
        }
    }
    private void fillUserInfo(BbbH5TradeListVO.tradeVO tradeVO) {
        BbbH5UserVO.InnerUserInfoVO innerUserInfoVO = bbcUserRpc.innerGetUserInfo(tradeVO.getUserId());
        if(ObjectUtils.isNotEmpty(innerUserInfoVO)){
            tradeVO.setUserName(innerUserInfoVO.getUserName());
        }
    }

    private void fillShop(BbbH5TradeListVO.tradeVO tradeVO) {
        BbbH5ShopQTO.InnerShopQTO innerShopQTO = new BbbH5ShopQTO.InnerShopQTO();
        innerShopQTO.setShopId(tradeVO.getShopId());
        BbbH5ShopVO.InnerDetailVO innerDetailVO = iBbbH5ShopRpc.innerDetailShop(innerShopQTO);
        if(ObjectUtils.isNotEmpty(innerDetailVO)){
            tradeVO.setShopName(innerDetailVO.getShopName());
        }
    }

    private void fillLogisticsCompany(BbbH5TradeListVO.tradeVO tradeVO) {
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


    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseData<Void> orderConfirmReceipt(BbbH5TradeDTO.IdDTO dto) {
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
        BbbH5UserIntegralDTO.ETO eto = new BbbH5UserIntegralDTO.ETO();
        eto.setTradeAmount(trade.getTradeAmount());
        eto.setFromType(20);
        eto.setUserId(trade.getUserId());
        eto.setFromId(trade.getId());
        iBbbH5UserIntegralRpc.addUserTradeIntergral(eto);

        return ResponseData.success();
    }

    @Override
    public ResponseData<Void> deliveryAmount(BbbH5TradeBuildDTO.DTO dto) {
        BbbH5StockDeliveryDTO.DeliveryAmountDTO deliveryAmountDTO = new BbbH5StockDeliveryDTO.DeliveryAmountDTO();
        deliveryAmountDTO.setAddressId(dto.getAddressId());
        deliveryAmountDTO.setDeliveryType(dto.getDeliveryType());
        deliveryAmountDTO.setShopId(dto.getShopId());
        List<BbbH5StockDeliveryDTO.DeliverySKUDTO> deliverySKUDTOS = new ArrayList<>();
        for(BbbH5TradeBuildDTO.DTO.ProductData goodsInfoVO : dto.getProductData()){
            BbbH5StockDeliveryDTO.DeliverySKUDTO deliverySKUDTO = new BbbH5StockDeliveryDTO.DeliverySKUDTO();
            deliverySKUDTO.setSkuId(goodsInfoVO.getGoodsSkuId());
            deliverySKUDTO.setAmount(new BigDecimal(goodsInfoVO.getQuantity()));
            deliverySKUDTOS.add(deliverySKUDTO);
        }
        deliveryAmountDTO.setSkus(deliverySKUDTOS);
        BbbH5StockDeliveryVO.DeliveryAmountVO deliveryAmountVO = bbcStockDeliveryRpc.calculate(deliveryAmountDTO);
        if(ObjectUtils.isNotEmpty(deliveryAmountVO)){
            Map result = new HashMap();
            result.put("deliveryAmount",deliveryAmountVO.getAmount());
            return ResponseData.data(result);
        }

        return ResponseData.fail();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String payNotify(BbbH5TradeResultNotifyVO.notifyVO notifyVO) {
        log.info("订单支付回调:"+notifyVO.toString());
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
            JSONObject responseJson = new JSONObject();
                System.out.println("验签成功，开始处理业务逻辑");
                //判断支付结果
                if(!resultNotify.getStatus().equals(TradePayResultStateEnum.SUCCESS.getRemark())){
                    log.error(resultNotify.getFailMsg());
                    responseJson.put("result",TradePayResultStateEnum.FAILED.getRemark());
                    return responseJson.toString();
                }
                //根据交易订单编号修改订单状态
                return paySuccess(resultNotify.getOrderId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据交易订单编号修改状态
     * @param tradeCode
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
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
            //添加常购商品（生产环境,测试环境在提交订单的位置做了一个副本，以便测通功能）
            this.addFrequent(null,trade.getId());
            commonMarketCardService.useCard(trade.getUserCardId(), trade.getUserId());
            responseJson.put("result",TradePayResultStateEnum.SUCCESS.getRemark());

            return responseJson.toString();
        }
        responseJson.put("result",TradePayResultStateEnum.FAILED.getRemark());
        return responseJson.toString();
    }

    private void addFrequent(List<TradeGoods> tradeGoodsList,String tradeId){
        //支付成功-把成功的订单商品信息->常购清单
        if(null == tradeGoodsList){
            QueryWrapper<TradeGoods> tradeGoodsQueryWrapper = MybatisPlusUtil.query();
            tradeGoodsQueryWrapper.in("trade_id",tradeId);
            tradeGoodsList = tradeGoodsRepository.list(tradeGoodsQueryWrapper);
        }
        if(ObjectUtils.isNotEmpty(tradeGoodsList)){
            BbbH5UserFrequentGoodsV2DTO.ETO frequentGoodsV2DTO = new BbbH5UserFrequentGoodsV2DTO.ETO();
            for(TradeGoods tradeGoods: tradeGoodsList){
                BbbH5UserFrequentGoodsV2DTO.OneItem oneItem = new BbbH5UserFrequentGoodsV2DTO.OneItem();
                oneItem.setGoodsId(tradeGoods.getGoodsId());
                oneItem.setSkuId(tradeGoods.getSkuId());
                oneItem.setQuantity(tradeGoods.getQuantity());
                oneItem.setShopId(tradeGoods.getShopId());
                oneItem.setMerchantId(tradeGoods.getMerchantId());
                if(StringUtils.isBlank(frequentGoodsV2DTO.getJwtUserId())){
                    frequentGoodsV2DTO.setJwtUserId(tradeGoods.getUserId());
                }
                frequentGoodsV2DTO.getSkuList().add(oneItem);
            }
            bbbH5UserFrequentV2Rpc.addMore(frequentGoodsV2DTO);
        }
    }

    /**
     * 隐藏订单
     * @param dto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseData<Void> orderHide(BbbH5TradeDTO.IdDTO dto) {
        Trade trade = tradeRepository.getById(dto.getId());
        if(ObjectUtils.isEmpty(trade)){
            throw new BusinessException("无订单信息");
        }
        trade.setIsHide(TradeHideEnum.隐藏.getCode());
        tradeRepository.saveOrUpdate(trade);

        return ResponseData.success();
    }

    /**
     * 取消订单
     * @param dto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseData<Void> orderCancel(BbbH5TradeCancelDTO.CancelDTO dto) {
        if (ObjectUtils.isEmpty(dto.getRemark())){
            throw new BusinessException("请输入原因");
        }
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
    private TradeRights inset(Trade trade, BbbH5TradeCancelDTO.CancelDTO dto, TradeCancel tradeCancel) {
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

    private void fillTradeCancel(BbbH5TradeCancelDTO.CancelDTO dto, TradeCancel tradeCancel, Trade trade, String tradePayId,Integer cancelState,Integer cancelRefundState) {
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
     * @param tradeId
     * @return
     */
    private boolean cancelTradeReturnStock(String tradeId) {
        List<CommonStockDTO.InnerChangeStockItem> goodsItemList = new ArrayList<>();
        fillChangeStockItem(tradeId, goodsItemList);
        return commonStockRpc.innerPlusStockForTrade(goodsItemList);
    }

    @Override
    public List<BbbH5TradeListVO.stateCountVO> tradeStateCount(BbbH5TradeDTO.IdDTO dto) {

        QueryWrapper<BbbH5TradeQTO> tradeWrapper = new QueryWrapper<>();
        tradeWrapper.and(i -> i.eq("t.`user_id`",dto.getJwtUserId()));
        tradeWrapper.and(i->i.eq("t.`source_type`",20));
        tradeWrapper.in("trade_state",Arrays.asList(TradeStateEnum.待支付.getCode(),TradeStateEnum.待收货.getCode(),TradeStateEnum.待发货.getCode()));
        tradeWrapper.groupBy("trade_state");
        //todo 欧阳
        List<BbbH5TradeListVO.stateCountVO> stateCountVO = tradeRepository.BbbH5selectTradeStateCount(tradeWrapper);

        return stateCountVO;
    }

    @Override
    public List<BbbH5TradeListVO.UseCard> useCard(BbbH5TradeDTO.UseCard dto) {
        //查询会员领取的所有优惠卷
        QueryWrapper<BbbH5TradeDTO.UseCard> query = MybatisPlusUtil.query();
        query.and(i->i.eq("td.`user_id`",dto.getJwtUserId()));
        query.and(i->i.gt("td.`valid_end_time`",LocalDateTime.now()).lt("td.`valid_start_time`",LocalDateTime.now()));
        if (StringUtils.isEmpty(dto.getShopId())){
            throw new BusinessException("请传入shopId");
        }
        query.and(i->i.eq("td.`shop_id`",dto.getShopId()));
        List<BbbH5TradeListVO.UseCard> useCards = iMarketMerchantCardUsersRepository.selectH5UseCard(query);
        BbbH5ShopVO.InnerDetailVO innerDetailVO = iBbbH5ShopRpc.innerDetailShop(new BbbH5ShopQTO.InnerShopQTO(dto.getShopId()));
        if (ObjectUtils.isNotEmpty(useCards)) {
            for (BbbH5TradeListVO.UseCard useCard : useCards) {
                if (ObjectUtils.isNotEmpty(innerDetailVO)){
                    useCard.setShopName(innerDetailVO.getShopName());
                }
                QueryWrapper<MarketMerchantCardGoods> query1 = MybatisPlusUtil.query();
                query1.and(i->i.eq(ObjectUtils.isNotEmpty(useCard.getCardId()),"card_id",useCard.getCardId()));
                List<MarketMerchantCardGoods> list = iMarketMerchantCardGoodsRepository.list(query1);
                BigDecimal total=new BigDecimal(0);
                if (ObjectUtils.isNotEmpty(list)){
                    for (MarketMerchantCardGoods marketMerchantCardGoods : list) {
                        for (BbbH5TradeDTO.UseCardGoods useCardGoods : dto.getGoodsInfo()) {
                            if (marketMerchantCardGoods.getGoodsId().equals(useCardGoods.getGoodsId())){
                                total=total.add(useCardGoods.getTotalAmount());
                            }
                        }

                    }
                }
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
    public void deleteTrade(BbbH5TradeDTO.IdDTO dto) {
        if (StringUtils.isEmpty(dto.getId())){
            throw new BusinessException("请传入订单ID");
        }
        Trade trade = tradeRepository.getById(dto.getId());
        if (trade.getUserId().equals(dto.getJwtUserId())){
            throw new BusinessException("此订单不是您的订单，不能操作");
        }
        if (ObjectUtils.isNotEmpty(trade)){
            throw new BusinessException("订单不存在");
        }
        trade.setIsHide(TradeHideEnum.隐藏.getCode());
        tradeRepository.updateById(trade);
    }

    @Override
    public List<BbbH5TradeListVO.InnerGoodsCompareTo> innerCommpareTo(BbbH5TradeDTO.innerCommpareTo dto) {
        if (dto.getCompareToType() == 10){
            dto.setCompareToType(20);
        }else if (dto.getCompareToType() == 20){
            dto.setCompareToType(10);
        }
        if (ObjectUtils.isNotEmpty(dto.getGoodsIds())){
            if (ObjectUtils.isNotEmpty(dto.getCompareToType())){
                if (dto.getCompareToType()==10){
                    //评分
                    QueryWrapper<BbbH5TradeDTO.innerCommpareTo> query = MybatisPlusUtil.query();
                    query.and(i->i.in("trade_goods_id",dto.getGoodsIds()));
                    query.groupBy("trade_goods_id");
                    List<BbbH5TradeListVO.InnerCompareTo> innerCompareTos = iTradeCommentRepository.selectBbbH5CompareTo(query);
                    if (ObjectUtils.isNotEmpty(innerCompareTos)){
                        if (ObjectUtils.isNotEmpty(dto.getCompareToMode())){
                            if (dto.getCompareToMode()==10){
                                //评分升序
                                innerCompareTos.sort(Comparator.comparing(BbbH5TradeListVO.InnerCompareTo::getGrade));
                                List<BbbH5TradeListVO.InnerGoodsCompareTo> list=new ArrayList<>();
                                int num=0;
                                for (int i = 0; i < innerCompareTos.size(); i++) {
                                    num++;
                                    list.add(new BbbH5TradeListVO.InnerGoodsCompareTo(innerCompareTos.get(i).getId(),num));
                                }
                                for (String s : dto.getGoodsIds()) {
                                    for( BbbH5TradeListVO.InnerGoodsCompareTo string : list) {
                                        if (!s.equals(string.getId())){
                                            list.add(new BbbH5TradeListVO.InnerGoodsCompareTo(s,num++));
                                            break;
                                        }
                                    }
                                }
                                return list;
                            }else if (dto.getCompareToMode()==20){
                                //评分降序
                                innerCompareTos.sort(Comparator.comparing(BbbH5TradeListVO.InnerCompareTo::getGrade).reversed());
                                List<BbbH5TradeListVO.InnerGoodsCompareTo> list=new ArrayList<>();
                                int num=0;
                                for (int i = 0; i < innerCompareTos.size(); i++) {
                                    num++;
                                    list.add(new BbbH5TradeListVO.InnerGoodsCompareTo(innerCompareTos.get(i).getId(),num));
                                }
                                for (String s : dto.getGoodsIds()) {
                                    for (BbbH5TradeListVO.InnerGoodsCompareTo string : list) {
                                        if (!s.equals(string.getId())){
                                            list.add(new BbbH5TradeListVO.InnerGoodsCompareTo(s,num++));
                                            break;
                                        }
                                    }
                                }
                                return list;
                            }
                        }
                    }else {
                        List<BbbH5TradeListVO.InnerGoodsCompareTo> list=new ArrayList<>();
                        int num=0;
                        for (String s : dto.getGoodsIds()) {
                            num++;
                            list.add(new BbbH5TradeListVO.InnerGoodsCompareTo(s,num));
                        }
                        return list;
                    }

                }else if (dto.getCompareToType()==20){
                    //销量
                    QueryWrapper<BbbH5TradeDTO.innerCommpareTo> query = MybatisPlusUtil.query();
                    query.and(i->i.in("goods_id",dto.getGoodsIds()));
                    List<BbbH5TradeListVO.InnerCompareToCount> innerCompareToCounts = iTradeCommentRepository.selectBbbH5CompareToCount(query);
                    if (ObjectUtils.isNotEmpty(innerCompareToCounts)) {
                        if (dto.getCompareToMode() == 10) {
                            //销量升序
                            innerCompareToCounts.sort(Comparator.comparing(BbbH5TradeListVO.InnerCompareToCount::getCount));
                            List<BbbH5TradeListVO.InnerGoodsCompareTo> list=new ArrayList<>();
                            int num=0;
                            for (int i = 0; i < innerCompareToCounts.size(); i++) {
                                num++;
                                list.add(new BbbH5TradeListVO.InnerGoodsCompareTo(innerCompareToCounts.get(i).getId(),num));
                            }
                            if (ObjectUtils.isNotEmpty(list)) {
                                for (String s : dto.getGoodsIds()) {
                                    for (BbbH5TradeListVO.InnerGoodsCompareTo string : list) {
                                        if (!s.equals(string.getId())){
                                            list.add(new BbbH5TradeListVO.InnerGoodsCompareTo(s,num++));
                                            break;
                                        }
                                    }
                                }
                            }else {
                                List<BbbH5TradeListVO.InnerGoodsCompareTo> list1=new ArrayList<>();
                                int num1=0;
                                for (String s : dto.getGoodsIds()) {
                                    num++;
                                    list1.add(new BbbH5TradeListVO.InnerGoodsCompareTo(s,num));
                                }
                                return list1;
                            }
                            return list;
                        } else if (dto.getCompareToMode() == 20) {
                            //销量降序
                            innerCompareToCounts.sort(Comparator.comparing(BbbH5TradeListVO.InnerCompareToCount::getCount).reversed());
                            List<BbbH5TradeListVO.InnerGoodsCompareTo> list=new ArrayList<>();
                            int num=0;
                            for (int i = 0; i < innerCompareToCounts.size(); i++) {
                                num++;
                                list.add(new BbbH5TradeListVO.InnerGoodsCompareTo(innerCompareToCounts.get(i).getId(),num));
                            }
                            if (ObjectUtils.isEmpty(list)){
                                List<BbbH5TradeListVO.InnerGoodsCompareTo> list1=new ArrayList<>();
                                int num1=0;
                                for (String s : dto.getGoodsIds()) {
                                    num++;
                                    list1.add(new BbbH5TradeListVO.InnerGoodsCompareTo(s,num));
                                }
                                return list1;
                            }
                            for (String s : dto.getGoodsIds()) {
                                for (BbbH5TradeListVO.InnerGoodsCompareTo string : list) {
                                    if (!s.equals(string.getId())){
                                        list.add(new BbbH5TradeListVO.InnerGoodsCompareTo(s,num++));
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
    public Integer myMerchantCard(BaseDTO dto) {
        QueryWrapper<MarketMerchantCardUsers> query = MybatisPlusUtil.query();
        query.and(i->i.eq("user_id",dto.getJwtUserId()));
        int count = iMarketMerchantCardUsersRepository.count(query);
        return count;
    }

    @Override
    public void offlinePay(BbbH5TradeDTO.OfflinePayDTO dto) {
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
    private void updateTradeOffline(BbbH5TradeDTO.OfflinePayDTO dto, TradePayOffline tradePayOffline) {
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
    public ResponseData<BbbH5TradeListVO.OfflinePayVO> offlineDetail(BbbH5TradeDTO.IdDTO dto) {
        BbbH5TradeListVO.OfflinePayVO offlinePayVO = new BbbH5TradeListVO.OfflinePayVO();
        SettingsReceiptVO.DetailVO detailVO = iSettingsReceiptRpc.detailSettingsReceipt(dto);
        BbbH5TradeListVO.OfflinePayVO.OfflinePayVOPl offlinePayVOPl = new BbbH5TradeListVO.OfflinePayVO.OfflinePayVOPl();
        if (ObjectUtils.isNotEmpty(offlinePayVO)){
            BeanUtils.copyProperties(detailVO,offlinePayVOPl);
        }
        if (StringUtils.isNotEmpty(dto.getId())){
            Trade trade = tradeRepository.getById(dto.getId());
            if (ObjectUtils.isNotEmpty(trade)){
                offlinePayVOPl.setTradeId(dto.getId()).setTradeAmount(trade.getTradeAmount());
            }
        }
        offlinePayVO.setOfflinePayVOPl(offlinePayVOPl);
        //获取线下支付表
        QueryWrapper<TradePayOffline> query = MybatisPlusUtil.query();
        query.and(i->i.eq("trade_id",dto.getId()));
        TradePayOffline one2 = tradePayOfflineRepository.getOne(query);
        if (ObjectUtils.isNotEmpty(one2)){
            BbbH5TradeListVO.OfflinePayVO.OfflinePayVOPp offlinePayVOPp = new BbbH5TradeListVO.OfflinePayVO.OfflinePayVOPp();
            offlinePayVOPp.setPayName(one2.getAccountName()).
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
            offlinePayVOPp.setTransImage(strings);
            offlinePayVO.setOfflinePayVOPp(offlinePayVOPp);
        }

        return ResponseData.data(offlinePayVO);
    }

    private TradePayOffline saveTradeOffline(BbbH5TradeDTO.OfflinePayDTO dto, Trade trade, TradePay tradePay) {
        QueryWrapper<TradePayOffline> query = MybatisPlusUtil.query();
        query.and(i->i.eq("trade_id",trade.getId()));
        List<TradePayOffline> list = tradePayOfflineRepository.list(query);
        if (list.size()>0){
            throw new BusinessException("此订单正在线下支付，等待审核");
        }
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
     * 组装TradeVO、tradeGoodsVO数据
     * @param tradeVO
     */
    private void fillTradeVO(BbbH5TradeListVO.tradeVO tradeVO) {
        QueryWrapper<TradeGoods> tradeGoodsQueryWrapper = new QueryWrapper<>();
        tradeGoodsQueryWrapper.eq("trade_id",tradeVO.getId());
        List<TradeGoods> tradeGoodsList = tradeGoodsRepository.list(tradeGoodsQueryWrapper);
        List<BbbH5TradeListVO.TradeGoodsVO> tradeGoodsVOS = new ArrayList<>();
        for(TradeGoods tradeGoods : tradeGoodsList){
            BbbH5TradeListVO.TradeGoodsVO tradeGoodsVO = new BbbH5TradeListVO.TradeGoodsVO();
            BeanUtils.copyProperties(tradeGoods, tradeGoodsVO);
            tradeGoodsVO.setShopName(tradeVO.getShopName());
            QueryWrapper<TradeComment> query = MybatisPlusUtil.query();
            query.and(i->i.eq("trade_goods_id",tradeGoods.getId()));
            List<TradeComment> list = iTradeCommentRepository.list(query);
            if (ObjectUtils.isNotEmpty(list)){
                tradeGoodsVO.setCommentFlag(20);
            }else {
                tradeGoodsVO.setCommentFlag(10);
            }
            tradeGoodsVOS.add(tradeGoodsVO);
        }
        tradeVO.setTradeGoodsVOS(tradeGoodsVOS);
    }

    @DubboReference
    private ICommonShopRpc iCommonShopRpc;
    public EntOffLinePaymentResponse doPreOrder(BossClient client,Trade trade) throws Exception {
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
        if(ObjectUtils.isNotEmpty(IpUtil.getHttpServletRequest())){
            request.setClientIP(IpUtil.getRemoteHost(IpUtil.getHttpServletRequest()));
        }else{
            request.setClientIP("127.0.0.1");
        }
        request.setRequestId(UuidUtil.getUuid());
        request.setMerchantId(merchantId);
        request.setMerchantName("商户展示名称");
        request.setOrderId(trade.getTradeCode());
        log.info("trade.getTradeCode():"+trade.getTradeCode());
        request.setOrderTime(orderTime);
        //TODO 测试1分钱
        //request.setTotalAmount("1");
        request.setTotalAmount(""+trade.getTradeAmount().multiply(new BigDecimal("100")).intValue());
        request.setCurrency("CNY");
        request.setBackParam("保留数据1");
        request.setSplitType("1");
        request.setValidUnit("00");
        request.setValidNum("15");
        //WECHATAPP:微信APP支付ALIPAYAPP 支付宝app支付
        //request.setTradeType("WECHATAPP");
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderSeqNo("001");//订单序号 订单顺序号,只能上送数字
        //orderDetail.setOrderAmt("1");
        orderDetail.setOrderAmt(""+trade.getTradeAmount().multiply(new BigDecimal("100")).intValue());//子订单金额，以分为单位，等于子订单支付金额与子订单手续费金额和,如果是按比例分账，此信息域上送分账比例，如40.75%上送为4075，我方收到后做相应缩小
        orderDetail.setShareFee("0");//分账手续费，如果上送，将按照此手续费计算，商户平台不需要指定手续费时，默认为0
        orderDetail.setDetailOrderId(trade.getChildTradeId());//子订单号 商户的子订单号，保证全局唯一，不能与订单号重复
        String childMerchantId = iCommonShopRpc.lakalaNoByShopId(trade.getShopId());
        if (com.baomidou.mybatisplus.core.toolkit.StringUtils.isNotBlank(childMerchantId)){
            orderDetail.setRcvMerchantId(childMerchantId);//收款方商户编号-子商户
        }
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
     * @param client
     * @param entOffLinePaymentResponse
     */
    private QRCodePaymentCommitResponse doPay(String openid,BossClient client, EntOffLinePaymentResponse entOffLinePaymentResponse) throws Exception {
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
        if(ObjectUtils.isNotEmpty(IpUtil.getHttpServletRequest())){
            request.setClientIP(IpUtil.getRemoteHost(IpUtil.getHttpServletRequest()));
        }else{
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
        request.setOpenId("ouyXz5OWt8cUakLNM-NXbDgg-4aM"); // 微信支付（扫码、APP、公众号、小程序）必传
        request.setOpenId(openid);
        return client.execute(request);
    }

}
