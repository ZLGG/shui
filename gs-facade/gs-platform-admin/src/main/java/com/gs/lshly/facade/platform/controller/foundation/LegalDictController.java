package com.gs.lshly.facade.platform.controller.foundation;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.common.LegalDictDTO;
import com.gs.lshly.common.struct.common.LegalDictQTO;
import com.gs.lshly.common.struct.common.LegalDictVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.rpc.api.common.ILegalDictRpc;
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
* @author xxfc
* @since 2020-10-06
*/
@RestController
@RequestMapping("/platform/legalDict")
@Api(tags = "法人单位管理",description = " ")
public class LegalDictController {

    @DubboReference
    private ILegalDictRpc legalDictRpc;

    @ApiOperation("企业法人列表")
    @GetMapping("")
    public ResponseData<PageData<LegalDictVO.ListVO>> list(LegalDictQTO.QTO qto) {
        return ResponseData.data(legalDictRpc.pageData(qto));
    }

    @ApiOperation("企业法人详情")
    @GetMapping(value = "/{id}")
    public ResponseData<LegalDictVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(legalDictRpc.detailLegalDict(new LegalDictDTO.IdDTO(id)));
    }

    @ApiOperation("编辑企业法人")
    @PutMapping(value = "/editor/{id}")
    public ResponseData<Void> editor(@PathVariable String id,@Valid @RequestBody LegalDictDTO.ETO dto) {
        dto.setId(id);
        legalDictRpc.editLegalDict(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    //-------------------------------------------个人-------------------------------


    @ApiOperation("个人法人列表")
    @GetMapping("/nalList")
    public ResponseData<PageData<LegalDictVO.NalListVO>> nalList(LegalDictQTO.NalQTO qto) {
        return ResponseData.data(legalDictRpc.nalList(qto));
    }

    @ApiOperation("个人法人详情")
    @GetMapping(value = "/nalDetails/{id}")
    public ResponseData<LegalDictVO.NalDetailVO> nalDetails(@PathVariable String id) {
        return ResponseData.data(legalDictRpc.nalDetailLegalDict(new LegalDictDTO.IdDTO(id)));
    }

    @ApiOperation("编辑个人法人")
    @PutMapping(value = "/nalEditor/{id}")
    public ResponseData<Void> nalEditor(@PathVariable String id,@Valid @RequestBody LegalDictDTO.NalETO dto) {
        dto.setId(id);
        legalDictRpc.nalEditLegalDict(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    //----------------------------------通用-----------------------------------

    @ApiOperation("批量删除法人(企业/个人)")
    @PostMapping(value = "/deleteBatch")
    public ResponseData<Void> deleteBatch(@Valid @RequestBody LegalDictDTO.IdListDTO dto) {
        legalDictRpc.deleteBatchLegalDict(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("批量导出法人(企业/个人)")
    @GetMapping(value = "/exportBatch")
    public void exportBatch(LegalDictDTO.IdListDTO dto,@ApiIgnore HttpServletResponse response) throws Exception{
        ExportDataDTO exportData =  legalDictRpc.export(dto);
        ExcelUtil.export(exportData, response);
    }

}
