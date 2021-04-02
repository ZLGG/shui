package com.gs.lshly.biz.support.trade.service.bbb.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.TradeInvoiceAddress;
import com.gs.lshly.biz.support.trade.repository.ITradeInvoiceAddressRepository;
import com.gs.lshly.biz.support.trade.service.bbb.pc.IPCBbbTradeInvoiceAddressService;
import com.gs.lshly.common.enums.TradeInvoiceAddressEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.PCBbbTradeInvoiceAddressDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbTradeInvoiceAddressQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbTradeInvoiceAddressVO;
import org.apache.commons.lang3.StringUtils;
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
* @since 2020-12-24
*/
@Component
public class PCBbbTradeInvoiceAddressServiceImpl implements IPCBbbTradeInvoiceAddressService {

    @Autowired
    private ITradeInvoiceAddressRepository repository;

    @Override
    public PageData<PCBbbTradeInvoiceAddressVO.ListVO> pageData(PCBbbTradeInvoiceAddressQTO.QTO qto) {
        QueryWrapper<TradeInvoiceAddress> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id" , qto.getJwtUserId());
        wrapper.orderByDesc("cdate");
        IPage<TradeInvoiceAddress> page = MybatisPlusUtil.pager(qto);
        IPage<TradeInvoiceAddress> iPage = repository.page(page, wrapper);
        if(ObjectUtils.isEmpty(iPage) || ObjectUtils.isEmpty(iPage.getRecords())){
            return new PageData<>();
        }
        return MybatisPlusUtil.toPageData(qto, PCBbbTradeInvoiceAddressVO.ListVO.class, page);
    }

    @Override
    public void addTradeInvoiceAddress(PCBbbTradeInvoiceAddressDTO.ETO eto) {
        if (StringUtils.isBlank(eto.getJwtUserId())){
            throw new BusinessException("未登录！！！");
        }
        TradeInvoiceAddress tradeInvoiceAddress = new TradeInvoiceAddress();
        BeanUtils.copyProperties(eto, tradeInvoiceAddress);
        tradeInvoiceAddress.setUserId(eto.getJwtUserId());
        tradeInvoiceAddress.setUserName(eto.getJwtUserName());
        //封装默认地址
        packIsdefault(eto.getIsDefault(),eto.getJwtUserId());
        repository.save(tradeInvoiceAddress);
    }


    @Override
    public void deleteTradeInvoiceAddress(PCBbbTradeInvoiceAddressDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }

    @Override
    public void editTradeInvoiceAddress(PCBbbTradeInvoiceAddressDTO.ETO eto) {
        TradeInvoiceAddress tradeInvoiceAddress = new TradeInvoiceAddress();
        BeanUtils.copyProperties(eto, tradeInvoiceAddress);
        //封装默认地址
        packIsdefault(eto.getIsDefault(),eto.getJwtUserId());
        tradeInvoiceAddress.setUdate(LocalDateTime.now());
        repository.updateById(tradeInvoiceAddress);
    }

    @Override
    public PCBbbTradeInvoiceAddressVO.DetailVO detailTradeInvoiceAddress(PCBbbTradeInvoiceAddressDTO.IdDTO dto) {
        TradeInvoiceAddress tradeInvoiceAddress = repository.getById(dto.getId());
        PCBbbTradeInvoiceAddressVO.DetailVO detailVo = new PCBbbTradeInvoiceAddressVO.DetailVO();
        if(ObjectUtils.isEmpty(tradeInvoiceAddress)){
            return detailVo;
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
