package com.gs.lshly.biz.support.foundation.service.common.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.RemindUser;
import com.gs.lshly.biz.support.foundation.repository.IRemindUserRepository;
import com.gs.lshly.biz.support.foundation.service.common.IRemindUserService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.dto.RemindUserDTO;
import com.gs.lshly.common.struct.common.qto.RemindUserQTO;
import com.gs.lshly.common.struct.common.vo.RemindUserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;


/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2021-02-05
*/
@Component
public class RemindUserServiceImpl implements IRemindUserService {

    @Autowired
    private IRemindUserRepository repository;

    @Override
    public PageData<RemindUserVO.ListVO> pageData(RemindUserQTO.QTO qto) {
        QueryWrapper<RemindUser> wrapper = new QueryWrapper<>();
        IPage<RemindUser> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, RemindUserVO.ListVO.class, page);
    }

    @Override
    public void addRemindUser(RemindUserDTO.ETO eto) {
        RemindUser remindUser = new RemindUser();
        BeanUtils.copyProperties(eto, remindUser);
        repository.save(remindUser);
    }


    @Override
    public void deleteRemindUser(RemindUserDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }

    @Override
    public void editRemindUser(String id,RemindUserDTO.ETO eto) {
        RemindUser remindUser = new RemindUser();
        BeanUtils.copyProperties(eto, remindUser);
        repository.updateById(remindUser);
    }

    @Override
    public RemindUserVO.DetailVO detailRemindUser(RemindUserDTO.IdDTO dto) {
        RemindUser remindUser = repository.getById(dto.getId());
        RemindUserVO.DetailVO detailVo = new RemindUserVO.DetailVO();
        if(ObjectUtils.isEmpty(remindUser)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(remindUser, detailVo);
        return detailVo;
    }

}
