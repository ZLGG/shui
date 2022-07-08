package com.citydo.appraisal.service.impl;

import com.citydo.appraisal.entity.AppraisalInfo;
import com.citydo.appraisal.entity.AppraisalPerson;
import com.citydo.appraisal.entity.AppraisalRecord;
import com.citydo.appraisal.entity.AppraisalWeight;
import com.citydo.appraisal.exception.BizException;
import com.citydo.appraisal.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author zhangluguang
 * @description:
 * @date 2022/6/27  13:43
 */
@Service
public class AppraisalServiceImpl implements AppraisalService {

    @Autowired
    private AppraisalInfoService appraisalInfoService;

    @Autowired
    private AppraisalWeightService appraisalWeightService;

    @Autowired
    private AppraisalPersonService appraisalPersonService;

    @Autowired
    private AppraisalRecordService appraisalRecordService;



    @Override
    public void inintAppraisal(ArrayList<AppraisalInfo> appraisalInfos, ArrayList<AppraisalWeight> appraisalWeights) {
        appraisalInfoService.insertBatch(appraisalInfos);
        appraisalWeightService.insertBatch(appraisalWeights);
    }

    @Override
    public List<AppraisalInfo> getStaffByOrg(String mobile) {
        AppraisalPerson appraisalPerson = appraisalPersonService.getAppraisalPersonByMobile(mobile);
        if (Objects.isNull(appraisalPerson)) {
            throw new BizException("考核人不存在");
        }
        List<AppraisalInfo> appraisalInfos = appraisalInfoService.queryByAppraisalCode(appraisalPerson.getApproverCode());
        return appraisalInfos;
    }


    @Override
    public List<AppraisalRecord> queryHasSubmit(List<String> staffCodeList, String mobile) {
        AppraisalPerson appraisalPerson = appraisalPersonService.getAppraisalPersonByMobile(mobile);

        List<AppraisalRecord> appraisalRecords = appraisalRecordService.queryHasSubmit(appraisalPerson.getApproverCode());
        return appraisalRecords;
    }
}
