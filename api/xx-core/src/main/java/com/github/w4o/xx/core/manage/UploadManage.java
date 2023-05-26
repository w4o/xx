package com.github.w4o.xx.core.manage;

import com.github.w4o.xx.core.vo.UploadVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 上传业务组件接口
 *
 * @author Frank
 */
public interface UploadManage {

    /**
     * 上传文件
     *
     * @param file    文件
     * @param extList 文件后缀名
     * @param path    文件路径
     * @return 上传结果
     */
    UploadVO upload(MultipartFile file, List<String> extList, String path) throws IOException;
}
