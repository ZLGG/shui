package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.SiteNotice;
import com.gs.lshly.biz.support.foundation.repository.ISiteNoticeRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteNoticeService;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteNoticeDTO.ETO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteNoticeDTO.IdDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteNoticeQTO.QTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteNoticeVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteNoticeVO.PCDetailVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteNoticeVO.PCListVO;
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
public class SiteNoticeServiceImpl implements ISiteNoticeService {

    @Autowired
    private ISiteNoticeRepository repository;

	@Override
	public PageData<PCListVO> pageData(QTO qto) {
		QueryWrapper<SiteNotice> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("terminal", qto.getTerminal());
        if(StringUtils.isNotEmpty(qto.getName())){
        	wrapper.like("name", qto.getName());
        }
        wrapper.orderByDesc("id");
        IPage<SiteNotice> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, SiteNoticeVO.PCListVO.class, page);
    
	}

	@Override
	public void editor(ETO eto) {
		SiteNotice SiteNotice = new SiteNotice();
		BeanUtils.copyProperties(eto, SiteNotice);
		
		repository.saveOrUpdate(SiteNotice);
	}

	@Override
	public PCDetailVO get(IdDTO dto) {
		if (dto.getId() == null){
            throw new BusinessException("参数不能为空！");
        }
        SiteNotice SiteNotice = repository.getById(dto.getId());
        if (SiteNotice == null){
            throw new BusinessException("查询异常！");
        }
        SiteNoticeVO.PCDetailVO detailVO = new SiteNoticeVO.PCDetailVO();
        BeanUtils.copyProperties(SiteNotice,detailVO);
		return detailVO;
	}

	@Override
	public void delete(IdDTO dto) {
        repository.removeById(dto);
		
	}

}
