package com.gs.lshly.biz.support.trade.service.bbb.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.TradeInvoice;
import com.gs.lshly.biz.support.trade.entity.TradeInvoiceAddress;
import com.gs.lshly.biz.support.trade.repository.ITradeInvoiceAddressRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeInvoiceRepository;
import com.gs.lshly.biz.support.trade.service.bbb.pc.IPCBbbTradeInvoiceService;
import com.gs.lshly.common.enums.TradeInvoiceAddressEnum;
import com.gs.lshly.common.enums.TradeInvoiceEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.PCBbbTradeInvoiceDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbTradeInvoiceQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbTradeInvoiceVO;
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
public class PCBbbTradeInvoiceServiceImpl implements IPCBbbTradeInvoiceService {

    @Autowired
    private ITradeInvoiceRepository repository;
    @Autowired
    private ITradeInvoiceAddressRepository iTradeInvoiceAddressRepository;

    @Override
    public PageData<PCBbbTradeInvoiceVO.ListVO> pageData(PCBbbTradeInvoiceQTO.QTO qto) {
        QueryWrapper<TradeInvoice> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id" , qto.getJwtUserId());
        IPage<TradeInvoice> page = MybatisPlusUtil.pager(qto);
        IPage<TradeInvoice> iPage = repository.page(page, wrapper);
        if(ObjectUtils.isEmpty(iPage) || ObjectUtils.isEmpty(iPage.getRecords())){
            return new PageData<>();
        }
        return MybatisPlusUtil.toPageData(qto, PCBbbTradeInvoiceVO.ListVO.class, page);
    }

    @Override
    public void addTradeInvoice(PCBbbTradeInvoiceDTO.ETO eto) {
        TradeInvoice tradeInvoice = new TradeInvoice();
        BeanUtils.copyProperties(eto, tradeInvoice);
        //封装默认发票
        packIsdefault(eto.getIsDefault(),eto.getJwtUserId());
        tradeInvoice.setUserId(eto.getJwtUserId());
        tradeInvoice.setUserName(eto.getJwtUserName());
        tradeInvoice.setInvoiceStatus(TradeInvoiceEnum.待开具.getCode());

        repository.save(tradeInvoice);
    }

    @Override
    public void deleteTradeInvoice(PCBbbTradeInvoiceDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }

    @Override
    public void editTradeInvoice(PCBbbTradeInvoiceDTO.ETO eto) {
        TradeInvoice tradeInvoice = new TradeInvoice();
        BeanUtils.copyProperties(eto, tradeInvoice);
        //封装默认发票
        packIsdefault(eto.getIsDefault(),eto.getJwtUserId());
        tradeInvoice.setUdate(LocalDateTime.now());

        repository.updateById(tradeInvoice);
    }

    @Override
    public PCBbbTradeInvoiceVO.DetailVO detailTradeInvoice(PCBbbTradeInvoiceDTO.IdDTO dto) {
        TradeInvoice tradeInvoice = repository.getById(dto.getId());
        PCBbbTradeInvoiceVO.DetailVO detailVo = new PCBbbTradeInvoiceVO.DetailVO();
        BeanUtils.copyProperties(tradeInvoice, detailVo);
        if(ObjectUtils.isEmpty(tradeInvoice)){
            throw new BusinessException("没有数据");
        }

        return detailVo;
    }

    @Override
    public void updateByIsDefault(PCBbbTradeInvoiceDTO.IsDefaultDTO eto) {
        TradeInvoice tradeInvoice = repository.getById(eto.getId());
        BeanUtils.copyProperties(eto, tradeInvoice);
        //封装默认发票
        packIsdefault(eto.getIsDefault(),eto.getJwtUserId());
        tradeInvoice.setUdate(LocalDateTime.now());

        repository.updateById(tradeInvoice);
    }

    @Override
    public PCBbbTradeInvoiceVO.ApplyInvoiceVO applyInvoiceList(PCBbbTradeInvoiceQTO.QTO qto) {
        PCBbbTradeInvoiceVO.ApplyInvoiceVO applyInvoiceVO = new PCBbbTradeInvoiceVO.ApplyInvoiceVO();
        QueryWrapper<TradeInvoice> tradeInvoiceQueryWrapper = new QueryWrapper<>();
        tradeInvoiceQueryWrapper.eq("user_id" , qto.getJwtUserId());
        tradeInvoiceQueryWrapper.eq("is_default",TradeInvoiceEnum.默认发票.getCode());
        TradeInvoice tradeInvoice= repository.getOne(tradeInvoiceQueryWrapper);
        PCBbbTradeInvoiceVO.ListVO listVO = new PCBbbTradeInvoiceVO.ListVO();
        if(ObjectUtils.isNotEmpty(tradeInvoice)){
            //封装默认发票数据
            packInvoiceDate(tradeInvoice,listVO);
            applyInvoiceVO.setInvoiceDate(listVO);
        }
        QueryWrapper<TradeInvoiceAddress> tradeInvoiceAddressQueryWrapper = new QueryWrapper<>();
        tradeInvoiceAddressQueryWrapper.eq("user_id" , qto.getJwtUserId());
        tradeInvoiceAddressQueryWrapper.eq("is_default",TradeInvoiceAddressEnum.默认地址.getCode());
        TradeInvoiceAddress tradeInvoiceAddress= iTradeInvoiceAddressRepository.getOne(tradeInvoiceAddressQueryWrapper);
        PCBbbTradeInvoiceVO.AddressDateVO addressDateVO = new PCBbbTradeInvoiceVO.AddressDateVO();
        if(ObjectUtils.isNotEmpty(tradeInvoiceAddress)){
            //封装默认地址数据
            packInvoiceAddressDate(tradeInvoiceAddress,addressDateVO);
            applyInvoiceVO.setAddressDateVO(addressDateVO);
        }
        return applyInvoiceVO;
    }

