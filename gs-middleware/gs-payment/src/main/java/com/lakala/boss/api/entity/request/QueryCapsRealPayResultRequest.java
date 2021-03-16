package com.lakala.boss.api.entity.request;

import com.lakala.boss.api.common.Header;
import com.lakala.boss.api.entity.caps.body.QueryCapsRealPayResultRequestBody;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 代付结果查询请求实体类
 * @author liu_dd
 * @date 2019/12/26 5:03 下午
 * @version 1.0.0
 */
@Data
@Builder
@ToString
public class QueryCapsRealPayResultRequest implements Serializable {

    private static final long serialVersionUID = 8410383158879270501L;
    /**
     * 公共参数
     */
    private Header header;

    /**
     * 业务参数
     */
    private QueryCapsRealPayResultRequestBody body;

}
