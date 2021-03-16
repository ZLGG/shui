package com.gs.lshly.rpc.api.bbb.h5.user;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserFrequentGoodsV2DTO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserFrequentGoodsV2QTO;

/**
*常购清单
* @author xxfc
* @since 2020-12-10
*/
public interface IBbbH5UserFrequentV2Rpc {

    PageData<BbbH5GoodsInfoVO.InnerServiceVO> pageData(BbbH5UserFrequentGoodsV2QTO.QTO qto);

    void addMore(BbbH5UserFrequentGoodsV2DTO.ETO eto);

    void addOne(BbbH5UserFrequentGoodsV2DTO.OneETO dto);

    void deleteUserFrequentGoods(BbbH5UserFrequentGoodsV2DTO.IdDTO dto);

    void deleteBatch(BbbH5UserFrequentGoodsV2DTO.IdListDTO dto);

}