package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.Trade;
import com.gs.lshly.biz.support.trade.entity.TradeDelivery;
import com.gs.lshly.biz.support.trade.repository.ITradeDeliveryRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchTradeDeliveryService;
import com.gs.lshly.biz.support.user.entity.User;
import com.gs.lshly.biz.support.user.repository.IUserRepository;
import com.gs.lshly.common.enums.TradeDeliveryTypeEnum;
import com.gs.lshly.common.enums.TradeStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.CommonLogisticsCompanyVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeDeliveryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeDeliveryQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeDeliveryVO;
import com.gs.lshly.common.struct.merchadmin.pc.user.vo.PCMerchUserVO;
import com.gs.lshly.common.utils.AESUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.middleware.sms.IContactSMSService;
import com.gs.lshly.middleware.sms.ISMSService;
import com.gs.lshly.rpc.api.common.ICommonLogisticsCompanyRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchShopRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.user.IPCMerchUserRpc;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* <p>
*  服务实现类
* </p>
* @author oy
* @since 2020-11-16
*/
@Component
@Slf4j
public class PCMerchTradeDeliveryServiceImpl implements IPCMerchTradeDeliveryService {

    private final ITradeDeliveryRepository tradeDeliveryRepository;

    private final ITradeRepository tradeRepository;
    @Autowired
    private IContactSMSService iContactSMSService;

    @Autowired
    private IUserRepository userRepository;

    @DubboReference
    private IPCMerchUserRpc ipcMerchUserRpc;

    @DubboReference
    private IPCMerchShopRpc ipcMerchShopRpc;

//    @Autowired
//    private  ISMSService ismsService ;

    @DubboReference
    private ICommonLogisticsCompanyRpc commonLogisticsCompanyRpc;

    public PCMerchTradeDeliveryServiceImpl(ITradeDeliveryRepository tradeDeliveryRepository,ITradeRepository tradeRepository) {
        this.tradeDeliveryRepository = tradeDeliveryRepository;
        this.tradeRepository = tradeRepository;
    }

    @Override
    public PageData<PCMerchTradeDeliveryVO.ListVO> pageData(PCMerchTradeDeliveryQTO.QTO qto) {
        QueryWrapper<PCMerchTradeDeliveryQTO.QTO> wrapper = new QueryWrapper<>();
        wrapper.and(i -> i.eq("td.`shop_id`",qto.getJwtShopId()));
        if(ObjectUtils.isNotEmpty(qto.getTradeState())){
            wrapper.and(i -> i.eq("t.`trade_state`",qto.getTradeState()));//交易状态,不传则查所有状态数据
        }
        if(ObjectUtils.isNotEmpty(qto.getTradeCode())){
            wrapper.and(i -> i.like("t.`trade_code`",qto.getTradeCode()));
        }
        if(ObjectUtils.isNotEmpty(qto.getLogisticsNumber())){
            wrapper.and(i -> i.like("td.`logistics_number`",qto.getLogisticsNumber()));
        }
        if(ObjectUtils.isNotEmpty(qto.getUserName())){
            wrapper.and(i -> i.like("u.`user_name`",qto.getUserName()));
        }
        if(ObjectUtils.isNotEmpty(qto.getUserPhone())){
            wrapper.and(i -> i.like("u.`phone`",qto.getUserPhone()));
        }
        if(ObjectUtils.isNotEmpty(qto.getAddress())){
            wrapper.and(i -> i.like("t.`recv_full_addres`",qto.getAddress()));
        }
        if(ObjectUtils.isNotEmpty(qto.getName())){
            wrapper.and(i -> i.like("t.`recv_person_name`",qto.getName()));
        }
        if(ObjectUtils.isNotEmpty(qto.getPhone())){
            wrapper.and(i -> i.like("t.`recv_phone`",qto.getPhone()));
        }
        wrapper.orderByDesc("t.`cdate`");
        IPage<PCMerchTradeDeliveryVO.ListVO> page = MybatisPlusUtil.pager(qto);
        tradeDeliveryRepository.selectListPage(page,wrapper);
        List<PCMerchTradeDeliveryVO.ListVO> listVOS = new ArrayList<>();
        for (PCMerchTradeDeliveryVO.ListVO record : page.getRecords()) {
            PCMerchTradeDeliveryVO.ListVO listVO = new PCMerchTradeDeliveryVO.ListVO();
            BeanUtils.copyProperties(record,listVO);
            CommonLogisticsCompanyVO.DetailVO logisticsCompany = commonLogisticsCompanyRpc.getLogisticsCompany(record.getLogisticsId());
            if (ObjectUtils.isNotEmpty(logisticsCompany)){
                listVO.setLogisticsName(logisticsCompany.getName());
            }
            PCMerchUserVO.UserSimpleVO userSimpleVO = ipcMerchUserRpc.innerUserSimple(record.getUserId());
            if (ObjectUtils.isNotEmpty(userSimpleVO)){
                listVO.setUserName(userSimpleVO.getUserName());
            }
            listVOS.add(listVO);
        }
        return new PageData<>(listVOS,qto.getPageNum(),qto.getPageSize(),listVOS.size());
    }

