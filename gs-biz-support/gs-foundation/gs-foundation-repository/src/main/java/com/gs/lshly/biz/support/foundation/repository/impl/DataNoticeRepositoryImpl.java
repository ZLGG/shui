package com.gs.lshly.biz.support.foundation.repository.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.foundation.entity.DataNotice;
import com.gs.lshly.biz.support.foundation.mapper.DataNoticeMapper;
import com.gs.lshly.biz.support.foundation.repository.IDataNoticeRepository;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  仓储服务
 * </p>
 * @author lxus
 * @since 2020-09-14
 */
@Component
public class DataNoticeRepositoryImpl extends ServiceImpl<DataNoticeMapper, DataNotice> implements IDataNoticeRepository {

}
