package com.gs.lshly.facade.platform.controller.foundation;

import cn.hutool.core.util.ObjectUtil;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.*;
import com.gs.lshly.common.struct.platadmin.foundation.qto.*;
import com.gs.lshly.common.struct.platadmin.foundation.vo.*;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.foundation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/platform/bbb/pcSiteWelfare")
@Api(tags = "B2BPC扶贫专栏",description = " ")
@Module(code = "covertyColumnB2BPC", parent = "site", name = "B2BPC扶贫专栏", index = 2)
public class SiteB2bPcWelfareController {

    @DubboReference
    private ISiteBannerRpc siteBannerRpc;

    @DubboReference
    private ISiteFloorRpc siteFloorRpc;

    @DubboReference
    private ISiteAdvertRpc siteAdvertRpc;

    @ApiOperation("轮播图列表(支持专栏)")
    @GetMapping("/pcBannerList")
    @Func(code="view", name="查")
    public ResponseData<List<SiteBannerVO.PCListVO>> pcBannerList(SiteBannerQTO.PCQTO qto) {
        qto.setSubject(SubjectEnum.扶贫.getCode());
        qto.setTerminal(TerminalEnum.BBB.getCode());
        return ResponseData.data(siteBannerRpc.pcList(qto));
    }

    @ApiOperation("轮播图编辑(支持专栏)")
    @PutMapping("/pcBannerEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> pcBannerEditor(@RequestBody SiteBannerDTO.PCDTO dto) {
        if(ObjectUtil.isNotEmpty(dto.getBannerList())){
            dto.getBannerList().forEach(item -> {
                item.setSubject(SubjectEnum.扶贫.getCode());
                item.setTerminal(TerminalEnum.BBB.getCode());
            });
        }
        siteBannerRpc.pcEditor(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("组合广告图列表(支持专栏)")
    @GetMapping("/pcAdvertGroupList")
    @Func(code="view", name="查")
    public ResponseData<List<SiteAdvertVO.PCGroupListVO>> pcAdvertGroupList(SiteAdvertQTO.PCSubjectQTO qto) {
        qto.setSubject(SubjectEnum.扶贫.getCode());
        qto.setTerminal(TerminalEnum.BBB.getCode());
        return ResponseData.data(siteAdvertRpc.pcAdvertGroupList(qto));
    }

    @ApiOperation("组合广告图编辑(支持专栏)")
    @PutMapping(value = "/pcAdvertGroupEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> pcAdvertGroupEditor(@Valid @RequestBody SiteAdvertDTO.PCGroupETO eto) {
        eto.setSubject(SubjectEnum.扶贫.getCode());
        eto.setTerminal(TerminalEnum.BBB.getCode());
        siteAdvertRpc.pcAdvertGroupEditor(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("楼层列表(支持专栏)")
    @GetMapping("/pcFloorList")
    @Func(code="view", name="查")
    public ResponseData<List<SiteFloorVO.PCListVO>> pcFloorList(SiteFloorQTO.PCQTO qto) {
        qto.setSubject(SubjectEnum.扶贫.getCode());
        qto.setTerminal(TerminalEnum.BBB.getCode());
        return ResponseData.data(siteFloorRpc.pcList(qto));
    }

    @ApiOperation("楼层编辑(支持专栏)")
    @PutMapping("/pcFloorEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> pcFloorEditor(@RequestBody  SiteFloorDTO.PCETO pcETO) {
        pcETO.setSubject(SubjectEnum.扶贫.getCode());
        pcETO.setTerminal(TerminalEnum.BBB.getCode());
        siteFloorRpc.pcEditor(pcETO);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("单张广告图列表(支持专栏)")
    @GetMapping("/pcBillBoardList")
    @Func(code="view", name="查")
    public ResponseData<SiteAdvertVO.PCBillBoardListVO> pcBillBoardList(SiteAdvertQTO.PCBillBoardQTO qto) {
        qto.setSubject(SubjectEnum.扶贫.getCode());
        qto.setTerminal(TerminalEnum.BBB.getCode());
        return ResponseData.data(siteAdvertRpc.pcBillBoardList(qto));
    }

    @ApiOperation("单张广告图编辑(支持专栏)")
    @PutMapping(value = "/pcBillBoardEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> pcBillBoardEditor(@Valid @RequestBody SiteAdvertDTO.PCBillBoardETO eto) {
        eto.setSubject(SubjectEnum.扶贫.getCode());
        eto.setTerminal(TerminalEnum.BBB.getCode());
        siteAdvertRpc.pcBillBoardEditor(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("单张广告图(多个)列表(支持专栏)")
    @GetMapping("/pcBillBoardMoreList")
    @Func(code="view", name="查")
    public ResponseData<List<SiteAdvertVO.PCBillBoardListVO>> pcBillBoardMoreList(SiteAdvertQTO.PCBillBoardQTO qto) {
        qto.setSubject(SubjectEnum.扶贫.getCode());
        qto.setTerminal(TerminalEnum.BBB.getCode());
        return ResponseData.data(siteAdvertRpc.pcBillBoardMoreList(qto));
    }

    @ApiOperation("单张广告图(多个)编辑(支持专栏)")
    @PutMapping(value = "/pcBillBoardMoreEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> pcBillBoardMoreEditor(@Valid @RequestBody SiteAdvertDTO.PCBillBoardMoreETO eto) {
        eto.setSubject(SubjectEnum.扶贫.getCode());
        eto.setTerminal(TerminalEnum.BBB.getCode());
        siteAdvertRpc.pcBillBoardMoreEditor(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

}