    @Override
    public void addTradeDelivery(PCMerchTradeDeliveryDTO.deliveryDTO eto) {
        //查询订单,判断订单状态
        Trade trade = tradeRepository.getById(eto.getTradeId());
        if(ObjectUtils.isEmpty(trade) ){
            throw new BusinessException("无订单信息");
        }else if(!trade.getTradeState().equals(TradeStateEnum.待发货.getCode())){
            throw new BusinessException("不是待发货状态");
        }
        if (trade.getDeliveryType().equals(TradeDeliveryTypeEnum.门店自提.getCode())){
            if (ObjectUtils.isNotEmpty(trade.getUserId())){
                PCMerchUserVO.UserSimpleVO userSimpleVO = ipcMerchUserRpc.innerUserSimple(trade.getUserId());
                if (ObjectUtils.isNotEmpty(userSimpleVO)){
                    if (ObjectUtils.isNotEmpty(userSimpleVO.getUserName())){
                        String s = "";//ismsService.sendPickUpSMSCode(trade.getRecvPhone(), trade.getTakeGoodsCode(),userSimpleVO.getUserName());
                        log.info("发送自提短信成功{}",s);
                    }
                }
            }
        }

        //新增发货记录
        if(trade.getDeliveryType().equals(TradeDeliveryTypeEnum.快递配送.getCode())){
            TradeDelivery tradeDelivery = new TradeDelivery();
            BeanUtils.copyProperties(eto, tradeDelivery);
            tradeDelivery.setUserId(trade.getUserId());
            tradeDelivery.setShopId(trade.getShopId());
            tradeDelivery.setOperatorId(eto.getJwtUserId());
            tradeDelivery.setOperatorName(eto.getJwtUserName());
            tradeDelivery.setDeliveryTime(LocalDateTime.now());
            tradeDeliveryRepository.save(tradeDelivery);
        }
        //修改订单状态
        trade.setTradeState(TradeStateEnum.待收货.getCode());
        tradeRepository.saveOrUpdate(trade);

        // 查询用户信息
        User user = userRepository.getById(trade.getUserId());
        // 查询物流信息
        CommonLogisticsCompanyVO.DetailVO logisticsCompany = commonLogisticsCompanyRpc.getLogisticsCompany(eto.getLogisticsId());
        // 发送收货短信
        iContactSMSService.shipmentReminder(AESUtil.aesDecrypt(user.getPhone()),AESUtil.aesDecrypt(user.getUserName()),logisticsCompany.getName(),eto.getLogisticsNumber());

    }

    @Override
    public void editTradeDelivery(PCMerchTradeDeliveryDTO.ETO eto) {
        TradeDelivery tradeDelivery = new TradeDelivery();
        BeanUtils.copyProperties(eto, tradeDelivery);
        tradeDeliveryRepository.updateById(tradeDelivery);
    }

