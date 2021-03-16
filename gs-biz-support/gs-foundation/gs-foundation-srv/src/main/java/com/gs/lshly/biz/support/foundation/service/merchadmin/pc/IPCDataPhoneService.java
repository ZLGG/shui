package com.gs.lshly.biz.support.foundation.service.merchadmin.pc;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataPhoneDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataPhoneVO;

public interface IPCDataPhoneService {


    /**
     * 保存运营商手机号码
     * @param eto
     */
    void saveDataPhone(DataPhoneDTO.ETO eto);


    /**
     * 查询运营商手机号码
     * @param dto
     * @return
     */
    DataPhoneVO.DetailVO detailDataPhone(BaseDTO dto);

}