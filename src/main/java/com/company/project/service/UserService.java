package com.company.project.service;
import com.company.project.core.Result;
import com.company.project.model.User;
import com.company.project.core.Service;
import com.company.project.vo.LoginVo;

import java.util.List;

public interface UserService extends Service<User> {

    Result logout(Long userId);

    Result login(LoginVo vo);

    Result captcha();

    Result add(User user);

    Result updateUser(User user);

    Result batchSendEmail(String ids);

    Result list(User user);

    Result findAllColumns();

    String randList();

    List<User> findUserById(Long id);

    Result delete(Long id);

    Result detailByToken(Long userId);

    Result sendPasswordCode(String phone);

    Result sendLogCode(String phone);

    Result updatePassword(User user);
}
