package com.gs.lshly.biz.support.trade.service.platadmin.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.gs.lshly.common.enums.MarketPtSeckillActivityEnum;
import com.gs.lshly.common.enums.MarketPtSeckillStatusEnum;
import com.gs.lshly.common.enums.MarketPtSeckillTimeQuantumEnum;
import com.lakala.boss.api.utils.DateUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.MarketPtActivity;
import com.gs.lshly.biz.support.trade.entity.MarketPtActivityGoodsCategory;
import com.gs.lshly.biz.support.trade.entity.MarketPtActivityJurisdiction;
import com.gs.lshly.biz.support.trade.entity.MarketPtSeckill;
import com.gs.lshly.biz.support.trade.entity.MarketPtSeckillGoodsCategory;
import com.gs.lshly.biz.support.trade.repository.IMarketPtSeckillGoodsCategoryRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketPtSeckillRepository;
import com.gs.lshly.biz.support.trade.service.platadmin.IMarketPtSeckillService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsCategoryVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.MarketPtActivityDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.MarketPtSeckillDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtSeckillQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtActivityVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtSeckillVO;
import com.gs.lshly.common.utils.DateUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsCategoryRpc;

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
    private IMarketPtSeckillGoodsCategoryRepository categoryRepository;

    @DubboReference
    private IGoodsCategoryRpc iGoodsCategoryRpc;

    @Override
    public PageData<MarketPtSeckillVO.activityListVO> pageData(MarketPtSeckillQTO.QTO qto) {
        QueryWrapper<MarketPtSeckill> wrapper = MybatisPlusUtil.query();
        wrapper.orderByDesc("cdate");
        if (ObjectUtil.isNotEmpty(qto) && StrUtil.isNotEmpty(qto.getAcName())) {
            wrapper.and(i -> i.like("name", qto.getAcName()));
        }
        IPage<MarketPtSeckill> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        List<MarketPtSeckill> pageRecords = page.getRecords();
        List<MarketPtSeckillVO.activityListVO> listVOS = new ArrayList<>();
        for (MarketPtSeckill pageRecord : pageRecords) {
            MarketPtSeckillVO.activityListVO activityListVO = getActivityListVO(pageRecord);
            listVOS.add(activityListVO);
        }
        return MybatisPlusUtil.toPageData(listVOS, qto.getPageNum(), qto.getPageSize(), page.getTotal());
    }

    private MarketPtSeckillVO.activityListVO getActivityListVO(MarketPtSeckill pageRecord) {
        LocalDateTime startTime = pageRecord.getSeckillStartTime();
        LocalDateTime endTime = pageRecord.getSeckillEndTime();
        MarketPtSeckillVO.activityListVO activityListVO = new MarketPtSeckillVO.activityListVO();
        BeanUtils.copyProperties(pageRecord, activityListVO);
        if (DateUtil.compareDate(DateUtil.getTime(), startTime.toString())) {
            if (DateUtil.compareDate(endTime.toString(), DateUtil.getTime())) {
                activityListVO.setState(MarketPtSeckillActivityEnum.进行中.getCode());
            } else {
                activityListVO.setState(MarketPtSeckillActivityEnum.已结束.getCode());
            }
        } else {
            activityListVO.setState(MarketPtSeckillActivityEnum.未开始.getCode());
        }
        return activityListVO;
    }

    @Override
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

    }

    private void checkTime(MarketPtSeckillDTO.ETO eto) {
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
    public MarketPtSeckillVO.activityListVO detailMarketPtSeckill(MarketPtSeckillDTO.IdDTO dto) {
        if (StrUtil.isEmpty(dto.getId())) {
            throw new BusinessException("参数不能为空");
        }
        MarketPtSeckill marketPtSeckill = repository.getById(dto.getId());
        //       MarketPtSeckillVO.activityListVO activityListVO = new MarketPtSeckillVO.activityListVO();
        if (ObjectUtils.isEmpty(marketPtSeckill)) {
            throw new BusinessException("没有数据");
        }
        //       BeanUtils.copyProperties(marketPtSeckill, activityListVO);
/*        QueryWrapper<MarketPtSeckillGoodsCategory> query = MybatisPlusUtil.query();
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
        }*/
        MarketPtSeckillVO.activityListVO activityListVO = getActivityListVO(marketPtSeckill);
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
    }
}
