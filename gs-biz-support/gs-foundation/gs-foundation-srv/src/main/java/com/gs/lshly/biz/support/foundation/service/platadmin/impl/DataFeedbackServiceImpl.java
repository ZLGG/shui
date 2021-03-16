package com.gs.lshly.biz.support.foundation.service.platadmin.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.foundation.entity.DataFeedback;
import com.gs.lshly.biz.support.foundation.enums.FeedbackHanderQueryEnum;
import com.gs.lshly.biz.support.foundation.enums.FeedbackQueryTypeEnum;
import com.gs.lshly.biz.support.foundation.repository.IDataFeedbackRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.IDataFeedbackService;
import com.gs.lshly.common.enums.OperatorTypeEnum;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataFeedbackDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataFeedbackQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataFeedbackVO;
import com.gs.lshly.common.utils.EnumUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lxus
 * @since 2020-09-14
 */
@Component
public class DataFeedbackServiceImpl implements IDataFeedbackService {

    @Autowired
    private IDataFeedbackRepository repository;

    @Override
    public void delete(DataFeedbackDTO.IdListDTO dto) {
        if(ObjectUtils.isNotEmpty(dto.getIdList())){
            repository.removeByIds(dto.getIdList());
        }
    }

    @Override
    public DataFeedbackVO.DetailVO detail(DataFeedbackDTO.IdDTO dto) {
        DataFeedback byId = repository.getById(dto.getId());
        DataFeedbackVO.DetailVO detailVO = new DataFeedbackVO.DetailVO();
        BeanUtils.copyProperties(byId, detailVO);
        return detailVO;
    }

    @Override
    public PageData<DataFeedbackVO.ListVO> pageData(DataFeedbackQTO.QTO qoDTO) {
        if(! EnumUtil.checkEnumCode(qoDTO.getOperatorType(),OperatorTypeEnum.class)){
            throw new BusinessException("反馈参考者类型不存在");
        }
        QueryWrapper<DataFeedback> queryWrapper = MybatisPlusUtil.query();
        if(StringUtils.isNotBlank(qoDTO.getQueryValue())) {
            if (FeedbackQueryTypeEnum.用户名.getCode().equals(qoDTO.getQueryType())) {
                queryWrapper.like("fb_operator_name", qoDTO.getQueryValue());
            } else if (FeedbackQueryTypeEnum.电话.getCode().equals(qoDTO.getQueryType())) {
                queryWrapper.like("fb_contact", qoDTO.getQueryValue());
            } else if (FeedbackQueryTypeEnum.邮箱.getCode().equals(qoDTO.getQueryType())) {
                queryWrapper.like("fb_email", qoDTO.getQueryValue());
            }
        }

        if(null != qoDTO.getState() && !FeedbackHanderQueryEnum.全部状态.getCode().equals(qoDTO.getState())){
            queryWrapper.eq("fb_hander_state",qoDTO.getState());
        }
        queryWrapper.eq("fb_operator_type", qoDTO.getOperatorType());
        IPage<DataFeedback> page = MybatisPlusUtil.pager(qoDTO);
        repository.page(page, queryWrapper);
        return MybatisPlusUtil.toPageData(qoDTO, DataFeedbackVO.ListVO.class, page);
    }

    @Override
    public void saveDataFeedback(DataFeedbackDTO.ETO dto) {
        DataFeedback dataFeedback= new DataFeedback();
        dto.setFbOperatorTime(LocalDateTime.now());
        BeanUtils.copyProperties(dto, dataFeedback);
        dataFeedback.setFbHanderState(TrueFalseEnum.否.getCode());
        dataFeedback.setFbOperatorTime(LocalDateTime.now());
        repository.save(dataFeedback);
    }

    @Override
    public void handleDataFeedback(DataFeedbackDTO.HanderDTO dto) {
        DataFeedback dataFeedback = repository.getById(dto.getId());
        if(null == dataFeedback){
            throw new BusinessException("意见反馈不存在");
        }
        BeanUtils.copyProperties(dto, dataFeedback);
        dataFeedback.setFbHanderState(TrueFalseEnum.是.getCode());
        dataFeedback.setFbResultTime(LocalDateTime.now());
        repository.updateById(dataFeedback);
    }

}
