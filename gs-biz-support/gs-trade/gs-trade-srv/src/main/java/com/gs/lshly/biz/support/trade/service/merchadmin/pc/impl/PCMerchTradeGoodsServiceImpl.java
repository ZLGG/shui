package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.codec.binary.Hex;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.google.common.hash.Hashing;
import com.gs.lshly.biz.support.trade.entity.Trade;
import com.gs.lshly.biz.support.trade.entity.TradeGoods;
import com.gs.lshly.biz.support.trade.mapper.TradeGoodsMapper;
import com.gs.lshly.biz.support.trade.repository.ITradeGoodsRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradeGoodsService;
import com.gs.lshly.common.enums.GoodsSaleEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeGoodsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeGoodsVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.MoziSMSDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeGoodsDTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeGoodsVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

import lombok.extern.slf4j.Slf4j;

/**
* <p>
*  服务实现类
* </p>
* @author oy
* @since 2020-11-16
*/
@Component
@Slf4j
public class PCMerchTradeGoodsServiceImpl implements IPCMerchTradeGoodsService {

        @Autowired
        private ITradeGoodsRepository repository;
        @Autowired
        private TradeGoodsMapper tradeGoodsMapper;

        @Override
        public PageData<PCMerchTradeGoodsVO.ListVO> pageData(PCMerchTradeGoodsQTO.QTO qto) {
            QueryWrapper<TradeGoods> wrapper = new QueryWrapper<>();
            wrapper.orderByDesc("cdate");
            IPage<TradeGoods> page = MybatisPlusUtil.pager(qto);
            repository.page(page, wrapper);
            return MybatisPlusUtil.toPageData(qto, PCMerchTradeGoodsVO.ListVO.class, page);
        }

        @Override
        public void addTradeGoods(PCMerchTradeGoodsDTO.ETO eto) {
            TradeGoods tradeGoods = new TradeGoods();
            BeanUtils.copyProperties(eto, tradeGoods);
            repository.save(tradeGoods);
        }


        @Override
        public void deleteTradeGoods(PCMerchTradeGoodsDTO.IdDTO dto) {
            repository.removeById(dto.getId());
        }
        @Override
        public void editTradeGoods(PCMerchTradeGoodsDTO.ETO eto) {
            TradeGoods tradeGoods = new TradeGoods();
            BeanUtils.copyProperties(eto, tradeGoods);
            repository.updateById(tradeGoods);
        }

        @Override
        public PCMerchTradeGoodsVO.DetailVO detailTradeGoods(PCMerchTradeGoodsDTO.IdDTO dto) {
            TradeGoods tradeGoods = repository.getById(dto.getId());
            PCMerchTradeGoodsVO.DetailVO detailVo = new PCMerchTradeGoodsVO.DetailVO();
            if(ObjectUtils.isEmpty(tradeGoods)){
                throw new BusinessException("没有数据");
            }
            BeanUtils.copyProperties(tradeGoods, detailVo);
            return detailVo;
        }
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


        public static Date getYearFirst(){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar c = Calendar.getInstance();
            c.clear(Calendar.MONTH);
            c.add(Calendar.YEAR, 0);
            /*c.add(Calendar.MONTH, 0);*/
            c.set(Calendar.DAY_OF_MONTH, 1);
            //设置为1号,当前日期既为本月第一天
            //c.set(Calendar.DAY_OF_MONTH, 1);
            //将小时至0
            c.set(Calendar.HOUR_OF_DAY, 0);
            //将分钟至0
            c.set(Calendar.MINUTE, 0);
            //将秒至0
            c.set(Calendar.SECOND, 0);
            //将毫秒至0
            c.set(Calendar.MILLISECOND, 0);
            String first = format.format(c.getTime());
            return 	DateUtils.parseDate(first);
        }

        public static Date getYearLast(){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar ca = Calendar.getInstance();
            ca.clear(Calendar.MONTH);
            ca.set(Calendar.MONTH, 11);
            ca.set(Calendar.DAY_OF_MONTH,
                    ca.getActualMaximum(Calendar.DAY_OF_MONTH));
            //将小时至23
            ca.set(Calendar.HOUR_OF_DAY, 23);
            //将分钟至59
            ca.set(Calendar.MINUTE, 59);
            //将秒至59
            ca.set(Calendar.SECOND, 59);
            //将毫秒至999
            ca.set(Calendar.MILLISECOND, 999);
            String last = format.format(ca.getTime());
            return 	DateUtils.parseDate(last);
        }


