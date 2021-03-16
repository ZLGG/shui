package com.gs.lshly.biz.support.commodity.service.bbb.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsCategory;
import com.gs.lshly.biz.support.commodity.repository.IGoodsCategoryRepository;
import com.gs.lshly.biz.support.commodity.service.bbb.h5.IBbbH5GoodsCategoryService;
import com.gs.lshly.common.enums.*;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsCategoryVO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteAdvertVO;
import com.gs.lshly.rpc.api.bbb.h5.foundation.IBbbH5SiteAdvertRpc;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-10-23
*/
@Component
@Slf4j
public class BbbH5GoodsCategoryServiceImpl implements IBbbH5GoodsCategoryService {

    @Autowired
    private IGoodsCategoryRepository repository;

    @DubboReference
    private IBbbH5SiteAdvertRpc siteAdvertRpc;


    @Override
    public List<BbbH5GoodsCategoryVO.CategoryTreeVO> listGoodsCategory(BaseDTO dto) {
        //查询所有的三级分类
        QueryWrapper<GoodsCategory> boost = new QueryWrapper<>();
        List<GoodsCategory> listLevel3 = repository.list(boost);
        //声明一个存储容器
        if(ObjectUtils.isEmpty(listLevel3)) {
            return null;
        }
        List<BbbH5GoodsCategoryVO.CategoryTreeVO> categoryTreeVOS = new ArrayList<>();
        Map<String, BbbH5GoodsCategoryVO.CategoryTreeVO> mapLevel2 = new HashMap<>();
        Map<String, BbbH5GoodsCategoryVO.CategoryTreeVO> mapLevel1 = new HashMap<>();
        for (GoodsCategory level3 : listLevel3) {
            if (level3.getUseFiled() == null || !level3.getGsCategoryLevel().equals(GoodsCategoryLevelEnum.THREE.getCode())) {
                continue;
            }
            if (!level3.getUseFiled().equals(GoodsUsePlatformEnums.B商城和C商城.getCode()) && !level3.getUseFiled().equals(GoodsUsePlatformEnums.B商城.getCode())) {
                continue;
            }
            BbbH5GoodsCategoryVO.CategoryTreeVO levelVO3 = new BbbH5GoodsCategoryVO.CategoryTreeVO();
            BeanUtils.copyProperties(level3, levelVO3);
            //根据获取的二级类目查询商品的二级类目
            BbbH5GoodsCategoryVO.CategoryTreeVO level2 = mapLevel2.get(level3.getParentId());
            if (level2 == null) {
                level2 = getParentCategory(levelVO3, listLevel3);
                if (level2 == null) {
                    log.error("三级类目id：{}；name：{}无上级类目{}", level3.getId(), level3.getGsCategoryName(), level3.getParentId());
                }
                mapLevel2.put(level3.getParentId(), level2);
                //根据获取的二级类目查询商品的一级类目
                BbbH5GoodsCategoryVO.CategoryTreeVO level1 = mapLevel1.get(level2.getParentId());
                if (level1 == null) {
                    level1 = getParentCategory(level2, listLevel3);
                    if (level1 == null) {
                        log.error("二级类目id：{}；name：{}无上级类目{}", level2.getId(), level2.getGsCategoryName(), level3.getParentId());
                    }
                    categoryTreeVOS.add(level1);
                    mapLevel1.put(level2.getParentId(), level1);
                    level1.getList().add(level2);
                } else {
                    level1.getList().add(level2);
                }
                level2.getList().add(levelVO3);
            } else {
                level2.getList().add(levelVO3);
            }
        }
        //组装每个一级分类下的分页广告图
        List<BbbH5SiteAdvertVO.InnerCategoryAdvertListVO> innerCategoryAdvertListVOS = siteAdvertRpc.innerCategoryAdvertList(new BaseDTO());
        for(BbbH5GoodsCategoryVO.CategoryTreeVO levelVO1 : mapLevel1.values()){
            //一级类目才有 广告图
            for (BbbH5SiteAdvertVO.InnerCategoryAdvertListVO advertListVO : innerCategoryAdvertListVOS) {
                if (levelVO1.getId().equals(advertListVO.getCategoryId())) {
                    levelVO1.getCategoryAdvertListVO().add(advertListVO);
                    break;
                }
            }
            //二级排序(包装类型去掉，可能会报nep)
            levelVO1.getList().sort((o1, o2) -> ((Integer)o1.getIdx()) != null && ((Integer)o2.getIdx()) != null ? (o1.getIdx() > o2.getIdx() ? 1 : -1) : -1);
            //三级排序
            for (BbbH5GoodsCategoryVO.CategoryTreeVO levelVO2 : levelVO1.getList()) {
                levelVO2.getList().sort((o1, o2) -> ((Integer)o1.getIdx()) != null && ((Integer)o2.getIdx()) != null ? (o1.getIdx() > o2.getIdx() ? 1 : -1) : -1);
            }
        }
        //一级排序
        categoryTreeVOS.sort((o1, o2) -> ((Integer)o1.getIdx()) != null && ((Integer)o2.getIdx()) != null ? (o1.getIdx() > o2.getIdx() ? 1 : -1) : -1);

        return categoryTreeVOS;
    }


    private BbbH5GoodsCategoryVO.CategoryTreeVO getParentCategory(BbbH5GoodsCategoryVO.CategoryTreeVO subVO, List<GoodsCategory> categories){
        if (subVO==null || StringUtils.isEmpty(subVO.getParentId())){
            throw new BusinessException("数据异常");
        }
        for(GoodsCategory category : categories ) {
            if (subVO.getParentId().equals(category.getId())) {
                BbbH5GoodsCategoryVO.CategoryTreeVO levelVO1 = new BbbH5GoodsCategoryVO.CategoryTreeVO();
                levelVO1.setList(new ArrayList<>());
                BeanUtils.copyProperties(category, levelVO1);
                return levelVO1;
            }
        }
        throw new BusinessException(String.format("无法为Id：%s；name：%s的类目找到上级类目", subVO.getId(), subVO.getGsCategoryName()));
    }



}
