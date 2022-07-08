package com.citydo.appraisal.service;

import com.citydo.appraisal.entity.AppraisalInfo;
import com.citydo.appraisal.entity.AppraisalRecord;
import com.citydo.appraisal.entity.AppraisalWeight;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangluguang
 * @description:
 * @date 2022/6/27  13:43
 */
public interface AppraisalService {

    /**
     * 导入数据
     * @param appraisalInfos
	 * @param appraisalWeights
     * @return void
     */
    void inintAppraisal(ArrayList<AppraisalInfo> appraisalInfos, ArrayList<AppraisalWeight> appraisalWeights);

    /**
     * 根据考核人手机号查询，考核员工列表
     * @param mobile
     * @return java.util.List<com.citydo.appraisal.entity.AppraisalInfo>
     */
    List<AppraisalInfo> getStaffByOrg(String mobile);

    /**
     * 查询已经填报的
     * @param staffCodeList
	 * @param mobile
     * @return void
     */
    List<AppraisalRecord> queryHasSubmit(List<String> staffCodeList, String mobile);
}
