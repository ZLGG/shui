package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataArticleDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataArticleQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataArticleVO;

import java.io.IOException;
import java.util.List;

/**
*
* @author 陈奇
* @since 2020-10-19
*/
public interface IDataArticleRpc {

    PageData<DataArticleVO.ListVO> pageData(DataArticleQTO.QTO qto) throws IOException;

    void addDataArticle(DataArticleDTO.ETO eto);

    void editDataArticle(DataArticleDTO.ETO eto);

    DataArticleVO.DVO detailDataArticle(DataArticleDTO.IdDTO dto);

    void deleteDataArticleList(DataArticleDTO.IdListDTO dto);

}