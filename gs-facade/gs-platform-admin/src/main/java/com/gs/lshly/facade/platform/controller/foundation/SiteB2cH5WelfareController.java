package com.gs.lshly.facade.platform.controller.foundation;


import cn.hutool.core.util.ObjectUtil;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.SiteNavigationEnum;
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
@RequestMapping("/platform/bbc/h5SiteWelfare")
@Api(tags = "B2CH5扶贫专栏",description =" ")
@Module(code = "covertyColumnB2CH5", parent = "site", name = "B2CH5扶贫专栏", index = 3)
public class SiteB2cH5WelfareController {

    @DubboReference
    private ISiteBannerRpc siteBannerRpc;

    @DubboReference
    private ISiteFloorRpc siteFloorRpc;



    @ApiOperation("轮播图列表")
    @GetMapping("/bannerList")
    @Func(code="view", name="查")
    public ResponseData<List<SiteBannerVO.H5ListVO>> listBanner(SiteBannerQTO.H5QTO qto) {
        qto.setSubject(SubjectEnum.扶贫.getCode());
        qto.setTerminal(TerminalEnum.BBC.getCode());
        return ResponseData.data(siteBannerRpc.h5List(qto));
    }

    @ApiOperation("轮播图编辑")
    @PutMapping("/bannerEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> updateBanner(@RequestBody SiteBannerDTO.H5DTO dto) {
        if(ObjectUtil.isNotEmpty(dto.getBannerList())){
            dto.getBannerList().forEach(item -> {
                item.setSubject(SubjectEnum.扶贫.getCode());
                item.setTerminal(TerminalEnum.BBC.getCode());
            });
        }
        siteBannerRpc.h5Editor(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("楼层列表")
    @GetMapping("/floorList")
    @Func(code="view", name="查")
    public ResponseData<List<SiteFloorVO.H5ListVO>> floorList(SiteFloorQTO.H5QTO qto) {
        qto.setSubject(SubjectEnum.扶贫.getCode());
        qto.setTerminal(TerminalEnum.BBC.getCode());
        return ResponseData.data(siteFloorRpc.h5List(qto));
    }

    @ApiOperation("楼层编辑")
    @PutMapping("/floorEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> floorEditor(@RequestBody  SiteFloorDTO.H5ETO h5ETO) {
        if(ObjectUtil.isNotEmpty(h5ETO.getFloorList())){
            h5ETO.getFloorList().forEach(item -> {
                item.setSubject(SubjectEnum.扶贫.getCode());
                item.setTerminal(TerminalEnum.BBC.getCode());
            });
        }
        siteFloorRpc.h5Editor(h5ETO);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

}














