package com.citydo.appraisal.controller;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.citydo.appraisal.entity.AppraisalInfo;
import com.citydo.appraisal.entity.AppraisalRecord;
import com.citydo.appraisal.entity.AppraisalSys;
import com.citydo.appraisal.entity.AppraisalWeight;
import com.citydo.appraisal.enums.AppraisalLevelEnum;
import com.citydo.appraisal.exception.BizException;
import com.citydo.appraisal.request.AppraisalRecordRequest;
import com.citydo.appraisal.request.ImportUserRequest;
import com.citydo.appraisal.request.InitStaffRequest;
import com.citydo.appraisal.result.RespResult;
import com.citydo.appraisal.service.AppraisalInfoService;
import com.citydo.appraisal.service.AppraisalRecordService;
import com.citydo.appraisal.service.AppraisalService;
import com.citydo.appraisal.service.AppraisalSysService;
import com.citydo.appraisal.utils.BaseResponse;
import com.citydo.appraisal.utils.BeanCopierUtils;
import com.citydo.appraisal.utils.Minio;
import com.citydo.appraisal.utils.excel.EasyExcelUtil;
import com.citydo.appraisal.vo.ImportFilePreVO;
import com.citydo.appraisal.vo.StaffInfoVO;
import com.citydo.appraisal.vo.StaffVO;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zhangluguang
 * @description:
 * @date 2022/6/27  13:25
 */
@RestController
@Slf4j
@RequestMapping(value = "/api/appraisal")
@Api(tags = {"考核"})
public class AppraisalController {

    private static final String EASY_TOKEN_PRE = "EasyToken-";

    @Autowired
    private Minio minio;

    @Autowired
    private AppraisalService appraisalService;

    @Autowired
    private AppraisalSysService appraisalSysService;

    @Autowired
    private AppraisalRecordService recordService;

    @PostMapping(value = "/importStaff")
    @ApiOperation(value = "用户导入", httpMethod = "POST")
    public RespResult<ImportFilePreVO> importStaff(MultipartFile file) {
        if (Objects.isNull(file)) {
            return RespResult.failure("文件为空");
        }
        try {
            String filename = file.getOriginalFilename();
            String suffix = filename.substring(filename.indexOf("."));

            InputStream inputStream = file.getInputStream();
            ExcelTypeEnum excelType = suffix.endsWith(ExcelTypeEnum.XLS.getValue()) ? ExcelTypeEnum.XLS : ExcelTypeEnum.XLSX;
            List<ImportUserRequest> importResourceLabelRequests = EasyExcelUtil.readExcelWithModel(inputStream, ImportUserRequest.class, excelType, 2);
            if (CollectionUtils.isEmpty(importResourceLabelRequests)) {
                return RespResult.failure("文件内容为空");
            }
            int i = 1;
            ArrayList<String> errorList = Lists.newArrayList();
            Long isNormalTotal = 0L;
            for (ImportUserRequest e : importResourceLabelRequests) {
                Boolean isNormal = Boolean.TRUE;
                isNormal = getaBoolean(i, errorList, e, isNormal);
                i++;
                if (isNormal) {
                    isNormalTotal++;
                }
            }


            BaseResponse baseResponse = minio.upload(file);
            Object responseData = baseResponse.getResponseData();
            if (baseResponse.getResponseCode() != 0 || responseData == null) {
                return RespResult.failure("上传失败");
            }
            Map<String, Object> resultMap = (Map) responseData;

            ImportFilePreVO vo = new ImportFilePreVO();
            vo.setImportList(importResourceLabelRequests);
            ImportFilePreVO.ErrorMsg errorMsg = new ImportFilePreVO.ErrorMsg();
            errorMsg.setErrorMsg(errorList);
            errorMsg.setErrorCount(importResourceLabelRequests.size() - isNormalTotal);
            errorMsg.setNormalCount(isNormalTotal);
            vo.setErrorMsg(errorMsg);
            vo.setExcelType(excelType.name());
            vo.setFileUrl(resultMap.get("fileUrl").toString());
            return RespResult.success(vo);
        } catch (BizException e) {
            log.error("AppraisalController importStaff error,e:", e);
            return RespResult.failure(e.getMessage());
        } catch (Exception e) {
            log.error("AppraisalController importStaff error,e:", e);
        }
        return RespResult.failure("操作失败");
    }

