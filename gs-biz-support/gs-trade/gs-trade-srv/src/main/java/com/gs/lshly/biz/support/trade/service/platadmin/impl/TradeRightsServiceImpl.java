package com.gs.lshly.biz.support.trade.service.platadmin.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gs.lshly.biz.support.trade.entity.*;
import com.gs.lshly.biz.support.trade.enums.PayTypeEnum;
import com.gs.lshly.biz.support.trade.repository.*;
import com.gs.lshly.biz.support.trade.service.platadmin.ITradeRightsService;
import com.gs.lshly.common.enums.*;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopDTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeRightsDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeRightsRefundDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeRightsQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeListVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeRightsRefundVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeRightsVO;
import com.gs.lshly.common.struct.platadmin.user.dto.UserDTO;
import com.gs.lshly.common.struct.platadmin.user.vo.UserVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsInfoRpc;
import com.gs.lshly.rpc.api.platadmin.merchant.IShopRpc;
import com.gs.lshly.rpc.api.platadmin.user.IUserRpc;
import com.lakala.boss.api.config.MerchantBaseEnum;
import com.lakala.boss.api.entity.request.EntOrderDetailRefundRequest;
import com.lakala.boss.api.entity.request.RefundDetail;
import com.lakala.boss.api.entity.response.EntOrderRefundResponse;
import com.lakala.boss.api.utils.BossClient;
import com.lakala.boss.api.utils.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zdf
 * @since 2020-12-22
 */
@Component
@Slf4j
public class TradeRightsServiceImpl implements ITradeRightsService {
    @Autowired
    private ITradeRepository iTradeRepository;
    @Autowired
    private ITradeRightsRepository repository;
    @Autowired
    private ITradeRightsRefundRepository iTradeRightsRefundRepository;
    @Autowired
    private ITradeRightsGoodsRepository iTradeRightsGoodsRepository;
    @Autowired
    private ITradeRightsImgRepository iTradeRightsImgRepository;
    @Autowired
    private ITradeRightsRecordRepository iTradeRightsRecordRepository;
    @Autowired
    private ITradePayRepository iTradePayRepository;
    @Autowired
    private ITradeCancelRepository iTradeCancelRepository;
    @DubboReference
    private IGoodsInfoRpc iGoodsInfoRpc;
    @DubboReference
    private IShopRpc iShopRpc;
    @DubboReference
    private IUserRpc iUserRpc;
    @DubboReference
    private ICommonShopRpc iCommonShopRpc;

