package com.citydo.appraisal.service;

import com.citydo.appraisal.entity.AppraisalRecord;
import com.citydo.appraisal.request.AppraisalRecordRequest;

import java.util.List;

/**
 * 考核记录(AppraisalRecord)表服务接口
 *
 * @author makejava
 * @since 2022-07-06 11:37:48
 */
public interface AppraisalRecordService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AppraisalRecord queryById(Long id);

    /**
     * 新增数据
     *
     * @param appraisalRecord 实例对象
     * @return 实例对象
     */
    AppraisalRecord insert(AppraisalRecord appraisalRecord);

    /**
     * 修改数据
     *
     * @param appraisalRecord 实例对象
     * @return 实例对象
     */
    AppraisalRecord update(AppraisalRecord appraisalRecord);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 查询考核人已经考核过的
     * @param approverCode
     * @return java.util.List<com.citydo.appraisal.entity.AppraisalRecord>
     */
    List<AppraisalRecord> queryHasSubmit(String approverCode);

    /**
     * 保存记录
     * @param request
     * @return void
     */
    void saveAppraisalRecord(AppraisalRecordRequest request);
}
