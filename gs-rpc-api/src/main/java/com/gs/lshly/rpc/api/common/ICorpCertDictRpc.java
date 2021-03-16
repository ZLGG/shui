package com.gs.lshly.rpc.api.common;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.CorpCertDictDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.CorpCertDictQTO;
import com.gs.lshly.common.struct.common.CorpCertDictVO;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-09
*/
public interface ICorpCertDictRpc {

    /**
     * 企业证照分页
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