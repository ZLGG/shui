package ${cfg.controllerPackage};
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import ${cfg.dtoPackage!}.${cfg.pch5?upper_case}${cfg.clientName}${entity}DTO;
import ${cfg.qtoPackage!}.${cfg.pch5?upper_case}${cfg.clientName}${entity}QTO;
import ${cfg.voPackage!}.${cfg.pch5?upper_case}${cfg.clientName}${entity}VO;
import ${cfg.rpcPackage!}.I${cfg.pch5?upper_case}${cfg.clientName}${entity}Rpc;
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
* @author ${author}
* @since ${date}
*/
@RestController
@RequestMapping("/${cfg.centerName}/${entity?uncap_first}")
@Api(tags = "${cfg.modelDesc}管理")
public class ${cfg.pch5?upper_case}${cfg.clientName}${entity}Controller {

    @DubboReference
    private I${cfg.pch5?upper_case}${cfg.clientName}${entity}Rpc ${cfg.pch5?uncap_first}<#if cfg.pch5==''>${cfg.clientName?uncap_first}<#else>${cfg.clientName}</#if>${entity}Rpc;

    @ApiOperation("${cfg.modelDesc}列表")
    @GetMapping("")
    public ResponseData<PageData<${cfg.pch5?upper_case}${cfg.clientName}${entity}VO.ListVO>> list(${cfg.pch5?upper_case}${cfg.clientName}${entity}QTO.QTO qto) {
        return ResponseData.data(${cfg.pch5?uncap_first}<#if cfg.pch5==''>${cfg.clientName?uncap_first}<#else>${cfg.clientName}</#if>${entity}Rpc.pageData(qto));
    }

    @ApiOperation("${cfg.modelDesc}详情")
    @GetMapping(value = "/{id}")
    public ResponseData<${cfg.pch5?upper_case}${cfg.clientName}${entity}VO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(${cfg.pch5?uncap_first}<#if cfg.pch5==''>${cfg.clientName?uncap_first}<#else>${cfg.clientName}</#if>${entity}Rpc.detail${entity}(new ${cfg.pch5?upper_case}${cfg.clientName}${entity}DTO.IdDTO(id)));
    }

    @ApiOperation("新增${cfg.modelDesc}")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody ${cfg.pch5?upper_case}${cfg.clientName}${entity}DTO.ETO dto) {
            ${cfg.pch5?uncap_first}<#if cfg.pch5==''>${cfg.clientName?uncap_first}<#else>${cfg.clientName}</#if>${entity}Rpc.add${entity}(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除${cfg.modelDesc}")
    @DeleteMapping(value = "/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        ${cfg.pch5?upper_case}${cfg.clientName}${entity}DTO.IdDTO dto = new ${cfg.pch5?upper_case}${cfg.clientName}${entity}DTO.IdDTO(id);
        ${cfg.pch5?uncap_first}<#if cfg.pch5==''>${cfg.clientName?uncap_first}<#else>${cfg.clientName}</#if>${entity}Rpc.delete${entity}(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

<#if cfg.delBatch! =="YES">
    @ApiOperation("批量删除${cfg.modelDesc}")
    @DeleteMapping(value = "/deleteBatch")
    public ResponseData<Void> deleteBatch(@Valid @RequestBody ${cfg.pch5?upper_case}${cfg.clientName}${entity}DTO.IdListDTO dto) {
        ${cfg.pch5?uncap_first}<#if cfg.pch5==''>${cfg.clientName?uncap_first}<#else>${cfg.clientName}</#if>${entity}Rpc.deleteBatch${entity}(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }
</#if>

    @ApiOperation("修改${cfg.modelDesc}")
    @PutMapping(value = "/{id}")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody ${cfg.pch5?upper_case}${cfg.clientName}${entity}DTO.ETO eto) {
        eto.setId(id);
        ${cfg.pch5?uncap_first}<#if cfg.pch5==''>${cfg.clientName?uncap_first}<#else>${cfg.clientName}</#if>${entity}Rpc.edit${entity}(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

}
