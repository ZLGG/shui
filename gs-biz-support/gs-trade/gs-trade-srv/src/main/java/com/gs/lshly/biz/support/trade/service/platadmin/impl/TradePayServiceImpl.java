package com.gs.lshly.biz.support.trade.service.platadmin.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.trade.entity.Trade;
import com.gs.lshly.biz.support.trade.entity.TradePay;
import com.gs.lshly.biz.support.trade.repository.ITradePayRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeRepository;
import com.gs.lshly.biz.support.trade.service.platadmin.ITradePayService;
import com.gs.lshly.common.enums.TradePayStateEnum;
import com.gs.lshly.common.enums.TradePayTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopDTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradePayDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradePayQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradePayVO;
import com.gs.lshly.common.struct.platadmin.user.dto.UserDTO;
import com.gs.lshly.common.struct.platadmin.user.vo.UserVO;
import com.gs.lshly.common.utils.EnumUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.platadmin.merchant.IShopRpc;
import com.gs.lshly.rpc.api.platadmin.user.IUserRpc;

/**
* <p>
*  服务实现类
* </p>
* @author oy
* @since 2020-11-16
*/
@Component
public class TradePayServiceImpl implements ITradePayService {

    @Autowired
    private ITradePayRepository repository;
    @Autowired
    private ITradeRepository iTradeRepository;
    @DubboReference
    private IUserRpc iUserRpc;
    @DubboReference
    private IShopRpc iShopRpc;

    @Override
    public PageData<TradePayVO.ListVO> pageData(TradePayQTO.QTO qto) {
        QueryWrapper<TradePay> query = MybatisPlusUtil.query();
        if (ObjectUtils.isNotEmpty(qto.getState())){
            if (qto.getState()==90){
                //线下支付
                query.and(i->i.eq("pay_type",qto.getState()));
            }else {
                query.and(i->i.eq("pay_type",10).
                                or().
                                eq("pay_type",20).
                                or().
                                eq("pay_type",30).
                                or().
                                eq("pay_type",40).
                                or().
                                eq("pay_type",50).
                                or().
                                eq("pay_type",60).
                                or().
                                eq("pay_type",70).
                                or().
                                eq("pay_type",80));
            }
        }
        if (ObjectUtils.isNotEmpty(qto.getPayState())){
            query.and(i->i.eq("pay_state",qto.getPayState()));
        }
        if (ObjectUtils.isNotEmpty(qto.getId())){
            query.and(i->i.like("trade",qto.getId()));
        }
        if (ObjectUtils.isNotEmpty(qto.getCdate()) || ObjectUtils.isNotEmpty(qto.getCdateState())){
            GetQuery(query,qto.getCdateState(),qto.getCdate(),qto.getCdateLittleDate());
        }
        query.orderByDesc("cdate");
        IPage<TradePay> page = MybatisPlusUtil.pager(qto);
        IPage<TradePay> payIPage = repository.page(page,query);
        if (ObjectUtils.isEmpty(payIPage) || (ObjectUtils.isEmpty(payIPage.getRecords()))){
            return new PageData<>();
        }
        List<TradePayVO.ListVO> listVOS =payIPage.getRecords().parallelStream().map(e ->{
            TradePayVO.ListVO listVO = new TradePayVO.ListVO();
            BeanUtils.copyProperties(e,listVO);
            UserVO.MiniVO mini = iUserRpc.mini(new UserDTO.IdDTO(listVO.getUserId()));
            if (ObjectUtils.isNotEmpty(mini)){
                listVO.setUserName(mini.getUserName());
                listVO.setPhone(mini.getPhone());
            }
            ShopVO.DetailVO detailVO = iShopRpc.shopDetails(new ShopDTO.IdDTO(listVO.getShopId()));
            if (ObjectUtils.isNotEmpty(detailVO)){
                listVO.setShopName(detailVO.getShopName());
            }
            if(listVO.getPayState().equals(TradePayStateEnum.已支付.getCode())){
                Trade trade = iTradeRepository.getById(listVO.getTradeId());
                if(ObjectUtils.isNotEmpty(trade)){
                    listVO.setPayTime(trade.getPayTime());
                }
            }
            return listVO;
        }).collect(Collectors.toList());
       return new PageData<>(listVOS,qto.getPageNum(),qto.getPageSize(),payIPage.getTotal());
    }

