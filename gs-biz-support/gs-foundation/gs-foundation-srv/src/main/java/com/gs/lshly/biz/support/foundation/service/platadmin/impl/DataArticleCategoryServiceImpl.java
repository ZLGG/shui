package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.foundation.entity.DataArticleCategory;
import com.gs.lshly.biz.support.foundation.entity.DataArticleRelationCategory;
import com.gs.lshly.biz.support.foundation.enums.CategoryLevelEnum;
import com.gs.lshly.biz.support.foundation.repository.IDataArticleCategoryRepository;
import com.gs.lshly.biz.support.foundation.repository.IDataArticleRelationCategoryRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.IDataArticleCategoryService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataArticleCategoryDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataArticleCategoryQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataArticleCategoryVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 陈奇
 * @since 2020-10-17
 */
@Slf4j
@Component
public class DataArticleCategoryServiceImpl implements IDataArticleCategoryService {

    @Autowired
    private IDataArticleCategoryRepository repository;

    @Autowired
    private IDataArticleRelationCategoryRepository dataArticleRelationCategoryRepository;


    @Override
    public List<DataArticleCategoryVO.FirstListVO> pageData(DataArticleCategoryQTO.FirstQTO qto) {
        QueryWrapper<DataArticleCategory> wrapper = MybatisPlusUtil.query();
        wrapper.eq("terminal", qto.getTerminal());
        wrapper.eq("leveone", qto.getLeveone());
        wrapper.orderByAsc("idx");
        List<DataArticleCategory> wrapperLists = repository.list(wrapper);
        List<DataArticleCategoryVO.FirstListVO> firstLevelLists = new ArrayList<>();
        wrapperLists.forEach(wrapperList -> {
            DataArticleCategoryVO.FirstListVO vo = new DataArticleCategoryVO.FirstListVO();
            BeanUtils.copyProperties(wrapperList, vo);
            firstLevelLists.add(vo);
        });
        firstLevelLists.forEach(firstLevelList -> {
            QueryWrapper<DataArticleCategory> wrapper2 = MybatisPlusUtil.query();
            wrapper2.eq("perent_id", firstLevelList.getId());
            wrapper2.orderByAsc("idx");
            List<DataArticleCategoryVO.SecondListVO> list = qto.toListData(DataArticleCategoryVO.SecondListVO.class, repository.list(wrapper2));
            firstLevelList.setLists(list);
        });
        return firstLevelLists;
    }

