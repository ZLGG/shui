package com.gs.lshly.biz.support.commodity.rpc.platadmin;

import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsAttributeDictionaryService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsAttributeDictionaryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsAttributeDictionaryQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsAttributeDictionaryVO;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsAttributeDictionaryRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author Starry
 * @Date 14:51 2020/9/23
 */
@DubboService
public class GoodsAttributeDictionaryRpc implements IGoodsAttributeDictionaryRpc {
    @Autowired
    private IGoodsAttributeDictionaryService attributeDictionaryService;

    @Override
    public void addGoodsAttributeDictionary(GoodsAttributeDictionaryDTO.ETO dto) {
        attributeDictionaryService.addAttribute(dto);
    }

    @Override
    public GoodsAttributeDictionaryVO.GetCategoryVO getGetCategoryVO(GoodsAttributeDictionaryDTO.IdDTO dto) {
        return attributeDictionaryService.getGoodsAttributeDictionary(dto);
    }

    @Override
    public void deleteAttribute(GoodsAttributeDictionaryDTO.IdDTO dto) {
        attributeDictionaryService.deleteAttribute(dto);
    }

    @Override
    public void deleteAttributeBatch(GoodsAttributeDictionaryDTO.IdListDTO dto) {
        attributeDictionaryService.deleteAttributeBatch(dto);
    }

    @Override
    public GoodsAttributeDictionaryVO.DetailVO getAttributeDetails(GoodsAttributeDictionaryDTO.IdDTO dto) {
        return attributeDictionaryService.getAttributeDetails(dto);

    }

    @Override
    public PageData<GoodsAttributeDictionaryVO.ListVO> listAttribute(GoodsAttributeDictionaryQTO.QTO qto) {
        return attributeDictionaryService.list(qto);
    }

    @Override
    public void updateAttribute(GoodsAttributeDictionaryDTO.ETO dto) {
        attributeDictionaryService.updateAttribute(dto);
    }

    @Override
    public List<GoodsAttributeDictionaryVO.ListVO> listAttributes(GoodsCategoryDTO.IdDTO dto) {
        return attributeDictionaryService.listAttributes(dto);
    }

    @Override
    public List<GoodsAttributeDictionaryVO.DetailVO> listAttributeDetail(GoodsCategoryDTO.IdDTO dto) {
        return attributeDictionaryService.listAttributeDetail(dto);
    }

    @Override
    public List<GoodsAttributeDictionaryVO.DetailVO> listAttributes() {
        return attributeDictionaryService.listAttributes();
    }
}
