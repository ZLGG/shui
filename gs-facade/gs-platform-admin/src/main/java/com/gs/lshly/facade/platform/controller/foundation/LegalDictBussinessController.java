package com.gs.lshly.facade.platform.controller.foundation;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.common.LegalDictDTO;
import com.gs.lshly.common.struct.common.LegalDictQTO;
import com.gs.lshly.common.struct.common.LegalDictVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
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
@RequestMapping("/platform/legalDict/bussiness")
@Api(tags = "法人单位管理(企业)",description = " ")
@Module(code = "listBussiness", parent = "enterprise", name = "企业列表", index = 8)
public class LegalDictBussinessController {

    @DubboReference
    private ILegalDictRpc legalDictRpc;

    @ApiOperation("企业法人列表")
    @GetMapping("")
    @Func(code="view", name="查")
    public ResponseData<PageData<LegalDictVO.ListVO>> list(LegalDictQTO.QTO qto) {
        return ResponseData.data(legalDictRpc.pageData(qto));
    }

    @ApiOperation("企业法人详情")
    @GetMapping(value = "/{id}")
    @Func(code="view", name="查")
    public ResponseData<LegalDictVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(legalDictRpc.detailLegalDict(new LegalDictDTO.IdDTO(id)));
    }

    @ApiOperation("编辑企业法人")
    @PutMapping(value = "/editor/{id}")
    @Func(code="edit", name="改")
    public ResponseData<Void> editor(@PathVariable String id,@Valid @RequestBody LegalDictDTO.ETO dto) {
        dto.setId(id);
        legalDictRpc.editLegalDict(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("批量删除法人(企业/个人)")
    @PostMapping(value = "/deleteBatch")
    @Func(code="delete", name="删")
    public ResponseData<Void> deleteBatch(@Valid @RequestBody LegalDictDTO.IdListDTO dto) {
        legalDictRpc.deleteBatchLegalDict(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("批量导出法人(企业/个人)")
    @GetMapping(value = "/exportBatch")
    @Func(code="view", name="查")
    public void exportBatch(LegalDictDTO.IdListDTO dto,@ApiIgnore HttpServletResponse response) throws Exception{
        ExportDataDTO exportData =  legalDictRpc.export(dto);
        ExcelUtil.export(exportData, response);
    }

}
