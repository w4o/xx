package com.github.w4o.xx.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.w4o.xx.core.base.BaseDataEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 知识点表实体
 *
 * @author Frank
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@TableName(value = "exam_knowledge")
public class ExamKnowledgeEntity extends BaseDataEntity {

    //gw
    public static LambdaQueryWrapper<ExamKnowledgeEntity> gw() {
        return new LambdaQueryWrapper<>();
    }

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 章节ID
     */
    private Long chapterId;
    /**
     * 知识点名称
     */
    private String name;
    /**
     * 是否启用
     */
    private Boolean enabled;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 图标
     */
    private String icon;
}
