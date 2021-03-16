package com.gs.lshly.biz.support.user.rpc.fy;

import com.gs.lshly.biz.support.user.service.fy.IFyWalletService;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO;
import com.gs.lshly.common.struct.fy.wallet.FyBaseRespDTO;
import com.gs.lshly.common.struct.fy.wallet.dto.*;
import com.gs.lshly.common.utils.RSAUtil;
import com.gs.lshly.rpc.api.fy.IFyWalletRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/23 下午10:25
 */

@DubboService
public class FyWalletRpc implements IFyWalletRpc {

    @Autowired
    IFyWalletService walletService;

    @Value("${wallet.gongHuiPublicKey}")
    private String gongHuiPublicKey;

    @Override
    public FyBaseRespDTO<AccountConsumeRespDTO> accountConsume(AccountConsumeParamsDTO.ETO eto) {
        return walletService.accountConsume(eto);
    }

    @Override
    public FyBaseRespDTO<QueryAccountTradeRespDTO> queryAccountTrade(QueryAccountTradeParamsDTO dto) {
        return walletService.queryAccountTrade(dto);
    }

    @Override
    public FyBaseRespDTO<QueryAccountBalanceRespDTO> queryAccountBalance(BaseDTO dto) {
        return walletService.queryAccountBalance(dto);
    }

    @Override
    public FyBaseRespDTO<CancelAccountConsumeRespDTO> cancelAccountConsume(CancelAccountConsumeParamsDTO.ETO eto) {
        return walletService.cancelAccountConsume(eto);
    }

    @Override
    public FyBaseRespDTO<AccountConsumeReturnRespDTO> accountConsumeReturn(AccountConsumeReturnParamsDTO.ETO eto) {
        return walletService.accountConsumeReturn(eto);
    }

    @Override
    public FyBaseRespDTO<QueryConsumeOrCancelOrReturnResultRespDTO> queryConsumeOrCancelOrReturnResult(QueryConsumeOrCancelOrReturnResultParamsDTO dto) {
        return walletService.queryConsumeOrCancelOrReturnResult(dto);
    }

    @Override
    public FyBaseRespDTO<ReplyMsgCodeRespDTO> replyMsgCode(ReplyMsgCodeParamsDTO dto) {
        return walletService.replyMsgCode(dto);
    }

    @Override
    public BbcUserVO.LoginVO syncUserWallet(FyWalletUserDTO.ETO eto) {
        boolean flag = RSAUtil.gongHuiVerifySign(gongHuiPublicKey, eto.getSignStr(), eto.getSign());
        if (!flag) {
            return null;
        }
        return walletService.syncUserWallet(eto);
    }
}
