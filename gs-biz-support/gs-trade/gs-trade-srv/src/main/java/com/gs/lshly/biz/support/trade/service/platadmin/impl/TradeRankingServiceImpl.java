package com.gs.lshly.biz.support.trade.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.Trade;
import com.gs.lshly.biz.support.trade.entity.TradeGoods;
import com.gs.lshly.biz.support.trade.entity.TradeRanking;
import com.gs.lshly.biz.support.trade.mapper.TradeGoodsMapper;
import com.gs.lshly.biz.support.trade.mapper.TradeRankingMapper;
import com.gs.lshly.biz.support.trade.mapper.TradeSettlementMapper;
import com.gs.lshly.biz.support.trade.service.platadmin.ITradeRankingService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeRankingQTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeSettlementQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeRankingVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.util.*;


/**
* <p>
*  服务实现类
* </p>
* @author zst
* @since 2020-12-18
*/
@Component
public class TradeRankingServiceImpl implements ITradeRankingService {

    @Autowired
    private TradeSettlementMapper tradeSettlementMapper;
    @Autowired
    private TradeGoodsMapper tradeGoodsMapper;
    @Autowired
    private TradeRankingMapper tradeRankingMapper;

        //获取当天的开始时间
        public static java.util.Date getDayBegin() {
            Calendar cal = new GregorianCalendar();
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            return cal.getTime();
        }

        //获取当天的结束时间
        public static java.util.Date getDayEnd() {
            Calendar cal = new GregorianCalendar();
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            return cal.getTime();
        }

    public static void main(String[] args) {
        Calendar calAnteayerStat = new GregorianCalendar();
        calAnteayerStat.setTime(getDayBegin());
        calAnteayerStat.add(Calendar.DAY_OF_MONTH, -2);
        Calendar calAnteayerEnd = new GregorianCalendar();
        calAnteayerEnd.setTime(getDayEnd());
        calAnteayerEnd.add(Calendar.DAY_OF_MONTH, -2);

        System.out.println(LocalDateTime.ofInstant(calAnteayerStat.getTime().toInstant(), ZoneOffset.systemDefault()));
    }

