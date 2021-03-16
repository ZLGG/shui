package com.gs.lshly.biz.support.trade.service.bbb.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.TradeInvoiceAddress;
import com.gs.lshly.biz.support.trade.repository.ITradeInvoiceAddressRepository;
import com.gs.lshly.biz.support.trade.service.bbb.h5.IH5BbbTradeInvoiceAddressService;
import com.gs.lshly.common.enums.TradeInvoiceAddressEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.H5BbbTradeInvoiceAddressDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.H5BbbTradeInvoiceAddressQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.H5BbbTradeInvoiceAddressVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.PCBbbTradeInvoiceAddressDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

import java.time.LocalDateTime;


/**
* <p>
*  服务实现类
* </p>
* @author zst
* @since 2021-01-15
*/
@Component
public class H5BbbTradeInvoiceAddressServiceImpl implements IH5BbbTradeInvoiceAddressService {

    @Autowired
    private ITradeInvoiceAddressRepository repository;

    @Override
    public PageData<H5BbbTradeInvoiceAddressVO.ListVO> pageData(H5BbbTradeInvoiceAddressQTO.IdQTO qto) {
        QueryWrapper<TradeInvoiceAddress> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",qto.getJwtUserId());
        IPage<TradeInvoiceAddress> page = MybatisPlusUtil.pager(qto);
        IPage<TradeInvoiceAddress> invoiceAddressIPage = repository.page(page, wrapper);
        if(ObjectUtils.isEmpty(invoiceAddressIPage) && ObjectUtils.isEmpty(invoiceAddressIPage.getRecords())){
            return new PageData<>();
        }
        return MybatisPlusUtil.toPageData(qto, H5BbbTradeInvoiceAddressVO.ListVO.class, page);
    }

    @Override
    public void addTradeInvoiceAddress(H5BbbTradeInvoiceAddressDTO.AddETO eto) {
        TradeInvoiceAddress tradeInvoiceAddress = new TradeInvoiceAddress();
        tradeInvoiceAddress.setUserId(eto.getJwtUserId());
        tradeInvoiceAddress.setUserName(eto.getJwtUserName());
        tradeInvoiceAddress.setIsDefault(TradeInvoiceAddressEnum.普通地址.getCode());
        BeanUtils.copyProperties(eto, tradeInvoiceAddress);
        repository.save(tradeInvoiceAddress);
    }


    @Override
    public void deleteTradeInvoiceAddress(H5BbbTradeInvoiceAddressDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editTradeInvoiceAddress(H5BbbTradeInvoiceAddressDTO.AddETO eto) {
        TradeInvoiceAddress tradeInvoiceAddress = new TradeInvoiceAddress();
        tradeInvoiceAddress.setUserId(eto.getJwtUserId());
        tradeInvoiceAddress.setUserName(eto.getJwtUserName());
        BeanUtils.copyProperties(eto, tradeInvoiceAddress);
        repository.updateById(tradeInvoiceAddress);
    }

    @Override
    public H5BbbTradeInvoiceAddressVO.DetailVO detailTradeInvoiceAddress(H5BbbTradeInvoiceAddressDTO.IdDTO dto) {
        TradeInvoiceAddress tradeInvoiceAddress = repository.getById(dto.getId());
        H5BbbTradeInvoiceAddressVO.DetailVO detailVo = new H5BbbTradeInvoiceAddressVO.DetailVO();
        if(ObjectUtils.isEmpty(tradeInvoiceAddress)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(tradeInvoiceAddress, detailVo);
        return detailVo;
    }

    @Override
    public void updateByIsDefault(PCBbbTradeInvoiceAddressDTO.IsDefaultDTO eto) {
        TradeInvoiceAddress tradeInvoiceAddress = repository.getById(eto.getId());
        BeanUtils.copyProperties(eto, tradeInvoiceAddress);
        tradeInvoiceAddress.setUdate(LocalDateTime.now());
        //封装默认地址
        packIsdefault(eto.getIsDefault(),eto.getJwtUserId());

        repository.updateById(tradeInvoiceAddress);
    }

    private void packIsdefault(Integer isDefault ,String userId){
        if(TradeInvoiceAddressEnum.默认地址.getCode().equals(isDefault)){
            QueryWrapper<TradeInvoiceAddress> wrapper = MybatisPlusUtil.query();
            wrapper.eq("user_id",userId)
                    .eq("is_default",isDefault);
            TradeInvoiceAddress repositoryOne = repository.getOne(wrapper);
            if(repositoryOne != null){
                repositoryOne.setIsDefault(TradeInvoiceAddressEnum.普通地址.getCode());
                repository.updateById(repositoryOne);
            }
        }
    }

}
