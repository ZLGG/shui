package com.gs.lshly.facade.platform.controller.trade;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.trade.dto.CtccPtActivityDTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.CtccPtActivityVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.trade.ICtccPtActivityRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @Author yangxi
 * @create 2021/5/7 14:48
 */
@RestController
@RequestMapping("/platadmin/ctccPtActivity")
@Api(tags = "平台电信国际管理-v1.1.0")
//@Module(code = "ctcc",parent = "marketing",name = "电信国际",index = 8)
public class CtccPtActivityController {
    @DubboReference
    private ICtccPtActivityRpc iCtccPtActivityRpc;

    @ApiOperation("创建活动")
    @PostMapping("/addActivity")
//    @Func(code = "add",name = "增")
    public ResponseData addActivity(@RequestBody CtccPtActivityDTO.AddDTO addDTO) {
        iCtccPtActivityRpc.addActivity(addDTO);
        return ResponseData.success();
    }

    @ApiOperation("修改活动")
    @PutMapping("/modifyActivity")
//    @Func(code = "edit",name = "改")
    public ResponseData modifyActivity(@PathVariable String id,@RequestBody CtccPtActivityDTO.ModifyDTO modifyDTO) {
        modifyDTO.setId(id);
        iCtccPtActivityRpc.modifyActivity(modifyDTO);
        return ResponseData.success();
    }

    @ApiOperation("商品类目列表展示")
    @GetMapping("/getCategoryList")
    public ResponseData<CtccPtActivityVO.CategoryListVO> getCategoryList(CtccPtActivityDTO.CateGoryListDTO listDTO) {
        CtccPtActivityVO.CategoryListVO categoryListVO = iCtccPtActivityRpc.getCategoryList(listDTO);
        return ResponseData.data(categoryListVO);
    }

    @ApiOperation("添加商品")
    @PostMapping("/addActivityGoods")
    public ResponseData addActivityGoods(@RequestBody List<CtccPtActivityDTO.AddActivityGoodsDTO> activityGoodsDTOList) {
        iCtccPtActivityRpc.addActivityGoods(activityGoodsDTOList);
        return ResponseData.success();
    }
}
