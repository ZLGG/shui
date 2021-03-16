package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.TradeCommentRecord;
import com.gs.lshly.biz.support.trade.repository.ITradeCommentRecordRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradeCommentRecordService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeCommentRecordDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeCommentRecordQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeCommentRecordVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 * @author Starry
 * @since 2020-11-16
 */
@Component
public class PCMerchTradeCommentRecordServiceImpl implements IPCMerchTradeCommentRecordService {

    @Autowired
    private ITradeCommentRecordRepository repository;

    @Override
    public void addTradeCommentRecord(PCMerchTradeCommentRecordDTO.ETO eto) {
        TradeCommentRecord tradeCommentRecord = new TradeCommentRecord();
        BeanUtils.copyProperties(eto, tradeCommentRecord);
        repository.save(tradeCommentRecord);
    }




    @Override
    public PageData<PCMerchTradeCommentRecordVO.ListVO> pageData(PCMerchTradeCommentRecordQTO.QTO qto) {
        QueryWrapper<TradeCommentRecord> wrapper = new QueryWrapper<>();
        IPage<TradeCommentRecord> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, PCMerchTradeCommentRecordVO.ListVO.class, page);
    }


    @Override
    public void deleteTradeCommentRecord(PCMerchTradeCommentRecordDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editTradeCommentRecord(PCMerchTradeCommentRecordDTO.ETO eto) {
        TradeCommentRecord tradeCommentRecord = new TradeCommentRecord();
        BeanUtils.copyProperties(eto, tradeCommentRecord);
        repository.updateById(tradeCommentRecord);
    }

    @Override
    public PCMerchTradeCommentRecordVO.DetailVO detailTradeCommentRecord(PCMerchTradeCommentRecordDTO.IdDTO dto) {
        TradeCommentRecord tradeCommentRecord = repository.getById(dto.getId());
        PCMerchTradeCommentRecordVO.DetailVO detailVo = new PCMerchTradeCommentRecordVO.DetailVO();
        if(ObjectUtils.isEmpty(tradeCommentRecord)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(tradeCommentRecord, detailVo);
        return detailVo;
    }

}
