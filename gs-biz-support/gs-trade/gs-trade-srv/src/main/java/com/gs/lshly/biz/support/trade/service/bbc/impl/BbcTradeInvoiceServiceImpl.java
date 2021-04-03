package com.gs.lshly.biz.support.trade.service.bbc.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.TradeInvoice;
import com.gs.lshly.biz.support.trade.repository.ITradeInvoiceRepository;
import com.gs.lshly.biz.support.trade.service.bbc.IBbcTradeInvoiceService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeInvoiceDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeInvoiceQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeInvoiceVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;


/**
* <p>
*  服务实现类
* </p>
* @author zst
* @since 2021-01-15
*/
@Component
public class BbcTradeInvoiceServiceImpl implements IBbcTradeInvoiceService {

    @Autowired
    private ITradeInvoiceRepository repository;

    @Override
    public PageData<BbcTradeInvoiceVO.BbcListVO> pageData(BbcTradeInvoiceQTO.Ids qto) {
        QueryWrapper<TradeInvoice> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",qto.getJwtUserId());
        wrapper.orderByDesc("cdate");
        IPage<TradeInvoice> page = MybatisPlusUtil.pager(qto);
        IPage<TradeInvoice> invoiceIPage = repository.page(page, wrapper);
        if(ObjectUtils.isEmpty(invoiceIPage) && ObjectUtils.isEmpty(invoiceIPage.getRecords())){
            return new PageData<>();
        }
        return MybatisPlusUtil.toPageData(qto, BbcTradeInvoiceVO.BbcListVO.class, page);
    }

    @Override
    public void addTradeInvoice(BbcTradeInvoiceDTO.AddETO eto) {
//        QueryWrapper<TradeInvoice> wrapper = new QueryWrapper<>();
//        wrapper.eq("user_id",eto.getJwtUserId());
//        if(ObjectUtils.isNotEmpty(eto.getTaxNumber())){
//            wrapper.eq("tax_number",eto.getTaxNumber());
//        }
//        TradeInvoice repositoryOne = repository.getOne(wrapper);
//        if(ObjectUtils.isNotEmpty(repositoryOne)){
//            throw new BootstrapMethodError("税务人识别号已存在,请输入正确税号");
//        }
//        TradeInvoice tradeInvoice = new TradeInvoice();
//        tradeInvoice.setUserId(eto.getJwtUserId());
//        tradeInvoice.setUserName(eto.getJwtUserName());
//        tradeInvoice.setIsDefault(TradeInvoiceEnum.普通发票.getCode());
//        tradeInvoice.setInvoiceStatus(TradeInvoiceEnum.待开具.getCode());
//        BeanUtils.copyProperties(eto, tradeInvoice);
//        repository.save(tradeInvoice);
    }


    @Override
    public void deleteTradeInvoice(BbcTradeInvoiceDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }

    @Override
    public void editTradeInvoice(BbcTradeInvoiceDTO.EditETO eto) {
        QueryWrapper<TradeInvoice> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",eto.getJwtUserId());
        if(ObjectUtils.isNotEmpty(eto.getTaxNumber())){
            wrapper.eq("tax_number",eto.getTaxNumber());
        }
        TradeInvoice tradeInvoiceOne = repository.getOne(wrapper);
        if(ObjectUtils.isNotEmpty(tradeInvoiceOne)){
            throw new BootstrapMethodError("税务人识别号已存在,请输入正确税号");
        }
        TradeInvoice tradeInvoice = new TradeInvoice();
        tradeInvoice.setUserId(eto.getJwtUserId());
        tradeInvoice.setUserName(eto.getJwtUserName());
        BeanUtils.copyProperties(eto, tradeInvoice);
        repository.updateById(tradeInvoice);
    }

    @Override
    public BbcTradeInvoiceVO.DetailVO detailTradeInvoice(BbcTradeInvoiceDTO.IdDTO dto) {
        TradeInvoice tradeInvoice = repository.getById(dto.getId());
        BbcTradeInvoiceVO.DetailVO detailVo = new BbcTradeInvoiceVO.DetailVO();
        if(ObjectUtils.isEmpty(tradeInvoice)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(tradeInvoice, detailVo);
        return detailVo;
    }

}
