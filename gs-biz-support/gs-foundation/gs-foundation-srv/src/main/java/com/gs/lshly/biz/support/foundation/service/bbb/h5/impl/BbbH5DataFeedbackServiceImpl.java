package com.gs.lshly.biz.support.foundation.service.bbb.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.foundation.entity.DataFeedback;
import com.gs.lshly.biz.support.foundation.repository.IDataFeedbackRepository;
import com.gs.lshly.biz.support.foundation.service.bbb.h5.IBbbH5DataFeedbackService;
import com.gs.lshly.common.enums.OperatorTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.foundation.dto.BbbH5DataFeedbackDTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5DataFeedbackQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5DataFeedbackVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
* <p>
*  服务实现类
* </p>
* @author 陈奇
* @since 2020-10-24
*/
@Component
public class BbbH5DataFeedbackServiceImpl implements IBbbH5DataFeedbackService {

    @Autowired
    private IDataFeedbackRepository repository;

    @Override
    public PageData<BbbH5DataFeedbackVO.ListVO> pageData(BbbH5DataFeedbackQTO.QTO qto) {
        if(StringUtils.isBlank(qto.getJwtUserId())){
            throw new BusinessException("没有登录");
        }
        QueryWrapper<DataFeedback> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("fb_operator_id",qto.getJwtUserId());
        wrapper.eq("fb_operator_type",OperatorTypeEnum.会员.getCode());
        IPage<DataFeedback> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, BbbH5DataFeedbackVO.ListVO.class, page);
    }

    @Override
    public void addDataFeedback(BbbH5DataFeedbackDTO.ETO eto) {
        if(StringUtils.isBlank(eto.getJwtUserId())){
            throw new BusinessException("没有登录");
        }
        DataFeedback dataFeedback = new DataFeedback();
        BeanUtils.copyProperties(eto, dataFeedback);
        dataFeedback.setFbType(eto.getFbType());
        dataFeedback.setFbOperatorType(OperatorTypeEnum.会员.getCode());
        dataFeedback.setFbOperatorName(eto.getJwtUserName());
        dataFeedback.setFbOperatorTime(LocalDateTime.now());
        dataFeedback.setFbOperatorId(eto.getJwtUserId());
        repository.save(dataFeedback);
    }

}