        @Override
        public List<TradeGoodsVO.GoodsSaleVO> exportGoodsSaleList(TradeDTO.PayDateList dto) {

            QueryWrapper<TradeGoods> wrapper = MybatisPlusUtil.query();
            if(ObjectUtils.isNotEmpty(dto.getStartTime()) && ObjectUtils.isNotEmpty(dto.getEndTime())){
                wrapper.ge("cdate",dto.getStartTime())
                        .le("cdate",dto.getEndTime());
            }
            if(ObjectUtils.isNotEmpty(dto.getQueryTimes())){
                switch (dto.getQueryTimes()){
                    case 10:
                        Calendar calYesterDayStat = new GregorianCalendar();
                        calYesterDayStat.setTime(getDayBegin());
                        calYesterDayStat.add(Calendar.DAY_OF_MONTH, -1);
                        Calendar calYesterDayEnd = new GregorianCalendar();
                        calYesterDayEnd.setTime(getDayEnd());
                        calYesterDayEnd.add(Calendar.DAY_OF_MONTH, -1);
                        wrapper.ge("cdate", LocalDateTime.ofInstant(calYesterDayStat.getTime().toInstant(), ZoneOffset.systemDefault()))
                                .le("cdate",LocalDateTime.ofInstant(calYesterDayEnd.getTime().toInstant(), ZoneOffset.systemDefault()));
                        break;
                    case 20:
                        Calendar calAnteayerStat = new GregorianCalendar();
                        calAnteayerStat.setTime(getDayBegin());
                        calAnteayerStat.add(Calendar.DAY_OF_MONTH, -2);
                        Calendar calAnteayerEnd = new GregorianCalendar();
                        calAnteayerEnd.setTime(getDayEnd());
                        calAnteayerEnd.add(Calendar.DAY_OF_MONTH, -2);
                        wrapper.ge("cdate",LocalDateTime.ofInstant(calAnteayerStat.getTime().toInstant(), ZoneOffset.systemDefault()))
                                .le("cdate",LocalDateTime.ofInstant(calAnteayerEnd.getTime().toInstant(), ZoneOffset.systemDefault()));
                        break;
                    case 30:
                        wrapper.ge("cdate",LocalDateTime.now().minus(Period.ofDays(7)))
                                .le("cdate",LocalDateTime.now());
                        break;
                    case 40:
                        wrapper.ge("cdate",LocalDateTime.now().minus(Period.ofDays(30)))
                                .le("cdate",LocalDateTime.now());
                        break;
                    default:
                        throw new BootstrapMethodError("时间选择错误");
                }
            }
            wrapper.eq("shop_id",dto.getJwtShopId());
            wrapper.groupBy("goods_id","goods_name");
            if(ObjectUtils.isNotEmpty(dto.getQueryStates())){
                if(dto.getQueryStates().equals(GoodsSaleEnum.销售数量.getCode())){
                    wrapper.orderByDesc("quantity");
                }else if(dto.getQueryStates().equals(GoodsSaleEnum.销售金额.getCode())){
                    wrapper.orderByDesc("salePrice");
                }
            }
            wrapper.last("limit 0,10");

            List<TradeGoodsVO.GoodsSaleVO> goodsSale= tradeGoodsMapper.goodsSale(wrapper);
            if (ObjectUtils.isEmpty(goodsSale)){
                throw new BusinessException("没有可以导出的数据");
            }
            return goodsSale;
        }

