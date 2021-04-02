package com.gs.lshly.biz.support.foundation.rpc.common;

import com.gs.lshly.biz.support.foundation.service.common.ILegalDictService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.common.LegalDictDTO;
import com.gs.lshly.common.struct.common.LegalDictQTO;
import com.gs.lshly.common.struct.common.LegalDictVO;
import com.gs.lshly.rpc.api.common.ILegalDictRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-06
*/
@DubboService
public class LegalDictRpc implements ILegalDictRpc {

    @Autowired
    private ILegalDictService legalDictService;

    @Override
    public PageData<LegalDictVO.ListVO> pageData(LegalDictQTO.QTO qto){
        return legalDictService.pageData(qto);
    }

    @Override
    public String addLegalDict(LegalDictDTO.ETO eto){
       return  legalDictService.addLegalDict(eto);
    }


    @Override
    public void editLegalDict(LegalDictDTO.ETO eto){
        legalDictService.editLegalDict(eto);
    }

    @Override
    public LegalDictVO.DetailVO detailLegalDict(LegalDictDTO.IdDTO dto){
        return legalDictService.detailLegalDict(dto);
    }

    @Override
    public LegalDictVO.DetailVO detailLegalDict(LegalDictDTO.LicenseNumDTO dto) {
        return legalDictService.detailLegalDict(dto);
    }

    @Override
    public void deleteBatchLegalDict(LegalDictDTO.IdListDTO dto) {
        legalDictService.deleteBatchLegalDict(dto);
    }


    //------------------------------个人------------------------------------------

    @Override
    public PageData<LegalDictVO.NalListVO> nalList(LegalDictQTO.NalQTO qto) {

        return legalDictService.nalList(qto);
    }

    @Override
    public void nalEditLegalDict(LegalDictDTO.NalETO eto) {
        legalDictService.nalEditLegalDict(eto);
    }

    @Override
    public LegalDictVO.NalDetailVO nalDetailLegalDict(LegalDictDTO.IdDTO dto) {
        return legalDictService.nalDetailLegalDict(dto);
    }

    @Override
    public LegalDictVO.CheckCertVO checkUploadCert(String corpTypeId,List<String> certIdList) {
        return legalDictService.checkUploadCert(corpTypeId,certIdList);
    }

    @Override
    public ExportDataDTO export(LegalDictDTO.IdListDTO dto) throws Exception {
        return legalDictService.export(dto);
    }

    @Override
    public LegalDictVO.SettledInfoVO getSettledInfo(BaseDTO dto) {
        return legalDictService.getSettledInfo(dto);
    }

    @Override
    public List<LegalDictVO.DetailVO> innerListLegalDict(List<String> legalIdList) {
        return legalDictService.innerListLegalDict(legalIdList);
    }

    @Override
    public String innerQueryAddLegalDict(LegalDictDTO.InnerETO eto) {
        return legalDictService.innerQueryAddLegalDict(eto);
    }

    @Override
    public LegalDictVO.InnerDetailVO innerdetailLegalDict(String legalDictId) {
        return legalDictService.innerdetailLegalDict(legalDictId);
    }

    @Override
    public LegalDictVO.ListVO innerViewlLegalDict(String legalDictId) {
        return legalDictService.innerViewlLegalDict(legalDictId);
    }

    @Override
    public void innerUpdateLegalDict(String legalId, Integer bussinessType) {
        legalDictService.innerUpdateLegalDict(legalId, bussinessType);
    }

    @Override
    public int innerCountNeedCert(String corpTypeId) {
        return legalDictService.innerCountNeedCert(corpTypeId);
    }

    @Override
    public LegalDictVO.MerchantApplyIdVO editSettledInfo(LegalDictDTO.SettledInfoETO eto) {
       return legalDictService.editSettledInfo(eto);
    }

    @Override
    public LegalDictVO.SettledCertInfoVO innerCertInfoVO(String corpTypeId) {
        return legalDictService.innerCertInfoVO(corpTypeId);
    }

    @Override
    public void innereditSettleState(String legalId, Integer state) {
        legalDictService.innereditSettleState(legalId, state);
    }

}