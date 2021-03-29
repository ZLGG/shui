package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.SiteAdvertPopup;
import com.gs.lshly.biz.support.foundation.repository.ISiteAdvertPopupRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteAdvertPopupService;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSitePopupVO.DetailVO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteAdvertPopupDTO.ETO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteAdvertPopupDTO.IdDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteAdvertPopupDTO.OnoffDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteAdvertPopupDTO.PopupDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteAdvertPopupQTO.BBBPCQTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteAdvertPopupQTO.QTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteAdvertPopupVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteAdvertPopupVO.PCDetailVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteAdvertPopupVO.PCListVO;
import com.gs.lshly.common.utils.BeanUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月10日 上午3:05:06
 */
@Component
public class SiteAdvertPopupServiceImpl implements ISiteAdvertPopupService {

    @Autowired
    private ISiteAdvertPopupRepository repository;

	@Override
	public PageData<PCListVO> pageData(QTO qto) {
		QueryWrapper<SiteAdvertPopup> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("terminal", qto.getTerminal());
        wrapper.orderByDesc("id");
        IPage<SiteAdvertPopup> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, SiteAdvertPopupVO.PCListVO.class, page);
    
	}

	@Override
	public void editor(ETO eto) {
		SiteAdvertPopup siteAdvertPopup = new SiteAdvertPopup();
		BeanUtils.copyProperties(eto, siteAdvertPopup);
		
		if(siteAdvertPopup.getOnoff().equals(TrueFalseEnum.是.getCode())){	//上架
			//查询库中是否有上架的数据
			QueryWrapper<SiteAdvertPopup> wrapper = MybatisPlusUtil.query();
	        wrapper.eq("onoff",TrueFalseEnum.是.getCode());
	        SiteAdvertPopup onoffEntity = repository.getOne(wrapper);
	        
	        if(StringUtils.isNotEmpty(siteAdvertPopup.getId())){
	        	if(onoffEntity!=null&&!onoffEntity.getId().equals(siteAdvertPopup.getId()))
	        		throw new BusinessException("上线弹窗只能一个！");
	        }else{
	        	if(onoffEntity!=null)
	        		throw new BusinessException("上线弹窗只能一个！");
	        }
		}
		
		repository.saveOrUpdate(siteAdvertPopup);
	}

	@Override
	public PCDetailVO get(IdDTO dto) {
		if (dto.getId() == null){
            throw new BusinessException("参数不能为空！");
        }
        SiteAdvertPopup siteAdvertPopup = repository.getById(dto.getId());
        if (siteAdvertPopup == null){
            throw new BusinessException("查询异常！");
        }
        SiteAdvertPopupVO.PCDetailVO detailVO = new SiteAdvertPopupVO.PCDetailVO();
        BeanUtils.copyProperties(siteAdvertPopup,detailVO);
		return detailVO;
	}

	@Override
	public void delete(IdDTO dto) {
        repository.removeById(dto);
		
	}

	@Override
	public void onoff(OnoffDTO dto) {
		if (dto.getId() == null){
            throw new BusinessException("参数不能为空！");
        }
        SiteAdvertPopup siteAdvertPopup = repository.getById(dto.getId());
        if (siteAdvertPopup == null){
            throw new BusinessException("查询异常！");
        }
        
        if(siteAdvertPopup.getOnoff().equals(TrueFalseEnum.是.getCode())){	//上架
			//查询库中是否有上架的数据
			QueryWrapper<SiteAdvertPopup> wrapper = MybatisPlusUtil.query();
	        wrapper.eq("onoff",TrueFalseEnum.是.getCode());
	        SiteAdvertPopup onoffEntity = repository.getOne(wrapper);

        	if(onoffEntity!=null&&!onoffEntity.getId().equals(siteAdvertPopup.getId()))
        		throw new BusinessException("上线弹窗只能一个！");
	        
		}
        siteAdvertPopup.setOnoff(dto.getOnoff());
        repository.saveOrUpdate(siteAdvertPopup);
	}

	@Override
	public DetailVO getPopup(BBBPCQTO qto) {
		QueryWrapper<SiteAdvertPopup> wrapper = MybatisPlusUtil.query();
        wrapper.eq("onoff",TrueFalseEnum.是.getCode());
        SiteAdvertPopup onoffEntity = repository.getOne(wrapper);
        DetailVO detailVO = new DetailVO();
        if(onoffEntity==null)
        	detailVO.setStatus(TrueFalseEnum.否.getCode());
        
        detailVO.setStatus(TrueFalseEnum.是.getCode());
        BeanUtils.copyProperties(onoffEntity, detailVO);
		return detailVO;
	}

}
