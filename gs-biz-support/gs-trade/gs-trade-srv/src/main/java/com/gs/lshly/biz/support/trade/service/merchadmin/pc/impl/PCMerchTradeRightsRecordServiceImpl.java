package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.TradeRightsRecord;
import com.gs.lshly.biz.support.trade.repository.ITradeRightsRecordRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradeRightsRecordService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeRightsRecordDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeRightsRecordQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeRightsRecordVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;


/**
* <p>
*  服务实现类
* </p>
* @author zdf
* @since 2020-12-17
*/
@Component
public class PCMerchTradeRightsRecordServiceImpl implements IPCMerchTradeRightsRecordService {

    @Autowired
    private ITradeRightsRecordRepository repository;

    @Override
    public PageData<PCMerchTradeRightsRecordVO.ListVO> pageData(PCMerchTradeRightsRecordQTO.QTO qto) {
        QueryWrapper<TradeRightsRecord> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("cdate");
        IPage<TradeRightsRecord> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, PCMerchTradeRightsRecordVO.ListVO.class, page);
    }

    @Override
    public void addTradeRightsRecord(PCMerchTradeRightsRecordDTO.ETO eto) {
        TradeRightsRecord tradeRightsRecord = new TradeRightsRecord();
        BeanUtils.copyProperties(eto, tradeRightsRecord);
        repository.save(tradeRightsRecord);
    }


    @Override
    public void deleteTradeRightsRecord(PCMerchTradeRightsRecordDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editTradeRightsRecord(PCMerchTradeRightsRecordDTO.ETO eto) {
        TradeRightsRecord tradeRightsRecord = new TradeRightsRecord();
        BeanUtils.copyProperties(eto, tradeRightsRecord);
        repository.updateById(tradeRightsRecord);
    }

    @Override
    public PCMerchTradeRightsRecordVO.DetailVO detailTradeRightsRecord(PCMerchTradeRightsRecordDTO.IdDTO dto) {
        TradeRightsRecord tradeRightsRecord = repository.getById(dto.getId());
        PCMerchTradeRightsRecordVO.DetailVO detailVo = new PCMerchTradeRightsRecordVO.DetailVO();
        if(ObjectUtils.isEmpty(tradeRightsRecord)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(tradeRightsRecord, detailVo);
        return detailVo;
    }

}
