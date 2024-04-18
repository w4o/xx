package com.github.w4o.xx.service.impl.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.SysMediaEntity;
import com.github.w4o.xx.service.dto.sys.media.MediaDTO;
import com.github.w4o.xx.service.mapper.SysMediaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 系统媒体管理服务实现
 *
 * @author Frank
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysMediaService extends BaseServiceImpl<SysMediaMapper, SysMediaEntity> implements BaseService<SysMediaEntity> {

    /**
     * 获取分页列表
     *
     * @param page      分页参数
     * @param condition 查询参数
     * @return 分页列表
     */
    public Page<MediaDTO> getPageList(Page<SysMediaEntity> page, Map<String, Object> condition) {
        Page<MediaDTO> pageList = baseMapper.findPage(page, condition);
        handlePageRecord(pageList);
        return pageList;
    }
}
