package com.gs.lshly.rpc.api.fy;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO;
import com.gs.lshly.common.struct.fy.wallet.FyBaseRespDTO;
import com.gs.lshly.common.struct.fy.wallet.dto.*;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/23 下午10:19
 */
public interface IFyWalletRpc {

    /**
     * 账户消费
     *
     * @param eto
     * @return
     */
    FyBaseRespDTO<AccountConsumeRespDTO> accountConsume(AccountConsumeParamsDTO.ETO eto);

    /**
     * 交易查询
     *
     * @param dto
     * @return
     */
    FyBaseRespDTO<QueryAccountTradeRespDTO> queryAccountTrade(QueryAccountTradeParamsDTO dto);

    /**
     * 查询余额
     *
     * @param dto
     * @return
     */
    FyBaseRespDTO<QueryAccountBalanceRespDTO> queryAccountBalance(BaseDTO dto);

    /**
     * 撤消消费
     *
     * @param eto
     * @return
     */
    FyBaseRespDTO<CancelAccountConsumeRespDTO> cancelAccountConsume(CancelAccountConsumeParamsDTO.ETO eto);

    /**
     * 消费退货
     *
     * @param eto
     * @return
     */
    FyBaseRespDTO<AccountConsumeReturnRespDTO> accountConsumeReturn(AccountConsumeReturnParamsDTO.ETO eto);

    /**
     * 查询消费/撤销/退货结果
     *
     * @param dto
     * @return
     */
    FyBaseRespDTO<QueryConsumeOrCancelOrReturnResultRespDTO> queryConsumeOrCancelOrReturnResult(QueryConsumeOrCancelOrReturnResultParamsDTO dto);

    /**
     * 验证短信验证码
     *
     * @param dto
     * @return
     */
    FyBaseRespDTO<ReplyMsgCodeRespDTO> replyMsgCode(ReplyMsgCodeParamsDTO dto);


    /**
     * 工会用户账号同步
     *
     * @param eto
     */
    BbcUserVO.LoginVO syncUserWallet(FyWalletUserDTO.ETO eto);
}
