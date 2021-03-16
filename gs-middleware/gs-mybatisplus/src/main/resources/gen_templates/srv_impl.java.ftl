package ${cfg.srvImplPackage};

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import ${cfg.entityPackage}.${entity};
import ${cfg.daoPackage}.I${entity}Repository;
import ${cfg.srvPackage}.I${cfg.pch5?upper_case}${cfg.clientName}${entity}Service;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import ${cfg.dtoPackage}.${cfg.pch5?upper_case}${cfg.clientName}${entity}DTO;
import ${cfg.qtoPackage}.${cfg.pch5?upper_case}${cfg.clientName}${entity}QTO;
import ${cfg.voPackage}.${cfg.pch5?upper_case}${cfg.clientName}${entity}VO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;


/**
* <p>
*  服务实现类
* </p>
* @author ${author}
* @since ${date}
*/
@Component
public class ${cfg.pch5?upper_case}${cfg.clientName}${entity}ServiceImpl implements I${cfg.pch5?upper_case}${cfg.clientName}${entity}Service {

    @Autowired
    private I${entity}Repository repository;

    @Override
    public PageData<${cfg.pch5?upper_case}${cfg.clientName}${entity}VO.ListVO> pageData(${cfg.pch5?upper_case}${cfg.clientName}${entity}QTO.QTO qto) {
        QueryWrapper<${entity}> wrapper = new QueryWrapper<>();
        IPage<${entity}> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, ${cfg.pch5?upper_case}${cfg.clientName}${entity}VO.ListVO.class, page);
    }

    @Override
    public void add${entity}(${cfg.pch5?upper_case}${cfg.clientName}${entity}DTO.ETO eto) {
        ${entity} ${entity?uncap_first} = new ${entity}();
        BeanUtils.copyProperties(eto, ${entity?uncap_first});
        repository.save(${entity?uncap_first});
    }


    @Override
    public void delete${entity}(${cfg.pch5?upper_case}${cfg.clientName}${entity}DTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
<#if cfg.delBatch! =="YES">
    @Override
    public void deleteBatch${entity}(${cfg.pch5?upper_case}${cfg.clientName}${entity}DTO.IdListDTO dto) {
        repository.removeByIds(dto.getIdList());
    }
</#if>
    @Override
    public void edit${entity}(${cfg.pch5?upper_case}${cfg.clientName}${entity}DTO.ETO eto) {
        ${entity} ${entity?uncap_first} = new ${entity}();
        BeanUtils.copyProperties(eto, ${entity?uncap_first});
        repository.updateById(${entity?uncap_first});
    }

    @Override
    public ${cfg.pch5?upper_case}${cfg.clientName}${entity}VO.DetailVO detail${entity}(${cfg.pch5?upper_case}${cfg.clientName}${entity}DTO.IdDTO dto) {
        ${entity} ${entity?uncap_first} = repository.getById(dto.getId());
        ${cfg.pch5?upper_case}${cfg.clientName}${entity}VO.DetailVO detailVo = new ${cfg.pch5?upper_case}${cfg.clientName}${entity}VO.DetailVO();
        if(ObjectUtils.isEmpty(${entity?uncap_first})){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(${entity?uncap_first}, detailVo);
        return detailVo;
    }

}
