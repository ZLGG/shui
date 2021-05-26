package com.gs.lshly.biz.support.commodity.service.bbc;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsServeDTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsServeQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsServeVO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsServeDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsServeQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsServeVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hanly
 */
public interface IBbcGoodsServeService {

    List<BbcGoodsServeVO.ListVO> getGoodsServeDetail(BbcGoodsServeQTO.GoodsInfoQTO qto);
}