    /**
     * 在这个类里进行调用的方法
     * 根据一级，查出对应二级
     *
     * @param qto
     * @return
     */
    public List<DataArticleCategoryVO.SecondListVO> selectByFirst(DataArticleCategoryQTO.SecondQTO qto) {
        return null;
    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void addDataArticleCategory(DataArticleCategoryDTO.ADTO adto) {
        DataArticleCategory dataArticleCategory = new DataArticleCategory();
        BeanUtils.copyProperties(adto, dataArticleCategory);
        if (StringUtils.isBlank(adto.getPerentId())) {
            dataArticleCategory.setLeveone(CategoryLevelEnum.一级.getCode());
            repository.save(dataArticleCategory);
        } else {
            dataArticleCategory.setLeveone(CategoryLevelEnum.二级.getCode());
            repository.save(dataArticleCategory);
            this.setArticleCategoryTwoisDefault(dataArticleCategory.getPerentId());
        }
    }

    @Override
    public void deleteDataArticleCategory(DataArticleCategoryDTO.IdDTO dto) {
        if(StringUtils.isBlank(dto.getId())){
            throw new BusinessException("删除栏目的ID不能为空");
        }
        DataArticleCategory category =  repository.getById(dto.getId());
        if(null == category){
            throw new BusinessException("文章栏目不存在");
        }
        if (CategoryLevelEnum.一级.getCode().equals(category.getLeveone())) {
            QueryWrapper<DataArticleCategory> wrapper2 = MybatisPlusUtil.query();
            wrapper2.eq("perent_id", dto.getId());
            if(repository.count(wrapper2)>0){
                throw new BusinessException("一级类目，有子节点，不能删除");
            }
            repository.removeById(dto.getId());
        }
        if(CategoryLevelEnum.二级.getCode().equals(category.getLeveone())){
            QueryWrapper<DataArticleRelationCategory> queryWrapper = MybatisPlusUtil.query();
            queryWrapper.eq("category_id",dto.getId());
            if (dataArticleRelationCategoryRepository.count(queryWrapper) > 0) {
                throw new BusinessException("栏目下面有文章,请先删除文章再删除");
            }
            repository.removeById(dto.getId());
        }
    }


    private void  setArticleCategoryTwoisDefault(String categoryLeve01Id){
        DataArticleCategory dataArticleCategory =  repository.getById(categoryLeve01Id);
        if(null == dataArticleCategory){
            throw new BusinessException("栏目不存在");
        }
        UpdateWrapper<DataArticleCategory> updateWrapper = MybatisPlusUtil.update();
        updateWrapper.set("is_default",dataArticleCategory.getIsDefault());
        updateWrapper.eq("perent_id",categoryLeve01Id);
        repository.update(updateWrapper);
    }

    /**
     * 编辑
     * @param udto
     */
    @Override
    public void editDataArticleCategory(DataArticleCategoryDTO.UDTO udto) {

        if(StringUtils.isBlank(udto.getId())){
            throw new BusinessException("栏目的ID不能为空");
        }
        DataArticleCategory category =  repository.getById(udto.getId());
        if(null == category){
            throw new BusinessException("文章栏目不存在");
        }
        if (CategoryLevelEnum.一级.getCode().equals(category.getLeveone())) {
            if (StringUtils.isBlank(udto.getPerentId())) {
                BeanUtils.copyProperties(udto, category);
                category.setLeveone(CategoryLevelEnum.一级.getCode());
                repository.updateById(category);
                this.setArticleCategoryTwoisDefault(category.getId());
            }else {
                QueryWrapper<DataArticleCategory> wrapperCountTwo =  MybatisPlusUtil.query();
                wrapperCountTwo.eq("perent_id",udto.getId());
                if (repository.count(wrapperCountTwo)>0) {
                    throw new BusinessException("栏目有子节点,不能更改层级");
                }else {
                    BeanUtils.copyProperties(udto, category);
                    category.setLeveone(CategoryLevelEnum.二级.getCode());
                    repository.updateById(category);
                    this.setArticleCategoryTwoisDefault(category.getPerentId());
                }
            }
        }else if (CategoryLevelEnum.二级.getCode().equals(category.getLeveone())) {
            if (StringUtils.isBlank(udto.getPerentId())){
                //新数据为一级
                QueryWrapper<DataArticleRelationCategory> queryWrapperArticle = MybatisPlusUtil.query();
                queryWrapperArticle.eq("category_id", udto.getId());
                int countArticle = dataArticleRelationCategoryRepository.count(queryWrapperArticle);
                if (countArticle > 0) {
                    throw new BusinessException("栏目下面有文章,不能更改为一级栏目");
                }
                BeanUtils.copyProperties(udto, category);
                category.setLeveone(CategoryLevelEnum.一级.getCode());
                repository.updateById(category);
                this.setArticleCategoryTwoisDefault(category.getId());
            }else {
                //新数据是二级,直接修改
                BeanUtils.copyProperties(udto, category);
                category.setLeveone(CategoryLevelEnum.二级.getCode());
                repository.updateById(category);
                this.setArticleCategoryTwoisDefault(category.getPerentId());
            }
        }
    }

        /**
         * 前端输入一级，查出对应二级
         * @param qto
         * @return
         */
        @Override
        public PageData<DataArticleCategoryVO.SecondListVO> pageData2 (DataArticleCategoryQTO.SecondQTO qto){
            QueryWrapper<DataArticleCategory> wrapper =  MybatisPlusUtil.query();
            wrapper.eq("perent_id", qto.getPerentId());
            IPage<DataArticleCategory> page = MybatisPlusUtil.pager(qto);
            repository.page(page, wrapper);
            return MybatisPlusUtil.toPageData(qto, DataArticleCategoryVO.SecondListVO.class, page);
        }

        /**
         * 列出2B、2C对应所有二级
         * @param qto
         * @return
         */
        @Override
        public PageData<DataArticleCategoryVO.SecondListVO> pageData3 (DataArticleCategoryQTO.SecondQTO qto){
            QueryWrapper<DataArticleCategory> wrapper = MybatisPlusUtil.query();
            wrapper.eq("leveone", CategoryLevelEnum.二级.getCode());
            wrapper.eq("terminal", qto.getTerminal());
            IPage<DataArticleCategory> page = MybatisPlusUtil.pager(qto);
            repository.page(page, wrapper);
            return MybatisPlusUtil.toPageData(qto, DataArticleCategoryVO.SecondListVO.class, page);
        }

        @Override
        public void changeIdx (List < DataArticleCategoryDTO.IdxDTO > idxDTOS) {
            if (idxDTOS == null || idxDTOS.size() == 0) {
                throw new BusinessException("参数不能为空！");
            }
            idxDTOS.forEach(idxDTO -> {
                DataArticleCategory category = new DataArticleCategory();
                BeanUtils.copyProperties(idxDTO, category);
                repository.updateById(category);
            });
        }
    }
