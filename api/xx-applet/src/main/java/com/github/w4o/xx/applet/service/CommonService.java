package com.github.w4o.xx.applet.service;

import com.github.w4o.xx.applet.param.SendSmsCodeParam;
import com.github.w4o.xx.core.vo.UploadVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author Frank
 */
public interface CommonService {

    /**
     * 发送短信验证码
     *
     * @param param 手机号
     * @return 发送结果
     */
    Map<String, Object> sendSmsCode(SendSmsCodeParam param);

    /**
     * 验证短信验证码
     *
     * @param phone   手机号
     * @param smsCode 短信验证码
     */
    void validateSmsCode(String phone, String smsCode);

    /**
     * 上传图片
     *
     * @param file 图片文件
     * @return 上传结果
     */
    UploadVO uploadImage(MultipartFile file);

}
