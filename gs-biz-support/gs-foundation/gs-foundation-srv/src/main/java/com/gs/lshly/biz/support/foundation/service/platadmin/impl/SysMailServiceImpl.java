package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.foundation.entity.SysMail;
import com.gs.lshly.biz.support.foundation.repository.ISysMailRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISysMailService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SysMailDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SysMailVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2021-03-20
*/
@Component
public class SysMailServiceImpl implements ISysMailService {

    @Autowired
    private ISysMailRepository repository;



    @Override
    public void saveSysMail(SysMailDTO.ETO eto) {
        if (null == eto ){
            throw new BusinessException("参数为空，异常！");
        }
        SysMail sysMail = new SysMail();
        BeanUtils.copyProperties(eto,sysMail);
        repository.saveOrUpdate(sysMail);

    }

    @Override
    public void deleteSysMail(SysMailDTO.IdDTO dto) {
        if (null == dto || StringUtils.isBlank(dto.getId())){
            throw new BusinessException("参数为空，异常！");
        }
        repository.removeById(dto.getId());
    }

    @Override
    public PageData<SysMailVO.ListVO> pageData(BaseQTO qto) {
        QueryWrapper<SysMail> wrapper = MybatisPlusUtil.query();
        IPage<SysMail> page = MybatisPlusUtil.pager(qto);
        IPage<SysMail> sysMailIPage = repository.page(page,wrapper);
        if (ObjectUtils.isEmpty(sysMailIPage) || ObjectUtils.isEmpty(sysMailIPage.getRecords())){
            return new PageData<>();
        }
        return MybatisPlusUtil.toPageData(qto,SysMailVO.ListVO.class,sysMailIPage);
    }
}
