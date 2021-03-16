package com.gs.lshly.middleware.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.IObjConvert;
import com.gs.lshly.common.utils.BU;

import java.util.List;


public class MybatisPlusUtil {

    public static IPage emptyPage(){
        return new Page<>(1,1);
    }

    public static <T> UpdateWrapper<T> update() {
        return new UpdateWrapper<>();
    }

    public static void eq(BaseDTO dto, QueryWrapper wq, String columnName) {
        eq(dto, wq, columnName,getPropertyName(columnName));
    }

    public static void eq(BaseDTO dto, QueryWrapper wq, String columnName, String propertyName) {
        Object value = BU.getValue(dto, propertyName);
        if (value != null && value.toString().length()>0) {
            wq.eq(columnName, value);
        }
    }

    public static void like(BaseDTO dto, QueryWrapper wq, String columnName) {
        like(dto, wq, columnName, getPropertyName(columnName));
    }

    public static void like(BaseDTO dto, QueryWrapper wq, String columnName, String propertyName) {
        Object value = BU.getValue(dto, propertyName);
        if (value != null && value.toString().length()>0) {
            wq.like(columnName, value);
        }
    }

    public static IPage pager(BaseQTO qto) {
        return new Page<>(qto.getPageNum(), qto.getPageSize());
    }

    public static <T,S> PageData<T> toPageData(BaseQTO qto, Class<T> clazz, IPage<S> pager, IObjConvert<T,S>... converts) {
        List<T> voList = qto.toListData(clazz, pager.getRecords(), converts);
        return new PageData<>(voList, qto.getPageNum(), qto.getPageSize(), pager.getTotal());
    }
    public static <T,S> PageData<T> toPageData(BaseQTO qto, Class<T> clazz, IPage<S> pager) {
        return toPageData(qto, clazz, pager, null);
    }

    public static <T> PageData<T> toPageData(BaseQTO qto, IPage<T> pager) {
        return new PageData<>(pager.getRecords(), qto.getPageNum(), qto.getPageSize(), pager.getTotal());
    }

    public static <T> PageData<T> toPageData(List<T> dataList,Integer pageNum,Integer pageSize,Long total) {
        return new PageData<>(dataList, pageNum, pageSize,total);
    }


    private static String getPropertyName(String columnName){
        int findIndex = columnName.indexOf('.');
        if(findIndex >=0){
            return StringUtils.underlineToCamel(columnName.substring(findIndex  + 1 ));
        }
        return StringUtils.underlineToCamel(columnName);
    }

    public static <T> QueryWrapper<T> query() {
        QueryWrapper queryWrapper = new QueryWrapper<T>();
        queryWrapper.eq("1", 1);
        return queryWrapper;
    }

    public static <T> QueryWrapper<T> queryContainShopId(BaseDTO dto) {
        QueryWrapper queryWrapper = query();
        if (dto.getJwtShopId() != null) {
            queryWrapper.eq("shop_id", dto.getJwtShopId());
        }
        return queryWrapper;
    }

    public static <T> QueryWrapper<T> buildContainShopIdWithAlias(BaseDTO dto, String alias) {
        QueryWrapper queryWrapper = query();
        if (dto.getJwtShopId() != null) {
            queryWrapper.eq((StringUtils.isNotBlank(alias) ? alias + "." : "") + "shop_id", dto.getJwtShopId());
        }
        return queryWrapper;
    }

}
