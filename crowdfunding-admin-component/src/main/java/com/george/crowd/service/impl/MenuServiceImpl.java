package com.george.crowd.service.impl;

import com.george.crowd.entity.Menu;
import com.george.crowd.entity.MenuExample;
import com.george.crowd.mapper.MenuMapper;
import com.george.crowd.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author georgeLin
 * @date 2021-04-22-21:50
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getAll() {
        return menuMapper.selectByExample(new MenuExample());
    }

    @Override
    public void saveMenu(Menu menu) {
        menuMapper.insert(menu);
    }

    @Override
    public void updateMenu(Menu menu) {

        // 由于pid没有传入，一定要使用有选择的更新，保证“pid”字段不会被置空
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public void removeMenu(Integer id) {
        menuMapper.deleteByPrimaryKey(id);
    }
}
