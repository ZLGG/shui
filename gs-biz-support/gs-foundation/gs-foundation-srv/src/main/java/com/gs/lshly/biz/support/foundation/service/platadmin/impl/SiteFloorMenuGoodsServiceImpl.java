package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.foundation.entity.SiteFloorMenuGoods;
import com.gs.lshly.biz.support.foundation.repository.ISiteFloorMenuGoodsRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteFloorMenuGoodsService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteFloorMenuGoodsDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteFloorMenuGoodsQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorMenuGoodsVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author 大飞船
* @since 2020-09-29
*/
@Component
public class SiteFloorMenuGoodsServiceImpl implements ISiteFloorMenuGoodsService {

    @Autowired
    private ISiteFloorMenuGoodsRepository repository;

    @Override
    public PageData<SiteFloorMenuGoodsVO.ListVO> pageData(SiteFloorMenuGoodsQTO.QTO qto) {
        QueryWrapper<SiteFloorMenuGoods> wq =  MybatisPlusUtil.query();
        IPage<SiteFloorMenuGoods> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wq);
        return MybatisPlusUtil.toPageData(qto, SiteFloorMenuGoodsVO.ListVO.class, page);
    }

    @Override
    public void addSiteFloorMenuGoods(SiteFloorMenuGoodsDTO.ADTO eto) {
        SiteFloorMenuGoods siteFloorMenuGoods = new SiteFloorMenuGoods();
        BeanUtils.copyProperties(eto, siteFloorMenuGoods);
        repository.save(siteFloorMenuGoods);
    }


    @Override
    public void deleteSiteFloorMenuGoods(SiteFloorMenuGoodsDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }


    @Override
    public void editSiteFloorMenuGoods(SiteFloorMenuGoodsDTO.ADTO eto) {
        SiteFloorMenuGoods siteFloorMenuGoods = new SiteFloorMenuGoods();
        BeanUtils.copyProperties(eto, siteFloorMenuGoods);
        repository.updateById(siteFloorMenuGoods);
    }

    /**
     * 根据“楼层菜单ID”获取商品
     * @param qto
     * @return
     */
    @Override
    public List<SiteFloorMenuGoodsVO.GoodsIdVO> selectByFloorMenuId(SiteFloorMenuGoodsQTO.FloorMenuIdQTO qto) {
        QueryWrapper<SiteFloorMenuGoods> queryWrapper =  MybatisPlusUtil.query();
        queryWrapper.eq("floor_menu_id",qto.getFloorMenuId());
        List<SiteFloorMenuGoods> list = repository.list(queryWrapper);
        List<SiteFloorMenuGoodsVO.GoodsIdVO> voList=
                qto.toListData(SiteFloorMenuGoodsVO.GoodsIdVO.class,list);

        return voList;
    }

}
