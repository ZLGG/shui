package com.gs.lshly.biz.support.foundation.service.bbc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.foundation.complex.ArticleCategoryComlex;
import com.gs.lshly.biz.support.foundation.entity.DataArticle;
import com.gs.lshly.biz.support.foundation.entity.DataArticleCategory;
import com.gs.lshly.biz.support.foundation.enums.CategoryLevelEnum;
import com.gs.lshly.biz.support.foundation.mapper.DataArticleMapper;
import com.gs.lshly.biz.support.foundation.mapper.view.ArticleCategoryView;
import com.gs.lshly.biz.support.foundation.repository.IDataArticleCategoryRepository;
import com.gs.lshly.biz.support.foundation.repository.IDataArticleRepository;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcDataArticleCategoryService;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.foundation.dto.BbcDataArticleCategoryDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcDataArticleCategoryQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcDataArticleCategoryVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
* <p>
*  服务实现类
* </p>
* @author hyy
* @since 2020-11-13
*/
@Component
public class BbcDataArticleCategoryServiceImpl implements IBbcDataArticleCategoryService {

    @Autowired
    private IDataArticleCategoryRepository repository;

    @Autowired
    private IDataArticleRepository dataArticleRepository;

    @Autowired
    private DataArticleMapper dataArticleMapper;


    @Override
    public List<BbcDataArticleCategoryVO.ListVO> list(BaseDTO dto) {
        QueryWrapper<DataArticleCategory> queryLeveWrapper =  MybatisPlusUtil.query();
        queryLeveWrapper.eq("terminal", TerminalEnum.BBC.getCode());
        List<DataArticleCategory> categoryList = repository.list(queryLeveWrapper);
        List<BbcDataArticleCategoryVO.ListVO> voList  = new ArrayList<>();
        for(DataArticleCategory category:categoryList){
            if(category.getLeveone().equals(CategoryLevelEnum.一级.getCode())){
                BbcDataArticleCategoryVO.ListVO listVO = new BbcDataArticleCategoryVO.ListVO();
                BeanUtils.copyProperties(category,listVO);
                voList.add(listVO);
            }
        }
        List<DataArticleCategory> categoryTwoList = new ArrayList<>();
        List<String> idListForTwo = new ArrayList<>();
        for(DataArticleCategory category:categoryList){
            if(category.getLeveone().equals(CategoryLevelEnum.二级.getCode())){
                idListForTwo.add(category.getId());
                categoryTwoList.add(category);
            }
        }
        if(ObjectUtils.isNotEmpty(idListForTwo)){
            QueryWrapper<ArticleCategoryView> articleQueryWrapper =  MybatisPlusUtil.query();
            articleQueryWrapper.in("rela.category_id",idListForTwo);
            List<ArticleCategoryView> articleList =  dataArticleMapper.mapperListWithCategory(articleQueryWrapper);
            for(ArticleCategoryView viewItem:articleList){
                BbcDataArticleCategoryVO.ArticleListVO articleListVO = new BbcDataArticleCategoryVO.ArticleListVO();
                BeanUtils.copyProperties(viewItem,articleListVO);
                for(DataArticleCategory categoryTwo:categoryTwoList){
                    if(viewItem.getCategoryId().equals(categoryTwo.getId())){
                        for(BbcDataArticleCategoryVO.ListVO categoryNoneVo:voList){
                            if(categoryNoneVo.getId().equals(categoryTwo.getPerentId())){
                                categoryNoneVo.getArticleList().add(articleListVO);
                            }
                        }
                    }
                }
            }
        }
        //排序
        voList.sort(Comparator.comparing(BbcDataArticleCategoryVO.ListVO::getIdx));
        for(BbcDataArticleCategoryVO.ListVO listVO:voList){
            listVO.getArticleList().sort(Comparator.comparing(BbcDataArticleCategoryVO.ArticleListVO::getIdx));
        }
        return voList;
    }

    @Override
    public List<BbcDataArticleCategoryVO.SearchListVO> search(BbcDataArticleCategoryQTO.QTO qto) {
        if(StringUtils.isNotBlank(qto.getTitle())){
            QueryWrapper<DataArticle> queryWrapper =  MybatisPlusUtil.query();
            queryWrapper.like("title",qto.getTitle());
            List<DataArticle> articleList = dataArticleRepository.list(queryWrapper);
            return ListUtil.listCover(BbcDataArticleCategoryVO.SearchListVO.class,articleList);
        }
        return new ArrayList<>();
    }

    @Override
    public BbcDataArticleCategoryVO.DetailsVO details(BbcDataArticleCategoryDTO.IdDTO dto) {
        DataArticle dataArticle =  dataArticleRepository.getById(dto.getId());
        if(null == dataArticle){
            return null;
        }
        BbcDataArticleCategoryVO.DetailsVO detailsVO = new BbcDataArticleCategoryVO.DetailsVO();
        BeanUtils.copyProperties(dataArticle,detailsVO);
        return detailsVO;
    }
}
