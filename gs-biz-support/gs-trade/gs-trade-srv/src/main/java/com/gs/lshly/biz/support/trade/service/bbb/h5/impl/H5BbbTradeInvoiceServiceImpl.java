package com.gs.lshly.biz.support.trade.service.bbb.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.TradeInvoice;
import com.gs.lshly.biz.support.trade.entity.TradeInvoiceAddress;
import com.gs.lshly.biz.support.trade.repository.ITradeInvoiceAddressRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeInvoiceRepository;
import com.gs.lshly.biz.support.trade.service.bbb.h5.IH5BbbTradeInvoiceService;
import com.gs.lshly.common.enums.TradeInvoiceAddressEnum;
import com.gs.lshly.common.enums.TradeInvoiceEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.H5BbbTradeInvoiceDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.H5BbbTradeInvoiceQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.H5BbbTradeInvoiceVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;


/**
* <p>
*  服务实现类
* </p>
* @author zst
* @since 2021-01-15
*/
@Component
public class H5BbbTradeInvoiceServiceImpl implements IH5BbbTradeInvoiceService {

    @Autowired
    private ITradeInvoiceRepository repository;
    @Autowired
    private ITradeInvoiceAddressRepository iTradeInvoiceAddressRepository;

    @Override
    public PageData<H5BbbTradeInvoiceVO.ListVO> pageData(H5BbbTradeInvoiceQTO.IdQTO qto) {
        QueryWrapper<TradeInvoice> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",qto.getJwtUserId());
        IPage<TradeInvoice> page = MybatisPlusUtil.pager(qto);
        IPage<TradeInvoice> invoiceIPage = repository.page(page, wrapper);
        if(ObjectUtils.isEmpty(invoiceIPage) && ObjectUtils.isEmpty(invoiceIPage.getRecords())){
            return new PageData<>();
        }
        return MybatisPlusUtil.toPageData(qto, H5BbbTradeInvoiceVO.ListVO.class, page);
    }

    @Override
    public void addTradeInvoice(H5BbbTradeInvoiceDTO.AddETO eto) {
        QueryWrapper<TradeInvoice> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",eto.getJwtUserId());
        if(ObjectUtils.isNotEmpty(eto.getTaxNumber())){
            wrapper.eq("tax_number",eto.getTaxNumber());
        }
        TradeInvoice repositoryOne = repository.getOne(wrapper);
        if(ObjectUtils.isNotEmpty(repositoryOne)){
            throw new BusinessException("税务人识别号已存在,请输入正确税号");
        }
        TradeInvoice tradeInvoice = new TradeInvoice();
        tradeInvoice.setUserId(eto.getJwtUserId());
        tradeInvoice.setUserName(eto.getJwtUserName());
        tradeInvoice.setIsDefault(TradeInvoiceEnum.普通发票.getCode());
        tradeInvoice.setInvoiceStatus(TradeInvoiceEnum.待开具.getCode());
        BeanUtils.copyProperties(eto, tradeInvoice);
        repository.save(tradeInvoice);
    }


    @Override
    public void deleteTradeInvoice(H5BbbTradeInvoiceDTO.IdDTO dto) { repository.removeById(dto.getId()); }


    @Override
    public void editTradeInvoice(H5BbbTradeInvoiceDTO.EditETO eto) {
        TradeInvoice tradeInvoice = new TradeInvoice();
        tradeInvoice.setUserId(eto.getJwtUserId());
        tradeInvoice.setUserName(eto.getJwtUserName());
        BeanUtils.copyProperties(eto, tradeInvoice);
        repository.updateById(tradeInvoice);
    }

