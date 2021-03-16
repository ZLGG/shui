package com.gs.lshly.biz.support.commodity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.gs.lshly.biz.support.user.service.fy.IFyWalletService;
import com.gs.lshly.biz.support.user.service.utils.HttpUtil;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.fy.wallet.FyBaseDTO;
import com.gs.lshly.common.struct.fy.wallet.FyBaseRespDTO;
import com.gs.lshly.common.struct.fy.wallet.dto.*;
import com.gs.lshly.common.utils.RSAUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/23 下午2:06
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class JunitFyWalletService {

    @Autowired
    private IFyWalletService fyWalletService;

    @Value("${wallet.privateKey}")
    private String privateKey;

    @Value("${wallet.publicKey}")
    private String publicKey;

    @Value("${wallet.fuYouPublicKey}")
    private String fuYouPublicKey;

    @Value("${wallet.requestUrl}")
    private String requestUrl;

    @Test
    public void TestAccountConsume() {
        String jsonData = "{\n" +
                "        \"senderSsn\": \"FYLRM202011091645157\",\n" +
                "        \"orderNo\": \"FYLRM202011091645157\",\n" +
                "        \"fuMerchantNum\": \"420\",\n" +
                "        \"reqReserved\": \"\",\n" +
                "        \"reserved\": \"\",\n" +
                "        \"codeSeq\": \"\",\n" +
                "        \"messageCode\": \"\",\n" +
                "        \"isCodeCheck\": \"\",\n" +
                "        \"mobile\": \"18921741611\",\n" +
                "        \"deviceName\": \"HUAWEI p40\",\n" +
                "        \"lbsInfo\": \"+123/-34\",\n" +
                "        \"interIpAddress\": \"\",\n" +
                "        \"simCardNumber\": \"\",\n" +
                "        \"deviceBankId\": \"\",\n" +
                "        \"simCarNum\": \"2\",\n" +
                "        \"mac\": \"\",\n" +
                "        \"deviceId\": \"\",\n" +
                "        \"ipAddress\": \"\",\n" +
                "        \"deviceType\": \"\",\n" +
                "        \"ipVersion\": \"04\",\n" +
                "        \"productType\": \"0\",\n" +
                "        \"payAccountNo\": \"\",\n" +
                "        \"collectionAmt\": \"0\",\n" +
                "        \"allowUnidentify\": \"\",\n" +
                "        \"accountNo\": \"11564201001001348255\",\n" +
                "        \"merchantNo\": \"115634200010000761\",\n" +
                "        \"transAmt\": \"11\",\n" +
                "        \"payAmt\": \"11\",\n" +
                "        \"freeAmt\": \"0\",\n" +
                "        \"tradeType\": \"BS0012\",\n" +
                "        \"explain\": \"\",\n" +
                "        \"settleTime\": \"20201228\",\n" +
                "        \"productNo\": \"\",\n" +
                "        \"productName\": \"\"\n" +
                "    }";
        AccountConsumeParamsDTO.DTO dto = JSON.parseObject(jsonData, AccountConsumeParamsDTO.DTO.class);
        dto.setAccountNo("11564201001001349056");
        dto.setMerchantNo("115634200010000761");
        dto.setFuMerchantNum("FU000015");
        dto.setMobile("13899998888");
        long timeMillis = System.currentTimeMillis();
        String orderNo = "FYCN" + timeMillis;
        dto.setSenderSsn(orderNo);
        dto.setOrderNo(orderNo);

        FyBaseRespDTO<AccountConsumeRespDTO> respDTO = fyWalletService.accountConsume(dto);


        System.out.println("=============" + JSON.toJSONString(respDTO));
    }

    @Test
    public void TestQueryAccountTrade() {
        String jsonData = "{\n" +
                "        \"senderSsn\": \"QU202012221415\",\n" +
                "        \"fuMerchantNum\": \"FU000012\",\n" +
                "        \"reqReserved\": \"\",\n" +
                "        \"reserved\": \"\",\n" +
                "        \"transFlowNo\": \"\",\n" +
                "        \"startDate\": \"2020-12-20\",\n" +
                "        \"endDate\": \"2020-12-21\",\n" +
                "        \"pageSize\": \"5\",\n" +
                "        \"pageNo\": \"1\",\n" +
                "        \"tradeType\": \"BS0012\"\n" +
                "    }";
        long orderNo = System.currentTimeMillis();
        QueryAccountTradeParamsDTO dto = JSON.parseObject(jsonData, QueryAccountTradeParamsDTO.class);
        dto.setFuMerchantNum("FU000015");
        dto.setSenderSsn("" + orderNo);
//        dto.setTransFlowNo("FYCN1609138453610");
        dto.setStartDate("2020-12-10");
        dto.setEndDate("2020-12-28");
        FyBaseRespDTO<QueryAccountTradeRespDTO> respDTO = fyWalletService.queryAccountTrade(dto);
        System.out.println("=============" + JSON.toJSONString(respDTO));
    }

    @Test
    public void TestQueryAccountBalance() {
        String jsonData = "{\n" +
                "        \"senderSsn\": \"FY202011091137\",\n" +
                "        \"fuMerchantNum\": \"420\",\n" +
                "        \"reqReserved\": \"\",\n" +
                "        \"reserved\": \"\",\n" +
                "        \"accountType\": \"V\",\n" +
                "        \"businessType\": \"\",\n" +
                "        \"queryType\": \"\",\n" +
                "        \"subAccNo\": \"\"\n" +
                "    }";
        long orderNo = System.currentTimeMillis();
        QueryAccountBalanceParamsDTO dto = JSON.parseObject(jsonData, QueryAccountBalanceParamsDTO.class);
        dto.setFuMerchantNum("FU000015");
        dto.setSenderSsn("" + orderNo);
        dto.setSubAccNo("11564201001001349056");
        FyBaseRespDTO<QueryAccountBalanceRespDTO> respDTO = fyWalletService.queryAccountBalance(dto);
        System.out.println("=============" + JSON.toJSONString(respDTO));

    }

    @Test
    public void TestCancelAccountConsume() {
        String jsonData = "{\n" +
                "        \"senderSsn\": \"FYccl202011161520\",\n" +
                "        \"fuMerchantNum\": \"420\",\n" +
                "        \"reqReserved\": \"\",\n" +
                "        \"reserved\": \"\",\n" +
                "        \"orderNo\": \"FY202011161517\",\n" +
                "        \"accountNo\": \"11564201001001348255\",\n" +
                "        \"merchantNo\": \"115634200010000761\",\n" +
                "        \"transAmt\": \"10\",\n" +
                "        \"payAmt\": \"10\",\n" +
                "        \"freeAmt\": \"0\",\n" +
                "        \"tradeType\": \"BS0013\",\n" +
                "        \"explain\": \"撤销消费\",\n" +
                "        \"settleTime\": \"20201116\",\n" +
                "        \"productNo\": \"\",\n" +
                "        \"productName\": \"\",\n" +
                "        \"productType\": \"\",\n" +
                "        \"payAccountNo\": \"\",\n" +
                "        \"collectionAmt\": \"0\",\n" +
                "        \"allowUnidentify\": \"1\"\n" +
                "    }";
        long orderNo = System.currentTimeMillis();
        CancelAccountConsumeParamsDTO.DTO dto = JSON.parseObject(jsonData, CancelAccountConsumeParamsDTO.DTO.class);
        dto.setFuMerchantNum("FU000015");
        dto.setSenderSsn("" + orderNo);
        dto.setAccountNo("11564201001001349056");
        dto.setMerchantNo("115634200010000761");
        dto.setOrderNo("FYCN1609138453610");
        dto.setSettleTime("20201228");
        FyBaseRespDTO<CancelAccountConsumeRespDTO> respDTO = fyWalletService.cancelAccountConsume(dto);
        System.out.println("=============" + JSON.toJSONString(respDTO));
    }

    @Test
    public void TestAccountConsumeReturn() {
        String jsonData = "{\n" +
                "        \"senderSsn\": \"FYrtn202011161602\",\n" +
                "        \"fuMerchantNum\": \"420\",\n" +
                "        \"reqReserved\": \"\",\n" +
                "        \"reserved\": \"\",\n" +
                "        \"orderNo\": \"FY202011161600\",\n" +
                "        \"accountNo\": \"11564201001001348255\",\n" +
                "        \"merchantNo\": \"115634200010000761\",\n" +
                "        \"transAmt\": \"10\",\n" +
                "        \"payAmt\": \"10\",\n" +
                "        \"freeAmt\": \"0\",\n" +
                "        \"tradeType\": \"BS0024\",\n" +
                "        \"explain\": \"消费退货\",\n" +
                "        \"settleTime\": \"20201116\",\n" +
                "        \"productNo\": \"\",\n" +
                "        \"productName\": \"\",\n" +
                "        \"productType\": \"\",\n" +
                "        \"payAccountNo\": \"\",\n" +
                "        \"collectionAmt\": \"0\",\n" +
                "        \"allowUnidentify\": \"1\"\n" +
                "    }";
        long orderNo = System.currentTimeMillis();
        AccountConsumeReturnParamsDTO.DTO dto = JSON.parseObject(jsonData, AccountConsumeReturnParamsDTO.DTO.class);
        dto.setFuMerchantNum("FU000015");
        dto.setSenderSsn("" + orderNo);
        dto.setAccountNo("11564201001001349056");
        dto.setMerchantNo("115634200010000761");
        dto.setOrderNo("FYCN1609138453610");
        dto.setSettleTime("20201228");
        FyBaseRespDTO<AccountConsumeReturnRespDTO> respDTO = fyWalletService.accountConsumeReturn(dto);
        System.out.println("=============" + JSON.toJSONString(respDTO));
    }

    @Test
    public void TestQueryConsumeOrCancelOrReturnResult() {
        String jsonData = "{\n" +
                "        \"senderSsn\": \"FYQuy202011271546\",\n" +
                "        \"fuMerchantNum\": \"420\",\n" +
                "        \"reqReserved\": \"\",\n" +
                "        \"reserved\": \"\",\n" +
                "        \"orFlowNo\": \"FYHHL202011271545\",\n" +
                "        \"transCode\": \"BS0012\"\n" +
                "    }";
        QueryConsumeOrCancelOrReturnResultParamsDTO dto = JSON.parseObject(jsonData, QueryConsumeOrCancelOrReturnResultParamsDTO.class);
        long orderNo = System.currentTimeMillis();
        dto.setFuMerchantNum("FU000015");
        dto.setSenderSsn("" + orderNo);
        dto.setOrFlowNo("FYCN1609138453610");
        FyBaseRespDTO<QueryConsumeOrCancelOrReturnResultRespDTO> respDTO = fyWalletService.queryConsumeOrCancelOrReturnResult(dto);
        System.out.println("=============" + JSON.toJSONString(respDTO));
    }

    @Test
    public void TestReplyMsgCode() {
        String jsonData = "{\n" +
                "        \"senderSsn\": \"FY202011041141\",\n" +
                "        \"fuMerchantNum\": \"420\",\n" +
                "        \"reqReserved\": \"\",\n" +
                "        \"reserved\": \"\",\n" +
                "        \"orgSenderSsn\": \"FY2020110411412\",\n" +
                "        \"smsCode\": \"104815\",\n" +
                "        \"pageNotifyUrl\": \"http://xxx.xx.xx\",\n" +
                "        \"backNotifyUrl\": \"http://xxx.xx.xx\"\n" +
                "    }";
        ReplyMsgCodeParamsDTO dto = JSON.parseObject(jsonData, ReplyMsgCodeParamsDTO.class);
        long orderNo = System.currentTimeMillis();
        dto.setFuMerchantNum("FU000015");
        dto.setSenderSsn("" + orderNo);
        dto.setOrgSenderSsn("FYCN1609146514502");
        FyBaseRespDTO<ReplyMsgCodeRespDTO> respDTO = fyWalletService.replyMsgCode(dto);
        System.out.println("=============" + JSON.toJSONString(respDTO));
    }

    @Test
    public void TestQueryOccupation() throws Exception {
        String data = "{\n" +
                "    \"obj\": {\n" +
                "        \"fuMerchantNum\": \"FU000015\",\n" +
                "        \"reqReserved\": \"\",\n" +
                "        \"reserved\": \"\",\n" +
                "        \"iType\": \"1\",\n" +
                "        \"senderSsn\": \"FY20200050712345102\"\n" +
                "    },\n" +
                "    \"action\": \"queryOccupation\",\n" +
                "    \"signature\": \"xxxxxxx111111\"\n" +
                "}";

        FyBaseDTO<TestReqBody> reqDTO = JSON.parseObject(data, FyBaseDTO.class);

        TestReqBody obj = new TestReqBody();
        obj.setFuMerchantNum("FU000015");
        obj.setIType("1");
        obj.setReqReserved("");
        obj.setReserved("");
        obj.setSenderSsn("FYCN1609146514502");
        reqDTO.setObj(obj);
        String reqData = JSON.toJSONString(obj);
        String respContext = postService(reqDTO, reqData);

        FyBaseRespDTO<QueryConsumeOrCancelOrReturnResultRespDTO> respDTO = JSON.parseObject(respContext, FyBaseRespDTO.class);
        JSONObject jsonObject = JSONObject.parseObject(respContext, Feature.OrderedField);
        String srcData = jsonObject.getString("obj");
        System.out.println("srcData=" + srcData);
        System.out.println("fuYouPublicKey=" + fuYouPublicKey);
        System.out.println("getSignature=" + respDTO.getSignature());
        if (!RSAUtil.verifySign(fuYouPublicKey, srcData, respDTO.getSignature())) {
            System.out.println("失败失败失败失败失败失败失败失败");
        }

        System.out.println("===respContext===" + respContext);
    }

    private <T> String postService(FyBaseDTO<T> reqDto, String objData) {
        String sign = RSAUtil.sign2Base64(privateKey, objData);
        boolean flag = RSAUtil.verifySign(publicKey, objData, sign);
        System.out.println("本地签名验证：" + flag);
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


    @Data
    @Accessors(chain = true)
    public static class TestReqBody implements Serializable {
        private String fuMerchantNum;

        private String reqReserved;

        private String reserved;

        private String iType;

        private String senderSsn;

    }


}
