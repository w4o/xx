package com.github.w4o.xx.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.w4o.xx.core.base.BaseDataEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 题目选项表实体
 *
 * @author Frank
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@TableName(value = "exam_question_option")
public class ExamQuestionOptionEntity extends BaseDataEntity {

    //gw
    public static LambdaQueryWrapper<ExamQuestionOptionEntity> gw() {
        return new LambdaQueryWrapper<>();
    }

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 题目ID
     */
    private Long questionId;

    /**
     * 选项内容
     */
    private String content;

    /**
     * 是否正确
     */
    private Boolean correct;
}
