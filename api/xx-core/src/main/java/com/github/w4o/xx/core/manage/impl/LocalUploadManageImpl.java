package com.github.w4o.xx.core.manage.impl;

import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.manage.UploadManage;
import com.github.w4o.xx.core.vo.UploadVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author Frank
 */
@Slf4j
@Component
@ConditionalOnProperty(name = "app.upload.type", havingValue = "local")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LocalUploadManageImpl implements UploadManage {

    @Value("${app.upload.local.url}")
    private String configUrl;

    @Value("${app.upload.local.path}")
    private String configPath;

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

        String url = configUrl + "/" + path + "/" + fileName;

        File dest = new File(configPath + "/" + path + "/");
        try {
            // 检测是否存在目录
            if (!dest.exists() && !dest.isDirectory()) {
                dest.mkdirs();
            }

            File target = new File(dest, fileName);
            file.transferTo(target);
        } catch (IOException e) {
            log.error("上传文件失败", e);
            throw e;
        }

        return UploadVO.builder().filePath(fileName).url(url).build();
    }
}
