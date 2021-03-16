package com.gs.lshly.facade.merchant.controller.pc.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.dto.PCMerchDataNoticeDTO;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.qto.PCMerchDataNoticeQTO;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.vo.PCMerchDataNoticeVO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataNoticeTypeQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataNoticeTypeVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.merchadmin.pc.foundation.IPCMerchDataNoticeRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* <p>
*  前端控制器
* </p>
*
* @author xxfc
* @since 2020-11-16
*/
@RestController
@RequestMapping("/merchadmin/dataNotice")
@Api(tags = "商家通知管理",description = " ")
@Module(code = "noticeBusiness", parent = "shop", name = "商家通知", index = 3)
public class PCMerchDataNoticeController {

    @DubboReference
    private IPCMerchDataNoticeRpc pcMerchDataNoticeRpc;


    @ApiOperation("通知类型列表")
    @GetMapping("/noticTypeList")
    @Func(code="view", name="查")
    public ResponseData<List<PCMerchDataNoticeVO.NoticeTypeListVO>> noticeTypeList(PCMerchDataNoticeQTO.NoticeTypeQTO qto) {
        return ResponseData.data(pcMerchDataNoticeRpc.noticeTypeList(qto));
    }

    @ApiOperation("商家通知列表")
    @GetMapping("")
    @Func(code="view", name="查")
    public ResponseData<PageData<PCMerchDataNoticeVO.ListVO>> list(PCMerchDataNoticeQTO.QTO qto) {
        return ResponseData.data(pcMerchDataNoticeRpc.pageData(qto));
    }

    @ApiOperation("商家通知详情")
    @GetMapping(value = "/{id}")
    @Func(code="view", name="查")
    public ResponseData<PCMerchDataNoticeVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(pcMerchDataNoticeRpc.detailDataNotice(new PCMerchDataNoticeDTO.IdDTO(id)));
    }

}
