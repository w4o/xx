package com.github.w4o.xx.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.w4o.xx.core.base.BaseDataEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 章节表实体
 *
 * @author Frank
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@TableName(value = "exam_chapter")
public class ExamChapterEntity extends BaseDataEntity {

    //gw
    public static LambdaQueryWrapper<ExamChapterEntity> gw() {
        return new LambdaQueryWrapper<>();
    }

    @Serial
    private static final long serialVersionUID = 1L;
    
    /**
     * 父ID
     */
    private Long parentId;
    /**
     * 章节名
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
}
