package com.gs.lshly.biz.support.commodity.service.merchadmin.pc.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.commodity.entity.GoodsParamInfo;
import com.gs.lshly.biz.support.commodity.entity.GoodsParams;
import com.gs.lshly.biz.support.commodity.repository.IGoodsParamInfoRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsParamsRepository;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsParamsService;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsParamInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsParamsVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

/**
 * @Author Starry
 * @Date 10:49 2020/10/20
 */
@Component
public class PCMerchGoodsParamsServiceImpl implements IPCMerchGoodsParamsService {
    @Autowired
    private IGoodsParamsRepository repository;
    @Autowired
    private IGoodsParamInfoRepository paramInfoRepository;

    @Override
    public List<PCMerchGoodsParamsVO.ParamsListVO> listGoodsParams(PCMerchGoodsCategoryDTO.IdDTO dto) {
        QueryWrapper<GoodsParams> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("category_id",dto.getId());
        List<GoodsParams> params = repository.list(queryWrapper);
        List<GoodsParamInfo> paramInfos = paramInfoRepository.list();
        List<PCMerchGoodsParamsVO.ParamsListVO> paramsListVOS = new ArrayList<>();
        for (GoodsParams goodsParams : params){

            PCMerchGoodsParamsVO.ParamsListVO listVO = new PCMerchGoodsParamsVO.ParamsListVO();
            BeanUtils.copyProperties(goodsParams,listVO);

            for (GoodsParamInfo info : paramInfos){
                if (goodsParams.getId().equals(info.getParamsId())){

                    PCMerchGoodsParamInfoVO.ListVO pa = new PCMerchGoodsParamInfoVO.ListVO();
                    BeanUtils.copyProperties(info,pa);
                    //添加参数值
                    listVO.getListVOS().add(pa);
                }
            }
            paramsListVOS.add(listVO);
        }
        return paramsListVOS;
    }
}
