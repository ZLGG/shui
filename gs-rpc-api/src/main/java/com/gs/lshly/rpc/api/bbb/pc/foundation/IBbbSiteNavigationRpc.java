package com.gs.lshly.rpc.api.bbb.pc.foundation;
import com.gs.lshly.common.struct.bbb.pc.commodity.qto.PCBbbGoodsCategoryQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteNavigationQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteNavigationVO;

/**
*
* @author 陈奇
* @since 2020-10-14
*/
public interface IBbbSiteNavigationRpc {

    /**
     * 首页数据 顶部链接 + 导航菜单 +  轮播图
     * @return
     */
    BbbSiteNavigationVO.HomeVO homeDetail(PCBbbGoodsCategoryQTO.QTO qto);

    /**
     * 专栏数据  顶部链接 + 导航菜单
     * @param qto
     * @return
     */
    BbbSiteNavigationVO.DetailVO subjectDetail(BbbSiteNavigationQTO.BQTO qto);
}