package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.SettingsPayMethod;
import com.gs.lshly.biz.support.foundation.repository.ISettingsPayMethodRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISettingsPayMethodService;
import com.gs.lshly.common.enums.PayMethodTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsPayMethodDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SettingsPayMethodQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsPayMethodVO;
import com.gs.lshly.common.utils.EnumUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

/**
* <p>
*  服务实现类
* </p>
* @author 陈奇
* @since 2020-10-12
*/
@Component
public class SettingsPayMethodServiceImpl implements ISettingsPayMethodService {

    @Autowired
    private ISettingsPayMethodRepository repository;

    @Override
    public PageData<SettingsPayMethodVO.ListVO> pageData(SettingsPayMethodQTO.QTO qto) {
        if(!EnumUtil.checkEnumCode(qto.getSupport(), PayMethodTypeEnum.class)){
            throw new BusinessException("支付支持类型枚举错误");
        }
        QueryWrapper<SettingsPayMethod> queryWrapper = MybatisPlusUtil.query();
        if(!PayMethodTypeEnum.全部.getCode().equals(qto.getSupport())){
            queryWrapper.eq("support",qto.getSupport());
        }
        IPage<SettingsPayMethod> page = MybatisPlusUtil.pager(qto);
        repository.page(page, queryWrapper);
        return MybatisPlusUtil.toPageData(qto, SettingsPayMethodVO.ListVO.class, page);
    }

    @Override
    public void addSettingsPayMethod(SettingsPayMethodDTO.ETO eto) {
        SettingsPayMethod settingsPayMethod = new SettingsPayMethod();
        BeanUtils.copyProperties(eto, settingsPayMethod);
        settingsPayMethod.setId(null);
        repository.save(settingsPayMethod);
    }

    @Override
    public SettingsPayMethodVO.DetailVO detailSettingsPayMethod(SettingsPayMethodDTO.IdDTO dto) {
        SettingsPayMethod settingsPayMethod = repository.getById(dto.getId());
        SettingsPayMethodVO.DetailVO detailVo = new SettingsPayMethodVO.DetailVO();
        if(null == settingsPayMethod){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(settingsPayMethod, detailVo);
        return detailVo;
    }

    @Override
    public void editor(SettingsPayMethodDTO.ETO dto) {
        SettingsPayMethod settingsPayMethod = repository.getById(dto.getId());
        if(null == settingsPayMethod){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(dto, settingsPayMethod);
        repository.updateById(settingsPayMethod);
    }

    @Override
    public void deleteBatch(SettingsPayMethodDTO.IdListDTO dto) {
        if(ObjectUtils.isEmpty(dto.getIdList())){
            throw new BusinessException("删除的数据不能为空");
        }
        repository.removeByIds(dto.getIdList());
    }
}
