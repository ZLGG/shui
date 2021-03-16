package com.gs.lshly.facade.merchant.controller.pc.commodity;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsStockDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsStockQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsStockVO;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchGoodsStockRpc;
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
* @author Starry
* @since 2020-10-27
*/
@RestController
@RequestMapping("/merchadmin/skuStockAlarm")
@Api(tags = "商家商品库存报警管理")
public class PCMerchSkuStockAlarmController {

    @DubboReference
    private IPCMerchGoodsStockRpc pcMerchSkuStockAlarmRpc;

    @ApiOperation("展示商家店铺商品库存报警数")
    @GetMapping(value = "showInfo")
    public ResponseData<PCMerchGoodsStockVO.SkuAlarmDetailVO> showInfo(PCMerchGoodsStockQTO.SkuAlarmQTO qto) {
        return ResponseData.data(pcMerchSkuStockAlarmRpc.detailSkuStockAlarm(qto));
    }

    @ApiOperation("设置商家店铺库存报警")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody PCMerchGoodsStockDTO.SkuAlarmETO dto) {
            pcMerchSkuStockAlarmRpc.addSkuStockAlarm(dto);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }


}