    @Override
    public H5BbbTradeInvoiceVO.DetailVO detailTradeInvoice(H5BbbTradeInvoiceDTO.IdDTO dto) {
        TradeInvoice tradeInvoice = repository.getById(dto.getId());
        H5BbbTradeInvoiceVO.DetailVO detailVo = new H5BbbTradeInvoiceVO.DetailVO();
        if(ObjectUtils.isEmpty(tradeInvoice)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(tradeInvoice, detailVo);
        return detailVo;
    }


    @Override
    public H5BbbTradeInvoiceVO.ApplyInvoiceVO applyInvoiceList(H5BbbTradeInvoiceQTO.QTO qto) {
        H5BbbTradeInvoiceVO.ApplyInvoiceVO applyInvoiceVO = new H5BbbTradeInvoiceVO.ApplyInvoiceVO();
        QueryWrapper<TradeInvoice> tradeInvoiceQueryWrapper = new QueryWrapper<>();
        tradeInvoiceQueryWrapper.eq("user_id" , qto.getJwtUserId());
        tradeInvoiceQueryWrapper.eq("is_default",TradeInvoiceEnum.默认发票.getCode());
        TradeInvoice tradeInvoice= repository.getOne(tradeInvoiceQueryWrapper);
        H5BbbTradeInvoiceVO.ListVO listVO = new H5BbbTradeInvoiceVO.ListVO();
        if(ObjectUtils.isNotEmpty(tradeInvoice)){
            //封装默认发票数据
            packInvoiceDate(tradeInvoice,listVO);
            applyInvoiceVO.setInvoiceDate(listVO);
        }
        QueryWrapper<TradeInvoiceAddress> tradeInvoiceAddressQueryWrapper = new QueryWrapper<>();
        tradeInvoiceAddressQueryWrapper.eq("user_id" , qto.getJwtUserId());
        tradeInvoiceAddressQueryWrapper.eq("is_default", TradeInvoiceAddressEnum.默认地址.getCode());
        TradeInvoiceAddress tradeInvoiceAddress= iTradeInvoiceAddressRepository.getOne(tradeInvoiceAddressQueryWrapper);
        H5BbbTradeInvoiceVO.AddressDateVO addressDateVO = new H5BbbTradeInvoiceVO.AddressDateVO();
        if(ObjectUtils.isNotEmpty(tradeInvoiceAddress)){
            //封装默认地址数据
            packInvoiceAddressDate(tradeInvoiceAddress,addressDateVO);
            applyInvoiceVO.setAddressDateVO(addressDateVO);
        }
        return applyInvoiceVO;
    }

    private void packInvoiceDate(TradeInvoice tradeInvoice,H5BbbTradeInvoiceVO.ListVO listVO){
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

    private void packInvoiceAddressDate(TradeInvoiceAddress tradeInvoiceAddress,H5BbbTradeInvoiceVO.AddressDateVO listVO){
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
    public void updateByIsDefaultList(H5BbbTradeInvoiceDTO.UpdateByIsDefaultIsDefaultDTO eto) {
        QueryWrapper<TradeInvoice> tradeInvoiceQueryWrapper = new QueryWrapper<>();
        tradeInvoiceQueryWrapper.eq("user_id", eto.getJwtUserId());
        tradeInvoiceQueryWrapper.eq("is_default", TradeInvoiceEnum.默认发票.getCode());
        TradeInvoice tradeInvoice = repository.getOne(tradeInvoiceQueryWrapper);
        if (ObjectUtils.isNotEmpty(tradeInvoice)) {
            tradeInvoice.setIsDefault(TradeInvoiceEnum.普通发票.getCode());
            repository.updateById(tradeInvoice);
        }
        TradeInvoice repositoryById = repository.getById(eto.getInvoiceId());
        if (ObjectUtils.isNotEmpty(repositoryById)) {
            repositoryById.setIsDefault(TradeInvoiceEnum.默认发票.getCode());
            repositoryById.setTradeId(eto.getTradeId());
            repositoryById.setInvoiceStatus(TradeInvoiceEnum.待开具.getCode());
            repository.updateById(repositoryById);
        }

        QueryWrapper<TradeInvoiceAddress> tradeInvoiceAddressQueryWrapper = new QueryWrapper<>();
        tradeInvoiceAddressQueryWrapper.eq("user_id", eto.getJwtUserId());
        tradeInvoiceAddressQueryWrapper.eq("is_default", TradeInvoiceAddressEnum.默认地址.getCode());
        TradeInvoiceAddress tradeInvoiceAddress = iTradeInvoiceAddressRepository.getOne(tradeInvoiceAddressQueryWrapper);
        if (ObjectUtils.isNotEmpty(tradeInvoiceAddress)) {
            tradeInvoiceAddress.setIsDefault(TradeInvoiceAddressEnum.普通地址.getCode());
            iTradeInvoiceAddressRepository.updateById(tradeInvoiceAddress);
        }
        TradeInvoiceAddress tradeInvoiceAddressRepositoryById = iTradeInvoiceAddressRepository.getById(eto.getInvoiceId());
        if (ObjectUtils.isNotEmpty(tradeInvoiceAddressRepositoryById)) {
            tradeInvoiceAddressRepositoryById.setIsDefault(TradeInvoiceAddressEnum.默认地址.getCode());
            tradeInvoiceAddressRepositoryById.setInvoiceId(repositoryById.getId());
            iTradeInvoiceAddressRepository.updateById(tradeInvoiceAddressRepositoryById);
        }
    }


    @Override
    public H5BbbTradeInvoiceVO.DetailVO detailByTradeId(H5BbbTradeInvoiceQTO.TradeIdQTO qto) {
        QueryWrapper<TradeInvoice> wrapper = MybatisPlusUtil.query();
        if(ObjectUtils.isEmpty(qto.getTradeId())){
            throw new BusinessException("订单编号不能为空");
        }
        wrapper.eq("trade_id",qto.getTradeId());
        TradeInvoice tradeInvoice = repository.getOne(wrapper);
        if(ObjectUtils.isEmpty(tradeInvoice)){
            throw new BusinessException("没有数据");
        }
        H5BbbTradeInvoiceVO.DetailVO detailVo = new H5BbbTradeInvoiceVO.DetailVO();
        BeanUtils.copyProperties(tradeInvoice, detailVo);
        return detailVo;
    }

}
