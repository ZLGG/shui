package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.foundation.entity.BasicAreas;
import com.gs.lshly.biz.support.foundation.entity.Pictures;
import com.gs.lshly.biz.support.foundation.repository.IBasicAreasRepository;
import com.gs.lshly.biz.support.foundation.repository.ISysSmRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.IBasicAreasService;
import com.gs.lshly.common.struct.platadmin.foundation.vo.BasicAreasVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.BasicAreasVO.AddressListVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.PicturesVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务实现类
 *
 * @author chenyang
 */
@Component
public class BasicAreasService implements IBasicAreasService {

    @Autowired
    private IBasicAreasRepository repository;

    @Override
    public List<BasicAreasVO.DropListVO> dropList(Integer pid) {
        if (null == pid) {
            pid = 330000;
        }
        QueryWrapper<BasicAreas> query = MybatisPlusUtil.query();
        query.eq("pid", pid);
        List<BasicAreas> areas = repository.list(query);
        List<BasicAreasVO.DropListVO> listVOS = new ArrayList<>();
        BasicAreasVO.DropListVO listVO;
        if (ObjectUtils.isNotEmpty(areas)) {
            for (BasicAreas city : areas) {
                listVO = new BasicAreasVO.DropListVO();
                listVO.setId(city.getId());
                listVO.setName(city.getName());
                listVOS.add(listVO);
            }
        }
        return listVOS;
    }

	@Override
	public List<AddressListVO> addressList(Integer pid) {
		if (null == pid) {
            pid = 330000;
        }
        QueryWrapper<BasicAreas> query = MybatisPlusUtil.query();
        query.eq("pid", pid);
        List<BasicAreas> areas = repository.list(query);
        List<BasicAreasVO.AddressListVO> listVOS = new ArrayList<>();
        BasicAreasVO.AddressListVO listVO;
        if (ObjectUtils.isNotEmpty(areas)) {
            for (BasicAreas city : areas) {
                listVO = new BasicAreasVO.AddressListVO();
                listVO.setValue(city.getId());
                listVO.setText(city.getName());
                listVOS.add(listVO);
            }
        }
        return listVOS;
	}
}
