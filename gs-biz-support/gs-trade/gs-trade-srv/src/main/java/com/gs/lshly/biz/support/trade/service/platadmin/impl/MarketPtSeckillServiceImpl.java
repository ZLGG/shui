package com.gs.lshly.biz.support.trade.service.platadmin.impl;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.MarketPtSeckill;
import com.gs.lshly.biz.support.trade.entity.MarketPtSeckillGoodsSku;
import com.gs.lshly.biz.support.trade.entity.MarketPtSeckillGoodsSpu;
import com.gs.lshly.biz.support.trade.entity.MarketPtSeckillTimeQuantum;
import com.gs.lshly.biz.support.trade.mapper.MarketPtSeckillMapper;
import com.gs.lshly.biz.support.trade.repository.IMarketPtSeckillGoodsSkuRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketPtSeckillGoodsSpuRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketPtSeckillRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketPtSeckillTimeQuantumRepository;
import com.gs.lshly.biz.support.trade.service.platadmin.IMarketPtSeckillService;
import com.gs.lshly.common.enums.MarketPtSeckillActivityEnum;
import com.gs.lshly.common.enums.MarketPtSeckillSkuStateEnum;
import com.gs.lshly.common.enums.MarketPtSeckillSpuChooseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.MarketPtSeckillDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtSeckillQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtSeckillVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsCategoryRpc;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsInfoRpc;
import com.gs.lshly.rpc.api.platadmin.commodity.ISkuGoodsInfoRpc;
import com.lakala.boss.api.utils.DateUtil;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zdf
 * @since 2020-11-28
 */
@Component
@SuppressWarnings("unchecked")
public class MarketPtSeckillServiceImpl implements IMarketPtSeckillService {

    @Autowired
    private IMarketPtSeckillRepository repository;

    @Autowired
    private IMarketPtSeckillTimeQuantumRepository marketPtSeckillTimeQuantumRepository;

//    @Autowired
//    private IMarketPtSeckillGoodsCategoryRepository categoryRepository;

    @Autowired
    private IMarketPtSeckillGoodsSpuRepository iMarketPtSeckillGoodsSpuRepository;

    @Autowired
    private IMarketPtSeckillGoodsSkuRepository iMarketPtSeckillGoodsSkuRepository;

    @Autowired
    private MarketPtSeckillMapper marketPtSeckillMapper;

    @DubboReference
    private IGoodsCategoryRpc iGoodsCategoryRpc;

    @DubboReference
    private IGoodsInfoRpc iGoodsInfoRpc;

    @DubboReference
    private ISkuGoodsInfoRpc iSkuGoodsInfoRpc;


