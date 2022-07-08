package com.citydo.appraisal.service.impl;

import com.citydo.appraisal.entity.AppraisalInfo;
import com.citydo.appraisal.entity.AppraisalPerson;
import com.citydo.appraisal.entity.AppraisalRecord;
import com.citydo.appraisal.mapper.AppraisalRecordMapper;
import com.citydo.appraisal.request.AppraisalRecordRequest;
import com.citydo.appraisal.service.AppraisalInfoService;
import com.citydo.appraisal.service.AppraisalPersonService;
import com.citydo.appraisal.service.AppraisalRecordService;
import com.citydo.appraisal.service.AppraisalSysService;
import com.citydo.appraisal.utils.BeanCopierUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 考核记录(AppraisalRecord)表服务实现类
 *
 * @author makejava
 * @since 2022-07-06 11:37:48
 */
@Service
public class AppraisalRecordServiceImpl implements AppraisalRecordService {
    @Resource
    private AppraisalRecordMapper appraisalRecordDao;

    @Autowired
    private AppraisalPersonService appraisalPersonService;

    @Autowired
    private AppraisalInfoService appraisalInfoService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AppraisalRecord queryById(Long id) {
        return this.appraisalRecordDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param appraisalRecord 实例对象
     * @return 实例对象
     */
    @Override
    public AppraisalRecord insert(AppraisalRecord appraisalRecord) {
        this.appraisalRecordDao.insert(appraisalRecord);
        return appraisalRecord;
    }

    /**
     * 修改数据
     *
     * @param appraisalRecord 实例对象
     * @return 实例对象
     */
    @Override
    public AppraisalRecord update(AppraisalRecord appraisalRecord) {
        this.appraisalRecordDao.update(appraisalRecord);
        return this.queryById(appraisalRecord.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.appraisalRecordDao.deleteById(id) > 0;
    }

    @Override
    public List<AppraisalRecord> queryHasSubmit(String approverCode) {
        return appraisalRecordDao.queryHasSubmit(approverCode);
    }

    @Override
    public void saveAppraisalRecord(AppraisalRecordRequest request) {
        AppraisalPerson appraisalPerson = appraisalPersonService.getAppraisalPersonByMobile(request.getMobile());
        if (Objects.isNull(appraisalPerson)) {
            return;
        }
        AppraisalInfo appraisalInfo = appraisalInfoService.getByAppraisalCodeAndstaffCode(appraisalPerson.getApproverCode(), request.getStaffCode());
        if (Objects.isNull(appraisalInfo)) {
            return;
        }
        AppraisalRecord appraisalRecord = appraisalRecordDao.getByAppraisalCodeAndstaffCode(appraisalPerson.getApproverCode(), appraisalInfo.getStaffCode());
        if (Objects.nonNull(appraisalRecord)) {
            return;
        }
        AppraisalRecord record = BeanCopierUtils.copyProperties(request, AppraisalRecord.class);
        record.setApprover(appraisalInfo.getApprover());
        record.setApproverCode(appraisalInfo.getApproverCode());
        record.setStaffOrg(appraisalInfo.getStaffOrg());
        record.setStaffPosition(appraisalInfo.getStaffPosition());
        record.setAssessmentSys(appraisalInfo.getAssessmentSys());
        appraisalRecordDao.insert(record);
    }
}
