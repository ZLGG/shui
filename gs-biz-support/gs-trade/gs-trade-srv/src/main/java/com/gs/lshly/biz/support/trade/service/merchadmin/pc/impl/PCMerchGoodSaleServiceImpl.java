package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.Trade;
import com.gs.lshly.biz.support.trade.mapper.TradeMapper;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchGoodSaleService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


@Component
public class PCMerchGoodSaleServiceImpl implements IPCMerchGoodSaleService {

    @Autowired
    private TradeMapper tradeMapper;
    @Override
    public List<TradeVO.PayDatelistVO> exportPayDateList(TradeQTO.PayDateList dto) {
        List<TradeVO.PayDatelistVO> payDatelistVOS =  new ArrayList<>();
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
        packPayDate2(dto,payDatelistVO);

        payDatelistVOS.add(payDatelistVO);
        if (ObjectUtils.isEmpty(payDatelistVOS)){
            throw new BusinessException("没有可以导出的数据");
        }
        return payDatelistVOS;
    }


    public void packPayDate2(TradeQTO.PayDateList dto ,TradeVO.PayDatelistVO payDatelistVO) {
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
                    throw new BusinessException("查询时间条件错误！");
            }
        }
    }


    @Override
    public List<TradeVO.OperationlistVO> exportOperationList(TradeQTO.OperationList dto) {
        if (ObjectUtils.isEmpty(dto)){
            throw new BusinessException("参数不能为空");
        }
        List<TradeVO.OperationlistVO> operationlistVOS = new ArrayList<>();
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
                    throw new BusinessException("时间查询方式错误");
            }

        }
        //封装数据
        packOperationDate2(dto,operationlistVO);
        //封装扇形图数据
        packDiagramDate2(dto,operationlistVO);
        operationlistVOS.add(operationlistVO);
        if (ObjectUtils.isEmpty(operationlistVOS)){
            throw new BusinessException("没有可以导出的数据");
        }
        return operationlistVOS;
    }
    public void packDiagramDate2(TradeQTO.OperationList dto ,TradeVO.OperationlistVO operationlistVO) {
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
                    throw new BusinessException("错误时间查询方式");
            }
        }
    }
    public void packOperationDate2(TradeQTO.OperationList dto ,TradeVO.OperationlistVO operationlistVO) {
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
                    throw new BusinessException("查询时间条件错误！");
            }
        }
    }



}
