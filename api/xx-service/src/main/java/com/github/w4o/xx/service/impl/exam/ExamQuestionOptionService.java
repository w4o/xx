package com.github.w4o.xx.service.impl.exam;

import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.ExamQuestionOptionEntity;
import com.github.w4o.xx.service.mapper.ExamQuestionOptionMapper;
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
public class ExamQuestionOptionService extends BaseServiceImpl<ExamQuestionOptionMapper, ExamQuestionOptionEntity> implements BaseService<ExamQuestionOptionEntity> {
}
