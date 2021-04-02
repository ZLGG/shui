package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.biz.support.trade.entity.*;
import com.gs.lshly.biz.support.trade.enums.MarketPtRightCheckStatusEnum;
import com.gs.lshly.biz.support.trade.enums.TradeRefundStatusEnum;
import com.gs.lshly.biz.support.trade.repository.*;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradeRightsService;
import com.gs.lshly.common.enums.*;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeRightsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeRightsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeRightsVO;
import com.gs.lshly.common.struct.merchadmin.pc.user.vo.PCMerchUserVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchShopRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.user.IPCMerchUserRpc;
import io.swagger.annotations.ApiModelProperty;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
* <p>
*  服务实现类
* </p>
* @author zdf
* @since 2020-12-17
*/
@Component
public class PCMerchTradeRightsServiceImpl implements IPCMerchTradeRightsService {

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
    private ITradeRightsImgRepository iTradeRightsImgRepository;
    @DubboReference
    private IPCMerchShopRpc ipcMerchShopRpc;
    @DubboReference
    private IPCMerchUserRpc ipcMerchUserRpc;
    @Override
    public PageData<PCMerchTradeRightsVO.RightList> pageData(PCMerchTradeRightsQTO.QTO qto) {
        QueryWrapper<PCMerchTradeRightsQTO.QTO> queryWrapper = new QueryWrapper<>();
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
        if (StringUtils.isNotEmpty(qto.getTradeCode())){
            queryWrapper.and(i->i.like("tg.`trade_code`",qto.getTradeCode()));
        }
        if (ObjectUtils.isNotEmpty(qto.getStartTime())){
            queryWrapper.and(i->i.ge("t.`cdate`",qto.getStartTime()));
        }
        if (ObjectUtils.isNotEmpty(qto.getEndTime())){
            queryWrapper.and(i->i.le("t.`cdate`",qto.getEndTime()));
        }
        queryWrapper.orderByDesc("t.`cdate`");
        IPage<PCMerchTradeRightsVO.ListVO> pager = MybatisPlusUtil.pager(qto);
        repository.selectMerchRightList(pager,queryWrapper);
        //获取售后表
        List<PCMerchTradeRightsVO.ListVO> records = pager.getRecords();
        List<PCMerchTradeRightsVO.RightList> listVO=new ArrayList<>();
        if (ObjectUtils.isNotEmpty(records)){
            for (PCMerchTradeRightsVO.ListVO record:records){
                QueryWrapper<TradeRightsGoods> query = MybatisPlusUtil.query();
                query.eq("rights_id",record.getId());
                List<TradeRightsGoods> list = iTradeRightsGoodsRepository.list(query);
                List<String> speedProgress = new ArrayList<>();
                PCMerchTradeRightsVO.RightList rightList = new PCMerchTradeRightsVO.RightList();
                rightList.setTradeCode(record.getOrderCode());
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

                    for (TradeRightsGoods tradeRightsGoods:list){
                        QueryWrapper<TradeRightsRecord> query1 = MybatisPlusUtil.query();
                        query1.and(i->i.eq("rights_id",tradeRightsGoods.getRightsId()));
                        query1.and(i->i.eq("trade_id",tradeRightsGoods.getTradeId()));
                        TradeRightsRecord one = iTradeRightsRecordRepository.getOne(query1);
                        if (ObjectUtils.isNotEmpty(one)){
                            rightList.setCdate(one.getCdate());
                        }
                        rightList.setId(record.getId()).
                                setTradeId(record.getTradeId()).
                                setRightsType(record.getRightsType()).
                                setQuantity(tradeRightsGoods.getQuantity()).
                                setSkuSpecValue(tradeRightsGoods.getSkuSpecValue()).
                                setGoodsName(tradeRightsGoods.getGoodsName()).
                                setSalePrice(tradeRightsGoods.getSalePrice()).
                                setCdate(record.getCdate());
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
    public PCMerchTradeRightsVO.DetailVO detailTradeRights(PCMerchTradeRightsDTO.IdDTO dto) {
        TradeRights tradeRights = repository.getById(dto.getId());
        PCMerchTradeRightsVO.DetailVO detailVo = new PCMerchTradeRightsVO.DetailVO();
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
        TradeRightsGoods one = iTradeRightsGoodsRepository.getOne(query);
        if (ObjectUtils.isEmpty(one)){
            throw new BusinessException("没有退货商品数据");
        }
        QueryWrapper<TradeRightsImg> query1 = MybatisPlusUtil.query();
        query1.and(i->i.eq("rights_id",dto.getId()));
        query1.and(i->i.eq("trade_id",trade.getId()));
        List<TradeRightsImg> list = iTradeRightsImgRepository.list(query1);
        if (ObjectUtils.isEmpty(one)){
            throw new BusinessException("没有退货凭证数据");
        }
        PCMerchShopVO.ShopSimpleVO shopSimpleVO = ipcMerchShopRpc.innerShopSimple(trade.getShopId());
        if (ObjectUtils.isNotEmpty(shopSimpleVO)){
            detailVo.setMerchantName(shopSimpleVO.getShopName());//商家名称
        }
        PCMerchUserVO.UserSimpleVO userSimpleVO = ipcMerchUserRpc.innerUserSimple(trade.getUserId());
        if (ObjectUtils.isNotEmpty(userSimpleVO)){
            detailVo.setUserName(userSimpleVO.getUserName());//会员名称
        }
        detailVo.setGoodsName(one.getGoodsName());//商品名称
        detailVo.setTradeDate(trade.getCdate());//下单时间
        detailVo.setTradeState(trade.getTradeState());//订单完成状态
        detailVo.setReceivingInformation(trade.getRecvPersonName()+" "+trade.getRecvPhone()+" "+trade.getRecvFullAddres());//收货信息(收货人名+电话+地址)
        detailVo.setQuantity(one.getQuantity());
        detailVo.setSalePrice(one.getSalePrice());//销售价
        detailVo.setTotalAmount(one.getRefundAmount());//总金额
        if (ObjectUtils.isNotEmpty(list)) {
            detailVo.setRightsImg(ListUtil.getIdList(TradeRightsImg.class,list,"rightsImg"));//评论图片
        }
        //退款撞塌10=等待平台处理 20=退款完成TradeRefundStatusEnum
        if (tradeRights.getRightsType().equals(TradeRightsTypeEnum.退货退款.getCode()) ||tradeRights.getRightsType().equals(TradeRightsTypeEnum.仅退款.getCode())){
            QueryWrapper<TradeRightsRefund> query2 = MybatisPlusUtil.query();
            query2.and(i->i.eq("rights_id",tradeRights.getId()));
            query2.and(i->i.eq("trade_id",tradeRights.getTradeId()));
            TradeRightsRefund one2 = iTradeRightsRefundRepository.getOne(query2);
            if (ObjectUtils.isNotEmpty(one2)){
                if (one2.getState().equals(TradeRightsRefundStateEnum.成功)){
                    detailVo.setRefundState(TradeRefundStatusEnum.退款完成.getCode());
                }
                else {
                    detailVo.setRefundState(TradeRefundStatusEnum.支付失败.getCode());
                }
            }else {
                detailVo.setRefundState(TradeRefundStatusEnum.等待平台退款.getCode());
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
    public void check(PCMerchTradeRightsDTO.IdCheckDTO dto) {
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
                    tradeRights.setRejectReason(dto.getRejectReason());
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
                         Trade byId = iTradeRepository.getById(tradeRights.getTradeId());
                         if (ObjectUtils.isNotEmpty(byId)){
                             byId.setTradeState(TradeStateEnum.待发货.getCode());
                             iTradeRepository.updateById(byId);
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
    public void receivedGet(PCMerchTradeRightsDTO.IdDTO qto) {
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
                Trade trade = iTradeRepository.getById(tradeRights.getTradeId());
                if (ObjectUtils.isNotEmpty(trade)) {
                    if (qto.getRefundAmount().compareTo(trade.getTradeAmount()) == 1) {
                        throw new BusinessException("退款金额大于商品的价格");
                    }
                    tradeRights.setRefundAmount(qto.getRefundAmount());
                }
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
    public void send(PCMerchTradeRightsDTO.SendDTO dto) {
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
