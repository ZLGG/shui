package com.gs.lshly.rpc.api.bbb.h5.foundation;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5DataArticleVO;
import java.util.List;

/**
*
* @author hyy
* @since 2020-11-13
*/
public interface IBbbH5DataArticleRpc {

    List<BbbH5DataArticleVO.ListVO> list(BaseDTO dto);

}