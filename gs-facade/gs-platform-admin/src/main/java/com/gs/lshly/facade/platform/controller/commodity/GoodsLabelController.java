package com.gs.lshly.facade.platform.controller.commodity;


import java.util.List;

import javax.validation.Valid;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsLabelDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsLabelQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsLabelVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsLabelRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
* <p>
*  前端控制器
* </p>
*
* @author Starry
* @since 2020-10-15
*/
@RestController
@RequestMapping("/platform/goodsLabel")
@Api(tags = "商品标签管理")
@Module(code = "labelManagement", parent = "commodityManagement", name = "商品标签", index =1)
@SuppressWarnings("unchecked")
public class GoodsLabelController {

    @DubboReference
    private IGoodsLabelRpc goodsLabelRpc;

	@ApiOperation("商品标签分页列表")
    @GetMapping("")
    @Func(code="view", name = "查看标签分页列表")
    public ResponseData<PageData<GoodsLabelVO.ListVO>> pageDataResponseData(GoodsLabelQTO.QTO qto) {
        return ResponseData.data(goodsLabelRpc.pageData(qto));
    }

    @ApiOperation("商品标签列表")
    @GetMapping("/listGoodsLabel")
    @Func(code="listGoodsLabel", name = "查看标签列表")
    public ResponseData<List<GoodsLabelVO.ListVO>> list() {
        return ResponseData.data(goodsLabelRpc.listGoodsLabel());
    }

    @ApiOperation("商品标签详情")
    @GetMapping(value = "/{id}")
    @Func(code="detail", name = "查看标签详情")
    public ResponseData<GoodsLabelVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(goodsLabelRpc.detailGoodsLabel(new GoodsLabelDTO.IdDTO(id)));
    }

    @ApiOperation("新增商品标签")
    @PostMapping("")
    @Func(code="add", name = "新增")
    public ResponseData<Void> add(@Valid @RequestBody GoodsLabelDTO.ETO dto) {
    	
    	if(dto.getLabelName().length()>10){
    		throw new BusinessException("标签名称请小于10个字符");
    	}
    	if(dto.getLabelRemark().length()>10){
    		throw new BusinessException("标签备注请小于10个字符");
    	}
        goodsLabelRpc.addGoodsLabel(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("批量删除商品标签")
    @PostMapping(value = "deleteBatches")
    @Func(code="delete", name = "删除")
    public ResponseData<Void> delete(@RequestBody GoodsLabelDTO.IdListDTO dto) {
        goodsLabelRpc.deleteGoodsLabel(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("修改商品标签")
    @PutMapping(value = "/{id}")
    @Func(code="edit", name = "修改")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody GoodsLabelDTO.ETO eto) {
    	
    	if(eto.getLabelName().length()>10){
    		throw new BusinessException("标签名称请小于10个字符");
    	}
    	if(eto.getLabelRemark().length()>10){
    		throw new BusinessException("标签备注请小于10个字符");
    	}
    	
        eto.setId(id);
        goodsLabelRpc.editGoodsLabel(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

}
