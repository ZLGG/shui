package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gs.lshly.biz.support.foundation.entity.DataArticle;
import com.gs.lshly.biz.support.foundation.entity.DataArticleRelationCategory;
import com.gs.lshly.biz.support.foundation.entity.SysUser;
import com.gs.lshly.biz.support.foundation.mapper.DataArticleMapper;
import com.gs.lshly.biz.support.foundation.mapper.DataArticleRelationCategoryMapper;
import com.gs.lshly.biz.support.foundation.mapper.view.RelationCategoryView;
import com.gs.lshly.biz.support.foundation.repository.IDataArticleCategoryRepository;
import com.gs.lshly.biz.support.foundation.repository.IDataArticleRelationCategoryRepository;
import com.gs.lshly.biz.support.foundation.repository.IDataArticleRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.IDataArticleService;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataArticleDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataArticleRelationCategoryDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataArticleQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataArticleVO;
import com.gs.lshly.common.utils.EnumUtil;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* <p>
*  服务实现类
* </p>
* @author 陈奇
* @since 2020-10-19
*/
@Component
@Slf4j
public class DataArticleServiceImpl implements IDataArticleService {

    @Autowired
    private IDataArticleRepository repository;

    @Autowired
    private IDataArticleRelationCategoryRepository dataArticleRelationCategoryRepository;

    @Autowired
    private DataArticleMapper dataArticleMapper;

    @Autowired
    private DataArticleRelationCategoryMapper dataArticleRelationCategoryMapper;


    @Override
    public PageData<DataArticleVO.ListVO> pageData(DataArticleQTO.QTO qto)  {
        if(!EnumUtil.checkEnumCode(qto.getTerminal(), TerminalEnum.class)){
            throw new BusinessException("2B,2C分类不能为空");
        }
        QueryWrapper<DataArticle> queryWrapper =  MybatisPlusUtil.query();
        if(StringUtils.isNotBlank(qto.getTitle())){
            queryWrapper.like("title",qto.getTitle());
        }
        queryWrapper.eq("terminal",qto.getTerminal());
        queryWrapper.select("id","title","logo","is_default","read_count","pc_show","idx","send_time");
        queryWrapper.orderByDesc("cdate");
        IPage<DataArticle> page = MybatisPlusUtil.pager(qto);
        repository.page(page, queryWrapper);
        Map<String, DataArticleVO.ListVO> voMap = new HashMap<>();
        List<String> idList = new ArrayList<>();
        if(ObjectUtils.isNotEmpty(page.getRecords())){
            for(DataArticle dataArticle:page.getRecords()){
                idList.add(dataArticle.getId());
                DataArticleVO.ListVO listVO  = new DataArticleVO.ListVO();
                BeanUtils.copyProperties(dataArticle,listVO);
                voMap.put(listVO.getId(),listVO);
            }
        }
        if (ObjectUtils.isEmpty(idList)) {
            return new PageData<>(new ArrayList<>(), qto.getPageNum(), qto.getPageSize(), page.getTotal());
        }
        QueryWrapper<RelationCategoryView> relationCategoryQueryWrapper =  MybatisPlusUtil.query();
        relationCategoryQueryWrapper.in("rel.article_id",idList);
        List<RelationCategoryView> viewList = dataArticleRelationCategoryMapper.mapperListCategoryWithArticleId(relationCategoryQueryWrapper);
        if(ObjectUtils.isNotEmpty(viewList)){
            for(RelationCategoryView viewItem:viewList){
                DataArticleVO.ListVO listVO = voMap.get(viewItem.getArticleId());
                listVO.getCategoryNameList().add(viewItem.getName());
            }
        }
        return new PageData<>(new ArrayList<>(voMap.values()), qto.getPageNum(), qto.getPageSize(), page.getTotal());
    }