    private Boolean getaBoolean(int i, ArrayList<String> errorList, ImportUserRequest e, Boolean isNormal) {
        if (Objects.isNull(e.getStaffName())) {
            errorList.add("第" + i + "行员工姓名为空");
            isNormal = Boolean.FALSE;
        }
        if (Objects.isNull(e.getWeight())) {
            errorList.add("第" + i + "行考核权重为空");
            isNormal = Boolean.FALSE;
        }
        if (Objects.isNull(e.getStaffCode())) {
            errorList.add("第" + i + "行员工工号为空");
            isNormal = Boolean.FALSE;
        }
        if (Objects.isNull(e.getStaffOrg())) {
            errorList.add("第" + i + "行员工部门为空");
            isNormal = Boolean.FALSE;
        }
        if (Objects.isNull(e.getStaffPosition())) {
            errorList.add("第" + i + "行员工职位为空");
            isNormal = Boolean.FALSE;
        }
        if (Objects.isNull(e.getAssessmentSys())) {
            errorList.add("第" + i + "行员工考核体系为空");
            isNormal = Boolean.FALSE;
        }
        if (Objects.isNull(e.getOneApproverCode())) {
            errorList.add("第" + i + "行一级考核人工号为空");
            isNormal = Boolean.FALSE;
        }
        if (Objects.isNull(e.getOneApprover())) {
            errorList.add("第" + i + "行一级考核人姓名为空");
            isNormal = Boolean.FALSE;
        }
        return isNormal;
    }


