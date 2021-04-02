package ${cfg.dtoPackage};
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author ${author}
* @since ${date}
*/
public abstract class ${cfg.pch5?upper_case}${cfg.clientName}${entity}DTO implements Serializable {

    @Data
    @ApiModel("${cfg.pch5?upper_case}${cfg.clientName}${entity}DTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {
    <#list table.fields as field>
    <#if (field.propertyName =="cdate" || field.propertyName =="udate") || field.propertyName =="flag" >
    <#else>
        <#if field.propertyName =="id">
        <#if field.comment!?length gt 0>

        @ApiModelProperty(value = "${field.comment}",hidden = true)
        <#else>

        @ApiModelProperty(value = "ID",hidden = true)
        </#if>
        <#else>

        @ApiModelProperty("${field.comment?if_exists}")
        </#if>
        <#if field.propertyType == "Date">
        private LocalDateTime ${field.propertyName};
        <#else>
        private ${field.propertyType} ${field.propertyName};
        </#if>
    </#if>
    </#list>
    }

    @Data
    @ApiModel("${cfg.pch5?upper_case}${cfg.clientName}${entity}DTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
    <#list table.fields as field>
    <#if field.propertyName == "id">
        <#if field.comment!?length gt 0>

        @ApiModelProperty(value = "${field.comment}")
        </#if>
        private ${field.propertyType} ${field.propertyName};
    </#if>
    </#list>
    }

<#if cfg.delBatch! =="YES">
    @Data
    @ApiModel("${cfg.pch5?upper_case}${cfg.clientName}${entity}DTO.IdListDTO")
    @AllArgsConstructor
    public static class IdListDTO extends BaseDTO {

    <#list table.fields as field>
        <#if field.propertyName == "id">
            @ApiModelProperty(value = "ID数组")
            private List<${field.propertyType}> idList;
        </#if>
    </#list>
    }
</#if>

}
