package com.gs.lshly.facade.platform.controller.foundation;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataNoticeTypeDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataNoticeTypeQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataNoticeTypeVO;
import com.gs.lshly.rpc.api.platadmin.foundation.IDataNoticeTypeRpc;
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
 * @author fdfdf
 * @since 2020-09-14
 */
@RestController
@RequestMapping("/platform/dataNoticeType")
@Api(tags = "通知类型管理",description = " ")
public class DataNoticeTypeController {

    @DubboReference
    private IDataNoticeTypeRpc dataNoticeTypeRpc;

    @ApiOperation("通知类型列表")
    @GetMapping("")
    public ResponseData<PageData<DataNoticeTypeVO.ListVO>> list(DataNoticeTypeQTO.QTO qo) {
        return ResponseData.data(dataNoticeTypeRpc.list(qo));
    }

    @ApiOperation("新增通知类型")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody DataNoticeTypeDTO.ETO dto) {
        dataNoticeTypeRpc.addDataNoticeType(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("通知类型详情")
    @GetMapping(value = "/{id}")
    public ResponseData<DataNoticeTypeVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(dataNoticeTypeRpc.getDataNoticeType(new DataNoticeTypeDTO.IdDTO(id)));
    }

    @ApiOperation("删除通知类型")
    @DeleteMapping(value = "/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        DataNoticeTypeDTO.IdDTO dto = new DataNoticeTypeDTO.IdDTO(id);
        dataNoticeTypeRpc.deleteDataNoticeType(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("修改通知类型")
    @PutMapping(value = "/{id}")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody DataNoticeTypeDTO.EditNoticeTypeDTO dto) {
        dto.setId(id);
        dataNoticeTypeRpc.updateDataNoticeType(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }




}
