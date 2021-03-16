package com.gs.lshly.rpc.api.merchadmin.h5.user;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchUserVO;

/**
*
* @author xxfc
* @since 2020-10-20
*/
public interface IH5MerchUserRpc {



    H5MerchUserVO.UserSimpleVO innerUserSimple(String userId);


}