package com.gs.lshly.biz.support.foundation.service.bbb.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.foundation.entity.DataArticle;
import com.gs.lshly.biz.support.foundation.entity.DataArticleCategory;
import com.gs.lshly.biz.support.foundation.entity.SiteBottomArticle;
import com.gs.lshly.biz.support.foundation.enums.CategoryLevelEnum;
import com.gs.lshly.biz.support.foundation.mapper.DataArticleMapper;
import com.gs.lshly.biz.support.foundation.mapper.view.ArticleCategoryView;
import com.gs.lshly.biz.support.foundation.repository.IDataArticleCategoryRepository;
import com.gs.lshly.biz.support.foundation.repository.IDataArticleRepository;
import com.gs.lshly.biz.support.foundation.repository.ISiteBottomArticleRepository;
import com.gs.lshly.biz.support.foundation.service.bbb.pc.IBbbArticleCategoryService;
import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.dto.BbbArticleCategoryDTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbArticleCategoryQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbArticleCategoryVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author hyy
* @since 2020-11-13
*/
@Component
public class BbbArticleCategoryServiceImpl implements IBbbArticleCategoryService {

    @Autowired
    private IDataArticleCategoryRepository repository;

    @Autowired
    private IDataArticleRepository dataArticleRepository;

    @Autowired
    private DataArticleMapper dataArticleMapper;

    @Autowired
    private ISiteBottomArticleRepository siteBottomArticleRepository;

    @Override
    public List<BbbArticleCategoryVO.HelpListVO> helpList(BaseDTO dto) {
        QueryWrapper<DataArticleCategory> queryLeveWrapper =  MybatisPlusUtil.query();
        queryLeveWrapper.eq("terminal", TerminalEnum.BBB.getCode());
        List<DataArticleCategory> categoryList = repository.list(queryLeveWrapper);
        List<BbbArticleCategoryVO.HelpListVO> voList  = new ArrayList<>();
        for(DataArticleCategory category:categoryList){
            if(category.getLeveone().equals(CategoryLevelEnum.一级.getCode())){
                BbbArticleCategoryVO.HelpListVO helpListVO = new BbbArticleCategoryVO.HelpListVO();
                BeanUtils.copyProperties(category,helpListVO);
                voList.add(helpListVO);
            }
        }
        for(DataArticleCategory category:categoryList){
            if(category.getLeveone().equals(CategoryLevelEnum.二级.getCode())){
                BbbArticleCategoryVO.HelpChildListVO childListVO = new BbbArticleCategoryVO.HelpChildListVO();
                BeanUtils.copyProperties(category,childListVO);
                for(BbbArticleCategoryVO.HelpListVO helpListVO:voList){
                    if(helpListVO.getId().equals(category.getPerentId())){
                        helpListVO.getChildList().add(childListVO);
                    }
                }
            }
        }
        //排序
        voList.sort(Comparator.comparing(BbbArticleCategoryVO.HelpListVO::getIdx));
        for(BbbArticleCategoryVO.HelpListVO helpListVO:voList){
            helpListVO.getChildList().sort(Comparator.comparing(BbbArticleCategoryVO.HelpChildListVO::getIdx));
        }
        return voList;
    }

    @Override
    public PageData<BbbArticleCategoryVO.ArticleListVO> articleList(BbbArticleCategoryQTO.ArticleQTO qto) {
        QueryWrapper<ArticleCategoryView> categoryViewQueryWrapper = MybatisPlusUtil.query();
        categoryViewQueryWrapper.eq("rela.category_id",qto.getCategoryId());
        categoryViewQueryWrapper.eq("art.pc_show",PcH5Enum.YES.getCode());
        categoryViewQueryWrapper.orderByAsc("idx");
        IPage<ArticleCategoryView> pager = MybatisPlusUtil.pager(qto);
        dataArticleMapper.mapperPageWithCategory(pager,categoryViewQueryWrapper);
        return MybatisPlusUtil.toPageData(qto,BbbArticleCategoryVO.ArticleListVO.class,pager);
    }

