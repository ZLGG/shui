package com.gs.lshly.rpc.api.common;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
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

    /**
     * 获取商家入驻信息
     * @param dto
     * @return
     */
    LegalDictVO.SettledInfoVO getSettledInfo(BaseDTO dto);

    List<LegalDictVO.DetailVO>   innerListLegalDict(List<String> legalIdList);

    String  innerQueryAddLegalDict(LegalDictDTO.InnerETO eto);

    LegalDictVO.InnerDetailVO innerdetailLegalDict(String legalDictId);

    LegalDictVO.ListVO innerViewlLegalDict(String legalDictId);

    void innerUpdateLegalDict(String legalId,Integer bussinessType);

    /**
     * 统计某企业类型必传证照数量
     * @param corpTypeId
     * @return
     */
    int innerCountNeedCert(String corpTypeId);

    /**
     * 修改入驻信息并返回id
     * @param eto
     * @return
     */
    LegalDictVO.MerchantApplyIdVO editSettledInfo(LegalDictDTO.SettledInfoETO eto);

    LegalDictVO.SettledCertInfoVO innerCertInfoVO(String corpTypeId);

    void  innereditSettleState(String legalId,Integer state);
}