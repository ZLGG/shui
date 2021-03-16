package com.gs.lshly.facade.platform.controller.foundation;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataPhoneDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataPhoneQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataPhoneVO;
import com.gs.lshly.rpc.api.platadmin.foundation.IDataPhoneRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
* <p>
*  前端控制器
* </p>
*
* @author xxfc
* @since 2021-01-28
*/
@RestController
@RequestMapping("/platadmin/dataPhone")
@Api(tags = "商家入驻审核运营人员管理")
public class DataPhoneController {

    @DubboReference
    private IDataPhoneRpc DataPhoneRpc;



    @ApiOperation("保存运营人员手机号")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody DataPhoneDTO.ETO dto) {
        DataPhoneRpc.saveDataPhone(dto);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }


    @ApiOperation("查询运营人员手机号")
    @GetMapping("")
    public ResponseData<DataPhoneVO.DetailVO> getDetailVo(BaseDTO dto) {
        return ResponseData.data(DataPhoneRpc.detailDataPhone(dto));
    }


}
