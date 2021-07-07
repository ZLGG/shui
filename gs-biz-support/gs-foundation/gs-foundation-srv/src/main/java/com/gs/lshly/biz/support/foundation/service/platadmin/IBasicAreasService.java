package com.gs.lshly.biz.support.foundation.service.platadmin;

import com.gs.lshly.common.struct.platadmin.foundation.vo.BasicAreasVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.BasicAreasVO.AddressListVO;

import java.util.List;

public interface IBasicAreasService {

    List<BasicAreasVO.DropListVO> dropList(Integer pid);
    
    List<AddressListVO> addressList(Integer pid) ;
}
