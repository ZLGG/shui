package com.gs.lshly.facade.merchant.controller.pc.merchant;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.AdvertTypeEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.*;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.*;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.*;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/merchant/bbb/pc")
@Api(tags = "店铺装饰(B2B-PC)")
@Module(code = "configB2BPC", parent = "shop", name = "B2B-PC站点配置", index = 4)
public class PCMerchDecorateB2bPcController {

    @DubboReference
    private IPCMerchShopBannerRpc pcMerchShopBannerRpc;

    @DubboReference
    private IPCMerchShopNavigationRpc pCMerchShopNavigationRpc;

    @DubboReference
    private IPCMerchShopNavigationMenuRpc pcMerchShopNavigationMenuRpc;

    @DubboReference
    private IPCMerchShopFloorRpc pCMerchShopFloorRpc;

    @DubboReference
    private IPCMerchShopCustomAreaRpc pCMerchShopCustomAreaRpc;

    @DubboReference
    private IPCMerchShopSignboardRpc pCMerchShopSignboardRpc;

    @DubboReference
    private IPCMerchShopAdvertRpc ipcMerchShopAdvertRpc;


    @ApiOperation("店招配置详情")
    @GetMapping("/signboard")
    @Func(code="view", name="查")
    public ResponseData<PCMerchShopSignboardVO.DetailVO> signboard(PCMerchShopSignboardQTO.QTO qto) {
        return ResponseData.data(pCMerchShopSignboardRpc.detailSignboard(qto));
    }

    @ApiOperation("店招配置编辑")
    @PutMapping(value = "/signboardEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> signboardEditor(@Valid @RequestBody PCMerchShopSignboardDTO.ETO eto) {
        pCMerchShopSignboardRpc.editShopSignboard(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

//    @ApiOperation("B2bPc轮播图列表")
//    @GetMapping("/bannerList")
//    public ResponseData<List<PCMerchShopBannerVO.PCListVO>> bannerList(PCMerchShopBannerQTO.PCQTO qto) {
//        qto.setTerminal(TerminalEnum.BBB.getCode());
//        return ResponseData.data(pcMerchShopBannerRpc.pcList(qto));
//    }
//
//    @ApiOperation("B2bPc轮播图编辑")
//    @PutMapping(value = "/bannerEditor")
//    public ResponseData<Void> bannerEditor(@Valid @RequestBody PCMerchShopBannerDTO.PCETO eto) {
//        eto.setTerminal(TerminalEnum.BBB.getCode());
//        pcMerchShopBannerRpc.pcEditor(eto);
//        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
//    }


    @ApiOperation("通栏广告图")
    @GetMapping("/shopAdvert")
    @Func(code="view", name="查")
    public ResponseData<PCMerchShopAdvertVO.DetailVO> detailShopAdvert(PCMerchShopAdvertQTO.QTO qto) {
        qto.setTerminal(TerminalEnum.BBB.getCode());
        qto.setAdvertType(AdvertTypeEnum.通栏广告.getCode());
        return ResponseData.data(ipcMerchShopAdvertRpc.detailShopAdvert(qto));
    }

    @ApiOperation("通栏广告图编辑")
    @PutMapping(value = "/shopAdvertEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> editShopAdvert(@Valid @RequestBody PCMerchShopAdvertDTO.ETO eto) {
        eto.setTerminal(TerminalEnum.BBB.getCode());
        eto.setAdvertType(AdvertTypeEnum.通栏广告.getCode());
        ipcMerchShopAdvertRpc.editShopAdvert(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("B2bPc店铺导航列表")
    @GetMapping("/shopMenuList")
    public ResponseData<PCMerchShopNavigationMenuVO.PcShopMenuVO> shopMenuList(PCMerchShopNavigationMenuQTO.PCShopMenuQTO qto) {
        qto.setTerminal(TerminalEnum.BBB.getCode());
        return ResponseData.data(pcMerchShopNavigationMenuRpc.pcShopMenuList(qto));
    }

    @ApiOperation("B2bPc店铺导航编辑")
    @PutMapping(value = "/shopMenuEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> shopMenuEditor(@Valid @RequestBody PCMerchShopNavigationMenuDTO.PCShopMenuETO eto) {
        eto.setTerminal(TerminalEnum.BBB.getCode());
        pcMerchShopNavigationMenuRpc.pcShopMenuEditor(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("B2bPc楼层列表")
    @GetMapping("/floorList")
    @Func(code="view", name="查")
    public ResponseData<List<PCMerchShopFloorVO.PCListVO>> floorList(PCMerchShopFloorQTO.PCQTO qto) {
        qto.setTerminal(TerminalEnum.BBB.getCode());
        return ResponseData.data(pCMerchShopFloorRpc.pcList(qto));
    }

    @ApiOperation("B2bPc楼层编辑")
    @PutMapping(value = "/floorEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> floorEditor(@Valid @RequestBody PCMerchShopFloorDTO.PCETO eto) {
        eto.setTerminal(TerminalEnum.BBB.getCode());
        pCMerchShopFloorRpc.pcEditor(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("B2bPc自定义区域详情")
    @GetMapping("/customArea")
    @Func(code="view", name="查")
    public ResponseData<PCMerchShopCustomAreaVO.DetailVO> customArea(PCMerchShopCustomAreaQTO.QTO qto) {
        return ResponseData.data(pCMerchShopCustomAreaRpc.detailShopCustomArea(qto));
    }

    @ApiOperation("B2bPc自定义区域编辑")
    @PutMapping(value = "/customAreaEditor")
    @Func(code="edit", name="改")
    public ResponseData<Void> customAreaEditor(@Valid @RequestBody PCMerchShopCustomAreaDTO.ETO eto) {
        pCMerchShopCustomAreaRpc.editShopCustomArea(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }
}