    @Override
    public PageData<TradeRightsVO.RightsListVO> pageData(TradeRightsQTO.StateDTO qto) {
        QueryWrapper<TradeRights> query = MybatisPlusUtil.query();
        if (ObjectUtils.isNotEmpty(qto.getStatus())) {
            //状态枚举类[10:换货,20:仅退款,30:退货退款]
            query.and(i -> i.eq("tr.`rights_type`", qto.getStatus()));
        }
        if (ObjectUtils.isNotEmpty(qto.getSourceType())) {
            query.and(i -> i.eq("t.`source_type`", qto.getSourceType()));
        }
        if (StringUtils.isNotBlank(qto.getTradeId())) {
            query.and(i -> i.like("tr.`trade_id`", qto.getTradeId()));
        }
        if (ObjectUtils.isNotEmpty(qto.getState())) {
            query.and(i -> i.eq("tr.`state`", qto.getState()));
        }
        if (ObjectUtils.isNotEmpty(qto.getCdate()) || ObjectUtils.isNotEmpty(qto.getCdateState())) {
            GetQuery(query, qto.getCdateState(), qto.getCdate(), qto.getCdateLittleDate());
        }
        IPage<TradeRights> pager = MybatisPlusUtil.pager(qto);
        IPage<TradeRights> page = repository.selectPlatadminTradeRightsList(pager, query);
        List<TradeRightsVO.RightsListVO> listVOS = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(page.getRecords()) || ObjectUtils.isNotEmpty(pager)) {
            for (TradeRights tradeRights : page.getRecords()) {
                TradeRightsVO.RightsListVO rightsListVO = new TradeRightsVO.RightsListVO();
                QueryWrapper<TradeRightsGoods> query1 = MybatisPlusUtil.query();
                query1.and(i -> i.eq("trade_id", tradeRights.getTradeId()));
                query1.and(i -> i.eq("rights_id", tradeRights.getId()));
                query1.last("limit 0,1");
                TradeRightsGoods list1 = iTradeRightsGoodsRepository.getOne(query1);
                if (ObjectUtils.isNotEmpty(list1)) {
                    GoodsInfoVO.ListVO goodsInfo = getGoodsInfo(list1.getTradeGoodsId());
                    if (ObjectUtils.isNotEmpty(goodsInfo)) {
                        rightsListVO.setGoodsTitle(goodsInfo.getGoodsTitle());
                    }
                    rightsListVO.setQuantity(list1.getQuantity());//数量
                }
                rightsListVO.setCdate(tradeRights.getCdate());
                List<String> shopIds = new ArrayList<>();
                shopIds.add(tradeRights.getShopId());
                List<ShopVO.InnerSimpleVO> innerSimpleVO = iShopRpc.innerlistShopIdName(new BaseDTO(), shopIds);
                ShopVO.InnerSimpleVO innerSimpleVO1 = null;
                if (ObjectUtils.isNotEmpty(innerSimpleVO)) {
                    innerSimpleVO1 = innerSimpleVO.get(0);
                }
                if (ObjectUtils.isNotEmpty(innerSimpleVO1)) {
                    rightsListVO.setShopName(innerSimpleVO1.getShopName());
                }
                UserVO.MiniVO mini = iUserRpc.mini(new UserDTO.IdDTO(tradeRights.getUserId()));
                if (ObjectUtils.isNotEmpty(mini)) {
                    rightsListVO.setUserName(mini.getUserName());
                }
                rightsListVO.setId(tradeRights.getId());

                rightsListVO.setRightsReasonType(tradeRights.getRightsReasonType());
                rightsListVO.setRightsType(tradeRights.getRightsType());
                rightsListVO.setState(tradeRights.getState());
                rightsListVO.setTradeId(tradeRights.getTradeId());
                rightsListVO.setTradeCode(tradeRights.getOrderCode());
                listVOS.add(rightsListVO);
            }
        }

