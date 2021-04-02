package com.gs.lshly.facade.platform.controller.foundation;


import cn.hutool.core.util.ObjectUtil;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.*;
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
@RequestMapping("/platform/bbc/h5Site")
@Api(tags = "站点配置(B2C-H5)",description =" ")
@Module(code = "configB2CH5", parent = "site", name = "B2CH5配置", index = 3)
public class SiteB2cH5Controller {

    @DubboReference
    private ISiteBannerRpc siteBannerRpc;

    @DubboReference
    private ISiteNavigationRpc siteNavigationRpc;

    @DubboReference
    private ISiteFloorRpc siteFloorRpc;

    @DubboReference
    private ISiteBroadRpc siteBroadRpc;

    @DubboReference
    private ISiteVideoRpc siteVideoRpc;

    @DubboReference
    private ISiteAdvertRpc siteAdvertRpc;

    @DubboReference
    private ISiteFloorNewRpc siteFloorNewRpc;

    @DubboReference
    private ISiteActiveRpc siteActiveRpc;


    @ApiOperation("轮播图列表")
    @GetMapping("/bannerList")
    @Func(code="view", name="查")
    public ResponseData<List<SiteBannerVO.H5ListVO>> listBanner(SiteBannerQTO.H5QTO qto) {
        qto.setTerminal(TerminalEnum.BBC.getCode());
        return ResponseData.data(siteBannerRpc.h5List(qto));
    }

