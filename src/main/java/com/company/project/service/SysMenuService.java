package com.company.project.service;
import com.company.project.model.SysMenu;
import com.company.project.core.Service;

import java.util.List;

public interface SysMenuService extends Service<SysMenu> {

    List<Object> selectMenuByRoleId(Long roleId);
}
