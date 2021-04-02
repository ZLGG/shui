package ${cfg.voPackage};
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
public abstract class ${cfg.pch5?upper_case}${cfg.clientName}${entity}VO implements Serializable {

    @Data
    @ApiModel("${cfg.pch5?upper_case}${cfg.clientName}${entity}VO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{
    <#list table.fields as field>
    <#if (field.propertyName =="cdate" || field.propertyName =="udate") || field.propertyName =="flag" >
    <#else>

        @ApiModelProperty("${field.comment?if_exists}")
        <#if field.propertyType == "Date">
        private LocalDateTime ${field.propertyName};
        <#else>
        private ${field.propertyType} ${field.propertyName};
        </#if>
    </#if>

    </#list>
    }

    @Data
    @ApiModel("${cfg.pch5?upper_case}${cfg.clientName}${entity}VO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
