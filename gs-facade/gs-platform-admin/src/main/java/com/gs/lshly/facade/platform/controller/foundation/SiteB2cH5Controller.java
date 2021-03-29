package com.gs.lshly.facade.platform.controller.foundation;


import java.util.List;

import javax.validation.Valid;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.SiteNavigationEnum;
import com.gs.lshly.common.enums.SitePCShowEnum;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteAdvertDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteAdvertPopupDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteBannerDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteBroadDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteFloorDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteNavigationDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteNoticeDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteTopicDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteVideoDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteAdvertPopupQTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteAdvertQTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteBannerQTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteBroadQTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteFloorQTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteNavigationQTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteNoticeQTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteTopicQTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteVideoQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteAdvertPopupVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteAdvertVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteBannerVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteBroadVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteNavigationVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteNoticeVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteTopicVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteVideoVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsInfoRpc;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteAdvertPopupRpc;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteAdvertRpc;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteBannerRpc;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteBroadRpc;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteFloorRpc;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteNavigationRpc;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteNoticeRpc;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteTopicRpc;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteVideoRpc;

import cn.hutool.core.util.ObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/platform/bbc/h5Site")
@Api(tags = "站点配置(B2C-H5)-v1.1.0",description =" ")
@Module(code = "configB2CH5", parent = "site", name = "B2CH5配置", index = 3)
@Slf4j
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
    private ISiteAdvertPopupRpc siteAdvertPopupRpc;
    
    @DubboReference
    private ISiteNoticeRpc siteNoticeRpc;
    
    @DubboReference
    private ISiteTopicRpc siteTopicRpc;

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
    
    @ApiOperation("广告弹窗列表-v1.1.0")
    @GetMapping("/advertPopupList")
    @Func(code="view", name="查")
    public ResponseData<PageData<SiteAdvertPopupVO.PCListVO>> listAdvertPopup(SiteAdvertPopupQTO.QTO qto) {
        qto.setTerminal(TerminalEnum.BBC.getCode());//2c
        return ResponseData.data(siteAdvertPopupRpc.pageData(qto));
    }

	@ApiOperation("广告弹窗编辑-v1.1.0")
    @PutMapping(value = "/advertPopupEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> editorAdvertPopup(@Valid @RequestBody SiteAdvertPopupDTO.ETO eto) {
    	eto.setTerminal(TerminalEnum.BBC.getCode());
    	eto.setPcShow(SitePCShowEnum.不显示.getCode());
    	eto.setSubject(SubjectEnum.积分商城.getCode());
    	siteAdvertPopupRpc.editor(eto);
        return ResponseData.success(MsgConst.SUBMIT_SUCCESS);
    }
    
    @ApiOperation("广告弹窗详情-v1.1.0")
    @GetMapping(value = "/advertPopup/{id}")
    @Func(code="view", name = "查")
    public ResponseData<SiteAdvertPopupVO.PCDetailVO> detailAdvertPopup(@PathVariable String id) {
        return ResponseData.data(siteAdvertPopupRpc.get(new SiteAdvertPopupDTO.IdDTO(id)));
    }
    
    
    @ApiOperation("广告弹窗删除-v1.1.0")
    @DeleteMapping(value = "/advertPopup/{id}")
    @Func(code="delete", name="删")
    public ResponseData<Void> deleteAdvertPopup(@PathVariable String id) {
    	SiteAdvertPopupDTO.IdDTO dto = new SiteAdvertPopupDTO.IdDTO(id);
        siteAdvertPopupRpc.delete(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }
    
    
    @ApiOperation("广告弹窗上下线-v1.1.0")
    @PutMapping(value = "/advertPopupOnoff")
    @Func(code="edit", name="改")
    public ResponseData<Void> onoffAdvertPopup(@Valid @RequestBody SiteAdvertPopupDTO.OnoffDTO dto) {
        siteAdvertPopupRpc.onoff(dto);
        return ResponseData.success(MsgConst.SUBMIT_SUCCESS);
    }
    
    
    
    @ApiOperation("公告配置列表-v1.1.0")
    @GetMapping("/noticeList")
    @Func(code="view", name="查")
    public ResponseData<PageData<SiteNoticeVO.PCListVO>> listNotice(SiteNoticeQTO.QTO qto) {
        qto.setTerminal(TerminalEnum.BBC.getCode());//2c
        return ResponseData.data(siteNoticeRpc.pageData(qto));
    }
    
    
    @ApiOperation("公告配置编辑-v1.1.0")
    @PutMapping(value = "/noticeEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> editorNotice(@Valid @RequestBody SiteNoticeDTO.ETO eto) {
    	eto.setTerminal(TerminalEnum.BBC.getCode());
    	eto.setPcShow(SitePCShowEnum.显示.getCode());
    	eto.setSubject(SubjectEnum.默认.getCode());
    	siteNoticeRpc.editor(eto);
        return ResponseData.success(MsgConst.SUBMIT_SUCCESS);
    }
    
    @ApiOperation("公告配置详情-v1.1.0")
    @GetMapping(value = "/notice/{id}")
    @Func(code="view", name = "查")
    public ResponseData<SiteNoticeVO.PCDetailVO> detailNotice(@PathVariable String id) {
        return ResponseData.data(siteNoticeRpc.get(new SiteNoticeDTO.IdDTO(id)));
    }
    
    
    @ApiOperation("公告配置删除-v1.1.0")
    @DeleteMapping(value = "/notice/{id}")
    @Func(code="delete", name="删")
    public ResponseData<Void> deleteNotice(@PathVariable String id) {
    	SiteNoticeDTO.IdDTO dto = new SiteNoticeDTO.IdDTO(id);
    	siteNoticeRpc.delete(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }
    
    @ApiOperation("专题列表-v1.1.0")
    @GetMapping("/topicList")
    @Func(code="view", name="查")
    public ResponseData<PageData<SiteTopicVO.PCListVO>> listTopic(SiteTopicQTO.QTO qto) {
        qto.setTerminal(TerminalEnum.BBC.getCode());//2c
        return ResponseData.data(siteTopicRpc.pageData(qto));
    }

	@ApiOperation("专题列表编辑-v1.1.0")
    @PutMapping(value = "/topicEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> editorTopic(@Valid @RequestBody SiteTopicDTO.ETO eto) {
    	eto.setTerminal(TerminalEnum.BBC.getCode());
    	eto.setPcShow(SitePCShowEnum.显示.getCode());
    	eto.setSubject(SubjectEnum.积分商城.getCode());
    	siteTopicRpc.editor(eto);
        return ResponseData.success(MsgConst.SUBMIT_SUCCESS);
    }
    
    @ApiOperation("专题列表详情-v1.1.0")
    @GetMapping(value = "/topic/{id}")
    @Func(code="view", name = "查")
    public ResponseData<SiteTopicVO.PCDetailVO> detailTopic(@PathVariable String id) {
        return ResponseData.data(siteTopicRpc.get(new SiteTopicDTO.IdDTO(id)));
    }
    
    
    @ApiOperation("专题列表删除-v1.1.0")
    @DeleteMapping(value = "/topic/{id}")
    @Func(code="delete", name="删")
    public ResponseData<Void> deleteTopic(@PathVariable String id) {
    	SiteTopicDTO.IdDTO dto = new SiteTopicDTO.IdDTO(id);
        siteTopicRpc.delete(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }
    
    
    @ApiOperation("专题列表上下线-v1.1.0")
    @PutMapping(value = "/topicOnoff/")
    @Func(code="edit", name="改")
    public ResponseData<Void> onoffTopic(@Valid @RequestBody SiteTopicDTO.OnoffDTO dto) {
    	siteTopicRpc.onoff(dto);
        return ResponseData.success(MsgConst.SUBMIT_SUCCESS);
    }
    
    @ApiOperation("专题列表->可选商品列表-v1.1.0")
    @GetMapping("/topicGoodsList")
    @Func(code="view", name="查")
    public ResponseData<List<SiteTopicVO.PCGoodsDetailVO>> listTopicGoods() {
        return ResponseData.data(siteTopicRpc.listGoods());
    }
    
    
    
    
}














