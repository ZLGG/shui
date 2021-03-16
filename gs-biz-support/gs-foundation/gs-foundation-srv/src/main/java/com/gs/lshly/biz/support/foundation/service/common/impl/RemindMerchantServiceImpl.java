package com.gs.lshly.biz.support.foundation.service.common.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.RemindMerchant;
import com.gs.lshly.biz.support.foundation.repository.IRemindMerchantRepository;
import com.gs.lshly.biz.support.foundation.service.common.IRemindMerchantService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.dto.RemindMerchantDTO;
import com.gs.lshly.common.struct.common.qto.RemindMerchantQTO;
import com.gs.lshly.common.struct.common.vo.RemindMerchantVO;
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
public class RemindMerchantServiceImpl implements IRemindMerchantService {

    @Autowired
    private IRemindMerchantRepository repository;

    @Override
    public PageData<RemindMerchantVO.ListVO> pageData(RemindMerchantQTO.QTO qto) {
        QueryWrapper<RemindMerchant> wrapper = new QueryWrapper<>();
        IPage<RemindMerchant> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, RemindMerchantVO.ListVO.class, page);
    }

    @Override
    public void addRemindMerchant(RemindMerchantDTO.ETO eto) {
        RemindMerchant remindMerchant = new RemindMerchant();
        BeanUtils.copyProperties(eto, remindMerchant);
        repository.save(remindMerchant);
    }


    @Override
    public void deleteRemindMerchant(RemindMerchantDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }

    @Override
    public void editRemindMerchant(String id,RemindMerchantDTO.ETO eto) {
        RemindMerchant remindMerchant = new RemindMerchant();
        BeanUtils.copyProperties(eto, remindMerchant);
        repository.updateById(remindMerchant);
    }

    @Override
    public RemindMerchantVO.DetailVO detailRemindMerchant(RemindMerchantDTO.IdDTO dto) {
        RemindMerchant remindMerchant = repository.getById(dto.getId());
        RemindMerchantVO.DetailVO detailVo = new RemindMerchantVO.DetailVO();
        if(ObjectUtils.isEmpty(remindMerchant)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(remindMerchant, detailVo);
        return detailVo;
    }

}
