package com.github.w4o.xx.core.manage.impl;

import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.manage.UploadManage;
import com.github.w4o.xx.core.vo.UploadVO;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * @author Frank
 */
@Slf4j
@Component
@ConditionalOnProperty(name = "app.upload.type", havingValue = "cos")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CosUploadManageImpl implements UploadManage {

    @Value("${app.upload.cos.access-key")
    private String accessKey;
    @Value("${app.upload.cos.secret-key")
    private String secretKey;
    @Value("${app.upload.cos.region")
    private String region;
    @Value("${app.upload.cos.bucket")
    private String bucket;
    @Value("${app.upload.cos.root-path")
    private String rootPath;
    @Value("${app.upload.cos.domain}")
    private String domain;

    @Override
    public UploadVO upload(MultipartFile file, List<String> extList, String path) throws IOException {

        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(accessKey, secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://www.qcloud.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setRegion(new Region(region));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);

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

        String filePath = rootPath + path + "/" +
                now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth() +
                "/" + fileName;


        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, filePath, file.getInputStream(), objectMetadata);
        PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);

        log.info(putObjectResult.getVersionId());
        String url = domain + filePath;

        return UploadVO.builder()
                .filePath(filePath)
                .url(url)
                .build();
    }
}
