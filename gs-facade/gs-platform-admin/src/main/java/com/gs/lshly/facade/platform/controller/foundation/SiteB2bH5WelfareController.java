package com.gs.lshly.facade.platform.controller.foundation;

import cn.hutool.core.util.ObjectUtil;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteAdvertDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteBannerDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteFloorDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteAdvertQTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteBannerQTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteFloorQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteAdvertVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteBannerVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteAdvertRpc;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteBannerRpc;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteFloorRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/platform/bbb/h5SiteWelfare")
@Api(tags = "B2BH5扶贫专栏",description = " ")
@Module(code = "covertyColumnB2BH5", parent = "site", name = "B2BH5扶贫专栏", index = 2)
public class SiteB2bH5WelfareController {

    @DubboReference
    private ISiteBannerRpc siteBannerRpc;

    @DubboReference
    private ISiteFloorRpc siteFloorRpc;


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


}













