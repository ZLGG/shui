package com.citydo.appraisal.service.impl;

import com.citydo.appraisal.entity.AppraisalSys;
import com.citydo.appraisal.mapper.AppraisalSysMapper;
import com.citydo.appraisal.service.AppraisalSysService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 考核体系(AppraisalSys)表服务实现类
 *
 * @author makejava
 * @since 2022-07-07 10:41:44
 */
@Service
public class AppraisalSysServiceImpl implements AppraisalSysService {


    @Resource
    private AppraisalSysMapper appraisalSysDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AppraisalSys queryById(Long id) {
        return this.appraisalSysDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param appraisalSys 实例对象
     * @return 实例对象
     */
    @Override
    public AppraisalSys insert(AppraisalSys appraisalSys) {
        this.appraisalSysDao.insert(appraisalSys);
        return appraisalSys;
    }

    /**
     * 修改数据
     *
     * @param appraisalSys 实例对象
     * @return 实例对象
     */
    @Override
    public AppraisalSys update(AppraisalSys appraisalSys) {
        this.appraisalSysDao.update(appraisalSys);
        return this.queryById(appraisalSys.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.appraisalSysDao.deleteById(id) > 0;
    }

    @Override
    public List<AppraisalSys> queryAll() {
        return appraisalSysDao.queryAll(null);
    }
}
