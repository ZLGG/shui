package com.gs.lshly.common.utils;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ClassLoaderUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import com.gs.lshly.common.annotation.ExportProperty;
import com.gs.lshly.common.enums.EnumMessage;
import com.gs.lshly.common.excel.ExcelHeader;
import com.gs.lshly.common.struct.ExportDataDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.dubbo.common.utils.StringUtils;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/**
 * @author lxus
 */
public class ExcelUtil {

    public static <T> ExportDataDTO treatmentBean(List<T> content, Class<T> clazz) throws Exception {
        List<Map<String, Object>> rowData = new ArrayList<>();
        ExportDataDTO dataDTO = new ExportDataDTO();
        ApiModel apiModel = AnnotationUtil.getAnnotation(clazz, ApiModel.class);
        //导出文件名
        dataDTO.setFileName(URLEncoder.encode(apiModel!=null && StringUtils.isNotEmpty(apiModel.value()) ? apiModel.value() : clazz.getSimpleName(),"UTF-8"));
        //导出列
        dataDTO.setHeaders(getHeader(clazz));
        //导出数据行
        for (T row : content) {
            Map<String, Object> rowMap = new HashMap<>();
            for (ExcelHeader header : dataDTO.getHeaders()) {
                Object value = BU.getValue(row, header.getProperty());
                if (value != null){
                    //处理枚举
                    if(StringUtils.isNotEmpty(header.getEnumClassName())) {
                        value = EnumUtil.getText((Integer) value, (Class<? extends EnumMessage>) ClassLoaderUtil.loadClass(header.getEnumClassName()));
                    }
                    //处理LocalDateTime
                    if (value instanceof LocalDateTime) {
                        value = LocalDateTimeUtil.format((LocalDateTime)value, "yyyy-MM-dd HH:mm:ss");
                    }
                    //处理LocalDate
                    if (value instanceof LocalDate) {
                        value = LocalDateTimeUtil.format((LocalDate)value, "yyyy-MM-dd");
                    }
                }
                rowMap.put(header.getProperty(), value);
            }
            rowData.add(rowMap);
        }
        dataDTO.setRowData(rowData);
        return dataDTO;
    }

    public static void export(ExportDataDTO exportData, HttpServletResponse response) throws IOException {

        String path = System.getProperty("java.io.tmpdir") + RandomUtil.randomString(32) + ".xlsx";
        File file = new File(path);

        BigExcelWriter writer = cn.hutool.poi.excel.ExcelUtil.getBigWriter(file);
        writeHeader(writer, exportData.getHeaders());
        writer.write(exportData.getRowData());

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");

        response.setHeader("Content-Disposition","attachment;filename="+ exportData.getFileName() +".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();

        IoUtil.close(out);
        file.deleteOnExit();
    }

    public static void writeHeader(BigExcelWriter writer, List<ExcelHeader> headers) {
        for (ExcelHeader header : headers) {
            writer.addHeaderAlias(header.getProperty(), header.getTitle());
        }
    }

    private static List<ExcelHeader> getHeader(Class clazz) {
        List<ExcelHeader> headers = new ArrayList<>();
        for (Field filed : clazz.getDeclaredFields()) {
            ExcelHeader header = new ExcelHeader();
            ApiModelProperty apiModelProperty = AnnotationUtil.getAnnotation(filed, ApiModelProperty.class);
            if (apiModelProperty != null) {
                if (apiModelProperty.hidden()) {
                    continue;
                } else {
                    header.setIdx(apiModelProperty.position()!=0 ? apiModelProperty.position() : 99);
                    header.setTitle(StringUtils.isNotEmpty(apiModelProperty.value()) ? apiModelProperty.value() : filed.getName());
                    header.setProperty(filed.getName());
                }
            }
            ExportProperty exportProperty = AnnotationUtil.getAnnotation(filed, ExportProperty.class);
            if (exportProperty != null) {
                if (exportProperty.hide()) {
                    continue;
                } else {
                    header.setTitle(StringUtils.isNotEmpty(exportProperty.value()) ? exportProperty.value() : header.getTitle());
                    header.setIdx(exportProperty.position()!=0 ? exportProperty.position() : header.getIdx());
                    header.setEnumClassName(StringUtils.isNotEmpty(exportProperty.enumFullName()) ? exportProperty.enumFullName() : null);
                }
            }
            headers.add(header);
        }
        Collections.sort(headers, (o1, o2) -> o1.getIdx()!=null && o2.getIdx()!=null && o1.getIdx()>o2.getIdx() ? 1 : -1);
        return headers;
    }


}
