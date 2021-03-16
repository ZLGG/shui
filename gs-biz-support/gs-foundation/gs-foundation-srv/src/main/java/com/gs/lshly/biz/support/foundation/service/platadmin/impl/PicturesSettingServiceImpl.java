package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.gs.lshly.biz.support.foundation.entity.PicturesSetting;
import com.gs.lshly.biz.support.foundation.repository.IPicturesSettingRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.IPicturesSettingService;
import com.gs.lshly.common.struct.platadmin.foundation.dto.PicturesSettingDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.PicturesSettingVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-10-21
*/
@Component
public class PicturesSettingServiceImpl implements IPicturesSettingService {

    @Autowired
    private IPicturesSettingRepository repository;

    @Override
    public List<PicturesSettingVO.ListVO> listPicturesSetting() {
        List<PicturesSetting> picturesSettings = repository.list();
        if (ObjectUtils.isNotEmpty(picturesSettings)){
            List<PicturesSettingVO.ListVO> listVOS = new ArrayList<>();
            for (PicturesSetting setting:picturesSettings) {
                PicturesSettingVO.ListVO listVO = new PicturesSettingVO.ListVO();
                BeanUtils.copyProperties(setting,listVO);
                listVOS.add(listVO);
            }
            return listVOS;
        }
        return null;
    }

    @Override
    public void addPicturesSetting(List<PicturesSettingDTO.ETO> eto) {
        List<PicturesSetting> settings = new ArrayList<>();
        for (PicturesSettingDTO.ETO setting:eto) {
            PicturesSetting picturesSetting = new PicturesSetting();
            BeanUtils.copyProperties(setting, picturesSetting);
            settings.add(picturesSetting);
        }
        repository.saveOrUpdateBatch(settings);
    }

}
