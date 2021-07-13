package com.gs.lshly.facade.platform.controller.commodity;

import javax.validation.Valid;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsServeDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsServeQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsServeVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsServeRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author hanly
 * @Date 17:28 2021/05/25
 */
@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/platform/GoodsServe")
@Api(tags = "商品服务管理-v1.1.0")
@Module(code = "commodityServeManagemet", parent = "commodity", name = "商品服务配置", index = 8)
public class GoodsServeController {

    @DubboReference
    private IGoodsServeRpc goodsServeRpc;

    /**
     * 分页查询商品服务列表
     *
     * @param qto
     * @return
     */
    @ApiOperation("商品服务列表")
    @GetMapping("")
    @Func(code = "query", name = "查询")
    public ResponseData<PageData<GoodsServeVO.ListVO>> pageGoodsServeData(GoodsServeQTO.QTO qto) {
        return ResponseData.data(goodsServeRpc.pageGoodsServeData(qto));
    }

    /**
     * 查询商品服务详情
     *
     * @param id
     * @return
     */
    @ApiOperation("商品服务详情")
    @GetMapping(value = "{id}")
    @Func(code = "detail", name = "查看详情")
    public ResponseData<GoodsServeVO.ListVO> getGoodsServeDetail(@PathVariable String id) {
        return ResponseData.data(goodsServeRpc.getGoodsServeDetail(new GoodsServeDTO.IdDTO(id)));
    }

    /**
     * 新增服务
     *
     * @param eto
     */
    @ApiOperation("新增服务")
    @PutMapping(value = "addGoodsServe")
    @Func(code = "add", name = "新增")
    public ResponseData<Void> addGoodsServe(@Valid @RequestBody GoodsServeDTO.ETO eto) {
        goodsServeRpc.addGoodsServe(eto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    /**
     * 修改服务信息
     *
     * @param eto
     */
    @ApiOperation("修改服务信息")
    @PutMapping(value = "editGoodsServe")
    @Func(code = "edit", name = "修改")
    public ResponseData<Void> editGoodsServe(@Valid @RequestBody GoodsServeDTO.EditDTO eto) {
        goodsServeRpc.editGoodsServe(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    /**
     * 删除服务
     *
     * @param dto
     */
	@ApiOperation("删除服务")
    @PutMapping(value = "deleteGoodsServe")
    @Func(code = "delete", name = "删除")
    public ResponseData<Void> deleteGoodsServe(@Valid @RequestBody GoodsServeDTO.IdListDTO dto) {
        goodsServeRpc.deleteGoodsServe(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }
}
