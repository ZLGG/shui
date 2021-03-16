package com.gs.lshly.biz.support.commodity.service.bbb.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.commodity.dto.PCBbbGoodsQaDTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.qto.PCBbbGoodsQaQTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsQaVO;

import java.util.Map;

public interface IPCBbbGoodsQaService {

    /**
     * 查询商品咨询列表
     * @param qto
     * @return
     */
    PageData<PCBbbGoodsQaVO.ShowListVO> pageData(PCBbbGoodsQaQTO.QTO qto);

    /**
     * 用户咨询信息管理
     * @param qto
     * @return
     */
    PageData<PCBbbGoodsQaVO.UserGoodsQaListVO> pageUserGoodsQa(PCBbbGoodsQaQTO.UserQTO qto);

    /**
     * 进行商品咨询
     * @param eto
     */
    void addGoodsQa(PCBbbGoodsQaDTO.ETO eto);

    /**
     * 统计每种咨询的数量
     * @param qto
     * @return
     */
    PCBbbGoodsQaVO.CountQuizVO countQuiz(PCBbbGoodsQaQTO.GoodsIdQTO qto);

    /**
     * 删除咨询
     * @param dto
     */
    void deleteGoodsQa(PCBbbGoodsQaDTO.IdListDTO dto);

}