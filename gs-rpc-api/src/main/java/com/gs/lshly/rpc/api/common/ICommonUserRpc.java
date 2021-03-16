package com.gs.lshly.rpc.api.common;
import com.gs.lshly.common.struct.common.CommonUserVO;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-05
*/
public interface ICommonUserRpc {

    CommonUserVO.DetailVO details(String userId);

    CommonUserVO.DetailVO detailsByUserName(String userName);

    List<CommonUserVO.DetailVO> moreDetail(List<String> userIdList);

    CommonUserVO.selectUserIdByShopIdVO selectUserIdByShopId(String userId);
}