    /**
     * 秒杀活动列表
     *
     * @param qto
     * @return
     */
    @Override
    public PageData<MarketPtSeckillVO.ActivityListVO> pageData(MarketPtSeckillQTO.QTO qto) {
/*        QueryWrapper<MarketPtSeckill> wrapper = MybatisPlusUtil.query();
        wrapper.orderByDesc("cdate");
        if (ObjectUtil.isNotEmpty(qto) && StrUtil.isNotEmpty(qto.getAcName())) {
            wrapper.and(i -> i.like("name", qto.getAcName()));
        }
        IPage<MarketPtSeckill> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        List<MarketPtSeckill> pageRecords = page.getRecords();
        List<MarketPtSeckillVO.ActivityListVO> listVOS = new ArrayList<>();
        for (MarketPtSeckill pageRecord : pageRecords) {
            MarketPtSeckillVO.ActivityListVO activityListVO = getActivityListVO(pageRecord);
            listVOS.add(activityListVO);
        }
        return MybatisPlusUtil.toPageData(listVOS, qto.getPageNum(), qto.getPageSize(), page.getTotal());*/
        QueryWrapper<MarketPtSeckill> query = MybatisPlusUtil.query();
        if (StrUtil.isNotEmpty(qto.getAcName())) {
            query.like("name", qto.getAcName());
        }
        if (ObjectUtil.isNotEmpty(qto.getState())) {
            query.eq("state", qto.getState());
        }
        if (ObjectUtil.isNotEmpty(qto.getStartTime())) {
            query.and(i -> i.ge("seckill_start_time", qto.getStartTime()).or(s -> s.ge("seckill_start_time", qto.getStartTime())))/*.or(i -> i.ge("seckill_start_time", qto.getStartTime()))*/;
        }
        if (ObjectUtil.isNotEmpty(qto.getEndTime())) {
            query.and(i -> i.le("seckill_end_time", qto.getEndTime()).or(s -> s.le("seckill_end_time", qto.getStartTime())))/*.or(i -> i.le("seckill_end_time", qto.getStartTime()))*/;
        }
        query.orderByDesc("cdate");
        IPage<MarketPtSeckill> page = MybatisPlusUtil.pager(qto);
        repository.page(page, query);
        List<MarketPtSeckill> pageRecords = page.getRecords();
        List<MarketPtSeckillVO.ActivityListVO> listVOS = new ArrayList<>();
        if (CollUtil.isNotEmpty(pageRecords)) {
            for (MarketPtSeckill pageRecord : pageRecords) {
                MarketPtSeckillVO.ActivityListVO activityListVO = getActivityListVO(pageRecord);
                repository.updateById(pageRecord);
                listVOS.add(activityListVO);
            }
        }
        return MybatisPlusUtil.toPageData(listVOS, qto.getPageNum(), qto.getPageSize(), page.getTotal());
    }

    /**
     * 添加秒杀活动
     *
     * @param eto
     */
    @Override
    @Transactional
    public void addMarketPtSeckill(MarketPtSeckillDTO.ETO eto) {
        if (!ObjectUtil.isAllNotEmpty(eto, eto.getSignStartTime(), eto.getSignEndTime(), eto.getSeckillStartTime(), eto.getSeckillEndTime(), eto.getName())) {
            throw new BusinessException("参数不能未空!");
        }

        boolean b1 = DateUtil.compareDate(eto.getSignEndTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),eto.getSignEndTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        boolean b2 = DateUtil.compareDate(eto.getSeckillEndTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), eto.getSeckillStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        if (!b1) {
            throw new BusinessException("报名的结束时间必须大于开始时间");
        }
        if (!b2) {
            throw new BusinessException("开售的结束时间必须大于开始时间");
        }

        //判断场次时间是否重叠
        for (int i = 0; i < eto.getSessionTime().size() - 1; i++) {
            String seEndTime = eto.getSessionTime().get(i).getEndTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String seStartTime = eto.getSessionTime().get(i).getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            if (!DateUtil.compareDate(seEndTime, seStartTime)) {
                throw new BusinessException("每个场次的结束时间必须大于开始时间");
            }
            for (int j = i + 1; j < eto.getSessionTime().size(); j++) {
                String startTime = eto.getSessionTime().get(j).getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                String endTime = eto.getSessionTime().get(j).getEndTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                if (!DateUtil.compareDate(seStartTime, endTime)) {
                    if (DateUtil.compareDate(seEndTime, startTime)) {
                        throw new BusinessException("单个活动起止时间内的场次时间不能重叠!");
                    }
                }
            }
        }
        QueryWrapper<MarketPtSeckillTimeQuantum> wrapper = MybatisPlusUtil.query();
        wrapper.ne(StrUtil.isNotEmpty(eto.getId()), "id", eto.getId());
        wrapper.eq("flag", 0);
        List<MarketPtSeckillTimeQuantum> list = marketPtSeckillTimeQuantumRepository.list(wrapper);
        //判断不同活动场次时间是否重叠
        for (int i = 0; i < eto.getSessionTime().size(); i++) {
            String seEndTime = eto.getSessionTime().get(i).getEndTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String seStartTime = eto.getSessionTime().get(i).getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            for (int j = i + 1; j < list.size(); j++) {
                String startTime = list.get(j).getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                String endTime = list.get(j).getEndTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                if (!DateUtil.compareDate(seStartTime, endTime)) {
                    if (DateUtil.compareDate(seEndTime, startTime)) {
                        throw new BusinessException("不同活动之间的活动时间不能重叠!");
                    }
                }
            }
        }

        if (StrUtil.isEmpty(eto.getId())) {
            MarketPtSeckill marketPtSeckill = new MarketPtSeckill();
            BeanUtils.copyProperties(eto, marketPtSeckill);
            marketPtSeckill.setState(MarketPtSeckillActivityEnum.未开始.getCode());
            repository.save(marketPtSeckill);
            for (MarketPtSeckillDTO.SessionDTO sessionDTO : eto.getSessionTime()) {
                MarketPtSeckillTimeQuantum marketPtSeckillTimeQuantum = new MarketPtSeckillTimeQuantum();
                marketPtSeckillTimeQuantum.setSeckillId(marketPtSeckill.getId());
                marketPtSeckillTimeQuantum.setStartTime(sessionDTO.getStartTime());
                marketPtSeckillTimeQuantum.setEndTime(sessionDTO.getEndTime());
                marketPtSeckillTimeQuantumRepository.save(marketPtSeckillTimeQuantum);
            }
        } else {
            MarketPtSeckill marketPtSeckill = repository.getById(eto.getId());
            if (ObjectUtil.isEmpty(marketPtSeckill)) {
                throw new BusinessException("未查询到活动信息。");
            }
            BeanUtils.copyProperties(eto, marketPtSeckill);
            repository.updateById(marketPtSeckill);
            QueryWrapper<MarketPtSeckillTimeQuantum> query = MybatisPlusUtil.query();
            query.eq("seckill_id", eto.getId());
            marketPtSeckillTimeQuantumRepository.remove(query);
            for (MarketPtSeckillDTO.SessionDTO sessionDTO : eto.getSessionTime()) {
                MarketPtSeckillTimeQuantum marketPtSeckillTimeQuantum = new MarketPtSeckillTimeQuantum();
                marketPtSeckillTimeQuantum.setSeckillId(marketPtSeckill.getId());
                marketPtSeckillTimeQuantum.setStartTime(sessionDTO.getStartTime());
                marketPtSeckillTimeQuantum.setEndTime(sessionDTO.getEndTime());
                marketPtSeckillTimeQuantumRepository.save(marketPtSeckillTimeQuantum);
            }
        }
    }

