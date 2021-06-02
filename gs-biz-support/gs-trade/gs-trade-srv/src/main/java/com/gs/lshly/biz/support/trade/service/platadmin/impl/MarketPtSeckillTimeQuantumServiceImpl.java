package com.gs.lshly.biz.support.trade.service.platadmin.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.MarketPtSeckillTimeQuantum;
import com.gs.lshly.biz.support.trade.repository.IMarketPtSeckillTimeQuantumRepository;
import com.gs.lshly.biz.support.trade.service.platadmin.IMarketPtSeckillTimeQuantumService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.MarketPtSeckillTimeQuantumDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtSeckillQTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtSeckillTimeQuantumQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtSeckillTimeQuantumVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtSeckillVO;
import com.gs.lshly.common.utils.BeanUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hanly
 * @since 2021-06-01
 */
@Component
@SuppressWarnings("unchecked")
public class MarketPtSeckillTimeQuantumServiceImpl implements IMarketPtSeckillTimeQuantumService {

    @Autowired
    private IMarketPtSeckillTimeQuantumRepository marketPtSeckillTimeQuantumRepository;

    @Override
    public PageData<MarketPtSeckillTimeQuantumVO.ListVO> pageTimeQuantumlist(MarketPtSeckillTimeQuantumQTO.QTO qto) {
        QueryWrapper<MarketPtSeckillTimeQuantum> query = MybatisPlusUtil.query();
        query.orderByDesc("cdate");
        IPage<MarketPtSeckillTimeQuantum> page = MybatisPlusUtil.pager(qto);
        marketPtSeckillTimeQuantumRepository.page(page, query);
        List<MarketPtSeckillTimeQuantumVO.ListVO> listVOList = new ArrayList<>();
        for (MarketPtSeckillTimeQuantum record : page.getRecords()) {
            MarketPtSeckillTimeQuantumVO.ListVO listVO = new MarketPtSeckillTimeQuantumVO.ListVO();
            BeanUtil.copyProperties(record,listVO);
            listVOList.add(listVO);
        }
        return new PageData<>(listVOList, qto.getPageNum(), qto.getPageSize(), page.getTotal());
    }

    @Override
    public MarketPtSeckillTimeQuantumVO.ListVO timeQuantum(MarketPtSeckillTimeQuantumQTO.IdQTO qto) {
        if (ObjectUtil.isEmpty(qto) || ObjectUtil.isEmpty(qto.getId())) {
            throw new BusinessException("参数不能为空");
        }
        MarketPtSeckillTimeQuantum marketPtSeckillTimeQuantum = marketPtSeckillTimeQuantumRepository.getById(qto.getId());
        if (ObjectUtil.isEmpty(marketPtSeckillTimeQuantum)) {
            throw new BusinessException("未查询到时间端数据");
        }
        MarketPtSeckillTimeQuantumVO.ListVO detail = new MarketPtSeckillTimeQuantumVO.ListVO();
        BeanUtil.copyProperties(marketPtSeckillTimeQuantum, detail);
        return detail;
    }

    @Override
    public void addTimeQuantum(MarketPtSeckillTimeQuantumDTO.ETO dto) {
        if (!ObjectUtil.isAllNotEmpty(dto.getTimeQuantumName(), dto.getStartTime(), dto.getEndTime())) {
            throw new BusinessException("参数不能为空");
        }
        QueryWrapper<MarketPtSeckillTimeQuantum> query = MybatisPlusUtil.query();
        query.eq("time_quantum_name", dto.getTimeQuantumName());
        MarketPtSeckillTimeQuantum marketPtSeckillTimeQuantum = marketPtSeckillTimeQuantumRepository.getOne(query);
        if (ObjectUtil.isNotEmpty(marketPtSeckillTimeQuantum)) {
            throw new BusinessException("已存在相同名称的时间段");
        }
        marketPtSeckillTimeQuantum = new MarketPtSeckillTimeQuantum();
        BeanUtil.copyProperties(dto, marketPtSeckillTimeQuantum);
        marketPtSeckillTimeQuantumRepository.save(marketPtSeckillTimeQuantum);
    }

    @Override
    public void updateTimeQuantum(MarketPtSeckillTimeQuantumDTO.ETO dto) {
        if (!ObjectUtil.isAllNotEmpty(dto.getId(), dto.getTimeQuantumName(), dto.getStartTime(), dto.getEndTime())) {
            throw new BusinessException("参数不能为空");
        }
        MarketPtSeckillTimeQuantum marketPtSeckillTimeQuantum = marketPtSeckillTimeQuantumRepository.getById(dto.getId());
        if (ObjectUtil.isEmpty(marketPtSeckillTimeQuantum)) {
            throw new BusinessException("未查询到时间段数据");
        }
        BeanUtil.copyProperties(dto, marketPtSeckillTimeQuantum);
        marketPtSeckillTimeQuantumRepository.updateById(marketPtSeckillTimeQuantum);
    }

    @Override
    public void deleteTimeQuantum(MarketPtSeckillTimeQuantumDTO.IdDTO dto) {
        if (ObjectUtil.isEmpty(dto) || ObjectUtil.isEmpty(dto.getId())) {
            throw new BusinessException("参数不能为空");
        }
        marketPtSeckillTimeQuantumRepository.removeById(dto.getId());
    }
}
