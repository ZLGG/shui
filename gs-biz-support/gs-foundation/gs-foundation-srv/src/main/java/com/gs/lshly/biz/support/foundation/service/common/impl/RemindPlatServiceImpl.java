package com.gs.lshly.biz.support.foundation.service.common.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.RemindPlat;
import com.gs.lshly.biz.support.foundation.repository.IRemindPlatRepository;
import com.gs.lshly.biz.support.foundation.service.common.IRemindPlatService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.dto.RemindPlatDTO;
import com.gs.lshly.common.struct.common.qto.RemindPlatQTO;
import com.gs.lshly.common.struct.common.vo.RemindPlatVO;
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
public class RemindPlatServiceImpl implements IRemindPlatService {

    @Autowired
    private IRemindPlatRepository repository;

    @Override
    public PageData<RemindPlatVO.ListVO> pageData(RemindPlatQTO.QTO qto) {
        QueryWrapper<RemindPlat> wrapper = new QueryWrapper<>();
        IPage<RemindPlat> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, RemindPlatVO.ListVO.class, page);
    }

    @Override
    public void addRemindPlat(RemindPlatDTO.ETO eto) {
        RemindPlat remindPlat = new RemindPlat();
        BeanUtils.copyProperties(eto, remindPlat);
        repository.save(remindPlat);
    }


    @Override
    public void deleteRemindPlat(RemindPlatDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }

    @Override
    public void editRemindPlat(String id,RemindPlatDTO.ETO eto) {
        RemindPlat remindPlat = new RemindPlat();
        BeanUtils.copyProperties(eto, remindPlat);
        repository.updateById(remindPlat);
    }

    @Override
    public RemindPlatVO.DetailVO detailRemindPlat(RemindPlatDTO.IdDTO dto) {
        RemindPlat remindPlat = repository.getById(dto.getId());
        RemindPlatVO.DetailVO detailVo = new RemindPlatVO.DetailVO();
        if(ObjectUtils.isEmpty(remindPlat)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(remindPlat, detailVo);
        return detailVo;
    }

    @Override
    public void addRemindPlatForUser2bApply(RemindPlatDTO.ETO eto) {

    }

    @Override
    public void addRemindPlatForMerchantApply(RemindPlatDTO.ETO eto) {

    }

    @Override
    public void addRemindPlatForGoodsUpApply(RemindPlatDTO.ETO eto) {

    }

    @Override
    public void addRemindPlatForGoodsCategoryApply(RemindPlatDTO.ETO eto) {

    }

    @Override
    public void addRemindPlatForMerchantArticleApply(RemindPlatDTO.ETO eto) {

    }

}
