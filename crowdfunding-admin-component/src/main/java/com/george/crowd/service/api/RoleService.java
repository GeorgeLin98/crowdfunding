package com.george.crowd.service.api;

import com.george.crowd.entity.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author georgeLin
 * @date 2021-04-15-1:31
 */
public interface RoleService {
    PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword);

    void saveRole(Role role);

    void updateRole(Role role);

    void removeRole(List<Integer> roleIdList);

    List<Role> getAssignedRole(Integer adminId);

    List<Role> getUnAssignedRole(Integer adminId);
}
