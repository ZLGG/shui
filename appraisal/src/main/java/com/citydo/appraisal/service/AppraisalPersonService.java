package com.citydo.appraisal.service;

import com.citydo.appraisal.entity.AppraisalPerson;

/**
 * 考核人(AppraisalPerson)表服务接口
 *
 * @author makejava
 * @since 2022-07-06 11:37:47
 */
public interface AppraisalPersonService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AppraisalPerson queryById(Long id);

    /**
     * 新增数据
     *
     * @param appraisalPerson 实例对象
     * @return 实例对象
     */
    AppraisalPerson insert(AppraisalPerson appraisalPerson);

    /**
     * 修改数据
     *
     * @param appraisalPerson 实例对象
     * @return 实例对象
     */
    AppraisalPerson update(AppraisalPerson appraisalPerson);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 根据考核人手机号，查询考核人
     * @param mobile
     * @return com.citydo.appraisal.entity.AppraisalPerson
     */
    AppraisalPerson getAppraisalPersonByMobile(String mobile);
}
