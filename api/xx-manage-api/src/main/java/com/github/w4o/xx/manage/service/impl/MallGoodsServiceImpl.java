package com.github.w4o.xx.manage.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.manage.param.mall.goods.AddGoodsParam;
import com.github.w4o.xx.manage.param.mall.goods.GoodsPageParam;
import com.github.w4o.xx.manage.service.MallGoodsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 商品服务实现类
 *
 * @author Frank
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MallGoodsServiceImpl implements MallGoodsService {

    @Override
    public Page<Map<String, Object>> getPageList(GoodsPageParam param) {
        return null;
    }

    @Override
    public void add(AddGoodsParam param) {

    }

    @Override
    public void update(long id, AddGoodsParam param) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void updateStatus(long id, int status) {

    }
}
