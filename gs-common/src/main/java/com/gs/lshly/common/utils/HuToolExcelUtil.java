package com.gs.lshly.common.utils;


import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.StyleSet;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gs.lshly.common.exception.BusinessException;
import io.swagger.annotations.ApiModelProperty;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author Starry
 * @Date 11:23 2020/10/14
 */
public class HuToolExcelUtil {
    public static <T> void downloadTemplate(Class<T> beanType, HttpServletResponse response) throws Exception{

        ExcelWriter writer = cn.hutool.poi.excel.ExcelUtil.getWriter();
        //下载模版
        writer.writeRow(getHeadTitleAndRule(beanType), true);

        String path = System.getProperty("java.io.tmpdir") + RandomUtil.randomString(32) + ".xlsx";
        File destFile = new File(path);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");

        response.setHeader("Content-Disposition","attachment;filename="+ beanType.getName() +".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();

        IoUtil.close(out);
        destFile.deleteOnExit();
        System.out.println("write data!");

    }

    /**
     * 默认注解使用ApiModelProperty
     *
     * @param beanType
     * @return
     */
    public static  <T> Map<String, Integer> getHeadTitleAndRule(Class<T> beanType) {
        Field[] fields = beanType.getDeclaredFields();
        Map<String, Integer> map = new HashMap<>();
        for (Field field : fields) {
            String alisaName = field.getDeclaredAnnotation(ApiModelProperty.class).value();
            Integer idx = field.getDeclaredAnnotation(ApiModelProperty.class).position();
            map.put(alisaName, idx);
        }
        map =   map.entrySet().stream()
                .sorted((Map.Entry.<String, Integer>comparingByValue()))
                .collect(Collectors.toMap(x ->(String) x.getKey(), x -> (Integer)x.getValue(), (x1, x2) -> x2, LinkedHashMap::new));
        for (Map.Entry<String, Integer> mm : map.entrySet()){
            map.put(mm.getKey(),null);
        }
        return map;

    }


    /**
     * 数据导入工具类
     * @param beanType
     * @param file
     * @param <T>
     * @return
     * @throws IOException
     */
    public static  <T> List<T> importData(Class<T> beanType, MultipartFile file) throws IOException {
        ExcelReader excelReader = cn.hutool.poi.excel.ExcelUtil.getReader(file.getInputStream());

        Field[] fields = beanType.getDeclaredFields();
        for (Field field : fields) {
            String headName = field.getDeclaredAnnotation(ApiModelProperty.class).value();
            String fieldName = field.getName();
            excelReader.addHeaderAlias(headName, fieldName);
        }

        List<T> data = excelReader.readAll(beanType);
        return data;
    }

}