        @Override
        public PageData<TradeRankingVO.RankingVO> pageData(TradeRankingQTO.RankingQTO qto) {
            TradeRankingVO.RankingVO rankingVO = new TradeRankingVO.RankingVO();
            QueryWrapper<TradeRanking> wrapper = MybatisPlusUtil.query();
            if(ObjectUtils.isNotEmpty(qto.getStartTime()) && ObjectUtils.isNotEmpty(qto.getEndTime())){
                wrapper.ge("gsg.cdate",qto.getStartTime())
                        .le("gsg.cdate",qto.getEndTime());
            }
            if(ObjectUtils.isNotEmpty(qto.getQueryTimes())){
                switch (qto.getQueryTimes()){
                    case 10:
                        Calendar calYesterDayStat = new GregorianCalendar();
                        calYesterDayStat.setTime(getDayBegin());
                        calYesterDayStat.add(Calendar.DAY_OF_MONTH, -1);
                        Calendar calYesterDayEnd = new GregorianCalendar();
                        calYesterDayEnd.setTime(getDayEnd());
                        calYesterDayEnd.add(Calendar.DAY_OF_MONTH, -1);
                        wrapper.ge("gsg.cdate", LocalDateTime.ofInstant(calYesterDayStat.getTime().toInstant(), ZoneOffset.systemDefault()))
                                .le("gsg.cdate",LocalDateTime.ofInstant(calYesterDayEnd.getTime().toInstant(), ZoneOffset.systemDefault()));
                        break;
                    case 20:
                        Calendar calAnteayerStat = new GregorianCalendar();
                        calAnteayerStat.setTime(getDayBegin());
                        calAnteayerStat.add(Calendar.DAY_OF_MONTH, -2);
                        Calendar calAnteayerEnd = new GregorianCalendar();
                        calAnteayerEnd.setTime(getDayEnd());
                        calAnteayerEnd.add(Calendar.DAY_OF_MONTH, -2);
                        wrapper.ge("gsg.cdate",LocalDateTime.ofInstant(calAnteayerStat.getTime().toInstant(), ZoneOffset.systemDefault()))
                                .le("gsg.cdate",LocalDateTime.ofInstant(calAnteayerEnd.getTime().toInstant(), ZoneOffset.systemDefault()));
                        break;
                    case 30:
                        wrapper.ge("gsg.cdate",LocalDateTime.now().minus(Period.ofDays(7)))
                                .le("gsg.cdate",LocalDateTime.now());
                        break;
                    case 40:
                        wrapper.ge("gsg.cdate",LocalDateTime.now().minus(Period.ofDays(30)))
                                .le("gsg.cdate",LocalDateTime.now());
                        break;
                    default:
                        throw new BootstrapMethodError("错误时间查询");

                }
            }
            wrapper.groupBy("goodsName","markeNumSpu","shopName");
            wrapper.orderByDesc("clickRate");
//            wrapper.last("limit 0,10");
            IPage<TradeRanking> page = MybatisPlusUtil.pager(qto);

            IPage<TradeRankingVO.RankingVO> pageVO = tradeRankingMapper.pageVo(page,wrapper);
            if(ObjectUtils.isEmpty(pageVO) || ObjectUtils.isEmpty(pageVO.getRecords())){
                return new PageData<>();
            }
            List<TradeRankingVO.RankingVO> listVOS = ListUtil.listCover(TradeRankingVO.RankingVO.class,pageVO.getRecords());
            if(ObjectUtils.isNotEmpty(listVOS)){
                for (TradeRankingVO.RankingVO rankingVO1:listVOS) {
                    for (TradeRankingVO.RankingVO ranVO :pageVO.getRecords()) {
                        Integer conversionRate= ranVO.getMarkeNumSpu()/ranVO.getClickRate();
                        if(ObjectUtils.isNotEmpty(conversionRate)){
                            rankingVO1.setConversionRate(conversionRate);
                        }
                    }
                }
            }

            return new PageData<>(listVOS,qto.getPageNum(),qto.getPageSize(),pageVO.getTotal());
        }


