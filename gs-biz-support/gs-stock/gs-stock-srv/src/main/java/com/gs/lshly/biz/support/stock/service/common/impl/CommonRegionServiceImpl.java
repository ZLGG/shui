package com.gs.lshly.biz.support.stock.service.common.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.stock.entity.StockRegion;
import com.gs.lshly.biz.support.stock.repository.IStockRegionRepository;
import com.gs.lshly.biz.support.stock.service.common.ICommonRegionService;
import com.gs.lshly.common.enums.RegionLevelEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.common.CommonRegionVO;
import com.gs.lshly.common.struct.common.dto.CommonRegionDTO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-24
*/
@Component
public class CommonRegionServiceImpl implements ICommonRegionService {

    @Autowired
    private IStockRegionRepository repository;

    @Override
    public List<CommonRegionVO.ProvinceVO> listToCounty() {
        QueryWrapper<StockRegion> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("level",RegionLevelEnum.省.getCode());
        List<StockRegion> regionList =  repository.list(queryWrapper);
        List<CommonRegionVO.ProvinceVO> voList = new ArrayList<>();
        List<String> provinceIdList = new ArrayList<>();
        Map<String,CommonRegionVO.ProvinceVO> provinceMap = new HashMap<>();
        for(StockRegion region:regionList){
            CommonRegionVO.ProvinceVO provinceVO = new CommonRegionVO.ProvinceVO();
            provinceVO.setId(region.getCode());
            provinceVO.setName(region.getName());
            voList.add(provinceVO);
            provinceIdList.add(region.getCode());
            provinceMap.put(region.getCode(),provinceVO);

        }
        QueryWrapper<StockRegion> queryCityWrapper = new QueryWrapper<>();
        queryCityWrapper.eq("level",RegionLevelEnum.市.getCode());
        List<StockRegion> regionCityList =  repository.list(queryCityWrapper);
        Map<String,CommonRegionVO.CityVO> cityMap = new HashMap<>();
        for(StockRegion region:regionCityList) {
            CommonRegionVO.CityVO cityVO = new CommonRegionVO.CityVO();
            cityVO.setId(region.getCode());
            cityVO.setName(region.getName());
            CommonRegionVO.ProvinceVO provinceVO = provinceMap.get(region.getParentCode());
            if (null != provinceVO) {
                provinceVO.getChildren().add(cityVO);
                cityMap.put(region.getCode(),cityVO);
            }
        }
        QueryWrapper<StockRegion> queryCountyWrapper = new QueryWrapper<>();
        queryCountyWrapper.eq("level",RegionLevelEnum.县区.getCode());
        List<StockRegion> regionCountyList =  repository.list(queryCountyWrapper);
        for(StockRegion region:regionCountyList) {
            CommonRegionVO.CountyVO countyVO = new CommonRegionVO.CountyVO();
            countyVO.setId(region.getCode());
            countyVO.setName(region.getName());
            CommonRegionVO.CityVO cityVO = cityMap.get(region.getParentCode());
            if (null != cityVO) {
                cityVO.getChildren().add(countyVO);
            }
        }
        return voList;
    }

    @Override
    public List<CommonRegionVO.ProvinceShortVO> listToCity() {
        QueryWrapper<StockRegion> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("level",RegionLevelEnum.省.getCode());
        List<StockRegion> regionList =  repository.list(queryWrapper);
        List<CommonRegionVO.ProvinceShortVO> voList = new ArrayList<>();
        List<String> provinceIdList = new ArrayList<>();
        Map<String,CommonRegionVO.ProvinceShortVO> provinceMap = new HashMap<>();
        for(StockRegion region:regionList){
            CommonRegionVO.ProvinceShortVO provinceVO = new CommonRegionVO.ProvinceShortVO();
            provinceVO.setId(region.getCode());
            provinceVO.setName(region.getName());
            voList.add(provinceVO);
            provinceIdList.add(region.getCode());
            provinceMap.put(region.getCode(),provinceVO);

        }

        QueryWrapper<StockRegion> queryCityWrapper = new QueryWrapper<>();
        queryCityWrapper.eq("level",RegionLevelEnum.市.getCode());
        List<StockRegion> regionCityList =  repository.list(queryCityWrapper);
        Map<String,CommonRegionVO.CityShortVO> cityMap = new HashMap<>();
        for(StockRegion region:regionCityList) {
            CommonRegionVO.CityShortVO cityVO = new CommonRegionVO.CityShortVO();
            cityVO.setId(region.getCode());
            cityVO.setName(region.getName());
            CommonRegionVO.ProvinceShortVO provinceVO = provinceMap.get(region.getParentCode());
            if (null != provinceVO) {
                provinceVO.getChildren().add(cityVO);
            }
        }
        return voList;
    }