    /**
     * 添加文章
     * @param eto
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void addDataArticle(DataArticleDTO.ETO eto) {
        if(ObjectUtils.isEmpty(eto.getCategoryList())){
            throw new BusinessException("文章栏目ID数组不能为空");
        }
        DataArticle dataArticle = new DataArticle();
        BeanUtils.copyProperties(eto, dataArticle);
        dataArticle.setSendTime(LocalDateTime.now());
        repository.save(dataArticle);
        //保存关系表
        List<DataArticleRelationCategory> relationCategoryList = new ArrayList<>();
        for(String categoryId:eto.getCategoryList()){
            DataArticleRelationCategory dataArticleRelationCategory = new DataArticleRelationCategory();
            dataArticleRelationCategory.setArticleId(dataArticle.getId());
            dataArticleRelationCategory.setCategoryId(categoryId);
            relationCategoryList.add(dataArticleRelationCategory);
        }
        dataArticleRelationCategoryRepository.saveBatch(relationCategoryList);
    }

    /**
     * 编辑文章，要首先删除关系表全部数据
     * @param eto
     */
    @Override
    public void editDataArticle(DataArticleDTO.ETO eto) {
        if(ObjectUtils.isEmpty(eto.getCategoryList())){
            throw new BusinessException("文章栏目ID数组不能为空");
        }
        DataArticle dataArticle = repository.getById(eto.getId());
        if(null != dataArticle){
            BeanUtils.copyProperties(eto, dataArticle);
            dataArticle.setSendTime(LocalDateTime.now());
            repository.updateById(dataArticle);
            //删除栏目关系
            QueryWrapper<DataArticleRelationCategory> relationCategoryQueryWrapper = MybatisPlusUtil.query();
            relationCategoryQueryWrapper.eq("article_id",dataArticle.getId());
            dataArticleRelationCategoryRepository.remove(relationCategoryQueryWrapper);
            //保存栏目关系
            List<DataArticleRelationCategory> relationCategoryList = new ArrayList<>();
            for(String categoryId:eto.getCategoryList()){
                DataArticleRelationCategory dataArticleRelationCategory = new DataArticleRelationCategory();
                dataArticleRelationCategory.setArticleId(dataArticle.getId());
                dataArticleRelationCategory.setCategoryId(categoryId);
                relationCategoryList.add(dataArticleRelationCategory);
            }
            if(ObjectUtils.isNotEmpty(relationCategoryList)){
                dataArticleRelationCategoryRepository.saveBatch(relationCategoryList);
            }

        }
    }

    /**
     * 文章详情
     * 数组形式返回栏目id
     * @param dto
     * @return
     */
    @Override
    public DataArticleVO.DVO detailDataArticle(DataArticleDTO.IdDTO dto) {
        if(StringUtils.isBlank(dto.getId())){
            throw new BusinessException("文章ID不能为空");
        }
        DataArticle article = repository.getById(dto.getId());
        if(null == article){
            throw new BusinessException("文章不存在");
        }
        DataArticleVO.DVO detailVo = new DataArticleVO.DVO();
        BeanUtils.copyProperties(article,detailVo);
        QueryWrapper<RelationCategoryView> relationCategoryQueryWrapper =  MybatisPlusUtil.query();
        relationCategoryQueryWrapper.eq("rel.article_id",article.getId());
        List<RelationCategoryView> viewList = dataArticleRelationCategoryMapper.mapperListCategoryWithArticleId(relationCategoryQueryWrapper);
        if(ObjectUtils.isNotEmpty(viewList)){
            for(RelationCategoryView viewItem:viewList){
                detailVo.getCategoryIds().add(viewItem.getId());
            }
        }
        return detailVo;
    }
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteDataArticleList(DataArticleDTO.IdListDTO dto) {
        if(ObjectUtils.isEmpty(dto.getIdList())){
            throw new BusinessException("删除的文章ID数组不能为空");
        }
        repository.removeByIds(dto.getIdList());
        //删除文章栏目关系
        QueryWrapper<DataArticleRelationCategory> relationCategoryQueryWrapper = MybatisPlusUtil.query();
        relationCategoryQueryWrapper.in("article_id",dto.getIdList());
        dataArticleRelationCategoryRepository.remove(relationCategoryQueryWrapper);
    }
}