        @Override
        public TradeSettlementVO.SaleslVO salesList (TradeSettlementQTO.SaleslQTO qto){
            TradeSettlementVO.SaleslVO saleslVO = new TradeSettlementVO.SaleslVO();
            QueryWrapper<Trade> wrapper = MybatisPlusUtil.query();
            QueryWrapper<TradeGoods> wrapperGoods = MybatisPlusUtil.query();

            if (ObjectUtils.isNotEmpty(qto.getQueryTimes())) {
                switch (qto.getQueryTimes()){
                    case 10:
                        Calendar calYesterDayStat = new GregorianCalendar();
                        calYesterDayStat.setTime(getDayBegin());
                        calYesterDayStat.add(Calendar.DAY_OF_MONTH, -1);
                        Calendar calYesterDayEnd = new GregorianCalendar();
                        calYesterDayEnd.setTime(getDayEnd());
                        calYesterDayEnd.add(Calendar.DAY_OF_MONTH, -1);
                        wrapper.ge("cdate", LocalDateTime.ofInstant(calYesterDayStat.getTime().toInstant(), ZoneOffset.systemDefault()))
                                .le("cdate",LocalDateTime.ofInstant(calYesterDayEnd.getTime().toInstant(), ZoneOffset.systemDefault()));

                        wrapperGoods.ge("cdate", LocalDateTime.ofInstant(calYesterDayStat.getTime().toInstant(), ZoneOffset.systemDefault()))
                                .le("cdate",LocalDateTime.ofInstant(calYesterDayEnd.getTime().toInstant(), ZoneOffset.systemDefault()));
                        break;
                    case 20:
                        Calendar calAnteayerStat = new GregorianCalendar();
                        calAnteayerStat.setTime(getDayBegin());
                        calAnteayerStat.add(Calendar.DAY_OF_MONTH, -2);
                        Calendar calAnteayerEnd = new GregorianCalendar();
                        calAnteayerEnd.setTime(getDayEnd());
                        calAnteayerEnd.add(Calendar.DAY_OF_MONTH, -2);
                        wrapper.ge("cdate", LocalDateTime.ofInstant(calAnteayerStat.getTime().toInstant(), ZoneOffset.systemDefault()))
                                .le("cdate",LocalDateTime.ofInstant(calAnteayerEnd.getTime().toInstant(), ZoneOffset.systemDefault()));

                        wrapperGoods.ge("cdate", LocalDateTime.ofInstant(calAnteayerStat.getTime().toInstant(), ZoneOffset.systemDefault()))
                                .le("cdate",LocalDateTime.ofInstant(calAnteayerEnd.getTime().toInstant(), ZoneOffset.systemDefault()));
                        break;
                    case 30:
                        wrapper.ge("cdate", LocalDateTime.now().minus(Period.ofDays(7)))
                                .le("cdate",LocalDateTime.now());

                        wrapperGoods.ge("cdate", LocalDateTime.now().minus(Period.ofDays(7)))
                                .le("cdate",LocalDateTime.now());
                        break;
                    case 40:
                        wrapper.ge("cdate", LocalDateTime.now().minus(Period.ofDays(30)))
                                .le("cdate",LocalDateTime.now());

                        wrapperGoods.ge("cdate", LocalDateTime.now().minus(Period.ofDays(30)))
                                .le("cdate",LocalDateTime.now());
                        break;
                     default:
                         throw new BootstrapMethodError("错误时间查询方式！");
                }
            }

            BigDecimal settlementAmount = tradeSettlementMapper.settlementAmount(wrapper);
            saleslVO.setSettlementAmount(settlementAmount);
            Integer orderQuantity = tradeSettlementMapper.orderQuantity(wrapper);
            saleslVO.setOrderQuantity(orderQuantity);
            Integer shopQuantity =tradeGoodsMapper.shopQuantity(wrapperGoods);
            saleslVO.setShopQuantity(shopQuantity);
            //平均订单价=销售金额/订单数量
            BigDecimal aveGprice = BigDecimal.ZERO;
            if(settlementAmount.compareTo(BigDecimal.ZERO)  == 0){
                saleslVO.setAveGprice(BigDecimal.ZERO);
            }else {
                aveGprice = settlementAmount
                        .divide(new BigDecimal(orderQuantity),2, RoundingMode.HALF_UP);
                if(ObjectUtils.isNotEmpty(aveGprice)){
                    saleslVO.setAveGprice(aveGprice);
                }
            }

            packDateSales(qto,saleslVO,wrapper,wrapperGoods);

            return saleslVO;
        }


