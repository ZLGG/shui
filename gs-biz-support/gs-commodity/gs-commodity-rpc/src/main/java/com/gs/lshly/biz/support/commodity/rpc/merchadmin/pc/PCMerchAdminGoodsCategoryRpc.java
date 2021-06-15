package com.gs.lshly.biz.support.commodity.rpc.merchadmin.pc;

import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsCategoryService;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.common.CommonShopDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsCategoryVO;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsCategoryRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author Starry
 * @Date 16:44 2020/10/19
 */
@DubboService
public class PCMerchAdminGoodsCategoryRpc implements IPCMerchAdminGoodsCategoryRpc {

   @Autowired
   private IPCMerchGoodsCategoryService categoryService;

    @Override
    public List<PCMerchGoodsCategoryVO.CategoryTreeVO> getCategoryVO(PCMerchGoodsCategoryDTO.IdListDTO dto) {
        return categoryService.getCategoryVO(dto);
    }

    @Override
    public List<PCMerchGoodsCategoryVO.ListVO> level1Categories() {
        return categoryService.level1Categories();
    }

    @Override
    public List<PCMerchGoodsCategoryVO.CategoryTreeVO> getAllCategoryVO() {
        return categoryService.getAllCategoryVO();
    }

    @Override
    public PCMerchGoodsCategoryVO.innerCategoryVO innerGetListVo(String categoryId) {
        return categoryService.innerByIdListVo(categoryId);
    }

    @Override
    public ResponseData<Void> innerCheckMerchantApplyCategory(List<CommonShopDTO.CategoryETO> categoryList) {
        return categoryService.innerCheckMerchantApplyCategory(categoryList);
    }

    @Override
    public PCMerchGoodsCategoryVO.innerCategoryVO innerGetListVoByName(String categoryName,String parentId) {
        return categoryService.innerGetListVo(categoryName,parentId);
    }

    @Override
    public List<String> selectThreeCategory(List<String> categoryIdList) {
        return categoryService.selectThreeCategory(categoryIdList);
    }

    @Override
    public String selectOneName(String categoryId) {
        return categoryService.selectOneName(categoryId);
    }
}
