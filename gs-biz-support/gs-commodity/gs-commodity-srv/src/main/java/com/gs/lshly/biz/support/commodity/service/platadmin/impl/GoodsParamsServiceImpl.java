package com.gs.lshly.biz.support.commodity.service.platadmin.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsParamInfo;
import com.gs.lshly.biz.support.commodity.entity.GoodsParams;
import com.gs.lshly.biz.support.commodity.repository.IGoodsParamInfoRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsParamsRepository;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsParamsService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsParamInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsParamsDTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsParamInfoVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsParamsVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-09-29
*/
@Component
public class GoodsParamsServiceImpl implements IGoodsParamsService {

    @Autowired
    private IGoodsParamsRepository repository;
    @Autowired
    private IGoodsParamInfoRepository paramInfoRepository;


    @Override
    public  List<GoodsParamsVO.ParamsListVO> listGoodsParams(GoodsCategoryDTO.IdDTO dto) {
        QueryWrapper<GoodsParams> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id",dto.getId());
        List<GoodsParams> params = repository.list(queryWrapper);
        List<GoodsParamInfo> paramInfos = paramInfoRepository.list();
        List<GoodsParamsVO.ParamsListVO> paramsListVOS = new ArrayList<>();
        for (GoodsParams params1 : params){

            GoodsParamsVO.ParamsListVO listVO = new GoodsParamsVO.ParamsListVO();
            BeanUtils.copyProperties(params1,listVO);

            List<GoodsParamInfoVO.ListVO> voList = new ArrayList<>();
            for (GoodsParamInfo info : paramInfos){
                if (params1.getId().equals(info.getParamsId())){

                    GoodsParamInfoVO.ListVO pa = new GoodsParamInfoVO.ListVO();
                    BeanUtils.copyProperties(info,pa);
                    //添加参数值
                    voList.add(pa);
                }
            }
            listVO.setParamInfos(voList);
            paramsListVOS.add(listVO);
        }
        return paramsListVOS;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void addGoodsParams(GoodsCategoryDTO.IdDTO dto,List<GoodsParamsDTO.ETO> eto) {

        if (ObjectUtils.isEmpty(dto)){
            throw new BusinessException("要绑定的类目参数不能为空！");
        }
        //如果该类目关联了参数先进行一个清除
        if (this.BindCategory(dto.getId())){
            deleteBindRelation(dto.getId());
        }

        if (ObjectUtils.isEmpty(eto)){
            return;
        }

        //关联参数
        checkParamsDate(eto);
        for (GoodsParamsDTO.ETO eto1 : eto){
            GoodsParams params = new GoodsParams();
            BeanUtils.copyProperties(eto1,params);
            params.setId("");
            params.setCategoryId(dto.getId());
            repository.save(params);

            List<GoodsParamInfo> paramInfoList = eto1.getParamInfos().parallelStream()
                    .map(e ->{
                        GoodsParamInfo paramInfo = new GoodsParamInfo();
                        BeanUtils.copyProperties(e,paramInfo);
                        paramInfo.setParamsId(params.getId());
                        paramInfo.setId("");
                        return paramInfo;
                    }).collect(Collectors.toList());
            paramInfoRepository.saveBatch(paramInfoList);
        }



    }


    private boolean BindCategory(String categoryId){
        QueryWrapper<GoodsParams> wrapper = MybatisPlusUtil.query();
        wrapper.eq("category_id",categoryId);
        int count = repository.count(wrapper);
        if (count > 0){
            return true;
        }
        return false;
    }

    private void deleteBindRelation(String categoryId){
        QueryWrapper<GoodsParams> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("category_id",categoryId);
        List<GoodsParams> params =repository.list(queryWrapper);

        //删除参数值
        List<String> idList = ListUtil.getIdList(GoodsParams.class,params);
        QueryWrapper<GoodsParamInfo> wrapper = MybatisPlusUtil.query();
        wrapper.in("params_id",wrapper);
        paramInfoRepository.remove(wrapper);

        //删除参数
        repository.remove(queryWrapper);
    }

    private void checkParamsDate(List<GoodsParamsDTO.ETO> eto){
        for (GoodsParamsDTO.ETO paramsEto : eto){
            if (ObjectUtils.isEmpty(paramsEto.getName())){
                throw new BusinessException("参数组名称不能为空！");
            }
            if (ObjectUtils.isEmpty(paramsEto.getParamInfos())){
                throw new BusinessException("每组参数至少添加一个参数值");
            }
            for (GoodsParamInfoDTO.ETO eto1 : paramsEto.getParamInfos()){

                if (ObjectUtils.isEmpty(eto1.getName())){
                    throw new BusinessException("参数值名称不能为空");
                }
            }
        }
    }

    @Override
    public void deleteGoodsParams(GoodsParamsDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }

    @Override
    public void editGoodsParams(GoodsParamsDTO.ETO eto) {
        GoodsParams goodsParams = new GoodsParams();
        BeanUtils.copyProperties(eto, goodsParams);
        repository.updateById(goodsParams);
    }

    @Override
    public GoodsParamsVO.DetailVO detailGoodsParams(GoodsParamsDTO.IdDTO dto) {
        GoodsParams goodsParams = repository.getById(dto.getId());
        GoodsParamsVO.DetailVO detailVo = new GoodsParamsVO.DetailVO();
        BeanUtils.copyProperties(goodsParams, detailVo);
        return detailVo;
    }

}
