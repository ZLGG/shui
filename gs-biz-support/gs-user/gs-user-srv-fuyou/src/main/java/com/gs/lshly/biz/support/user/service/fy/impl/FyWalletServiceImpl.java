package com.gs.lshly.biz.support.user.service.fy.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.user.entity.User;
import com.gs.lshly.biz.support.user.entity.UserWallet;
import com.gs.lshly.biz.support.user.repository.IUserRepository;
import com.gs.lshly.biz.support.user.repository.IUserWalletRepository;
import com.gs.lshly.biz.support.user.service.bbc.impl.BbcUserAuthServiceImpl;
import com.gs.lshly.biz.support.user.service.fy.IFyWalletService;
import com.gs.lshly.biz.support.user.service.platadmin.IUserService;
import com.gs.lshly.biz.support.user.service.utils.FupinUtil;
import com.gs.lshly.biz.support.user.service.utils.HttpUtil;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO;
import com.gs.lshly.common.struct.fy.wallet.FyBaseDTO;
import com.gs.lshly.common.struct.fy.wallet.FyBaseRespDTO;
import com.gs.lshly.common.struct.fy.wallet.dto.*;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeDTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeListVO;
import com.gs.lshly.common.struct.platadmin.user.dto.UserDTO;
import com.gs.lshly.common.struct.platadmin.user.vo.UserVO;
import com.gs.lshly.common.utils.RSAUtil;
import com.gs.lshly.rpc.api.fy.IFyPayRpc;
import com.gs.lshly.rpc.api.platadmin.trade.ITradeRpc;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Random;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/22 下午3:01
 */
@Component
@Slf4j
public class FyWalletServiceImpl implements IFyWalletService {

    @Value("${wallet.privateKey}")
    private String privateKey;

    @Value("${wallet.publicKey}")
    private String publicKey;

    @Value("${wallet.fuYouPublicKey}")
    private String fuYouPublicKey;

    @Value("${wallet.requestUrl}")
    private String requestUrl;

    @Value("${wallet.fuMerchantNum}")
    private String fuMerchantNum;

    @Value("${wallet.merchantNo}")
    private String merchantNo;

    @DubboReference
    private ITradeRpc tradeRpc;

    @DubboReference
    private IFyPayRpc fyPayRpc;

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IUserWalletRepository userWalletRepository;

