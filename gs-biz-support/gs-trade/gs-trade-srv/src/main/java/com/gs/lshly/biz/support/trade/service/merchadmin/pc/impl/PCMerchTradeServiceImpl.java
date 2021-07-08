package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.trade.entity.Trade;
import com.gs.lshly.biz.support.trade.entity.TradeCancel;
import com.gs.lshly.biz.support.trade.entity.TradeDelivery;
import com.gs.lshly.biz.support.trade.entity.TradeGoods;
import com.gs.lshly.biz.support.trade.entity.TradePay;
import com.gs.lshly.biz.support.trade.entity.TradeRights;
import com.gs.lshly.biz.support.trade.entity.TradeRightsGoods;
import com.gs.lshly.biz.support.trade.mapper.TradeMapper;
import com.gs.lshly.biz.support.trade.repository.ITradeDeliveryRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.ITradePayRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeRightsGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeRightsRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradeService;
import com.gs.lshly.biz.support.trade.utils.TradeUtils;
import com.gs.lshly.common.enums.TradeCancelApplyTypeEnum;
import com.gs.lshly.common.enums.TradeDeliveryTypeEnum;
import com.gs.lshly.common.enums.TradePayTypeEnum;
import com.gs.lshly.common.enums.TradeRightsEndStateEnum;
import com.gs.lshly.common.enums.TradeRightsTypeEnum;
import com.gs.lshly.common.enums.TradeStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeCancelDTO;
import com.gs.lshly.common.struct.common.CommonLogisticsCompanyVO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.common.CommonStockDTO;
import com.gs.lshly.common.struct.common.CommonUserVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchSkuGoodInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchSkuGoodInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeListVO;
import com.gs.lshly.common.struct.merchadmin.pc.user.vo.PCMerchUserVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeDTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeVO;
import com.gs.lshly.common.utils.EnumUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.common.ICommonLogisticsCompanyRpc;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import com.gs.lshly.rpc.api.common.ICommonStockRpc;
import com.gs.lshly.rpc.api.common.ICommonUserRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminSkuGoodInfoRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.user.IPCMerchUserRpc;
import com.lakala.boss.api.common.Common;

import cn.hutool.core.collection.CollectionUtil;

/**
* <p>
*  服务实现类
* </p>
* @author oy
* @since 2020-11-16
*/
@Component
public class PCMerchTradeServiceImpl implements IPCMerchTradeService {

    private final ITradeRepository tradeRepository;
    private final ITradeGoodsRepository tradeGoodsRepository;
    private final ITradeDeliveryRepository tradeDeliveryRepository;
    @Autowired
    private ITradePayRepository tradePayRepository;
    @Autowired
    private ITradeRightsRepository iTradeRightsRepository;
    @Autowired
    private ITradeRightsGoodsRepository iTradeRightsGoodsRepository;
    @Autowired
    private TradeMapper tradeMapper;
    
    @DubboReference
    private ICommonShopRpc commonShopRpc;
    @DubboReference
    private ICommonUserRpc commonUserRpc;
    @DubboReference
    private ICommonLogisticsCompanyRpc commonLogisticsCompanyRpc;
    @DubboReference
    private IPCMerchUserRpc ipcMerchUserRpc;
    @DubboReference
    private ICommonStockRpc commonStockRpc;
    @DubboReference
    private IPCMerchAdminSkuGoodInfoRpc iPCMerchAdminSkuGoodInfoRpc;

    public PCMerchTradeServiceImpl(ITradeRepository tradeRepository, ITradeGoodsRepository tradeGoodsRepository, ITradeDeliveryRepository tradeDeliveryRepository) {
        this.tradeRepository = tradeRepository;
        this.tradeGoodsRepository = tradeGoodsRepository;
        this.tradeDeliveryRepository = tradeDeliveryRepository;
    }

