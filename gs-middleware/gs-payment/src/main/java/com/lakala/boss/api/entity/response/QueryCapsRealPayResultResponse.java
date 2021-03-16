package com.lakala.boss.api.entity.response;

import com.lakala.boss.api.common.Header;
import com.lakala.boss.api.entity.caps.body.QueryCapsRealPayResultResponseBody;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 代付结果查询响应实体类
 * @author liu_dd
 * @date 2019/12/26 5:04 下午
 * @version 1.0.0
 */
@Data
@ToString
public class QueryCapsRealPayResultResponse implements Serializable {

    private static final long serialVersionUID = -6623809742602050486L;
    /***
     * 公共参数
     */
    private Header header;

    /***
     * 公共参数
     */
    private QueryCapsRealPayResultResponseBody body;
}
