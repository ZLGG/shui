package com.gs.lshly.biz.support.foundation.service.platadmin.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.foundation.entity.SysDataMessage;
import com.gs.lshly.biz.support.foundation.repository.ISysDataMessageRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISysDataMessageService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SysDataMessageDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SysDataMessageQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SysDataMessageVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
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
public class SysDataMessageServiceImpl implements ISysDataMessageService {

    @Autowired
    private ISysDataMessageRepository repository;

    @Override
    public void delete(SysDataMessageDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }

    @Override
    public SysDataMessageVO.DetailVO detail(SysDataMessageDTO.IdDTO dto) {
        SysDataMessage message = repository.getById(dto);
        if(ObjectUtils.isEmpty(message)){
            throw new BusinessException("没有数据");
        }
        SysDataMessageVO.DetailVO vo = new SysDataMessageVO.DetailVO();
        BeanUtils.copyProperties(message, vo);
        return vo;
    }

    @Override
    public PageData<SysDataMessageVO.ListVO> pageData(SysDataMessageQTO.QTO qoDTO) {
        QueryWrapper<SysDataMessage> wq =  MybatisPlusUtil.query();
        IPage<SysDataMessage> page = MybatisPlusUtil.pager(qoDTO);
        repository.page(page, wq);
        return MybatisPlusUtil.toPageData(qoDTO, SysDataMessageVO.ListVO.class, page);
    }

    @Override
    public void saveSysDataMessage(SysDataMessageDTO.ETO dto) {
        SysDataMessage DataMessage = new SysDataMessage();
        dto.setSendTime(LocalDateTime.now());
        BeanUtils.copyProperties(dto, DataMessage);
        repository.save(DataMessage);

    }

}
