package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.Columns;
import com.company.project.model.Tables;
import com.company.project.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends Mapper<User> {

    User findUserByUserName(@Param("userName") String userName, @Param("id") Long id);

    List<User> selectUserListById(String ids);

    List<User> list(User user);

    List<Tables> selectAllTable(String databaseName);

    List<Columns> selectAllColumnsByTableName(@Param("databaseName") String databaseName,@Param("tableName") String tableName);

    String randList();

    List<User> findUserById(Long userId);

    void updatePassword(User user);
}
