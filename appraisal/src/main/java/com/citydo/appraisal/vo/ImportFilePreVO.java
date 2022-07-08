package com.citydo.appraisal.vo;

import com.citydo.appraisal.request.ImportUserRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zlg
 * @Description
 * @Date 2022/6/29 9:26
 */
@Data
public class ImportFilePreVO {


    @ApiModelProperty(value = "文件类型")
    private String excelType;

    @ApiModelProperty(value = "异常数据信息")
    private ErrorMsg errorMsg;

    @ApiModelProperty(value = "文件url")
    private String fileUrl;

    @ApiModelProperty(value = "预览数据")
    private List<ImportUserRequest> importList;


    @Data
    public static class ErrorMsg{

        @ApiModelProperty(value = "异常数据条数")
        private Long errorCount;

        @ApiModelProperty(value = "正常数据条数")
        private Long normalCount;

        @ApiModelProperty(value = "异常数据信息")
        private List<String> errorMsg;
    }


}
