package com.gs.lshly.biz.support.foundation.rpc.platadmin;

import com.gs.lshly.biz.support.foundation.service.platadmin.IBasicAreasService;
import com.gs.lshly.common.struct.platadmin.foundation.vo.BasicAreasVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.BasicAreasVO.AddressListVO;
import com.gs.lshly.rpc.api.platadmin.foundation.IBasicAreasRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 * @author chenyang
 */
@DubboService
public class BasicAreasRpc implements IBasicAreasRpc {

    @Autowired
    private IBasicAreasService iBasicAreasService;

    @Override
    public List<BasicAreasVO.DropListVO> dropList(Integer pid) {
        return iBasicAreasService.dropList(pid);
    }

	@Override
	public List<AddressListVO> addressList(Integer pid) {
		 return iBasicAreasService.addressList(pid);
	}
}
