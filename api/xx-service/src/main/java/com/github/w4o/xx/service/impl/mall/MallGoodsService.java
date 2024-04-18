package com.github.w4o.xx.service.impl.mall;

import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.MallGoodsEntity;
import com.github.w4o.xx.service.mapper.MallGoodsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品服务实现类
 *
 * @author Frank
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MallGoodsService extends BaseServiceImpl<MallGoodsMapper, MallGoodsEntity> implements BaseService<MallGoodsEntity> {

}
