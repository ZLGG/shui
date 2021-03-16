package com.gs.lshly.facade.platform.controller.foundation;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.common.CorpCertDictDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.CorpCertDictQTO;
import com.gs.lshly.common.struct.common.CorpCertDictVO;
import com.gs.lshly.rpc.api.common.ICorpCertDictRpc;
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
* @author xxfc
* @since 2020-10-09
*/
@RestController
@RequestMapping("/platform/corpCertDict")
@Api(tags = "企业证照管理",description = " ")
public class CorpCertDictController {

    @DubboReference
    private ICorpCertDictRpc corpCertDictRpc;

    @ApiOperation("企业证照列表")
    @GetMapping("")
    public ResponseData<PageData<CorpCertDictVO.ListVO>> list(CorpCertDictQTO.QTO qto) {
        return ResponseData.data(corpCertDictRpc.pageData(qto));
    }

    @ApiOperation("企业证照详情")
    @GetMapping(value = "/{id}")
    public ResponseData<CorpCertDictVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(corpCertDictRpc.detailCorpCertDict(new CorpCertDictDTO.IdDTO(id)));
    }

    @ApiOperation("新增企业证照")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody CorpCertDictDTO.ETO dto) {
        corpCertDictRpc.addCorpCertDict(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除企业证照")
    @PostMapping(value = "/deleteBatch")
    public ResponseData<Void> deleteBatch(@Valid @RequestBody CorpCertDictDTO.IdListDTO dto) {
        corpCertDictRpc.deleteBatchCorpCertDict(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("修改企业证照")
    @PutMapping(value = "/{id}")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody CorpCertDictDTO.ETO eto) {
        eto.setId(id);
        corpCertDictRpc.editCorpCertDict(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

}
