package com.gs.lshly.biz.support.user.rpc.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.merchadmin.pc.user.dto.PCMerchUserDTO;
import com.gs.lshly.common.struct.merchadmin.pc.user.qto.PCMerchUserQTO;
import com.gs.lshly.common.struct.merchadmin.pc.user.vo.PCMerchUserVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.user.IPCMerchUserRpc;
import com.gs.lshly.biz.support.user.service.merchadmin.pc.IPCMerchUserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-20
*/
@DubboService
public class PCMerchUserRpc implements IPCMerchUserRpc{

    @Autowired
    private IPCMerchUserService  pCMerchUserService;

    @Override
    public PageData<PCMerchUserVO.ListVO> pageData(PCMerchUserQTO.QTO qto){
        return pCMerchUserService.pageData(qto);
    }

    @Override
    public PageData<PCMerchUserVO.ApplyListVO> applyPageList(PCMerchUserQTO.ApplyListQTO qto) {
        return pCMerchUserService.applyPageList(qto);
    }

    @Override
    public PCMerchUserVO.ApplyPrivateUserDetailVO applyDetailUser(PCMerchUserDTO.IdDTO dto) {
        return  pCMerchUserService.applyDetailUser(dto);
    }

    @Override
    public void stopBatchUser(PCMerchUserDTO.IdListDTO dto) {
        pCMerchUserService.stopBatchUser(dto);
    }

    @Override
    public void beginBatchUser(PCMerchUserDTO.IdListDTO dto) {
        pCMerchUserService.beginBatchUser(dto);
    }

    @Override
    public void setUserType(PCMerchUserDTO.UserTypeDTO dto) {
        pCMerchUserService.setUserType(dto);
    }


    @Override
    public void apply(PCMerchUserDTO.ApplyDTO dto){
        pCMerchUserService.apply(dto);
    }


    @Override
    public PCMerchUserVO.PrivateUserDetailVO detailUser(PCMerchUserDTO.IdDTO dto){
        return  pCMerchUserService.detailUser(dto);
    }

    @Override
    public ExportDataDTO exportUser(PCMerchUserDTO.IdListDTO dto) throws Exception{
        List<PCMerchUserVO.ExportVO> voList =   pCMerchUserService.exportUser(dto);
        return ExcelUtil.treatmentBean(voList, PCMerchUserVO.ExportVO.class);
    }


    @Override
    public PCMerchUserVO.UserSimpleVO innerUserSimple(String userId) {
        return pCMerchUserService.innerUserSimple(userId);
    }




}