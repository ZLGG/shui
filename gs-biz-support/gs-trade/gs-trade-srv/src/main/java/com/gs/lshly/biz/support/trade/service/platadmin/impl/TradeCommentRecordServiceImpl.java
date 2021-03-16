package com.gs.lshly.biz.support.trade.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.trade.entity.TradeCommentRecord;
import com.gs.lshly.biz.support.trade.repository.ITradeCommentRecordRepository;
import com.gs.lshly.biz.support.trade.service.platadmin.ITradeCommentImgService;
import com.gs.lshly.biz.support.trade.service.platadmin.ITradeCommentRecordService;
import com.gs.lshly.common.enums.TradeAppealStateEnum;
import com.gs.lshly.common.enums.TradeCommentImgBelongEnum;
import com.gs.lshly.common.enums.TradeCommentRecordTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeCommentRecordDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeCommentImgQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeCommentImgVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeCommentRecordVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-11-17
*/
@Component
public class TradeCommentRecordServiceImpl implements ITradeCommentRecordService {

    @Autowired
    private ITradeCommentRecordRepository repository;

    @Autowired
    private ITradeCommentImgService imgService;

    @Override
    public TradeCommentRecordVO.AppealRecordDetailVO getAppealDetailInfo(TradeCommentRecordDTO.CommentIdDTO dto) {
        //获取申诉内容
        QueryWrapper<TradeCommentRecord> boost = MybatisPlusUtil.query();
        boost.eq("comment_id",dto.getId());
        boost.eq("appeal_state", TradeAppealStateEnum.申诉.getCode());
        boost.eq("record_type", TradeCommentRecordTypeEnum.店铺.getCode());
        boost.orderByDesc("cdate");
        List<TradeCommentRecord> commentRecordList = repository.list(boost);


        //获取申诉驳回理由
        QueryWrapper<TradeCommentRecord> wrapperBoost = MybatisPlusUtil.query();
        wrapperBoost.eq("comment_id",dto.getId());
        wrapperBoost.ne("appeal_state", TradeAppealStateEnum.未申诉.getCode());
        wrapperBoost.eq("record_type",TradeCommentRecordTypeEnum.平台.getCode());
        wrapperBoost.orderByDesc("cdate");
        List<TradeCommentRecord> commentRecords = repository.list(wrapperBoost);

        if (ObjectUtils.isEmpty(commentRecordList) || (ObjectUtils.isNotEmpty(commentRecordList) && commentRecordList.size() == 2 && ObjectUtils.isEmpty(commentRecords))){
            throw new BusinessException("数据异常！！");
        }
        TradeCommentRecordVO.AppealRecordDetailVO recordDetailVO = new TradeCommentRecordVO.AppealRecordDetailVO();

        //一次申诉零次审核或一次申诉一次审核
        if (commentRecordList.size() == 1){
            recordDetailVO.setFirstAppealContent(commentRecordList.get(0).getContent());
            if (ObjectUtils.isNotEmpty(commentRecords) && commentRecords.size() == 1){
                recordDetailVO.setFirstRefuseIdea(commentRecords.get(0).getContent());

            }
            List<TradeCommentImgVO.ListVO> listVOS = getImgListVO(dto.getId(),TradeCommentImgBelongEnum.初次申诉.getCode());
            if (ObjectUtils.isNotEmpty(listVOS)){
                recordDetailVO.setFirstAppealImgs(listVOS);
            }
        }
        //二次申诉一次审核
        if (commentRecordList.size() == 2 && commentRecords.size() == 1){
            recordDetailVO.setFirstAppealContent(commentRecordList.get(1).getContent());
            recordDetailVO.setSecondAppealContent(commentRecordList.get(0).getContent());
            List<TradeCommentImgVO.ListVO> firstListVOS = getImgListVO(dto.getId(),TradeCommentImgBelongEnum.初次申诉.getCode());
            if (ObjectUtils.isNotEmpty(firstListVOS)){
                recordDetailVO.setFirstAppealImgs(firstListVOS);
            }
            List<TradeCommentImgVO.ListVO> listVOS = getImgListVO(dto.getId(),TradeCommentImgBelongEnum.再次申诉.getCode());
            recordDetailVO.setSecondAppealImgs(listVOS);
            recordDetailVO.setFirstRefuseIdea(commentRecords.get(0).getContent());
        }

        //二次申诉二次审核
        if (commentRecordList.size() == 2 && commentRecords.size() == 2){
            recordDetailVO.setFirstAppealContent(commentRecordList.get(1).getContent());
            recordDetailVO.setSecondAppealContent(commentRecordList.get(0).getContent());
            recordDetailVO.setFirstRefuseIdea(commentRecords.get(1).getContent());
            recordDetailVO.setSecondRefuseIdea(commentRecords.get(0).getContent());

            List<TradeCommentImgVO.ListVO> firstListVOS = getImgListVO(dto.getId(),TradeCommentImgBelongEnum.初次申诉.getCode());
            if (ObjectUtils.isNotEmpty(firstListVOS)){
                recordDetailVO.setFirstAppealImgs(firstListVOS);
            }
            List<TradeCommentImgVO.ListVO> listVOS = getImgListVO(dto.getId(),TradeCommentImgBelongEnum.再次申诉.getCode());
            recordDetailVO.setSecondAppealImgs(listVOS);
        }

        recordDetailVO.setAppealState(commentRecordList.get(0).getAppealState());
        recordDetailVO.setAppealUdate(commentRecordList.get(0).getUdate());
        return recordDetailVO;
    }

    private List<TradeCommentImgVO.ListVO> getImgListVO(String commentId,Integer belong){
        TradeCommentImgQTO.QTO qto = new TradeCommentImgQTO.QTO();
        qto.setCommentId(commentId);
        qto.setCommentImgBelong(belong);
        if (belong.intValue() == TradeCommentImgBelongEnum.再次申诉.getCode().intValue()){
            if (ObjectUtils.isEmpty(imgService.listImageVO(qto))){
                throw new BusinessException("二次申诉图片凭证数据异常");
            }
        }
        if (ObjectUtils.isNotEmpty(imgService.listImageVO(qto))){
            return imgService.listImageVO(qto);
        }
        return new ArrayList<>();
    }

}