        private void packDateSales (TradeSettlementQTO.SaleslQTO qto,TradeSettlementVO.SaleslVO saleslVO,QueryWrapper<Trade> wrapper,QueryWrapper<TradeGoods> wrapperGoods){
            wrapper.groupBy("DATE_FORMAT(cdate,'%Y-%m-%d')");
            wrapper.orderByDesc("DATE_FORMAT(cdate,'%Y-%m-%d')");

            wrapperGoods.groupBy("DATE_FORMAT(cdate,'%Y-%m-%d')");
            wrapperGoods.orderByDesc("DATE_FORMAT(cdate,'%Y-%m-%d')");

            Calendar calYesterDayStat = new GregorianCalendar();
            calYesterDayStat.setTime(getDayBegin());
            calYesterDayStat.add(Calendar.DAY_OF_MONTH, -1);
            Calendar calYesterDayEnd = new GregorianCalendar();
            calYesterDayEnd.setTime(getDayEnd());
            calYesterDayEnd.add(Calendar.DAY_OF_MONTH, -1);
            Calendar calAnteayerStat = new GregorianCalendar();
            calAnteayerStat.setTime(getDayBegin());
            calAnteayerStat.add(Calendar.DAY_OF_MONTH, -2);
            Calendar calAnteayerEnd = new GregorianCalendar();
            calAnteayerEnd.setTime(getDayEnd());
            calAnteayerEnd.add(Calendar.DAY_OF_MONTH, -2);
            if(ObjectUtils.isNotEmpty(qto.getQueryTimes())){
                switch (qto.getQueryTimes()){
                    case 10:
                        if(ObjectUtils.isNotEmpty(qto.getQueryType())){
                            switch (qto.getQueryType()){
                                case 10:
                                    wrapper.ge("cdate", LocalDateTime.ofInstant(calYesterDayStat.getTime().toInstant(), ZoneOffset.systemDefault()))
                                            .le("cdate",LocalDateTime.ofInstant(calYesterDayEnd.getTime().toInstant(), ZoneOffset.systemDefault()));
                                    saleslVO.setPackDateList(tradeSettlementMapper.packDateList(wrapper));
                                    break;
                                case 20:
                                    wrapper.ge("cdate", LocalDateTime.ofInstant(calYesterDayStat.getTime().toInstant(), ZoneOffset.systemDefault()))
                                            .le("cdate",LocalDateTime.ofInstant(calYesterDayEnd.getTime().toInstant(), ZoneOffset.systemDefault()));
                                    saleslVO.setPackDateList(tradeSettlementMapper.packDateList(wrapper));
                                    break;
                                case 30:
                                    wrapperGoods.ge("cdate", LocalDateTime.ofInstant(calYesterDayStat.getTime().toInstant(), ZoneOffset.systemDefault()))
                                            .le("cdate",LocalDateTime.ofInstant(calYesterDayEnd.getTime().toInstant(), ZoneOffset.systemDefault()));
                                    saleslVO.setPackCountList(tradeGoodsMapper.packCountList(wrapperGoods));
                                    break;
                                case 40:
                                    break;
                                default:
                                    throw new BootstrapMethodError("类型查询方式错误！");
                            }
                        }
                        break;
                    case 20:
                        if(ObjectUtils.isNotEmpty(qto.getQueryType())){
                            switch (qto.getQueryType()){
                                case 10:
                                    wrapper.ge("cdate", LocalDateTime.ofInstant(calAnteayerStat.getTime().toInstant(), ZoneOffset.systemDefault()))
                                            .le("cdate",LocalDateTime.ofInstant(calAnteayerEnd.getTime().toInstant(), ZoneOffset.systemDefault()));
                                    saleslVO.setPackDateList(tradeSettlementMapper.packDateList(wrapper));
                                    break;
                                case 20:
                                    wrapper.ge("cdate", LocalDateTime.ofInstant(calAnteayerStat.getTime().toInstant(), ZoneOffset.systemDefault()))
                                            .le("cdate",LocalDateTime.ofInstant(calAnteayerEnd.getTime().toInstant(), ZoneOffset.systemDefault()));
                                    saleslVO.setPackDateList(tradeSettlementMapper.packDateList(wrapper));
                                    break;
                                case 30:
                                    wrapperGoods.ge("cdate", LocalDateTime.ofInstant(calAnteayerStat.getTime().toInstant(), ZoneOffset.systemDefault()))
                                            .le("cdate",LocalDateTime.ofInstant(calAnteayerEnd.getTime().toInstant(), ZoneOffset.systemDefault()));
                                    saleslVO.setPackCountList(tradeGoodsMapper.packCountList(wrapperGoods));
                                    break;
                                case 40:
                                    break;
                                default:
                                    throw new BootstrapMethodError("类型查询方式错误！");
                            }
                        }
                        break;
                    case 30:
                        if(ObjectUtils.isNotEmpty(qto.getQueryType())){
                            switch (qto.getQueryType()){
                                case 10:
                                    wrapper.ge("cdate", LocalDateTime.now().minus(Period.ofDays(7)))
                                            .le("cdate",LocalDateTime.now());
                                    saleslVO.setPackDateList(tradeSettlementMapper.packDateList(wrapper));
                                    break;
                                case 20:
                                    wrapper.ge("cdate", LocalDateTime.now().minus(Period.ofDays(7)))
                                            .le("cdate",LocalDateTime.now());
                                    saleslVO.setPackDateList(tradeSettlementMapper.packDateList(wrapper));
                                    break;
                                case 30:
                                    wrapperGoods.ge("cdate", LocalDateTime.now().minus(Period.ofDays(7)))
                                            .le("cdate",LocalDateTime.now());
                                    saleslVO.setPackCountList(tradeGoodsMapper.packCountList(wrapperGoods));
                                    break;
                                case 40:
                                    break;
                                default:
                                    throw new BootstrapMethodError("类型查询方式错误！");
                            }
                        }
                        break;
                    case 40:
                        if(ObjectUtils.isNotEmpty(qto.getQueryType())){
                            switch (qto.getQueryType()){
                                case 10:
                                    wrapper.ge("cdate", LocalDateTime.now().minus(Period.ofDays(30)))
                                            .le("cdate",LocalDateTime.now());
                                    saleslVO.setPackDateList(tradeSettlementMapper.packDateList(wrapper));
                                    break;
                                case 20:
                                    wrapper.ge("cdate", LocalDateTime.now().minus(Period.ofDays(30)))
                                            .le("cdate",LocalDateTime.now());
                                    saleslVO.setPackDateList(tradeSettlementMapper.packDateList(wrapper));
                                    break;
                                case 30:
                                    wrapperGoods.ge("cdate", LocalDateTime.now().minus(Period.ofDays(30)))
                                            .le("cdate",LocalDateTime.now());
                                    saleslVO.setPackCountList(tradeGoodsMapper.packCountList(wrapperGoods));
                                    break;
                                case 40:
                                    break;
                                default:
                                    throw new BootstrapMethodError("类型查询方式错误！");
                            }
                        }
                        break;
                    }

                }

        }


