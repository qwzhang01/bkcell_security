package com.bkcell.security.web.dao.sql;

import cn.hutool.core.util.StrUtil;
import com.bkcell.security.web.vo.user.UserQuery;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class UserSql {

    public String insertRoleSql(Integer userId, Integer[] roleId) {
        String sql = new SQL() {
            {
                INSERT_INTO(" rbac_role_user_tb ");
                INTO_COLUMNS("RoleId", "UserId");
            }
        }.toString();

        StringBuilder sb = new StringBuilder(" VALUES ");
        for (Integer rId : roleId) {
            sb.append(String.format("(%s, %s),", rId, userId));
        }
        String intoSql = sb.substring(0, sb.length() - 1);
        return sql + intoSql;
    }

    public String validUserNameSql(@Param(value = "userName") String userName, @Param(value = "userId") Integer userId) {
        String sql = " select count(*) from global_user_tb r where UserName=#{userName,jdbcType=VARCHAR}";
        if (userId != null && userId != 0) {
            sql += " AND UserId <> #{userId, jdbcType = INTEGER}";
        }
        return sql;
    }

    public String selectUserSql(UserQuery query) {
        String sql = new SQL() {
            {
                SELECT("ui.*");
                SELECT("ar.RoleName");
                SELECT("o.OrgName");

                FROM("global_user_tb ui");
                LEFT_OUTER_JOIN("global_org_tb o on ui.OrgId=o.OrgId");
                LEFT_OUTER_JOIN("(select ur.UserId,group_concat(r.RoleName separator ',') as RoleName from rbac_role_user_tb ur left join rbac_role_tb r on ur.RoleId=r.RoleId  group by ur.UserId) ar on ui.UserId=ar.UserId");

                WHERE("NOT EXISTS(SELECT * from rbac_role_user_tb ru where ru.userid = ui.UserId AND  ru.RoleId = 4) ");

                if (StrUtil.isNotBlank(query.getSearchKey())) {
                    WHERE("(ui.UserName LIKE concat('%', #{searchKey, jdbcType=VARCHAR}, '%') " +
                            "or ui.RealName like concat('%', #{searchKey, jdbcType=VARCHAR}, '%') " +
                            "or ui.ContactWay like concat('%', #{searchKey, jdbcType=VARCHAR}, '%'))");
                }
                if (query.getRoleId() != null && 0 != query.getRoleId()) {
                    WHERE("EXISTS(SELECT * from rbac_role_user_tb ru1 WHERE ru1.userid = ui.UserId and  ru1.RoleId = #{roleId, jdbcType=INTEGER})");
                }
                ORDER_BY("  ui.UserId DESC ");
            }
        }.toString();
        return sql;
    }
}