package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SysSmDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SysSmTemplateDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SysSmTemplateVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SysSmVO;
import com.gs.lshly.rpc.api.platadmin.foundation.ISysSmRpc;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISysSmService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author Starry
* @since 2021-03-20
*/
@DubboService
public class SysSmRpc implements ISysSmRpc{
    @Autowired
    private ISysSmService  SysSmService;


    @Override
    public List<SysSmVO.ListVO> listSysSM(BaseDTO dto) {
        return SysSmService.listSysSM(dto);
    }

    @Override
    public void saveSysSm(SysSmDTO.ETO eto) {

        SysSmService.saveSysSm(eto);
    }

    @Override
    public void deleteSysSm(SysSmDTO.IdDTO dto) {

        SysSmService.deleteSysSm(dto);
    }

    @Override
    public void saveSysSmTemplate(SysSmTemplateDTO.ETO eto) {

        SysSmService.saveSysSmTemplate(eto);
    }

    @Override
    public void deleteSysSmTemplate(SysSmTemplateDTO.IdDTO dto) {

        SysSmService.deleteSysSmTemplate(dto);
    }

    @Override
    public PageData<SysSmTemplateVO.DetailVO> templatePage(BaseQTO dto) {
        return SysSmService.templatePage(dto);
    }
}