package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.TradeInvoice;
import com.gs.lshly.biz.support.trade.repository.ITradeInvoiceRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradeInvoiceService;
import com.gs.lshly.common.enums.TradeInvoiceEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeInvoiceDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeInvoiceQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeInvoiceListVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author zst
* @since 2020-12-22
*/
@Component
public class PCMerchTradeInvoiceServiceImpl implements IPCMerchTradeInvoiceService {

    @Autowired
    private ITradeInvoiceRepository iTradeInvoiceRepository;

    @Override
    public PageData<PCMerchTradeInvoiceListVO.ListVO> invoiceListPageData(PCMerchTradeInvoiceQTO.QTO qto) {
        QueryWrapper<TradeInvoice> wrapper=new QueryWrapper<>();
        if(StringUtils.isNotEmpty(qto.getTradeId())){
            wrapper.eq("trade_id",qto.getTradeId());
        }
        if(StringUtils.isNotEmpty(qto.getInvoiceNumber())){
            wrapper.eq("invoice_number",qto.getInvoiceNumber());
        }
        if(ObjectUtils.isNotEmpty(qto.getInvoiceStatus())){
            wrapper.eq("invoice_status",qto.getInvoiceStatus());
        }
        wrapper.eq("shop_id",qto.getJwtShopId());
        IPage<TradeInvoice> page = MybatisPlusUtil.pager(qto);
        IPage<TradeInvoice> invoiceIPage = iTradeInvoiceRepository.page(page,wrapper);
        if(ObjectUtils.isEmpty(invoiceIPage) && ObjectUtils.isEmpty(invoiceIPage.getRecords())){
            return new PageData<>();
        }
        return MybatisPlusUtil.toPageData(qto, PCMerchTradeInvoiceListVO.ListVO.class, page);

    }

    @Override
    public Boolean issueInvoice(PCMerchTradeInvoiceQTO.IssueQTO qto) {
        TradeInvoice tradeInvoice=iTradeInvoiceRepository.getById(qto.getId());
        tradeInvoice.setInvoiceNumber(qto.getInvoiceNumber());
        tradeInvoice.setInvoiceStatus(TradeInvoiceEnum.已开具.getCode());
        tradeInvoice.setUdate(LocalDateTime.now());
        iTradeInvoiceRepository.updateById(tradeInvoice);
        return true;
    }

    @Override
    public Boolean mailInvoice(PCMerchTradeInvoiceQTO.MailQTO qto) {
        TradeInvoice tradeInvoice=iTradeInvoiceRepository.getById(qto.getId());
        tradeInvoice.setLogisticsCode(qto.getLogisticsCode());
        tradeInvoice.setExpressNumber(qto.getExpressNumber());
        tradeInvoice.setInvoiceStatus(TradeInvoiceEnum.已邮寄.getCode());
        iTradeInvoiceRepository.updateById(tradeInvoice);
        return true;
    }

    @Override
    public void saveInvReqData(PCMerchTradeInvoiceDTO.DTO dto) {
        TradeInvoice tradeInvoice=new TradeInvoice();
        BeanCopyUtils.copyProperties(dto,tradeInvoice);
        tradeInvoice.setInvoiceStatus(TradeInvoiceEnum.待开具.getCode());
        iTradeInvoiceRepository.save(tradeInvoice);
    }
}
