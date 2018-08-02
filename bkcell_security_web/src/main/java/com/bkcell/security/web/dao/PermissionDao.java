package com.bkcell.security.web.dao;

import com.bkcell.security.generator.mapper.PermissionMapper;
import com.bkcell.security.generator.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface PermissionDao extends PermissionMapper {

    @Select({
            "select p.* from global_permission_role_tb rp,global_permission_tb p",
            "where rp.PermissionId=p.PermissionId and RoleId=#{roleId, jdbcType=INTEGER}"
    })
    List<Permission> selectAuthInfoByRoleId(@Param(value = "roleId") Integer roleId);

    @Select({
            "SELECT * FROM global_permission_tb"
    })
    List<Permission> selectAllPermission();

    @Select({
            "		SELECT SubMenu subMenu, PermissionDetail permissionDetail,\n" +
                    "        (SELECT ap.SortNo FROM global_permission_tb ap WHERE ap.SubMenu = B.SubMenu ORDER BY ap.SortNo ASC LIMIT 1) sortNo,\n" +
                    "        (SELECT ap.Menu FROM global_permission_tb ap WHERE ap.SubMenu = B.SubMenu ORDER BY ap.SortNo ASC LIMIT 1) menu\n" +
                    "        FROM\n" +
                    "        (SELECT A.SubMenu,\n" +
                    "        GROUP_CONCAT(A.Operate , '|',A.PermissionId, '|', A.HasPermission ORDER BY A.SortNo) PermissionDetail\n" +
                    "        FROM\n" +
                    "        (SELECT\n" +
                    "        \n" +
                    "        p.SubMenu,\n" +
                    "        p.PermissionId,\n" +
                    "        p.SortNo,\n" +
                    "        p.Operate,\n" +
                    "        (\n" +
                    "        CASE WHEN EXISTS (\n" +
                    "        SELECT RpId FROM global_permission_role_tb pr\n" +
                    "        WHERE pr.PermissionId = p.PermissionId AND pr.RoleId = #{roleId})\n" +
                    "        THEN 1 ELSE 0 END\n" +
                    "        ) AS HasPermission\n" +
                    "        FROM global_permission_tb p) as A\n" +
                    "        GROUP BY A.SubMenu) B ORDER BY SortNo ASC"
    })
    List<Map<String, Object>> selectByRoleId(Integer roleId);
}