package com.gs.lshly.biz.support.user.service.merchadmin.h5;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchUserVO;
import com.gs.lshly.common.struct.merchadmin.pc.user.dto.PCMerchUserDTO;
import com.gs.lshly.common.struct.merchadmin.pc.user.qto.PCMerchUserQTO;
import com.gs.lshly.common.struct.merchadmin.pc.user.vo.PCMerchUserVO;

import java.util.List;

public interface IH5MerchUserService {


    H5MerchUserVO.UserSimpleVO innerUserSimple(String userId);

}