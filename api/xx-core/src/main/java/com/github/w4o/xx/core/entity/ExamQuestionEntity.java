package com.github.w4o.xx.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.w4o.xx.core.base.BaseDataEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 题目表实体
 *
 * @author Frank
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@TableName(value = "exam_question")
public class ExamQuestionEntity extends BaseDataEntity {

    //gw
    public static LambdaQueryWrapper<ExamQuestionEntity> gw() {
        return new LambdaQueryWrapper<>();
    }

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 章节ID
     */
    private Long chapterId;
    /**
     * 知识点ID
     */
    private Long knowledgeId;
    /**
     * 题目类型
     */
    private String questionType;
    /**
     * 题目标题
     */
    private String title;
    /**
     * 题目是否启用
     */
    private Boolean enabled;

}