    /**
     * 秒杀活动详情
     *
     * @param qto
     * @return
     */
    @Override
    public MarketPtSeckillVO.ActivityVO detailMarketPtSeckill(MarketPtSeckillQTO.IdQTO qto) {
        if (StrUtil.isEmpty(qto.getId())) {
            throw new BusinessException("参数不能为空");
        }
        MarketPtSeckill marketPtSeckill = repository.getById(qto.getId());
        if (ObjectUtil.isEmpty(marketPtSeckill)) {
            throw new BusinessException("未查询到活动信息!");
        }
        MarketPtSeckillVO.ActivityVO activityVO = new MarketPtSeckillVO.ActivityVO();
        BeanUtils.copyProperties(marketPtSeckill, activityVO);
        QueryWrapper<MarketPtSeckillTimeQuantum> query = MybatisPlusUtil.query();
        query.eq("seckill_id", marketPtSeckill.getId());
        query.orderByAsc("start_time");
        List<MarketPtSeckillTimeQuantum> marketPtSeckillTimeQuantumList = marketPtSeckillTimeQuantumRepository.list(query);
        List<MarketPtSeckillVO.SessionVO> sessionVOList = new ArrayList<>();
        if (CollUtil.isNotEmpty(marketPtSeckillTimeQuantumList)) {
            for (MarketPtSeckillTimeQuantum marketPtSeckillTimeQuantum : marketPtSeckillTimeQuantumList) {
                MarketPtSeckillVO.SessionVO sessionVO = new MarketPtSeckillVO.SessionVO();
                sessionVO.setStartTime(marketPtSeckillTimeQuantum.getStartTime());
                sessionVO.setEndTime(marketPtSeckillTimeQuantum.getEndTime());
                sessionVOList.add(sessionVO);
            }
        }
        activityVO.setSessionTime(sessionVOList);
        return activityVO;
    }

