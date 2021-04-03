package com.gs.lshly.biz.support.trade.service.platadmin.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.TradeMarginDetail;
import com.gs.lshly.biz.support.trade.repository.ITradeMarginDetailRepository;
import com.gs.lshly.biz.support.trade.service.platadmin.ITradeMarginDetailService;
import com.gs.lshly.common.enums.MarginEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeMarginDetailQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeMarginDetailVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;


/**
* <p>
*  服务实现类
* </p>
* @author zst
* @since 2020-12-10
*/
@Component
public class TradeMarginDetailServiceImpl implements ITradeMarginDetailService {

    @Autowired
    private ITradeMarginDetailRepository repository;

    @Override
    public PageData<TradeMarginDetailVO.ListVO> pageData(TradeMarginDetailQTO.QTO qto) {
        QueryWrapper<TradeMarginDetail> wrapper = new QueryWrapper<>();
        if(ObjectUtils.isNotEmpty(qto.getMarginDetailScreen())){
            if(qto.getMarginDetailScreen().equals(MarginEnum.大于.getCode())){
                wrapper.gt("pay_amount",qto.getPayPmount());
            }
            if(qto.getMarginDetailScreen().equals(MarginEnum.小于.getCode())){
                wrapper.lt("pay_amount",qto.getPayPmount());
            }
            if(qto.getMarginDetailScreen().equals(MarginEnum.等于.getCode())){
                wrapper.eq("pay_amount",qto.getPayPmount());
            }
            if(qto.getMarginDetailScreen().equals(MarginEnum.大于等于.getCode())){
                wrapper.ge("pay_amount",qto.getPayPmount());
            }
            if(qto.getMarginDetailScreen().equals(MarginEnum.小于等于.getCode())){
                wrapper.le("pay_amount",qto.getPayPmount());
            }
            if(qto.getMarginDetailScreen().equals(MarginEnum.介于.getCode())){
                if(ObjectUtils.isNotEmpty(qto.getPayGe()) && ObjectUtils.isNotEmpty(qto.getPayLe())){
                    wrapper.between("pay_amount",qto.getPayGe(),qto.getPayLe());
                }
            }
        }
        if(ObjectUtils.isNotEmpty(qto.getPayType())){
            wrapper.eq("pay_type",qto.getPayType());
        }
        if(ObjectUtils.isNotEmpty(qto.getComment())){
            wrapper.eq("comment",qto.getComment());
        }
        if(ObjectUtils.isNotEmpty(qto.getUserId())){
            wrapper.like("user_id",qto.getUserId());
        }
        if(ObjectUtils.isNotEmpty(qto.getUserName())){
            wrapper.like("user_name",qto.getUserName());
        }
        if(ObjectUtils.isNotEmpty(qto.getBillStartTime())){
            wrapper.ge("cdate",qto.getBillStartTime());
        }
        if(ObjectUtils.isNotEmpty(qto.getBillEndTime())){
            wrapper.le("cdate",qto.getBillEndTime());
        }

        wrapper.orderByDesc("cdate");
        IPage<TradeMarginDetail> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, TradeMarginDetailVO.ListVO.class, page);
    }

    @Override
    public List<TradeMarginDetailVO.ListVO> exportMarginDetailData(TradeMarginDetailQTO.IdListQTO qto) {
        if (ObjectUtils.isEmpty(qto)){
            throw new BusinessException("参数不能为空");
        }
        List<TradeMarginDetail> marginDetailList= repository.listByIds(qto.getIdList());
        if(ObjectUtils.isEmpty(marginDetailList)){
            throw new BusinessException("查询异常");
        }
        List<TradeMarginDetailVO.ListVO> excelMarginDetailDataVOS = new ArrayList<>();

        for (TradeMarginDetail tradeMarginDetail:marginDetailList) {
            TradeMarginDetailVO.ListVO marginDetailVO = new TradeMarginDetailVO.ListVO();
            BeanUtils.copyProperties(tradeMarginDetail,marginDetailVO);
            if(ObjectUtils.isEmpty(tradeMarginDetail.getMarginId())){
                throw new BusinessException("数据异常");
            }
            //封装保证金明细数据
            findMargindetailDate(tradeMarginDetail);
            excelMarginDetailDataVOS.add(marginDetailVO);
        }
        return excelMarginDetailDataVOS;
    }

    private void findMargindetailDate(TradeMarginDetail tradeMarginDetail){
        TradeMarginDetailVO.ListVO marginDetailVO = new TradeMarginDetailVO.ListVO();
        marginDetailVO.setShopName(tradeMarginDetail.getShopName());
        marginDetailVO.setUserId(tradeMarginDetail.getUserId());
        marginDetailVO.setUserName(tradeMarginDetail.getUserName());
        marginDetailVO.setPayAmount(tradeMarginDetail.getPayAmount());
        marginDetailVO.setBankSerialNum(tradeMarginDetail.getBankSerialNum());
        marginDetailVO.setTradeCode(tradeMarginDetail.getTradeCode());
        marginDetailVO.setPenaltyReason(tradeMarginDetail.getPenaltyReason());
        marginDetailVO.setIllegalDescription(tradeMarginDetail.getIllegalDescription());
        marginDetailVO.setPayType(tradeMarginDetail.getPayType());
        marginDetailVO.setComment(tradeMarginDetail.getComment());
    }

}
