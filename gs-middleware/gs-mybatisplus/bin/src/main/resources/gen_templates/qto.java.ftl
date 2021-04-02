package ${cfg.qtoPackage};
import com.gs.lshly.common.struct.BaseQTO;
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
public abstract class ${cfg.pch5?upper_case}${cfg.clientName}${entity}QTO implements Serializable {

    @Data
    @ApiModel("${cfg.pch5?upper_case}${cfg.clientName}${entity}QTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {
    <#list table.fields as field>
    <#if (field.propertyName =="cdate" || field.propertyName =="udate") || field.propertyName =="flag" || field.propertyName =="id">
    <#else>
        <#if field.propertyName =="id">

        @ApiModelProperty(value = "${field.comment?if_exists}",hidden = true)
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
}
