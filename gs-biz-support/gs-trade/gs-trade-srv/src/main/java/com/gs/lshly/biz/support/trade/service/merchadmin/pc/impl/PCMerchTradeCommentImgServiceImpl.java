package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.TradeCommentImg;
import com.gs.lshly.biz.support.trade.repository.ITradeCommentImgRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradeCommentImgService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeCommentImgDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeCommentImgQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeCommentImgVO;
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
public class PCMerchTradeCommentImgServiceImpl implements IPCMerchTradeCommentImgService {

    @Autowired
    private ITradeCommentImgRepository repository;

    @Override
    public void addTradeCommentImg(PCMerchTradeCommentImgDTO.ETO eto) {
        TradeCommentImg tradeCommentImg = new TradeCommentImg();
        BeanUtils.copyProperties(eto, tradeCommentImg);
        repository.save(tradeCommentImg);
    }




    @Override
    public PageData<PCMerchTradeCommentImgVO.ListVO> pageData(PCMerchTradeCommentImgQTO.QTO qto) {
        QueryWrapper<TradeCommentImg> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("cdate");
        IPage<TradeCommentImg> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, PCMerchTradeCommentImgVO.ListVO.class, page);
    }


    @Override
    public void deleteTradeCommentImg(PCMerchTradeCommentImgDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editTradeCommentImg(PCMerchTradeCommentImgDTO.ETO eto) {
        TradeCommentImg tradeCommentImg = new TradeCommentImg();
        BeanUtils.copyProperties(eto, tradeCommentImg);
        repository.updateById(tradeCommentImg);
    }

    @Override
    public PCMerchTradeCommentImgVO.DetailVO detailTradeCommentImg(PCMerchTradeCommentImgDTO.IdDTO dto) {
        TradeCommentImg tradeCommentImg = repository.getById(dto.getId());
        PCMerchTradeCommentImgVO.DetailVO detailVo = new PCMerchTradeCommentImgVO.DetailVO();
        if(ObjectUtils.isEmpty(tradeCommentImg)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(tradeCommentImg, detailVo);
        return detailVo;
    }

}
