package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SysMailDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SysMailVO;
import com.gs.lshly.rpc.api.platadmin.foundation.ISysMailRpc;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISysMailService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author Starry
* @since 2021-03-20
*/
@DubboService
public class SysMailRpc implements ISysMailRpc{
    @Autowired
    private ISysMailService  SysMailService;


    @Override
    public void saveSysMail(SysMailDTO.ETO eto) {
        SysMailService.saveSysMail(eto);
    }

    @Override
    public void deleteSysMail(SysMailDTO.IdDTO dto) {
        SysMailService.deleteSysMail(dto);
    }

    @Override
    public PageData<SysMailVO.ListVO> pageData(BaseQTO qto) {
        return SysMailService.pageData(qto);
    }
}