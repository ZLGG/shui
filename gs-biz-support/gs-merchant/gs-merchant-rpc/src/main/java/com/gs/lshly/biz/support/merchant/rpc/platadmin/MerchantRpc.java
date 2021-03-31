//package com.gs.lshly.biz.support.merchant.rpc.platadmin;
//import org.apache.dubbo.config.annotation.DubboService;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.gs.lshly.biz.support.merchant.service.platadmin.IMerchantAccountService;
//import com.gs.lshly.common.response.PageData;
//import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantAccountDTO;
//import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantAccountQTO;
//import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantAccountVO;
//
///**
//*
//* @author xxfc
//* @since 2020-10-08
//*/
//@DubboService
//public class MerchantRpc implements IMerchantRpc{
//
//    @Autowired
//    private IMerchantAccountService merchantAccountService;
//
//    @Override
//    public PageData<MerchantAccountVO.ListVO> pageData(MerchantAccountQTO.QTO qto){
//        return merchantAccountService.pageData(qto);
//    }
//
//    @Override
//    public void addMerchantAccountForPlatForm(MerchantAccountDTO.PlatformAccountETO eto){
//        merchantAccountService.addMerchantAccountForPlatForm(eto);
//    }
//
//    @Override
//    public void editMerchantAccountPwd(MerchantAccountDTO.ModifyPwdETO eto) {
//        merchantAccountService.editMerchantAccountPwd(eto);
//    }
//
//
//
//
//
//
//}