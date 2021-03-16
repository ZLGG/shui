package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.biz.support.foundation.service.merchadmin.pc.IPCDataPhoneService;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataPhoneDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataPhoneVO;
import com.gs.lshly.rpc.api.platadmin.foundation.IDataPhoneRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author xxfc
* @since 2021-01-28
*/
@DubboService
public class DataPhoneRpc implements IDataPhoneRpc{

    @Autowired
    private IPCDataPhoneService DataPhoneService;


    @Override
    public void saveDataPhone(DataPhoneDTO.ETO eto) {
        DataPhoneService.saveDataPhone(eto);
    }

    @Override
    public DataPhoneVO.DetailVO detailDataPhone(BaseDTO dto){
        return  DataPhoneService.detailDataPhone(dto);
    }

}