        @Override
        public List<TradeGoodsVO.GoodsSaleVO> goodsSaleList(TradeGoodsDTO.GoodsSale dto) {

            QueryWrapper<TradeGoods> wrapper = MybatisPlusUtil.query();
            if(ObjectUtils.isNotEmpty(dto.getStartTime()) && ObjectUtils.isNotEmpty(dto.getEndTime())){
                wrapper.ge("cdate",dto.getStartTime())
                        .le("cdate",dto.getEndTime());
            }
            if(ObjectUtils.isNotEmpty(dto.getQueryTimes())){
                switch (dto.getQueryTimes()){
                    case 10:
                        Calendar calYesterDayStat = new GregorianCalendar();
                        calYesterDayStat.setTime(getDayBegin());
                        calYesterDayStat.add(Calendar.DAY_OF_MONTH, -1);
                        Calendar calYesterDayEnd = new GregorianCalendar();
                        calYesterDayEnd.setTime(getDayEnd());
                        calYesterDayEnd.add(Calendar.DAY_OF_MONTH, -1);
                        wrapper.ge("cdate", LocalDateTime.ofInstant(calYesterDayStat.getTime().toInstant(), ZoneOffset.systemDefault()))
                                .le("cdate",LocalDateTime.ofInstant(calYesterDayEnd.getTime().toInstant(), ZoneOffset.systemDefault()));
                        break;
                    case 20:
                        Calendar calAnteayerStat = new GregorianCalendar();
                        calAnteayerStat.setTime(getDayBegin());
                        calAnteayerStat.add(Calendar.DAY_OF_MONTH, -2);
                        Calendar calAnteayerEnd = new GregorianCalendar();
                        calAnteayerEnd.setTime(getDayEnd());
                        calAnteayerEnd.add(Calendar.DAY_OF_MONTH, -2);
                        wrapper.ge("cdate",LocalDateTime.ofInstant(calAnteayerStat.getTime().toInstant(), ZoneOffset.systemDefault()))
                                .le("cdate",LocalDateTime.ofInstant(calAnteayerEnd.getTime().toInstant(), ZoneOffset.systemDefault()));
                        break;
                    case 30:
                        wrapper.ge("cdate",LocalDateTime.now().minus(Period.ofDays(7)))
                                .le("cdate",LocalDateTime.now());
                        break;
                    case 40:
                        wrapper.ge("cdate",LocalDateTime.now().minus(Period.ofDays(30)))
                                .le("cdate",LocalDateTime.now());
                        break;
                    default:
                        throw new BootstrapMethodError("时间选择错误");
                }
            }
            wrapper.eq("shop_id",dto.getJwtShopId());
            wrapper.groupBy("goods_id","goods_name");
            if(ObjectUtils.isNotEmpty(dto.getQueryStates())){
                if(dto.getQueryStates().equals(GoodsSaleEnum.销售数量.getCode())){
                    wrapper.orderByDesc("quantity");
                }else if(dto.getQueryStates().equals(GoodsSaleEnum.销售金额.getCode())){
                    wrapper.orderByDesc("salePrice");
                }
            }
            wrapper.last("limit 0,10");

            List<TradeGoodsVO.GoodsSaleVO> goodsSale= tradeGoodsMapper.goodsSale(wrapper);
            if (ObjectUtils.isEmpty(goodsSale)){
                return new ArrayList<>();
            }
            return goodsSale;
        }


        @Override
        public List<TradeGoodsVO.GoodsSaleVO> exportGoodsSaleListDetail(TradeDTO.PayDateList dto) {

            QueryWrapper<TradeGoods> wrapper = MybatisPlusUtil.query();
            if(ObjectUtils.isNotEmpty(dto.getStartTime()) && ObjectUtils.isNotEmpty(dto.getEndTime())){
                wrapper.ge("cdate",dto.getStartTime())
                        .le("cdate",dto.getEndTime());
            }
            if(ObjectUtils.isNotEmpty(dto.getQueryTimes())){
                switch (dto.getQueryTimes()){
                    case 10:
                        Calendar calYesterDayStat = new GregorianCalendar();
                        calYesterDayStat.setTime(getDayBegin());
                        calYesterDayStat.add(Calendar.DAY_OF_MONTH, -1);
                        Calendar calYesterDayEnd = new GregorianCalendar();
                        calYesterDayEnd.setTime(getDayEnd());
                        calYesterDayEnd.add(Calendar.DAY_OF_MONTH, -1);
                        wrapper.ge("cdate", LocalDateTime.ofInstant(calYesterDayStat.getTime().toInstant(), ZoneOffset.systemDefault()))
                                .le("cdate",LocalDateTime.ofInstant(calYesterDayEnd.getTime().toInstant(), ZoneOffset.systemDefault()));
                        break;
                    case 20:
                        Calendar calAnteayerStat = new GregorianCalendar();
                        calAnteayerStat.setTime(getDayBegin());
                        calAnteayerStat.add(Calendar.DAY_OF_MONTH, -2);
                        Calendar calAnteayerEnd = new GregorianCalendar();
                        calAnteayerEnd.setTime(getDayEnd());
                        calAnteayerEnd.add(Calendar.DAY_OF_MONTH, -2);
                        wrapper.ge("cdate",LocalDateTime.ofInstant(calAnteayerStat.getTime().toInstant(), ZoneOffset.systemDefault()))
                                .le("cdate",LocalDateTime.ofInstant(calAnteayerEnd.getTime().toInstant(), ZoneOffset.systemDefault()));
                        break;
                    case 30:
                        wrapper.ge("cdate",LocalDateTime.now().minus(Period.ofDays(7)))
                                .le("cdate",LocalDateTime.now());
                        break;
                    case 40:
                        wrapper.ge("cdate",LocalDateTime.now().minus(Period.ofDays(30)))
                                .le("cdate",LocalDateTime.now());
                        break;
                    default:
                        throw new BootstrapMethodError("时间选择错误");
                }
            }
            wrapper.eq("shop_id",dto.getJwtShopId());
            wrapper.groupBy("MONTH(cdate)","goods_id","goods_name");
            if(ObjectUtils.isNotEmpty(dto.getQueryStates())){
                if(dto.getQueryStates().equals(GoodsSaleEnum.销售数量.getCode())){
                    wrapper.orderByDesc("quantity");
                }else if(dto.getQueryStates().equals(GoodsSaleEnum.销售金额.getCode())){
                    wrapper.orderByDesc("payAmount");
                }
            }
            wrapper.last("limit 0,10");

            List<TradeGoodsVO.GoodsSaleVO> goodsSale= tradeGoodsMapper.goodsSaleDetail(wrapper);
            if (ObjectUtils.isEmpty(goodsSale)){
                throw new BusinessException("没有可以导出的数据");
            }
            return goodsSale;
        }

