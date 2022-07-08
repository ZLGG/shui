package com.citydo.appraisal.service.impl;

import com.citydo.appraisal.entity.AppraisalPerson;
import com.citydo.appraisal.mapper.AppraisalPersonMapper;
import com.citydo.appraisal.service.AppraisalPersonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 考核人(AppraisalPerson)表服务实现类
 *
 * @author makejava
 * @since 2022-07-06 11:37:47
 */
@Service
public class AppraisalPersonServiceImpl implements AppraisalPersonService {
    @Resource
    private AppraisalPersonMapper appraisalPersonDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AppraisalPerson queryById(Long id) {
        return this.appraisalPersonDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param appraisalPerson 实例对象
     * @return 实例对象
     */
    @Override
    public AppraisalPerson insert(AppraisalPerson appraisalPerson) {
        this.appraisalPersonDao.insert(appraisalPerson);
        return appraisalPerson;
    }

    /**
     * 修改数据
     *
     * @param appraisalPerson 实例对象
     * @return 实例对象
     */
    @Override
    public AppraisalPerson update(AppraisalPerson appraisalPerson) {
        this.appraisalPersonDao.update(appraisalPerson);
        return this.queryById(appraisalPerson.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.appraisalPersonDao.deleteById(id) > 0;
    }

    @Override
    public AppraisalPerson getAppraisalPersonByMobile(String mobile) {
        return appraisalPersonDao.getAppraisalPersonByMobile(mobile);
    }


}
