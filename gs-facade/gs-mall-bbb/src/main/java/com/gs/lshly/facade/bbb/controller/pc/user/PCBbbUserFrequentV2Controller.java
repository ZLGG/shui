package com.gs.lshly.facade.bbb.controller.pc.user;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsInfoVO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserFrequentGoodsDTO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserFrequentGoodsV2DTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserFrequentGoodsQTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserFrequentGoodsV2QTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserFrequentGoodsVO;
import com.gs.lshly.rpc.api.bbb.pc.user.IPCBbbUserFrequentRpc;
import com.gs.lshly.rpc.api.bbb.pc.user.IPCBbbUserFrequentV2Rpc;
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
* @since 2020-12-10
*/
@RestController
@RequestMapping("/bbb/userCenter/userFrequent")
@Api(tags = "常购商品管理2.0")
public class PCBbbUserFrequentV2Controller {

    @DubboReference
    private IPCBbbUserFrequentV2Rpc pCBbbUserFrequentV2Rpc;

    @ApiOperation("常购商品列表")
    @GetMapping("")
    public ResponseData< PageData<PCBbbGoodsInfoVO.InnerServiceVO>> pageData(PCBbbUserFrequentGoodsV2QTO.QTO qto) {
        return ResponseData.data(pCBbbUserFrequentV2Rpc.pageData(qto));
    }


    @ApiOperation("常购商品添加")
    @PostMapping(value = "")
    public ResponseData<Void> add(PCBbbUserFrequentGoodsV2DTO.OneETO dto) {
        pCBbbUserFrequentV2Rpc.addOne(dto);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }


    @ApiOperation("常购商品删除")
    @DeleteMapping(value = "/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        pCBbbUserFrequentV2Rpc.deleteUserFrequentGoods(new PCBbbUserFrequentGoodsV2DTO.IdDTO(id));
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("常购商品删除(批量)")
    @PostMapping(value = "/deleteBatch")
    public ResponseData<Void> deleteBatch(@Valid @RequestBody PCBbbUserFrequentGoodsV2DTO.IdListDTO dto) {
        pCBbbUserFrequentV2Rpc.deleteBatch(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


}
