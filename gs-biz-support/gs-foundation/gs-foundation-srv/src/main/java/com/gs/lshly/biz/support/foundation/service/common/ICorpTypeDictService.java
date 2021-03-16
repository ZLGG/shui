package com.gs.lshly.biz.support.foundation.service.common;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.CorpTypeDictDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.CorpTypeDictQTO;
import com.gs.lshly.common.struct.common.CorpTypeDictVO;

import java.util.List;

public interface ICorpTypeDictService {

    List<CorpTypeDictVO.SimpleList> simpleList(CorpTypeDictQTO.SimpleQTO qto);

    /**
     * 企业类型分页列表
     * @param qto
     * @return
     */
    PageData<CorpTypeDictVO.ListVO> pageData(CorpTypeDictQTO.QTO qto);

    /**
     * 新增企业类型
     * @param eto
     */
    void addCorpTypeDict(CorpTypeDictDTO.ETO eto);

    /**
     * 批量删除
     * @param dto
     */
    void deleteBatchCorpTypeDict(CorpTypeDictDTO.IdListDTO dto);


    /**
     * 编辑
     * @param eto
     */
    void editCorpTypeDict(CorpTypeDictDTO.ETO eto);

    /**
     * 企业类型详情
     * @param dto
     * @return
     */
    CorpTypeDictVO.DetailVO detailCorpTypeDict(CorpTypeDictDTO.IdDTO dto);

}