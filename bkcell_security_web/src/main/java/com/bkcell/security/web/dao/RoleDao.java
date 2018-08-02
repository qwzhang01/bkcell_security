package com.bkcell.security.web.dao;

import com.bkcell.security.generator.mapper.RoleMapper;
import com.bkcell.security.generator.pojo.Role;
import com.bkcell.security.web.dao.sql.RoleSql;
import com.bkcell.security.web.vo.role.RoleQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoleDao extends RoleMapper {
    @Select({
            " select r.* from rbac_role_user_tb ur ",
            " left join rbac_role_tb r on ur.RoleId=r.RoleId ",
            " where UserId=#{userid, jdbcType=INTEGER}"
    })
    List<Role> selectByUserId(@Param(value = "userid") Integer userid);

    @Select({
            "SELECT RoleId, RoleName FROM rbac_role_tb ORDER BY RoleId ASC"
    })
    List<Map<Integer, String>> selectRole();

    @SelectProvider(type = RoleSql.class, method = "selectRoleSql")
    List<Map<String, Object>> selectByQuery(RoleQuery query);

    @SelectProvider(type = RoleSql.class, method = "validRoleName")
    int validRoleName(@Param(value = "roleName") String roleName, @Param(value = "roleId") Integer roleId);

    @Delete({
            " DELETE FROM global_permission_role_tb WHERE RoleId = #{roleId, jdbcType=INTEGER} "
    })
    void deletePermission(Integer roleId);

    @SelectProvider(type = RoleSql.class, method = "insertRolePermissionSql")
    Integer insertRolePermission(Integer roleId, Integer[] permissionId);

    @SelectProvider(type = RoleSql.class, method = "selectUserRoleSql")
    List<Map<Integer, Object>> selectUserRole(Integer userId);
}
