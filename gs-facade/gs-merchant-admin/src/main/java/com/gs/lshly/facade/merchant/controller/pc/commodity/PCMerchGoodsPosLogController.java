package com.gs.lshly.facade.merchant.controller.pc.commodity;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsPosLogDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsInfoQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsPosLogQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsPosLogVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchGoodsPosLogRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
* <p>
*  前端控制器
* </p>
*
* @author Starry
* @since 2020-12-15
*/
@RestController
@RequestMapping("/merchadmin/goodsPosLog")
@Api(tags = "商品pos记录管理管理")
public class PCMerchGoodsPosLogController {

    @DubboReference
    private IPCMerchGoodsPosLogRpc pcMerchGoodsPosLogRpc;

    @ApiOperation("商品pos记录管理列表")
    @GetMapping("")
    public ResponseData<PageData<PCMerchGoodsPosLogVO.DetailListVO>> list(PCMerchGoodsPosLogQTO.QTO qto) {
        return ResponseData.data(pcMerchGoodsPosLogRpc.pageData(qto));
    }

    @ApiOperation("商品pos记录管理详情")
    @GetMapping(value = "/{id}")
    public ResponseData<PCMerchGoodsPosLogVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(pcMerchGoodsPosLogRpc.detailGoodsPosLog(new PCMerchGoodsPosLogDTO.IdDTO(id)));
    }

    @ApiOperation("删除商品pos记录管理")
    @DeleteMapping(value = "/{numIid}")
    public ResponseData<Void> delete(@PathVariable String numIid) {
        PCMerchGoodsPosLogDTO.NumIidDTO dto = new PCMerchGoodsPosLogDTO.NumIidDTO(numIid);
        pcMerchGoodsPosLogRpc.deleteGoodsPosLog(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("批量删除商品pos记录管理")
    @PostMapping(value = "/deleteBatch")
    public ResponseData<Void> delete(@RequestBody PCMerchGoodsPosLogDTO.NumIidListDTO dto) {
        pcMerchGoodsPosLogRpc.deleteBatchGoodsPosLog(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("导出商品pos相关数据到Excel表格")
    @GetMapping(value = "/exportData")
    public void export(@ApiIgnore HttpServletResponse response, PCMerchGoodsPosLogDTO.IdListDTO dto) throws Exception {
        ExportDataDTO exportData = pcMerchGoodsPosLogRpc.export(dto);
        ExcelUtil.export(exportData, response);
    }

}
