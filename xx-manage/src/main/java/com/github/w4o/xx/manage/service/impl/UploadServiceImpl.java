package com.github.w4o.xx.manage.service.impl;

import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.manage.UploadManage;
import com.github.w4o.xx.core.vo.UploadVO;
import com.github.w4o.xx.manage.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

/**
 * 文件上传服务实现
 *
 * @author Frank
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UploadServiceImpl implements UploadService {

    private final UploadManage uploadManage;

    @Override
    public UploadVO uploadImage(MultipartFile file) {
        try {
            return uploadManage.upload(file, Arrays.asList(".jpg", ".png", ".jpeg", ".gif"), "image");
        } catch (IOException e) {
            throw new CustomException(ErrorCode.E9002);
        }
    }
}
