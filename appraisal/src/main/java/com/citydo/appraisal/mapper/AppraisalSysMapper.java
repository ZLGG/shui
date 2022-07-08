package com.citydo.appraisal.mapper;

import com.citydo.appraisal.entity.AppraisalSys;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 考核体系(AppraisalSys)表数据库访问层
 *
 * @author makejava
 * @since 2022-07-07 10:41:44
 */
@Repository
public interface AppraisalSysMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AppraisalSys queryById(Long id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param appraisalSys 实例对象
     * @return 对象列表
     */
    List<AppraisalSys> queryAll(AppraisalSys appraisalSys);

    /**
     * 新增数据
     *
     * @param appraisalSys 实例对象
     * @return 影响行数
     */
    int insert(AppraisalSys appraisalSys);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AppraisalSys> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AppraisalSys> entities);

    /**
     * 修改数据
     *
     * @param appraisalSys 实例对象
     * @return 影响行数
     */
    int update(AppraisalSys appraisalSys);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);
}

