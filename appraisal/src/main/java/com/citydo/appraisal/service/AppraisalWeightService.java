package com.citydo.appraisal.service;

import com.citydo.appraisal.entity.AppraisalWeight;

import java.util.List;

/**
 * 考核权重(AppraisalWeight)表服务接口
 *
 * @author makejava
 * @since 2022-07-06 11:37:49
 */
public interface AppraisalWeightService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AppraisalWeight queryById(Long id);

    /**
     * 新增数据
     *
     * @param appraisalWeight 实例对象
     * @return 实例对象
     */
    AppraisalWeight insert(AppraisalWeight appraisalWeight);

    /**
     * 批量新增
     * @param appraisalWeights
     * @return void
     */
    void insertBatch(List<AppraisalWeight> appraisalWeights);

    /**
     * 修改数据
     *
     * @param appraisalWeight 实例对象
     * @return 实例对象
     */
    AppraisalWeight update(AppraisalWeight appraisalWeight);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