        return new PageData<>(listVOS, qto.getPageNum(), qto.getPageSize(), listVOS.size());
    }

    private void GetQuery(QueryWrapper<?> query, Integer qto, LocalDateTime date, LocalDateTime littleDate) {
        switch (qto) {
            case 10:
                query.and(i -> i.ge("tr.`cdate`", date));
                break;
            case 20:
                query.and(i -> i.le("tr.`cdate`", date));
                break;
            case 30:
                query.and(i -> i.eq("tr.`cdate`", date));
                break;
            case 40:
                if (ObjectUtils.isNotEmpty(littleDate)) {
                    query.and(i -> i.ge("tr.`cdate`", date).le("tr.`cdate`", littleDate));
                }
                break;
        }

    }


    private GoodsInfoVO.ListVO getGoodsInfo(String tradeGoodsId) {
        List<String> ids = new ArrayList<>();
        ids.add(tradeGoodsId);
        List<GoodsInfoVO.ListVO> listVOS = iGoodsInfoRpc.innerServiceSpuGoodsInfo(new GoodsInfoDTO.IdsInnerServiceDTO().setGoodsIdList(ids).setQueryType(10));
        if (ObjectUtils.isNotEmpty(listVOS)) {
            return listVOS.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void addTradeRights(TradeRightsDTO.ETO eto) {
        TradeRights tradeRights = new TradeRights();
        BeanUtils.copyProperties(eto, tradeRights);
        repository.save(tradeRights);
    }


    @Override
    public void deleteTradeRights(TradeRightsDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }

    @Override
    public void editTradeRights(TradeRightsDTO.ETO eto) {
        TradeRights tradeRights = new TradeRights();
        BeanUtils.copyProperties(eto, tradeRights);
        repository.updateById(tradeRights);
    }

    @Override
    public TradeRightsVO.DetailVO detailTradeRights(TradeRightsDTO.IdDTO dto) {
        TradeRights tradeRights = repository.getById(dto.getId());
        TradeRightsVO.DetailVO detailVo = new TradeRightsVO.DetailVO();
        if (ObjectUtils.isEmpty(tradeRights)) {
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(tradeRights, detailVo);
        return detailVo;
    }

    @Override
    public TradeRightsVO.RightsListViewVO get(TradeRightsDTO.IdDTO dto) {
        if (StringUtils.isNotBlank(dto.getId())) {
            TradeRights tradeRights = repository.getById(dto.getId());
            TradeRightsVO.RightsListViewVO rightsListViewVO = new TradeRightsVO.RightsListViewVO();
            if (ObjectUtils.isNotEmpty(tradeRights)) {
                BeanUtils.copyProperties(tradeRights, rightsListViewVO);
            } else {
                throw new BusinessException("没有查询到售后表");
            }
            QueryWrapper<TradeRightsGoods> query1 = MybatisPlusUtil.query();
            query1.and(i -> i.eq("trade_id", tradeRights.getTradeId()));
            query1.and(i -> i.eq("rights_id", tradeRights.getId()));
            TradeRightsGoods list1 = iTradeRightsGoodsRepository.getOne(query1);
            if (ObjectUtils.isNotEmpty(list1)) {
                rightsListViewVO.setQuantity(list1.getQuantity());
                rightsListViewVO.setSalePrice(list1.getSalePrice());
                rightsListViewVO.setTotalPrice(list1.getSalePrice().multiply(new BigDecimal(list1.getQuantity())));
            }
            GoodsInfoVO.ListVO goodsInfo = getGoodsInfo(list1.getTradeGoodsId());
            if (ObjectUtils.isNotEmpty(goodsInfo)) {
                rightsListViewVO.setGoodsImage(goodsInfo.getGoodsImage());
                rightsListViewVO.setGoodsName(goodsInfo.getGoodsName());
            }
            List<TradeRightsImg> rightsImg = getRightsImg(tradeRights.getId(), tradeRights.getTradeId());
            StringBuilder rightsImage = new StringBuilder();
            if (ObjectUtils.isNotEmpty(rightsImg)) {
                for (TradeRightsImg rightsImg1 : rightsImg) {
                    rightsImage.append(rightsImg1.getRightsImg());
                }
                rightsListViewVO.setRightsImage(rightsImage.toString());
            }
            List<String> shopIds = new ArrayList<>();
            shopIds.add(tradeRights.getShopId());
            ShopVO.InnerSimpleVO innerSimpleVO = iShopRpc.innerlistShopIdName(new BaseDTO(), shopIds).get(0);
            if (ObjectUtils.isNotEmpty(innerSimpleVO)) {
                rightsListViewVO.setShopName(innerSimpleVO.getShopName());
            }
            QueryWrapper<TradeRightsRecord> query = MybatisPlusUtil.query();
            query.and(i -> i.eq("trade_id", tradeRights.getTradeId()));
            query.and(i -> i.eq("rights_id", tradeRights.getId()));
            query.and(i -> i.eq("state", TradeRightsStateEnum.通过.getCode()));
            TradeRightsRecord one = iTradeRightsRecordRepository.getOne(query);
            if (ObjectUtils.isNotEmpty(one)) {
                rightsListViewVO.setMerchanHandle(one.getRemark());
            }
            return rightsListViewVO;
        } else {
            throw new BusinessException("请输入ID");
        }

    }

    @Override
    public PageData<TradeRightsVO.RightsRefundListVO> getRightsRefundList(TradeRightsQTO.StateRefundDTO qto) {
        QueryWrapper<TradeRights> query = MybatisPlusUtil.query();
        query.and(i -> i.eq("rights_type", TradeRightsTypeEnum.仅退款.getCode()).or().eq("rights_type", TradeRightsTypeEnum.退货退款.getCode()));
        if (ObjectUtils.isNotEmpty(qto.getStatus())) {
            query.and(i -> i.eq("state", qto.getStatus()).or().eq(qto.getStatus() == 60, "state", 50));
        }
        if (StringUtils.isNotBlank(qto.getTradeId())) {
            query.and(i -> i.like("trade_id", qto.getTradeId()));
        }
        if (StringUtils.isNotBlank(qto.getId())) {
            query.and(i -> i.like("id", qto.getId()));
        }
        int count = repository.count(query);
        query.orderByDesc("cdate");
        IPage pager = MybatisPlusUtil.pager(qto);
        query.last("limit " + pager.offset() + "," + pager.getSize());
        List<TradeRights> list = repository.list(query);
        List<TradeRightsVO.RightsRefundListVO> ListVOS = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(list)) {
            for (TradeRights tradeRights : list) {
                TradeRightsVO.RightsRefundListVO rightsRefundListVO = new TradeRightsVO.RightsRefundListVO();
                BeanUtils.copyProperties(tradeRights, rightsRefundListVO);
                rightsRefundListVO.setUdate(tradeRights.getUdate());
                Trade trade = iTradeRepository.getById(tradeRights.getTradeId());
                if (ObjectUtils.isNotEmpty(trade)) {
                    rightsRefundListVO.setTradeAmount(trade.getTradeAmount());
                }
                rightsRefundListVO.setTradeCode(tradeRights.getOrderCode());
                ListVOS.add(rightsRefundListVO);
            }
        }
        return new PageData<>(ListVOS, qto.getPageNum(), qto.getPageSize(), count);
    }

    @Override
    public TradeRightsVO.RightsRefundViewVO getRightsRefund(TradeRightsDTO.IdDTO dto) {
        if (StringUtils.isBlank(dto.getId())) {
            throw new BusinessException("请传入售后ID");
        }
        TradeRights tradeRights = repository.getById(dto);
        TradeRightsVO.RightsRefundViewVO rightsRefundViewVO = new TradeRightsVO.RightsRefundViewVO();
        if (ObjectUtils.isNotEmpty(tradeRights)) {
            BeanUtils.copyProperties(tradeRights, rightsRefundViewVO);
        }
        rightsRefundViewVO.setTradeCode(tradeRights.getOrderCode());
        UserVO.MiniVO mini = iUserRpc.mini(new UserDTO.IdDTO(tradeRights.getUserId()));
        if (ObjectUtils.isNotEmpty(mini)) {
            if (StringUtils.isNotBlank(mini.getUserName())) {
                rightsRefundViewVO.setUserName(mini.getUserName());
            }
        }
        Trade trade = iTradeRepository.getById(tradeRights.getTradeId());
        if (ObjectUtils.isNotEmpty(trade)) {
            rightsRefundViewVO.setTradeAmount(trade.getTradeAmount());
        }
        ShopVO.DetailVO detailVO = iShopRpc.shopDetails(new ShopDTO.IdDTO(tradeRights.getTradeId()));
        if (ObjectUtils.isNotEmpty(detailVO)) {
            rightsRefundViewVO.setShopName(detailVO.getShopName());
        }
        rightsRefundViewVO.setShopRefundAmount(tradeRights.getRefundAmount());
        if (tradeRights.getState().equals(TradeRightsStateEnum.完成.getCode())) {
            QueryWrapper<TradeRightsRefund> query = MybatisPlusUtil.query();
            query.and(i -> i.eq("rights_id", tradeRights.getId()));
            query.and(i -> i.eq("trade_id", tradeRights.getTradeId()));
            TradeRightsRefund one = iTradeRightsRefundRepository.getOne(query);
            if (ObjectUtils.isNotEmpty(one)) {
                rightsRefundViewVO.setRefundType(one.getRefundType());
                rightsRefundViewVO.setRefundAccount(one.getRefundAccount());
                rightsRefundViewVO.setRefundBankName(one.getRefundBankName());
                rightsRefundViewVO.setRefundName(one.getRefundName());
                rightsRefundViewVO.setCollectAccount(one.getCollectAccount());
                rightsRefundViewVO.setCollectBankName(one.getCollectBankName());
                rightsRefundViewVO.setCollectName(one.getCollectName());
            }
        }
        if (StringUtils.isNotBlank(dto.getJwtUserName())) {
            rightsRefundViewVO.setRefundPersonName(dto.getJwtUserName());
        }
        return rightsRefundViewVO;
    }

    @Override
    @Transactional
    public void getRightsRefundAmont(TradeRightsDTO.RefundDTO dto) {
        if (StringUtils.isNotBlank(dto.getId())) {
            TradeRights tradeRights = repository.getById(dto.getId());
            if (ObjectUtils.isEmpty(tradeRights)) {
                throw new BusinessException("没有查询到数据");
            }
            if (tradeRights.getState().equals(TradeRightsStateEnum.收到退货.getCode()) || tradeRights.getState().equals(TradeRightsStateEnum.等待退款.getCode())) {
                TradeRightsRefund tradeRightsRefund = new TradeRightsRefund();
                tradeRightsRefund.setRefundType(dto.getRefundType());
                tradeRightsRefund.setRefundAmount(tradeRights.getRefundAmount());
                tradeRightsRefund.setTradeId(tradeRights.getTradeId());
                tradeRightsRefund.setRightsId(tradeRights.getId());
                tradeRightsRefund.setOperationName(dto.getJwtUserName());
                if (dto.getRefundType().equals(PlataRefundTypeEnum.线下退款.getCode())) {
                    tradeRightsRefund.setCollectAccount(dto.getCollectAccount());
                    tradeRightsRefund.setCollectBankName(dto.getCollectBankName());
                    tradeRightsRefund.setCollectName(dto.getCollectName());
                    tradeRightsRefund.setRefundAccount(dto.getRefundAccount());
                    tradeRightsRefund.setRefundBankName(dto.getRefundBankName());
                    tradeRightsRefund.setRefundName(dto.getRefundName());
                    tradeRightsRefund.setState(TradeRightsRefundStateEnum.成功.getCode());
                    iTradeRightsRefundRepository.save(tradeRightsRefund);
                } else {
                    //原路返回
                    QueryWrapper<TradePay> query = MybatisPlusUtil.query();
                    query.and(i -> i.eq("trade_id", tradeRights.getTradeId()));
                    TradePay one = iTradePayRepository.getOne(query);
                    Trade trade = iTradeRepository.getById(one.getTradeId());
                    if (ObjectUtils.isEmpty(trade)) {
                        throw new BusinessException("没有查询到订单数据");
                    }
                    if (ObjectUtils.isNotEmpty(one)) {
                        if (one.getPayState() == 40) {
                            EntOrderDetailRefundRequest request = new EntOrderDetailRefundRequest();
                            String childMerchantId = iCommonShopRpc.lakalaNoByShopId(one.getShopId());

                            //测试放开
                            request.setMerchantId(MerchantBaseEnum.merchant_hly_Id.getValue());
                            request.setRequestId(UuidUtil.getUuid());
                            request.setOrderId(one.getTradeCode());
                            request.setRefundOrderId(tradeRights.getId());
                            String total = String.valueOf(tradeRights.getRefundAmount().multiply(new BigDecimal(100)).intValue());
                            if (Integer.parseInt(total) == 0) {
                                tradeRightsRefund.setState(TradeRightsRefundStateEnum.成功.getCode());
                            } else if (Integer.parseInt(total) > 0) {
                                List<RefundDetail> list = new ArrayList<>();
                                RefundDetail refundDetail = new RefundDetail();
                                if (StringUtils.isNotBlank(childMerchantId)) {
                                    refundDetail.setRcvMerchantId(childMerchantId);
                                }
                                refundDetail.setDetailOrderId(trade.getChildTradeId());//子订单号
                                refundDetail.setDetailRefundAmount(total);
                                list.add(refundDetail);
                                request.setRefundDetail(JSONObject.toJSONString(list));
                                request.setRefundTolAmount(total);
                                BossClient client = new BossClient(MerchantBaseEnum.merchant_hly_CertPath.getValue(),
                                        MerchantBaseEnum.merchant_hly_CertPass.getValue(), MerchantBaseEnum.serverUrl.getValue());
                                EntOrderRefundResponse response = null;
                                try {
                                    response = client.execute(request);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    throw new BusinessException(e.getMessage());
                                }
                                System.out.println("[REQUEST]: " + request.toString());

                                if (response.getStatus().equals(TradePayResultStateEnum.SUCCESS.getRemark())) {
                                    tradeRightsRefund.setState(TradeRightsRefundStateEnum.成功.getCode());
                                } else {
                                    tradeRightsRefund.setState(TradeRightsRefundStateEnum.失败.getCode());
                                }
                            } else {
                                throw new BusinessException("退款金额有误");
                            }
                            iTradeRightsRefundRepository.save(tradeRightsRefund);
                        } else {
                            throw new BusinessException("此订单没有支付");
                        }

                    } else {
                        log.info("platadmin.TradeRightsServiceImpl{}", "没有查询到支付信息");
                    }
                }
            } else {
                throw new BusinessException("已退款或不能退款");
            }
            tradeRights.setState(TradeRightsStateEnum.完成.getCode());
            repository.updateById(tradeRights);
            if (tradeRights.getRightsType().equals(TradeRightsTypeEnum.取消订单.getCode())) {
                QueryWrapper<TradeCancel> query = MybatisPlusUtil.query();
                query.and(i -> i.eq("trade_id", tradeRights.getTradeId()));
                TradeCancel one = iTradeCancelRepository.getOne(query);
                if (ObjectUtils.isNotEmpty(one)) {
                    one.setRefundState(TradeCancelRefundStateEnum.退款成功.getCode()).
                            setRefundTime(LocalDateTime.now()).
                            setCancelState(TradeCancelStateEnum.完成.getCode());
                    iTradeCancelRepository.updateById(one);
                }
            }
            //推送到POS
//             PosFinishAndCancelRequestDTO.DTO dto1 = new PosFinishAndCancelRequestDTO.DTO();
//             dto1.setNumber(tradeRights.getId()).setShop("dpos-int1021").setPlatformId("dpos1021");
            // iPosTradeRpc.FinishOrderRight(dto1);
        }
    }

    @Override
    public PageData<TradeRightsRefundVO.DetailVO> rightsRefunList(TradeRightsQTO.NewQTO qto) {
        IPage<TradeRightsRefund> pager = MybatisPlusUtil.pager(qto);
        QueryWrapper<TradeRightsRefund> query = new QueryWrapper<>();
        if (ObjectUtil.isNotEmpty(qto.getPayType())) {
            query.eq("pay_type", qto.getPayType());
        }
        //根据订单编号查询
        if (StrUtil.isNotEmpty(qto.getTradeCode())) {
            query.eq("trade_code", qto.getTradeCode());
        } else if (StrUtil.isNotEmpty(qto.getPhone())) {
            //根据用户手机号查询
            query.eq("phone", qto.getPhone());
        }
        IPage<TradeRightsRefund> page = iTradeRightsRefundRepository.page(pager, query);
        List<TradeRightsRefundVO.DetailVO> ListVOS = new ArrayList<>();
        for (TradeRightsRefund tradeRightsRefund : page.getRecords()) {
            TradeRightsRefundVO.DetailVO detailVO = new TradeRightsRefundVO.DetailVO();
            BeanUtils.copyProperties(tradeRightsRefund, detailVO);
            ListVOS.add(detailVO);
        }
        /*IPage<TradeRightsRefund> pager = MybatisPlusUtil.pager(qto);
        QueryWrapper<TradeRightsRefund> query = MybatisPlusUtil.query();
        if (ObjectUtils.isNotEmpty(qto.getCollectAccount())){
            query.and(i->i.like("collect_account",qto.getCollectAccount()));
        }
        if (ObjectUtils.isNotEmpty(qto.getCollectBankName())){
            query.and(i->i.like("collect_bank_name",qto.getCollectBankName()));
        }
        if (ObjectUtils.isNotEmpty(qto.getId())){
            query.and(i->i.like("id",qto.getId()));
        }
        if (ObjectUtils.isNotEmpty(qto.getTradeId())){
            query.and(i->i.like("trade_id",qto.getTradeId()));
        }
        if (ObjectUtils.isNotEmpty(qto.getRightsId())){
            query.and(i->i.like("rights_id",qto.getRightsId()));
        }
        if (ObjectUtils.isNotEmpty(qto.getRefundAccount())){
            query.and(i->i.like("refund_account",qto.getRefundAccount()));
        }
        if (ObjectUtils.isNotEmpty(qto.getRefundBankName())){
            query.and(i->i.like("refund_bank_name",qto.getRefundBankName()));
        }
        if (ObjectUtils.isNotEmpty(qto.getRefundName())){
            query.and(i->i.like("refund_name",qto.getRefundName()));
        }
        if (ObjectUtils.isNotEmpty(qto.getRefundType())){
            query.and(i->i.eq("refund_type",qto.getRefundType()));
        }
        if (ObjectUtils.isNotEmpty(qto.getState())){
            query.and(i->i.eq("state",qto.getState()));
        }
        if (ObjectUtils.isNotEmpty(qto.getPayStartDate())|| ObjectUtils.isNotEmpty(qto.getPayStartDateState())){
            GetQuery(query,qto.getPayStartDateState(),qto.getPayStartDate(),qto.getPayStartLittleDate());
        }
        IPage<TradeRightsRefund> page = iTradeRightsRefundRepository.page(pager,query);
        List<TradeRightsRefundVO.DetailVO> ListVOS=new ArrayList<>();
        if (ObjectUtils.isNotEmpty(page.getRecords()) || ObjectUtils.isNotEmpty(pager)){
            for (TradeRightsRefund tradeRightsRefund:page.getRecords()){
                TradeRightsRefundVO.DetailVO detailVO = new TradeRightsRefundVO.DetailVO();
                BeanUtils.copyProperties(tradeRightsRefund,detailVO);
                if (detailVO.getRefundType().equals(PlataRefundTypeEnum.线下退款.getCode())){
                    detailVO.setApplyType(TradePayTypeEnum.线下支付.getCode());
                }
                detailVO.setApplyState(tradeRightsRefund.getState());
                ListVOS.add(detailVO);
            }
        }*/

        return new PageData<>(ListVOS, qto.getPageNum(), qto.getPageSize(), page.getTotal());
    }

    @Autowired
    private ITradeRightsRepository iTradeRightsRepository;
    @Autowired
    private ITradeGoodsRepository iTradeGoodsRepository;

    @Override
    public TradeRightsRefundVO.DetailViewVO rightsRefunView(TradeRightsRefundDTO.IdDTO dto) {
        TradeRightsRefundVO.DetailViewVO detailViewVO = new TradeRightsRefundVO.DetailViewVO();
        if (StringUtils.isNotBlank(dto.getId())) {
            TradeRightsRefund tradeRightsRefund = iTradeRightsRefundRepository.getById(dto.getId());
            if (ObjectUtils.isNotEmpty(tradeRightsRefund)) {
                BeanUtils.copyProperties(tradeRightsRefund, detailViewVO);
            }
            TradeRights tradeRights = iTradeRightsRepository.getById(tradeRightsRefund.getRightsId());
            if (ObjectUtils.isNotEmpty(tradeRights)) {
                detailViewVO.setApplyTime(tradeRights.getApplyTime());
            }
            QueryWrapper<TradeGoods> query = MybatisPlusUtil.query();
            query.eq("trade_id", tradeRightsRefund.getTradeId());
            List<TradeGoods> tradeGoodsList = iTradeGoodsRepository.list(query);
            List<TradeListVO.TradeRightsGoodsDetailViewVO> viewVOList = new ArrayList<>();
            if (CollUtil.isNotEmpty(tradeGoodsList)) {
                for (TradeGoods tradeGoods : tradeGoodsList) {
                    TradeListVO.TradeRightsGoodsDetailViewVO viewVO = new TradeListVO.TradeRightsGoodsDetailViewVO();
                    viewVO.setTradeCode(tradeRightsRefund.getTradeCode());
                    viewVO.setGoodsName(tradeGoods.getGoodsName());
                    viewVO.setQuantity(tradeGoods.getQuantity());
                    viewVO.setGoodsOnePrice(tradeGoods.getSalePrice());
                    viewVO.setGoodsAmount(tradeGoods.getPayAmount());
                    viewVOList.add(viewVO);
                }
                detailViewVO.setMerchantName(tradeGoodsList.get(0).getShopName());
            }
            detailViewVO.setDetailViewVOList(viewVOList);
        }
        return detailViewVO;
    }

    private List<TradeRightsImg> getRightsImg(String id, String tradeId) {
        QueryWrapper<TradeRightsImg> query = MybatisPlusUtil.query();
        query.and(i -> i.eq("rights_id", id));
        query.and(i -> i.eq("trade_id", tradeId));
        List<TradeRightsImg> list = iTradeRightsImgRepository.list(query);
        if (ObjectUtils.isNotEmpty(list)) {
            return list;
        }
        return new ArrayList<>();
    }

}
