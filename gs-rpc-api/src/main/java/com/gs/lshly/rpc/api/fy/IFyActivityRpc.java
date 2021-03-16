package com.gs.lshly.rpc.api.fy;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.fy.activity.dto.ActivityDTO;
import com.gs.lshly.common.struct.fy.activity.qto.ActivityQTO;
import com.gs.lshly.common.struct.fy.activity.qto.ActivityUserQTO;
import com.gs.lshly.common.struct.fy.activity.vo.ActivityUserVO;
import com.gs.lshly.common.struct.fy.activity.vo.ActivityVO;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/24 下午8:18
 */
public interface IFyActivityRpc {

    PageData<ActivityVO.ListVO> pageData(ActivityQTO.QTO qto);

    void addActivity(ActivityDTO.ETO eto);

    ActivityVO.DetailVO get(String id);

    void editActivity(ActivityDTO.PCUDTO dto);

    void deleteBatch(ActivityDTO.ActivityIdListDTO dto);

    PageData<ActivityUserVO.ListVO> activityUserPageData(ActivityUserQTO.QTO qto);
}
