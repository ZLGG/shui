package com.gs.lshly.common.struct.log;

import com.gs.lshly.common.struct.BaseDTO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lxus
 */
@Data
@Accessors(chain = true)
public class AccessLogDTO extends BaseDTO {

    String module;

    String func;

    String description;

    String className;

    String method;

    String requestUri;

    String userAgent;

    String params;

    String remoteAddr;

    Boolean success;

    String exception;

    Long useTime;

}
