package com.github.w4o.xx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.ExamChapterEntity;
import com.github.w4o.xx.service.mapper.ExamChapterMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Frank
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExamChapterService extends BaseServiceImpl<ExamChapterMapper, ExamChapterEntity> implements BaseService<ExamChapterEntity> {

    /**
     * 获取分页列表
     *
     * @param examChapter 查询条件
     * @return 分页列表
     */
    public Page<ExamChapterEntity> getPageList(Page<ExamChapterEntity> page, ExamChapterEntity examChapter) {
        LambdaQueryWrapper<ExamChapterEntity> wrapper = ExamChapterEntity.gw();
        // TODO 这里需要补充检索条件
        return this.page(page, wrapper);
    }

    /**
     * 添加或修改时判断是否存在
     *
     * @param examChapter 章节信息
     * @return 是否存在（true/false）
     */
    public boolean exists(ExamChapterEntity examChapter) {
        LambdaQueryWrapper<ExamChapterEntity> wrapper = ExamChapterEntity.gw()
                .eq(ExamChapterEntity::getName, examChapter.getName())
                .eq(ExamChapterEntity::getParentId, examChapter.getParentId());
        if (examChapter.getId() != null) {
            wrapper.ne(ExamChapterEntity::getId, examChapter.getId());
        }
        return baseMapper.exists(wrapper);
    }
}
