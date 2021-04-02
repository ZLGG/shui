package com.gs.lshly.biz.support.merchant.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMerchantAccountDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMerchantAccountQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantAccountVO;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchMerchantAccountRpc;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchMerchantAccountService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author xxfc
* @since 2020-10-23
*/
@DubboService
public class PCMerchMerchantAccountRpc implements IPCMerchMerchantAccountRpc{

    @Autowired
    private IPCMerchMerchantAccountService  pCMerchMerchantAccountService;

    @Override
    public boolean checkUserName(PCMerchMerchantAccountDTO.CheckUserNameDTO dto) {
        return pCMerchMerchantAccountService.checkUserName(dto);
    }

    @Override
    public String regMerchantAccount(PCMerchMerchantAccountDTO.RegDTO eto) {
        return pCMerchMerchantAccountService.regMerchantAccount(eto);
    }

    @Override
    public PageData<PCMerchMerchantAccountVO.ListVO> pageData(PCMerchMerchantAccountQTO.QTO qto){
        return pCMerchMerchantAccountService.pageData(qto);
    }

    @Override
    public void addMerchantAccount(PCMerchMerchantAccountDTO.AddDTO eto){
        pCMerchMerchantAccountService.addMerchantAccount(eto);
    }

    @Override
    public void deleteMerchantAccount(PCMerchMerchantAccountDTO.IdDTO dto){
        pCMerchMerchantAccountService.deleteMerchantAccount(dto);
    }


    @Override
    public void editMerchantAccount(PCMerchMerchantAccountDTO.ETO eto){
        pCMerchMerchantAccountService.editMerchantAccount(eto);
    }

    @Override
    public PCMerchMerchantAccountVO.DetailVO detailMerchantAccount(PCMerchMerchantAccountDTO.IdDTO dto){
        return  pCMerchMerchantAccountService.detailMerchantAccount(dto);
    }

    @Override
    public void updatePassworld(PCMerchMerchantAccountDTO.PassworldETO eto) {
        pCMerchMerchantAccountService.updatePassworld(eto);
    }

    @Override
    public PCMerchMerchantAccountVO.CheckShopVO checkShop(BaseDTO dto) {
        return pCMerchMerchantAccountService.checkShop(dto);
    }

    @Override
    public PCMerchMerchantAccountVO.ListVO innnerDetailMerchantAccount(String phoneNum) {
        return pCMerchMerchantAccountService.innnerDetailMerchantAccount(phoneNum);
    }

    @Override
    public void getPhoneValidCode(String phone) {
        pCMerchMerchantAccountService.getPhoneValidCode(phone);

    }

    @Override
    public String forgetPasswordByPhone(PCMerchMerchantAccountDTO.ForgetByPhoneETO dto) {
        return pCMerchMerchantAccountService.forgetPasswordByPhone(dto);
    }

    @Override
    public String forgetByEmail(PCMerchMerchantAccountDTO.ForgetByEmailETO dto) {
        return pCMerchMerchantAccountService.forgetByEmail(dto);
    }

    @Override
    public void getEmailNum(String email) {
        pCMerchMerchantAccountService.getEmailNum(email);
    }


}