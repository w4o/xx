package com.github.w4o.xx.core.manage.impl;

import cn.hutool.json.JSONUtil;
import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.manage.SmsManage;
import darabonba.core.client.ClientOverrideConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Frank
 */
@Slf4j
@Component
@ConditionalOnProperty(name = "app.sms.type", havingValue = "aliyun")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AliyunSmsManageImpl implements SmsManage {

    @Value("${app.sms.aliyun.sign}")
    private String sign;
    @Value("${app.sms.aliyun.tpl}")
    private String tpl;
    @Value("${app.sms.aliyun.access-key}")
    private String accessKey;
    @Value("${app.sms.aliyun.secret-key}")
    private String secretKey;

    @Override
    public void sendSmsCode(String phone, String code) {

        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId(accessKey)
                .accessKeySecret(secretKey)
                .build());

        AsyncClient client = AsyncClient.builder()
                .region("cn-beijing")
                .credentialsProvider(provider)
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                .setEndpointOverride("dysmsapi.aliyuncs.com")
                )
                .build();

        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .phoneNumbers(phone)
                .signName(sign)
                .templateCode(tpl)
                .templateParam("{\"code\":\"" + code + "\"}")
                .build();

        CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
        try {
            SendSmsResponse resp = response.get();
            log.debug("短信接口返回的数据: {}", JSONUtil.toJsonStr(resp));
        } catch (InterruptedException | ExecutionException e) {
            log.error("短信发送失败: {}", e.getMessage());
            throw new CustomException(ErrorCode.E2004);
        }
    }
}
