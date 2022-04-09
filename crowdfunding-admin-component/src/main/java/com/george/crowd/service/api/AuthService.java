package com.george.crowd.service.api;

import com.george.crowd.entity.Auth;

import java.util.List;
import java.util.Map;

/**
 * @author georgeLin
 * @date 2021-04-22-22:39
 */
public interface AuthService {
    List<Auth> getAll();

    List<Integer> getAssignedAuthIdByRoleId(Integer roleId);

    void saveRoleAuthRelathinship(Map<String, List<Integer>> map);

    List<String> getAssignedAuthNameByAdminId(Integer adminId);
}
