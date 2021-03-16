package ${cfg.rpcPackage};
import com.gs.lshly.common.response.PageData;
import ${cfg.dtoPackage}.${cfg.pch5?upper_case}${cfg.clientName}${entity}DTO;
import ${cfg.qtoPackage}.${cfg.pch5?upper_case}${cfg.clientName}${entity}QTO;
import ${cfg.voPackage}.${cfg.pch5?upper_case}${cfg.clientName}${entity}VO;

/**
*
* @author ${author}
* @since ${date}
*/
public interface I${cfg.pch5?upper_case}${cfg.clientName}${entity}Rpc {

    PageData<${cfg.pch5?upper_case}${cfg.clientName}${entity}VO.ListVO> pageData(${cfg.pch5?upper_case}${cfg.clientName}${entity}QTO.QTO qto);

    void add${entity}(${cfg.pch5?upper_case}${cfg.clientName}${entity}DTO.ETO eto);

    void delete${entity}(${cfg.pch5?upper_case}${cfg.clientName}${entity}DTO.IdDTO dto);

<#if cfg.delBatch! =="YES">

    void deleteBatch${entity}(${cfg.pch5?upper_case}${cfg.clientName}${entity}DTO.IdListDTO dto);
</#if>

    void edit${entity}(${cfg.pch5?upper_case}${cfg.clientName}${entity}DTO.ETO eto);

    ${cfg.pch5?upper_case}${cfg.clientName}${entity}VO.DetailVO detail${entity}(${cfg.pch5?upper_case}${cfg.clientName}${entity}DTO.IdDTO dto);

}