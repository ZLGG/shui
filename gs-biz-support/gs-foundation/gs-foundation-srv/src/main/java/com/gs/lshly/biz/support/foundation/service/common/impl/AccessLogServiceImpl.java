package com.gs.lshly.biz.support.foundation.service.common.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.AccessLog;
import com.gs.lshly.biz.support.foundation.repository.IAccessLogRepository;
import com.gs.lshly.biz.support.foundation.service.common.IAccessLogService;
import com.gs.lshly.common.enums.UserTypeEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.log.AccessLogDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SysAccessLogQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SysAccessLogVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
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

    @Override
    public PageData<SysAccessLogVO.MerchantLogin> merchantLoginLogs(SysAccessLogQTO.MerchantLogin qto) {
        QueryWrapper<AccessLog> wq =  MybatisPlusUtil.query();
        MybatisPlusUtil.like(qto, wq, "userId");
        MybatisPlusUtil.like(qto, wq, "userName");
        MybatisPlusUtil.like(qto, wq, "shopId");
        wq.in("user_type", ListUtil.of(UserTypeEnum._2B商家主账号.getCode(), UserTypeEnum._2B商家子账号,
                UserTypeEnum._2C商家主账号.getCode(), UserTypeEnum._2C商家子账号));
        wq.eq("module", "登陆");
        wq.eq("func", "商家登陆");
        wq.orderByDesc("cdate");
        IPage<AccessLog> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wq);
        return MybatisPlusUtil.toPageData(qto, SysAccessLogVO.MerchantLogin.class, page);
    }

    @Override
    public void delete(SysAccessLogQTO.IdDTO dto) {
        if (dto != null && dto.getIds() != null && dto.getIds().size() > 0) {
            repository.removeByIds(dto.getIds());
        }
    }

    @Override
    public PageData<SysAccessLogVO.OperatorLogin> operatorLoginLogs(SysAccessLogQTO.Operator qto) {
        QueryWrapper<AccessLog> wq =  MybatisPlusUtil.query();
        MybatisPlusUtil.like(qto, wq, "userId");
        MybatisPlusUtil.like(qto, wq, "userName");
        wq.in("user_type", ListUtil.of(UserTypeEnum.平台超管账号.getCode(),UserTypeEnum.平台运营账号.getCode()));
        wq.eq("module", "登陆");
        wq.eq("func", "平台登陆");
        wq.orderByDesc("cdate");
        IPage<AccessLog> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wq);
        return MybatisPlusUtil.toPageData(qto, SysAccessLogVO.OperatorLogin.class, page);
    }

    @Override
    public PageData<SysAccessLogVO.Operator> operatorWithoutLoginLogs(SysAccessLogQTO.Operator qto) {
        QueryWrapper<AccessLog> wq =  MybatisPlusUtil.query();
        MybatisPlusUtil.like(qto, wq, "userId");
        MybatisPlusUtil.like(qto, wq, "userName");
        wq.in("user_type", ListUtil.of(UserTypeEnum.平台超管账号.getCode(),UserTypeEnum.平台运营账号.getCode()));
        wq.ne("module", "登陆");
        wq.orderByDesc("cdate");
        IPage<AccessLog> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wq);
        return MybatisPlusUtil.toPageData(qto, SysAccessLogVO.Operator.class, page);
    }

    @Override
    public ExportDataDTO export(SysAccessLogQTO.Operator qto) {
        return null;
    }

    @Override
    public PageData<SysAccessLogVO.UserLogin> userLoginLogs(SysAccessLogQTO.UserLogin qto) {
        QueryWrapper<AccessLog> wq =  MybatisPlusUtil.query();
        MybatisPlusUtil.like(qto, wq, "userId");
        MybatisPlusUtil.like(qto, wq, "userName");
        wq.in("user_type", ListUtil.of(UserTypeEnum._2C用户.getCode(), UserTypeEnum._2B用户.getCode()));
        wq.eq("module", "登陆");
        wq.in("func", ListUtil.of("2C-手机验证码", "2C-小程序", "2B-手机验证码", "2B-小程序", "PC-账号密码", "PC-手机验证码", "2B-扫码"));
        wq.orderByDesc("cdate");
        IPage<AccessLog> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wq);
        PageData<SysAccessLogVO.UserLogin> result = MybatisPlusUtil.toPageData(qto, SysAccessLogVO.UserLogin.class, page);
        for (SysAccessLogVO.UserLogin record : result.getContent()) {
            if (StrUtil.isNotBlank(record.getFunc())) {
                String[] platAndType = record.getFunc().split("-");
                if (platAndType.length == 2) {
                    record.setLoginPlatform(platAndType[0]).setLoginType(platAndType[1]);
                }
            }
        }
        return result;
    }
}
