package ${cfg.rpcImplPackage};
import com.gs.lshly.common.response.PageData;
import ${cfg.dtoPackage}.${cfg.pch5?upper_case}${cfg.clientName}${entity}DTO;
import ${cfg.qtoPackage}.${cfg.pch5?upper_case}${cfg.clientName}${entity}QTO;
import ${cfg.voPackage}.${cfg.pch5?upper_case}${cfg.clientName}${entity}VO;
import ${cfg.rpcPackage}.I${cfg.pch5?upper_case}${cfg.clientName}${entity}Rpc;
import ${cfg.srvPackage}.I${cfg.pch5?upper_case}${cfg.clientName}${entity}Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author ${author}
* @since ${date}
*/
@DubboService
public class ${cfg.pch5?upper_case}${cfg.clientName}${entity}Rpc implements I${cfg.pch5?upper_case}${cfg.clientName}${entity}Rpc{
    @Autowired
    private I${cfg.pch5?upper_case}${cfg.clientName}${entity}Service  ${cfg.pch5Case?uncap_first}<#if cfg.pch5==''>${cfg.clientName?uncap_first}<#else>${cfg.clientName}</#if>${entity}Service;

    @Override
    public PageData<${cfg.pch5?upper_case}${cfg.clientName}${entity}VO.ListVO> pageData(${cfg.pch5?upper_case}${cfg.clientName}${entity}QTO.QTO qto){
        return ${cfg.pch5Case?uncap_first}<#if cfg.pch5==''>${cfg.clientName?uncap_first}<#else>${cfg.clientName}</#if>${entity}Service.pageData(qto);
    }

    @Override
    public void add${entity}(${cfg.pch5?upper_case}${cfg.clientName}${entity}DTO.ETO eto){
        ${cfg.pch5Case?uncap_first}<#if cfg.pch5==''>${cfg.clientName?uncap_first}<#else>${cfg.clientName}</#if>${entity}Service.add${entity}(eto);
    }

    @Override
    public void delete${entity}(${cfg.pch5?upper_case}${cfg.clientName}${entity}DTO.IdDTO dto){
        ${cfg.pch5Case?uncap_first}<#if cfg.pch5==''>${cfg.clientName?uncap_first}<#else>${cfg.clientName}</#if>${entity}Service.delete${entity}(dto);
    }

<#if cfg.delBatch! =="YES">
    @Override
    public void deleteBatch${entity}(${cfg.pch5?upper_case}${cfg.clientName}${entity}DTO.IdListDTO dto){
        ${cfg.pch5Case?uncap_first}<#if cfg.pch5==''>${cfg.clientName?uncap_first}<#else>${cfg.clientName}</#if>${entity}Service.deleteBatch${entity}(dto);
    }
</#if>

    @Override
    public void edit${entity}(${cfg.pch5?upper_case}${cfg.clientName}${entity}DTO.ETO eto){
        ${cfg.pch5Case?uncap_first}<#if cfg.pch5==''>${cfg.clientName?uncap_first}<#else>${cfg.clientName}</#if>${entity}Service.edit${entity}(eto);
    }

    @Override
    public ${cfg.pch5?upper_case}${cfg.clientName}${entity}VO.DetailVO detail${entity}(${cfg.pch5?upper_case}${cfg.clientName}${entity}DTO.IdDTO dto){
        return  ${cfg.pch5Case?uncap_first}<#if cfg.pch5==''>${cfg.clientName?uncap_first}<#else>${cfg.clientName}</#if>${entity}Service.detail${entity}(dto);
    }

}