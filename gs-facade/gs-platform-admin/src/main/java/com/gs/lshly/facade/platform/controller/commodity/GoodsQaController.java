package com.gs.lshly.facade.platform.controller.commodity;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsQaDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsQaQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsQaVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsQaRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Starry
 * @Date 16:36 2020/10/17
 */
@RestController
@RequestMapping("/platform/goodsQa")
@Api(tags = "平台商品咨询")
//@Module(code = "commodityAsk", parent = "commodity", name = "商品问答", index =2)
public class GoodsQaController {
    @DubboReference
    private IGoodsQaRpc goodsQaRpc;

    @ApiOperation("商品咨询列表")
    @GetMapping("")
//    @Func(code="view", name = "查看")
    public ResponseData<PageData<GoodsQaVO.GoodsQaListVO>> list(GoodsQaQTO.QTO qto) {
        return ResponseData.data(goodsQaRpc.pageGoodsQa(qto));
    }

    @ApiOperation("商品咨询详情")
    @GetMapping(value = "/{id}")
//    @Func(code="view", name = "查看")
    public ResponseData<GoodsQaVO.GoodsQaDetailVO> detail(@PathVariable String id) {
        GoodsQaDTO.IdDTO dto = new GoodsQaDTO.IdDTO(id);
        return ResponseData.data(goodsQaRpc.getGoodsQaDetailVO(dto));
    }

    @ApiOperation("删除商品咨询内容")
    @DeleteMapping(value = "{id}")
//    @Func(code="delete", name = "删除")
    public ResponseData<Void> delete(@PathVariable String id) {
        GoodsQaDTO.IdDTO dto = new GoodsQaDTO.IdDTO(id);
        goodsQaRpc.deleteGoodsQa(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("删除商品咨询回复")
    @PutMapping(value = "deleteReply/{id}")
//    @Func(code="edit", name = "编辑")
    public ResponseData<Void> deleteReply(@PathVariable String id) {
        GoodsQaDTO.IdDTO dto = new GoodsQaDTO.IdDTO(id);
        goodsQaRpc.deleteGoodsQaReply(dto);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }

    @ApiOperation("是否显示将商品咨询显示在商品详情")
    @PutMapping(value = "showOrHidden/{id}")
//    @Func(code="edit", name = "编辑")
    public ResponseData<Void> showOrHidden(@PathVariable String id,@RequestBody GoodsQaDTO.ShowContentETO eto) {
        eto.setId(id);
        goodsQaRpc.IsShowGoodsQaContent(eto);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }
}
