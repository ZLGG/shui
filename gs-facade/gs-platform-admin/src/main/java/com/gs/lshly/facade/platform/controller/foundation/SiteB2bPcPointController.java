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
import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.enums.SiteNavigationEnum;
import com.gs.lshly.common.enums.SitePCShowEnum;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteAdvertDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteBannerDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteBottomArticleDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteFloorDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteNavigationDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteTopicDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteAdvertQTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteBannerQTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteBottomArticleQTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteFloorQTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteNavigationQTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteTopicQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteAdvertVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteBannerVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteBottomArticleVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteNavigationVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteTopicVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteAdvertRpc;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteBannerRpc;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteBottomArticleRpc;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteBroadRpc;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteFloorRpc;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteNavigationRpc;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteTopicRpc;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteVideoRpc;

import cn.hutool.core.util.ObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/platform/bbb/pcSitePoint")
@Api(tags = "B2BPC积分专栏-v1.1.0",description = " ")
@Module(code = "covertyColumnB2BPC", parent = "site", name = "B2BPC扶贫专栏", index = 2)
public class SiteB2bPcPointController {

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
	    private ISiteTopicRpc siteTopicRpc;


	    @ApiOperation("顶部链接列表-v1.1.0")
	    @GetMapping("/topList")
	    @Func(code="view", name="查")
	    public ResponseData<List<SiteNavigationVO.PCListVO>> pcTopList(SiteNavigationQTO.PCQTO qto) {
	        qto.setType(SiteNavigationEnum.顶部链接.getCode());
	        qto.setTerminal(TerminalEnum.BBB.getCode());
	        return ResponseData.data(siteNavigationRpc.pcList(qto));
	    }

	    @ApiOperation("顶部链接编辑-v1.1.0")
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

	    @ApiOperation("菜单导航列表-v1.1.0")
	    @GetMapping("/menuList")
	    @Func(code="view", name="查")
	    public ResponseData<List<SiteNavigationVO.PCListVO>> menuList(SiteNavigationQTO.PCQTO qto) {
	        qto.setTerminal(TerminalEnum.BBB.getCode());
	        qto.setType(SiteNavigationEnum.菜单导航.getCode());
	        return ResponseData.data(siteNavigationRpc.pcList(qto));
	    }

	    @ApiOperation("菜单导航编辑-v1.1.0")
	    @PutMapping("/menuEditor")
	    @Func(code="edit", name="改")
	    public ResponseData<Void> menuEditor2(@RequestBody SiteNavigationDTO.PC2DTO eto) {
	        eto.setTerminal(TerminalEnum.BBB.getCode());
	        eto.setType(SiteNavigationEnum.菜单导航.getCode());
	        siteNavigationRpc.pcEditor2(eto);
	        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
	    }

	    @ApiOperation("轮播图列表(支持专栏)-v1.1.0")
	    @GetMapping("/pcBannerList")
	    @Func(code="view", name="查")
	    public ResponseData<List<SiteBannerVO.PCListVO>> pcBannerList(SiteBannerQTO.PCQTO qto) {
	        qto.setTerminal(TerminalEnum.BBB.getCode());
	        return ResponseData.data(siteBannerRpc.pcList(qto));
	    }

	    @ApiOperation("轮播图编辑(支持专栏)-v1.1.0")
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

	    @ApiOperation("组合广告图列表(支持专栏)-v1.1.0")
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

	    @ApiOperation("底部文章编辑-v1.1.0")
	    @PutMapping("/pcBottomArticleEditor")
	    @Func(code="edit", name="改")
	    public ResponseData<Void> pcBottomArticleEditor( @Valid @RequestBody SiteBottomArticleDTO.PCUDTO eto) {
	        eto.setTerminal(TerminalEnum.BBB.getCode());
	        eto.setPcShow(PcH5Enum.YES.getCode());
	        eto.setSubject(SubjectEnum.默认.getCode());
	        siteBottomArticleRpc.bottomArticleEditor(eto);
	        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
	    }

	    @ApiOperation("专题列表-v1.1.0")
	    @GetMapping("/topicList")
	    @Func(code="view", name="查")
	    public ResponseData<PageData<SiteTopicVO.PCListVO>> listTopic(SiteTopicQTO.QTO qto) {
	        qto.setTerminal(TerminalEnum.BBB.getCode());//2c
	        qto.setSubject(SubjectEnum.积分商城.getCode());//2c
	        return ResponseData.data(siteTopicRpc.pageData(qto));
	    }

		@ApiOperation("专题列表编辑-v1.1.0")
	    @PutMapping(value = "/topicEditor")
	    @Func(code="edit", name="改")
	    public ResponseData<Void> editorTopic(@Valid @RequestBody SiteTopicDTO.ETO eto) {
	    	eto.setTerminal(TerminalEnum.BBB.getCode());
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













