package com.gs.lshly.biz.support.foundation.rpc.common;

import com.gs.lshly.biz.support.foundation.service.common.ICorpTypeDictService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.CorpTypeDictDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.CorpTypeDictQTO;
import com.gs.lshly.common.struct.common.CorpTypeDictVO;
import com.gs.lshly.rpc.api.common.ICorpTypeDictRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-09
*/
@DubboService
public class CorpTypeDictRpc implements ICorpTypeDictRpc{

    @Autowired
    private ICorpTypeDictService corpTypeDictService;

    @Override
    public List<CorpTypeDictVO.SimpleList> simpleList(CorpTypeDictQTO.SimpleQTO qto) {
        return corpTypeDictService.simpleList(qto);
    }

    @Override
    public PageData<CorpTypeDictVO.ListVO> pageData(CorpTypeDictQTO.QTO qto){
        return corpTypeDictService.pageData(qto);
    }

    @Override
    public void addCorpTypeDict(CorpTypeDictDTO.ETO eto){
        corpTypeDictService.addCorpTypeDict(eto);
    }

    @Override
    public void deleteBatchCorpTypeDict(CorpTypeDictDTO.IdListDTO dto){
        corpTypeDictService.deleteBatchCorpTypeDict(dto);
    }


    @Override
    public void editCorpTypeDict(CorpTypeDictDTO.ETO eto){
        corpTypeDictService.editCorpTypeDict(eto);
    }

    @Override
    public CorpTypeDictVO.DetailVO detailCorpTypeDict(CorpTypeDictDTO.IdDTO dto){
        return corpTypeDictService.detailCorpTypeDict(dto);
    }

}