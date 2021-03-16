package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.TradeRightsImg;
import com.gs.lshly.biz.support.trade.repository.ITradeRightsImgRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradeRightsImgService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeRightsImgDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeRightsImgQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeRightsImgVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;


/**
* <p>
*  服务实现类
* </p>
* @author zdf
* @since 2020-12-17
*/
@Component
public class PCMerchTradeRightsImgServiceImpl implements IPCMerchTradeRightsImgService {

    @Autowired
    private ITradeRightsImgRepository repository;

    @Override
    public PageData<PCMerchTradeRightsImgVO.ListVO> pageData(PCMerchTradeRightsImgQTO.QTO qto) {
        QueryWrapper<TradeRightsImg> wrapper = new QueryWrapper<>();
        IPage<TradeRightsImg> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, PCMerchTradeRightsImgVO.ListVO.class, page);
    }

    @Override
    public void addTradeRightsImg(PCMerchTradeRightsImgDTO.ETO eto) {
        TradeRightsImg tradeRightsImg = new TradeRightsImg();
        BeanUtils.copyProperties(eto, tradeRightsImg);
        repository.save(tradeRightsImg);
    }


    @Override
    public void deleteTradeRightsImg(PCMerchTradeRightsImgDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editTradeRightsImg(PCMerchTradeRightsImgDTO.ETO eto) {
        TradeRightsImg tradeRightsImg = new TradeRightsImg();
        BeanUtils.copyProperties(eto, tradeRightsImg);
        repository.updateById(tradeRightsImg);
    }

    @Override
    public PCMerchTradeRightsImgVO.DetailVO detailTradeRightsImg(PCMerchTradeRightsImgDTO.IdDTO dto) {
        TradeRightsImg tradeRightsImg = repository.getById(dto.getId());
        PCMerchTradeRightsImgVO.DetailVO detailVo = new PCMerchTradeRightsImgVO.DetailVO();
        if(ObjectUtils.isEmpty(tradeRightsImg)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(tradeRightsImg, detailVo);
        return detailVo;
    }

}
