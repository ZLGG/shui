package com.citydo.appraisal.mapper;

import com.citydo.appraisal.entity.AppraisalInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 考核人员信息(AppraisalInfo)表数据库访问层
 *
 * @author makejava
 * @since 2022-06-29 10:50:35
 */
@Repository
public interface AppraisalInfoMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AppraisalInfo queryById(Long id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param appraisalInfo 实例对象
     * @return 对象列表
     */
    List<AppraisalInfo> queryAll(AppraisalInfo appraisalInfo);

    /**
     * 新增数据
     *
     * @param appraisalInfo 实例对象
     * @return 影响行数
     */
    int insert(AppraisalInfo appraisalInfo);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AppraisalInfo> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AppraisalInfo> entities);

    /**
     * 修改数据
     *
     * @param appraisalInfo 实例对象
     * @return 影响行数
     */
    int update(AppraisalInfo appraisalInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 根据考核人，查询考核员工
     * @param approverCode
     * @return java.util.List<com.citydo.appraisal.entity.AppraisalInfo>
     */
    List<AppraisalInfo> queryByAppraisalCode(@Param("approverCode") String approverCode);


    AppraisalInfo getByAppraisalCodeAndstaffCode(@Param("approverCode") String approverCode, @Param("staffCode") String staffCode);
}

