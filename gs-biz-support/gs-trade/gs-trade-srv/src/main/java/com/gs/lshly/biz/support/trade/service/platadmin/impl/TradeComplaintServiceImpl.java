package com.gs.lshly.biz.support.trade.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.TradeCommentImg;
import com.gs.lshly.biz.support.trade.entity.TradeComplaint;
import com.gs.lshly.biz.support.trade.entity.TradeComplaintImg;
import com.gs.lshly.biz.support.trade.mapper.TradeComplaintMapper;
import com.gs.lshly.biz.support.trade.mapper.view.TradeComplaintView;
import com.gs.lshly.biz.support.trade.repository.ITradeComplaintImgRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeComplaintRepository;
import com.gs.lshly.biz.support.trade.service.platadmin.ITradeComplaintService;
import com.gs.lshly.common.enums.TradeComplaintStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbTradeComplaintVO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeComplaintDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeComplaintQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeComplaintVO;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

import java.util.List;
import java.util.stream.Collectors;


/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-12-24
*/
@Component
public class TradeComplaintServiceImpl implements ITradeComplaintService {

    @Autowired
    private ITradeComplaintRepository repository;
    @Autowired
    private ITradeComplaintImgRepository complaintImgRepository;
    @Autowired
    private TradeComplaintMapper complaintMapper;
    @DubboReference
    private ICommonShopRpc commonShopRpc;

    @Override
    public PageData<TradeComplaintVO.DetailListVO> pageData(TradeComplaintQTO.QTO qto) {
        QueryWrapper<TradeComplaintView> wrapper = MybatisPlusUtil.query();
        if (ObjectUtils.isNotEmpty(qto.getTradeCode())){
            wrapper.like("gt.trade_code",qto.getTradeCode());
        }
        if (ObjectUtils.isNotEmpty(qto.getQueryType()) && !qto.getQueryType().equals(TradeComplaintStateEnum.全部.getCode())){
            wrapper.eq("tc.handle_state",qto.getQueryType());
        }
        wrapper.orderByDesc("tc.cdate");
        IPage<TradeComplaintView> page = MybatisPlusUtil.pager(qto);
        IPage<TradeComplaintView> viewIPage = complaintMapper.pageVo(page,wrapper);
        if (ObjectUtils.isEmpty(viewIPage) || ObjectUtils.isEmpty(viewIPage.getRecords())){
            return new PageData<>();
        }
        List<TradeComplaintVO.DetailListVO> detailListVOS = viewIPage.getRecords()
                .parallelStream().map(e ->{
                    TradeComplaintVO.DetailListVO detailListVO = new TradeComplaintVO.DetailListVO();
                    BeanUtils.copyProperties(e,detailListVO);
                    CommonShopVO.SimpleVO simpleVO = getShopVo(e.getShopId());
                    detailListVO.setShopName(StringUtils.isBlank(simpleVO.getShopName())?"":simpleVO.getShopName());
                    return detailListVO;
                }).collect(Collectors.toList());
        return new PageData<>(detailListVOS,qto.getPageNum(),qto.getPageSize(),viewIPage.getTotal());
    }

    @Override
    public void deleteTradeComplaint(TradeComplaintDTO.IdListDTO dto) {
        if (ObjectUtils.isEmpty(dto) || ObjectUtils.isEmpty(dto.getIdList())){
            throw new BusinessException("请选择要删除的订单申诉！！！");
        }
        QueryWrapper<TradeComplaintImg> wrapper = MybatisPlusUtil.query();
        wrapper.in("complaint_id",dto.getIdList());
        complaintImgRepository.remove(wrapper);

        QueryWrapper<TradeComplaint> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.in("id",dto.getIdList());
        repository.remove(queryWrapper);
    }

    @Override
    public void editTradeComplaint(TradeComplaintDTO.ETO eto) {
        if (ObjectUtils.isEmpty(eto) || ObjectUtils.isEmpty(eto.getPlatformReply())){
            throw new BusinessException("请填写处理备注！！！");
        }
        TradeComplaint tradeComplaint = new TradeComplaint();
        BeanUtils.copyProperties(eto, tradeComplaint);
        repository.updateById(tradeComplaint);
    }

    @Override
    public TradeComplaintVO.DetailVO detailTradeComplaint(TradeComplaintDTO.IdDTO dto) {
        QueryWrapper<TradeComplaintView> wrapper = MybatisPlusUtil.query();
        wrapper.eq("tc.id",dto.getId());
        TradeComplaintView complaintView = complaintMapper.getDetail(wrapper);
        TradeComplaintVO.DetailVO detailVo = new TradeComplaintVO.DetailVO();
        if(ObjectUtils.isEmpty(complaintView)){
            throw new BusinessException("数据异常！！");
        }
        BeanUtils.copyProperties(complaintView, detailVo);
        CommonShopVO.SimpleVO simpleVO = getShopVo(complaintView.getShopId());
        detailVo.setShopName(StringUtils.isBlank(simpleVO.getShopName())?"":simpleVO.getShopName());
        return detailVo;
    }


    private CommonShopVO.SimpleVO getShopVo(String shopId){
        CommonShopVO.SimpleVO simpleVO = commonShopRpc.shopDetails(shopId);
        if (ObjectUtils.isEmpty(simpleVO)){
            throw new BusinessException("店铺不存在！！");
        }
        return simpleVO;
    }

}
