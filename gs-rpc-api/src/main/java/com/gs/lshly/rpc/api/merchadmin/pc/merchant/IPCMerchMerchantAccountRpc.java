package com.gs.lshly.rpc.api.merchadmin.pc.merchant;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMerchantAccountDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchantAccountLoginDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMerchantAccountQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantAccountVO;

/**
*
* @author xxfc
* @since 2020-10-23
*/
public interface IPCMerchMerchantAccountRpc {

    boolean checkUserName(PCMerchMerchantAccountDTO.CheckUserNameDTO dto);

    String regMerchantAccount(PCMerchMerchantAccountDTO.RegDTO eto);

    PageData<PCMerchMerchantAccountVO.ListVO> pageData(PCMerchMerchantAccountQTO.QTO qto);

    void addMerchantAccount(PCMerchMerchantAccountDTO.AddDTO eto);

    void deleteMerchantAccount(PCMerchMerchantAccountDTO.IdDTO dto);

    void editMerchantAccount(PCMerchMerchantAccountDTO.ETO eto);

    PCMerchMerchantAccountVO.DetailVO detailMerchantAccount(PCMerchMerchantAccountDTO.IdDTO dto);

    void updatePassworld(PCMerchMerchantAccountDTO.PassworldETO eto);

    PCMerchMerchantAccountVO.CheckShopVO checkShop(BaseDTO dto);

    PCMerchMerchantAccountVO.ListVO innnerDetailMerchantAccount(String phoneNum);

    void getPhoneValidCode(String phone);

    void getRegPhoneValidCode(String phone);

    String forgetPasswordByPhone(PCMerchMerchantAccountDTO.ForgetByPhoneETO dto);

    String forgetByEmail(PCMerchMerchantAccountDTO.ForgetByEmailETO dto);

    void getEmailNum(String email);
    
    /**
     * 跟据手机号码查询商户帐户
     * @param phone
     * @return
     */
    PCMerchMerchantAccountVO.AccountDetailVO getByPhone(String phone);
    
}