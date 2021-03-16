package com.gs.lshly.biz.support.foundation.service.common.impl;

import com.gs.lshly.biz.support.foundation.entity.AccessLog;
import com.gs.lshly.biz.support.foundation.repository.IAccessLogRepository;
import com.gs.lshly.biz.support.foundation.service.common.IAccessLogService;
import com.gs.lshly.common.struct.log.AccessLogDTO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessLogServiceImpl implements IAccessLogService {

    @Autowired
    private IAccessLogRepository repository;

    @Override
    public void save(AccessLogDTO dto) {
        AccessLog accessLog = new AccessLog();
        BeanCopyUtils.copyProperties(dto, accessLog);
        accessLog.setUserId(dto.getJwtUserId()).setUserName(dto.getJwtUserName())
                .setUserType(dto.getJwtUserType()).setMerchantId(dto.getJwtMerchantId())
                .setShopId(dto.getJwtShopId()).setWxOpenid(dto.getJwtWxOpenid());
        repository.save(accessLog);
    }
}
