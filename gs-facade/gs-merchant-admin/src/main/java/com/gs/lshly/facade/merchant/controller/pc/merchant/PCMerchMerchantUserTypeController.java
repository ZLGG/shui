package com.gs.lshly.facade.merchant.controller.pc.merchant;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMerchantUserTypeDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMerchantUserTypeQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantUserTypeVO;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchMerchantUserTypeRpc;
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
* @since 2020-12-25
*/
@RestController
@RequestMapping("/merchadmin/merchantUserType")
@Api(tags = "商家的会员等级字典管理")
public class PCMerchMerchantUserTypeController {

    @DubboReference
    private IPCMerchMerchantUserTypeRpc pcMerchMerchantUserTypeRpc;

    @ApiOperation("商家的会员类型字典列表(分页)")
    @GetMapping("")
    public ResponseData<PageData<PCMerchMerchantUserTypeVO.ListVO>> pageData(PCMerchMerchantUserTypeQTO.QTO qto) {
        return ResponseData.data(pcMerchMerchantUserTypeRpc.pageData(qto));
    }


    @ApiOperation("商家的会员类型字典列表(不分页)")
    @GetMapping("listData")
    public ResponseData<List<PCMerchMerchantUserTypeVO.ListVO>> listData(BaseDTO dto) {
        return ResponseData.data(pcMerchMerchantUserTypeRpc.listData(dto));
    }



    @ApiOperation("商家的会员类型字典详情")
    @GetMapping(value = "/{id}")
    public ResponseData<PCMerchMerchantUserTypeVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(pcMerchMerchantUserTypeRpc.detailMerchantUserType(new PCMerchMerchantUserTypeDTO.IdDTO(id)));
    }

    @ApiOperation("新增商家的会员类型字典")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody PCMerchMerchantUserTypeDTO.ETO dto) {
        pcMerchMerchantUserTypeRpc.saveMerchantUserType(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }


    @ApiOperation("删除商家的会员类型字典(批量)")
    @PostMapping(value = "/deleteBatch")
    public ResponseData<Void> deleteBatch(@Valid @RequestBody PCMerchMerchantUserTypeDTO.IdListDTO dto) {
        pcMerchMerchantUserTypeRpc.deleteBatch(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("修改商家的会员类型字典")
    @PutMapping(value = "/{id}")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody PCMerchMerchantUserTypeDTO.ETO eto) {
        eto.setId(id);
        pcMerchMerchantUserTypeRpc.saveMerchantUserType(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

}
