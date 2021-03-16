package com.gs.lshly.biz.support.commodity.service.platadmin;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsBrandDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsBrandVO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsBrandDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsBrandQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsBrandVO;

import java.util.List;

/**
 * <p>
 * 商品品牌 服务类
 * </p>
 *
 * @author 
 * @since 2020-09-17
 */
public interface IGoodsBrandService {

    /**
     * 添加品牌
     * @param dto
     */
    void save(GoodsBrandDTO.ETO dto);

    void delete(GoodsBrandDTO.IdDTO dto);

    void update(GoodsBrandDTO.ETO dto);

    PageData<GoodsBrandVO.ListVO> list(GoodsBrandQTO.QTO qoDTO);

    GoodsBrandVO.DetailVO select(GoodsBrandDTO.IdDTO dto);

    /**
     * 查询类目关联的品牌信息
     * @param dto
     * @return
     */
    List<GoodsBrandVO.ListVO> listGoodsBrand(GoodsCategoryDTO.IdDTO dto);

    /**
     * 根据品牌名称或去品牌信息
     * @param dto
     * @return
     */
    GoodsBrandVO.DetailVO selectByName(GoodsBrandDTO.BrandNameDTO dto);

    /**
     * 根据商品品牌id获取商品品牌信息
     * @param dto
     * @return
     */
    GoodsBrandVO.ListVO getBrandVOById(GoodsBrandDTO.IdDTO dto);

    /**
     * 保存商品信息并返回id
     * @param eto
     * @return
     */
    GoodsBrandVO.BrandIdVO saveBrand(GoodsBrandDTO.ETO eto);


    List<GoodsBrandVO.ListVO> innerCheckGoodsBrand(String brandName);

    String innerAddGoodsBrand(GoodsBrandDTO.ETO dto);
}
