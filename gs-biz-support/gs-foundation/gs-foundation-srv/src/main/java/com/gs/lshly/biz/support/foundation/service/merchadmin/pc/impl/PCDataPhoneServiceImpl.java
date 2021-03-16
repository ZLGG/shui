package com.gs.lshly.biz.support.foundation.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.foundation.entity.DataPhone;
import com.gs.lshly.biz.support.foundation.repository.IDataPhoneRepository;
import com.gs.lshly.biz.support.foundation.service.merchadmin.pc.IPCDataPhoneService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataPhoneDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataPhoneQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataPhoneVO;
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
* @since 2021-01-28
*/
@Component
public class PCDataPhoneServiceImpl implements IPCDataPhoneService {

    @Autowired
    private IDataPhoneRepository repository;


    @Override
    public void saveDataPhone(DataPhoneDTO.ETO eto) {
        if (null == eto || StringUtils.isBlank(eto.getPhone())){
            throw new BusinessException("手机号码不能为空！！");
        }
        QueryWrapper<DataPhone> dataPhoneQueryWrapper = MybatisPlusUtil.query();
        DataPhone dataPhone = repository.getOne(dataPhoneQueryWrapper);
        DataPhone phone = new DataPhone();
        if (null != dataPhone){
            phone.setId(dataPhone.getId());
        }
        phone.setPhone(eto.getPhone());
        repository.saveOrUpdate(phone);
    }

    @Override
    public DataPhoneVO.DetailVO detailDataPhone(BaseDTO dto) {
        QueryWrapper<DataPhone> dataPhoneQueryWrapper = MybatisPlusUtil.query();
        DataPhone dataPhone = repository.getOne(dataPhoneQueryWrapper);
        if(null == dataPhone){
            return null;
        }
        DataPhoneVO.DetailVO detailVO = new DataPhoneVO.DetailVO();
        BeanUtils.copyProperties(dataPhone, detailVO);
        return detailVO;
    }

}
