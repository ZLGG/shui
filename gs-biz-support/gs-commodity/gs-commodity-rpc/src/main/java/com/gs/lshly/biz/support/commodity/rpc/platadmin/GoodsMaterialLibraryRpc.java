package com.gs.lshly.biz.support.commodity.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsMaterialLibraryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsMaterialLibraryQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsCategoryVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsMaterialLibraryVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsMaterialLibraryRpc;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsMaterialLibraryService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author Starry
* @since 2020-12-10
*/
@DubboService
public class GoodsMaterialLibraryRpc implements IGoodsMaterialLibraryRpc{
    @Autowired
    private IGoodsMaterialLibraryService  GoodsMaterialLibraryService;

    @Override
    public PageData<GoodsMaterialLibraryVO.DetailListVO> pageData(GoodsMaterialLibraryQTO.QTO qto){
        return GoodsMaterialLibraryService.pageData(qto);
    }

    @Override
    public void addGoodsMaterialLibrary(GoodsMaterialLibraryDTO.ETO eto){
        GoodsMaterialLibraryService.addGoodsMaterialLibrary(eto);
    }

    @Override
    public void deleteGoodsMaterialLibrary(GoodsMaterialLibraryDTO.IdListDTO dto){
        GoodsMaterialLibraryService.deleteGoodsMaterialLibrary(dto);
    }


    @Override
    public void editGoodsMaterialLibrary(GoodsMaterialLibraryDTO.ETO eto){
        GoodsMaterialLibraryService.editGoodsMaterialLibrary(eto);
    }

    @Override
    public GoodsMaterialLibraryVO.DetailVO detailGoodsMaterialLibrary(GoodsMaterialLibraryDTO.IdDTO dto){
        return  GoodsMaterialLibraryService.detailGoodsMaterialLibrary(dto);
    }

    @Override
    public ExportDataDTO export(GoodsMaterialLibraryDTO.IdListDTO dto) throws Exception {
        return ExcelUtil.treatmentBean(GoodsMaterialLibraryService.exportData(dto), GoodsMaterialLibraryVO.exportDataVO.class);
    }

}