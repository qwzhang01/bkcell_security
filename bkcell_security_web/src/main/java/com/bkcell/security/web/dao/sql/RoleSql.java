package com.bkcell.security.web.dao.sql;

import cn.hutool.core.util.StrUtil;
import com.bkcell.security.web.vo.role.RoleQuery;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class RoleSql {

    public String selectUserRoleSql(Integer userId) {
        String sql = "select r.RoleId,r.RoleName,0 as HasRole from rbac_role_tb r where r.RoleId!=4";
        if (userId != null && userId != 0) {
            sql = "select r.RoleId,r.RoleName,(case (select count(*) from rbac_role_user_tb where RoleId=r.RoleId and UserId=#{userId, jdbcType = INTEGER}) when 0 THEN false ELSE TRUE END) as HasRole "
                    + " from rbac_role_tb r where r.RoleId!=4";
        }
        return sql;
    }

    public String insertRolePermissionSql(Integer roleId, Integer[] permissionId) {
        String sql = new SQL() {
            {
                INSERT_INTO(" global_permission_role_tb ");
                INTO_COLUMNS("RoleId", "PermissionId");
            }
        }.toString();

        StringBuilder sb = new StringBuilder(" VALUES ");
        for (Integer pId : permissionId) {
            sb.append(String.format("(%s, %s),", roleId, pId));
        }
        String intoSql = sb.substring(0, sb.length() - 1);
        return sql + intoSql;
    }

    public String validRoleName(@Param(value = "roleName") String roleName, @Param(value = "roleId") Integer roleId) {
        String sql = " select count(*) from rbac_role_tb r where r.RoleName=#{roleName,jdbcType=VARCHAR}";
        if (roleId != null && roleId != 0) {
            sql += " AND r.RoleId <> #{roleId, jdbcType = INTEGER}";
        }
        return sql;
    }

    public String selectRoleSql(RoleQuery query) {
        String sql = new SQL() {
            {
                SELECT(" * ");
                FROM(" rbac_role_tb ");
                if (StrUtil.isNotBlank(query.getSearchKey())) {
                    WHERE(" RoleName LIKE concat('%',#{searchKey, jdbcType=VARCHAR} , '%')");
                }
                ORDER_BY(" RoleId ASC ");
            }
        }.toString();
        return sql;
    }
}
