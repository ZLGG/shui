package com.gs.lshly.biz.support.commodity.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.commodity.entity.GoodsSpecDictionaryItem;
import com.gs.lshly.biz.support.commodity.repository.IGoodsSpecDictionaryItemRepository;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsSpecDictionaryItemService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsSpecDictionaryItemDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsSpecDictionaryItemQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsSpecDictionaryItemVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2020-09-25
 */
@Service
public class GoodsSpecDictionaryItemServiceImpl implements IGoodsSpecDictionaryItemService {

    @Autowired
    private IGoodsSpecDictionaryItemRepository repository;

    @Override
    public PageData<GoodsSpecDictionaryItemVO.ListVO> listSpecItem(GoodsSpecDictionaryItemQTO.QTO qto) {
        QueryWrapper<GoodsSpecDictionaryItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("spec_id",qto.getSpecId());
        IPage<GoodsSpecDictionaryItem> page = MybatisPlusUtil.pager(qto);
        repository.page(page, queryWrapper);
        return MybatisPlusUtil.toPageData(qto, GoodsSpecDictionaryItemVO.ListVO.class,page);
    }

    @Override
    public void removeById(GoodsSpecDictionaryItemDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
}
