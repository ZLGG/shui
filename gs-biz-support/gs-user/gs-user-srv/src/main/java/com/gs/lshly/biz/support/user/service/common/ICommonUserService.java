package com.gs.lshly.biz.support.user.service.common;
import com.gs.lshly.common.struct.common.CommonUserVO;

import java.util.List;

/**
 * @author xxfc
 */
public interface ICommonUserService {

    CommonUserVO.DetailVO details(String userId);

    CommonUserVO.DetailVO detailsByUserName(String userName);

    List<CommonUserVO.DetailVO> moreDetail(List<String> userIdList);

    CommonUserVO.selectUserIdByShopIdVO selectUserIdByShopId(String userId);
    
    /**
     * 查询电信用户详情
     * @param userId
     * @return
     */
    CommonUserVO.UserCtccDetailVO userCtccDetails(String userId);
}