        private TradeSettlementVO.SaleslVO packSales (Map < String, Object > tabulateMap){
            TradeSettlementVO.SaleslVO saleslVO = new TradeSettlementVO.SaleslVO();
            BigDecimal aveGprice = BigDecimal.ZERO;
            BigDecimal tradeAmount = new BigDecimal(tabulateMap.get("tradeAmount").toString());
            Integer quantity = Integer.parseInt(tabulateMap.get("quantity").toString());
            Integer count = Integer.parseInt(tabulateMap.get("count").toString());
            saleslVO.setSettlementAmount(tradeAmount);
            saleslVO.setOrderQuantity(quantity);
            saleslVO.setShopQuantity(count);
            //平均订单价=销售金额/订单数量
            if (quantity == 0 && count == 0) {
                aveGprice = BigDecimal.ZERO;
            } else {
                aveGprice = tradeAmount
                        .divide(new BigDecimal(count), 2, RoundingMode.HALF_UP);
            }
            saleslVO.setAveGprice(aveGprice);
            return saleslVO;
        }


        private void packOrderQuantityGroup (Map < String, Object > orderQuantityGroup, TradeSettlementVO.SaleslVO saleslVO){
            BigDecimal aveGprice = BigDecimal.ZERO;
            BigDecimal tradeAmount = new BigDecimal(orderQuantityGroup.get("tradeAmount").toString());
            Integer count = Integer.parseInt(orderQuantityGroup.get("count").toString());
            if (tradeAmount == BigDecimal.ZERO && count == 0) {
                orderQuantityGroup.put("aveGprice", BigDecimal.ZERO);
                saleslVO.setOrderQuantityGroup(orderQuantityGroup);
            } else {
                aveGprice = new BigDecimal(orderQuantityGroup.get("tradeAmount").toString()).
                        divide(new BigDecimal(Integer.parseInt(orderQuantityGroup.get("count").toString())), 2, RoundingMode.HALF_UP);
                orderQuantityGroup.put("aveGprice", aveGprice);
                saleslVO.setOrderQuantityGroup(orderQuantityGroup);
            }
        }

}