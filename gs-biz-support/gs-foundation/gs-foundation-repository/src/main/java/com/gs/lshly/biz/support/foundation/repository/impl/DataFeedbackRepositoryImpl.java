package com.gs.lshly.biz.support.foundation.repository.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.foundation.entity.DataFeedback;
import com.gs.lshly.biz.support.foundation.mapper.DataFeedbackMapper;
import com.gs.lshly.biz.support.foundation.repository.IDataFeedbackRepository;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  仓储服务
 * </p>
 * @author lxus
 * @since 2020-09-14
 */
@Component
public class DataFeedbackRepositoryImpl extends ServiceImpl<DataFeedbackMapper, DataFeedback> implements IDataFeedbackRepository {

}
