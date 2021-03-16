package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataPhoneDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataPhoneVO;

/**
*
* @author xxfc
* @since 2021-01-28
*/
public interface IDataPhoneRpc {


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