    @Override
    public List<BbbArticleCategoryVO.SearchListVO> search(BbbArticleCategoryQTO.QTO qto) {
        if(StringUtils.isNotBlank(qto.getTitle())){
            QueryWrapper<DataArticle> queryWrapper =  MybatisPlusUtil.query();
            queryWrapper.like("title",qto.getTitle());
            List<DataArticle> articleList = dataArticleRepository.list(queryWrapper);
            return ListUtil.listCover(BbbArticleCategoryVO.SearchListVO.class,articleList);
        }
        return new ArrayList<>();
    }

    @Override
    public BbbArticleCategoryVO.DetailsVO details(BbbArticleCategoryDTO.IdDTO dto) {
        DataArticle dataArticle =  dataArticleRepository.getById(dto.getId());
        if(null == dataArticle){
            return null;
        }
        BbbArticleCategoryVO.DetailsVO detailsVO = new BbbArticleCategoryVO.DetailsVO();
        BeanUtils.copyProperties(dataArticle,detailsVO);
        return detailsVO;
    }

    @Override
    public BbbArticleCategoryVO.ArticleLinksVO homeIndexArticleLinks(BaseDTO dto) {

        //栏目文章
        BbbArticleCategoryVO.ArticleLinksVO articleLinksVO = new BbbArticleCategoryVO.ArticleLinksVO();
        articleLinksVO.setArticleList(this.homeList(dto));

        //底部链接文章
        QueryWrapper<SiteBottomArticle> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("terminal",TerminalEnum.BBB.getCode());
        queryWrapper.eq("pc_show", PcH5Enum.YES.getCode());
        queryWrapper.eq("subject", SubjectEnum.默认.getCode());
        List<SiteBottomArticle> bottomArticleList =  siteBottomArticleRepository.list(queryWrapper);
        articleLinksVO.setLinksList(ListUtil.listCover(BbbArticleCategoryVO.LinksVO.class,bottomArticleList));
        return articleLinksVO;
    }

    private List<BbbArticleCategoryVO.ListVO> homeList(BaseDTO dto) {
        QueryWrapper<DataArticleCategory> queryLeveWrapper =  MybatisPlusUtil.query();
        queryLeveWrapper.eq("terminal", TerminalEnum.BBB.getCode());
        queryLeveWrapper.eq("is_default", TrueFalseEnum.是.getCode());
        List<DataArticleCategory> categoryList = repository.list(queryLeveWrapper);
        List<BbbArticleCategoryVO.ListVO> voList  = new ArrayList<>();
        for(DataArticleCategory category:categoryList){
            if(category.getLeveone().equals(CategoryLevelEnum.一级.getCode())){
                BbbArticleCategoryVO.ListVO listVO = new BbbArticleCategoryVO.ListVO();
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
            articleQueryWrapper.eq("art.is_default",TrueFalseEnum.是.getCode());
            articleQueryWrapper.eq("art.pc_show",PcH5Enum.YES.getCode());
            List<ArticleCategoryView> articleList =  dataArticleMapper.mapperListWithCategory(articleQueryWrapper);
            for(ArticleCategoryView viewItem:articleList){
                BbbArticleCategoryVO.ArticleListVO articleListVO = new BbbArticleCategoryVO.ArticleListVO();
                BeanUtils.copyProperties(viewItem,articleListVO);
                for(DataArticleCategory categoryTwo:categoryTwoList){
                    if(viewItem.getCategoryId().equals(categoryTwo.getId())){
                        for(BbbArticleCategoryVO.ListVO categoryNoneVo:voList){
                            if(categoryNoneVo.getId().equals(categoryTwo.getPerentId())){
                                categoryNoneVo.getArticleList().add(articleListVO);
                            }
                        }
                    }
                }
            }
        }
        //排序
        voList.sort(Comparator.comparing(BbbArticleCategoryVO.ListVO::getIdx));
        for(BbbArticleCategoryVO.ListVO listVO:voList){
            listVO.getArticleList().sort(Comparator.comparing(BbbArticleCategoryVO.ArticleListVO::getIdx));
        }
        return voList;
    }

}
