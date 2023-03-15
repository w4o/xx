package com.github.w4o.xx.manage.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.entity.MallGoodsCategoryEntity;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.util.AssertUtils;
import com.github.w4o.xx.manage.mapper.MallGoodsCategoryMapper;
import com.github.w4o.xx.manage.param.mall.category.AddGoodsCategoryParam;
import com.github.w4o.xx.manage.param.mall.category.GoodsCategoryPageParam;
import com.github.w4o.xx.manage.service.MallGoodsCategoryService;
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
public class MallGoodsCategoryServiceImpl implements MallGoodsCategoryService {

    private final MallGoodsCategoryMapper mallGoodsCategoryMapper;

    @Override
    public Page<Map<String, Object>> getPageList(GoodsCategoryPageParam param) {
        return null;
    }

    @Override
    public void add(AddGoodsCategoryParam param) {
        // 检查分类名称是否存在
        if (mallGoodsCategoryMapper.existsByCategoryNameAndParentId(param.getCategoryName(), param.getParentId(), null)) {
            throw new CustomException(ErrorCode.E1100);
        }
        MallGoodsCategoryEntity goodsCategory = new MallGoodsCategoryEntity();
        BeanUtils.copyProperties(param, goodsCategory);
        goodsCategory.setEnabled(false);
        mallGoodsCategoryMapper.insert(goodsCategory);
    }

    @Override
    public void update(long id, AddGoodsCategoryParam param) {
        MallGoodsCategoryEntity goodsCategory = mallGoodsCategoryMapper.selectById(id);
        AssertUtils.notNull(goodsCategory);

        // 检查分类名称是否存在
        if (mallGoodsCategoryMapper.existsByCategoryNameAndParentId(param.getCategoryName(), param.getParentId(), id)) {
            throw new CustomException(ErrorCode.E1100);
        }
        BeanUtils.copyProperties(param, goodsCategory);
        mallGoodsCategoryMapper.updateById(goodsCategory);
    }

    @Override
    public void delete(long id) {
        mallGoodsCategoryMapper.deleteById(id);
    }

    @Override
    public void enable(long id) {
        MallGoodsCategoryEntity goodsCategory = mallGoodsCategoryMapper.selectById(id);
        AssertUtils.notNull(goodsCategory);
        goodsCategory.setEnabled(true);
        mallGoodsCategoryMapper.updateById(goodsCategory);
    }

    @Override
    public void disable(long id) {
        MallGoodsCategoryEntity goodsCategory = mallGoodsCategoryMapper.selectById(id);
        AssertUtils.notNull(goodsCategory);
        goodsCategory.setEnabled(false);
        mallGoodsCategoryMapper.updateById(goodsCategory);
    }
}
