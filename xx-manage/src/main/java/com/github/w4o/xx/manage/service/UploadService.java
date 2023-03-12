package com.github.w4o.xx.manage.service;

import com.github.w4o.xx.core.vo.UploadVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传服务接口
 *
 * @author Frank
 */
public interface UploadService {

    /**
     * 上传图片
     *
     * @param file 文件
     * @return 响应信息
     */
    UploadVO uploadImage(MultipartFile file);
}
