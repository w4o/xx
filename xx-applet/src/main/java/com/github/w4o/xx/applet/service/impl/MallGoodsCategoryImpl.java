package com.github.w4o.xx.applet.service.impl;

import com.github.w4o.xx.applet.dto.mall.GoodsCategoryDTO;
import com.github.w4o.xx.applet.service.MallGoodsCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Frank
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MallGoodsCategoryImpl implements MallGoodsCategoryService {

    @Override
    public List<GoodsCategoryDTO> list() {
        return null;
    }
}
