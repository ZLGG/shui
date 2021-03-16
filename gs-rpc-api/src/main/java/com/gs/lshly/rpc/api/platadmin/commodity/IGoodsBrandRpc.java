package com.gs.lshly.rpc.api.platadmin.commodity;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsBrandDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsBrandQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsBrandVO;

import java.util.List;


/**
 *
 * @author Hasee
 * @since  2020/9/17
 */
public interface IGoodsBrandRpc {

    void addGoodsBrand(GoodsBrandDTO.ETO dto);

    void deleteGoodsBrand(GoodsBrandDTO.IdDTO dto);

    void updateGoodsBrand(GoodsBrandDTO.ETO dto);

    PageData<GoodsBrandVO.ListVO> list(GoodsBrandQTO.QTO qoDTO);

    GoodsBrandVO.DetailVO getGoodsBrand(GoodsBrandDTO.IdDTO idDTO);

    /**
     * 查询类目关联的品牌信息
     * @param dto
     * @return
     */
    List<GoodsBrandVO.ListVO> listGoodsBrand(GoodsCategoryDTO.IdDTO dto);

    /**
     * 检查品牌名称是否在品牌字典库内
     * @param brandName
     * @return
     */
    List<GoodsBrandVO.ListVO> innerCheckGoodsBrand(String brandName);

    /**
     * 保存商品信息并返回id
     * @param eto
     * @return
     */
    GoodsBrandVO.BrandIdVO saveBrand(GoodsBrandDTO.ETO eto);

    /**
     * 添加品牌并返回品牌ID
     * @param dto
     * @return
     */
    String innerAddGoodsBrand(GoodsBrandDTO.ETO dto);
}
