package com.gs.lshly.biz.support.foundation.service.platadmin;

import com.gs.lshly.common.struct.platadmin.foundation.vo.BasicAreasVO;

import java.util.List;

public interface IBasicAreasService {

    List<BasicAreasVO.DropListVO> dropList(Integer pid);
}