    @Override
    public PCMerchTradeDeliveryVO.DetailVO detailTradeDelivery(PCMerchTradeDeliveryDTO.IdDTO dto) {
        TradeDelivery tradeDelivery = tradeDeliveryRepository.getById(dto.getId());
        PCMerchTradeDeliveryVO.DetailVO detailVo = new PCMerchTradeDeliveryVO.DetailVO();
        if(ObjectUtils.isEmpty(tradeDelivery)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(tradeDelivery, detailVo);
        CommonLogisticsCompanyVO.DetailVO logisticsCompany = commonLogisticsCompanyRpc.getLogisticsCompany(detailVo.getLogisticsId());
        if (ObjectUtils.isNotEmpty(logisticsCompany)){
            detailVo.setLogisticsName(logisticsCompany.getName());
        }
        return detailVo;
    }

    @Override
    public List<CommonLogisticsCompanyVO.DetailVO> listShopLogisticsCompany(PCMerchTradeDeliveryDTO.IdDTO dto) {
        return commonLogisticsCompanyRpc.listShopLogisticsCompany(dto.getJwtShopId());
    }

    @Override
    public void addTakeGoodsCodeCheck(PCMerchTradeDeliveryDTO.takeGoodsCodeCheckDTO eto) {
        //查询订单,判断订单状态
        Trade trade = tradeRepository.getById(eto.getTradeId());
        if(ObjectUtils.isEmpty(trade) ){
            throw new BusinessException("无订单信息");
        }else if(!trade.getTradeState().equals(TradeStateEnum.待收货.getCode())){
            throw new BusinessException("不是待取货状态");
        }
        //判断自提码
        if(!eto.getTakeGoodsCode().equals(trade.getTakeGoodsCode())){
            throw new BusinessException("自提码不正确");
        }

        trade.setTradeState(TradeStateEnum.已完成.getCode());
        trade.setRecvTime(LocalDateTime.now());
        tradeRepository.saveOrUpdate(trade);
    }

    @Override
    public List<PCMerchTradeDeliveryVO.ListVOExport> export(PCMerchTradeDeliveryQTO.IdListQTO qo) {
        if (ObjectUtils.isEmpty(qo.getIdList())){
            throw new BusinessException("请传入导出ID");
        }
        List<TradeDelivery> tradeDeliveries = tradeDeliveryRepository.listByIds(qo.getIdList());
        if (ObjectUtils.isNotEmpty(tradeDeliveries)) {
            List<PCMerchTradeDeliveryVO.ListVOExport> listVOS = tradeDeliveries.parallelStream().map(i -> {
                PCMerchTradeDeliveryVO.ListVOExport listVO = new PCMerchTradeDeliveryVO.ListVOExport();
                BeanUtils.copyProperties(i, listVO);
                CommonLogisticsCompanyVO.DetailVO logisticsCompany = commonLogisticsCompanyRpc.getLogisticsCompany(i.getLogisticsId());
                if (ObjectUtils.isNotEmpty(logisticsCompany)){
                    listVO.setLogisticsName(logisticsCompany.getName());
                }
                PCMerchUserVO.UserSimpleVO userSimpleVO = ipcMerchUserRpc.innerUserSimple(i.getUserId());
                if (ObjectUtils.isNotEmpty(userSimpleVO)){
                    listVO.setUserName(userSimpleVO.getUserName());
                }
                PCMerchShopVO.ShopSimpleVO shopSimpleVO = ipcMerchShopRpc.innerShopSimple(i.getShopId());
                if (ObjectUtils.isNotEmpty(shopSimpleVO)){
                    listVO.setShopName(shopSimpleVO.getShopName());
                }
                Trade byId = tradeRepository.getById(i.getTradeId());
                if (ObjectUtils.isNotEmpty(byId)){
                    listVO.setTradeCode(byId.getTradeCode());
                }
                return listVO;
            }).collect(Collectors.toList());
            return listVOS;
        }
        return new ArrayList<>();
    }

}
