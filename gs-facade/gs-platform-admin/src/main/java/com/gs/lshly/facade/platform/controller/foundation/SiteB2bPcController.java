package com.gs.lshly.facade.platform.controller.foundation;

import cn.hutool.core.util.ObjectUtil;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.PcH5Enum;
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
@RequestMapping("/platform/bbb/pcSite")
@Api(tags = "站点配置(BBB-PC)",description = " ")
@Module(code = "configB2BPc", parent = "site", name = "B2BPC配置", index = 2)
public class SiteB2bPcController {

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
    private ISiteBottomArticleRpc siteBottomArticleRpc;

    @DubboReference
    private ISiteActiveRpc siteActiveRpc;


    @ApiOperation("顶部链接列表")
    @GetMapping("/topList")
    @Func(code="view", name="查")
    public ResponseData<List<SiteNavigationVO.PCListVO>> pcTopList(SiteNavigationQTO.PCQTO qto) {
        qto.setType(SiteNavigationEnum.顶部链接.getCode());
        qto.setTerminal(TerminalEnum.BBB.getCode());
        return ResponseData.data(siteNavigationRpc.pcList(qto));
    }

    @ApiOperation("顶部链接编辑")
    @PutMapping("/topEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> pcTopEditor(@RequestBody SiteNavigationDTO.PCDTO eto) {
        if(ObjectUtil.isNotEmpty(eto.getNavigationList())){
            eto.getNavigationList().forEach(item -> {
                item.setTerminal(TerminalEnum.BBB.getCode());
                item.setType(SiteNavigationEnum.顶部链接.getCode());
            });
        }
        siteNavigationRpc.pcEditor(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("菜单导航列表")
    @GetMapping("/menuList")
    @Func(code="view", name="查")
    public ResponseData<List<SiteNavigationVO.PCListVO>> menuList(SiteNavigationQTO.PCQTO qto) {
        qto.setTerminal(TerminalEnum.BBB.getCode());
        qto.setType(SiteNavigationEnum.菜单导航.getCode());
        return ResponseData.data(siteNavigationRpc.pcList(qto));
    }

    @ApiOperation("菜单导航编辑")
    @PutMapping("/menuEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> menuEditor2(@RequestBody SiteNavigationDTO.PC2DTO eto) {
        eto.setTerminal(TerminalEnum.BBB.getCode());
        eto.setType(SiteNavigationEnum.菜单导航.getCode());
        siteNavigationRpc.pcEditor2(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("轮播图列表(支持专栏)")
    @GetMapping("/pcBannerList")
    @Func(code="view", name="查")
    public ResponseData<List<SiteBannerVO.PCListVO>> pcBannerList(SiteBannerQTO.PCQTO qto) {
        qto.setTerminal(TerminalEnum.BBB.getCode());
        return ResponseData.data(siteBannerRpc.pcList(qto));
    }

    @ApiOperation("轮播图编辑(支持专栏)")
    @PutMapping("/pcBannerEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> pcBannerEditor(@RequestBody SiteBannerDTO.PCDTO dto) {
        if(ObjectUtil.isNotEmpty(dto.getBannerList())){
            dto.getBannerList().forEach(item -> {
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
        qto.setTerminal(TerminalEnum.BBB.getCode());
        return ResponseData.data(siteAdvertRpc.pcAdvertGroupList(qto));
    }

    @ApiOperation("组合广告图编辑(支持专栏)")
    @PutMapping(value = "/pcAdvertGroupEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> pcAdvertGroupEditor(@Valid @RequestBody SiteAdvertDTO.PCGroupETO eto) {
        eto.setTerminal(TerminalEnum.BBB.getCode());
        siteAdvertRpc.pcAdvertGroupEditor(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("楼层列表(支持专栏)")
    @GetMapping("/pcFloorList")
    @Func(code="view", name="查")
    public ResponseData<List<SiteFloorVO.PCListVO>> pcFloorList(SiteFloorQTO.PCQTO qto) {
        qto.setTerminal(TerminalEnum.BBB.getCode());
        return ResponseData.data(siteFloorRpc.pcList(qto));
    }

    @ApiOperation("楼层编辑(支持专栏)")
    @PutMapping("/pcFloorEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> pcFloorEditor(@RequestBody  SiteFloorDTO.PCETO pcETO) {
        pcETO.setTerminal(TerminalEnum.BBB.getCode());
        siteFloorRpc.pcEditor(pcETO);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }


    @ApiOperation("底部文章列表")
    @GetMapping("/pcBottomArticleList")
    @Func(code="view", name="查")
    public ResponseData<List<SiteBottomArticleVO.PCListVO>> pcBottomArticleList(SiteBottomArticleQTO.PCQTO qto) {
        qto.setTerminal(TerminalEnum.BBB.getCode());
        qto.setPcShow(PcH5Enum.YES.getCode());
        qto.setSubject(SubjectEnum.默认.getCode());
        return ResponseData.data(siteBottomArticleRpc.list(qto));
    }

    @ApiOperation("底部文章编辑")
    @PutMapping("/pcBottomArticleEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> pcBottomArticleEditor( @Valid @RequestBody SiteBottomArticleDTO.PCUDTO eto) {
        eto.setTerminal(TerminalEnum.BBB.getCode());
        eto.setPcShow(PcH5Enum.YES.getCode());
        eto.setSubject(SubjectEnum.默认.getCode());
        siteBottomArticleRpc.bottomArticleEditor(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("视频列表(支持专栏)")
    @GetMapping("/pcVideoList")
    @Func(code="view", name="查")
    public ResponseData<List<SiteVideoVO.PCListVO>> pcVideoList(SiteVideoQTO.PCQTO qto) {
        qto.setTerminal(TerminalEnum.BBB.getCode());
        return ResponseData.data(siteVideoRpc.pcList(qto));
    }


    @ApiOperation("视频编辑(支持专栏)")
    @PutMapping("/pcVideoEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> videoEditor(@Valid @RequestBody SiteVideoDTO.PCDTO dto) {
        if(ObjectUtil.isNotEmpty(dto.getVideoList())){
            for (SiteVideoDTO.PCItemDTO dtoItem : dto.getVideoList()   ) {
                dtoItem.setTerminal(TerminalEnum.BBB.getCode());
            }
        }
        siteVideoRpc.pcEditor(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }


    @ApiOperation("单张广告图列表(支持专栏)")
    @GetMapping("/pcBillBoardList")
    @Func(code="view", name="查")
    public ResponseData<SiteAdvertVO.PCBillBoardListVO> pcBillBoardList(SiteAdvertQTO.PCBillBoardQTO qto) {
        qto.setTerminal(TerminalEnum.BBB.getCode());
        return ResponseData.data(siteAdvertRpc.pcBillBoardList(qto));
    }

    @ApiOperation("单张广告图编辑(支持专栏)")
    @PutMapping(value = "/pcBillBoardEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> pcBillBoardEditor(@Valid @RequestBody SiteAdvertDTO.PCBillBoardETO eto) {
        eto.setTerminal(TerminalEnum.BBB.getCode());
        siteAdvertRpc.pcBillBoardEditor(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("单张广告图(多个)列表(支持专栏)")
    @GetMapping("/pcBillBoardMoreList")
    @Func(code="view", name="查")
    public ResponseData<List<SiteAdvertVO.PCBillBoardListVO>> pcBillBoardMoreList(SiteAdvertQTO.PCBillBoardQTO qto) {
        qto.setTerminal(TerminalEnum.BBB.getCode());
        return ResponseData.data(siteAdvertRpc.pcBillBoardMoreList(qto));
    }

    @ApiOperation("单张广告图(多个)编辑(支持专栏)")
    @PutMapping(value = "/pcBillBoardMoreEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> pcBillBoardMoreEditor(@Valid @RequestBody SiteAdvertDTO.PCBillBoardMoreETO eto) {
        eto.setTerminal(TerminalEnum.BBB.getCode());
        siteAdvertRpc.pcBillBoardMoreEditor(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("活动图片配置")
    @GetMapping("/getSiteActiveVO")
    @Func(code="view", name="查")
    public ResponseData<SiteActiveVO.ListVO> getSiteActiveVO(SiteActiveDTO.QueryDTO dto) {
        dto.setPcShow(PcH5Enum.YES.getCode());
        dto.setTerminal(TerminalEnum.BBB.getCode());
        return ResponseData.data(siteActiveRpc.getListVO(dto));
    }

    @ApiOperation("保存活动图片配置")
    @PostMapping(value = "/saveSiteActiveVO")
    @Func(code="edit", name="改")
    public ResponseData<Void>  saveSiteActiveVO(@Valid @RequestBody SiteActiveDTO.ETO eto) {
        eto.setPcShow(PcH5Enum.YES.getCode());
        eto.setTerminal(TerminalEnum.BBB.getCode());
        siteActiveRpc.saveSiteActive(eto);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }

}