    /**
     * 秒杀spu列表
     *
     * @param qto
     * @return
     */
    @Override
    public PageData<MarketPtSeckillVO.KillGoodsVO> seckillGoods(MarketPtSeckillQTO.GoodsQTO qto) {
        if (!ObjectUtil.isAllNotEmpty(qto, qto.getSeckillId(), qto.getTimeQuanTumId())) {
            throw new BusinessException("参数不能未空！");
        }
        List<MarketPtSeckillVO.KillGoodsVO> killGoodsVOList = new ArrayList<>();
        QueryWrapper<MarketPtSeckillGoodsSpu> query = MybatisPlusUtil.query();
        if (ObjectUtil.isNotEmpty(qto.getBrandId())) {
            query.and(i -> i.eq("brand_id", qto.getBrandId()));
        }
        if (ObjectUtil.isNotEmpty(qto.getCategoryId())) {
            query.and(i -> i.eq("category_id", qto.getCategoryId()));
        }
        query.eq("seckill_id", qto.getSeckillId());
        query.eq("time_quantum_id", qto.getTimeQuanTumId());
        query.orderByDesc("cdate");
        IPage<MarketPtSeckillGoodsSpu> page = MybatisPlusUtil.pager(qto);
        iMarketPtSeckillGoodsSpuRepository.page(page, query);
        if (CollUtil.isNotEmpty(page.getRecords())) {
            for (MarketPtSeckillGoodsSpu record : page.getRecords()) {
                MarketPtSeckillVO.KillGoodsVO killGoodsVO = new MarketPtSeckillVO.KillGoodsVO();
                BeanUtil.copyProperties(record, killGoodsVO);
                GoodsInfoVO.DetailVO goodsDetail = iGoodsInfoRpc.getGoodsDetail(new GoodsInfoDTO.IdDTO(record.getGoodsId()), 1);
                killGoodsVO.setKillSpuId(record.getId());
                killGoodsVO.setGoodsName(goodsDetail.getGoodsName());
                killGoodsVO.setGoodsImage(goodsDetail.getGoodsImage());
                //killGoodsVO.setGoodsType(goodsDetail.getIsPointGood() ? MarketPtSeckillSpuTypeEnum.普通商品.getCode() : MarketPtSeckillSpuTypeEnum.积分商品.getCode());
                killGoodsVOList.add(killGoodsVO);
            }
        }
        return MybatisPlusUtil.toPageData(killGoodsVOList, qto.getPageNum(), qto.getPageSize(), page.getTotal());
    }

    @Override
    public void saveKillGoods(MarketPtSeckillDTO.SeckillGoodsDTO qto) {
        if (!ObjectUtil.isAllNotEmpty(qto, qto.getKillSpuIdList())) {
            throw new BusinessException("参数不能未空!");
        }
        for (MarketPtSeckillDTO.SeckillSpuSkuGoodsDTO seckillSpuSkuGoodsDTO : qto.getKillSpuIdList()) {
            if (ObjectUtil.isEmpty(seckillSpuSkuGoodsDTO)) {
                throw new BusinessException("参数不能未空!");
            }
            MarketPtSeckillGoodsSpu spu = iMarketPtSeckillGoodsSpuRepository.getById(seckillSpuSkuGoodsDTO.getKillSpuId());
            if (ObjectUtil.isEmpty(spu)) {
                throw new BusinessException("未查询到报名的spu商品信息!");
            }
            spu.setChoose(MarketPtSeckillSpuChooseEnum.已选择.getCode());
            iMarketPtSeckillGoodsSpuRepository.updateById(spu);
            QueryWrapper<MarketPtSeckillGoodsSku> query = MybatisPlusUtil.query();
            query.eq("goods_spu_item_id", seckillSpuSkuGoodsDTO.getKillSpuId());
            List<MarketPtSeckillGoodsSku> skuList = iMarketPtSeckillGoodsSkuRepository.list(query);
            if (CollUtil.isEmpty(skuList)) {
                throw new BusinessException("未查询到报名的sku商品信息!");
            }
            for (MarketPtSeckillGoodsSku sku : skuList) {
                for (String killSkuId : seckillSpuSkuGoodsDTO.getKillSkuId()) {
                    sku.setState(MarketPtSeckillSkuStateEnum.已驳回.getCode());
                    if (sku.getId().equals(killSkuId)) {
                        sku.setState(MarketPtSeckillSkuStateEnum.已通过.getCode());
                    }
                    iMarketPtSeckillGoodsSkuRepository.updateById(sku);
                }
            }
        }
    }

