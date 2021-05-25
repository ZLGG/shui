package com.gs.lshly.rpc.api.platadmin.foundation;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.BasicAreasVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SysSmVO;

import java.util.List;

/**
 *
 * @author chenyang
 */
public interface IBasicAreasRpc {

    List<BasicAreasVO.DropListVO> dropList(Integer pid);
}
