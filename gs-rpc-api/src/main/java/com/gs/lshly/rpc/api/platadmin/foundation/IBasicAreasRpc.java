package com.gs.lshly.rpc.api.platadmin.foundation;

import java.util.List;

import com.gs.lshly.common.struct.platadmin.foundation.vo.BasicAreasVO;

/**
 *
 * @author chenyang
 */
public interface IBasicAreasRpc {

    List<BasicAreasVO.DropListVO> dropList(Integer pid);
    
    
    List<BasicAreasVO.AddressListVO> addressList(Integer pid);
}
