package com.github.w4o.xx.manage.service.impl;

import com.github.w4o.xx.core.entity.SysMediaEntity;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.manage.UploadManage;
import com.github.w4o.xx.core.vo.UploadVO;
import com.github.w4o.xx.manage.service.UploadService;
import com.github.w4o.xx.service.mapper.SysMediaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

import static com.github.w4o.xx.core.entity.SysMediaEntity.TYPE_IMAGE;

/**
 * 文件上传服务实现
 *
 * @author Frank
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UploadServiceImpl implements UploadService {

    private final UploadManage uploadManage;
    private final SysMediaMapper sysMediaMapper;

    @Override
    public UploadVO uploadImage(MultipartFile file, Long categoryId) {
        try {
            UploadVO vo = uploadManage.upload(file, Arrays.asList(".jpg", ".png", ".jpeg", ".gif"), "image");

            // 插入媒体信息
            SysMediaEntity entity = new SysMediaEntity();
            entity.setMediaCategoryId(categoryId);
            entity.setTitle(file.getOriginalFilename());
            entity.setUrl(vo.getUrl());
            entity.setType(TYPE_IMAGE);
            sysMediaMapper.insert(entity);

            return vo;
        } catch (IOException e) {
            throw new CustomException(ErrorCode.E9002);
        }
    }
}
