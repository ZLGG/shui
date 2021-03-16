package com.gs.lshly.biz.support.foundation.service.bbb.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.foundation.complex.bbb.h5.BbbH5ArticleCategoryComlex;
import com.gs.lshly.biz.support.foundation.entity.DataArticle;
import com.gs.lshly.biz.support.foundation.entity.DataArticleCategory;
import com.gs.lshly.biz.support.foundation.enums.CategoryLevelEnum;
import com.gs.lshly.biz.support.foundation.mapper.DataArticleMapper;
import com.gs.lshly.biz.support.foundation.mapper.view.ArticleCategoryView;
import com.gs.lshly.biz.support.foundation.repository.IDataArticleCategoryRepository;
import com.gs.lshly.biz.support.foundation.repository.IDataArticleRepository;
import com.gs.lshly.biz.support.foundation.service.bbb.h5.IBbbH5DataArticleCategoryService;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.dto.BbbH5DataArticleCategoryDTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5DataArticleCategoryQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5DataArticleCategoryVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.rbac.SysFuncVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* <p>
*  服务实现类
* </p>
* @author hyy
* @since 2020-11-13
*/
@Component
public class BbbH5DataArticleCategoryServiceImpl implements IBbbH5DataArticleCategoryService {

    @Autowired
    private IDataArticleCategoryRepository repository;

    @Autowired
    private IDataArticleRepository dataArticleRepository;

    @Autowired
    private DataArticleMapper dataArticleMapper;

    @Override
    public List<BbbH5DataArticleCategoryVO.ListVO> list(BaseDTO dto) {

        QueryWrapper<DataArticleCategory> queryLeveWrapper =  MybatisPlusUtil.query();
        queryLeveWrapper.eq("terminal", TerminalEnum.BBB.getCode());
        List<DataArticleCategory> categoryList = repository.list(queryLeveWrapper);
        List<BbbH5DataArticleCategoryVO.ListVO> oneVoCategoryList = new ArrayList<>();
        List<DataArticleCategory> twoCategoryList = new ArrayList<>();
        List<String> idList = new ArrayList<>();
        for(DataArticleCategory category:categoryList){
            if(category.getLeveone().equals(CategoryLevelEnum.一级.getCode())){
                BbbH5DataArticleCategoryVO.ListVO listVO = new BbbH5DataArticleCategoryVO.ListVO();
                BeanUtils.copyProperties(category,listVO);
                oneVoCategoryList.add(listVO);
            }else if(category.getLeveone().equals(CategoryLevelEnum.二级.getCode())){
                idList.add(category.getId());
                twoCategoryList.add(category);
            }
        }
        //二级栏目查找文章
        if(ObjectUtils.isNotEmpty(idList)){
            QueryWrapper<ArticleCategoryView> articleQueryWrapper =  MybatisPlusUtil.query();
            articleQueryWrapper.in("rela.category_id",idList);
            List<ArticleCategoryView> articleList =  dataArticleMapper.mapperListWithCategory(articleQueryWrapper);
            for(ArticleCategoryView viewItem:articleList){
                for(DataArticleCategory twoCategory:twoCategoryList){
                    if(twoCategory.getId().equals(viewItem.getCategoryId())){
                        BbbH5DataArticleCategoryVO.ArticleListVO articleListVO = new BbbH5DataArticleCategoryVO.ArticleListVO();
                        BeanUtils.copyProperties(viewItem,articleListVO);
                        for(BbbH5DataArticleCategoryVO.ListVO oneVo:oneVoCategoryList){
                            if(twoCategory.getPerentId().equals(oneVo.getId())){
                                oneVo.getArticleList().add(articleListVO);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return oneVoCategoryList;
    }

    @Override
    public List<BbbH5DataArticleCategoryVO.SearchListVO> search(BbbH5DataArticleCategoryQTO.QTO qto) {
        if(StringUtils.isNotBlank(qto.getTitle())){
            QueryWrapper<DataArticle> queryWrapper =  MybatisPlusUtil.query();
            queryWrapper.like("title",qto.getTitle());
            queryWrapper.eq("terminal", TerminalEnum.BBB.getCode());
            List<DataArticle> articleList = dataArticleRepository.list(queryWrapper);
            return ListUtil.listCover(BbbH5DataArticleCategoryVO.SearchListVO.class,articleList);
        }
        return new ArrayList<>();
    }

    @Override
    public BbbH5DataArticleCategoryVO.DetailsVO details(BbbH5DataArticleCategoryDTO.IdDTO dto) {
        DataArticle dataArticle =  dataArticleRepository.getById(dto.getId());
        if(null == dataArticle){
            return null;
        }
        BbbH5DataArticleCategoryVO.DetailsVO detailsVO = new BbbH5DataArticleCategoryVO.DetailsVO();
        BeanUtils.copyProperties(dataArticle,detailsVO);
        return detailsVO;
    }
}
