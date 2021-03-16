package com.gs.lshly.biz.support.foundation.rpc.common;
import com.gs.lshly.biz.support.foundation.service.common.ICorpCertDictService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.CorpCertDictDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.CorpCertDictQTO;
import com.gs.lshly.common.struct.common.CorpCertDictVO;
import com.gs.lshly.rpc.api.common.ICorpCertDictRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-09
*/
@DubboService
public class CorpCertDictRpc implements ICorpCertDictRpc{

    @Autowired
    private ICorpCertDictService corpCertDictService;

    @Override
    public PageData<CorpCertDictVO.ListVO> pageData(CorpCertDictQTO.QTO qto){
        return corpCertDictService.pageData(qto);
    }

    @Override
    public void addCorpCertDict(CorpCertDictDTO.ETO eto){
        corpCertDictService.addCorpCertDict(eto);
    }

    @Override
    public void deleteBatchCorpCertDict(CorpCertDictDTO.IdListDTO dto){
        corpCertDictService.deleteBatchCorpCertDict(dto);
    }


    @Override
    public void editCorpCertDict(CorpCertDictDTO.ETO eto){
        corpCertDictService.editCorpCertDict(eto);
    }

    @Override
    public CorpCertDictVO.DetailVO detailCorpCertDict(CorpCertDictDTO.IdDTO dto){
        return corpCertDictService.detailCorpCertDict(dto);
    }

    @Override
    public List<CorpCertDictVO.SimpleVO> ListCorpTypeCert(CorpCertDictQTO.CorpTypeIdQTO qto) {

        return corpCertDictService.ListCorpTypeCert(qto);
    }

}