package com.gs.lshly.biz.support.foundation.service.platadmin.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.DataNoticeType;
import com.gs.lshly.biz.support.foundation.repository.IDataNoticeTypeRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.IDataNoticeTypeService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataNoticeTypeDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataNoticeTypeQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataNoticeTypeVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.prefs.BackingStoreException;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lxus
 * @since 2020-09-14
 */
@Component
public class DataNoticeTypeServiceImpl implements IDataNoticeTypeService {

    @Autowired
    private IDataNoticeTypeRepository repository;

    @Override
    public PageData<DataNoticeTypeVO.ListVO> pageData(DataNoticeTypeQTO.QTO qoDTO) {
        QueryWrapper<DataNoticeType> wq =  MybatisPlusUtil.query();
        IPage<DataNoticeType> page = MybatisPlusUtil.pager(qoDTO);
        repository.page(page, wq);
        return MybatisPlusUtil.toPageData(qoDTO, DataNoticeTypeVO.ListVO.class, page);
    }

    @Override
    public void saveDataNoticeType(DataNoticeTypeDTO.ETO dto) {
        QueryWrapper<DataNoticeType> countWrapper =  MybatisPlusUtil.query();
        countWrapper.eq("notice_type_name",dto.getNoticeTypeName());
        int count =  repository.count(countWrapper);
        if(count > 0){
            throw new BusinessException("通知类型名已存在");
        }
        DataNoticeType DataMessage = new DataNoticeType();
        BeanUtils.copyProperties(dto, DataMessage);
        repository.save(DataMessage);
    }

    @Override
    public void editDataNoticeType(DataNoticeTypeDTO.EditNoticeTypeDTO dto) {
        DataNoticeType dataNoticeType = new DataNoticeType();
        BeanUtils.copyProperties(dto, dataNoticeType);
        repository.updateById(dataNoticeType);
    }

    @Override
    public void delete(DataNoticeTypeDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }

    @Override
    public DataNoticeTypeVO.DetailVO detail(DataNoticeTypeDTO.IdDTO dto) {
        DataNoticeType message = repository.getById(dto);
        DataNoticeTypeVO.DetailVO vo = new DataNoticeTypeVO.DetailVO();
        BeanUtils.copyProperties(message, vo);
        return vo;
    }
}
