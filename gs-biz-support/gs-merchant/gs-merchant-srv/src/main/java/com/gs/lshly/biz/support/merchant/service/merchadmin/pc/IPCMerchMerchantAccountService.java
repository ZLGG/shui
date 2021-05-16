package com.gs.lshly.biz.support.merchant.service.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMerchantAccountDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMerchantAccountQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantAccountVO;

public interface IPCMerchMerchantAccountService {

    boolean checkUserName(PCMerchMerchantAccountDTO.CheckUserNameDTO dto);

    String regMerchantAccount(PCMerchMerchantAccountDTO.RegDTO eto);

    PageData<PCMerchMerchantAccountVO.ListVO> pageData(PCMerchMerchantAccountQTO.QTO qto);

    void addMerchantAccount(PCMerchMerchantAccountDTO.AddDTO eto);

    void deleteMerchantAccount(PCMerchMerchantAccountDTO.IdDTO dto);


    void editMerchantAccount(PCMerchMerchantAccountDTO.ETO eto);

    PCMerchMerchantAccountVO.DetailVO detailMerchantAccount(PCMerchMerchantAccountDTO.IdDTO dto);

    PCMerchMerchantAccountVO.AccountDetailVO getByPhone(String phone);
    
    void updatePassworld(PCMerchMerchantAccountDTO.PassworldETO eto);

    PCMerchMerchantAccountVO.CheckShopVO checkShop(BaseDTO dto);

    PCMerchMerchantAccountVO.ListVO innnerDetailMerchantAccount(String phoneNum);

    void getPhoneValidCode(String phone);

    void getRegPhoneValidCode(String phone);

    String forgetPasswordByPhone(PCMerchMerchantAccountDTO.ForgetByPhoneETO dto);

    String forgetByEmail(PCMerchMerchantAccountDTO.ForgetByEmailETO dto);

    void getEmailNum(String email);
}