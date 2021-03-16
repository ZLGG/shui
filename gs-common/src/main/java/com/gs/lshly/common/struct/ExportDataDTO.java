package com.gs.lshly.common.struct;

import com.gs.lshly.common.excel.ExcelHeader;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author lxus
 */
@Data
public class ExportDataDTO implements Serializable {

    /**
     * 下载文件名称
     */
    private String fileName;

    /**
     * 表头
     */
    private List<ExcelHeader> headers;

    /**
     * 数据
     */
    private List<Map<String, Object>> rowData;


}
