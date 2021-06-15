package com.gs.lshly.biz.support.commodity.service.merchadmin.pc;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.common.CommonShopDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsCategoryVO;

import java.util.List;

/**
 * @Author Starry
 * @Date 16:45 2020/10/19
 */
public interface IPCMerchGoodsCategoryService {
    /**
     *根据来自店铺的类目id集合查询类目信息
     * @param dto
     * @return
     */
    List<PCMerchGoodsCategoryVO.CategoryTreeVO> getCategoryVO(PCMerchGoodsCategoryDTO.IdListDTO dto);


    /**
     * 若是自营店铺则查询所有的类目信息
     * @return
     */
    List<PCMerchGoodsCategoryVO.CategoryTreeVO> getAllCategoryVO();

    /**
     * 根据商品类目id查询商品数据
     * @param dto
     * @return
     */
    PCMerchGoodsCategoryVO.ListVO getListVOById(PCMerchGoodsCategoryDTO.IdDTO dto);

    /**
     * 查询商品一级分类
     * @return
     */
    List<PCMerchGoodsCategoryVO.ListVO> level1Categories();

    ResponseData<Void> innerCheckMerchantApplyCategory(List<CommonShopDTO.CategoryETO> categoryList);

    //----------------内部服务--------------


    PCMerchGoodsCategoryVO.innerCategoryVO innerGetListVo(String categoryName,String parentId);

    PCMerchGoodsCategoryVO.innerCategoryVO innerByIdListVo(String categoryId);

    List<String> selectThreeCategory(List<String> categoryIdList);

    String selectOneName(String categoryId);
}
