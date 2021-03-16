package com.gs.lshly.biz.support.foundation.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataArticleDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataArticleQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataArticleVO;

import java.io.IOException;
import java.util.List;

public interface IDataArticleService {

    PageData<DataArticleVO.ListVO> pageData(DataArticleQTO.QTO qto) throws IOException;

    void addDataArticle(DataArticleDTO.ETO eto);

    void editDataArticle(DataArticleDTO.ETO eto);

    DataArticleVO.DVO detailDataArticle(DataArticleDTO.IdDTO dto);

    void deleteDataArticleList(DataArticleDTO.IdListDTO dto);

}