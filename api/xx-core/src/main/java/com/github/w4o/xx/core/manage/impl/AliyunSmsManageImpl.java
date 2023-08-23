package com.github.w4o.xx.core.manage.impl;

import cn.hutool.json.JSONUtil;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.aliyuncs.exceptions.ClientException;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.manage.SmsManage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

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
        Config config = new Config()
                .setAccessKeyId(accessKey)
                .setAccessKeySecret(secretKey);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        Client client;
        try {
            client = new Client(config);
        } catch (Exception e) {
            log.error("短信发送失败: {}", e.getMessage());
            throw new CustomException(ErrorCode.E2004);
        }

        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        // 必填:待发送手机号
        request.setPhoneNumbers(phone);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(sign);
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(tpl);
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\"" + code + "\"}");

        try {
            SendSmsResponse sendSmsResponse = client.sendSms(request);
            log.debug("短信接口返回的数据: {}", JSONUtil.toJsonStr(sendSmsResponse));
        } catch (Exception e) {
            log.error("短信发送失败: {}", e.getMessage());
            throw new CustomException(ErrorCode.E2004);
        }
    }
}
