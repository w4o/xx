package com.github.w4o.xx.manage.param.sys.user;

import com.github.w4o.xx.core.base.BasePageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户分页查询请求参数
 *
 * @author Frank
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPageParam extends BasePageParam {
    private String username;
}
