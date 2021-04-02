package com.gs.lshly.biz.support.trade.service.bbb.h5.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.*;
import com.gs.lshly.biz.support.trade.repository.*;
import com.gs.lshly.biz.support.trade.service.bbb.h5.IH5BbbTradeInvoiceService;
import com.gs.lshly.common.enums.TradeInvoiceAddressEnum;
import com.gs.lshly.common.enums.TradeInvoiceEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.H5BbbTradeInvoiceDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.H5BbbTradeInvoiceQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.H5BbbTradeInvoiceVO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbTradeInvoiceVO;
import com.gs.lshly.common.struct.common.CommonUserVO;
import com.gs.lshly.rpc.api.common.ICommonUserRpc;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


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
    private ITradeRepository iTradeRepository;
    @Autowired
    private ITradeGoodsRepository iTradeGoodsRepository;
    @Autowired
    private ITradeInvoiceAddressRepository iTradeInvoiceAddressRepository;
    @Autowired
    private ITradeInvoiceTemplateRepository iTradeInvoiceTemplateRepository;
    @DubboReference
    private ICommonUserRpc iCommonUserRpc;

    @Override
    public PageData<H5BbbTradeInvoiceVO.ListVO> pageData(H5BbbTradeInvoiceQTO.IdQTO qto) {
        QueryWrapper<TradeInvoiceTemplate> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id" , qto.getJwtUserId());
        wrapper.orderByDesc("cdate");
        IPage<TradeInvoiceTemplate> page = MybatisPlusUtil.pager(qto);
        iTradeInvoiceTemplateRepository.page(page, wrapper);
        if(ObjectUtils.isEmpty(page) || ObjectUtils.isEmpty(page.getRecords())){
            return new PageData<>();
        }
        return MybatisPlusUtil.toPageData(qto, H5BbbTradeInvoiceVO.ListVO.class, page);
    }

    @Override
    public void addTradeInvoice(H5BbbTradeInvoiceDTO.AddETO eto) {
        if (StringUtils.isBlank(eto.getJwtUserId())){
            throw new BusinessException("未登录！！！");
        }
        if (null == eto){
            throw new BusinessException("参数不能为空！！");
        }
        if (ObjectUtils.isEmpty(eto.getInvoiceRise())){
            throw new BusinessException("发票类型不能为空！！");
        }
        //保存发票
        TradeInvoiceTemplate tradeInvoiceTemplate = new TradeInvoiceTemplate();
        BeanUtils.copyProperties(eto,tradeInvoiceTemplate);
        tradeInvoiceTemplate.setUserId(eto.getJwtUserId());
       //如果将新增的发票设为默认先判断该用户是否有设置默认发票，若有则先将原先的默认发票取消掉
        if (ObjectUtils.isNotEmpty(eto.getIsDefault()) && eto.getIsDefault().equals(TradeInvoiceEnum.默认发票.getCode())){
           QueryWrapper<TradeInvoiceTemplate> wrapper = MybatisPlusUtil.query();
           wrapper.eq("user_id",eto.getJwtUserId());
           wrapper.eq("is_default",TradeInvoiceEnum.默认发票.getCode());
           int count = iTradeInvoiceTemplateRepository.count(wrapper);
           if (count > 0){
               //取消掉前面的默认发票
               TradeInvoiceTemplate invoice = new TradeInvoiceTemplate();
               invoice.setIsDefault(TradeInvoiceEnum.普通发票.getCode());

               UpdateWrapper<TradeInvoiceTemplate> wrapper1 = MybatisPlusUtil.update();
               wrapper1.eq("user_id",eto.getJwtUserId());
               iTradeInvoiceTemplateRepository.update(invoice,wrapper1);
           }
            tradeInvoiceTemplate.setIsDefault(TradeInvoiceEnum.默认发票.getCode());
        }else {
            //查询用户是否有新增了发票,若没有则将第一个新增发票设为默认发票
            QueryWrapper<TradeInvoiceTemplate> wrapper = MybatisPlusUtil.query();
            wrapper.eq("user_id",eto.getJwtUserId());
            List<TradeInvoiceTemplate> invoiceList = iTradeInvoiceTemplateRepository.list(wrapper);
            if (ObjectUtils.isNotEmpty(invoiceList)){
                tradeInvoiceTemplate.setIsDefault(TradeInvoiceEnum.普通发票.getCode());
            }else {
                tradeInvoiceTemplate.setIsDefault(TradeInvoiceEnum.默认发票.getCode());
            }
        }
        iTradeInvoiceTemplateRepository.saveOrUpdate(tradeInvoiceTemplate);




    }

    private void packIsdefault(Integer isDefault ,String userId){
        if(TradeInvoiceEnum.默认发票.getCode().equals(isDefault)){
            //将当前的发票设置为默认发票
            //清除此用户原来的默认发票
            QueryWrapper<TradeInvoiceTemplate> wrapper = MybatisPlusUtil.query();
            wrapper.eq("user_id",userId)
                    .eq("is_default",isDefault);
            TradeInvoiceTemplate one = iTradeInvoiceTemplateRepository.getOne(wrapper);
            if(one != null){
                one.setIsDefault(TradeInvoiceEnum.普通发票.getCode());
                iTradeInvoiceTemplateRepository.updateById(one);
            }
        }
    }

    @Override
    public void deleteTradeInvoice(H5BbbTradeInvoiceDTO.IdDTO dto) { repository.removeById(dto.getId()); }


    @Override
    public void editTradeInvoice(H5BbbTradeInvoiceDTO.EditETO eto) {
        if(ObjectUtils.isEmpty(eto)){
            throw new BusinessException("参数异常");
        }
        TradeInvoiceTemplate tradeInvoiceTemplate = new TradeInvoiceTemplate();
        if(eto.getIsDefault().equals(TradeInvoiceEnum.默认发票.getCode())){
            QueryWrapper<TradeInvoiceTemplate> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("user_id", eto.getJwtUserId());
            wrapper1.eq("is_default", TradeInvoiceEnum.默认发票.getCode());
            TradeInvoiceTemplate tradeInvoice1 = iTradeInvoiceTemplateRepository.getOne(wrapper1);
            BeanUtils.copyProperties(eto, tradeInvoice1);
            //封装默认发票
            packIsdefault(TradeInvoiceEnum.默认发票.getCode(),eto.getJwtUserId());
            tradeInvoiceTemplate.setIsDefault(TradeInvoiceEnum.默认发票.getCode());
        }else {
            tradeInvoiceTemplate.setIsDefault(TradeInvoiceEnum.普通发票.getCode());
        }
        tradeInvoiceTemplate.setUserId(eto.getJwtUserId());
        tradeInvoiceTemplate.setUserName(eto.getJwtUserName());
        BeanUtils.copyProperties(eto, tradeInvoiceTemplate);
        iTradeInvoiceTemplateRepository.updateById(tradeInvoiceTemplate);
    }

    @Override
    public H5BbbTradeInvoiceVO.DetailVO detailTradeInvoice(H5BbbTradeInvoiceDTO.IdDTO dto) {
        TradeInvoiceTemplate tradeInvoiceTemplate = iTradeInvoiceTemplateRepository.getById(dto.getId());
        H5BbbTradeInvoiceVO.DetailVO detailVo = new H5BbbTradeInvoiceVO.DetailVO();
        if(ObjectUtils.isEmpty(tradeInvoiceTemplate)){
            return detailVo;
        }
        BeanUtils.copyProperties(tradeInvoiceTemplate, detailVo);
        return detailVo;
    }

    @Override
    public H5BbbTradeInvoiceVO.DetailVO detailInvoice(H5BbbTradeInvoiceQTO.IdQTO dto) {
        H5BbbTradeInvoiceVO.DetailVO detailVo = new H5BbbTradeInvoiceVO.DetailVO();
        QueryWrapper<TradeInvoiceTemplate> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",dto.getJwtUserId())
                    .eq("is_default",TradeInvoiceEnum.默认发票.getCode());
        TradeInvoiceTemplate tradeInvoiceTemplate = iTradeInvoiceTemplateRepository.getOne(wrapper);
        if(ObjectUtils.isEmpty(tradeInvoiceTemplate)){
            return detailVo;
        }
        BeanUtils.copyProperties(tradeInvoiceTemplate, detailVo);
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
        listVO.setInvoiceName(listVO.getInvoiceName());
        listVO.setRegisterAddress(tradeInvoice.getRegisterAddress());
        listVO.setOpeningBank(tradeInvoice.getOpeningBank());
        listVO.setAccountNumber(tradeInvoice.getAccountNumber());
        listVO.setPhone(tradeInvoice.getPhone());
        listVO.setContactsUser(tradeInvoice.getContactsUser());
        listVO.setContactsEmail(tradeInvoice.getContactsEmail());
        listVO.setInvoiceContent(tradeInvoice.getInvoiceContent());
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
        if (ObjectUtils.isEmpty(eto.getTradeId()) || ObjectUtils.isEmpty(eto.getInvoiceId()) || ObjectUtils.isEmpty(eto.getInvoiceAddressId()) || ObjectUtils.isEmpty(eto.getEmailNum())){
            throw new BusinessException("没有模版或没有传入联系人邮箱");
        }
        if (ObjectUtils.isEmpty(eto.getTradeId())){
            throw new BusinessException("请传订单号");
        }
        QueryWrapper<TradeInvoice> query = MybatisPlusUtil.query();
        query.and(i->i.eq("trade_id",eto.getTradeId()));
        List<TradeInvoice> list = repository.list(query);
        if (ObjectUtils.isNotEmpty(list)){
            throw new BusinessException("此订单已经开过发票了");
        }
        Trade trade = iTradeRepository.getById(eto.getTradeId());
        if (ObjectUtils.isEmpty(trade)){
            throw new BusinessException("没有查询到相应的订单");
        }
        TradeInvoice tradeInvoice = new TradeInvoice();
        TradeInvoiceAddress tradeInvoiceAddress = iTradeInvoiceAddressRepository.getById(eto.getInvoiceAddressId());
        if (ObjectUtils.isEmpty(tradeInvoiceAddress)){
            throw new BusinessException("没有查询到相应的发票地址模版");
        }
        BeanUtils.copyProperties(tradeInvoiceAddress,tradeInvoice);
        TradeInvoiceTemplate tradeInvoiceTemplate = iTradeInvoiceTemplateRepository.getById(eto.getInvoiceId());
        if (ObjectUtils.isEmpty(tradeInvoiceTemplate)){
            throw new BusinessException("没有查询到相应到发票模版");
        }
        BeanUtils.copyProperties(tradeInvoiceTemplate,tradeInvoice);
        CommonUserVO.DetailVO details = iCommonUserRpc.details(trade.getUserId());
        if (ObjectUtils.isNotEmpty(details)){
            tradeInvoice.setUserName(details.getUserName());
        }
        tradeInvoice.setInvoiceStatus(TradeInvoiceEnum.已开具.getCode()).
                setShopId(trade.getShopId()).
                setTradeId(trade.getId()).setUserId(trade.getUserId()).setInvoiceAmount(trade.getTradeAmount()).setContactsEmail(eto.getEmailNum());
        //封装发票内容
        PackTradeInviceContent(trade,tradeInvoice);
        tradeInvoice.setId(null);
        repository.save(tradeInvoice);
    }
    private void PackTradeInviceContent(Trade trade, TradeInvoice tradeInvoice) {
        QueryWrapper<TradeGoods> query = MybatisPlusUtil.query();
        query.and(i->i.eq("trade_id",trade.getId()));
        List<TradeGoods> list = iTradeGoodsRepository.list(query);
        if (ObjectUtils.isNotEmpty(list)){
            List<PCBbbTradeInvoiceVO.invoiceContendVO> contend=list.parallelStream().map(e ->{
                PCBbbTradeInvoiceVO.invoiceContendVO invoiceContendVO = new PCBbbTradeInvoiceVO.invoiceContendVO();
                BeanUtils.copyProperties(e,invoiceContendVO);
                return invoiceContendVO;
            }).collect(Collectors.toList());
            String contendJSON = JSONObject.toJSONString(contend);
            tradeInvoice.setInvoiceContent(contendJSON);
        }

    }


    @Override
    public H5BbbTradeInvoiceVO.DetailVO detailByTradeId(H5BbbTradeInvoiceQTO.TradeIdQTO qto) {
        if(ObjectUtils.isEmpty(qto.getTradeId())){
            throw new BusinessException("订单编号不能为空");
        }
        Trade byId = iTradeRepository.getById(qto.getTradeId());
        if (ObjectUtils.isNotEmpty(byId)){
            TradeInvoice byId1 = repository.getById(byId.getInvoiceId());
            if (ObjectUtils.isNotEmpty(byId1)){
                H5BbbTradeInvoiceVO.DetailVO detailVo = new H5BbbTradeInvoiceVO.DetailVO();
                BeanUtils.copyProperties(byId1, detailVo);
                return detailVo;
            }
        }
        return null;

    }

}
