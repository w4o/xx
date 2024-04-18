package com.github.w4o.xx.service.impl.exam;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.ExamKnowledgeEntity;
import com.github.w4o.xx.service.mapper.ExamKnowledgeMapper;
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
public class ExamKnowledgeService extends BaseServiceImpl<ExamKnowledgeMapper, ExamKnowledgeEntity> implements BaseService<ExamKnowledgeEntity> {

    /**
     * 获取分页列表
     *
     * @param examKnowledge 查询条件
     * @return 分页列表
     */
    public Page<?> getPageList(ExamKnowledgeEntity examKnowledge) {
        return null;
    }

    /**
     * 添加或修改时判断是否存在
     *
     * @param examKnowledge 知识点信息
     * @return 是否存在（true/false）
     */
    public boolean exists(ExamKnowledgeEntity examKnowledge) {
        LambdaQueryWrapper<ExamKnowledgeEntity> wrapper = ExamKnowledgeEntity.gw()
                .eq(ExamKnowledgeEntity::getName, examKnowledge.getName())
                .eq(ExamKnowledgeEntity::getChapterId, examKnowledge.getChapterId());
        if (examKnowledge.getId() != null) {
            wrapper.ne(ExamKnowledgeEntity::getId, examKnowledge.getId());
        }
        return baseMapper.exists(wrapper);
    }
}