    @Override
    public TradePayVO.DetailVO detailTradePay(TradePayDTO.IdDTO dto) {
        TradePay tradePay = repository.getById(dto.getId());
        TradePayVO.DetailVO detailVo = new TradePayVO.DetailVO();
        if(ObjectUtils.isEmpty(tradePay)){
            return new TradePayVO.DetailVO();
        }
        BeanUtils.copyProperties(tradePay, detailVo);
        UserVO.MiniVO mini=null;
        if (StringUtils.isNotBlank(detailVo.getUserId())){
             mini = iUserRpc.mini(new UserDTO.IdDTO(detailVo.getUserId()));
        }

        if (ObjectUtils.isNotEmpty(mini)){
            detailVo.setUserName(mini.getUserName());
        }
        return detailVo;
    }

    @Override
    public PageData<TradePayVO.RelationDetailVO> relationList(TradePayQTO.RelationQTO qto) {
        IPage<TradePay> pager = MybatisPlusUtil.pager(qto);
        QueryWrapper<TradePay> query = MybatisPlusUtil.query();
        if (StringUtils.isNotBlank(qto.getTradeId())){
            query.and(i->i.like("trade_id",qto.getTradeId()));
        }
        if (StringUtils.isNotBlank(qto.getTradePayId())){
            query.and(i->i.like("id",qto.getTradePayId()));
        }
        if (ObjectUtils.isNotEmpty(qto.getPayState())){
            query.and(i->i.eq("pay_state",qto.getPayState()));
        }
        if (ObjectUtils.isNotEmpty(qto.getPayStartDate()) || ObjectUtils.isNotEmpty(qto.getPayStartDateState()) ){
            GetQuery(query,qto.getPayStartDateState(),qto.getPayStartDate(),qto.getPayStartLittleDate());
        }
        if (ObjectUtils.isNotEmpty(qto.getPayFinishDate()) || ObjectUtils.isNotEmpty(qto.getPayFinishDateState()) ){
            query.and(i->i.eq("pay_state",TradePayStateEnum.已支付.getCode()));
            GetuDateQuery(query,qto,qto.getPayFinishDate(),qto.getPayFinishLittleDate());
        }
        if (ObjectUtils.isNotEmpty(qto.getUdate()) || ObjectUtils.isNotEmpty(qto.getUdateState())){
            GetUDateQuery(query,qto,qto.getUdate());
        }


        IPage<TradePay> page = repository.page(pager,query);
        if (ObjectUtils.isEmpty(pager) || (ObjectUtils.isEmpty(page.getRecords()))){
            return new PageData<>();
        }
        List<TradePayVO.RelationDetailVO> listVO=page.getRecords().parallelStream().map(i->{
            TradePayVO.RelationDetailVO relationDetailVO = new TradePayVO.RelationDetailVO();
            BeanUtils.copyProperties(i,relationDetailVO);
            if (relationDetailVO.getPayState().equals(TradePayStateEnum.已支付)){
                relationDetailVO.setFinishDate(i.getUdate());
            }else {
                relationDetailVO.setFinishDate(null);
            }
            Trade byId = iTradeRepository.getById(i.getTradeId());
            if (ObjectUtils.isNotEmpty(byId)){
                relationDetailVO.setTradeCode(byId.getTradeCode());
            }
            //add 用户手机号
            UserVO.MiniVO mini=null;
            if (StringUtils.isNotBlank(i.getUserId())){
                mini = iUserRpc.mini(new UserDTO.IdDTO(i.getUserId()));
            }
            if (ObjectUtils.isNotEmpty(mini)){
                relationDetailVO.setPhone(mini.getPhone());
            }

            return relationDetailVO;
        }).collect(Collectors.toList());
        return new PageData<>(listVO,qto.getPageNum(),qto.getPageSize(),page.getTotal());
    }

