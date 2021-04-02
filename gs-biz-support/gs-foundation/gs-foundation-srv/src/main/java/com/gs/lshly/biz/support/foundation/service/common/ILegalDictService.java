package com.gs.lshly.biz.support.foundation.service.common;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.common.LegalDictDTO;
import com.gs.lshly.common.struct.common.LegalDictQTO;
import com.gs.lshly.common.struct.common.LegalDictVO;

import java.util.List;

public interface ILegalDictService {

    /**
     * 分页查询企业列表
     * @param qto
     * @return
     */
    PageData<LegalDictVO.ListVO> pageData(LegalDictQTO.QTO qto);

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

    /**
     * 个人分页查询
     * @param qto
     * @return
     */
    PageData<LegalDictVO.NalListVO> nalList(LegalDictQTO.NalQTO qto);

    /**
     * 个人编辑
     * @param eto
     */
    void nalEditLegalDict(LegalDictDTO.NalETO eto);

    /**
     * 个人详情
     * @param dto
     * @return
     */
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

    /**
     * 修改入驻信息并返回id
     * @param eto
     * @return
     */
    LegalDictVO.MerchantApplyIdVO editSettledInfo(LegalDictDTO.SettledInfoETO eto);

    //-------------内部服务--------------


    void innerUpdateLegalDict(String legalId,Integer bussinessType);


    List<LegalDictVO.DetailVO>   innerListLegalDict(List<String> legalIdList);

    String  innerQueryAddLegalDict(LegalDictDTO.InnerETO eto);


    LegalDictVO.InnerDetailVO innerdetailLegalDict(String legalDictId);

    LegalDictVO.ListVO innerViewlLegalDict(String legalDictId);

    /**
     * 统计某企业类型必传证照数量
     * @param corpTypeId
     * @return
     */
    int innerCountNeedCert(String corpTypeId);

    LegalDictVO.SettledCertInfoVO innerCertInfoVO(String corpTypeId);

    void  innereditSettleState(String legalId,Integer state);
}