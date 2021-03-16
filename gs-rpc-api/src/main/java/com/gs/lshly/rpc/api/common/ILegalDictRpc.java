package com.gs.lshly.rpc.api.common;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.common.LegalDictDTO;
import com.gs.lshly.common.struct.common.LegalDictQTO;
import com.gs.lshly.common.struct.common.LegalDictVO;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-06
*/
public interface ILegalDictRpc {

    /**
     * 分页查询企业列表
     * @param qto
     * @return
     */
    PageData<LegalDictVO.ListVO> pageData(LegalDictQTO.QTO qto);

    /**
     * 添加企业信息
     * @param eto
     * @return
     */
    String addLegalDict(LegalDictDTO.ETO eto);

    /**
     * 修改
     * @param eto
     */
    void editLegalDict(LegalDictDTO.ETO eto);

    /**
     * 企业信息详情
     * @param dto
     * @return
     */
    LegalDictVO.DetailVO detailLegalDict(LegalDictDTO.IdDTO dto);

    LegalDictVO.DetailVO detailLegalDict(LegalDictDTO.LicenseNumDTO dto);

    //--------------------------个人------------------------------

    PageData<LegalDictVO.NalListVO> nalList(LegalDictQTO.NalQTO qto);

    void nalEditLegalDict(LegalDictDTO.NalETO eto);

    LegalDictVO.NalDetailVO nalDetailLegalDict(LegalDictDTO.IdDTO dto);

    /**
     * 批量删除
     * @param dto
     */
    void deleteBatchLegalDict(LegalDictDTO.IdListDTO dto);

    LegalDictVO.CheckCertVO checkUploadCert(String corpTypeId,List<String> certIdList);

    /**
     * 导出
     * @param dto
     * @return
     * @throws Exception
     */
    ExportDataDTO export(LegalDictDTO.IdListDTO dto) throws Exception;

    List<LegalDictVO.DetailVO>   innerListLegalDict(List<String> legalIdList);

    String  innerQueryAddLegalDict(LegalDictDTO.InnerETO eto);

    LegalDictVO.InnerDetailVO innerdetailLegalDict(String legalDictId);

    LegalDictVO.ListVO innerViewlLegalDict(String legalDictId);

    void innerUpdateLegalDict(String legalId,Integer bussinessType);

}