package com.gs.lshly.rpc.api.common;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.log.AccessLogDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SysAccessLogQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SysAccessLogVO;

/**
 * 日志服务
 * @author lxus
 * @since 2020-12-20
 */
public interface IAccessLogRpc {

    void save(AccessLogDTO dto);

    PageData<SysAccessLogVO.MerchantLogin> merchantLoginLogs(SysAccessLogQTO.MerchantLogin qto);

    void delete(SysAccessLogQTO.IdDTO dto);

    PageData<SysAccessLogVO.OperatorLogin> operatorLoginLogs(SysAccessLogQTO.Operator qto);

    PageData<SysAccessLogVO.Operator> operatorWithoutLoginLogs(SysAccessLogQTO.Operator qto);

    ExportDataDTO export(SysAccessLogQTO.Operator qto);

    PageData<SysAccessLogVO.UserLogin> userLoginLogs(SysAccessLogQTO.UserLogin qto);
}
