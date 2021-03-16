package com.gs.lshly.facade.merchant.excel.pc;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.middleware.excel.IExcelImportValidator;
import com.gs.lshly.middleware.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lxus
 */
@Slf4j
public abstract class BaseExcelImportDataListener<T> extends AnalysisEventListener<T> implements IExcelImportValidator<T> {

    private RedisUtil redisUtil;
    private String traceId;

    public BaseExcelImportDataListener(RedisUtil redisUtil, String traceId) {
        this.redisUtil = redisUtil;
        this.traceId = traceId;
    }

    @Override
    public void invoke(T data, AnalysisContext context) {
        Integer totalRowNumber = context.readSheetHolder().getApproximateTotalRowNumber()-1;
        Integer rowIndex = context.readRowHolder().getRowIndex();
        log.info("共:{}行数据，当前解析第:{}行，数据内容:{};", totalRowNumber, rowIndex, JSON.toJSONString(data));

        boolean valid = validate(rowIndex, data);
        //验证成功
        if (valid) {
            doValidSuccess(data);
        }
    }

    protected abstract void doValidSuccess(T data);

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    @Override
    public boolean validate(Integer rowIndex, T data) {
        log.info("校验第:{}行数据", rowIndex);
        return doValid(rowIndex, data);
    }

    protected abstract boolean doValid(Integer rowIndex, T data);

    protected void addError(int rowIndex, int colIndex, String errMsg, T data) {
        Object errListData = redisUtil.hget(traceId, IExcelImportValidator.ERROR_DATA);
        if (errListData == null) {
            errListData = new ArrayList<>();
        }
        ((ArrayList) errListData).add(data);
        redisUtil.hset(traceId, IExcelImportValidator.ERROR_DATA, errListData, 5 * 60);
        redisUtil.hset(traceId, IExcelImportValidator.ERROR_COUNT, ((ArrayList) errListData).size(), 5 * 60);

        Object errListMsg = redisUtil.hget(traceId, IExcelImportValidator.ERROR_MSG);
        if (errListMsg == null) {
            errListMsg = new ArrayList<>();
        }

        ((ArrayList) errListMsg).add("第" + rowIndex + "行,第" + colIndex + "列:" + errMsg);
        redisUtil.hset(traceId, IExcelImportValidator.ERROR_MSG, errListMsg, 5 * 60);
    }

    public static ResponseData importResult(RedisUtil redisUtil, String traceId){
        Object errListMsg = redisUtil.hget(traceId, IExcelImportValidator.ERROR_MSG);
        if (errListMsg == null) {
            return ResponseData.success("导入成功");
        }else {
            Object errListSize = redisUtil.hget(traceId, IExcelImportValidator.ERROR_COUNT);
            Map result = new HashMap<>();
            result.put("traceId", traceId);
            result.put("errMsg", errListMsg);
            result.put("errCount", errListSize);
            return ResponseData.data(result);
        }
    }


    public static <T> ExportDataDTO exportErrorData(RedisUtil redisUtil, String traceId, Class<T> clazz) throws Exception {
        Object errListData = redisUtil.hget(traceId, IExcelImportValidator.ERROR_DATA);
        if (errListData == null) {
            throw new BusinessException("数据已过期或任务id错误");
        } else {
           return ExcelUtil.treatmentBean((List)errListData, clazz);
        }
    }

}
