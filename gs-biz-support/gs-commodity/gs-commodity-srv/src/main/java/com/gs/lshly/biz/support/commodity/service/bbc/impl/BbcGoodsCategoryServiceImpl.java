package com.gs.lshly.biz.support.commodity.service.bbc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsCategory;
import com.gs.lshly.biz.support.commodity.repository.IGoodsCategoryRepository;
import com.gs.lshly.biz.support.commodity.service.bbc.IBbcGoodsCategoryService;
import com.gs.lshly.common.enums.GoodsCategoryLevelEnum;
import com.gs.lshly.common.enums.GoodsUsePlatformEnums;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsCategoryVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteAdvertVO;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteAdvertRpc;
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
public class BbcGoodsCategoryServiceImpl implements IBbcGoodsCategoryService {

    @Autowired
    private IGoodsCategoryRepository repository;

    @DubboReference
    private IBbcSiteAdvertRpc siteAdvertRpc;


    @Override
    public List<BbcGoodsCategoryVO.CategoryTreeVO> listGoodsCategory() {
        //查询所有的三级分类
        QueryWrapper<GoodsCategory> boost = new QueryWrapper<>();
        List<GoodsCategory> listLevel3 = repository.list(boost);
        //声明一个存储容器
        if(ObjectUtils.isEmpty(listLevel3)) {
            return null;
        }
        List<BbcGoodsCategoryVO.CategoryTreeVO> categoryTreeVOS = new ArrayList<>();
        Map<String, BbcGoodsCategoryVO.CategoryTreeVO> mapLevel2 = new HashMap<>();
        Map<String, BbcGoodsCategoryVO.CategoryTreeVO> mapLevel1 = new HashMap<>();
        for (GoodsCategory level3 : listLevel3) {
            if (level3.getUseFiled() == null || !level3.getGsCategoryLevel().equals(GoodsCategoryLevelEnum.THREE.getCode())) {
                continue;
            }
            if (!level3.getUseFiled().equals(GoodsUsePlatformEnums.B商城和C商城.getCode()) && !level3.getUseFiled().equals(GoodsUsePlatformEnums.C商城.getCode())) {
                continue;
            }
            BbcGoodsCategoryVO.CategoryTreeVO levelVO3 = new BbcGoodsCategoryVO.CategoryTreeVO();
            BeanUtils.copyProperties(level3, levelVO3);
            //根据获取的二级类目查询商品的二级类目
            BbcGoodsCategoryVO.CategoryTreeVO level2 = mapLevel2.get(level3.getParentId());
            if (level2 == null) {
                level2 = getParentCategory(levelVO3, listLevel3);
                if (level2 == null) {
                    log.error("三级类目id：{}；name：{}无上级类目{}", level3.getId(), level3.getGsCategoryName(), level3.getParentId());
                }
                mapLevel2.put(level3.getParentId(), level2);
                //根据获取的二级类目查询商品的一级类目
                BbcGoodsCategoryVO.CategoryTreeVO level1 = mapLevel1.get(level2.getParentId());
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
        List<BbcSiteAdvertVO.InnerCategoryAdvertListVO> innerCategoryAdvertListVOS = siteAdvertRpc.innerCategoryAdvertList(new BaseDTO());
        for(BbcGoodsCategoryVO.CategoryTreeVO levelVO1 : mapLevel1.values()){
            //一级类目才有 广告图
            for (BbcSiteAdvertVO.InnerCategoryAdvertListVO advertListVO : innerCategoryAdvertListVOS) {
                if (levelVO1.getId().equals(advertListVO.getCategoryId())) {
                    levelVO1.getCategoryAdvertListVO().add(advertListVO);
                }
            }
            //二级排序(包装类型去掉，可能会报nep)
            levelVO1.getList().sort((o1, o2) -> ((Integer)o1.getIdx()) != null && ((Integer)o2.getIdx()) != null ? (o1.getIdx() > o2.getIdx() ? 1 : -1) : -1);
            //三级排序
            for (BbcGoodsCategoryVO.CategoryTreeVO levelVO2 : levelVO1.getList()) {
                levelVO2.getList().sort((o1, o2) -> ((Integer)o1.getIdx()) != null && ((Integer)o2.getIdx()) != null ? (o1.getIdx() > o2.getIdx() ? 1 : -1) : -1);
            }
        }
        //一级排序
        categoryTreeVOS.sort((o1, o2) -> ((Integer)o1.getIdx()) != null && ((Integer)o2.getIdx()) != null ? (o1.getIdx() > o2.getIdx() ? 1 : -1) : -1);

        return categoryTreeVOS;
    }

    private BbcGoodsCategoryVO.CategoryTreeVO getParentCategory(BbcGoodsCategoryVO.CategoryTreeVO subVO, List<GoodsCategory> categories){
        if (subVO==null || StringUtils.isEmpty(subVO.getParentId())){
            throw new BusinessException("数据异常");
        }
        for(GoodsCategory category : categories ) {
            if (subVO.getParentId().equals(category.getId())) {
                BbcGoodsCategoryVO.CategoryTreeVO levelVO1 = new BbcGoodsCategoryVO.CategoryTreeVO();
                levelVO1.setList(new ArrayList<>());
                BeanUtils.copyProperties(category, levelVO1);
                return levelVO1;
            }
        }
        throw new BusinessException(String.format("无法为Id：%s；name：%s的类目找到上级类目", subVO.getId(), subVO.getGsCategoryName()));
    }

}
