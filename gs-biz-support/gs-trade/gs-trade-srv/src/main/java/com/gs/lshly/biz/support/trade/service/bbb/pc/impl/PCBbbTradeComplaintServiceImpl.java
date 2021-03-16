package com.gs.lshly.biz.support.trade.service.bbb.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.TradeComplaint;
import com.gs.lshly.biz.support.trade.entity.TradeComplaintImg;
import com.gs.lshly.biz.support.trade.mapper.TradeComplaintMapper;
import com.gs.lshly.biz.support.trade.mapper.view.TradeComplaintView;
import com.gs.lshly.biz.support.trade.repository.ITradeComplaintImgRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeComplaintRepository;
import com.gs.lshly.biz.support.trade.service.bbb.pc.IPCBbbTradeComplaintService;
import com.gs.lshly.common.enums.TradeComplaintStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.PCBbbTradeComplaintDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbTradeComplaintQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbTradeComplaintVO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-12-23
*/
@Component
public class PCBbbTradeComplaintServiceImpl implements IPCBbbTradeComplaintService {

    @Autowired
    private ITradeComplaintRepository repository;
    @Autowired
    private ITradeComplaintImgRepository complaintImgRepository;
    @Autowired
    private TradeComplaintMapper complaintMapper;
    @DubboReference
    private ICommonShopRpc commonShopRpc;

    @Override
    public PageData<PCBbbTradeComplaintVO.DetailListVO> pageData(PCBbbTradeComplaintQTO.QTO qto) {
        if (ObjectUtils.isEmpty(qto.getJwtUserId())){
            throw new BusinessException("未登录！！");
        }
        QueryWrapper<TradeComplaintView> wrapper = MybatisPlusUtil.query();
        wrapper.eq("tc.user_id",qto.getJwtUserId());
        IPage<TradeComplaintView> page = MybatisPlusUtil.pager(qto);
        IPage<TradeComplaintView> complaintViewIPage = complaintMapper.pageVo(page,wrapper);
        if (ObjectUtils.isEmpty(complaintViewIPage) || ObjectUtils.isEmpty(complaintViewIPage.getRecords())){
            return new PageData<>();
        }
        List<PCBbbTradeComplaintVO.DetailListVO> listVOS = ListUtil.listCover(PCBbbTradeComplaintVO.DetailListVO.class,complaintViewIPage.getRecords());
        return new PageData<>(listVOS,qto.getPageNum(),qto.getPageSize(),complaintViewIPage.getTotal());
    }

    @Override
    public void addTradeComplaint(PCBbbTradeComplaintDTO.DetailEto eto) {
        if (ObjectUtils.isEmpty(eto.getJwtUserId())){
            throw new BusinessException("未登录！！");
        }
        TradeComplaint complaint = new TradeComplaint();
        BeanUtils.copyProperties(eto,complaint);
        complaint.setUserId(eto.getJwtUserId());
        complaint.setUserName(eto.getJwtUserName());
        complaint.setComplaintTime(LocalDateTime.now());
        complaint.setHandleState(TradeComplaintStateEnum.等待处理.getCode());
        repository.save(complaint);

        //若上传了凭证则保存数据
        if (ObjectUtils.isNotEmpty(eto.getImageList())){
            for (String img : eto.getImageList()){
                TradeComplaintImg complaintImg = new TradeComplaintImg();
                complaintImg.setComplaintId(complaint.getId());
                complaintImg.setImgUrl(img);
                complaintImgRepository.save(complaintImg);
            }
        }
    }

    @Override
    public void editTradeComplaint(PCBbbTradeComplaintDTO.CancelIdeaDTO dto) {
        if (ObjectUtils.isEmpty(dto.getJwtUserId())){
            throw new BusinessException("未登录！！");
        }
        if (ObjectUtils.isNotEmpty(dto) && StringUtils.isBlank(dto.getCancelIdea())){
            throw new BusinessException("请填写撤销理由！！！");
        }
        QueryWrapper<TradeComplaint> wrapper = MybatisPlusUtil.query();
        wrapper.eq("id",dto.getId());
        TradeComplaint complaint = repository.getOne(wrapper);
        if (!complaint.getHandleState().equals(TradeComplaintStateEnum.等待处理.getCode())){
            throw new BusinessException("投诉状态不处于等待处理中，不能撤销！！");
        }
        TradeComplaint tradeComplaint = new TradeComplaint();
        tradeComplaint.setHandleState(TradeComplaintStateEnum.买家撤销了投诉.getCode());
        tradeComplaint.setCancenlIdea(dto.getCancelIdea());

        repository.update(tradeComplaint,wrapper);
    }


    @Override
    public PCBbbTradeComplaintVO.DetailVO detailTradeComplaint(PCBbbTradeComplaintDTO.IdDTO dto) {
        if (ObjectUtils.isEmpty(dto.getJwtUserId())){
            throw new BusinessException("未登录！！");
        }
        QueryWrapper<TradeComplaintView> wrapper = MybatisPlusUtil.query();
        wrapper.eq("tc.user_id",dto.getJwtUserId());
        wrapper.eq("tc.id",dto.getId());
        TradeComplaintView complaintView = complaintMapper.getDetail(wrapper);
        PCBbbTradeComplaintVO.DetailVO detailVo = new PCBbbTradeComplaintVO.DetailVO();
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
