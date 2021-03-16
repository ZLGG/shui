package com.gs.lshly.biz.support.foundation.service.common;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.dto.RemindPlatDTO;
import com.gs.lshly.common.struct.common.qto.RemindPlatQTO;
import com.gs.lshly.common.struct.common.vo.RemindPlatVO;

public interface IRemindPlatService {

    PageData<RemindPlatVO.ListVO> pageData(RemindPlatQTO.QTO qto);

    void addRemindPlat(RemindPlatDTO.ETO eto);

    void deleteRemindPlat(RemindPlatDTO.IdDTO dto);

    void editRemindPlat(String id,RemindPlatDTO.ETO eto);

    RemindPlatVO.DetailVO detailRemindPlat(RemindPlatDTO.IdDTO dto);

    void addRemindPlatForUser2bApply(RemindPlatDTO.ETO eto);

    void addRemindPlatForMerchantApply(RemindPlatDTO.ETO eto);

    void addRemindPlatForGoodsUpApply(RemindPlatDTO.ETO eto);

    void addRemindPlatForGoodsCategoryApply(RemindPlatDTO.ETO eto);

    void addRemindPlatForMerchantArticleApply(RemindPlatDTO.ETO eto);

}