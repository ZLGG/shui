package com.lakala.boss.api.entity.request;

import com.lakala.boss.api.entity.response.StandardAPDownLoadFileResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created with IntelliJ IDEA.
 * User: gtli
 * Date: 2019-11-20
 * Time: 14:53
 * Description: No Description
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class StandardAPDownLoadFileRequest extends BaseRequest<StandardAPDownLoadFileResponse> {

    /**
     * 业务类型
     */
    private String service = "StandardAPDownLoadFile";

    /**
     * 对账文件下载申请日期
     */
    private String acDate;

    @Override
    public Class<StandardAPDownLoadFileResponse> getResponseClass() {
        return StandardAPDownLoadFileResponse.class;
    }
}
