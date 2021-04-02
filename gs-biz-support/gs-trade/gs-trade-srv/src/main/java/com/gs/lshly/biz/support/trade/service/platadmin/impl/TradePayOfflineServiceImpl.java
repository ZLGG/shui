package com.gs.lshly.biz.support.trade.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.Trade;
import com.gs.lshly.biz.support.trade.entity.TradePay;
import com.gs.lshly.biz.support.trade.entity.TradePayOffline;
import com.gs.lshly.biz.support.trade.entity.TradePayOfflineImg;
import com.gs.lshly.biz.support.trade.repository.ITradePayOfflineImgRepository;
import com.gs.lshly.biz.support.trade.repository.ITradePayOfflineRepository;
import com.gs.lshly.biz.support.trade.repository.ITradePayRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeRepository;
import com.gs.lshly.biz.support.trade.service.platadmin.ITradePayOfflineService;
import com.gs.lshly.common.enums.TradePayOfficeEnum;
import com.gs.lshly.common.enums.TradePayStateEnum;
import com.gs.lshly.common.enums.TradeStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradePayOfflineDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradePayOfflineQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradePayOfflineVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.EnumUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbb.pc.stock.IBbbPcStockAddressRpc;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zst
 * @version 1.0
 * @date 2020/12/22 10:31
 */
@Slf4j
@Component
public class TradePayOfflineServiceImpl implements ITradePayOfflineService {

    @Autowired
    private ITradePayOfflineRepository iTradePayOfflineRepository;

    @Autowired
    private ITradePayOfflineImgRepository iTradePayOfflineImgRepository;

    @Autowired
    private ITradeRepository iTradeRepository;

    @Autowired
    private ITradePayRepository iTradePayRepository;

    @DubboReference
    private IBbbPcStockAddressRpc iBbbPcStockAddressRpc;

