package com.gs.lshly.facade.platform.controller.foundation;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SysMailDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SysMailVO;
import com.gs.lshly.rpc.api.platadmin.foundation.ISysMailRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
* <p>
*  前端控制器
* </p>
*
* @author Starry
* @since 2021-03-20
*/
@RestController
@RequestMapping("/platadmin/sysMail")
@Api(tags = "邮件模板设置管理")
public class SysMailController {

    @DubboReference
    private ISysMailRpc SysMailRpc;


    @ApiOperation("保存邮件模板设置")
    @GetMapping("/pageData")
    public ResponseData<PageData<SysMailVO.ListVO>> page(BaseQTO qto) {
        return ResponseData.data(SysMailRpc.pageData(qto));
    }

    @ApiOperation("删除邮件模板设置")
    @DeleteMapping(value = "/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        SysMailDTO.IdDTO dto = new SysMailDTO.IdDTO(id);
        SysMailRpc.deleteSysMail(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("保存邮件模板设置")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody SysMailDTO.ETO dto) {
        SysMailRpc.saveSysMail(dto);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }



}
