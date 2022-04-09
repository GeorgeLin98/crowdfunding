package com.george.crowd.service.api;

import com.george.crowd.entity.Menu;

import java.util.List;

/**
 * @author georgeLin
 * @date 2021-04-22-21:51
 */
public interface MenuService {
    List<Menu> getAll();

    void saveMenu(Menu menu);

    void updateMenu(Menu menu);

    void removeMenu(Integer id);
}