    /**
     * 秒杀sku列表
     *
     * @param qto
     * @return
     */
    @Override
    public List<MarketPtSeckillVO.KillSkuGoods> seckillSkuGoods(MarketPtSeckillQTO.SkuGoodsQTO qto) {
        if (ObjectUtil.isEmpty(qto.getKillSpuId())) {
            throw new BusinessException("参数不能未空!");
        }
        List<MarketPtSeckillVO.KillSkuGoods> killSkuGoodsList = new ArrayList<>();
        QueryWrapper<MarketPtSeckillGoodsSku> wrapper = MybatisPlusUtil.query();
        wrapper.eq("goods_spu_item_id", qto.getKillSpuId());
        List<MarketPtSeckillGoodsSku> skuList = iMarketPtSeckillGoodsSkuRepository.list(wrapper);
        for (MarketPtSeckillGoodsSku marketPtSeckillGoodsSku : skuList) {
            MarketPtSeckillVO.SkuGoodsInfo skuGoodsInfo = iSkuGoodsInfoRpc.selectOne(marketPtSeckillGoodsSku.getSkuId());
            MarketPtSeckillVO.KillSkuGoods killSkuGoods = new MarketPtSeckillVO.KillSkuGoods();
            BeanUtil.copyProperties(marketPtSeckillGoodsSku, killSkuGoods);
            killSkuGoods.setSeckillInventory(marketPtSeckillGoodsSku.getSeckillQuantity() - marketPtSeckillGoodsSku.getSaleQuantity());
            killSkuGoods.setKillSkuId(marketPtSeckillGoodsSku.getId());
            if (ObjectUtil.isNotEmpty(skuGoodsInfo)) {
                killSkuGoods.setSpecsValue(skuGoodsInfo.getSpecsValue());
                killSkuGoods.setSaleSkuPrice(skuGoodsInfo.getSaleSkuPrice());
            }
            killSkuGoodsList.add(killSkuGoods);
        }
        return killSkuGoodsList;
    }

    /**
     * 获取秒杀时间段
     *
     * @param dto
     * @return
     */
    @Override
    public PageData<MarketPtSeckillVO.QuantumSessionVO> getKillQuanTum(MarketPtSeckillQTO.QuantumQTO dto) {
        if (StrUtil.isEmpty(dto.getId())) {
            throw new BusinessException("参数不能为空！");
        }
        IPage<MarketPtSeckillTimeQuantum> pager = MybatisPlusUtil.pager(dto);
        QueryWrapper<MarketPtSeckillTimeQuantum> query = MybatisPlusUtil.query();
        query.eq("seckill_id", dto.getId());
        marketPtSeckillTimeQuantumRepository.page(pager, query);
        List<MarketPtSeckillVO.QuantumSessionVO> quantumVOList = new ArrayList<>();
        if (CollUtil.isNotEmpty(pager.getRecords())) {
            for (MarketPtSeckillTimeQuantum record : pager.getRecords()) {
                MarketPtSeckillVO.QuantumSessionVO quantumVO = new MarketPtSeckillVO.QuantumSessionVO();
                BeanUtil.copyProperties(record, quantumVO);
                quantumVOList.add(quantumVO);
            }
        }
        return MybatisPlusUtil.toPageData(quantumVOList, dto.getPageNum(), dto.getPageSize(), pager.getTotal());
    }

