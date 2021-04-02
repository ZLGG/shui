package com.gs.lshly.facade.platform.controller.merchant;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.ShopQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.merchant.IShopRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* <p>
*  前端控制器
* </p>
*
* @author xxfc
* @since 2020-10-13
*/
@RestController
@RequestMapping("/platform/shop2b")
@Api(tags = "店铺管理(2b)",description = " ")
@Module(code = "settlementBussiness2B", parent = "bussiness", name = "2B商家入驻", index = 5)
public class Shop2bController {

    @DubboReference
    private IShopRpc shopRpc;

    @ApiOperation("店铺列表")
    @GetMapping("")
    @Func(code="view", name="查")
    public ResponseData<PageData<ShopVO.ListVO>> list(ShopQTO.QTO qto) {
        qto.setTerminal(TerminalEnum.BBB.getCode());
        return ResponseData.data(shopRpc.pageListShop(qto));
    }


    @ApiOperation("店铺详情")
    @GetMapping(value = "/shopDetails/{id}")
    @Func(code="view", name="查")
    public ResponseData<ShopVO.DetailVO> shopDetails(@PathVariable String id) {
        return ResponseData.data( shopRpc.shopDetails(new ShopDTO.IdDTO(id)));
    }

    @ApiOperation("店铺入驻申请资料编辑")
    @PutMapping(value = "/editorShop/{id}")
    @Func(code="edit", name="改")
    public ResponseData<Void> editorShop(@PathVariable String id,@Valid @RequestBody ShopDTO.ComplexETO dto) {
        dto.getShopETO().setId(id);
        shopRpc.editorShop(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }


    @ApiOperation("店铺关闭")
    @DeleteMapping(value = "/{id}")
    @Func(code="edit", name="改")
    public ResponseData<Void> delete(@PathVariable String id) {
        ShopDTO.IdDTO dto = new ShopDTO.IdDTO(id);
        dto.setId(id);
        shopRpc.closeShop(dto);
        return ResponseData.success(MsgConst.CLOSE_SUCCESS);
    }


    @ApiOperation("店铺开启")
    @PutMapping(value = "/{id}")
    @Func(code="edit", name="改")
    public ResponseData<Void> update(@PathVariable String id) {
        ShopDTO.IdDTO dto = new ShopDTO.IdDTO(id);
        shopRpc.openShop(dto);
        return ResponseData.success(MsgConst.OPEN_SUCCESS);
    }


    @ApiOperation("自营店添加")
    @PostMapping("")
    @Func(code="add", name="增")
    public ResponseData<Void> addSelftShop(@Valid @RequestBody ShopDTO.ETO dto) {
        dto.setTerminal(TerminalEnum.BBB.getCode());
        dto.validLongitudeLatitude();
        shopRpc.addSelftShop(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("自营店编辑")
    @PutMapping("editorSelftShop/{id}")
    @Func(code="edit", name="改")
    public ResponseData<Void> editorSelftShop(@PathVariable String id,@Valid @RequestBody ShopDTO.ETO dto) {
        dto.setId(id);
        dto.setTerminal(TerminalEnum.BBC.getCode());
        dto.validLongitudeLatitude();
        shopRpc.editorSelftShop(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }



    @ApiOperation("自营店列表")
    @GetMapping(value = "/selfShopList")
    @Func(code="view", name="查")
    public ResponseData<List<ShopVO.IdNameVO>> selfShopList(ShopQTO.SelfShopQTO qto) {
        qto.setTerminal(TerminalEnum.BBB.getCode());
        return ResponseData.data(shopRpc.selfShopList(qto));
    }

    @ApiOperation("自营店详情")
    @GetMapping(value = "/selfShopDetails/{id}")
    @Func(code="view", name="查")
    public ResponseData<ShopVO.SelfShopDetailVO> selfShopDetails(@PathVariable String id) {
        return ResponseData.data( shopRpc.selfShopDetails(new ShopDTO.IdDTO(id)));
    }

}
