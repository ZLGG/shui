package com.gs.lshly.facade.platform.controller.foundation;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.common.CorpTypeDictDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.CorpCertDictQTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.CorpTypeDictQTO;
import com.gs.lshly.common.struct.common.CorpCertDictVO;
import com.gs.lshly.common.struct.common.CorpTypeDictVO;
import com.gs.lshly.rpc.api.common.ICorpCertDictRpc;
import com.gs.lshly.rpc.api.common.ICorpTypeDictRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
* <p>
*  前端控制器
* </p>
*
* @author xxfc
* @since 2020-10-09
*/
@RestController
@RequestMapping("/platform/corpTypeDict")
@Api(tags = "企业类型管理",description = " ")
public class CorpTypeDictController {

    @DubboReference
    private ICorpTypeDictRpc corpTypeDictRpc;

    @DubboReference
    private ICorpCertDictRpc corpCertDictRpc;

    @ApiOperation("企业类型列表")
    @GetMapping("")
    public ResponseData<PageData<CorpTypeDictVO.ListVO>> list(CorpTypeDictQTO.QTO qto) {
        return ResponseData.data(corpTypeDictRpc.pageData(qto));
    }

    @ApiOperation("企业类型详情")
    @GetMapping(value = "/{id}")
    public ResponseData<CorpTypeDictVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(corpTypeDictRpc.detailCorpTypeDict(new CorpTypeDictDTO.IdDTO(id)));
    }

    @ApiOperation("新增企业类型")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody CorpTypeDictDTO.ETO dto) {
        corpTypeDictRpc.addCorpTypeDict(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除企业类型")
    @PostMapping(value = "/deleteBatch")
    public ResponseData<Void> deleteBatchCorpTypeDict(@Valid @RequestBody CorpTypeDictDTO.IdListDTO dto) {
        corpTypeDictRpc.deleteBatchCorpTypeDict(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("编辑企业类型")
    @PutMapping(value = "/{id}")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody CorpTypeDictDTO.ETO eto) {
        eto.setId(id);
        corpTypeDictRpc.editCorpTypeDict(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("获取企业类型的所有证照")
    @GetMapping(value = "/getCorpTypeCert/{corpTypeId}")
    public ResponseData<List<CorpCertDictVO.SimpleVO>> ListCorpTypeCert(@PathVariable @ApiParam(value = "企业类型ID")String corpTypeId) {
        CorpCertDictQTO.CorpTypeIdQTO qto = new CorpCertDictQTO.CorpTypeIdQTO();
        qto.setCorpTypeId(corpTypeId);
        return ResponseData.data(corpCertDictRpc.ListCorpTypeCert(qto));
    }

}