    @Override
    public PageData<TradePayOfflineVO.ListVO> pageData(TradePayOfflineQTO.QTO qto) {

        QueryWrapper<TradePayOffline> wrapper = new QueryWrapper<>();
        if (ObjectUtils.isNotEmpty(qto.getTradeId())) {
            wrapper.eq("trade_id", qto.getTradeId());
        }
        if (ObjectUtils.isNotEmpty(qto.getUserName())) {
            wrapper.eq("user_name", qto.getUserName());
        }
        if (ObjectUtils.isNotEmpty(qto.getAccountNumber())) {
            wrapper.eq("account_number", qto.getAccountNumber());
        }
        if (ObjectUtils.isNotEmpty(qto.getAccountName())) {
            wrapper.eq("account_name", qto.getAccountName());
        }
        if (ObjectUtils.isNotEmpty(qto.getBankName())) {
            wrapper.eq("bank_name", qto.getBankName());
        }

        if (ObjectUtils.isNotEmpty(qto.getPayAmountType())) {
            switch (qto.getPayAmountType()) {
                case 0:
                    break;
                case 1:
                    wrapper.gt("pay_amount", qto.getPayAmount());
                    break;
                case 2:
                    wrapper.lt("pay_amount", qto.getPayAmount());
                    break;
                case 3:
                    wrapper.eq("pay_amount", qto.getPayAmount());
                    break;
                case 4:
                    wrapper.ge("pay_amount", qto.getPayAmount());
                    break;
                case 5:
                    wrapper.le("pay_amount", qto.getPayAmount());
                    break;
                case 6:
                    wrapper.ge("pay_amount", qto.getPayAmountStart());
                    wrapper.le("pay_amount", qto.getPayAmountEnd());
                    break;
                default:
                    throw new BusinessException("错误的转账金额查询类型");
            }
        }

        if (ObjectUtils.isNotEmpty(qto.getCDateType())) {
            switch (qto.getCDateType()) {
                case 0:
                    break;
                case 1:
                    wrapper.gt("pay_amount", qto.getCDate());
                    break;
                case 2:
                    wrapper.lt("pay_amount", qto.getCDate());
                    break;
                case 3:
                    wrapper.eq("pay_amount", qto.getCDate());
                    break;
                case 4:
                    wrapper.ge("pay_amount", qto.getCDateStart());
                    wrapper.le("pay_amount", qto.getCDateEnd());
                    break;
                default:
                    throw new BusinessException("错误的创建时间查询类型");
            }
        }
        wrapper.orderByDesc("cdate");
        IPage<TradePayOffline> page = MybatisPlusUtil.pager(qto);
        iTradePayOfflineRepository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, TradePayOfflineVO.ListVO.class, page);
    }

    @Override
    public TradePayOfflineVO.DetailVO showDetail(String id) {
        TradePayOfflineVO.DetailVO detailVO=new  TradePayOfflineVO.DetailVO();
        TradePayOfflineVO.Transfer transfer=new  TradePayOfflineVO.Transfer();
        List <String> img = new ArrayList<>();
        TradePayOffline tradePayOffline = iTradePayOfflineRepository.getById(id);
        if(ObjectUtils.isNotEmpty(tradePayOffline)){
            BeanCopyUtils.copyProperties(tradePayOffline,transfer);
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("offline_id",tradePayOffline.getId());
            List<TradePayOfflineImg> list= iTradePayOfflineImgRepository.list(wrapper);
            if(ObjectUtils.isNotEmpty(list)){
                for (TradePayOfflineImg tradePayOfflineImg:list) {
                    img.add(tradePayOfflineImg.getOfflineImg());
                }
                transfer.setCertificatePicture(img);
            }
            detailVO.setTransfer(transfer);

            TradePayOfflineVO.Order order = new  TradePayOfflineVO.Order();
            Trade trade = iTradeRepository.getById(tradePayOffline.getTradeId());
            if(ObjectUtils.isNotEmpty(trade)){
                BeanCopyUtils.copyProperties(trade,order);
                order.setTradeId(trade.getId());
                String recvId = trade.getRecvAddresId();
                //这里需要物流模块提供一个RPC服务，通过提供一个收货地址id，返回收货地址
                String address = iBbbPcStockAddressRpc.queryStockAddress(recvId);
                if(ObjectUtils.isNotEmpty(address)){
                    order.setRecvAddress(address);
                    detailVO.setOrder(order);
                }
            }
        }

        return detailVO;
    }

    @Override
    public Boolean verify(TradePayOfflineDTO.DTO dto) {
        TradePayOffline tradePayOffline = iTradePayOfflineRepository.getById(dto.getId());
        tradePayOffline.setVerifyRemark(dto.getVerifyRemark());
        tradePayOffline.setVerifyState(dto.getVerifyState());
        tradePayOffline.setVerifyAttach(dto.getVerifyAttach());
        tradePayOffline.setVerifyName(dto.getJwtUserName());
        tradePayOffline.setUdate(LocalDateTime.now());
        iTradePayOfflineRepository.updateById(tradePayOffline);
        return true;
    }


    @Override
    @Transactional
    public void updateByRefuse(TradePayOfflineDTO.RefuseDTO dto) {
        TradePayOffline tradePayOffline = iTradePayOfflineRepository.getById(dto.getId());
        tradePayOffline.setVerifyRemark(dto.getVerifyRemark());
        Trade trade = iTradeRepository.getById(tradePayOffline.getTradeId());
        QueryWrapper<TradePay> query = MybatisPlusUtil.query();
        query.and(i->i.eq("trade_id",tradePayOffline.getTradeId()));
        TradePay one = iTradePayRepository.getOne(query);
        switch (dto.getVerifyType()) {
            case 10:
                tradePayOffline.setVerifyState(TradePayOfficeEnum.确认.getCode());
                if (ObjectUtils.isNotEmpty(trade)){
                    trade.setTradeState(TradeStateEnum.待发货.getCode());
                }
                if (ObjectUtils.isNotEmpty(one)){
                    one.setPayState(TradePayStateEnum.已支付.getCode());
                }
                break;
            case 20:
                tradePayOffline.setVerifyState(TradePayOfficeEnum.驳回.getCode());
                break;
            case 30:
                tradePayOffline.setVerifyState(TradePayOfficeEnum.待确认.getCode());
                break;
            default:
                throw new BusinessException("Exception e");
        }
        tradePayOffline.setMerchantAccountId(dto.getJwtUserId());
        tradePayOffline.setVerifyName(dto.getJwtUserName());
        tradePayOffline.setUdate(LocalDateTime.now());
        iTradePayRepository.saveOrUpdate(one);
        iTradeRepository.saveOrUpdate(trade);
        iTradePayOfflineRepository.updateById(tradePayOffline);
    }

    @Override
    public List<TradePayOfflineVO.ListVOExport> exportPayOfficeData(TradePayOfflineQTO.IdListQTO qto) {
        if (ObjectUtils.isEmpty(qto)){
            throw new BusinessException("参数不能为空");
        }
        List<TradePayOffline> marginDetailList= iTradePayOfflineRepository.listByIds(qto.getIdList());
        if(ObjectUtils.isEmpty(marginDetailList)){
            throw new BusinessException("查询异常");
        }
        List<TradePayOfflineVO.ListVOExport> excelPayOfficeVOS = new ArrayList<>();

        for (TradePayOffline tradePayOffline:marginDetailList) {
            TradePayOfflineVO.ListVOExport payOfficeVO = new TradePayOfflineVO.ListVOExport();
            BeanUtils.copyProperties(tradePayOffline,payOfficeVO);
            if(ObjectUtils.isEmpty(tradePayOffline.getTradeId())){
                throw new BusinessException("数据异常");
            }
            //封装线下审核数据
            packMargindetailDate(tradePayOffline);
            excelPayOfficeVOS.add(payOfficeVO);
        }
        return excelPayOfficeVOS;
    }

    private void packMargindetailDate(TradePayOffline tradePayOffline){
        TradePayOfflineVO.ListVOExport payOfficeVO = new TradePayOfflineVO.ListVOExport();
        payOfficeVO.setUserName(tradePayOffline.getUserName());
        payOfficeVO.setPayAmount(tradePayOffline.getPayAmount());
        payOfficeVO.setVerifyRemark(tradePayOffline.getVerifyRemark());
        payOfficeVO.setPayRemark(tradePayOffline.getPayRemark());
        payOfficeVO.setVerifyState(EnumUtil.getText(tradePayOffline.getVerifyState(),TradePayOfficeEnum.class));
        payOfficeVO.setAccountName(tradePayOffline.getAccountName());
        payOfficeVO.setBankName(tradePayOffline.getBankName());
        payOfficeVO.setAccountNumber(tradePayOffline.getAccountNumber());
        payOfficeVO.setCdate(tradePayOffline.getCdate());
        payOfficeVO.setTradeId(tradePayOffline.getTradeId());
    }

}
