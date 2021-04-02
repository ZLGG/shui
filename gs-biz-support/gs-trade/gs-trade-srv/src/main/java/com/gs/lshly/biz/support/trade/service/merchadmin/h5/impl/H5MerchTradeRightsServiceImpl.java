package com.gs.lshly.biz.support.trade.service.merchadmin.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.trade.entity.*;
import com.gs.lshly.biz.support.trade.enums.MarketPtRightCheckStatusEnum;
import com.gs.lshly.biz.support.trade.enums.TradeRefundStatusEnum;
import com.gs.lshly.biz.support.trade.repository.*;
import com.gs.lshly.biz.support.trade.service.merchadmin.h5.IH5MerchTradeRightsService;
import com.gs.lshly.common.enums.*;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.vo.H5MerchShopVO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.dto.H5MerchTradeRightsDTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradeRightsQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeRightsVO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchUserVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeRightsDTO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.merchadmin.h5.merchant.IH5MerchShopRpc;
import com.gs.lshly.rpc.api.merchadmin.h5.user.IH5MerchUserRpc;
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
*  服务实现类
* </p>
* @author zst
* @since 2021-2-1
*/
@Component
public class H5MerchTradeRightsServiceImpl implements IH5MerchTradeRightsService {

    @Autowired
    private ITradeRightsRepository repository;
    @Autowired
    private ITradeRightsGoodsRepository iTradeRightsGoodsRepository;
    @Autowired
    private ITradeRightsRefundRepository iTradeRightsRefundRepository;
    @Autowired
    private ITradeRightsRecordRepository iTradeRightsRecordRepository;
    @Autowired
    private ITradeComplaintRepository iTradeComplaintRepository;
    @Autowired
    private ITradeRepository iTradeRepository;
    @Autowired
    private ITradeGoodsRepository iTradeGoodsRepository;
    @Autowired
    private ITradeRightsImgRepository iTradeRightsImgRepository;
    @DubboReference
    private IH5MerchShopRpc ipcMerchShopRpc;
    @DubboReference
    private IH5MerchUserRpc ipcMerchUserRpc;
    @Override
    public PageData<H5MerchTradeRightsVO.RightList> pageData(H5MerchTradeRightsQTO.QTO qto) {
        QueryWrapper<H5MerchTradeRightsQTO.QTO> queryWrapper = new QueryWrapper<>();
        if (ObjectUtils.isNotEmpty(qto.getTradeRightType())) {
            queryWrapper.and(i -> i.eq("tg.`source_type`", qto.getTradeRightType()));
        }
        if (StringUtils.isNotBlank(qto.getJwtShopId())){
            queryWrapper.and(i -> i.eq("tg.`shop_id`", qto.getJwtShopId()));
        }
        if (ObjectUtils.isNotEmpty(qto.getRightType())){
            queryWrapper.and(i->i.eq("t.`state`",qto.getRightType()).or().eq(qto.getRightType()==40,"t.`state`",30));
        }
        if (ObjectUtils.isNotEmpty(qto.getTradeRightsType())){
            queryWrapper.and(i->i.eq("t.`rights_type`",qto.getTradeRightsType()));
        }
        if (ObjectUtils.isNotEmpty(qto.getId())){
            queryWrapper.and(i->i.like("t.`id`",qto.getId()));
        }
        if (ObjectUtils.isNotEmpty(qto.getTradeId())){
            queryWrapper.and(i->i.like("t.`trade_id`",qto.getTradeId()));
        }
        if (ObjectUtils.isNotEmpty(qto.getEndTime()) || ObjectUtils.isNotEmpty(qto.getStartTime())){
            queryWrapper.and(i->i.ge("t.`cdate`",qto.getStartTime()).le("t.`cdate`",qto.getEndTime()));
        }
        queryWrapper.orderByDesc("t.cdate");
        IPage<H5MerchTradeRightsVO.ListVO> pager = MybatisPlusUtil.pager(qto);
        repository.selectMerchH5RightList(pager,queryWrapper);
        //获取售后表
        List<H5MerchTradeRightsVO.ListVO> records = pager.getRecords();
        List<H5MerchTradeRightsVO.RightList> listVO=new ArrayList<>();
        if (ObjectUtils.isNotEmpty(records)){
            for (H5MerchTradeRightsVO.ListVO record:records){
                QueryWrapper<TradeRightsGoods> query = MybatisPlusUtil.query();
                query.eq("rights_id",record.getId());
                List<TradeRightsGoods> list = iTradeRightsGoodsRepository.list(query);
                List<String> speedProgress = new ArrayList<>();
                H5MerchTradeRightsVO.RightList rightList = new H5MerchTradeRightsVO.RightList();
                if (record.getState().equals(TradeRightsStateEnum.驳回.getCode())){
                    QueryWrapper<TradeComplaint> queryComplaint = MybatisPlusUtil.query();
                    query.eq("trade_id",record.getTradeId());
                    List<TradeComplaint> list1 = iTradeComplaintRepository.list(queryComplaint);
                    if (ObjectUtils.isNotEmpty(list1)){
                        for (TradeComplaint tradeComplaint : list1) {
                            if (tradeComplaint.getHandleState().equals(TradeComplaintStateEnum.关闭投诉.getCode())){
                                speedProgress.add(0,"投诉驳回");
                            }else if(tradeComplaint.getHandleState().equals(TradeComplaintStateEnum.投诉成功.getCode())){
                                speedProgress.add(0,"投诉成功");
                            }else if(tradeComplaint.getHandleState().equals(TradeComplaintStateEnum.买家撤销了投诉.getCode())){
                                speedProgress.add(0,"买家取消投诉");
                            }
                            else if(tradeComplaint.getHandleState().equals(TradeComplaintStateEnum.等待处理.getCode())){
                                speedProgress.add(0,"投诉受理");
                            }
                        }
                    }
                }
                if (ObjectUtils.isNotEmpty(list)){

                    List<H5MerchTradeRightsVO.RightGoodsList> goodsLists = new ArrayList<>();
                    for (TradeRightsGoods tradeRightsGoods:list){
                        H5MerchTradeRightsVO.RightGoodsList rightGoodsList = new H5MerchTradeRightsVO.RightGoodsList();
                        QueryWrapper<TradeRightsRecord> query1 = MybatisPlusUtil.query();
                        query1.and(i->i.eq("rights_id",tradeRightsGoods.getRightsId()));
                        query1.and(i->i.eq("trade_id",tradeRightsGoods.getTradeId()));
                        TradeRightsRecord one = iTradeRightsRecordRepository.getOne(query1);
                        if (ObjectUtils.isNotEmpty(one)){
                            rightList.setCdate(one.getCdate());
                        }
                        TradeGoods byId = iTradeGoodsRepository.getById(tradeRightsGoods.getTradeGoodsId());
                        if (ObjectUtils.isNotEmpty(byId)){
                            rightGoodsList.setSkuImg(byId.getSkuImg());
                        }
                        rightGoodsList.setGoodsName(tradeRightsGoods.getGoodsName()).
                                setQuantity(tradeRightsGoods.getQuantity()).
                                setSalePrice(tradeRightsGoods.getSalePrice()).
                                setSkuSpecValue(tradeRightsGoods.getSkuSpecValue()).
                                setTradeGoodsId(tradeRightsGoods.getId());
                        goodsLists.add(rightGoodsList);
                        rightList.setId(record.getId()).
                                setTradeId(record.getTradeId()).
                                setRightsType(record.getRightsType()).
                                setQuantity(tradeRightsGoods.getQuantity()).
                                setSkuSpecValue(tradeRightsGoods.getSkuSpecValue()).
                                setGoodsName(tradeRightsGoods.getGoodsName()).
                                setSalePrice(tradeRightsGoods.getSalePrice()).
                                setCdate(record.getCdate()).
                                setState(record.getState());

                        QueryWrapper<TradeRightsImg> query2 = MybatisPlusUtil.query();
                        query2.and(i->i.eq("rights_id",tradeRightsGoods.getRightsId()));
                        query2.and(i->i.eq("trade_id",tradeRightsGoods.getTradeId()));
                        List<TradeRightsImg> one1 = iTradeRightsImgRepository.list(query2);
                        if (ObjectUtils.isNotEmpty(one1)) {
                            rightList.setRightsImg(ListUtil.getIdList(TradeRightsImg.class,one1,"rightsImg"));//评论图片
                        }
                        //如果是换货
                        //等待买家回寄-》查询到了买家回寄信息-》商家收到回寄-》商家发货
                        //如果是退货退款
                        //等待买家回寄-》查询到了买家回寄信息
                        //
                        //状态(10:申请,20:驳回,30:通过,40:已退货,50:收到退货,80:等待发货,90:已发货,60:等待退款,70:退款完成,91:确认收货,95:用户取消,99:完成)
                        //售后类型(10:换货,20:仅退款,30:退货退款)
                        //到商家收到退货，如果是换货，商家

                        switch (record.getState()){
                            case 10: speedProgress.add("等待商家审核");break;
                            case 20: speedProgress.add("商家已驳回");break;
                            case 30:
                            case 40:
                            case 50:
                            case 60:
                            case 70:
                            case 80:
                            case 90:
                            case 91:if(record.getRightsType().equals(10)){ speedProgress.add("等待商家处理");break;}else { speedProgress.add("等待平台处理");break;}
                            case 95: speedProgress.add("用户取消");break;
                            case 99:if(record.getRightsType().equals(10)){  speedProgress.add("商家已处理");break;}else {  speedProgress.add("平台已处理");break;}
                        }
                        rightList.setSpeedProgress(speedProgress);
                        rightList.setRightGoodsList(goodsLists);
                        listVO.add(rightList);
                    }

                }

            }
        }
        return new PageData<>(listVO, qto.getPageNum(), qto.getPageSize(),listVO.size());
    }

