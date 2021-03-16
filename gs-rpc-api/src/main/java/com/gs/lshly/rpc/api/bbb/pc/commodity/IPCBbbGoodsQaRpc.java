package com.gs.lshly.rpc.api.bbb.pc.commodity;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.commodity.dto.PCBbbGoodsQaDTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.qto.PCBbbGoodsQaQTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsQaVO;

import java.util.Map;

/**
*
* @author Starry
* @since 2020-10-16
*/
public interface IPCBbbGoodsQaRpc {

    PageData<PCBbbGoodsQaVO.ShowListVO> pageData(PCBbbGoodsQaQTO.QTO qto);

    /**
     * 用户咨询信息管理
     * @param qto
     * @return
     */
    PageData<PCBbbGoodsQaVO.UserGoodsQaListVO> pageUserGoodsQa(PCBbbGoodsQaQTO.UserQTO qto);



    void addGoodsQa(PCBbbGoodsQaDTO.ETO eto);

    /**
     * 删除商品咨询
     * @param dto
     */
    void deleteGoodsQa(PCBbbGoodsQaDTO.IdListDTO dto);

    /**
     * 统计每种咨询的数量
     * @param qto
     * @return
     */
    PCBbbGoodsQaVO.CountQuizVO countQuiz(PCBbbGoodsQaQTO.GoodsIdQTO qto);


}