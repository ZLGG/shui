package com.gs.lshly.facade.bbb.controller.pc.trade;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.PCBbbMarketMerchantCardUsersDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbMarketMerchantCardUsersQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketMerchantCardUsersVO;
import com.gs.lshly.rpc.api.bbb.pc.trade.IPCBbbMarketMerchantCardUsersRpc;
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
* @since 2021-01-08
*/
@RestController
@RequestMapping("/bbb/userCenter/marketMerchantCardUsers")
@Api(tags = "我的优惠卷")
public class PCBbbMarketMerchantCardUsersController {

    @DubboReference
    private IPCBbbMarketMerchantCardUsersRpc pcBbbMarketMerchantCardUsersRpc;

    @ApiOperation("我的优惠卷列表")
    @GetMapping("")
    public ResponseData<PageData<PCBbbMarketMerchantCardUsersVO.PageData>> list(PCBbbMarketMerchantCardUsersQTO.QTO qto) {
        return ResponseData.data(pcBbbMarketMerchantCardUsersRpc.pageData(qto));
    }

    @ApiOperation("我的优惠卷详情")
    @GetMapping(value = "/{id}")
    public ResponseData<PCBbbMarketMerchantCardUsersVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(pcBbbMarketMerchantCardUsersRpc.detailMarketMerchantCardUsers(new PCBbbMarketMerchantCardUsersDTO.IdDTO(id)));
    }

    @ApiOperation("新增商家优惠卷会员令取记录")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody PCBbbMarketMerchantCardUsersDTO.ETO dto) {
            pcBbbMarketMerchantCardUsersRpc.addMarketMerchantCardUsers(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除商家优惠卷会员令取记录")
    @DeleteMapping(value = "/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        PCBbbMarketMerchantCardUsersDTO.IdDTO dto = new PCBbbMarketMerchantCardUsersDTO.IdDTO(id);
        pcBbbMarketMerchantCardUsersRpc.deleteMarketMerchantCardUsers(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("修改商家优惠卷会员令取记录")
    @PutMapping(value = "/{id}")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody PCBbbMarketMerchantCardUsersDTO.ETO eto) {
        eto.setId(id);
        pcBbbMarketMerchantCardUsersRpc.editMarketMerchantCardUsers(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

}
