package com.gs.lshly.biz.support.commodity.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsMaterialLibraryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsMaterialLibraryQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsMaterialLibraryVO;

import java.util.List;

public interface IGoodsMaterialLibraryService {

    /**
     * 素材库信息分页列表
     * @param qto
     * @return
     */
    PageData<GoodsMaterialLibraryVO.DetailListVO> pageData(GoodsMaterialLibraryQTO.QTO qto);

    /**
     * 添加素材库模板
     * @param eto
     */
    void addGoodsMaterialLibrary(GoodsMaterialLibraryDTO.ETO eto);

    /**
     * 批量删除素材库模板
     * @param dto
     */
    void deleteGoodsMaterialLibrary(GoodsMaterialLibraryDTO.IdListDTO dto);


    /**
     * 编辑素材库模板
     * @param eto
     */
    void editGoodsMaterialLibrary(GoodsMaterialLibraryDTO.ETO eto);


    /**
     * 查询素材库详情
     * @param dto
     * @return
     */
    GoodsMaterialLibraryVO.DetailVO detailGoodsMaterialLibrary(GoodsMaterialLibraryDTO.IdDTO dto);

    /**
     *
     * 素材库数据导出
     * @param dto
     * @return
     */
    List<GoodsMaterialLibraryVO.exportDataVO> exportData(GoodsMaterialLibraryDTO.IdListDTO dto);
}