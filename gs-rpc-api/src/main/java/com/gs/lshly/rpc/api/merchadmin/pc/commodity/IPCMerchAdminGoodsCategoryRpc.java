package com.gs.lshly.rpc.api.merchadmin.pc.commodity;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.common.CommonShopDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsCategoryVO;

import java.util.List;

/**
 * @Author Starry
 * @Date 16:50 2020/10/19
 */
public interface IPCMerchAdminGoodsCategoryRpc {
    /**
     *根据来自店铺的类目id集合查询类目信息
     * @param dto
     * @return
     */
    List<PCMerchGoodsCategoryVO.CategoryTreeVO> getCategoryVO(PCMerchGoodsCategoryDTO.IdListDTO dto);


    /**
     * 查询商品一级分类
     * @return
     */
    List<PCMerchGoodsCategoryVO.ListVO> level1Categories();


    /**
     * 若是自营店铺则查询所有的类目信息
     * @return
     */
    List<PCMerchGoodsCategoryVO.CategoryTreeVO> getAllCategoryVO();

    /**
     * zdf
     *
     * */
    PCMerchGoodsCategoryVO.innerCategoryVO innerGetListVo(String categoryId);


    /**
     * 检查商家入驻选择的商品分类数据是否正确
     * @return
     */
    ResponseData<Void> innerCheckMerchantApplyCategory(List<CommonShopDTO.CategoryETO> categoryList);

    PCMerchGoodsCategoryVO.innerCategoryVO innerGetListVoByName(String categoryName,String parentId);

    /**
     * 根据一级分类查找三级分类
     * @param keyWord
     * @return
     */
    List<String> selectThreeCategory(String keyWord);
}

