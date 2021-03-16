package com.gs.lshly.biz.support.foundation.service.common;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.CorpCertDictDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.CorpCertDictQTO;
import com.gs.lshly.common.struct.common.CorpCertDictVO;

import java.util.List;

public interface ICorpCertDictService {

    /**
     * 证照分页列表
     * @param qto
     * @return
     */
    PageData<CorpCertDictVO.ListVO> pageData(CorpCertDictQTO.QTO qto);

    void addCorpCertDict(CorpCertDictDTO.ETO eto);

    /**
     * 批量删除
     * @param dto
     */
    void deleteBatchCorpCertDict(CorpCertDictDTO.IdListDTO dto);

    void editCorpCertDict(CorpCertDictDTO.ETO eto);

    /**
     * 企业证照详情
     * @param dto
     * @return
     */
    CorpCertDictVO.DetailVO detailCorpCertDict(CorpCertDictDTO.IdDTO dto);

    List<CorpCertDictVO.SimpleVO> ListCorpTypeCert(CorpCertDictQTO.CorpTypeIdQTO qto);

}