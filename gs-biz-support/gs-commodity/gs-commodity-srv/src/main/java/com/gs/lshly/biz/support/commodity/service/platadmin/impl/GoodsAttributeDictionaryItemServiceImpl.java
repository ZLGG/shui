package com.gs.lshly.biz.support.commodity.service.platadmin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.commodity.entity.GoodsAttributeDictionaryItem;
import com.gs.lshly.biz.support.commodity.repository.IGoodsAttributeDictionaryItemRepository;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsAttributeDictionaryItemService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsAttributeDictionaryItemDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsAttributeDictionaryItemQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsAttributeDictionaryItemVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2020-09-23
 */
@Service
public class GoodsAttributeDictionaryItemServiceImpl implements IGoodsAttributeDictionaryItemService {

    @Autowired
    private IGoodsAttributeDictionaryItemRepository repository;

    @Override
    public PageData<GoodsAttributeDictionaryItemVO.ListVO> listAttributeValue(GoodsAttributeDictionaryItemQTO.QTO qto) {
        QueryWrapper<GoodsAttributeDictionaryItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attribute_id",qto.getAttributeId());
        IPage<GoodsAttributeDictionaryItem> page = MybatisPlusUtil.pager(qto);
        repository.page(page, queryWrapper);
        return MybatisPlusUtil.toPageData(qto, GoodsAttributeDictionaryItemVO.ListVO.class, page);
    }

    @Override
    public void removeById(GoodsAttributeDictionaryItemDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
}
