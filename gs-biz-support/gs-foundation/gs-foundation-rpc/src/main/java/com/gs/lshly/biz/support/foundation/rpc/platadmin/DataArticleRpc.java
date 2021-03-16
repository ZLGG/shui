package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataArticleDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataArticleQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataArticleVO;
import com.gs.lshly.rpc.api.platadmin.foundation.IDataArticleRpc;
import com.gs.lshly.biz.support.foundation.service.platadmin.IDataArticleService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

/**
*
* @author 陈奇
* @since 2020-10-19
*/
@DubboService
public class DataArticleRpc implements IDataArticleRpc{

    @Autowired
    private IDataArticleService  DataArticleService;

    @Override
    public PageData<DataArticleVO.ListVO> pageData(DataArticleQTO.QTO qto) throws IOException {
        return DataArticleService.pageData(qto);
    }

    @Override
    public void addDataArticle(DataArticleDTO.ETO eto){
        DataArticleService.addDataArticle(eto);
    }

    @Override
    public void editDataArticle(DataArticleDTO.ETO eto){
        DataArticleService.editDataArticle(eto);
    }

    @Override
    public DataArticleVO.DVO detailDataArticle(DataArticleDTO.IdDTO dto){
        return  DataArticleService.detailDataArticle(dto);
    }

    @Override
    public void deleteDataArticleList(DataArticleDTO.IdListDTO dto) {
        DataArticleService.deleteDataArticleList(dto);
    }


}