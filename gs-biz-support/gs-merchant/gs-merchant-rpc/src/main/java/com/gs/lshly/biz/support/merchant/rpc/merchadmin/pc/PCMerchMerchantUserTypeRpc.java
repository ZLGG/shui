package com.gs.lshly.biz.support.merchant.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMerchantUserTypeDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMerchantUserTypeQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantUserTypeVO;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchMerchantUserTypeRpc;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchMerchantUserTypeService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-12-25
*/
@DubboService
public class PCMerchMerchantUserTypeRpc implements IPCMerchMerchantUserTypeRpc{

    @Autowired
    private IPCMerchMerchantUserTypeService  pCMerchMerchantUserTypeService;

    @Override
    public PageData<PCMerchMerchantUserTypeVO.ListVO> pageData(PCMerchMerchantUserTypeQTO.QTO qto){
        return pCMerchMerchantUserTypeService.pageData(qto);
    }

    @Override
    public List<PCMerchMerchantUserTypeVO.ListVO> listData(BaseDTO dto) {
        return pCMerchMerchantUserTypeService.listData(dto);
    }


    @Override
    public void saveMerchantUserType(PCMerchMerchantUserTypeDTO.ETO eto){
        pCMerchMerchantUserTypeService.saveMerchantUserType(eto);
    }



    @Override
    public void deleteBatch(PCMerchMerchantUserTypeDTO.IdListDTO dto) {
        pCMerchMerchantUserTypeService.deleteBatch(dto);
    }




    @Override
    public PCMerchMerchantUserTypeVO.DetailVO detailMerchantUserType(PCMerchMerchantUserTypeDTO.IdDTO dto){
        return  pCMerchMerchantUserTypeService.detailMerchantUserType(dto);
    }

    @Override
    public List<PCMerchMerchantUserTypeVO.ListVO> list(BaseDTO dto) {
        return pCMerchMerchantUserTypeService.list(dto);
    }

}