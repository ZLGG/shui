package com.gs.lshly.rpc.api.platadmin.merchant;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantAccountDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantAccountQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantAccountVO;

/**
*
* @author xxfc
* @since 2020-10-08
*/
public interface IMerchantAccountRpc {

    PageData<MerchantAccountVO.ListVO> pageData(MerchantAccountQTO.QTO qto);

    /**
     * 添加自营帐号
     * @param eto
     */
    void addMerchantAccountForPlatForm(MerchantAccountDTO.PlatformAccountETO eto);

    /**
     * 修改帐号密码
     * @param eto
     */
    void editMerchantAccountPwd(MerchantAccountDTO.ModifyPwdETO eto);


}