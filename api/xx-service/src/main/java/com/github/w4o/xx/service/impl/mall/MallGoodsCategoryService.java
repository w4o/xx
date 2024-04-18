package com.github.w4o.xx.service.impl.mall;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.MallGoodsCategoryEntity;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.util.AssertUtils;
import com.github.w4o.xx.service.mapper.MallGoodsCategoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Frank
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MallGoodsCategoryService extends BaseServiceImpl<MallGoodsCategoryMapper, MallGoodsCategoryEntity> implements BaseService<MallGoodsCategoryEntity> {

    private final MallGoodsCategoryMapper mallGoodsCategoryMapper;

    /**
     * 获取分页列表
     *
     * @param page   分页对象
     * @param entity 查询条件
     * @return 分页列表
     */
    public Page<Map<String, Object>> getPageList(Page<MallGoodsCategoryEntity> page, MallGoodsCategoryEntity entity) {
        return null;
    }

    /**
     * 添加商品分类
     *
     * @param entity 商品分类实体
     */
    public void add(MallGoodsCategoryEntity entity) {
        // 检查分类名称是否存在
        if (mallGoodsCategoryMapper.existsByCategoryNameAndParentId(entity.getCategoryName(), entity.getParentId(), null)) {
            throw new CustomException(ErrorCode.E1100);
        }
        mallGoodsCategoryMapper.insert(entity);
    }

    /**
     * 修改商品分类
     *
     * @param entity 请求参数
     */
    public void update(MallGoodsCategoryEntity entity) {
        MallGoodsCategoryEntity goodsCategory = mallGoodsCategoryMapper.selectById(entity.getId());
        AssertUtils.notNull(goodsCategory);

        // 检查分类名称是否存在
        if (mallGoodsCategoryMapper.existsByCategoryNameAndParentId(entity.getCategoryName(), entity.getParentId(), entity.getId())) {
            throw new CustomException(ErrorCode.E1100);
        }
        BeanUtils.copyProperties(entity, goodsCategory);
        mallGoodsCategoryMapper.updateById(goodsCategory);
    }

}
