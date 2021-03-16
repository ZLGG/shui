package com.gs.lshly.facade.merchant.controller.pc.merchant;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.common.CommonShopDTO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopNavigationDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopNavigationQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopNavigationMenuVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopNavigationVO;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchShopNavigationRpc;
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
* @since 2020-10-18
*/
@RestController
@RequestMapping("/merchadmin/shopNavigation")
@Api(tags = "店铺自定义分类管理",description = " ")
public class PCMerchShopNavigationController {

    @DubboReference
    private IPCMerchShopNavigationRpc pcMerchShopNavigationRpc;

    @DubboReference
    private IPCMerchShopNavigationRpc pCMerchShopNavigationRpc;

    @DubboReference
    private ICommonShopRpc iCommonShopRpc;

    @ApiOperation("2b店铺自定义分类列表[不分页，区分端]")
    @GetMapping("/terminal/list")
    public ResponseData<List<CommonShopVO.NavigationListVO>> shopNavigation(CommonShopDTO.NavigationDTO dto) {
        return ResponseData.data(iCommonShopRpc.shopNavigation(dto));
    }

    @ApiOperation("2b店铺自定义分类列表")
    @GetMapping("/2b/pageList")
    public ResponseData<List<PCMerchShopNavigationVO.ListVO>> list2b(PCMerchShopNavigationQTO.QTO qto) {
        qto.setTerminal(TerminalEnum.BBB.getCode());
        return ResponseData.data(pcMerchShopNavigationRpc.list(qto));
    }

    @ApiOperation("2b批量删除店铺自定义分类")
    @PostMapping(value = "/2b/deleteBatch")
    public ResponseData<Void> deleteBatch2b(@Valid @RequestBody PCMerchShopNavigationDTO.DeleteDTO eto) {
        eto.setTerminal(TerminalEnum.BBB.getCode());
        pcMerchShopNavigationRpc.deleteShopNavigation(eto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("2b删除自定义分类(单删)")
    @DeleteMapping(value = "/2b/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        pcMerchShopNavigationRpc.delete(new PCMerchShopNavigationDTO.IdDTO(id));
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("2b保存店铺自定义分类")
    @PutMapping(value = "/2b/save")
    public ResponseData<Void> update2b(@Valid @RequestBody PCMerchShopNavigationDTO.ItemListDTO eto) {
        eto.setTerminal(TerminalEnum.BBB.getCode());
        pcMerchShopNavigationRpc.editShopNavigation(eto);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }


    @ApiOperation("2c店铺自定义分类列表")
    @GetMapping("/2c/pageList")
    public ResponseData<List<PCMerchShopNavigationVO.ListVO>> list2c(PCMerchShopNavigationQTO.QTO qto) {
        qto.setTerminal(TerminalEnum.BBC.getCode());
        return ResponseData.data(pcMerchShopNavigationRpc.list(qto));
    }

    @ApiOperation("2c批量删除店铺自定义分类")
    @PostMapping(value = "/2c/deleteBatch")
    public ResponseData<Void> deleteBatch2c(@Valid @RequestBody PCMerchShopNavigationDTO.DeleteDTO eto) {
        eto.setTerminal(TerminalEnum.BBC.getCode());
        pcMerchShopNavigationRpc.deleteShopNavigation(eto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("2b删除自定义分类(单删)")
    @DeleteMapping(value = "/2c/{id}")
    public ResponseData<Void> delete2c(@PathVariable String id) {
        pcMerchShopNavigationRpc.delete(new PCMerchShopNavigationDTO.IdDTO(id));
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("2c保存店铺自定义分类")
    @PutMapping(value = "/2c/save")
    public ResponseData<Void> update2c(@Valid @RequestBody PCMerchShopNavigationDTO.ItemListDTO eto) {
        eto.setTerminal(TerminalEnum.BBC.getCode());
        pcMerchShopNavigationRpc.editShopNavigation(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("店铺分类设置为导航菜单(二级分类ID数组)")
    @PutMapping(value = "/toMenuEditor")
    public ResponseData<Void> toMenuEditor(@Valid @RequestBody PCMerchShopNavigationDTO.ToMenuListDTO dto) {
        dto.setTerminal(TerminalEnum.BBB.getCode());
        pCMerchShopNavigationRpc.editShopNavigationToMenu(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }
}