        @Override
        public List<TradeGoodsVO.GoodsSaleVO> goodsSaleListDetail(TradeGoodsDTO.GoodsSale dto) {

            QueryWrapper<TradeGoods> wrapper = MybatisPlusUtil.query();
            if(ObjectUtils.isNotEmpty(dto.getStartTime()) && ObjectUtils.isNotEmpty(dto.getEndTime())){
                wrapper.ge("cdate",dto.getStartTime())
                        .le("cdate",dto.getEndTime());
            }
            if(ObjectUtils.isNotEmpty(dto.getQueryTimes())){
                switch (dto.getQueryTimes()){
                    case 10:
                        Calendar calYesterDayStat = new GregorianCalendar();
                        calYesterDayStat.setTime(getDayBegin());
                        calYesterDayStat.add(Calendar.DAY_OF_MONTH, -1);
                        Calendar calYesterDayEnd = new GregorianCalendar();
                        calYesterDayEnd.setTime(getDayEnd());
                        calYesterDayEnd.add(Calendar.DAY_OF_MONTH, -1);
                        wrapper.ge("cdate", LocalDateTime.ofInstant(calYesterDayStat.getTime().toInstant(), ZoneOffset.systemDefault()))
                                .le("cdate",LocalDateTime.ofInstant(calYesterDayEnd.getTime().toInstant(), ZoneOffset.systemDefault()));
                        break;
                    case 20:
                        Calendar calAnteayerStat = new GregorianCalendar();
                        calAnteayerStat.setTime(getDayBegin());
                        calAnteayerStat.add(Calendar.DAY_OF_MONTH, -2);
                        Calendar calAnteayerEnd = new GregorianCalendar();
                        calAnteayerEnd.setTime(getDayEnd());
                        calAnteayerEnd.add(Calendar.DAY_OF_MONTH, -2);
                        wrapper.ge("cdate",LocalDateTime.ofInstant(calAnteayerStat.getTime().toInstant(), ZoneOffset.systemDefault()))
                                .le("cdate",LocalDateTime.ofInstant(calAnteayerEnd.getTime().toInstant(), ZoneOffset.systemDefault()));
                        break;
                    case 30:
                        wrapper.ge("cdate",LocalDateTime.now().minus(Period.ofDays(7)))
                                .le("cdate",LocalDateTime.now());
                        break;
                    case 40:
                        wrapper.ge("cdate",LocalDateTime.now().minus(Period.ofDays(30)))
                                .le("cdate",LocalDateTime.now());
                        break;
                    default:
                        throw new BootstrapMethodError("时间选择错误");
                }
            }
            wrapper.eq("shop_id",dto.getJwtShopId());
            wrapper.groupBy("MONTH(cdate)","goods_id","goods_name");
            if(ObjectUtils.isNotEmpty(dto.getQueryStates())){
                if(dto.getQueryStates().equals(GoodsSaleEnum.销售数量.getCode())){
                    wrapper.orderByDesc("quantity");
                }else if(dto.getQueryStates().equals(GoodsSaleEnum.销售金额.getCode())){
                    wrapper.orderByDesc("payAmount");
                }
            }
            wrapper.last("limit 0,10");

            List<TradeGoodsVO.GoodsSaleVO> goodsSale= tradeGoodsMapper.goodsSaleDetail(wrapper);
            if (ObjectUtils.isEmpty(goodsSale)){
                return null;
            }
            return goodsSale;
        }


