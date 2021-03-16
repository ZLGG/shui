package com.gs.lshly.facade.merchant.controller.pc.user;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.merchadmin.pc.user.dto.PCMerchUserDTO;
import com.gs.lshly.common.struct.merchadmin.pc.user.qto.PCMerchUserQTO;
import com.gs.lshly.common.struct.merchadmin.pc.user.vo.PCMerchUserVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.user.IPCMerchUserRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
* <p>
*  前端控制器
* </p>
*
* @author xxfc
* @since 2020-10-20
*/
@RestController
@RequestMapping("/merchadmin/user")
@Api(tags = "商家私域会员管理管理",description = " ")
public class PCMerchPrivateUserController {

    @DubboReference
    private IPCMerchUserRpc pcMerchUserRpc;


    @ApiOperation("商家私域会员审核列表")
    @GetMapping("/applyList")
    public ResponseData<PageData<PCMerchUserVO.ApplyListVO>> applyPageList(PCMerchUserQTO.ApplyListQTO qto) {
        return ResponseData.data(pcMerchUserRpc.applyPageList(qto));
    }


    @ApiOperation("商家私域会员审核数据详情")
    @GetMapping(value = "/applyDetail/{id}")
    public ResponseData<PCMerchUserVO.ApplyPrivateUserDetailVO> applyDetailUser(@PathVariable String id) {
        PCMerchUserDTO.IdDTO dto = new PCMerchUserDTO.IdDTO(id);
        return ResponseData.data(pcMerchUserRpc.applyDetailUser(dto));
    }


    @ApiOperation("商家私域会员审核提交")
    @PutMapping(value = "/apply/{id}")
    public ResponseData<Void> apply(@PathVariable String id,@Valid @RequestBody PCMerchUserDTO.ApplyDTO dto) {
        pcMerchUserRpc.apply(dto.setId(id));
        return ResponseData.success(MsgConst.HANDER_SUCCESS);
    }

    @ApiOperation("商家私域会员列表")
    @GetMapping("")
    public ResponseData<PageData<PCMerchUserVO.ListVO>> pageList(PCMerchUserQTO.QTO qto) {
        return ResponseData.data(pcMerchUserRpc.pageData(qto));
    }

    @ApiOperation("商家私域会员详情")
    @GetMapping(value = "/detail/{id}")
    public ResponseData<PCMerchUserVO.PrivateUserDetailVO> detail(@PathVariable String id) {
        return ResponseData.data(pcMerchUserRpc.detailUser( new PCMerchUserDTO.IdDTO(id)));
    }

    @ApiOperation("商家私域会员停用")
    @PutMapping(value = "/stopBatchUser")
    public ResponseData<Void> stopBatchUser(@Valid @RequestBody PCMerchUserDTO.IdListDTO dto) {
        pcMerchUserRpc.stopBatchUser(dto);
        return ResponseData.success(MsgConst.DISABLE_SUCCESS);
    }

    @ApiOperation("商家私域会员启用")
    @PutMapping("/enableBatchUser")
    public ResponseData<Void> beginBatchUser(@Valid @RequestBody PCMerchUserDTO.IdListDTO dto) {
        pcMerchUserRpc.beginBatchUser(dto);
        return ResponseData.success(MsgConst.ENABLE_SUCCESS);
    }

    @ApiOperation("商家私域会员类型设置")
    @PutMapping("/setUserType")
    public ResponseData<Void> setUserType(@PathVariable String id,String userTypeId) {
        pcMerchUserRpc.setUserType(new PCMerchUserDTO.UserTypeDTO(id,userTypeId));
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("商家私域会员导出")
    @GetMapping(value = "/export")
    public void export(PCMerchUserDTO.IdListDTO dto,@ApiIgnore HttpServletResponse response) throws Exception {
        ExportDataDTO exportData = pcMerchUserRpc.exportUser(dto);
        ExcelUtil.export(exportData, response);
    }

}