    private void packInvoiceDate(TradeInvoice tradeInvoice,PCBbbTradeInvoiceVO.ListVO listVO){
        listVO.setId(tradeInvoice.getId());
        listVO.setShopId(tradeInvoice.getShopId());
        listVO.setTradeId(tradeInvoice.getTradeId());
        listVO.setUserId(tradeInvoice.getUserId());
        listVO.setUserName(tradeInvoice.getUserName());
        listVO.setInvoiceStatus(tradeInvoice.getInvoiceStatus());
        listVO.setFirmName(tradeInvoice.getFirmName());
        listVO.setTaxNumber(tradeInvoice.getTaxNumber());
        listVO.setInvoiceRise(tradeInvoice.getInvoiceRise());
        listVO.setRegisterAddress(tradeInvoice.getRegisterAddress());
        listVO.setOpeningBank(tradeInvoice.getOpeningBank());
        listVO.setAccountNumber(tradeInvoice.getAccountNumber());
        listVO.setPhone(tradeInvoice.getPhone());
        listVO.setIsDefault(tradeInvoice.getIsDefault());
    }

    private void packInvoiceAddressDate(TradeInvoiceAddress tradeInvoiceAddress,PCBbbTradeInvoiceVO.AddressDateVO listVO){
        listVO.setId(tradeInvoiceAddress.getId());
        listVO.setUserId(tradeInvoiceAddress.getUserId());
        listVO.setUserName(tradeInvoiceAddress.getUserName());
        listVO.setPostalCode(tradeInvoiceAddress.getPostalCode());
        listVO.setProvince(tradeInvoiceAddress.getProvince());
        listVO.setCity(tradeInvoiceAddress.getCity());
        listVO.setCounty(tradeInvoiceAddress.getCounty());
        listVO.setStreet(tradeInvoiceAddress.getStreet());
        listVO.setPostalCode(tradeInvoiceAddress.getPostalCode());
        listVO.setConsigneeName(tradeInvoiceAddress.getConsigneeName());
        listVO.setConsigneePhone(tradeInvoiceAddress.getConsigneePhone());
        listVO.setIsDefault(tradeInvoiceAddress.getIsDefault());
    }


    @Override
    public void updateByIsDefaultList(PCBbbTradeInvoiceDTO.UpdateByIsDefaultIsDefaultDTO eto) {
        QueryWrapper<TradeInvoice> tradeInvoiceQueryWrapper = new QueryWrapper<>();
        tradeInvoiceQueryWrapper.eq("user_id" , eto.getJwtUserId());
        tradeInvoiceQueryWrapper.eq("is_default",TradeInvoiceEnum.默认发票.getCode());
        TradeInvoice tradeInvoice= repository.getOne(tradeInvoiceQueryWrapper);
        if(ObjectUtils.isNotEmpty(tradeInvoice)){
            tradeInvoice.setIsDefault(TradeInvoiceEnum.普通发票.getCode());
            repository.updateById(tradeInvoice);
        }
        TradeInvoice repositoryById = repository.getById(eto.getInvoiceId());
        if(ObjectUtils.isNotEmpty(repositoryById)){
            repositoryById.setIsDefault(TradeInvoiceEnum.默认发票.getCode());
            repositoryById.setInvoiceStatus(TradeInvoiceEnum.待开具.getCode());
            repository.updateById(repositoryById);
        }

        QueryWrapper<TradeInvoiceAddress> tradeInvoiceAddressQueryWrapper = new QueryWrapper<>();
        tradeInvoiceAddressQueryWrapper.eq("user_id" , eto.getJwtUserId());
        tradeInvoiceAddressQueryWrapper.eq("is_default",TradeInvoiceAddressEnum.默认地址.getCode());
        TradeInvoiceAddress tradeInvoiceAddress= iTradeInvoiceAddressRepository.getOne(tradeInvoiceAddressQueryWrapper);
        if(ObjectUtils.isNotEmpty(tradeInvoiceAddress)){
            tradeInvoiceAddress.setIsDefault(TradeInvoiceAddressEnum.普通地址.getCode());
            iTradeInvoiceAddressRepository.updateById(tradeInvoiceAddress);
        }
        TradeInvoiceAddress tradeInvoiceAddressRepositoryById = iTradeInvoiceAddressRepository.getById(eto.getInvoiceId());
        if(ObjectUtils.isNotEmpty(tradeInvoiceAddressRepositoryById)){
            tradeInvoiceAddressRepositoryById.setIsDefault(TradeInvoiceAddressEnum.默认地址.getCode());
            iTradeInvoiceAddressRepository.updateById(tradeInvoiceAddressRepositoryById);
        }

    }



    private void packIsdefault(Integer isDefault ,String userId){
        if(TradeInvoiceEnum.默认发票.getCode().equals(isDefault)){
            QueryWrapper<TradeInvoice> wrapper = MybatisPlusUtil.query();
            wrapper.eq("user_id",userId)
                    .eq("is_default",isDefault);
            TradeInvoice repositoryOne = repository.getOne(wrapper);
            if(repositoryOne != null){
                repositoryOne.setIsDefault(TradeInvoiceEnum.普通发票.getCode());
                repository.updateById(repositoryOne);
            }
        }
    }

}