    private MarketPtSeckillVO.ActivityListVO getActivityListVO(MarketPtSeckill pageRecord) {
        //等待开启 todo 定时任务
        String startTime = pageRecord.getSeckillStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String endTime = pageRecord.getSeckillEndTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        MarketPtSeckillVO.ActivityListVO activityListVO = new MarketPtSeckillVO.ActivityListVO();
        if (DateUtil.compareDate(DateUtil.getTime(), startTime)) {
            //现在的时间大于开始时间
            if (DateUtil.compareDate(endTime, DateUtil.getTime())) {
                //结束的时间大于现在的时间
                if (!pageRecord.getState().equals(MarketPtSeckillActivityEnum.进行中.getCode())) {
                    pageRecord.setState(MarketPtSeckillActivityEnum.进行中.getCode());
                }
            } else {
                //结束的时间小于现在的时间
                if (!pageRecord.getState().equals(MarketPtSeckillActivityEnum.已结束.getCode())) {
                    pageRecord.setState(MarketPtSeckillActivityEnum.已结束.getCode());
                }
            }
        } else {
            if (!pageRecord.getState().equals(MarketPtSeckillActivityEnum.未开始.getCode())) {
                pageRecord.setState(MarketPtSeckillActivityEnum.未开始.getCode());
            }
        }
        BeanUtils.copyProperties(pageRecord, activityListVO);
        return activityListVO;
    }

    @Override
    public List<String> listGoodsIdBySeckillIng() {
        return marketPtSeckillMapper.listGoodsIdBySeckillIng();
    }

/*    @Override
    @Transactional
    public void addMarketPtSeckill(MarketPtSeckillDTO.ETO eto) {
        if (null == eto) {
            throw new BusinessException("参数不能微空");
        }
        checkTime(eto);
        MarketPtSeckill marketPtSeckill = new MarketPtSeckill();
        BeanUtils.copyProperties(eto, marketPtSeckill);
        if (ObjectUtils.isNotEmpty(eto.getBuyMax())) {
            marketPtSeckill.setBuyMax(999);
        }
        if (ObjectUtils.isNotEmpty(eto.getGoodsMax())) {
            marketPtSeckill.setGoodsMax(999);
        }

        marketPtSeckill.setTypeCode(DateUtils.fomatLocalDateTime(eto.getSeckillStartTime(), "HH"));
        repository.save(marketPtSeckill);
        //获取活动id
        String activityId = marketPtSeckill.getId();
        //遍历类目id
        if (eto.getCategoryIds() == null || eto.getTypeCode() == null) {
            throw new BusinessException("请选择商品类目或选择店铺类型");
        }
        for (String categoryId : eto.getCategoryIds()) {
            MarketPtSeckillGoodsCategory marketPtSeckillGoodsCategory = new MarketPtSeckillGoodsCategory();
            marketPtSeckillGoodsCategory.setActivityId(activityId).setCategoryId(categoryId);
            categoryRepository.save(marketPtSeckillGoodsCategory);
        }

    }*/

