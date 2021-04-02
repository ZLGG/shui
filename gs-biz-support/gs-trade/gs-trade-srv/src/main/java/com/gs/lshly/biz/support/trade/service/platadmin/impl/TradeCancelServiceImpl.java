package com.gs.lshly.biz.support.trade.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.trade.entity.Trade;
import com.gs.lshly.biz.support.trade.entity.TradeCancel;
import com.gs.lshly.biz.support.trade.entity.TradeGoods;
import com.gs.lshly.biz.support.trade.entity.TradePay;
import com.gs.lshly.biz.support.trade.repository.ITradeCancelRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.ITradePayRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeRepository;
import com.gs.lshly.biz.support.trade.service.platadmin.ITradeCancelService;
import com.gs.lshly.common.enums.TradeCancelApplyTypeEnum;
import com.gs.lshly.common.enums.TradeCancelRefundStateEnum;
import com.gs.lshly.common.enums.TradeCancelStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteAdvertVO;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopDTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeCancelDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeCancelQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeCancelVO;
import com.gs.lshly.common.struct.platadmin.user.dto.UserDTO;
import com.gs.lshly.common.struct.platadmin.user.vo.UserVO;
import com.gs.lshly.common.utils.EnumUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.platadmin.merchant.IShopRpc;
import com.gs.lshly.rpc.api.platadmin.user.IUserRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
* <p>
*  服务实现类
* </p>
* @author oy
* @since 2020-11-21
*/
@Component
public class TradeCancelServiceImpl implements ITradeCancelService {

    @Autowired
    private ITradeCancelRepository repository;
    @Autowired
    private ITradeRepository iTradeRepository;
    @Autowired
    private ITradeGoodsRepository iTradeGoodsRepository;
    @Autowired
    private ITradePayRepository iTradePayRepository;

    @DubboReference
    private IShopRpc iShopRpc;

    @DubboReference
    private IUserRpc iUserRpc;

    @Override
    public PageData<TradeCancelVO.ListVO> pageData(TradeCancelQTO.QTO qto) {
        IPage<TradeCancelVO.ListVO> pager = MybatisPlusUtil.pager(qto);
        QueryWrapper<TradeCancelQTO.QTO> query = MybatisPlusUtil.query();
        if (ObjectUtils.isNotEmpty(qto.getSourceType())){
            query.and(i->i.eq("t.`source_type`",qto.getSourceType()));
        }
        if (ObjectUtils.isNotEmpty(qto.getTradeCode())){
            query.and(i->i.like("t.`trade_code`",qto.getTradeCode()));
        }
        if (ObjectUtils.isEmpty(pager)||ObjectUtils.isEmpty(query)){
            return new PageData<>();
        }
        if (ObjectUtils.isNotEmpty(qto.getUserName())){
            query.and(i->i.eq("u.`user_name`",qto.getUserName()));
        }
        query.orderByDesc("t.cdate");
        IPage<TradeCancelVO.ListVO> page = repository.selectListPage(pager, query);
        if (ObjectUtils.isEmpty(page.getRecords()) || ObjectUtils.isEmpty(pager)){
            return new PageData<>();
        }
        List<TradeCancelVO.ListVO> listVOS = new ArrayList<>();

        for (TradeCancelVO.ListVO record : page.getRecords()) {
            TradeCancelVO.ListVO listVO = new TradeCancelVO.ListVO();
            BeanUtils.copyProperties(record,listVO);
            UserVO.MiniVO mini = iUserRpc.mini(new UserDTO.IdDTO(record.getUserId()));
            listVO.setUserName(mini.getUserName());
            listVOS.add(listVO);
        }
        return new PageData<>(listVOS,qto.getPageNum(),qto.getPageSize(),page.getTotal());
    }

