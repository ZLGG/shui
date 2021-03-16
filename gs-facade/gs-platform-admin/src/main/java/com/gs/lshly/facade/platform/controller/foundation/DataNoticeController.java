package com.gs.lshly.facade.platform.controller.foundation;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataNoticeDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataNoticeQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataNoticeVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.foundation.IDataNoticeRpc;
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
@RequestMapping("/platform/dataNotice")
@Api(tags = "通知管理",description = " ")
@Module(code = "noticeBusiness", parent = "bussiness", name = "商家通知", index = 8)
public class DataNoticeController {

    @DubboReference
    private IDataNoticeRpc dataNoticeRpc;

    @ApiOperation("通知列表")
    @GetMapping("")
    @Func(code="view", name="查")
    public ResponseData<PageData<DataNoticeVO.ListVO>> pageData(DataNoticeQTO.QTO qto) {
        return ResponseData.data(dataNoticeRpc.pageData(qto));
    }

    @ApiOperation("新增通知")
    @PostMapping("")
    @Func(code="add", name="增")
    public ResponseData<Void> add(@Valid @RequestBody DataNoticeDTO.ETO dto) {
        dataNoticeRpc.addDataNotice(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("通知详情")
    @GetMapping(value = "/{id}")
    @Func(code="view", name="查")
    public ResponseData<DataNoticeVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(dataNoticeRpc.detailNotice(new DataNoticeDTO.IdDTO(id)));
    }

    @ApiOperation("删除通知")
    @PostMapping(value = "/deleteBeatch")
    @Func(code="delete", name="删")
    public ResponseData<Void> delete(@Valid @RequestBody DataNoticeDTO.IdListDTO dto) {
        dataNoticeRpc.deleteBatchDataNotice(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }




}
