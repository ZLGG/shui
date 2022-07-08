package com.citydo.appraisal.service.impl;

import com.citydo.appraisal.entity.AppraisalWeight;
import com.citydo.appraisal.mapper.AppraisalWeightMapper;
import com.citydo.appraisal.service.AppraisalWeightService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 考核权重(AppraisalWeight)表服务实现类
 *
 * @author makejava
 * @since 2022-07-06 11:37:49
 */
@Service
public class AppraisalWeightServiceImpl implements AppraisalWeightService {
    @Resource
    private AppraisalWeightMapper appraisalWeightDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AppraisalWeight queryById(Long id) {
        return this.appraisalWeightDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param appraisalWeight 实例对象
     * @return 实例对象
     */
    @Override
    public AppraisalWeight insert(AppraisalWeight appraisalWeight) {
        this.appraisalWeightDao.insert(appraisalWeight);
        return appraisalWeight;
    }

    /**
     * 修改数据
     *
     * @param appraisalWeight 实例对象
     * @return 实例对象
     */
    @Override
    public AppraisalWeight update(AppraisalWeight appraisalWeight) {
        this.appraisalWeightDao.update(appraisalWeight);
        return this.queryById(appraisalWeight.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.appraisalWeightDao.deleteById(id) > 0;
    }


    @Override
    public void insertBatch(List<AppraisalWeight> appraisalWeights) {
        appraisalWeightDao.insertBatch(appraisalWeights);
    }
}
