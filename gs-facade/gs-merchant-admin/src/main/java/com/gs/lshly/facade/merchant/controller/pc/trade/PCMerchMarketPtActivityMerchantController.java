package com.gs.lshly.facade.merchant.controller.pc.trade;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtActivityMerchantDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMarketPtActivityMerchantQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtActivityMerchantVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMarketPtActivityMerchantRpc;
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
* @author zdf
* @since 2020-12-01
*/
@RestController
@RequestMapping("/merchadmin/marketPtActivityMerchant")
@Api(tags = "活动商家参与记录管理")
public class PCMerchMarketPtActivityMerchantController {

    @DubboReference
    private IPCMerchMarketPtActivityMerchantRpc pcMerchMarketPtActivityMerchantRpc;

    @ApiOperation("活动商家参与记录列表")
    @GetMapping("")
    public ResponseData<PageData<PCMerchMarketPtActivityMerchantVO.ListVO>> list(PCMerchMarketPtActivityMerchantQTO.QTO qto) {
        return ResponseData.data(pcMerchMarketPtActivityMerchantRpc.pageData(qto));
    }

    @ApiOperation("活动商家参与记录详情")
    @GetMapping(value = "/{id}")
    public ResponseData<PCMerchMarketPtActivityMerchantVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(pcMerchMarketPtActivityMerchantRpc.detailMarketPtActivityMerchant(new PCMerchMarketPtActivityMerchantDTO.IdDTO(id)));
    }

    @ApiOperation("新增活动商家参与记录")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody PCMerchMarketPtActivityMerchantDTO.ETO dto) {
            pcMerchMarketPtActivityMerchantRpc.addMarketPtActivityMerchant(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除活动商家参与记录")
    @DeleteMapping(value = "/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        PCMerchMarketPtActivityMerchantDTO.IdDTO dto = new PCMerchMarketPtActivityMerchantDTO.IdDTO(id);
        pcMerchMarketPtActivityMerchantRpc.deleteMarketPtActivityMerchant(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("修改活动商家参与记录")
    @PutMapping(value = "/{id}")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody PCMerchMarketPtActivityMerchantDTO.ETO eto) {
        eto.setId(id);
        pcMerchMarketPtActivityMerchantRpc.editMarketPtActivityMerchant(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

}
