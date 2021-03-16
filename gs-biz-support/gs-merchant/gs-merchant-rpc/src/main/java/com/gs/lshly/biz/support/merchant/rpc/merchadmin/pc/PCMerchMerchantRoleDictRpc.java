package com.gs.lshly.biz.support.merchant.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantRoleDictDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantRoleDictQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantRoleDictVO;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchantRoleDictService;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchMerchantRoleDictRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author xxfc
* @since 2020-10-08
*/
@DubboService
public class PCMerchMerchantRoleDictRpc implements IPCMerchMerchantRoleDictRpc {

    @Autowired
    private IPCMerchantRoleDictService pCMerchantRoleDictService;

    @Override
    public PageData<MerchantRoleDictVO.ListVO> pageData(MerchantRoleDictQTO.QTO qto){
        return pCMerchantRoleDictService.pageData(qto);
    }

    @Override
    public MerchantRoleDictVO.DetailVO detail(MerchantRoleDictDTO.IdDTO dto) {

        return pCMerchantRoleDictService.detail(dto);
    }


    @Override
    public void addMerchantRoleDict(MerchantRoleDictDTO.ETO eto){
        pCMerchantRoleDictService.addMerchantRoleDict(eto);
    }

    @Override
    public void deleteMerchantRoleDict(MerchantRoleDictDTO.IdDTO dto){
        pCMerchantRoleDictService.deleteMerchantRoleDict(dto);
    }


    @Override
    public void editMerchantRoleDict(MerchantRoleDictDTO.ETO eto){
        pCMerchantRoleDictService.editMerchantRoleDict(eto);
    }


}