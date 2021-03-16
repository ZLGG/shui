package com.gs.lshly.biz.support.foundation.repository.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.foundation.entity.DataNoticeRecv;
import com.gs.lshly.biz.support.foundation.mapper.DataNoticeRecvMapper;
import com.gs.lshly.biz.support.foundation.repository.IDataNoticeRecvRepository;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  仓储服务
 * </p>
 * @author lxus
 * @since 2020-09-14
 */
@Component
public class DataNoticeRecvRepositoryImpl extends ServiceImpl<DataNoticeRecvMapper, DataNoticeRecv> implements IDataNoticeRecvRepository {

}
