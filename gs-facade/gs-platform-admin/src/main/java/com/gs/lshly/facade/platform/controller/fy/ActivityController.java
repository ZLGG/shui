package com.gs.lshly.facade.platform.controller.fy;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.fy.activity.dto.ActivityDTO;
import com.gs.lshly.common.struct.fy.activity.qto.ActivityQTO;
import com.gs.lshly.common.struct.fy.activity.qto.ActivityUserQTO;
import com.gs.lshly.common.struct.fy.activity.vo.ActivityUserVO;
import com.gs.lshly.common.struct.fy.activity.vo.ActivityVO;
import com.gs.lshly.rpc.api.fy.IFyActivityRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 活动表 前端控制器
 * </p>
 *
 * @author zhaoqiang
 * @since 2020-12-24
 */
@Api(tags = "平台运营活动管理")
@RestController
@RequestMapping("/fy/platform/activity")
public class ActivityController {

    @DubboReference
    private IFyActivityRpc activityRpc;

    /*@DubboReference
    private IShopRpc shopRpc;*/

    @ApiOperation("平台活动列表")
    @GetMapping("/list")
    public ResponseData<PageData<ActivityVO.ListVO>> list(ActivityQTO.QTO qto) {
        return ResponseData.data(activityRpc.pageData(qto));
    }

    @ApiOperation("新增活动")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody ActivityDTO.ETO eto) {
        activityRpc.addActivity(eto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("活动查看")
    @GetMapping(value = "/{id}")
    public ResponseData<ActivityVO.DetailVO> detail(@PathVariable String id) {
        return ResponseData.data(activityRpc.get(id));
    }

    @ApiOperation("活动编辑")
    @PutMapping("/edit")
    public ResponseData<Void> edit(@Valid @RequestBody ActivityDTO.PCUDTO dto) {
        activityRpc.editActivity(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("批量删除活动")
    @PostMapping("/deleteBatch")
    public ResponseData<Void> deleteBatch(@RequestBody ActivityDTO.ActivityIdListDTO dto) {
        activityRpc.deleteBatch(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("活动绑定活动列表")
    @GetMapping("/activityList")
    public ResponseData<PageData<ActivityUserVO.ListVO>> activityList(ActivityUserQTO.QTO qto) {
        return ResponseData.data(activityRpc.activityUserPageData(qto));
    }

    //添加活动和会员绑定接口、取消绑定接口


}
