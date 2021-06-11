package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.*;
import com.gs.lshly.biz.support.trade.repository.*;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketPtSeckillMerchantService;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketPtSeckillService;
import com.gs.lshly.common.enums.MarketPtSeckillActivityEnum;
import com.gs.lshly.common.enums.MarketPtSeckillApplyTypeEnum;
import com.gs.lshly.common.enums.SeckillSignEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsCategoryVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchSkuGoodInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtSeckillDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtSeckillGoodsSpuDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtSeckillMerchantDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMarketPtSeckillMerchantQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMarketPtSeckillQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtSeckillMerchantVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtSeckillVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.MarketPtSeckillDTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtSeckillVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsCategoryRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsInfoRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminSkuGoodInfoRpc;
import com.lakala.boss.api.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * <p>
 * 活动记录
 * </p>
 *
 * @author hanly
 * @since 2021-06-10
 */
@Component
@Slf4j
public class PCMerchMarketPtSeckillServiceImpl implements IPCMerchMarketPtSeckillService {
    @Autowired
    private IMarketPtSeckillRepository iMarketPtSeckillRepository;
    @Autowired
    private IMarketPtSeckillGoodsSpuRepository iMarketPtSeckillGoodsSpuRepository;
    @Autowired
    private IMarketPtSeckillGoodsSkuRepository iMarketPtSeckillGoodsSkuRepository;
    @Autowired
    private IMarketPtSeckillTimeQuantumRepository iMarketPtSeckillTimeQuantumRepository;

    /**
     * 秒杀活动列表
     *
     * @param qto
     * @return
     */
    @Override
    public PageData<PCMerchMarketPtSeckillVO.ListVO> seckillList(PCMerchMarketPtSeckillQTO.QTO qto) {
        List<PCMerchMarketPtSeckillVO.ListVO> listVOList = new ArrayList<>();
        IPage<MarketPtSeckill> pager = MybatisPlusUtil.pager(qto);
        QueryWrapper<MarketPtSeckill> query = MybatisPlusUtil.query();
        if (ObjectUtil.isNotEmpty(qto.getState())) {
            query.eq("state", qto.getState());
        }
        if (ObjectUtil.isNotEmpty(qto.getStartTime())) {
            query.and(i -> i.ge("sign_start_time", qto.getStartTime()).or(s -> s.ge("seckill_start_time", qto.getStartTime())));
        }
        if (ObjectUtil.isNotEmpty(qto.getEndTime())) {
            query.and(i -> i.ge("sign_end_time", qto.getEndTime()).or(s -> s.ge("seckill_end_time", qto.getEndTime())));
        }
        if (ObjectUtil.isNotEmpty(qto.getKeyWord())) {
            query.eq("name", qto.getKeyWord());
        }
        query.orderByDesc("cdate");
        iMarketPtSeckillRepository.page(pager, query);
        if (CollUtil.isEmpty(pager.getRecords())) {
            return MybatisPlusUtil.toPageData(listVOList, qto.getPageNum(), qto.getPageSize(), pager.getTotal());
        }
        for (MarketPtSeckill record : pager.getRecords()) {
            PCMerchMarketPtSeckillVO.ListVO listVO = fillListVO(record, qto);
            listVOList.add(listVO);
        }
        return MybatisPlusUtil.toPageData(listVOList, qto.getPageNum(), qto.getPageSize(), pager.getTotal());
    }

    private PCMerchMarketPtSeckillVO.ListVO fillListVO(MarketPtSeckill record, PCMerchMarketPtSeckillQTO.QTO qto) {
        PCMerchMarketPtSeckillVO.ListVO listVO = new PCMerchMarketPtSeckillVO.ListVO();
        String startTime = record.getSeckillStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String endTime = record.getSeckillEndTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        if (DateUtil.compareDate(DateUtil.getTime(), startTime)) {
            //现在的时间大于开始时间
            if (DateUtil.compareDate(endTime, DateUtil.getTime())) {
                //结束的时间大于现在的时间
                if (!record.getState().equals(MarketPtSeckillActivityEnum.进行中.getCode())) {
                    record.setState(MarketPtSeckillActivityEnum.进行中.getCode());
                }
            } else {
                //结束的时间小于现在的时间
                if (!record.getState().equals(MarketPtSeckillActivityEnum.已结束.getCode())) {
                    record.setState(MarketPtSeckillActivityEnum.已结束.getCode());
                }
            }
        } else {
            if (!record.getState().equals(MarketPtSeckillActivityEnum.未开始.getCode())) {
                record.setState(MarketPtSeckillActivityEnum.未开始.getCode());
            }
        }
        QueryWrapper<MarketPtSeckillGoodsSpu> query = MybatisPlusUtil.query();
        query.eq("seckill_id", record.getId());
        query.eq("shop_id", qto.getJwtShopId());
        int count = iMarketPtSeckillGoodsSpuRepository.count(query);
        if (count > 0) {
            listVO.setApplyState(MarketPtSeckillApplyTypeEnum.已报名.getCode());
        } else {
            listVO.setApplyState(MarketPtSeckillApplyTypeEnum.去报名.getCode());
        }
        if (record.getState().equals(MarketPtSeckillActivityEnum.已结束.getCode())) {
            listVO.setApplyState(MarketPtSeckillApplyTypeEnum.已结束.getCode());
        }
        BeanUtil.copyProperties(record, listVO);
        iMarketPtSeckillRepository.updateById(record);
        return listVO;
    }

