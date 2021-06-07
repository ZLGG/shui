package com.gs.lshly.facade.bbc.controller.h5.trade;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeRightsBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeRightsDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeRightsLogQTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeRightsQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeRightsLogVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeRightsVO;
import com.gs.lshly.rpc.api.bbc.trade.IBbcTradeRightsLogRpc;
import com.gs.lshly.rpc.api.bbc.trade.IBbcTradeRightsRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author oy
 * @since 2020-12-06
 */
@RestController
@RequestMapping("/bbc/userCenter/tradeRights")
@Api(tags = "交易售后表管理")
public class BbcTradeRightsController {

    @DubboReference
    private IBbcTradeRightsRpc bbcTradeRightsRpc;
    @DubboReference
    private IBbcTradeRightsLogRpc bbcTradeRightsLogRpc;

    @ApiOperation("交易售后表列表")
    @GetMapping("")
    public ResponseData<PageData<BbcTradeRightsVO.ListVO>> list(BbcTradeRightsQTO.RightsList qto) {
        return ResponseData.data(bbcTradeRightsRpc.tradeRightsListData(qto));
    }

    @ApiOperation("交易售后表详情")
    @GetMapping(value = "/{id}")
    public ResponseData<BbcTradeRightsVO.DetailVO> get(BbcTradeRightsDTO.IdDTO id) {
        return ResponseData.data(bbcTradeRightsRpc.detailTradeRights(id));
    }

    @ApiOperation("申请售后")
    @PostMapping("/apply")
    public ResponseData<List<String>> add(@Valid @RequestBody BbcTradeRightsBuildDTO.ETO dto) {
        return ResponseData.data(bbcTradeRightsRpc.addTradeRights(dto));
    }

    @ApiOperation("修改售后")
    @PostMapping("/update")
    public ResponseData<BbcTradeRightsVO.DetailVO> update(@Valid @RequestBody BbcTradeRightsBuildDTO.UpdateETO dto) {
        bbcTradeRightsRpc.updateTradeRights(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("撤销售后申请")
    @GetMapping("/revocation/{id}")
    public ResponseData<Void> revocation(BbcTradeRightsBuildDTO.RevocationTradeRightsETO dto) {
        bbcTradeRightsRpc.revocationTradeRights(dto);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }

    @ApiOperation("计算待退货商品实付金额与积分")
    @PostMapping("/total")
    public ResponseData<Void> goodsTotal(@Valid @RequestBody BbcTradeRightsBuildDTO.GoodsTotalDTO dto) {
        return ResponseData.data(bbcTradeRightsRpc.goodsTotal(dto));
    }

/*    @ApiOperation("货物寄回")
    @PostMapping("/returnGoods")
    public ResponseData<Void> returnGoods(@Valid @RequestBody BbcTradeRightsBuildDTO.RightsReturnGoodsLogistics dto) {
        bbcTradeRightsRpc.returnGoods(dto);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }*/

    @ApiOperation("完成换货")
    @GetMapping("/addAddress/{id}")
    public ResponseData<Void> confirmReceipt(BbcTradeRightsDTO.IdDTO dto) {
        bbcTradeRightsRpc.confirmReceipt(dto);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }


    @ApiOperation("删除记录")
    @PutMapping("/deleteRecord")
    public ResponseData<Void> deleteRecord(BbcTradeRightsDTO.IdDTO dto) {
        bbcTradeRightsRpc.deleteRecord(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("协商进度")
    @GetMapping("/log/{id}")
    public ResponseData<List<BbcTradeRightsLogVO.listVO>> rightsLog(BbcTradeRightsLogQTO.QTO qto) {
        return ResponseData.data(bbcTradeRightsLogRpc.rightsLog(qto));
    }

    @ApiOperation("二次申诉")
    @GetMapping("/twoCheck/{id}")
    public ResponseData<Void> twoCheck(BbcTradeRightsDTO.IdDTO dto) {
        bbcTradeRightsRpc.twoCheck(dto);
        return ResponseData.success(MsgConst.APPEAL_COMMENT);
    }
}