    @Override
    public PageData<PCMerchTradeListVO.tradeVO> tradeListPageData(PCMerchTradeQTO.TradeList qto) {
        QueryWrapper<PCMerchTradeQTO.TradeList> wrapper = new QueryWrapper<>();
        wrapper.and(i -> i.eq("t.`shop_id`",qto.getJwtShopId()));
        if(ObjectUtils.isNotEmpty(qto.getCreateTime())){
            wrapper.and(i -> i.eq("",qto.getCreateTime()));
        }
        if(StringUtils.isNotBlank(qto.getTradeCode())){
            wrapper.and(i -> i.like("t.`trade_code`",qto.getTradeCode()));
        }
        if(StringUtils.isNotBlank(qto.getRecvPersonName())){
            wrapper.and(i -> i.like("t.`recv_person_name`",qto.getRecvPersonName()));
        }
        if(StringUtils.isNotBlank(qto.getRecvPhone())){
            wrapper.and(i -> i.like("t.`recv_phone`",qto.getRecvPhone()));
        }
        if(ObjectUtils.isNotEmpty(qto.getTradeState())){
            wrapper.and(i -> i.eq("t.`trade_state`",qto.getTradeState()));//交易状态,不传则查所有状态数据
        }
        if(ObjectUtils.isNotEmpty(qto.getSourceType())){
            wrapper.and(i -> i.eq("t.`source_type`",qto.getSourceType()));//区分2b2c
        }
        if(ObjectUtils.isNotEmpty(qto.getDeliveryType())){
            wrapper.and(i -> i.eq("t.`delivery_type`",qto.getDeliveryType()));
        }
        if(StringUtils.isNotBlank(qto.getUserName())){
            CommonUserVO.DetailVO userDetailVO = commonUserRpc.detailsByUserName(qto.getUserName());
            if(ObjectUtils.isNotEmpty(userDetailVO) && StringUtils.isNotBlank(userDetailVO.getId())){
                //查询用户id
                wrapper.and(i -> i.eq("t.`user_id`",userDetailVO.getId()));
            }
        }
        if(StringUtils.isNotBlank(qto.getKeywords())){
        	 wrapper.and(i -> i.like("t.`trade_code`",qto.getKeywords()));
        }
        wrapper.orderByDesc("t.cdate");

        IPage<PCMerchTradeListVO.tradeVO> page = MybatisPlusUtil.pager(qto);

        tradeRepository.selectPCMerchTradePage(page,wrapper);

        List<PCMerchTradeListVO.tradeVO> voList = new ArrayList<>();
        for(PCMerchTradeListVO.tradeVO tradeVO : page.getRecords()){
            //查询售后信息
            QueryWrapper<TradeRights> query = MybatisPlusUtil.query();
            query.and(i->i.eq("trade_id",tradeVO.getId()));
            query.last("limit 0,1");
            TradeRights one = iTradeRightsRepository.getOne(query);
            if (ObjectUtils.isNotEmpty(one)){
                PCMerchTradeListVO.tradeVO.Right right = new PCMerchTradeListVO.tradeVO.Right();
                right.setRightsState(one.getState()).setRemark(one.getRightsRemark());
                tradeVO.setRightsInfo(right);
            }
            //查询店铺信息
            CommonShopVO.SimpleVO simpleVO = commonShopRpc.shopDetails(tradeVO.getShopId());
            if (ObjectUtils.isNotEmpty(simpleVO)) {
                tradeVO.setShopName(simpleVO.getShopName());
            }
            CommonUserVO.DetailVO details = commonUserRpc.details(tradeVO.getUserId());
            if (ObjectUtils.isNotEmpty(details)){
                tradeVO.setUserName(details.getUserName());
            }
            //根据交易ID查询交易商品集合
            fillTradeVO(tradeVO);
            if(tradeVO.getTradeState().equals(TradeStateEnum.待支付.getCode())){
                tradeVO.setPayDeadline(tradeVO.getCreateTime().plusMinutes(Common.PAYMENT_TIME_OUT));
            }
            voList.add(tradeVO);
        }

        return new PageData<>(voList, qto.getPageNum(), qto.getPageSize(), page.getTotal());
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
    @Override
    public PCMerchTradeListVO.tradeVO detail(PCMerchTradeDTO.IdDTO dto) {
        PCMerchTradeListVO.tradeVO tradeVO = new PCMerchTradeListVO.tradeVO();
        Trade trade = tradeRepository.getById(dto.getId());
        if(ObjectUtils.isEmpty(trade)){
            throw new BusinessException("无订单数据");
        }
        BeanUtils.copyProperties(trade, tradeVO);
        tradeVO.setTradeStateText(EnumUtil.getText(trade.getTradeState(),TradeStateEnum.class));
        //查询售后信息
        QueryWrapper<TradeRights> query = MybatisPlusUtil.query();
        query.and(i->i.eq("trade_id",tradeVO.getId()));
        query.last("limit 0,1");
        TradeRights one = iTradeRightsRepository.getOne(query);
        if (ObjectUtils.isNotEmpty(one)){
            PCMerchTradeListVO.tradeVO.Right right = new PCMerchTradeListVO.tradeVO.Right();
            right.setRightsState(one.getState()).setRemark(one.getRightsRemark());
            //有售后，则使用售后状态
            tradeVO.setTradeStateText(EnumUtil.getText(one.getState(), TradeRightsEndStateEnum.class));
            tradeVO.setRightsInfo(right);
        }
        //填充商家信息
        fillShop(tradeVO);
        fillTradeVO(tradeVO);
        if(tradeVO.getTradeState().equals(TradeStateEnum.待支付.getCode())){
            tradeVO.setPayDeadline(tradeVO.getCreateTime().plusMinutes(Common.PAYMENT_TIME_OUT));
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

        return tradeVO;
    }

    @Override
    public List<PCMerchTradeListVO.innerGoodsIdAndName> innergoodsIdsCheck(PCMerchTradeDTO.GoodsIdsDTO dto) {
        if (ObjectUtils.isEmpty(dto.getGoodsId())){
            throw new BusinessException("请传入参数");
        }
        QueryWrapper<TradeGoods> query = MybatisPlusUtil.query();
        query.and(i->i.ne("tg.`trade_state`",TradeStateEnum.已完成.getCode()).or().ne("tg.`trade_state`",TradeStateEnum.已取消.getCode()));
        if (ObjectUtils.isNotEmpty(dto.getGoodsId())){
            query.and(i->i.in("t.`goods_id`",dto.getGoodsId()));
        }
        List<PCMerchTradeListVO.innerGoodsIdAndName> list = tradeGoodsRepository.selectTradeIng(query);
        if (list.size()>0){
            return list;
        }
        return null;
    }

    @Override
    public PCMerchTradeListVO.innerGoodsIdAndName innergoodsIdCheck(PCMerchTradeDTO.GoodsIdDTO dto) {
        if (ObjectUtils.isEmpty(dto.getGoodsId())){
            throw new BusinessException("请传入参数");
        }
        QueryWrapper<TradeGoods> query = MybatisPlusUtil.query();
        query.and(i->i.ne("tg.`trade_state`",TradeStateEnum.已完成.getCode()).or().ne("tg.`trade_state`",TradeStateEnum.已取消.getCode()));
        if (ObjectUtils.isNotEmpty(dto.getGoodsId())){
            query.and(i->i.eq("t.`goods_id`",dto.getGoodsId()));
        }
        List<PCMerchTradeListVO.innerGoodsIdAndName> list = tradeGoodsRepository.selectTradeIng(query);
        if (list.size()>0){
            return list.get(0);
        }
        return null;


    }

	@Override
	public void editOrderAmount(PCMerchTradeDTO.orderAmountOrFreight dto) {
		if (StringUtils.isBlank(dto.getId())) {
			throw new BusinessException("请传入订单ID");
		}
		Trade trade = tradeRepository.getById(dto.getId());
		if (ObjectUtils.isEmpty(trade)) {
			throw new BusinessException("没有查询到订单");
		}
		if (trade.getTradeState().equals(TradeStateEnum.待支付.getCode())) {
			if (ObjectUtils.isNotEmpty(dto.getOrderAmount())) {// 改价格
				BigDecimal total = trade.getTradeAmount();
				// 修改交易总金额
				BigDecimal differencePrice = (trade.getTradeAmount() != null ? trade.getTradeAmount() : BigDecimal.ZERO)
						.subtract(dto.getOrderAmount());
				// trade.setGoodsAmount(trade.getGoodsAmount().add(differencePrice));
				trade.setTradeAmount(dto.getOrderAmount());

				// 修改优惠
				trade.setDiscountAmount(trade.getDiscountAmount() != null
						? trade.getDiscountAmount().add(differencePrice) : BigDecimal.ZERO.add(differencePrice));

				// 更新订单明细
				QueryWrapper<TradeGoods> tradeGoodsWrapper = new QueryWrapper<>();
				tradeGoodsWrapper.eq("trade_id", dto.getId());
				List<TradeGoods> tradeGoodsList = tradeGoodsRepository.list(tradeGoodsWrapper);
				if (CollectionUtil.isNotEmpty(tradeGoodsList)) {
					for (TradeGoods tradeGoods : tradeGoodsList) {
						//除数不能为0
						if(total.compareTo(BigDecimal.ZERO)==0){
						    tradeGoods.setTradeAmount(BigDecimal.ZERO);
						    tradeGoods.setDiscountAmount(BigDecimal.ZERO);
                        } else {
                            // 计算百分比
                            BigDecimal rate = tradeGoods.getTradeAmount().divide(total);

                            tradeGoods.setTradeAmount(dto.getOrderAmount().multiply(rate));

                            tradeGoods.setDiscountAmount(tradeGoods.getDiscountAmount() != null
                                    ? tradeGoods.getDiscountAmount().add(differencePrice.multiply(rate))
                                    : BigDecimal.ZERO.add(differencePrice.multiply(rate)));
                        }

						tradeGoodsRepository.saveOrUpdate(tradeGoods);
					}
				}

			}
			if (ObjectUtils.isNotEmpty(dto.getOrderPointAmount())) {
				BigDecimal totalPoint = trade.getTradePointAmount();
				BigDecimal differencePoint = (trade.getTradePointAmount() != null ? trade.getTradePointAmount(): BigDecimal.ZERO)
                        .subtract(dto.getOrderPointAmount());

				trade.setTradePointAmount(dto.getOrderPointAmount());
				trade.setDiscountPointAmount(trade.getDiscountPointAmount() != null
						? trade.getDiscountPointAmount().add(differencePoint) : BigDecimal.ZERO.add(differencePoint));

				QueryWrapper<TradeGoods> tradeGoodsWrapper = new QueryWrapper<>();
				tradeGoodsWrapper.eq("trade_id", dto.getId());
				List<TradeGoods> tradeGoodsList = tradeGoodsRepository.list(tradeGoodsWrapper);
				if (CollectionUtil.isNotEmpty(tradeGoodsList)) {
					for (TradeGoods tradeGoods : tradeGoodsList) {
					    //除数不能为0
					    if(totalPoint.compareTo(BigDecimal.ZERO)==0){
					        tradeGoods.setTradePointAmount(BigDecimal.ZERO);
					        tradeGoods.setDiscountPointAmount(BigDecimal.ZERO);
                        } else {
                            // 计算百分比
                            BigDecimal rate = tradeGoods.getGoodsPointAmount().divide(trade.getGoodsPointAmount());
                            tradeGoods.setTradePointAmount(dto.getOrderPointAmount().multiply(rate));

                            tradeGoods.setDiscountPointAmount(tradeGoods.getDiscountPointAmount() != null
                                    ? tradeGoods.getDiscountPointAmount().add(differencePoint.multiply(rate))
                                    : BigDecimal.ZERO.add(differencePoint.multiply(rate)));
                        }

						tradeGoodsRepository.saveOrUpdate(tradeGoods);
					}
				}

			}

			trade.setChangePriceCause(dto.getChangePriceCause());
			// 改订单编号
			trade.setTradeCode(TradeUtils.getTradeCode());
			// 改支付单支付信息
			tradePayRepository.update(new UpdateWrapper<TradePay>()
					// .set("trade_code", trade.getTradeCode())
					.set("token", "").set("pay_code", "").set("pay_info", "")
					.set("total_amount", trade.getTradeAmount()).set("total_point_amount", trade.getTradePointAmount())
					.eq("trade_id", trade.getId()));
			// 改定订单
			tradeRepository.saveOrUpdate(trade);
		} else {
			throw new BusinessException("该订单状态已无法修改价格");
		}

	}

    private void fillUserInfo(PCMerchTradeListVO.tradeVO tradeVO) {
        PCMerchUserVO.UserSimpleVO userSimpleVO = ipcMerchUserRpc.innerUserSimple(tradeVO.getUserId());
        if(ObjectUtils.isNotEmpty(userSimpleVO)){
            tradeVO.setUserName(userSimpleVO.getUserName());
        }
    }

    private void fillShop(PCMerchTradeListVO.tradeVO tradeVO) {
        CommonShopVO.SimpleVO innerDetailVO = commonShopRpc.shopDetails(tradeVO.getShopId());
        if(ObjectUtils.isNotEmpty(innerDetailVO)){
            tradeVO.setShopName(innerDetailVO.getShopName());
        }
    }

    private void fillLogisticsCompany(PCMerchTradeListVO.tradeVO tradeVO) {
        QueryWrapper<TradeDelivery> tradeDeliveryQueryWrapper = new QueryWrapper<>();
        tradeDeliveryQueryWrapper.eq("trade_id", tradeVO.getId());
        TradeDelivery tradeDelivery = tradeDeliveryRepository.getOne(tradeDeliveryQueryWrapper);
        if(ObjectUtils.isNotEmpty(tradeDelivery)){
            tradeVO.setLogisticsNumber(tradeDelivery.getLogisticsNumber());
            tradeVO.setDeliveryRemark(tradeDelivery.getDeliveryRemark());
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
    private void fillTradeVO(PCMerchTradeListVO.tradeVO tradeVO) {
        QueryWrapper<TradeGoods> tradeGoodsQueryWrapper = new QueryWrapper<>();
        tradeGoodsQueryWrapper.eq("trade_id",tradeVO.getId());
        List<TradeGoods> tradeGoodsList = tradeGoodsRepository.list(tradeGoodsQueryWrapper);
        List<PCMerchTradeListVO.TradeGoodsVO> tradeGoodsVOS = new ArrayList<>();
        for(TradeGoods tradeGoods : tradeGoodsList){
            PCMerchTradeListVO.TradeGoodsVO tradeGoodsVO = new PCMerchTradeListVO.TradeGoodsVO();
            BeanUtils.copyProperties(tradeGoods, tradeGoodsVO);
            QueryWrapper<TradeRightsGoods> rightGoodsQuery = MybatisPlusUtil.query();
            rightGoodsQuery.eq("trade_id", tradeVO.getId());
            rightGoodsQuery.eq("trade_goods_id", tradeGoods.getId());
            rightGoodsQuery.eq("is_revocation", 0);
            TradeRightsGoods tradeRightsGoods = iTradeRightsGoodsRepository.getOne(rightGoodsQuery);
            if(ObjectUtils.isNotEmpty(tradeRightsGoods)){
                QueryWrapper<TradeRights> rightQuery = MybatisPlusUtil.query();
                rightQuery.and(i->i.eq("id", tradeRightsGoods.getRightsId()));
                rightQuery.last("limit 0,1");
                TradeRights tradeRights = iTradeRightsRepository.getOne(rightQuery);
                if(ObjectUtils.isNotEmpty(tradeRights)){
                    tradeGoodsVO.setRightsState(tradeRights.getState());
                    tradeGoodsVO.setRightsStateText(EnumUtil.getText(tradeRights.getState(), TradeRightsEndStateEnum.class));
                    tradeGoodsVO.setRightsType(tradeRights.getRightsType());
                    tradeGoodsVO.setRightsTypeText(EnumUtil.getText(tradeRights.getRightsType(), TradeRightsTypeEnum.class));
                }
            }
            tradeGoodsVO.setTradeState(tradeVO.getTradeState());
            tradeGoodsVO.setTradeStateText(tradeVO.getTradeStateText());
            tradeGoodsVO.setShopName(tradeVO.getShopName());
            
            String skuId = tradeGoods.getSkuId();
            PCMerchSkuGoodInfoVO.DetailVO skuDetail = iPCMerchAdminSkuGoodInfoRpc.detailSkuGoodInfo(new PCMerchSkuGoodInfoDTO.IdDTO(skuId));
            String userId = tradeGoods.getUserId();
            
            CommonUserVO.DetailVO userDetail = commonUserRpc.details(userId);
            if(userDetail.getMemberType().equals(1)){
            	tradeGoodsVO.setSalePrice(skuDetail.getSalePrice());
            }else{
            	if(userDetail.getIsInUser().equals(1)){
            		tradeGoodsVO.setSalePrice(skuDetail.getInMemberPointPrice());
            	}else{
            		tradeGoodsVO.setSalePrice(skuDetail.getPointPrice());
            	}
            }
            tradeGoodsVO.setIsPointGood(skuDetail.getIsPointGood());
            
            
            tradeGoodsVOS.add(tradeGoodsVO);
        }
        tradeVO.setTradeGoodsVOS(tradeGoodsVOS);
    }

    private void fillTradeVOE(PCMerchTradeListVO.tradeVOExport tradeVO) {
        QueryWrapper<TradeGoods> tradeGoodsQueryWrapper = new QueryWrapper<>();
        tradeGoodsQueryWrapper.eq("trade_id",tradeVO.getId());
        List<TradeGoods> tradeGoodsList = tradeGoodsRepository.list(tradeGoodsQueryWrapper);
        List<PCMerchTradeListVO.TradeGoodsVO> tradeGoodsVOS = new ArrayList<>();
        for(TradeGoods tradeGoods : tradeGoodsList){
            PCMerchTradeListVO.TradeGoodsVO tradeGoodsVO = new PCMerchTradeListVO.TradeGoodsVO();
            BeanUtils.copyProperties(tradeGoods, tradeGoodsVO);
            tradeGoodsVO.setShopName(tradeVO.getShopName());
            tradeGoodsVOS.add(tradeGoodsVO);
        }
        tradeVO.setTradeGoodsVOS(tradeGoodsVOS);
    }


    @Override
    public TradeVO.PayDatelistVO payDateList(TradeDTO.PayDateList dto) {
        TradeVO.PayDatelistVO payDatelistVO = new TradeVO.PayDatelistVO();
        QueryWrapper<Trade> wrapper = MybatisPlusUtil.query();
        wrapper.eq("shop_id",dto.getJwtShopId());
        wrapper.groupBy("DATE_FORMAT(cdate,'%Y-%m-%d')");
        wrapper.orderByDesc("DATE_FORMAT(cdate,'%Y-%m-%d')");
        wrapper.last("limit 0,10");
        TradeVO.AvgAmountlistVO avgAmountlistVOS = new TradeVO.AvgAmountlistVO();
        BigDecimal avgAmount = BigDecimal.ZERO;
        if(ObjectUtils.isNotEmpty(dto.getQueryTimes())){
            switch (dto.getQueryTimes()){
                case 10:
                    if(ObjectUtils.isNotEmpty(dto.getQueryStates())){
                        switch (dto.getQueryStates()){
                            case 10:
                                payDatelistVO.setPackDatelist(tradeMapper.payDatelist(wrapper));
                                break;
                            case 20:
                                payDatelistVO.setPackDatelist(tradeMapper.payDatelist(wrapper));
                                break;
                            case 30:
                                List<TradeVO.PackDatelistVO> list = tradeMapper.payDatelist(wrapper);
                                for (TradeVO.PackDatelistVO packDatelistVO:list) {
                                    if(packDatelistVO.getTradeAmount().compareTo(BigDecimal.ZERO)  == 0){
                                        payDatelistVO.setAvgAmountlist(avgAmountlistVOS);
                                    }else {
                                        avgAmount = packDatelistVO.getTradeAmount()
                                                .divide(new BigDecimal(packDatelistVO.getCount()),2, RoundingMode.HALF_UP);
                                        avgAmountlistVOS.setCdate(packDatelistVO.getCdate());
                                        avgAmountlistVOS.setAvgAmount(avgAmount);
                                        payDatelistVO.setAvgAmountlist(avgAmountlistVOS);
                                    }
                                }
                                break;
                            case 40:
                                payDatelistVO.setPackCancelDatelist(tradeMapper.packCancelDatelist(wrapper));
                                break;
                            case 50:
                                payDatelistVO.setPackCancelDatelist(tradeMapper.packCancelDatelist(wrapper));
                                break;
                        }
                    }
                    break;
                case 20:
                    if(ObjectUtils.isNotEmpty(dto.getQueryStates())){
                        switch (dto.getQueryStates()){
                            case 10:
                                payDatelistVO.setPackDatelist(tradeMapper.anteayerPayDatelist(wrapper));
                                break;
                            case 20:
                                payDatelistVO.setPackDatelist(tradeMapper.anteayerPayDatelist(wrapper));
                                break;
                            case 30:
                                List<TradeVO.PackDatelistVO> list = tradeMapper.anteayerPayDatelist(wrapper);
                                for (TradeVO.PackDatelistVO packDatelistVO:list) {
                                    if(packDatelistVO.getTradeAmount().compareTo(BigDecimal.ZERO)  == 0){
                                        payDatelistVO.setAvgAmountlist(avgAmountlistVOS);
                                    }else {
                                        avgAmount = packDatelistVO.getTradeAmount()
                                                .divide(new BigDecimal(packDatelistVO.getCount()),2, RoundingMode.HALF_UP);
                                        avgAmountlistVOS.setCdate(packDatelistVO.getCdate());
                                        avgAmountlistVOS.setAvgAmount(avgAmount);
                                        payDatelistVO.setAvgAmountlist(avgAmountlistVOS);
                                    }
                                }
                                break;
                            case 40:
                                payDatelistVO.setPackCancelDatelist(tradeMapper.anteayerPackCancelDatelist(wrapper));
                                break;
                            case 50:
                                payDatelistVO.setPackCancelDatelist(tradeMapper.anteayerPackCancelDatelist(wrapper));
                                break;
                        }
                    }
                    break;
                case 30:
                    if(ObjectUtils.isNotEmpty(dto.getQueryStates())){
                        switch (dto.getQueryStates()){
                            case 10:
                                payDatelistVO.setPackDatelist(tradeMapper.weekPayDatelist(wrapper));
                                break;
                            case 20:
                                payDatelistVO.setPackDatelist(tradeMapper.weekPayDatelist(wrapper));
                                break;
                            case 30:
                                List<TradeVO.PackDatelistVO> list = tradeMapper.weekPayDatelist(wrapper);
                                for (TradeVO.PackDatelistVO packDatelistVO:list) {
                                    if(packDatelistVO.getTradeAmount().compareTo(BigDecimal.ZERO)  == 0){
                                        payDatelistVO.setAvgAmountlist(avgAmountlistVOS);
                                    }else {
                                        avgAmount = packDatelistVO.getTradeAmount()
                                                .divide(new BigDecimal(packDatelistVO.getCount()),2, RoundingMode.HALF_UP);
                                        avgAmountlistVOS.setCdate(packDatelistVO.getCdate());
                                        avgAmountlistVOS.setAvgAmount(avgAmount);
                                        payDatelistVO.setAvgAmountlist(avgAmountlistVOS);
                                    }
                                }
                                break;
                            case 40:
                                payDatelistVO.setPackCancelDatelist(tradeMapper.weekPackCancelDatelist(wrapper));
                                break;
                            case 50:
                                payDatelistVO.setPackCancelDatelist(tradeMapper.weekPackCancelDatelist(wrapper));
                                break;
                        }
                    }
                    break;
                case 40:
                    if(ObjectUtils.isNotEmpty(dto.getQueryStates())){
                        switch (dto.getQueryStates()){
                            case 10:
                                payDatelistVO.setPackDatelist(tradeMapper.monthPayDatelist(wrapper));
                                break;
                            case 20:
                                payDatelistVO.setPackDatelist(tradeMapper.monthPayDatelist(wrapper));
                                break;
                            case 30:
                                List<TradeVO.PackDatelistVO> list = tradeMapper.monthPayDatelist(wrapper);
                                for (TradeVO.PackDatelistVO packDatelistVO:list) {
                                    if(packDatelistVO.getTradeAmount().compareTo(BigDecimal.ZERO)  == 0){
                                        payDatelistVO.setAvgAmountlist(avgAmountlistVOS);
                                    }else {
                                        avgAmount = packDatelistVO.getTradeAmount()
                                                .divide(new BigDecimal(packDatelistVO.getCount()),2, RoundingMode.HALF_UP);
                                        avgAmountlistVOS.setCdate(packDatelistVO.getCdate());
                                        avgAmountlistVOS.setAvgAmount(avgAmount);
                                        payDatelistVO.setAvgAmountlist(avgAmountlistVOS);
                                    }
                                }
                                break;
                            case 40:
                                payDatelistVO.setPackCancelDatelist(tradeMapper.monthPackCancelDatelist(wrapper));
                                break;
                            case 50:
                                payDatelistVO.setPackCancelDatelist(tradeMapper.monthPackCancelDatelist(wrapper));
                                break;
                        }
                    }
                    break;
                default:
                    throw new BootstrapMethodError("时间查询方式错误");
            }
        }

        if(ObjectUtils.isNotEmpty(dto.getStartTime()) && ObjectUtils.isNotEmpty(dto.getEndTime())){
            wrapper.ge("cdate",dto.getStartTime())
                    .le("cdate",dto.getEndTime());
            switch (dto.getQueryStates()){
                case 10:
                    payDatelistVO.setPackDatelist(tradeMapper.ayDate(wrapper));
                    break;
                case 20:
                    payDatelistVO.setPackDatelist(tradeMapper.ayDate(wrapper));
                    break;
                case 30:
                    List<TradeVO.PackDatelistVO> list = tradeMapper.ayDate(wrapper);
                    for (TradeVO.PackDatelistVO packDatelistVO:list) {
                        if(packDatelistVO.getTradeAmount().compareTo(BigDecimal.ZERO)  == 0){
                            payDatelistVO.setAvgAmountlist(avgAmountlistVOS);
                        }else {
                            avgAmount = packDatelistVO.getTradeAmount()
                                    .divide(new BigDecimal(packDatelistVO.getCount()),2, RoundingMode.HALF_UP);
                            avgAmountlistVOS.setCdate(packDatelistVO.getCdate());
                            avgAmountlistVOS.setAvgAmount(avgAmount);
                            payDatelistVO.setAvgAmountlist(avgAmountlistVOS);
                        }
                    }
                    break;
                case 40:
                    payDatelistVO.setPackCancelDatelist(tradeMapper.eekPackCancelDatelist(wrapper));
                    break;
                case 50:
                    payDatelistVO.setPackCancelDatelist(tradeMapper.eekPackCancelDatelist(wrapper));
                    break;
            }
        }

        //封装数据
        packPayDate(dto,payDatelistVO);
        return payDatelistVO;
    }

    public void packPayDate(TradeDTO.PayDateList dto ,TradeVO.PayDatelistVO payDatelistVO) {
        //新增订单金额
        BigDecimal addTradeAmount = BigDecimal.ZERO;
        //新增订单数量
        Integer addTradeCount = null;
        //平均单价
        BigDecimal avgAmount = BigDecimal.ZERO;

        if(ObjectUtils.isNotEmpty(dto.getStartTime())&&ObjectUtils.isNotEmpty(dto.getEndTime())){
            QueryWrapper<Trade> wrapper2 = MybatisPlusUtil.query();
            wrapper2.eq("shop_id",dto.getJwtShopId());
            wrapper2.ge("cdate",dto.getStartTime())
                        .le("cdate",dto.getEndTime());
            addTradeAmount = tradeMapper.esterdayAddTradeAmount(wrapper2);
            payDatelistVO.setAddTradeAmount(addTradeAmount);
            addTradeCount = tradeMapper.esterdayAddTradeCount(wrapper2);
            payDatelistVO.setAddTradeCount(addTradeCount);
            if(addTradeAmount.compareTo(BigDecimal.ZERO)  == 0){
                payDatelistVO.setAvgAmount(avgAmount);
            }else {
                avgAmount = addTradeAmount.divide(new BigDecimal(addTradeCount),2, RoundingMode.HALF_UP);
                if(ObjectUtils.isNotEmpty(avgAmount)){
                    payDatelistVO.setAvgAmount(avgAmount);
                }
            }
            payDatelistVO.setPayTradeAmount(tradeMapper.esterdayPayTradeAmount(wrapper2));
            payDatelistVO.setPayTradeCount(tradeMapper.esterdayPayTradeCount(wrapper2));
            payDatelistVO.setCancelTradeAmount(tradeMapper.esterdayCancelTradeAmount(wrapper2));
            payDatelistVO.setCancelTradeCount(tradeMapper.esterdayCancelTradeCount(wrapper2));
        }

        if(ObjectUtils.isNotEmpty(dto.getQueryTimes())){
            QueryWrapper<Trade> wrapper = MybatisPlusUtil.query();
            wrapper.eq("shop_id",dto.getJwtShopId());
            switch (dto.getQueryTimes()){
                case 10:
                    addTradeAmount = tradeMapper.yesterdayAddTradeAmount(wrapper);
                    payDatelistVO.setAddTradeAmount(addTradeAmount);
                    addTradeCount = tradeMapper.yesterdayAddTradeCount(wrapper);
                    payDatelistVO.setAddTradeCount(addTradeCount);
                    if(addTradeAmount.compareTo(BigDecimal.ZERO)  == 0){
                        payDatelistVO.setAvgAmount(avgAmount);
                    }else {
                        avgAmount = addTradeAmount.divide(new BigDecimal(addTradeCount),2, RoundingMode.HALF_UP);
                        if(ObjectUtils.isNotEmpty(avgAmount)){
                            payDatelistVO.setAvgAmount(avgAmount);
                        }
                    }
                    payDatelistVO.setPayTradeAmount(tradeMapper.yesterdayPayTradeAmount(wrapper));
                    payDatelistVO.setPayTradeCount(tradeMapper.yesterdayPayTradeCount(wrapper));
                    payDatelistVO.setCancelTradeAmount(tradeMapper.yesterdayCancelTradeAmount(wrapper));
                    payDatelistVO.setCancelTradeCount(tradeMapper.yesterdayCancelTradeCount(wrapper));
                    break;
                case 20:
                    addTradeAmount = tradeMapper.anteayerAddTradeAmount(wrapper);
                    payDatelistVO.setAddTradeAmount(addTradeAmount);
                    addTradeCount = tradeMapper.anteayerAddTradeCount(wrapper);
                    payDatelistVO.setAddTradeCount(addTradeCount);
                    if(addTradeAmount.compareTo(BigDecimal.ZERO)  == 0){
                        payDatelistVO.setAvgAmount(avgAmount);
                    }else {
                        avgAmount = addTradeAmount.divide(new BigDecimal(addTradeCount),2, RoundingMode.HALF_UP);
                        if(ObjectUtils.isNotEmpty(avgAmount)){
                            payDatelistVO.setAvgAmount(avgAmount);
                        }
                    }
                    payDatelistVO.setPayTradeAmount(tradeMapper.anteayerPayTradeAmount(wrapper));
                    payDatelistVO.setPayTradeCount(tradeMapper.anteayerPayTradeCount(wrapper));
                    payDatelistVO.setCancelTradeAmount(tradeMapper.anteayerCancelTradeAmount(wrapper));
                    payDatelistVO.setCancelTradeCount(tradeMapper.anteayerCancelTradeCount(wrapper));
                    break;
                case 30:
                    addTradeAmount = tradeMapper.weekAddTradeAmount(wrapper);
                    payDatelistVO.setAddTradeAmount(addTradeAmount);
                    addTradeCount = tradeMapper.weekAddTradeCount(wrapper);
                    payDatelistVO.setAddTradeCount(addTradeCount);
                    if(addTradeAmount.compareTo(BigDecimal.ZERO)  == 0){
                        payDatelistVO.setAvgAmount(avgAmount);
                    }else {
                        avgAmount = addTradeAmount.divide(new BigDecimal(addTradeCount),2, RoundingMode.HALF_UP);
                        if(ObjectUtils.isNotEmpty(avgAmount)){
                            payDatelistVO.setAvgAmount(avgAmount);
                        }
                    }
                    payDatelistVO.setPayTradeAmount(tradeMapper.weekPayTradeAmount(wrapper));
                    payDatelistVO.setPayTradeCount(tradeMapper.weekPayTradeCount(wrapper));
                    payDatelistVO.setCancelTradeAmount(tradeMapper.weekCancelTradeAmount(wrapper));
                    payDatelistVO.setCancelTradeCount(tradeMapper.weekCancelTradeCount(wrapper));
                    break;
                case 40:
                    addTradeAmount = tradeMapper.monthAddTradeAmount(wrapper);
                    payDatelistVO.setAddTradeAmount(addTradeAmount);
                    addTradeCount = tradeMapper.monthAddTradeCount(wrapper);
                    payDatelistVO.setAddTradeCount(addTradeCount);
                    if(addTradeAmount.compareTo(BigDecimal.ZERO)  == 0){
                        payDatelistVO.setAvgAmount(avgAmount);
                    }else {
                        avgAmount = addTradeAmount.divide(new BigDecimal(addTradeCount),2, RoundingMode.HALF_UP);
                        if(ObjectUtils.isNotEmpty(avgAmount)){
                            payDatelistVO.setAvgAmount(avgAmount);
                        }
                    }
                    payDatelistVO.setPayTradeAmount(tradeMapper.monthPayTradeAmount(wrapper));
                    payDatelistVO.setPayTradeCount(tradeMapper.monthPayTradeCount(wrapper));
                    payDatelistVO.setCancelTradeAmount(tradeMapper.monthCancelTradeAmount(wrapper));
                    payDatelistVO.setCancelTradeCount(tradeMapper.monthCancelTradeCount(wrapper));
                    break;
                 default:
                     throw new BootstrapMethodError("查询时间条件错误！");
            }
        }
    }


    @Override
    public TradeVO.OperationlistVO operationList(TradeDTO.OperationList dto) {
        BigDecimal avgAmount = BigDecimal.ZERO;
        List<BigDecimal> avgAmountlistVOS = new ArrayList<>();
        TradeVO.AvgAmountlistVO avgAmountlistVOArrayList = new TradeVO.AvgAmountlistVO();
        List<TradeVO.AvgAmountlistVO> avgAmountlistVOArrayList2 = new ArrayList<>();
        TradeVO.OperationlistVO operationlistVO = new TradeVO.OperationlistVO();
        QueryWrapper<Trade> wrapper = MybatisPlusUtil.query();
        wrapper.eq("shop_id",dto.getJwtShopId());
        wrapper.groupBy("DATE_FORMAT(cdate,'%Y-%m-%d')");
        wrapper.orderByDesc("DATE_FORMAT(cdate,'%Y-%m-%d')");
        wrapper.last("limit 0,10");

        if(ObjectUtils.isNotEmpty(dto.getStartTime()) && ObjectUtils.isNotEmpty(dto.getEndTime())){
            wrapper.ge("cdate",dto.getStartTime())
                    .le("cdate",dto.getEndTime());
            switch (dto.getQueryStates()){
                case 10:
                    operationlistVO.setPackDatelist(tradeMapper.ayDatelist(wrapper));
                    break;
                case 20:
                    operationlistVO.setPackDatelist(tradeMapper.ayDatelist(wrapper));
                    break;
                case 30:
                    List<TradeVO.PackDatelistVO> list = tradeMapper.ayDatelist(wrapper);
                    for (TradeVO.PackDatelistVO packDatelistVO:list) {
                        if(packDatelistVO.getTradeAmount().compareTo(BigDecimal.ZERO)  == 0){
                            operationlistVO.setAvgAmountlist(avgAmountlistVOArrayList);
                        }else {
                            avgAmount = packDatelistVO.getTradeAmount()
                                    .divide(new BigDecimal(packDatelistVO.getCount()),2, RoundingMode.HALF_UP);
                            avgAmountlistVOArrayList.setCdate(packDatelistVO.getCdate());
                            avgAmountlistVOArrayList.setAvgAmount(avgAmount);
                            avgAmountlistVOArrayList2.add(avgAmountlistVOArrayList);
                            continue;
                        }
                    }
                    operationlistVO.setAvgAmountlist2(avgAmountlistVOArrayList2);
                    break;
                case 40:
                    operationlistVO.setPackDatelist(tradeMapper.esterDayFinishlist(wrapper));
                    break;
                case 50:
                    operationlistVO.setPackDatelist(tradeMapper.esterDayFinishlist(wrapper));
                    break;
                case 60:
                    operationlistVO.setPackCancelDatelist(tradeMapper.ackCancelDatelist(wrapper));
                    break;
            }
        }

        if(ObjectUtils.isNotEmpty(dto.getQueryTimes())){
            switch (dto.getQueryTimes()){
                case 10:
                    if(ObjectUtils.isNotEmpty(dto.getQueryStates())){
                        switch (dto.getQueryStates()){
                            case 10:
                                operationlistVO.setPackDatelist(tradeMapper.payDatelist(wrapper));
                                break;
                            case 20:
                                operationlistVO.setPackDatelist(tradeMapper.payDatelist(wrapper));
                                break;
                            case 30:
                                List<TradeVO.PackDatelistVO> list = tradeMapper.payDatelist(wrapper);
                                for (TradeVO.PackDatelistVO packDatelistVO:list) {
                                    if(packDatelistVO.getTradeAmount().compareTo(BigDecimal.ZERO)  == 0){
                                        operationlistVO.setAvgAmountlist(avgAmountlistVOArrayList);
                                    }else {
                                        avgAmount = packDatelistVO.getTradeAmount()
                                                .divide(new BigDecimal(packDatelistVO.getCount()),2, RoundingMode.HALF_UP);
                                        avgAmountlistVOArrayList.setCdate(packDatelistVO.getCdate());
                                        avgAmountlistVOArrayList.setAvgAmount(avgAmount);
                                        avgAmountlistVOArrayList2.add(avgAmountlistVOArrayList);
                                        continue;
                                    }
                                }

                                operationlistVO.setAvgAmountlist2(avgAmountlistVOArrayList2);
                                break;
                            case 40:
                                operationlistVO.setPackDatelist(tradeMapper.yesterDayFinishlist(wrapper));
                                break;
                            case 50:
                                operationlistVO.setPackDatelist(tradeMapper.yesterDayFinishlist(wrapper));
                                break;
                            case 60:
                                operationlistVO.setPackCancelDatelist(tradeMapper.packCancelDatelist(wrapper));
                                break;
                        }
                    }
                    break;
                case 20:
                    if(ObjectUtils.isNotEmpty(dto.getQueryStates())){
                        switch (dto.getQueryStates()){
                            case 10:
                                operationlistVO.setPackDatelist(tradeMapper.anteayerPayDatelist(wrapper));
                                break;
                            case 20:
                                operationlistVO.setPackDatelist(tradeMapper.anteayerPayDatelist(wrapper));
                                break;
                            case 30:
                                List<TradeVO.PackDatelistVO> list = tradeMapper.anteayerPayDatelist(wrapper);
                                for (TradeVO.PackDatelistVO packDatelistVO:list) {
                                    if(packDatelistVO.getTradeAmount().compareTo(BigDecimal.ZERO)  == 0){
                                        operationlistVO.setAvgAmountlist(avgAmountlistVOArrayList);
                                    }else {
                                        avgAmount = packDatelistVO.getTradeAmount()
                                                .divide(new BigDecimal(packDatelistVO.getCount()),2, RoundingMode.HALF_UP);
                                        avgAmountlistVOArrayList.setCdate(packDatelistVO.getCdate());
                                        avgAmountlistVOArrayList.setAvgAmount(avgAmount);
                                        avgAmountlistVOArrayList2.add(avgAmountlistVOArrayList);
                                        continue;
                                    }
                                }
                                operationlistVO.setAvgAmountlist2(avgAmountlistVOArrayList2);
                                break;
                            case 40:
                                operationlistVO.setPackDatelist(tradeMapper.anteayerFinishlist(wrapper));
                                break;
                            case 50:
                                operationlistVO.setPackDatelist(tradeMapper.anteayerFinishlist(wrapper));
                                break;
                            case 60:
                                operationlistVO.setPackCancelDatelist(tradeMapper.anteayerPackCancelDatelist(wrapper));
                                break;
                        }
                    }
                    break;
                case 30:
                    if(ObjectUtils.isNotEmpty(dto.getQueryStates())){
                        switch (dto.getQueryStates()){
                            case 10:
                                operationlistVO.setPackDatelist(tradeMapper.weekFinishlist(wrapper));
                                break;
                            case 20:
                                operationlistVO.setPackDatelist(tradeMapper.weekFinishlist(wrapper));
                                break;
                            case 30:
                                List<TradeVO.PackDatelistVO> list = tradeMapper.weekFinishlist(wrapper);
                                for (TradeVO.PackDatelistVO packDatelistVO:list) {
                                    if(packDatelistVO.getTradeAmount().compareTo(BigDecimal.ZERO)  == 0){
                                        operationlistVO.setAvgAmountlist(avgAmountlistVOArrayList);
                                    }else {
                                        avgAmount = packDatelistVO.getTradeAmount()
                                                .divide(new BigDecimal(packDatelistVO.getCount()),2, RoundingMode.HALF_UP);
                                        avgAmountlistVOArrayList.setCdate(packDatelistVO.getCdate());
                                        avgAmountlistVOArrayList.setAvgAmount(avgAmount);
                                        avgAmountlistVOArrayList2.add(avgAmountlistVOArrayList);
                                        continue;
                                    }
                                }
                                operationlistVO.setAvgAmountlist2(avgAmountlistVOArrayList2);
                                break;
                            case 40:
                                operationlistVO.setPackDatelist(tradeMapper.weekFinishlist(wrapper));
                                break;
                            case 50:
                                operationlistVO.setPackDatelist(tradeMapper.weekFinishlist(wrapper));
                                break;
                            case 60:
                                operationlistVO.setPackCancelDatelist(tradeMapper.weekPackCancelDatelist(wrapper));
                                break;
                        }
                    }
                    break;
                case 40:
                    if(ObjectUtils.isNotEmpty(dto.getQueryStates())){
                        switch (dto.getQueryStates()){
                            case 10:
                                operationlistVO.setPackDatelist(tradeMapper.monthPayDatelist(wrapper));
                                break;
                            case 20:
                                operationlistVO.setPackDatelist(tradeMapper.monthPayDatelist(wrapper));
                                break;
                            case 30:
                                List<TradeVO.PackDatelistVO> list = tradeMapper.monthPayDatelist(wrapper);
                                for (TradeVO.PackDatelistVO packDatelistVO:list) {
                                    if(packDatelistVO.getTradeAmount().compareTo(BigDecimal.ZERO)  == 0){
                                        operationlistVO.setAvgAmountlist(avgAmountlistVOArrayList);
                                    }else {
                                        avgAmount = packDatelistVO.getTradeAmount()
                                                .divide(new BigDecimal(packDatelistVO.getCount()),2, RoundingMode.HALF_UP);
                                        avgAmountlistVOArrayList.setCdate(packDatelistVO.getCdate());
                                        avgAmountlistVOArrayList.setAvgAmount(avgAmount);
                                        avgAmountlistVOArrayList2.add(avgAmountlistVOArrayList);
                                        continue;
                                    }
                                }
                                operationlistVO.setAvgAmountlist2(avgAmountlistVOArrayList2);
                                break;
                            case 40:
                                operationlistVO.setPackDatelist(tradeMapper.monthFinishlist(wrapper));
                                break;
                            case 50:
                                operationlistVO.setPackDatelist(tradeMapper.monthFinishlist(wrapper));
                                break;
                            case 60:
                                operationlistVO.setPackCancelDatelist(tradeMapper.monthPackCancelDatelist(wrapper));
                                break;
                        }
                    }
                    break;
                default:
                    throw new BootstrapMethodError("时间查询方式错误");
            }

        }
        //封装数据
        packOperationDate(dto,operationlistVO);
        //封装扇形图数据
        packDiagramDate(dto,operationlistVO);
        return operationlistVO;
    }

    @Override
    public List<PCMerchTradeListVO.waitSendTradeExport> export(PCMerchTradeQTO.IdListQTO qo) {
        if (ObjectUtils.isEmpty(qo.getIdList())){
            throw new BusinessException("请传入ID");
        }
        List<Trade> trades = tradeRepository.listByIds(qo.getIdList());
        if (ObjectUtils.isNotEmpty(trades)) {
            List<PCMerchTradeListVO.waitSendTradeExport> list = trades.parallelStream()
                    .map(e -> {
                        PCMerchTradeListVO.waitSendTradeExport tradeVO = new PCMerchTradeListVO.waitSendTradeExport();
                        BeanUtils.copyProperties(e, tradeVO);
                        tradeVO.setTradeState(EnumUtil.getText(e.getTradeState(),TradeStateEnum.class));
                        //fillTradeVOE(tradeVO);
                        return tradeVO;
                    }).collect(toList());
            return list;
        }
        return new ArrayList<>();
    }

    @Override
    public List<PCMerchTradeListVO.hasSentTradeExport> hasSentExport(PCMerchTradeQTO.IdListQTO qo) {
        if (ObjectUtils.isEmpty(qo.getIdList())){
            throw new BusinessException("请传入ID");
        }
        List<Trade> trades = tradeRepository.listByIds(qo.getIdList());
        if (ObjectUtils.isNotEmpty(trades)) {
            List<PCMerchTradeListVO.hasSentTradeExport> list = trades.parallelStream()
                    .map(e -> {
                        PCMerchTradeListVO.hasSentTradeExport tradeVO = new PCMerchTradeListVO.hasSentTradeExport();
                        BeanUtils.copyProperties(e, tradeVO);
                        tradeVO.setTradeState(EnumUtil.getText(e.getTradeState(),TradeStateEnum.class));
                        tradeVO.setPointPriceActuallyPaid(ObjectUtils.isNotEmpty(e.getPointPriceActuallyPaid())?e.getPointPriceActuallyPaid().setScale(0).toString():"0");
                        //物流信息,快递单号
                        QueryWrapper<TradeDelivery> tradeDeliveryQueryWrapper = new QueryWrapper<>();
                        tradeDeliveryQueryWrapper.eq("trade_id", e.getId());
                        TradeDelivery tradeDelivery = tradeDeliveryRepository.getOne(tradeDeliveryQueryWrapper);
                        if(ObjectUtils.isNotEmpty(tradeDelivery)){
                            tradeVO.setLogisticsNumber(tradeDelivery.getLogisticsNumber());
                            tradeVO.setDeliveryRemark(tradeDelivery.getDeliveryRemark());
                            CommonLogisticsCompanyVO.DetailVO logisticsDetailVO = commonLogisticsCompanyRpc.getLogisticsCompany(tradeDelivery.getLogisticsId());
                            if(ObjectUtils.isNotEmpty(logisticsDetailVO)){
                                tradeVO.setLogisticsCompanyName(logisticsDetailVO.getName());
                            }
                        }
                        return tradeVO;
                    }).collect(toList());
            return list;
        }
        return new ArrayList<>();
    }


    public void packOperationDate(TradeDTO.OperationList dto ,TradeVO.OperationlistVO operationlistVO) {
        //新增订单金额
        BigDecimal addTradeAmount = BigDecimal.ZERO;
        //新增订单数量
        Integer addTradeCount = null;
        //平均单价
        BigDecimal avgAmount = BigDecimal.ZERO;
        QueryWrapper<Trade> wrapper = MybatisPlusUtil.query();
        wrapper.eq("shop_id",dto.getJwtShopId());
        if(ObjectUtils.isNotEmpty(dto.getStartTime()) && ObjectUtils.isNotEmpty(dto.getEndTime())){
            wrapper.ge("cdate",dto.getStartTime())
                    .le("cdate",dto.getEndTime());
            addTradeAmount = tradeMapper.esterdayAddTradeAmount(wrapper);
            operationlistVO.setAddTradeAmount(addTradeAmount);
            addTradeCount = tradeMapper.esterdayAddTradeCount(wrapper);
            operationlistVO.setAddTradeCount(addTradeCount);
            if(addTradeAmount.compareTo(BigDecimal.ZERO)  == 0){
                operationlistVO.setAvgAmount(avgAmount);
            }else {
                avgAmount = addTradeAmount.divide(new BigDecimal(addTradeCount),2, RoundingMode.HALF_UP);
                if(ObjectUtils.isNotEmpty(avgAmount)){
                    operationlistVO.setAvgAmount(avgAmount);
                }
            }
            operationlistVO.setIndependentUV(new BigDecimal(1));
            operationlistVO.setAftermarketCount(tradeMapper.esterDayAftermarketCount(wrapper));
        }

        if(ObjectUtils.isNotEmpty(dto.getQueryTimes())){
            switch (dto.getQueryTimes()){
                case 10:
                    addTradeAmount = tradeMapper.yesterdayAddTradeAmount(wrapper);
                    operationlistVO.setAddTradeAmount(addTradeAmount);
                    addTradeCount = tradeMapper.yesterdayAddTradeCount(wrapper);
                    operationlistVO.setAddTradeCount(addTradeCount);
                    if(addTradeAmount.compareTo(BigDecimal.ZERO)  == 0){
                        operationlistVO.setAvgAmount(avgAmount);
                    }else {
                        avgAmount = addTradeAmount.divide(new BigDecimal(addTradeCount),2, RoundingMode.HALF_UP);
                        if(ObjectUtils.isNotEmpty(avgAmount)){
                            operationlistVO.setAvgAmount(avgAmount);
                        }
                    }
                    operationlistVO.setIndependentUV(new BigDecimal(1));
                    operationlistVO.setAftermarketCount(tradeMapper.yesterDayAftermarketCount(wrapper));
                    break;
                case 20:
                    addTradeAmount = tradeMapper.anteayerAddTradeAmount(wrapper);
                    operationlistVO.setAddTradeAmount(addTradeAmount);
                    addTradeCount = tradeMapper.anteayerAddTradeCount(wrapper);
                    operationlistVO.setAddTradeCount(addTradeCount);
                    if(addTradeAmount.compareTo(BigDecimal.ZERO)  == 0){
                        operationlistVO.setAvgAmount(avgAmount);
                    }else {
                        avgAmount = addTradeAmount.divide(new BigDecimal(addTradeCount),2, RoundingMode.HALF_UP);
                        if(ObjectUtils.isNotEmpty(avgAmount)){
                            operationlistVO.setAvgAmount(avgAmount);
                        }
                    }
                    operationlistVO.setIndependentUV(new BigDecimal(1));
                    operationlistVO.setAftermarketCount(tradeMapper.anteayerAftermarketCount(wrapper));
                    break;
                case 30:
                    addTradeAmount = tradeMapper.weekAddTradeAmount(wrapper);
                    operationlistVO.setAddTradeAmount(addTradeAmount);
                    addTradeCount = tradeMapper.weekAddTradeCount(wrapper);
                    operationlistVO.setAddTradeCount(addTradeCount);
                    if(addTradeAmount.compareTo(BigDecimal.ZERO)  == 0){
                        operationlistVO.setAvgAmount(avgAmount);
                    }else {
                        avgAmount = addTradeAmount.divide(new BigDecimal(addTradeCount),2, RoundingMode.HALF_UP);
                        if(ObjectUtils.isNotEmpty(avgAmount)){
                            operationlistVO.setAvgAmount(avgAmount);
                        }
                    }
                    operationlistVO.setIndependentUV(new BigDecimal(1));
                    operationlistVO.setAftermarketCount(tradeMapper.weekAftermarketCount(wrapper));
                    break;
                case 40:
                    addTradeAmount = tradeMapper.monthAddTradeAmount(wrapper);
                    operationlistVO.setAddTradeAmount(addTradeAmount);
                    addTradeCount = tradeMapper.monthAddTradeCount(wrapper);
                    operationlistVO.setAddTradeCount(addTradeCount);
                    if(addTradeAmount.compareTo(BigDecimal.ZERO)  == 0){
                        operationlistVO.setAvgAmount(avgAmount);
                    }else {
                        avgAmount = addTradeAmount.divide(new BigDecimal(addTradeCount),2, RoundingMode.HALF_UP);
                        if(ObjectUtils.isNotEmpty(avgAmount)){
                            operationlistVO.setAvgAmount(avgAmount);
                        }
                    }
                    operationlistVO.setIndependentUV(new BigDecimal(1));
                    operationlistVO.setAftermarketCount(tradeMapper.monthAftermarketCount(wrapper));
                    break;
                default:
                    throw new BootstrapMethodError("查询时间条件错误！");
            }
        }
    }


    public void packDiagramDate(TradeDTO.OperationList dto ,TradeVO.OperationlistVO operationlistVO) {
        //订单完成数据集合
        TradeVO.PayTradeVO payTradeVO = new TradeVO.PayTradeVO();
        //总订单金额集合
        TradeVO.PayTradeAmountVO payTradeAmountVO = new TradeVO.PayTradeAmountVO();
        //访问量数据
        TradeVO.VisitorDateVO visitorDateVO = new TradeVO.VisitorDateVO();
        QueryWrapper<Trade> wrapper = MybatisPlusUtil.query();

        if(ObjectUtils.isNotEmpty(dto.getStartTime()) && ObjectUtils.isNotEmpty(dto.getEndTime())){
            wrapper.ge("cdate",dto.getStartTime())
                    .le("cdate",dto.getEndTime());
            payTradeVO.setPayTradeCount(tradeMapper.esterdayPayTradeCount(wrapper));
            payTradeVO.setPayNotTradeCount(tradeMapper.esterDayNotPayTradeCount(wrapper));
            operationlistVO.setPayTradeList(payTradeVO);
            payTradeAmountVO.setPayTradeAmountCount(tradeMapper.esterdayPayTradeAmount(wrapper));
            payTradeAmountVO.setPayNotTradeAmountCount(tradeMapper.esterDayNotPayTradeAmount(wrapper));
            operationlistVO.setPayTradeAmountList(payTradeAmountVO);
            visitorDateVO.setAddVisitorCount(1);
            visitorDateVO.setAllVisitorCount(1);
            operationlistVO.setVisitorDateList(visitorDateVO);
        }

       if(ObjectUtils.isNotEmpty(dto.getQueryTimes())){
//           wrapper.eq("shop_id",dto.getJwtShopId());
           switch (dto.getQueryTimes()){
               case 10:
                   payTradeVO.setPayTradeCount(tradeMapper.yesterdayPayTradeCount(wrapper));
                   payTradeVO.setPayNotTradeCount(tradeMapper.yesterDayNotPayTradeCount(wrapper));
                   operationlistVO.setPayTradeList(payTradeVO);
                   payTradeAmountVO.setPayTradeAmountCount(tradeMapper.yesterdayPayTradeAmount(wrapper));
                   payTradeAmountVO.setPayNotTradeAmountCount(tradeMapper.yesterDayNotPayTradeAmount(wrapper));
                   operationlistVO.setPayTradeAmountList(payTradeAmountVO);
                   visitorDateVO.setAddVisitorCount(1);
                   visitorDateVO.setAllVisitorCount(1);
                   operationlistVO.setVisitorDateList(visitorDateVO);
                   break;
               case 20:
                   payTradeVO.setPayTradeCount(tradeMapper.anteayerPayTradeCount(wrapper));
                   payTradeVO.setPayNotTradeCount(tradeMapper.anteayerNotPayTradeCount(wrapper));
                   operationlistVO.setPayTradeList(payTradeVO);
                   payTradeAmountVO.setPayTradeAmountCount(tradeMapper.anteayerPayTradeAmount(wrapper));
                   payTradeAmountVO.setPayNotTradeAmountCount(tradeMapper.anteayerNotPayTradeAmount(wrapper));
                   operationlistVO.setPayTradeAmountList(payTradeAmountVO);
                   visitorDateVO.setAddVisitorCount(1);
                   visitorDateVO.setAllVisitorCount(1);
                   operationlistVO.setVisitorDateList(visitorDateVO);
                   break;
               case 30:
                   payTradeVO.setPayTradeCount(tradeMapper.weekPayTradeCount(wrapper));
                   payTradeVO.setPayNotTradeCount(tradeMapper.weekNotPayTradeCount(wrapper));
                   operationlistVO.setPayTradeList(payTradeVO);
                   payTradeAmountVO.setPayTradeAmountCount(tradeMapper.weekPayTradeAmount(wrapper));
                   payTradeAmountVO.setPayNotTradeAmountCount(tradeMapper.weekNotPayTradeAmount(wrapper));
                   operationlistVO.setPayTradeAmountList(payTradeAmountVO);
                   visitorDateVO.setAddVisitorCount(1);
                   visitorDateVO.setAllVisitorCount(1);
                   operationlistVO.setVisitorDateList(visitorDateVO);
                   break;
               case 40:
                   payTradeVO.setPayTradeCount(tradeMapper.monthPayTradeCount(wrapper));
                   payTradeVO.setPayNotTradeCount(tradeMapper.monthNotPayTradeCount(wrapper));
                   operationlistVO.setPayTradeList(payTradeVO);
                   payTradeAmountVO.setPayTradeAmountCount(tradeMapper.monthPayTradeAmount(wrapper));
                   payTradeAmountVO.setPayNotTradeAmountCount(tradeMapper.monthNotPayTradeAmount(wrapper));
                   operationlistVO.setPayTradeAmountList(payTradeAmountVO);
                   visitorDateVO.setAddVisitorCount(1);
                   visitorDateVO.setAllVisitorCount(1);
                   operationlistVO.setVisitorDateList(visitorDateVO);
                   break;
                default:
                    throw new BootstrapMethodError("错误时间查询方式");
           }

       }

    }

}