    @Override
    public PageData<PCMerchMarketPtSeckillVO.SessionVO> seckillTimeQuantum(PCMerchMarketPtSeckillQTO.IdQTO qto) {
        List<PCMerchMarketPtSeckillVO.SessionVO> sessionVOList = new ArrayList<>();
        if (StrUtil.isEmpty(qto.getId())) {
            throw new BusinessException("参数不能为空!");
        }
        IPage<MarketPtSeckillTimeQuantum> pager = MybatisPlusUtil.pager(qto);
        QueryWrapper<MarketPtSeckillTimeQuantum> query = MybatisPlusUtil.query();
        query.eq("seckill_id", qto.getId());
        query.orderByAsc("start_time");
        iMarketPtSeckillTimeQuantumRepository.page(pager, query);
        if (CollUtil.isEmpty(pager.getRecords())) {
            return MybatisPlusUtil.toPageData(sessionVOList, qto.getPageNum(), qto.getPageSize(), pager.getTotal());
        }
        for (MarketPtSeckillTimeQuantum record : pager.getRecords()) {
            PCMerchMarketPtSeckillVO.SessionVO sessionVO = new PCMerchMarketPtSeckillVO.SessionVO();
            BeanUtil.copyProperties(record, sessionVO);
            sessionVOList.add(sessionVO);
        }
        return MybatisPlusUtil.toPageData(sessionVOList, qto.getPageNum(), qto.getPageSize(), pager.getTotal());
    }

    @Override
    public PageData<PCMerchMarketPtSeckillVO.AllSpuVO> allSpu(PCMerchMarketPtSeckillQTO.AllSpuQTO qto) {
        return null;
    }

    @Override
    public PageData<PCMerchMarketPtSeckillVO.AllSkuVO> allSku(PCMerchMarketPtSeckillQTO.AllSkuQTO qto) {
        return null;
    }

    /**
     * 报名的商品信息
     *
     * @param qto
     * @return
     */
    @Override
    public PageData<PCMerchMarketPtSeckillVO.SeckillGoodsInfoVO> seckillSpuGoods(PCMerchMarketPtSeckillQTO.SpuQTO qto) {
        List<PCMerchMarketPtSeckillVO.SeckillGoodsInfoVO> spuGoodsInfoVOList = new ArrayList<>();
        if (!StrUtil.isAllNotEmpty(qto.getId(), qto.getSeckillId())) {
            throw new BusinessException("商品不能为空!");
        }
        if (ObjectUtil.isNotEmpty(qto.getGoodsType())) {

        }
        //spu信息
        IPage<MarketPtSeckillGoodsSpu> pager = MybatisPlusUtil.pager(qto);
        QueryWrapper<MarketPtSeckillGoodsSpu> query = MybatisPlusUtil.query();
        query.eq("shop_id", qto.getJwtShopId());
        iMarketPtSeckillGoodsSpuRepository.page(pager, query);
        if (CollUtil.isEmpty(pager.getRecords())) {
            return MybatisPlusUtil.toPageData(spuGoodsInfoVOList, qto.getPageNum(), qto.getPageSize(), pager.getTotal());
        }
        for (MarketPtSeckillGoodsSpu record : pager.getRecords()) {
            PCMerchMarketPtSeckillVO.SeckillGoodsInfoVO seckillGoodsInfoVO = new PCMerchMarketPtSeckillVO.SeckillGoodsInfoVO();
            BeanUtil.copyProperties(record, seckillGoodsInfoVO);
            seckillGoodsInfoVO.setSpuId(record.getId());
        }
        return null;
    }

    @Override
    public PageData<PCMerchMarketPtSeckillVO.SkuGoodsInfoVO> seckillSkuGoods(PCMerchMarketPtSeckillQTO.SkuQTO qto) {
        return null;
    }

    @Override
    public void addSpuGoods(PCMerchMarketPtSeckillDTO.SpuIdETO dto) {

    }

    @Override
    public void addSkuGoods(PCMerchMarketPtSeckillDTO.SpuIdETO dto) {

    }

    @Override
    public void addSkuGoodsDetail(PCMerchMarketPtSeckillDTO.AddSeckillGoodsETO dto) {

    }

    @Override
    public void delSpu(PCMerchMarketPtSeckillDTO.SpuIdETO dto) {

    }
}
