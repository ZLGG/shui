package com.gs.lshly.facade.platform.controller.foundation;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SysSmDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SysSmTemplateDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SysSmTemplateVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SysSmVO;
import com.gs.lshly.rpc.api.platadmin.foundation.ISysSmRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
* <p>
*  前端控制器
* </p>
*
* @author Starry
* @since 2021-03-20
*/
@RestController
@RequestMapping("/platadmin/sysSm")
@Api(tags = "短信平台设置管理")
public class SysSmController {

    @DubboReference
    private ISysSmRpc SysSmRpc;

    @ApiOperation("短信平台设置列表")
    @GetMapping("")
    public ResponseData<List<SysSmVO.ListVO>> list() {
        return ResponseData.data(SysSmRpc.listSysSM(new BaseDTO()));
    }


    @ApiOperation("保存短信平台设置")
    @PostMapping("/saveSysSm")
    public ResponseData<Void> saveSysSm(@Valid @RequestBody SysSmDTO.ETO dto) {
            SysSmRpc.saveSysSm(dto);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }

    @ApiOperation("删除短信平台设置")
    @DeleteMapping(value = "deleteSysSm/{id}")
    public ResponseData<Void> deleteSysSm(@PathVariable String id) {
        SysSmDTO.IdDTO dto = new SysSmDTO.IdDTO(id);
        SysSmRpc.deleteSysSm(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("保存短信设置")
    @PostMapping("saveSysSmTemplate")
    public ResponseData<Void> saveSysSmTemplate(@Valid @RequestBody SysSmTemplateDTO.ETO dto) {
        SysSmRpc.saveSysSmTemplate(dto);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }

    @ApiOperation("删除短信设置")
    @DeleteMapping(value = "deleteSysSmTemplate/{id}")
    public ResponseData<Void> deleteSysSmTemplate(@PathVariable String id) {
        SysSmTemplateDTO.IdDTO dto = new SysSmTemplateDTO.IdDTO(id);
        SysSmRpc.deleteSysSmTemplate(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("短信设置列表分页")
    @GetMapping(value = "pageDataResponseData")
    public ResponseData<PageData<SysSmTemplateVO.DetailVO>> pageDataResponseData(BaseQTO qto) {
        return ResponseData.data(SysSmRpc.templatePage(qto));
    }




}
