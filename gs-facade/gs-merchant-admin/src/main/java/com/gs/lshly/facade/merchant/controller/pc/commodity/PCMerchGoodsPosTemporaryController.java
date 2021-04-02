package com.gs.lshly.facade.merchant.controller.pc.commodity;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsPosTemporaryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsPosTemporaryQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsPosTemporaryVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchGoodsPosTemporaryRpc;
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
* @since 2021-02-23
*/
@RestController
@RequestMapping("/merchadmin/goodsPosTemporary")
@Api(tags = "商品临时库管理")
public class PCMerchGoodsPosTemporaryController {

    @DubboReference
    private IPCMerchGoodsPosTemporaryRpc pcMerchGoodsPosTemporaryRpc;

    @ApiOperation("商品临时库列表")
    @GetMapping("")
    public ResponseData<PageData<PCMerchGoodsPosTemporaryVO.ListVO>> list(PCMerchGoodsPosTemporaryQTO.QTO qto) {
        return ResponseData.data(pcMerchGoodsPosTemporaryRpc.pageData(qto));
    }

    @ApiOperation("商品临时库详情")
    @GetMapping(value = "/{id}")
    public ResponseData<PCMerchGoodsPosTemporaryVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(pcMerchGoodsPosTemporaryRpc.getEditDetailEto(new PCMerchGoodsPosTemporaryDTO.PosSpuIdDTO(id)));
    }


    @ApiOperation("删除商品临时库")
    @PostMapping(value = "deleteBatches")
    public ResponseData<Void> delete(@RequestBody PCMerchGoodsPosTemporaryDTO.IdListDTO dto) {
        pcMerchGoodsPosTemporaryRpc.deleteGoodsPosTemporary(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("导出商品pos相关数据到Excel表格")
    @GetMapping(value = "/exportData")
    public void export(@ApiIgnore HttpServletResponse response, PCMerchGoodsPosTemporaryDTO.IdListDTO dto) throws Exception {
        ExportDataDTO exportData = pcMerchGoodsPosTemporaryRpc.export(dto);
        ExcelUtil.export(exportData, response);
    }


    @ApiOperation("修改发布状态")
    @PostMapping(value = "/updateState/{id}")
    public ResponseData<Void> updateState(@PathVariable String id) {
        pcMerchGoodsPosTemporaryRpc.modifyReleaseState(new PCMerchGoodsPosTemporaryDTO.PosSpuIdDTO(id));
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }

}