 /*   private void checkTime(MarketPtSeckillDTO.ETO eto) {
        if (ObjectUtils.isEmpty(eto.getSeckillEndTime())) {
            throw new BusinessException("时间为空");
        }

        if (ObjectUtils.isEmpty(eto.getSeckillStartTime())) {
            throw new BusinessException("时间为空");
        }
        if (ObjectUtils.isEmpty(eto.getOnlineStartTime())) {
            throw new BusinessException("时间为空");
        }
        if (ObjectUtils.isEmpty(eto.getSignEndTime())) {
            throw new BusinessException("时间为空");
        }
        if (ObjectUtils.isEmpty(eto.getSignStartTime())) {
            throw new BusinessException("时间为空");
        }
        if (LocalDateTime.now().isAfter(eto.getSeckillEndTime())) {
            throw new BusinessException("请检查时间");
        }
        if (LocalDateTime.now().isAfter(eto.getSeckillStartTime())) {
            throw new BusinessException("请检查时间");
        }
        if (LocalDateTime.now().isAfter(eto.getOnlineStartTime())) {
            throw new BusinessException("请检查时间");
        }
        if (LocalDateTime.now().isAfter(eto.getSignEndTime())) {
            throw new BusinessException("请检查时间");
        }
        if (LocalDateTime.now().isAfter(eto.getSignStartTime())) {
            throw new BusinessException("请检查时间");
        }
    }

    @Override
    @Transactional
    public void deleteMarketPtSeckill(MarketPtSeckillDTO.IdListDTO dto) {
        if (dto == null || dto.getIdList() == null || dto.getIdList().size() == 0) {
            throw new BusinessException("参数不能为空！");
        }
        //删除活动类目表
        for (String seckillId : dto.getIdList()) {
            QueryWrapper<MarketPtSeckillGoodsCategory> wrapper = new QueryWrapper<>();
            wrapper.eq("seckill_id", seckillId);
            List<MarketPtSeckillGoodsCategory> list = categoryRepository.list(wrapper);
            if (list != null && list.size() > 0) {
                categoryRepository.remove(wrapper);
            }
        }
        repository.removeByIds(dto.getIdList());
    }

    @Override
    public void editMarketPtSeckill(MarketPtSeckillDTO.ETO eto) {
        MarketPtSeckill marketPtSeckill = new MarketPtSeckill();
        BeanUtils.copyProperties(eto, marketPtSeckill);
        repository.updateById(marketPtSeckill);
    }

    @Override
    public MarketPtSeckillVO.ActivityListVO detailMarketPtSeckill(MarketPtSeckillQTO.IdQTO qto) {
        if (StrUtil.isEmpty(qto.getId())) {
            throw new BusinessException("参数不能为空");
        }
        MarketPtSeckill marketPtSeckill = repository.getById(qto.getId());
        //       MarketPtSeckillVO.activityListVO activityListVO = new MarketPtSeckillVO.activityListVO();
        if (ObjectUtils.isEmpty(marketPtSeckill)) {
            throw new BusinessException("没有数据");
        }
        //       BeanUtils.copyProperties(marketPtSeckill, activityListVO);
*//*        QueryWrapper<MarketPtSeckillGoodsCategory> query = MybatisPlusUtil.query();
        query.and(i -> i.eq("seckill_id", dto.getId()));
        List<MarketPtSeckillGoodsCategory> list = categoryRepository.list(query);
        if (ObjectUtils.isNotEmpty(list)) {
            List<String> categoryIds = new ArrayList<>();
            for (MarketPtSeckillGoodsCategory marketPtSeckillGoodsCategory : list) {
                categoryIds.add(marketPtSeckillGoodsCategory.getCategoryId());
            }
            if (ObjectUtils.isNotEmpty(categoryIds)) {
                List<GoodsCategoryVO.CategoryJoinSearchVO> categoryJoinSearchVOS = iGoodsCategoryRpc.innerGetIdAndName(categoryIds);
                activityListVO.setCategory(categoryJoinSearchVOS);
            }
        }*//*
        MarketPtSeckillVO.ActivityListVO activityListVO = getActivityListVO(marketPtSeckill);
        return activityListVO;
    }

    @Override
    public List<MarketPtSeckillVO.ListVO> list() {
        List<MarketPtSeckill> list = repository.list();
        List<MarketPtSeckillVO.ListVO> ListVO = new ArrayList<MarketPtSeckillVO.ListVO>();
        for (MarketPtSeckill marketPtSeckill : list) {
            MarketPtSeckillVO.ListVO VO = new MarketPtSeckillVO.ListVO();
            BeanUtils.copyProperties(marketPtSeckill, VO);
            ListVO.add(VO);
        }
        return ListVO;
    }*/
}
