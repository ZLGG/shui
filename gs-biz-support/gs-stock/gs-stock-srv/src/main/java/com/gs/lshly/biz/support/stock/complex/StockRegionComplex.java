package com.gs.lshly.biz.support.stock.complex;

import com.gs.lshly.common.struct.common.CommonRegionVO;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class StockRegionComplex {

    private CommonRegionVO.ProvinceVO provinceVO;

    private Map<String,CommonRegionVO.CityVO> cityMap = new HashMap<>();

}
