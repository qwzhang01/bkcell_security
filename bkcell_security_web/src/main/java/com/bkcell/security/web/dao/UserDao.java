package com.bkcell.security.web.dao;

import com.bkcell.security.generator.mapper.UserMapper;
import com.bkcell.security.generator.pojo.User;
import com.bkcell.security.web.dao.sql.UserSql;
import com.bkcell.security.web.vo.user.UserQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao extends UserMapper {

    @Select({
            "SELECT * FROM global_user_tb WHERE UserName = #{username} LIMIT 1"
    })
    User selectByUserName(String username);

    @SelectProvider(type = UserSql.class, method = "selectUserSql")
    List<Map<String, Object>> selectByQuery(UserQuery query);

    @SelectProvider(type = UserSql.class, method = "validUserNameSql")
    int validUserName(@Param(value = "userName") String userName, @Param(value = "userId") Integer userId);

    @Delete({
            "delete from rbac_role_user_tb where UserId=#{userId, jdbcType=INTEGER}"
    })
    void deleteRole(Integer userId);

    @SelectProvider(type = UserSql.class, method = "insertRoleSql")
    void insertRole(Integer userId, Integer[] roleId);
}