    @Override
    public void addTradeRights(PCMerchTradeRightsDTO.ETO eto) {
        TradeRights tradeRights = new TradeRights();
        BeanUtils.copyProperties(eto, tradeRights);
        repository.save(tradeRights);
    }


    @Override
    public void deleteTradeRights(PCMerchTradeRightsDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }

    @Override
    public void editTradeRights(PCMerchTradeRightsDTO.ETO eto) {
        TradeRights tradeRights = new TradeRights();
        BeanUtils.copyProperties(eto, tradeRights);
        repository.updateById(tradeRights);
    }

    @Override
    public H5MerchTradeRightsVO.DetailVO detailTradeRights(H5MerchTradeRightsDTO.IdDTO dto) {
        List<H5MerchTradeRightsVO.RightGoodsList> goodsLists = new ArrayList<>();
        TradeRights tradeRights = repository.getById(dto.getId());
        H5MerchTradeRightsVO.DetailVO detailVo = new H5MerchTradeRightsVO.DetailVO();
        if(ObjectUtils.isEmpty(tradeRights)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(tradeRights, detailVo);
        Trade trade = iTradeRepository.getById(detailVo.getTradeId());
        if (ObjectUtils.isEmpty(trade)){
            throw new BusinessException("没有订单数据");
        }
        QueryWrapper<TradeRightsGoods> query = MybatisPlusUtil.query();
        query.and(i->i.eq("rights_id",dto.getId()));
        query.and(i->i.eq("trade_id",trade.getId()));
        List<TradeRightsGoods> list = iTradeRightsGoodsRepository.list(query);
        for (TradeRightsGoods tradeRightsGoods:list) {
            if (ObjectUtils.isEmpty(tradeRightsGoods)){
                throw new BusinessException("没有退货商品数据");
            }
            QueryWrapper<TradeRightsImg> query1 = MybatisPlusUtil.query();
            query1.and(i->i.eq("rights_id",dto.getId()));
            query1.and(i->i.eq("trade_id",trade.getId()));
            List<TradeRightsImg> one1 = iTradeRightsImgRepository.list(query1);
            if (ObjectUtils.isEmpty(tradeRightsGoods)){
                throw new BusinessException("没有退货凭证数据");
            }
            H5MerchShopVO.ShopSimpleVO shopSimpleVO = ipcMerchShopRpc.innerShopSimple(trade.getShopId());
            if (ObjectUtils.isNotEmpty(shopSimpleVO)){
                detailVo.setMerchantName(shopSimpleVO.getShopName());//商家名称
            }
            H5MerchUserVO.UserSimpleVO userSimpleVO = ipcMerchUserRpc.innerUserSimple(trade.getUserId());
            if (ObjectUtils.isNotEmpty(userSimpleVO)){
                detailVo.setUserName(userSimpleVO.getUserName());//会员名称
            }
            H5MerchTradeRightsVO.RightGoodsList rightGoodsList = new H5MerchTradeRightsVO.RightGoodsList();
            rightGoodsList.setGoodsName(tradeRightsGoods.getGoodsName()).
                    setQuantity(tradeRightsGoods.getQuantity()).
                    setSalePrice(tradeRightsGoods.getSalePrice()).
                    setSkuSpecValue(tradeRightsGoods.getSkuSpecValue()).
                    setTradeGoodsId(tradeRightsGoods.getId());
            goodsLists.add(rightGoodsList);
            //售后商品集合
            detailVo.setRightGoodsList(goodsLists);
            detailVo.setGoodsName(tradeRightsGoods.getGoodsName());//商品名称
            TradeGoods byId = iTradeGoodsRepository.getById(tradeRightsGoods.getTradeGoodsId());
            if (ObjectUtils.isNotEmpty(byId)){
                detailVo.setSkuImg(byId.getSkuImg());//商品图片
            }
            detailVo.setTradeDate(trade.getCdate());//下单时间
            detailVo.setTradeState(trade.getTradeState());//订单完成状态
            detailVo.setReceivingInformation(trade.getRecvPersonName()+" "+trade.getRecvPhone()+" "+trade.getRecvFullAddres());//收货信息(收货人名+电话+地址)
            detailVo.setQuantity(tradeRightsGoods.getQuantity());
            detailVo.setSalePrice(tradeRightsGoods.getSalePrice());//销售价
            detailVo.setTotalAmount(tradeRightsGoods.getRefundAmount());//总金额
            if (ObjectUtils.isNotEmpty(one1)) {
                detailVo.setRightsImg(ListUtil.getIdList(TradeRightsImg.class,one1,"rightsImg"));//评论图片
            }
        }
        //退款撞塌10=等待平台处理 20=退款完成TradeRefundStatusEnum
        if (tradeRights.getRightsType().equals(TradeRightsTypeEnum.退货退款.getCode()) ||tradeRights.getRightsType().equals(TradeRightsTypeEnum.仅退款.getCode())){
            QueryWrapper<TradeRightsRefund> query2 = MybatisPlusUtil.query();
            query2.and(i->i.eq("rights_id",tradeRights.getId()));
            query2.and(i->i.eq("trade_id",tradeRights.getTradeId()));
            List<TradeRightsRefund> list2 = iTradeRightsRefundRepository.list(query2);
            for (TradeRightsRefund tradeRightsRefund:list2) {
                if (ObjectUtils.isNotEmpty(tradeRightsRefund)){
                    if (tradeRightsRefund.getState().equals(TradeRightsRefundStateEnum.成功.getCode())){
                        detailVo.setRefundState(TradeRefundStatusEnum.退款完成.getCode());
                    }
                    else {
                        detailVo.setRefundState(TradeRefundStatusEnum.支付失败.getCode());
                    }
                }else {
                    detailVo.setRefundState(TradeRefundStatusEnum.等待平台退款.getCode());
                }
            }
        }
        List<String> speedProgress = new ArrayList<>();
        if (tradeRights.getState().equals(TradeRightsStateEnum.驳回.getCode())){
            QueryWrapper<TradeComplaint> queryComplaint = MybatisPlusUtil.query();
            query.eq("trade_id",tradeRights.getTradeId());
            List<TradeComplaint> list1 = iTradeComplaintRepository.list(queryComplaint);
            if (ObjectUtils.isNotEmpty(list1)){
                for (TradeComplaint tradeComplaint : list1) {
                    if (tradeComplaint.getHandleState().equals(TradeComplaintStateEnum.关闭投诉.getCode())){
                        speedProgress.add(0,"投诉驳回");
                    }else if(tradeComplaint.getHandleState().equals(TradeComplaintStateEnum.投诉成功.getCode())){
                        speedProgress.add(0,"投诉成功");
                    }else if(tradeComplaint.getHandleState().equals(TradeComplaintStateEnum.买家撤销了投诉.getCode())){
                        speedProgress.add(0,"买家取消投诉");
                    }
                    else if(tradeComplaint.getHandleState().equals(TradeComplaintStateEnum.等待处理.getCode())){
                        speedProgress.add(0,"投诉受理");
                    }
                }
            }
        }
        switch (tradeRights.getState()){
            case 10:detailVo.setCheckState("等待商家审核"); speedProgress.add("等待商家审核");break;
            case 20: speedProgress.add("商家已驳回");detailVo.setCheckState("商家不同意售后申请");break;
            case 30:  detailVo.setCheckState("商家同意售后申请");
            case 40: detailVo.setCheckState("商家同意售后申请");
            case 50: detailVo.setCheckState("商家同意售后申请");
            case 60: detailVo.setCheckState("商家同意售后申请");
            case 70: detailVo.setCheckState("商家同意售后申请");
            case 80: detailVo.setCheckState("商家同意售后申请");
            case 90: detailVo.setCheckState("商家同意售后申请");
            case 91: detailVo.setCheckState("商家同意售后申请");if(tradeRights.getRightsType().equals(10)){ speedProgress.add("等待商家处理");break;}else {
                speedProgress.add("等待平台处理");
                break;}
            case 95: detailVo.setCheckState("商家同意售后申请"); speedProgress.add("用户取消");break;
            case 99: detailVo.setCheckState("商家同意售后申请");if(tradeRights.getRightsType().equals(10)){  speedProgress.add("商家已处理");break;}else {  speedProgress.add("平台已处理");break;}
        }
        detailVo.setSpeedProgress(speedProgress);
        return detailVo;
    }
    @Autowired
    private ITradeCancelRepository iTradeCancelRepository;

    @Override
    @Transactional
    public void check(H5MerchTradeRightsDTO.IdCheckDTO dto) {
        //售后表的状态是是否为申请
        TradeRights tradeRights = repository.getById(dto);
        TradeRightsRecord tradeRightsRecord = new TradeRightsRecord();
        if (ObjectUtils.isNotEmpty(tradeRights)){
            if (tradeRights.getState().equals(TradeRightsStateEnum.申请.getCode())){
                if (dto.getCheckState().equals(MarketPtRightCheckStatusEnum.审核驳回.getCode())){
                    if (StringUtils.isBlank(dto.getRejectReason())){
                        throw new BusinessException("请输入驳回原因");
                    }
                    tradeRights.setState(TradeRightsStateEnum.驳回.getCode());
                    if (tradeRights.getRightsType().equals(TradeRightsTypeEnum.仅退款.getCode())){
                        QueryWrapper<TradeCancel> query = MybatisPlusUtil.query();
                        query.and(i->i.eq("trade_id",tradeRights.getTradeId()));
                        TradeCancel one = iTradeCancelRepository.getOne(query);
                        if (ObjectUtils.isNotEmpty(one)){
                            one.setCancelState(TradeCancelStateEnum.商家驳回.getCode()).
                                    setRejectReason(dto.getRejectReason()).
                                    setRejectTime(LocalDateTime.now());
                            iTradeCancelRepository.updateById(one);
                        }
                    }
                }else {
                    if (tradeRights.getRightsType().equals(TradeRightsTypeEnum.仅退款.getCode())){
                        tradeRights.setState(TradeRightsStateEnum.等待退款.getCode());
                        QueryWrapper<TradeCancel> query = MybatisPlusUtil.query();
                        query.and(i->i.eq("trade_id",tradeRights.getTradeId()));
                        TradeCancel one = iTradeCancelRepository.getOne(query);
                        if (ObjectUtils.isNotEmpty(one)){
                            one.setCancelState(TradeCancelStateEnum.退款中.getCode()).
                                    setPassTime(LocalDateTime.now()).setRefundState(TradeCancelRefundStateEnum.等待退款.getCode());
                            iTradeCancelRepository.updateById(one);
                        }
                    }else {
                        tradeRights.setState(TradeRightsStateEnum.通过.getCode());
                    }
                }
            }else {
                throw new BusinessException("售后单不为申请状态");
            }
        }
        BeanUtils.copyProperties(tradeRights,tradeRightsRecord);
        tradeRightsRecord.setId(null).setCdate(LocalDateTime.now()).setUdate(LocalDateTime.now());
        iTradeRightsRecordRepository.save(tradeRightsRecord);
        repository.updateById(tradeRights);
    }
    /**
     * 收到退货
     * */
    @Override
    public void receivedGet(H5MerchTradeRightsDTO.IdDTO qto) {
        if (StringUtils.isBlank(qto.getId())){
            throw new BusinessException("没有传入售后ID");
        }
        TradeRights tradeRights = repository.getById(qto.getId());
        if (ObjectUtils.isEmpty(tradeRights)){
            throw new BusinessException("没有数据");
        }
        if (tradeRights.getRightsType().equals(TradeRightsTypeEnum.换货.getCode()) && tradeRights.getState().equals(TradeRightsStateEnum.已退货.getCode())){
            tradeRights.setState(TradeRightsStateEnum.等待发货.getCode());
        }
        if (tradeRights.getState().equals(TradeRightsStateEnum.已退货.getCode()) && tradeRights.getRightsType().equals(TradeRightsTypeEnum.退货退款.getCode())){
            tradeRights.setState(TradeRightsStateEnum.收到退货.getCode());
            if (StringUtils.isNotBlank(qto.getRefundNotes())){
                tradeRights.setRefundRemarks(qto.getRefundNotes());
            }
            if (ObjectUtils.isNotEmpty(qto.getRefundAmount())){
                QueryWrapper<TradeRightsGoods> query = MybatisPlusUtil.query();
                query.and(i->i.eq("rights_id",tradeRights.getId()));
                query.and(i->i.eq("trade_id",tradeRights.getTradeId()));
                List<TradeRightsGoods> list = iTradeRightsGoodsRepository.list(query);
                BigDecimal total=BigDecimal.ZERO;
                if (ObjectUtils.isNotEmpty(list)){
                    for (TradeRightsGoods tradeRightsGoods:list){
                        if (ObjectUtils.isNotEmpty(tradeRightsGoods.getSalePrice())) {
                            total.add(tradeRightsGoods.getSalePrice());
                        }
                    }
                }
                if (tradeRights.getRefundAmount().compareTo(total)==-1){
                    throw new BusinessException("退款金额大于商品的价格");
                }
                tradeRights.setRefundAmount(qto.getRefundAmount());
            }else {
                throw new BusinessException("退款备注不能为空");
            }
        }
        repository.updateById(tradeRights);
    }

    /**
     * 商家寄回给用户
     * */
    @Override
    public void send(H5MerchTradeRightsDTO.SendDTO dto) {
        TradeRights tradeRights = repository.getById(dto.getId());
        if (ObjectUtils.isEmpty(tradeRights)){
            throw new BusinessException("没有数据");
        }
        if (tradeRights.getState().equals(TradeRightsStateEnum.等待发货.getCode()) && tradeRights.getRightsType().equals(TradeRightsTypeEnum.换货.getCode())){
            tradeRights.setState(TradeRightsStateEnum.已发货.getCode()).setSendBackLogisticsDate(LocalDateTime.now());
            if (StringUtils.isNotBlank(dto.getSendBackLogisticsName())){
                tradeRights.setSendBackLogisticsName(dto.getSendBackLogisticsName());
            }
            if (StringUtils.isNotBlank(dto.getSendBackLogisticsNum())){
                tradeRights.setSendBackLogisticsNum(dto.getSendBackLogisticsNum());
            }
        }else {
            throw new BusinessException("不能寄回");
        }
        repository.updateById(tradeRights);
    }
}
