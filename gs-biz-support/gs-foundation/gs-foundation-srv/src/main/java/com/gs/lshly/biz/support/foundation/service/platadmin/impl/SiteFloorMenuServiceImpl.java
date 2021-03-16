package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.foundation.entity.SiteFloorMenu;
import com.gs.lshly.biz.support.foundation.entity.SiteFloorMenuGoods;
import com.gs.lshly.biz.support.foundation.repository.ISiteFloorMenuGoodsRepository;
import com.gs.lshly.biz.support.foundation.repository.ISiteFloorMenuRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteFloorMenuService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteFloorMenuDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteFloorMenuQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorMenuVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author 大飞船
* @since 2020-09-29
*/
@Component
public class SiteFloorMenuServiceImpl implements ISiteFloorMenuService {

    @Autowired
    private ISiteFloorMenuRepository repository;
    @Autowired
    private ISiteFloorMenuGoodsRepository siteFloorMenuGoodsRepository;

    /**
     * 楼层菜单顶部（与商品有关）
     * @param qto
     * @return
     */
    @Override
    public PageData<SiteFloorMenuVO.ListVO> pageData(SiteFloorMenuQTO.QTO qto) {
        QueryWrapper<SiteFloorMenu> wq =  MybatisPlusUtil.query();
        wq.eq("menu_type",qto.getMenuType())
                .eq("floor_id",qto.getFloorId());
        IPage<SiteFloorMenu> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wq);
        return MybatisPlusUtil.toPageData(qto, SiteFloorMenuVO.ListVO.class, page);
    }

    /**
     * 楼层菜单：左侧链接（可能是文章，可能是商品）
     * @param qto
     * @return
     */
    @Override
    public PageData<SiteFloorMenuVO.BListVO> pageDatalistBottom(SiteFloorMenuQTO.QTO qto) {
        QueryWrapper<SiteFloorMenu> boost = MybatisPlusUtil.query();
        boost.eq("menu_type",qto.getMenuType());
        boost.eq("floor_id",qto.getFloorId());
        IPage<SiteFloorMenu> page = MybatisPlusUtil.pager(qto);
        repository.page(page, boost);
        return MybatisPlusUtil.toPageData(qto, SiteFloorMenuVO.BListVO.class, page);
    }

    @Override
    public void addSiteFloorMenu(SiteFloorMenuDTO.ADTO eto) {
        SiteFloorMenu siteFloorMenu = new SiteFloorMenu();
        BeanUtils.copyProperties(eto, siteFloorMenu);
        repository.save(siteFloorMenu);
    }


    @Override
    public void addBatch(List<SiteFloorMenuDTO.ADTO> adtos) {
        SiteFloorMenuDTO.ADTO adto = new SiteFloorMenuDTO.ADTO();
        List<SiteFloorMenu> siteFloorMenus = adto.toListData(SiteFloorMenu.class,adtos);
        repository.saveBatch(siteFloorMenus);
    }

    /**
     * 添加底部链接
     * @param badto
     */
    @Override
    public void addSiteBottomLink(SiteFloorMenuDTO.BADTO badto) {
        SiteFloorMenu siteFloorMenu = new SiteFloorMenu();
        BeanUtils.copyProperties(badto, siteFloorMenu);
        repository.save(siteFloorMenu);
    }

    @Override
    public void addSiteBottomLinkList(List<SiteFloorMenuDTO.BADTO> badtos) {
        SiteFloorMenuDTO.BADTO badto = new SiteFloorMenuDTO.BADTO();
        List<SiteFloorMenu> siteFloorMenus = badto.toListData(SiteFloorMenu.class,badtos);
        repository.saveBatch(siteFloorMenus);
    }

    @Override
    public void editSiteBottomLink(SiteFloorMenuDTO.BUDTO budto) {
        SiteFloorMenu siteFloorMenu = new SiteFloorMenu();
        BeanUtils.copyProperties(budto, siteFloorMenu);
        repository.updateById(siteFloorMenu);
    }



    /**
     * 目的：删除楼层菜单
     * 判断条件：该菜单下是否有数据
     * @param dto :楼层菜单id
     */
    @Override
    public void deleteSiteFloorMenu(SiteFloorMenuDTO.IdDTO dto) {
        QueryWrapper<SiteFloorMenuGoods> queryWrapper =  MybatisPlusUtil.query();
        queryWrapper.eq("floor_menu_id",dto.getId());
        if(siteFloorMenuGoodsRepository.count()>0){
            throw new BusinessException("楼层菜单下有商品，不能删除！");
        }
        repository.removeById(dto.getId());
    }

    @Override
    public void editSiteFloorMenu(SiteFloorMenuDTO.UDTO udto) {
        SiteFloorMenu siteFloorMenu = new SiteFloorMenu();
        BeanUtils.copyProperties(udto, siteFloorMenu);
        repository.updateById(siteFloorMenu);
    }
}
