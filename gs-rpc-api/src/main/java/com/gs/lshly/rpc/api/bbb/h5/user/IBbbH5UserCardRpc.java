package com.gs.lshly.rpc.api.bbb.h5.user;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserCardDTO;
import com.gs.lshly.common.struct.bbb.h5.user.qto.BbbH5UserCardQTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserCardVO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserVO;

import java.util.List;

/**
*
* @author xxfc
* @since 2021-01-05
*/
public interface IBbbH5UserCardRpc {

    PageData<BbbH5UserCardVO.ListVO> pageData(BbbH5UserCardQTO.QTO qto);

    void addUserCard(BbbH5UserCardDTO.ETO eto);

    void deleteUserCard(BbbH5UserCardDTO.IdDTO dto);

    BbbH5UserCardVO.DetailVO detailUserCard(BbbH5UserCardDTO.IdDTO dto);

    BbbH5UserVO.UserIntegralVO integral(BaseDTO dto);

    PageData<BbbH5UserVO.UserIntegralRecordVO> integralLog(BaseQTO qto);

    void signInIntegralLog(BaseDTO dto);

    BbbH5UserVO.UserIntegralStatusVO signInIntegralLogState(BaseDTO dto);
}