    private void GetQuery(QueryWrapper<TradePay> query, Integer qto, LocalDateTime date,LocalDateTime littleDate) {
        switch (qto){
            case 10:query.and(i->i.ge("cdate",date));break;
            case 20:query.and(i->i.le("cdate",date));break;
            case 30:query.and(i->i.eq("cdate",date));break;
            case 40:if (ObjectUtils.isNotEmpty(littleDate)){
                         query.and(i->i.ge("cdate",date).le("cdate",littleDate));
                    }break;
        }

    }
    private void GetuDateQuery(QueryWrapper<TradePay> query, TradePayQTO.RelationQTO qto, LocalDateTime date,LocalDateTime littleDate) {
        switch (qto.getPayFinishDateState()){
            case 10:query.and(i->i.ge("udate",date));break;
            case 20:query.and(i->i.le("udate",date));break;
            case 30:query.and(i->i.eq("udate",date));break;
            case 40:if (ObjectUtils.isNotEmpty(littleDate)){
                query.and(i->i.ge("udate",date).le("udate",littleDate));
            }break;
        }

    }
    private void GetUDateQuery(QueryWrapper<TradePay> query, TradePayQTO.RelationQTO qto, LocalDateTime date) {
        switch (qto.getUdateState()){
            case 10:query.and(i->i.gt("udate",date));break;
            case 20:query.and(i->i.lt("udate",date));break;
            case 30:query.and(i->i.eq("udate",date));break;
        }

    }

    @Override
    public TradePayVO.DetailVO relationGet(TradePayVO.DetailVO detailVO) {
        TradePay tradePay = repository.getById(detailVO.getId());
        TradePayVO.DetailVO detailVo = new TradePayVO.DetailVO();
        if(ObjectUtils.isEmpty(tradePay)){
            return new TradePayVO.DetailVO();
        }
        BeanUtils.copyProperties(tradePay, detailVo);
        UserVO.MiniVO mini=null;
        if (StringUtils.isNotBlank(detailVo.getUserId())){
            mini = iUserRpc.mini(new UserDTO.IdDTO(detailVo.getUserId()));
        }

        if (ObjectUtils.isNotEmpty(mini)){
            detailVo.setUserName(mini.getUserName());
        }
        return detailVo;
    }

    @Override
    public List<TradePayVO.RelationDetailExport> export(TradePayDTO.IdsDTO qo) {
        if (ObjectUtils.isEmpty(qo.getIds())){
            throw new BusinessException("请传入ID");
        }
        List<TradePay> tradePays = repository.listByIds(qo.getIds());
        if (ObjectUtils.isEmpty(tradePays)){
            return new ArrayList<>();
        }
        List<TradePayVO.RelationDetailExport> listVO=tradePays.parallelStream().map(i->{
            TradePayVO.RelationDetailExport relationDetailVO = new TradePayVO.RelationDetailExport();
            BeanUtils.copyProperties(i,relationDetailVO);
            relationDetailVO.setPayType(TradePayTypeEnum.getEnum(i.getPayType()).getRemark());
            relationDetailVO.setPayState(EnumUtil.getText(i.getPayState(),TradePayStateEnum.class));
            if (relationDetailVO.getPayState().equals(TradePayStateEnum.已支付)){
                relationDetailVO.setFinishDate(i.getUdate());
            }else {
                relationDetailVO.setFinishDate(null);
            }
            return relationDetailVO;
        }).collect(Collectors.toList());
        return listVO;
    }

    @Override
    public void delete(TradePayQTO.IdListQTO ids) {
        if (ObjectUtils.isNotEmpty(ids.getIds())){
            repository.removeByIds(ids.getIds());
        }
    }

    @Override
    public List<TradePayVO.ListVOExport> payExport(TradePayQTO.IdListQTO qo) {
        if (ObjectUtils.isEmpty(qo.getIds())){
            throw new BusinessException("请传入参数");
        }
        List<TradePay> tradePays = repository.listByIds(qo.getIds());
        if (ObjectUtils.isNotEmpty(tradePays)){
            List<TradePayVO.ListVOExport> list=tradePays.parallelStream().map(e ->{
                TradePayVO.ListVOExport listVO = new TradePayVO.ListVOExport();
                BeanUtils.copyProperties(e,listVO);
                UserVO.MiniVO mini = iUserRpc.mini(new UserDTO.IdDTO(e.getUserId()));
                if (ObjectUtils.isNotEmpty(mini)){
                    listVO.setUserName(mini.getUserName());
                }
                ShopVO.DetailVO detailVO = iShopRpc.shopDetails(new ShopDTO.IdDTO(e.getShopId()));
                if (ObjectUtils.isNotEmpty(detailVO)){
                    listVO.setShopName(detailVO.getShopName());
                }
                listVO.setPayType(TradePayTypeEnum.getEnum(e.getPayType()).getRemark());
                listVO.setPayState(EnumUtil.getText(e.getPayState(),TradePayStateEnum.class));
                return listVO;
            }).collect(Collectors.toList());
            return list;
        }
        return new ArrayList<>();
    }


}