    @ApiOperation("轮播图编辑")
    @PutMapping("/bannerEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> updateBanner(@RequestBody SiteBannerDTO.H5DTO dto) {
        if(ObjectUtil.isNotEmpty(dto.getBannerList())){
            dto.getBannerList().forEach(item -> {
                item.setTerminal(TerminalEnum.BBC.getCode());
            });
        }
        siteBannerRpc.h5Editor(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }


    @ApiOperation("菜单导航列表")
    @GetMapping("/navigationList")
    @Func(code="view", name="查")
    public ResponseData<List<SiteNavigationVO.H5ListVO>> navigationList(SiteNavigationQTO.H5QTO qto) {
        qto.setType(SiteNavigationEnum.菜单导航.getCode());
        qto.setTerminal(TerminalEnum.BBC.getCode());
        return ResponseData.data(siteNavigationRpc.h5List(qto));
    }

    @ApiOperation("菜单导航编辑")
    @PutMapping("/navigationEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> navigationEditor(  @RequestBody  SiteNavigationDTO.H5DTO  udtos) {
        if(ObjectUtil.isNotEmpty(udtos.getNavigationList())){
            udtos.getNavigationList().forEach(udto -> {
                udto.setTerminal(TerminalEnum.BBC.getCode());
                udto.setType(SiteNavigationEnum.菜单导航.getCode());
            });
        }
        siteNavigationRpc.h5Editor(udtos);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("楼层列表")
    @GetMapping("/floorList")
    @Func(code="view", name="查")
    public ResponseData<List<SiteFloorVO.H5ListVO>> floorList(SiteFloorQTO.H5QTO qto) {
        qto.setTerminal(TerminalEnum.BBC.getCode());
        return ResponseData.data(siteFloorRpc.h5List(qto));
    }

    @ApiOperation("楼层编辑")
    @PutMapping("/floorEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> floorEditor(@RequestBody  SiteFloorDTO.H5ETO h5ETO) {
        if(ObjectUtil.isNotEmpty(h5ETO.getFloorList())){
            h5ETO.getFloorList().forEach(item -> {
                item.setTerminal(TerminalEnum.BBC.getCode());
            });
        }
        siteFloorRpc.h5Editor(h5ETO);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }


    @ApiOperation("公告文章列表")
    @GetMapping("/broadList")
    @Func(code="view", name="查")
    public ResponseData<List<SiteBroadVO.ListVO>> broadList(SiteBroadQTO.QTO qto) {
        qto.setTerminal(TerminalEnum.BBC.getCode());
        return ResponseData.data(siteBroadRpc.list(qto));
    }


    @ApiOperation("公告文章编辑")
    @PutMapping(value = "/broadEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> broadEditor(@Valid @RequestBody SiteBroadDTO.ETO eto) {
        if(ObjectUtil.isNotEmpty(eto.getItemList())){
            eto.getItemList().forEach(item -> {
                item.setTerminal(TerminalEnum.BBC.getCode());
            });
        }
        siteBroadRpc.editSiteBroad(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }



    @ApiOperation("视频列表")
    @GetMapping("/videoList")
    @Func(code="view", name="查")
    public ResponseData<List<SiteVideoVO.H5ListVO>> h5VideoList(SiteVideoQTO.H5QTO qto) {
        qto.setTerminal(TerminalEnum.BBC.getCode());
        return ResponseData.data(siteVideoRpc.h5List(qto));
    }


    @ApiOperation("视频编辑")
    @PutMapping("/videoEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> videoEditor(@Valid @RequestBody SiteVideoDTO.H5DTO udto) {
        if(ObjectUtil.isNotEmpty(udto.getVideoList())){
            for (SiteVideoDTO.H5ItemDTO uvideo : udto.getVideoList()   ) {
                uvideo.setTerminal(TerminalEnum.BBC.getCode());
            }
        }
        siteVideoRpc.h5Editor(udto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("商品分类广告图列表")
    @GetMapping("/advertList")
    @Func(code="view", name="查")
    public ResponseData<List<SiteAdvertVO.H5CategoryListVO>> advertList(SiteAdvertQTO.H5CategoryQTO qto) {
        qto.setTerminal(TerminalEnum.BBC.getCode());
        return ResponseData.data(siteAdvertRpc.h5CategoryList(qto));
    }

    @ApiOperation("商品分类广告图编辑")
    @PutMapping(value = "/advertEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> advertEditor(@Valid @RequestBody SiteAdvertDTO.H5CategoryETO eto) {
        eto.setTerminal(TerminalEnum.BBC.getCode());
        siteAdvertRpc.h5CategoryEditor(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }


    @ApiOperation("专栏广告图列表")
    @GetMapping("/advertSubjectList")
    @Func(code="view", name="查")
    public ResponseData<List<SiteAdvertVO.H5SubjectListVO>> advertSubjectList(SiteAdvertQTO.H5SubjectQTO qto) {
        qto.setTerminal(TerminalEnum.BBC.getCode());
        return ResponseData.data(siteAdvertRpc.h5SubjectList(qto));
    }

    @ApiOperation("专栏广告图编辑")
    @PutMapping(value = "/advertSubjectEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> advertSubjectEditor(@Valid @RequestBody SiteAdvertDTO.H5SubjectETO eto) {
        if(ObjectUtil.isNotEmpty(eto.getAdvertList())){
            eto.getAdvertList().forEach(item -> {
                item.setTerminal(TerminalEnum.BBC.getCode());
            });
        }
        siteAdvertRpc.h5SubjectEditor(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("楼层配置列表")
    @GetMapping("/siteFloorNewList")
    @Func(code="view", name="查")
    public ResponseData<List<SiteFloorNewVO.ListVO>> siteFloorNewList() {
        SiteFloorNewDTO.ShowDTO dto = new SiteFloorNewDTO.ShowDTO();
        dto.setTerminal(TerminalEnum.BBC.getCode());
        dto.setPcShow(SitePCShowEnum.不显示.getCode());
        return ResponseData.data(siteFloorNewRpc.listSiteFloorNewVO(dto));
    }

    @ApiOperation("配置楼层配置")
    @PutMapping(value = "/saveSiteFloorNewList")
    @Func(code="edit", name="改")
    public ResponseData<Void> saveSiteFloorNewList(@Valid @RequestBody List<SiteFloorNewDTO.ETO> etoList) {
        if(ObjectUtil.isNotEmpty(etoList)){
            etoList.forEach(item -> {
                item.setTerminal(TerminalEnum.BBC.getCode());
                item.setPcShow(SitePCShowEnum.不显示.getCode());
            });
        }
        siteFloorNewRpc.saveSiteFloorNew(etoList);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }

    @ApiOperation("活动图片配置")
    @GetMapping("/getSiteActiveVO")
    @Func(code="view", name="查")
    public ResponseData<SiteActiveVO.ListVO> getSiteActiveVO(SiteActiveDTO.QueryDTO dto) {
        dto.setPcShow(PcH5Enum.NO.getCode());
        dto.setTerminal(TerminalEnum.BBC.getCode());
        return ResponseData.data(siteActiveRpc.getListVO(dto));
    }

    @ApiOperation("保存活动图片配置")
    @PostMapping(value = "/saveSiteActiveVO")
    @Func(code="edit", name="改")
    public ResponseData<Void>  saveSiteActiveVO(@Valid @RequestBody SiteActiveDTO.ETO eto) {
        eto.setPcShow(PcH5Enum.NO.getCode());
        eto.setTerminal(TerminalEnum.BBC.getCode());
        siteActiveRpc.saveSiteActive(eto);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }

}