        @Override
        public TradeVO.SalesSummaryVO salesSummaryList(BaseDTO dto) {
            TradeVO.SalesSummaryVO detailVo = new TradeVO.SalesSummaryVO();
            if(ObjectUtils.isEmpty(dto.getJwtShopId())){
                throw new BootstrapMethodError("暂无此店铺数据");
            }
            QueryWrapper<Trade> wrapper = new QueryWrapper<>();
            wrapper.eq("shop_id",dto.getJwtShopId());
//            wrapper.and(i -> i.eq("user_id",dto.getJwtUserId()));
            detailVo.setWeekDate(tradeGoodsMapper.getWeekDate(wrapper)==null?0:tradeGoodsMapper.getWeekDate(wrapper));
            detailVo.setMonthDate(tradeGoodsMapper.getMonthDate(wrapper)==null?0:tradeGoodsMapper.getMonthDate(wrapper));
            detailVo.setNinetyDate(tradeGoodsMapper.getNinetyDate(wrapper)==null?0:tradeGoodsMapper.getNinetyDate(wrapper));
            detailVo.setUserCount(tradeGoodsMapper.getUserCountDate(wrapper)==null?0:tradeGoodsMapper.getUserCountDate(wrapper));
            QueryWrapper<Trade> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id",dto.getJwtUserId());
            detailVo.setPackTradeStatesDate(tradeGoodsMapper.packTradeStatesDate(queryWrapper));

            return detailVo;
        }

        @Override
        public TradeVO.PerformanceVO performanceList(TradeDTO.PerformanceListDTO dto) {
            TradeVO.PerformanceVO detailVo = new TradeVO.PerformanceVO();
            if(ObjectUtils.isEmpty(dto.getJwtShopId())){
                throw new BootstrapMethodError("店铺数据错误");
            }
            QueryWrapper<Trade> wrapper = MybatisPlusUtil.query();
            wrapper.ge("cdate",dto.getYear()+"-1-1 00:00:00")
                    .le("cdate",dto.getYear()+"-12-31 59:59:59");
            wrapper.groupBy("MONTH(cdate)");
            wrapper.orderByDesc("MONTH(cdate) ");
            if(ObjectUtils.isEmpty(dto.getShopId())){
                wrapper.eq("shop_id",dto.getJwtShopId());
            }else {
                wrapper.eq("shop_id",dto.getShopId());
            }
            detailVo.setPackPerformanceVO(tradeGoodsMapper.performanceList(wrapper));

            QueryWrapper<Trade> wrapper2 = MybatisPlusUtil.query();
            wrapper2.ge("cdate",dto.getYear()+"-1-1 00:00:00")
                    .le("cdate",dto.getYear()+"-12-31 59:59:59");
            if(ObjectUtils.isEmpty(dto.getShopId())){
                wrapper2.eq("shop_id",dto.getJwtShopId());
            }else {
                wrapper2.eq("shop_id",dto.getShopId());
            }
            detailVo.setCount(tradeGoodsMapper.count(wrapper2));
            return detailVo;
        }

        @Override
        public TradeVO.ClientListVO clientList(TradeDTO.ClientListDTO dto) {
            TradeVO.ClientListVO detailVo = new TradeVO.ClientListVO();
            if(ObjectUtils.isEmpty(dto.getJwtShopId())){
                throw new BootstrapMethodError("店铺数据错误");
            }
            QueryWrapper<Trade> wrapper = MybatisPlusUtil.query();
            if(ObjectUtils.isNotEmpty(dto.getUserName())){
                wrapper.like("gu.user_name",dto.getUserName());
            }
            wrapper.ge("gs.cdate",dto.getYear()+"-1-1 00:00:00")
                    .le("gs.cdate",dto.getYear()+"-12-31 59:59:59");
            wrapper.groupBy("MONTH(gs.cdate)","userName");
            wrapper.orderByDesc("MONTH(gs.cdate)","tradeAmount");

            if(ObjectUtils.isEmpty(dto.getShopId())){
                wrapper.eq("gs.shop_id",dto.getJwtShopId());
            }else {
                wrapper.eq("gs.shop_id",dto.getShopId());
            }

            detailVo.setPackClientListVOS(tradeGoodsMapper.clientList(wrapper));

            QueryWrapper<Trade> wrapper2 = MybatisPlusUtil.query();
            if(ObjectUtils.isNotEmpty(dto.getUserName())){
                wrapper2.like("gu.user_name",dto.getUserName());
            }
            wrapper2.ge("gs.cdate",dto.getYear()+"-1-1 00:00:00")
                    .le("gs.cdate",dto.getYear()+"-12-31 59:59:59");
            wrapper2.groupBy("userName");
            wrapper2.orderByDesc("tradeAmount");
            wrapper2.last("limit 0,20");
            if(ObjectUtils.isEmpty(dto.getShopId())){
                wrapper2.eq("gs.shop_id",dto.getJwtShopId());
            }else {
                wrapper2.eq("gs.shop_id",dto.getShopId());
            }

            detailVo.setPackClientVOS(tradeGoodsMapper.clientDate(wrapper2));
            return detailVo;
        }

