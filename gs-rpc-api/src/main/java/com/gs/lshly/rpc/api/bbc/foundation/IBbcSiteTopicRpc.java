package com.gs.lshly.rpc.api.bbc.foundation;
import java.util.List;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteTopicQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteTopicVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;

/**
 * 
 * 
 * 
 * @author yingjun
 * @date 2021年3月12日 下午2:01:46
 */
public interface IBbcSiteTopicRpc {

	
    List<BbcSiteTopicVO.CategoryListVO> list(BbcSiteTopicQTO.QTO qto);
    
    
    PageData<GoodsInfoVO.DetailVO> pageEnjoy(BbcSiteTopicQTO.EnjoyQTO qto);
    
    
    /**
     * 获取积分首页的配置
     * @return
     */
    List<BbcSiteTopicVO.CategoryListVO> listPointHome(BbcSiteTopicQTO.QTO qto);
    
    /**
     * 
     * @param topicId
     * @return
     */
    BbcSiteTopicVO.CategoryListVO topicGoods(String topicId);
    
    /**
     * 查询当前分类更多
     * @param qto
     * @return
     */
    BbcSiteTopicVO.GoodsVO pageMore(BbcSiteTopicQTO.SearchmoreQTO qto);
    
    /**
     * 获取电信产品分类下商品列表信息
     * @param id
     * @return
     */
    List<BbcGoodsInfoVO.SimpleListVO> listGoodsInfo(String id);
    
    
}