    @Override
    public void addRegion(CommonRegionDTO.ETO dto){
        QueryWrapper<StockRegion> wrapper = new QueryWrapper();
        StockRegion stockRegion = new StockRegion();
        if(ObjectUtils.isNotEmpty(dto.getLevel())){
            switch (dto.getLevel()){
                case 1:
                    if(ObjectUtils.isEmpty(dto.getProvinceName())){
                        throw new BusinessException("省级名称为空");
                    }
                    if(ObjectUtils.isEmpty(dto.getProvinceCode())){
                        throw new BusinessException("省级编号为空");
                    }
                    wrapper.eq("province_name",dto.getProvinceName());
                    wrapper.eq("province_code",dto.getProvinceCode());

                    stockRegion = repository.getOne(wrapper);
                    if(stockRegion != null){
                       throw new BusinessException("该省数据已存在,请勿重复添加");
                    }
                    packProvinceDate(dto);
                    break;
                case 2:
                    if(ObjectUtils.isEmpty(dto.getCityName())){
                        throw new BusinessException("市级名称为空");
                    }
                    if(ObjectUtils.isEmpty(dto.getCityCode())){
                        throw new BusinessException("市级编号为空");
                    }
                    wrapper.eq("city_name",dto.getCityName());
                    wrapper.eq("city_code",dto.getCityCode());
                    stockRegion = repository.getOne(wrapper);
                    if(stockRegion != null){
                        throw new BusinessException("该市数据已存在,请勿重复添加");
                    }
                    packCityDate(dto);
                    break;
                case 3:
                    if(ObjectUtils.isEmpty(dto.getDistrictName())){
                        throw new BusinessException("区级名称为空");
                    }
                    if(ObjectUtils.isEmpty(dto.getDistrictCode())){
                        throw new BusinessException("区级编号为空");
                    }
                    wrapper.eq("district_name",dto.getDistrictName());
                    wrapper.eq("district_code",dto.getDistrictCode());
                    stockRegion = repository.getOne(wrapper);
                    if(stockRegion != null){
                        throw new BusinessException("该区数据已存在,请勿重复添加");
                    }
                    packDistrictDate(dto);
                    break;
                default:
                    throw new BusinessException("选择层级错误");
            }
        }
    }


    private void packProvinceDate(CommonRegionDTO.ETO dto){
        StockRegion stockRegion = new StockRegion();
        stockRegion.setCode(dto.getProvinceCode());
        stockRegion.setParentCode("00");
        stockRegion.setAncestors("00");
        stockRegion.setName(dto.getProvinceName());
        stockRegion.setProvinceName(dto.getProvinceName());
        stockRegion.setProvinceCode(dto.getProvinceCode());
        stockRegion.setLevel(dto.getLevel());
        stockRegion.setSort(1);
        BeanCopyUtils.copyProperties(dto, stockRegion);
        repository.save(stockRegion);
    }

    private void packCityDate(CommonRegionDTO.ETO dto){
        StockRegion stockRegion = new StockRegion();
        stockRegion.setCode(dto.getCityCode());
        stockRegion.setParentCode(dto.getProvinceCode());
        stockRegion.setAncestors("00"+","+dto.getProvinceCode());
        stockRegion.setName(dto.getCityName());
        stockRegion.setProvinceName(dto.getProvinceName());
        stockRegion.setProvinceCode(dto.getProvinceCode());
        stockRegion.setCityName(dto.getCityName());
        stockRegion.setCityCode(dto.getCityCode());
        stockRegion.setLevel(dto.getLevel());
        stockRegion.setSort(1);
        BeanCopyUtils.copyProperties(dto, stockRegion);
        repository.save(stockRegion);
    }

    private void packDistrictDate(CommonRegionDTO.ETO dto){
        StockRegion stockRegion = new StockRegion();
        stockRegion.setCode(dto.getDistrictCode());
        stockRegion.setParentCode(dto.getCityCode());
        stockRegion.setAncestors("00"+","+dto.getProvinceCode()+","+dto.getCityCode());
        stockRegion.setName(dto.getDistrictName());
        stockRegion.setProvinceName(dto.getProvinceName());
        stockRegion.setProvinceCode(dto.getProvinceCode());
        stockRegion.setCityName(dto.getCityName());
        stockRegion.setCityCode(dto.getCityCode());
        stockRegion.setDistrictName(dto.getDistrictName());
        stockRegion.setDistrictCode(dto.getDistrictCode());
        stockRegion.setLevel(dto.getLevel());
        stockRegion.setSort(1);
        BeanCopyUtils.copyProperties(dto, stockRegion);
        repository.save(stockRegion);
    }


    @Override
    public void editRegion(CommonRegionDTO.EditRegion dto){
        StockRegion stockRegion = new StockRegion();
        QueryWrapper<StockRegion> wrapper = new QueryWrapper();
        if(ObjectUtils.isNotEmpty(dto.getProvinceName())){
            wrapper.eq("province_name",dto.getProvinceName());
            if(ObjectUtils.isNotEmpty(repository.getOne(wrapper))){
                throw new BusinessException("该省已存在!");
            }
        }
        if(ObjectUtils.isNotEmpty(dto.getCityName())){
            wrapper.eq("city_name",dto.getCityName());
            if(ObjectUtils.isNotEmpty(repository.getOne(wrapper))){
                throw new BusinessException("该市已存在!");
            }
        }
        if(ObjectUtils.isNotEmpty(dto.getDistrictName())){
            wrapper.eq("district_name",dto.getDistrictName());
            if(ObjectUtils.isNotEmpty(repository.getOne(wrapper))){
                throw new BusinessException("该区已存在!");
            }
        }
        BeanUtils.copyProperties(dto, stockRegion);
        repository.updateById(stockRegion);
    }

    @Override
    public void deteleRegion(CommonRegionDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
}
