package com.gs.lshly.facade.merchant.controller.pc.commodity;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.excel.EasyExcel;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.facade.merchant.excel.pc.BaseExcelImportDataListener;
import com.gs.lshly.facade.merchant.excel.pc.GoodsInfoExcelDataListener;
import com.gs.lshly.middleware.redis.RedisUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchGoodsInfoImportRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lxus
 * @since 2021-01-20
 */
@RestController
@RequestMapping("/merchant/pc/goodsInfo/import")
@Api(tags = "商品信息-导入管理-v1.1.0")
@Slf4j
public class PCMerchGoodsInfoImportController {

    @DubboReference
    private IPCMerchGoodsInfoImportRpc importRpc;

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation("商品导入-v1.1.0")
    @PostMapping(value = "")
    @ResponseBody
    public ResponseData<Void> importExcel(MultipartFile file) throws IOException {
        String traceId = RandomUtil.randomString(16);
        EasyExcel.read(file.getInputStream(), GoodsInfoExcelDataListener.GoodsInfoExcelData.class, new GoodsInfoExcelDataListener(importRpc, redisUtil, traceId)).sheet().doRead();
        return BaseExcelImportDataListener.importResult(redisUtil, traceId);
    }

    @ApiOperation("下载错误数据到Excel表格")
    @GetMapping(value = "/exportErrData/{traceId}")
    public void export(@PathVariable String traceId, @ApiIgnore HttpServletResponse response) throws Exception {
        ExportDataDTO exportData = BaseExcelImportDataListener.exportErrorData(redisUtil, traceId, GoodsInfoExcelDataListener.GoodsInfoExcelData.class);
        ExcelUtil.export(exportData, response);
    }

}
