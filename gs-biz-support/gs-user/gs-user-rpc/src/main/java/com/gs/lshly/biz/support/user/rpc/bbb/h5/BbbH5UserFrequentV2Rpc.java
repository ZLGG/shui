package com.gs.lshly.biz.support.user.rpc.bbb.h5;
import com.gs.lshly.biz.support.user.service.bbb.h5.IBbbH5UserFrequentGoodsV2Service;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserFrequentGoodsV2DTO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserFrequentGoodsV2QTO;
import com.gs.lshly.rpc.api.bbb.h5.user.IBbbH5UserFrequentV2Rpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author xxfc
* @since 2020-12-10
*/
@DubboService
public class BbbH5UserFrequentV2Rpc implements IBbbH5UserFrequentV2Rpc {

    @Autowired
    private IBbbH5UserFrequentGoodsV2Service BbbH5UserFrequentGoodsV2Service;


    @Override
    public PageData<BbbH5GoodsInfoVO.InnerServiceVO> pageData(BbbH5UserFrequentGoodsV2QTO.QTO qto) {
        return BbbH5UserFrequentGoodsV2Service.pageData(qto);
    }

    @Override
    public void addMore(BbbH5UserFrequentGoodsV2DTO.ETO eto) {
        BbbH5UserFrequentGoodsV2Service.addMore(eto);
    }

    @Override
    public void addOne(BbbH5UserFrequentGoodsV2DTO.OneETO dto) {
        BbbH5UserFrequentGoodsV2Service.addOne(dto);
    }

    @Override
    public void deleteUserFrequentGoods(BbbH5UserFrequentGoodsV2DTO.IdDTO dto) {
        BbbH5UserFrequentGoodsV2Service.deleteUserFrequentGoods(dto);
    }

    @Override
    public void deleteBatch(BbbH5UserFrequentGoodsV2DTO.IdListDTO dto) {
        BbbH5UserFrequentGoodsV2Service.deleteBatch(dto);
    }
}