package com.citydo.appraisal.service.impl;

import com.citydo.appraisal.entity.AppraisalInfo;
import com.citydo.appraisal.mapper.AppraisalInfoMapper;
import com.citydo.appraisal.service.AppraisalInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 考核人员信息(AppraisalInfo)表服务实现类
 *
 * @author makejava
 * @since 2022-06-29 10:50:36
 */
@Service
public class AppraisalInfoServiceImpl implements AppraisalInfoService {


    @Resource
    private AppraisalInfoMapper appraisalInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AppraisalInfo queryById(Long id) {
        return this.appraisalInfoDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param appraisalInfo 实例对象
     * @return 实例对象
     */
    @Override
    public AppraisalInfo insert(AppraisalInfo appraisalInfo) {
        this.appraisalInfoDao.insert(appraisalInfo);
        return appraisalInfo;
    }

    /**
     * 批量新增数据
     *
     * @param entities 实例对象
     * @return 实例对象
     */
    @Override
    public void insertBatch(List<AppraisalInfo> entities) {
        this.appraisalInfoDao.insertBatch(entities);
    }

    /**
     * 修改数据
     *
     * @param appraisalInfo 实例对象
     * @return 实例对象
     */
    @Override
    public AppraisalInfo update(AppraisalInfo appraisalInfo) {
        this.appraisalInfoDao.update(appraisalInfo);
        return this.queryById(appraisalInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.appraisalInfoDao.deleteById(id) > 0;
    }

    @Override
    public List<AppraisalInfo> queryByAppraisalCode(String approverCode) {
        return appraisalInfoDao.queryByAppraisalCode(approverCode);
    }

    @Override
    public AppraisalInfo getByAppraisalCodeAndstaffCode(String approverCode, String staffCode) {
        return appraisalInfoDao.getByAppraisalCodeAndstaffCode(approverCode, staffCode);
    }
}
