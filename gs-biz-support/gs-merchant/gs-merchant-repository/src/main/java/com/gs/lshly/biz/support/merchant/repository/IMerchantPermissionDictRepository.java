package com.gs.lshly.biz.support.merchant.repository;

import com.gs.lshly.biz.support.merchant.entity.MerchantPermissionDict;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantPermissionDictVO;

import java.util.List;

/**
 * <p>
 * 商家帐号权限字典 服务类
 * </p>
 *
 * @author xxfc
 * @since 2020-10-08
 */
public interface IMerchantPermissionDictRepository extends IService<MerchantPermissionDict> {

    List<PCMerchMerchantPermissionDictVO.ListVO> selectPermissionDictForGroup();
}
