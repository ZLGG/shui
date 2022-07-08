package com.citydo.appraisal.service;

import com.citydo.appraisal.entity.AppraisalInfo;

import java.util.List;

/**
 * 考核人员信息(AppraisalInfo)表服务接口
 *
 * @author makejava
 * @since 2022-06-29 10:50:36
 */
public interface AppraisalInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AppraisalInfo queryById(Long id);

    /**
     * 新增数据
     *
     * @param appraisalInfo 实例对象
     * @return 实例对象
     */
    AppraisalInfo insert(AppraisalInfo appraisalInfo);

    /**
     * 修改数据
     *
     * @param appraisalInfo 实例对象
     * @return 实例对象
     */
    AppraisalInfo update(AppraisalInfo appraisalInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 批量插入
     * @param entities
     * @return void
     */
    void insertBatch(List<AppraisalInfo> entities);


    /**
     * 根据考核人查询考核员工
     * @param approverCode
     * @return java.util.List<com.citydo.appraisal.entity.AppraisalInfo>
     */
    List<AppraisalInfo> queryByAppraisalCode(String approverCode);

    /**
     * 根据考核人与员工查询
     * @param approverCode
	 * @param staffCode
     * @return com.citydo.appraisal.entity.AppraisalInfo
     */
    AppraisalInfo getByAppraisalCodeAndstaffCode(String approverCode, String staffCode);
}