    @Override
    public FyBaseRespDTO<AccountConsumeRespDTO> accountConsume(AccountConsumeParamsDTO.ETO eto) {
        if (StringUtils.isBlank(eto.getSenderSsn())) {
            throw new BusinessException("请求流水号不能为空");
        }

        if (StringUtils.isBlank(eto.getOrderNo())) {
            throw new BusinessException("消费订单号不能为空");
        }

        if (StringUtils.isBlank(eto.getSettleTime())) {
            throw new BusinessException("业务日期不能为空");
        }

        AccountConsumeParamsDTO.DTO dto = new AccountConsumeParamsDTO.DTO();
        TradeDTO.IdDTO tradeIdDto = new TradeDTO.IdDTO(eto.getOrderNo());
        TradeListVO.tradeVO tradeVO = tradeRpc.detail(tradeIdDto);
        BeanUtils.copyProperties(eto, dto);
        dto.setFuMerchantNum(fuMerchantNum);
        dto.setMerchantNo(merchantNo);


        UserWallet userWallet = userWalletRepository.getOne(new QueryWrapper<UserWallet>().eq("open_id", eto.getJwtUserId()));
        if (null == userWallet) {
            throw new BusinessException("钱包账号不存在");
        }
        dto.setAccountNo(userWallet.getAccountNo());

        dto.setSettleTime(FupinUtil.getCurrentDate());
        dto.setFreeAmt("0.00");
        dto.setPayAmt(tradeVO.getTradeAmount().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        dto.setTransAmt(tradeVO.getTradeAmount().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        dto.setTradeType("BS0012");

        FyBaseDTO<AccountConsumeParamsDTO.DTO> reqDto = new FyBaseDTO<>();
        reqDto.setAction("accountConsume");
        reqDto.setObj(dto);
        String objData = JSON.toJSONString(dto);
        String respContext = postService(reqDto, objData);
        FyBaseRespDTO<AccountConsumeRespDTO> respDTO = JSON.parseObject(respContext, FyBaseRespDTO.class);
        if (!verify(respContext, respDTO.getSignature())) {
            throw new BusinessException("支付失败");
        }
        if ("0000".equalsIgnoreCase(respDTO.getRespCode())) {
            fyPayRpc.payEditState(tradeVO.getId());
        }
        return respDTO;
    }

    @Override
    public FyBaseRespDTO<QueryAccountTradeRespDTO> queryAccountTrade(QueryAccountTradeParamsDTO dto) {
        if (StringUtils.isBlank(dto.getSenderSsn())) {
            throw new BusinessException("请求流水号不能为空");
        }

        if (StringUtils.isBlank(dto.getStartDate())) {
            throw new BusinessException("开始日期不能为空");
        }

        if (StringUtils.isBlank(dto.getEndDate())) {
            throw new BusinessException("结束日期不能为空");
        }

        String querySn = "QU" + System.currentTimeMillis() + new Random().nextInt(9999);
        dto.setSenderSsn(querySn);
        dto.setFuMerchantNum(fuMerchantNum);
        dto.setTradeType("BS0012");

        FyBaseDTO<QueryAccountTradeParamsDTO> reqDto = new FyBaseDTO<>();
        reqDto.setAction("queryAccountTrade");
        reqDto.setObj(dto);
        String objData = JSON.toJSONString(dto);
        String respContext = postService(reqDto, objData);
        FyBaseRespDTO<QueryAccountTradeRespDTO> respDTO = JSON.parseObject(respContext, FyBaseRespDTO.class);
        if (!verify(respContext, respDTO.getSignature())) {
            throw new BusinessException("查询失败");
        }
        return respDTO;
    }

    @Override
    public FyBaseRespDTO<QueryAccountBalanceRespDTO> queryAccountBalance(BaseDTO dto) {

        QueryAccountBalanceParamsDTO queryAccountBalanceParamsDTO = new QueryAccountBalanceParamsDTO();

        String querySn = "QUERY" + System.currentTimeMillis() + new Random().nextInt(9999);
        queryAccountBalanceParamsDTO.setSenderSsn(querySn);
        queryAccountBalanceParamsDTO.setFuMerchantNum(fuMerchantNum);
        queryAccountBalanceParamsDTO.setAccountType("V");

        UserWallet userWallet = userWalletRepository.getOne(new QueryWrapper<UserWallet>().eq("open_id", dto.getJwtUserId()));
        if (null == userWallet) {
            throw new BusinessException("钱包账号不存在");
        }
        queryAccountBalanceParamsDTO.setSubAccNo(userWallet.getAccountNo());

        if (StringUtils.isBlank(queryAccountBalanceParamsDTO.getSubAccNo())) {
            throw new BusinessException("二二三类户卡号不能为空");
        }

        FyBaseDTO<QueryAccountBalanceParamsDTO> reqDto = new FyBaseDTO<>();
        reqDto.setAction("queryAccountBalance");
        reqDto.setObj(queryAccountBalanceParamsDTO);
        String objData = JSON.toJSONString(queryAccountBalanceParamsDTO);
        String respContext = postService(reqDto, objData);
        FyBaseRespDTO<QueryAccountBalanceRespDTO> respDTO = JSON.parseObject(respContext, FyBaseRespDTO.class);
        if (!verify(respContext, respDTO.getSignature())) {
            throw new BusinessException("查询失败");
        }
        return respDTO;
    }

    @Override
    public FyBaseRespDTO<CancelAccountConsumeRespDTO> cancelAccountConsume(CancelAccountConsumeParamsDTO.ETO eto) {
        if (StringUtils.isBlank(eto.getSenderSsn())) {
            throw new BusinessException("请求流水号不能为空");
        }

        if (StringUtils.isBlank(eto.getOrderNo())) {
            throw new BusinessException("消费订单号不能为空");
        }

        if (StringUtils.isBlank(eto.getSettleTime())) {
            throw new BusinessException("业务日期不能为空");
        }

        CancelAccountConsumeParamsDTO.DTO dto = new CancelAccountConsumeParamsDTO.DTO();
        BeanUtils.copyProperties(eto, dto);

        TradeDTO.IdDTO tradeIdDto = new TradeDTO.IdDTO(eto.getOrderNo());
        TradeListVO.tradeVO tradeVO = tradeRpc.detail(tradeIdDto);

        String querySn = "QU" + System.currentTimeMillis() + new Random().nextInt(9999);
        dto.setSenderSsn(querySn);

        dto.setFuMerchantNum(fuMerchantNum);
        dto.setMerchantNo(merchantNo);

        UserWallet userWallet = userWalletRepository.getOne(new QueryWrapper<UserWallet>().eq("open_id", eto.getJwtUserId()));
        if (null == userWallet) {
            throw new BusinessException("钱包账号不存在");
        }
        dto.setAccountNo(userWallet.getAccountNo());
        dto.setSettleTime(FupinUtil.getCurrentDate());
        dto.setExplain("撤销消费");
        dto.setTradeType("BS0013");

        dto.setTransAmt(tradeVO.getTradeAmount().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        dto.setPayAmt(tradeVO.getTradeAmount().setScale(2, BigDecimal.ROUND_HALF_UP).toString());

        FyBaseDTO<CancelAccountConsumeParamsDTO.DTO> reqDto = new FyBaseDTO<>();
        reqDto.setAction("cancelAccountConsume");
        reqDto.setObj(dto);
        String objData = JSON.toJSONString(eto);
        String respContext = postService(reqDto, objData);
        FyBaseRespDTO<CancelAccountConsumeRespDTO> respDTO = JSON.parseObject(respContext, FyBaseRespDTO.class);
        if (!verify(respContext, respDTO.getSignature())) {
            throw new BusinessException("撤销失败");
        }
        return respDTO;
    }

    @Override
    public FyBaseRespDTO<AccountConsumeReturnRespDTO> accountConsumeReturn(AccountConsumeReturnParamsDTO.ETO eto) {
        if (StringUtils.isBlank(eto.getSenderSsn())) {
            throw new BusinessException("请求流水号不能为空");
        }

        if (StringUtils.isBlank(eto.getOrderNo())) {
            throw new BusinessException("消费订单号不能为空");
        }

        if (StringUtils.isBlank(eto.getSettleTime())) {
            throw new BusinessException("业务日期不能为空");
        }

        AccountConsumeReturnParamsDTO.DTO dto = new AccountConsumeReturnParamsDTO.DTO();
        BeanUtils.copyProperties(eto, dto);

        String querySn = "CCL" + System.currentTimeMillis() + new Random().nextInt(9999);
        dto.setSenderSsn(querySn);

        dto.setFuMerchantNum(fuMerchantNum);
        dto.setMerchantNo(merchantNo);

        UserWallet userWallet = userWalletRepository.getOne(new QueryWrapper<UserWallet>().eq("open_id", eto.getJwtUserId()));
        if (null == userWallet) {
            throw new BusinessException("钱包账号不存在");
        }
        dto.setAccountNo(userWallet.getAccountNo());
        dto.setSettleTime(FupinUtil.getCurrentDate());
        dto.setExplain("撤销消费");
        dto.setTradeType("BS0013");

        TradeDTO.IdDTO tradeIdDto = new TradeDTO.IdDTO(eto.getOrderNo());
        TradeListVO.tradeVO tradeVO = tradeRpc.detail(tradeIdDto);

        dto.setTransAmt(tradeVO.getTradeAmount().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        dto.setPayAmt(tradeVO.getTradeAmount().setScale(2, BigDecimal.ROUND_HALF_UP).toString());

        FyBaseDTO<AccountConsumeReturnParamsDTO.DTO> reqDto = new FyBaseDTO<>();
        reqDto.setAction("accountConsumeReturn");
        reqDto.setObj(dto);
        String objData = JSON.toJSONString(eto);
        String respContext = postService(reqDto, objData);
        FyBaseRespDTO<AccountConsumeReturnRespDTO> respDTO = JSON.parseObject(respContext, FyBaseRespDTO.class);
        if (!verify(respContext, respDTO.getSignature())) {
            throw new BusinessException("撤销失败");
        }
        return respDTO;
    }

    @Override
    public FyBaseRespDTO<QueryConsumeOrCancelOrReturnResultRespDTO> queryConsumeOrCancelOrReturnResult(QueryConsumeOrCancelOrReturnResultParamsDTO dto) {
        if (StringUtils.isBlank(dto.getSenderSsn())) {
            throw new BusinessException("请求流水号不能为空");
        }
        if (StringUtils.isBlank(dto.getOrFlowNo())) {
            throw new BusinessException("原交易流水不能为空");
        }
        if (StringUtils.isBlank(dto.getTransCode())) {
            throw new BusinessException("交易码不能为空");
        }

        String querySn = "CCLRT" + System.currentTimeMillis() + new Random().nextInt(9999);
        dto.setSenderSsn(querySn);
        dto.setFuMerchantNum(fuMerchantNum);

        FyBaseDTO<QueryConsumeOrCancelOrReturnResultParamsDTO> reqDto = new FyBaseDTO<>();
        reqDto.setAction("queryConsumeOrCancelOrReturnResult");
        reqDto.setObj(dto);
        String objData = JSON.toJSONString(dto);
        String respContext = postService(reqDto, objData);
        FyBaseRespDTO<QueryConsumeOrCancelOrReturnResultRespDTO> respDTO = JSON.parseObject(respContext, FyBaseRespDTO.class);
        if (!verify(respContext, respDTO.getSignature())) {
            throw new BusinessException("查询失败");
        }
        return respDTO;
    }

    @Override
    public FyBaseRespDTO<ReplyMsgCodeRespDTO> replyMsgCode(ReplyMsgCodeParamsDTO dto) {
        if (StringUtils.isBlank(dto.getSenderSsn())) {
            throw new BusinessException("请求流水号不能为空");
        }
        if (StringUtils.isBlank(dto.getOrgSenderSsn())) {
            throw new BusinessException("原消费订单流水号不能为空");
        }
        if (StringUtils.isBlank(dto.getSmsCode())) {
            throw new BusinessException("验证码不能为空");
        }

        String querySn = "MSG" + System.currentTimeMillis() + new Random().nextInt(9999);
        dto.setSenderSsn(querySn);
        dto.setFuMerchantNum(fuMerchantNum);

        FyBaseDTO<ReplyMsgCodeParamsDTO> reqDto = new FyBaseDTO<>();
        reqDto.setAction("replyMsgCode");
        reqDto.setObj(dto);
        String objData = JSON.toJSONString(dto);
        String respContext = postService(reqDto, objData);
        FyBaseRespDTO<ReplyMsgCodeRespDTO> respDTO = JSON.parseObject(respContext, FyBaseRespDTO.class);
        if (!verify(respContext, respDTO.getSignature())) {
            throw new BusinessException("支付失败");
        }

        if ("0000".equalsIgnoreCase(respDTO.getRespCode())) {

            TradeDTO.IdDTO tradeIdDto = new TradeDTO.IdDTO(dto.getOrgSenderSsn());
            TradeListVO.tradeVO tradeVO = tradeRpc.detail(tradeIdDto);
            fyPayRpc.payEditState(tradeVO.getId());
        }

        return respDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BbcUserVO.LoginVO syncUserWallet(FyWalletUserDTO.ETO eto) {
        UserDTO.IdDTO dto = new UserDTO.IdDTO(eto.getOpenId());
        UserVO.MiniVO miniVO = userService.mini(dto);
        if (miniVO == null) {
            UserDTO.AddETO addETO = new UserDTO.AddETO();
            addETO.setId(eto.getOpenId());
            addETO.setPhone(eto.getPhone());
            userService.addGongHuiUser(addETO);

            UserWallet insert = new UserWallet();
            insert.setOpenId(eto.getOpenId());
            insert.setAccountNo(eto.getAccountNo());
            userWalletRepository.save(insert);
        } else {
            UserDTO.ETO editETO = new UserDTO.ETO();
            editETO.setId(eto.getOpenId());
            editETO.setPhone(eto.getPhone());
            userService.editorUserInfo(editETO);

            UserWallet userWallet = userWalletRepository.getOne(new QueryWrapper<UserWallet>().eq("open_id", eto.getOpenId()));
            userWallet.setAccountNo(eto.getAccountNo());
            userWalletRepository.updateById(userWallet);
        }
        User user = userRepository.getById(eto.getOpenId());
        if (user != null) {
            return BbcUserAuthServiceImpl.userToLoginVO(user, null);
        } else {
            return null;
        }
    }

    /**
     * 发起请求
     *
     * @param reqDto
     * @param objData
     * @param <T>
     * @return
     */
    private <T> String postService(FyBaseDTO<T> reqDto, String objData) {
        String sign = RSAUtil.sign2Base64(privateKey, objData);
        boolean flag = RSAUtil.verifySign(publicKey, objData, sign);
        reqDto.setSignature(sign);

        String reqContext = JSON.toJSONString(reqDto);
        try {
            String respContext = HttpUtil.doPost(requestUrl, reqContext);
            return respContext;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException("富友接口调用失败");
        }
    }

    /**
     * 验签
     *
     * @param respContext
     * @param sign
     * @return
     */
    private boolean verify(String respContext, String sign) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(respContext, Feature.OrderedField);
            String srcData = jsonObject.getString("obj");
            return RSAUtil.verifySign(fuYouPublicKey, srcData, sign);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException("支付失败，验签失败");
        }
    }
}
