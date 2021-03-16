package com.gs.lshly.biz.support.merchant.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.AuthDTO;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantAccountDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantAccountQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantAccountVO;

public interface IMerchantAccountService {

    PageData<MerchantAccountVO.ListVO> pageData(MerchantAccountQTO.QTO qto);

    void addMerchantAccountForPlatForm(MerchantAccountDTO.PlatformAccountETO eto);

    void editMerchantAccountPwd(MerchantAccountDTO.ModifyPwdETO eto);

    /**
     * 通过手机号码取商家ID 和 店铺ID
     * @param qto
     * @return
     */
    MerchantAccountVO.MerchShopVO getMerchantShopByPhone(MerchantAccountQTO.PhoneQTO qto);

    /**
     * 验证账号是否有效
     * @param id
     * @return
     */
    Boolean isValidAccount(String id);

    AuthDTO getJwtUserById(String userId);

    AuthDTO findByUserName(String username);
}