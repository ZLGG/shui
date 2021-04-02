package com.gs.lshly.facade.merchant.controller.pc.merchant;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopBannerDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopFloorDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopNavigationMenuDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopBannerQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopFloorQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopNavigationMenuQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopBannerVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopFloorVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopNavigationMenuVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchShopBannerRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchShopFloorRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchShopNavigationMenuRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/merchant/bbc/h5")
@Api(tags = "店铺装饰B2C-H5")
public class PCMerchDecorateB2cH5Controller {

    @DubboReference
    private IPCMerchShopBannerRpc pcMerchShopBannerRpc;

    @DubboReference
    private IPCMerchShopNavigationMenuRpc pcMerchShopNavigationMenuRpc;

    @DubboReference
    private IPCMerchShopFloorRpc pCMerchShopFloorRpc;


    @ApiOperation("BbcH5文本导航列表")
    @GetMapping("/textMenuList")
    public ResponseData<List<PCMerchShopNavigationMenuVO.H5TextMenuListVO>> textMenuList(PCMerchShopNavigationMenuQTO.H5TextMenuQTO qto) {
        qto.setTerminal(TerminalEnum.BBC.getCode());
        return ResponseData.data(pcMerchShopNavigationMenuRpc.h5TextMenuList(qto));
    }



    @ApiOperation("BbcH5文本导航编辑")
    @PutMapping(value = "/textMenuEditor")
    public ResponseData<Void> textMenuEditor(@Valid @RequestBody PCMerchShopNavigationMenuDTO.H5TextMenuETO eto) {
        eto.setTerminal(TerminalEnum.BBC.getCode());
        pcMerchShopNavigationMenuRpc.h5TextMenuEditor(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }



    @ApiOperation("店铺轮播图列表(BBC-H5)")
    @GetMapping("/bannerList")
    public ResponseData<List<PCMerchShopBannerVO.H5ListVO>> bannerList(PCMerchShopBannerQTO.H5QTO qto) {
        qto.setTerminal(TerminalEnum.BBC.getCode());
        return ResponseData.data(pcMerchShopBannerRpc.h5List(qto));
    }

    @ApiOperation("店铺轮播图编辑(BBC-H5)")
    @PutMapping(value = "/bannerEditor")
    public ResponseData<Void> bannerEditor(@Valid @RequestBody PCMerchShopBannerDTO.H5ETO eto) {
        eto.setTerminal(TerminalEnum.BBC.getCode());
        pcMerchShopBannerRpc.h5Editor(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }


    @ApiOperation("BbcH5菜单导航列表")
    @GetMapping("/menuList")
    public ResponseData<List<PCMerchShopNavigationMenuVO.H5ListVO>> menuList(PCMerchShopNavigationMenuQTO.H5QTO qto) {
        qto.setTerminal(TerminalEnum.BBC.getCode());
        return ResponseData.data(pcMerchShopNavigationMenuRpc.h5List(qto));
    }

    @ApiOperation("BbcH5菜单导航编辑")
    @PutMapping(value = "/menuEditor")
    public ResponseData<Void> menuEditor(@Valid @RequestBody PCMerchShopNavigationMenuDTO.H5ETO eto) {
        eto.setTerminal(TerminalEnum.BBC.getCode());
        pcMerchShopNavigationMenuRpc.h5Editor(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }


    @ApiOperation("BbcH5楼层列表")
    @GetMapping("/floorList")
    public ResponseData<List<PCMerchShopFloorVO.H5ListVO>> floorList(PCMerchShopFloorQTO.H5QTO qto) {
        qto.setTerminal(TerminalEnum.BBC.getCode());
        return ResponseData.data(pCMerchShopFloorRpc.h5List(qto));
    }


    @ApiOperation("BbcH5楼层编辑")
    @PutMapping(value = "/floorEditor")
    public ResponseData<Void> floorEditor(@Valid @RequestBody PCMerchShopFloorDTO.H5ETO eto) {
        eto.setTerminal(TerminalEnum.BBC.getCode());
        pCMerchShopFloorRpc.h5Editor(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

}














