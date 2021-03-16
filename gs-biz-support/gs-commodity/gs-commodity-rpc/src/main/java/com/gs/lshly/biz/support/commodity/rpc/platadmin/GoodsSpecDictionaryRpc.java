package com.gs.lshly.biz.support.commodity.rpc.platadmin;

import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsSpecsDictionaryService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsSpecDictionaryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsSpecDictionaryQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsAttributeDictionaryVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsSpecDictionaryVO;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsSpecDictionaryRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author Starry
 * @Date 13:48 2020/9/25
 */
@DubboService
public class GoodsSpecDictionaryRpc implements IGoodsSpecDictionaryRpc {
    @Autowired
    private IGoodsSpecsDictionaryService dictionaryService;

    @Override
    public void addSpec(GoodsSpecDictionaryDTO.ETO addSpecInfoETO) {
        dictionaryService.addSpecInfo(addSpecInfoETO);
    }

    @Override
    public void deleteSpec(GoodsSpecDictionaryDTO.IdDTO idDTO) {
        dictionaryService.deleteSpecInfo(idDTO);
    }

    @Override
    public void deleteSpecBatches(GoodsSpecDictionaryDTO.IdListDTO dto) {
        dictionaryService.deleteSpecBatches(dto);
    }

    @Override
    public GoodsSpecDictionaryVO.DetailVO getSpecDetails(GoodsSpecDictionaryDTO.IdDTO idDTO) {
        return dictionaryService.getSpecDetails(idDTO);
    }

    @Override
    public void updateSpec(GoodsSpecDictionaryDTO.ETO dto) {
        dictionaryService.updateSpec(dto);
    }

    @Override
    public GoodsSpecDictionaryVO.GetCategoryVO getSpecInfo(GoodsSpecDictionaryDTO.IdDTO idDTO) {
        return dictionaryService.getSpecInfo(idDTO);
    }

    @Override
    public PageData<GoodsSpecDictionaryVO.ListVO> listSpec(GoodsSpecDictionaryQTO.QTO qto) {
        return dictionaryService.list(qto);
    }

    @Override
    public List<GoodsSpecDictionaryVO.ListVO> listSpecInfo(GoodsCategoryDTO.IdDTO dto) {
        return dictionaryService.listSpec(dto);
    }

    @Override
    public List<GoodsSpecDictionaryVO.DetailVO> listSpecDetail(GoodsCategoryDTO.IdDTO dto) {
        return dictionaryService.listSpecDetail(dto);
    }

    @Override
    public List<GoodsSpecDictionaryVO.DetailVO> listSpecInfos() {
        return dictionaryService.listSpecInfos();
    }


}
