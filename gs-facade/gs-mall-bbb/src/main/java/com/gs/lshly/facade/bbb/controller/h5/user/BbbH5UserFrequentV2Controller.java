package com.gs.lshly.facade.bbb.controller.h5.user;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserFrequentGoodsV2DTO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserFrequentGoodsV2QTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsInfoVO;
import com.gs.lshly.rpc.api.bbb.h5.user.IBbbH5UserFrequentV2Rpc;
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
@RequestMapping("/bbb/h5/userCenter/userFrequent")
@Api(tags = "常购商品管理2.0")
public class BbbH5UserFrequentV2Controller {

    @DubboReference
    private IBbbH5UserFrequentV2Rpc bbbH5UserFrequentV2Rpc;

    @ApiOperation("常购商品列表")
    @GetMapping("")
    public ResponseData<PageData<BbbH5GoodsInfoVO.InnerServiceVO>> pageData(BbbH5UserFrequentGoodsV2QTO.QTO qto) {
        return ResponseData.data(bbbH5UserFrequentV2Rpc.pageData(qto));
    }

    @ApiOperation("常购商品添加")
    @PostMapping(value = "")
    public ResponseData<Void> add(BbbH5UserFrequentGoodsV2DTO.OneETO dto) {
        bbbH5UserFrequentV2Rpc.addOne(dto);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }

    @ApiOperation("常购商品批量添加")
    @PostMapping(value = "list")
    public ResponseData<Void> add(BbbH5UserFrequentGoodsV2DTO.ETO dto) {
        bbbH5UserFrequentV2Rpc.addMore(dto);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }

    @ApiOperation("常购商品删除")
    @DeleteMapping(value = "/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        bbbH5UserFrequentV2Rpc.deleteUserFrequentGoods(new BbbH5UserFrequentGoodsV2DTO.IdDTO(id));
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("常购商品删除(批量)")
    @PostMapping(value = "/deleteBatch")
    public ResponseData<Void> deleteBatch(@Valid @RequestBody BbbH5UserFrequentGoodsV2DTO.IdListDTO dto) {
        bbbH5UserFrequentV2Rpc.deleteBatch(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


}
