package com.gs.lshly.biz.support.foundation.repository.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.foundation.entity.DataNoticeType;
import com.gs.lshly.biz.support.foundation.mapper.DataNoticeTypeMapper;
import com.gs.lshly.biz.support.foundation.repository.IDataNoticeTypeRepository;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  仓储服务
 * </p>
 * @author lxus
 * @since 2020-09-14
 */
@Component
public class DataNoticeTypeRepositoryImpl extends ServiceImpl<DataNoticeTypeMapper, DataNoticeType> implements IDataNoticeTypeRepository {

}
