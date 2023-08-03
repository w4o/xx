package com.github.w4o.xx.core.manage.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ObjectMetadata;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.manage.UploadManage;
import com.github.w4o.xx.core.vo.UploadVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * 上传业务组件实现
 *
 * @author Frank
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UploadManageImpl implements UploadManage {

    @Value("${app.oss.domain}")
    private String ossDomain;
    @Value("${app.oss.bucket}")
    private String ossBucket;
    @Value("${app.oss.root-path}")
    private String ossRootPath;
    private final OSS ossClient;

    @Override
    public UploadVO upload(MultipartFile file, List<String> extList, String path) throws IOException {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        assert fileName != null;
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        if (extList != null && !extList.contains(suffixName)) {
            throw new CustomException(ErrorCode.E9001);
        }
        fileName = UUID.randomUUID().toString().replace("-", "") + suffixName;

        LocalDate now = LocalDate.now();

        String filePath = ossRootPath + path + "/" +
                now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth() +
                "/" + fileName;

        String url = ossDomain + "/" + filePath;

        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentType("image/jpg");

        try {
            ossClient.putObject(ossBucket, filePath, file.getInputStream(), meta);
        } catch (OSSException | ClientException e) {
            log.error("OSS上传文件失败", e);
            throw e;
        }

        return UploadVO.builder()
                .filePath(filePath)
                .url(url)
                .build();
    }
}
