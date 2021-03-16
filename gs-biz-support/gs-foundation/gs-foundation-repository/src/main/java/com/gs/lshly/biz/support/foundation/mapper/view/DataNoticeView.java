package com.gs.lshly.biz.support.foundation.mapper.view;

import com.gs.lshly.biz.support.foundation.entity.DataNotice;
import lombok.Data;

@Data
public class DataNoticeView extends DataNotice {

    private String recvId;

    private String noticeId;

    private Integer state;

    private String shopId;

}
