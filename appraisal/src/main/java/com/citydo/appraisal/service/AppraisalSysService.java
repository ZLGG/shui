package com.citydo.appraisal.service;

import com.citydo.appraisal.entity.AppraisalSys;

import java.util.List;

/**
 * 考核体系(AppraisalSys)表服务接口
 *
 * @author makejava
 * @since 2022-07-07 10:41:44
 */
public interface AppraisalSysService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AppraisalSys queryById(Long id);

    /**
     * 新增数据
     *
     * @param appraisalSys 实例对象
     * @return 实例对象
     */
    AppraisalSys insert(AppraisalSys appraisalSys);

    /**
     * 修改数据
     *
     * @param appraisalSys 实例对象
     * @return 实例对象
     */
    AppraisalSys update(AppraisalSys appraisalSys);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 查询所有
     * @param
     * @return java.util.List<com.citydo.appraisal.entity.AppraisalSys>
     */
    List<AppraisalSys> queryAll();
}
