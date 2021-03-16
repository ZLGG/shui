package com.gs.lshly.biz.support.merchant.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantPermissionDictDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantPermissionDictQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantPermissionDictVO;
import com.gs.lshly.biz.support.merchant.service.platadmin.IMerchantPermissionDictService;
import com.gs.lshly.rpc.api.platadmin.merchant.IMerchantPermissionDictRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author xxfc
* @since 2020-10-08
*/
@DubboService
public class MerchantPermissionDictRpc implements IMerchantPermissionDictRpc{

    @Autowired
    private IMerchantPermissionDictService merchantPermissionDictService;

    @Override
    public PageData<MerchantPermissionDictVO.ListVO> pageData(MerchantPermissionDictQTO.QTO qto){
        return merchantPermissionDictService.pageData(qto);
    }

    @Override
    public void addMerchantPermissionDict(MerchantPermissionDictDTO.ETO eto){
        merchantPermissionDictService.addMerchantPermissionDict(eto);
    }

    @Override
    public void deleteMerchantPermissionDict(MerchantPermissionDictDTO.IdDTO dto){
        merchantPermissionDictService.deleteMerchantPermissionDict(dto);
    }


    @Override
    public void editMerchantPermissionDict(MerchantPermissionDictDTO.ETO eto){
        merchantPermissionDictService.editMerchantPermissionDict(eto);
    }

    @Override
    public MerchantPermissionDictVO.DetailVO detailMerchantPermissionDict(MerchantPermissionDictDTO.IdDTO dto){
        return merchantPermissionDictService.detailMerchantPermissionDict(dto);
    }

}