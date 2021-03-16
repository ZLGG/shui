package com.gs.lshly.biz.support.user.service.bbb.h5;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserFrequentGoodsV2DTO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserFrequentGoodsV2QTO;

public interface IBbbH5UserFrequentGoodsV2Service {

    PageData<BbbH5GoodsInfoVO.InnerServiceVO> pageData(BbbH5UserFrequentGoodsV2QTO.QTO qto);

    void addMore(BbbH5UserFrequentGoodsV2DTO.ETO eto);

    void addOne(BbbH5UserFrequentGoodsV2DTO.OneETO dto);

    void deleteUserFrequentGoods(BbbH5UserFrequentGoodsV2DTO.IdDTO dto);

    void deleteBatch(BbbH5UserFrequentGoodsV2DTO.IdListDTO dto);

}