        @Override
        public TradeVO.GoodsDateVO goodsDateList(TradeDTO.PerformanceListDTO dto) {
            TradeVO.GoodsDateVO detailVo = new TradeVO.GoodsDateVO();
            if(ObjectUtils.isEmpty(dto.getJwtShopId())){
                throw new BootstrapMethodError("店铺数据错误");
            }
            QueryWrapper<Trade> wrapper = MybatisPlusUtil.query();
            wrapper.ge("cdate",dto.getYear()+"-1-1 00:00:00")
                    .le("cdate",dto.getYear()+"-12-31 59:59:59");
            if(ObjectUtils.isEmpty(dto.getShopId())){
                wrapper.eq("shop_id",dto.getJwtShopId());
            }else {
                wrapper.eq("shop_id",dto.getShopId());
            }
            wrapper.groupBy("MONTH(cdate)","goods_name","sku_img");
            wrapper.orderByDesc("MONTH(cdate) ","tradeAmount");

            detailVo.setPackGoodsDateVO(tradeGoodsMapper.packGoodsDate(wrapper));

            QueryWrapper<Trade> wrapper2 = MybatisPlusUtil.query();
            wrapper2.ge("cdate",dto.getYear()+"-1-1 00:00:00")
                    .le("cdate",dto.getYear()+"-12-31 59:59:59");
            if(ObjectUtils.isEmpty(dto.getShopId())){
                wrapper2.eq("shop_id",dto.getJwtShopId());
            }else {
                wrapper2.eq("shop_id",dto.getShopId());
            }
            wrapper2.groupBy("goodsId","goodsName");
            wrapper2.orderByDesc("tradeAmount");
            wrapper2.last("limit 0,10");
            detailVo.setPackGoodsDateListVO(tradeGoodsMapper.packGoodsDateList(wrapper2));
            return detailVo;
        }



//        @Autowired
//        private IMoZiSMSService smsService;


        //测试接口
        @Override
        public String testDete(String mobiles, String sign, String parameter, String merchantOrderId) {
            return "";//smsService.sendTemplate(mobiles,sign, parameter, merchantOrderId);
        }

        //内容发送接口
        @Override
        public String sendContentDate(MoziSMSDTO.SendContentDTO dto) {
            return "";//smsService.sendTemplate(dto.getMobiles(),dto.getSign(),dto.getContent(),dto.getMerchantOrderId());
        }

        //模板发送接口
        @Override
        public String TemplateDate(MoziSMSDTO.TemplateDTO dto) {
            return "";//smsService.TemplateDate(dto.getMobiles(),dto.getTplId(),dto.getParameter(),dto.getMerchantOrderId());
        }

        //余额查询接口
        @Override
        public String balanceQueryDate(MoziSMSDTO.BalanceQueryDTO dto) {
            return "";//smsService.balanceQueryDate(dto.getMerchantId());
        }


        /**
         * 生成请求签名
         *
         * @param data body 数据
         * @param apikey apikey
         * @return
         */
        private static String buildSign(String data, String apikey) throws
                UnsupportedEncodingException {
            return Hex.encodeHexString(Hashing.sha1().hashBytes((data +
                    apikey).getBytes("UTF-8")).asBytes());
        }

        /**
         * MD5加密
         * @param str 内容
         * @param charset 编码方式
         * @throws Exception
         */
        @SuppressWarnings("unused")
        private static String MD5(String str, String charset) throws Exception {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes(charset));
            byte[] result = md.digest();
            StringBuffer sb = new StringBuffer(32);
            for (int i = 0; i < result.length; i++) {
                int val = result[i] & 0xff;
                if (val <= 0xf) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(val));
            }
            return sb.toString().toLowerCase();
        }



}
