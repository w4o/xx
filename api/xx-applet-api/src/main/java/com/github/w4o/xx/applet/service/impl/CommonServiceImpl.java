package com.github.w4o.xx.applet.service.impl;

import com.github.w4o.xx.applet.domain.param.SendSmsCodeParam;
import com.github.w4o.xx.applet.domain.vo.SendSmsCodeVO;
import com.github.w4o.xx.applet.service.CommonService;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.manage.SmsManage;
import com.github.w4o.xx.core.manage.UploadManage;
import com.github.w4o.xx.core.util.BusinessUtils;
import com.github.w4o.xx.core.vo.UploadVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.github.w4o.xx.core.constant.Constant.REDIS_SMS_CODE_PREFIX;

/**
 * @author Frank
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommonServiceImpl implements CommonService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final SmsManage smsManage;
    private final UploadManage uploadManage;

    @Override
    public SendSmsCodeVO sendSmsCode(SendSmsCodeParam param) {
        Long expire = redisTemplate.getExpire(REDIS_SMS_CODE_PREFIX + param.getPhone());
        if (expire != null && expire > 1740L) {
            throw new CustomException(ErrorCode.E2003);
        }
        SendSmsCodeVO.SendSmsCodeVOBuilder resultBuilder = SendSmsCodeVO.builder();
        String code = RandomStringUtils.randomNumeric(6);
        if (BusinessUtils.isDebug()) {
            resultBuilder.code(code);
        } else {
            smsManage.sendSmsCode(param.getPhone(), code);
        }
        redisTemplate.opsForValue().set(REDIS_SMS_CODE_PREFIX + param.getPhone(), code, 30, TimeUnit.MINUTES);
        return resultBuilder.success(true).build();
    }

    @Override
    public void validateSmsCode(String phone, String smsCode) {
        String smsCodeKey = REDIS_SMS_CODE_PREFIX + phone;
        String code = (String) redisTemplate.opsForValue().get(smsCodeKey);
        redisTemplate.delete(Objects.requireNonNull(redisTemplate.keys(smsCodeKey)));
        if (StringUtils.isEmpty(code) || !StringUtils.equalsIgnoreCase(smsCode, code)) {
            throw new CustomException(ErrorCode.E2005);
        }
    }

    @Override
    public UploadVO uploadImage(MultipartFile file) {
        try {
            return uploadManage.upload(file, Arrays.asList(".jpg", ".png", ".jpeg", ".gif"), "applet");
        } catch (IOException e) {
            throw new CustomException(ErrorCode.E9002);
        }
    }
}
