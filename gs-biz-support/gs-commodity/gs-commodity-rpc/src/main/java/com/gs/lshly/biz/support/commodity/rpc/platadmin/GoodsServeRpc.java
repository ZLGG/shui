package com.gs.lshly.biz.support.commodity.rpc.platadmin;

import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsServeService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsServeDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsServeQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsServeVO;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsServeRpc;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author hanly
 * @Date 17:12 2021/5/25
 */
public class GoodsServeRpc implements IGoodsServeRpc {

    @Autowired
    private IGoodsServeService goodsServeService;

    /**
     * 分页查询商品服务列表
     *
     * @param qto
     * @return
     */
    @Override
    public PageData<GoodsServeVO.ListVO> pageGoodsServeData(GoodsServeQTO.QTO qto) {
        return goodsServeService.pageGoodsServeData(qto);
    }

    /**
     * 查询商品详情
     *
     * @param dto
     * @return
     */
    @Override
    public GoodsServeVO.ListVO getGoodsServeDetail(GoodsServeDTO.IdDTO dto) {
        return goodsServeService.getGoodsServeDetail(dto);
    }

    /**
     * 新增服务
     *
     * @param eto
     */
    @Override
    public void addGoodsServe(GoodsServeDTO.ETO eto) {
        goodsServeService.addGoodsServe(eto);
    }

    /**
     * 修改服务信息
     *
     * @param eto
     */
    @Override
    public void editGoodsServe(GoodsServeDTO.ETO eto) {
        goodsServeService.editGoodsServe(eto);
    }

    /**
     * 删除服务
     *
     * @param dto
     */
    @Override
    public void deleteGoodsServe(GoodsServeDTO.IdListDTO dto) {
        goodsServeService.deleteGoodsServe(dto);
    }
}