    @Override
    public TradeCancelVO.DetailVO detailTradeCancel(TradeCancelDTO.IdDTO dto) {
        TradeCancel tradeCancel = repository.getById(dto.getId());
        TradeCancelVO.DetailVO detailVo = new TradeCancelVO.DetailVO();
        if(ObjectUtils.isEmpty(tradeCancel)){
            return new TradeCancelVO.DetailVO();
        }
        BeanUtils.copyProperties(tradeCancel, detailVo);
        QueryWrapper<TradeGoods> query = MybatisPlusUtil.query();
        query.and(i->i.eq("trade_id",tradeCancel.getTradeId()));
        List<TradeGoods> list = iTradeGoodsRepository.list(query);
        if (ObjectUtils.isNotEmpty(list)){
            List<TradeCancelVO.GoodsList> goodsList=list.parallelStream().map(i->{
                TradeCancelVO.GoodsList goodsVO = new TradeCancelVO.GoodsList();
                goodsVO.setGoodsName(i.getGoodsName());
                goodsVO.setQuantity(i.getQuantity());
                goodsVO.setSalaPrice(i.getSalePrice());
                goodsVO.setId(i.getGoodsId());
                goodsVO.setTotalPrice(i.getSalePrice().multiply(new BigDecimal(i.getQuantity())));
                return goodsVO;
            }).collect(Collectors.toList());
            detailVo.setGoodsList(goodsList);
        }
        ShopVO.InnerSimpleVO innerSimpleVO=null;
        if (StringUtils.isNotBlank(tradeCancel.getShopId())){
            ArrayList<String> shopIds = new ArrayList<>();
            shopIds.add(tradeCancel.getShopId());
            innerSimpleVO = iShopRpc.innerlistShopIdName(dto, shopIds).get(0);
        }
        if (ObjectUtils.isNotEmpty(innerSimpleVO)){
            detailVo.setShopName(innerSimpleVO.getShopName());
        }
        QueryWrapper<TradePay> query1 = MybatisPlusUtil.query();
        query1.and(i->i.eq("trade_id",tradeCancel.getTradeId()));
        TradePay one = iTradePayRepository.getOne(query1);
        if (ObjectUtils.isNotEmpty(one)){
            detailVo.setPayAmount(one.getTotalAmount());
        }
        return detailVo;
    }

    @Override
    public List<TradeCancelVO.ListVOExport> export(TradeCancelQTO.IdListQTO qo) {
        if (ObjectUtils.isEmpty(qo)){
            throw new BusinessException("请选择取消订单的id");
        }
        List<TradeCancel> list = repository.listByIds(qo.getIdList());
        if (ObjectUtils.isNotEmpty(list)){
            List<TradeCancelVO.ListVOExport> listVOExports=list .stream().map(e ->{
                TradeCancelVO.ListVOExport listVOExport = new TradeCancelVO.ListVOExport();
                BeanUtils.copyProperties(e,listVOExport);
                UserVO.MiniVO mini = iUserRpc.mini(new UserDTO.IdDTO(e.getUserId()));
                if (ObjectUtils.isNotEmpty(mini)){
                    listVOExport.setUserName(mini.getUserName());
                }
                ShopVO.DetailVO detailVO = iShopRpc.shopDetails(new ShopDTO.IdDTO(e.getShopId()));
                if (ObjectUtils.isNotEmpty(detailVO)){
                    listVOExport.setShopName(detailVO.getShopName());
                }
                listVOExport.setCancelState(EnumUtil.getText(e.getCancelState(), TradeCancelStateEnum.class));
                listVOExport.setApplyType(EnumUtil.getText(e.getApplyType(), TradeCancelApplyTypeEnum.class));
                listVOExport.setRefundState(EnumUtil.getText(e.getRefundState(), TradeCancelRefundStateEnum.class));
                return listVOExport;
            }).collect(Collectors.toList());
            return listVOExports;
        }
        return null;
    }

    @Override
    public void delete(TradeCancelQTO.IdListQTO ids) {
        if (ObjectUtils.isNotEmpty(ids)){
            repository.removeByIds(ids.getIdList());
        }
    }

}
