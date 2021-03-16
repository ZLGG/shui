package com.gs.lshly.biz.support.user.rpc.fy;

import com.gs.lshly.biz.support.user.service.fy.IFyActivityService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.fy.activity.dto.ActivityDTO;
import com.gs.lshly.common.struct.fy.activity.qto.ActivityQTO;
import com.gs.lshly.common.struct.fy.activity.qto.ActivityUserQTO;
import com.gs.lshly.common.struct.fy.activity.vo.ActivityUserVO;
import com.gs.lshly.common.struct.fy.activity.vo.ActivityVO;
import com.gs.lshly.rpc.api.fy.IFyActivityRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/24 下午8:20
 */
@DubboService
public class FyActivityRpc implements IFyActivityRpc {

    @Autowired
    IFyActivityService activityService;

    @Override
    public PageData<ActivityVO.ListVO> pageData(ActivityQTO.QTO qto) {
        return activityService.pageData(qto);
    }

    @Override
    public void addActivity(ActivityDTO.ETO eto) {
        activityService.addActivity(eto);
    }

    @Override
    public ActivityVO.DetailVO get(String id) {
        return activityService.get(id);
    }

    @Override
    public void editActivity(ActivityDTO.PCUDTO dto) {
        activityService.editActivity(dto);
    }

    @Override
    public void deleteBatch(ActivityDTO.ActivityIdListDTO dto) {
        activityService.deleteBatch(dto);
    }

    @Override
    public PageData<ActivityUserVO.ListVO> activityUserPageData(ActivityUserQTO.QTO qto) {
        return activityService.activityUserPageData(qto);
    }
}
