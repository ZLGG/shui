package com.gs.lshly.biz.support.trade.service.bbb.pc.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.*;
import com.gs.lshly.biz.support.trade.repository.*;
import com.gs.lshly.biz.support.trade.service.bbb.pc.IPCBbbTradeInvoiceService;
import com.gs.lshly.common.enums.TradeInvoiceAddressEnum;
import com.gs.lshly.common.enums.TradeInvoiceEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.PCBbbTradeInvoiceDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbTradeInvoiceQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbTradeInvoiceVO;
import com.gs.lshly.common.struct.common.CommonUserVO;
import com.gs.lshly.rpc.api.common.ICommonUserRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


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
    private ITradeRepository iTradeRepository;
    @Autowired
    private ITradeGoodsRepository iTradeGoodsRepository;
    @Autowired
    private ITradeInvoiceTemplateRepository iTradeInvoiceTemplateRepository;
    @Autowired
    private ITradeInvoiceAddressRepository iTradeInvoiceAddressRepository;
    @DubboReference
    private ICommonUserRpc iCommonUserRpc;

    @Override
    public PageData<PCBbbTradeInvoiceVO.ListVO> pageData(PCBbbTradeInvoiceQTO.QTO qto) {
        QueryWrapper<TradeInvoiceTemplate> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id" , qto.getJwtUserId());
        wrapper.orderByDesc("cdate");
        IPage<TradeInvoiceTemplate> page = MybatisPlusUtil.pager(qto);
        iTradeInvoiceTemplateRepository.page(page, wrapper);
        if(ObjectUtils.isEmpty(page) || ObjectUtils.isEmpty(page.getRecords())){
            return new PageData<>();
        }
        return MybatisPlusUtil.toPageData(qto, PCBbbTradeInvoiceVO.ListVO.class, page);
    }

    @Override
    public void addTradeInvoice(PCBbbTradeInvoiceDTO.ETO eto) {
        TradeInvoiceTemplate tradeInvoiceTemplate = new TradeInvoiceTemplate();
        BeanUtils.copyProperties(eto, tradeInvoiceTemplate);
        //封装默认发票
        if(eto.getIsDefault().equals(TradeInvoiceEnum.默认发票.getCode())){
            packIsdefault(eto.getIsDefault(),eto.getJwtUserId());
            tradeInvoiceTemplate.setIsDefault(TradeInvoiceEnum.默认发票.getCode());
        }else {
            QueryWrapper<TradeInvoiceTemplate> wrapper = new QueryWrapper<>();
            if(iTradeInvoiceTemplateRepository.count(wrapper.eq("user_id",eto.getJwtUserId()))==0){
                tradeInvoiceTemplate.setIsDefault(TradeInvoiceEnum.默认发票.getCode());
            }else {
                tradeInvoiceTemplate.setIsDefault(TradeInvoiceEnum.普通发票.getCode());
            }

        }
        tradeInvoiceTemplate.setUserId(eto.getJwtUserId())
                .setUserName(eto.getJwtUserName());
        iTradeInvoiceTemplateRepository.save(tradeInvoiceTemplate);
    }

    @Override
    public void deleteTradeInvoice(PCBbbTradeInvoiceDTO.IdDTO dto) {
        iTradeInvoiceTemplateRepository.removeById(dto.getId());
    }

    @Override
    public void editTradeInvoice(PCBbbTradeInvoiceDTO.ETO eto) {
        TradeInvoiceTemplate tradeInvoiceTemplate = new TradeInvoiceTemplate();
        BeanUtils.copyProperties(eto, tradeInvoiceTemplate);
        //封装默认发票
        if(eto.getIsDefault().equals(TradeInvoiceEnum.默认发票.getCode())){
            packIsdefault(eto.getIsDefault(),eto.getJwtUserId());
            tradeInvoiceTemplate.setIsDefault(TradeInvoiceEnum.默认发票.getCode());
        }else {
            tradeInvoiceTemplate.setIsDefault(TradeInvoiceEnum.普通发票.getCode());
        }
        iTradeInvoiceTemplateRepository.updateById(tradeInvoiceTemplate);
    }

    @Override
    public PCBbbTradeInvoiceVO.DetailVO detailTradeInvoice(PCBbbTradeInvoiceDTO.IdDTO dto) {
        TradeInvoiceTemplate tradeInvoiceTemplate = iTradeInvoiceTemplateRepository.getById(dto.getId());
        PCBbbTradeInvoiceVO.DetailVO detailVo = new PCBbbTradeInvoiceVO.DetailVO();
        BeanUtils.copyProperties(tradeInvoiceTemplate, detailVo);
        if(ObjectUtils.isEmpty(detailVo)){
            return new PCBbbTradeInvoiceVO.DetailVO();
        }
        return detailVo;
    }

    @Override
    public void updateByIsDefault(PCBbbTradeInvoiceDTO.IsDefaultDTO eto) {
        TradeInvoiceTemplate tradeInvoiceTemplate = iTradeInvoiceTemplateRepository.getById(eto.getId());
        BeanUtils.copyProperties(eto, tradeInvoiceTemplate);
        //封装默认发票
        packIsdefault(eto.getIsDefault(),eto.getJwtUserId());
        tradeInvoiceTemplate.setUdate(LocalDateTime.now());

        iTradeInvoiceTemplateRepository.updateById(tradeInvoiceTemplate);
    }


    @Override
    public PCBbbTradeInvoiceVO.ChooseVO choose(PCBbbTradeInvoiceDTO.ETO eto) {
        PCBbbTradeInvoiceVO.ChooseVO chooseVO = new PCBbbTradeInvoiceVO.ChooseVO();
        QueryWrapper<TradeInvoice> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id" , eto.getJwtUserId());
        Integer count = repository.count(wrapper);
        if(count==0){
            TradeInvoice tradeInvoice = new TradeInvoice();
            BeanUtils.copyProperties(eto, tradeInvoice);
            tradeInvoice.setUserId(eto.getJwtUserId());
            tradeInvoice.setUserName(eto.getJwtUserName());
            tradeInvoice.setInvoiceStatus(TradeInvoiceEnum.待开具.getCode());
            Boolean b = repository.save(tradeInvoice);
            System.out.println(b);
            if(b == true){
                QueryWrapper<TradeInvoice> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("user_id" , eto.getJwtUserId());
                queryWrapper.eq("is_default",TradeInvoiceEnum.默认发票.getCode());
                TradeInvoice tradeInvoice1= repository.getOne(queryWrapper);
                PCBbbTradeInvoiceVO.ListVO listVO = new PCBbbTradeInvoiceVO.ListVO();
                if(ObjectUtils.isNotEmpty(tradeInvoice1)){
                    //封装默认发票数据
                    packInvoiceDate(tradeInvoice1,listVO);
                    chooseVO.setInvoiceDate(listVO);
                }
            }

        }else {
            QueryWrapper<TradeInvoice> tradeInvoiceQueryWrapper = new QueryWrapper<>();
            tradeInvoiceQueryWrapper.eq("user_id" , eto.getJwtUserId());
            tradeInvoiceQueryWrapper.eq("is_default",TradeInvoiceEnum.默认发票.getCode());
            TradeInvoice tradeInvoice= repository.getOne(tradeInvoiceQueryWrapper);
            PCBbbTradeInvoiceVO.ListVO listVO = new PCBbbTradeInvoiceVO.ListVO();
            if(ObjectUtils.isNotEmpty(tradeInvoice)){
                //封装默认发票数据
                packInvoiceDate(tradeInvoice,listVO);
                chooseVO.setInvoiceDate(listVO);
            }
        }

        return chooseVO;
    }


    @Override
    public PCBbbTradeInvoiceVO.ApplyInvoiceVO applyInvoiceList(PCBbbTradeInvoiceDTO.ETO qto) {
        PCBbbTradeInvoiceVO.ApplyInvoiceVO applyInvoiceVO = new PCBbbTradeInvoiceVO.ApplyInvoiceVO();
        if(ObjectUtils.isNotEmpty(qto.getIsAdd())){
            TradeInvoice tradeInvoice = new TradeInvoice();
            BeanUtils.copyProperties(qto, tradeInvoice);
            //封装默认发票
            packIsdefault(qto.getIsDefault(),qto.getJwtUserId());
            tradeInvoice.setUserId(qto.getJwtUserId());
            tradeInvoice.setUserName(qto.getJwtUserName());
            tradeInvoice.setInvoiceStatus(TradeInvoiceEnum.待开具.getCode());

            Boolean b =repository.save(tradeInvoice);
            if(b == true){
                QueryWrapper<TradeInvoice> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("user_id" , qto.getJwtUserId());
                queryWrapper.eq("is_default",TradeInvoiceEnum.默认发票.getCode());
                TradeInvoice tradeInvoice1= repository.getOne(queryWrapper);
                PCBbbTradeInvoiceVO.ListVO listVO = new PCBbbTradeInvoiceVO.ListVO();
                if(ObjectUtils.isNotEmpty(tradeInvoice1)){
                    //封装默认发票数据
                    packInvoiceDate(tradeInvoice1,listVO);
                    applyInvoiceVO.setInvoiceDate(listVO);
                }
            }
        }

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
        listVO.setInvoiceName(tradeInvoice.getInvoiceName());
        listVO.setRegisterAddress(tradeInvoice.getRegisterAddress());
        listVO.setOpeningBank(tradeInvoice.getOpeningBank());
        listVO.setAccountNumber(tradeInvoice.getAccountNumber());
        listVO.setPhone(tradeInvoice.getPhone());
        listVO.setInvoiceContent(tradeInvoice.getInvoiceContent());
        listVO.setContactsUser(tradeInvoice.getContactsUser());
        listVO.setContactsEmail(tradeInvoice.getContactsEmail());
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
        if (ObjectUtils.isEmpty(eto.getTradeId()) || ObjectUtils.isEmpty(eto.getInvoiceId()) || ObjectUtils.isEmpty(eto.getInvoiceAddressId()) || ObjectUtils.isEmpty(eto.getEmailNum())){
            throw new BusinessException("没有模版");
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
    public void updateByAddressIsDefault(PCBbbTradeInvoiceDTO.IsDefaultDTO eto) {

        TradeInvoiceAddress tradeInvoiceAddress = iTradeInvoiceAddressRepository.getById(eto.getId());
        BeanUtils.copyProperties(eto, tradeInvoiceAddress);
        //封装默认发票
        packAddressIsdefault(eto.getIsDefault(),eto.getJwtUserId());
        tradeInvoiceAddress.setIsDefault(TradeInvoiceEnum.默认发票.getCode());
        iTradeInvoiceAddressRepository.updateById(tradeInvoiceAddress);

    }

    @Override
    public PCBbbTradeInvoiceVO.clickBillingVO clickBilling(PCBbbTradeInvoiceDTO.clickBilingDTO dto) {
        //发票模版选择
        //优先获取订单中带的发票模版ID
        Trade trade = iTradeRepository.getById(ObjectUtils.isNotEmpty(dto.getTradeId()) ? dto.getTradeId() : "");
        if (ObjectUtils.isEmpty(trade)){
            throw new BusinessException("没有查询到此订单");
        }
        PCBbbTradeInvoiceVO.clickBillingVO clickBillingVO = new PCBbbTradeInvoiceVO.clickBillingVO();
        PCBbbTradeInvoiceVO.clickBillingVO.clickBillingInvoiceVO clickBillingInvoiceVO = new PCBbbTradeInvoiceVO.clickBillingVO.clickBillingInvoiceVO();
        PCBbbTradeInvoiceVO.clickBillingVO.clickBillingInvoiceAddressVO clickBillingInvoiceAddressVO = new PCBbbTradeInvoiceVO.clickBillingVO.clickBillingInvoiceAddressVO();
        //判断订单中有没有发票模版ID
        if (ObjectUtils.isNotEmpty(trade.getInvoiceId())){
            //有地址
            TradeInvoiceTemplate tradeInvoiceTemplate = iTradeInvoiceTemplateRepository.getById(trade.getInvoiceId());
            if (ObjectUtils.isNotEmpty(tradeInvoiceTemplate)){
                clickBillingInvoiceVO.setId(tradeInvoiceTemplate.getId()).
                        setInvoiceName(tradeInvoiceTemplate.getInvoiceName()).
                        setInvoiceRise(tradeInvoiceTemplate.getInvoiceRise());
                clickBillingVO.setInvoiceInfo(clickBillingInvoiceVO);
            }
        }else {
            //订单中没有发票模版，获取用户的默认发票模版
            QueryWrapper<TradeInvoiceTemplate> query = MybatisPlusUtil.query();
            query.and(i->i.eq(ObjectUtils.isNotEmpty(dto.getJwtUserId()),"user_id",dto.getJwtUserId()));
            query.and(i->i.eq("is_default",TradeInvoiceEnum.默认发票.getCode()));
            query.last("limit 0,1");
            TradeInvoiceTemplate one = iTradeInvoiceTemplateRepository.getOne(query);
            if (ObjectUtils.isNotEmpty(one)){
                clickBillingInvoiceVO.setId(one.getId()).
                        setInvoiceName(one.getInvoiceName()).
                        setInvoiceRise(one.getInvoiceRise());
                clickBillingVO.setInvoiceInfo(clickBillingInvoiceVO);
            }
        }
        //判断订单中有没有发票地址ID
        if (ObjectUtils.isNotEmpty(trade.getInvoiceAddressId())){
            //订单中有地址ID
            TradeInvoiceAddress tradeInvoiceAddress = iTradeInvoiceAddressRepository.getById(trade.getInvoiceAddressId());
            if (ObjectUtils.isNotEmpty(tradeInvoiceAddress)){
                BeanUtils.copyProperties(tradeInvoiceAddress,clickBillingInvoiceAddressVO);
                clickBillingVO.setInvoicAddressInfo(clickBillingInvoiceAddressVO);
            }

        }else {
            QueryWrapper<TradeInvoiceAddress> query = MybatisPlusUtil.query();
            query.and(i->i.eq(ObjectUtils.isNotEmpty(dto.getJwtUserId()),"user_id",dto.getJwtUserId()));
            query.and(i->i.eq("is_default",TradeInvoiceEnum.默认发票.getCode()));
            query.last("limit 0,1");
            TradeInvoiceAddress one = iTradeInvoiceAddressRepository.getOne(query);
            if (ObjectUtils.isNotEmpty(one)){
                BeanUtils.copyProperties(one,clickBillingInvoiceAddressVO);
                clickBillingVO.setInvoicAddressInfo(clickBillingInvoiceAddressVO);
            }
        }

        return clickBillingVO;
    }


    private void packAddressIsdefault(Integer isDefault ,String userId){
        if(TradeInvoiceEnum.默认发票.getCode().equals(isDefault)){
            //将当前的发票设置为默认发票
            //清除此用户原来的默认发票
            QueryWrapper<TradeInvoiceAddress> wrapper = MybatisPlusUtil.query();
            wrapper.eq("user_id",userId)
                    .eq("is_default",isDefault);
            TradeInvoiceAddress one = iTradeInvoiceAddressRepository.getOne(wrapper);
            if(one != null){
                one.setIsDefault(TradeInvoiceEnum.普通发票.getCode());
                iTradeInvoiceAddressRepository.updateById(one);
            }
        }
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

}
