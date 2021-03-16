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
import com.gs.lshly.common.enums.SitePCShowEnum;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteBannerDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteFloorDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteTopicDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteBannerQTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteFloorQTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteTopicQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteBannerVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteTopicVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteBannerRpc;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteFloorRpc;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteTopicRpc;

import cn.hutool.core.util.ObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/platform/bbc/h5SitePoint")
@Api(tags = "B2CH5积分专栏-v1.1.0",description =" ")
@Module(code = "covertyColumnB2CH5", parent = "site", name = "B2CH5积分专栏", index = 3)
public class SiteB2cH5PointController {

    @DubboReference
    private ISiteBannerRpc siteBannerRpc;

    @DubboReference
    private ISiteFloorRpc siteFloorRpc;
    
    @DubboReference
    private ISiteTopicRpc siteTopicRpc;



    @ApiOperation("轮播图列表-v1.1.0")
    @GetMapping("/bannerList")
    @Func(code="view", name="查")
    public ResponseData<List<SiteBannerVO.H5ListVO>> listBanner(SiteBannerQTO.H5QTO qto) {
        qto.setSubject(SubjectEnum.积分商城.getCode());
        qto.setTerminal(TerminalEnum.BBC.getCode());
        return ResponseData.data(siteBannerRpc.h5List(qto));
    }

    @ApiOperation("轮播图编辑-v1.1.0")
    @PutMapping("/bannerEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> updateBanner(@RequestBody SiteBannerDTO.H5DTO dto) {
        if(ObjectUtil.isNotEmpty(dto.getBannerList())){
            dto.getBannerList().forEach(item -> {
                item.setSubject(SubjectEnum.积分商城.getCode());
                item.setTerminal(TerminalEnum.BBC.getCode());
            });
        }
        siteBannerRpc.h5Editor(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("楼层列表-v1.1.0")
    @GetMapping("/floorList")
    @Func(code="view", name="查")
    public ResponseData<List<SiteFloorVO.H5ListVO>> floorList(SiteFloorQTO.H5QTO qto) {
        qto.setSubject(SubjectEnum.积分商城.getCode());
        qto.setTerminal(TerminalEnum.BBC.getCode());
        return ResponseData.data(siteFloorRpc.h5List(qto));
    }

    @ApiOperation("楼层编辑-v1.1.0")
    @PutMapping("/floorEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> floorEditor(@RequestBody  SiteFloorDTO.H5ETO h5ETO) {
        if(ObjectUtil.isNotEmpty(h5ETO.getFloorList())){
            h5ETO.getFloorList().forEach(item -> {
                item.setSubject(SubjectEnum.积分商城.getCode());
                item.setTerminal(TerminalEnum.BBC.getCode());
            });
        }
        siteFloorRpc.h5Editor(h5ETO);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }
    
    
    
    
    
    
    @ApiOperation("专题列表-v1.1.0")
    @GetMapping("/topicList")
    @Func(code="view", name="查")
    public ResponseData<PageData<SiteTopicVO.PCListVO>> listTopic(SiteTopicQTO.QTO qto) {
        qto.setTerminal(TerminalEnum.BBC.getCode());//2c
        qto.setSubject(SubjectEnum.积分商城.getCode());//2c
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














