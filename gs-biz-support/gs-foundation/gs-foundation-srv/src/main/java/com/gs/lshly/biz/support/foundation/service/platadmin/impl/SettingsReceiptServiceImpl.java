package com.gs.lshly.biz.support.foundation.service.platadmin.impl;
import com.gs.lshly.biz.support.foundation.entity.SettingsReceipt;
import com.gs.lshly.biz.support.foundation.repository.ISettingsReceiptRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISettingsReceiptService;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsReceiptDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsReceiptVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* <p>
*  服务实现类
* </p>
* @author 陈奇
* @since 2020-10-12
*/
@Component
public class SettingsReceiptServiceImpl implements ISettingsReceiptService {

    @Autowired
    private ISettingsReceiptRepository repository;

    @Override
    public void addSettingsReceipt(SettingsReceiptDTO.ETO eto) {
        SettingsReceipt settingsReceipt =  repository.getOne(MybatisPlusUtil.query());
        if(null == settingsReceipt){
            settingsReceipt = new SettingsReceipt();
            BeanUtils.copyProperties(eto, settingsReceipt);
            repository.save(settingsReceipt);
        }else{
            BeanUtils.copyProperties(eto, settingsReceipt);
            repository.updateById(settingsReceipt);
        }
    }


    @Override
    public SettingsReceiptVO.DetailVO detailSettingsReceipt(BaseDTO dto) {
        SettingsReceipt settingsReceipt =  repository.getOne(MybatisPlusUtil.query());
        if(null == settingsReceipt){
            return null;
        }
        SettingsReceiptVO.DetailVO detailVo = new SettingsReceiptVO.DetailVO();
        BeanUtils.copyProperties(settingsReceipt, detailVo);
        return detailVo;
    }

}