    @PostMapping(value = "/initStaff")
    @ApiOperation(value = "初始化数据", httpMethod = "POST")
    public RespResult<Void> initStaff(@RequestBody InitStaffRequest request) {
        try {
            URL url = new URL(request.getUrl());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置超时间为3秒
            conn.setConnectTimeout(3 * 1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //得到输入流
            InputStream inputStream = conn.getInputStream();
            ArrayList<AppraisalInfo> appraisalInfos = Lists.newArrayList();
            List<ImportUserRequest> importResourceLabelRequests = EasyExcelUtil.readExcelWithModel(inputStream, ImportUserRequest.class, ExcelTypeEnum.valueOf(request.getExcelType()), 2);

            ArrayList<AppraisalWeight> appraisalWeights = Lists.newArrayList();
            for (ImportUserRequest e : importResourceLabelRequests) {
                if (check(e)) continue;
                AppraisalWeight appraisalWeight = BeanCopierUtils.copyProperties(e, AppraisalWeight.class);
                appraisalWeights.add(appraisalWeight);
                AppraisalInfo appraisalInfo = BeanCopierUtils.copyProperties(e, AppraisalInfo.class);
                appraisalInfo.setApprover(e.getOneApprover());
                appraisalInfo.setApproverCode(e.getOneApproverCode());
                appraisalInfo.setLevel(AppraisalLevelEnum.ONE_LEVEL.getType());
                appraisalInfos.add(appraisalInfo);
                if (Objects.isNull(e.getTwoApproverCode())) {
                    continue;
                }
                AppraisalInfo twoAppraisal = BeanCopierUtils.copyDeepPros(appraisalInfo, AppraisalInfo.class);
                twoAppraisal.setApprover(e.getTwoApprover());
                twoAppraisal.setApproverCode(e.getTwoApproverCode());
                twoAppraisal.setLevel(AppraisalLevelEnum.TWO_LEVEL.getType());
                appraisalInfos.add(twoAppraisal);
                if (Objects.isNull(e.getThreeApproverCode())) {
                    continue;
                }
                AppraisalInfo threeAppraisal = BeanCopierUtils.copyDeepPros(appraisalInfo, AppraisalInfo.class);
                threeAppraisal.setApprover(e.getThreeApprover());
                threeAppraisal.setApproverCode(e.getThreeApproverCode());
                threeAppraisal.setLevel(AppraisalLevelEnum.THREE_LEVEL.getType());
                appraisalInfos.add(threeAppraisal);
            }
            appraisalService.inintAppraisal(appraisalInfos, appraisalWeights);
            //appraisalInfoService.insertBatch(BeanCopierUtils.copyListProperties(importResourceLabelRequests, AppraisalInfo.class));
            return RespResult.success();
        } catch (IOException e) {
            log.error("AppraisalController initStaff error,e:", e);
        } catch (Exception e) {
            log.error("AppraisalController initStaff error,e:", e);
        }
        return RespResult.failure("初始化失败");
    }

    private boolean check(ImportUserRequest e) {
        if (Objects.isNull(e.getStaffName())) {
            return true;
        }
        if (Objects.isNull(e.getStaffCode())) {
            return true;
        }
        if (Objects.isNull(e.getStaffOrg())) {
            return true;
        }
        if (Objects.isNull(e.getStaffPosition())) {
            return true;
        }
        if (Objects.isNull(e.getAssessmentSys())) {
            return true;
        }
        if (Objects.isNull(e.getOneApproverCode())) {
            return true;
        }
        if (Objects.isNull(e.getOneApprover())) {
            return true;
        }
        return false;
    }

    @ApiOperation(value = "获取部门员工列表", httpMethod = "GET")
    @GetMapping(value = "/getStaffByOrg")
    public RespResult<List<StaffVO>> getStaffByOrg(@ApiParam(value = "考核人手机号") String mobile) {
        try {
            ArrayList<StaffVO> staffVOS = Lists.newArrayList();

            //查询考核员工
            List<AppraisalInfo> appraisalInfos = appraisalService.getStaffByOrg(mobile);
            if (CollectionUtils.isEmpty(appraisalInfos)) {
                return RespResult.success(staffVOS);
            }
            //查询考核员工是否已经填报过
            List<String> staffCodeList = appraisalInfos.stream().map(e -> e.getStaffCode()).collect(Collectors.toList());
            List<AppraisalRecord> appraisalRecords = appraisalService.queryHasSubmit(staffCodeList, mobile);
            Set<String> hasSubmitStaff = appraisalRecords.stream().map(e -> e.getStaffCode()).collect(Collectors.toSet());

            Map<String, List<AppraisalInfo>> orgStaffMap = appraisalInfos.stream().collect(Collectors.groupingBy(AppraisalInfo::getStaffOrg));
            int i = 1;
            List<AppraisalSys> appraisalSys = appraisalSysService.queryAll();
            Map<String, AppraisalSys> appraisalSysMap = appraisalSys.stream().filter(e->Objects.nonNull(e.getAssessmentSys())).collect(Collectors.toMap(AppraisalSys::getAssessmentSys, Function.identity()));

            for (Map.Entry<String, List<AppraisalInfo>> e : orgStaffMap.entrySet()) {
                StaffVO staffVO = new StaffVO();
                staffVO.setStaffOrg(e.getKey());
                staffVO.setLabel(e.getKey());
                staffVO.setValue(String.valueOf(i++));

                List<StaffInfoVO> staffInfoVOS = BeanCopierUtils.copyListProperties(e.getValue(), StaffInfoVO.class);
                staffInfoVOS.stream().forEach(t->{
                    t.setStaffName(hasSubmitStaff.contains(t.getStaffCode()) ? t.getStaffName() + "✅" : t.getStaffName());
                    AppraisalSys sys = appraisalSysMap.get(t.getAssessmentSys());
                    if (Objects.nonNull(sys)) {
                        t.setFormName(sys.getFormName());
                        t.setFormTable(sys.getFormKey());
                        t.setValue(sys.getFormKey());
                    }
                    t.setLabel(t.getStaffName());
                });

                staffVO.setStaffList(staffInfoVOS);

                staffVOS.add(staffVO);
            }
//            ArrayList<StaffVO> staffVOS = Lists.newArrayList();
//            StaffVO staffVO = new StaffVO();
//
//            StaffInfoVO staffInfoVO= new StaffInfoVO();
//            staffInfoVO.setStaffOrg("后端开发一组");
//            staffInfoVO.setStaffCode("2306");
//            staffInfoVO.setStaffName("张三");
//            staffInfoVO.setAssessmentSys("");
//            staffInfoVO.setStaffPosition("后端");
//            staffInfoVO.setFormName("管理体系绩效考核汇总表-营销");
//            staffInfoVO.setFormTable("subform_table_m_marketing");
//            staffInfoVO.setLabel(staffInfoVO.getStaffCode());
//            staffInfoVO.setValue(staffInfoVO.getFormTable());
//            StaffInfoVO staffInfoVO2= new StaffInfoVO();
//
//            staffInfoVO2.setStaffOrg("后端开发一组");
//            staffInfoVO2.setStaffCode("2300");
//            staffInfoVO2.setStaffName("王五");
//            staffInfoVO2.setAssessmentSys("");
//            staffInfoVO2.setStaffPosition("后端");
//            staffInfoVO2.setFormName("管理体系绩效考核汇总表-营销");
//            staffInfoVO2.setFormTable("subform_table_m_marketing");
//            staffInfoVO2.setLabel(staffInfoVO2.getStaffName());
//            staffInfoVO2.setValue(staffInfoVO2.getFormTable());
//            staffVO.setStaffOrg("后端一组");
//            staffVO.setLabel("后端一组");
//            staffVO.setValue("1");
//            staffVO.setStaffList(Lists.newArrayList(staffInfoVO2, staffInfoVO));
//            staffVOS.add(staffVO);
//
//
//            StaffVO staffVO1 = new StaffVO();
//
//            StaffInfoVO staffInfoVO1= new StaffInfoVO();
//            staffInfoVO1.setStaffOrg("后端开发二组");
//            staffInfoVO1.setStaffCode("2307");
//            staffInfoVO1.setStaffName("李四");
//            staffInfoVO1.setAssessmentSys("");
//            staffInfoVO1.setStaffPosition("后端");
//            staffInfoVO1.setFormName("管理体系绩效考核汇总表-技术");
//            staffInfoVO1.setFormTable("subform_table_m_t");
//            staffInfoVO1.setLabel(staffInfoVO1.getStaffCode());
//            staffInfoVO1.setValue(staffInfoVO1.getFormTable());
//            staffVO1.setStaffOrg("后端二组");
//            staffVO1.setStaffList(Lists.newArrayList(staffInfoVO1));
//            staffVO1.setLabel("后端二组");
//            staffVO1.setValue("2");
//            staffVOS.add(staffVO1);

            return RespResult.success(staffVOS);
        } catch (BizException e) {
            log.error("AppraisalController getStaffByOrg error,e:", e);
            return RespResult.failure(e.getErrorMsg());
        } catch (Exception e) {
            log.error("AppraisalController getStaffByOrg error,e:", e);
        }
        return RespResult.failure("获取失败");
    }

    @ApiOperation(value = "保存考核记录", httpMethod = "POST")
    @PostMapping(value = "/saveAppraisalRecord")
    public RespResult<Void> saveAppraisalRecord(@RequestBody AppraisalRecordRequest request) {
        try {
            if (Objects.isNull(request)) {
                return RespResult.failure("参数为空");
            }
            recordService.saveAppraisalRecord(request);
            return RespResult.success();
        } catch (BizException e) {
            log.error("AppraisalController saveAppraisalRecord error,e:", e);
        } catch (Exception e) {
            log.error("AppraisalController saveAppraisalRecord error,e:", e);
        }
        return RespResult.failure("操作失败");
    }


//
//    @GetMapping("/verifyToken")
//    @ApiOperation("联系人鉴权")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "phone", value = "手机号码", dataType = "String"),
//            @ApiImplicitParam(name = "easyToken", value = "简单token", dataType = "string")
//    })
//    public RespResult<Boolean> verifyToken(@RequestParam String phone, @RequestParam String easyToken) {
//        if (Strings.isNullOrEmpty(easyToken)) {
//            return RespResult.success(Boolean.FALSE);
//        }
//        // 验证简单 Token，验证不通过则走后续的验证码逻辑
//        String redisToken = (String) RedisCache.get(EASY_TOKEN_PRE + phone);
//        if (Strings.isNullOrEmpty(redisToken) || !redisToken.equals(easyToken)) {
//            return RespResult.success(Boolean.FALSE);
//        }
//
//        return RespResult.success(Boolean.TRUE);
//    }
//
//    @GetMapping(value = "/getStaffBy")
//    @ApiOperation(value = "获取考核人列表", httpMethod = "GET")
//    public RespResult<List<StaffVO>> getStaffBy() {
//
//        return RespResult.success(Lists.newArrayList());
//    }
}

